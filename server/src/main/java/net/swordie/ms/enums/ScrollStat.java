package net.swordie.ms.enums;

import java.util.Arrays;

import static net.swordie.ms.enums.EquipBaseStat.*;

/**
 * Created on 1/9/2018.
 */
public enum ScrollStat {
    success,
    incSTR,
    incDEX,
    incINT,
    incLUK,
    incPAD,
    incMAD,
    incPDD,
    incMDD,
    incACC,
    incEVA,
    incMHP,
    incMMP,
    incSpeed,
    incJump,
    incIUC,
    incPERIOD,
    incReqLevel,
    reqRUC,
    randOption,
    randStat,
    tuc,
    speed,
    forceUpgrade,
    cursed,
    maxSuperiorEqp,
    noNegative,
    incRandVol,
    reqEquipLevelMax,
    createType,
    optionType,
    recover,
    reset,
    perfectReset;

    public static ScrollStat getScrollStatByString(String name) {
        return Arrays.stream(values()).filter(ss -> ss.toString().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static EquipBaseStat[] getRandStats() {
        return new EquipBaseStat[]{iStr, iDex, iInt, iLuk, iMaxHP, iMaxMP, iPAD, iMAD, iDEF};
    }

    public EquipBaseStat getEquipStat() {
        switch(this) {
            case incSTR:
                return iStr;
            case incDEX:
                return iDex;
            case incINT:
                return iInt;
            case incLUK:
                return iLuk;
            case incPAD:
                return iPAD;
            case incMAD:
                return iMAD;
            case incPDD:
                return iDEF;
            case incMHP:
                return iMaxHP;
            case incMMP:
                return iMaxMP;
            case incSpeed:
            case speed:
                return iSpeed;
            case incJump:
                return iJump;
            case incReqLevel:
                return iReduceReq;
            default:
                return null;
        }
    }
}
