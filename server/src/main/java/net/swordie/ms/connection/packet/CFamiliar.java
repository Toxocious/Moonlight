package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Familiar;
import net.swordie.ms.life.movement.Movement;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;

import java.util.List;

/**
 * @author Sjonnie
 * Created on 6/9/2018.
 */
public class CFamiliar {
    // Thanks to nox, my ida wasn't able to give me the correct opcodes :(

    public static OutPacket familiarEnterField(int charID, boolean transfer, Familiar familiar, boolean on, boolean animation) {
        OutPacket outPacket = new OutPacket(transfer ? OutHeader.FAMILIAR_TRANSFER_FIELD : OutHeader.FAMILIAR_ENTER_FIELD);
        outPacket.encodeInt(charID);
        outPacket.encodeByte(on); // on/off
        outPacket.encodeByte(!animation); // animation
        outPacket.encodeByte(0); // idk
        if (on) {
            outPacket.encodeInt(familiar.getFamiliarID());
            outPacket.encodeInt(familiar.getFatigue()); // fatigue
            outPacket.encodeInt(familiar.getVitality() * GameConstants.FAMILIAR_ORB_VITALITY); // total vitality
            outPacket.encodeString(familiar.getName());
            outPacket.encodePosition(familiar.getPosition());
            outPacket.encodeByte(familiar.getMoveAction());
            outPacket.encodeShort(familiar.getFh());
        }
        return outPacket;
    }

    public static OutPacket familiarMove(int charID, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.FAMILIAR_MOVE);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(0);
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket familiarAttack(int charID, AttackInfo attackInfo){
        OutPacket outPacket = new OutPacket(OutHeader.FAMILIAR_ATTACK);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(0); // ?
        outPacket.encodeByte(attackInfo.idk);
        outPacket.encodeByte(attackInfo.mobCount);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            outPacket.encodeInt(mai.mobId);
            outPacket.encodeByte(mai.byteIdk1);
            outPacket.encodeByte(mai.damages.length);
            for (long dmg : mai.damages) {
                outPacket.encodeInt(Util.maxInt(dmg));
            }
        }

        return outPacket;
    }

    public static OutPacket familiarUpdateInfo(int charID, Familiar familiar) {
        OutPacket outPacket = new OutPacket(OutHeader.FAMILIAR_UPDATE_INFO);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(familiar.getFamiliarID());
        outPacket.encodeInt(familiar.getFatigue());
        outPacket.encodeFT(familiar.getExpiration());

        return outPacket;
    }
}
