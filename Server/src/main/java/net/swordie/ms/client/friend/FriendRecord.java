package net.swordie.ms.client.friend;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/19/2017.
 */
public class FriendRecord {
    private int pairCharacterId;
    private String pairCharacterName;
    private long sn;
    private long pairSn;
    private int friendItemId;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getPairCharacterId());
        outPacket.encodeString(getPairCharacterName(), 13);
        outPacket.encodeLong(getSn());
        outPacket.encodeLong(getPairSn());
        outPacket.encodeInt(getFriendItemId());
    }

    public int getPairCharacterId() {
        return pairCharacterId;
    }

    public void setPairCharacterId(int pairCharacterId) {
        this.pairCharacterId = pairCharacterId;
    }

    public String getPairCharacterName() {
        return pairCharacterName;
    }

    public void setPairCharacterName(String pairCharacterName) {
        this.pairCharacterName = pairCharacterName;
    }

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }

    public long getPairSn() {
        return pairSn;
    }

    public void setPairSn(long pairSn) {
        this.pairSn = pairSn;
    }

    public int getFriendItemId() {
        return friendItemId;
    }

    public void setFriendItemId(int friendItemId) {
        this.friendItemId = friendItemId;
    }
}
