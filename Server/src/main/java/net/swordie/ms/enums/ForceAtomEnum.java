package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1/7/2018.
 */
public enum ForceAtomEnum {
    DEMON_SLAYER_FURY_1(0,2), //Fury 1st ~ 3rd Job
    DEMON_SLAYER_FURY_2(0,4), //Fury 4th Job
    DEMON_SLAYER_FURY_1_BOSS(0,6), //Fury from Bosses (1st ~ 3rd Job)
    DEMON_SLAYER_FURY_2_BOSS(0,10), //Fury from Bosses (4th Job)

    PHANTOM_CARD_1(1, 1), //Phantom - Carte Blanch (2nd Job)
    PHANTOM_CARD_2(1, 2), //Phantom - Carte Noir (4th Job)

    KAISER_WEAPON_THROW_1(2, 1), //Kaiser 3 Swords (2nd Job)
    KAISER_WEAPON_THROW_2(2, 2), //Kaiser 5 Swords (4th Job)
    KAISER_WEAPON_THROW_MORPH_1(2, 3), //Kaiser 3 Swords Final Form
    KAISER_WEAPON_THROW_MORPH_2(2, 4), //Kaiser 5 Swords Final Form

    AB_ORB(3, 1), //AB - Soul Seeker
    MEGIDDO_FLAME(3, 2), //Megiddo Flame (mage FP Lv170 Hyper)
    NETHER_SHIELD(3, 3), //Nether Shield
    RABBIT_ORB(3, 4), // Shade
    FLAMING_RABBIT_ORB(3, 5), // same for 4, but insta disappear (byMob?)
    FLAME_DISCHARGE(3, 6), // Blaze Wizard V Skill

    AB_ORB_RECREATION(4, 1), //AB - Soul Seeker - Recreation
    MEGIDDO_FLAME_RECREATION(4, 2),
    NETHER_SHIELD_RECREATION(4, 3),
    RABBIT_ORB_RECREATION(4, 4),
    FLAMING_RABBIT_ORB_RECREATION(4, 5),
    FLAME_DISCHARGE_RECREATION(4, 6),

    XENON_ROCKET_1(5, 1), //Xenon Aegis System Rockets
    XENON_ROCKET_2(5, 2), //Xenon Aegis System Rockets

    XENON_ROCKET_3(6, 1), //Xenon Pinpoint Salvo

    WA_ARROW_1(7, 1), //WA Green Arrow
    WA_ARROW_2(7, 2), //WA Purple Arrow

    WA_ARROW_HYPER(8, 1), //WA Hyper Arrow

    KANNA_ORB_1(9, 1), // to char       isMobStartForceAtom
    KANNA_ORB_2(9, 2), // to char       isMobStartForceAtom

    BM_ARROW(10, 1), //Magic Arrow from Quiver Cartridge

    ASSASSIN_MARK(11, 1), //Assassin's Mark         isMobStartForceAtom
    NIGHTLORD_MARK(11, 2), //Night Lord's Mark      isMobStartForceAtom

    FLYING_MESO(12, 1), //Flying Meso - Shadower's Meso Explosion

    BLUE_RABBIT_ORB(13, 1), //Shade 2nd Job
    RED_RABBIT_ORB(13, 2), //Shade 4th Job upgrade

    YELLOW_ORB_TO_SELF(14, 1), //Looks similar to Demon Slayer's Fury Orbs but it's yellow instead of blue      isMobStartForceAtom
    YELLOW_ORB_TO_SELF_2(14, 2), //Looks similar to Demon Slayer's Fury Orbs but it's yellow instead of blue      isMobStartForceAtom

    NIGHT_WALKER_BAT(15, 1), //Night Walker Bat
    NIGHT_WALKER_BAT_4(15, 2), //Night Walker Bats(4th)

    NIGHT_WALKER_FROM_MOB(16, 1), //Night Walker Bat                isMobStartForceAtom
    NIGHT_WALKER_FROM_MOB_4(16, 2), //Night Walker Bats (4th Job)   isMobStartForceAtom

    ORBITAL_FLAME_1(17, 1), //Blaze Wizard(1)
    ORBITAL_FLAME_3(17, 2), //Blaze Wizard(3)
    ORBITAL_FLAME_2(17, 3), //Blaze Wizard(2)
    ORBITAL_FLAME_4(17, 4), //Blaze Wizard(4)

    STAR_1(18, 1), //Star (white/blue interior) - Star Planet?
    STAR_2(18, 2), //Star (purple) - Star Planet?
    UNK(18, 3), // ?
    UNK1(18, 4), // ?
    YELLOW_BLACK_ORB(18, 6), //Looks like the Soul orbs from having a Soul Enchanted weapon but these are different colour and bigger
    PURPLE_BLACK_ORB(18, 10), //Looks like the Soul orbs from having a Soul Enchanted weapon but these are different colour and bigger

    MECH_ROCKET(19, 1),

    MECH_MEGA_ROCKET_1(20, 1),
    MECH_MEGA_ROCKET_2(20, 2),

    MECH_MEGA_ROCKET_1_1(21, 1), // Duplicate from  AtomType 20 ?
    MECH_MEGA_ROCKET_2_1(21, 2),

    KINESIS_ORB_REAL(22, 1),
    KINESIS_ORB_REAL_2(22, 2),
    KINESIS_ORB_REAL_3(22, 3),

    WRECKAGE(23, 1), //Evan's Magic Debris
    WRECKAGE_1(23, 2), // ?

    ADV_WRECKAGE(24, 1), //Evan's Advanced Magic Debris
    ADV_WRECKAGE_1(24, 1), // ?

    TRANSPARENT_AB_ORB(25, 1), // same for 26, but that disappears

    TRANSPARENT_AB_ORB_RECREATION(26,1),

    // v188.4  New Force Atoms  ---------------------
    GUIDED_ARROW(27, 1), // Archers V Skill

    DOT_PUNISHER(28, 1), // Mage FP  V Skill

    SPARKLE_BURST_1(29, 1), // AB's V Skill (small green fa)
    SPARKLE_BURST_2(29, 2), // AB's V Skill (medium blue fa)
    SPARKLE_BURST_3(29, 3), // AB's V Skill (large yellow fa)
    GRIM_HARVEST(29, 4), // BaM 2nd V Skill
    ELEMENTAL_RADIANCE(29, 5), // Evan's 3rd V Skill

    INVISIBLE_ATOM(30,1), // Haven't tested yet

    INHUMAN_SPEED(31, 1), // BowMaster's V skill
    GREEN_TORNADO(31, 2), // Wind Archer 3rd V Skill

    KAISER_V_WEAPON_THROW_1(32, 1), // Kaiser V skill 3 Swords (2nd Job)
    KAISER_V_WEAPON_THROW_2(32, 2), // Kaiser V skill 5 Swords (4th Job)
    KAISER_V_WEAPON_THROW_MORPH_1(32, 3), // Kaiser V skill 3 Swords Final Form
    KAISER_V_WEAPON_THROW_MORPH_2(32, 4), // Kaiser V skill 5 Swords Final Form

    ACE_IN_THE_HOLE(33, 1), // Phantom's V skill

    MERCILESS_WINDS(34, 1), // WindArcher V skill

    SPIRIT_DOMAIN(35, 1), // Kanna's 2nd V skill

    // v199.4  New Force Atoms  ---------------------
    RADIANT_JAVELIN(36, 1),
    RADIANT_JAVELIN_2(36, 2),

    GLORY_WING_JAVELIN(37, 1),

    GLORY_WING_JAVELIN_SMALL_2(38, 1),

    RADIANT_JAVELIN_AFTER(39, 1),

    GLORY_WING_JAVELIN_SMALL(39, 1),

    GLORY_WING_WING_BEAT(40, 1),

    GLORY_WING_WING_BEAT_2(41, 1),

    UNK_2(42, 1), // Blue orb
    SHADOW_BITE(42, 2), // NW 3rd V Skill

    BASIC_CHARGE(43, 1),

    SCARLET_CHARGE(44, 1),

    GUST_CHARGE(45, 1),

    ABYSSAL_CHARGE(46, 1),

    IMPENDING_DEATH(47, 1),

    VENGEFUL_HATE(48, 1),

    // v200.1  New Force Atoms  ---------------------
    DARK_LORD_OMEN (49, 1), // Night Lord 3rd V Skill
    DARK_LORD_OMEN_2(49, 2),

    QUIVER_FULL_BURST(50, 1), // Bow Master 3rd V Skill

    GREEN_TORNADO_2(51, 1), // Wind Archer 3rd V Skill

    THROWING_SPEAR(52, 1), // idk (mini game maybe?)

    BULLET_SHOT(53, 1), // idk (mini game maybe?)

    THROWING_POLEARM(54, 1), // idk (mini game maybe?)

    UNK_3(55, 1), // Green/Blue Orb  idk

    CARDINAL_DISCHARGE_1(56, 1), // originates from person (actively used)
    CARDINAL_DISCHARGE_2(56, 2), // originates from person (actively used)

    BOUNTIFUL_DELUGE(57, 1), // originates from person (passively used)
    BOUNTIFUL_BURST(57, 2), // originates from person (passively used)

    SPLIT_MISTEL(58, 1), // originates from mob (created upon mob hit by Split Mistel)
    ANCIENT_ASTRA(58, 2), // originates from mob (created upon mob hit by Ancient Astra (Discharge) )

    YOSUZUME_1(59, 1), // From Kanna
    YOSUZUME_2(59, 2), // From Kanna

    CLONE_ATOM(60, 1),

    BUTTERYFLY_1(61, 1),// HoYoung Butteryfly summon
    BUTTERYFLY_2(61, 2),// HoYoung Butteryfly summon
    BUTTERYFLY_3(61, 3), // HoYoung Butteryfly summon
    BUTTERYFLY_4(61, 4), // HoYoung Butteryfly summon
    BUTTERYFLY_5(61, 5), // HoYoung Butteryfly summon

    SWALLOW_MOB(63, 1), // HoYoung - Evil-Sealing Gourd

    CLONE_R_ATOM(65, 1), // HoYoung - Evil-Sealing Gourd
    ;

    private int forceAtomType;
    private int inc;

    ForceAtomEnum(int forceAtomType, int inc) {
        this.forceAtomType = forceAtomType;
        this.inc = inc;
    }

    public int getForceAtomType() {
        return forceAtomType;
    }

    public void setForceAtomType(int forceAtomType) {
        this.forceAtomType = forceAtomType;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public static ForceAtomEnum getTypeBy(int type) {
        return Arrays.stream(values()).filter(fae -> fae.getForceAtomType() == type).findFirst().orElse(null);
    }

    public static ForceAtomEnum getRecreationType(ForceAtomEnum fae) {
        switch (fae) {
            case AB_ORB:
                return AB_ORB_RECREATION;
            case TRANSPARENT_AB_ORB:
                return TRANSPARENT_AB_ORB_RECREATION;
            case FLAMING_RABBIT_ORB:
                return FLAMING_RABBIT_ORB_RECREATION;
            case FLAME_DISCHARGE:
                return FLAME_DISCHARGE_RECREATION;
            case MEGIDDO_FLAME:
                return MEGIDDO_FLAME_RECREATION;
            case RABBIT_ORB:
                return RABBIT_ORB_RECREATION;
            case NETHER_SHIELD:
                return NETHER_SHIELD_RECREATION;
            case NIGHT_WALKER_BAT:
                return NIGHT_WALKER_FROM_MOB;
            case NIGHT_WALKER_BAT_4:
                return NIGHT_WALKER_FROM_MOB_4;
            default:
                return fae;
        }
    }
}
