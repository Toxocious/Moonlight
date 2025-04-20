package net.swordie.ms.enums;

/**
 * Created on 28-9-2018.
 * @author Asura
 */
public enum MedalReissueResultType {
    Success(0),
    NoMoney(1),
    NoSlot(2),
    AlreadyHas(3),
    Unknown(4),
    ;

    private byte val;

    MedalReissueResultType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
