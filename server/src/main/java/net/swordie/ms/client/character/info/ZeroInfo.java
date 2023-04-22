package net.swordie.ms.client.character.info;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ClothingStats;

public class ZeroInfo {
    private boolean isZeroBetaState;
    private int subHP;
    private int subMHP;
    private int subMP;
    private int subMMP;
    private int subSkin;
    private int subHair;
    private int subFace;
    private int dbcharZeroLinkCashPart;
    private int mixBaseHairColor;
    private int mixAddHairColor;
    private int mixHairBaseProb;

    public ZeroInfo deepCopy() {
        ZeroInfo zi = new ZeroInfo();
        zi.setZeroBetaState(isZeroBetaState());
        zi.setSubHP(getSubHP());
        zi.setSubMHP(getSubMHP());
        zi.setSubMP(getSubMP());
        zi.setSubMMP(getSubMMP());
        zi.setSubSkin(getSubSkin());
        zi.setSubHair(getSubHair());
        zi.setSubFace(getSubFace());
        zi.setDbcharZeroLinkCashPart(getDbcharZeroLinkCashPart());
        zi.setMixBaseHairColor(getMixBaseHairColor());
        zi.setMixAddHairColor(getMixAddHairColor());
        zi.setMixHairBaseProb(getMixHairBaseProb());
        return zi;
    }

    public boolean isZeroBetaState() {
        return isZeroBetaState;
    }

    public void setZeroBetaState(boolean zeroBetaState) {
        isZeroBetaState = zeroBetaState;
    }

    public int getSubHP() {
        return subHP;
    }

    public void setSubHP(int subHP) {
        this.subHP = subHP;
    }

    public int getSubMHP() {
        return subMHP;
    }

    public void setSubMHP(int subMHP) {
        this.subMHP = subMHP;
    }

    public int getSubMP() {
        return subMP;
    }

    public void setSubMP(int subMP) {
        this.subMP = subMP;
    }

    public int getSubMMP() {
        return subMMP;
    }

    public void setSubMMP(int subMMP) {
        this.subMMP = subMMP;
    }

    public int getSubSkin() {
        return subSkin;
    }

    public void setSubSkin(int subSkin) {
        this.subSkin = subSkin;
    }

    public int getSubHair() {
        return subHair;
    }

    public void setSubHair(int subHair) {
        this.subHair = subHair;
    }

    public int getSubFace() {
        return subFace;
    }

    public void setSubFace(int subFace) {
        this.subFace = subFace;
    }

    public int getDbcharZeroLinkCashPart() {
        return dbcharZeroLinkCashPart;
    }

    public void setDbcharZeroLinkCashPart(int dbcharZeroLinkCashPart) {
        this.dbcharZeroLinkCashPart = dbcharZeroLinkCashPart;
    }

    public int getMixBaseHairColor() {
        return mixBaseHairColor;
    }

    public void setMixBaseHairColor(int mixBaseHairColor) {
        this.mixBaseHairColor = mixBaseHairColor;
    }

    public int getMixAddHairColor() {
        return mixAddHairColor;
    }

    public void setMixAddHairColor(int mixAddHairColor) {
        this.mixAddHairColor = mixAddHairColor;
    }

    public int getMixHairBaseProb() {
        return mixHairBaseProb;
    }

    public void setMixHairBaseProb(int mixHairBaseProb) {
        this.mixHairBaseProb = mixHairBaseProb;
    }

    public void betaClothing(int kind, byte check, Char c) {
        int value = ClothingStats.getValueByOrder(kind), bc = getDbcharZeroLinkCashPart();
        int newVal = 0;
        if (check == 1 && (bc & value) == 0) {
            newVal = getDbcharZeroLinkCashPart() + value;
            setDbcharZeroLinkCashPart(newVal);
        } else if (check == 0 && (bc & value) != 0) {
            newVal = getDbcharZeroLinkCashPart() -value;
            setDbcharZeroLinkCashPart(newVal);
        }
        c.setQuestRecordEx(9000001, "zeroClothingData", getDbcharZeroLinkCashPart());
    }

    public void encode(OutPacket outPacket, Char chr) {
        short mask = 0x1 | 0x2 | 0x4 | 0x10 | 0x20 | 0x40 | 0x80 | 0x100;
        outPacket.encodeShort(mask);
        byte alpha = (byte) chr.getRecordFromQuestEx(9000001, "zeroState");
        AvatarLook avatar = alpha == 1 ? chr.getAvatarData().getZeroAvatarLook() : chr.getAvatarData().getAvatarLook();
        if((mask & 0x1) != 0){
            outPacket.encodeByte(alpha);
        }
        if ((mask & 0x2) != 0) {
            outPacket.encodeInt(getSubHP());
        }
        if ((mask & 0x4) != 0) {
            outPacket.encodeInt(getSubMP());
        }
        if ((mask & 0x8) != 0) {
            outPacket.encodeByte(getSubSkin());
        }
        if ((mask & 0x10) != 0) {
            outPacket.encodeInt(avatar.getHair());
        }
        if ((mask & 0x20) != 0) {
            outPacket.encodeInt(avatar.getFace());
        }
        if ((mask & 0x40) != 0) {
            outPacket.encodeInt(getSubMHP());
        }
        if ((mask & 0x80) != 0) {
            outPacket.encodeInt(getSubMMP());
        }
        if ((mask & 0x100) != 0) {
            outPacket.encodeInt(getDbcharZeroLinkCashPart());
        }
        if ((mask & 0x200) != 0) {
            outPacket.encodeInt(getMixBaseHairColor());
            outPacket.encodeInt(getMixAddHairColor());
            outPacket.encodeInt(getMixHairBaseProb());
        }
    }
}
