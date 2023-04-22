package net.swordie.ms.enums;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.info.ToBaseStat;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 5/4/2018.
 */
public enum BaseStat {
    unk,
    str,
    strR,
    dex,
    dexR,
    inte,
    intR,
    luk,
    lukR,
    mdf, // MP for non-MP jobs,   Time Force, Fury, Mana
    pad,
    padR,
    mad,
    madR,
    pdd,
    pddR,
    mdd,
    mddR,
    mhp,
    mhpR,
    mmp,
    mmpR,
    cr, // Crit rate
    addCrOnBoss, // Additional Crit rate on bosses
    crDmg, // Crit Dmg %
    fd, // Final damage %
    damR, // Damage %
    bd, // Boss damage %
    nbd, // Non-Boss damage %
    ied, // Ignore enemy defense %
    asr, // All status resistance %
    ter, // Status time minus
    eleAttrResistance, // Elemental Attribute Resistance
    acc,
    accR,
    eva,
    evaR,
    jump,
    speed,
    expR,
    comboKillOrbExpR, // Exp multiplier specifically for Combo Kill Orbs
    dropR,
    dropRMulti,
    mesoR,
    mesoRMulti,
    booster,
    stance,
    mastery,
    damageOver, // max damage
    allStat,
    allStatR,
    hpRecovery,
    mpRecovery,
    incAllSkill,
    strLv,
    dexLv,
    intLv,
    lukLv,
    summonTimeR, // Summon Duration multiplier
    buffTimeR, // Buff Duration multiplier
    runeBuffTimerR, // Buff Duration multiplier specifically for Rune's EXP buff
    noCoolProp, // chance to skip cooltimes
    reduceCooltimeR, // %
    costhpR,
    costmpR,
    hpDrain,
    mpDrain,
    recoveryUp,
    mpconReduce, // % increase in heal potion use
    padLv,
    madLv,
    mhpLv,
    mmpLv,
    dmgReduce,
    magicGuard, // in %  of HP goes to MP instead.
    invincibleAfterRevive, // in seconds
    shopDiscountR, // % discount on shop items
    pqShopDiscountR, // % discount in pq Shop
    arc,
    ;


    public static BaseStat getFromStat(Stat s) {
        switch (s) {
            case str:
                return str;
            case dex:
                return dex;
            case inte:
                return inte;
            case luk:
                return luk;
            case mhp:
                return mhp;
            case mmp:
                return mmp;
            default:
                return unk;
        }
    }

    public BaseStat getRateVar() {
        switch (this) {
            case str:
                return strR;
            case dex:
                return dexR;
            case inte:
                return intR;
            case luk:
                return lukR;
            case pad:
                return padR;
            case mad:
                return madR;
            case pdd:
                return pddR;
            case mdd:
                return mddR;
            case mhp:
                return mhpR;
            case mmp:
                return mmpR;
            case acc:
                return accR;
            case eva:
                return evaR;
            case dropR:
                return dropRMulti;
            case mesoR:
                return mesoRMulti;
            default:
                return null;
        }
    }

    public BaseStat getLevelVar() {
        switch(this) {
            case str:
                return strLv;
            case dex:
                return dexLv;
            case inte:
                return intLv;
            case luk:
                return lukLv;
            case pad:
                return padLv;
            case mad:
                return madLv;
            case mhp:
                return mhpLv;
            case mmp:
                return mmpLv;
        }
        return null;
    }

    public static Map<BaseStat, Integer> getFromCTS(Char chr, CharacterTemporaryStat ctsArg, Option o) {
        Map<BaseStat, Integer> stats = new HashMap<>();
        switch (ctsArg) {
            case IndiePAD:
            case PyramidBonusDamageBuff:
                stats.put(pad, o.nValue);
                break;
            case EPAD:
            case PAD:
                stats.put(pad, o.nOption);
                break;
            case IndieMAD:
                stats.put(mad, o.nValue);
                break;
            case MAD:
            case EMAD:
                stats.put(mad, o.nOption);
                break;
            case IndieDEF:
                stats.put(pdd, o.nValue);
                break;
            case DEF:
            case EPDD:
                stats.put(pdd, o.nOption);
                break;
            case IndiePADR:
                stats.put(padR, o.nValue);
                break;
            case IndieMADR:
                stats.put(madR, o.nValue);
                break;
            case IndiePDDR:
                stats.put(pddR, o.nValue);
                break;
            case IndieMHP:
                stats.put(mhp, o.nValue);
                break;
            case IndieMHPR:
            case MaxHP:
            case IncMaxHP:
                stats.put(mhpR, o.nOption);
                break;
            case IndieMMP:
            case MaxMP:
            case IncMaxMP:
                stats.put(mmpR, o.nOption);
                break;
            case IndieMMPR:
                stats.put(mmpR, o.nValue);
                break;
            case IndieACC:
                stats.put(acc, o.nValue);
                break;
            case ACC:
                stats.put(acc, o.nOption);
                break;
            case ACCR:
                stats.put(accR, o.nOption);
                break;
            case IndieEVA:
                stats.put(eva, o.nValue);
                break;
            case EVA:
                stats.put(eva, o.nOption);
                break;
            case IndieEVAR:
                stats.put(evaR, o.nValue);
                break;
            case EVAR:
            case RWMovingEvar:
                stats.put(evaR, o.nOption);
                break;
            case Speed:
                stats.put(speed, o.nOption);
                break;
            case IndieSpeed:
                stats.put(speed, o.nValue);
                break;
            case IndieJump:
                stats.put(jump, o.nValue);
                break;
            case Jump:
                stats.put(jump, o.nOption);
                break;
            case IndieAllStat:
                stats.put(str, o.nValue);
                stats.put(dex, o.nValue);
                stats.put(inte, o.nValue);
                stats.put(luk, o.nValue);
                break;
            case IndieDodgeCriticalTime:
            case IndieCr:
                stats.put(cr, o.nValue);
                break;
            case CriticalGrowing:
            case EnrageCr:
            case CriticalBuff:
            case ItemCritical:
                stats.put(cr, o.nOption);
                break;
            case SharpEyes:
                // Combination of Cr, CrDmg, Ied
                stats.put(cr, o.xOption);
                stats.put(crDmg, o.yOption);
                stats.put(ied, o.mOption);
                break;
            case Enrage:
                stats.put(fd, o.xOption); // fd
                break;
            case EnrageCrDamMin:
            case SoulGazeCriDamR:
            case BullsEye:
                stats.put(crDmg, o.nOption);
                break;
            case IndieCrDmg:
                stats.put(crDmg, o.nValue);
                break;
            case IndieEXP:
            case IndieRelaxEXP:
                stats.put(expR, o.nValue);
                break;
            case HolySymbol:
            case ExpBuffRate:
            case CarnivalExp:
            case PlusExpRate:
                stats.put(expR, o.nOption);
                break;
            case IndieBooster:
                stats.put(booster, o.nValue);
                break;
            case Booster:
            case PartyBooster:
            case HayatoBooster:
                stats.put(booster, o.nOption);
                break;
            case STR:
                stats.put(str, o.nOption);
                break;
            case IndieSTR:
                stats.put(str, o.nValue);
                break;
            case IndieDEX:
                stats.put(dex, o.nValue);
                break;
            case IndieINT:
                stats.put(inte, o.nValue);
                break;
            case IndieLUK:
                stats.put(luk, o.nValue);
                break;
            case IndieStatR:
            case IndieAllStatR:
                stats.put(strR, o.nValue);
                stats.put(dexR, o.nValue);
                stats.put(intR, o.nValue);
                stats.put(lukR, o.nValue);
                break;
            case IndieDamR:
                stats.put(damR, o.nValue);
                break;
            case DamR:
            case BeastFormDamageUp:
                stats.put(damR, o.nOption);
                break;
            case IndieMDF:
                stats.put(mdf, o.nValue);
                break;
            case IndiePMdR:
                stats.put(fd, o.nValue);
                break;
            case IndieAsrR:
                stats.put(asr, o.nValue);
                break;
            case AsrR:
            case AsrRByItem:
            case IncAsrR:
                stats.put(asr, o.nOption);
                break;
            case IndieTerR:
                stats.put(ter, o.nValue);
                break;
            case TerR:
            case IncTerR:
                stats.put(ter, o.nOption);
                break;
            case IndieBDR:
                stats.put(bd, o.nValue);
                break;
            case Preparation:
            case BdR:
                stats.put(bd, o.nOption);
                break;
            case IndieStance:
                stats.put(stance, o.nValue);
                break;
            case IndieIgnoreMobpdpR:
                stats.put(ied, o.nValue);
                break;
            case BasicStatUp:
                // TODO what exactly does this give?
                break;
            case Stance:
                stats.put(stance, o.nOption);
                break;
            case AdvancedBless:
                // TODO: Advanced bless stat
                break;
            case DropRIncrease:
            case DropRate:
            case IndiePartyDrop:
                stats.put(dropR, o.nOption);
                break;
            case ItemUpByItem:
                stats.put(dropRMulti, o.nOption);
                break;
            case MesoUp:
                stats.put(mesoR, o.nOption);
                break;
            case MesoUpByItem:
                stats.put(mesoRMulti, o.nOption);
                break;
            case EMHP:
//            case BeastFormMaxHP:
                stats.put(mhp, o.nOption);
                break;
            case EMMP:
                stats.put(mmp, o.nOption);
                break;
            case Bless:
                // TODO
                break;
            case IndieScriptBuff:
                stats.put(buffTimeR, o.nValue);
                break;
            case RhoAias:
            case DamageReduce:
            case DamAbsorbShield:
            case ReduceHitDmgOnlyHPR:
            case Jinsoku:
            case HowlingDefence:
                stats.put(dmgReduce, o.nOption);
                break;
            case IndieHitDamageInclHPR:
                stats.put(dmgReduce, -o.nValue); // IndieHitDmgInclHPR  increases hit dmg if nValue is positive
                break;
            case IndieDamReduceR:
                stats.put(dmgReduce, o.nValue);
                break;
            case IndieDrainHP:
                stats.put(hpDrain, o.nValue);
                break;
            case ComboDrain:
                stats.put(hpDrain, o.nOption);
                break;
            case ManaReflection:
                stats.put(costmpR, o.xOption);
                break;
            case MagicGuard:
                stats.put(magicGuard, o.nOption);
                break;
            case ElementalReset:
                stats.put(eleAttrResistance, o.nOption);
                break;
            case IndieCooltimeReduce:
                stats.put(reduceCooltimeR, o.nValue);
                break;
            case ComboCounter:
                ToBaseStat.comboCounter(chr, o, stats);
                break;
            case BowMasterConcentration:
                ToBaseStat.focusedFury(chr, o, stats);
                break;
            case IndieArcaneForce:
                stats.put(arc, o.nValue);
                break;

				
            default:
                stats.put(unk, o.nOption);
        }
        return stats;
    }

    public Stat toStat() {
        switch(this) {
            case str:
                return Stat.str;
            case dex:
                return Stat.dex;
            case inte:
                return Stat.inte;
            case luk:
                return Stat.luk;
            case mhp:
                return Stat.mhp;
            case mmp:
                return Stat.mmp;
            default:
                return null;
        }
    }

    public boolean isNonAdditiveStat() {
        return this == fd || this == ied;
    }
}
