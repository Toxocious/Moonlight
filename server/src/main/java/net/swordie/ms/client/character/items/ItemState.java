package net.swordie.ms.client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum ItemState {
    MiraculousEnhancement(0x1),
    RefundableGachaponItem(0x8),
    RefundableEventGachaponItem(0x10),
    RedLabelItem(0x20),
    BlackLabelItem(0x40),
    InnocentRUCItem(0x80),
    AmazingHyperUpgradeChecked(0x100),
    AmazingHyperUpgradeUsed_Log(0x200),
    AmazingHyperUpgradeUsed_Stat(0x400),
    AmazingHyperUpgradeUsed_Sync(0x800);

    private int val;

    ItemState(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
