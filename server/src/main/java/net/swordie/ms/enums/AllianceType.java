package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 9/1/2018.
 */
public enum AllianceType {
    Req_Create(0),
    Req_Load(1),
    Req_Withdraw(2),
    Req_Invite(3),
    Req_Join(4),
    Req_UpdateMemberCountMax(5),
    Req_Kick(6),
    Req_ChangeMaster(7),
    Req_SetGradeName(8),
    Req_ChangeGrade(9),
    Req_SetNotice(10),
    Req_Destroy(11),
    Req_ChangeName(12),

    Res_LoadDone(13),
    Res_LoadGuildDone(14),
    Res_NotifyLoginOrLogout(15),
    Res_CreateDone(16),
    Res_Withdraw_Done(17),
    Res_Withdraw_Failed(18),
    Res_Invite_Done(19),
    Res_Invite_Failed(20),
    Res_InviteGuild_BlockedByOpt(21),
    Res_InviteGuild_AlreadyInvited(22),
    Res_InviteGuild_Rejected(23),
    Res_UpdateAllianceInfo(24),
    Res_ChangeLevelOrJob(25),
    Res_ChangeMaster_Done(26),
    Res_SetGradeName_Done(27),
    Res_ChangeGrade_Done(28),
    Res_ChangeGrade_Fail(29),
    Res_SetNotice_Done(30),
    Res_Destroy_Done(31),
    Res_UpdateGuildInfo(32),
    Res_ChangeName_Done(33),
    Res_ChangeName_Failed(34),
    ;

    private final int val;

    AllianceType(int val) {
        this.val = val;
    }

    public static AllianceType getByVal(int val) {
        if (val >= 0 && val < values().length) {
            return values()[val];
        }
        return null;
    }

    public int getVal() {
        return val;
    }
}
