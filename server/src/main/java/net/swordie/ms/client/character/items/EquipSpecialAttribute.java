package net.swordie.ms.client.character.items;

/**
 * Created on 1/25/2018.
 */
public enum EquipSpecialAttribute {
    NotDestroy(0x1),
    GradeUpgrade(0x2),
    EnchantSuccess(0x4), // scroll always succeed
    Extended(0x8),
    OneMeso(0x10),
    MakingSkillMeister(0x20),
    MakingSkillMaster(0x40),
    Vestige(0x80),
    VestigeBound(0x200),
    VestigePossibleTrading(0x400),
    VestigeAppliedAccountShare(0x800);

    private int val;

    EquipSpecialAttribute(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
