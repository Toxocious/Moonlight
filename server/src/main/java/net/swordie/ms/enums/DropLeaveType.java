package net.swordie.ms.enums;

/**
 * Created on 2/21/2018.
 */
public enum DropLeaveType {
    Fade(0),
    NoFade(1),
    CharPickup1(2),
    CharPickup2(3),
    DelayedPickup(4),
    PetPickup(5),
    Fade2(6),
    Absorb(7); // CAnimationDisplayer::RegisterAbsorbItemAnimationJP ?

    private byte val;

    DropLeaveType(int  val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
