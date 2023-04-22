package net.swordie.ms.client.character.items;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/20/2017.
 */
public class DressUpInfo {
    private int face;
    private int hair;
    private int clothe;
    private byte skin;
    private int mixBaseHairColor = -1;
    private int mixAddHairColor;
    private int mixHairBaseProb;

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getHair() {
        return hair;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public int getClothe() {
        return clothe;
    }

    public void setClothe(int clothe) {
        this.clothe = clothe;
    }

    public byte getSkin() {
        return skin;
    }

    public void setSkin(byte skin) {
        this.skin = skin;
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

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getFace());
        outPacket.encodeInt(getHair());
        outPacket.encodeInt(getClothe());
        outPacket.encodeByte(getSkin());
        outPacket.encodeInt(getMixBaseHairColor());
        outPacket.encodeInt(getMixAddHairColor());
        outPacket.encodeInt(getMixHairBaseProb());
    }
}
