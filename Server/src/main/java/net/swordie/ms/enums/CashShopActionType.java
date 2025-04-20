package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * @author Sjonnie
 * Created on 7/7/2018.
 */
public enum CashShopActionType {
    ShowCategory(11),
    ShowCategory2(13),
    Res_14(14),
    Res_16(16),
    ShowFavorites(18),
    Res_19(19),
    Res_20(20),
    Res_21(21),
    Res_22(22),
    Req_OpenCategory(101),
    Req_Leave(102),
    Req_Favorite(103);

    private int val;

    CashShopActionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static CashShopActionType getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), csat -> csat.getVal() == val);
    }
}
