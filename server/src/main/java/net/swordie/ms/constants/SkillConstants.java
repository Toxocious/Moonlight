package net.swordie.ms.constants;

import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.Zero;
import net.swordie.ms.client.jobs.adventurer.BeastTamer;
import net.swordie.ms.client.jobs.adventurer.Kinesis;
import net.swordie.ms.client.jobs.adventurer.archer.Archer;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Marksman;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.magician.IceLightning;
import net.swordie.ms.client.jobs.adventurer.pirate.Buccaneer;
import net.swordie.ms.client.jobs.adventurer.pirate.Cannonneer;
import net.swordie.ms.client.jobs.adventurer.pirate.Corsair;
import net.swordie.ms.client.jobs.adventurer.pirate.Jett;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.adventurer.thief.NightLord;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.adventurer.thief.Thief;
import net.swordie.ms.client.jobs.adventurer.warrior.DarkKnight;
import net.swordie.ms.client.jobs.adventurer.warrior.Hero;
import net.swordie.ms.client.jobs.adventurer.warrior.Paladin;
import net.swordie.ms.client.jobs.adventurer.warrior.Warrior;
import net.swordie.ms.client.jobs.anima.HoYoung;
import net.swordie.ms.client.jobs.cygnus.*;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.flora.Ark;
import net.swordie.ms.client.jobs.flora.Illium;
import net.swordie.ms.client.jobs.legend.*;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Cadena;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.Mechanic;
import net.swordie.ms.client.jobs.resistance.WildHunter;
import net.swordie.ms.client.jobs.resistance.Xenon;
import net.swordie.ms.client.jobs.resistance.demon.Demon;
import net.swordie.ms.client.jobs.resistance.demon.DemonAvenger;
import net.swordie.ms.client.jobs.resistance.demon.DemonSlayer;
import net.swordie.ms.client.jobs.sengoku.Hayato;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.BeastTamerBeasts;
import net.swordie.ms.loaders.SkillData;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.swordie.ms.client.jobs.adventurer.magician.Magician.GUIDED_ARROW;
import static net.swordie.ms.client.jobs.cygnus.WindArcher.MERCILESS_WINDS;
import static net.swordie.ms.client.jobs.cygnus.WindArcher.SPIRALING_VORTEX_EXPLOSION;
import static net.swordie.ms.client.jobs.flora.Ark.*;
import static net.swordie.ms.client.jobs.legend.Aran.*;
import static net.swordie.ms.client.jobs.legend.Mercedes.STAGGERING_STRIKES;
import static net.swordie.ms.client.jobs.legend.Mercedes.STUNNING_STRIKES;
import static net.swordie.ms.client.jobs.legend.Phantom.ACE_IN_THE_HOLE;
import static net.swordie.ms.client.jobs.legend.Phantom.ACE_IN_THE_HOLE_ATOM;
import static net.swordie.ms.client.jobs.legend.Shade.*;
import static net.swordie.ms.client.jobs.nova.AngelicBuster.*;
import static net.swordie.ms.client.jobs.nova.Kaiser.*;
import static net.swordie.ms.client.jobs.resistance.Blaster.*;
import static net.swordie.ms.client.jobs.resistance.demon.DemonAvenger.NETHER_SHIELD;
import static net.swordie.ms.client.jobs.resistance.demon.DemonAvenger.NETHER_SHIELD_ATOM;
import static net.swordie.ms.client.jobs.sengoku.Kanna.*;

/**
 * Created on 12/18/2017.
 */
public class SkillConstants {
    public static final short PASSIVE_HYPER_MIN_LEVEL = 140;
    public static final List<Short> ACTIVE_HYPER_LEVELS = Arrays.asList((short) 150, (short) 170, (short) 200);

    private static final Logger log = Logger.getLogger(SkillConstants.class);

    public static final int LEVEL_UP_DAMAGE_SKILL_ID = 80001770;

    public static final short LINK_SKILL_1_LEVEL = 70;
    public static final short LINK_SKILL_2_LEVEL = 120;
    public static final short LINK_SKILL_3_LEVEL = 210;

    public static final byte PASSIVE_HYPER_JOB_LEVEL = 6;
    public static final byte ACTIVE_HYPER_JOB_LEVEL = 7;
    public static final int MAKING_SKILL_EXPERT_LEVEL = 10;
    public static final int MAKING_SKILL_MASTER_LEVEL = 11;
    public static final int MAKING_SKILL_MEISTER_LEVEL = 12;

    public static final int DUMMY_FOR_PVAC = 69001000;


    // used for char specific Xenon pod cooldown.
    public static final int XENON_POD_FOR_COOLDOWN = 899999999;
    public static final int CARDINAL_TORRENT_COOLDOWN = Pathfinder.CARDINAL_TORRENT + 200;



    public static int getPrefix(int skillID) {
        int prefix = skillID / 10000;
        if(prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix;
    }


    public static boolean isSkillNeedMasterLevel(int skillId) {
        if (isIgnoreMasterLevel(skillId)
                || (skillId / 1000000 == 92 && (skillId % 10000 == 0))
                || isMakingSkillRecipe(skillId)
                || isCommonSkill(skillId)
                || isNoviceSkill(skillId)
                || isFieldAttackObjSkill(skillId)) {
            return false;
        }
        if (isPreFourthNeedMasterLevelSkill(skillId)) {
            return true;
        }
        int job = getSkillRootFromSkill(skillId);
        return isPreFourthNeedMasterLevelSkill(skillId) || !JobConstants.isBeastTamer((short) job)
                && (isAddedSpDualAndZeroSkill(skillId) || (JobConstants.getJobLevel((short) job) == 4 && !JobConstants.isZero((short) job)));
    }

    private static boolean isPreFourthNeedMasterLevelSkill(int skillId) {
        return skillId == 42120024;
    }

    public static boolean isAddedSpDualAndZeroSkill(int skillId) {
        if (skillId > 101100101) {
            if (skillId > 101110203) {
                if (skillId == 101120104)
                    return true;
                return skillId == 101120204;
            } else {
                if (skillId == 101110203 || skillId == 101100201 || skillId == 101110102)
                    return true;
                return skillId == 101110200;
            }
        } else {
            if (skillId == 101100101)
                return true;
            if (skillId > 4331002) {
                if (skillId == 4340007 || skillId == 4341004)
                    return true;
                return skillId == 101000101;
            } else {
                if (skillId == 4331002 || skillId == 4311003 || skillId == 4321006)
                    return true;
                return skillId == 4330009;
            }
        }
    }

    public static int getSkillRootFromSkill(int skillId) {
        int prefix = skillId / 10000;
        if (prefix == 8000 || prefix == 8001) {
            prefix = skillId / 100;
        }
        return prefix;
    }

    public static boolean isFieldAttackObjSkill(int skillId) {
        if (skillId <= 0) {
            return false;
        }
        int prefix = skillId / 10000;
        if (prefix == 8000 || prefix == 8001) {
            prefix = skillId / 100;
        }
        return prefix == 9500;
    }

    private static boolean isNoviceSkill(int skillId) {
        int prefix  = skillId / 10000;
        if (skillId / 10000 == 8000) {
            prefix = skillId / 100;
        }
        return JobConstants.isBeginnerJob((short) prefix);
    }

    private static boolean isCommonSkill(int skillId) {
        int prefix = skillId / 10000;
        if (skillId / 10000 == 8000 || skillId / 10000 == 8001) {
            prefix = skillId / 100;
        }
        return (prefix >= 800000 && prefix < 800100);
    }

    public static boolean isMakingSkillRecipe(int recipeId) {
        boolean result = false;
        if (recipeId / 1000000 != 92 || recipeId % 10000 == 1) {
            int v1 = 10000 * (recipeId / 10000);
            if (v1 / 1000000 == 92 && (v1 % 10000 == 0))
                result = true;
        }
        return result;
    }

    public static boolean isIgnoreMasterLevel(int skillId) { // updated to v200.1
        switch (skillId) {
            case 1120012:
            case 1320011:
            case 2121009:
            case 2221009:
            case 2321010:
            case 3210015:
            case 4110012:
            case 4210012:
            case 4340010:
            case 4340012:
            case 5120011:
            case 5120012:
            case 5220012:
            case 5220014:
            case 5221022:
            case 5320007:
            case 5321004:
            case 5321006:
            case 21120011:
            case 21120014:
            case 21120020:
            case 21120021:
            case 21121008:
            case 22171069:
            case 23120013:
            case 23121008:
            case 23121011:
            case 33120010:
            case 35120014:
            case 51120000:
            case 80001913:
            case 152120003:
            case 152120012:
            case 152120013:
            case 152121006:
            case 152121010:
                return true;
            default:
                return false;
        }
    }

    public static boolean isKeyDownSkill(int skillId) {
        return skillId == 2321001 || skillId == 80001836 || skillId == 37121052 || skillId == 36121000 ||
                skillId == 37121003 || skillId == 36101001 || skillId == 33121114 || skillId == 33121214 ||
                skillId == 35121015 || skillId == 33121009 || skillId == 32121003 || skillId == 31211001 ||
                skillId == 31111005 || skillId == 30021238 || skillId == 31001000 || skillId == 31101000 ||
                skillId == 80001887 || skillId == 80001880 || skillId == 80001629 || skillId == 20041226 ||
                skillId == 60011216 || skillId == 65121003 || skillId == 80001587 || skillId == 131001008 ||
                skillId == 142111010 || skillId == 131001004 || skillId == 95001001 || skillId == 101110100 ||
                skillId == 101110101 || skillId == 101110102 || skillId == 27111100 || skillId == 12121054 ||
                skillId == 11121052 || skillId == 11121055 || skillId == 5311002 || skillId == 4341002 ||
                skillId == 5221004 || skillId == 5221022 || skillId == 3121020 || skillId == 3101008 ||
                skillId == 3111013 || skillId == 1311011 || skillId == 2221011 || skillId == 2221052 ||
                skillId == 25121030 || skillId == 27101202 || skillId == 25111005 || skillId == 23121000 ||
                skillId == 22171083 || skillId == 14121004 || skillId == 13111020 || skillId == 13121001 ||
                skillId == 14111006 || (skillId >= 80001389 && skillId <= 80001392) || skillId == 42121000 ||
                skillId == 42120003 || skillId == 5700010 || skillId == 5711021 || skillId == 5721001 ||
                skillId == 5721061 || skillId == 21120018 || skillId == 21120019 || skillId == 400041006 ||
                skillId == 400041009 || skillId == 400051024 || skillId == 400011028 || skillId == 400031024 ||
                skillId == 64121002 || skillId == 400011068 || skillId == 24121000 || skillId == 400041039 ||
                skillId == 400051041 || skillId == 41121001 || skillId == 155121341 || skillId == 400011072 ||
                skillId == 24121005 || skillId == 112001008 || skillId == 112110003 || skillId == 112111016 ||
                skillId == 400021072 || skillId == 400021061 || skillId == 3321036 || skillId == 3321035 ||
                skillId == 3321040 || skillId == 3321038|| skillId == 400051334 || skillId == 64101002 || skillId == 64101008 ||
                skillId == 164121042 || skillId == 151121004;
    }

    public static boolean isEvanForceSkill(int skillId) {
        switch (skillId) {
            case 22140022:
            case 22111011:
            case 22111012:
            case 22110022:
            case 22110023:
            case 22111017:
            case 80001894:
            case 22171062:
            case 22171063:
            case 22141011:
            case 22141012:
            case 400021046:
            case 400021012:
                return true;
        }
        return false;
    }

    public static boolean isSuperNovaSkill(int skillID) {
        return skillID == 4221052 || skillID == 65121052;
    }

    public static boolean isDelayedCooldownSkill(int skillID) {
        // used to bypass not attacking monsters because skill is on CD before
        // attack takes place. This however doesn't bypass CD. So these skills
        // will still be on the proper CD after using the skill.
        // Maybe these fall into isMultiAttackCooldownSkill...
        switch (skillID) {
            case BladeMaster.CHAINS_OF_HELL:
            case BladeMaster.FLASHBANG:
            case 400041021: // Blades of Destiny
            case DemonSlayer.DARK_METAMORPHOSIS:
            case NightWalker.DOMINION:
            case Adele.IMPALE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isGrenadeSkill(int skillID) {
        return SkillConstants.isRushBombSkill(skillID) ||
                skillID == 5300007 || skillID == 27120211 ||
                skillID == 14111023 || skillID == 400031003 ||
                skillID == 400031004 || skillID == 80011389 ||
                skillID == 80011390 || skillID == 400031036;
    }

    public static boolean isRushBombSkill(int skillID) {
        switch (skillID) {
            case 101120205:
            case 101120203:
            case 80011386:
            case 101120200:
            case 80011380:
            case 61111113:
            case 61111218:
            case 61111111:
            case 42120003:
            case 61111100:
            case 40021186:
            case 31201001:
            case 27121201:
            case 22140015:
            case 22140024:
            case 14111022:
            case 5101014:
            case 5301001:
            case 12121001:
            case 2221012:
            case 5101012:
            case 400001018:
            case 400031002:
            case 400031003:
            case 400031004:
            case 400011104:
                return true;

        }
        return false;
    }

    public static boolean isZeroSkill(int skillID) {
        int prefix = getPrefix(skillID);
        return prefix == 10000 || prefix == 10100 || prefix == 10110 || prefix == 10111 || prefix == 10112;
    }

    public static boolean isUsercloneSummonedAbleSkill(int skillID) {
        switch (skillID) {
            case 11101120:		// Flicker
            case 11101121:		// Trace Cut
            case 11101220:		// Bluster
            case 11101221:		// Shadow Tackle
            case 11111120:		// Moon Shadow
            case 11111121:		// Moon Cross
            case 11111220:		// Light Merger
            case 11111221:		// Sun Cross
            case 11121101:		// Moon Dancer
            case 11121102:		// Moon Dancer
            case 11121103:		// Crescent Divide
            case 11121201:		// Speeding Sunset
            case 11121202:		// Speeding Sunset
            case 11121203:		// Solar Pierce

            case 14001020:		// Lucky Seven
            case 14101020:		// Triple Throw
            case 14101021:		// Triple Throw
            case 14111020:		// Quad Star
            case 14111021:		// Quad Star
            case 14111022:		// Shadow Spark
            case 14111023:		// Shadow Spark
            case 14121001:		// Quintuple Star
            case 14121002:		// Quintuple Star

            case 23001000:		// Swift Dual Shot
            case 23100004:		// Parting Shot
            case 23101000:		// Piercing Storm
            case 23101001:		// Rising Rush
            case 23101007:		// Rising Rush
            case 23111000:		// Stunning Strikes
            case 23111001:		// Leap Tornado
            case 23111003:		// Gust Dive
            case 23110006:      // Aerial Barrage
            case 23120013:		// Staggering Strikes
            case 23121052:		// Wrath of Enlil
            case 23121000:      // Ishtar's Ring
            case 400031024:		// Irkalla's Wrath

            case 131001000:		// Pink Powerhouse
            case 131001001:		// Pink Powerhouse
            case 131001002:		// Pink Powerhouse
            case 131001003:		// Pink Powerhouse
            case 131001004:		// Let's Roll!
            case 131001005:		// Umbrella
            case 131001008:		// Sky Jump
            case 131001010:		// Blazing Yo-yo
            case 131001011:		// Blazing Yo-yo
            case 131001012:		// Pink Pulverizer
            case 131001013:		// Let's Rock!
            case 131001101:		// Pink Powerhouse
            case 131001102:		// Pink Powerhouse
            case 131001103:		// Pink Powerhouse
            case 131001104:		// Let's Roll!
            case 131001108:		// Mid-air Sky Jump
            case 131001113:		// Electric Guitar
            case 131001208:		// Sky Jump Grounder
            case 131001213:		// Whistle
            case 131001313:		// Megaphone
            case 131002010:		// Blazing Yo-yo
                return true;
        }
        return false;
    }

    public static BeastTamerBeasts getBeastFromSkill(int skillId) {
        switch (skillId / 10000) {
            case 11200:
                return BeastTamerBeasts.BEAR;
            case 11210:
                return BeastTamerBeasts.LEOPARD;
            case 11211:
                return BeastTamerBeasts.BIRD;
            case 11212:
                return BeastTamerBeasts.CAT;
            default:
                return BeastTamerBeasts.NONE;
        }
    }

    public static boolean isNoConsumeUseBulletMeleeAttack(int skillID) {
        return skillID == 14121052 || skillID == 14121003 || skillID == 14000028 || skillID == 14000029;
    }

    public static boolean isScreenCenterAttackSkill(int skillID) {
        switch (skillID) {
            case 80001431:
            case 80011562:
            case 100001283:
            case 21121057:
            case 13121052:
            case 14121050:
            case 14121052: // err 38 fix
            case 15121052:
                return true;
        }
        return false;
    }

    public static int getSkillIdByAtomSkillId(int skillID) {
        switch (skillID) {
            case SOUL_SEEKER_ATOM:
                return SOUL_SEEKER;
            case FirePoison.MEGIDDO_FLAME_ATOM:
                return FirePoison.MEGIDDO_FLAME;
            case Shadower.MESO_EXPLOSION_ATOM:
                return Shadower.MESO_EXPLOSION;
            case NightLord.ASSASSIN_MARK_ATOM:
                return NightLord.ASSASSINS_MARK;
            case NightLord.NIGHTLORD_MARK_ATOM:
                return NightLord.NIGHT_LORD_MARK;
            case FOX_SPIRITS_ATOM:
                return FOX_SPIRITS_INIT;
            case FOX_SPIRITS_ATOM_2:
                return FIRE_FOX_SPIRIT_MASTERY;
            case NETHER_SHIELD_ATOM:
                return NETHER_SHIELD;
            case ACE_IN_THE_HOLE_ATOM:
                return ACE_IN_THE_HOLE;
            default:
                return skillID;
        }
    }

    public static boolean isForceAtomSkill(int skillID) {
        switch (skillID) {

            // Adventurer
            // Fire Poison
            case FirePoison.MEGIDDO_FLAME_ATOM:
            case FirePoison.DOT_PUNISHER:

                // Archer
            case Archer.GUIDED_ARROW:

                // BowMaster
            case BowMaster.QUIVER_CARTRIDGE_ATOM:
            case BowMaster.QUIVER_BARRAGE_ATOM:

                // Pathfinder
            case Pathfinder.CARDINAL_DELUGE:
            case Pathfinder.CARDINAL_DELUGE_AMPLIFICATION:
            case Pathfinder.CARDINAL_DELUGE_ADVANCED:
            case Pathfinder.BOUNTIFUL_DELUGE:
            case Pathfinder.SWARM_SHOT_ATOM:
            case Pathfinder.BOUNTIFUL_BURST:
            case Pathfinder.ANCIENT_ASTRA_DELUGE_ATOM:

                // Night Lord
            case NightLord.ASSASSIN_MARK_ATOM:
            case NightLord.NIGHTLORD_MARK_ATOM:
            case NightLord.DARK_LORDS_OMEN:

                // Shadower
            case Shadower.MESO_EXPLOSION_ATOM:


                // Cygnus
                // Blaze Wizard
            case BlazeWizard.ORBITAL_FLAME_ATOM:
            case BlazeWizard.GREATER_ORBITAL_FLAME_ATOM:
            case BlazeWizard.GRAND_ORBITAL_FLAME_ATOM:
            case BlazeWizard.FINAL_ORBITAL_FLAME_ATOM:
            case BlazeWizard.SAVAGE_FLAME_FOX_ATOM:

                // Wind Archer
            case WindArcher.TRIFLING_WIND_I:
            case WindArcher.TRIFLING_WIND_II:
            case WindArcher.TRIFLING_WIND_III:
            case WindArcher.TRIFLING_WIND_ATOM_ENHANCED:
            case WindArcher.TRIFLING_WIND_II_ATOM:
            case WindArcher.TRIFLING_WIND_III_ENHANCED:
            case WindArcher.STORM_BRINGER:
            case WindArcher.GALE_BARRIER_ATOM:
            case WindArcher.MERCILESS_WINDS:

                // Night Walker
            case NightWalker.SHADOW_BAT_ATOM:
            case NightWalker.SHADOW_BAT_FROM_MOB_ATOM:
            case NightWalker.SHADOW_BITE:


                // Legend
                // Evan
            case Evan.MAGIC_DEBRIS:
            case Evan.ENHANCED_MAGIC_DEBRIS:

                // Phantom
            case Phantom.CARTE_NOIR:
            case Phantom.CARTE_BLANCHE:
            case Phantom.LUCK_OF_THE_DRAW_ATOM:
            case Phantom.ACE_IN_THE_HOLE_ATOM:

                // Shade
            case Shade.FOX_SPIRITS_ATOM:
            case Shade.FOX_SPIRITS_ATOM_2:


                // Resistance
                // Demon Avenger
            case DemonAvenger.NETHER_SHIELD_ATOM:

                // Battle Mage
            case BattleMage.GRIM_HARVEST:

                // Mechanic
            case Mechanic.MOBILE_MISSILE_BATTERY:
            case Mechanic.HOMING_BEACON:


                // Sengoku
                // Kanna
                // TODO Kanna's new ForceAtom


                // Nova
                // Kaiser
            case Kaiser.BLADEFALL_ATTACK:
            case Kaiser.BLADEFALL_ATTACK_FF:
            case Kaiser.TEMPEST_BLADES_THREE:
            case Kaiser.TEMPEST_BLADES_THREE_FF:
            case Kaiser.TEMPEST_BLADES_FIVE:
            case Kaiser.TEMPEST_BLADES_FIVE_FF:

                // Angelic Buster
            case AngelicBuster.SOUL_SEEKER_ATOM:
            case AngelicBuster.SPARKLE_BURST:


                // Flora
                // Illium
            case Illium.RADIANT_JAVELIN:
            case Illium.RADIANT_JAVELIN_II:
            case Illium.RADIANT_JAVELIN_ENHANCED:

                // Ark
            case Ark.IMPENDING_DEATH_ATOM:
            case Ark.VENGEFUL_HATE:
            case Ark.BASIC_CHARGE_DRIVE_ATOM:
            case Ark.SCARLET_CHARGE_DRIVE_ATOM:
            case Ark.GUST_CHARGE_DRIVE_ATOM:
            case Ark.ABYSSAL_CHARGE_DRIVE_ATOM:


                // Others
                // Kinesis
            case Kinesis.KINETIC_COMBO:

                return true;
            default:
                return false;
        }
    }

    public static boolean isAranFallingStopSkill(int skillID) {
        switch(skillID) {
            case 21110028:
            case 21120025:
            case 21110026:
            case 21001010:
            case 21000006:
            case 21000007:
            case 21110022:
            case 21110023:
            case 80001925:
            case 80001926:
            case 80001927:
            case 80001936:
            case 80001937:
            case 80001938:
                return true;
            default:
                return false;
        }
    }

    public static boolean isFlipAffectAreaSkill(int skillID) {
        if (isSomeAreaAffectSkill(skillID)) {
            return true;
        }
        switch (skillID) {
            case 4121015:
            case 51120057: // a passive hyper skill?
            case 131001107:
            case 131001207:
            case 152121041:
            case 400001017:
            case 400021039:
            case 400041041:
                return true;

        }
        return false;
    }

    private static boolean isSomeAreaAffectSkill(int skillID) {
        switch (skillID) {
            case 33111013:
            case 33121012:
            case 33121016:
            case 35121052:
            case 400020046:
            case 400020051:
                return true;
        }
        return false;
    }

    public static boolean isShootSkillNotConsumingBullets(int skillID) {
        int job = skillID / 10000;
        if (skillID / 10000 == 8000) {
            job = skillID / 100;
        }
        switch (skillID) {
            case 80001279:
            case 80001914:
            case 80001915:
            case 80001880:
            case 80001629:
            case 33121052:
            case 33101002:
            case 14101006:
            case 13101020:
            case 1078:
                return true;
            default:
                return getDummyBulletItemIDForJob(job, 0, 0) > 0
                        || isShootSkillNotUsingShootingWeapon(skillID, false)
                        || isFieldAttackObjSkill(skillID);

        }
    }

    private static boolean isShootSkillNotUsingShootingWeapon(int skillID, boolean bySteal) {
        if(bySteal || (skillID >= 80001848 && skillID <= 80001850)) {
            return true;
        }
        switch (skillID) {
            case 80001863:
            case 80001880:
            case 80001914:
            case 80001915:
            case 80001939:
            case 101110204:
            case 101110201:
            case 101000202:
            case 101100202:
            case 80001858:
            case 80001629:
            case 80001829:
            case 80001838:
            case 80001856:
            case 80001587:
            case 80001418:
            case 80001387:
            case 61111215:
            case 80001279:
            case 61001101:
            case 51121008:
            case 51111007:
            case 51001004:
            case 36111010:
            case 36101009:
            case 31111005:
            case 31111006: // ? was 26803624, guessing it's just a +1
            case 31101000:
            case 22110024:
            case 22110014:
            case 21120006:
            case 21100007:
            case 21110027:
            case 21001009:
            case 21000004:
            case 5121013:
            case 1078:
            case 1079:
                return true;
            default:
                return false;

        }
    }

    private static int getDummyBulletItemIDForJob(int job, int subJob, int skillID) {
        if ( job / 100 == 35 )
            return 2333000;
        if ( job / 10 == 53 || job == 501 || (job / 1000) == 0 && subJob == 2 )
            return 2333001;
        if ( JobConstants.isMercedes((short) job) )
            return 2061010;
        if ( JobConstants.isAngelicBuster(job) )
            return 2333001;
        // TODO:
//        if ( !JobConstants.isPhantom((short) job)
//                || !is_useable_stealedskill(skillID)
//                || (result = get_vari_dummy_bullet_by_cane(skillID), result <= 0) )
//        {
//            result = 0;
//        }
        return 0;
    }

    public static boolean isKeydownSkillRectMoveXY(int skillID) {
        return skillID == 13111020 || skillID == 112111016;
    }

    public static int getLinkedSkillOfOriginal(int skillID) {
        switch (skillID) {
            case 110:
                return 80000000;
            case 1214:
                return 80001151;
            case 10000255:
                return 80000066;
            case 10000256:
                return 80000067;
            case 10000257:
                return 80000068;
            case 10000258:
                return 80000069;
            case 10000259:
                return 80000070;
            case 20000297:
                return 80000370;
            case 20010294:
                return 80000369;
            case 20021110:
                return 80001040;
            case 20030204:
                return 80000002;
            case 20040218:
                return 80000005;
            case 20050286:
                return 80000169;
            case 30000074:
                return 80000333;
            case 30000075:
                return 80000334;
            case 30000076:
                return 80000335;
            case 30000077:
                return 80000378;
            case 30010112:
                return 80000001;
            case 30010241:
                return 80000050;
            case 30020233:
                return 80000047;
            case 40010001:
                return 80000003;
            case 40020002:
                return 80000004;
            case 50001214:
                return 80001140;
            case 60000222:
                return 80000006;
            case 60011219:
                return 80001155;
            case 60020218:
                return 80000261;
            case 100000271:
                return 80000110;
            case 110000800:
                return 80010006;
            case 140000292:
                return 80000188;
            case 150000017:
                return 80000268;
            case 150010241:
                return 80000514;
        }
        return 0;
    }

    public static int getOriginalOfLinkedSkill(int skillID) {
        switch(skillID) {
            case 80000000:
                return 110;
            case 80001151:
                return 1214;
            case 80000066:
                return 10000255;
            case 80000067:
                return 10000256;
            case 80000068:
                return 10000257;
            case 80000069:
                return 10000258;
            case 80000070:
                return 10000259;
            case 80000370:
                return 20000297;
            case 80000369:
                return 20010294;
            case 80001040:
                return 20021110;
            case 80000002:
                return 20030204;
            case 80000005:
                return 20040218;
            case 80000169:
                return 20050286;
            case 80000333:
                return 30000074;
            case 80000334:
                return 30000075;
            case 80000335:
                return 30000076;
            case 80000378:
                return 30000077;
            case 80000001:
                return 30010112;
            case 80000050:
                return 30010241;
            case 80000047:
                return 30020233;
            case 80000003:
                return 40010001;
            case 80000004:
                return 40020002;
            case 80001140:
                return 50001214;
            case 80000006:
                return 60000222;
            case 80001155:
                return 60011219;
            case 80000261:
                return 60020218;
            case 80000110:
                return 100000271;
            case 80010006:
                return 110000800;
            case 80000188:
                return 140000292;
            case 80000268:
                return 150000017;
            case 80000514:
                return 150010241;
            default:
                if (skillID != 0) {
                    log.error("Unknown corresponding link skill for link skill id " + skillID);
                }
                return 0;
        }
    }

    public static boolean isZeroAlphaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 2;
    }

    public static boolean isZeroBetaSkill(int skillID) {
        return isZeroSkill(skillID) && skillID % 1000 / 100 == 1;
    }

    public static boolean isLightmageSkill(int skillID) {
        int prefix = skillID / 10000;
        if(prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix / 100 == 27 || prefix == 2004;
    }

    public static boolean isLarknessDarkSkill(int skillID) {
        return skillID != 20041222 && isLightmageSkill(skillID) && (skillID % 1000 >= 200 && skillID % 1000 < 300);
    }

    public static boolean isLarknessLightSkill(int skillID) {
        return skillID != 20041222 && isLightmageSkill(skillID) && (skillID % 1000 >= 100 && skillID % 1000 < 200);
    }

    public static boolean isEquilibriumSkill(int skillID) {
        return (skillID >= 20040219 && skillID <= 20040220) || (skillID % 1000 >= 300 && skillID % 1000 < 400);
    }

    public static int getAdvancedCountHyperSkill(int skillId) {
        switch(skillId) {
            case 4121013:
                return 4120051;
            case 5321012:
                return 5320051;
            default:
                return 0;
        }
    }

    public static int getAdvancedAttackCountHyperSkill(int skillId) {
        switch(skillId) {
            case 25121005:
                return 25120148;
            case 31121001:
                return 31120050;
            case 31111005:
                return 31120044;
            case 22140023:
                return 22170086;
            case 21120022:
            case 21121015:
            case 21121016:
            case 21121017:
                return 21120066;
            case 21120006:
                return 21120049;
            case 21110020:
            case 21111021:
                return 21120047;
            case 15121002:
                return 15120048;
            case 14121002:
                return 14120045;
            case 15111022:
            case 15120003:
                return 15120045;
            case 51121008:
                return 51120048;
            case 32111003:
                return 32120047;
            case 35121016:
                return 35120051;
            case 37110002:
                return 37120045;
            case 51120057:
                return 51120058;
            case 51121007:
                return 51120051;
            case 65121007:
            case 65121008:
            case 65121101:
                return 65120051;
            case 61121201:
            case 61121100:
                return 61120045;
            case 51121009:
                return 51120058;
            case 13121002:
                return 13120048;
            case 5121016:
            case 5121017:
                return 5120051;
            case 3121015:
                return 3120048;
            case 2121006:
                return 2120048;
            case 2221006:
                return 2220048;
            case 1221011:
                return 1220050;
            case 1120017:
            case 1121008:
                return 1120051;
            case 1221009:
                return 1220048;
            case 4331000:
                return 4340045;
            case 3121020:
                return 3120051;
            case 3221017:
                return 3220048;
            case 4221007:
                return 4220048;
            case 4341009:
                return 4340048;
            case 5121007:
                return 5120048;
            case 5321004:
                return 5320043;
            // if ( nSkillID != &loc_A9B1CF ) nothing done with line 172?
            case 12110028:
            case 12000026:
            case 12100028:
                return 12120045;
            case 12120010:
                return 12120045;
            case 12120011:
                return 12120046;
            default:
                return isDelugeSkill(skillId)
                        || isBurstAttackingSkill(skillId)
                        || isTorrentSkill(skillId)
                        || isBurstSkill(skillId) ? 3320030 : 0;
        }
    }

    public static boolean isKinesisPsychicLockSkill(int skillId) {
        switch(skillId) {
            case 142120000:
            case 142120001:
            case 142120002:
            case 142120014:
            case 142111002:
            case 142100010:
            case 142110003:
            case 142110015:
                return true;
            default:
                return false;
        }
    }

    public static int getActualSkillIDfromSkillID(int skillID) {
        switch (skillID) {
            case 101120206: //Zero - Severe Storm Break (Tile)
                return 101120204; //Zero - Adv Storm Break

            case 4221016: //Shadower - Assassinate 2
                return 4221014; //Shadower - Assassinate 1

            case 41121020: //Hayato - Tornado Blade-Battoujutsu Link
                return 41121017; //Tornado Blade

            case 41121021: //Hayato - Sudden Strike-Battoujutsu Link
                return 41121018; //Sudden Strike

            case 5121017: //Bucc - Double Blast
                return 5121016; //Bucc - Buccaneer Blast

            case 5101014: //Bucc - Energy Vortex
                return 5101012; //Bucc - Tornado Uppercut

            case 5121020: //Bucc - Octopunch (Max Charge)
                return 5121007; //Bucc - Octopunch

            case 5111013: //Bucc - Hedgehog Buster
                return 5111002; //Bucc - Energy Burst

            case 5111015: //Bucc - Static Thumper
                return 5111012; //Bucc - Static Thumper

            case 31011004: //DA - Exceed Double Slash 2
            case 31011005: //DA - Exceed Double Slash 3
            case 31011006: //DA - Exceed Double Slash 4
            case 31011007: //DA - Exceed Double Slash Purple
                return 31011000; //DA - Exceed Double Slash 1

            case 31201007: //DA - Exceed Demon Strike 2
            case 31201008: //DA - Exceed Demon Strike 3
            case 31201009: //DA - Exceed Demon Strike 4
            case 31201010: //DA - Exceed Demon Strike Purple
                return 31201000; //DA - Exceed Demon Strike 1

            case 31211007: //DA - Exceed Lunar Slash 2
            case 31211008: //DA - Exceed Lunar Slash 3
            case 31211009: //DA - Exceed Lunar Slash 4
            case 31211010: //DA - Exceed Lunar Slash Purple
                return 31211000; //DA - Exceed Lunar Slash 1

            case 31221009: //DA - Exceed Execution 2
            case 31221010: //DA - Exceed Execution 3
            case 31221011: //DA - Exceed Execution 4
            case 31221012: //DA - Exceed Execution Purple
                return 31221000; //DA - Exceed Execution 1

            case 31211002: //DA - Shield Charge (Spikes)
                return 31211011; //DA - Shield Charge (Rush)

            case 61120219: //Kaiser - Dragon Slash (Final Form)
                return 61001000; //Kaiser - Dragon Slash 1

            case 61111215: //Kaiser - Flame Surge (Final Form)
                return 61001101; //Kaiser - Flame Surge

            case 61111216: //Kaiser - Impact Wave (Final Form)
                return 61101100; //Kaiser - Impact Wave

            case 61111217: //Kaiser - Piercing Blaze (Final Form)
                return 61101101; //Kaiser - Piercing Blaze

            case 61111111: //Kaiser - Wing Beat (Final Form)
                return 61111100; //Kaiser - Wing Beat

            case 61111219: //Kaiser - Pressure Chain (Final Form)
                return 61111101; //Kaiser - Pressure Chain

            case 61121201: //Kaiser - Gigas Wave (Final Form)
                return 61121100; //Kaiser - Gigas Wave

            case 61121222: //Kaiser - Inferno Breath (Final Form)
                return 61121105; //Kaiser - Inferno Breath

            case 61121203: //Kaiser - Dragon Barrage (Final Form)
                return 61121102; //Kaiser - Dragon Barrage

            case 61121221: //Kaiser - Blade Burst (Final Form)
                return 61121104; //Kaiser - Blade Burst

            case 14101021: //NW - Quint. Throw Finisher
                return 14101020; //NW - Quint. Throw

            case 14111021: //NW - Quad Throw Finisher
                return 14111020; //NW - Quad Throw

            case 14121002: //NW - Triple Throw Finisher
                return 14121001; //NW - Triple Throw

            case STAGGERING_STRIKES:
                return STUNNING_STRIKES;

            case SMASH_WAVE_COMBO:
                return SMASH_WAVE;

            case FINAL_BLOW_COMBO:
            case FINAL_BLOW_SMASH_SWING_COMBO:
                return FINAL_BLOW;

            case SOUL_SEEKER_ATOM:
                return SOUL_SEEKER;

            case 65101006: //AB - Lovely Sting Explosion
                return LOVELY_STING;

            case 65121007:
            case 65121008:
                return TRINITY;

            case REVOLVING_CANNON_2:
            case REVOLVING_CANNON_3:
                return REVOLVING_CANNON;

            case WindArcher.HOWLING_GALE_2:
                return WindArcher.HOWLING_GALE_1;

            default:
                return skillID;
        }
    }

    public static int getKaiserGaugeIncrementBySkill(int skillID) {
        HashMap<Integer, Integer> hashMapIncrement = new HashMap<>();
        hashMapIncrement.put(DRAGON_SLASH_1, 1);
        hashMapIncrement.put(DRAGON_SLASH_2, 3);
        hashMapIncrement.put(DRAGON_SLASH_3, 4);
        hashMapIncrement.put(DRAGON_SLASH_1_FINAL_FORM, 1);

        hashMapIncrement.put(Kaiser.FLAME_SURGE, 2);
        hashMapIncrement.put(FLAME_SURGE_FINAL_FORM, 2);

        hashMapIncrement.put(IMPACT_WAVE, 5);
        hashMapIncrement.put(IMPACT_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(PIERCING_BLAZE, 5);
        hashMapIncrement.put(PIERCING_BLAZE_FINAL_FORM, 0);

        hashMapIncrement.put(WING_BEAT, 2);
        hashMapIncrement.put(WING_BEAT_FINAL_FORM, 1);

        hashMapIncrement.put(PRESSURE_CHAIN, 8);
        hashMapIncrement.put(PRESSURE_CHAIN_FINAL_FORM, 0);

        hashMapIncrement.put(GIGA_WAVE, 8);
        hashMapIncrement.put(GIGA_WAVE_FINAL_FORM, 0);

        hashMapIncrement.put(INFERNO_BREATH, 14);
        hashMapIncrement.put(INFERNO_BREATH_FINAL_FORM, 0);

        hashMapIncrement.put(DRAGON_BARRAGE, 6);
        hashMapIncrement.put(DRAGON_BARRAGE_FINAL_FORM, 0);

        hashMapIncrement.put(BLADE_BURST, 6);
        hashMapIncrement.put(BLADE_BURST_FINAL_FORM, 0);

        hashMapIncrement.put(TEMPEST_BLADES_FIVE, 15);
        hashMapIncrement.put(TEMPEST_BLADES_FIVE_FF, 0);

        hashMapIncrement.put(TEMPEST_BLADES_THREE, 15);
        hashMapIncrement.put(TEMPEST_BLADES_THREE_FF, 0);

        return hashMapIncrement.getOrDefault(skillID, 0);
    }

    public static boolean isEvanFusionSkill(int skillID) {
        switch (skillID) {
            case 22110014:
            case 22110025:
            case 22140014:
            case 22140015:
            case 22140024:
            case 22140023:
            case 22170065:
            case 22170066:
            case 22170067:
            case 22170094:
                return true;
            default:
                return false;
        }
    }

    public static boolean isShikigamiHauntingSkill(int skillID) {
        switch(skillID) {
            case 80001850:
            case 42001000:
            case 42001005:
            case 42001006:
            case 40021185:
            case 80011067:
                return true;
            default:
                return false;
        }
    }

    public static boolean isStealableSkill(int skillID) {
        // TODO
        return false;
    }

    public static boolean isNoRemoteEncode(int skillID) {
        switch (skillID) {
            case 11121014:
            case Shadower.SHADOW_ASSAULT:
            case Shadower.SHADOW_ASSAULT_2:
            case Shadower.SHADOW_ASSAULT_3:
            case Shadower.SHADOW_ASSAULT_4:
                return true;
        }
        return false;
    }

    public static int getStealSkillManagerTabFromSkill(int skillID) {
        int smJobID;

        //Hyper Skills
        if(skillID % 100 == 54) {
            return 5;
        }
        switch (skillID / 10000) {

            // 1st Job Tab
            case 100:
            case 200:
            case 300:
            case 400:
            case 430:
            case 500:
            case 501:
                return 1;

            // 2nd Job Tab
            case 110:
            case 120:
            case 130:

            case 210:
            case 220:
            case 230:


            case 310:
            case 320:

            case 410:
            case 420:
            case 431:
            case 432:

            case 510:
            case 520:
            case 530:
                return 2;

            // 3rd Job Tab
            case 111:
            case 121:
            case 131:

            case 211:
            case 221:
            case 231:

            case 311:
            case 321:

            case 411:
            case 421:
            case 433:

            case 511:
            case 521:
            case 531:
                return 3;

            // 4th job Tab
            case 112:
            case 122:
            case 132:

            case 212:
            case 222:
            case 232:

            case 312:
            case 322:

            case 412:
            case 422:
            case 434:

            case 512:
            case 522:
            case 532:
                return 4;
        }
        return -1;
    }

    public static int getMaxPosBysmJobID(int smJobID) {
        int maxPos = 0;
        switch (smJobID) {
            case 1:
            case 2:
                maxPos = 3;
                break;
            case 3:
                maxPos = 2;
                break;
            case 4:
            case 5:
                maxPos = 1;
                break;
        }
        return maxPos;
    }

    public static int getStartPosBysmJobID(int smJobID) {
        int startPos = 0;
        switch (smJobID) {
            case 1:
                startPos = 0;
                break;
            case 2:
                startPos = 4;
                break;
            case 3:
                startPos = 8;
                break;
            case 4:
                startPos = 11;
                break;
            case 5:
                startPos = 13;
                break;
        }
        return startPos;
    }

    public static int getImpecSkillIDBysmJobID(int smJobID) {
        int impecSkillID = 0;
        switch (smJobID) {
            case 1:
                impecSkillID = 24001001;
                break;
            case 2:
                impecSkillID = 24101001;
                break;
            case 3:
                impecSkillID = 24111001;
                break;
            case 4:
                impecSkillID = 24121001;
                break;
            case 5:
                impecSkillID = 24121054;
                break;
        }
        return impecSkillID;
    }

    public static int getSMJobIdByImpecSkillId(int impecSkillId) {
        switch (impecSkillId) {
            case 24001001:  // 1st Job
                return 1;
            case 24101001:  // 2nd Job
                return 2;
            case 24111001:  // 3rd job
                return 3;
            case 24121001:  // 4th Job
                return 4;
            case 24121054:  // Hyper Skill
                return 5;
        }
        return -1;
    }

    public static int getLinkSkillByJob(short job) {
        if (JobConstants.isAngelicBuster(job)) { // Terms and Conditions
            return 80001155;
        } else if (JobConstants.isAran(job)) { // Combo Kill Blessing
            return 80000370;
        } else if (JobConstants.isArk(job)) { // Solus
            return 80000514;
        } else if (JobConstants.isBeastTamer(job)) { // Focus Spirit
            return 80010006;
        } else if (JobConstants.isCannonShooter(job)) { // Pirate Blessing
            return 80000000;
        } else if (JobConstants.isCygnusKnight(job)) { // Cygnus Blessing
            return 80000070;
        } else if (JobConstants.isDemonAvenger(job)) { // Wild Rage
            return 80000050;
        } else if (JobConstants.isDemonSlayer(job)) { // Fury Unleashed
            return 80000001;
        } else if (JobConstants.isShade(job)) { // Close Call
            return 80000169;
        } else if (JobConstants.isEvan(job)) { // Rune Persistence
            return 80000369;
        } else if (JobConstants.isHayato(job)) { // Keen Edge
            return 80000003;
        } else if (JobConstants.isIllium(job)) { // Tide of Battle
            return 80000268;
        } else if (JobConstants.isJett(job)) { // Core Aura
            return 80001151;
        } else if (JobConstants.isKaiser(job)) { // Iron Will
            return 80000006;
        } else if (JobConstants.isKanna(job)) { // Elementalism
            return 80000004;
        } else if (JobConstants.isKinesis(job)) { // Judgment
            return 80000188;
        } else if (JobConstants.isLuminous(job)) { // Light Wash
            return 80000005;
        } else if (JobConstants.isMercedes(job)) { // Elven Blessing
            return 80001040;
        }  else if (JobConstants.isMihile(job)) { // Knight's Watch
            return 80001140;
        } else if (JobConstants.isPhantom(job)) { // Phantom Instinct
            return 80000002;
        } else if (JobConstants.isXenon(job)) { // Hybrid Logic
            return 80000047;
        } else if (JobConstants.isZero(job)) { // Rhinne's blessing
            return 80000110;
        }
        // Resistance jobs (Spirit of Freedom)
        else if (JobConstants.isBlaster(job)) {
            return 80000378;
        } else if (JobConstants.isBattleMage(job)) {
            return 80000333;
        } else if (JobConstants.isWildHunter(job)) {
            return 80000334;
        } else if (JobConstants.isMechanic(job)) {
            return 80000335;
        }
        return 0;
    }

    public static int getLinkSkillLevelByCharLevel(short level) {
        int res = 0;
        if (level >= LINK_SKILL_3_LEVEL) {
            res = 3;
        } else if (level >= LINK_SKILL_2_LEVEL) {
            res = 2;
        } else if (level >= LINK_SKILL_1_LEVEL) {
            res = 1;
        }
        return res;
    }

    public static int getLinkedSkill(int skillID) {
        switch(skillID) {
            case Zero.STORM_BREAK_INIT:
                return Zero.STORM_BREAK;
            case Zero.ADV_STORM_BREAK_SHOCK_INIT:
                return Zero.ADV_STORM_BREAK;
        }
        return skillID;
    }

    public static boolean isPassiveSkill_NoPsdSkillsCheck(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        return si != null && si.isPsd() || SkillConstants.isPsd(skillId);
    }

    private static boolean isPsd(int skillId) {
        // for (mostly old) skills that aren't specified as passives in wz
        return skillId == 3000001 ||
                skillId == BeastTamer.GROWTH_SPURT ||
                skillId == BeastTamer.BEAST_SCEPTER_MASTERY;
    }

    public static boolean isPassiveSkill(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        return SkillConstants.isPsd(skillId) || (si != null && si.isPsd() && si.getPsdSkills().size() == 0);
    }

    public static boolean isHyperstatSkill(int skillID) {
        return skillID >= 80000400 && skillID <= 80000421 && skillID != 80000418;
    }

    public static int getTotalHyperStatSpByLevel(short level) {
        int sp = 0;
        for (int i = 140; i <= level; i++) {
            sp += getHyperStatSpByLv(level);
        }
        return sp;
    }

    public static int getHyperStatSpByLv(short level) {
        return 3 + ((level - 140) / 10);
    }

    public static int getNeededSpForHyperStatSkill(int lv) {
        switch (lv) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 20;
            case 8:
                return 25;
            case 9:
                return 30;
            case 10:
                return 35;
            case 11:
                return 50;
            case 12:
                return 65;
            case 13:
                return 80;
            case 14:
                return 95;
            case 15:
                return 110;
            default:
                return 0;
        }
    }

    public static int getTotalNeededSpForHyperStatSkill(int lv) {
        switch (lv) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 7;
            case 4:
                return 15;
            case 5:
                return 25;
            case 6:
                return 40;
            case 7:
                return 60;
            case 8:
                return 85;
            case 9:
                return 115;
            case 10:
                return 150;
            case 11:
                return 200;
            case 12:
                return 265;
            case 13:
                return 345;
            case 14:
                return 440;
            case 15:
                return 550;
            default:
                return 0;
        }
    }

    public static int getHyperStatExpBySlv(int slv) {
        if (slv <= 10) {
            return 50 * slv;
        }
        switch (slv) {
            case 11:
                return 600;
            case 12:
                return 700;
            case 13:
                return 800;
            case 14:
                return 900;
            case 15:
                return 1000;
        }
        return 0;
    }

    public static boolean isUnregisteredSkill(int skillID) {
        int prefix = skillID / 10000;
        if (prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix != 9500 && skillID / 10000000 == 9;
    }

    public static boolean isHomeTeleportSkill(int skillId) {
        switch (skillId) {
            // Adventurers
            case Warrior.MAPLE_RETURN: // All Adventurers
            case Pathfinder.RETURN_TO_PARTEM: // Pathfinder
            case BladeMaster.RETURN: // BladeMaster

                // Cygnus Knights
            case DawnWarrior.IMPERIAL_RECALL: // All Cygnus Knights
            case Mihile.IMPERIAL_RECALL:

                // Legends
            case Aran.RETURN_TO_RIEN:
            case Evan.BACK_TO_NATURE:
            case Phantom.TO_THE_SKIES:

                // Resistance
            case Demon.SECRET_ASSEMBLY:  // All Resistance
            case Xenon.PROMESSA_ESCAPE:

                // Sengoku
            case Hayato.RETURN_OF_THE_FIVE_PLANETS:
            case Kanna.RETURN_OF_THE_FIVE_PLANETS:

                // Nova
            case Kaiser.GUARDIANS_RETURN:
            case AngelicBuster.DAY_DREAMER:
            case Cadena.BACK_TO_HQ:

                // Lef
            case Illium.SHELTER_RETURN:

                // Others
            case BeastTamer.HOMEWARD_BOUND:
            case Kinesis.RETURN_KINESIS:
            case Zero.TEMPLE_RECALL:

                return true;
            default:
                return false;
        }
    }

    public static boolean isArmorPiercingSkill(int skillId) {
        switch (skillId) {
            case 3120017:
            case 95001000:
            case 3120008:
            case 3100001:
            case 3100010:
                return false;

            default:
                return true;
        }
    }

    public static int getBaseSpByLevel(short level) {
        return level > 140 ? 0
                : level > 130 ? 6
                : level > 120 ? 5
                : level > 110 ? 4
                : 3;
    }

    public static int getTotalPassiveHyperSpByLevel(short level) {
        return level < 140 ? 0 : (level - 130) / 10;
    }

    public static int getTotalActiveHyperSpByLevel(short level) {
        return level < 140 ? 0 : level < 170 ? 1 : level < 200 ? 2 : 3;
    }

    public static boolean isGuildSkill(int skillID) {
        int prefix = skillID / 10000;
        if (prefix == 8000) {
            prefix = skillID / 100;
        }
        return prefix == 9100;
    }

    public static boolean isGuildContentSkill(int skillID) {
        return (skillID >= 91000007 && skillID <= 91000015) || (skillID >= 91001016 && skillID <= 91001021);
    }

    public static boolean isGuildNoblesseSkill(int skillID) {
        return skillID >= 91001022 && skillID <= 91001024;
    }

    public static boolean isExtraSkill(int skillID){
        switch (skillID){
            case 27121201:
                return true;
        }
        return false;
    }

    public static boolean isMultiAttackCooldownSkill(int skillID) {
        switch (skillID) {
            case 5311010:
            case 5711021:
            case 22171063:
            case MERCILESS_WINDS:
            case FirePoison.DOT_PUNISHER:
            case IceLightning.THUNDER_STORM:
            case Noblesse.PHALANX_CHARGE:
            case ThunderBreaker.LIGHTNING_CASCADE:
            case ThunderBreaker.SHARK_TORPEDO:
            case 400041020:
            case 400011004:
            case Cannonneer.BIG_HUGE_GIGANTIC_ROCKET:
            case Buccaneer.LORD_OF_THE_DEEP:
            case 22141012:
            //case 64101002:
            case 155101104:
            case 155121306:
            case DemonSlayer.DEMON_CRY:
            case 400011058: // Bladefall
            case 11121014:
            case 4221052:
            case 400011068:
            case DemonSlayer.SPIRIT_OF_RAGE:
            case Paladin.HAMMERS_OF_THE_RIGHTEOUS:
            case Paladin.HAMMERS_OF_THE_RIGHTEOUS_2:
            case BladeMaster.BLADE_TEMPEST:
            case Kaiser.TEMPEST_BLADES_FIVE:
            case Kaiser.TEMPEST_BLADES_FIVE_FF:
            case Kaiser.TEMPEST_BLADES_THREE:
            case Kaiser.TEMPEST_BLADES_THREE_FF:
            case 65121003: // Soul resonance
            case 400041039:
            case Mechanic.FULL_METAL_BARRAGE:
            case 400021028:
            case 400021029:
            case BowMaster.QUIVER_BARRAGE_ATOM:
            case Illium.LONGINUS_SPEAR:
            case NightLord.DARK_LORDS_OMEN:
            case Kinesis.MIND_OVER_MATTER:
            case DarkKnight.RADIANT_EVIL:
            case Illium.REACTION_DESTRUCTION_II:
            case Job.GUIDED_ARROW:
            case LIBERATED_SPIRIT_CIRCLE_SMALL:
            case LIBERATED_SPIRIT_CIRCLE_BIG:
            case LIBERATED_SPIRIT_CIRCLE_SUMMON:
            case LIBERATED_SPIRIT_CIRCLE_SUMMON_2:
            case 400021019: // Start    Champ Charge [Beast Tamer]
            case 400021020:
            case 400021021:
            case 400021022:
            case 400021023:
            case 400021024:
            case 400021025:
            case 400021026:
            case 400021027:
            case 400021034:
            case 400021035:
            case 400021036:
            case 400021037:
            case 400021038:
            case 400021039: // End      Champ Charge [Beast Tamer]
            case Pathfinder.SWARM_SHOT:
            case Pathfinder.SWARM_SHOT_ATOM:
            case Pathfinder.ANCIENT_ASTRA_DELUGE_ATOM:
            case Pathfinder.ANCIENT_ASTRA_DELUGE:
            case 400031036:
            case 95001000:

            case Hero.BURNING_SOUL_BLADE:
            case 65121052:
            case IceLightning.FROZEN_ORB:
            case Ark.VENGEFUL_HATE:
                //case Corsair.NAUTILUS_ASSAULT:
            case BlazeWizard.CATACLYSM:
                //  case Jett.FALLING_STARS:

            case Kinesis.PSYCHIC_TORNADO:
            case 400021009:
            case 400021010:
            case 400021011:

            case Adele.MAGIC_DISPATCH:
            case Adele.HUNTING_DECREE:
            case Adele.INFINITY_BLADE:
            case Adele.AETHER_FORGE_1:
            case Adele.AETHER_FORGE_2:
            case Adele.AETHER_FORGE_3:
                return true;
        }
        return false;
    }

    public static boolean isBypassCooldownSkill(int skillID) {
        switch (skillID) {
            case Job.LAST_RESORT:
            case Mihile.SHIELD_OF_LIGHT:
            case HoYoung.CLONE_1:
            case HoYoung.CLONE_RAMPAGE_ATOM:
                return true;
        }
        return false;
    }

    public static boolean isUserCloneSummon(int skillID) {
        switch (skillID) {
            case 14111024:
            case 14121054:
            case 14121055:
            case 14121056:
            case 131001017:
            case 131003017:
            case 400011005:
            case 400031007:
            case 400031008:
            case 400031009:
            case 400041028:
                return true;
        }
        return false;
    }

    public static boolean isStateUsingSummon(int skillId) {
        switch (skillId) {
            case Illium.DEPLOY_CRYSTAL:
            case NightWalker.GREATER_DARK_SERVANT:
            case MIGHTY_MASCOT:

                return true;
            default:
                return false;
        }
    }

    public static boolean isDivineEchoMimicSkills(int skillId) {
        switch (skillId) {
            case Paladin.FLAME_CHARGE:
            case Paladin.BLIZZARD_CHARGE:
            case Paladin.LIGHTNING_CHARGE:
            case Paladin.DIVINE_CHARGE:
            case Paladin.BLAST:
            case Paladin.HEAVENS_HAMMER:
            case Paladin.SMITE_SHIELD:

            case Paladin.THREATEN:
            case Paladin.MAGIC_CRASH_PALLY:

            case Paladin.WEAPON_BOOSTER_PAGE:
            case Paladin.ELEMENTAL_FORCE:
            case Paladin.SACROSANCTITY:
            case Paladin.HP_RECOVERY:
                return true;

            default:
                return false;
        }
    }

    public static boolean isSuborbitalStrike(int skillID) {
        return skillID >= 400051028 && skillID <= 400051032;
    }

    public static boolean isSummonJaguarSkill(int skillID) {
        return skillID >= 33001007 && skillID <= 33001015;
    }

    public static boolean isShadowAssault(int skillID) {
        return skillID >= 400041002 && skillID <= 400041005;
    }

    public static boolean isMatching(int rootId, int job) {
        boolean matchingStart = job / 100 == rootId / 100;
        boolean matching = matchingStart;
        if (matchingStart && rootId % 100 != 0) {
            // job path must match
            if (rootId == 301) {
                matching = JobConstants.isPathFinder((short) job);
            } else {
                matching = (rootId % 100) / 10 == (job % 100) / 10;
            }
        }
        return matching;
    }

    // is_skill_from_item(signed int nSkillID)
    public static boolean isSkillFromItem(int skillID) {
        switch (skillID) {
            case 80011123: // New Destiny
            case 80011247: // Dawn Shield
            case 80011248: // Dawn Shield
            case 80011249: // Divine Guardian
            case 80011250: // Divine Shield
            case 80011251: // Divine Brilliance
            case 80011261: // Monolith
            case 80011295: // Scouter
            case 80011346: // Ribbit Ring
            case 80011347: // Krrr Ring
            case 80011348: // Rawr Ring
            case 80011349: // Pew Pew Ring
            case 80011475: // Elunarium Power (ATT & M. ATT)
            case 80011476: // Elunarium Power (Skill EXP)
            case 80011477: // Elunarium Power (Boss Damage)
            case 80011478: // Elunarium Power (Ignore Enemy DEF)
            case 80011479: // Elunarium Power (Crit Rate)
            case 80011480: // Elunarium Power (Crit Damage)
            case 80011481: // Elunarium Power (Status Resistance)
            case 80011482: // Elunarium Power (All Stats)
            case 80011492: // Firestarter Ring
            case 80001768: // Rope Lift
            case 80001705: // Rope Lift
            case 80001941: // Scouter
            case 80010040: // Altered Fate
                return true;
        }
        // Tower of Oz skill rings
        return (skillID >= 80001455 && skillID <= 80001479);
    }

    public static int getHyperPassiveSkillSpByLv(int level) {
        // 1 sp per 10 levels, starting at 140, ending at 220
        return level >= 140 && level <= 220 && level % 10 == 0 ? 1 : 0;
    }

    public static int getHyperActiveSkillSpByLv(int level) {
        return level == 150 || level == 170 || level == 200 ? 1 : 0;
    }

    public static boolean isSomeFifthSkillForRemote(int skillId) {
        switch (skillId) {
            case 400051003:
            case 400051008:
            case 400051016:
            case 400041034:
            case 400041020:
            case 400041016:
            case 400021078:
            case 400021080:
            case 400021009:
            case 400021010:
            case 400021011:
            case 400021028:
            case 400021047:
            case 400021048:
            case 400011004:
            case 152121004:
            case 152001002:
            case 152120003:
                return true;
        }
        return false;
    }

    public static boolean isArkSkill1(int skillID) {
        return JobConstants.isArk((short) (skillID / 10000));
    }

    public static boolean isArkSkill2(int skillID) {
        switch (skillID) {
            case 155001100:
            case 155120000:
                return true;
            default:
                return false;
        }
    }

    public static boolean isArkForceAtomAttack(int skillID) {
        switch (skillID) {
            case Ark.BASIC_CHARGE_DRIVE_ATOM:
            case Ark.SCARLET_CHARGE_DRIVE_ATOM:
            case Ark.GUST_CHARGE_DRIVE_ATOM:
            case ABYSSAL_CHARGE_DRIVE_ATOM:
            case IMPENDING_DEATH_ATOM:
            case VENGEFUL_HATE:
                return true;
            default:
                return false;
        }
    }

    public static int getNoviceSkillRoot(short job) {
        if (job / 100 == 22 || job == 2001) {
            return JobConstants.JobEnum.EVAN_NOOB.getJobId();
        }
        if (job / 100 == 23 || job == 2002) {
            return JobConstants.JobEnum.MERCEDES.getJobId();
        }
        if (job / 100 == 24 || job == 2003) {
            return JobConstants.JobEnum.PHANTOM.getJobId();
        }
        if (JobConstants.isDemon(job)) {
            return JobConstants.JobEnum.DEMON.getJobId();
        }
        if (JobConstants.isMihile(job)) {
            return JobConstants.JobEnum.NAMELESS_WARDEN.getJobId();
        }
        if (JobConstants.isLuminous(job)) {
            return JobConstants.JobEnum.LUMINOUS.getJobId();
        }
        if (JobConstants.isAngelicBuster(job)) {
            return JobConstants.JobEnum.ANGELIC_BUSTER.getJobId();
        }
        if (JobConstants.isXenon(job)) {
            return JobConstants.JobEnum.XENON.getJobId();
        }
        if (JobConstants.isShade(job)) {
            return JobConstants.JobEnum.SHADE.getJobId();
        }
        if (JobConstants.isKinesis(job)) {
            return JobConstants.JobEnum.KINESIS_0.getJobId();
        }
        if (JobConstants.isBlaster(job)) {
            return JobConstants.JobEnum.CITIZEN.getJobId();
        }
        if (JobConstants.isHayato(job)) {
            return JobConstants.JobEnum.HAYATO.getJobId();
        }
        if (JobConstants.isKanna(job)) {
            return JobConstants.JobEnum.KANNA.getJobId();
        }
        return 1000 * (job / 1000);
    }

    public static int getNoviceSkillFromRace(int skillID) {
        if (skillID == 50001215 || skillID == 10001215) {
            return 1005;
        }
        if (isCommonSkill(skillID) || (skillID >= 110001500 && skillID <= 110001504)) {
            return skillID;
        }
        if (isNoviceSkill(skillID)) {
            return skillID % 10000;
        }
        return 0;
    }

    public static int getBuffSkillItem(int buffSkillID) {
        int novice = getNoviceSkillFromRace(buffSkillID);
        switch (novice) {
            // Angelic Blessing
            case 86:
                return 2022746;
            // Dark Angelic Blessing
            case 88:
                return 2022747;
            // Angelic Blessing
            case 91:
                return 2022764;
            // White Angelic Blessing
            case 180:
                return 2022823;
            // Lightning God's Blessing
            case 80000086:
                return 2023189;
            // White Angelic Blessing
            case 80000155:
                return 2022823;
            // Lightning God's Blessing
            case 80010065:
                return 2023189;
            // Goddess' Guard
            case 80011150:
                return 1112932;
        }
        return 0;
    }

    public static String getMakingSkillName(int skillID) {
        switch (skillID) {
            case 92000000:
                return "Herbalism";
            case 92010000:
                return "Mining";
            case 92020000:
                return "Smithing";
            case 92030000:
                return "Accessory Crafting";
            case 92040000:
                return "Alchemy";
        }
        return null;
    }

    public static int recipeCodeToMakingSkillCode(int skillID) {
        return 10000 * (skillID / 10000);
    }

    public static int getNeededProficiency(int level) {
        if (level <= 0 || level >= MAKING_SKILL_EXPERT_LEVEL) {
            return 0;
        }
        return ((100 * level * level) + (level * 400)) / 2;
    }

    public static boolean isSynthesizeRecipe(int recipeID) {
        return isMakingSkillRecipe(recipeID) && recipeID % 10000 == 9001;
    }

    public static boolean isDecompositionRecipeScroll(int recipeID) {
        return isMakingSkillRecipe(recipeID)
                && recipeCodeToMakingSkillCode(recipeID) == 92040000
                && recipeID - 92040000 >= 9003
                && recipeID - 92040000 <= 9006;
    }

    public static boolean isDecompositionRecipeCube(int recipeID) {
        return isMakingSkillRecipe(recipeID) && recipeCodeToMakingSkillCode(recipeID) == 92040000 && recipeID == 92049002;
    }

    public static boolean isDecompositionRecipe(int recipeID) {
        if (isMakingSkillRecipe(recipeID) && recipeCodeToMakingSkillCode(recipeID) == 92040000 && recipeID == 92049000
                || isDecompositionRecipeScroll(recipeID)
                || isDecompositionRecipeScroll(recipeID)) {
            return true;
        }
        return false;
    }

    public static int getFairyBlessingByJob(short job) {
        short beginJob = JobConstants.JobEnum.getJobById(job).getBeginnerJobId();
        // xxxx0012, where xxxx is the "0th" job
        return beginJob * 10000 + 12;
    }

    public static int getEmpressBlessingByJob(short job) {
        short beginJob = JobConstants.JobEnum.getJobById(job).getBeginnerJobId();
        // xxxx0073, where xxxx is the "0th" job
        return beginJob * 10000 + 73;
    }

    public static boolean isBlessingSkill(int skillId) {
        return JobConstants.isBeginnerJob((short) (skillId / 10000)) && skillId % 100 == 12 || skillId % 100 == 73;
    }

    public static boolean isBeginnerSpAddableSkill(int skillID) {
        return skillID == 1000 || skillID == 1001 || skillID == 1002 || skillID == 140000291 || skillID == 30001000
                || skillID == 30001001 || skillID == 30001002;
    }

    public static boolean isNoMPConsumeSkill(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        if (si == null) {
            return false;
        }
        return isMultiAttackCooldownSkill(skillId) ||
                isBypassCooldownSkill(skillId) ||
                isForceAtomSkill(skillId) ||
                isKeyDownSkill(skillId) ||
                isKeydownSkillRectMoveXY(skillId) ||
                skillId == 400031003 ||
                skillId == 400031004 ||
                skillId == 400031036 ||
                skillId == 61111100 || skillId == WING_BEAT_FINAL_FORM ||
                skillId == BattleMage.ALTAR_OF_ANNIHILATION ||
                skillId == Xenon.OMEGA_BLASTER;
        // More exceptions
    }

    public static boolean isWingedJavelinOrAbyssalCast(int skillID) {
        switch (skillID) {
            case 152110004:
            case 152120016:
            case 155121003:
                return true;
        }
        return false;
    }

    public static boolean isRandomAttackSkill(int skillID) {
        switch (skillID) {
            case 80011561: // Support Skill: Skuas Catapults
            case 80002463: // Alliance Support: Bombardment Support
            case 80001762: // Liberate the Rune of Thunder
            case 80002212: // Lure of Storms
                return true;
        }
        return false;
    }

    public static boolean isSentientArrowOrTornadoFlight(int skillID) {
        return skillID == 13111020 || skillID == 112111016;
    }

    public static boolean isSomeAA(int skillID) {
        switch (skillID) {
            case 400021053: // Ultimate - Mind Over Matter
            case 400021029: // Poison Nova
            case 64111012:  // Summon Decoy Bomb
            case 400020009: // Psychic Tornado
            case 400020010: // ^
            case 400020011: // ^
                return true;
        }
        return false;
    }

    public static boolean isLuckOfTheDrawSkill(int skillID) {
        int num = skillID - 400041011;
        return num >= 0 && num <= 4;
    }

    public static boolean isSomeBlasterSkill(int skillID) {
        switch (skillID) {
            case WEAVING_ATTACK:
            case WEAVING:
            case HAMMER_SMASH:
            case HAMMER_SMASH_CHARGE:
            case BOBBING:
            case BOBBING_ATTACK:
            case REVOLVING_CANNON_RELOAD:
            case REVOLVING_CANNON:
                return true;
        }
        return false;
    }

    public static boolean isSummon(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        if (si == null || skillId == GUIDED_ARROW) {
            return false;
        }
        return si.isSummon();
    }

    public static boolean isAuraSkill(int skillId) {
        switch (skillId) {
            case BattleMage.HASTY_AURA:
            case BattleMage.DRAINING_AURA:
            case BattleMage.BLUE_AURA:
            case BattleMage.DARK_AURA:
            case BattleMage.WEAKENING_AURA:
            case BattleMage.AURA_SCYTHE:
                return true;

            default:
                return false;
        }
    }

    public static boolean isExplodeOnDeathSummon(int skillId) {
        switch (skillId) {
            case Hayato.BATTOUJUTSU_ULTIMATE_WILL:
            case DawnWarrior.RIFT_OF_DAMNATION_SUMMON:
            case NightLord.DARK_LORDS_OMEN:
            case Jett.GRAVITY_CRUSH:
            case SPARKLE_BURST:
            case BeastTamer.CUB_CAVALRY_SUMMON_1:
            case BeastTamer.CUB_CAVALRY_SUMMON_2:
            case BeastTamer.CUB_CAVALRY_SUMMON_3:
            case BeastTamer.CUB_CAVALRY_SUMMON_4:
                return true;
        }
        return false;
    }

    public static boolean isEncode4Reason(int rOption) {
        // NewFlying + NotDamaged are normally ints, but are encoded as ints in this skill
        return rOption == Evan.DRAGON_MASTER;
    }

    public static boolean isSpecialEffectSkill(int skillID) {
        return isExplosionSkill(skillID) || skillID == BattleMage.DARK_SHOCK || skillID == 80002206 ||
                skillID == 80000257 || skillID == 80000260 || skillID == 80002599;
    }

    public static boolean isExplosionSkill(int skillID) {
        return skillID == Xenon.TRIANGULATION
                || skillID == LOVELY_STING_EXPLOSION
                || skillID == SPIRALING_VORTEX_EXPLOSION
                || skillID == DawnWarrior.IMPALING_RAYS_EXPLOSION
                || skillID == BlazeWizard.IGNITION_EXPLOSION;
    }

    public static boolean isCardinalForceSkill(int skillID) {
        return isDelugeSkill(skillID) || isBurstAttackingSkill(skillID) || isTorrentSkill(skillID);
    }

    public static boolean isAncientForceSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.SWARM_SHOT:
            case Pathfinder.TRIPLE_IMPACT:
            case Pathfinder.GLYPH_OF_IMPALEMENT:
            case Pathfinder.NOVA_BLAST:
            case Pathfinder.RAVEN_TEMPEST:
                return true;
            default:
                return false;
        }
    }

    public static boolean isEnchantForceSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.COMBO_ASSAULT_NONE:
            case Pathfinder.COMBO_ASSAULT_DELUGE:
            case Pathfinder.COMBO_ASSAULT_BURST:
            case Pathfinder.COMBO_ASSAULT_TORRENT:

            case Pathfinder.ANCIENT_ASTRA_NONE:
            case Pathfinder.ANCIENT_ASTRA_DELUGE:
            case Pathfinder.ANCIENT_ASTRA_BURST_HOLD:
            case Pathfinder.ANCIENT_ASTRA_BURST:
            case Pathfinder.ANCIENT_ASTRA_TORRENT:

            case Pathfinder.OBSIDIAN_BARRIER_NONE:
            case Pathfinder.OBSIDIAN_BARRIER_TORRENT:
            case Pathfinder.OBSIDIAN_BARRIER_BURST:
            case Pathfinder.OBSIDIAN_BARRIER_DELUGE:
                return true;
            default:
                return false;
        }
    }

    public static boolean isDelugeSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.CARDINAL_DELUGE:
            case Pathfinder.CARDINAL_DELUGE_AMPLIFICATION:
            case Pathfinder.CARDINAL_DELUGE_ADVANCED:
                return true;
            default:
                return false;
        }
    }

    public static boolean isBurstAttackingSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.CARDINAL_BURST:
            case Pathfinder.CARDINAL_BURST_AMPLIFICATION:
            case Pathfinder.CARDINAL_BURST_ADVANCED:
                return true;
            default:
                return false;
        }
    }

    public static boolean isTorrentSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.CARDINAL_TORRENT:
            case Pathfinder.CARDINAL_TORRENT_SWEEP:
            case Pathfinder.CARDINAL_TORRENT_ADVANCED:
            case Pathfinder.CARDINAL_TORRENT_SWEEP_ADVANCED:
                return true;
            default:
                return false;
        }
    }

    public static boolean isSomePathfinderSkill(int skillID) {
        switch (skillID) {
            case Pathfinder.CARDINAL_BURST:
            case 3311011:
            case Pathfinder.CARDINAL_BURST_AMPLIFICATION:
            case Pathfinder.CARDINAL_BURST_ADVANCED:
            case Pathfinder.ANCIENT_ASTRA_BURST:
            case Pathfinder.SWARM_SHOT:
            case 400031035:
                return true;
        }
        return false;
    }

    public static boolean isBurstSkill(int skillID) {
        return skillID == 3301004 || skillID == 3311013 || skillID == 3321005;
    }

    public static int getCorrectCooltimeSkillID(int skillID) {
        switch (skillID) {
            case 33121214:
                return 33121114;
            case Pathfinder.ANCIENT_ASTRA_DELUGE:
            case Pathfinder.ANCIENT_ASTRA_BURST_HOLD:
            case Pathfinder.ANCIENT_ASTRA_BURST:
            case Pathfinder.ANCIENT_ASTRA_TORRENT:
                return Pathfinder.ANCIENT_ASTRA_NONE;
            case Luminous.AETHER_CONDUIT_EQ:
            case Luminous.AETHER_CONDUIT_D:
                return Luminous.AETHER_CONDUIT_L;
            default:
                return skillID;
        }
    }

    public static boolean isAntiRepeatBuff(int skillID) {
        switch (skillID) {
            case 1000003:      // Iron Body
            case 1121000:      // Maple Warrior
            case 1121016:      // Magic Crash
            case 1121053:      // Epic Adventure
            case 1121054:      // Cry Valhalla
            case 1211010:      // HP Recovery
            case 1211013:      // Threaten
            case 1221000:      // Maple Warrior
            case 1221014:      // Magic Crash
            case 1221052:      // Smite Shield
            case 1221053:      // Epic Adventure
            case 1301006:      // Iron Will
            case 1301007:      // Hyper Body
            case 1311015:      // Cross Surge
            case 1321000:      // Maple Warrior
            case 1321014:      // Magic Crash
            case 1321015:      // Sacrifice
            case 1321053:      // Epic Adventure
            case 2101001:      // Meditation
            case 2101010:      // Ignite
            case 2121000:      // Maple Warrior
            case 2121053:      // Epic Adventure
            case 2121054:      // Inferno Aura
            case 2201001:      // Meditation
            case 2221000:      // Maple Warrior
            case 2221053:      // Epic Adventure
            case 2301004:      // Bless
            case 2311001:      // Dispel
            case 2311003:      // Holy Symbol
            case 2321000:      // Maple Warrior
            case 2321005:      // Advanced Blessing
            case 2321053:      // Epic Adventure
            case 3111011:      // Reckless Hunt: Bow
            case 3121000:      // Maple Warrior
            case 3121002:      // Sharp Eyes
            case 3121053:      // Epic Adventure
            case 3211012:      // Reckless Hunt: Crossbow
            case 3221000:      // Maple Warrior
            case 3221053:      // Epic Adventure
            case 3321022:      // Sharp Eyes
            case 3321023:      // Maple Warrior
            case 3321041:      // Epic Adventure
            case 4001003:      // Dark Sight
            case 4001005:      // Haste
            case 4101011:      // Assassin's Mark
            case 4121000:      // Maple Warrior
            case 4121053:      // Epic Adventure
            case 4221000:      // Maple Warrior
            case 4221053:      // Epic Adventure
            case 4301003:      // Self Haste
            case 4341000:      // Maple Warrior
            case 4341053:      // Epic Adventure
            case 5111007:      // Roll of the Dice
            case 5120012:      // Double Down
            case 5121000:      // Maple Warrior
            case 5121009:      // Speed Infusion
            case 5121015:      // Crossbones
            case 5121053:      // Epic Adventure
            case 5121054:      // Stimulating Conversation
            case 5211007:      // Roll of the Dice
            case 5220014:      // Double Down
            case 5221000:      // Maple Warrior
            case 5221018:      // Jolly Roger
            case 5221053:      // Epic Adventure
            case 5221054:      // Whaler's Potion
            case 5301003:      // Monkey Magic
            case 5311004:      // Barrel Roulette
            case 5311005:      // Roll of the Dice
            case 5320007:      // Double Down
            case 5320008:      // Mega Monkey Magic
            case 5321005:      // Maple Warrior
            case 5321053:      // Epic Adventure
            case 5701013:      // Bounty Chaser
            case 5711024:      // Slipstream Suit
            case 5721000:      // Maple Warrior
            case 5721053:      // Rising Cosmos
            case 5721054:      // Bionic Resilience
            case 11001022:      // Soul Element
            case 11101022:      // Falling Moon
            case 11111022:      // Rising Sun
            case 11111023:      // True Sight
            case 11121000:      // Call of Cygnus
            case 11121005:      // Equinox Cycle
            case 11121053:      // Glory of the Guardians
            case 11121054:      // Soul Forge
            case 12101000:      // Meditation
            case 12101001:      // Slow
            case 12121000:      // Call of Cygnus
            case 12121053:      // Glory of the Guardians
            case 13001022:      // Storm Elemental
            case 13101024:      // Sylvan Aid
            case 13121000:      // Call of Cygnus
            case 13121005:      // Sharp Eyes
            case 13121053:      // Glory of the Guardians
            case 14001003:      // Dark Sight
            case 14001007:      // Haste
            case 14001022:      // Haste
            case 14001023:      // Dark Sight
            case 14001027:      // Shadow Bat
            case 14121000:      // Call of Cygnus
            case 14121053:      // Glory of the Guardians
            case 15001022:      // Lightning Elemental
            case 15121000:      // Call of Cygnus
            case 15121005:      // Speed Infusion
            case 15121053:      // Glory of the Guardians
            case 20031209:      // Judgment Draw
            case 20031210:      // Judgment Draw
            case 21111012:      // Maha Blessing
            case 21121000:      // Maple Warrior
            case 21121053:      // Heroic Memories
            case 21121054:      // Unlimited Combo
            case 22171068:      // Maple Warrior
            case 22171073:      // Blessing of the Onyx
            case 22171082:      // Heroic Memories
            case 23121004:      // Ancient Warding
            case 23121005:      // Maple Warrior
            case 23121053:      // Heroic Memories
            case 23121054:      // Elvish Blessing
            case 24111003:      // Bad Luck Ward
            case 24121004:      // Priere D'Aria
            case 24121007:      // Vol D'Ame
            case 24121008:      // Maple Warrior
            case 24121053:      // Heroic Memories
            case 25101009:      // Fox Spirits
            case 25121108:      // Maple Warrior
            case 25121132:      // Heroic Memories
            case 27111005:      // Dusk Guard
            case 27111006:      // Photic Meditation
            case 27121006:      // Arcane Pitch
            case 27121009:      // Maple Warrior
            case 27121053:      // Heroic Memories
            case 27121054:      // Equalize
            case 31011001:      // Overload Release
            case 31121003:      // Demon Cry
            case 31121004:      // Maple Warrior
            case 31121005:      // Dark Metamorphosis
            case 31121053:      // Demonic Fortitude
            case 31211003:      // Ward Evil
            case 31211004:      // Diabolic Recovery
            case 31221001:      // Nether Shield
            case 31221008:      // Maple Warrior
            case 31221053:      // Demonic Fortitude
            case 31221054:      // Forbidden Contract
            case 32121007:      // Maple Warrior
            case 32121053:      // For Liberty
            case 33121007:      // Maple Warrior
            case 33121053:      // For Liberty
            case 35111013:      // Roll of the Dice
            case 35120014:      // Double Down
            case 35121053:      // For Liberty
            case 36111004:      // Aegis System
            case 36121008:      // Maple Warrior
            case 36121053:      // Entangling Lash
            case 36121054:      // Amaranth Generator
            case 37121053:      // For Liberty
            case 41001010:      // Battoujutsu Advance
            case 41101003:      // Military Might
            case 41121005:      // Akatsuki Warrior
            case 41121053:      // Princess's Vow
            case 41121054:      // God of Blades
            case 42121006:      // Akatsuki Warrior
            case 42121053:      // Princess's Vow
            case 42121054:      // Circle of Suppression
            case 51101004:      // Rally
            case 51111004:      // Enduring Spirit
            case 51111005:      // Magic Crash
            case 51121005:      // Call of Cygnus
            case 51121053:      // Queen of Tomorrow
            case 51121054:      // Sacred Cube
            case 61101002:      // Tempest Blades
            case 61110211:      // Tempest Blades
            case 61110212:      // Dragon Slash
            case 61111008:      // Final Form
            case 61120007:      // Advanced Tempest Blades
            case 61121014:      // Nova Warrior
            case 61121217:      // Advanced Tempest Blades
            case 64121004:      // Nova Warrior
            case 64121011:      // Summon Spiked Bat
            case 64121054:      // Shadowdealer's Elixir
            case 65111100:      // Soul Seeker
            case 65121004:      // Star Gazer
            case 65121009:      // Nova Warrior
            case 65121011:      // Soul Seeker Expert
            case 65121053:      // Final Contract
            case 65121054:      // Pretty Exaltation
            case 80000365:      // Battoujutsu Soul
            case 80001816:      // Military Might
            case 80011032:      // Military Might
            case 100001268:      // Rhinne's Protection
            case 100001271:      // Time Dispel
            case 110001511:      // Maple Guardian
            case 112121006:      // Meow Card
            case 112121010:      // Meow Cure
            case 112121056:      // Team Roar
            case 131001018:      // Pink Warrior
            case 142121016:      // President's Orders
            case 400001020:      // Decent Holy Symbol
            case 400011010:      // Demonic Frenzy
            case 400011066:      // Impenetrable Skin
            case 400011102:      // Dimensional Sword
            case 400021024:      // Champ Charge
            case 400021035:      // Champ Charge
            case 400031002:      // Storm of Arrows
            case 400041008:      // Shadow Spear
            case 400051001:      // Roll of the Dice
            case 400051015:      // Lord of the Deep
            case 400051033:      // Overdrive
                return true;
            default:
                return false;
        }
    }

    // unknown func name
    // v205-1 idb -> sub_BC1DE0
    public static boolean someUpgradeSkillCheck(int skillID) {
        switch (skillID) {
            case 3011004:      // Cardinal Deluge
            case 3300002:      // Cardinal Deluge Amplification
            case 3301003:      // Cardinal Burst
            case 3310001:      // Cardinal Burst Amplification
            case 3321003:      // Cardinal Deluge
            case 3321004:      // Cardinal Burst
            case 152001001:      // Radiant Javelin
            case 152120001:      // Radiant Javelin II
                return true;
            default:
                return false;
        }
    }

    public static boolean isNoProjectileConsumptionSkill(int skillID) {
        switch (skillID) {
            case 4111009:       // Shadow Stars
            case 14111007:      // Shadow Stars
            case 5201008:       // Infinity Blast
            case 14111025:      // Spirit Projection
                return true;
            default:
                return false;
        }
    }

    public static boolean isSpiritWalkerSkill(int skillID) {
        switch (skillID) {
            case SHIKIGAMI_HAUNTING_1_3:
            case SHIKIGAMI_HAUNTING_2_3:
            case SHIKIGAMI_HAUNTING_3_3:
            case SHIKIGAMI_HAUNTING_4_3:
            case SHIKIGAMI_CHARM:
            case EXORCIST_CHARM:
            case FALLING_SAKURA:
                return true;

            default:
                return false;
        }
    }

    public static void putMissingBaseStatsBySkill(Map<BaseStat, Integer> stats, SkillInfo si, int slv) {
        int skillId = si.getSkillId();
        // pad/mad not in wz
        if (SkillConstants.isBlessingSkill(skillId)) {
            stats.put(BaseStat.pad, si.getValue(SkillStat.x, slv));
            stats.put(BaseStat.mad, si.getValue(SkillStat.x, slv));
        }
        switch (skillId) {
            // mostly old skills that have no info in wz
            case 12120009: // Pure Magic
            case 12100027: // Spell Control
            case 22110018: // Spell Mastery
            case 22170071: // Magic Mastery
            case 27120007: // Magic Mastery
            case 32120016: // Staff Expert
            case 32100006: // Staff Mastery
            case BeastTamer.BEAST_SCEPTER_MASTERY:
                stats.put(BaseStat.mad, si.getValue(SkillStat.x, slv));
                break;
            case 13120006: // Bow Expert
            case 3120005: // Bow Expert
            case 3220004: // Crossbow Expert
            case 33120000: // Crossbow Expert
            case 14120005: // Throwing Expert
            case 4120012: // Claw Expert
            case 4220012: // Dagger Expert
            case 4340013: // Katara Expert
            case 11120007: // Student of the Blade
            case 15120006: // Knuckle Expert
            case 21120001: // High Mastery
            case 23120009: // Dual Bowgun Expert
            case 3320010: // Ancient Bow Expertise
                stats.put(BaseStat.pad, si.getValue(SkillStat.x, slv));
                break;
            case 3000001: // Critical Shot
            case 14100001: // Critical Throw
            case 14100024: // Critical Throw
            case 4100001: // Critical Throw
            case 22140018: // Critical Magic
                stats.put(BaseStat.cr, si.getValue(SkillStat.prop, slv));
                break;
            case 14110027: // Alchemic Adrenaline
            case 4110014:
                stats.put(BaseStat.recoveryUp, si.getValue(SkillStat.x, slv));
                break;
            case 13110025: // Featherweight
            case 40020000: // Blessing of the Five Elements
                stats.put(BaseStat.dmgReduce, si.getValue(SkillStat.x, slv));
                break;
            case 21120004: // High Defense
                stats.put(BaseStat.dmgReduce, si.getValue(SkillStat.t, slv));
                break;
            case 80000066: // Cygnus Blessing (Warrior)
            case 80000067: // Cygnus Blessing (Magician)
            case 80000068: // Cygnus Blessing (Bowman)
            case 80000069: // Cygnus Blessing (Thief)
            case 80000070: // Cygnus Blessing (Pirate)
                stats.put(BaseStat.asr, si.getValue(SkillStat.x, slv));
                stats.put(BaseStat.ter, si.getValue(SkillStat.y, slv));
                break;
            case FirePoison.ELEMENTAL_ADAPTATION_FP:
            case IceLightning.ELEMENTAL_ADAPTATION_IL:
            case Bishop.DIVINE_PROTECTION:
                stats.put(BaseStat.ter, si.getValue(SkillStat.asrR, slv));
                break;
            case 61000003: // Scale Skin
            case 112000010: // Dumb Luck
                stats.put(BaseStat.stance, si.getValue(SkillStat.prop, slv));
                break;
            case 112000012: // Defense Ignorance
                stats.put(BaseStat.booster, si.getValue(SkillStat.q, slv));
                break;
            case BowMaster.ILLUSION_STEP_BOW:
            case Marksman.ILLUSION_STEP_XBOW:
                stats.remove(BaseStat.dex);
                break;
            case 3320011: // Illusion Step
                stats.put(BaseStat.evaR, si.getValue(SkillStat.x, slv));
                break;
            case Pathfinder.ANCIENT_CURSE:
                stats.clear();
                switch (si.getCurrentLevel()) {
                    case 1:
                        stats.put(BaseStat.cr, -20);
                        // Fall through intended
                    case 2:
                        stats.put(BaseStat.pddR, -20);
                        break;
                    case 3:
                        stats.put(BaseStat.asr, -20);
                        break;
                }
                break;
            case Noblesse.ELEMENTAL_EXPERT:
            case 50000250: // Elemental Expert
                stats.put(BaseStat.madR, si.getValue(SkillStat.padR, slv));
                break;
            case DawnWarrior.WILL_OF_STEEL:
                stats.put(BaseStat.dmgReduce, si.getValue(SkillStat.x, slv));
                break;
            case 12000024: // Fire Repulsion
            case 27000003: // Standard Magic Guard
                stats.put(BaseStat.magicGuard, si.getValue(SkillStat.x, slv));
                break;
            case 12110025: // Liberated magic
                stats.put(BaseStat.fd, si.getValue(SkillStat.z, slv));
                stats.put(BaseStat.costmpR, si.getValue(SkillStat.x, slv));
                break;
            case WindArcher.SECOND_WIND:
                stats.remove(BaseStat.pad); // part of the buff
                break;
            case WindArcher.TOUCH_OF_THE_WIND:
            case Mercedes.IGNIS_ROAR:
                stats.put(BaseStat.evaR, si.getValue(SkillStat.prop, slv));
                break;
            case Evan.PARTNERS:
                stats.remove(BaseStat.damR); // part of the buff
                break;
            case DarkKnight.SACRIFICE:
                stats.remove(BaseStat.bd); // part of the buff
                break;
            case NightWalker.HASTE:
            case BladeMaster.SELF_HASTE:
            case Thief.HASTE:
            case BeastTamer.HAWK_FLOCK:
                stats.remove(BaseStat.speed); // part of the buff
                stats.remove(BaseStat.jump); // part of the buff
                break;
            case 15110009: // Precision Strikes
            case 5110011: // Precision Strikes
                stats.put(BaseStat.addCrOnBoss, si.getValue(SkillStat.prop, slv));
                break;
            case TRUE_HEART_INHERITANCE:
                stats.put(BaseStat.mastery, 10); // gj nexon, please hardcode more.
                break;
            case 5210012: // Outlaw's Code
                stats.put(BaseStat.mhp, si.getValue(SkillStat.x, slv));
                stats.put(BaseStat.mmp, si.getValue(SkillStat.x, slv));
                break;
            case Corsair.PIRATE_REVENGE_SAIR:
            case Buccaneer.STIMULATING_CONVERSATION:
                stats.remove(BaseStat.damR); // part of the buff
                break;
            case Buccaneer.CROSSBONES:
                stats.remove(BaseStat.padR);
                break;
            case Mihile.RALLY:
                stats.clear();
                stats.put(BaseStat.pad, si.getValue(SkillStat.padX, slv));
                break;
            case Jett.BOUNTY_CHASER:
                stats.remove(BaseStat.cr); // part of the buff
                stats.remove(BaseStat.damR); // part of the buff
                break;
            case Jett.HIGH_GRAVITY:
                stats.remove(BaseStat.allStat); // part of the buff
                stats.remove(BaseStat.cr); // part of the buff
                break;
            case MELODY_CROSS:
                stats.remove(BaseStat.booster); // part of the buff
                break;
            case BattleMage.HASTY_AURA:
                stats.clear();
                stats.put(BaseStat.booster, si.getValue(SkillStat.actionSpeed, slv));
                stats.put(BaseStat.speed, si.getValue(SkillStat.psdSpeed, slv));
                stats.put(BaseStat.jump, si.getValue(SkillStat.psdJump, slv));
                stats.put(BaseStat.evaR, si.getValue(SkillStat.er, slv));
                break;
            case BattleMage.BLUE_AURA:
                stats.clear();
                stats.put(BaseStat.asr, si.getValue(SkillStat.asrR, slv));
                stats.put(BaseStat.ter, si.getValue(SkillStat.terR, slv));
                stats.put(BaseStat.pddR, si.getValue(SkillStat.pddR, slv));
                stats.put(BaseStat.dmgReduce, si.getValue(SkillStat.ignoreMobDamR, slv));
                break;
            case BattleMage.DARK_AURA:
                stats.remove(BaseStat.damR); // part of the buff
                break;
            case Mechanic.SUPPORT_UNIT_HEX:
                stats.remove(BaseStat.hpRecovery);
                break;
            case 36101003: // Efficiency Streamline
                stats.remove(BaseStat.mhpR);
                break;
            case Illium.LUCENT_BRAND:
            case 152110011: // Might of the Flora
                stats.put(BaseStat.mhpR, si.getValue(SkillStat.mmpR, slv));
                break;
            case 80011558: // Konyanyachiwa
                stats.put(BaseStat.mhp, si.getValue(SkillStat.mhpX, slv));
                break;
            case 20000297: // Combo Kill Blessing
                stats.put(BaseStat.comboKillOrbExpR, si.getValue(SkillStat.x, slv));
                break;
            case BladeMaster.SHADOW_MELD:
                stats.remove(BaseStat.pad);
                break;
            case 80011545: // Lunar New Year Knight
            case 80011570: // MapleStory X Cardcaptor Sakura
                stats.remove(BaseStat.inte);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.intX, slv));
                stats.put(BaseStat.mmp, si.getValue(SkillStat.mhpX, slv));
                break;
            case 80010108: // Exclusive V Title
            case 80002525: // Alliance basics I
            case 80002526: // Alliance basics II
            case 80002527: // Alliance basics III
            case 80000109: // King of Neckbraces
            case 80000003: // Keen Edge
            case 40010001: // Keen Edge
            case 20010194: // Inherited Will
            case 33120013: // Extended Magazine
            case 36100005: // Structural Integrity
            case Xenon.HYBRID_DEFENSES:
            case 70000015: // All Stats Increase
            case ROPE_LIFT:
            case 400001007: // Blink
            case DECENT_MYSTIC_DOOR_V:
            case DECENT_SHARP_EYES_V:
            case DECENT_HYPER_BODY_V:
            case DECENT_SPEED_INFUSION_V:
            case 11120006: // Soul Pledge
                stats.remove(BaseStat.str);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.strX, slv));
                break;
            case 70000044: // All Stat Increase
                stats.remove(BaseStat.str);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.strFX, slv));
                break;
            case 80000047: // Hybrid Logic
            case 30020233: // Hybrid Logic
                stats.remove(BaseStat.str);
                stats.put(BaseStat.allStatR, si.getValue(SkillStat.strR, slv));
                break;
            case 80011224: // [Beefy's Kitchen] Maker's Buff
            case 80000080: // Sweet White Day
            case 80000085: // Maple Windrider
            case 80000051: // 2013 Summer Heat
            case 80000046: // Gauntlet Hero
            case 80000043: // Miwok Cheers
            case 80000032: // Post Season Supporter
                stats.remove(BaseStat.dex);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.dexX, slv));
                break;
            case 80002532: // Alliance's Strength: Attack Power/Magic ATT
            case 80000565: // Challenge: Attack Power & Magic ATT
            case 80000419: // Attack Power & Magic ATT
            case 71009002: // Lab Server Legion Block
            case 71009003: // Enhanced Lab Server Legion Block
                stats.put(BaseStat.mad, si.getValue(SkillStat.padX, slv));
                break;
            case 80000329: // Spirit of Freedom
                stats.put(BaseStat.invincibleAfterRevive, si.getValue(SkillStat.x, slv));
                break;
            case 80000333: // Spirit of Freedom (Magician)
            case 80000334: // Spirit of Freedom (Bowman)
            case 80000335: // Spirit of Freedom (Pirate)
            case 80000378: // Spirit of Freedom (Warrior)
                stats.put(BaseStat.invincibleAfterRevive, si.getValue(SkillStat.u, slv));
                break;
            case 80000582: // Adventurer Stats
                stats.remove(BaseStat.str);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.strX, slv));
                stats.put(BaseStat.mad, si.getValue(SkillStat.padX, slv));
                break;
            case 80000133: // Apprentice Hunter
            case 80000134: // Capable Hunter
            case 80000135: // Veteran Hunter
            case 80000136: // Superior Hunter
                stats.remove(BaseStat.str);
                stats.put(BaseStat.allStat, si.getValue(SkillStat.strX, slv));
                stats.put(BaseStat.mmp, si.getValue(SkillStat.mhpX, slv));
                break;
            case 80002407: // Summon Parrot
            case 80002408: // Summon Parrot
            case 80002409: // Summon Parrot
            case 80002410: // Summon Parrot
            case 80002411: // Summon Parrot
            case 80002412: // Summon Parrot
            case 80002413: // Summon Parrot
            case 80002414: // Summon Parrot
            case 80002415: // Summon Parrot
            case 80002416: // Summon Parrot
                stats.put(BaseStat.pad, si.getValue(SkillStat.y, slv));
                stats.put(BaseStat.mad, si.getValue(SkillStat.y, slv));
                stats.put(BaseStat.nbd, si.getValue(SkillStat.y, slv));
                stats.put(BaseStat.ied, si.getValue(SkillStat.y, slv));
                stats.put(BaseStat.bd, si.getValue(SkillStat.y, slv));
                stats.put(BaseStat.cr, si.getValue(SkillStat.y, slv));
                break;
            case 80000251: // Rune Love power!
                stats.put(BaseStat.runeBuffTimerR, 100); // great..
                break;
            case 80000370: // Combo kill Blessing
                stats.put(BaseStat.comboKillOrbExpR, si.getValue(SkillStat.x, slv));
                break;
            case REALIGN_ATTACKER_MODE:
            case REALIGN_ATTACKER_MODE_I:
            case REALIGN_ATTACKER_MODE_II:
            case REALIGN_ATTACKER_MODE_III:
            case REALIGN_DEFENDER_MODE:
            case REALIGN_DEFENDER_MODE_I:
            case REALIGN_DEFENDER_MODE_II:
            case REALIGN_DEFENDER_MODE_III:
            case Kinesis.MENTAL_TEMPEST:
            case Xenon.SUPPLY_SURPLUS:
            case 22171073: // Blessing of the Onyx
            case 5921007: // Battleship Cannon
            case 5821004: // Demolition
            case 155120014: // Battle Frenzy
            case 21110000: // Advanced Combo Ability
            case WildHunter.SILENT_RAMPAGE:
            case NightLord.EXPERT_THROWING_STAR_HANDLING:
            case 50001214: // Knight's Watch
            case 42101002: // Haku Reborn
                stats.clear();
                break;



            // TODO Haku Reborn(42101002)
            // TODO Blessing of the Five Elements(40020000)
            // TODO Deadly Fangs(112100010)     insta-kill chance
            // TODO Weapon Mastery(64100005)    more complex passive
            // TODO Critical Insight(25120214)  more complex passive
            // TODO  Magic Conversation (150000079  150010079)
            // TODO  Shield Mastery(1210001) passives if holding shield
            case DarkKnight.FINAL_PACT_INFO: // TODO  more complex passive
            case 142120011: // TODO  Critical Rush(142120011)
            case 33110014: // Jaguar Link   // TODO
            case 112120014: // Purr-Powered // TODO
                stats.clear();
                break;
        }
    }
}
