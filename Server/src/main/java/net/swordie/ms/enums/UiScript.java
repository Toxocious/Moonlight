package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 2/13/2019.
 */
public enum UiScript {
    DamageSkinHelp(13),

    UnionRaidStart(18),
    ;

    private int val;

    UiScript(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static UiScript getByVal(int val) {
        return Util.findWithPred(values(), us -> us.getVal() == val);
    }
}
