package net.swordie.ms.constants;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.PinkBean;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Marksman;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.magician.IceLightning;
import net.swordie.ms.client.jobs.adventurer.pirate.Cannonneer;
import net.swordie.ms.client.jobs.adventurer.pirate.Corsair;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.enums.BaseStat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.jobs.adventurer.magician.Bishop.ARCANE_AIM_BISH;
import static net.swordie.ms.client.jobs.adventurer.magician.FirePoison.ARCANE_AIM_FP;
import static net.swordie.ms.client.jobs.adventurer.magician.IceLightning.ARCANE_AIM_IL;

public class SkillChanges {

    public static final boolean overrideSkills = true;

    /**
     * Global Changes
     */

    public static final boolean HYPER_200_CONVERT_PADR_ADD_20_AS = true;

    public static final Map<Integer, Boolean> INFINITE_SUMMON = new HashMap<Integer, Boolean>() {{
        put(FirePoison.IFRIT, true);
        put(IceLightning.ELQUINES, true);
        put(Bishop.BAHAMUT, true);
        put(BowMaster.PHOENIX, true);
        put(Marksman.FREEZER, true);
        put(Pathfinder.RAVEN, true);
    }};

    public static final Map<Integer, Float> dotTimeMultipliers = new HashMap<Integer, Float>() {{
        put(BowMaster.FLAME_SURGE, 5F);
        put(Cannonneer.MONKEY_FURY, 2F);
       // put(PinkBean.WHISTLE, 3F);
    }};

    public static final Map<Integer, Float> dotIntervalDivisors = new HashMap<Integer, Float>() {{
        put(FirePoison.PARALYZE, 3F);
    }};

    // Below only is in effect when SkillChanges.overrideSkills is active
    // Meant to be for skills that would be broken OP if affected by CDR
    public static final Set<Integer> CUSTOM_BLOCKED_CDR_SKILLS = new HashSet<Integer>() {{
        add(Corsair.OCTO_CANNON);
    }};


    /**
     * Warrior
     */

    // Booster (ALL)
    public static final int HERO_WEAPON_BOOSTER_CHANGE = 1; // ATT Speed + 1 (from base -2 -> -3)
    public static final int DARK_KNIGHT_WEAPON_BOOSTER_CHANGE = 2; // ATT Speed + 2
    public static final int PALADIN_WEAPON_BOOSTER_CHANGE = 2; // ATT Speed + 2

    // Hero Skill Changes

    public static final boolean HERO_GUARANTEED_BLINDNESS = true;
    public static final int HERO_FINAL_ATT_ADD_PROP = 40;
    public static final int PANIC_MAX_ORBS = 5;
    public static final boolean HERO_SELF_RECOVERY_CONVERT_TO_PCT = true;
    public static final int HERO_SELF_RECOVERY_HP_DIVISOR = 1000; // (Divides the HP amount to convert to %)
    public static final int HERO_SELF_RECOVERY_MP_DIVISOR = 100;  // (Divides the MP amount to convert to %)
    public static final boolean HERO_PUNCTURE_ADD_DMG_REAPPLY = true;
    public static final int VALHALLA_DURATION = 0; // 100% duration (always on)
    public static final int VALHALLA_CRITICAL_RATE_CHANGE = -25; // 50% -> 45% (DECREASE)
    public static final int VALHALLA_STATUS_RESISTANCE_CHANGE = -70; // 100% -> 30% (DECREASE)
    public static final int VALHALLA_ATTACK_CHANGE = 35; // 0% -> 35% ATT (INCREASE) (ADDED)
    public static final int VALHALLA_DAMAGE_MULTIPLIER = 50; // 0 -> 1.5x (INCREASE) (ADDED)
    public static final boolean VALHALLA_REMOVE_COOL_TIME = true;
    public static final boolean RAGING_BLOW_PUNCTURE_PANIC_EXTRA_LINES = true;
    public static final boolean PANIC_EXTRA_LINES_PER_COST_INC = true;
    public static final boolean RISING_RAGE_APPLY_PUNCTURE = true;
    public static final int RISING_RAGE_EXTRA_LINES_MOBBING = 22;

    // Paladin Skill Changes

    public static final boolean PALADIN_CHARGES_GUARANTEED_PROC = true;
    public static final boolean MOVE_ATTACK_TO_PARASHOCK_GAURD = true; // Divine Shield - ATT moved to Parashock Guard
    public static final int BLAST_DURATION = 0; // Unlimited duration
    public static final boolean VOID_ELEMENTAL_DAMR_TO_PAD = true; // Damage % changed to Attack %
    public static final int VOID_ELEMENTAL_EXTRA_PADR = 20; // 20% Extra Att
    public static final int VOID_ELEMENTAL_DAMAGE_MULTI = 170; // 2.7x Damage Multiplier
    public static final int VOID_ELEMENTAL_RESET_AMOUNT = 100; // Elemental Resistance Ignore
    public static final float CHARGE_DURATION_MULTIPLIER = 12;
    public static final boolean HAMMER_ELEMENTAL_CHARGE_EFFECTS = true;
    public static final int PALADIN_FINAL_ATT_ADD_PROP = 60;

    // Dark Knight Skill Changes
    public static final boolean ALLOW_HEX_EVIL_EYE_REFRESH = true;
    public static final boolean CROSS_SURGE_FULL_EFFECT = true;
    public static final int CROSS_SURGE_FINAL_DAMAGE_MULTIPLIER = 30; // 1.4x Damage Multiplier
    public static final boolean GUNGIRS_DESCENT_REMOVE_COOL_TIME = true;
    public static final int SACRIFICE_DURATION_MULTI = 4; // Current rate * multi
    public static final int SACRIFICE_CRITICAL_RATE = 15; // +15% min & max critical damage
    public static final int SACRIFICE_IGNORE_ENEMY_DEF_MULTI = 2; // Current rate * multi
    public static final boolean SACRIFICE_DONT_CONSUME_BEHOLDER = true;
    public static final int DK_FINAL_ATT_ADD_PROP = 60;

    // Dawn Warrior
    public static final int NIMBLE_FINGER_BOOSTER_CHANGE = -2;
    public static final boolean RISING_SUN_DMG_TO_ATT = true;
    public static final boolean MASTER_OF_SWORD_DMG_TO_ATT = true;
    public static final boolean REPLACE_TRUESIGHT_ONATTACK_BUFF = true;
    public static final int SOUL_FORGE_DURATION = 0; // unlimited
    public static final int SOUL_PLEDGE_FINAL_DAMAGE_ADD = 180; // 2.8x Damage Multiplier
    public static final int SOUL_PLEDGE_CRIT_RATE_INC = 10;
    public static final int SOUL_PLEDGE_STATUS_RESIST = 20; // +20
    public static final int SOUL_PLEDGE_CRIT_DAMAGE_INC = 20;
    public static final boolean MOBBING_COMBO_REPLICATE_STYX_CROSSING_ON_CD = true;
    public static final boolean STYX_CROSSING_EMPOWER_BOSSING_COMBO = true;
    public static final int DW_ANOTHER_BITE_AMT = 5;

    // Aran
    public static final int ARAN_POLE_ARM_BOOSTER_CHANGE = -1;
    public static final int DRAIN_ATT_INC = 10;
    public static final int DRAIN_DAMAGE_MULTI = 30; // 1.3x Damage Multiplier
    public static final int DRAIN_ATT_PERCENT_INC = 10;
    public static final int ARAN_FINAL_ATT_ADD_PROC = 40;

    // Kaiser Skill Changes
    public static final int FINAL_FORM_ATT_SPEED_ADD = -5;
    public static final int FINAL_FORM_EXTRA_DURATION = 30;
    public static final boolean FINAL_FORM_REMOVE_CR = true;
    public static final boolean FINAL_FORM_REMOVE_FDMG = true;
    public static final int CURSEBITE_CR_ADD = 40;
    public static final int CURSEBITE_FDMG_ADD = 150; // 2.5x
    public static final int CURSEBITE_STANCE_ADD = 100;
    public static final boolean WING_BEAT_SCALE_STR = true;

    // Mihile Skill Changes
    public static final int MIHILE_EXTRA_ATT_SPEED = 0;
    public static final int MIHILE_EXTRA_FINAL_ATT_CHANCE = 25;
    public static final boolean MIHILE_SELF_RECOVERY_PERCENTAGE = true;
    public static final int MIHILE_BLIND_CHANCE_MULTIPLIER = 3;
    public static final int ROILING_SOUL_DAMAGE_PERC_MULTI = 2;
    public static final int ROILING_SOUL_MAX_TARGET = 15;
    public static final int ROILING_SOUL_EXTRA_CRIT_RATE = 20;
    public static final int ROILING_SOUL_EXTRA_MIN_CRIT = 15; //15% -> 30%
    public static final int ROILING_SOUL_EXTRA_MAX_CRIT = 15;
    public static final int SACRED_CUBE_DURATION_MUL = 6;
    public static final int SACRED_CUBE_EXTRA_DMG = 20;
    public static final int SACRED_CUBE_CRITR = 20;
    public static final boolean CHARGING_LIGHT_SPAWN_CROSSES_BISH_PALLY_DEPENDENT = true;
    public static final boolean FOUR_POINT_ASSAULT_APPLIES_CHARGING_LIGHT_DEBUFF = true;
    public static final boolean FOUR_POINT_ASSAULT_REPLICATE_RADIANT_CROSS_SPREAD = true;


    //Demon Avenger
    public static final int OVERLOAD_RELEASE_DURATION = 0; //Unlimited
    public static final int DEMON_AVENGER_EXTRA_BOOSTER = -2;
    public static final int NETHER_SHIELDS_PROC_CHANCE = 70;
    public static final int NETHER_SHIELDS_REPROC_CHANCE = 100;
    public static final int DA_EXTRA_SHIELDS = 2;
    public static final int DA_OVERLOAD_ATT_INC = 20; //20% att
    public static final int DA_RELEASE_EXTRA_FD = 16; //16% extra FD
    public static final int DA_OVERLOAD_CRIT_INC = 20; //20% crit

    // Demon Slayer Skill Changes
    public static final int DEMON_SLAYER_BOOSTER_CHANGE = -1; // +1 speed
    public static final int CARRION_BREATH_DOT_DMG_MULT = 2;
    public static final int CARRION_BREATH_TOTAL_DMG_ADD = 200;
    public static final boolean DARK_META_DAMAGE_TO_ATTACK_PERC = true;
    public static final int DARK_META_FINAL_DAMAGE_ADD = 30; // 1.3x Damage Multiplier
    public static final int DARK_META_MAX_CD_ADD = 20; // 20%
    public static final int DARK_META_MIN_CD_ADD = 20; // 20%
    public static final int DARK_META_CRIT_RATE = 30; // 30%
    public static final int BOUNDLESS_RAGE_COOL_DOWN_REDUCE = 60; // 60 seconds
    public static final int BOUNDLESS_RAGE_ATTACK_INC = 20; // +20%
    public static final boolean BOUNDLESS_RAGE_RESET_CRY_CD = true;
    public static final int BLUE_BLOOD_DURATION = 0; // unlimited

    // Hayato Skill Changes
    public static final int SUMMER_RAIN_HITOKIRI_HUNDRED_DUR_MULT = 5;
    public static final boolean SUMMER_RAIN_HITOKIRI_HUNDRED_CONVERT_PADR = true;
    public static final int KATANA_BOOSTER_BOOSTER_ADD = -1;
    public static final int WILLOW_DODGE_STARTING_STACKS = 5;
    public static final boolean HITOKIRI_STRIKE_SUDDEN_STRIKE_EFFECT = true;
    public static final int FALCONS_HONOR_SWORD_ENERGY_GAIN = 100;
    public static final int GOD_OF_BLADES_DUR = 0;
    public static final boolean GOD_OF_BLADES_REMOVE_ATT = true;
    public static final int GOD_OF_BLADES_ASR_OVERRIDE = 30;
    public static final int GOD_OF_BLADES_PADR_ADD = 20;
    public static final int GOD_OF_BLADES_CR_ADD = 45;
    public static final int GOD_OF_BLADES_MAX_CD_ADD = 20;
    public static final int GOD_OF_BLADES_MIN_CD_ADD = 20;
    //public static final int GOD_OF_BLADES_FINAL_DMG_ADD = 15; //1.15x
    public static final boolean IGNORE_QUICKDRAW_STANCE_ENERGY_REQ = true;

    // Blaster Skill Changes
    public static final int ARM_CANNON_BOOSTER_STANCE_ADD = 100;
    public static final int HYPER_MAGNUM_BLOW_FDMG_PER_HIT = 17;
    public static final int BLASTER_DMG_MULTIPLIER = 50; // 1.5x
    public static final int BLASTER_BONUS_PADR = 20; // 20% bonus att %

    // Pink Bean Skill Changes
    public static final boolean MINI_BEAN_DONT_CONSUME_POWERHOUSE_BUFF = true;
    public static final boolean LETS_ROLL_INSTANT_INCREMENT = true;
    public static final float SKY_JUMP_BOUNCE_POWERHOUSE_BUFF_AMOUNT = 12.5f;
    public static final int SKY_JUMP_BOUNCE_RESTORE_YOYO_INTERVAL = 1;
    public static final int SKY_JUMP_BOUNCE_ROLL_CD_REDUCTION = 1500;

    /**
     * Magician
     */

    // Booster (ALL)
    public static final int FIRE_POISON_WEAPON_BOOSTER_CHANGE = 3;
    public static final int ICE_LIGHTING_WEAPON_BOOSTER_CHANGE = 4;
    public static final int BISHOP_WEAPON_BOOSTER_CHANGE = 5;

    public static final int MAGICIAN_INFINITY_STANCE_PERCENT = 100;
    public static final int MAGICIAN_FINAL_ATTACK_PROCESS_RATE = 100; // 100% process rate for attack
    public static final int ARCANE_AIM_ICE_LIGHTNING_IED = 50;
    public static final int ARCANE_AIM_BISHOP_IED = 50;
    public static final int ARCANE_AIM_CRITICAL_RATE_MULTI_IL = 250; // 2.5x
    public static final int ARCANE_AIM_CRITICAL_RATE_MULTI_BISH = 370; // 3.7x
    public static final int ARCANE_AIM_CRITICAL_RATE_MULTI_FP = 120; // 1.2x

    // Fire Poison Skill Changes
    public static final boolean INFINITY_FP_STACK_DURATION = true;
    public static final boolean PARALYZE_ADDITIVE_MOB_STATS = true; // Added Untouchable and Speed MTS and Flame Haze DoT
    public static final int IFRIT_STATUS_RESISTANCE_RATE = 20; // additive
    public static final int IFRIT_STATUS_CRITICAL_RATE = 15; // additive
    public static final int MEGIDDO_FLAME_RECREATE_CHANCE = 30;
    public static final boolean MIST_ERUPTION_BUFF_MEGIDDO_FLAME_SCALE_CRIT_DAMAGE = false;

    // Ice Lighting Skill Changes
    public static final boolean INFINITY_IL_STACK_DURATION = true;
    public static final int CHILLING_STEP_DURATION = 30000; // 30 seconds
    public static final int CHILLING_STEP_PROP_RATE = 40; // + 40%
    public static final int CHAIN_LIGHTNING_DOT_DMG = 100;
    public static final int CHAIN_LIGHTNING_MDEF_REDUCT = -30;
    public static final int ELQUINES_MAGIC_ATTACK_RATE = 20; // 10%
    public static final int ELQUINES_MAGIC_IGNORE_ENEMY_DEF = 20; // 20%

    // Bishop Skill Changes
    public static final boolean INFINITY_BISH_STACK_DURATION = true;
    public static final int BAHAMUT_ELEMENT_RESET_PERCENT = 100;
    public static final int BLESSED_ENSEMBLE_MULTI = 6; // override for blessed ensemble count
    public static final int VENGEANCE_OF_ANGEL_MAGIC_ATTACK_INC = 10; // + 10%
    public static final int INFINITY_BISH_LEECH_MAD = 20;

    // Blaze Wizard Skill Changes
    public static final int BW_CRIT_DAMAGE_INCREASE_RATE = 320; //3.2x crit damage
    public static final int IGNITION_MAD_PERCENT = 50;
    public static final int IGNITION_CR_PERCENT = 20;
    public static final int IGNITION_DOT_DURATION_DIVISOR = 2;
    public static final int BW_BOOSTER_EXTRA_ATT_SPEED = -5;
    public static final int PHOENIX_RUN_DURATION = 0;
    public static final double PHOENIX_RUN_HP_RECOVERY = 1; // Percent to heal at, vanilla is 50%
    public static final boolean PHOENIX_RUN_CD_START_ON_EFFECT = true; // Set cooldown when player is revived instead of when casting the skill
    public static final int FLAME_BARRIER_DUR_MULTIPLIER = 5;
    public static final boolean THROW_ALL_ORBITAL_FLAMES_AT_ONCE = true;
    public static final boolean ORBITAL_FLAME_DIFF_DIRECTIONS = true;

    // Evan Skill Changes
    public static final int EVAN_MAGIC_BOOSTER_CHANGE = -4;
    public static final int PARTNERS_CRIT_DMG_MULTI = 420;
    public static final boolean SHOOT_WRECKAGE_AUTOMATICALLY = true;
    public static final int BLESSING_OF_ONYX_IGNORE_DEFENSE = 50; // 50% inc
    public static final int BLESSING_OF_ONYX_MATT_RATE = 40; // 40% matt
    public static final int SUMMON_ONYX_DRAGON_DURATION = 0; // unlimited
    public static final boolean SUMMON_ONYX_DRAGON_TOGGLE = true;

    // Battle Mage Skill Changes
    public static final boolean CONDEMNATION_BOSS_INCREMENT_EVERY_HIT = true;
    public static final boolean CONDEMNATION_REMOVE_CD = true;
    public static final boolean DRAINING_AURA_PASSIVE_CONVERT_TO_PERC = true;
    public static final int BAM_BOOSTER_EXTRA_ATT_SPEED = -4;
    public static final int BAM_CRIT_DMG_MULTI = 270; // 2.7x
    public static final int FINISHING_BLOW_BONUS_BD = 50;
    public static final boolean REMOVE_MAX_MOBS_CAP_BATTLE_RAGE = true;
    public static final boolean MASTER_OF_DEATH_REMOVE_CONDEMNATION_COUNTER_LOWER = true;
    public static final int MASTER_OF_DEATH_DURATION = 0;
    //public static final int MASTER_OF_DEATH_BONUS_LINES = 1;
    public static final int SWEEPING_STAFF_CD_RED_ON_DARK_SHOCK = 1500;
    public static final boolean PROC_AURAS_ON_DARK_SHOCK_SCALE_INT = true;
    public static final boolean FINISHING_BLOW_PROC_DARK_SHOCK = true;

    // Beast Tamer
    public static final int STACKABLE_MAJESTIC_TRUMPET = 6;
    public static final int MAJESTIC_TRUMPET_VERTICAL_SHIFT = -50;
    public static final int BEAR_ASSAULT_MOB_COUNT = 10;
    public static final int BEAR_ASSAULT_STANCE = 100;
    public static final int BEAR_ASSAULT_TIME = 0;
    public static final int BEAR_ASSAULT_ATT_SPEED = -1;
    public static final int BEAR_ASSAULT_STATUS_RESIST = 30;
    public static final int BEAR_ASSAULT_ELEMENTAL_RESET = 100;


    public static final int BEAR_ASSAULT_CRIT_MULTI = 3;
    public static final int BEAR_ASSAULT_CRIT_DAMAGE = 30;

    public static final int PURR_ZONE_TIME_MULTIPLIER = 2;
    public static final boolean PURR_ZONE_CONVERT_PCT_HEALING = true;
    public static final int THUNDER_TRAIL_TIME_MULTIPLIER = 5;
    public static final int BT_CRIT_DMG_MULTIPLIER = 320;

    // Kanna Skill Changes
    public static final boolean KANNA_CUSTOM_EXTRA_MP = true;
    public static final int KANNA_CUSTOM_EXTRA_MP_LEVEL = 200;
    public static final boolean EQUIP_HAKU_FAN_SHIELD_SLOT = true;
    public static final int BREATH_UNSEEN_STANCE_ADD = 20;
    public static final int KANNA_EXTRA_BOOSTER_ATT_SPEED = -4;
    public static final int SOUL_SHEAR_CRIT_MULTI = 250; //2.5x
    public static final int BLOSSOM_BARRIER_EXTRA_DUR = 50000;
    //public static final int VANQUISHER_CHARM_TOTAL_DMG_ADD = 30;
    public static final boolean BINDING_TEMPEST_REMOVE_BIND = true;
    public static final boolean HAKU_PERFECTED_MATT_FROM_WEAPON = true;
    public static final int HAKU_PERFECTED_MADR_ADD = 35;
    public static final int HAKU_PERFECTED_CR_ADD = 20;
    public static final int HAKU_PERFECTED_ASR_ADD = 25;
    public static final int HAKU_PERFECTED_ELE_RESET_ADD = 100;
    public static final boolean NINE_TAILED_FURY_CONVERT_MADR = true;
    public static final int NINE_TAILED_FURY_DUR_MULTI = 5;
    public static final int KISHIN_PARTY_MEMBERS_KILL_REPLICATION_CHANCE = 25000;

    // Luminous Skill Changes
    public static final boolean LUNAR_TIDE_USE_CRIT_COND = true; // if critRate is less than 80%
    public static final int LUMINOUS_BOOSTER_CHANGE = 4;
    public static final int LUMINOUS_LUNAR_TIDE_CRIT_MULTI = 370; // 3.7x crit multi
    public static final int ARCANE_PITCH_MATT_INC = 50; // % inc
    public static final int ARCANE_PITCH_CRIT_RATE_INC = 20; // % inc
    public static final int EQUILIBRIUM_DURATION_INC = 50; // last 50 secs longer

    // Kinesis Skill Changes
    public static final int KINESIS_BOOSTER_CHANGE = 2;
    public static final int KINESIS_ATOM_PROC_RATE = 100;
    public static final int TELEPATH_TACTICS_MATT = 70;
    public static final int TELEPATH_TACTICS_MIN_CRIT = 60;
    public static final int TELEPATH_TACTICS_MAX_CRIT = 60;
    public static final boolean PSYCHIC_ASSAULT_TRIGGERS_MIND_QUAKE = true;
    public static final boolean MIND_QUAKE_TRIGGERS_PSYCHIC_ASSAULT = true;
    public static final boolean MIND_QUAKE_FULL_EFFECT = true;
    public static final boolean MIND_BREAK_FULL_EFFECT = true;

    /**
     * Archer
     */

    // Booster (Bowmaster + Marksman + Pathfinder)
    public static final int ARCHER_BOOSTER_CHANGE = 2; // (For both bows and crossbows)

    // Bowmaster Skill Changes
    public static final int ARROW_PLATTER_TOTAL_DAMAGE_ADD = 50;
    public static final int BINDING_SHOT_DURATION_MULTI = 2;
    public static final int ILLUSION_STEP_FDMG_ADD = 60; // 1.6x
    public static final boolean ENCHANTED_QUIVER_ALL_EFFECTS = true;
    public static final int ENCHANTED_QUIVER_DURATION_MULTI = 6;
    public static final boolean ENCHANTED_QUIVER_REMOVE_COOL_TIME = true;
    public static final int ENCHANTED_QUIVER_MAGIC_ARROWS_PROP = 100;
    public static final int CONCENTRATION_DURATION = 0; // unlimited;
    public static final boolean CONCENTRATION_REMOVE_COOL_DOWN = true;
    public static final boolean CUSTOM_ARROW_PLATTERS = true;
    public static final int ARMOR_BREAK_ADD_COOLDOWN = 2000; // Gets added to base 1000 ms cooldown

    // Marksman Skill Changes
    public static final int NET_TOSS_ADD_CHANCE = 30; //  +30% from orig
    public static final int ARROW_ILLUSION_HP_MULTI = 50; // x50 of current
    public static final int ARROW_ILLUSION_DURATION_MULTI = 3; // x3
    public static final int BULLS_EYE_DURATION = 0; // unlimited
    public static final boolean BULLS_EYE_REMOVE_COOL_DOWN = true;
    public static final int BULLS_EYE_IGNORE_ENEMY_DEF_INCREASE = 20; // in %
    public static final int BULLS_EYE_CRITICAL_RATE_INCREASE = 20; // in %
    public static final boolean SNIPE_CAPPED_EXTRA_LINES = true;
    public static final int MARKSMEN_FA_PROP = 100;

    // Wind Archer Skill Changes
    public static final int WA_BOOSTER_ADD = -2;
    public static final int EMERALD_FLOWER_HP_MULTIPLIER = 50;
    public static final int SONG_OF_HEAVEN_TOTAL_DAMAGE_ADD = 30;
    public static final int SONG_OF_HEAVEN_CRIT_DMG_ADD = 15;
    public static final double ALBATROSS_MAX_STAT_MULTIPLIER = 1; // Everything but IED and Booster
    public static final int ALBATROSS_MAX_EXTRA_IED = 30;
    public static final int STROM_BRINGER_EXTRA_CHANCE = 70;
    public static final int TRIFLING_ATTACK_COUNT = 2;

    // Mercedes Skill Changes
    public static final int MERCEDES_BOOSTER_CHANGE = -3;
    public static final int MERC_FINAL_ATT_ADD_PROP = 25;
    public static final int UNICORN_SPIKE_DURATION_MULTI = 6;
    public static final int IGNIS_ROAR_FINAL_DMG_ADD = 60; // 1.6x Damage Multiplier
    public static final int IGNIS_ROAR_DEFENSE_IGNORE_INC = 30; // + 30%
    public static final int IGNIS_ROAR_STARTING_STACK = 10;
    public static final int IGNIS_ROAR_STACK_DUR = 0;
    public static final boolean ELEMENTAL_KNIGHTS_SUMMON_BOTH = true;
    public static final int ELEMENTAL_KNIGHTS_SUMMON_TERM = 0;
    public static final int ISHTARS_RING_MIN_CD = 20;
    public static final int ISHTARS_RING_MAX_CD = 20;
    public static final int ISHTARS_RING_ATT_PERC = 20;
    public static final int SPIKES_ROYALE_DURATION_MULTI = 6;
    public static final int LIGHTNING_EDGE_DURATION_MULTI = 6;
    public static final int ELVISH_BLESSING_DUR = 0;
    public static final boolean ISHTARS_RING_REPLICATE_COMBO_SKILLS = true;

    // Wild Hunter Skill Changes
    public static final int SUMMON_JAGUAR_BONUS_STANCE = 50;
    public static final int WILD_ARROW_BLAST_ADD_FD = 50;
    public static final int WILD_ARROW_BLAST_ATT_PERCENT = 10;
    public static final int WILD_ARROW_BLAST_CRIT_DAMAGE = 10;
    public static final int SILENT_RAMPAGE_DUR = 0;
    public static final int WH_FA_PROC = 100;

    /**
     * Thief
     */

    // Booster (ALL)
    public static final int THIEF_BOOSTER_CHANGE = -1; // (For NL Shad DB)
    public static final int SHADOW_PARTNER_DURATION = 0; // unlimited duration
    public static final boolean SHAD_DB_PET_BUFFS_DS_NO_EXPIRE = true;

    // Throwing Stars
    public static final boolean WHITE_GOLD_DOT_EXPLOSIONS = true;
    public static final boolean FLAME_EXTRA_LINES_AND_SPREADING = true;
    public static final boolean HWABI_TICK_TIME_SCALING = true;

    public static final boolean ILBI_ATTACK_REPLICATION = true;
    public static final boolean BALANCED_FURY_STACKING_REPLICATIONS = true;
    public static final boolean MAGIC_BURST_REPLICATION = true;

    public static final boolean STEELY_EFFECT = true;
    public static final boolean INFINITE_EFFECT = true;

    // Night Lord Skill Changes
    public static final int ASSASSINS_MARK_ADD_CHANCE = 40;
    public static final int BLEED_DART_DURATION_MULTI = 6;
    public static final int BLEED_DART_BONUS_FD = 20;
    public static final int BLEED_DART_ATTACK_PERC_INCREASE = 20; // +20% attack
    public static final boolean DEATH_STAR_MAP_WIDE_MARK = true;

    // Shadower Skill Changes
    public static final boolean STEAL_AUTO_LOOT_BOSSES = true;
    public static final boolean ASSASSINATE_DONT_CONSUME_KP = true;
    public static final int SHADOWERS_INSTINCT_DURATION_MULTI = 2; // x2
    public static final double SHADOWERS_INSTINCT_KILLING_POINT_MULTI = 1.66;
    public static final boolean FLIP_THE_COIN_USE_ATTACK_PERCENT = true; // Uses Attack % instead of Damage %
    public static final int FLIP_THE_COIN_DURATION = 0;
    public static final boolean FLIP_THE_COIN_AUTOMATIC = true;
    public static final boolean PICK_POCKET_AUTO_EXPLODE = true;
    public static final boolean ASSASSINATE_EXTRA_LINES_SV_FLARE_ADD_LINES = true;

    // Phantom Skill Changes
    public static final int PHANTOM_BOOSTER_CHANGE = -1;
    public static final int PHANTOM_JUDGMENT_DRAW_DURATION_MULTIPLIER = 6;
    public static final boolean PHANTOM_JUDGMENT_FINAL_DRAW = true;
    public static final int MILLE_AIGUILLES_DAMAGE_MULTIPLIER = 20; // 1.2x Damage Multiplier
    public static final boolean PRIER_DARIA_DMG_PADR_CONVERT = true;
    public static final int PRIERE_DARIA_DAMAGE_MULTIPLIER = 120; // 2.2x Damage Multiplier
    public static final boolean TEMPEST_ACTIVATE_STOLEN_ATTACKS = true;

    public static final Set<Integer> WHITELISTED_TEMPEST_SKILLS = new HashSet<Integer>() {{
        // Slash Blast; Brandish; Flame Charge; Blizzard Charge; Close Combat; Piercing Drive; Spear Sweep
        add(1001005); add(1101011); add(1201011); add(1201012); add(1201013); add(1301011); add(1301012);
        // Intrepid Slash; Lightning Charge; La Mancha Spear; Divine Charge; Blast; Heaven's Hammer; Dark Impale; Gungnir's Descent
        add(1111010); add(1211008); add(1311011); add(1221004); add(1221009); add(1221011); add(1321012); add(1321013);
        // Energy Bolt; Flame Orb; Poison Breath; Thunder Bolt; Cold Beam; Holy Arrow
        add(2001008); add(2101004); add(2101005); add(2201005); add(2201008); add(2301005);
        // Explosion; Ice Strike; Glacier Chain; Shining Ray; Paralyze; Meteor Shower; Blizzard
        add(2111002); add(2211002); add(2211010); add(2311004); add(2121006); add(2121007); add(2221007);
        // Big Bang; Genesis
        add(2321001); add(2321008);
        // Arrow Blow; Arrow Bomb; Iron Arrow; Net Toss; Flame Surge; Dragon's Breath; Explosive Bolt; Arrow Stream
        add(3001004); add(3101005); add(3201005); add(3201008); add(3111003); add(3211008); add(3211009); add(3121015);
        // Double Stab; Lucky Seven; Shuriken Burst; Gust Charm; Steal; Savage Blow; Midnight Carnival; Fatal Blow
        add(4001334); add(4001344); add(4101008); add(4101010); add(4201004); add(4201012); add(4211011); add(4311002);
        // Sudden Raid; Showdown; Boomerang Stab; Sudden Raid; Assassinate; Blade Fury; Phantom Blow; Sudden Raid;
        add(4121016); add(4121017); add(4221007); add(4221010); add(4221014); add(4341004); add(4341009); add(4341011);
        // Sommersault Kick; Double Shot; Cannon Blaster; Cannon Strike; Corkscrew Blow; Rapid Blast; Scatter Shot
        add(5001002); add(5001003); add(5011000); add(5011001); add(5101004); add(5201001); add(5301000);
        // Blunderbuster; Blackboot Bill; Cannon Spike; Monkey Wave; Monkey Fury; Nautilus Strike;
        add(5211008); add(5211010); add(5311000); add(5311002); add(5311010); add(5121013);
        // Eight-Legs Easton; Cannon Bazooka; Nautilus Strike;
        add(5221017); add(5321000); add(5321001);
    }};

    // Night Walker Skill Changes
    public static final int NW_BOOSTER_EXTRA_ATT_SPEED = -1;
    public static final int SHADOW_BAT_HEALING_MULTIPLIER = 5;
    public static final int MAX_SIPHON_STACK = 2;
    public static final int SIPHON_EFFECT_MULTIPLIER = 5;
    public static final int NW_ILLUSION_BONUS_TIME = 10;
    public static final int NW_SPIRIT_PROJECTION_IED = 30;
    public static boolean SPIRIT_PROJECTION_ACTIVE = false;

    // Dual Blade Skill Changes
    public static final int DB_ADV_DARK_SIGHT_MAINTAIN_CHANCE = 100;
    public static final int MIRRORED_TARG_HP_MULTI = 50;
    public static final boolean MIRORED_TARG_DONT_REMOVE_IMAGE = true;
    public static final boolean MIRRORED_TARG_PROC_SHADOWMELD = true;
    public static final boolean MIRRORED_TARGET_REPLICATE_SUDDEN_RAID = true;
    public static final int FINAL_CUT_DUR_MULTIPLIER = 2;
    public static final int ASURAS_ANGER_ATT_PERC = 20;
    public static final int BLADE_CLONE_DURATION = 0;
    public static final int BLADE_CLONE_ADD_CR = 15;
    public static final boolean BLADE_CLONE_DAM_TO_ATT = true;
    public static final int BLADE_CLONE_EXTRA_ATT = 10;
    public static final int BLADE_CLONE_ADD_FD = 50; // 1.5x Multiplier
    public static final boolean SHADOW_MELD_ALLOW_REFRESH = true;
    public static final boolean SHADOW_MELD_NO_CD = true;

    // Xenon Skill Changes
    public static final boolean PINPOINT_SALVO_TOGGLE = true;
    public static final boolean ION_THRUST_NO_COST = true;
    public static final int XENON_BOOSTER_CHANGE = -2; // +2 speed
    public static final int EMERGENCY_RESUPPLY_MULTI = 2; // 2x more
    public static final int OOPARTS_CODE_DURATION_MULTI = 8;
    public static final boolean OOPARTS_CODE_DAMAGE_TO_ATTACK_PEC = true;
    public static final int OOPARTS_CODE_FINAL_DAMAGE_ADD = 120;
    public static final int OOPARTS_CODE_MAX_CD = 30; // +30
    public static final int OOPARTS_CODE_MIN_CD = 30; // +30
    public static final int OOPARTS_CODE_CR = 30; // +30
    public static final int AMARANTH_GEN_DURATION = 70;



    /**
     * Pirate
     */

    public static final int BUCC_CORS_EXTRA_ATT_SPEED = -2;

    // Buccaneer Skill Changes
    public static final int CROSSBONES_ADD_FD = 80;
    public static final int POWER_UNITY_STARTING_STACK = 4;
    public static final int POWER_UNITY_DUR_MULTI = 3;
    public static final int STIMULATING_CONVERSION_DUR_MULTI = 3;
    public static final boolean STIMULATING_CONVERSION_AOE_DAMAGE = true;
    public static final int DRAGON_STRIKE_DURATION = 30;
    public static final int OCTOPUNCH_CANCEL_HITS = 11;
    public static final int DRAGON_STRIKE_CANCEL_HITS = 12;
    public static final int CORKSCREW_BLOW_CANCEL_HITS = 10;
    public static final int BUCC_EXPLODE_ON_ENERGY_EMPTY = 15;

    // Corsair Skill Changes
    public static final boolean OCTO_CANNON_EXTEND_SUMMONS = true;
    public static final boolean AHOY_MATEYS_ALL_SUMMONS = true;
    public static final int RAPID_FIRE_DMG = 120;
    public static final int RAPID_FIRE_MAX_CD = 15;
    public static final int RAPID_FIRE_MIN_CD = 15;
    public static final int JOLLY_ROGER_EXTRA_STANCE = 40;
    public static final int JOLLY_ROGER_ADD_FD = 20;
    public static final int JOLLY_CRIT_DMG = 20;
    public static final int JOLLY_DURATION = 0;
    public static final boolean QUICKDRAW_AUTO_ACTIVATE = true;
    public static final int WHALERS_POTION_DUR_MULTI = 2;
    public static final boolean UGLY_BOMB_SUMMON_OCTOCANNONS = true;
    public static final boolean BRAIN_SCRAMBLER_SUMMONS_OCTOCANNONS = true;

    // Cannon Master Skill Changes
    public static final int MONKEY_FURY_REDUCE_WDEF_AMOUNT = -20;
    public static final int MONKEY_MILITIA_RESISTANCE = 15;
    public static final int PIRATE_SPIRIT_ADD_FD = 110;
    public static final int PIRATE_SPIRIT_ATT_RATE = 20;
    public static final int PIRATE_SPIRIT_CRIT_DMG = 30;
    public static final int PIRATE_SPIRIT_EXTRA_STANCE = 20;
    public static final int ROLLING_RAINBOW_DUR_MULTI = 3;
    public static final int BUCKSHOT_DUR = 0;

    // Jett Skill Changes
    public static final int JETT_EXTRA_ATT_SPEED = -3;
    public static final int JETT_TURRET_DEPLOYMENT_DUR_MULT = 3;
    public static final int SPLITSTREAM_SUIT_DUR_ADD = 30;
    public static final int STARFORCE_SALVO_DAM_ADD = 15;
    public static final int STARFORCE_SALVO_PADR_ADD = 15;
    public static final int STARFORCE_SALVO_CD_ADD = 30;
    public static final int HIGH_GRAVITY_FD_ADD = 30;
    public static final int HIGH_GRAVITY_STANCE_ADD = 30;
    public static final int JETT_STRIKEFORCE_SHOWDOWN_PROC_CHANCE = 50;
    public static final int BIONIC_MAXIMIZER_DUR_MULT = 2;
    public static final int BIONIC_MAXIMIZER_DMG_RED_MULT = 2;


    // Angelic Buster Skill Changes
    public static final int MELODY_CROSS_ATTACK_SPEED = -3;
    public static final int SILVER_BLOSSOM_STANCE = 100;
    public static final int TRINITY_MAX_STACKS = 6;
    public static final boolean TRINITY_FIRST_HIT_SOUL_SEEKER_EXPERT_DOUBLE_CHANCE = true;
    public static final int SOUL_RESONANCE_TOTAL_DAMAGE_ADD = 120;
    public static final int AH_IV_FDMG_ADD = 50;
    public static final boolean AH_IV_PADR_CONVERT = true;
    public static final boolean SOUL_SEEKER_EXPERT_NO_DMG_REDUC = true;
    public static final int SOUL_SEEKER_EXPERT_MAX_RECREATE = 35;
    public static final int SOUL_SEEKER_EXPERT_RECREATE_CHANCE = 95;
    public static final int SUPERNOVA_CD_REDUCE_TIME = 30;
    public static final int FINAL_CONTRACT_ATT_PERC = 10;
    public static final int FINAL_CONTRACT_ALL_STAT = 20;
    public static final boolean PRETTY_EXALTATION_IGNORE_DR = true;

    // Shade Skill Changes
    public static final int SPIRIT_FRENZY_TOTAL_DAMAGE_ADD = 100;
    public static final int SPIRIT_FRENZY_MIN_CD_ADD = 15;
    public static final int SPIRIT_FRENZY_MAX_CD_ADD = 15;
    public static final int SPIRIT_TRAP_CD_REDUCTION = 90;
    public static final int FIRE_FOX_SPIRITS_FINAL_DMG_ADD = 90;
    public static final int FIRE_FOX_SPIRITS_CR_ADD = 45;
    public static final int FIRE_FOX_SPIRIT_ASR_ADD = 25;
    public static final int FIRE_FOX_SPIRIT_BONUS_PROP = 90;
    public static final boolean SPIRIT_BOND_MAX_DAMR_CONVERT_PADR = true;
    public static final int SPIRIT_BOND_MAX_DUR_MULTI = 10;
    public static final int SPIRIT_BOND_MAX_EXTRA_ATT_SPEED = -1;

    // Mechanic Skill Changes
    public static final int HUMANOID_MECH_MIN_CD_ADD = 20;
    public static final int HUMANOID_MECH_MAX_CD_ADD = 20;
    public static final int MECHANIC_RAGE_EXTRA_ATT_SPEED = -1;
    public static final int MECHANIC_RAGE_FINAL_DAMAGE_ADD = 110;
    public static final int MECHANIC_RAGE_DUR = 300;
    public static final int MECHANIC_RAGE_PADR_ADD = 10;
    public static final int MECHANIC_RAGE_CRIT_ADD = 20;
    public static final int UNIT_HEX_DUR_MULTIPLIER = 3;
    public static final int MECHANIC_SUMMON_DUR_MULTI = 3;


    // Thunder Breaker Skill Changes
    public static final int THUNDER_BREAKER_BOOSTER_CHANGE = -1;
    public static final int PRIMAL_BOLT_STANCE_PERC = 100;
    public static final boolean TYPHOON_DAMAGE_TO_ATTACK_PERC = true;
    public static final int TYPHOON_ATT_PERC_INC = 15;
    public static final int TYPHOON_CRIT_DMG_INC = 15;
    public static final int TYPHOON_FINAL_DMG_ADD = 30; // 1.3x Damage Multiplier
    public static final int ARC_CHARGER_DUR = 0;
    public static final boolean PRIMAL_BOLT_UNLIMITED_DUR = true;

    //UNIVERSAL
    public static final int CLONE_STAT_AMT = 60000;
    public static final int DSE_CRIT = 15;
    public static final int DSE_CRIT_DMG = 20;


    public static void addCriticalDamageToMageSkill(Char chr, TemporaryStatManager tsm, int skillID) {
        int multiplier = 100;
        multiplier = skillID == ARCANE_AIM_FP ? ARCANE_AIM_CRITICAL_RATE_MULTI_FP
                : skillID == ARCANE_AIM_IL ? ARCANE_AIM_CRITICAL_RATE_MULTI_IL
                : skillID == ARCANE_AIM_BISH ? ARCANE_AIM_CRITICAL_RATE_MULTI_BISH : multiplier;
        multiplier = skillID == BlazeWizard.FLAME_ELEMENT ? BW_CRIT_DAMAGE_INCREASE_RATE
                : skillID == Evan.PARTNERS ? PARTNERS_CRIT_DMG_MULTI
                : skillID == Luminous.LUNAR_TIDE  ? LUMINOUS_LUNAR_TIDE_CRIT_MULTI : multiplier;
        multiplier = skillID == BattleMage.BATTLE_RAGE ? BAM_CRIT_DMG_MULTI
                : skillID == Kanna.LIFEBLOOD_RITUAL ? SOUL_SHEAR_CRIT_MULTI
                : skillID == BeastTamer.MEOW_CARD ? BT_CRIT_DMG_MULTIPLIER
                : multiplier;

        if (multiplier == 100)
            return;

        tsm.removeStatsBySkill(2120014);

        boolean buccaneerPresent = false;
        int minCritUnscaledAmount = chr.getTotalStat(BaseStat.allStat); // Passive Min Crit
        int maxCritUnscaledAmount = chr.getTotalStat(BaseStat.allStat); // Passive Max Crit
        if (chr.getParty() != null) { // Scan for a buccaneer and take 40% min crit
            for (PartyMember pm : chr.getParty().getPartyMembers()) {
                if (pm != null && pm.getChr().getJob() == 512 && pm.getChr().getLevel() >= 140) {
                    buccaneerPresent = true;
                    break;
                }
            }
        }

        int addMinCrit = (minCritUnscaledAmount*multiplier/100) + (buccaneerPresent ? 40*multiplier/100 : 0) - minCritUnscaledAmount;
        int powerUnityStacks = addMinCrit/5;
        int enrageStacks = addMinCrit % 5;
        int addMaxCrit = (maxCritUnscaledAmount*multiplier/100) + (buccaneerPresent ? 40*multiplier/100 : 0) - maxCritUnscaledAmount;
        int indieMaxCrit = addMaxCrit - powerUnityStacks*5;


        Option o97 = new Option();
        Option o98 = new Option();
        Option o99 = new Option();

        if (powerUnityStacks > 0) {
            o97.nOption = powerUnityStacks;
            o97.rOption = 2120014;
            o97.tOption = 5;
            tsm.putCharacterStatValue(UnityOfPower, o97);
        }
        if (enrageStacks > 0) {
            o98.nOption = enrageStacks;
            o98.rOption = 2120014;
            o98.tOption = 5;
            tsm.putCharacterStatValue(EnrageCrDamMin, o98);
        }
        if (indieMaxCrit > 0) {
            o99.nValue = indieMaxCrit;
            o99.nReason = 2120014;
            o99.tTerm = 5;
            tsm.putCharacterStatValue(IndieCr, o99);
        }
        tsm.sendSetStatPacket();
    }
}