package net.swordie.ms.client.character.skills.info;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

import java.util.ArrayList;
import java.util.List;

public class SecondAtomInfo {
    private int key;
    private int objectID;
    private int ownerID;
    private int firstImpact;
    private int secondImpact;
    private int skillID;
    private int startDelay;
    private int createTime;
    private int maxHitCount;
    private int dataIndex;
    private int targetID;
    private Position startPosition;
    private int disappearDelay;
    private List<SecondAtomInfo> faiList;

    public SecondAtomInfo() {
    }

    public SecondAtomInfo(int key, int objectID, int ownerID, int createDelay, int enableDelay, int maxHitCount,
                          int dataIndex, Position startPosition, int targetID) {
        this.key = key;
        this.objectID = objectID;
        this.ownerID = ownerID;
        this.startDelay = createDelay;
        this.createTime = enableDelay;
        this.maxHitCount = maxHitCount;
        this.dataIndex = dataIndex;
        this.startPosition = startPosition;
        this.targetID = targetID;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getObjectID());
        outPacket.encodeInt(getDataIndex()); //DataIndex || 1-6 Swords that chill | 7 Actual Sword | 8 V Job Sword
        outPacket.encodeInt(getKey()); //Key
        outPacket.encodeInt(getOwnerID());
        outPacket.encodeInt(getTargetID()); //Target

        outPacket.encodeInt(0); //Create
        outPacket.encodeInt(600); //Enable
        outPacket.encodeInt(0); //Rotate
        outPacket.encodeInt(getSkillID()); //skillID //151001001
        outPacket.encodeInt(10); //Slv

        outPacket.encodeInt(20000); //Expire
        outPacket.encodeInt(0); //CustomRotate
        outPacket.encodeInt(1); //AttAcount

        outPacket.encodePositionInt(getStartPosition());

        outPacket.encodeByte(false);

        outPacket.encodeByte(0);

        int size = 0;
        outPacket.encodeInt(size);
        for (int z = 0; z < size; z++) {
            outPacket.encodeInt(0);
        }
    }

    public List<SecondAtomInfo> getFaiList() {
        return faiList;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownid) {
        this.ownerID = ownid;
    }

    public void setObjectID(int oid) {
        this.objectID = oid;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setSkillID(int sid) { this.skillID = sid; }

    public int getSkillID() {return skillID; }

    public void setTargetID(int tid) { this.targetID = tid; }

    public int getTargetID() { return targetID; }

    public void setDataIndex(int dix) {this.dataIndex = dix; }

    public int getDataIndex() { return dataIndex; }


    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public int getDisappearDelay() {
        return disappearDelay;
    }

    public void setDisappearDelay(int disappearDelay) {
        this.disappearDelay = disappearDelay;
    }

    public List<Integer> getKeys() {
        return new ArrayList<Integer>() {{
            getFaiList().forEach(fai -> add(fai.getKey()));
        }};
    }

    public SecondAtomInfo getSecondFaiByKey(int faKey) {
        return getFaiList().stream().filter(fai -> fai.getKey() == faKey).findFirst().orElse(null);
    }

    public SecondAtomInfo getSecondFaiByObjectID(int oid) {
        return getFaiList().stream().filter(fai -> fai.getObjectID() == oid).findFirst().orElse(null);
    }

}

