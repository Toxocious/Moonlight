package net.swordie.ms.handlers.life;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.DragonPool;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.Dragon;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

public class SummonedHandler {

    private static final Logger log = Logger.getLogger(SummonedHandler.class);

    @Handler(op = InHeader.SUMMONED_MOVE)
    public static void handleSummonedMove(Char chr, InPacket inPacket) {
        // CVecCtrlSummoned::EndUpdateActive
        int summonID = inPacket.decodeInt();
        Life life = chr.getField().getLifeByObjectID(summonID);
        if (life instanceof Summon) {
            Summon summon = (Summon) life;
            MovementInfo movementInfo = new MovementInfo(inPacket);
            movementInfo.applyTo(summon);
            chr.getField().broadcastPacket(Summoned.summonedMove(chr.getId(), summonID, movementInfo), chr);
        }
    }





    @Handler(op = InHeader.SUMMONED_REMOVE)
    public static void handleSummonedRemove(Client c, InPacket inPacket) {
        int id = inPacket.decodeInt();

        Char chr = c.getChr();
        Summon summon = (Summon) chr.getField().getLifeByObjectID(id);
        if (summon == null || summon.getChr() != c.getChr()) {
            return;
        }
        int skillId = summon.getSkillID();
        if (skillId == BattleMage.CONDEMNATION
                || skillId == BattleMage.CONDEMNATION_I
                || skillId == BattleMage.CONDEMNATION_II
                || skillId == BattleMage.CONDEMNATION_III) {

            ((BattleMage) chr.getJobHandler()).removeCondemnationBuff(summon);
        } else if (summon.getAssistType() == AssistType.CreateShootObj) {
            return;
        }

        chr.getField().removeLife(id, false);
    }

    @Handler(op = InHeader.SUMMONED_HIT)
    public static void handleSummonedHit(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();

        int summonObjId = inPacket.decodeInt();
        byte attackId = inPacket.decodeByte();
        int damage = inPacket.decodeInt();
        int mobTemplateId = inPacket.decodeInt();
        boolean isLeft = inPacket.decodeByte() != 0;
     //   int mobObjId = inPacket.decodeInt(); // removed from v207 -> 206

        Life life = field.getLifeByObjectID(summonObjId);
        if (!(life instanceof Summon)) {
            return;
        }

        ((Summon) life).onHit(damage, mobTemplateId);
    }

    @Handler(op = InHeader.SUMMONED_SKILL)
    public static void handleSummonedSkill(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();

        int objectID = inPacket.decodeInt();
        inPacket.decodeInt(); // 0
        int skillId = inPacket.decodeInt();
        inPacket.decodeByte();
        inPacket.decodeInt(); // current Time

        if (field.getLifeByObjectID(objectID) != null && field.getLifeByObjectID(objectID) instanceof Summon) {
            Summon summon = (Summon) field.getLifeByObjectID(objectID);
            summon.onSkillUse(skillId, inPacket);
        }
    }

    @Handler(op = InHeader.SUMMONED_SKILL_ATTACK)
    public static void handleSummonedSkillAttack(Char chr, InPacket inPacket) {
        int summonObjId = inPacket.decodeInt();
        int unk1 = inPacket.decodeInt();
        int summonSkillId = inPacket.decodeInt(); // Skill ID the Summon originates from
        int summonAttackId = inPacket.decodeInt(); // Attack Skill Id
        if (summonSkillId == Illium.CRYSTALLINE_SPIRIT) {
            if (summonAttackId == Illium.REACTION_DESTRUCTION_II) {
                ((Illium) chr.getJobHandler()).doCrystallineDestruction(summonObjId, summonAttackId);
            }
        }
        //int attackTime = inPacket.decodeInt(); // attack time ms removed in v213
        Summon summon = (Summon) chr.getField().getLifeByObjectID(summonObjId);
        if (summon != null && summon.getChr() == chr && summon.getSkillID() == summonSkillId) {
            summon.onAttack(summonAttackId);
        }

    }

    @Handler(op = InHeader.RECALL_SUMMON)
    public static void handleRecallSummon(Char chr, InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        int slv = inPacket.decodeInt();
        byte isLeft = inPacket.decodeByte();
        short unk = inPacket.decodeShort();

        if (!chr.hasSkill(skillId)) {
            return;
        }

        switch (skillId) {
            case Kaiser.NOVA_GUARDIANS:
                ((Kaiser) chr.getJobHandler()).recallNovaGuardians();
                break;
        }
    }

    @Handler(op = InHeader.SUMMONED_ASSIST_ATTACK_DONE)
    public static void handleAssistAttackDone(Char chr, InPacket inPacket) {
        Field field = chr.getField();

        int objId = inPacket.decodeInt();
        byte unk = inPacket.decodeByte();

        Life life = field.getLifeByObjectID(objId);
        if (life instanceof Summon) {
            // Handle
        }
    }
}
