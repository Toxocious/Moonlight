package net.swordie.ms.client.guild.bbs;

import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 8/12/2018.
 */
@Entity
@Table(name = "bbs_replies")
public class BBSReply implements Encodable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idForReply;
    private int creatorID;
    @Convert(converter = FileTimeConverter.class)
    private FileTime creationDate;
    private String msg;

    public BBSReply() {
    }

    public BBSReply(int creatorID, FileTime creationDate, String msg) {
        this.creatorID = creatorID;
        this.creationDate = creationDate;
        this.msg = msg;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getId());
        outPacket.encodeInt(getCreatorID());
        outPacket.encodeFT(getCreationDate());
        outPacket.encodeString(getMsg());
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

    public FileTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(FileTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdForReply() {
        return idForReply;
    }

    public void setIdForReply(int idForReply) {
        this.idForReply = idForReply;
    }
}
