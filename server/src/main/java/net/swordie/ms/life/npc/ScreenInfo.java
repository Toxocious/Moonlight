package net.swordie.ms.life.npc;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 2/19/2018.
 */
public class ScreenInfo {
    private byte type;
    private int value;

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getType());
        outPacket.encodeInt(getValue());
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
