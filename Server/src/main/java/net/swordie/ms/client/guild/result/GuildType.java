package net.swordie.ms.client.guild.result;

import net.swordie.ms.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created on 3/21/2018.
 */
public enum GuildType {
    Req_LoadGuild(0),
    Req_FindGuildByCid(1),
    Req_FindGuildByGID(2),
    Req_InputGuildName(3),
    Req_CheckGuildName(4),
    Req_CreateGuildAgree(5),
    Req_CreateNewGuild(6),

    Req_InviteGuild(7),
    Req_JoinGuild(8),
    Req_JoinGuildDirect(9),
    Req_UpdateJoinState(10),
    Req_WithdrawGuild(11),
    Req_KickGuild(12),
    Req_RemoveGuild(13),
    Req_IncMaxMemberNum(14),

    Req_ChangeLevel(15),
    Req_ChangeJob(16),
    Req_SetGuildName(17),
    Req_SetGradeName(18),
    Req_SetMemberGrade(19),
    Req_SetMark(20),
    Req_SetNotice(21),
    Req_InputMark(22),

    Req_CheckQuestWaiting(23),
    Req_CheckQuestWaiting2(24),
    Req_InsertQuestWaiting(25),
    Req_CancelQuestWaiting(26),
    Req_RemoveQuestCompleteGuild(27),

    Req_IncPoint(28),
    Req_IncCommitment(29),
    Req_DecGGP(30),
    Req_DecIGP(31),

    Req_SetQuestTime(32),
    Req_ShowGuildRanking(33),

    Req_SetSkill(34),
    Req_SkillLevelSetUp(35),
    Req_ResetGuildBattleSkill(36),
    Req_UseActiveSkill(37),
    Req_UseADGuildSkill(38),
    Req_ExtendSkill(39),
    Req_ChangeGuildMaster(40),
    Req_FromGuildMember_GuildSkillUse(41),

    Req_SetGGP(42),
    Req_SetIGP(43),

    Req_BattleSkillOpen(44),
    Req_Search(45),

    Req_CreateNewGuild_Block(46),
    Req_CreateNewAlliance_Block(47),

    // Results ------------------------------
    Res_LoadGuild_Done(59),
    Res_FindGuild_Done(60),

    Res_CheckGuildName_Available(61),
    Res_CheckGuildName_AlreadyUsed(62),
    Res_CheckGuildName_Unknown(63),

    Res_CreateGuildAgree_Reply(64),
    Res_CreateGuildAgree_Unknown(65),
    Res_CreateNewGuild_Done(67),
    Res_CreateNewGuild_AlreadyJoined(68),
    Res_CreateNewGuild_GuildNameAlreayExist(69),
    Res_CreateNewGuild_Beginner(70),
    Res_CreateNewGuild_Disagree(71),
    Res_CreateNewGuild_NotFullParty(72),
    Res_CreateNewGuild_Unknown(73),

    Res_JoinGuild_Done(74),
    Res_JoinGuild_AlreadyJoined(75),
    Res_JoinGuild_AlreadyJoinedToUser(76),
    Res_JoinGuild_AlreadyFull(77),
    Res_JoinGuild_LimitRequest(78),
    Res_JoinGuild_UnknownUser(79),
    Res_JoinGuild_NonRequestFindUser(80),
    Res_JoinGuild_Unknown(81),

    Res_JoinRequest_Done(82),
    Res_JoinRequest_DoneToUser(83),
    Res_JoinRequest_AlreadyFull(84),
    Res_JoinRequest_LimitTime(85),
    Res_JoinRequest_Unknown(86),
    Res_JoinCancelRequest_Done(87),

    Res_WithdrawGuild_Done(88),
    Res_WithdrawGuild_NotJoined(89),
    Res_WithdrawGuild_Unknown(90),

    Res_KickGuild_Done(91),
    Res_KickGuild_NotJoined(92),
    Res_KickGuild_Unknown(93),

    Res_RemoveGuild_Done(94),
    Res_RemoveGuild_NotExist(95),
    Res_RemoveGuild_Unknown(96),
    Res_RemoveRequestGuild_Done(97),

    Res_InviteGuild_BlockedUser(98),
    Res_InviteGuild_BlockedRequests(99),
    Res_InviteGuild_AlreadyInvited(100),
    Res_InviteGuild_Rejected(101),

    Res_Join_Guild_New(106),
    // Bunch of Create/Join messages, copies of above?

    Res_AdminCannotCreate(112),
    Res_AdminCannotInvite(113),

    Res_IncMaxMemberNum_Done(114),
    Res_IncMaxMemberNum_Unknown(115),

    Res_ChangeMemberName(116),
    Res_ChangeRequestUserName(117),
    Res_ChangeLevelOrJob(118),
    Res_NotifyLoginOrLogout(119),
    Res_SetGradeName_Done(120),
    Res_SetGradeName_Unknown(121),
    Res_SetMemberGrade_Done(122),
    Res_SetMemberGrade_Unknown(123),
    Res_SetMemberCommitment_Done(128),
    Res_SetMark_Done(129),
    Res_SetMark_Unknown(130),
    Res_SetNotice_Done(131),

    Res_InsertQuest(132),
    Res_NoticeQuestWaitingOrder(133),
    Res_SetGuildCanEnterQuest(134),

    Res_IncPoint_Done(147),
    Res_ShowGuildRanking(148),
    Res_SetGGP_Done(149), //?????
    Res_SetIGP_Done(150),

    Res_GuildQuest_NotEnoughUser(151),
    Res_GuildQuest_RegisterDisconnected(152),
    Res_GuildQuest_NoticeOrder(153),

    Res_Authkey_Update(154),
    Res_SetSkill_Done(155),
    Res_SetSkill_Extend_Unknown(156),
    Res_SetSkill_LevelSet_Unknown(157),
    Res_SetSkill_ResetBattleSkill(158),

    Res_UseSkill_Success(159),
    Res_UseSkill_Err(160),

    Res_ChangeName_Done(161),
    Res_ChangeName_Unknown(162),
    Res_ChangeMaster_Done(163),
    Res_ChangeMaster_Unknown(164),

    Res_BlockedBehaviorCreate(165),
    Res_BlockedBehaviorJoin(166),
    Res_BattleSkillOpen(167),
    Res_GetData(168),
    Res_Rank_Reflash(169),
    Res_FindGuild_Error(170),
    Res_ChangeMaster_Pinkbean(171),

    Res_CheckIn(176),

    No(-1),
    ;

    private byte val;

    GuildType(int val) {
        this.val = (byte) val;
    }

    public static GuildType getTypeByVal(byte val) {
        return Arrays.stream(values()).filter(grt -> grt.getVal() == val).findAny().orElse(null);
    }

    public byte getVal() {
        return val;
    }

    public static void main(String[] args) {
        File file = new File("D:\\SwordieMS\\SwordieUTD\\src\\main\\java\\net\\swordie\\ms\\client\\guild\\result\\GuildType.java");
        int change = -1;
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
                    GuildType oh = Arrays.stream(GuildType.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (oh != null) {
                        if (oh.ordinal() >= Res_AdminCannotCreate.ordinal() && oh.ordinal() < No.ordinal()) {
                            val += change;
                            System.out.println(String.format("%s(%d),", name, val));
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
    }
}