package net.swordie.ms.connection.api;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public enum ApiInHeader {
    REQUEST_TOKEN(100),
    CREATE_ACCOUNT_REQUEST(101),
    ;

    private int val;

    ApiInHeader(int val) {
        this.val = val;
    }

    public static ApiInHeader getByVal(short op) {
        return Util.findWithPred(values(), header -> header.getVal() == op);
    }

    public int getVal() {
        return val;
    }
}
