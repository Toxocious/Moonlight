package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/19/2017.
 */
public class MarriageRecord {
    private int marriageNo;
    private int groomId;
    private int brideId;
    private short status;
    private int groomItemId;
    private int brideItemId;
    private String groomName;
    private String bridgeName;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getMarriageNo());
        outPacket.encodeInt(getGroomId());
        outPacket.encodeInt(getBrideId());
        outPacket.encodeShort(getStatus());
        outPacket.encodeInt(getGroomItemId());
        outPacket.encodeInt(getBrideItemId());
        outPacket.encodeString(getGroomName(), 13); //max length 13
        outPacket.encodeString(getBridgeName(), 13);
    }

    public int getMarriageNo() {
        return marriageNo;
    }

    public void setMarriageNo(int marriageNo) {
        this.marriageNo = marriageNo;
    }

    public int getGroomId() {
        return groomId;
    }

    public void setGroomId(int groomId) {
        this.groomId = groomId;
    }

    public int getBrideId() {
        return brideId;
    }

    public void setBrideId(int brideId) {
        this.brideId = brideId;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getGroomItemId() {
        return groomItemId;
    }

    public void setGroomItemId(int groomItemId) {
        this.groomItemId = groomItemId;
    }

    public int getBrideItemId() {
        return brideItemId;
    }

    public void setBrideItemId(int brideItemId) {
        this.brideItemId = brideItemId;
    }

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getBridgeName() {
        return bridgeName;
    }

    public void setBridgeName(String bridgeName) {
        this.bridgeName = bridgeName;
    }

    public void encodeForRemote(OutPacket outPacket) {
        // TODO make it so this works for a single player (groom/bride should be turned around 50% of the time)
        outPacket.encodeInt(getGroomId());
        outPacket.encodeInt(getBrideId());
        outPacket.encodeInt(getGroomItemId());
    }
}
