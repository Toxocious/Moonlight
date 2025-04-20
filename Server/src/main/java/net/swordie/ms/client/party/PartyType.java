package net.swordie.ms.client.party;

import net.swordie.ms.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 3/19/2018.
 */
public enum PartyType {
    PartyReq_LoadParty(0),
    PartyReq_CreateNewParty(1),
    PartyReq_WithdrawParty(2),
    PartyReq_JoinParty(3),
    PartyReq_InviteParty(4),
    PartyReq_InviteIntrusion(5), // member -> party request
    PartyReq_KickParty(6), // party -> member request
    PartyReq_ChangePartyBoss(7),
    PartyReq_ApplyParty(8),
    PartyReq_SetAppliable(9),
    PartyReq_ClearIntrusion(10),
    PartyReq_CreateNewParty_Group(11),
    PartyReq_JoinParty_Group(12),
    PartyReq_PartySetting(13),
    PartyReq_LoadStarPlanetPoint(14),

    PartyRes_LoadParty_Done(19),
    PartyRes_CreateNewParty_Done(20),
    PartyRes_CreateNewParty_AlreadyJoined(21),
    PartyRes_CreateNewParty_Beginner(22),
    PartyRes_CreateNewParty_Unknown(23),
    PartyRes_CreateNewParty_ByNonBoss(24),

    PartyRes_WithdrawParty_Done(25),
    PartyRes_WithdrawParty_NotJoined(26),
    PartyRes_WithdrawParty_Unknown(27),

    PartyRes_JoinParty_Done(28),
    PartyRes_JoinParty_Done2(29),

    PartyRes_JoinParty_AlreadyJoined(30),
    PartyRes_JoinParty_AlreadyFull(31),
    PartyRes_JoinParty_OverDesiredSize(32),
    PartyRes_JoinParty_UnknownUser(33),
    PartyRes_JoinParty_Unknown(34),
    PartyRes_JoinParty_OtherUserFieldLimit(35),

    PartyRes_JoinIntrusion_Done(36),
    PartyRes_JoinIntrusion_UnknownParty(37),

    PartyRes_InviteParty_Sent(38),
    PartyRes_InviteParty_BlockedUser(39),
    PartyRes_InviteParty_AlreadyInvited(40),
    PartyRes_InviteParty_AlreadyInvitedByInviter(41),
    PartyRes_InviteParty_Rejected(42),
    PartyRes_InviteParty_Accepted(43),
    PartyRes_InviteParty_OtherUserFieldLimit(44),

    PartyRes_InviteIntrusion_Sent(45),
    PartyRes_InviteIntrusion_BlockedUser(46),
    PartyRes_InviteIntrusion_AlreadyInvited(47),
    PartyRes_InviteIntrusion_AlreadyInvitedByInviter(48),
    PartyRes_InviteIntrusion_Rejected(49),
    PartyRes_InviteIntrusion_Accepted(50),

    PartyRes_KickParty_Done(51),
    PartyRes_KickParty_FieldLimit(52),
    PartyRes_KickParty_New(53),
    PartyRes_KickParty_Unknown(54),

    PartyRes_ChangePartyBoss_Done(55),
    PartyRes_ChangePartyBoss_NotSameField(56),
    PartyRes_ChangePartyBoss_NoMemberInSameField(57),
    PartyRes_ChangePartyBoss_NotSameChannel(58),
    PartyRes_ChangePartyBoss_Unknown(59),

    PartyRes_AdminCannotCreate(60),
    PartyRes_AdminCannotInvite(61),

    PartyRes_InAnotherWorld(62),
    PartyRes_InAnotherChanelBlockedUser(63),

    PartyRes_UserMigration(64),
    PartyRes_ChangeLevelOrJob(65),
    PartyRes_UpdateShutdownStatus(66),
    PartyRes_UpdateShutdownStatus_New(67),
    PartyRes_SetAppliable(68),
    PartyRes_SetAppliableFailed(69),
    PartyRes_SuccessToSelectPQReward(70),
    PartyRes_FailToSelectPQReward(71),
    PartyRes_ReceivePQReward(72),
    PartyRes_FailToRequestPQReward(73),
    PartyRes_FieldLimit(74),

    PartyRes_Unknown208(75), // could be wrong...

    PartyRes_ApplyParty_Sent(76),
    PartyRes_ApplyParty_UnknownParty(77),
    PartyRes_ApplyParty_BlockedUser(78),
    PartyRes_ApplyParty_AlreadyApplied(79),
    PartyRes_ApplyParty_AlreadyAppliedByApplier(80),
    PartyRes_ApplyParty_AlreadyFull(81),
    PartyRes_ApplyParty_Rejected(82),
    PartyRes_ApplyParty_Accepted(83),

    PartyRes_FoundPossibleMember(84),
    PartyRes_FoundPossibleParty(85),

    PartyRes_PartySettingDone(86), // everything under here is uncertain
    PartyRes_Load_StarGrade_Result(87),
    PartyRes_Load_StarGrade_Result2(88),
    PartyRes_Member_Rename(89),

    PartyInfo_TownPortalChanged(90),
    PartyInfo_OpenGate(91),

    // Unused ----------------------------------------

    ExpeditionReq_Load(92),
    ExpeditionReq_CreateNew(93),
    ExpeditionReq_Invite(94),
    ExpeditionReq_ResponseInvite(95),
    ExpeditionReq_Withdraw(96),
    ExpeditionReq_Kick(97),
    ExpeditionReq_ChangeMaster(98),
    ExpeditionReq_ChangePartyBoss(99),
    ExpeditionReq_RelocateMember(100),

    ExpeditionNoti_Load_Done(101),
    ExpeditionNoti_Load_Fail(102),
    ExpeditionNoti_CreateNew_Done(103),
    ExpeditionNoti_Join_Done(104),
    ExpeditionNoti_You_Joined(105),
    ExpeditionNoti_You_Joined2(106),
    ExpeditionNoti_Join_Fail(107),
    ExpeditionNoti_Withdraw_Done(108),
    ExpeditionNoti_You_Withdrew(109),
    ExpeditionNoti_Kick_Done(110),
    ExpeditionNoti_You_Kicked(111),
    ExpeditionNoti_Removed(112),
    ExpeditionNoti_MasterChanged(113),
    ExpeditionNoti_Modified(114),
    ExpeditionNoti_Modified2(115),
    ExpeditionNoti_Invite(116),
    ExpeditionNoti_ResponseInvite(117),
    ExpeditionNoti_Create_Fail_By_Over_Weekly_Counter(118),
    ExpeditionNoti_Invite_Fail_By_Over_Weekly_Counter(119),
    ExpeditionNoti_Apply_Fail_By_Over_Weekly_Counter(120),
    ExpeditionNoti_Invite_Fail_By_Blocked_Behavior(121),

    AdverNoti_LoadDone(122),
    AdverNoti_Change(123),
    AdverNoti_Remove(124),
    AdverNoti_GetAll(125),
    AdverNoti_Apply(126),
    AdverNoti_ResultApply(127),
    AdverNoti_AddFail(128),
    AdverReq_Add(129),
    AdverReq_Remove(130),
    AdverReq_GetAll(131),
    AdverReq_RemoveUserFromNotiList(132),
    AdverReq_Apply(133),
    AdverReq_ResultApply(134),

    No(-1)

    ;

    private byte val;

    PartyType(int val) {
        this.val = (byte) val;
    }

    public static PartyType getByVal(byte type) {
        if (type >= 0 && type <= values().length) {
            return values()[type];
        }
        return null;
    }

    public byte getVal() {
        return val;
    }

    public static void main(String[] args) {
        File file = new File("C:\\v207\\src\\main\\java\\net\\swordie\\ms\\client\\party\\PartyType.java");
        int change = -3;
        boolean check = false;
        PartyType checkOp = null;
        try(Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(",") && line.contains("(")) {
                    String[] split = line.split("[()]");
                    String name = split[0];
                    if (!Util.isNumber(split[1])) {
                        System.out.println(line);
                        continue;
                    }
                    int val = Integer.parseInt(split[1]);
                    PartyType oh = Arrays.stream(PartyType.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (oh != null) {
                        PartyType start = PartyRes_InviteIntrusion_Sent;
                        if (oh.ordinal() >= start.ordinal() && oh.ordinal() < No.ordinal()) {
                            if (line.contains("*")) {
                                check = true;
                                checkOp = oh;
                            }
                            val += change;
                            System.out.println(String.format("%s(%d), %s", name, val, start == oh ? "// *" : ""));
                        } else {
                            System.out.println(line);
                        }
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (check) {
            System.err.println(String.format("Current op (%s) contains a * (= updated). Be sure to check for overlap.", checkOp));
        }

    }
}