package net.swordie.ms.enums;

/**
 * Created on 5/17/2018.
 */
public enum ReviveType {
    NORMAL(0),
    PREMIUM(1),
    MAPLEPOINT(2),
    QUESTPOINT(3),
    UPGRADETOMB(4),
    SOULSTONE(5),
    EVENT(6),
    WHEEL_OF_DESTINY(7), // wheel of destiny?
    TOWN(8), // safe nearby town
    OTHER_WORLDLY_LUCK(9),
    MAPLEPOINT2(10),
    CUR_FIELD(11),
    ;

    private byte val;

    ReviveType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
