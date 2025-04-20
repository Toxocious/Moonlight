package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by Asura on 10-5-2018.
 */
public enum StylishKillType {
    MULTI_KILL(0),
    COMBO(1),
    ;

    private byte val;

    StylishKillType(int val) { this.val = (byte) val;}

    public byte getVal() { return val;}

    public static StylishKillType getByVal(byte val) {
        return Arrays.stream(values()).filter(skt -> skt.getVal() == val).findAny().orElse(null);
    }
}
