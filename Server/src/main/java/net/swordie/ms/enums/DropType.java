package net.swordie.ms.enums;

/**
 * Created on 2/21/2018.
 */
public enum DropType {
    Mesos(0),
    Item(1);

    private byte val;

    DropType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
