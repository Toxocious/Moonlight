package net.swordie.ms.life.pet;

/**
 * Created on 4/20/2018.
 */
public enum PetSkill {
    ITEM_PICKUP(0x1),
    EXPANDED_AUTO_MOVE(0x2),
    AUTO_MOVE(0x4),
    IGNORE_ITEM(0x8),
    EXPIRED_PICKUP(0x10),
    AUTO_HP(0x20),
    AUTO_MP(0x40),
    AUTO_BUFF(0x80),
    AUTO_FEED(0x100),
    FATTEN_UP(0x200),
    PET_SHOP(0x400),
    RECALL(0x800),
    AUTO_SPEAKING(0x1000),
    AUTO_ALL_CURE(0x2000),

    ;

    private final int val;

    public int getVal() {
        return val;
    }

    PetSkill(int val) {
        this.val = val;
    }
}
