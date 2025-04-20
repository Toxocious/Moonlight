package net.swordie.ms.constants;

/**
 * @author Sjonnie
 * Created on 8/19/2018.
 */
public class QuestConstants {
    public static final int WILD_HUNTER_JAGUAR_STORAGE_ID = 23008;
    public static final int WILD_HUNTER_JAGUAR_CHOSEN_ID = 23009;
    public static final int FIFTH_JOB_QUEST = 1465;
    public static final int NODESHARD_COUNT = 1477;
    public static final int PQPOINT_COUNT = 7907;
    public static final int DOJO_COUNT = 3887;
    public static final int CARTAS_PEARL = 7784; // ElfEarRecord
    public static final int INTENSE_POWER_CRYSTAL_COUNT = 1068;
    public static final int MVP_MILEAGE = 5;
    public static final int DIMENSION_LIBRARY = 32600;
    public static final int SKILL_COMMAND_LOCK_ARK = 1544;
    public static final int SKILL_COMMAND_LOCK_ARAN = 21770;
    public static final int DAMAGE_SKIN = 7291;
    public static final int TOWER_CHAIR = 7266;

    public static final int PVAC_DATA = 9000002;

    public static final int VIOLET_CUBE_INFO = 52998;

    public static final int SHAPESHIFT_QR = 7786;

    public static final int UNION_START_QUEST = 16013;
    public static final int UNION_MAP = 16014;
    public static final int UNION_COIN = 18098; // lastTime=YYMMDDHHMMSS;coin=x
    public static final int UNION_RANK = 18771; // rank=XXX (101 is default, check Etc.wz/mapleUnion/unionRank. 1st digit is top node, 3rd is child node, 2nd digit always seems to be empty.
    public static final int UNION_RAID = 18790; // mod=x;lastTime=YYMMDDHHMMSS;damage=x;coin=x idk what mob is
    public static final int UNION_QUEST = 18793; // q0=0;q1=0;pq=0;q2=0;q1Date=YY/MM/DD;pqDate=YY/MM/DD;q2Date=YY/MM/DD
    public static final int UNION_SYNERGY_BOARD = 18791; // X=Y; repeated 8 times, 0 <= x & y <= 7
    public static final int UNION_PRESET = 500630; // presetNo=n, 0~4

    public static final int SILENT_CRUSADE_WANTED_TAB_1 = 1648;
    public static final int SILENT_CRUSADE_WANTED_TAB_2 = 1649;
    public static final int SILENT_CRUSADE_WANTED_TAB_3 = 1650;
    public static final int SILENT_CRUSADE_WANTED_TAB_4 = 1651;

    public static final int MEDAL_REISSUE_QUEST = 29949;
    public static final int ZERO_SET_QUEST = 41907;
    public static final int ZERO_WEAPON_WINDOW_QUEST = 40905;

    public static final int ZERO_DATA = 9000001;

    public static String getWhStorageQuestValByTemplateID(int templateId) {
        if (templateId >= 9304000 && templateId <= 9304008) {
            return String.valueOf((templateId % 10) + 1);
        }
        return null;
    }

    public static boolean isSpamQuest(String scriptName) {
        return scriptName.contains("63360") || scriptName.contains("7707") || scriptName.contains("36102");
    }
}
