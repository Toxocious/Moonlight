package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.jobs.adventurer.pirate.Jett;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.util.Position;

import java.util.Map;

/**
 * Created on 5/21/2018.
 */
public class Summoned {

    public static OutPacket summonedAssistAttackRequest(Summon summon, int summonSkillIdentifier) {
        OutPacket outpacket = new OutPacket(OutHeader.SUMMONED_ASSIST_ATTACK_REQUEST);

        outpacket.encodeInt(summon.getChr().getId());
        outpacket.encodeInt(summon.getObjectId());
        outpacket.encodeInt(summonSkillIdentifier); // seems to be used to identify which skill to attack with.

        return outpacket;
    }

    public static OutPacket assistSpecialAttackRequest(Summon summon, int summonSkillIdentifier) {
        // something with 400051028~400051032 (Suborbital Strike)
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_ASSIST_SPECIAL_ATTACK_REQUEST);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(summonSkillIdentifier);
        outPacket.encodeByte(true); // isLeft (?)
        outPacket.encodePositionInt(summon.getPosition());

        return outPacket;
    }

    public static OutPacket summonedSummonAttackActive(Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SUMMON_ATTACK_ACTIVE);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(0);

        return outPacket;
    }

    public static OutPacket summonedSkill(Summon summon, byte summonSkillType, int summonSkillID) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SKILL);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(summonSkillType);
        outPacket.encodeInt(summonSkillID);

        return outPacket;
    }

    public static OutPacket summonBeholderRevengeAttack(Summon summon, int mob) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_BEHOLDER_REVENGE_ATTACK);

        outPacket.encodeInt(summon.getChr().getId());//char ID
        outPacket.encodeInt(summon.getObjectId());//summon
        outPacket.encodeInt(mob);//mob

        return outPacket;
    }

    public static OutPacket summonedCreated(int charID, Summon summon) {
        int skillID = summon.getSkillID();
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_CREATED);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(summon.getSkillID());
        outPacket.encodeInt(summon.getCharLevel());
        outPacket.encodeInt(summon.getSlv());
        // CSummoned::Init
        outPacket.encodePosition(summon.getPosition());
        outPacket.encodeByte(summon.getMoveAction());
        outPacket.encodeShort(summon.getCurFoothold());
        outPacket.encodeByte(summon.getMoveAbility().getVal());
        outPacket.encodeByte(summon.getAssistType().getVal());
        outPacket.encodeByte(summon.getEnterType().getVal());
        outPacket.encodeInt(summon.getMobTemplateId()); // Cinder Maelstrom
        outPacket.encodeByte(summon.isFlyMob());
        outPacket.encodeByte(summon.isBeforeFirstAttack());
        outPacket.encodeInt(summon.getTemplateId());
        outPacket.encodeInt(summon.getBulletID());
        AvatarLook al = summon.getAvatarLook();
        outPacket.encodeByte(al != null);
        if(al != null) {
            al.encode(outPacket);
        }
        if (skillID == 35111002) { // Rock 'n Shock (aka Tesla Coil)
            outPacket.encodeByte(summon.getTeslaCoilState());
            for(Position pos : summon.getTeslaCoilPositions()) {
                outPacket.encodePosition(pos);
            }
        }

        if (SkillConstants.isUserCloneSummon(skillID)) {
            outPacket.encodeInt(summon.getActionDelay()); // in ms
            outPacket.encodeInt(summon.getMovementDelay()); // unsure what this exactly is
        }
        if (skillID == Kanna.KISHIN_SHOUKAN) {
            for(Position pos : summon.getKishinPositions()) {
                outPacket.encodePosition(pos);
            }
        }
        if (skillID == Jett.GRAVITY_CRUSH) {
            outPacket.encodeLong(summon.getGravityCrushDmg());
        }
        if (SkillConstants.isSuborbitalStrike(skillID)) {
            outPacket.encodeByte(!summon.isHide()); // show
        }
        outPacket.encodeByte(summon.isJaguarActive());
        outPacket.encodeInt(summon.getSummonTerm());
        outPacket.encodeByte(summon.isAttackActive());
        outPacket.encodeInt(summon.isFlip() ? 0 : 1); // isLeft
        if (SkillConstants.isSummonJaguarSkill(skillID)) {
            outPacket.encodeByte(summon.getState());
            outPacket.encodeInt(summon.getCount());
        }
        // 199
        boolean isStateUsing = SkillConstants.isStateUsingSummon(summon.getSkillID());
        outPacket.encodeByte(isStateUsing);
        if (isStateUsing) {
            outPacket.encodeInt(summon.getCount());
            outPacket.encodeInt(summon.getState());
            outPacket.encodeInt(0); // dog idefk
            outPacket.encodeInt(0); // also new??
            if (JobConstants.isIllium((short) (skillID / 10000))) {
                outPacket.encodeInt(((Illium) summon.getChr().getJobHandler()).crystalSkillMap.size());
                for (Map.Entry<Integer, Boolean> entry : ((Illium) summon.getChr().getJobHandler()).crystalSkillMap.entrySet()) {
                    outPacket.encodeInt(entry.getKey());
                    outPacket.encodeInt(entry.getValue() ? 1 : 0);
                }
            }
        }
        // ~199
        outPacket.encodeInt(summon.getLinkedSummonSkillIds().size());
        for (int linkedSummonSkillId : summon.getLinkedSummonSkillIds()) {
            outPacket.encodeInt(linkedSummonSkillId);
        }

        // new 208 (can't find these in IDA, placement could be wrong!)
        if (summon.getSkillID() == Kanna.TENGU_STRIKE) {
            outPacket.encodeInt(0);
        }
        if (summon.getSkillID() == Kanna.TENGU_STRIKE_SUMMON_L || summon.getSkillID() == Kanna.TENGU_STRIKE_SUMMON_R) {
            outPacket.encodeByte(0);
        }
        outPacket.encodeInt(0);
        if (summon.getSkillID() == Adele.REIGN_OF_DESTRUCTION) {
            outPacket.encodeInt(0);
        }
        return outPacket;
    }

    public static OutPacket summonedRemoved(Summon summon, LeaveType leaveType) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_REMOVED);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeByte(leaveType.getVal());

        return outPacket;
    }

    public static OutPacket summonedAttack(int charID, AttackInfo ai, boolean counter) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_ATTACK);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(ai.summon.getObjectId());

        outPacket.encodeInt(ai.summon.getCharLevel());
        byte left = (byte) (ai.left ? 1 : 0);
        outPacket.encodeByte((left << 7) | ai.attackActionType);
        byte attackCount = (byte) (ai.mobAttackInfo.size() > 0 ? ai.mobAttackInfo.get(0).damages.length : 0);
        outPacket.encodeByte((ai.mobCount << 4) | (attackCount & 0xF));
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            outPacket.encodeInt(mai.mobId);
            outPacket.encodeByte(mai.byteIdk1);
            for (long dmg : mai.damages) {
                outPacket.encodeLong(dmg);
            }
        }
        outPacket.encodeByte(counter); // bCounterAttack
        outPacket.encodeByte(ai.attackActionType == 0);
        outPacket.encodeShort(ai.summon.getX());
        outPacket.encodeShort(ai.summon.getY());
        outPacket.encodeInt(ai.skillId == ai.summon.getSkillID() ? (ai.summonSpecialSkillId == 0 ? ai.skillId : ai.summonSpecialSkillId): ai.skillId); // Attack Skill ID  TODO
        outPacket.encodeByte(false);
        outPacket.encodeShort(0);
        outPacket.encodeShort(0);

        return outPacket;
    }

    public static OutPacket summonedMove(int charID, int summonID, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_MOVE);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(summonID);
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket summonedUpdateHPTag(Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_UPDATE_HP_TAG);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(summon.getHp());

        return outPacket;
    }

    public static OutPacket new980(Summon summon) {
        // something with 400011054 (Radiant Evil, DK skill)
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_UNK1057);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(2);
        outPacket.encodeInt(2);

        return outPacket;
    }

    public static OutPacket stateChanged(Summon summon, int type, Map<Integer, Boolean> crystalSkillModeMap) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_STATE_CHANGED);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(type);
        switch (type) {
            case 1: // Rift of Damnation

                break;

            case 2: // Illium
                outPacket.encodeInt(crystalSkillModeMap.size()); // loop size
                for (Map.Entry<Integer, Boolean> entry : crystalSkillModeMap.entrySet()) {
                    outPacket.encodeInt(entry.getKey());            // Crystal Skill Idx
                    outPacket.encodeInt(entry.getValue() ? 1 : 0);  // boolean  Can use
                }
                break;

            // maybe more cases
        }



        return outPacket;
    }

    public static OutPacket new982(Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_UNK1059);

        outPacket.encodeInt(summon.getChr().getId());
        int size = 1;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(2);
            outPacket.encodeLong(2);
        }

        return outPacket;
    }

    public static OutPacket repositionSummon(Summon summon, int skillId, Position newPosition) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_REPOSITION_SUMMON);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(skillId);
        outPacket.encodeInt(summon.getSlv());
        outPacket.encodePositionInt(newPosition);

        return outPacket;
    }

    public static OutPacket effect(Summon summon, int effectType) {
        // something with 400021054 (Spirit's Domain)
        // also used by Hayato's 3rd V skill summon
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_EFFECT);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(effectType); // seems to show different summon effects
        switch (effectType) {
            case 0:
            case 1:
            case 2:
                // no decodes in IDA
                break;
            case 3:
                outPacket.encodeInt(0); // unk
                break;
            case 4:
                outPacket.encodeInt(0); // unk
                break;
        }

        return outPacket;
    }

    public static OutPacket summonUseSpecifiedSkill(Summon summon, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SUMMON_USE_SPECIFIED_SKILL);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeInt(skillId);

        return outPacket;
    }

    public static OutPacket summonUpgradeStage(Summon summon, int type) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_UPGRADE_STAGE);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());

        outPacket.encodeInt(type); // still unsure about the exact structure.
        switch (type) {
            case 0:
                outPacket.encodeInt(0); // unk
                outPacket.encodeInt(0); // unk
                break;
            case 1:
                // no decodes
                break;
            case 2:
                outPacket.encodeInt(summon.getCount()); // crystal Count
                outPacket.encodeInt(summon.getState()); // crystal State
                break;
            case 3:
                // no decodes
                break;
            case 4:
                outPacket.encodeInt(0); // unk
                outPacket.encodeInt(0); // unk
                break;

        }

        return outPacket;
    }

    public static OutPacket gravityCrushDamage(Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_GRAVITY_CRUSH_DAMAGE);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(1); // Unknown
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeLong(summon.getGravityCrushDmg());

        return outPacket;
    }

    public static OutPacket gravityCrushMaxDamage(Summon summon) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_GRAVITY_CRUSH_MAX_DAMAGE);

        outPacket.encodeInt(summon.getChr().getId());
        outPacket.encodeInt(summon.getObjectId());
        outPacket.encodeLong(summon.getGravityCrushMaxDmg());

        return outPacket;
    }

    public static OutPacket broadcastSpiritDomainState(int broadcastToChr, int ownerChr, int state) {
        OutPacket outPacket = new OutPacket(OutHeader.SUMMONED_SPIRIT_DOMAIN_STATE);

        outPacket.encodeInt(broadcastToChr);
        outPacket.encodeInt(ownerChr);
        outPacket.encodeInt(state);

        return outPacket;
    }
}
