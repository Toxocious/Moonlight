package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 8/10/2018.
 */
public enum RoomLeaveType {
    MRLeave_UserRequest(0),
    MRLeave_WrongPosition(1),
    MRLeave_Closed(2),
    MRLeave_HostOut(3),
    MRLeave_Booked(4),
    MRLeave_Kicked(5),
    MRLeave_OpenTimeOver(6),
    TRLeave_TradeDone(7),
    TRLeave_TradeFail(8),
    TRLeave_TradeFail_OnlyItem(9),
    TRLeave_TradeFail_Expired(10),
    TRLeave_TradeFail_Denied(11),
    TRLeave_TradeFail_Invalid_CashItemSN(12),
    TRLeave_FieldError(13),
    TRLeave_ItemCRCFailed(14),
    TRLeave_Trade_UnableWorld(15),
    PSLeave_NoMoreItem(16),
    PSLeave_KickedTimeOver(17),
    ESLeave_Open(18),
    ESLeave_StartManage(19),
    ESLeave_ClosedTimeOver(20),
    ESLeave_EndManage(21),
    ESLeave_DestoryByAdmin(22),
    MGLeave_UserRequest(23),
    WRLeave_PartnerGone(24),
    WRLeave_CanceledByNoInventory0(25),
    WRLeave_CanceledByNoInventory1(26),
    WRLeave_GracefulClose(27),
    CMRLeave_NoCandy(28),
    CMRLeave_NoHostInven(29),
    CMRLeave_NoGuestInven(30),
    CMRLeave_Reject(31),
    CMRLeave_NotOnTime(32),
    CMRLeave_Done(33),
    CMRLeave_Failed(34),
        ;

    private int val;

    RoomLeaveType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
