package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 12/19/2017.
 */
public class CoupleRecord {
    private Map<Long, Integer> snToItemMap = new HashMap<>();
    private int pairCharacterId;
    private String pairCharacterName;
    private long sn;
    private long pairSn;
    private int itemID;

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

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(0xdeadbeef);
        outPacket.encodeString(getPairCharacterName(), 13);
        outPacket.encodeLong(0xdeadbeef);
        outPacket.encodeLong(0xdeadbeef);
    }

    public void encodeForRemote(OutPacket outPacket) {
        outPacket.encodeInt(1); // there can be more of the following 3 things (combined)
        outPacket.encodeLong(getSn());
        outPacket.encodeLong(getPairSn());
        outPacket.encodeInt(getItemID());
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
