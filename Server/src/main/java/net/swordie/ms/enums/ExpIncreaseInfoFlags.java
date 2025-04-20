package net.swordie.ms.enums;

/**
 * Created on 1/25/2018.
 */
public enum ExpIncreaseInfoFlags {
    SelectedMobBonusExp(0x1),
    PartyBonusPercentage(0x4),
    PartyBonusExp(0x10),
    WeddingBonusExp(0x20),
    ItemBonusExp(0x40),
    PremiumIPBonusExp(0x80),
    RainbowWeekEventBonusExp(0x100),
    BoomUpEventBonusExp(0x200),
    PlusExpBuffBonusExp(0x400),
    PsdBonusExpRate(0x800),
    IndieBonusExp(0x1000),
    RelaxBonusExp(0x2000),
    InstallItemBonusExp(0x4000),
    AswanWinnerBonusExp(0x8000),
    ExpByIncExpR(0x10000),
    ValuePackBonusExp(0x20000),
    ExpByIncPQExpR(0x40000),
    BaseAddExp(0x80000),
    BloodAllianceBonusExp(0x100000),
    FreezeHotEventBonusExp(0x200000),
    RestField(0x400000),
    UserHPRateBonusExp(0x800000),
    FieldValueBonusExp(0x1000000),
    MobKillBonusExp(0x2000000),
    LiveEventBonusExp(0x4000000);

    private int val;

    ExpIncreaseInfoFlags(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
