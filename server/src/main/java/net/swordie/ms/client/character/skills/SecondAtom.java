package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.skills.info.SecondAtomInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

import java.util.ArrayList;
import java.util.List;

public class SecondAtom {
    private int charId;
    private int objectID;
    private int targetID;
    private int rotate;
    private int skillId;
    private int skilllv;
    private int dataIndex;
    private int key;
    private int customrotate;
    private int maxAtt;
    private int createDel;
    private int enableDel;
    private int expire;
    private boolean specSummon;
    private Position position;
    private List<SecondAtomInfo> keyList;
    private List<Integer> custom;

    public SecondAtom(int objectID, int charId, int targetID,int rotate, int skillId, int skilllv,
                      Position position, int dataIndex, int key, int customrotate, int maxAttCount, int createDelay, int enableDelay, int expire, boolean specSummon, List<Integer> custom) {
        this.objectID = objectID;
        this.charId = charId;
        this.targetID = targetID;
        this.rotate = rotate;
        this.skillId = skillId;
        this.skilllv = skilllv;
        this.position = position;
        this.dataIndex = dataIndex;
        this.key = key;
        this.customrotate = customrotate;
        this.maxAtt = maxAttCount;
        this.createDel = createDelay;
        this.enableDel = enableDelay;
        this.expire = expire;
        this.specSummon = specSummon;
        this.custom = custom;
    }

    public SecondAtom(SecondAtom fa) {
        this.objectID = fa.getObjectID();
        this.charId = fa.getCharId();
        this.targetID = fa.getTargetID();
        this.rotate = fa.getRotate();
        this.skillId = fa.getSkillId();
        this.skilllv = skilllv;
        this.position = fa.getPosition();
        this.dataIndex = fa.getDataIndex();
        this.key = fa.getKey();
        this.customrotate = fa.getCustomRotate();
        this.maxAtt = fa.getMaxAtt();
        this.createDel = fa.getCreateDel();
        this.enableDel = fa.getEnableDel();
        this.expire = fa.getExpire();
        this.specSummon = fa.isSpecialSummon();
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public int getObjectID() { return objectID; }

    public void setObjectID(int oid) { this.objectID = oid; }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }


    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getSkilllv() {
        return skilllv;
    }

    public void setSkilllv(int lv) {
        this.skilllv = lv;
    }

    public int getKey() { return key; }

    public void setKey(int key) { this.key = key; }

    public Position getPosition() {
        return position;
    }

    public boolean isSpecialSummon() {
        return specSummon;
    }

    public void setSpecialSummon(boolean enable) {
        this.specSummon = enable;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDataIndex() {
        return dataIndex;
    }

    public void setTargetID(int tid) { this.targetID = tid; }

    public int getTargetID() { return targetID; }

    public void setDataIndex(int dix) {
        this.dataIndex = dix;
    }

    public int getCustomRotate() {
        return customrotate;
    }

    public void setCustomrotate(int customrotate) {
        this.customrotate = customrotate;
    }

    public void setMaxAtt(int max) { this.maxAtt = max; }

    public int getMaxAtt() { return maxAtt; }

    public void setCreateDel(int cdl) { this.createDel = cdl; }

    public int getCreateDel() { return createDel; }

    public void setEnableDel(int edl) { this.enableDel = edl; }

    public int getEnableDel() { return enableDel; }

    public void setExpire(int exp) { this.expire = exp; }

    public int getExpire() { return expire; }

    public List<SecondAtomInfo> getFaiList() {
        return keyList;
    }

    public void setSFaiList(List<SecondAtomInfo> faiList) {
        this.keyList = faiList;
    }

    public List<Integer> getCustom() {
        return custom;
    }

    public void setCustom(List<Integer> cus) {
        this.custom = cus;
    }

    public List<Integer> getKeys() {
        return new ArrayList<Integer>() {{
            getFaiList().forEach(fai -> add(fai.getKey()));
        }};
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getObjectID());
        outPacket.encodeInt(getDataIndex()); //DataIndex || 1-6 Swords that chill | 7 Actual Sword | 8 V Job Sword
        outPacket.encodeInt(getKey()); //Key
        outPacket.encodeInt(getCharId());
        outPacket.encodeInt(getTargetID()); //Target

        outPacket.encodeInt(getCreateDel()); //Create
        outPacket.encodeInt(getEnableDel()); //Enable
        outPacket.encodeInt(0); //Rotate
        outPacket.encodeInt(getSkillId()); //skillID //151001001
        outPacket.encodeInt(10); //Slv

        outPacket.encodeInt(getExpire()); //Expire
        outPacket.encodeInt(0); //CustomRotate
        outPacket.encodeInt(getMaxAtt()); //AttAcount

        outPacket.encodePositionInt(getPosition());

        outPacket.encodeByte(false);

        outPacket.encodeByte(0);

        int size = 0;
        outPacket.encodeInt(size);
        for (int z = 0; z < size; z++) {
            outPacket.encodeInt(0);
        }
    }
}

