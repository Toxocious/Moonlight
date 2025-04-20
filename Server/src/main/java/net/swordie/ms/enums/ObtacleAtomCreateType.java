package net.swordie.ms.enums;

/**
 * Created on 5/30/2018.
 */
public enum ObtacleAtomCreateType {
    NORMAL(0),
    IN_ROW(1),
    TORNADO(2),
    MOB_SKILL(3),
    RADIAL(4),
    DIAGONAL(5),
    FIXED_POS(6),
    // NO, seems to indicate end
    ;

    private byte val;

    ObtacleAtomCreateType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
