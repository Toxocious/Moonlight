package net.swordie.ms.client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum EquipAttribute {
    Locked(0x1),
    PreventSlipping(0x2),
    PreventColdness(0x4),
    Untradable(0x8),
    UntradableAfterTransaction(0x10),
    NoNonCombatStatGain(0x20),
    Used(0x40),
    Crafted(0x80),
    ProtectionScroll(0x100),
    LuckyDay(0x200),
    CubeExOptLv(0x400),
    CubeExOptLv2(0x800),
    TradedOnceWithinAccount(0x1000),
    UpgradeCountProtection(0x2000),
    ScrollProtection(0x4000),
    ReturnScroll(0x8000);


    private int val;

    EquipAttribute(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
