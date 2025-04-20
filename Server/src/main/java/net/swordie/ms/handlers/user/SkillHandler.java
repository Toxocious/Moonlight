package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.LinkSkill;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.b2body.B2Body;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.EquipAttribute;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.pirate.Buccaneer;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.anima.HoYoung;
import net.swordie.ms.client.jobs.cygnus.DawnWarrior;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.Xenon;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.VCoreData;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.loaders.containerclasses.VCoreInfo;
import net.swordie.ms.loaders.containerclasses.VNodeInfo;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.*;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.jobs.legend.Luminous.PRESSURE_VOID;
import net.swordie.ms.client.jobs.resistance.Mechanic;

public class SkillHandler {

    private static final Logger log = Logger.getLogger(SkillHandler.class);


    @Handler(op = InHeader.USER_SKILL_CANCEL_REQUEST)
    public static void handleUserSkillCancelRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillId = inPacket.decodeInt();
        tsm.removeStatsBySkill(skillId);

        if (SkillConstants.isKeyDownSkill(skillId)) {
            chr.getField().broadcastPacket(UserRemote.skillCancel(chr.getId(), skillId), chr);
            if (tsm.hasStat(CharacterTemporaryStat.IndieKeyDownTime)) {
                tsm.removeStat(CharacterTemporaryStat.IndieKeyDownTime, true);
            }

            chr.getJobHandler().handleCancelKeyDownSkill(chr, SkillConstants.getCorrectCooltimeSkillID(skillId));
                log.debug(String.format("Stop skill keydown  |  SkillId = %d", skillId));
        } else {
            if (skillId == Evan.DRAGON_MASTER || skillId == Evan.DRAGON_MASTER_2) {
                tsm.sendResetStatPacket(true);
            }
            if (skillId == Mechanic.HUMANOID_MECH || skillId == Mechanic.TANK_MECH) {
                tsm.removeStatsBySkill(skillId + 100); // because of special use
                tsm.sendResetStatPacket(true);
            } else {
                tsm.sendResetStatPacket();
            }
            if (skillId == Kaiser.NOVA_GUARDIANS || skillId == Kaiser.NOVA_GUARDIANS_2 || skillId == Kaiser.NOVA_GUARDIANS_3 || skillId == Buccaneer.MELTDOWN) {
                return;
            }
            chr.getJobHandler().handleSkillRemove(chr, skillId);
        }
    }

    @Handler(op = InHeader.USER_FORCE_ATOM_COLLISION)
    public static void handleForceAtomCollision(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int size = inPacket.decodeInt();
        int skillId = inPacket.decodeInt();
        int size2 = inPacket.decodeInt();
        for (int i = 0; i < size2; i++) {
            int skillId2 = inPacket.decodeInt();
            int forceAtomKey = inPacket.decodeInt();
            int unk = inPacket.decodeInt();
            Position position = inPacket.decodePositionInt();
            chr.getJobHandler().handleGuidedForceAtomCollision(forceAtomKey, skillId2, position);
        }
        for (int i = 0; i < size; i++) {
            int forceAtomKey = inPacket.decodeInt();
            byte unk = inPacket.decodeByte();
            int mobObjId = inPacket.decodeInt();
            int createTime = inPacket.decodeInt();
            int ownerId = inPacket.decodeInt(); // sometimes also the mobId (?)
            int unk2 = inPacket.decodeInt();
            Position position = inPacket.decodePositionInt();
            if (skillId == 0) {
                byte unkk = inPacket.decodeByte(); //new sometime after 207
                skillId = inPacket.decodeInt();
            }
            chr.getJobHandler().handleForceAtomCollision(forceAtomKey, skillId, mobObjId, position, inPacket);
        }
    }


   // @Handler(op = InHeader.USER_ACTIVATE_DAMAGE_SKIN)
    //public static void handleUserActivateDamageSkin(Client c, InPacket inPacket) {
    //    int damageSkin = inPacket.decodeInt();
     //   Char chr = c.getChr();
     //   chr.setDamageSkin(damageSkin);
   // }

    @Handler(op = InHeader.USER_ACTIVATE_DAMAGE_SKIN__PREMIUM)
    public static void handleUserActivateDamageSkinPremium(Client c, InPacket inPacket) {
        int damageSkin = inPacket.decodeInt();
        Char chr = c.getChr();
        if (chr.hasItem(damageSkin)) {
            chr.setPremiumDamageSkin(damageSkin);
        }
    }


    @Handler(op = InHeader.USER_DAMAGE_SKIN_SAVE_REQUEST)
    public static void handleUserDamageSkinSaveRequest(Char chr, InPacket inPacket) {
        byte typeVal = inPacket.decodeByte();
        short skinId = inPacket.decodeShort();
        DamageSkinType dst = DamageSkinType.getByVal(typeVal);
        if (dst == null || dst.getVal() >= DamageSkinType.Res_Success.getVal()) {
            log.error("Unknown DamageSkinType " + dst);
            return;
        }
        DamageSkinSaveData curSkin = chr.getDamageSkin();
        Account acc = chr.getAccount();
        QuestManager qm = chr.getQuestManager();
        switch (dst) {
            case Req_Active:
                DamageSkinSaveData dssd = acc.getDamageSkinBySkinID(skinId);
                if (dssd == null) {
                    chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Fail_Unknown, chr));
                    return;
                }
                if (curSkin != null && dssd.getDamageSkinID() == curSkin.getDamageSkinID()) {
                    chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Fail_AlreadyActive, chr));
                    return;
                }
                chr.setDamageSkin(dssd);
                chr.write(UserLocal.damageSkinSaveResult(dst, DamageSkinType.Res_Success, chr));

                Quest q = qm.getQuests().getOrDefault(QuestConstants.DAMAGE_SKIN, null);
                if (q == null) {
                    q = new Quest(QuestConstants.DAMAGE_SKIN, QuestStatus.Started);
                    qm.addQuest(q);
                }
                q.setQrValue(dssd.getDamageSkinID() + "");
                chr.write(WvsContext.questRecordMessage(q));
                chr.getField().broadcastPacket(UserPacket.setDamageSkin(chr));
                break;
        }
    }

    @Handler(op = InHeader.USER_CREATE_AURA_BY_GRENADE)
    public static void handleUserCreateAuraByGrenade(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int objID = inPacket.decodeInt();
        int skillID = SkillConstants.getActualSkillIDfromSkillID(inPacket.decodeInt());
        if (!chr.hasSkill(skillID)) {
            chr.getOffenseManager().addOffense("Tried creating an aura by grenade with unavailable skill.", 0, skillID);
            return;
        }
        Position position = inPacket.decodePosition();
        byte isLeft = inPacket.decodeByte();
        SkillInfo fci = SkillData.getSkillInfoById(skillID);
        int slv = chr.getSkill(skillID).getCurrentLevel();
        AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
        aa.setPosition(position);
        aa.setSkillID(skillID);
        aa.setSlv(slv);
        aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
        chr.getField().spawnAffectedArea(aa);
    }


    // Skill refactor
/*
    public static void handleUserSkillUseRequest2(Char chr, InPacket inPacket) {
        Field field = chr.getField();
        SkillUseInfo sui = new SkillUseInfo();

        if (GameConstants.getMaplerunnerField(field.getId()) == -1
                && ((field.getFieldLimit() & FieldOption.SkillLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.MoveSkillOnly.getVal()) > 0)) {
            chr.dispose();
            return;
        }
        inPacket.decodeInt(); // get_update_time
        int skillID = inPacket.decodeInt();
        if (JobConstants.isZero(chr.getJob())) {
            inPacket.decodeByte(); // COutPacket::Encode1((COutPacket *)&v74, 0); :thinking:
        }
        int slv = inPacket.decodeInt();
        new ProcessType().decode(inPacket); // Not using anything from Process type as of now
        int option = inPacket.decodeInt();
        if (((option >> 4) & 1) != 0) {
            inPacket.decodeShort();
            inPacket.decodeShort();
        }

        // DoActiveSkill_Summon
        if (SkillConstants.isSummon(skillID)) {
            if (skillID == 35111002) {
                sui.rockNshockSize = inPacket.decodeByte();
                if (sui.rockNshockSize == 2) {
                    for (int i = 0; i < 2; i++) {
                        sui.rockNshockLifes[i] = inPacket.decodeInt();
                    }
                }
            }
            sui.position = inPacket.decodePosition();
            sui.isLeft = inPacket.decodeByte() != 0;
            inPacket.decodeByte(); // unk
            inPacket.decodeByte(); // hardcoded 0 (?)
        }

        // DoActiveSkill_TownPortal
        else if (skillID == Bishop.MYSTIC_DOOR || skillID == Job.DECENT_MYSTIC_DOOR_V || skillID == BeastTamer.EKA_EXPRESS) {
            sui.position = inPacket.decodePosition();
            inPacket.decodeByte(); // unk

        


        // SendSkillUseRequest
        } else {
            if (SkillConstants.isAntiRepeatBuff(skillID) || SkillConstants.someUpgradeSkillCheck(skillID)) {
                sui.position = inPacket.decodePosition(); // chrPos
            }
            if (SkillConstants.isNoProjectileConsumptionSkill(skillID)) {
                sui.projectileItemId = inPacket.decodeInt();
            }
            // Affected Areas
            if (skillID == 100001261        // Time Distortion
                    || skillID == 80001408    // No-Name Skill
                    || skillID == 25111206    // Spirit Trap
                    || skillID == 12121005    // Burning Conduit
                    || skillID == 21121068    // Maha's Domain
                    || skillID == 37110002    // Hammer Smash
                    || skillID == 80001839    // Bellflower Barrier
                    || skillID == 80001840    // Blossom Barrier
                    || skillID == 25111012    // Spirit Frenzy
                    || skillID == 25121055    // Spirit Incarnation
                    || skillID == 400020051    // Ludicrous Speed
                    || skillID == 400021039    // Champ Charge
                    || skillID == 33121016    // Drill Salvo
                    || skillID == 33111013    // Hunting Assistant Unit
                    || skillID == 131001107    // Posie
                    || skillID == 131001207    // Breezy
                    || skillID == 51120057    // Radiant Cross - Spread
                    || skillID == 400031012    // Primal Fury
                    || skillID == 400001017    // Pirate's Banner
                    || (skillID == 400021041 || skillID > 400021048 && skillID <= 400021050)) {
                sui.position = inPacket.decodePosition();
            } else {
                if (skillID != 400020046) {
                    if (skillID == 152121041) {
                        sui.position = inPacket.decodePosition();
                    }
                    // if (a4) {
                    if (skillID == Paladin.GUARDIAN || skillID == Bishop.DISPEL || skillID == BeastTamer.MEOW_CURE) {

                        if (skillID == Paladin.GUARDIAN) {   // Guardian (Pally Revive)
                            inPacket.decodeInt(); // Resurrected Character?
                        }
                        if (skillID == Bishop.DISPEL || skillID == BeastTamer.MEOW_CURE) {
                            inPacket.decodeShort();
                            inPacket.decodeByte();
                        }
                    }
                    // }

                    // if (a5) {

                    // }
                }
            }
        }
    }
*/


    @Handler(op = InHeader.USER_SKILL_USE_REQUEST)
    public static void handleUserSkillUseRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        if (GameConstants.getMaplerunnerField(field.getId()) == -1
                && ((field.getFieldLimit() & FieldOption.SkillLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.MoveSkillOnly.getVal()) > 0)) {
            chr.dispose();
            return;
        }

        SkillUseInfo skillUseInfo = new SkillUseInfo();
        inPacket.decodeInt(); // crc
        int skillID = inPacket.decodeInt();

        chr.chatMessage("SkillID : " + skillID);

        if (skillID == 20038001 || skillID == 8001 || skillID == 60018001 || skillID == 40028001 || skillID == 160008001 || skillID == 30008001 || skillID == 110008001 ||
                skillID == 20028001 || skillID == 60008001 || skillID == 140008001 || skillID == 10008001 || skillID == 30018001 || skillID == 150018001 || skillID == 20018001 || skillID == 20058001
                || skillID == 30028001 || skillID == 20008001 || skillID == 20048001 || skillID == 150008001 || skillID == 60028001 || skillID == 40018001 || skillID == 100008001 || skillID == 2311002 ||
                skillID == 50008001 || skillID == 130008001 || skillID == 400001001) {
            chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("This skill is currently disabled.")));
            chr.dispose();
            return;
        }

        // if (skillID == DarkKnight.RADIANT_EVIL) {
        //     chr.addSkillCoolTime(DarkKnight.RADIANT_EVIL, 100000);
        //  }


        if (JobConstants.isZero(chr.getJob()) && skillID != 400001045) {
            inPacket.decodeByte(); // unk
        }

        int slv = inPacket.decodeInt();
        new ProcessType(skillUseInfo).decode(inPacket); // Not using anything from Process type as of now
        int option = inPacket.decodeInt();
        if (((option >> 4) & 1) != 0) {
            inPacket.decodeShort();
            inPacket.decodeShort();
        }

        if (skillID == 4221052 || skillID == 400041021) { // fix for shadower hyper
            chr.write(UserLocal.skillUseResult((byte) 1, skillID));
        }
        if (skillID == Adele.RESONANCE_RUSH) {
            skillUseInfo.objectId = inPacket.decodeInt();
        }

        boolean isByUnreliableMemory = option == 1824;
        if ((!SkillConstants.isNoRemoteEncode(skillID)) && ((isByUnreliableMemory) ||  (chr.applyMpCon(skillID, slv) && ((chr.checkAndSetSkillCooltime(skillID) || SkillConstants.isBypassCooldownSkill(skillID)) || chr.hasSkillCDBypass())))) {
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(skillID, chr.getLevel(), slv, 0)));
            Char copy = chr.getCopy();
            if (copy != null) {
                chr.write(UserRemote.effect(copy.getId(), Effect.skillUse(skillID, chr.getLevel(), slv, 0)));
            }
            Job sourceJobHandler = c.getChr().getJobHandler();
            SkillInfo si = SkillData.getSkillInfoById(skillID);
            if (chr.getParty() != null && si != null && (si.isMassSpell() && si.getRects().size() > 0) && !SkillConstants.isAuraSkill(skillID)) {
                Rect r = si.getFirstRect();
                if (r != null) {
                    Rect rectAround = chr.getRectAround(r);
                    for (PartyMember pm : chr.getParty().getOnlineMembers()) {
                        if (pm.getChr() != null && pm.getFieldID() == chr.getFieldID() && rectAround.hasPositionInside(pm.getChr().getPosition())) {
                            Char ptChr = pm.getChr();
                            Effect effect = Effect.skillAffected(skillID, slv, 0);
                            if (ptChr != chr) { // Caster shouldn't get the Affected Skill Effect
                                chr.getField().broadcastPacket(UserRemote.effect(ptChr.getId(), effect), ptChr);
                                ptChr.write(UserPacket.effect(effect));
                            }
                            sourceJobHandler.handleSkill(chr, ptChr.getTemporaryStatManager(), skillID, slv, inPacket, skillUseInfo);
                        }
                    }
                }
            } else {
                sourceJobHandler.handleSkill(chr, chr.getTemporaryStatManager(), skillID, slv, inPacket, skillUseInfo);
            }
        }
        chr.dispose();
    }

    @Handler(op = InHeader.SET_SON_OF_LINKED_SKILL_REQUEST)
    public static void handleSetSonOfLinkedSkillRequest(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();
        int sonID = inPacket.decodeInt();
        short jobID = chr.getJob();
        Account acc = chr.getAccount();
        Char son = acc.getCharacters().stream().filter(c -> c.getId() == sonID).findAny().orElse(null);
        // remove old link skill if another with the same skill exists
        acc.getLinkSkills().stream()
                .filter(ls -> SkillConstants.getOriginalOfLinkedSkill(ls.getLinkSkillID()) == skillID)
                .findAny()
                .ifPresent(oldLinkSkill -> acc.removeLinkSkillByOwnerID(oldLinkSkill.getUsingID()));
        // if the skill is not null and we expect this link skill id from the given job
        int linkSkillID = SkillConstants.getLinkSkillByJob(jobID);
        if (son != null && SkillConstants.getOriginalOfLinkedSkill(linkSkillID) == skillID) {
            acc.addLinkSkill(chr, sonID, linkSkillID);
            chr.write(WvsContext.setSonOfLinkedSkillResult(LinkedSkillResultType.SetSonOfLinkedSkillResult_Success,
                    son.getId(), son.getName(), skillID, null));
        } else {
            chr.write(WvsContext.setSonOfLinkedSkillResult(LinkedSkillResultType.SetSonOfLinkedSkillResult_Fail_Unknown,
                    0, null, 0, null));
        }
    }

    @Handler(op = InHeader.USER_THROW_GRENADE)
    public static void handleUserThrowGrenade(Char chr, InPacket inPacket) {
        SkillUseInfo skillUseInfo = new SkillUseInfo();

        Position pos = inPacket.decodePositionInt();
        Position pos2 = inPacket.decodePositionInt();
        int keyDown = inPacket.decodeInt();
        int skillID = inPacket.decodeInt();
        int bySummonedID = inPacket.decodeInt(); // slv according to ida, but let's just take that server side
        boolean left = inPacket.decodeByte() != 0;
        int attackSpeed = inPacket.decodeInt();
        int grenadeID = inPacket.decodeInt();
        Skill skill = chr.getSkill(SkillConstants.getLinkedSkill(skillID));
        int slv = skill == null ? 0 : skill.getCurrentLevel();
        boolean success = true;
        if (SkillData.getSkillInfoById(SkillConstants.getActualSkillIDfromSkillID(skillID)).hasCooltime()) {
            if (chr.hasSkillOnCooldown(skillID)) {
                success = false;
            } else {
                chr.setSkillCooldown(skillID, (byte) slv);
            }
        }
        if (success) {
            chr.getField().broadcastPacket(UserRemote.throwGrenade(chr.getId(), grenadeID, pos, keyDown, skillID,
                    bySummonedID, slv, left, attackSpeed), chr);
            Job jobHandler = chr.getJobHandler();
            jobHandler.handleSkill(chr, chr.getTemporaryStatManager(), skillID, (byte) slv, inPacket, skillUseInfo);
        }
    }


    @Handler(op = InHeader.PASSIVE_SKILL_INFO_UPDATE)
    public static void handlePassiveSkillInfoUpdate(Client client, InPacket inPacket) {
        final var chr = client.getChr();
        if (chr != null && chr.getJobHandler() != null) {
            chr.getJobHandler().handleRecoverySchedulers();
        }
    }


    @Handler(op = InHeader.USER_DESTROY_GRENADE)
    public static void handleUserDestroyGrenade(Char chr, InPacket inPacket) {
        int grenadeID = inPacket.decodeInt();
        byte unk = inPacket.decodeByte();
        int skillID = inPacket.decodeInt();
        chr.getField().broadcastPacket(UserRemote.destroyGrenade(chr.getId(), grenadeID), chr);
    }

    @Handler(op = InHeader.USER_B2_BODY_REQUEST)
    public static void handleB2BodyRequest(Char chr, InPacket inPacket) { // TODO  turn the 'friction' switch off, once I find it..
        short requestType = inPacket.decodeShort();
        int chrId = inPacket.decodeInt();

        switch (requestType) {
            case 0:
                byte unk1 = inPacket.decodeByte();
                int b2BodyId = inPacket.decodeInt();
                byte type = inPacket.decodeByte();
                Position position = inPacket.decodePosition();
                short nRadius = 0;
                short fRadius = 0;
                if (type == 5) {
                    nRadius = inPacket.decodeShort();
                    fRadius = inPacket.decodeShort();
                }
                short scale = inPacket.decodeShort();
                int skillId = inPacket.decodeInt();
                int slv = inPacket.decodeShort();
                short unk2 = inPacket.decodeShort(); // 0 encoded
                int duration = inPacket.decodeInt(); // in MS
                short unk3 = inPacket.decodeShort(); // 10 encoded

                B2Body b2Body = new B2Body(chr, b2BodyId, type, position, nRadius, fRadius, scale, skillId, slv, duration);
                chr.write(UserLocal.b2BodyResult(requestType, b2Body));
                break;
            case 3:
                b2BodyId = inPacket.decodeInt();
                skillId = inPacket.decodeInt();
                slv = inPacket.decodeInt();
                int maxSpeedX = inPacket.decodeInt();
                int maxSpeedY = inPacket.decodeInt();
                b2Body = new B2Body(chr, b2BodyId, skillId, slv, maxSpeedX, maxSpeedY);
                chr.write(UserLocal.b2BodyResult(requestType, b2Body));
                break;
            case 4:
                b2BodyId = inPacket.decodeInt();
                position = inPacket.decodePosition();
                skillId = inPacket.decodeInt();
                boolean left = inPacket.decodeByte() != 0;
                unk1 = inPacket.decodeByte(); // 0 encoded
                slv = inPacket.decodeShort();
                unk2 = inPacket.decodeShort(); // 0 encoded
                unk3 = inPacket.decodeShort(); // 10 encoded
                maxSpeedX = inPacket.decodeInt();
                maxSpeedY = inPacket.decodeInt();
                b2Body = new B2Body(chr, b2BodyId, skillId, slv, maxSpeedX, maxSpeedY);
                b2Body.setPosition(position);
                chr.write(UserLocal.b2BodyResultNew(requestType, b2Body));
                break;
            default:
                log.error(String.format("Unhandled B2Body Request Type: %d", requestType));
                chr.chatMessage(String.format("Unhandled B2Body Request Type: %d", requestType));
                break;
        }
    }

    @Handler(op = InHeader.USER_SKILL_PREPARE_START)
    public static void handleUserSkillPrepareStart(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int slv = inPacket.decodeInt();

        // dunno
        inPacket.decodeShort(); // unk struct
        inPacket.decodeByte(); // unk struct
        short attSpeed = inPacket.decodeShort();
        inPacket.decodeShort();

        short actionSpeed = inPacket.decodeShort();
        int curTime = inPacket.decodeInt();

        // Commented, as there as several invisible skills that get caught in this if check
/*        if (!chr.hasSkill(skillId) || chr.getSkillLevel(skillId) <= 0) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to prepare a keydown skill %d without having the skill", chr.getId(), skillId));
            return;
        }*/
        if (SkillConstants.isKeyDownSkill(skillId)) {
            Skill skill = chr.getSkill(skillId);
            SkillInfo si = null;
            if (skill != null) {
                si = SkillData.getSkillInfoById(skill.getSkillId());
            }

            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o1 = new Option();
            Option o2 = new Option();

            if (skillId == Xenon.ION_THRUST) {
                if (si != null)
                    ((Xenon) chr.getJobHandler()).applySupplyCost(skillId, chr.getSkillLevel(skillId), si);
            }

            if (skillId == PRESSURE_VOID) {
                // only gets sent once.
                if (chr.hasSkill(PRESSURE_VOID) && tsm.getOptByCTSAndSkill(CharacterTemporaryStat.KeyDownAreaMoving, PRESSURE_VOID) == null) {
                    o1.nOption = 16;
                    o1.rOption = PRESSURE_VOID;
                    tsm.putCharacterStatValue(CharacterTemporaryStat.KeyDownAreaMoving, o1);
                    tsm.sendSetStatPacket();
                }
            } else {
                o1.nValue = 1;
                o1.nReason = chr.getJob();
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieKeyDownTime, o1);
                tsm.sendSetStatPacket();
            }
            if (skillId == Adele.AETHER_GUARD) {
                o1.nOption = 1;
                o1.rOption = Adele.AETHER_GUARD;
                o1.tOption = 8;
                tsm.putCharacterStatValue(CharacterTemporaryStat.AetherGuard, o1);
                o2.nValue = 40;
                o2.nReason = chr.getJob();
                o2.tOption = 8;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamReduceR, o2);
                tsm.sendSetStatPacket();
            }
            if (skillId == HoYoung.DREAM_OF_SHANGRI_LA) {
                o1.nValue = 1;
                o1.nReason = HoYoung.DREAM_OF_SHANGRI_LA;
                o1.tTerm = 20;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieNotDamaged, o1);
                o2.nValue = 1;
                o2.nReason = HoYoung.DREAM_OF_SHANGRI_LA;
                o2.tTerm = 20;
                tsm.putCharacterStatValue(CharacterTemporaryStat.unk214_2, o2);
                tsm.sendSetStatPacket();
            }
            chr.getJobHandler().handleKeyDownSkill(chr, skillId, inPacket);
            chr.getField().broadcastPacket(UserRemote.skillPrepare(chr, skillId, chr.getSkillLevel(skillId)));
        }
    }

    @Handler(op = InHeader.USER_SKILL_PREPARE_STOP)
    public static void handleUserSkillPrepareStop(Char chr, InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        if ((field.getFieldLimit() & FieldOption.SkillLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.MoveSkillOnly.getVal()) > 0) {
            chr.dispose();
            return;
        }
        int skillId = inPacket.decodeInt();
        boolean bool = inPacket.decodeByte() != 0; // unk

        if (SkillConstants.isKeyDownSkill(skillId)) {
            chr.getField().broadcastPacket(UserRemote.skillCancel(chr.getId(), skillId), chr);
            // USER_SKILL_CANCEL_REQUEST has the same one but such as Bishop's Bigbang, DualBlade's FinalCut don't send it, causes infinity MP consumption
            if (tsm.hasStat(CharacterTemporaryStat.IndieKeyDownTime)) {
                tsm.removeStat(CharacterTemporaryStat.IndieKeyDownTime, true);
            }
            if (tsm.hasStat(CharacterTemporaryStat.KeyDownAreaMoving)) {
                tsm.removeStat(CharacterTemporaryStat.KeyDownAreaMoving, true);
            }

            chr.getJobHandler().handleCancelKeyDownSkill(chr, SkillConstants.getCorrectCooltimeSkillID(skillId));
                log.debug(String.format("Stop skill keydown  |  SkillId = %d", skillId));
        }
        //tsm.removeStatsBySkill(skillId); // <- do we need this? buffs should be removed by JobHandler#handleSkillRemove
        chr.getJobHandler().handleSkillRemove(chr, skillId);
    }


    @Handler(op = InHeader.USER_TEMPORARY_STAT_UPDATE_REQUEST)
    public static void handleUserTemporaryStatUpdateRequest(Char chr, InPacket inPacket) {
        chr.getTemporaryStatManager().resetByTime(Util.getCurrentTime());
    }

    @Handler(op = InHeader.MATRIX_UPDATE_REQUEST)
    public static void handleMatrixUpdateRequest(Char chr, InPacket inPacket) {
        List<MatrixRecord> mrs = chr.getSortedMatrixRecords();
        int typeVal = inPacket.decodeInt();
        MatrixUpdateRequest type = MatrixUpdateRequest.getByVal(typeVal);
        if (type == null) {
            log.error(String.format("Unknown matrix udpate request type %d", typeVal));
            return;
        }
        switch (type) {
            case Activate:
                int pos = inPacket.decodeInt(); // pos
                int idk1 = inPacket.decodeInt(); // swapPos
                int idk2 = inPacket.decodeInt(); // slotPrev
                int toPos = inPacket.decodeInt(); // slot
                boolean byDrag = inPacket.decodeByte() != 0; // by Dragging Node with the Cursor
                MatrixRecord mr1 = mrs.get(pos);
                if (pos >= mrs.size()) {
                    return;
                }
                if (!byDrag && toPos < 0) {
                    pos = chr.getFirstOpenMatrixSlot(); // Check  Locked Slots
                }
                if (toPos == -1337) {
                    chr.chatMessage("You have no empty node slots.");
                    return;
                }
                if (!mr1.isActive()) {
                    // Check if new position is filled by another Node.
                    if (idk1 >= 0) {
                        MatrixRecord oldMR = mrs.get(idk1);
                        if (oldMR != null) {
                            if (idk2 < 0) {
                                oldMR.setActive(false);
                                oldMR.addSkillsToChar(chr, true);
                            }
                            oldMR.setPosition(idk2);
                        }
                    }
                    mr1.activate(chr, pos);
                    chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
                }
                break;
            case Deactivate: //
                pos = inPacket.decodeInt();
                toPos = inPacket.decodeInt();
                if (pos >= 0 && pos < mrs.size()) {
                    mr1 = mrs.get(pos);
                    if (mr1.isActive()) {
                        mr1.setActive(false);
                        mr1.addSkillsToChar(chr, true);
                        mr1.setPosition(toPos);
                        chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
                    }
                }
                break;
            case Enhance:
                int upgradePos = inPacket.decodeInt();
                int size = inPacket.decodeInt();
                MatrixRecord mr = mrs.get(upgradePos);
                Set<MatrixRecord> removeMrs = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    pos = inPacket.decodeInt();
                    if (pos >= 0 && pos < mrs.size()) {
                        MatrixRecord mr2 = mrs.get(pos);
                        if (!mr2.isActive() && mr2.getIconID() == mr.getIconID()) {
                            removeMrs.add(mr2);
                        }
                    }
                }
                int exp = 0;
                int oldSlv = mr.getSlv();
                chr.getMatrixRecords().removeAll(removeMrs);
                for (MatrixRecord removeMr : removeMrs) {
                    exp += VCoreData.getNodeInfo(removeMr).getExpEnforce();
                }
                mr.setExp(mr.getExp() + exp);
                VNodeInfo vni = VCoreData.getNodeInfo(mr);
                if (mr.isActive()) {
                    mr.addSkillsToChar(chr, true);
                }
                while (mr.getExp() >= vni.getNextExp() && mr.getSlv() < mr.getMaxLevel()) {
                    mr.setExp(mr.getExp() - vni.getNextExp());
                    mr.setSlv(mr.getSlv() + 1);
                    vni = VCoreData.getNodeInfo(mr);
                }
                if (mr.isActive()) {
                    mr.addSkillsToChar(chr, false);
                }
                chr.getMatrixRecords().removeAll(removeMrs);
                chr.write(WvsContext.nodeEnhanceResult(upgradePos, exp, oldSlv, mr.getSlv()));
                chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
                break;
            case Disassemble:
                pos = inPacket.decodeInt();
                if (pos >= 0 && pos < mrs.size()) {
                    mr = mrs.get(pos);
                    int shards = VCoreData.getNodeInfo(mr).getExtract();
                    chr.addNodeShards(shards);
                    chr.getMatrixRecords().remove(mr);
                    chr.write(WvsContext.nodeDisassembleResult(shards));
                }
                break;
            case DisassembleGroup:
                size = inPacket.decodeInt();
                removeMrs = new HashSet<>();
                int shards = 0;
                for (int i = 0; i < size; i++) {
                    pos = inPacket.decodeInt();
                    if (pos >= 0 && pos < mrs.size()) {
                        mr = mrs.get(pos);
                        if (!mr.isActive()) {
                            removeMrs.add(mr);
                            shards += VCoreData.getNodeInfo(mr).getExtract();
                        }
                    }
                }
                chr.getMatrixRecords().removeAll(removeMrs);
                chr.addNodeShards(shards);
                chr.write(WvsContext.nodeDisassembleResult(shards));
                break;
            case CraftNode:
                int iconID = inPacket.decodeInt();
                int shardCost = GameConstants.getNodeCreateShardCost(iconID);
                List<VCoreInfo> infos = new ArrayList<>(VCoreData.getPossibilitiesByJob(chr.getJob()));
                VCoreInfo vci = Util.findWithPred(infos, v -> v.getIconID() == iconID);
                infos.remove(Util.findWithPred(infos, v -> v.getIconID() == vci.getIconID()));
                if (vci == null) {
                    chr.chatMessage("Use a node from your own job.");
                    return;
                }
                if (shardCost > chr.getNodeShards()) {
                    chr.chatMessage("You don't have enough node shards.");
                    return;
                }
                mr = new MatrixRecord();
                mr.setIconID(vci.getIconID());
                mr.setMaxLevel(vci.getMaxLevel());
                mr.setSkillID1(vci.getSkillID());
                mr.setSlv(1);
                if (vci.isEnforce()) {
                    for (int i = 0; i < 2; i++) {
                        VCoreInfo randVci = Util.getRandomFromCollection(infos.stream().filter(VCoreInfo::isEnforce).collect(Collectors.toList()));
                        infos.remove(randVci);
                        switch (i) {
                            case 0:
                                mr.setSkillID2(randVci.getSkillID());
                                break;
                            case 1:
                                mr.setSkillID3(randVci.getSkillID());
                                break;
                        }
                    }
                }
                chr.addNodeShards(-shardCost);
                chr.getMatrixRecords().add(mr);
                chr.write(WvsContext.nodeCraftResult(mr));
                chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), true, typeVal, 0));
                break;
            case CraftNodestone:
                Npc npc = (Npc) chr.getField().getLifeByTemplateId(1540945); // Archelle
                chr.getScriptManager().startScript(npc.getTemplateId(), npc.getObjectId(), "craft_nodestone", ScriptType.Npc);
                break;
            case Swap: //
                int switcher_id = inPacket.decodeInt(); // the main node that is dragged
                int switched_id = inPacket.decodeInt(); // one that is automatically swapped as well
                int fromPos = inPacket.decodeInt();
                int toPos1 = inPacket.decodeInt();

                if (switcher_id >= 0 && switcher_id < mrs.size()) {
                    mr = mrs.get(switcher_id);
                    mr.addSkillsToChar(chr, true); // remove Skill
                    mr.setPosition(toPos1);
                    mr.addSkillsToChar(chr, false); // add Skill
                    if (switched_id >= 0 && switched_id < mrs.size()) {
                        MatrixRecord otherMr = mrs.get(switched_id);
                        otherMr.addSkillsToChar(chr, true); // remove Skill
                        otherMr.setPosition(fromPos);
                        otherMr.addSkillsToChar(chr, false); // add Skill
                    }
                    chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), true, typeVal, switcher_id));
                }
                break;
            case ExpandSlot:
                int slot = inPacket.decodeInt();
                inPacket.decodeInt(); // Always -1
                int[] aSlotPrice = {0, 0, 0, 0, 25900000, 30800000, 36400000, 43400000, 51800000, 60900000, 72100000,
                        85400000, 101500000, 119700000, 142100000, 168000000, 198800000, 235200000, 278600000};
                int nReqLevel = 200 + ((slot - 3) * 5);
                double dSlotOneMultiplier = 1.0;
                double dSlotTwoMultiplier = 3.7029;
                int nLevelGap = nReqLevel - chr.getLevel();
                switch (nLevelGap) {
                    case 1:
                        dSlotOneMultiplier = 1.0;
                        break;
                    case 2:
                        dSlotOneMultiplier = 1.3656;
                        break;
                    case 3:
                        dSlotOneMultiplier = 1.8042;
                        break;
                    case 4:
                        dSlotOneMultiplier = 2.3307;
                        break;
                    case 5:
                        dSlotOneMultiplier = 2.9624;
                        break;
                    case 6:
                        dSlotTwoMultiplier = 3.7029;
                        break;
                    case 7:
                        dSlotTwoMultiplier = 4.6285;
                        break;
                    case 8:
                        dSlotTwoMultiplier = 5.7857;
                        break;
                    case 9:
                        dSlotTwoMultiplier = 7.2321;
                        break;
                    case 10:
                        dSlotTwoMultiplier = 9.0401;
                        break;
                }
                double dPrice = aSlotPrice[slot] * (nLevelGap <= 5 ? dSlotOneMultiplier : dSlotTwoMultiplier);
                chr.deductMoney((long) dPrice);
                chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
                break;
            default:
                log.error(String.format("Unhandled matrix udpate request type %s", type));
        }
        chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0)); // Note: This needs to be in each case cause params are not the same
    }

    @Handler(op = InHeader.SHOOT_OBJECT_CREATE_REQUEST)
    public static void handleShootObjectCreateRequest(Char chr, InPacket inPacket) {
        List<ShootObject> shootObjIdList = new ArrayList<>();
        SkillUseInfo skillUseInfo = new SkillUseInfo();

        int skillId = inPacket.decodeInt();
        int slv = inPacket.decodeInt();
        new ProcessType(skillUseInfo).decode(inPacket);
        int action = inPacket.decodeInt(); // Unknown
        int actionSpeed = inPacket.decodeInt(); // action Speed
        inPacket.decodeInt(); // Unknown
        inPacket.decodeInt(); // Unknown
        inPacket.decodeByte(); // Unknown
        inPacket.decodeByte(); // Unknown

        Position position = inPacket.decodePosition();
        inPacket.decodeByte(); // unk

        if (skillId == Adele.IMPALE) {
            inPacket.decodeArr(8);
            inPacket.decodeArr(8);
            inPacket.decodeArr(5);
		}	
        if (skillId == 64111004) {
            inPacket.decodeArr(22);
        }

        int loopSize = inPacket.decodeInt();
        for (int i = 0; i < loopSize; i++) {
            ShootObject shootObject = new ShootObject(chr, inPacket);
            shootObjIdList.add(shootObject);
        }

        Job jobHandler = chr.getJobHandler();
        jobHandler.handleShootObj(chr, skillId, chr.getSkillLevel(skillId));

        chr.write(UserLocal.shootObjectCreated(skillId, slv, shootObjIdList));
        chr.getField().broadcastPacket(UserRemote.shootObject(chr, action, skillId, shootObjIdList), chr);
        if (chr.getCopy() != null) {
            chr.write(UserRemote.shootObject(chr.getCopy(), action, skillId, shootObjIdList));
        }
        shootObjIdList.clear();
    }


    @Handler(op = InHeader.MAKING_SKILL_REQUEST)
    public static void handleMakingSkillRequest(Char chr, InPacket inPacket) {
        int recipeID = inPacket.decodeInt();
        MakingSkillRecipe msr = SkillData.getRecipeById(recipeID);
        if (chr == null || msr == null || !msr.isAbleToBeUsedBy(chr)) {
            return;
        }
        List<Tuple<Integer, Integer>> itemResult = new ArrayList<>();
        for (Tuple<Integer, Integer> repice : msr.getIngredient()) {
            int itemID = repice.getLeft();
            int count = repice.getRight();
            if (chr.hasItemCount(itemID, count)) {
                chr.consumeItem(itemID, count);
                itemResult.add(new Tuple<>(itemID, -count));
            } else {
                chr.write(UserLocal.noticeMsg("You need more materials.", true));
                return;
            }
        }
        int reqSkillID = msr.getReqSkillID();
        Item crafted = null;
        MakingSkillRecipe.TargetElem target = new MakingSkillRecipe.TargetElem();
        MakingSkillResult result = MakingSkillResult.CRAFTING_FAILED;
        if (Randomizer.nextInt(100) < MakingSkillRecipe.getSuccessProb(reqSkillID, msr.getRecommandedSkillLevel(), chr.getMakingSkillLevel(reqSkillID)) || recipeID / 10000 <= 9201) {
            int rand = Randomizer.nextInt(100);
            List<MakingSkillRecipe.TargetElem> targets = msr.getTarget();
            while (true) {
                target = targets.get(Randomizer.rand(0, targets.size() - 1));
                if (target.getProbWeight() >= rand) {
                    break;
                } else {
                    rand = Randomizer.nextInt(100);
                }
            }
            crafted = ItemData.getItemDeepCopy(target.getItemID(), Randomizer.isSuccess(chr.getMakingSkillLevel(reqSkillID) * 2));
            if (crafted == null) {
                chr.getField().broadcastPacket(FieldPacket.makingSkillResult(chr.getId(), recipeID, MakingSkillResult.UNKNOWN_ERROR, target, 0));
                return;
            }
            crafted.setQuantity(target.getCount());
            result = MakingSkillResult.SUCESS_COOL;
            if (ItemConstants.isEquip(target.getItemID())) {
                ((Equip) crafted).addAttribute(EquipAttribute.Crafted);
                crafted.setOwner(chr.getName());
                crafted.setQuantity(1);// equipment shouldn't be more than one
            }
            if (msr.getExpiredPeriod() > 0) {
                crafted.setDateExpire(FileTime.fromLong(System.currentTimeMillis() + ((long) msr.getExpiredPeriod() * 60 * 1000)));
            }
            if (msr.isNeedOpenItem()) {
                chr.removeSkillAndSendPacket(recipeID);
            }
        }

        boolean success = result != MakingSkillResult.CRAFTING_FAILED;
        int incSkillProficiency = msr.getIncProficiency(chr, success);
        if (crafted != null) {
            chr.addItemToInventory(crafted);
            itemResult.add(new Tuple<>(crafted.getItemId(), crafted.getQuantity()));
        }
        chr.addMakingSkillProficiency(recipeID, incSkillProficiency);
        chr.addStatAndSendPacket(Stat.fatigue, msr.getIncFatigability());
        if (success) {
            Stat trait = Stat.craftEXP;
            switch (reqSkillID) {
                case 92000000:
                    trait = Stat.senseEXP;
                    break;
                case 92010000:
                    trait = Stat.willEXP;
                    break;
            }
            chr.addTraitExp(trait, (int) Math.pow(2, chr.getMakingSkillLevel(reqSkillID) + 2));
        }
        chr.getField().broadcastPacket(FieldPacket.makingSkillResult(chr.getId(), recipeID, result, target, incSkillProficiency));
        chr.write(UserPacket.effect(Effect.gainQuestItem(itemResult)));
    }

    @Handler(op = InHeader.SHOOT_OBJECT_EXPLODE_REQUEST)
    public static void handleShootObjectExplodeRequest(Char chr, InPacket inPacket) {
        int size = inPacket.decodeInt();
        List<Integer> shootObjectIdList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            shootObjectIdList.add(inPacket.decodeInt());
        }

        if (JobConstants.isFirePoison(chr.getJob()) && chr.hasSkill(FirePoison.POISON_NOVA)) {
            ((FirePoison) chr.getJobHandler()).setExplodeShootObjList(shootObjectIdList);
            EventManager.addEvent(() -> ((FirePoison) chr.getJobHandler()).getExplodeShootObjList().clear(), 6, TimeUnit.SECONDS);
        }
    }

    @Handler(op = InHeader.LINK_SKILL_REQUEST)
    public static void handleLinkSkillRequest(Char chr, InPacket inPacket) {
        int linkSkillID = inPacket.decodeInt();
        if (linkSkillID >= 80000066 && linkSkillID <= 80000070) {
            linkSkillID = 80000055;
        }
        Account acc = chr.getAccount();
        LinkSkill ls = acc.getLinkSkillByLinkSkillId(linkSkillID);
        byte skillLv = 0;
        if (linkSkillID == 80000055) { //Find a better way to do this >.>
            LinkSkill ls2 = acc.getLinkSkillByLinkSkillId(80000066); //Warrior
            LinkSkill ls3 = acc.getLinkSkillByLinkSkillId(80000067); //Mage
            LinkSkill ls4 = acc.getLinkSkillByLinkSkillId(80000068); //Bowman
            LinkSkill ls5 = acc.getLinkSkillByLinkSkillId(80000069); //Thief
            LinkSkill ls6 = acc.getLinkSkillByLinkSkillId(80000070); //Pirate
            if (ls2 != null) {
                skillLv += ls2.getLevel();
            } if (ls3 != null) {
                skillLv += ls3.getLevel();
            }if (ls4 != null) {
                skillLv += ls4.getLevel();
            }if (ls5 != null) {
                skillLv += ls5.getLevel();
            }if (ls6 != null) {
                skillLv += ls6.getLevel();
            }
        }
        if (ls == null) {
            chr.addSkill(linkSkillID, 0, 0);
            chr.removeSkillAndSendPacket(linkSkillID);
            chr.write(WvsContext.linkSkillResult(0, LinkSkillResult.UnknownError));
            return;
        }
        int skillID = ls.getLinkSkillID();
        int level = ls.getLevel();
        if (skillLv > 0) {
            level = skillLv;
        }
        if (ls.getUsingID() == chr.getId()) {
            chr.write(WvsContext.linkSkillResult(skillID, LinkSkillResult.AlreadyActive));
            return;
        }
        ls.setUsingID(chr.getId());
        chr.addSkill(skillID, level, level);
        chr.write(WvsContext.linkedSkillInfo(ls, level));
        chr.write(WvsContext.linkSkillResult(skillID, LinkSkillResult.Link_Success));
    }

    @Handler(op = InHeader.UNLINK_SKILL_REQUEST)
    public static void handleUnlinkSkillRequest(Char chr, InPacket inPacket) {
        int linkSkillID = inPacket.decodeInt();
        if (linkSkillID >= 80000066 && linkSkillID <= 80000070) {
            linkSkillID = 80000055;
        }
        // System.err.println(linkSkillID);
        Account acc = chr.getAccount();
        LinkSkill ls = acc.getLinkSkillByLinkSkillId(linkSkillID);
        if (ls == null) {
            chr.addSkill(linkSkillID, 0, 0);
            chr.removeSkillAndSendPacket(linkSkillID);
            chr.write(WvsContext.linkSkillResult(0, LinkSkillResult.UnknownError));
            return;
        }
        int skillID = ls.getLinkSkillID();
        if (ls.getUsingID() != chr.getId()) {
            chr.write(WvsContext.linkSkillResult(skillID, LinkSkillResult.InvalidRequest));
            return;
        }
        ls.setUsingID(0);
        chr.addSkill(skillID, 0, 0);
        chr.write(WvsContext.unlinkedSkillInfo(ls));
        chr.write(WvsContext.linkSkillResult(skillID, LinkSkillResult.Unlink_Success));
    }

    @Handler(op = InHeader.USER_CREATE_AREA_DOT_REQUEST)
    public static void handleUserCreateAreaDoTRequest(Char chr, InPacket inPacket) {
        SkillUseInfo skillUseInfo = new SkillUseInfo();
        inPacket.decodeInt(); // unk
        int skillId = inPacket.decodeInt();
        int unk = inPacket.decodeInt(); // unk

        short loopSize = inPacket.decodeShort();
        for (int i = 0; i < loopSize; i++) {
            Rect rect = inPacket.decodeIntRect();
        }

        // TODO: this probably isn't the correct way to handle this
        if (skillId == Adele.SHARDBREAKER)
        {
            skillUseInfo.spawnCrystals = true;
        }

        chr.getJobHandler().handleSkill(chr, chr.getTemporaryStatManager(), skillId, chr.getSkillLevel(skillId), inPacket, skillUseInfo);
    }


}
