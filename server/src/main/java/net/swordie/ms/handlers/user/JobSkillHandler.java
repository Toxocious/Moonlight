package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.ZeroInfo;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.Kinesis;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.pirate.Corsair;
import net.swordie.ms.client.jobs.adventurer.warrior.Paladin;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.client.jobs.cygnus.WindArcher;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.Aran;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.legend.Phantom;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.WildHunterInfo;
import net.swordie.ms.client.jobs.resistance.Xenon;
import net.swordie.ms.client.jobs.resistance.demon.DemonAvenger;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.FieldAttackObj;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.enums.StealMemoryType.REMOVE_STEAL_MEMORY;
import static net.swordie.ms.enums.StealMemoryType.STEAL_SKILL;

public class JobSkillHandler {

    private static final Logger log = Logger.getLogger(JobSkillHandler.class);


    @Handler(op = InHeader.CREATE_KINESIS_PSYCHIC_AREA)
    public static void handleCreateKinesisPsychicArea(Char chr, InPacket inPacket) {
        PsychicArea pa = new PsychicArea();
        pa.action = inPacket.decodeInt();
        pa.actionSpeed = inPacket.decodeInt();
        pa.localPsychicAreaKey = inPacket.decodeInt() + 1;
        pa.psychicAreaKey = inPacket.decodeInt();
        pa.skillID = inPacket.decodeInt();
        pa.slv = inPacket.decodeShort();
        pa.duration = inPacket.decodeInt();
        pa.isLeft = inPacket.decodeByte() != 0;
        pa.skeletonFilePathIdx = inPacket.decodeShort();
        pa.skeletonAniIdx = inPacket.decodeShort();
        pa.skeletonLoop = inPacket.decodeShort();
        pa.start = inPacket.decodePositionInt();
        pa.success = true;
        if (!chr.hasSkillWithSlv(pa.skillID, pa.slv)) {
            return;
        }
        chr.write(FieldPacket.createPsychicArea(chr.getId(), pa));
        chr.addPsychicArea(pa.localPsychicAreaKey, pa);
//        chr.write(UserLocal.doActivePsychicArea(pa));
//        chr.getField().broadcastPacket(UserLocal.enterFieldPsychicInfo(chr.getId(), null, Collections.singletonList(pa)), chr);

        chr.getJobHandler().handleSkill(chr, chr.getTemporaryStatManager(), pa.skillID, chr.getSkillLevel(pa.skillID), inPacket, new SkillUseInfo());
    }

    @Handler(op = InHeader.DO_ACTIVE_PSYCHIC_AREA)
    public static void handleDoActivePsychicArea(Char chr, InPacket inPacket) {
        int localKey = inPacket.decodeInt();
        short unk = inPacket.decodeShort(); // unk
        Position position = inPacket.decodePositionInt();
        PsychicArea pa = chr.getPsychicAreas().getOrDefault(localKey, null);
        if (pa == null) {
            return;
        }
        //pa.localPsychicAreaKey = localKey;
        pa.psychicAreaKey = localKey;
        chr.write(UserLocal.doActivePsychicArea(pa));

        if (pa.skillID == Kinesis.ULTIMATE_BPM) {
            chr.getJobHandler().handleSkill(chr, chr.getTemporaryStatManager(), pa.skillID, chr.getSkillLevel(pa.skillID), inPacket, new SkillUseInfo());
        }
    }

    @Handler(op = InHeader.DEBUFF_PSYCHIC_AREA)
    public static void handleDebuffPsychicArea(Char chr, InPacket inPacket) {
        List<Mob> mobList = new ArrayList<>();

        int skillId = inPacket.decodeInt();
        int slv = inPacket.decodeShort();
        int localKey = inPacket.decodeInt();
        boolean isLeft = inPacket.decodeByte() != 0;
        Position position = inPacket.decodePositionInt();

        PsychicArea pa = chr.getPsychicAreas().getOrDefault(localKey, null);
        if (pa == null || !JobConstants.isKinesis(chr.getJob()) || chr.getSkillLevel(skillId) <= 0) {
            return;
        }

        short loopSize = inPacket.decodeShort();
        for (int i = 0; i < loopSize; i++) {
            int mobObjId = inPacket.decodeInt();

            Mob mob = (Mob) chr.getField().getLifeByObjectID(mobObjId);
            if (mob == null) {
                continue;
            }
            mobList.add(mob);
        }

        short unk = inPacket.decodeShort(); // not sure

        if (mobList.size() > 0) {
            ((Kinesis) chr.getJobHandler()).applyMindAreaDebuff(skillId, position, mobList);
        }
    }

    @Handler(op = InHeader.RELEASE_PSYCHIC_AREA)
    public static void handleReleasePsychicArea(Char chr, InPacket inPacket) {
        int localPsychicAreaKey = inPacket.decodeInt();
        chr.getField().broadcastPacket(UserPool.releasePsychicArea(chr, localPsychicAreaKey));
        chr.removePsychicArea(localPsychicAreaKey);
    }

    @Handler(op = InHeader.CREATE_PSYCHIC_LOCK)
    public static void handleCreatePsychicLock(Char chr, InPacket inPacket) {
        Field f = chr.getField();
        PsychicLock pl = new PsychicLock();
        pl.skillID = inPacket.decodeInt();
        pl.slv = inPacket.decodeShort();
        pl.action = inPacket.decodeInt();
        pl.actionSpeed = inPacket.decodeInt();
        int i = 1;
        while (inPacket.decodeByte() != 0) {
            PsychicLockBall plb = new PsychicLockBall();
            plb.localKey = inPacket.decodeInt();
            plb.psychicLockKey = inPacket.decodeInt();
            plb.psychicLockKey = i++;
            int mobID = inPacket.decodeInt();
            Life life = f.getLifeByObjectID(mobID);
            plb.mob = life == null ? null : (Mob) life;
            plb.stuffID = inPacket.decodeShort();
            inPacket.decodeInt(); // usually 0
            plb.usableCount = inPacket.decodeShort();
            plb.posRelID = inPacket.decodeByte();
            plb.start = inPacket.decodePositionInt();
            plb.rel = inPacket.decodePositionInt();
            pl.psychicLockBalls.add(plb);
        }
        if (!chr.hasSkillWithSlv(pl.skillID, pl.slv)) {
            return;
        }
        chr.getField().broadcastPacket(UserLocal.enterFieldPsychicInfo(chr.getId(), pl, null), chr);
        chr.write(UserPool.createPsychicLock(chr, pl.success, pl));
    }

    @Handler(op = InHeader.RELEASE_PSYCHIC_LOCK)
    public static void handleReleasePsychicLock(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        int slv = inPacket.decodeInt();
        if (!chr.hasSkillWithSlv(skillID, (short) slv)) {
            return;
        }
        int id = inPacket.decodeInt();
        int mobID = inPacket.decodeInt();
        int stuffId = inPacket.decodeInt();
        Position position = inPacket.decodePositionInt();

        if (mobID != 0) {
            List<Integer> l = new ArrayList<>();
            l.add(mobID);
            chr.getField().broadcastPacket(UserPool.releasePsychicLockMob(chr, l));
        } else {
            chr.getField().broadcastPacket(UserPool.releasePsychicLock(chr, id));
        }
    }


    @Handler(op = InHeader.REQUEST_ARROW_PLATTER_OBJ)
    public static void handleRequestArrowPlatterObj(Char chr, InPacket inPacket) {
        boolean flip = inPacket.decodeByte() != 0;
        Position position = inPacket.decodePositionInt(); // ignoring this, we just take the char's info we know
        int skillID = BowMaster.ARROW_PLATTER;
        Skill skill = chr.getSkill(skillID);
        if (skill != null && skill.getCurrentLevel() > 0) {
            Field field = chr.getField();
            Set<FieldAttackObj> currentFaos = field.getFieldAttackObjects();
            // remove the old arrow platter
            currentFaos.stream()
                    .filter(fao -> fao.getOwnerID() == chr.getId() && fao.getTemplateId() == 1)
                    .findAny().ifPresent(field::removeLife);
            SkillInfo si = SkillData.getSkillInfoById(skillID);
            int slv = skill.getCurrentLevel();
            FieldAttackObj fao = new FieldAttackObj(1, chr.getId(), chr.getPosition().deepCopy(), flip);
            field.spawnLife(fao, chr);
            field.broadcastPacket(FieldAttackObjPool.objCreate(fao), chr);
            ScheduledFuture sf = EventManager.addEvent(() -> field.removeLife(fao.getObjectId(), true),
                    si.getValue(SkillStat.u, slv), TimeUnit.SECONDS);
            field.addLifeSchedule(fao, sf);
            field.broadcastPacket(FieldAttackObjPool.setAttack(fao.getObjectId(), 0));
        }
        chr.dispose();
    }


    @Handler(op = InHeader.USER_FLAME_ORB_REQUEST)
    public static void handleUserFlameOrbRequest(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        int slv = inPacket.decodeByte();
        short dir = inPacket.decodeShort();
        if (!chr.hasSkill(skillID)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        slv = (byte) chr.getSkillLevel(skillID);
        int range = si.getValue(SkillStat.range, slv);
        ForceAtomEnum fae;
        switch (skillID) {
            case BlazeWizard.FINAL_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_4;
                skillID = BlazeWizard.FINAL_ORBITAL_FLAME_ATOM;
                break;
            case BlazeWizard.GRAND_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_3;
                skillID = BlazeWizard.GRAND_ORBITAL_FLAME_ATOM;
                break;
            case BlazeWizard.GREATER_ORBITAL_FLAME:
                fae = ForceAtomEnum.ORBITAL_FLAME_2;
                skillID = BlazeWizard.GREATER_ORBITAL_FLAME_ATOM;
                break;
            default:
                fae = ForceAtomEnum.ORBITAL_FLAME_1;
                skillID = BlazeWizard.ORBITAL_FLAME_ATOM;
                break;
        }

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStatBySkillId(BlazeWizard.ORBITAL_FLAME_RANGE)) {
            range = tsm.getOptByCTSAndSkill(CharacterTemporaryStat.AddRangeOnOff, BlazeWizard.ORBITAL_FLAME_RANGE).nOption;
        }
        int angle = 0;
        int curTime = Util.getCurrentTime();
        int firstImpact = 5;
        int secondImpact = 21;
        switch (dir) {
            case 1: // right
                break;
            case 2: // up
                angle = 90;
                firstImpact = 11;
                secondImpact = 13;
                if (tsm.hasStat(CharacterTemporaryStat.AddRangeOnOff)) {
                    range /= 1.4;
                }
                break;
            case 3: // down
                angle = 90;
                firstImpact = 11;
                secondImpact = 13;
                if (tsm.hasStat(CharacterTemporaryStat.AddRangeOnOff)) {
                    range /= 1.4;
                }
                break;
        }
        ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), firstImpact, secondImpact,
                angle, 0, curTime, si.getValue(SkillStat.mobCount, slv), skillID, new Position(0, 0));
        List<ForceAtomInfo> faiList = new ArrayList<>();
        faiList.add(fai);
        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae, false,
                new ArrayList<>(), skillID, faiList, null, dir, range, null, 0, null, 0));

    }

    @Handler(op = InHeader.ZERO_TAG)
    public static void handleZeroTag(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int newTF = inPacket.decodeInt();
        int oldTF = inPacket.decodeInt();
        chr.swapZeroState();
    }

    @Handler(op = InHeader.ZERO_SHARE_CASH_EQUIP_PART)
    public static void handleZeroShareCashEquip(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int slot = inPacket.decodeInt();
        byte share = inPacket.decodeByte();
        ZeroInfo currentInfo = c.getChr().getZeroInfo();
        currentInfo.betaClothing(slot, share, chr);
        chr.write(WvsContext.zeroInfo(currentInfo, chr));
        chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
        chr.write(WvsContext.exclRequest());
    }

    @Handler(op = InHeader.REQUEST_SET_BLESS_OF_DARKNESS)
    public static void handleRequestSetBlessOfDarkness(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (JobConstants.isLuminous(chr.getJob())) {
            ((Luminous) chr.getJobHandler()).changeBlackBlessingCount(true);
        }
    }

    @Handler(op = InHeader.RW_MULTI_CHARGE_CANCEL_REQUEST)
    public static void handleRWMultiChargeCancelRequest(Client c, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        int skillid = inPacket.decodeInt();

        c.write(UserLocal.skillUseResult(unk, skillid));
    }

    @Handler(op = InHeader.FOX_MAN_ACTION_SET_USE_REQUEST)
    public static void handleFoxManActionSetUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (!chr.hasSkill(Kanna.HAKU) && !chr.hasSkill(Kanna.HAKU_REBORN)) {
            return;
        }
        c.verifyTick(inPacket);
        int skillNumber = inPacket.decodeInt(); //bSkill Number
        boolean enable = inPacket.decodeByte() != 0;
        // more packet, but seems useless
        switch (skillNumber) {
            case 1:
                Kanna.hakuGift(chr);
                break;
            case 2:
                break;
            case 3:
                Kanna.hakuFoxFire(chr);
                break;
            case 4:
                if (chr.getTemporaryStatManager().getOptByCTSAndSkill(CharacterTemporaryStat.HakuBlessing, Kanna.HAKUS_BLESSING_2) != null) {
                    chr.dispose();
                    return;
                }
                if (chr.getParty() != null) {
                    Set<Char> chrList = chr.getParty().getPartyMembersInSameField(chr);
                    for (Char pChr : chrList) {
                        Kanna.hakuHakuBlessing(chr, pChr);
                    }
                } else {
                    Kanna.hakuHakuBlessing(chr, chr);
                }
                break;
            case 5:
                Kanna.hakuBreathUnseen(chr);
                break;
        }
        Kanna.foxManUpdate(chr);
    }

    @Handler(op = InHeader.USER_CREATE_HOLIDOM_REQUEST)
    public static void handleUserCreateHolidomRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();

        inPacket.decodeInt(); //tick
        inPacket.decodeByte(); //unk
        int skillID = inPacket.decodeInt();
        inPacket.decodeInt(); //unk

        if (field.getAffectedAreas().stream().noneMatch(ss -> ss.getSkillID() == skillID)) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to heal from Holy Fountain (%d) whilst there isn't any on the field.", chr.getId(), skillID));
            return;
        }
        c.getChr().heal((int) (c.getChr().getMaxHP() / ((double) 100 / 40)));
    }

    @Handler(op = InHeader.REQUEST_DEC_COMBO)
    public static void handleRequestDecCombo(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (JobConstants.isAran(chr.getJob())) {
            Aran aranJobHandler = ((Aran) c.getChr().getJobHandler());
            aranJobHandler.DecrementComboAbility();
        }
    }

    @Handler(op = InHeader.REQUEST_SET_HP_BASE_DAMAGE)
    public static void handleRequestSetHpBaseDamage(Char chr, InPacket inPacket) {
        if (JobConstants.isDemonAvenger(chr.getJob())) {
            ((DemonAvenger) chr.getJobHandler()).sendHpUpdate();
        }
    }

    @Handler(op = InHeader.USER_REQUEST_FLYING_SWORD_START)
    public static void handleUserRequestFlyingSwordStart(Char chr, InPacket inPacket) {
        if (JobConstants.isKaiser(chr.getJob())) {
            ((Kaiser) chr.getJobHandler()).createFlyingSwordForceAtom(inPacket);
        }
    }


    @Handler(op = InHeader.USER_REQUEST_STEAL_SKILL_LIST)
    public static void handleUserRequestStealSkillList(Client c, InPacket inPacket) {
        int targetChrID = inPacket.decodeInt();

        Char chr = c.getChr();
        Char targetChr = chr.getField().getCharByID(targetChrID);
        Set<Skill> targetSkillsList = targetChr.getSkills();

        chr.write(UserLocal.resultStealSkillList(targetSkillsList, 4, targetChrID, targetChr.getJob()));
        chr.dispose();
    }

    @Handler(op = InHeader.USER_REQUEST_STEAL_SKILL_MEMORY)
    public static void handleUserRequestStealSkillMemory(Client c, InPacket inPacket) {
        int stealSkillID = inPacket.decodeInt();
        int targetChrID = inPacket.decodeInt();
        boolean add = inPacket.decodeByte() != 0;   // 0 = add  |  1 = remove

        Char chr = c.getChr();
        Char targetChr = c.getChr().getField().getCharByID(targetChrID);

        Skill stolenSkill = SkillData.getSkillDeepCopyById(stealSkillID);
        int stealSkillMaxLv = stolenSkill.getMasterLevel();
        int stealSkillCurLv = targetChr == null ? stealSkillMaxLv : targetChr.getSkill(stealSkillID).getCurrentLevel(); //TODO this is for testing,  needs to be:    targetChr.getSkillID(stealSkillID).getCurrentLevel();

        if (!add) {
            // /Add Stolen Skill

            if (chr.getStolenSkillBySkillId(stealSkillID) != null) {
                chr.chatMessage("You already have this stolen skill.");
                chr.dispose();
                return;
            }

            int position = StolenSkill.getFirstEmptyPosition(chr, stealSkillID);
            if (position == -1) {
                chr.chatMessage("Could not continue with stealing skills due to an unknown error.");
                chr.dispose();
                return;
            }
            StolenSkill.setSkill(chr, stealSkillID, position, (byte) stealSkillCurLv);

            int positionPerTab = StolenSkill.getPositionForTab(position, stealSkillID);
            c.write(UserLocal.changeStealMemoryResult(STEAL_SKILL.getVal(), SkillConstants.getStealSkillManagerTabFromSkill(stealSkillID), positionPerTab, stealSkillID, stealSkillCurLv, stealSkillMaxLv));
        } else {
            //Remove Stolen Skill
            int position = StolenSkill.getPositionPerTabFromStolenSkill(chr.getStolenSkillBySkillId(stealSkillID));
            StolenSkill.removeSkill(chr, stealSkillID);
            c.write(UserLocal.changeStealMemoryResult(REMOVE_STEAL_MEMORY.getVal(), SkillConstants.getStealSkillManagerTabFromSkill(stealSkillID), position, stealSkillID, stealSkillCurLv, stealSkillMaxLv));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_REQUEST_SET_STEAL_SKILL_SLOT)
    public static void handleUserRequestSetStealSkillSlot(Client c, InPacket inPacket) {
        int impeccableSkillID = inPacket.decodeInt();
        int stealSkillID = inPacket.decodeInt();

        ChosenSkill.setChosenSkill(c.getChr(), stealSkillID, impeccableSkillID);
        c.write(UserLocal.resultSetStealSkill(true, impeccableSkillID, stealSkillID));
        c.getChr().dispose();
    }

    @Handler(op = InHeader.ENTER_OPEN_GATE_REQUEST)
    public static void handleEnterOpenGateRequest(Char chr, InPacket inPacket) {
        int chrId = inPacket.decodeInt();
        Position position = inPacket.decodePosition();
        byte gateId = inPacket.decodeByte();

        // Probably needs remote player position handling
        chr.dispose(); // Necessary as going through the portal will stuck you
    }

    @Handler(op = InHeader.USER_SET_DRESS_CHANGED_REQUEST)
    public static void handleUserSetDressChangedRequest(Char chr, InPacket inPacket) {
          boolean on = inPacket.decodeByte() != 0;
          if (on) {
             chr.write(UserLocal.setDressChanged(on, true)); // causes client to send this packet again
          }
    }

    @Handler(op = InHeader.BEAST_TAMER_REGROUP_REQUEST)
    public static void handleBeastTamerRegroupRequest(Char chr, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        int skillId = inPacket.decodeInt();

        if (skillId == BeastTamer.REGROUP) {
            BeastTamer.beastTamerRegroup(chr);
        } else {
            log.error(String.format("Unhandled Beast Tamer Request %d", skillId));
            chr.chatMessage(String.format("Unhandled Beast Tamer Request %d", skillId));
        }
    }

    @Handler(op = InHeader.USER_JAGUAR_CHANGE_REQUEST)
    public static void handleUserJaguarChangeRequest(Char chr, InPacket inPacket) {
        final int questID = QuestConstants.WILD_HUNTER_JAGUAR_STORAGE_ID;
        QuestManager qm = chr.getQuestManager();
        Quest quest = qm.getQuestById(questID);
        if (quest == null) {
            return;
        }
        int fromID = inPacket.decodeInt();
        int toID = inPacket.decodeInt();
        String value = quest.getProperty("" + (toID + 1));
        if (value != null) {
            WildHunterInfo whi = chr.getWildHunterInfo();
            whi.setIdx((byte) toID);
            whi.setRidingType((byte) toID);
            chr.write(WvsContext.wildHunterInfo(whi));
            // could make WildHunterInfo an entity for this
            Quest chosenQuest = qm.getQuestById(QuestConstants.WILD_HUNTER_JAGUAR_CHOSEN_ID);
            if (chosenQuest == null) {
                chosenQuest = new Quest(QuestConstants.WILD_HUNTER_JAGUAR_CHOSEN_ID, QuestStatus.Started);
                qm.addQuest(chosenQuest);
            }
            chosenQuest.setQrValue("" + toID);
        } else {
            chr.chatMessage("You do not have that jaguar.");
        }
    }

    @Handler(op = InHeader.XENON_CORE_OVERLOAD)
    public static void handleXenonCoreOverload(Char chr, InPacket inPacket) {
        ((Xenon) chr.getJobHandler()).coreOverloadManaConsumption();
    }

    @Handler(op = InHeader.LOADED_DICE_SELECTION_RESULT)
    public static void handleLoadedDiceSelectionResult(Char chr, InPacket inPacket) {
        int diceSelection = inPacket.decodeInt();
        if (chr.getQuestManager().getQuestById(GameConstants.LOADED_DICE_SELECTION) == null) {
            chr.getScriptManager().createQuestWithQRValue(GameConstants.LOADED_DICE_SELECTION, diceSelection + "");
        } else {
            chr.getScriptManager().setQRValue(GameConstants.LOADED_DICE_SELECTION, diceSelection + "");
        }
    }

    @Handler(ops = {InHeader.PEACEMAKER_EXPLOSION_CONTACT_COUNT, InHeader.PEACEMAKER_TRAVEL_CONTACT_COUNT})
    public static void handlePeacemakerContactCount(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int objId = inPacket.decodeInt();
        int unk1 = inPacket.decodeInt();
        Rect rect = inPacket.decodeIntRect();
        int contactCount = inPacket.decodeInt();
        int unk2 = inPacket.decodeInt();

        if (JobConstants.isBishop(chr.getJob())) {
            ((Bishop) chr.getJobHandler()).givePeacemakerBuffs(skillId, rect, contactCount);
        }
    }

    @Handler(op = InHeader.BULLET_BARRAGE_DURATION_EXTENSION_REQUEST)
    public static void handleBulletBarrageDurationExtensionRequest(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();

        switch (skillId) {
            case Corsair.BULLET_BARRAGE:
                if (JobConstants.isCorsair(chr.getJob())) {
                    ((Corsair) chr.getJobHandler()).extendBulletBarrageDuration();
                }
                break;
        }
    }

    @Handler(op = InHeader.DIMENSIONAL_SWORD_CHANGE)
    public static void handleDimensionalSwordChange(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        short job = chr.getJob();
        switch (skillId) {
            case DemonAvenger.DIMENSIONAL_SWORD_SUMMON:
                if (JobConstants.isDemonAvenger(job)) {
                    ((DemonAvenger) chr.getJobHandler()).changeDimensionalSword();
                }
                break;
            case AngelicBuster.MIGHTY_MASCOT:
                if (JobConstants.isAngelicBuster(job)) {
                    ((AngelicBuster) chr.getJobHandler()).doBubbleBreath();
                }
                break;
        }
    }

    @Handler(op = InHeader.GREATER_DARK_SERVANT_SWAP_REQUEST)
    public static void handleGreaterDarkServantSwapRequest(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();

        if (!chr.hasSkill(skillId)) {
            return;
        }

        switch (skillId) {
            case NightWalker.GREATER_DARK_SERVANT:
                ((NightWalker) chr.getJobHandler()).swapWithServant();
                break;
        }
    }

    @Handler(op = InHeader.INHUMAN_SPEED_FORCE_ATOM_REQUEST)
    public static void handleInhumanSpeedForceAtomRequest(Char chr, InPacket inPacket) {
        int mobId = inPacket.decodeInt();
        int time = inPacket.decodeInt(); // tick

        if (JobConstants.isBowMaster(chr.getJob())) {
            ((BowMaster) chr.getJobHandler()).createInhumanSpeedForceAtom(mobId);
        }
    }

    @Handler(op = InHeader.CRYSTAL_SKILL_REQUEST)
    public static void handleCrystalSkillRequest(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();

        if (JobConstants.isIllium(chr.getJob())) {
            if (((Illium) chr.getJobHandler()).getCrystal() != null) {
                ((Illium) chr.getJobHandler()).incrementCrystal(skillId);
            }
        }
    }

    @Handler(op = InHeader.SPOTLIGHT_STACK_REQUEST)
    public static void handleSpotlightStackRequest(Char chr, InPacket inPacket) {
        boolean giveBuff = inPacket.decodeByte() != 0; // remove if 0;
        int stackAmount = inPacket.decodeInt();

        if (JobConstants.isAngelicBuster(chr.getJob()) && chr.hasSkill(AngelicBuster.SUPER_STAR_SPOTLIGHT)) {
            ((AngelicBuster) chr.getJobHandler()).giveSpotlightBuff(giveBuff, stackAmount);
        }
    }

    @Handler(op = InHeader.USER_UPDATE_LAPIDIFICATION)
    public static void handleUserUpdateLapidification(Char chr, InPacket inPacket) {
        if (JobConstants.isPhantom(chr.getJob()) && chr.hasSkill(Phantom.LUCK_OF_THE_DRAW)) {
            ((Phantom) chr.getJobHandler()).createLuckOfTheDrawForceAtom();
        }
    }

    @Handler(op = InHeader.USER_KEY_DOWN_STEP_REQUEST)
    public static void handleUserKeyDownStepRequest(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int keydownDurationMS = inPacket.decodeInt();

        if (keydownDurationMS <= 0) {
            return;
        }

        if (JobConstants.isPaladin(chr.getJob()) && skillId == Paladin.GRAND_GUARDIAN && chr.hasSkill(Paladin.GRAND_GUARDIAN)) {
            ((Paladin) chr.getJobHandler()).increaseGrandGuardianState();
        }
    }

    @Handler(op = InHeader.KEY_DOWN_SKILL_COST)
    public static void handleKeyDownSkillCost(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();

        if (JobConstants.isPaladin(chr.getJob()) && skillId == Paladin.GRAND_GUARDIAN && chr.hasSkill(Paladin.GRAND_GUARDIAN)) {
            ((Paladin) chr.getJobHandler()).decreaseHPByGrandGuardian();
        } else if (JobConstants.isPathFinder(chr.getJob())) {
            ((Pathfinder) chr.getJobHandler()).handleAncientAstraRelicCost(skillId);
        }
    }

    @Handler(op = InHeader.SKILL_COMMAND_LOCK_ARK)
    public static void handleSkillCommandLockArk(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int questId = QuestConstants.SKILL_COMMAND_LOCK_ARK;
        Quest quest = chr.getQuestManager().getQuestById(questId);

        if (skillId == WindArcher.ALBATROSS) {
            // Albatross
            if (quest == null) {
                chr.getScriptManager().createQuestWithQRValue(questId, "");
            } else {
                if (quest.getQRValue().equalsIgnoreCase("alba=1")) {
                    quest.setQrValue("");
                } else {
                    quest.setQrValue("alba=1");
                }
            }
        } else {
            // Ark Skills
            if (quest == null) {
                chr.getScriptManager().createQuestWithQRValue(questId, String.format("%d=1", skillId));
            } else {
                if (quest.getProperty(skillId + "") == null) { // doesn't have the skill in qrValue yet
                    quest.setProperty(skillId + "", "1");
                } else {
                    quest.setProperty(skillId + "", quest.getIntProperty(skillId + "") == 1 ? "0" : "1");
                }
            }
        }
        chr.write(WvsContext.questRecordExMessage(quest));
        chr.dispose();
    }

    @Handler(op = InHeader.BEAST_FORM_WING_OFF)
    public static void handleBeastFormWingOff(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int questId = QuestConstants.SKILL_COMMAND_LOCK_ARK; // questId 1544
        Quest quest = chr.getQuestManager().getQuestById(questId);
        switch (skillId) {
            case WindArcher.ALBATROSS:
                if (quest == null) {
                    chr.getScriptManager().createQuestWithQRValue(questId, "");
                } else {
                    if (quest.getQRValue().equalsIgnoreCase("alba=1")) {
                        quest.setQrValue("");
                    } else {
                        quest.setQrValue("alba=1");
                    }
                }
                break;
            default:
                chr.chatMessage(String.format("Unhandled Skill Effect ON/OFF  SkillID = %d", skillId));
                log.error(String.format("Unhandled Skill Effect ON/OFF  SkillID = %d", skillId));
                break;
        }

        if (quest != null) {
            chr.write(WvsContext.questRecordExMessage(quest));
            chr.showSkillOnOffEffect();
        }
    }

    @Handler(op = InHeader.SKILL_COMMAND_LOCK_ARAN)
    public static void handleSkillCommandLockAran(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int questId = QuestConstants.SKILL_COMMAND_LOCK_ARAN;
        Quest quest = chr.getQuestManager().getQuestById(questId);
        int lockId = -1;

        switch (skillId) {
            case Aran.COMBAT_STEP:
                lockId = 0;
                break;
            case Aran.SMASH_WAVE:
                lockId = 1;
                break;
            case Aran.FINAL_CHARGE:
                lockId = 2;
                break;
            case Aran.FINAL_TOSS:
                lockId = 3;
                break;
            case Aran.ROLLING_SPIN:
                lockId = 4;
                break;
            case Aran.JUDGEMENT_DRAW:
                lockId = 5;
                break;
            case Aran.GATHERING_HOOK:
                lockId = 6;
                break;
            case Aran.FINAL_BLOW:
                lockId = 7;
                break;
            case Aran.FINISHER_STORM_OF_FEAR:
                lockId = 8;
                break;
            case Aran.FINISHER_HUNTER_PREY:
                lockId = 9;
                break;
        }
        if (lockId < 0) {
            return;
        }
        if (quest == null) {
            chr.getScriptManager().createQuestWithQRValue(questId, String.format("%d=1", lockId));
        } else {
            if (quest.getProperty(lockId + "") == null) { // doesn't have the skill in qrValue yet
                quest.setProperty(lockId + "", "1");
            } else {
                quest.setProperty(lockId + "", quest.getIntProperty(lockId + "") == 1 ? "0" : "1");
            }
            chr.chatMessage(quest.toString());
            chr.write(WvsContext.questRecordExMessage(quest));
        }
        chr.dispose();
    }

    @Handler(op = InHeader.USER_SKILL_KEYDOWN_CHARGE_REQUEST)
    public static void handleUserSkillKeydownChargeRequest(Char chr, InPacket inPacket) {
        boolean charging = inPacket.decodeByte() != 0;

        if (JobConstants.isXenon(chr.getJob()) && chr.hasSkill(Xenon.OMEGA_BLASTER)) {
            if (charging) {
                // Done by the TSM
            } else {
                ((Xenon) chr.getJobHandler()).useOmegaBlasterAttack();
            }
        }
    }

    @Handler(op = InHeader.USER_UPDATE_PARTY_BUFF_AREA)
    public static void handleUserUpdatePartyBuffArea(Char chr, InPacket inPacket) {
        if (JobConstants.isBishop(chr.getJob())) {
            ((Bishop) chr.getJobHandler()).giveBenedictionBuff();
        }
    }

    @Handler(op = InHeader.DIVINE_ECHO_EXPIRE_REQUEST)
    public static void handleDivineEchoExpireRequest(Char chr, InPacket inPacket) {
        Paladin.removeDivineEchoLinkedBuffs(chr);
    }

    @Handler(op = InHeader.DEMON_BLOOD_SPILL_REQUEST)
    public static void handleDemonBloodSpillRequest(Char chr, InPacket inPacket) {
        if (JobConstants.isDemonAvenger(chr.getJob())) {
            ((DemonAvenger) chr.getJobHandler()).giveDemonFrenzy();
        }
    }

    @Handler(op = InHeader.SERPENT_VORTEX_INCREMENT_REQUEST)
    public static void handleSerpentVortexIncrementRequest(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        if (JobConstants.isPathFinder(chr.getJob()) && (skillID == 3311002 || skillID == 3321006)) {
            ((Pathfinder) chr.getJobHandler()).incrementSwiftStrikeCharge();
        }
        if (JobConstants.isIllium(chr.getJob()) && (skillID == 400021068)) {
            ((Illium) chr.getJobHandler()).incrementCrystallineShard();
        }
    }
}
