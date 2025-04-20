package net.swordie.ms.enums;

/**
 * Created on 12/22/2017.
 */
public enum EquipBaseStat {
    tuc(0x1, 0),
    cuc(0x2, 0),
    iStr(0x4, 0),
    iDex(0x8, 0),
    iInt(0x10, 0),
    iLuk(0x20, 0),
    iMaxHP(0x40, 0),
    iMaxMP(0x80, 0),
    iPAD(0x100, 0),
    iMAD(0x200, 0),
    iDEF(0x400, 0),
    iCraft(0x4000, 0),
    iSpeed(0x8000, 0),
    iJump(0x10000, 0),
    attribute(0x20000, 0),
    levelUpType(0x40000, 0),
    level(0x80000, 0),
    exp(0x100000, 0),
    durability(0x200000, 0),
    iuc(0x400000, 0),
    iPvpDamage(0x800000, 0),
    iReduceReq(0x1000000, 0),
    specialAttribute(0x2000000, 0),
    durabilityMax(0x4000000, 0),
    iIncReq(0x8000000, 0),
    growthEnchant(0x10000000, 0),
    psEnchant(0x20000000, 0),
    bdr(0x40000000, 0),
    imdr(0x80000000, 0),
    damR(0x1, 1),
    statR(0x2, 1),
    cuttable(0x4, 1),
    exGradeOption(0x8, 1),
    hyperUpgrade(0x10, 1);

    private int val, pos;

    EquipBaseStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    public int getVal() {
        return val;
    }

    public int getPos() {
        return pos;
    }
}
