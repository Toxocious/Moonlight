package net.swordie.ms.life.mob;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Util;

public class ForcedMobStat {
    private long maxHP, maxMP, exp, pushed;
    private int pad, mad, pdr, mdr, acc, eva, speed, level, userCount;

    public long getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(long maxHP) {
        this.maxHP = maxHP;
    }

    public long getMaxMP() {
        return maxMP;
    }

    public void setMaxMP(long maxMP) {
        this.maxMP = maxMP;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getPad() {
        return pad;
    }

    public void setPad(int pad) {
        this.pad = pad;
    }

    public int getMad() {
        return mad;
    }

    public void setMad(int mad) {
        this.mad = mad;
    }

    public int getPdr() {
        return pdr;
    }

    public void setPdr(int pdr) {
        this.pdr = pdr;
    }

    public int getMdr() {
        return mdr;
    }

    public void setMdr(int mdr) {
        this.mdr = mdr;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public int getEva() {
        return eva;
    }

    public void setEva(int eva) {
        this.eva = eva;
    }

    public long getPushed() {
        return pushed;
    }

    public void setPushed(long pushed) {
        this.pushed = pushed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeLong(getMaxHP());
        outPacket.encodeInt(Util.maxInt(getMaxMP()));
        outPacket.encodeInt(Util.maxInt(getExp()));
        outPacket.encodeInt(getPad());
        outPacket.encodeInt(getMad());
        outPacket.encodeInt(getPdr());
        outPacket.encodeInt(getMdr());
        outPacket.encodeInt(getAcc());
        outPacket.encodeInt(getEva());
        outPacket.encodeInt(Util.maxInt(getPushed()));
        outPacket.encodeInt(getSpeed() > 0 ? -getSpeed() : getSpeed());
        outPacket.encodeInt(getLevel());
        outPacket.encodeInt(getUserCount());
    }

    public ForcedMobStat deepCopy() {
        ForcedMobStat copy = new ForcedMobStat();
        copy.setMaxHP(getMaxHP());
        copy.setMaxMP(getMaxMP());
        copy.setExp(getExp());
        copy.setPad(getPad());
        copy.setMad(getMad());
        copy.setPdr(getPdr());
        copy.setMdr(getMdr());
        copy.setAcc(getAcc());
        copy.setEva(getEva());
        copy.setPushed(getPushed());
        copy.setSpeed(getSpeed());
        copy.setLevel(getLevel());
        copy.setUserCount(getUserCount());
        return copy;
    }
}
