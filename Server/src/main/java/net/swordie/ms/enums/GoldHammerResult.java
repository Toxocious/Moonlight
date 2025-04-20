package net.swordie.ms.enums;

/**
 * @author Arnah
 * @since Jul 09, 2019
 */
public enum GoldHammerResult {
    Success(0),
    Fail(1),
    Done(2),
    Error(3);

    private byte val;

    GoldHammerResult(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}