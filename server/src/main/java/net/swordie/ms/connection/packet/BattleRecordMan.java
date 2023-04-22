package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.mob.skill.BurnedInfo;
import net.swordie.ms.util.Util;

/**
 * Created on 2/10/2018.
 */
public class BattleRecordMan {
    public static OutPacket serverOnCalcRequestResult(boolean on) {
        OutPacket outPacket = new OutPacket(OutHeader.SERVER_ON_CALC_REQUEST_RESULT);

        outPacket.encodeByte(true);

        return outPacket;
    }

    public static OutPacket dotDamageInfo(BurnedInfo burnedInfo, int count) {
        OutPacket outPacket = new OutPacket(OutHeader.DOT_DAMAGE_INFO.getValue());

        outPacket.encodeLong(burnedInfo.getDamage());
        outPacket.encodeInt(count);

        outPacket.encodeByte(burnedInfo.getDotTickDamR() > 0);
        if (burnedInfo.getDotTickDamR() > 0) {
            outPacket.encodeInt(burnedInfo.getDotTickDamR());
        }
        outPacket.encodeInt(burnedInfo.getSkillId());

        return outPacket;
    }
}
