package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 3/24/2019.
 */
public enum AchievementType {
    ChangeInsignia(3),
    LoadInsignia(4),
    LoadRank(5),
    LoadAchievements(6),
    Refresh(7),
    ;

    private int val;

    AchievementType(int val) {
        this.val = val;
    }

    public static AchievementType getByVal(int val) {
        return Util.findWithPred(values(), at -> at.getVal() == val);
    }

    public int getVal() {
        return val;
    }

}
