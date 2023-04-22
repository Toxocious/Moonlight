package net.swordie.ms.enums;

/**
 * Created on 1/6/2018.
 */
public enum MoveAbility {
    Stop(0),
    Walk(1),
    WalkRandom(2),
    Fly(3),
    FlyRandom(4),
    Smart(5),
    FixVMove(6),
    WalkSmart(7),
    WalkClone(8),
    FlyClone(9),
    WalkHang(10),
    Jaguar(11),
    FlyJaguar(12),
    FlyRandomAroundPos(13),
    Crystal(14), // used for Illium Crystal

    SmartFollow(16),
    WalkAcrossFootHold(17), // used in CubCavalry
    ;

    private byte val;

    MoveAbility(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public boolean changeFieldWithOwner() {
        return this != Stop;
    }
}
