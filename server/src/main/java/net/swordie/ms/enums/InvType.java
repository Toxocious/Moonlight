package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 11/23/2017.
 */
public enum InvType {
    EQUIPPED(-1),
    EQUIP(1),
    CONSUME(2),
    ETC(4),
    INSTALL(3),
    CASH(5),
    HAIR(6),
    FACE(7)
    ;

    private byte val;

    InvType(int val) {
        this((byte) val);
    }

    InvType(byte val) {
        this.val = val;
    }

    public byte getVal() {
        return val;
    }

    public static InvType getInvTypeByVal(int val) {
        return Arrays.stream(InvType.values()).filter(t -> t.getVal() == val).findFirst().orElse(null);
    }

    public static InvType getInvTypeByString(String subMap) {
        subMap = subMap.toLowerCase();
        InvType res = null;
        switch(subMap) {
            case "cash":
            case "pet":
                res = CASH;
                break;
            case "consume":
            case "special":
            case "use":
                res = CONSUME;
                break;
            case "etc":
                res = ETC;
                break;
            case "install":
            case "setup":
                res = INSTALL;
                break;
            case "eqp":
            case "equip":
                res = EQUIP;
                break;
            case "hair":
                res = HAIR;
                break;
            case "face":
                res = FACE;
                break;
        }
        return res;
    }

    public boolean isStackable() {
        return this != EQUIP && this != EQUIPPED && this != CASH;
    }
}
