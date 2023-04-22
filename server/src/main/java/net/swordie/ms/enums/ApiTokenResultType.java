package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public enum ApiTokenResultType {
    Success(0),
    InvalidUserPassCombination(1),
    TooManyRequest(2),
    ;
    private int val;

    ApiTokenResultType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ApiTokenResultType getByVal(int val) {
        return Util.findWithPred(values(), a -> a.getVal() == val);
    }
}
