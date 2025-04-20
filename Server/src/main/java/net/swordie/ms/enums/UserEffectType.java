package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 6/7/2018.
 */
public enum UserEffectType {
    LevelUp(0),
    SkillUse(1),
    SkillUseBySummoned(2),
    Unk3(3), // new
    SkillAffected(4),
    SkillAffected_Ex(5),
    SkillAffected_Select(6),
    SkillSpecialAffected(7),
    Quest(8),
    Pet(9),
    SkillSpecial(10),
    Resist(11),
    ProtectOnDieItemUse(12),
    PlayPortalSE(13),
    JobChanged(14),
    QuestComplete(15),
    IncDecHPEffect(16),
    BuffItemEffect(17),
    SquibEffect(18),
    MonsterBookCardGet(19),
    LotteryUse(20),
    ItemLevelUp(21),
    ItemMaker(22),
    FieldMesoItemConsumed(23), // new
    ExpItemConsumed(24),
    FieldExpItemConsumed(25),
    ReservedEffect(26),
    unk27(27), // old unknown
    UpgradeTombItemUse(28),
    BattlefieldItemUse(29),
    unk30(30), // old unknown
    AvatarOriented(31),
    AvatarOrientedRepeat(32),
    AvatarOrientedMultipleRepeat(33),
    IncubatorUse(34),
    PlaySoundWithMuteBGM(35),
    PlayExclSoundWithDownBGM(36),
    SoulStoneUse(37),
    IncDecHPEffect_EX(38), // correct with 202.3
    IncDecHPRegenEffect(39),
    EffectUOL(40),
    PvPRage(41),
    PvPChampion(42),
    PvPGradeUp(43),
    PvPRevive(44),
    PvPJobEffect(45),
    FadeInOut(46),
    MobSkillHit(47),
    unk48(48), // new
    BlindEffect(49),
    BossShieldCount(50),
    ResetOnStateForOnOffSkill(51),
    JewelCraft(52),
    ConsumeEffect(53),
    PetBuff(54),
    LotteryUIResult(55),
    LeftMonsterNumber(56),
    ReservedEffectRepeat(57),
    RobbinsBomb(58),
    SkillMode(59),
    ActQuestComplete(60),
    Point(61),
    SpeechBalloon(62),
    TextEffect(63),
    SkillPreLoopEnd(64),
    Aiming(65),
    Unk66(66),
    PickUpItem(67),
    BattlePvP_IncDecHp(68),
    CatchEffect(69),
    FailCatchEffect(70),
    BiteAttack_ReceiveSuccess(71),
    BiteAttack_ReceiveFail(72),
    IncDecHPEffect_Delayed(73),
    Unk74(74),
    MobSkillSpecial(75),
    BlackMageEffect(76), // Black Mage  Effect (100017)
    ResistAbnormalStatus(77),
    Unk78(78),
    RedChat(79),
    Unk80(80),
    Unk81(81),
    GoblinBatHit(82), // new v212
    Unk82(83),
    SkillMoveEffect(84), // Skill/%03d.img/skill/%07d/moveEffect
    UpgradePotionMsg(85),
    MonsterBookSetComplete(86),
    FamiliarEscape(87),
    WaterSmashResult(88),
    Unk88(89), // Eluna Jewel?
    SomeUpgradeEffectOnUser(90), // Eluna
    ;

    private byte val;

    UserEffectType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }

    public static UserEffectType getByVal(int val) {
        return Arrays.stream(values()).filter(uet -> uet.getVal() == val).findAny().orElse(null);
    }
}
