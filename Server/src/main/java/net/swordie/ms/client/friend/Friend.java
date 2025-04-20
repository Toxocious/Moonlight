package net.swordie.ms.client.friend;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;

/**
 * Created on 3/31/2018.
 */
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int friendID;
    private String name;
    private byte flag; // 5 through 8 = account friend
    @Transient
    private int channelID;
    @Column(name = "groupName")
    private String group;
    private byte mobile;
    private int friendAccountID;
    private String nickname;
    private String memo;
    @Transient
    private boolean inShop;
    @Transient
    private Char chr;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getFriendID());
        outPacket.encodeString(getName(), 13);
        outPacket.encodeByte(getFlag());
        outPacket.encodeInt(getChannelID());
        outPacket.encodeString(getGroup(), 17);
        outPacket.encodeByte(getMobile());
        outPacket.encodeInt(getFriendAccountID());
        outPacket.encodeString(getNickname(), 13);
        outPacket.encodeString(getMemo(), 256);
        outPacket.encodeInt(isInShop() ? 1 : 0);
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public void setFlag(FriendFlag flag) {
        this.flag = (byte) flag.getVal();
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public byte getMobile() {
        return mobile;
    }

    public void setMobile(byte mobile) {
        this.mobile = mobile;
    }

    public int getFriendAccountID() {
        return friendAccountID;
    }

    public void setFriendAccountID(int friendAccountID) {
        this.friendAccountID = friendAccountID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isInShop() {
        return inShop;
    }

    public void setInShop(boolean inShop) {
        this.inShop = inShop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccount() {
        return getFlag() > 4;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public void write(OutPacket outPacket) {
        if (isOnline()) {
            chr.write(outPacket);
        }
    }

    public boolean isOnline() {
        return getChr() != null;
    }
}
