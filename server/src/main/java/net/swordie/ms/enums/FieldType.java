package net.swordie.ms.enums;

import java.util.Arrays;

public enum FieldType {
    // Thank you KMST leak <3
    DEAFULT(0),
    SNOWBALL(1),
    CONTIMOVE(2),
    TOURNAMENT(3),
    COCONUT(4),
    OX_QUIZ(5),
    PERSONAL_TIME_LIMIT(6),
    WAITING_ROOM(7),
    GUILD_BOSS(8),
    LIMITED_VIEW(9),
    MONSTER_CARNIVAL_S2(10),
    MONSTER_CARNIVAL_REVIVE(11),
    ZAKUM(12),
    ARIANT_ARENA(13),
    DO_JANG(14),// DOJO
    MONSTER_CARNIVAL_NOT_USE(15),
    MONSTER_CARNIVAL_WAITING_ROOM(16),
    COOKIE_HOUSE(17),
    BALROG(18),
    BATTLE_FIELD(19),
    SPACE_GAGA(20),
    WITCH_TOWER(21),
    TUTORIAL(22),
    MASSACRE(23),
    MASSACRE_RESULT(24),
    PARTY_RAID(25),
    PARTY_RAID_BOSS(26),
    PARTY_RAID_RESULT(27),
    NO_DRAGON(28),
    DYNAMIC_FOOTHOLD(29),
    ESCORT(30),
    ESCORT_RESULT(31),
    HUNTING_A_DBALLOON(32),
    CHAOS_ZAKUM(33),
    KILL_COUNT(34),
    // 35 nothing
    PVP(36),
    WAITING_PARTY_QUEST(37),
    DEFENSE(38),// pyramid
    JULDARIGI(39),
    LITTLE_INDIAN(40),// JungleBook?
    CASH_MINI_DUNGEON(41),
    CON_QUEST(42),
    CON_QUEST_WAITING_PARTY(43),
    MONSTER_PARK_BONUS(44),
    HILLAH(45),// ASWAN
    RANDOM_DROP(46),
    SUMMON_EVENT(47),
    GLISSAM(48),
    MULTI_STAGE(49),
    BASEBALLDROP(50),
    DEATHCOUNT(51),
    COOK(52),
    FLYINGKITEDROP(53),
    FADED_CITADEL(54),// no idea and not exists in KMST leak
    GIANT_BOSS_FIELD(55),// not exists in KMST leak
    SAIL_FIELD(56),// not exists in KMST leak
    PARTY_SAIL_FIELD(57),// not exists in KMST leak
    BEAST_TAMER_TUTORIAL(58),// not exists in KMST leak cuz they don't have beast tamer.
    // 59 nothing
    WEDDING(60),
    WEDDING_PHOTO(61),
    PIERRE_SUMMON(62),// not exists in KMST leak
    BAN_BAN_SUMMON(63),// not exists in KMST leak
    // 64 nothing
    VELUM_SUMMON(65),// not exists in KMST leak
    RHYTHM_GAME(66),
    FPS_MODE(67),
    MOUNTAIN_RIDING(68),
    FABRES_NIPING(69),
    PIRANHA(70),
    ROBBINS(71),
    FALLING_STONE(72),
    HEKATON(73),
    ZERO_TUTORIAL(74),
    BOSS_ARENA(75),
    INVASION(76),
    DUNGEON(77),// not sure & not exists in KMST leak
    // 78 nothing
    // 79 nothing
    // 80 nothing
    SPACE_MOON_RABBIT(81),
    CATAPULT(82),
    SPACE_MOON_RABBIT_BONUS(83),
    CAPTURE_THE_FLAG(84),
    HUNDRED_BINGO(85),
    BONUS_REWARD(86),
    TYPING_GAME(87),
    HUNDRED_OX_QUIZ(88),
    CUSTOM_IMPACT_PORTAL_USABLE(89),
    COORDY(90),
    // 91 nothing
    HIEIZAN_FIELD(92),// not sure & not exists in KMST leak
    HIEIZAN_FIELD_2(93),// not sure & not exists in KMST leak
    // 94 nothing
    SAD(95),
    SOUL(96),
    // 97 nothing
    // 98 nothing
    // 99 nothing
    STAR_PLANET_RPS(100),
    BIZARRE_ALIEN_DIMENSION(101),// not exists in KMST leak
    SIDE_SCROLL_FLY_SHOOTING(102),
    FPS_MODE_LEAF_GAME(103),
    // 104 nothing
    // 105 nothing
    // 106 nothing
    // 107 nothing
    STAR_PLANET_BATTLE(108),
    RUNNING_GAME(109),
    // 110 nothing
    CLIMB_MINI_GAME(111),// not exists in KMST leak
    BRIDGE_JUMP_MINI_GAME(112),// not exists in KMST leak
    WU_TIEN_TIME_ATTACK(113),// not exists in KMST leak
    MOMENT_SWIM_AREA(114),
    VERTICAL_RUNAWAY(115),
    _12TH_TRESURE(116),
    _12TH_TRESURE_SAT(117),
    PIGGY_BAR(118),
    // 119 nothing
    // 120 nothing
    URUS(121),
    DOJANG_MINI_BODY_TRAINING(122),// Mu Lung Dojo Unity Training Center
    GHOST_PARK(123),
    COW_GAME(124),
    FPS_MODE_LEAF_GAME_KR(125),
    MINI_GAME_PINBALL_KR(126),
    ROLE_PLAYING_KR(127),
    POLO_FRITO_BOUNTY_HUNTING(128),
    POLO_FRITO_TOWN_DEFENSE(129),
    POLO_FRITO_KILL_MONSTER(130),
    POLO_FRITO_EAGLE_HUNTING(131),
    POLO_FRITO_STEAL_DRAGON_EGG(132),
    POLO_FRITO_COURT_SHIP_DANCE(133),
    // 134 nothing
    POLO_FRITO_FLAME_WOLF(135),
    // 136 nothing
    WADDLER(137),
    MOONLIGHT_FIELD(138),// not exists in KMST leak
    // 139 nothing
    // 140 nothing
    KAPUS(141),
    TRADE_KING(142),
    PLAT_PORMER_OXYGEN(143),
    DEMIAN(144),
    UNK_145(145),
    UNK_146(146),
    // 147
    // 148
    // 149
    MAPLE_MUSIC_FESTIVAL(150),// not exists in KMST leak
    UNK_151(151),
    COUNT_OF_FIELDTYPE(152);
    private final int val;

    FieldType(int val) {
        this.val = val;
    }

    public static FieldType getByVal(int val) {
        return Arrays.stream(values()).filter(ft -> ft.val == val).findFirst().orElse(null);
    }

    public int getVal() {
        return val;
    }
}
