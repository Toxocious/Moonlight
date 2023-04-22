package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.BeautyAlbum;
import net.swordie.ms.client.character.b2body.B2Body;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.jobs.cygnus.ThunderBreaker;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Android;
import net.swordie.ms.life.Familiar;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;

/**
 * Created on 1/2/2018.
 */
public class UserLocal {
    public static OutPacket noticeMsg(String msg, boolean autoSeparated) {
        OutPacket outPacket = new OutPacket(OutHeader.NOTICE_MSG);

        outPacket.encodeString(msg);
        outPacket.encodeByte(autoSeparated);

        return outPacket;
    }

    public static OutPacket chatMsg(ChatType colour, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.CHAT_MSG);

        outPacket.encodeShort(colour.getVal());
        outPacket.encodeString(msg);

        return outPacket;
    }

    public static OutPacket setForcedReviveTime(int duration, int delay, boolean unk) {
        OutPacket outPacket = new OutPacket(OutHeader.FORCED_REVIVE_TIME);
        outPacket.encodeInt(duration);
        outPacket.encodeInt(delay);
        outPacket.encodeByte(unk);
        return outPacket;
    }

    public static OutPacket videoByScript(String videoPath, boolean isMuted) {
        OutPacket outPacket = new OutPacket(OutHeader.VIDEO_BY_SCRIPT);

        outPacket.encodeString(videoPath);
        outPacket.encodeByte(isMuted);

        return outPacket;
    }

    public static OutPacket beautyAlbumActions(int actionType, Char chr, int styleId, int slotId) {
        OutPacket outPacket = new OutPacket(OutHeader.DECODE_SALON);
        switch (actionType) {
            case 0:
                outPacket.encodeArr("9C F3 70 EB");
                outPacket.encodeByte(3);
                outPacket.encodeInt(1);
                outPacket.encodeByte(31);
                outPacket.encodeInt(100);

                outPacket.encodeInt(chr.getHairInventory().getSlots());
                outPacket.encodeInt(chr.getFaceInventory().getSlots());

                outPacket.encodeInt(chr.getHairInventory().getSlots());
                for (int i = 0; i < chr.getHairInventory().getSlots(); i++) {
                    BeautyAlbum hair = chr.getStyleBySlotId(30000 + i);
                    outPacket.encodeInt(hair == null ? 0 : hair.getStyleID());
                    outPacket.encodeByte(hair == null ? 0 : -1);
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                }

                outPacket.encodeInt(chr.getFaceInventory().getSlots());
                for (int i = 0; i < chr.getFaceInventory().getSlots(); i++) {
                    BeautyAlbum face = chr.getStyleBySlotId(20000 + i);
                    outPacket.encodeInt(face == null ? 0 : face.getStyleID());
                }
                break;
            case 1:
                outPacket.encodeArr("33 4C 50 2C");
                outPacket.encodeByte(1);
                if (slotId / 10000 >= 3) {
                    outPacket.encodeInt(0);
                } else {
                    outPacket.encodeInt(1);
                }
                break;
            case 5:
                outPacket.encodeArr("05 6C 46 83");
                outPacket.encodeByte(7);
                outPacket.encodeInt(1);
                if (styleId / 10000 >= 3) {
                    outPacket.encodeInt(3);
                } else {
                    outPacket.encodeInt(2);
                }
                outPacket.encodeInt(slotId);
                break;
            case 7:
                outPacket.encodeArr("66 46 3C 2E");
                if (styleId / 10000 >= 3) {
                    outPacket.encodeByte(11);
                } else {
                    outPacket.encodeByte(7);
                }
                outPacket.encodeInt(1);
                outPacket.encodeInt(slotId);
                outPacket.encodeInt(styleId);
                if (styleId / 10000 >= 3) {
                    outPacket.encodeByte(styleId == 0 ? 0 : -1);
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                }
                break;
        }
        return outPacket;
    }

    public static OutPacket jaguarActive(boolean active) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_ACTIVE);

        outPacket.encodeByte(active);

        return outPacket;
    }

    public static OutPacket SitTimeCapsule() {
        return new OutPacket(OutHeader.SIT_TIME_CAPSULE);
    }

    public static OutPacket jaguarSkill(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.JAGUAR_SKILL);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket skillUseResult(byte unk, int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_USE_RESULT);

        outPacket.encodeByte(unk);
        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket addPopupSay(int npcID, int duration, String message, String effect) {
        OutPacket outPacket = new OutPacket(OutHeader.ADD_POPUP_SAY);

        outPacket.encodeInt(npcID);
        outPacket.encodeInt(duration);
        outPacket.encodeString(message);
        outPacket.encodeString(effect);
        outPacket.encodeByte(0); // new v208
        return outPacket;
    }

    public static OutPacket incLarknessResponse(LarknessManager larknessManager) {
        OutPacket outPacket = new OutPacket(OutHeader.INC_LARKNESS_RESPONSE);

        outPacket.encodeInt(larknessManager.getGauge());
        outPacket.encodeByte(larknessManager.getLarknessType().getVal());

        return outPacket;
    }

    public static OutPacket royalGuardAttack(boolean attack) {
        OutPacket outPacket = new OutPacket(OutHeader.ROYAL_GUARD_ATTACK);

        outPacket.encodeByte(attack);

        return outPacket;
    }

    public static OutPacket rwMultiChargeCancelRequest(byte unk, int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_USE_RESULT);

        outPacket.encodeByte(unk);
        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket setOffStateForOffSkill(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_OFF_STATE_FOR_OFF_SKILL);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket resetStateForOffSkill() {
        OutPacket outPacket = new OutPacket(OutHeader.RESET_STATE_FOR_OFF_SKILL);

        outPacket.encodeInt(0);

        return outPacket;
    }

    public static OutPacket modHayatoCombo(int amount) {
        OutPacket outPacket = new OutPacket(OutHeader.MOD_HAYATO_COMBO);

        outPacket.encodeShort(amount);

        return outPacket;
    }

    public static OutPacket incJudgementStack(byte amount) {
        OutPacket outPacket = new OutPacket(OutHeader.INC_JUDGEMENT_STACK_RESPONSE);

        outPacket.encodeByte(1);
        outPacket.encodeByte(amount);

        return outPacket;
    }

    public static OutPacket changeStealMemoryResult(byte type, int stealManagerJobID, int position, int skillid, int stealSkillLv, int stealSkillMaxLv) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_STEAL_MEMORY_RESULT);
        StealMemoryType smType = StealMemoryType.getByVal(type);

        outPacket.encodeByte(1); //Set Excl Request
        outPacket.encodeByte(smType.getVal());    //Type

        switch (smType) {
            case STEAL_SKILL:
                outPacket.encodeInt(stealManagerJobID); //jobId  1~5 | 1 = 1stJob , 2 = 2ndJob ... ..
                outPacket.encodeInt(position); //impecMemSkillID // nPOS  0,1,2,3
                outPacket.encodeInt(skillid); //skill
                outPacket.encodeInt(stealSkillLv);   //StealSkill Lv
                outPacket.encodeInt(stealSkillMaxLv);   //StealSkill Max Lv
                break;
            case NO_TARGETS:
            case FAILED_UNK_REASON:
                break;
            case REMOVE_STEAL_MEMORY:
                outPacket.encodeInt(stealManagerJobID);
                outPacket.encodeInt(position);
                outPacket.encodeByte(0);
                break;
            case REMOVE_MEMORY_ALL_SLOT:
                outPacket.encodeInt(skillid);
                break;
            case REMOVE_ALL_MEMORY:
                break;
        }

        return outPacket;
    }

    public static OutPacket resultSetStealSkill(boolean set, int impecMemSkilLID, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.RESULT_SET_STEAL_SKILL);

        outPacket.encodeByte(1); //Set Excl Request
        outPacket.encodeByte(set); //bSet
        outPacket.encodeInt(impecMemSkilLID); //impecMemSkilLID
        if (set) {
            outPacket.encodeInt(skillId); //skill Id
        }
        return outPacket;
    }

    public static OutPacket resultStealSkillList(Set<Skill> targetSkillsList, int phantomStealResult, int targetChrId, int targetJobId) {
        OutPacket outPacket = new OutPacket(OutHeader.RESULT_STEAL_SKILL_LIST);
        outPacket.encodeByte(0); //Set Excl Request
        outPacket.encodeInt(targetChrId);
        outPacket.encodeInt(phantomStealResult); //   Gets a check  if == 4,   else:   nPhantomStealWrongResult
        if (phantomStealResult == 4) {
            outPacket.encodeInt(targetJobId);
            outPacket.encodeInt(targetSkillsList.size());

            for (Skill skills : targetSkillsList) {
                // if v9 (index??) > 0
                outPacket.encodeInt(skills.getSkillId());
            }
        }

        return outPacket;
    }

    public static OutPacket setFuncKeyByScript(boolean add, int action, int key) {
        OutPacket outPacket = new OutPacket(OutHeader.FUNCKEY_SET_BY_SCRIPT);
        outPacket.encodeByte(add);
        outPacket.encodeInt(action);
        outPacket.encodeInt(key);

        return outPacket;
    }

    public static OutPacket damageSkinSaveResult(DamageSkinType req, DamageSkinType res, Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.DAMAGE_SKIN_SAVE_RESULT);

        outPacket.encodeByte(req.getVal());
        if (req.getVal() <= 2) {
            outPacket.encodeByte(res.getVal());
            if (res == DamageSkinType.Res_Success) {
                chr.encodeDamageSkins(outPacket);
            }
        } else if (req == DamageSkinType.Req_SendInfo) {
            chr.encodeDamageSkins(outPacket);
        }
        return outPacket;
    }

    public static OutPacket explosionAttack(int skillID, Position position, int mobID, int count) {
        OutPacket outPacket = new OutPacket(OutHeader.EXPLOSION_ATTACK);

        outPacket.encodeInt(skillID);
        outPacket.encodePositionInt(position);
        outPacket.encodeInt(mobID);
        outPacket.encodeInt(count);

        return outPacket;
    }

    public static OutPacket setNextShootExJablin() {
        OutPacket outPacket = new OutPacket(OutHeader.SET_NEXT_SHOOT_EX_JABLIN);

        return outPacket;
    }

    public static OutPacket cadenaVoidStrikeRequest(Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.CADENA_VOID_STRIKE_REQUEST);

        outPacket.encodePositionInt(position);

        return outPacket;
    }

    public static OutPacket userRandAreaAttackRequest(Mob mob, int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_RAND_AREA_ATTACK_REQUEST);

        outPacket.encodeInt(skillID);
        outPacket.encodeInt(1); //# of mobs to attack

        outPacket.encodePositionInt(mob.getPosition());
        outPacket.encodeInt(mob.getObjectId());

        return outPacket;
    }

    public static OutPacket userBonusAttackRequest(int skillId) {
        return userBonusAttackRequest(skillId, new ArrayList<>());
    }

    public static OutPacket userBonusAttackRequest(int skillId, List<Integer> mobObjIdList) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_BONUS_ATTACK_REQUEST);

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(mobObjIdList.size()); // mobs hit
        outPacket.encodeByte(mobObjIdList.size() <= 0); // true ? placeOnChrPosition : placeOnMobPosition
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        for (int mobObjId : mobObjIdList) {
            outPacket.encodeInt(mobObjId);
            outPacket.encodeInt(0);
            if (skillId == 400041030) { // Thief V - Venom Explosion
                outPacket.encodeInt(2);
            }
        }

        return outPacket;
    }

    public static OutPacket petActivateChange(Pet pet, boolean active, byte removedReason) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_ACTIVATED);

        outPacket.encodeInt(pet.getOwnerID());
        outPacket.encodeInt(pet.getIdx());
        outPacket.encodeByte(active);
        if (active) {
            outPacket.encodeByte(true); // init
            pet.encode(outPacket);
        } else {
            outPacket.encodeByte(removedReason);
        }

        return outPacket;
    }

    public static OutPacket petNameChange(Char chr, int idx, String name) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_NAME_CHANGED);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(idx);
        outPacket.encodeString(name);

        return outPacket;
    }

    public static OutPacket petActionSpeak(Char chr, int idk, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_ACTION);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(idk);

        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeString(msg);

        return outPacket;
    }

    public static OutPacket petColorChange(Char chr, int color) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_HUE_CHANGED);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(0); // pet equipped number
        outPacket.encodeInt(color); // color

        return outPacket;
    }

    public static OutPacket consumeItemCooltime() {
        return new OutPacket(OutHeader.CONSUME_ITEM_COOLTIME);
    }

    public static OutPacket petModified() {
        OutPacket outPacket = new OutPacket(OutHeader.PET_MODIFIED);

        outPacket.encodeShort(0);
        outPacket.encodeByte(1);
        outPacket.encodeByte(0);

        return outPacket;
    }


    public static OutPacket comboCounter(byte type, int comboCount, int mobID, int skinType) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);
        outPacket.encodeByte(MessageType.STYLISH_KILL_MESSAGE.getVal());
        StylishKillType smType = StylishKillType.getByVal(type);

        outPacket.encodeByte(type); //1 for Combo   |  0 for MultiKill

        switch (smType) {
            case COMBO: //Combo Kill Message
                outPacket.encodeInt(comboCount); // nCount
                outPacket.encodeInt(mobID); // nMobID
                outPacket.encodeInt(skinType); // nType (0~4)
                outPacket.encodeInt(0); // some arg for a response packet
                break;

            case MULTI_KILL: //MultiKill Pop-up
                outPacket.encodeLong(comboCount); // nBonus
                outPacket.encodeInt(0); // some arg for a response packet
                outPacket.encodeInt(mobID); // mCount
                outPacket.encodeInt(skinType); // nType (0~4)
                break;
        }

        return outPacket;
    }

    public static OutPacket collectionRecordMessage(int collectionIndex, String value) {
        OutPacket outPacket = new OutPacket(OutHeader.MESSAGE);

        outPacket.encodeByte(MessageType.COLLECTION_RECORD_MESSAGE.getVal());
        outPacket.encodeInt(collectionIndex);
        outPacket.encodeString(value);

        return outPacket;
    }

    public static OutPacket setDead(boolean tremble) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_DEAD);

        outPacket.encodeByte(tremble);

        return outPacket;
    }

    public static OutPacket reviveOnCurFieldAtPoint(int x, int y) {
        OutPacket outPacket = new OutPacket(OutHeader.REVIVE_CUR_FIELD_TRANSFER_POINT);
        outPacket.encodeInt(x);
        outPacket.encodeInt(y);
        return outPacket;
    }

    public static OutPacket openUIOnDead(boolean onDeadRevive, boolean onDeadProtectForBuff, boolean onDeadProtectExpMaplePoint,
                                         boolean onDeadProtectBuffMaplePoint, boolean anniversary, ReviveType reviveType, int protectType) {
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_UI_DEAD);

        int reviveMask = 0;
        if (onDeadRevive) {
            reviveMask |= 0x1;
        }
        if (onDeadProtectForBuff) {
            reviveMask |= 0x2;
        }
        if (onDeadProtectBuffMaplePoint) {
            reviveMask |= 0x4;
        }
        if (onDeadProtectExpMaplePoint) {
            reviveMask |= 0x8;
        }
        outPacket.encodeInt(reviveMask);
        outPacket.encodeByte(anniversary);
        outPacket.encodeInt(reviveType.getVal());
        if (onDeadProtectForBuff || onDeadProtectExpMaplePoint) {
            outPacket.encodeInt(protectType);
        }

        return outPacket;
    }

    public static OutPacket skillCooltimeSetM(int skillID, int cdMS) {
        Map<Integer, Integer> cds = new HashMap<>();
        cds.put(skillID, cdMS);
        return skillCooltimeSetM(cds);
    }

    public static OutPacket skillCooltimeSetM(Map<Integer, Integer> cooldowns) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_COOLTIME_SET_M);

        outPacket.encodeInt(cooldowns.size());
        cooldowns.forEach((skillID, cooldown) -> {
            outPacket.encodeInt(skillID);
            outPacket.encodeInt(cooldown);
        });

        return outPacket;
    }

    public static OutPacket skillCooltimeSet(Map<Integer, Integer> cooldowns) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_COOLTIME_SET_M);

        outPacket.encodeInt(cooldowns.size());
        cooldowns.forEach((skillID, cooldown) -> {
            outPacket.encodeInt(skillID);
            outPacket.encodeInt(cooldown);
        });

        return outPacket;
    }

    public static OutPacket setBuffProtector(int itemID, boolean active) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_BUFF_PROTECTOR);

        outPacket.encodeInt(itemID);
        outPacket.encodeByte(active);

        return outPacket;
    }

    public static OutPacket renameResult(int result, int cost) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_RENAME_RESULT);
        outPacket.encodeByte(result);
        if (result == 9) {
            outPacket.encodeInt(cost);
        }
        return outPacket;
    }

    public static OutPacket deathCountInfo(int deathCount) {
        OutPacket outPacket = new OutPacket(OutHeader.DEATH_COUNT_INFO);

        outPacket.encodeInt(deathCount);

        return outPacket;
    }

    public static OutPacket familiarAddResult(Familiar familiar, boolean showInfoChanged, boolean adminMob) {
        OutPacket outPacket = new OutPacket(OutHeader.FAMILIAR_ADD_RESULT);

        outPacket.encodeLong(familiar.getId());
        //    familiar.encode(outPacket);
        outPacket.encodeByte(showInfoChanged);
        outPacket.encodeByte(adminMob);

        return outPacket;
    }

    public static OutPacket petMove(int id, int petID, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.PET_MOVE);

        outPacket.encodeInt(id); // outside


        outPacket.encodeInt(petID);
        outPacket.encode(movementInfo);

        return outPacket;
    }


    public static OutPacket hakuAction(Char chr, int action, int randomKey) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_PET_ACTION);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(action);
        outPacket.encodeByte(randomKey);

        outPacket.encodeString("");

        return outPacket;
    }


    public static OutPacket setDressChanged(boolean on, boolean dressInfinity) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_DRESS_CHANGED);

        outPacket.encodeByte(1);
        outPacket.encodeByte(1);
        outPacket.encodeByte(1); // unknown a boolean

        return outPacket;
    }

    public static OutPacket setDirectionMode(boolean show, int unk) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_DIRECTION_MODE);
        outPacket.encodeByte(show);
        outPacket.encodeInt(unk);
        return outPacket;
    }

    public static OutPacket setInGameDirectionMode(boolean lockUI, boolean blackFrame, boolean forceMouseOver) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_IN_GAME_DIRECTION_MODE);

        outPacket.encodeByte(lockUI); // Locks User's UI        - Is 'showUI' in IDA
        outPacket.encodeByte(blackFrame); // Usually 1 in gms? (@aviv)
        if (lockUI) {
            outPacket.encodeByte(forceMouseOver);
            outPacket.encodeByte(!lockUI); // showUI
        }

        return outPacket;
    }

    public static OutPacket inGameDirectionEvent(InGameDirectionEvent igdr) {
        OutPacket outPacket = new OutPacket(OutHeader.IN_GAME_DIRECTION_EVENT);

        outPacket.encode(igdr);

        return outPacket;
    }

    public static OutPacket hireTutor(boolean set) {
        OutPacket outPacket = new OutPacket(OutHeader.HIRE_TUTOR);

        outPacket.encodeByte(set);

        return outPacket;
    }

    public static OutPacket tutorMsg(int id, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.TUTOR_MSG);

        boolean automated = true;
        outPacket.encodeByte(automated);
        outPacket.encodeInt(id);
        outPacket.encodeInt(duration);

        return outPacket;
    }

    public static OutPacket tutorMsg(String message, int width, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.TUTOR_MSG);

        boolean automated = false;
        outPacket.encodeByte(automated);
        outPacket.encodeString(message);
        outPacket.encodeInt(width);
        outPacket.encodeInt(duration);

        return outPacket;
    }

    public static OutPacket emotion(int emotion, int duration, boolean byItemOption) {
        OutPacket outPacket = new OutPacket(OutHeader.EMOTION);

        outPacket.encodeInt(emotion);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(byItemOption);

        return outPacket;
    }

    public static OutPacket rotateCamera(int degree, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.CAMERA_ROTATION);

        outPacket.encodeInt(degree);
        outPacket.encodeInt(duration);

        return outPacket;
    }

    public static OutPacket questResult(QuestType type, int questID, int npcTemplateID, int secondQuestID, boolean startNavigation) {
        OutPacket outPacket = new OutPacket(OutHeader.QUEST_RESULT);

        outPacket.encodeByte(type.getVal());
        outPacket.encodeInt(questID);
        outPacket.encodeInt(npcTemplateID);

        outPacket.encodeInt(secondQuestID); // starts a second quest
        outPacket.encodeByte(startNavigation);

        return outPacket;
    }

    public static OutPacket medalReissueResult(MedalReissueResultType medalReissueResultType, int itemId) {
        OutPacket outPacket = new OutPacket(OutHeader.MEDAL_REISSUE_RESULT);

        outPacket.encodeByte(medalReissueResultType.getVal());
        outPacket.encodeInt(itemId);

        return outPacket;
    }

    public static OutPacket stopNoMovementKeyDownSkillRequest(int skillID) {
        OutPacket outPacket = new OutPacket(OutHeader.STOP_NO_MOVEMENT_KEY_DOWN_SKILL_REQUEST);

        outPacket.encodeInt(skillID);

        return outPacket;
    }

    public static OutPacket moveParticleEff(String type, Position startPoint, Position endPoint, int moveTime, int totalCount, int oneSprayMin, int oneSprayMax) {
        OutPacket outPacket = new OutPacket(OutHeader.MOVE_PARTICLE_EFF);

        outPacket.encodeString(type);
        outPacket.encodePosition(startPoint);
        outPacket.encodePosition(endPoint);
        outPacket.encodeShort(moveTime);
        outPacket.encodeShort(totalCount);
        outPacket.encodeShort(oneSprayMin);
        outPacket.encodeShort(oneSprayMax);

        return outPacket;
    }

    public static OutPacket balloonMsg(String message, int width, int timeOut, Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.BALLOON_MSG);

        outPacket.encodeString(message);
        outPacket.encodeShort(width);// 100
        outPacket.encodeShort(timeOut);// 3
        outPacket.encodeByte(position == null);
        if (position != null) {
            outPacket.encodePosition(position);
        }
        return outPacket;
    }

    public static OutPacket lightningUnionSubAttack(int skillId, int slv) {
        OutPacket outPacket = new OutPacket(OutHeader.LIGHTNING_UNION_SUB_ATTACK);

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(ThunderBreaker.LIGHTNING_CASCADE);
        outPacket.encodeInt(slv);

        return outPacket;
    }

    public static OutPacket giantShadowSpearAttack(Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.GIANT_SHADOW_SPEAR_ATTACK);

        outPacket.encodePositionInt(position);

        return outPacket;
    }

    public static OutPacket shootObjectCreated(int skillId, int slv, List<ShootObject> shootObjList) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOOT_OBJECT_CREATED);

        outPacket.encodeByte(true);
        outPacket.encodeInt(skillId);
        outPacket.encodeInt(slv);
        outPacket.encodeInt(shootObjList.size());
        for(ShootObject shootObject : shootObjList) {
            outPacket.encodeInt(shootObject.getId());
        }

        return outPacket;
    }

    public static OutPacket doActivePsychicArea(PsychicArea pa) {
        OutPacket outPacket = new OutPacket(OutHeader.DO_ACTIVE_PSYCHIC_AREA);

        outPacket.encodeInt(pa.psychicAreaKey);
        outPacket.encodeInt(pa.localPsychicAreaKey);

        return outPacket;
    }

    public static OutPacket dodgeSkillReady() {
        OutPacket outPacket = new OutPacket(OutHeader.DODGE_SKILL_READY);

        return outPacket;
    }

    public static OutPacket enterFieldPsychicInfo(int userID, PsychicLock pl, List<PsychicArea> psychicAreas) {
        OutPacket outPacket = new OutPacket(OutHeader.ENTER_FIELD_PSYCHIC_INFO);
        outPacket.encodeByte(true);

        outPacket.encodeInt(userID);
        if (pl == null) {
            outPacket.encodeInt(0);
        } else {
            outPacket.encodeInt(pl.psychicLockBalls.size());
            for (PsychicLockBall plb : pl.psychicLockBalls) {
                boolean hasMob = plb.mob != null;
                outPacket.encodeByte(plb.success);
                outPacket.encodeInt(plb.localKey);
                outPacket.encodeInt(plb.psychicLockKey);
                outPacket.encodeInt(pl.skillID);
                outPacket.encodeShort(pl.slv);
                outPacket.encodeInt(hasMob ? plb.mob.getObjectId() : 0);
                outPacket.encodeShort(plb.stuffID);
                outPacket.encodeLong(hasMob ? Util.maxInt(plb.mob.getMaxHp()) : 0);
                outPacket.encodeLong(hasMob ? Util.maxInt(plb.mob.getHp()) : 0);
                outPacket.encodeByte(plb.posRelID);
                outPacket.encodePositionInt(plb.start);
                outPacket.encodePositionInt(plb.rel);
            }
        }
        if (psychicAreas == null) {
            outPacket.encodeInt(0);
        } else {
            outPacket.encodeInt(psychicAreas.size());
            for (PsychicArea pa : psychicAreas) {
                pa.encode(outPacket);
            }
        }
        // indicate end
        outPacket.encodeByte(false);

        return outPacket;
    }

    public static OutPacket b2BodyResult(short requestType, B2Body b2Body) {
        OutPacket outPacket = new OutPacket(OutHeader.B2_BODY_RESULT);
        Char chr = b2Body.getChr();
        outPacket.encodeShort(requestType);
        outPacket.encodeInt(chr.getId()); // owner Id
        outPacket.encodeInt(chr.getFieldID());
        short loopsize = 1;

        switch (requestType) {
            case 0:
                outPacket.encodeShort(loopsize); // loop size
                for (int i = 0; i < loopsize; i++) {
                    outPacket.encodeInt(b2Body.getBodyId());
                    outPacket.encodeByte(b2Body.getType());
                    outPacket.encodeByte(true); // redraw
                    outPacket.encodePosition(b2Body.getPosition());
                    if (b2Body.getType() == 5) {
                        outPacket.encodeShort(b2Body.getnRadius());
                        outPacket.encodeShort(b2Body.getfRadius());
                    } else if (b2Body.getType() == 6) {
                        outPacket.encodeInt(0); // unk
                    }
                    outPacket.encodeShort(b2Body.getDuration());
                    outPacket.encodeShort(b2Body.getScale());
                    outPacket.encodeShort(0); // sniffed from GMS
                    outPacket.encodeShort(10); // sniffed from GMS
                    outPacket.encodeInt(b2Body.getSkillId());
                    outPacket.encodeShort(b2Body.getSlv());
                    outPacket.encodeByte(chr.isLeft());
                }
                break;
            case 3:
                outPacket.encodeInt(chr.getId());
                outPacket.encodeInt(b2Body.getSkillId());
                outPacket.encodeInt(b2Body.getMaxSpeedX());
                outPacket.encodeInt(b2Body.getMaxSpeedY());
                break;
            case 4:
                outPacket.encodeShort(loopsize);
                for (int i = 0; i < loopsize; i++) {
                    outPacket.encodeByte(true); // redraw
                    outPacket.encodePosition(b2Body.getPosition());
                    outPacket.encodeInt(b2Body.getDuration());
                    outPacket.encodeShort(0); // unk
                    outPacket.encodeShort(0); // unk
                    outPacket.encodeShort(0); // unk
                    outPacket.encodeInt(b2Body.getSkillId());
                    outPacket.encodeByte(chr.isLeft());
                    outPacket.encodeInt(b2Body.getMaxSpeedX());
                    outPacket.encodeInt(b2Body.getMaxSpeedY());
                }
                break;
            case 5:
                outPacket.encodeInt(b2Body.getSkillId()); // mob Skill Id
                outPacket.encodeInt(b2Body.getSlv()); // mob Skill Lv
                break;
        }

        return outPacket;
    }

    public static OutPacket b2BodyResultNew(short requestType, B2Body b2Body) {
        OutPacket outPacket = new OutPacket(OutHeader.B2_BODY_RESULT_NEW);

        outPacket.encodeInt(requestType);
        outPacket.encodeInt(b2Body.getBodyId());

        return outPacket;
    }

    public static OutPacket registerFadeInOutAnimation(int beginFadeTime, int shadeDuration, int endFadeTime, int shadeIntensity) {
        OutPacket outPacket = new OutPacket(OutHeader.REGISTER_FADE_IN_OUT_ANIMATION);

        outPacket.encodeInt(beginFadeTime);
        outPacket.encodeInt(shadeDuration);
        outPacket.encodeInt(endFadeTime);
        outPacket.encodeInt(shadeIntensity);

        return outPacket;
    }

    public static OutPacket greaterDarkServantSwapResult(Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.GREATER_DARK_SERVANT_SWAP_RESULT);

        outPacket.encodePositionInt(position);

        return outPacket;
    }

    public static OutPacket skillRequestRequest(int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_REQUEST_REQUEST);

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(0);

        return outPacket;
    }

    public static OutPacket aceInTheHoleFinisher(int skillId, int slv, Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.ACE_IN_THE_HOLE_FINISHER);

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(slv);
        outPacket.encodeInt(1);
        outPacket.encodePositionInt(position);

        return outPacket;
    }

    public static OutPacket getPhotoResult(Client c, byte[] farmImg) {
        OutPacket outPacket = new OutPacket(OutHeader.PHOTO_GET_RESULT);

        outPacket.encodeInt(c.getUser().getId());
        outPacket.encodeInt(farmImg.length);
        if (farmImg.length > 0) {
            outPacket.encodeArr(farmImg);
        }

        return outPacket;
    }

    public static OutPacket bonusAttackDelayRequest(Map<Integer, Integer> bonusAttackDelayMap) {
        OutPacket outPacket = new OutPacket(OutHeader.BONUS_ATTACK_DELAY_REQUEST);

        outPacket.encodeInt(bonusAttackDelayMap.size());
        for (Map.Entry<Integer, Integer> entry : bonusAttackDelayMap.entrySet()) {
            outPacket.encodeInt(entry.getKey());    // skill Id
            outPacket.encodeInt(entry.getValue());  // delay
        }

        return outPacket;
    }

    public static OutPacket adeleShardBreakerResult(int skillId, List<Rect> shardRects) {
        OutPacket outPacket = new OutPacket(OutHeader.AREA_EXPLOSION_REQUEST);

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(0); // ?
        outPacket.encodeInt(shardRects.size());
        int itr = 1;
        for (Rect rect : shardRects) {
            outPacket.encodeInt(itr); // iterator (might need to sniff again, might be a new key/id everytime)
            outPacket.encodeRectInt(rect);
            itr++;
        }

        return outPacket;
    }
}
