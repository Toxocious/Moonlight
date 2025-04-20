package net.swordie.ms.client.guild;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.MessageType;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

@Entity
@Table(name = "guildmembers")
public class GuildMember implements Encodable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int charID;
    @Transient
    private Char chr;
    private int grade;
    private int allianceGrade;
    private int commitment;
    private int dayCommitment;
    private int igp;
    @Convert(converter = FileTimeConverter.class)
    private FileTime commitmentIncTime;
    private String name;
    private int job;
    private int level;
    @Column(name = "loggedIn")
    private boolean online;

    public GuildMember() {
    }

    public GuildMember(Char chr, boolean online) {
        this.chr = chr;
        updateInfoFromChar(chr);
        grade = 5;
        allianceGrade = 5;
        if (!online) {
            this.chr = null; // so they're not mistaken as online players
        }
    }

    public void updateInfoFromChar(Char chr) {
        setName(chr.getName());
        setCharID(chr.getId());
        setJob(chr.getJob());
        setLevel(chr.getLevel());
        setOnline(chr.isOnline());
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAllianceGrade() {
        return allianceGrade;
    }

    public void setAllianceGrade(int allianceGrade) {
        this.allianceGrade = allianceGrade;
    }

    public int getCommitment() {
        return commitment;
    }

    public void setCommitment(int commitment) {
        this.commitment = commitment;
    }

    public int getDayCommitment() {
        return dayCommitment;
    }

    public void setDayCommitment(int dayCommitment) {
        this.dayCommitment = dayCommitment;
    }

    public int getIgp() {
        return igp;
    }

    public void setIgp(int igp) {
        this.igp = igp;
    }

    public FileTime getCommitmentIncTime() {
        return commitmentIncTime;
    }

    public void setCommitmentIncTime(FileTime commitmentIncTime) {
        this.commitmentIncTime = commitmentIncTime;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getName(), 13);
        outPacket.encodeInt(getJob());
        outPacket.encodeInt(getLevel());
        outPacket.encodeInt(getGrade());
        outPacket.encodeInt(isOnline() ? 1 : 0);
        outPacket.encodeInt(getAllianceGrade());
        outPacket.encodeInt(getCommitment());
        outPacket.encodeInt(getDayCommitment());
        outPacket.encodeInt(getIgp());
        outPacket.encodeFT(getCommitmentIncTime());
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
    }

    public int getRemainingDayCommitment() {
        return GameConstants.MAX_DAY_COMMITMENT - getDayCommitment();
    }

    public void addCommitment(int commitment) {
        setCommitment(getCommitment() + commitment);
        setDayCommitment(getDayCommitment() + commitment);
        addIgp((int) (commitment * GameConstants.IGP_PER_CONTRIBUTION));
        setCommitmentIncTime(FileTime.currentTime());
        if (getChr() != null) {
            Guild g = getChr().getGuild();
            g.broadcast(WvsContext.guildResult(GuildResult.setMemberCommitment(g, this)));
            getChr().write(WvsContext.message(MessageType.INC_COMMITMENT_MESSAGE, commitment, "", (byte) 0));
        }

    }

    private void addIgp(int igp) {
        setIgp(getIgp() + igp);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GuildMember && getCharID() == ((GuildMember) obj).getCharID();
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
