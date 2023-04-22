package net.swordie.ms.client.guild.bbs;

import java.util.Arrays;

/**
 * @author Sjonnie
 * Created on 8/12/2018.
 */
public enum GuildBBSType {
    Req_CreateRecord(0),
    Req_DeleteRecord(1),
    Req_LoadPages(2),
    Req_LoadRecord(3),
    Req_CreateReply(4),
    Req_DeleteReply(5),
    Res_LoadPages(6),
    Res_LoadRecord(7),
    Res_SomethingElse(8),
    ;

    private int val;

    GuildBBSType(int val) {
        this.val = val;
    }

    public static GuildBBSType getByValue(byte val) {
        return Arrays.stream(values()).filter(gbt -> gbt.getVal() == val).findAny().orElse(null);
    }

    public int getVal() {
        return val;
    }
}
