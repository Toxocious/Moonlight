package net.swordie.ms.enums;

/**
 * Created on 11/17/2017.
 */
public enum PicStatus {
    CREATE_PIC(0),
    ENTER_PIC(1),
    IGNORE(2),
    OUTDATED(4),
    ;

    private byte val;

    PicStatus(byte val) {
        this.val = val;
    }

    PicStatus(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
