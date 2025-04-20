package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1/26/2018.
 */
public enum ItemGrade {
    LegendaryBonusHidden(-12),
    UniqueBonusHidden(-13),
    EpicBonusHidden(-14),
    RareBonusHidden(-15),

    HiddenLegendary(4),
    HiddenUnique(3),
    HiddenEpic(2),
    HiddenRare(1),
    Hidden(-1), // buggy

    None(0),

    Rare(17),
    Epic(18),
    Unique(19),
    Legendary(20),
    ;

    private int val;

    ItemGrade(int val) {
        this.val = val;
    }

    public static ItemGrade getHiddenBonusGradeByBaseGrade(ItemGrade gradeByVal) {
        switch (gradeByVal) {
            case HiddenRare:
            case Rare:
                return RareBonusHidden;
            case HiddenEpic:
            case Epic:
                return EpicBonusHidden;
            case HiddenUnique:
            case Unique:
                return UniqueBonusHidden;
            case HiddenLegendary:
            case Legendary:
                return LegendaryBonusHidden;
            default:
                return None;
        }
    }

    public short getVal() {
        return (short) val;
    }

    public static ItemGrade getGradeByVal(int grade) {
        return Arrays.stream(values()).filter(ig -> ig.getVal() == grade).findFirst().orElse(null);
    }

    public static ItemGrade getGradeByOption(int option) {
        ItemGrade itemGrade = None;
        if(option < 0) {
            itemGrade =  Arrays.stream(values()).filter(is -> is.getVal() == Math.abs(option)).findFirst().orElse(None);
        }
        if(option > 0 && option < 20000) {
            itemGrade = Rare;
        }
        if(option > 20000 && option < 30000) {
            itemGrade = Epic;
        }
        if(option > 30000 && option < 40000) {
            itemGrade = Unique;
        }
        if(option > 40000 && option < 60000) {
            itemGrade = Legendary;
        }
        return itemGrade;
    }

    public static boolean isMatching(short first, short second) {
        ItemGrade firstGrade = getGradeByVal(first);
        ItemGrade other = getGradeByVal(second);
        switch(firstGrade) {
            case None:
                return other == None;
            case HiddenRare:
            case Rare:
                return other == HiddenRare || other == Rare;
            case HiddenEpic:
            case Epic:
                return other == HiddenEpic || other == Epic;
            case HiddenUnique:
            case Unique:
                return other == HiddenUnique || other == Unique;
            case HiddenLegendary:
            case Legendary:
                return other == HiddenLegendary || other == Legendary;
            default:
                return false;
        }
    }

    public static ItemGrade getHiddenGradeByVal(short val) {
        ItemGrade ig = None;
        ItemGrade arg = getGradeByVal(val);
        switch(arg) {
            case Rare:
            case HiddenRare:
                ig = HiddenRare;
                break;
            case Epic:
            case HiddenEpic:
                ig = HiddenEpic;
                break;
            case Unique:
            case HiddenUnique:
                ig = HiddenUnique;
                break;
            case Legendary:
            case HiddenLegendary:
                ig = HiddenLegendary;
                break;
        }
        return ig;
    }

    public static ItemGrade getOneTierLower(short val) {
        ItemGrade ig = None;
        ItemGrade arg = getGradeByVal(val);
        switch(arg) {
            case Rare:
            case Epic:
                ig = Rare;
                break;
            case HiddenRare:
            case HiddenEpic:
                ig = HiddenRare;
                break;
            case Unique:
                ig = Epic;
                break;
            case HiddenUnique:
                ig = HiddenEpic;
                break;
            case Legendary:
                ig = Unique;
                break;
            case HiddenLegendary:
                ig = HiddenUnique;
                break;
        }
        return ig;
    }

    public boolean isHidden() {
        switch (this) {
            case Hidden:
            case HiddenRare:
            case HiddenEpic:
            case HiddenUnique:
            case HiddenLegendary:
                return true;
        }
        return false;
    }
}
