package net.swordie.ms.enums;

/**
 * Created on 6/7/2018.
 */
public enum LinkedSkillResultType {
    SetSonOfLinkedSkillResult_Success(0),
    SetSonOfLinkedSkillResult_Fail_ParentAlreadyExist(1),
    SetSonOfLinkedSkillResult_Fail_Unknown(2),
    SetSonOfLinkedSkillResult_Fail_MaxCount(3),
    SetSonOfLinkedSkillResult_Fail_DBRequestFail(4),
    ;

    private int val;

    LinkedSkillResultType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
