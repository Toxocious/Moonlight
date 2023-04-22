package net.swordie.ms.life.mob.skill;

import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;

import java.util.Arrays;

/**
 * Created on 3/18/2018.
 */
public enum MobSkillID {
    Unk(-1),
    PowerUp(100),
    MagicUp(101),
    PGuardUp(102),
    MGuardUp(103),
    Haste(104),
    MobConsume(105),
    PowerUpM(110),
    MagicUpM(111),
    PGuardUpM(112),
    MGuardUpM(113),
    HealM(114),
    HasteM(115),
    Seal(120),
    Darkness(121),
    Weakness(122),
    Stun(123),
    Curse(124),
    Poison(125),
    Slow(126),
    Dispel(127),
    Attract(128),
    BanMap(129),
    AreaFire(130),
    AreaPoison(131),
    ReverseInput(132),
    Undead(133),
    StopPortion(134),
    StopMotion(135),
    Fear(136),
    Frozen(137),
    DispelItemOption(138),
    PhysicalImmune(140),
    MagicImmune(141),
    Hardskin(142),
    PCounter(143),
    MCounter(144),
    PMCounter(145),
    Invincible(146),
    Pad(150),
    Mad(151),
    Pdr(152),
    Mdr(153),
    Acc(154),
    Eva(155),
    Speed(156),
    SealSkill(157),
    BalrogCounter(158),
    Teleport(170),
    UserBomb(171),
    UserMorph(172),
    DarkTornado(173),
    Lapidification(174),
    DeathMark(175),
    Damage(176),
    VenomSnake(177),
    SlowAttack(178),
    PainMark(179),
    VampDeath(180),
    Magnet(181),
    GiveMeHeal(182),
    FireBomb(183),
    ReturnTeleport(184),
    AreaForce(186),
    NearBuff(187),
    Dazzle(188),
    CapDebuffRed(189),
    CapDebuffBlue(190),
    AreaTimezone(191),
    Summon(200),
    Summon2(201),
    PassiveFirewalk(202),
    BreakdownTimezone(203),
    AllKill(204),
    FireBombDispel(205),
    AreaTosp(206),
    Hekaton(207),
    StunMadeBody(208),
    ResetMobStat(209),
    AreaAbnormal(211),
    AreaMobBuff(212),
    AreaWarning(213),
    CastingBar(214),
    ObstacleAttack(215),
    FixDamrBuff(216),
    BounceAttack(217),
    DebuffHalf(219),
    Knockback(220),
    AreaInstallBuff(221),
    AreaInstalledFire(222),
    LaserAttack(223),
    LtRbDamage(224),
    SummonSubBody(225),
    Toos(226),
    AreaForceFromUser(227),
    LaserControl(228),
    DecJump(229),
    FireAtRandomAttack(230),
    BattlefieldDead(231),
    TouchMe(232),
    PowerImmune(233),
    Contagion(234),
    FieldCommand(235),
    Hangover(236),
    Stigma(237),
    RandomAngleLucidAttack(238),
    Unk239(239),
    MassivePurpleArea(240),
    RandomDebuff(241),
    BossWillAttack(242),
    Pad2(243),
    Heal2(244),
    HpDecreaseDebuff(245),
    TrueHillaRedStringHand(246),
    TrueHillaScreenSwipe(247),
    TrueHillaMultiScaleInstallArea(248),
    TrueHillaRedDebuff(249),
    ChangeSpineAlpha(250),
    RetroWorldLaser(251),
    LaserBeam(252),
    MapleFlag(799),
    GolluxMobCount(800),
    No(999),
    ;

    private int val;

    MobSkillID(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static MobSkillID getMobSkillIDByVal(int val) {
        return Arrays.stream(values()).filter(m -> m.getVal() == val).findAny().orElse(Unk);
    }

    public CharacterTemporaryStat getAffectedCTS() {
        switch (this) {
            case Seal:
                return CharacterTemporaryStat.Seal;
            case Darkness:
                return CharacterTemporaryStat.Darkness;
            case Weakness:
                return CharacterTemporaryStat.Weakness;
            case Stun:
                return CharacterTemporaryStat.Stun;
            case Curse:
                return CharacterTemporaryStat.Curse;
            case Slow:
                return CharacterTemporaryStat.Slow;
            case PainMark:
                return CharacterTemporaryStat.PainMark;
            case Poison:
                return CharacterTemporaryStat.Poison;
            case Fear:
                return CharacterTemporaryStat.Fear;
        }
        return null;
    }
}
