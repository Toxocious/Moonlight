package net.swordie.ms.enums;

public enum SecondAtomEnum {
    MagicShard(0),
    SwordCape(1),
    SwordCape_2(2),
    SwordCape_3(3),
    SwordCape_4(4),
    SwordCape_5(5),
    SwordCape_6(6),
    FlyingSword(7),
    RedFlyingSword(8);

    private byte val;

    SecondAtomEnum(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
