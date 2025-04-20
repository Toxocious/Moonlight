package net.swordie.ms.client.character.skills.info;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Position;

/**
 * Created on 1/7/2018.
 */
public class ForceAtomInfo {
    private int key;
    private int inc;
    private int firstImpact;
    private int secondImpact;
    private int angle;
    private int startDelay;
    private int createTime;
    private int maxHitCount;
    private int effectIdx;
    private Position startPosition;
    private int disappearDelay;
    private boolean isBossMob;
    private int curRecreationCount = 0, maxRecreationCount = 0, recreationChance = 100;

    public ForceAtomInfo() {
    }

    public ForceAtomInfo(int key, int inc, int firstImpact, int secondImpact, int angle, int startDelay, int createTime, int maxHitCount, int effectIdx, Position startPosition) {
        this.key = key;
        this.inc = inc;
        this.firstImpact = firstImpact;
        this.secondImpact = secondImpact;
        this.angle = angle;
        this.startDelay = startDelay;
        this.createTime = createTime;
        this.maxHitCount = maxHitCount;
        this.effectIdx = effectIdx;
        this.startPosition = startPosition;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getKey());
        outPacket.encodeInt(getInc());
        outPacket.encodeInt(getFirstImpact());
        outPacket.encodeInt(getSecondImpact());
        outPacket.encodeInt(getAngle());
        outPacket.encodeInt(getStartDelay());
        outPacket.encodeInt(getStartPosition().getX());
        outPacket.encodeInt(getStartPosition().getY());
        outPacket.encodeInt(getCreateTime());
        outPacket.encodeInt(getMaxHitCount());
        outPacket.encodeInt(getEffectIdx());
        outPacket.encodeInt(getDisappearDelay()); // new 188 (Used in BladeFall)
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public int getFirstImpact() {
        return firstImpact;
    }

    public void setFirstImpact(int firstImpact) {
        this.firstImpact = firstImpact;
    }

    public int getSecondImpact() {
        return secondImpact;
    }

    public void setSecondImpact(int secondImpact) {
        this.secondImpact = secondImpact;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(int startDelay) {
        this.startDelay = startDelay;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getMaxHitCount() {
        return maxHitCount;
    }

    public void setMaxHitCount(int maxHitCount) {
        this.maxHitCount = maxHitCount;
    }

    public int getEffectIdx() {
        return effectIdx;
    }

    public void setEffectIdx(int effectIdx) {
        this.effectIdx = effectIdx;
    }

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

    public boolean isBossMob() {
        return isBossMob;
    }

    public void setBossMob(boolean bossMob) {
        isBossMob = bossMob;
    }

    public int getCurRecreationCount() {
        return curRecreationCount;
    }

    public void setCurRecreationCount(int curRecreationCount) {
        this.curRecreationCount = curRecreationCount;
    }

    public void incrementCurRecreationCount() {
        setCurRecreationCount(getCurRecreationCount() + 1);
    }

    public int getMaxRecreationCount() {
        return maxRecreationCount;
    }

    public void setMaxRecreationCount(int maxRecreationCount) {
        this.maxRecreationCount = maxRecreationCount;
    }

    public int getRecreationChance() {
        return recreationChance;
    }

    public void setRecreationChance(int recreationChance) {
        this.recreationChance = recreationChance;
    }
}
