package net.swordie.ms.life.mob.skill;

import net.swordie.ms.connection.OutPacket;

public class ShootingMoveStat {
    private int movePattern, moveRange, bulletUpgrade, moveSpeed, moveAngle;

    public ShootingMoveStat(int movePattern, int moveRange, int bulletUpgrade, int moveSpeed, int moveAngle) {
        this.movePattern = movePattern;
        this.moveRange = moveRange;
        this.bulletUpgrade = bulletUpgrade;
        this.moveSpeed = moveSpeed;
        this.moveAngle = moveAngle;
    }

    public ShootingMoveStat() {
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getMovePattern());
        outPacket.encodeInt(getMoveRange());
        outPacket.encodeInt(getBulletUpgrade());
        outPacket.encodeInt(getMoveSpeed());
        outPacket.encodeInt(getMoveAngle());
    }

    public int getMovePattern() {
        return movePattern;
    }

    public void setMovePattern(int movePattern) {
        this.movePattern = movePattern;
    }

    public int getMoveRange() {
        return moveRange;
    }

    public void setMoveRange(int moveRange) {
        this.moveRange = moveRange;
    }

    public int getBulletUpgrade() {
        return bulletUpgrade;
    }

    public void setBulletUpgrade(int bulletUpgrade) {
        this.bulletUpgrade = bulletUpgrade;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getMoveAngle() {
        return moveAngle;
    }

    public void setMoveAngle(int moveAngle) {
        this.moveAngle = moveAngle;
    }
}
