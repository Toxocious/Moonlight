package net.swordie.ms.enums;

/**
 * Created on 3/2/2018.
 */
public enum QuestStatus {
    NotStarted(0),
    Started(1),
    Completed(2);

    private byte val;

    QuestStatus(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
