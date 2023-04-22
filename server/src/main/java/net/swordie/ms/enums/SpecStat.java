package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1/9/2018.
 */
public enum SpecStat {
    runOnPickup,
    consumeOnPickup,
    hp,
    mp,
    hpR,
    mpR,
    eva,
    time,
    speed,
    mad,
    pad,
    acc,
    pdd,
    mdd,
    jump,
    imhp,
    immp,
    indieAllStat,
    indieSpeed,
    indieJump,
    indieSTR,
    indieDEX,
    indieINT,
    indieLUK,
    indiePad,
    indiePdd,
    indieMad,
    indieMdd,
    indieBDR,
    indieDamR,
    indieIgnoreMobpdpR,
    indieStatR,
    indieMhp,
    indieMmp,
    indieBooster,
    indieScriptBuff,
    incEffectHPPotion,
    indieAcc,
    indieEva,
    indieAllSkill,
    indieMhpR,
    indieMmpR,
    indieStance,
    indieForceSpeed,
    indieForceJump,
    indieQrPointTerm,
    indieWaterSmashBuff,
    padRate,
    madRate,
    pddRate,
    mddRate,
    accRate,
    evaRate,
    speedRate,
    mhpR,
    mhpRRate,
    mmpR,
    mmpRRate,
    booster,
    expinc,
    str,
    dex,
    inte,
    luk,
    asrR,
    bdR,
    prob,
    itemupbyitem,
    mesoupbyitem,
    party,
    charismaEXP,
    insightEXP,
    willEXP,
    craftEXP,
    senseEXP,
    charmEXP,
    inflation,
    morph,
    repeatEffect,
    recipe,
    reqSkillLevel,
    mesoAmountRate,
    dropPer,
    expBuff,
    indieExp;

    public static SpecStat getSpecStatByName(String name) {
        if("int".equalsIgnoreCase(name)) {
            return inte;
        }
        return  Arrays.stream(values()).filter(ss ->
                ss.toString().equalsIgnoreCase(name.toLowerCase())).
                findFirst().
                orElse(null);
    }
}
