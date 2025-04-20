package net.swordie.ms.client.guild;

import net.swordie.ms.Server;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.bbs.BBSRecord;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.guild.result.GuildType;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.World;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 3/18/2018.
 */
@Entity
@Table(name = "guilds")
public class Guild implements Encodable {

    @Transient
    public static final int MASTER = 1, JR_MASTER = 2, VETERAN = 3, MEMBER = 4, NEW = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int leaderID;
    private int worldID;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "guildID")
    private Set<GuildRequestor> requestors = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "gradenames", joinColumns = @JoinColumn(name = "guildID"))
    @Column(name = "gradeName")
    private List<String> gradeNames = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "guildID")
    private List<GuildMember> members = new ArrayList<>();
    private int markBg;
    private int markBgColor;
    private int mark;
    private int markColor;
    private int maxMembers;
    private String notice;
    private int points;
    private int seasonPoints;
    private int allianceID;
    private int level;
    private int rank;
    private int ggp;
    // Start GUILDSETTING struct
    private boolean appliable;
    private int joinSetting;
    private int reqLevel;
    // End GUILDSETTING struct
    private int battleSp;

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @CollectionTable(name = "guildskills")
    @MapKeyColumn(name = "skillID")
    private Map<Integer, GuildSkill> skills = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "guildID")
    private Set<BBSRecord> bbsRecords = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bbsNotice")
    private BBSRecord bbsNotice;
    @Transient
    private Alliance alliance;

    public Guild() {
        setGradeNames(new String[]{"Guild Master", "Junior", "Veteran", "Member", "Newbie"});
        setAppliable(true);
        setMaxMembers(50);
        setLevel(1);
        setName("Default guild");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void encodeForRemote(OutPacket outPacket) {
        outPacket.encodeString(getName());
        outPacket.encodeShort(getMarkBg());
        outPacket.encodeByte(getMarkBgColor());
        outPacket.encodeShort(getMark());
        outPacket.encodeByte(getMarkColor());
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getId());
        outPacket.encodeString(getName());
        for (String str : getGradeNames()) {
            outPacket.encodeString(str); // 5 times total
            outPacket.encodeInt(-1);
        }
        outPacket.encodeShort(getMembers().size());
        getMembers().forEach(gm -> outPacket.encodeInt(gm.getCharID()));
        getMembers().forEach(gm -> gm.encode(outPacket));
        outPacket.encodeShort(getRequestors().size());
        getRequestors().forEach(gm -> outPacket.encodeInt(gm.getCharID()));
        getRequestors().forEach(gm -> gm.encode(outPacket));
        outPacket.encodeInt(getMaxMembers());
        outPacket.encodeShort(getMarkBg());
        outPacket.encodeByte(getMarkBgColor());
        outPacket.encodeShort(getMark());
        outPacket.encodeByte(getMarkColor());
        outPacket.encodeString(getNotice());
        outPacket.encodeInt(getPoints());
        outPacket.encodeInt(getSeasonPoints());
        outPacket.encodeInt(getAllianceID());
        outPacket.encodeByte(getLevel());
        outPacket.encodeInt(getRank());
        outPacket.encodeInt(getGgp());

        //new ?
        outPacket.encodeInt(0); //idk
        outPacket.encodeByte(0);
        outPacket.encodeLong(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);

        outPacket.encodeShort(getSkills().size());
        getSkills().forEach((id, skill) -> {
            outPacket.encodeInt(id);
            outPacket.encode(skill);
        });
        outPacket.encodeByte(isAppliable());
        if (isAppliable()) {
            outPacket.encodeByte(getJoinSetting());
            outPacket.encodeInt(getReqLevel());
        }
        int size = 0;
        outPacket.encodeInt(size);
        if (size > 0) {
            outPacket.encodeByte(0);
        }
        outPacket.encodeInt(0);
    }

    public List<GuildMember> getMembers() {
        return members;
    }

    public void addMember(GuildMember guildMember) {
        getMembers().add(guildMember);
        if (guildMember.getChr() != null && guildMember.getChr().getGuild() == null) {
            guildMember.getChr().setGuild(this);
        }
        if (getLeaderID() == 0) {
            // no one is guild leader
            setLeader(guildMember);
        } else {
            guildMember.setGrade(NEW);
            broadcast(WvsContext.guildResult(GuildResult.joinGuild(this, guildMember)));
        }
    }

    public void demote(GuildMember guildMember) {
        guildMember.setGrade(Math.min(guildMember.getGrade() + 1, getGradeNames().size()));
    }

    public void promote(GuildMember guildMember) {
        guildMember.setGrade(Math.max(guildMember.getGrade() - 1, 1));
    }

    public void addMember(Char chr, boolean online) {
        addMember(new GuildMember(chr, online));
    }

    public void addMember(Char chr) {
        addMember(chr, true);
    }

    public void removeMember(GuildMember guildMember) {
        if (guildMember.getChr() != null) {
            guildMember.getChr().setGuild(null);
        }
        getMembers().remove(guildMember);
    }

    public void removeMember(Char chr) {
        getMembers().remove(getMemberByChar(chr));
    }

    public GuildMember getMemberByChar(Char chr) {
        return getMembers().stream().filter(gm -> gm.getChr().equals(chr)).findAny().orElse(null);
    }

    public int getLeaderID() {
        return leaderID;
    }

    public void setLeaderID(int leaderID) {
        this.leaderID = leaderID;
    }

    public void setLeader(GuildMember leader) {
        int oldGrade = leader.getGrade();
        if (getLeaderID() != 0) {
            getMemberByCharID(getLeaderID()).setGrade(oldGrade);
        }
        this.leaderID = leader.getCharID();
        leader.setGrade(MASTER);
    }

    public boolean isGuildMember(GuildMember gm) {
        return getLeaderID() == gm.getCharID();
    }

    public GuildMember getMemberByCharID(int id) {
        return getMembers().stream().filter(gm -> gm.getCharID() == id).findAny().orElse(null);
    }

    public int getMarkBg() {
        return markBg;
    }

    public void setMarkBg(int markBg) {
        this.markBg = markBg;
    }

    public int getMarkBgColor() {
        return markBgColor;
    }

    public void setMarkBgColor(int markBgColor) {
        this.markBgColor = markBgColor;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getMarkColor() {
        return markColor;
    }

    public void setMarkColor(int markColor) {
        this.markColor = markColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GuildMember> getOnlineMembers() {
        return getMembers().stream().filter(GuildMember::isOnline).collect(Collectors.toList());
    }

    public void broadcast(OutPacket outPacket) {
        getOnlineMembers().stream().filter(gm -> gm.getChr() != null).forEach(gm -> gm.getChr().write(outPacket));
    }

    public void broadcast(OutPacket outPacket, Char exceptChr) {
        getOnlineMembers().stream().filter(gm -> gm.getChr() != null && !gm.getChr().equals(exceptChr))
                .forEach(gm -> gm.getChr().write(outPacket));
    }

    public Set<GuildRequestor> getRequestors() {
        return requestors;
    }

    public void setRequestors(Set<GuildRequestor> requestors) {
        this.requestors = requestors;
    }

    public void addGuildRequestor(Char chr) {
        GuildRequestor guildRequestor = new GuildRequestor(chr);
        getRequestors().add(guildRequestor);
    }

    public void removeGuildRequestor(GuildRequestor gr) {
        getRequestors().remove(gr);
    }

    public List<String> getGradeNames() {
        return gradeNames;
    }

    public void setGradeNames(List<String> gradeNames) {
        this.gradeNames = gradeNames;
    }

    public void setGradeNames(String[] gradeNames) {
        getGradeNames().clear();
        for (String gradeName : gradeNames) {
            getGradeNames().add(gradeName);
        }
    }

    public void setMembers(List<GuildMember> members) {
        this.members = members;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getSeasonPoints() {
        return seasonPoints;
    }

    public void setSeasonPoints(int seasonPoints) {
        this.seasonPoints = seasonPoints;
    }

    public int getAllianceID() {
        return allianceID;
    }

    public void setAllianceID(int allianceID) {
        this.allianceID = allianceID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGgp() {
        return ggp;
    }

    public void setGgp(int ggp) {
        this.ggp = ggp;
    }

    public boolean isAppliable() {
        return appliable;
    }

    public void setAppliable(boolean setState) {
        this.appliable = setState;
    }

    public int getJoinSetting() {
        return joinSetting;
    }

    public void setJoinSetting(int joinSetting) {
        this.joinSetting = joinSetting;
    }

    public int getReqLevel() {
        return reqLevel;
    }

    public void setReqLevel(int reqLevel) {
        this.reqLevel = reqLevel;
    }

    public Map<Integer, GuildSkill> getSkills() {
        return skills;
    }

    public void setSkills(Map<Integer, GuildSkill> skills) {
        this.skills = skills;
    }

    public void addGuildSkill(GuildSkill gs) {
        getSkills().put(gs.getSkillID(), gs);
    }

    public int getWorldID() {
        return worldID;
    }

    public void setWorldID(int worldID) {
        this.worldID = worldID;
    }

    public static void defaultEncodeForRemote(OutPacket outPacket) {
        outPacket.encodeString("");
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
    }

    /**
     * Adds a given amount of commitment to the char.
     *
     * @param chr        the char that the commitment should be given to
     * @param commitment the amount of commitment to add
     */
    public void addCommitmentToChar(Char chr, int commitment) {
        GuildMember gm = getMemberByCharID(chr.getId());
        if (gm != null && gm.getRemainingDayCommitment() > 0) {
            int commitmentInc = gm.getDayCommitment() + commitment > GameConstants.MAX_DAY_COMMITMENT
                    ? GameConstants.MAX_DAY_COMMITMENT - gm.getDayCommitment()
                    : commitment;
            gm.addCommitment(commitmentInc);
            addGgp((int) (commitmentInc * GameConstants.GGP_PER_CONTRIBUTION));
            addPoints(commitment);
        }
    }

    private void addPoints(int commitment) {
        setPoints(getPoints() + commitment);
        if (getLevel() < GameConstants.MAX_GUILD_LV && getPoints() > GameConstants.getExpRequiredForNextGuildLevel(getLevel())) {
            setLevel(getLevel() + 1);
            broadcast(UserLocal.chatMsg(ChatType.Notice2, String.format("%s has reached level %d!",
                    getName(), getLevel())));
        }
        broadcast(WvsContext.guildResult(GuildResult.setPointAndLevel(this)));
    }

    private void addGgp(int ggp) {
        setGgp(getGgp() + ggp);
        broadcast(WvsContext.guildResult(GuildResult.setGgp(this)));
    }

    public Set<BBSRecord> getBbsRecords() {
        return bbsRecords;
    }

    public void setBbsRecords(Set<BBSRecord> bbsRecords) {
        this.bbsRecords = bbsRecords;
    }

    public void addBbsRecord(BBSRecord record) {
        getBbsRecords().add(record);
        record.setIdForBbs(getBbsRecords().size()); // whatevs
    }

    public BBSRecord getRecordByID(int id) {
        BBSRecord record;
        if (id == 0) {
            record = getBbsNotice();
        } else {
            record = getBbsRecords().stream().filter(r -> r.getIdForBbs() == id).findAny().orElse(null);
        }
        return record;
    }

    public void removeRecord(BBSRecord record) {
        getBbsRecords().remove(record);
        if (getBbsNotice() != null && getBbsNotice().getIdForBbs() == record.getIdForBbs()) {
            setBbsNotice(null);
        }
        int i = 1;
        for (BBSRecord r : getBbsRecords().stream().sorted().collect(Collectors.toList())) {
            r.setIdForBbs(i++);
        }
    }

    public BBSRecord getBbsNotice() {
        return bbsNotice;
    }

    public void setBbsNotice(BBSRecord bbsNotice) {
        this.bbsNotice = bbsNotice;
    }

    public int getSpentSp() {
        return getSkills().values().stream().mapToInt(GuildSkill::getLevel).sum();
    }

    public int getSpentBattleSp() {
        int spentSp = 0;
        for (int i = 91001022; i < 91001024; i++) {
            GuildSkill gs = getSkills().getOrDefault(i, null);
            spentSp += gs == null ? 0 : gs.getLevel();
        }
        return spentSp;
    }

    public int getBattleSp() {
        return getLevel() + 4;
    }

    public GuildSkill getSkillById(int skillID) {
        return getSkills().getOrDefault(skillID, null);
    }

    public void setAlliance(Alliance alliance) {
        this.alliance = alliance;
        if (alliance == null) {
            setAllianceID(0);
        } else {
            setAllianceID(alliance.getId());
        }
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public boolean isGuildMaster(Char chr) {
        return getLeaderID() == chr.getId();
    }

    public GuildMember getGuildLeader() {
        return getMemberByCharID(getLeaderID());
    }

    public int getAverageMemberLevel() {
        int size = getMembers().size();
        int averageLevel = 0;
        for (GuildMember gm : getMembers()) {
            averageLevel += gm.getLevel();
        }
        return averageLevel / size;
    }

    public void disband() {
        for (GuildMember gm : getMembers()) {
            Char chr = gm.getChr();
            if (chr != null) {
                chr.setGuild(null);
                chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Res_RemoveRequestGuild_Done)));
                chr.getField().broadcastPacket(UserRemote.guildNameChanged(chr));
                chr.getField().broadcastPacket(UserRemote.guildMarkChanged(chr));
            } else {
                chr = Char.getFromDBById(gm.getCharID());
                chr.setGuild(null);
                DatabaseManager.saveToDB(chr);
            }
        }

        GuildMember leader = getGuildLeader();
        if (leader.getChr() != null) {
            leader.getChr().write(WvsContext.guildResult(GuildResult.removeComplete(this)));
        }
        World world = Server.getInstance().getWorldById(getWorldID());
        if (world != null) {
            world.removeGuild(this);
        }
        if (getAlliance() != null) {
            getAlliance().removeGuild(this);
        }
    }

    public boolean canAcceptMember(Char chr) {
        GuildMember gm = getMemberByChar(chr);
        return gm != null && gm.getGrade() <= JR_MASTER;
    }

    public GuildRequestor getRequestorById(int charId) {
        return Util.findWithPred(getRequestors(), gr -> gr.getCharID() == charId);
    }

    public boolean isFull() {
        return getMembers().size() >= getMaxMembers();
    }

    public void updateToMembers() {
        broadcast(WvsContext.guildResult(GuildResult.loadGuild(this)));
    }
}
