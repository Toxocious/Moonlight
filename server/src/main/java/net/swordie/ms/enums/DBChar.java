package net.swordie.ms.enums;

/**
 *
 * @author Five
 */
public enum DBChar {
    None                (0),
    Character           (0x1),
    Money               (0x2),
    ItemSlotEquip       (0x4),
    ItemSlotConsume     (0x8),
    ItemSlotInstall     (0x10),
    ItemSlotEtc         (0x20),
    ItemSlotCash        (0x40),
    InventorySize       (0x80),
    SkillRecord         (0x100),
    QuestRecord         (0x200),
    MinigameRecord      (0x400),
    CoupleRecord        (0x800),
    MapTransfer         (0x1000),
    QuestComplete       (0x4000),
    SkillCooltime       (0x8000),
    MonsterBookCard     (0x10000),
    MonsterBookCover    (0x20000),
    QuestRecordEx       (0x40000),
    NewYearCard         (0x80000),
    EquipExtension      (0x100000),
    WildHunterInfo      (0x200000),
    Unk400000           (0x400000),
    ShopBuyLimit        (0x400000),
    FamiliarCodex       (0x400000),
    Unk800000           (0x800000),
    ItemPot             (0x800000), // was Visitor
    EquipExt            (0x1000000),
    CoreAura            (0x1000000),
    ExpConsumeItem      (0x2000000),
    QuestCompleteOld    (0x4000000),
    VisitorLog4         (0x8000000),
    ChosenSkills        (0x10000000),
    StolenSkills        (0x20000000),
    Unk40000000         (0x40000000),
    CharacterPotential  (0x80000000L),
    HonorInfo           (0x100000000L),
    Unk200000000        (0x200000000L),
    ReturnEffectInfo    (0x400000000L),
    DressUpInfo         (0x800000000L),
    CoreInfo            (0x1000000000L),
    FarmPotential       (0x2000000000L),
    FarmUserInfo        (0x4000000000L),
    MemorialCubeInfo    (0x8000000000L),
    Unk10000000000      (0x10000000000L),
    Unk20000000000      (0x20000000000L),
    LikePoint           (0x40000000000L),
    ZeroInfo            (0x80000000000L),
    Unk100000000000     (0x100000000000L),
    Avatar              (0x200000000000L),
    MonsterBattleInfo   (0x800000000000L),
    Unk800000000000     (0x800000000000L),
    RunnerGameRecord    (0x2000000000000L),
    SoulCollection      (0x1000000000000L),
    MonsterCollection   (0x4000000000000L),
    Unk4000000000000    (0x4000000000000L),
    Unk8000000000000    (0x8000000000000L),
    Unk10000000000000   (0x10000000000000L),
    VMatrix             (0x20000000000000L),
    Achievement         (0x40000000000000L),
    ActiveDamageSkin    (0x80000000000000L),
    MemorialFlameInfo   (0x100000000000000L),
    Unk200000000000000  (0x200000000000000L),
    Unk400000000000000  (0x400000000000000L),
    Familiar            (0x800000000000000L),
    Unk1000000000000000 (0x1000000000000000L),
    RedLeafInfo         (0x2000000000000000L),
    Unk8000000000000000 (0x8000000000000000L),
    All                 (0xFFFFFFFFFFFFFFFFL),
    ;

    public long uFlag;

    DBChar(long uFlag) {
        this.uFlag = uFlag;
    }

    public long get() {
        return uFlag;
    }

    public boolean isInMask(long mask){
        return (mask & get()) != 0;
    }

    public boolean isInMask(DBChar mask){
        return (mask.get() & get()) != 0;
    }

    public void remove(DBChar mask) {
        if (isInMask(mask)) {
            uFlag ^= mask.get();
        }
    }

    public void add(DBChar mask) {
        uFlag |= mask.get();
    }
}
