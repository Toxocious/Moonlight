package net.swordie.ms.client.guild.bbs;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 8/12/2018.
 */
@Entity
@Table(name = "bbs_records")
public class BBSRecord implements Comparable<BBSRecord> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idForBbs; // just the number in the list
    private int creatorID;
    private String subject;
    private String msg;
    @Convert(converter = FileTimeConverter.class)
    private FileTime creationDate;
    private int icon;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recordid")
    private List<BBSReply> replies = new ArrayList<>();

    public BBSRecord() {
    }

    public BBSRecord(int creatorID, String subject, String msg, FileTime creationDate, int icon) {
        this.creatorID = creatorID;
        this.subject = subject;
        this.msg = msg;
        this.creationDate = creationDate;
        this.icon = icon;
    }

    public void encodeForPagesLoad(OutPacket outPacket) {
        outPacket.encodeInt(getIdForBbs());
        outPacket.encodeInt(getCreatorID());
        outPacket.encodeString(getSubject());
        outPacket.encodeFT(getCreationDate());
        outPacket.encodeInt(getIcon());
        outPacket.encodeInt(getReplies().size());
    }

    public void encodeForRecordLoad(OutPacket outPacket) {
        outPacket.encodeInt(getIdForBbs());
        outPacket.encodeInt(getCreatorID());
        outPacket.encodeFT(getCreationDate());
        outPacket.encodeString(getSubject());
        outPacket.encodeString(getMsg());
        outPacket.encodeInt(getIcon());
        outPacket.encodeInt(getReplies().size());
        for (BBSReply reply : getReplies()) {
            outPacket.encode(reply);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public FileTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(FileTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<BBSReply> getReplies() {
        return replies;
    }

    public void setReplies(List<BBSReply> replies) {
        this.replies = replies;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdForBbs() {
        return idForBbs;
    }

    public void setIdForBbs(int idForBbs) {
        this.idForBbs = idForBbs;
    }

    public void addReply(BBSReply reply) {
        getReplies().add(reply);
        reply.setIdForReply(getReplies().size());
    }

    public void removeReply(BBSReply reply) {
        getReplies().remove(reply);
        int i = 1;
        for (BBSReply r : getReplies()) {
            r.setIdForReply(i++);
        }
    }

    public BBSReply getReplyById(int id) {
        return getReplies().stream().filter(reply -> reply.getIdForReply() == id).findAny().orElse(null);
    }


    @Override
    public int compareTo(BBSRecord o) {
        return getCreationDate().toLocalDateTime().compareTo(o.getCreationDate().toLocalDateTime());
    }
}
