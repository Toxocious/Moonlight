package net.swordie.ms.client.guild.result;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.GuildSkill;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;

import static net.swordie.ms.client.guild.result.GuildType.*;

/**
 * Created on 3/21/2018.
 */
public class GuildResult {

    private GuildType type;
    private Guild guild;
    private GuildMember member;
    private String[] gradeNames;
    private boolean online;
    private boolean showBox;
    private Char chr;
    private int intArg;
    private String stringArg;
    private GuildSkill skill;

    private GuildResult(GuildType type) {
        this.type = type;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(type.getVal());
        switch (type) {
            case Res_LoadGuild_Done:
                outPacket.encodeInt(50000); //??
                outPacket.encodeByte(guild != null);
                if (guild != null) {
                    outPacket.encodeInt(0);
                    outPacket.encode(guild);
                    int someint = 0;
                    outPacket.encodeInt(someint); // aGuildNeedPoint
                    if (someint > 0) {
                        outPacket.encodeInt(0);
                    }
                }
                break;
            case Res_FindGuild_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encode(guild);
                break;
            case Res_SetGradeName_Done:
                outPacket.encodeInt(guild.getId());
                for (String name : gradeNames) {
                    outPacket.encodeString(name);
                }
                break;
            case Res_CreateNewGuild_Done:
                outPacket.encode(guild);
                break;
            case Res_Join_Guild_New:
                outPacket.encodeInt(chr.getGuild().getId());
                outPacket.encodeString(chr.getGuild().getName());
                outPacket.encodeInt(chr.getId());
                outPacket.encodeString(chr.getName());
                outPacket.encodeInt(chr.getLevel());
                outPacket.encodeInt(chr.getJob());
                outPacket.encodeInt(chr.getSubJob());
                break;
            case Res_JoinGuild_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encode(member);
                break;
            case Res_KickGuild_Done:
            case Res_WithdrawGuild_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(intArg); // expelledID
                outPacket.encodeString(stringArg); // expelledName
                break;
            case Res_RemoveGuild_Done:
                outPacket.encodeInt(guild.getId());
                break;
            case Res_SetMark_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeShort(guild.getMarkBg());
                outPacket.encodeByte(guild.getMarkBgColor());
                outPacket.encodeShort(guild.getMark());
                outPacket.encodeByte(guild.getMarkColor());
                break;
            case Res_IncMaxMemberNum_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(guild.getMaxMembers());
                break;
            case Res_SetMemberGrade_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeByte(member.getGrade());
                break;
            case Res_ChangeLevelOrJob:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeInt(member.getLevel());
                outPacket.encodeInt(member.getJob());
                break;
            case Res_NotifyLoginOrLogout:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeByte(online);
                outPacket.encodeByte(showBox);
                break;
            case Res_SetMemberCommitment_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                //outPacket.encodeInt(member.getCommitment());
                outPacket.encodeInt(member.getIgp());
                outPacket.encodeInt(member.getDayCommitment());
                outPacket.encodeFT(member.getCommitmentIncTime());
                break;
            case Res_SetGGP_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(guild.getGgp());
                break;
            case Res_SetIGP_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeInt(member.getIgp());
                break;
            case Res_IncPoint_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(guild.getPoints());
                outPacket.encodeInt(guild.getLevel());
                outPacket.encodeInt(guild.getGgp());
                outPacket.encodeInt(0);
                break;
            case Res_SetSkill_Done:
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(skill.getSkillID());
                outPacket.encodeInt(intArg); // nBuyCharacterID
                outPacket.encode(skill);
                break;
            case Res_BattleSkillOpen:
                outPacket.encodeInt(guild.getBattleSp());
                break;
            case Res_Rank_Reflash:
                outPacket.encodeInt(guild.getRank());
                break;
            case Res_SetSkill_LevelSet_Unknown:
                outPacket.encodeByte(false);
                break;
            case Res_InviteGuild_Rejected:
                outPacket.encodeString(stringArg);
                break;
        }
    }

    public static GuildResult loadGuild(Guild guild) {
        GuildResult gri = new GuildResult(Res_LoadGuild_Done);
        gri.guild = guild;
        return gri;
    }

    public static GuildResult setGradeName(Guild guild, String[] gradeNames) {
        GuildResult gri = new GuildResult(Res_SetGradeName_Done);
        gri.guild = guild;
        gri.gradeNames = gradeNames;
        return gri;
    }

    public static GuildResult createNewGuild(Guild guild) {
        GuildResult gri = new GuildResult(Res_CreateNewGuild_Done);
        gri.guild = guild;
        return gri;
    }

    public static GuildResult inviteGuild(Char chr) {
        GuildResult gri = new GuildResult(Res_Join_Guild_New);
        gri.chr = chr;
        return gri;
    }

    public static GuildResult joinGuild(Guild guild, GuildMember member) {
        GuildResult gri = new GuildResult(Res_JoinGuild_Done);
        gri.guild = guild;
        gri.member = member;
        return gri;
    }

    public static GuildResult leaveGuild(Guild guild, int leaverID, String leaverName, boolean expelled) {
        GuildResult gri = new GuildResult(expelled ? Res_KickGuild_Done : Res_WithdrawGuild_Done);
        gri.guild = guild;
        gri.intArg = leaverID;
        gri.stringArg = leaverName;
        return gri;
    }

    public static GuildResult setMark(Guild guild) {
        GuildResult gri = new GuildResult(Res_SetMark_Done);
        gri.guild = guild;
        return gri;
    }

    public static GuildResult setMemberGrade(Guild guild, GuildMember member) {
        GuildResult gri = new GuildResult(Res_SetMemberGrade_Done);
        gri.guild = guild;
        gri.member = member;
        return gri;
    }

    public static GuildResult changeLevelOrJob(Guild guild, GuildMember member) {
        GuildResult gri = new GuildResult(Res_ChangeLevelOrJob);
        gri.guild = guild;
        gri.member = member;
        return gri;
    }

    public static GuildResult notifyLoginOrLogout(Guild guild, GuildMember member, boolean online, boolean showBox) {
        GuildResult gri = new GuildResult(Res_NotifyLoginOrLogout);
        gri.guild = guild;
        gri.member = member;
        gri.online = online;
        gri.showBox = showBox;
        return gri;
    }

    public static GuildResult msg(GuildType type) {
        return new GuildResult(type);
    }

    public static GuildResult setMemberCommitment(Guild guild, GuildMember member) {
        GuildResult gri = new GuildResult(Res_SetMemberCommitment_Done);
        gri.guild = guild;
        gri.member = member;
        return gri;
    }

    public static GuildResult setMemberIgp(Guild guild, GuildMember member) {
        GuildResult gri = new GuildResult(Res_SetIGP_Done);
        gri.guild = guild;
        gri.member = member;
        return gri;
    }

    public static GuildResult setGgp(Guild guild) {
        GuildResult gri = new GuildResult(Res_SetGGP_Done);
        gri.guild = guild;
        return gri;
    }

    public static GuildResult setPointAndLevel(Guild guild) {
        GuildResult gr = new GuildResult(Res_IncPoint_Done);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult setSkill(Guild guild, GuildSkill skill, int buyCharID) {
        GuildResult gr = new GuildResult(Res_SetSkill_Done);
        gr.guild = guild;
        gr.skill = skill;
        gr.intArg = buyCharID;
        return gr;
    }

    public static GuildResult battleSkillOpen(Guild guild) {
        GuildResult gr = new GuildResult(Res_BattleSkillOpen);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult setRank(Guild guild) {
        GuildResult gr = new GuildResult(Res_Rank_Reflash);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult incMaxMemberNum(Guild guild) {
        GuildResult gr = new GuildResult(Res_IncMaxMemberNum_Done);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult removeComplete(Guild guild) {
        GuildResult gr = new GuildResult(Res_RemoveGuild_Done);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult findGuild(Guild guild) {
        GuildResult gr = new GuildResult(Res_FindGuild_Done);
        gr.guild = guild;
        return gr;
    }

    public static GuildResult inviteGuildRejected(String stringArg) {
        GuildResult gr = new GuildResult(Res_InviteGuild_Rejected);
        gr.stringArg = stringArg;
        return gr;
    }


    public GuildType getType() {
        return type;
    }

}
