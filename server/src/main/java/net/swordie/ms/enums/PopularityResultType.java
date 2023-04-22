package net.swordie.ms.enums;

/**
 * Created by Asura on 8-9-2018.
 */
public enum PopularityResultType {
    Success(0),
    InvalidCharacterId(1),
    LevelLow(2),
    AlreadyDoneToday(3),
    AlreadyDoneTarget(4),
    Notify(5),
    ;

    private byte val;

    PopularityResultType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
