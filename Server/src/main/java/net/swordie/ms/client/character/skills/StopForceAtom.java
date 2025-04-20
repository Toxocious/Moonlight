package net.swordie.ms.client.character.skills;

import net.swordie.ms.connection.OutPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/3/2018.
 */
public class StopForceAtom {
    private int idx;
    private int count;
    private int weaponId;
    private List<Integer> angleInfo = new ArrayList<>();

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getIdx());
        outPacket.encodeInt(getCount());
        outPacket.encodeInt(getWeaponId());
        outPacket.encodeInt(getAngleInfo().size());
        for(int i : getAngleInfo()) {
            outPacket.encodeInt(i);
        }
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public List<Integer> getAngleInfo() {
        return angleInfo;
    }

    public void setAngleInfo(List<Integer> angleInfo) {
        this.angleInfo = angleInfo;
    }
}
