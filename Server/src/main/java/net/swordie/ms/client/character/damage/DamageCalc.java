package net.swordie.ms.client.character.damage;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;

import java.util.*;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.SetBaseDamage;
import static net.swordie.ms.enums.BaseStat.*;
import static net.swordie.ms.enums.BaseStat.cr;


/**
 * Created on 5/4/2018.
 */
public class DamageCalc {

    private final static int RAND_NUM = 11;
    public final static int MAX_CRIT_DMG = 50;
    public final static int MIN_CRIT_DMG = 20;

    private Rand32 rand;
    private Char chr;

    public Rand32 getRand() {
        return rand;
    }

    public DamageCalc(Char chr, int seed1, int seed2, int seed3) {
        rand = new Rand32();
        rand.seed(seed1, seed2, seed3);
        this.chr = chr;
    }

    public long calcPDamageForPvM(int skillID, int slv, int dotDmg) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        WeaponType weaponType = chr.getWeaponType();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        double mult = (si == null ? 100 : dotDmg) / 100D;
        mult = mult == 0 ? si.getValue(SkillStat.damage, slv) / 100D : mult;
        BaseStat mainStat = GameConstants.getMainStatForJob(chr.getJob());
        BaseStat secStat = GameConstants.getSecStatByMainStat(mainStat);
        BaseStat attStat = mainStat == inte ? mad : pad;
        int mainAmount = chr.getTotalStat(mainStat);
        int secAmount = chr.getTotalStat(secStat);
        int attAmount = chr.getTotalStat(attStat);
        Map<BaseStat, Integer> basicStats = chr.getTotalBasicStats();
        int setBaseDamage = tsm.hasStat(SetBaseDamage) ? tsm.getOption(SetBaseDamage).nOption : 0;
        long damage = calcDamageByWT(weaponType, basicStats, setBaseDamage, skillID);
        return (long) (damage * mult);
    }

    private double getMinBaseDamage() {
        return 0.5 + getMaxBaseDamage() * (getMastery() / 100D); // 0.5 for rounding to closest int
    }

    private double getMastery() {
        int mas = chr.getTotalStat(mastery);
        int base = 0;
        switch (chr.getWeaponType()) {
            case None:
                break;
            // physical melee
            case Desperado:
            case ChainSword:
            case Chain:
            case Gauntlet:
            case OneHandedSword:
            case OneHandedAxe:
            case OneHandedMace:
            case Dagger:
            case Katara:
            case Cane:
            case Barehand:
            case TwoHandedSword:
            case TwoHandedAxe:
            case TwoHandedMace:
            case Spear:
            case Polearm:
            case Knuckle:
            case Katana:
            case BigSword:
            case LongSword:
            case ArmCannon:
                base = 20;
                break;
            // physical ranged
            case SoulShooter:
            case Bow:
            case Crossbow:
            case Claw:
            case Gun:
            case DualBowgun:
            case HandCannon:
            case AncientBow:
                base = 15;
                break;
            // magic
            case ShiningRod:
            case Scepter:
            case PsyLimiter:
            case Wand:
            case Staff:
            case Fan:
                base = 25;
                break;
        }
        return Math.min(99, base + mas); // 99% mastery is maximum
    }

    private double getMaxBaseDamage() {
        WeaponType weaponType = ItemConstants.getWeaponType(chr.getEquippedItemByBodyPart(BodyPart.Weapon).getItemId());
        BaseStat mainStat = GameConstants.getMainStatForJob(chr.getJob());
        BaseStat secStat = GameConstants.getSecStatByMainStat(mainStat);
        BaseStat attStat = mainStat == inte ? mad : pad;
        int mainAmount = chr.getTotalStat(mainStat);
        int secAmount = chr.getTotalStat(secStat);
        int attAmount = chr.getTotalStat(attStat);
        Map<BaseStat, Integer> basicStats = chr.getTotalBasicStats();
        long damage = calcDamageByWT(weaponType, basicStats, 0, 0);
        return (damage);
    }

    private long calcDamageByWT(WeaponType wt, Map<BaseStat, Integer> stats, int setBaseDamage, int skillID) {
        if (setBaseDamage > 0) {
            return setBaseDamage;
        }
        long dmg = 0;
        double jobConst = JobConstants.getDamageConstant(chr.getJob());
        short job = chr.getJob();
        if (JobConstants.isBeginnerJob(job)) {
            dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.2);
        } else {
            if (JobConstants.getJobCategory(job) != 2 || JobConstants.isLuminous(job) || JobConstants.isKinesis(job)) {
                switch (wt) {
                    case None:
                        break;
                    case ShiningRod:
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.2);
                        break;
                    case SoulShooter:
                        dmg = calcBaseDamage(stats.get(dex), stats.get(str), 0, stats.get(pad), jobConst + 1.7);
                        break;
                    case ChainSword:
                        dmg = calcHybridBaseDamage(stats.get(str), stats.get(dex), stats.get(luk), 0, stats.get(pad), jobConst + 1.5);
                        break;
                    case Scepter:
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.34);
                        break;
                    case PsyLimiter:
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.2);
                        break;
                    case Chain:
                        dmg = calcBaseDamage(stats.get(luk), stats.get(dex), stats.get(str), stats.get(pad), jobConst + 1.3);
                        break;
                    case Gauntlet:
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.2);
                        break;
                    case OneHandedSword:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.2);
                        break;
                    case OneHandedAxe:
                    case OneHandedMace:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.2);
                        break;
                    case Dagger:
                        dmg = calcBaseDamage(stats.get(luk), stats.get(dex), stats.get(str), stats.get(pad), jobConst + 1.2);
                        break;
                    case Katara:
                        dmg = calcBaseDamage(stats.get(luk), stats.get(dex), stats.get(str), stats.get(pad), jobConst + 1.3);
                        break;
                    case Cane:
                        double k;
                        if (SkillConstants.isStealableSkill(skillID)) {
                            k = jobConst + 1.2;
                        } else {
                            k = jobConst + 1.3;
                        }
                        dmg = calcBaseDamage(stats.get(luk), stats.get(dex), 0, stats.get(pad), k);
                        break;
                    case Wand:
                    case Staff:
                        double mult;
                        if (JobConstants.isBlazeWizard(chr.getJob()) || JobConstants.isExplorer(chr.getJob())) {
                            mult = 1.2;
                        } else {
                            mult = 1;
                        }
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + mult);
                        break;
                    case Barehand:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, 1, jobConst + 1.43);
                        break;
                    case TwoHandedSword:
                    case LongSword:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.34);
                        break;
                    case TwoHandedAxe:
                    case TwoHandedMace:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.34);
                        break;
                    case Katana:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.25);
                        break;
                    case Fan:
                        dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.35);
                        break;
                    case Spear:
                    case Polearm:
                    case BigSword:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.49);
                        break;
                    case Bow:
                    case AncientBow: // ?
                        dmg = calcBaseDamage(stats.get(dex), stats.get(str), 0, stats.get(pad), jobConst + 1.3);
                        break;
                    case Crossbow:
                        dmg = calcBaseDamage(stats.get(dex), stats.get(str), 0, stats.get(pad), jobConst + 1.35);
                        break;
                    case Claw:
                        dmg = calcBaseDamage(stats.get(luk), stats.get(dex), 0, stats.get(pad), jobConst + 1.75);
                        break;
                    case Knuckle:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.7);
                        break;
                    case Gun:
                        dmg = calcBaseDamage(stats.get(dex), stats.get(str), 0, stats.get(pad), jobConst + 1.5);
                        break;
                    case DualBowgun:
                        dmg = calcBaseDamage(stats.get(dex), stats.get(str), 0, stats.get(pad), jobConst + 1.3);
                        break;
                    case HandCannon:
                        int dexNum = stats.get(dex);
                        int strNum = stats.get(str);
                        if (strNum >= dexNum) {
                            int temp = dexNum;
                            dexNum = strNum;
                            strNum = temp;
                        }
                        dmg = calcBaseDamage(dexNum, strNum, 0, stats.get(pad), jobConst + 1.5);
                        break;
                    case ArmCannon:
                        dmg = calcBaseDamage(stats.get(str), stats.get(dex), 0, stats.get(pad), jobConst + 1.7);
                        break;
                    case Desperado:
                        // calcDamageByHp, first arg is raw hp, 2nd is
                        dmg = calcBaseDamageByHp(chr.getStat(Stat.mhp), stats.get(mhp), stats.get(str), stats.get(pad), jobConst + 1.3);
                        break;
                }
            } else {
                dmg = calcBaseDamage(stats.get(inte), stats.get(luk), 0, stats.get(mad), jobConst + 1.0);
            }
        }
        return dmg;
    }

    private long calcHybridBaseDamage(int stat1, int stat2, int stat3, int stat4, int pad, double finalDamage) {
        return (long) ((stat1 * 3.5 + stat2 * 3.5 + stat3 * 3.5 + stat4) / 100.0 * (pad * finalDamage) + 0.5);
    }

    private long calcBaseDamage(int mainStat, int secStat, int tertStat, int pad, double finalDamage) {
        return (long) ((tertStat + secStat + 4 * mainStat) / 100.0 * (pad * finalDamage) + 0.5);
    }

    private long calcBaseDamageByHp(int rawHp, int totalHp, int str, int pad, double finalDamage) {
        return (long) (((int) (rawHp / 3.5) + 0.8 * ((int) ((totalHp - rawHp) / 3.5)) + str) / 100.0 * (pad * finalDamage) + 0.5);
    }

    public void checkDamage(AttackInfo attackInfo) {

        long minDamage = (long) getMinBaseDamage();
        long maxDamage = (long) getMaxBaseDamage();
        int skillId = attackInfo.skillId;
        // make seperate variables, as they can change based on the mob being a boss or not
        int chrIed = chr.getTotalStat(BaseStat.ied);
        int chrFd = chr.getTotalStat(BaseStat.fd);
        int chrCr = chr.getTotalStat(BaseStat.cr);
        int chrDam = chr.getTotalStat(damR);
        // star force
        int reqChuc = chr.getField().getBarrier();
        int sfMult = 0;
        if (reqChuc > 0) {
            int userChuc = chr.getTotalChuc();
            int perc = userChuc / reqChuc;
            int diff = userChuc - reqChuc;
            sfMult = GameConstants.getStarForceMultiplier(perc, diff);
        }
        // arcane force
        int reqArc = chr.getField().getBarrierArc();
        int arcMult = 0;
        if (reqArc > 0) {
            int userArc = chr.getTotalStat(BaseStat.arc);
            int perc = userArc / reqArc;
            arcMult = GameConstants.getArcaneForceMultiplier(perc);
        }
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            int ied = chrIed;
            int fd = chrFd;
            int cr = chrCr;
            int dam = chrDam;
            Mob mob = mai.mob;
            long[] randNums = new long[RAND_NUM];
            long[] damages = new long[RAND_NUM];
            boolean[] usedIdx = new boolean[RAND_NUM];
            for (int i = 0; i < randNums.length; i++) {
                randNums[i] = getRand().random();
            }
            if (mob == null) {
                return;
            }
            boolean boss = mob.isBoss();
            StringBuilder sb = new StringBuilder("(C):");
            StringBuilder sb2 = new StringBuilder("(S):");

            for (int i = 0; i < damages.length; i++) {
                int index = i;
                double realDamage = 0;
                long rand = randNums[index++ % RAND_NUM];

                double adjustedRandomDamage = randomInRange(rand, minDamage, maxDamage);
                realDamage += adjustedRandomDamage;

                //Adjusted Damage By Monster's Physical Defense Rate
                double monsterPDRate = mob.getForcedMobStat().getPdr();
                double percentDmgAfterPDRate = Math.max(0.0, 100.0 - monsterPDRate);
                realDamage = percentDmgAfterPDRate / 100.0 * realDamage;

                //Adjusted Damage By Skill
                SkillInfo si = null;
                if (skillId > 0) {
                    si = SkillData.getSkillInfoById(skillId);
                }
                if (si != null) {
                    realDamage = realDamage * (double) si.getValue(SkillStat.damage, chr.getSkillLevel(skillId)) / 100.0;
                }

                realDamage *= 1 + GameConstants.getDamageBonusFromLevelDifference(chr.getLevel(), mob.getLevel());
                if (realDamage <= 0) {
                    realDamage = 1;
                }
                damages[i] = (long) realDamage;
            }
            if (chr.isShowDamageCalc()) {
                chr.chatMessage("Possible damages: " + Arrays.toString(damages));
            }

            int index = 0;
            int[] indices = new int[mai.damages.length];
            boolean start = true;
            int j = 0;
            mai.crits = new boolean[mai.damages.length];
            for (long damage : mai.damages) {
                boolean reusedIdx = usedIdx[index % RAND_NUM];
                if (!reusedIdx) {
                    usedIdx[index % RAND_NUM] = true;
                }
                if (start) {
                    sb.append(" ").append(damage);
                } else {
                    sb.append(" + ").append(damage);
                }
                double realDamage = 0d;
                index++;
                long unkRand1 = randNums[index++ % RAND_NUM];
                index++;
                index++;


                // Adjusted Random Damage
                // TODO: cache min/max damage
                long rand;
                indices[j] = index % 11;
                rand = randNums[index++ % RAND_NUM];

                double adjustedRandomDamage = randomInRange(rand, minDamage, maxDamage);
                realDamage += adjustedRandomDamage;


                // Adjusted Damage By Monster's Physical Defense Rate
                double monsterPDRate = mob.getForcedMobStat().getPdr();
                monsterPDRate -= monsterPDRate * (ied / 100D);
                double percentDmgAfterPDRate = Math.max(0.0, 100D - monsterPDRate);
                realDamage = percentDmgAfterPDRate / 100D * realDamage;


                // Adjusted Damage By Skill
                SkillInfo si = null;
                if (skillId > 0) {
                    si = SkillData.getSkillInfoById(skillId);
                }
                if (si != null) {
                    int slv = chr.getSkillLevel(skillId);
                    realDamage = realDamage * si.getValue(SkillStat.damage, slv) / 100D;
                    cr += si.getValue(SkillStat.cr, slv);
                    if (boss) {
                        dam += si.getValue(SkillStat.damageToBoss, slv);
                    }
                }

                // total damage
                if (mob.isBoss()) {
                    dam += chr.getTotalStat(bd);
                } else {
                    dam += chr.getTotalStat(nbd);
                }
                realDamage += realDamage * (dam / 100D);

                // Index reusage check
                if (reusedIdx) {
                    long randNewIndex = randNums[(j + index) % RAND_NUM];
                    index += randomInRange(randNewIndex, 0, 9);
                }

                // Final damage
                realDamage += realDamage * (fd / 100D);

                // Adjusted Critical Damage
                if (mob.isBoss()) {
                    cr += chr.getTotalStat(addCrOnBoss);
                }
                if (cr >= 100 || (cr > 0 && randomInRange(randNums[index++ % RAND_NUM], 0, 100) < cr)) {
                    mai.crits[j] = true;
                    int minCritDamage = getMinCritDamage();
                    int maxCritDamage = getMaxCritDamage();
                    double criticalDamageRate = randomInRange(randNums[index++ % RAND_NUM], minCritDamage, maxCritDamage);
                    realDamage = (long) (criticalDamageRate / 100D * realDamage + realDamage);
                }

                // Level difference
                realDamage *= 1 + GameConstants.getDamageBonusFromLevelDifference(chr.getLevel(), mob.getLevel());

                // Star force
                realDamage += realDamage * (sfMult / 100D);

                // Arcane force
                realDamage += realDamage * (arcMult / 100D);

                // Apply damage cap
                realDamage = Math.min(GameConstants.DAMAGE_CAP, (long) realDamage);

                if (realDamage <= 0) {
                    realDamage = 1;
                }

                // debug stuff
                if (start) {
                    sb2.append(" ").append((long) realDamage);
                } else {
                    sb2.append(" + ").append((long) realDamage);
                }
                if (mai.crits[j]) {
                    // current hit crit
                    sb.append(" (Crit)");
                    sb2.append(" (Crit)");
                }
                if (start) {
                    index++;
                }
                start = false;
                j++;
            }
            sb2.append(" ").append(Arrays.toString(indices));
            if (chr.isShowDamageCalc()) {
                chr.chatMessage(sb.toString());
                chr.chatMessage(sb2.toString());
            }
        }
    }

    private int getMinCritDamage() {
        return chr.getTotalStat(crDmg) + MIN_CRIT_DMG;
    }

    private int getMaxCritDamage() {
        return chr.getTotalStat(crDmg) + MAX_CRIT_DMG;
    }

    public double randomInRange(long randomNum, long min, long max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            // swap min and max values
            long temp = max;
            max = min;
            min = temp;
        }
        return min + (randomNum % 10000000) * (max - min) / 9999999.0;
    }

}
