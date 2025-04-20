package net.swordie.ms.client.character.skills.temp;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.util.Util;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created on 1/2/2018.
 */
/**
 * Created on 1/2/2018.
 */
public enum CharacterTemporaryStat implements Comparator<CharacterTemporaryStat> {
    IndiePAD(0),
    IndieMAD(1),
    IndieDEF(2),
    IndieMHP(3),
    IndieMHPR(4),
    IndieMMP(5),
    IndieMMPR(6),
    IndieACC(7),

    IndieEVA(8),
    IndieJump(9),
    IndieSpeed(10),
    IndieAllStat(11),
    IndieAllStatR(12),
    IndieDodgeCriticalTime(13),
    IndieEXP(14),
    IndieBooster(15),

    IndieFixedDamageR(16),
    PyramidStunBuff(17), // Osiris' Eye: Stuns monsters for 1 second. Includes Pharaoh Yetis and Pharaoh Mummies at a 30% chance. Costs 500 points.
    PyramidFrozenBuff(18), // Horus' Eye: Slows down all monsters for 15 seconds. Includes Pharaoh Yetis and Pharaoh Mummies at a 30% chance. Costs 700 points.
    PyramidFireBuff(19), // Isis' Eye: Does Damage over time to all monsters for 15 seconds every second. Costs 500 points.
    PyramidBonusDamageBuff(20), // Anubis' Eye: Increases 40 attack for 15 seconds. Costs 500 points.
    IndieRelaxEXP(21),
    IndieSTR(22),
    IndieDEX(23),

    IndieINT(24),
    IndieLUK(25),
    IndieDamR(26),
    IndieScriptBuff(27),
    IndieMDF(28),
    IndieAsrR(29),
    IndieTerR(30),
    IndieCr(31),

    IndiePDDR(32),
    IndieCrDmg(33),
    IndieBDR(34),
    IndieStatR(35),
    IndieStance(36),
    IndieIgnoreMobpdpR(37),
    IndieEmpty(38),
    IndiePADR(39),

    IndieMADR(40),
    IndieEVAR(41),
    IndieDrainHP(42),
    IndiePMdR(43),
    IndieForceJump(44), // Max Jump (?)
    IndieForceSpeed(45), // Max Speed (?)
    IndieQrPointTerm(46),

    IndieSummon(47), // Seems to be used by GMS for almost all their summons
    IndieCooltimeReduce(48),
    IndieNotDamaged(49), // Given by Soul Eclipse DW 3rd V & Omega Blaster (Attack mode)  |  Invincibility
    IndieKeyDownTime(50), // DemonicBlast | Twin Blades of Time
    IndieDamReduceR(51),
    IndieCrystalCharge(52),
    IndieNegativeEVAR(53),
    IndieHitDamage(54),
    IndieNoKnockBack(55),
    Indie205_56(56), // something that affects moving stat
    IndieHitDamageInclHPR(57), // Indie Hit Damage, including HP% hits
    IndieArcaneForce(58),
    Indie205_59(59), // may not be here, but at the very least before IndieUNK3
    Indie205_60(60),
    Indie205_61(61),
    NuclearOption(62),
    Indie208_63(63), // new guessed
    Indie208_64(64), // new guessed
    IndieHyperStat(65), // +x to Hyper Stats
    IndiePartyExp(66),
    IndieUNK5(67),
    IndiePartyDrop(68),
    IndieUnk63(69),
    Indie212_70(70), // just a place holder
    Indie212_71(71), // just a place holder
    IndieStatCount(72),
    PAD(73),
    DEF(74),
    MAD(75),
    ACC(76),
    EVA(77),
    Craft(78),

    Speed(79),
    Jump(80),
    MagicGuard(81),
    DarkSight(82),
    Booster(83),
    PowerGuard(84),
    MaxHP(85),
    MaxMP(86),

    Invincible(87),
    SoulArrow(88),
    Stun(89),
    Poison(90),
    Seal(91),
    Darkness(92),
    ComboCounter(93),
    WorldReaver(94),
    HammerOfTheRighteous(95),
    BigHammerOfTheRighteous(96),
    WeaponCharge(97),

    HolySymbol(98),
    MesoUp(99),
    ShadowPartner(100),
    PickPocket(101),
    MesoGuard(102),

    Thaw(103),
    Weakness(104),
    Curse(105),
    Slow(106),
    Morph(107),
    Regen(108),
    BasicStatUp(109),
    Stance(110),

    SharpEyes(111),
    ManaReflection(112),
    Attract(113),
    NoBulletConsume(114),
    Infinity(115),
    AdvancedBless(116),
    IllusionStep(117),
    Blind(118),

    Concentration(119),
    BanMap(120),
    MaxLevelBuff(121),
    MesoUpByItem(122),
    Unk111(123),
    Unk112(124),
    Ghost(125),
    Barrier(126),
    ReverseInput(127),
    ItemUpByItem(128),
    RespectPImmune(129),
    RespectMImmune(130),
    DefenseAtt(131),
    DefenseState(132),
    DojangBerserk(133),
    DojangInvincible(134),

    DojangShield(135),
    SoulMasterFinal(136),
    WindBreakerFinal(137),
    ElementalReset(138),
    HideAttack(139),
    EventRate(140),
    ComboAbilityBuff(141),
    ComboDrain(142),

    ComboBarrier(143),
    BodyPressure(144),
    RepeatEffect(145),
    ExpBuffRate(146),
    StopPortion(147),
    StopMotion(148),
    Fear(149),
    HiddenPieceOn(150),

    MagicShield(151),
    MagicResistance(152),
    SoulStone(153),
    Flying(154),
    Frozen(155),
    AssistCharge(156),
    Enrage(157),
    DrawBack(158),

    NotDamaged(159),
    FinalCut(160),
    HowlingAttackDamage(161),
    BeastFormDamageUp(162),
    Dance(163),
    EMHP(164),
    EMMP(165),
    EPAD(166),

    EMAD(167),
    EPDD(168),
    Guard(169),
    Cyclone(170),
    RadiantOrb(171),
    HowlingCritical(172),
    HowlingMaxMP(173),
    HowlingDefence(174),

    HowlingEvasion(175),
    Conversion(176),
    Sneak(177),
    Mechanic(178),
    BeastFormMaxHP(179),
    Dice(180),

    BlessingArmor(181),
    DamR(182),
    TeleportMasteryOn(183),
    CombatOrders(184),
    Beholder(185),
    DispelItemOption(186),
    Inflation(187),
    OnixDivineProtection(188),

    Web(189),
    Bless(190),
    TimeBomb(191),
    DisOrder(192),
    Thread(193),
    Team(194),
    Explosion(195),
    BuffLimit(196),

    STR(197),
    INT(198),
    DEX(199),
    LUK(200),
    DispelItemOptionByField(201),
    DarkTornado(202),
    PVPDamage(203),
    PvPScoreBonus(204),

    PvPInvincible(205),
    PvPRaceEffect(206),
    WeaknessMdamage(207),
    Frozen2(208),
    PVPDamageSkill(209),
    AmplifyDamage(210),
    Shock(211),
    InfinityForce(212),

    IncMaxHP(213),
    IncMaxMP(214),
    HolyMagicShell(215),
    KeyDownTimeIgnore(216),
    ArcaneAim(217),
    MasterMagicOn(218),
    AsrR(219),
    TerR(220),

    DamAbsorbShield(221),
    DevilishPower(222),
    Roulette(223),
    SpiritLink(224),
    AsrRByItem(225),
    Event(226),
    CriticalBuff(227),
    DropRate(228),

    PlusExpRate(229),
    ItemInvincible(230),
    Awake(231),
    ItemCritical(232),
    Event2(233),
    VampiricTouch(234),
    DDR(235),
    IncCriticalDam(236),

    IncTerR(237),
    IncAsrR(238),
    DeathMark(239),
    UsefulAdvancedBless(240),
    Lapidification(241),
    VenomSnake(242),
    CarnivalAttack(243),
    CarnivalDefence(244),

    CarnivalExp(245),
    SlowAttack(246),
    PyramidEffect(247),
    KillingPoint(248),
    HollowPointBullet(249),
    KeyDownMoving(250),
    IgnoreTargetDEF(251),
    ReviveOnce(252),

    Invisible(253),
    EnrageCr(254),
    EnrageCrDamMin(255),
    Judgement(256),
    DojangLuckyBonus(257),
    PainMark(258),
    Magnet(259),
    MagnetArea(260),

    GuidedArrow(261),
    SpiritFlow(262),
    LucentBrand(263),
    ExtraSkillCTS(264),
    Unk199_256(265),
    TideOfBattle(266),
    GrandGuardian(267),
    Unk200_260(268),
    VampDeath(269),
    BlessingArmorIncPAD(270),
    KeyDownAreaMoving(271),
    Larkness(272),
    StackBuff(273),
    BlessOfDarkness(274),

    AntiMagicShell(275),
    AntiMagicShellEx(275),

    LifeTidal(276),
    HitCriDamR(277),
    SmashStack(278),
    PartyBarrier(279),
    ReshuffleSwitch(280),
    SpecialAction(281),
    VampDeathSummon(282),

    StopForceAtomInfo(283),
    SoulGazeCriDamR(284),
    SoulRageCount(285),
    PowerTransferGauge(286),
    AffinitySlug(287),
    Trinity(288),
    IncMaxDamage(289),
    BossShield(290),

    MobZoneState(291),
    GiveMeHeal(292),
    TouchMe(293),
    Contagion(294),
    ComboUnlimited(295),
    SoulExalt(296),
    IgnorePCounter(297),
    IgnoreAllCounter(298),

    IgnorePImmune(299),
    IgnoreAllImmune(300),
    FinalJudgement(301),
    // Unk188_283(300), // Critical Rate removed in v212?
    IceAura(302),
    FireAura(303),
    VengeanceOfAngel(304),
    HeavensDoor(305),

    Preparation(306),
    BullsEye(307),
    IncEffectHPPotion(308),
    IncEffectMPPotion(309),
    BleedingToxin(310),
    IgnoreMobDamR(311),
    Asura(312),
    OmegaBlaster(313),
    FlipTheCoin(314),

    UnityOfPower(315),
    Stimulate(316),
    ReturnTeleport(317),
    DropRIncrease(318),
    IgnoreMobpdpR(319),
    BdR(320),
    CapDebuff(321),

    Exceed(322),
    DiabolikRecovery(323),
    FinalAttackProp(324),
    ExceedOverload(325),
    OverloadCount(326),
    BuckShot(327),
    FireBomb(328),
    HalfstatByDebuff(329),

    SurplusSupply(330),
    SetBaseDamage(331),
    EVAR(332),
    NewFlying(333),
    AmaranthGenerator(334),
    OnCapsule(335),
    CygnusElementSkill(336),
    StrikerHyperElectric(337),

    EventPointAbsorb(338),
    EventAssemble(339),
    StormBringer(340),
    ACCR(341),
    DEXR(342),
    Albatross(343),
    Translucence(344),
    PoseType(345),

    LightOfSpirit(346),
    ElementSoul(347),
    GlimmeringTime(348),
    TrueSight(349),
    SoulExplosion(350),
    SoulMP(351),
    FullSoulMP(352),
    SoulSkillDamageUp(353),

    ElementalCharge(354),
    Restoration(355),
    CrossOverChain(356),
    ChargeBuff(357),
    Reincarnation(358),
    KnightsAura(359),
    ChillingStep(360),
    DotBasedBuff(361),

    BlessEnsenble(362),
    ComboCostInc(363),
    ExtremeArchery(364),
    NaviFlying(365),
    QuiverCatridge(366),
    AdvancedQuiver(367),
    UserControlMob(368),
    ImmuneBarrier(369),

    ArmorPiercing(370),
    ZeroAuraStr(371),
    ZeroAuraSpd(372),
    CriticalGrowing(373),
    RelicEmblem(374),
    QuickDraw(375),
    BowMasterConcentration(376),
    TimeFastABuff(377),
    TimeFastBBuff(378),
    GatherDropR(379),
    AimBox2D(380),
    CursorSniping(381), // also used by Seal Snipe in Hekaton
    DebuffTolerance(382), // increase Status Resistance

    Unk_382(383),
    DotHealHPPerSecond(384), // or 384
    Unk203_374(385), // might be DotHealMPPerSecond

    SpiritGuard(386),
    PreReviveOnce(387),
    SetBaseDamageByBuff(388),
    LimitMP(389),
    ReflectDamR(390),
    ComboTempest(391),
    MHPCutR(392),
    MMPCutR(393),
    SelfWeakness(394),
    ElementDarkness(395),

    FlareTrick(396),
    Ember(397),
    Dominion(398),
    SiphonVitality(399),
    DarknessAscension(400),
    BossWaitingLinesBuff(401),
    DamageReduce(402),
    ShadowServant(403),

    ShadowIllusion(404),
    KnockBack(405),
    AddAttackCount(406),
    ComplusionSlant(407),
    JaguarSummoned(408),
    JaguarCount(409),

    SSFShootingAttack(410),
    DevilCry(411),
    ShieldAttack(412),
    BMageAura(413),
    DarkLighting(414),
    AttackCountX(415),
    BMageDeath(416),
    BombTime(417),

    NoDebuff(418),
    BattlePvPMikeShield(419),
    BattlePvPMikeBugle(420),
    XenonAegisSystem(421),
    AngelicBursterSoulSeeker(422),
    HiddenPossession(423),
    NightWalkerBat(424),
    NightLordMark(425),
    WizardIgnite(426),
    FireBarrier(427),
    ChangeFoxMan(428),
    DivineEcho(429),
    DemonicFrenzy(430), // recv DEMON_BLOOD_SPILL_REQUEST
    Unk200_431(431), // ?
    MastemasMark(432), //v208

    RiftOfDamnation(433),
    QuiverBarrage(434),
    SwordsOfConsciousness(435),
    PrimalGrenade(436),
    Unk188_419(437),
    Unk188_420(438),

    BattlePvPHelenaMark(439),
    BattlePvPHelenaWindSpirit(440),
    BattlePvPLangEProtection(441),
    BattlePvPLeeMalNyunScaleUp(442),
    BattlePvPRevive(443),
    PinkbeanAttackBuff(444),
    PinkbeanRelax(445),
    PinkbeanRollingGrade(446),
    PinkbeanYoYoStack(447),
    RandAreaAttack(448),

    Unk200_442(449),
    NextAttackEnhance(450),
    //    AranBeyonderDamAbsorb(447), // between NextAttackEnhance and RoyalGuardState: 1 removed
    AranCombotempastOption(451),
    NautilusFinalAttack(452),
    ViperTimeLeap(453),
    RoyalGuardState(454),

    RoyalGuardPrepare(455),
    MichaelSoulLink(456),
    MichaelStanceLink(457),
    TriflingWhimOnOff(458),
    AddRangeOnOff(459),
    KinesisPsychicPoint(460),
    KinesisPsychicOver(461),
    KinesisPsychicShield(462),

    KinesisIncMastery(463),
    KinesisPsychicEnergeShield(464),
    BladeStance(465),
    DebuffActiveSkillHPCon(466),
    DebuffIncHP(467),
    BowMasterMortalBlow(468),
    AngelicBursterSoulResonance(469),
    Fever(470),

    IgnisRore(471),
    //    RpSiksin(459), somewhere between 436 and 459 one is removed
    TeleportMasteryRange(472),
    FixCoolTime(473),
    IncMobRateDummy(474),
    AdrenalinBoost(475),
    AranSmashSwing(476),
    AranDrain(477),

    AranBoostEndHunt(478),
    HiddenHyperLinkMaximization(479),
    RWCylinder(480),
    RWCombination(481),
    Unk188_460(482),
    RWMagnumBlow(483),
    RWBarrier(484),
    RWBarrierHeal(485),

    RWMaximizeCannon(486),
    RWOverHeat(487),
    UsingScouter(488),
    RWMovingEvar(489),
    Stigma(490),
    MahasFury(491),
    RuneCooltime(492),
    Unk188_471(493),

    Unk188_472(494),
    Unk188_474(495),
    MeltDown(496),
    SparkleBurstStage(497),
    LightningCascade(498),
    BulletParty(499),
    Unk188_479(500),

    AuraScythe(501),
    Benediction(502),
    VoidStrike(503),
    ReduceHitDmgOnlyHPR(504),
    Unk199_493(505),
    WeaponAura(506),
    ManaOverload(507),
    RhoAias(508),
    PsychicTornado(509),

    SpreadThrow(510),
    WindEnergy(511),
    MassDestructionRockets(512),
    ShadowAssault(513),
    Unk188_492(514), // Awakened Relic placeholder // From here to overloadmode something was removed
    // Unk188_493(513), removed (could be the one above or ones below)
    Unk188_494(515),
    BlitzShield(516),

    SplitShot(517),
    FreudBlessing(518),
    OverloadMode(519),
    SpotLight(520),
    Unk188_500(521),
    MuscleMemory(522),
    //    NuclearOption(522), // moved/removed
    WingsOfGlory(523),
    TrickBladeMobStat(524),
    Overdrive(525),
    EtherealForm(526),
    LastResort(527),
    ViciousShot(528),
    Unk176_466(529),
    Unk200_527(530),
    Unk200_528(531), //Black Aura
    ImpenetrableSkin(532),

    Unk199_520(533),
    Unk199_521(534),
    Unk199_521Ex(534),
    Resonance(535),
    FlashCrystalBattery(536),
    Unk199_524(537),
    CrystalBattery(538),
    Unk199_526(539),
    Unk199_527(540),
    Unk199_528(541),
    Unk199_529(542),
    Unk199_530(543),
    Unk200_557(544),
    SpecterEnergy(545),
    SpecterState(546),
    BasicCast(547),
    ScarletCast(548),
    GustCast(549),
    AbyssalCast(550),
    ImpendingDeath(551),
    AbyssalRecall_Old(552),
    ChargeSpellAmplifier(553),
    InfinitySpell(554),
    ConversionOverdrive(555),
    Solus(556),
    AbyssalRecall(557), // AbyssRecall actually v211
    Unk199_544(558),
    Unk199_545(559),
    Unk199_546(560),
    BigBangAttackCharge(561),
    Unk199_548(562),
    TridentStrike(563),
    ComboInstinct(564),
    GaleBarrier(565),
    Unk199_552(566),
    Unk199_553(567),
    SwordOfLight(568),
    PhantomMark(569),
    PhantomMarkMobStat(570),
    Unk200_568(571),
    Unk200_569(572),
    Unk200_570(573),
    KeyDownCTS(574), // Used in Keydown skills: 400011028, 37121052, 37121003<U202C>, 400051024, 11121052, 400011091,  more
    Unk200_572(575), // Alliance Snow
    Unk200_573(576),
    Unk200_574(577),
    Unk202_575(578),
    Unk202_576(579),
    TanadianRuin(580),
    AeonianRise(581),
    Unk202_579(582),
    Unk202_580(583),
    Unk205_583(584),
    Unk205_584(585),
    Unk205_585(586), // charging dark blue aura...
    AncientGuidance(587), // Ancient Guidance Mode   Mode and Stack should be encoded (x,y)
    Unk205_587(588),
    Unk205_588(589),
    Unk205_589(590),
    Unk205_590(591), // affected effect
    Unk205_591(592), // flashing
    Unk205_592(593), // affected effect
    Unk210_593(594), // new v210?
    Unk205_593(595), // cannot move

    Unk208_594(596), // new CTS v208 start (7 added)
    Unk208_595(597),
    Unk208_596(598),
    Unk208_597(599),
    Unk208_598(600),
    Unk208_599(601), // looks like scroll UI?

    TalismanEnergy(602), // Hoyoung Start? (new CTS v209~v210 start (1+10 added))
    ScrollEnergy(603), // Hoyoung Spell/Talisman Gauge
    TalismanClone(604),
    CloneRampage(605),
    ButterFlyDream(606),
    MiracleTonic(607),
    Unk210_607(608),
    Unk210_608(609),
    Unk210_609(610),
    Unk210_610(611), // new CTS v210 end

    // +10 new CTS v214
    unk214_1(612),
    unk214_2(613),
    unk214_3(614),

    //Adele CTS Block
    AetherGauge(615), //Ether
    Creation(616), //Creation
    AetherGuard(617),
    Restore(618), //Restore
    unk214_10(619),
    Nobility(620), //Nobility
    Adele_Resonance(621), //Resonance

    // new 214 end

    Unk212_612(622), // *
    Unk212_613(623),
    Unk212_614(624),
    Unk212_615(625),
    Unk212_616(626),

    Unk208_600(627),
    Unk199_555(628),
    Unk199_556(629),
    Unk199_557(630),
    Unk199_558(631),
    Unk199_559(632),

    HayatoStance(633),
    HayatoStanceBonus(634),
    EyeForEye(635),
    WillowDodge(636),

    Unk176_471(637),
    HayatoPAD(638),
    HayatoHPR(639),
    HayatoMPR(640),
    HayatoBooster(641),
    Unk176_476(642),
  //  Unk176_477(643),
    Jinsoku(643),

    HayatoCr(644),
    HakuBlessing(645),
    HayatoBoss(646),
    BattoujutsuAdvance(647),
    Unk176_483(648),
    Unk176_484(649),
    BlackHeartedCurse(650),

    BeastMode(651),
    TeamRoar(652),
    Unk176_488(653),
    Unk176_489(654),
    Unk176_490(655),
    Unk176_491(656),
    Unk176_492(657),
    Unk176_493(658),
    WaterSplashEventMarking(659),

    WaterSplashEventMarking2(660),
    WaterSplashEventCombo(661),
    WaterSplashEventWaterDripping(662),
    Unk188_539(663),
    YukiMusumeShoukan(664),
    IaijutsuBlade(665),
    Unk199_594(666),
    Unk199_595(667),
    Unk199_596(668),
    Unk199_597(669),
    BroAttack(670),
    LiberatedSpiritCircle(671),
    Unk205_639(672),
    Unk207_640(673),
    Unk208_648(674), //Should be 674


    EnergyCharged(676),
    DashSpeed(677),
    DashJump(678),
    RideVehicle(679),
    PartyBooster(680),
    GuidedBullet(681),
    Undead(682),
    RideVehicleExpire(683),
    RelicGauge(684),
    SecondAtomLockOn(685),

    None(-1),
    ;

    private int bitPos;
    private int val;
    private int pos;
    public static final int length = 33;
    private static final Logger log = LogManager.getLogger(CharacterTemporaryStat.class);



    public static final List<CharacterTemporaryStat> ORDER = Arrays.asList(
            STR, INT, DEX, LUK, PAD, DEF, MAD, ACC, EVA, EVAR,
            Craft, Speed, Jump, EMHP, EMMP, EPAD, EMAD, EPDD, MagicGuard, DarkSight,
            Booster, PowerGuard, Guard, MaxHP, MaxMP, Invincible, SoulArrow, Stun, Shock, Poison,
            Seal, Darkness, ComboCounter, WorldReaver, HammerOfTheRighteous, BigHammerOfTheRighteous, WeaponCharge, ElementalCharge, HolySymbol, MesoUp,
            ShadowPartner, PickPocket, MesoGuard, Thaw, Weakness, WeaknessMdamage, Curse, Slow, TimeBomb, BuffLimit,
            Team, DisOrder, Thread, Morph, Unk111, Regen, BasicStatUp, Stance, SharpEyes, ManaReflection,
            Attract, Magnet, MagnetArea, GuidedArrow, SpiritFlow, LucentBrand, ExtraSkillCTS, Unk199_256, TideOfBattle, NoBulletConsume,
            StackBuff, Trinity, Infinity, AdvancedBless, IllusionStep, Blind, Concentration, BanMap, MaxLevelBuff, Unk112,
            DojangShield, ReverseInput, MesoUpByItem, Ghost, Barrier, ItemUpByItem, RespectPImmune, RespectMImmune, DefenseAtt, DefenseState,
            DojangBerserk, DojangInvincible, SoulMasterFinal, WindBreakerFinal, ElementalReset, HideAttack, EventRate, ComboAbilityBuff, ComboDrain, ComboBarrier,
            PartyBarrier, BodyPressure, RepeatEffect, ExpBuffRate, StopPortion, StopMotion, Fear, MagicShield, MagicResistance, SoulStone,
            Flying, NewFlying, NaviFlying, Frozen, Frozen2, Web, Enrage, NotDamaged, FinalCut, HowlingAttackDamage,
            BeastFormDamageUp, Dance, OnCapsule, HowlingCritical, HowlingMaxMP, HowlingDefence, HowlingEvasion, Conversion, Sneak, Mechanic,
            DrawBack, BeastFormMaxHP, Dice, BlessingArmor, BlessingArmorIncPAD, DamR, TeleportMasteryOn, CombatOrders, Beholder, DispelItemOption,
            DispelItemOptionByField, Inflation, OnixDivineProtection, Bless, Explosion, DarkTornado, IncMaxHP, IncMaxMP, PVPDamage, PVPDamageSkill,
            PvPScoreBonus, PvPInvincible, PvPRaceEffect, HolyMagicShell, InfinityForce, AmplifyDamage, KeyDownTimeIgnore, MasterMagicOn, AsrR, AsrRByItem,
            TerR, DamAbsorbShield, Roulette, Event, SpiritLink, CriticalBuff, DropRate, PlusExpRate, ItemInvincible, ItemCritical,
            Event2, VampiricTouch, DDR, IncCriticalDam, IncTerR, IncAsrR, DeathMark, PainMark, UsefulAdvancedBless, Lapidification,
            VampDeath, VampDeathSummon, VenomSnake, CarnivalAttack, CarnivalDefence, CarnivalExp, SlowAttack, PyramidEffect, HollowPointBullet, KeyDownMoving,
            KeyDownAreaMoving, CygnusElementSkill, IgnoreTargetDEF, Invisible, ReviveOnce, AntiMagicShell, EnrageCr, EnrageCrDamMin, BlessOfDarkness, LifeTidal,
            Judgement, DojangLuckyBonus, HitCriDamR, Larkness, SmashStack, ReshuffleSwitch, SpecialAction, ArcaneAim, StopForceAtomInfo, SoulGazeCriDamR,
            SoulRageCount, PowerTransferGauge, AffinitySlug, SoulExalt, HiddenPieceOn, BossShield, MobZoneState, GiveMeHeal, TouchMe, Contagion,
            ComboUnlimited, IgnorePCounter, IgnoreAllCounter, IgnorePImmune, IgnoreAllImmune, FinalJudgement, KnightsAura, IceAura, FireAura,
            VengeanceOfAngel, HeavensDoor, Preparation, BullsEye, IncEffectHPPotion, IncEffectMPPotion, SoulMP, FullSoulMP, SoulSkillDamageUp, BleedingToxin,
            IgnoreMobDamR, Asura, OmegaBlaster, FlipTheCoin, UnityOfPower, Stimulate, ReturnTeleport, CapDebuff, DropRIncrease, IgnoreMobpdpR,
            BdR, Exceed, DiabolikRecovery, FinalAttackProp, ExceedOverload, DevilishPower, OverloadCount, BuckShot, FireBomb, HalfstatByDebuff,
            SurplusSupply, SetBaseDamage, AmaranthGenerator, StrikerHyperElectric, EventPointAbsorb, EventAssemble, StormBringer, ACCR, DEXR, Albatross,
            Translucence, PoseType, LightOfSpirit, ElementSoul, GlimmeringTime, Restoration, ComboCostInc, ChargeBuff, TrueSight, CrossOverChain,
            ChillingStep, Reincarnation, DotBasedBuff, BlessEnsenble, ExtremeArchery, QuiverCatridge, AdvancedQuiver, UserControlMob, ImmuneBarrier, ArmorPiercing,
            ZeroAuraStr, ZeroAuraSpd, CriticalGrowing, RelicEmblem, QuickDraw, BowMasterConcentration, TimeFastABuff, TimeFastBBuff, GatherDropR, AimBox2D,
            CursorSniping, DebuffTolerance, Unk203_374,  DotHealHPPerSecond, SpiritGuard, PreReviveOnce, SetBaseDamageByBuff, LimitMP, ReflectDamR,
            ComboTempest, MHPCutR, MMPCutR, SelfWeakness, ElementDarkness, FlareTrick, Ember, Dominion, SiphonVitality, DarknessAscension,
            BossWaitingLinesBuff, DamageReduce, ShadowServant, ShadowIllusion, AddAttackCount, ComplusionSlant, JaguarSummoned, JaguarCount, SSFShootingAttack, DevilCry,
            ShieldAttack, BMageAura, DarkLighting, AttackCountX, BMageDeath, BombTime, NoDebuff, XenonAegisSystem, AngelicBursterSoulSeeker, HiddenPossession,
            NightWalkerBat, NightLordMark, WizardIgnite, BattlePvPHelenaMark, BattlePvPHelenaWindSpirit, BattlePvPLangEProtection, BattlePvPLeeMalNyunScaleUp, BattlePvPRevive, Unk188_419, Unk188_420, PinkbeanAttackBuff,
            RandAreaAttack, Unk200_442, BattlePvPMikeShield, BattlePvPMikeBugle, PinkbeanRelax, PinkbeanYoYoStack, WindEnergy, NextAttackEnhance, AranCombotempastOption,
            NautilusFinalAttack, ViperTimeLeap, RoyalGuardState, RoyalGuardPrepare, MichaelSoulLink, MichaelStanceLink, TriflingWhimOnOff, AddRangeOnOff, KinesisPsychicPoint, KinesisPsychicOver,
            KinesisPsychicShield, KinesisIncMastery, KinesisPsychicEnergeShield, BladeStance, DebuffActiveSkillHPCon, DebuffIncHP, BowMasterMortalBlow, AngelicBursterSoulResonance, Fever, IgnisRore,
            TeleportMasteryRange, FireBarrier, ChangeFoxMan, FixCoolTime, IncMobRateDummy, AdrenalinBoost, AranSmashSwing, AranDrain, AranBoostEndHunt, HiddenHyperLinkMaximization,
            RWCylinder, RWCombination, Unk188_460, RWMagnumBlow, RWBarrier, RWBarrierHeal, RWMaximizeCannon, RWOverHeat, RWMovingEvar, Stigma,
            MahasFury, RuneCooltime, Unk188_471, Unk188_472, /*Unk188_473,*/ Unk188_474, MeltDown, SparkleBurstStage, LightningCascade, BulletParty,
            Unk188_479, AuraScythe, Benediction, VoidStrike, ReduceHitDmgOnlyHPR, Unk199_493, DivineEcho, WeaponAura, ManaOverload, RhoAias, PsychicTornado,
            SpreadThrow, MassDestructionRockets, ShadowAssault, Unk188_492, Unk188_494, BlitzShield, SplitShot, FreudBlessing, OverloadMode, MastemasMark,
            WingsOfGlory, RiftOfDamnation, Unk188_500, SpotLight, Cyclone, MuscleMemory, TrickBladeMobStat, Overdrive, EtherealForm, LastResort,
            ViciousShot, Unk176_466, Unk200_527, Unk200_528, ImpenetrableSkin, RadiantOrb, Unk199_520, Unk199_521, Resonance, FlashCrystalBattery,
            Unk199_524, CrystalBattery, Unk199_526, Unk199_527, Unk199_528, Unk199_529, Unk199_530, Unk200_557, SpecterEnergy, SpecterState,
            BasicCast, ScarletCast, GustCast, AbyssalCast, ImpendingDeath, AbyssalRecall_Old, ChargeSpellAmplifier, InfinitySpell, ConversionOverdrive, Solus,
            AbyssalRecall, Unk199_544, Unk199_545, Unk199_546, Unk199_548, TridentStrike, ComboInstinct, GaleBarrier, GrandGuardian, QuiverBarrage,
            Unk199_552, SwordsOfConsciousness, Unk199_553, SwordOfLight, PhantomMark, PhantomMarkMobStat, PrimalGrenade, DemonicFrenzy, Unk200_568, Unk200_569,
            Unk200_260, Unk200_431, Unk200_570, KeyDownCTS, Unk200_572, Unk200_573, Unk200_574, Unk202_575, Unk202_576, TanadianRuin,
            AeonianRise, Unk202_579, Unk202_580, Unk205_583, Unk205_584, Unk205_585, AncientGuidance, Unk205_587, Unk205_588, Unk205_589,
            Unk205_590, Unk205_591, Unk205_592, Unk210_593, Unk205_593, Unk208_594, Unk208_595, Unk208_596, Unk208_597, Unk208_598, Unk208_599,
            TalismanEnergy, ScrollEnergy, TalismanClone, MiracleTonic, CloneRampage, Unk210_607, Unk210_608, Unk210_609, Unk210_610, unk214_1,
            unk214_2, unk214_3, AetherGauge, Creation, AetherGuard, Restore, Nobility, Adele_Resonance, unk214_10,
            Unk212_612, Unk212_613, Unk212_614, Unk212_615, Unk212_616, Unk208_600,
            IncMaxDamage, Unk199_555, Unk199_556, Unk199_558, Unk199_559, IndieHyperStat, HayatoStance, HayatoBooster, HayatoStanceBonus,
            WillowDodge, Unk176_471, HayatoPAD, HayatoHPR, HayatoMPR, Jinsoku, HayatoCr, HakuBlessing, HayatoBoss, BattoujutsuAdvance,
            Unk176_483, Unk176_484, BlackHeartedCurse, EyeForEye, BeastMode, TeamRoar, Unk176_488, Unk176_489, Unk176_493, WaterSplashEventMarking,
            WaterSplashEventMarking2, WaterSplashEventCombo, WaterSplashEventWaterDripping, Unk188_539, YukiMusumeShoukan, IaijutsuBlade, Unk199_595, Unk199_596, Unk199_597, BroAttack,
            LiberatedSpiritCircle, Unk207_640, Unk208_648
    );


    private static final List<CharacterTemporaryStat> REMOTE_ORDER = Arrays.asList(
            Speed, ComboCounter, WorldReaver, HammerOfTheRighteous, WeaponCharge, ElementalCharge, Stun, Shock,
            Darkness, Seal, Weakness, WeaknessMdamage, Curse, Slow, PvPRaceEffect, TimeBomb,
            Team, DisOrder, Thread, Poison, Poison, ShadowPartner, DarkSight, SoulArrow,
            Morph, Unk111, Attract, Magnet, MagnetArea, NoBulletConsume, BanMap, Unk112,
            DojangShield, ReverseInput, RespectPImmune, RespectMImmune, DefenseAtt, DefenseState, DojangBerserk, DojangInvincible,
            RepeatEffect, Unk176_483, StopPortion, StopMotion, Fear, MagicShield, Flying, Frozen,
            Frozen2, Web, DrawBack, FinalCut, OnCapsule, OnCapsule, Sneak, BeastFormDamageUp,
            Mechanic, BlessingArmor, BlessingArmorIncPAD, Inflation, Explosion, DarkTornado, AmplifyDamage, HideAttack,
            HolyMagicShell, DevilishPower, SpiritLink, Event, VampiricTouch, DeathMark, PainMark, Lapidification,
            VampDeath, VampDeathSummon, VenomSnake, PyramidEffect, KillingPoint, PinkbeanRollingGrade, IgnoreTargetDEF, Invisible,
            Judgement, KeyDownAreaMoving, StackBuff, BlessOfDarkness, Larkness, ReshuffleSwitch, SpecialAction, StopForceAtomInfo,
            SoulGazeCriDamR, PowerTransferGauge, BlitzShield, AffinitySlug, SoulExalt, HiddenPieceOn, SmashStack, MobZoneState,
            GiveMeHeal, TouchMe, Contagion, Contagion, ComboUnlimited, IgnorePCounter, IgnoreAllCounter, IgnorePImmune,
            IgnoreAllImmune, FinalJudgement, KnightsAura, IceAura, FireAura, VengeanceOfAngel, HeavensDoor,
            DamAbsorbShield, AntiMagicShell, NotDamaged, BleedingToxin, WindBreakerFinal, IgnoreMobDamR, Asura, OmegaBlaster,
            UnityOfPower, Stimulate, ReturnTeleport, CapDebuff, OverloadCount, FireBomb, SurplusSupply, NewFlying,
            NaviFlying, AmaranthGenerator, CygnusElementSkill, StrikerHyperElectric, EventPointAbsorb, EventAssemble, Albatross, Translucence,
            PoseType, LightOfSpirit, ElementSoul, GlimmeringTime, Reincarnation, Beholder, QuiverCatridge, ArmorPiercing,
            UserControlMob, ZeroAuraStr, ZeroAuraSpd, ImmuneBarrier, ImmuneBarrier, FullSoulMP, AntiMagicShellEx, Dance,
            SpiritGuard, MastemasMark, ComboTempest, HalfstatByDebuff, ComplusionSlant, JaguarSummoned, BMageAura, BombTime,
            MeltDown, SparkleBurstStage, LightningCascade, BulletParty, Unk188_479, AuraScythe, Benediction, DarkLighting,
            AttackCountX, FireBarrier, KeyDownMoving, MichaelSoulLink, KinesisPsychicEnergeShield, BladeStance, BladeStance, Fever,
            AdrenalinBoost, RWBarrier, Unk188_460, RWMagnumBlow, GuidedArrow, SpiritFlow, LucentBrand, ExtraSkillCTS,
            Unk199_256, Stigma, DivineEcho, RhoAias, PsychicTornado, MahasFury, ManaOverload, CursorSniping, CloneRampage,
            Unk188_500, SpotLight, OverloadMode, FreudBlessing, BigHammerOfTheRighteous, Overdrive, EtherealForm, LastResort,
            ViciousShot, Unk176_466, Unk200_527, Unk200_528, ImpenetrableSkin, WingsOfGlory, Unk199_520, Unk199_520,
            Unk199_521, Resonance, FlashCrystalBattery, SpecterState, ImpendingDeath, Unk199_545, Unk199_521Ex, SwordOfLight,
            GrandGuardian, Unk205_584, Unk205_585, Unk205_588, Unk205_589, Unk205_590, Unk205_591, Unk205_593,
            Unk208_598, TanadianRuin, AeonianRise, Unk210_610, Unk212_612, Unk199_555, BeastMode, TeamRoar, HayatoStance, HayatoBooster, HayatoStanceBonus,
            HayatoPAD, HayatoHPR, HayatoMPR, HayatoCr, HayatoBoss, Stance, BattoujutsuAdvance, Unk176_484,
            BlackHeartedCurse, EyeForEye, Unk176_489, Unk176_493, WaterSplashEventMarking, WaterSplashEventMarking2,
            WaterSplashEventWaterDripping, Unk188_539, YukiMusumeShoukan, Unk199_559, Unk199_559, SplitShot

    );



    CharacterTemporaryStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    CharacterTemporaryStat(int bitPos) {
        this.bitPos = bitPos;
        this.val = 1 << (31 - bitPos % 32);
        this.pos = bitPos / 32;
    }

    public static CharacterTemporaryStat getByBitPos(int bitPos) {
        return Arrays.stream(values()).filter(v -> v.getBitPos() == bitPos).findAny().orElse(null);
    }

    public boolean isEncodeInt() {
        switch (this) {
            case RideVehicle:
            case RideVehicleExpire:
            case CarnivalDefence:
            case SpiritLink:
            case DojangLuckyBonus:
            case SoulGazeCriDamR:
            case PowerTransferGauge:
            case ReturnTeleport:
            case ShadowPartner:
            case AranSmashSwing:
            case SetBaseDamage:
            case QuiverCatridge:
            case ImmuneBarrier:
            case NaviFlying:
            case Dance:
            case SetBaseDamageByBuff:
            case DotHealHPPerSecond:
            case Unk203_374:
            case IncMaxDamage:
            case Unk176_493:
            case Magnet:
            case MagnetArea:
            case DivineEcho:
            case OmegaBlaster:
            case VampDeath:
            case VampDeathSummon:
            case BlitzShield:
            case Cyclone:
            case RWBarrier:
            case Unk205_584:

                // EXPERIMENTAL *******
                // case unk214_1:

            /*
            case Unk199_527: //?
            case Unk199_528: //?
            case IndieKeyDownTime:
            case EnergyCharged:
            case DashSpeed:
            case DashJump:
            case PartyBooster:
            case GuidedBullet:
            case Undead:
            */
                return true;
            default:
                return false;
        }
    }

    public boolean isIndie() {
        return ordinal() < IndieStatCount.ordinal();
    }

    public boolean isMovementAffectingStat() {
        switch (this) {
            case Speed:
            case Jump:
            case Stun:
            case Weakness:
            case Slow:
            case Morph:
            case Ghost:
            case BasicStatUp:
            case Attract:
            case RideVehicle:
            case RideVehicleExpire:
            case DashSpeed:
            case DashJump:
            case Flying:
            case Frozen:
            case Frozen2:
            case Lapidification:
            case IndieSpeed:
            case IndieJump:
            case KeyDownMoving:
            case EnergyCharged:
            case Mechanic:
            case Magnet:
            case MagnetArea:
            case VampDeath:
            case VampDeathSummon:
            case GiveMeHeal:
            case DarkTornado:
            case NewFlying:
            case NaviFlying:
            case UserControlMob:
            case Dance:
            case SelfWeakness:
            case BattlePvPHelenaWindSpirit:
            case Indie205_56:
            case BattlePvPLeeMalNyunScaleUp:
            case TouchMe:
            case IndieForceSpeed:
            case IndieForceJump:
            case Unk199_528:
            case Unk199_529:
            case Unk205_587:
            case Unk205_588:
            case Unk208_598:
            /*
            case EtherealForm:
            case MeltDown:
             */
                return true;
            default:
                return false;
        }
    }

    public int getVal() {
        return val;
    }

    public int getPos() {
        return pos;
    }

    public int getOrder() {
        return ORDER.indexOf(this);
    }

    public int getRemoteOrder() {
        return REMOTE_ORDER.indexOf(this);
    }

    public boolean isRemoteEncode4() {
        switch (this) {
            case NoBulletConsume:
            case RespectPImmune:
            case RespectMImmune:
            case DefenseAtt:
            case DefenseState:
            case MagicShield:
            case PyramidEffect:
            case BlessOfDarkness:
            case ImmuneBarrier:
            case Dance:
            case OmegaBlaster:
            case SpiritGuard:
            case KinesisPsychicEnergeShield:
            case AdrenalinBoost:
            case RWBarrier:
            case RWMagnumBlow:
            case Unk176_493:
            case MahasFury:
            case ManaOverload:
            case PsychicTornado:
            case Unk199_521:
            case YukiMusumeShoukan:
            case WaterSplashEventMarking:
            case WaterSplashEventMarking2:
            case DivineEcho:
            case MastemasMark:
            case RhoAias:
                //case FullSoulMP:
                return true;
            default:
                return false;
        }
    }

    public boolean isRemoteEncode1() {
        switch (this) {
            case Speed:
            case ComboCounter:
            case WorldReaver:
            case Shock:
            case Team:
            case OnCapsule:
            case KillingPoint:
            case PinkbeanRollingGrade:
            case ReturnTeleport:
            case FireBomb:
            case SurplusSupply:
            case Unk176_466:
                return true;
            default:
                return false;
        }
    }

    public boolean isNotEncodeReason() {
        switch (this) {
            case Speed:
            case ComboCounter:
            case ElementalCharge:
            case WorldReaver:
            case Shock:
            case Team:
            case Ghost:
            case NoBulletConsume:
            case RespectPImmune:
            case RespectMImmune:
            case DefenseAtt:
            case DefenseState:
            case MagicShield:
            case OnCapsule:
            case PyramidEffect:
            case KillingPoint:
            case PinkbeanRollingGrade:
            case StackBuff:
            case BlessOfDarkness:
            case SurplusSupply:
            case ImmuneBarrier:
            case AdrenalinBoost:
            case RWBarrier:
            case RWMagnumBlow:
            case ManaOverload:
            case MahasFury:
            case PsychicTornado:
            case YukiMusumeShoukan:
            case WaterSplashEventMarking:
            case WaterSplashEventMarking2:
            case Unk176_466:
                return true;
            default:
                return false;
        }
    }

    public boolean isNotEncodeAnything() {
        switch (this) {
            case DarkSight:
            case SoulArrow:
            case DojangInvincible:
            case Flying:
            case Sneak:
            case BeastFormDamageUp:
            case BlessingArmor:
            case BlessingArmorIncPAD:
            case HolyMagicShell:
            case VengeanceOfAngel:
            case SwordOfLight:
            case None:
            case FullSoulMP:
                return true;
            default:
                return isSeperatedDuplicate();
        }
    }

    public static final CharacterTemporaryStat[] isAuraCTS = new CharacterTemporaryStat[] {
            BMageAura,
            Benediction,
            KnightsAura,
            MichaelSoulLink,
    };

    public static final EnumSet<CharacterTemporaryStat> RESET_BY_TIME_CTS = EnumSet.of(
            Stun, Shock, Poison, Seal, Darkness, Weakness, WeaknessMdamage, Curse, Slow, /*TimeBomb,*/
            DisOrder, Thread, Attract, Magnet, MagnetArea, ReverseInput, BanMap, StopPortion, StopMotion,
            Fear, Frozen, Frozen2, Web, NotDamaged, FinalCut, Lapidification, VampDeath, VampDeathSummon,
            GiveMeHeal, TouchMe, Contagion, ComboUnlimited, CrossOverChain, Reincarnation, ComboCostInc,
            DotBasedBuff, ExtremeArchery, QuiverCatridge, AdvancedQuiver, UserControlMob, ArmorPiercing,
            CriticalGrowing, QuickDraw, BowMasterConcentration, ComboTempest, SiphonVitality, KnockBack, RWMovingEvar);

    public final boolean isScheduledNoReasonStat() {
        switch (this) {
            case WindEnergy:
            case SpecterEnergy:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int compare(CharacterTemporaryStat o1, CharacterTemporaryStat o2) {
        if (o1.getPos() < o2.getPos()) {
            return -1;
        } else if (o1.getPos() > o2.getPos()) {
            return 1;
        }
        // hacky way to circumvent java not having unsigned ints
        int o1Val = o1.getVal();
        if (o1Val == 0x8000_0000) {
            o1Val = Integer.MAX_VALUE;
        }
        int o2Val = o2.getVal();
        if (o2Val == 0x8000_0000) {
            o2Val = Integer.MAX_VALUE;
        }

        if (o1Val > o2Val) {
            // bigger value = earlier in the int => smaller
            return -1;
        } else if (o1Val < o2Val) {
            return 1;
        }
        return 0;
    }

    public int getBitPos() {
        return bitPos;
    }



    public static void main(String[] args) {
//        getBitPosByString("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 80 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 10 40 21 00 01 00 87 93 03 00 87 00 00 00 00 00 00 00 00 00 00 00"
//        );
        changeCts();
    }

    private static void getBitPosByString(String str) {
        byte[] bArr = Util.getByteArrayByString(str);
        int[] iArr = new int[bArr.length / 4];
        for (int i = 0; i < bArr.length; i += 4) {
            if (i + 3 >= bArr.length) {
                break;
            }
            iArr[i / 4] |= bArr[i];
            iArr[i / 4] |= bArr[i + 1] << 8;
            iArr[i / 4] |= bArr[i + 2] << 16;
            iArr[i / 4] |= bArr[i + 3] << 24;
        }
        for (int i = 0; i < iArr.length; i++) {
            int mask = iArr[i];
            for (CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                if (cts.getPos() == i && (cts.getVal() & mask) != 0) {
                    log.error(String.format("Contains stat %s", cts.toString()));
                }
            }
        }
    }

    public boolean requiresDuplicate() {
        return this == Unk199_521 || this == AntiMagicShell;
    }

    public boolean isSeperatedDuplicate() {
        switch (this) {
            case AntiMagicShellEx:
            case Unk199_521Ex:
                return true;
        }
        return false;
    }

    public CharacterTemporaryStat getDuplicateCts() {
        switch (this) {
            case AntiMagicShell:
                return AntiMagicShellEx;
            case Unk199_521:
                return Unk199_521Ex;
        }
        return null;
    }

    public CharacterTemporaryStat getOriginalFromDuplicate() {
        switch (this) {
            case AntiMagicShellEx:
                return AntiMagicShell;
            case Unk199_521Ex:
                return Unk199_521;
        }
        return null;
    }


    private static void changeCts() {
        File file = new File(ServerConstants.DIR + "\\src\\main\\java\\net\\ristonia\\ms\\client\\character\\skills\\temp\\CharacterTemporaryStat.java");
        int change = -1;
        CharacterTemporaryStat checkOp = null;
        try(Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(",") && line.contains("(")) {
                    String[] split = line.split("[()]");
                    String name = split[0];
                    if (!Util.isNumber(split[1])) {
                        System.out.println(line);
                        continue;
                    }
                    int val = Integer.parseInt(split[1]);
                    CharacterTemporaryStat ih = Arrays.stream(CharacterTemporaryStat.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (ih != null) {
                        CharacterTemporaryStat start = Unk212_612;
                        if (ih.ordinal() >= start.ordinal() && ih.ordinal() <= Unk208_648.ordinal()) {
                            if (line.contains("*")) {
                                checkOp = ih;
                            }
                            val += change;
                            System.out.println(String.format("%s(%d), %s", name, val, start == ih ? "// *" : ""));
                        } else {
                            System.out.println(line);
                        }
                    } else {
                        System.out.println(line);
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (checkOp != null) {
            System.err.println(String.format("Current op (%s) contains a * (= updated). Be sure to check for overlap.", checkOp));
        }
    }
}