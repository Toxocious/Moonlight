package net.swordie.ms.constants;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Itzik
 */
public class JobConstants {

    public static final boolean enableJobs = true;
    public static final int jobOrder = 3;

    public static JobEnum getJobEnumById(short jobId) {
        return Arrays.stream(JobEnum.values()).filter(job -> job.getJobId() == jobId)
                .findFirst().orElse(null);
    }

    public static boolean isXenon(short jobId) {
        return jobId / 100 == 36 || jobId == 3002;
    }

    public static boolean isBeastTamer(short job) {
        return job == 11000 || (job >= 11200 && job <= 11212);
    }

    public static boolean isPinkBean(short job) {
        return job == JobEnum.PINK_BEAN_0.getJobId() || job == JobEnum.PINK_BEAN_1.getJobId();
    }

    public static boolean isWildHunter(short job) {
        return job / 100 == 33;
    }

    public static boolean isAngelicBuster(int id) {
        return id == JobConstants.JobEnum.ANGELIC_BUSTER.getJobId() || id / 100 == 65;
    }

    public static boolean isBlazeWizard(short job) {
        return job / 100 == 12;
    }

    public static boolean isDawnWarrior(short job) {
        return job / 100 == 11;
    }

    public static boolean isShadower(short job) {
        return job / 10 == 42;
    }

    public static boolean isNightLord(short job) {
        return job / 10 == 41;
    }

    public static boolean isDualBlade(short job) {
        return job / 10 == 43;
    }

    public static boolean isHero(short job) {
        return job / 10 == 11;
    }

    public static boolean isPaladin(short job) {
        return job / 10 == 12;
    }

    public static boolean isDarkKnight(short job) {
        return job / 10 == 13;
    }

    public static boolean isFirePoison(short job) {
        return job / 10 == 21;
    }

    public static boolean isIceLightning(short job) {
        return job / 10 == 22;
    }

    public static boolean isBishop(short job) {
        return job / 10 == 23;
    }

    public static boolean isBowMaster(short job) {
        return job / 10 == 31;
    }

    public static boolean isMarksman(short job) {
        return job / 10 == 32;
    }

    public static boolean isBuccaneer(short job) {
        return job / 10 == 51;
    }

    public static boolean isCorsair(short job) {
        return job / 10 == 52;
    }

    public static boolean isJett(short job) {
        return job / 10 == 57 || job == JobEnum.JETT_1.getJobId();
    }

    public static boolean isDemonSlayer(short job) {
        return job / 10 == 311 || job == 3100;
    }

    public static boolean isDemonAvenger(short job) {
        return job / 10 == 312 || job == 3101;
    }

    public static boolean isKanna(short id) {
        return id == JobConstants.JobEnum.KANNA.getJobId() || id / 100 == 42;
    }

    public static boolean isHayato(short id) {
        return id == JobConstants.JobEnum.HAYATO.getJobId() || id / 100 == 41;
    }

    public static boolean isNightWalker(short id) {
        return id / 100 == 14;
    }

    public static boolean isThunderBreaker(short id) {
        return id / 100 == 15;
    }

    public static boolean isBlaster(short id) {
        return id / 100 == 37;
    }

    public static boolean isShade(short id) {
        return id == JobEnum.SHADE.getJobId() || id / 100 == 25;
    }

    public static boolean isWindArcher(short id) {
        return id / 100 == 13;
    }

    public static boolean isMihile(short id) {
        return id / 100 == 51 || id == 5000;
    }

    public static boolean isPathFinder(short id) {
        return id == JobEnum.PATHFINDER_1.getJobId()
                || id == JobEnum.PATHFINDER_2.getJobId()
                || id == JobEnum.PATHFINDER_3.getJobId()
                || id == JobEnum.PATHFINDER_4.getJobId();
    }

    public static double getDamageConstant(short job) {
        // get_job_damage_const 
        if (job > 222) {
            if (job > 1200) {
                if (job >= 1210 && job <= 1212)
                    return 0.2;
            } else if (job == 1200 || job >= 230 && job <= 232) {
                return 0.2;
            }
            return 0.0;
        }
        if (job < 220) {
            switch (job) {
                case 110:
                case 111:
                case 112:
                    return 0.1;
                case 200:
                case 210:
                case 211:
                case 212:
                    return 0.2;
                default:
                    return 0.0;
            }
        }
        return 0.2;
    }

    public static boolean isHeroJob(short jobID) {
        return isAran(jobID) || isEvan(jobID) || isLuminous(jobID) || isMercedes(jobID) || isPhantom(jobID) || isShade(jobID);
    }


    public static int getJobLevelDetail(short jobId) {
        int prefix;
        if (isBeginnerJob(jobId) || (jobId % 100 == 0) || jobId == 301 || jobId == 501 || jobId == 508 || jobId == 3101) {
            return 1;
        }
        if (isEvan(jobId)) {
            return getEvanJobLevel(jobId);
        }

        if (isDualBlade(jobId)) {
            prefix = (jobId % 10) + 2;
            if (prefix < 2) {
                return 0;
            }
            if (prefix <= 6) {
                return jobId % 10 + 2;
            }
        }

        //Jett needs its own reference since it doesnt follow the usual job id curve
        if (jobId == 570) {
            return 2;
        }
        if (jobId == 571) {
            return 3;
        }
        if (jobId == 572) {
            return 4;
        }

        return (jobId % 10) <= 2 ? (jobId % 10) + 2 : 0;
    }


    public static int getJobCategory(short job) {
        int res = 0;
        switch (job / 100) {
            case 27:
            case 140:
            case 142:
                res = 2;
                break;
            case 36:
                res = 4;
                break;
            case 37:
                res = 1;
                break;
            default:
                res = job % 1000 / 100;
        }
        return res;
    }

    public static byte getJobLevelByZeroSkillID(int skillID) {
        int prefix = (skillID % 1000) / 100;
        return (byte) (prefix == 1 ? 2
                : prefix == 2 ? 1
                : 3);
    }

    public static boolean isMechanic(short id) {
        return id >= JobConstants.JobEnum.MECHANIC_1.getJobId() && id <= JobConstants.JobEnum.MECHANIC_4.getJobId();
    }

    public static boolean isBattleMage(short id) {
        return id >= JobConstants.JobEnum.BATTLE_MAGE_1.getJobId() && id <= JobConstants.JobEnum.BATTLE_MAGE_4.getJobId();
    }

    public static boolean isGmJob(short id) {
        return isGm(id) || isSuperGm(id);
    }

    public static boolean isGm(short id) {
        return id == JobEnum.GM.getJobId();
    }

    public static boolean isManager(short id) {
        return id == JobEnum.MANAGER.getJobId();
    }

    public static boolean isSuperGm(short id) {
        return id == JobEnum.SUPER_GM.getJobId();
    }

    public static boolean isExplorer(short job) {
        return job >= 0 && job < 600; // good enough to catch them all
    }


    public enum JobEnum {
        BEGINNER(0, 0, "Beginner"),
        WARRIOR(100, 0, "Warrior"),
        FIGHTER(110, 0, "Fighter"),
        CRUSADER(111, 0, "Crusader"),
        HERO(112, 0, "Hero"),
        PAGE(120, 0, "Page"),
        WHITE_KNIGHT(121, 0, "White Knight"),
        PALADIN(122, 0, "Paladin"),
        SPEARMAN(130, 0, "Spearman"),
        DRAGON_KNIGHT(131, 0, "Dragon Knight"),
        DARK_KNIGHT(132, 0, "Dark Knight"),
        MAGICIAN(200, 0, "Magician"),
        FP_WIZARD(210, 0, "Wizard (Fire, Poison)"),
        FP_MAGE(211, 0, "Mage (Fire, Poison)"),
        FP_ARCHMAGE(212, 0, "Arch Mage (Fire, Poison)"),
        IL_WIZARD(220, 0, "Wizard (Ice, Lightning)"),
        IL_MAGE(221, 0, "Mage (Ice, Lightning)"),
        IL_ARCHMAGE(222, 0, "Arch Mage (Ice, Lightning)"),
        CLERIC(230, 0, "Cleric"),
        PRIEST(231, 0, "Priest"),
        BISHOP(232, 0, "Bishop"),
        BOWMAN(300, 0, "Archer"),
        HUNTER(310, 0, "Hunter"),
        RANGER(311, 0, "Ranger"),
        BOWMASTER(312, 0, "Bowmaster"),
        CROSSBOWMAN(320, 0, "Crossbowman"),
        SNIPER(321, 0, "Sniper"),
        MARKSMAN(322, 0, "Marksman"),
        PATHFINDER_1(301, 0, "Pathfinder"),
        PATHFINDER_2(330, 0, "Pathfinder"),
        PATHFINDER_3(331, 0, "Pathfinder"),
        PATHFINDER_4(332, 0, "Pathfinder"),
        THIEF(400, 0, "Thief"),
        ASSASSIN(410, 0, "Assassin"),
        HERMIT(411, 0, "Hermit"),
        NIGHT_LORD(412, 0, "Night Lord"),
        BANDIT(420, 0, "Bandit"),
        CHIEF_BANDIT(421, 0, "Chief Bandit"),
        SHADOWER(422, 0, "Shadower"),
        BLADE_RECRUIT(430, 0, "Blade Recruit"),
        BLADE_ACOLYTE(431, 0, "Blade Acolyte"),
        BLADE_SPECIALIST(432, 0, "Blade Specialist"),
        BLADE_LORD(433, 0, "Blade Lord"),
        BLADE_MASTER(434, 0, "Blade Master"),
        PIRATE(500, 0, "Pirate"),
        PIRATE_CANNONEER(501, 0, "Pirate"),
        JETT_1(508, 0, "Jett"),
        BRAWLER(510, 0, "Brawler"),
        MARAUDER(511, 0, "Marauder"),
        BUCCANEER(512, 0, "Buccaneer"),
        GUNSLINGER(520, 0, "Gunslinger"),
        OUTLAW(521, 0, "Outlaw"),
        CORSAIR(522, 0, "Corsair"),
        CANNONEER(530, 0, "Cannoneer"),
        CANNON_BLASTER(531, 0, "Cannon Trooper"),
        CANNON_MASTER(532, 0, "Cannon Master"),
        JETT_2(570, 0, "Jett"),
        JETT_3(571, 0, "Jett"),
        JETT_4(572, 0, "Jett"),
        MANAGER(800, 0, "Manager"),
        GM(900, 0, "GM"),
        SUPER_GM(910, 0, "SuperGM"),
        NOBLESSE(1000, 1000, "Noblesse"),
        DAWN_WARRIOR_1(1100, 1000, "Dawn Warrior"),
        DAWN_WARRIOR_2(1110, 1000, "Dawn Warrior"),
        DAWN_WARRIOR_3(1111, 1000, "Dawn Warrior"),
        DAWN_WARRIOR_4(1112, 1000, "Dawn Warrior"),
        BLAZE_WIZARD_1(1200, 1000, "Blaze Wizard"),
        BLAZE_WIZARD_2(1210, 1000, "Blaze Wizard"),
        BLAZE_WIZARD_3(1211, 1000, "Blaze Wizard"),
        BLAZE_WIZARD_4(1212, 1000, "Blaze Wizard"),
        WIND_ARCHER_1(1300, 1000, "Wind Archer"),
        WIND_ARCHER_2(1310, 1000, "Wind Archer"),
        WIND_ARCHER_3(1311, 1000, "Wind Archer"),
        WIND_ARCHER_4(1312, 1000, "Wind Archer"),
        NIGHT_WALKER_1(1400, 1000, "Night Walker"),
        NIGHT_WALKER_2(1410, 1000, "Night Walker"),
        NIGHT_WALKER_3(1411, 1000, "Night Walker"),
        NIGHT_WALKER_4(1412, 1000, "Night Walker"),
        THUNDER_BREAKER_1(1500, 1000, "Thunder Breaker"),
        THUNDER_BREAKER_2(1510, 1000, "Thunder Breaker"),
        THUNDER_BREAKER_3(1511, 1000, "Thunder Breaker"),
        THUNDER_BREAKER_4(1512, 1000, "Thunder Breaker"),
        LEGEND(2000, 2000, "Legend"),
        EVAN_NOOB(2001, 2001, "Evan"),
        ARAN_1(2100, 2000, "Aran"),
        ARAN_2(2110, 2000, "Aran"),
        ARAN_3(2111, 2000, "Aran"),
        ARAN_4(2112, 2000, "Aran"),
        EVAN_1(2210, 2001, "Evan"),
        EVAN_2(2212, 2001, "Evan"),
        EVAN_3(2214, 2001, "Evan"),
        EVAN_4(2217, 2001, "Evan"),
        MERCEDES(2002, 2002, "Mercedes"),
        MERCEDES_1(2300, 2002, "Mercedes"),
        MERCEDES_2(2310, 2002, "Mercedes"),
        MERCEDES_3(2311, 2002, "Mercedes"),
        MERCEDES_4(2312, 2002, "Mercedes"),
        PHANTOM(2003, 2003, "Phantom"),
        PHANTOM_1(2400, 2003, "Phantom"),
        PHANTOM_2(2410, 2003, "Phantom"),
        PHANTOM_3(2411, 2003, "Phantom"),
        PHANTOM_4(2412, 2003, "Phantom"),
        SHADE(2005, 2005, "???"),
        SHADE_1(2500, 2005, "Shade"),
        SHADE_2(2510, 2005, "Shade"),
        SHADE_3(2511, 2005, "Shade"),
        SHADE_4(2512, 2005, "Shade"),
        LUMINOUS(2004, 2004, "Luminous"),
        LUMINOUS_1(2700, 2004, "Luminous"),
        LUMINOUS_2(2710, 2004, "Luminous"),
        LUMINOUS_3(2711, 2004, "Luminous"),
        LUMINOUS_4(2712, 2004, "Luminous"),
        CITIZEN(3000, 3000, "Citizen"),
        DEMON(3001, 3001, "Demon Slayer"),
        XENON(3002, 3002, "Xenon"),
        DEMON_SLAYER_1(3100, 3001, "Demon Slayer"),
        DEMON_SLAYER_2(3110, 3001, "Demon Slayer"),
        DEMON_SLAYER_3(3111, 3001, "Demon Slayer"),
        DEMON_SLAYER_4(3112, 3001, "Demon Slayer"),
        DEMON_AVENGER_1(3101, 3001, "Demon Avenger"),
        DEMON_AVENGER_2(3120, 3001, "Demon Avenger"),
        DEMON_AVENGER_3(3121, 3001, "Demon Avenger"),
        DEMON_AVENGER_4(3122, 3001, "Demon Avenger"),
        BATTLE_MAGE_1(3200, 3000, "Battle Mage"),
        BATTLE_MAGE_2(3210, 3000, "Battle Mage"),
        BATTLE_MAGE_3(3211, 3000, "Battle Mage"),
        BATTLE_MAGE_4(3212, 3000, "Battle Mage"),
        WILD_HUNTER_1(3300, 3000, "Wild Hunter"),
        WILD_HUNTER_2(3310, 3000, "Wild Hunter"),
        WILD_HUNTER_3(3311, 3000, "Wild Hunter"),
        WILD_HUNTER_4(3312, 3000, "Wild Hunter"),
        MECHANIC_1(3500, 3000, "Mechanic"),
        MECHANIC_2(3510, 3000, "Mechanic"),
        MECHANIC_3(3511, 3000, "Mechanic"),
        MECHANIC_4(3512, 3000, "Mechanic"),
        BLASTER_1(3700, 3000, "Blaster"),
        BLASTER_2(3710, 3000, "Blaster"),
        BLASTER_3(3711, 3000, "Blaster"),
        BLASTER_4(3712, 3000, "Blaster"),
        XENON_1(3600, 3002, "Xenon"),
        XENON_2(3610, 3002, "Xenon"),
        XENON_3(3611, 3002, "Xenon"),
        XENON_4(3612, 3002, "Xenon"),
        HAYATO(4001, 4001, "Hayato"),
        KANNA(4002, 4002, "Kanna"),
        HAYATO_1(4100, 4001, "Hayato"),
        HAYATO_2(4110, 4001, "Hayato"),
        HAYATO_3(4111, 4001, "Hayato"),
        HAYATO_4(4112, 4001, "Hayato"),
        KANNA_1(4200, 4002, "Kanna"),
        KANNA_2(4210, 4002, "Kanna"),
        KANNA_3(4211, 4002, "Kanna"),
        KANNA_4(4212, 4002, "Kanna"),
        NAMELESS_WARDEN(5000, 5000, "Mihile"),
        MIHILE_1(5100, 5000, "Mihile"),
        MIHILE_2(5110, 5000, "Mihile"),
        MIHILE_3(5111, 5000, "Mihile"),
        MIHILE_4(5112, 5000, "Mihile"),
        KAISER(6000, 6000, "Kaiser"),
        ANGELIC_BUSTER(6001, 6001, "Angelic Buster"),
        KAISER_1(6100, 6000, "Kaiser"),
        KAISER_2(6110, 6000, "Kaiser"),
        KAISER_3(6111, 6000, "Kaiser"),
        KAISER_4(6112, 6000, "Kaiser"),
        CADENA(6002, 6002, "Cadena"),
        CADENA_1(6400, 6002, "Cadena"),
        CADENA_2(6410, 6002, "Cadena"),
        CADENA_3(6411, 6002, "Cadena"),
        CADENA_4(6412, 6002, "Cadena"),
        ANGELIC_BUSTER_1(6500, 6001, "Angelic Buster"),
        ANGELIC_BUSTER_2(6510, 6001, "Angelic Buster"),
        ANGELIC_BUSTER_3(6511, 6001, "Angelic Buster"),
        ANGELIC_BUSTER_4(6512, 6001, "Angelic Buster"),
        ADDITIONAL_SKILLS(9000, 0, ""),
        ZERO(10000, 10000, "Zero"),
        ZERO_1(10100, 10000, "Zero"),
        ZERO_2(10110, 10000, "Zero"),
        ZERO_3(10111, 10000, "Zero"),
        ZERO_4(10112, 10000, "Zero"),
        BEAST_TAMER(11000, 11000, "Beast Tamer"),
        BEAST_TAMER_1(11200, 11000, "Beast Tamer"),
        BEAST_TAMER_2(11210, 11000, "Beast Tamer"),
        BEAST_TAMER_3(11211, 11000, "Beast Tamer"),
        BEAST_TAMER_4(11212, 11000, "Beast Tamer"),
        PINK_BEAN_0(13000, 13000, "Pink Bean"),
        PINK_BEAN_1(13100, 13000, "Pink Bean"),
        KINESIS_0(14000, 14000, "Kinesis"),
        KINESIS_1(14200, 14000, "Kinesis"),
        KINESIS_2(14210, 14000, "Kinesis"),
        KINESIS_3(14211, 14000, "Kinesis"),
        KINESIS_4(14212, 14000, "Kinesis"),
        ILLIUM(15000, 15000, "Illium"),
        ARK(15001, 15001, "Ark"),
        ARK_1(15500, 15000, "Ark"),
        ARK_2(15510, 15000, "Ark"),
        ARK_3(15511, 15000, "Ark"),
        ARK_4(15512, 15000, "Ark"),
        ADELE(15002, 15002, "Adele"),
        ADELE_1(15100, 15002, "Adele"),
        ADELE_2(15110, 15002, "Adele"),
        ADELE_3(15111, 15002, "Adele"),
        ADELE_4(15112, 15002, "Adele"),
        HOYOUNG(16000, 16000, "Hoyoung"),
        HOYOUNG_1(16400, 16000, "Hoyoung"),
        HOYOUNG_2(16410, 16000, "Hoyoung"),
        HOYOUNG_3(16411, 16000, "Hoyoung"),
        HOYOUNG_4(16412, 16000, "Hoyoung"),
        ILLIUM_1(15200, 15001, "Illium"),
        ILLIUM_2(15210, 15001, "Illium"),
        ILLIUM_3(15211, 15001, "Illium"),
        ILLIUM_4(15212, 15001, "Illium"),
        EMPTY_0(30000, 0, ""),
        V_SKILLS_COMMON(40000, 0, ""),
        V_SKILLS_WARRIOR(40001, 0, ""),
        V_SKILLS_MAGE(40002, 0, ""),
        V_SKILLS_ARCHER(40003, 0, ""),
        V_SKILLS_THIEF(40004, 0, ""),
        V_SKILLS_PIRATE(40005, 0, ""),
        PINK_BEAN_EMPTY_0(800000, 13000, ""),
        PINK_BEAN_EMPTY_1(800001, 13000, ""),
        PINK_BEAN_EMPTY_2(800002, 13000, ""),
        PINK_BEAN_EMPTY_3(800003, 13000, ""),
        PINK_BEAN_EMPTY_4(800004, 13000, ""),
        PINK_BEAN_EMPTY_5(800010, 13000, ""),
        PINK_BEAN_EMPTY_6(800011, 13000, ""),
        PINK_BEAN_EMPTY_7(800012, 13000, ""),
        PINK_BEAN_EMPTY_8(800013, 13000, ""),
        PINK_BEAN_EMPTY_9(800014, 13000, ""),
        PINK_BEAN_EMPTY_10(800015, 13000, ""),
        PINK_BEAN_EMPTY_11(800016, 13000, ""),
        PINK_BEAN_EMPTY_12(800017, 13000, ""),
        PINK_BEAN_EMPTY_13(800018, 13000, ""),
        PINK_BEAN_EMPTY_14(800019, 13000, ""),
        PINK_BEAN_EMPTY_15(800022, 13000, "");

        private short jobId;
        private short beginnerJobId;
        private String jobName;

        JobEnum(short jobId, short beginnerJobId, String jobName) {
            this.jobId = jobId;
            this.beginnerJobId = beginnerJobId;
            this.jobName = jobName;
        }

        JobEnum(int jobId, int beginnerJobId, String jobName) {
            this((short) jobId, (short) beginnerJobId, jobName);
        }

        public short getJobId() {
            return jobId;
        }

        public short getBeginnerJobId() {
            return beginnerJobId;
        }

        public static JobEnum getJobById(short id) {
            return Util.findWithPred(values(), j -> j.getJobId() == id);
        }

        public String getJobName() { return jobName; }

        public Set<WeaponType> getUsingWeapons() {
            Set<WeaponType> wts = new HashSet<>();
            switch (this) {
                case BEGINNER:
                case WARRIOR:
                case NOBLESSE:
                case LEGEND:
                case CITIZEN:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedAxe);
                    wts.add(WeaponType.TwoHandedMace);
                    break;
                case FIGHTER:
                case CRUSADER:
                case HERO:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedAxe);
                    break;
                case PAGE:
                case WHITE_KNIGHT:
                case PALADIN:
                    wts.add(WeaponType.OneHandedSword);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.TwoHandedSword);
                    wts.add(WeaponType.TwoHandedMace);
                    break;
                case SPEARMAN:
                case DRAGON_KNIGHT:
                case DARK_KNIGHT:
                    wts.add(WeaponType.Spear);
                    wts.add(WeaponType.Polearm);
                    break;
                case MAGICIAN:
                case FP_WIZARD:
                case FP_MAGE:
                case FP_ARCHMAGE:
                case IL_WIZARD:
                case IL_MAGE:
                case IL_ARCHMAGE:
                case CLERIC:
                case PRIEST:
                case BISHOP:
                case EVAN_NOOB:
                case EVAN_1:
                case EVAN_2:
                case EVAN_3:
                case EVAN_4:
                case BLAZE_WIZARD_1:
                case BLAZE_WIZARD_2:
                case BLAZE_WIZARD_3:
                case BLAZE_WIZARD_4:
                    wts.add(WeaponType.Wand);
                    wts.add(WeaponType.Staff);
                    break;
                case BOWMAN:
                case HUNTER:
                case RANGER:
                case BOWMASTER:
                case WIND_ARCHER_1:
                case WIND_ARCHER_2:
                case WIND_ARCHER_3:
                case WIND_ARCHER_4:
                    wts.add(WeaponType.Bow);
                    break;
                case CROSSBOWMAN:
                case SNIPER:
                case MARKSMAN:
                case WILD_HUNTER_1:
                case WILD_HUNTER_2:
                case WILD_HUNTER_3:
                case WILD_HUNTER_4:
                    wts.add(WeaponType.Crossbow);
                    break;
                case THIEF:
                    wts.add(WeaponType.Dagger);
                    wts.add(WeaponType.Claw);
                    break;
                case ASSASSIN:
                case HERMIT:
                case NIGHT_LORD:
                case NIGHT_WALKER_1:
                case NIGHT_WALKER_2:
                case NIGHT_WALKER_3:
                case NIGHT_WALKER_4:
                    wts.add(WeaponType.Claw);
                    break;
                case BANDIT:
                case CHIEF_BANDIT:
                case SHADOWER:
                case BLADE_RECRUIT:
                case BLADE_ACOLYTE:
                case BLADE_SPECIALIST:
                case BLADE_LORD:
                case BLADE_MASTER:
                    wts.add(WeaponType.Dagger);
                    break;
                case PIRATE:
                    wts.add(WeaponType.Knuckle);
                    wts.add(WeaponType.Gun);
                    break;
                case BRAWLER:
                case MARAUDER:
                case BUCCANEER:
                case SHADE:
                case SHADE_1:
                case SHADE_2:
                case SHADE_3:
                case SHADE_4:
                case THUNDER_BREAKER_1:
                case THUNDER_BREAKER_2:
                case THUNDER_BREAKER_3:
                case THUNDER_BREAKER_4:
                    wts.add(WeaponType.Knuckle);
                    break;
                case GUNSLINGER:
                case OUTLAW:
                case CORSAIR:
                case JETT_1:
                case JETT_2:
                case JETT_3:
                case JETT_4:
                case MECHANIC_1:
                case MECHANIC_2:
                case MECHANIC_3:
                case MECHANIC_4:
                    wts.add(WeaponType.Gun);
                    break;
                case PIRATE_CANNONEER:
                case CANNONEER:
                case CANNON_BLASTER:
                case CANNON_MASTER:
                    wts.add(WeaponType.HandCannon);
                    break;
                case DAWN_WARRIOR_1:
                case DAWN_WARRIOR_2:
                case DAWN_WARRIOR_3:
                case DAWN_WARRIOR_4:
                case KAISER:
                case KAISER_1:
                case KAISER_2:
                case KAISER_3:
                case KAISER_4:
                    wts.add(WeaponType.TwoHandedSword);
                    break;
                case ARAN_1:
                case ARAN_2:
                case ARAN_3:
                case ARAN_4:
                    wts.add(WeaponType.Polearm);
                    break;
                case MERCEDES:
                case MERCEDES_1:
                case MERCEDES_2:
                case MERCEDES_3:
                case MERCEDES_4:
                    wts.add(WeaponType.DualBowgun);
                    break;
                case PHANTOM:
                case PHANTOM_1:
                case PHANTOM_2:
                case PHANTOM_3:
                case PHANTOM_4:
                    wts.add(WeaponType.Cane);
                    break;
                case LUMINOUS:
                case LUMINOUS_1:
                case LUMINOUS_2:
                case LUMINOUS_3:
                case LUMINOUS_4:
                    wts.add(WeaponType.ShiningRod);
                    break;
                case DEMON:
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    wts.add(WeaponType.Desperado);
                    break;
                case DEMON_SLAYER_1:
                case DEMON_SLAYER_2:
                case DEMON_SLAYER_3:
                case DEMON_SLAYER_4:
                    wts.add(WeaponType.OneHandedAxe);
                    wts.add(WeaponType.OneHandedMace);
                    break;
                case DEMON_AVENGER_1:
                case DEMON_AVENGER_2:
                case DEMON_AVENGER_3:
                case DEMON_AVENGER_4:
                    wts.add(WeaponType.Desperado);
                    break;
                case BATTLE_MAGE_1:
                case BATTLE_MAGE_2:
                case BATTLE_MAGE_3:
                case BATTLE_MAGE_4:
                    wts.add(WeaponType.Staff);
                    break;
                case BLASTER_1:
                case BLASTER_2:
                case BLASTER_3:
                case BLASTER_4:
                    wts.add(WeaponType.ArmCannon);
                    break;
                case XENON:
                case XENON_1:
                case XENON_2:
                case XENON_3:
                case XENON_4:
                    wts.add(WeaponType.ChainSword);
                    break;
                case HAYATO:
                case HAYATO_1:
                case HAYATO_2:
                case HAYATO_3:
                case HAYATO_4:
                    wts.add(WeaponType.Katana);
                    break;
                case KANNA:
                case KANNA_1:
                case KANNA_2:
                case KANNA_3:
                case KANNA_4:
                    wts.add(WeaponType.Fan);
                    break;
                case NAMELESS_WARDEN:
                case MIHILE_1:
                case MIHILE_2:
                case MIHILE_3:
                case MIHILE_4:
                    wts.add(WeaponType.OneHandedSword);
                    break;
                case ANGELIC_BUSTER:
                case ANGELIC_BUSTER_1:
                case ANGELIC_BUSTER_2:
                case ANGELIC_BUSTER_3:
                case ANGELIC_BUSTER_4:
                    wts.add(WeaponType.SoulShooter);
                    break;
                case ZERO:
                case ZERO_1:
                case ZERO_2:
                case ZERO_3:
                case ZERO_4:
                    wts.add(WeaponType.LongSword);
                    wts.add(WeaponType.BigSword);
                    break;
                case BEAST_TAMER:
                case BEAST_TAMER_1:
                case BEAST_TAMER_2:
                case BEAST_TAMER_3:
                case BEAST_TAMER_4:
                    wts.add(WeaponType.Scepter);
                    break;
                case KINESIS_0:
                case KINESIS_1:
                case KINESIS_2:
                case KINESIS_3:
                case KINESIS_4:
                    wts.add(WeaponType.PsyLimiter);
                    break;
                case PATHFINDER_1:
                case PATHFINDER_2:
                case PATHFINDER_3:
                case PATHFINDER_4:
                    wts.add(WeaponType.AncientBow);
                    break;
                case HOYOUNG:
                case HOYOUNG_1:
                case HOYOUNG_2:
                case HOYOUNG_3:
                case HOYOUNG_4:
                    wts.add(WeaponType.RitualFan);
                    break;
            }
            return wts;
        }
    }

    public enum LoginJob {
        RESISTANCE(0, JobFlag.ENABLED, JobEnum.CITIZEN),
        EXPLORER(1, JobFlag.ENABLED, JobEnum.BEGINNER),
        CYGNUS(2, JobFlag.ENABLED, JobEnum.NOBLESSE),
        ARAN(3, JobFlag.ENABLED, JobEnum.LEGEND),
        EVAN(4, JobFlag.ENABLED, JobEnum.EVAN_NOOB),
        MERCEDES(5, JobFlag.ENABLED, JobEnum.MERCEDES),
        DEMON(6, JobFlag.ENABLED, JobEnum.DEMON),
        PHANTOM(7, JobFlag.ENABLED, JobEnum.PHANTOM),
        DUAL_BLADE(8, JobFlag.ENABLED, JobEnum.BEGINNER),
        MIHILE(9, JobFlag.ENABLED, JobEnum.NAMELESS_WARDEN),
        LUMINOUS(10, JobFlag.ENABLED, JobEnum.LUMINOUS),
        KAISER(11, JobFlag.ENABLED, JobEnum.KAISER),
        ANGELIC(12, JobFlag.ENABLED, JobEnum.ANGELIC_BUSTER),
        CANNONER(13, JobFlag.ENABLED, JobEnum.BEGINNER),
        XENON(14, JobFlag.ENABLED, JobEnum.XENON),
        ZERO(15, JobFlag.ENABLED, JobEnum.ZERO),
        SHADE(16, JobFlag.ENABLED, JobEnum.SHADE),
        PINK_BEAN(17, JobFlag.DISABLED, JobEnum.PINK_BEAN_0),
        KINESIS(18, JobFlag.ENABLED, JobEnum.KINESIS_0),
        CADENA(19, JobFlag.ENABLED, JobEnum.CADENA),
        ILLIUM(20, JobFlag.ENABLED, JobEnum.ILLIUM),
        ARK(21, JobFlag.ENABLED, JobEnum.ARK),
        PATHFINDER(22, JobFlag.ENABLED, JobEnum.PATHFINDER_1),
        HOYOUNG(23, JobFlag.ENABLED, JobEnum.HOYOUNG),
        ADELE(24, JobFlag.ENABLED, JobEnum.ADELE),
        JETT(1000, JobFlag.ENABLED, JobEnum.BEGINNER),
        HAYATO(1001, JobFlag.ENABLED, JobEnum.HAYATO),
        KANNA(1002, JobFlag.ENABLED, JobEnum.KANNA),
        CHASE(1003, JobFlag.ENABLED, JobEnum.BEAST_TAMER),
        ;

        private final int jobType, flag;
        private JobEnum beginJob;

        LoginJob(int jobType, JobFlag flag, JobEnum beginJob) {
            this.jobType = jobType;
            this.flag = flag.getFlag();
            this.beginJob = beginJob;
        }

        public int getJobType() {
            return jobType;
        }

        public int getFlag() {
            return flag;
        }

        public JobEnum getBeginJob() {
            return beginJob;
        }

        public enum JobFlag {

            DISABLED(0),
            ENABLED(1);
            private final int flag;

            JobFlag(int flag) {
                this.flag = flag;
            }

            public int getFlag() {
                return flag;
            }
        }

        public static LoginJob getLoginJobById(int id) {
            return Arrays.stream(LoginJob.values()).filter(j -> j.getJobType() == id).findFirst().orElse(null);
        }
    }

    public static void encode(OutPacket outPacket) {
        outPacket.encodeByte(enableJobs);
        outPacket.encodeByte(jobOrder);
        for (LoginJob loginJobId : LoginJob.values()) {
            outPacket.encodeByte(loginJobId.getFlag());
            outPacket.encodeShort(loginJobId.getFlag());
        }
    }

    public static boolean isAdventurerWarrior(short jobId) {
        return jobId == JobEnum.WARRIOR.getJobId()
                || jobId == JobEnum.FIGHTER.getJobId()
                || jobId == JobEnum.CRUSADER.getJobId()
                || jobId == JobEnum.HERO.getJobId()
                || jobId == JobEnum.PAGE.getJobId()
                || jobId == JobEnum.WHITE_KNIGHT.getJobId()
                || jobId == JobEnum.PALADIN.getJobId()
                || jobId == JobEnum.SPEARMAN.getJobId()
                || jobId == JobEnum.DRAGON_KNIGHT.getJobId()
                || jobId == JobEnum.DARK_KNIGHT.getJobId();
    }


    public static boolean isAdventurerBeginner(short jobId) {
        return jobId == JobEnum.WARRIOR.getJobId()
                || jobId == JobEnum.MAGICIAN.getJobId()
                || jobId == JobEnum.BOWMAN.getJobId()
                || jobId == JobEnum.THIEF.getJobId()
                || jobId == JobEnum.PIRATE.getJobId();
    }

    public static boolean isAdventurerMage(short jobId) {
        return jobId == JobEnum.MAGICIAN.getJobId()
                || jobId == JobEnum.FP_WIZARD.getJobId()
                || jobId == JobEnum.FP_MAGE.getJobId()
                || jobId == JobEnum.FP_ARCHMAGE.getJobId()
                || jobId == JobEnum.IL_WIZARD.getJobId()
                || jobId == JobEnum.IL_MAGE.getJobId()
                || jobId == JobEnum.IL_ARCHMAGE.getJobId()
                || jobId == JobEnum.CLERIC.getJobId()
                || jobId == JobEnum.PRIEST.getJobId()
                || jobId == JobEnum.BISHOP.getJobId();
    }

    public static boolean isAdventurerArcher(short jobId) {
        return jobId == JobEnum.BOWMAN.getJobId()
                || jobId == JobEnum.HUNTER.getJobId()
                || jobId == JobEnum.RANGER.getJobId()
                || jobId == JobEnum.BOWMASTER.getJobId()
                || jobId == JobEnum.CROSSBOWMAN.getJobId()
                || jobId == JobEnum.SNIPER.getJobId()
                || jobId == JobEnum.MARKSMAN.getJobId()
                || jobId == JobEnum.PATHFINDER_1.getJobId()
                || jobId == JobEnum.PATHFINDER_2.getJobId()
                || jobId == JobEnum.PATHFINDER_3.getJobId()
                || jobId == JobEnum.PATHFINDER_4.getJobId();
    }

    public static boolean isAdventurerThief(short jobId) {
        return jobId == JobEnum.THIEF.getJobId()
                || jobId == JobEnum.ASSASSIN.getJobId()
                || jobId == JobEnum.HERMIT.getJobId()
                || jobId == JobEnum.NIGHT_LORD.getJobId()
                || jobId == JobEnum.BANDIT.getJobId()
                || jobId == JobEnum.CHIEF_BANDIT.getJobId()
                || jobId == JobEnum.SHADOWER.getJobId()
                || jobId == JobEnum.BLADE_RECRUIT.getJobId()
                || jobId == JobEnum.BLADE_ACOLYTE.getJobId()
                || jobId == JobEnum.BLADE_SPECIALIST.getJobId()
                || jobId == JobEnum.BLADE_LORD.getJobId()
                || jobId == JobEnum.BLADE_MASTER.getJobId();
    }

    public static boolean isAdventurerPirate(short jobId) {
        return jobId == JobEnum.PIRATE.getJobId()
                || jobId == JobEnum.BRAWLER.getJobId()
                || jobId == JobEnum.MARAUDER.getJobId()
                || jobId == JobEnum.BUCCANEER.getJobId()
                || jobId == JobEnum.GUNSLINGER.getJobId()
                || jobId == JobEnum.OUTLAW.getJobId()
                || jobId == JobEnum.CORSAIR.getJobId()
                || isCannonShooter(jobId);
    }

    public static boolean isCannonShooter(short jobId) {
        return jobId / 10 == 53 || jobId == 501;
    }

    public static boolean isCygnusKnight(short jobId) {
        return jobId / 1000 == 1;
    }

    public static boolean isResistance(short jobId) {
        return jobId / 1000 == 3;
    }

    public static boolean isSengoku(short jobId) {
        return jobId / 1000 == 4;
    }

    public static boolean isEvan(short jobId) {
        return jobId / 100 == 22 || jobId == 2001;
    }

    public static boolean isMercedes(short jobId) {
        return jobId / 100 == 23 || jobId == 2002;
    }

    public static boolean isPhantom(short jobId) {
        return jobId / 100 == 24 || jobId == 2003;
    }

    public static boolean isLeader(short jobId) {
        return jobId / 1000 == 5;
    }

    public static boolean isLuminous(short jobId) {
        return jobId / 100 == 27 || jobId == 2004;
    }

    public static boolean isKaiser(short jobId) {
        return jobId == JobConstants.JobEnum.KAISER.getJobId() || jobId / 100 == 61;
    }

    public static boolean isZero(short jobId) {
        return jobId == JobEnum.ZERO.getJobId()
                || jobId == JobEnum.ZERO_1.getJobId()
                || jobId == JobEnum.ZERO_2.getJobId()
                || jobId == JobEnum.ZERO_3.getJobId()
                || jobId == JobEnum.ZERO_4.getJobId();
    }

    public static boolean isArk(short jobID) {
        return jobID == 15001 || (jobID >= 15500 && jobID <= 15512);
    }

    public static boolean isCadena(short jobID) {
        return jobID == 6002 || (jobID >= 6400 && jobID <= 6412);
    }

    public static boolean isIllium(short jobID) {
        return jobID == 15000 || (jobID >= 15200 && jobID <= 15212);
    }

    public static boolean isHidden(short jobId) {
        return jobId / 100 == 25 || jobId == 2005;
    }

    public static boolean isAran(short jobId) {
        return jobId / 100 == 21 || jobId == 2000;
    }

    public static boolean isHoYoung(short jobId) {
        return jobId == JobEnum.HOYOUNG.getJobId()
                || jobId == JobEnum.HOYOUNG_1.getJobId()
                || jobId == JobEnum.HOYOUNG_2.getJobId()
                || jobId == JobEnum.HOYOUNG_3.getJobId()
                || jobId == JobEnum.HOYOUNG_4.getJobId();
    }


    public static boolean isAdele(short jobId) {
        return jobId == JobEnum.ADELE.getJobId()
                || jobId == JobEnum.ADELE_1.getJobId()
                || jobId == JobEnum.ADELE_2.getJobId()
                || jobId == JobEnum.ADELE_3.getJobId()
                || jobId == JobEnum.ADELE_4.getJobId();
    }

    public static boolean isKinesis(short jobId) {
        return jobId == JobEnum.KINESIS_0.getJobId()
                || jobId == JobEnum.KINESIS_1.getJobId()
                || jobId == JobEnum.KINESIS_2.getJobId()
                || jobId == JobEnum.KINESIS_3.getJobId()
                || jobId == JobEnum.KINESIS_4.getJobId();
    }

    public static boolean isExtendSpJob(short jobId) {
        return !isBeastTamer(jobId) && !isPinkBean(jobId) && !isGmJob(jobId) && !isManager(jobId);
    }

    public static boolean isDemon(short jobId) {
        return jobId / 100 == 31 || jobId == 3001;
    }

    public static boolean isFlora(short jobId) {
        return jobId / 1000 == 15;
    }

    public static boolean isBeginnerJob(short jobId) {
        switch (jobId) {
            case 8001:
            case 15001:
            case 15002:
            case 6001:
            case 6002:
            case 4001:
            case 4002:
            case 3001:
            case 3002:
            case 2001:
            case 2002:
            case 2003:
            case 2004:
            case 2005:
                return true;
            default:
                return jobId % 1000 == 0 || jobId / 100 == 8000 || jobId / 100 == 8001;
        }
    }

    public static int getJobLevel(short jobId) {
        int prefix;
        if (isBeginnerJob(jobId) || (jobId % 100 == 0) || jobId == 301 || jobId == 501 || jobId == 3101) {
            return 1;
        }
        if (isEvan(jobId)) {
            return getEvanJobLevel(jobId);
        }
        if (isDualBlade(jobId)) {
            prefix = (jobId - 430) / 2;
            if (prefix <= 2) {
                prefix = prefix + 2;
            }
            return prefix;
        } else {
            prefix = jobId % 10;
        }
        return prefix <= 2 ? prefix + 2 : 0;
    }

    public static int getJobLevelByCharLevel(short job, int charLevel) {
        if (JobConstants.isDualBlade(job)) {
            if (charLevel <= 10) {
                return 0;
            } else if (charLevel <= 20) {
                return 1;
            } else if (charLevel <= 30) {
                return 2;
            } else if (charLevel <= 45) {
                return 3;
            } else if (charLevel <= 60) {
                return 4;
            } else if (charLevel <= 100) {
                return 5;
            } else {
                return 6;
            }
        }
        if (charLevel <= 10) {
            return 0;
        } else if (charLevel <= 30) {
            return 1;
        } else if (charLevel <= 60) {
            return 2;
        } else if (charLevel <= 100) {
            return 3;
        } else {
            return 4;
        }
    }

    private static int getEvanJobLevel(short jobId) {
        int result;
        switch (jobId) {
            case 2200:
            case 2210:
                result = 1;
                break;
            case 2211:
            case 2212:
            case 2213:
                result = 2;
                break;
            case 2214:
            case 2215:
            case 2216:
                result = 3;
                break;
            case 2217:
            case 2218:
                result = 4;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    public static boolean isNoManaJob(short job) {
        return isDemon(job) || isAngelicBuster(job) || isZero(job) || isKinesis(job) || isKanna(job);
    }

    public static boolean isWarriorEquipJob(short jobID) {
        return isAdventurerWarrior(jobID) || isPinkBean(jobID) || isDawnWarrior(jobID) || isMihile(jobID) ||
                isAran(jobID) || isKaiser(jobID) || isBlaster(jobID) || isDemon(jobID) || isHayato(jobID) ||
                isZero(jobID) || isAdele(jobID);

    }

    public static boolean isMageEquipJob(short jobID) {
        return isBeastTamer(jobID) || isKinesis(jobID) || isAdventurerMage(jobID) || isBlazeWizard(jobID) ||
                isEvan(jobID) || isLuminous(jobID) || isBattleMage(jobID) || isKanna(jobID) || isIllium(jobID);
    }


    public static boolean isArcherEquipJob(short jobID) {
        return isAdventurerArcher(jobID) || isWindArcher(jobID) || isMercedes(jobID) || isWildHunter(jobID);
    }

    public static boolean isThiefEquipJob(short jobID) {
        return isAdventurerThief(jobID) || isNightWalker(jobID) || isPhantom(jobID) || isXenon(jobID) || isCadena(jobID) || isHoYoung(jobID);
    }

    public static boolean isPirateEquipJob(short jobID) {
        return isAdventurerPirate(jobID) || isThunderBreaker(jobID) || isShade(jobID) || isAngelicBuster(jobID) ||
                isXenon(jobID) || isMechanic(jobID) || isJett(jobID) || isArk(jobID);
    }

}
