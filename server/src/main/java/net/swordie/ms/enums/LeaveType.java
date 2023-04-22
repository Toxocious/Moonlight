package net.swordie.ms.enums;

/**
 * Created on 1/13/2018.
 */
public enum LeaveType {
    NO_ANIMATION(0),
    ANIMATION(1),

    BAT_LEAVE_TYPE(17),
    ;


    private byte val;

    LeaveType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
