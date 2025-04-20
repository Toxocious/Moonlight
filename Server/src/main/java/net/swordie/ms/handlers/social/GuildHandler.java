package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.alliance.AllianceResult;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.GuildRequestor;
import net.swordie.ms.client.guild.GuildSkill;
import net.swordie.ms.client.guild.bbs.BBSRecord;
import net.swordie.ms.client.guild.bbs.BBSReply;
import net.swordie.ms.client.guild.bbs.GuildBBSPacket;
import net.swordie.ms.client.guild.bbs.GuildBBSType;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.guild.result.GuildType;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AllianceType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class GuildHandler {

    private static final Logger log = Logger.getLogger(GuildHandler.class);


    @Handler(op = InHeader.GUILD_REQUEST)
    public static void handleGuildRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte type = inPacket.decodeByte();
        GuildType grt = GuildType.getTypeByVal(type);
        if (grt == null) {
            log.error(String.format("Unknown guild request %d", type));
            return;
        }
        Guild guild = chr.getGuild();
        Field field = chr.getField();
        World world = chr.getWorld();
        switch (grt) {
            case Req_LoadGuild:
                guild = chr.getClient().getWorld().getGuildByID(chr.getGuild().getId());
                if (guild != null && chr.getGuild() == null) {
                    chr.write(WvsContext.guildResult(GuildResult.loadGuild(guild)));
                    field.broadcastPacket(UserRemote.guildNameChanged(chr));
                    field.broadcastPacket(UserRemote.guildMarkChanged(chr));
                }
                break;
            case Req_FindGuildByCid: // AcceptJoinRequest:
                int charID = inPacket.decodeInt();
                Char other = chr.getClient().getChannelInstance().getCharById(charID);

                if (other != null && other.getGuild() != null) {
                    chr.write(WvsContext.guildResult(GuildResult.findGuild(other.getGuild())));
                } else {
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_UnknownUser)));
                }
                break;
            case Req_FindGuildByGID: //
                int guildID = inPacket.decodeInt();
                guild = chr.getWorld().getGuildByID(guildID);
                if (guild != null) {
                    chr.write(WvsContext.guildResult(GuildResult.findGuild(guild)));
                } else {
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_FindGuild_Error)));
                }
                break;
            case Req_CheckGuildName:
                String name = inPacket.decodeString();
                guild = world.getGuildByName(name);
                if (guild == null) {
                    guild = new Guild();
                    guild.setLevel(1);
                    guild.setName(name);
                    DatabaseManager.saveToDB(guild);
                    world.addGuild(guild);
                    chr.setGuild(guild);
                    guild = chr.getGuild(); // setGuild may change the instance
                    guild.addMember(chr);
                    guild.setWorldID(chr.getClient().getWorldId());
                    chr.getWorld().addGuild(guild);
                    chr.write(WvsContext.guildResult(GuildResult.createNewGuild(guild)));
                    field.broadcastPacket(UserRemote.guildNameChanged(chr));
                    field.broadcastPacket(UserRemote.guildMarkChanged(chr));
                    chr.deductMoney(5000000);
                } else {
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_CheckGuildName_AlreadyUsed)));
                }
                break;
            case Req_InviteGuild:
                Char invited = chr.getClient().getChannelInstance().getCharByName(inPacket.decodeString());
                if (invited == null) {
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_UnknownUser)));
                } else {
                    invited.write(WvsContext.guildResult(GuildResult.inviteGuild(chr)));
                }
                chr.dispose();
                break;
            case Req_WithdrawGuild:
                if (guild == null) {
                    return;
                }
                name = chr.getName();
                guild.broadcast(WvsContext.guildResult(GuildResult.leaveGuild(guild, chr.getId(), name, false)));
                guild.removeMember(chr);
                chr.setGuild(null);
                field.broadcastPacket(UserRemote.guildNameChanged(chr));
                field.broadcastPacket(UserRemote.guildMarkChanged(chr));
                break;
            case Req_KickGuild:
                if (guild == null) {
                    return;
                }
                int expelledID = inPacket.decodeInt();
                String expelledName = inPacket.decodeString();
                GuildMember gm = guild.getMemberByCharID(expelledID);
                Char expelled = gm.getChr();
                guild.broadcast(WvsContext.guildResult(GuildResult.leaveGuild(guild, expelledID, expelledName, true)));
                if (expelled == null) {
                    expelled = Char.getFromDBById(expelledID);
                    guild.removeMember(gm);
                    DatabaseManager.saveToDB(expelled);
                } else {
                    guild.removeMember(gm);
                    field.broadcastPacket(UserRemote.guildNameChanged(expelled));
                    field.broadcastPacket(UserRemote.guildMarkChanged(expelled));
                }
                break;
            case Req_SetMark:
                if (guild == null) {
                    return;
                }
                if (guild.getGgp() < 150000 || guild.getLevel() < 2) {
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetMark_Unknown)));
                    return;
                }
                guild.setGgp(guild.getGgp() - 150000);
                guild.setMarkBg(inPacket.decodeShort());
                guild.setMarkBgColor(inPacket.decodeByte());
                guild.setMark(inPacket.decodeShort());
                guild.setMarkColor(inPacket.decodeByte());
                chr.write(WvsContext.guildResult(GuildResult.setMark(guild))); // This doesn't actually update the emblem client side
                guild.broadcast(WvsContext.guildResult(GuildResult.loadGuild(guild)));
                field.broadcastPacket(UserRemote.guildNameChanged(chr));
                field.broadcastPacket(UserRemote.guildMarkChanged(chr));
                break;
            case Req_SetGradeName:
                if (guild == null) {
                    return;
                }
                String[] newNames = new String[guild.getGradeNames().size()];
                for (int i = 0; i < newNames.length; i++) {
                    newNames[i] = inPacket.decodeString();
                }
                guild.setGradeNames(newNames);
                guild.broadcast(WvsContext.guildResult(GuildResult.setGradeName(guild, newNames)));
                break;
            case Req_SetMemberGrade:
                if (guild == null) {
                    return;
                }
                int id = inPacket.decodeInt();
                byte grade = inPacket.decodeByte();
                gm = guild.getMemberByCharID(id);
                gm.setGrade(grade);
                guild.broadcast(WvsContext.guildResult(GuildResult.setMemberGrade(guild, gm)));
                break;
            case Req_SkillLevelSetUp:
                if (guild == null) {
                    return;
                }
                int skillID = inPacket.decodeInt();
                boolean up = inPacket.decodeByte() != 0;
                if (up) {
                    if (!SkillConstants.isGuildContentSkill(skillID) && !SkillConstants.isGuildNoblesseSkill(skillID)) {
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to add an invalid guild skill (%d)", chr.getId(), skillID));
                        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_UseSkill_Err)));
                        return;
                    }
                    int spentSp = guild.getSpentSp();
                    if (SkillConstants.isGuildContentSkill(skillID)) {
                        if (spentSp >= guild.getLevel() * 2) {
                            chr.getOffenseManager().addOffense(String.format("Character %d tried to add a guild skill without enough sp (spent %d, level %d).",
                                    chr.getId(), spentSp, guild.getLevel()));
                            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                            return;
                        }
                    } else if (guild.getBattleSp() - guild.getSpentBattleSp() <= 0) { // Noblesse
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to add a guild battle skill without enough sp (spent %d).",
                                chr.getId(), guild.getSpentSp()));
                        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                        return;
                    }
                    SkillInfo si = SkillData.getSkillInfoById(skillID);
                    if (spentSp < si.getReqTierPoint()) {
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to add a guild skill without enough sp spent (spent %d, needed %d).",
                                chr.getId(), spentSp, si.getReqTierPoint()));
                        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                        return;
                    }
                    for (Map.Entry<Integer, Integer> entry : si.getReqSkills().entrySet()) {
                        int reqSkillID = entry.getKey();
                        int reqSlv = entry.getValue();
                        GuildSkill gs = guild.getSkillById(reqSkillID);
                        if (gs == null || gs.getLevel() < reqSlv) {
                            chr.getOffenseManager().addOffense(String.format("Character %d tried to add a guild skill without having the required "
                                            + "skill first (req id %d, needed %d, has %d).",
                                    chr.getId(), reqSkillID, reqSlv, gs == null ? 0 : gs.getLevel()));
                            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                            return;
                        }
                    }
                    GuildSkill skill = guild.getSkillById(skillID);
                    if (skill == null) {
                        skill = new GuildSkill();
                        skill.setBuyCharacterName(chr.getName());
                        skill.setExtendCharacterName(chr.getName());
                        skill.setSkillID(skillID);
                        guild.addGuildSkill(skill);
                    }
                    if (skill.getLevel() >= si.getMaxLevel()) {
                        chr.chatMessage("That skill is already at its max level.");
                        chr.dispose();
                        return;
                    }
                    skill.setLevel((short) (skill.getLevel() + 1));
                    guild.broadcast(WvsContext.guildResult(GuildResult.setSkill(guild, skill, chr.getId())));
                } else {
                    GuildSkill gs = guild.getSkillById(skillID);
                    if (gs == null || gs.getLevel() == 0) {
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to decrement a guild skill without that skill existing (id %d).",
                                chr.getId(), skillID));
                        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                        return;
                    }
                    if (guild.getGgp() < GameConstants.GGP_FOR_SKILL_RESET) {
                        chr.getOffenseManager().addOffense(String.format("Character %d tried to decrement a guild skill without having enough GGP (needed %d, has %d).",
                                chr.getId(), GameConstants.GGP_FOR_SKILL_RESET, guild.getGgp()));
                        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                        return;
                    }
                    guild.setGgp(guild.getGgp() - GameConstants.GGP_FOR_SKILL_RESET);
                    gs.setLevel((short) (gs.getLevel() - 1));
                    guild.broadcast(WvsContext.guildResult(GuildResult.setSkill(guild, gs, chr.getId())));
                    guild.broadcast(WvsContext.guildResult(GuildResult.setGgp(guild)));
                }
                break;
            case Req_BattleSkillOpen:
                if (guild == null) {
                    return;
                }
                chr.write(WvsContext.guildResult(GuildResult.battleSkillOpen(guild)));
                break;
            case Req_UseActiveSkill:
                skillID = inPacket.decodeInt();
                // TODO Remove igp
                long usabletime = chr.getSkillCoolTimes().getOrDefault(skillID, Long.MIN_VALUE);
                if (usabletime > System.currentTimeMillis() && !chr.hasSkillCDBypass()) {
                    chr.chatMessage("That skill is still on cooldown.");
                    return;
                }
                GuildSkill gs = guild.getSkillById(skillID);
                if (gs == null || gs.getLevel() == 0) {
                    chr.getOffenseManager().addOffense(String.format("Character %d tried to use a guild skill without having it (id %d).",
                            chr.getId(), skillID));
                    chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_SetSkill_LevelSet_Unknown)));
                    return;
                }
                SkillInfo si = SkillData.getSkillInfoById(skillID);
                chr.getSkillCoolTimes().put(skillID, System.currentTimeMillis() + 1000 * si.getValue(SkillStat.cooltime, gs.getLevel()));
                chr.getJobHandler().handleJoblessBuff(c, inPacket, skillID, (byte) gs.getLevel());
                break;
            case Req_Search:
                byte generalSearch = inPacket.decodeByte();
                Collection<Guild> guildCol;
                if (generalSearch == 1) {
                    int levMin = inPacket.decodeUByte();
                    int levMax = inPacket.decodeUByte();
                    int sizeMin = inPacket.decodeUByte();
                    int sizeMax = inPacket.decodeUByte();
                    int avgLevMin = inPacket.decodeUByte();
                    int avgLevMax = inPacket.decodeUByte();
                    guildCol = world.getGuildsWithCriteria(levMin, levMax, sizeMin, sizeMax, avgLevMin, avgLevMax);
                } else {
                    int searchType = inPacket.decodeShort();
                    boolean exactWord = inPacket.decodeByte() != 0;
                    String searchTerm = inPacket.decodeString();
                    if (searchTerm.equals("Type guild or master's name.")) {
                        // if empty string, client sends this
                        searchTerm = "";
                    }
                    guildCol = world.getGuildsByString(searchType, exactWord, searchTerm);
                }
                chr.write(WvsContext.guildSearchResult(guildCol));
                break;
            default:
                log.error(String.format("Unhandled guild request %s", grt.toString()));
                break;

        }
    }

    @Handler(op = InHeader.GUILD_JOIN_REQUEST)
    public static void handleGuildJoinRequest(Char chr, InPacket inPacket) {
        if (chr.getGuild() != null) {
            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_AlreadyJoined)));
            return;
        }
        int guildId = inPacket.decodeInt();
        Guild guild = chr.getWorld().getGuildByID(guildId);
        if (guild == null) {
            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_Unknown)));
            return;
        }
        if (!guild.isAppliable() || guild.isFull()) {
            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_AlreadyFull)));
            return;
        }
        guild.addGuildRequestor(chr);
        guild.broadcast(WvsContext.guildResult(GuildResult.loadGuild(guild)));
    }


    @Handler(op = InHeader.GUILD_JOIN_ACCEPT)
    public static void handleGuildJoinAccept(Char chr, InPacket inPacket) {
        Guild guild = chr.getGuild();
        World world = chr.getWorld();
        if (guild == null || !guild.canAcceptMember(chr)) {
            chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_JoinGuild_NonRequestFindUser)));
            return;
        }
        byte size = inPacket.decodeByte();
        Set<Char> onlineChars = new HashSet<>();
        Set<Char> offlineChars = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int charId = inPacket.decodeInt();
            GuildRequestor gr = guild.getRequestorById(charId);
            if (gr == null) {
                chr.chatMessage("%s no longer requests to join your guild.");
                continue;
            }
            Char toJoin = world.getCharByID(charId);
            if (toJoin != null) {
                if (toJoin.getGuild() != null) {
                    chr.chatMessage("%s is already in a guild.", gr.getName());
                } else {
                    onlineChars.add(toJoin);
                }
            } else {
                toJoin = Char.getFromDBById(charId);
                if (toJoin != null) {
                    if (toJoin.getGuild() != null) {
                        chr.chatMessage("%s is already in a guild.", gr.getName());
                    } else {
                        offlineChars.add(toJoin);
                    }
                } else {
                    chr.chatMessage("Character %s could not be found, their character is probably gone.", gr.getName());
                }
            }
            guild.removeGuildRequestor(gr);
        }
        for (Char c : onlineChars) {
            if (guild.isFull()) {
                chr.chatMessage("%s was not added, as your guild is full.", c.getName());
                continue;
            }
            Field field = c.getField();
            guild.addMember(c);
            chr.write(WvsContext.guildResult(GuildResult.loadGuild(guild)));
            field.broadcastPacket(UserRemote.guildNameChanged(chr));
            field.broadcastPacket(UserRemote.guildMarkChanged(chr));
        }
        for (Char c : offlineChars) {
            if (guild.isFull()) {
                chr.chatMessage("%s was not added, as your guild is full.", c.getName());
                continue;
            }
            guild.addMember(c, false);
            c.setGuild(guild);
            DatabaseManager.saveToDB(c);
        }
    }

    @Handler(op = InHeader.ALLIANCE_REQUEST)
    public static void handleAllianceRequest(Char chr, InPacket inPacket) {
        byte type = inPacket.decodeByte();
        AllianceType at = AllianceType.getByVal(type);
        if (at == null) {
            log.error(String.format("Unknown alliance request %d", type));
            return;
        }
        Guild guild = chr.getGuild();
        Alliance alliance = guild == null ? null : guild.getAlliance();
        Char other;
        Guild otherGuild;
        World world = chr.getClient().getWorld();
        GuildMember member = guild == null ? null : chr.getGuild().getMemberByCharID(chr.getId());
        GuildMember otherMember;
        if (!chr.isGuildMaster()) {
            return;
        }
        switch (at) {
            case Req_Withdraw:
                if (member.getAllianceGrade() == 1) {
                    if (alliance.getGuilds().size() <= 1) {
                        alliance.broadcast(WvsContext.allianceResult(AllianceResult.withdraw(alliance, guild, false)));
                        alliance.removeGuild(guild);
                        alliance.disband();
                        world.removeAlliance(alliance);
                    } else {
                        Set<Guild> remainingGuilds = new HashSet<>(alliance.getGuilds());
                        remainingGuilds.remove(guild);
                        Guild newLeadingGuild = Util.getRandomFromCollection(remainingGuilds);
                        otherMember = newLeadingGuild.getGuildLeader();
                        otherMember.setAllianceGrade(1);
                        alliance.broadcast(WvsContext.allianceResult(AllianceResult.changeMaster(alliance, member, otherMember)));
                        alliance.broadcast(WvsContext.allianceResult(AllianceResult.withdraw(alliance, guild, false)));
                        alliance.removeGuild(guild);
                    }
                } else {
                    alliance.broadcast(WvsContext.allianceResult(AllianceResult.withdraw(alliance, guild, false)));
                    alliance.removeGuild(guild);
                }
                break;
            case Req_Invite:
                String guildName = inPacket.decodeString();
                otherGuild = world.getGuildByName(guildName);
                if (otherGuild != null) {
                    other = world.getCharByID(otherGuild.getLeaderID());
                    if (other != null) {
                        if (other.getGuild().getAlliance() == null) {
                            other.write(WvsContext.allianceResult(AllianceResult.inviteGuild(alliance, member)));
                            alliance.addPendingGuild(guild);
                        } else {
                            other.write(WvsContext.allianceResult(AllianceResult.msg(AllianceType.Res_InviteGuild_AlreadyInvited)));
                        }
                    } else {
                        chr.write(WvsContext.allianceResult(AllianceResult.msg(AllianceType.Res_Invite_Failed)));
                    }
                } else {
                    chr.write(WvsContext.allianceResult(AllianceResult.msg(AllianceType.Res_Invite_Failed)));
                }
                break;
            case Req_Join:
                int allianceId = inPacket.decodeInt();
                alliance = world.getAlliance(allianceId);
                if (alliance == null || alliance.isFull() || !alliance.hasPendingGuildInvite(guild.getId())) {
                    chr.write(WvsContext.allianceResult(AllianceResult.msg(AllianceType.Res_Invite_Failed)));
                    return;
                }
                alliance.addGuild(guild);
                break;
            case Req_Load:
                chr.write(WvsContext.allianceResult(AllianceResult.loadDone(alliance)));
                chr.write(WvsContext.allianceResult(AllianceResult.loadGuildDone(alliance)));
                break;
            case Req_ChangeMaster:
                other = world.getCharByID(inPacket.decodeInt());
                if (other != null) {
                    otherMember = other.getGuild().getMemberByCharID(other.getId());
                    member.setAllianceGrade(Guild.JR_MASTER);
                    otherMember.setAllianceGrade(Guild.MASTER);
                    alliance.broadcast(WvsContext.allianceResult(AllianceResult.changeMaster(alliance, member, otherMember)));
                    alliance.updateToMembers();
                } else {
                    chr.chatMessage("That character is not online.");
                }
                break;
            case Req_Kick:
                int otherID = inPacket.decodeInt();
                int kickedGuildID = inPacket.decodeInt();
                otherGuild = alliance.getGuildByID(kickedGuildID);
                if (otherGuild != null) {
                    alliance.broadcast(WvsContext.allianceResult(AllianceResult.withdraw(alliance, otherGuild, true)));
                    alliance.removeGuild(otherGuild);
                    alliance.updateToMembers();
                }
                break;
            case Req_SetGradeName:
                for (int i = 0; i < 5; i++) {
                    String gradeName = inPacket.decodeString();
                    if (gradeName.length() >= 4 && gradeName.length() <= 10) {
                        alliance.getGradeNames().set(i, gradeName);
                    }
                }
                alliance.broadcast(WvsContext.allianceResult(AllianceResult.setGradeName(alliance)));
                break;
            default:
                log.error(String.format("Unhandled alliance request type %s", at));
        }
    }

    @Handler(op = InHeader.GUILD_BBS)
    public static void handleGuildBBS(Char chr, InPacket inPacket) {
        Guild guild = chr.getGuild();
        if (guild == null) {
            return;
        }
        byte val = inPacket.decodeByte();
        GuildBBSType type = GuildBBSType.getByValue(val);
        if (type == null) {
            log.error(String.format("Unknown guild bbs type %s", val));
            return;
        }
        switch (type) {
            case Req_CreateRecord:
                boolean edit = inPacket.decodeByte() != 0;
                BBSRecord record = null;
                if (edit) {
                    int id = inPacket.decodeInt();
                    record = guild.getRecordByID(id);
                    if (record == null || record.getCreatorID() != chr.getId()) {
                        chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Invalid BBS record.")));
                        chr.dispose();
                        return;
                    }
                }
                boolean notice = inPacket.decodeByte() != 0;
                String subject = inPacket.decodeString();
                String msg = inPacket.decodeString();
                int icon = inPacket.decodeInt();
                if (edit) {
                    record.setSubject(subject);
                    record.setMsg(msg);
                    record.setIcon(icon);
                } else {
                    record = new BBSRecord(chr.getId(), subject, msg, FileTime.currentTime(), icon);
                }
                if (notice) {
                    guild.setBbsNotice(record);
                } else if (!edit) {
                    guild.addBbsRecord(record);
                }
                chr.write(WvsContext.guildBBSResult(GuildBBSPacket.loadRecord(record)));
                break;
            case Req_DeleteRecord:
                int id = inPacket.decodeInt();
                record = guild.getRecordByID(id);
                if (record != null && chr.getId() == record.getCreatorID()) {
                    guild.removeRecord(record);
                } else {
                    chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Invalid BBS record.")));
                    chr.dispose();
                }
                break;
            case Req_LoadPages:
                int page = inPacket.decodeInt();
                List<BBSRecord> records = guild.getBbsRecords()
                        .stream()
                        .sorted(Comparator.comparingInt(BBSRecord::getIdForBbs))
                        .collect(Collectors.toList());
                final int PAGE_SIZE = GameConstants.GUILD_BBS_RECORDS_PER_PAGE;
                if (page != 0 && page * PAGE_SIZE >= records.size()) {
                    chr.chatMessage("No more BBS records to show.");
                    return;
                }
                // select all records that are on the requested page
                int start = page * PAGE_SIZE;
                int end = start + PAGE_SIZE >= records.size() ? records.size() : start + PAGE_SIZE;
                List<BBSRecord> pageRecords = records.subList(start, end);
                chr.write(WvsContext.guildBBSResult(GuildBBSPacket.loadPages(guild.getBbsNotice(), records.size(), pageRecords)));
                break;
            case Req_LoadRecord:
                id = inPacket.decodeInt();
                record = guild.getRecordByID(id);
                chr.write(WvsContext.guildBBSResult(GuildBBSPacket.loadRecord(record)));
                break;
            case Req_CreateReply:
                id = inPacket.decodeInt();
                msg = inPacket.decodeString();
                record = guild.getRecordByID(id);
                if (record != null) {
                    BBSReply reply = new BBSReply(chr.getId(), FileTime.currentTime(), msg);
                    record.addReply(reply);
                }
                chr.write(WvsContext.guildBBSResult(GuildBBSPacket.loadRecord(record)));
                break;
            case Req_DeleteReply:
                id = inPacket.decodeInt();
                int replyId = inPacket.decodeInt();
                record = guild.getRecordByID(id);
                if (record != null) {
                    BBSReply reply = record.getReplyById(replyId);
                    record.removeReply(reply);
                }
                chr.write(WvsContext.guildBBSResult(GuildBBSPacket.loadRecord(record)));
            default:
                log.error(String.format("Unhandled guild bbs type %s", type));

        }
    }
}
