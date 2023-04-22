package net.swordie.ms.enums;

/**
 * Created on 9-5-2019.
 *
 */
public enum AttackIndex {
    MobPhysical(0),
    MobMagic(-1),
    Counter(-2),
    Obstacle(-3),
    Stat(-4),
    ObstacleAtom(-5),
    Dead(-6),
    FieldSkill(-7),
    MobSkill(-8),
    PartsSystem(-9),
    FieldEtc(-10),
    ;
    int val;

    AttackIndex(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
