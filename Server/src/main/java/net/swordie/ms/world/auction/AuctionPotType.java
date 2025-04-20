package net.swordie.ms.world.auction;

import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.enums.ItemGrade;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionPotType {
    All,
    Normal,
    Rare,
    Epic,
    Unique,
    Legendary;

    public static AuctionPotType getByVal(int val) {
        if (val == -1) {
            return All;
        }
        return values()[val];
    }

    public boolean isMatching(Equip equip) {
        ItemGrade grade = ItemGrade.getGradeByVal(equip.getGrade());
        switch (this) {
            case All:
                return true;
            case Normal:
                return grade == ItemGrade.None;
            case Rare:
                return grade == ItemGrade.Rare || grade == ItemGrade.RareBonusHidden || grade == ItemGrade.HiddenRare;
            case Epic:
                return grade == ItemGrade.Epic || grade == ItemGrade.EpicBonusHidden || grade == ItemGrade.HiddenEpic;
            case Unique:
                return grade == ItemGrade.Unique || grade == ItemGrade.UniqueBonusHidden|| grade == ItemGrade.HiddenUnique;
            case Legendary:
                return grade == ItemGrade.Legendary || grade == ItemGrade.LegendaryBonusHidden || grade == ItemGrade.HiddenLegendary;
        }
        return false;
    }
}
