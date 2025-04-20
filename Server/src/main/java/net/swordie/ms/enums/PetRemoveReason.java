package net.swordie.ms.enums;

/**
 * Created on 4/19/2018.
 */
public enum PetRemoveReason {
    MANUALLY_DISABLED(0),
    LOST_SIGHT(1),
    MAGIC_ENDED(2),
    ;

    private final int val;

    PetRemoveReason(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
