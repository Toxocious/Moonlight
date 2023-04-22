package net.swordie.ms.handlers.user;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterCollection;
import net.swordie.ms.client.character.MonsterCollectionExploration;
import net.swordie.ms.client.character.PortableChair;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.potential.CharacterPotentialMan;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.runestones.RuneStoneAckType;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.adventurer.warrior.DarkKnight;
import net.swordie.ms.client.jobs.adventurer.warrior.Hero;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.WildHunter;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.MonsterCollectionData;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Instance;
import net.swordie.ms.world.field.Portal;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static net.swordie.ms.enums.ChatType.SystemNotice;

public class UserHandler {

    private static final Logger log = Logger.getLogger(UserHandler.class);


    @Handler(op = InHeader.USER_MOVE)
    public static void handleUserMove(Char chr, InPacket inPacket) {
        Position oldPos = chr.getPosition();
        Field field = chr.getField();
        // CVecCtrlUser::EndUpdateActive
        byte fieldKey = inPacket.decodeByte();
        inPacket.decodeInt(); // ? something with field
        inPacket.decodeInt(); // tick
        inPacket.decodeByte(); // ? doesn't get set at all
        // CMovePathCommon::Encode
        MovementInfo movementInfo = new MovementInfo(inPacket);
        movementInfo.applyTo(chr);
        field.checkCharInAffectedAreas(chr);
        field.broadcastPacket(UserRemote.move(chr, movementInfo), chr);
        Char copy = chr.getCopy();
        if (copy != null) {
            chr.write(UserRemote.move(copy, movementInfo));
        }
        if (chr.getPosition().getY() > 5000) {
            // failsafe when the char falls outside of the map
            Portal portal = field.getDefaultPortal();
            Position position = new Position(portal.getX(), portal.getY());
            chr.setPosition(position);
            chr.write(FieldPacket.teleport(position, chr));
        }
        // client has stopped moving. this might not be the best way
        if (chr.getMoveAction() == 4 || chr.getMoveAction() == 5) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            for (int skill : Job.REMOVE_ON_STOP) {
                if (tsm.hasStatBySkillId(skill)) {
                    tsm.removeStatsBySkill(skill);
                }
            }
        }
        if (chr.hasSkill(150000017) || chr.hasSkill(80000268)) {
            chr.getJobHandler().handleTideOfBattle(oldPos, chr.getPosition());
        }

        if (JobConstants.isHero(chr.getJob())) {
            if (chr.getField().getSummons().stream().anyMatch(s -> s.getSkillID() == Hero.BURNING_SOUL_BLADE_STATIONARY && s.getChr() == chr)) {
                Summon summon = ((Hero) chr.getJobHandler()).getSoulBlade();
                boolean isInsideAA = summon.getPosition().getRectAround(new Rect(-600, -600, 600, 600)).hasPositionInside(chr.getPosition());
                if (!isInsideAA) {
                    ((Hero) chr.getJobHandler()).changeSoulBlade();
                }
            }
        }
    }



    @Handler(op = InHeader.USER_RENAME_REQUEST)
    public static void handleUserRenameRequest(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // 1
        inPacket.decodeByte(); // 1
        inPacket.decodeInt(); // cost
        String currentName = inPacket.decodeString();
        String newName = inPacket.decodeString();
        int result = 0, itemID = 0;
        if (newName.equalsIgnoreCase(currentName) || !GameConstants.isValidName(chr.getClient(), newName)) {
            result = 6;
        } else {
            try {

                if (Char.getFromDBByName(newName) != null) {
                    result = 7;
                } else if (chr.hasItem(4034803)) {
                    itemID = 4034803;
                } else {
                    result = 3;
                }
            } catch (Exception e) {
                result = 1;
                e.printStackTrace();
            } finally {

            }
            if (result == 0) {
                chr.consumeItem(itemID, 1);
                chr.getAvatarData().getCharacterStat().setName(newName);
            }
        }
        chr.write(UserLocal.renameResult(result, 0));
    }



    @Handler(op = InHeader.USER_RENAME_SPW_CHECK)
    public static void handleUserRenameSpwCheck(Char chr, InPacket inPacket) {
        String pic = inPacket.decodeString();
        inPacket.decodeInt();
        User user = chr.getUser();
        if (!Util.isStringBCrypt(user.getPic())) {
            user.setPic(BCrypt.hashpw(user.getPic(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
        }
        int result;
        if (BCrypt.checkpw(pic, user.getPic())) {
            if (chr.hasItem(4034803)) {
                result = 9; // Open UI
            } else {
                result = 3;
            }
        } else {
            result = 10;
        }
        chr.write(UserLocal.renameResult(result, 4034803));
    }



    @Handler(op = InHeader.USER_HIT)
    public static void handleUserHit(Char chr, InPacket inPacket) {
        chr.getJobHandler().handleHit(inPacket);
    }


    @Handler(op = InHeader.USER_GROWTH_HELPER_REQUEST)
    public static void handleUserGrowthRequestHelper(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
            chr.dispose();
            return;
        }
        short status = inPacket.decodeShort();
        if (status == 0) {
            // TODO: verify that this map is actually valid, otherwise players can warp to pretty much anywhere they want
            int mapleGuideMapId = inPacket.decodeInt();
            Field toField = chr.getClient().getChannelInstance().getField(mapleGuideMapId);
            if (toField == null || (toField.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
                chr.dispose();
                return;
            }
            chr.warp(toField);
        }
        if (status == 2) {
            //TODO wtf happens here
            //int write 0
            //int something?
        }

    }

    @Handler(op = InHeader.FUNC_KEY_MAPPED_MODIFIED)
    public static void handleFuncKeyMappedModified(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int updateType = inPacket.decodeInt();
        switch (updateType) {
            case 0:
                FuncKeyMap funcKeyMap = chr.getFuncKeyMap();
                int size = inPacket.decodeInt();
                for (int i = 0; i < size; i++) {
                    int index = inPacket.decodeInt();
                    byte type = inPacket.decodeByte();
                    int value = inPacket.decodeInt();
                    if (JobConstants.isBeastTamer(chr.getJob())) {
                        int keyMap = SkillConstants.getBeastFromSkill(value).getValue();
                        funcKeyMap = chr.getFuncKeyMaps().get(keyMap);
                    }
                    funcKeyMap.putKeyBinding(index, type, value);
                }
                break;
            case 1: // HP potion
                break;
        }
    }


    @Handler(op = InHeader.USER_CHARACTER_INFO_REQUEST)
    public static void handleUserCharacterInfoRequest(Client c, InPacket inPacket) throws IOException {
        Char chr = c.getChr();
        Field field = chr.getField();
        inPacket.decodeInt(); // tick
        int requestID = inPacket.decodeInt();
        Char requestChar = field.getCharByID(requestID);
        if (requestChar == null) {
            chr.chatMessage(SystemNotice, "The character you tried to find could not be found.");
        } else {
            c.write(FieldPacket.characterInfo(requestChar));

            BufferedImage bImage = ImageIO.read(new File(ServerConstants.RESOURCES_DIR + "/farm.jpeg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpeg", bos);
            byte[] img = bos.toByteArray();
            requestChar.write(UserLocal.getPhotoResult(c, img));

        }
    }


    @Handler(op = InHeader.EVENT_UI_REQ)
    public static void handleEventUiReq(Client c, InPacket inPacket) {
        //TODO: get opcodes for CUIContext::OnPacket
    }


    @Handler(op = InHeader.BATTLE_RECORD_ON_OFF_REQUEST)
    public static void handleBattleRecordOnOffRequest(Client c, InPacket inPacket) {
        // CBattleRecordMan::RequestOnCalc
        boolean on = inPacket.decodeByte() != 0;
        boolean isNew = inPacket.decodeByte() != 0;
        boolean clear = inPacket.decodeByte() != 0;
        c.getChr().setBattleRecordOn(on);
        c.write(BattleRecordMan.serverOnCalcRequestResult(on));
    }

    @Handler(op = InHeader.USER_SIT_REQUEST)
    public static void handleUserSitRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();

        int fieldSeatId = inPacket.decodeShort();

        var chair = chr.getChair();

        int itemID = chair != null ? chair.getItemID() : 0;

        switch (itemID / 100) {
            case 30162 -> {
            }
            case 30161 -> {

            }
        }

        chr.setChair(new PortableChair(chr, 0, ChairType.None));

        chr.write(FieldPacket.sitResult(chr.getId(), fieldSeatId));
        field.broadcastPacket(UserRemote.remoteSetActivePortableChair(chr.getId(), chr.getChair()), chr);
        Char copy = chr.getCopy();
        if (copy != null) {
            chr.write(UserRemote.remoteSetActivePortableChair(copy.getId(), chr.getChair()));
        }

        chr.dispose();
    }

    @Handler(op = InHeader.USER_PORTABLE_CHAIR_SIT_REQUEST)
    public static void handleUserPortableChairSitRequest(Char chr, InPacket inpacket) {
        Field field = chr.getField();
        int fieldId = inpacket.decodeInt(); // fieldId
        System.out.println("field id: " + fieldId);
        int itemId = inpacket.decodeInt(); // item id
        System.out.println("item id: " + itemId);
        int pos = inpacket.decodeInt(); // setup position
        System.out.println("pos : " + pos);
        byte chairBag = inpacket.decodeByte(); // is PortableChair in a bag
        System.out.println("chair bag: " + chairBag);


        PortableChair chair = new PortableChair(chr, itemId, ChairType.NormalChair);


        Position charPos = inpacket.decodePositionInt();
        System.out.println("char pos: " + charPos);


        int unknown = inpacket.decodeInt();

        int randInt = inpacket.decodeInt(); // rand Int
        byte randByte = inpacket.decodeByte(); // rand Byte

        chr.setChair(chair);
        field.broadcastPacket(UserRemote.remoteSetActivePortableChair(chr.getId(), chr.getChair()));
        System.out.println("cha: " + charPos);
        chr.dispose();
    }


    @Handler(op = InHeader.USER_REVIVE_REQUEST)
    public static void handleUserReviveRequest(Char chr, InPacket inPacket) {
        byte type = inPacket.decodeByte(); // 0: normal, 7: forced by revive timer

        Instance instance = chr.getInstance();
        Field field = chr.getField();
        if (!field.isReviveCurFieldOfNoTransfer() || chr.getHP() > 0) {
            if (instance != null) {
                instance.clear();
            }
            return;
        }
        int x, y;
        if (field.getReviveCurFieldOfNoTransferPoint() != null) {
            x = field.getReviveCurFieldOfNoTransferPoint().getX();
            y = field.getReviveCurFieldOfNoTransferPoint().getY();
        } else {
            Portal sp = chr.getField().getPortalByName("sp");
            if (sp == null) {
                return;
            }
            x = sp.getX();
            y = sp.getY();
        }
        // need to correct
        int returnMap = chr.getField().getReturnMap();
        int deathCount = chr.getDeathCount();
        if (deathCount > 0) {
            deathCount--;
            chr.setDeathCount(deathCount);
            chr.write(UserLocal.deathCountInfo(deathCount));
            chr.write(UserLocal.reviveOnCurFieldAtPoint(x, y));
            chr.heal(chr.getMaxHP());
            chr.setBuffProtector(false);
        } else {
            if (instance != null) {
                instance.removeChar(chr);
            }
            chr.heal(50);
            chr.warp(chr.getOrCreateFieldByCurrentInstanceType(returnMap));
        }
    }

    @Handler(op = InHeader.USER_TOWER_CHAIR_SETTING)
    public static void handleUserTowerChairSetting(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int unk = inPacket.decodeInt();

        QuestManager qm = chr.getQuestManager();
        Quest q = qm.getQuests().getOrDefault(QuestConstants.TOWER_CHAIR, null);
        if (q == null) {
            q = new Quest(QuestConstants.TOWER_CHAIR, QuestStatus.Started);
            qm.addQuest(q);
        }

        for (int i = 0; i < 6; i++) {
            q.setProperty(String.format("%d", i), inPacket.decodeInt());
        }

        chr.write(WvsContext.message(MessageType.QUEST_RECORD_EX_MESSAGE, q.getQRKey(), q.getQRValue(), (byte) 0));
        chr.setChair(new PortableChair(chr, 0, ChairType.None));
        field.broadcastPacket(UserRemote.remoteSetActivePortableChair(chr.getId(), chr.getChair()));
        Char copy = chr.getCopy();
        if (copy != null) {
            chr.write(UserRemote.remoteSetActivePortableChair(copy.getId(), chr.getChair()));
        }
        chr.write(WvsContext.towerChairSettingResult());
        chr.dispose();
    }


    @Handler(op = InHeader.USER_EMOTION)
    public static void handleUserEmotion(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int emotion = inPacket.decodeInt();
        int duration = inPacket.decodeInt();
        boolean byItemOption = inPacket.decodeByte() != 0;
        if (GameConstants.isValidEmotion(emotion)) {
            chr.getField().broadcastPacket(UserRemote.emotion(chr.getId(), emotion, duration, byItemOption), chr);
        }
    }

    @Handler(op = InHeader.USER_ACTIVATE_EFFECT_ITEM)
    public static void handleUserActivateEffectItem(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int itemId = inPacket.decodeInt();
        if (chr.hasItem(itemId)) {
            chr.setActiveEffectItemID(itemId);
        }
    }

    @Handler(op = InHeader.USER_SOUL_EFFECT_REQUEST)
    public static void handleUserSoulEffectRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        boolean set = inPacket.decodeByte() != 0;
        chr.getField().broadcastPacket(UserPacket.SetSoulEffect(chr.getId(), set));
    }



    @Handler(op = InHeader.MONSTER_BOOK_MOB_INFO)
    public static void handleMonsterBookMobInfo(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        int cardID = inPacket.decodeInt();
        ItemInfo ii = ItemData.getItemInfoByID(cardID);
        Mob mob = MobData.getMobById(ii.getMobID());
        if (mob != null) {
            // TODO Figure out which packet to send
        }
    }

    @Handler(op = InHeader.USER_REQUEST_CHARACTER_POTENTIAL_SKILL_RAND_SET_UI)
    public static void handleUserRequestCharacterPotentialSkillRandSetUi(Char chr, InPacket inPacket) {
        // what a name
        int cost = GameConstants.CHAR_POT_RESET_COST;
        int rate = inPacket.decodeInt();
        int size = inPacket.decodeInt();
        CharacterPotentialMan cpm = chr.getPotentialMan();
        Map<Byte, CharacterPotential> lockedLines = new HashMap<>();
        for (int i = 0; i < size; i++) {
            byte key = (byte) inPacket.decodeInt();
            lockedLines.put(key, cpm.getPotentialByKey(key));
            if (lockedLines.size() == 0) {
                cost += GameConstants.CHAR_POT_LOCK_1_COST;
            } else {
                cost += GameConstants.CHAR_POT_LOCK_2_COST;
            }
        }
        boolean locked = rate > 0;
        if (locked) {
            cost += GameConstants.CHAR_POT_GRADE_LOCK_COST;
        }
        if (cost > chr.getHonorExp()) {
            chr.chatMessage("You do not have enough honor exp (locking a grade costs 10k).");
            chr.getOffenseManager().addOffense(String.format("Character %d tried to reset honor without having enough exp (required %d, has %d)",
                    chr.getId(), cost, chr.getHonorExp()));
            chr.dispose();
            return;
        }
        chr.addHonorExp(-cost);

        boolean gradeUp = !locked && Util.succeedProp(GameConstants.BASE_CHAR_POT_UP_RATE);
        boolean gradeDown = !locked && Util.succeedProp(GameConstants.BASE_CHAR_POT_DOWN_RATE);
        byte grade = cpm.getGrade();
        // update grades
        if (grade < CharPotGrade.Legendary.ordinal() && gradeUp) {
            grade++;
        } else if (grade > CharPotGrade.Rare.ordinal() && gradeDown) {
            grade--;
        }
        // set new potentials that weren't locked
        List<CharacterPotential> potentials = CharacterPotentialMan.generateRandomPotential(3, grade, false, lockedLines);
        for (CharacterPotential cp : potentials) {
            cpm.addPotential(cp);
        //    chr.chatMessage("Key:" + cp.getKey() + " Grade:" + cp.getGrade() + " SkillID:" + cp.getSkillID() + "(" + StringData.getSkillStringById(cp.getSkillID()).getName() + ") SLV:" + cp.getSlv());
        }
    }

    @Handler(op = InHeader.RUNE_STONE_USE_REQ)
    public static void handleRuneStoneUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int runestoneSkillId = RuneStone.SEALED_RUNE_POWER;

        int unknown = inPacket.decodeInt(); // unknown
        RuneType runeType = RuneType.getByVal((byte) inPacket.decodeInt());

        // User is on RuneStone Cooldown
        if (!chr.hasSkillOnCooldown(runestoneSkillId)) {
            int minLevel = chr.getField().getMobs().stream().mapToInt(m -> m.getForcedMobStat().getLevel()).min().orElse(0);

            // Rune is too strong for user
            if ((minLevel - 10) > chr.getStat(Stat.level)) {
                chr.write(FieldPacket.runeStoneUseAck(RuneStoneAckType.TooStrongForYouToHandle, 0));
                return;
            }

            // Send Arrow Message
            chr.write(FieldPacket.runeStoneUseAck(RuneStoneAckType.ShowArrows, 0));
        } else {
            long cooltime = chr.getRemainingCoolTime(runestoneSkillId);
            long minutes = (cooltime / 60000);
            long seconds = (cooltime / 1000);
            chr.chatScriptMessage("You cannot use another Rune for "
                    + (minutes > 0
                    ? minutes + " minute" + (minutes > 1 ? "s" : "") + " and " + (seconds - (minutes * 60)) + " second" + ((seconds - (minutes * 60)) > 1 ? "s" : "") + ""
                    : seconds + " second" + (seconds > 1 ? "s" : "")));

            //chr.write(FieldPacket.runeStoneUseAck(RuneStoneAckType.RuneDelayTime, (int) seconds));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.RUNE_STONE_USE_RES)
    public static void handleRuneStoneUseResult(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        //B6 89 D2 1A DF 00 00 00 27 FD FF FF 0F 00 00 00 01 00 00 00 6D E6 FE EB |
        if (inPacket.decodeByte() == 1) {
            int fieldID = inPacket.decodeInt(); // is supposed to be map id... but maybe from RuneStoneUseAck packet :(
            int unk = inPacket.decodeInt(); // ?
            Position pos = inPacket.decodePositionInt();
            boolean left = inPacket.decodeInt() == 1;
            int unk2 = inPacket.decodeInt(); // ?
            RuneStone runeStone = field.getRuneStone();
            if (runeStone == null || fieldID != 450005430 || unk != 0xDF || unk2 != 0xEBFEE66D // TODO
                    || !runeStone.getPosition().getRectAround(new Rect(-150, -100, 150, 100)).hasPositionInside(pos)) {
                chr.write(FieldPacket.runeStoneUseAck(RuneStoneAckType.MustBeCloserToDoThat, 0));
                return;
            }
            field.useRuneStone(c, runeStone);
            runeStone.activateRuneStoneEffect(chr);
            chr.setRuneCooldown(System.currentTimeMillis());
            chr.addSkillCoolTime(RuneStone.SEALED_RUNE_POWER, GameConstants.RUNE_COOLDOWN_TIME * 60 * 1000);
            chr.setRuneCooldownVisual(GameConstants.RUNE_COOLDOWN_TIME * 60);
        } else {
            inPacket.decodeString();
            inPacket.decodeInt();
            inPacket.decodeInt();
        }
    }

    @Handler(op = InHeader.RUNE_STONE_USE_CHK)
    public static void handleRuneStoneUseChk(Char chr, InPacket inPacket) {
        // TODO
        int step = inPacket.decodeInt(); // 0->1->2->3
        int num = inPacket.decodeInt(); // 0~3
    }


    @Handler(op = InHeader.RUNE_STONE_SKILL_REQ)
    public static void handleRuneStoneSkillRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        inPacket.decodeInt(); // new unknown

        RuneStone runeStone = field.getRuneStone();
        if (runeStone == null) {
            return;
        }
        field.useRuneStone(c, runeStone);
        runeStone.activateRuneStoneEffect(chr);
        chr.setRuneCooldown(System.currentTimeMillis());
        chr.addSkillCoolTime(RuneStone.SEALED_RUNE_POWER, GameConstants.RUNE_COOLDOWN_TIME * 60 * 1000);
        chr.setRuneCooldownVisual(GameConstants.RUNE_COOLDOWN_TIME * 60);
       // chr.dispose();
    }

    @Handler(op = InHeader.MONSTER_COLLECTION_EXPLORE_REQ)
    public static void handleMonsterCollectionExploreReq(Char chr, InPacket inPacket) {
        int region = inPacket.decodeInt();
        int session = inPacket.decodeInt();
        int group = inPacket.decodeInt();
        int key = region * 10000 + session * 100 + group;
        Account account = chr.getAccount();
        MonsterCollection mc = account.getMonsterCollection();
        MonsterCollectionExploration mce = mc.getExploration(region, session, group);
        boolean complete = mc.isComplete(region, session, group);
        if (complete && mce == null) {
            // starting an exploration
            if (mc.getOpenExplorationSlots() <= 0) {
                chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.NotEnoughExplorationSlots, null, 0));
                return;
            }
            mce = mc.createExploration(region, session, group);
            mc.addExploration(mce);
            chr.write(UserLocal.collectionRecordMessage(mce.getPosition(), mce.getValue(true)));
            chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.ExploreBegin, null, 0));
        } else {
            // trying to start an incomplete/already exploring group
            chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.NoMonstersForExploring, null, 0));
        }
        chr.dispose(); // still required even if you send a collection result
    }

    @Handler(op = InHeader.MONSTER_COLLECTION_COMPLETE_REWARD_REQ)
    public static void handleMonsterCollectionCompleteRewardReq(Char chr, InPacket inPacket) {
        int reqType = inPacket.decodeInt(); // 0 = group
        int region = inPacket.decodeInt();
        int session = inPacket.decodeInt();
        int group = inPacket.decodeInt();
        int exploreIndex = inPacket.decodeInt();
        MonsterCollection mc = chr.getAccount().getMonsterCollection();
        switch (reqType) {
            case 0: // group
                MonsterCollectionGroup mcs = mc.getGroup(region, session, group);
                if (mcs != null && !mcs.isRewardClaimed() && mc.isComplete(region, session, group)) {
                    Tuple<Integer, Integer> rewardInfo = MonsterCollectionData.getReward(region, session, group);
                    Item item = ItemData.getItemDeepCopy(rewardInfo.getLeft());
                    item.setQuantity(rewardInfo.getRight());
                    chr.addItemToInventory(item);
                    mcs.setRewardClaimed(true);
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CollectionCompletionRewardSuccess, null, 0));
                } else if (mcs != null && mcs.isRewardClaimed()) {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.AlreadyClaimedReward, null, 0));
                } else {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CompleteCollectionBeforeClaim, null, 0));
                }
                break;
            case 4: // exploration
                MonsterCollectionExploration mce = mc.getExploration(region, session, group);
                if (mce != null && mce.getEndDate().isExpired()) {
                    mc.removeExploration(mce);
                    chr.write(UserLocal.collectionRecordMessage(mce.getPosition(), mce.getValue(false)));
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.CollectionCompletionRewardSuccess, null, 0));
                } else {
                    chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.TryAgainInAMoment, null, 0));
                }
                break;
            default:
                log.warn("Unhandled MonsterCollectionCompleteRewardReq type " + reqType);
                chr.write(WvsContext.monsterCollectionResult(MonsterCollectionResultType.TryAgainInAMoment, null, 0));

        }
        chr.dispose(); // still required even if you send a collection result
    }

    @Handler(op = InHeader.MOVE_TO_BOSS_MATCHING_FIELD)
    public static void moveToBossMatchingField(Char chr, InPacket inPacket) {
        inPacket.decodeInt();
        inPacket.decodeInt();
        int fieldID = inPacket.decodeInt();

        chr.warp(fieldID);
    }

    @Handler(op = InHeader.USER_EFFECT_LOCAL)
    public static void handleUserEffectLocal(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int slv = inPacket.decodeByte();
        boolean sendLocal = inPacket.decodeByte() != 0;

        int chrId = chr.getId();
        Field field = chr.getField();
        Effect effect = null;

        if (!chr.hasSkill(skillId)) {
            chr.getOffenseManager().addOffense(String.format("Character {%d} tried to use a skill {%d} they do not have.", chrId, skillId));
            return;
        }

        // TODO: handle more multi-attack skills that don't have their CDs registered...
        if (skillId == 65121052) { // Supreme Super Nova (AB)
            chr.setSkillCooldown(skillId, chr.getSkillLevel(skillId));
        }

        if (skillId == Evan.DRAGON_FURY) {
            effect = Effect.showDragonFuryEffect(skillId, slv, 0, true);

        } else if (skillId == DarkKnight.FINAL_PACT) {
            effect = Effect.showFinalPactEffect(skillId, slv, 0, true);

        } else if (skillId == WildHunter.CALL_OF_THE_HUNTER) {
            effect = Effect.showCallOfTheHunterEffect(skillId, slv, 0, chr.isLeft(), chr.getPosition().getX(), chr.getPosition().getY());

        } else if (skillId == Kaiser.VERTICAL_GRAPPLE || skillId == AngelicBuster.GRAPPLING_HEART || skillId == Job.ROPE_LIFT) { // 'Grappling Hook' Skills
            int chrPositionY = inPacket.decodeInt();
            Position ropeConnectDest = inPacket.decodePositionInt();
            effect = Effect.showVerticalGrappleEffect(skillId, chr.getLevel(), slv, 0, chrPositionY, ropeConnectDest.getX(), ropeConnectDest.getY());

        } else if (skillId == 15001021/*TB  Flash*/ || skillId == Shade.FOX_TROT || skillId == 5081021/*Jett Flash*/ || skillId == Shadower.TRICKBLADE_FINISHER || skillId == Shadower.INTO_DARKNESS) { // 'Flash' Skills
            Position origin = inPacket.decodePositionInt();
            Position dest = inPacket.decodePositionInt();
            effect = Effect.showFlashBlinkEffect(skillId, slv, 0, origin.getX(), origin.getY(), dest.getX(), dest.getY());

        } else if (SkillConstants.isSuperNovaSkill(skillId)) { // 'SuperNova' Skills
            Position chrPosition = inPacket.decodePositionInt();
            effect = Effect.showSuperNovaEffect(skillId, slv, 0, chrPosition.getX(), chrPosition.getY());

        } else if (SkillConstants.isUnregisteredSkill(skillId)) { // 'Unregistered' Skills
            effect = Effect.showUnregisteredSkill(skillId, slv, 0, chr.isLeft());

        } else if (SkillConstants.isHomeTeleportSkill(skillId)) {
            effect = Effect.skillUse(skillId, chr.getLevel(), slv, 0);

        } else if (skillId == BattleMage.DARK_SHOCK) {
            Position origin = inPacket.decodePositionInt();
            Position dest = inPacket.decodePositionInt();
            effect = Effect.showDarkShockSkill(skillId, slv, origin, dest);
        } else {
            log.error(String.format("Unhandled Remote Effect Skill id %d", skillId));
            chr.chatMessage(String.format("Unhandled Remote Effect Skill:  id = %d", skillId));
        }

        if (effect != null) {
            if (sendLocal) {
                chr.write(UserPacket.effect(effect));
            }
            if (chr.getCopy() != null) {
                chr.write(UserRemote.effect(1337, effect));
            }
            field.broadcastPacket(UserRemote.effect(chr.getId(), effect));
        }
    }

    @Handler(op = InHeader.ACHIEVEMENT_REQUEST)
    public static void handleAchievementRequest(Char chr, InPacket inPacket) {
        int type = inPacket.decodeInt();
        AchievementType at = AchievementType.getByVal(type);
        if (at == null) {
            log.error("Unknown achievement type " + type);
            chr.dispose();
            return;
        }
        switch (at) {
            default:
                log.error("Unhandled achievement type " + at);
                chr.dispose();
        }
    }

    @Handler(op = InHeader.USER_FOLLOW_CHARACTER_REQUEST)
    public static void handleUserFollowCharacterRequest(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int driverChrId = inPacket.decodeInt();
        short unk = inPacket.decodeShort();

        Char driverChr = field.getCharByID(driverChrId);
        if (driverChr == null) {
            return;
        }
        driverChr.write(WvsContext.setPassenserRequest(chr.getId()));
    }

    @Handler(op = InHeader.SET_PASSENGER_RESULT)
    public static void handleSetPassengerResult(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        int requestorChrId = inPacket.decodeInt();
        boolean accepted = inPacket.decodeByte() != 0;
        Char requestorChr = field.getCharByID(requestorChrId);

        if (!accepted) {

            int errorType = inPacket.decodeInt();
            switch (errorType) {

            }

        } else {
            requestorChr.write(UserPacket.followCharacter(chr.getId(), true, new Position()));

        }
    }

    @Handler(op = InHeader.QUICKSLOT_KEY_MAPPED_MODIFIED)
    public static void handleQuickslotKeyMappedModified(Char chr, InPacket inPacket) {
        final int length = GameConstants.QUICKSLOT_LENGTH;
        List<Integer> quickslotKeys = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            quickslotKeys.add(inPacket.decodeInt());
        }
        chr.setQuickslotKeys(quickslotKeys);
    }

    @Handler(op = InHeader.USER_CATCH_DEBUFF_COLLISION)
    public static void handleUserCatchDebuffCollision(Char chr, InPacket inPacket) {
        int hpR = inPacket.decodeInt();
        chr.damage(chr.getHPPerc(hpR), true);
    }

    @Handler(op = InHeader.SECOND_ATOM_REMOVE_REQUEST)
    public static void handleSecondAtomRemove(Char chr, InPacket inPacket) {
        int atomID = inPacket.decodeInt();
        chr.getField().broadcastPacket(SecondAtomPacket.removeSecondAtom(chr, atomID));
    }
}
