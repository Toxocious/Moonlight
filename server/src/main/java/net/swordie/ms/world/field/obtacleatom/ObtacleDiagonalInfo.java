package net.swordie.ms.world.field.obtacleatom;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 5/30/2018.
 */
public class ObtacleDiagonalInfo {
    private int effect;
    private int minAngle; // guess
    private int maxAngle; // guess
    private int createDuration;
    private int height;

    public ObtacleDiagonalInfo() {
    }

    public ObtacleDiagonalInfo(int effect, int minAngle, int maxAngle, int createDuration, int height) {
        this.effect = effect;
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
        this.createDuration = createDuration;
        this.height = height;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getEffect());
        outPacket.encodeInt(getMinAngle());
        outPacket.encodeInt(getMaxAngle());
        outPacket.encodeInt(getCreateDuration());
        outPacket.encodeInt(getHeight());
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public int getMinAngle() {
        return minAngle;
    }

    public void setMinAngle(int minAngle) {
        this.minAngle = minAngle;
    }

    public int getMaxAngle() {
        return maxAngle;
    }

    public void setMaxAngle(int maxAngle) {
        this.maxAngle = maxAngle;
    }

    public int getCreateDuration() {
        return createDuration;
    }

    public void setCreateDuration(int createDuration) {
        this.createDuration = createDuration;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
