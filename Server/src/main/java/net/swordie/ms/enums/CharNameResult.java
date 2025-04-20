package net.swordie.ms.enums;

/**
 * Created on 11/17/2017.
 */
public enum CharNameResult {
    Available(0),
    Unavailable_InUse(1),
    Unavailable_Invalid(2),
    Unavailable_CashItem(3);

    private byte val;

    CharNameResult(byte val) {
        this.val = val;
    }

    CharNameResult(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
