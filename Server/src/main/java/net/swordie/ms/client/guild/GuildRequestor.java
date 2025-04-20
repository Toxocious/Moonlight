package net.swordie.ms.client.guild;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created on 3/21/2018.
 */
@Entity
@Table(name = "guildrequestors")
public class GuildRequestor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int charID;
    @Transient
    private Char chr;
    private String name;
    private int job;
    private int level;
    @Column(name = "loggedIn")
    private boolean online;

    public GuildRequestor() {
    }

    public GuildRequestor(Char chr) {
        this.chr = chr;
        updateInfoFromChar(chr);
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

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getName(), 13);
        outPacket.encodeInt(getJob());
        outPacket.encodeInt(getLevel());
        outPacket.encodeInt(0);
        outPacket.encodeInt(isOnline() ? 1 : 0);
        // Following is guild specific info, requestors don't have these
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeLong(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuildRequestor that = (GuildRequestor) o;
        return charID == that.charID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(charID);
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

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }
}
