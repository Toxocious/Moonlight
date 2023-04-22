package net.swordie.ms.enums;

/**
 * Created on 12/13/2017.
 */
public enum CharSelectType {
    InAlba(39),
    ClientAlreadyRunning(55),
    HavingTroubleLoggingIn(67);

    private byte val;

    CharSelectType(byte val) {
        this.val = val;
    }

    CharSelectType(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
