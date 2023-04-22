package net.swordie.ms.client.alliance;

import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.AllianceType;

/**
 * @author Sjonnie
 * Created on 9/1/2018.
 */
public class AllianceResult implements Encodable {
    private AllianceType type;
    private Alliance alliance;
    private Guild guild;
    private GuildMember member;
    private GuildMember member2;
    private boolean bool;
    private int arg;

    public AllianceResult(AllianceType type) {
        this.type = type;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(type.getVal());
        switch (type) {
            case Req_Invite:
                outPacket.encodeInt(member.getId()); // dwMemberID
                outPacket.encodeString(member.getName());
                outPacket.encodeString(alliance.getName());
                break;
            case Res_LoadDone:
                outPacket.encodeByte(alliance != null);
                if (alliance != null) {
                    outPacket.encode(alliance);
                }
                break;
            case Res_LoadGuildDone:
                if (alliance.getGuilds() != null) {
                    outPacket.encodeInt(alliance.getGuilds().size());
                    for (Guild guild : alliance.getGuilds()) {
                        outPacket.encode(guild);
                    }
                } else {
                    outPacket.encodeInt(0);
                }
                break;
            case Res_NotifyLoginOrLogout:
                outPacket.encodeInt(alliance.getId()); // ignored?
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeByte(member.isOnline());
                outPacket.encodeByte(bool); // bCreateWindow
                break;
            case Res_CreateDone:
                outPacket.encode(alliance);
                for (Guild guild : alliance.getGuilds()) {
                    outPacket.encode(guild);
                }
                break;
            case Res_Withdraw_Done:
                outPacket.encode(alliance);
                outPacket.encodeInt(guild.getId()); // nLeavingGuildID
                outPacket.encode(guild);
                outPacket.encodeByte(bool); // bKicked
                break;
            case Res_Invite_Done:
                outPacket.encode(alliance);
                outPacket.encodeInt(guild.getId());
                outPacket.encode(guild);
                break;
            case Res_Invite_Failed:
            case Res_ChangeGrade_Fail:
                outPacket.encodeInt(arg); // nType
                break;
            case Res_UpdateAllianceInfo:
                outPacket.encode(alliance);
                break;
            case Res_ChangeLevelOrJob:
                outPacket.encodeInt(alliance.getId());
                outPacket.encodeInt(guild.getId());
                outPacket.encodeInt(member.getCharID());
                outPacket.encodeInt(member.getLevel());
                outPacket.encodeInt(member.getJob());
                break;
            case Res_ChangeMaster_Done:
                outPacket.encodeInt(alliance.getId());
                outPacket.encodeInt(member.getId()); // nOldLeaderID
                outPacket.encodeInt(member2.getId()); // nNewLeaderID
                break;
            case Res_SetGradeName_Done:
                outPacket.encodeInt(alliance.getId());
                for (String gradeName : alliance.getGradeNames()) {
                    outPacket.encodeString(gradeName);
                }
                break;
            case Res_ChangeGrade_Done:
                outPacket.encodeInt(member.getId());
                outPacket.encodeByte(arg); // nAllianceGrade
                break;
            case Res_SetNotice_Done:
                outPacket.encodeInt(alliance.getId());
                outPacket.encodeString(alliance.getNotice());
                break;
            case Res_Destroy_Done:
                outPacket.encodeInt(alliance.getId());
                break;
            case Res_UpdateGuildInfo:
                outPacket.encodeInt(alliance.getId());
                outPacket.encodeInt(guild.getId());
                outPacket.encode(guild);
                break;
        }
    }


    public static AllianceResult inviteGuild(Alliance alliance, GuildMember member) {
        AllianceResult ar = new AllianceResult(AllianceType.Req_Invite);

        ar.member = member;
        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult loadDone(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_LoadDone);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult loadGuildDone(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_LoadGuildDone);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult notifyLoginOrLogout(Alliance alliance, Guild guild, GuildMember guildMember, boolean showWindow) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_NotifyLoginOrLogout);

        ar.alliance = alliance;
        ar.guild = guild;
        ar.member = guildMember;
        ar.bool = showWindow;

        return ar;
    }

    public static AllianceResult createDone(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_CreateDone);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult withdraw(Alliance alliance, Guild guild, boolean kicked) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_Withdraw_Done);

        ar.alliance = alliance;
        ar.guild = guild;
        ar.bool = kicked;

        return ar;
    }

    public static AllianceResult inviteDone(Alliance alliance, Guild guild) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_Invite_Done);

        ar.alliance = alliance;
        ar.guild = guild;

        return ar;
    }

    public static AllianceResult msg(AllianceType art) {
        return msg(art, 0);
    }

    public static AllianceResult msg(AllianceType art, int arg) {
        AllianceResult ar = new AllianceResult(art);

        ar.arg = arg;

        return ar;
    }

    public static AllianceResult updateAllianceInfo(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_UpdateAllianceInfo);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult changeLevelOrJob(Alliance alliance, Guild guild, GuildMember member) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_ChangeLevelOrJob);

        ar.alliance = alliance;
        ar.guild = guild;
        ar.member = member;

        return ar;
    }

    public static AllianceResult changeMaster(Alliance alliance, GuildMember oldMaster, GuildMember newMaster) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_ChangeMaster_Done);

        ar.alliance = alliance;
        ar.member = oldMaster;
        ar.member2 = newMaster;

        return ar;
    }

    public static AllianceResult setGradeName(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_SetGradeName_Done);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult changeGrade(GuildMember member) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_ChangeGrade_Done);

        ar.member = member;
        ar.arg = member.getAllianceGrade();

        return ar;
    }

    public static AllianceResult setNotice(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_SetNotice_Done);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult destroy(Alliance alliance) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_Destroy_Done);

        ar.alliance = alliance;

        return ar;
    }

    public static AllianceResult updateGuildInfo(Alliance alliance, Guild guild) {
        AllianceResult ar = new AllianceResult(AllianceType.Res_UpdateGuildInfo);

        ar.alliance = alliance;
        ar.guild = guild;

        return ar;
    }
}
