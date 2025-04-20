package net.swordie.ms.enums;

public enum FieldOption {
    MoveLimit(0x1L),
    SkillLimit(0x2L),
    SummonLimit(0x4L),
    MysticDoorLimit(0x8L),
    MigrateLimit(0x10L),
    PortalScrollLimit(0x20L),
    TeleportItemLimit(0x40L),
    MiniGameLimit(0x80L),
    SpecificPortalScrollLimit(0x100L),
    TamingMobLimit(0x200L),
    StatChangeItemConsumeLimit(0x400L),
    PartyBossChangeLimit(0x800L),
    NoMobCapacityLimit(0x1000L),
    WeddingInvitationLimit(0x2000L),
    CashWeatherConsumeLimit(0x4000L),
    NoPet(0x8000L),
    AntiMacroLimit(0x10000L),
    FallDownLimit(0x20000L),
    SummonNPCLimit(0x40000L),
    NoEXPDecrease(0x80000L),
    NoDamageOnFalling(0x100000L),
    ParcelOpenLimit(0x200000L),
    DropLimit(0x400000L),
    RocketBoosterLimit(0x800000L),
    ItemOptionLimit(0x1000000L),
    NoQuestAlertLimit(0x2000000L),
    NoAndroid(0x4000000L),
    AutoExpandMinimap(0x8000000L),
    MoveSkillOnly(0x10000000L),
    OnlyStarPlanetPet(0x20000000L);

    private long val;

    FieldOption(long val) {
        this.val = val;
    }

    public long getVal() {
        return val;
    }
}
