package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 9/12/2018.
 */
public enum RandomMissionType {
    Res_InventoryFull(13),
    Res_TooManyMesos(15),
    Res_UniqueItem(18),
    Res_TimeLimitPassed_1(20),
    Res_TimeLimitPassed_2(21),

    ;

    private int val;

    RandomMissionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
