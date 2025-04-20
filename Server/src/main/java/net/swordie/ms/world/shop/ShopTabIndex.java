package net.swordie.ms.world.shop;

/**
 * Created on 3/28/2018.
 */
public enum ShopTabIndex {
    NONE(-1),
    EMPTY(0),
    EQUIP(1),
    USE(2),
    SETUP(3),
    ETC(4),
    RECIPE(5),
    SCROLL(6),
    SPECIAL(7),
    EIGHTH_YEAR(8),
    BUTTON(9),
    TICKET(10),
    MATERIAL(11),
    MAPLE(12),
    HOMECOMING(13),
    CORE(14),
    CORE_2(15),
    ;

    private int val;

    ShopTabIndex(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
