package net.swordie.ms.client.character.damage;

import net.swordie.ms.util.Util;

/**
 * Created on 4/5/2018.
 */
public enum DamageSkinType {
    Req_Reg(0),
    Req_Remove(1),
    Req_Active(2),
    Req_SendInfo(3),
    Res_Success(4),
    Res_Fail_Unknown(5),
    Res_Fail_SlotCount(6),
    Res_Fail_AlreadyExist(7),
    Res_Fail_NotSave(8),
    Res_Fail_ServerValueBlock(9),
    Res_Fail_AlreadyActive(10),
    ;

    private int val;

    DamageSkinType(int val) {
        this.val = val;
    }

    public static DamageSkinType getByVal(int val) {
        return Util.findWithPred(values(), dst -> dst.getVal() == val);
    }

    public int getVal() {
        return val;
    }

}
