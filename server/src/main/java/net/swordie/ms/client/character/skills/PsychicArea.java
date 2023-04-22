package net.swordie.ms.client.character.skills;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/11/2018.
 */
public class PsychicArea {

    public int action;
    public int actionSpeed;
    public int localPsychicAreaKey;
    public int skillID;
    public short slv;
    public int duration;
    public boolean isLeft;
    public short skeletonFilePathIdx;
    public short skeletonAniIdx;
    public short skeletonLoop;
    public Position start;
    public int psychicAreaKey;
    public int time;
    public boolean success = true;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(success);
        outPacket.encodeInt(localPsychicAreaKey);
        outPacket.encodeInt(time);
        outPacket.encodeInt(skillID);
        outPacket.encodeShort(slv);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(isLeft);
        outPacket.encodeShort(skeletonFilePathIdx);
        outPacket.encodeShort(skeletonAniIdx);
        outPacket.encodeShort(skeletonLoop);
        outPacket.encodePositionInt(start);
    }
}
