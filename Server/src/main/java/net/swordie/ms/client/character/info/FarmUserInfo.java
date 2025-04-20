package net.swordie.ms.client.character.info;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/20/2017.
 */
public class FarmUserInfo {
    private String farmName = "v214";
    private int farmPoint;
    private int farmLevel;
    private int farmExp;
    private int decoPoint;
    private int farmCash;
    private byte farmGender;
    private int farmTheme;
    private int slotExtend;
    private int lockerSlotCount;

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public int getFarmPoint() {
        return farmPoint;
    }

    public void setFarmPoint(int farmPoint) {
        this.farmPoint = farmPoint;
    }

    public int getFarmLevel() {
        return farmLevel;
    }

    public void setFarmLevel(int farmLevel) {
        this.farmLevel = farmLevel;
    }

    public int getFarmExp() {
        return farmExp;
    }

    public void setFarmExp(int farmExp) {
        this.farmExp = farmExp;
    }

    public int getDecoPoint() {
        return decoPoint;
    }

    public void setDecoPoint(int decoPoint) {
        this.decoPoint = decoPoint;
    }

    public int getFarmCash() {
        return farmCash;
    }

    public void setFarmCash(int farmCash) {
        this.farmCash = farmCash;
    }

    public byte getFarmGender() {
        return farmGender;
    }

    public void setFarmGender(byte farmGender) {
        this.farmGender = farmGender;
    }

    public int getFarmTheme() {
        return farmTheme;
    }

    public void setFarmTheme(int farmTheme) {
        this.farmTheme = farmTheme;
    }

    public int getSlotExtend() {
        return slotExtend;
    }

    public void setSlotExtend(int slotExtend) {
        this.slotExtend = slotExtend;
    }

    public int getLockerSlotCount() {
        return lockerSlotCount;
    }

    public void setLockerSlotCount(int lockerSlotCount) {
        this.lockerSlotCount = lockerSlotCount;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getFarmName());
        outPacket.encodeInt(getFarmPoint());
        outPacket.encodeInt(69);
        outPacket.encodeInt(getFarmExp());
        outPacket.encodeInt(getDecoPoint());
        outPacket.encodeInt(getFarmCash());
        outPacket.encodeInt(getFarmGender());
        outPacket.encodeInt(getFarmTheme());
        outPacket.encodeInt(getSlotExtend());
        outPacket.encodeInt(getLockerSlotCount());
    }
}
