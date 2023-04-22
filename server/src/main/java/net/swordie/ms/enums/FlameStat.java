package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created on 28/8/2018.
 */
public enum FlameStat {
    STR(0, 0),
    DEX(1, 1),
    INT(2, 2),
    LUK(3, 3),
    STRDEX(4, 4),
    STRINT(5, 5),
    STRLUK(6, 6),
    DEXINT(7, 7),
    DEXLUK(8, 8),
    INTLUK(9, 9),
    Attack(10, 17),
    MagicAttack(11, 18),
    Defense(12, 13),
    MaxHP(13, 10),
    MaxMP(14, 11),
    Speed(15, 19),
    Jump(16, 20),
    AllStats(17, 24),
    BossDamage(18, 21),
    Damage(19, 23),
    LevelReduction(20, 12); // doesn't actually show up in-game as green

    private int val;
    private int exGrade;

    FlameStat(int val, int exGrade) {
        this.val = val;
        this.exGrade = exGrade;
    }

    public int getVal() {
        return val;
    }

    public static FlameStat getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), stat -> stat.getVal() == val);
    }

    public int getExGrade() {
        return exGrade;
    }
}
