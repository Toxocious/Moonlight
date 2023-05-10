package net.swordie.ms.connection.api;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public enum ApiOutHeader {
    REQUEST_TOKEN_RESULT(100),
    CREATE_ACCOUNT_RESULT(101),
    CHECK_FILE_CHECKSUM_RESULT(102),
    ;

    private int val;

    ApiOutHeader(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ApiOutHeader getByVal(int val) {
        return Util.findWithPred(values(), header -> header.getVal() == val);
    }
}
