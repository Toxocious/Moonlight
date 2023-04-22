package net.swordie.ms.life;

/**
 * Created on 1/2/2018.
 */
public enum DeathType {
    STAY(0),
    ANIMATION_DEATH(1),
    NO_ANIMATION_DEATH(2),
    INSTA_DEATH(3),
    NO_ANIMATION_DEATH_2(4),
    ANIMATION_DEATH_2(5),
    ANIMATION_DEATH_3(6),

    ;
    private byte val;

    DeathType(byte val) {
        this.val = val;
    }

    DeathType(int val) {
        this((byte) val);
    }

    public byte getVal() {
        return val;
    }
}
