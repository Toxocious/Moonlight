package net.swordie.ms.constants;
import net.swordie.ms.life.mob.skill.MobSkillID;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.util.container.Tuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BossConstants {

    // GENERAL BOSS CONSTANTS
    public final static int DR_GRACE_PERIOD = 2000;
    public static final int MOB_CONSUME_DELAY = 2500;


    public static final Map<Integer, Map<Integer, ArrayList<Integer>>> BLOCKED_SKILLS = new HashMap<Integer, Map<Integer, ArrayList<Integer>>>() {{
        /*put(CHAOS_PIERRE, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.Summon2.getVal(), new ArrayList<Integer>() {{
                add(40);
            }});
        }});

        put(NORMAL_VON_BON, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.AreaTimezone.getVal(), new ArrayList<Integer>() {{
                add(1);
                add(2);
            }});
        }});
        put(CHAOS_VON_BON, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.AreaTimezone.getVal(), new ArrayList<Integer>() {{
                add(1);
                add(2);
            }});
        }});

        put(CRIMSON_QUEEN_REFLECT_FACE, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.Undead.getVal(), new ArrayList<Integer>() {{
                add(18);
            }});
        }});
        put(CHAOS_CRIMSON_QUEEN_REFLECT_FACE, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.Undead.getVal(), new ArrayList<Integer>() {{
                add(17);
            }});
        }});

        put(EASY_CYGNUS, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.HealM.getVal(), new ArrayList<Integer>() {{
                add(43);
            }});
        }});
        put(NORMAL_CYGNUS, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.HealM.getVal(), new ArrayList<Integer>() {{
                add(43);
            }});
        }});*/

        put(9450020, new HashMap<Integer, ArrayList<Integer>>() {{ // Mob before P.No
            put(MobSkillID.Teleport.getVal(), new ArrayList<Integer>() {{
                add(27);
            }});

            put(MobSkillID.VampDeath.getVal(), new ArrayList<Integer>() {{
                add(2);
            }});

            put(MobSkillID.CastingBar.getVal(), new ArrayList<Integer>() {{
                add(9);
            }});
        }});

        put(9450022, new HashMap<Integer, ArrayList<Integer>>() {{
            put(MobSkillID.Teleport.getVal(), new ArrayList<Integer>() {{
                add(25);
                add(27);
            }});
        }});

        put(8190003, new HashMap<Integer, ArrayList<Integer>>() {{ // Leafre Skelegons
            put(MobSkillID.PGuardUpM.getVal(), new ArrayList<Integer>() {{
                add(4);
            }});
        }});

        put(9390016, new HashMap<Integer, ArrayList<Integer>>() {{ // DDS3 Horrendous Skelegon
            put(MobSkillID.PGuardUpM.getVal(), new ArrayList<Integer>() {{
                add(4);
            }});
        }});


    }};

    public static boolean isBlockedSkill(int mTemplateID, int sID, int sLV) {
        return BLOCKED_SKILLS.containsKey(mTemplateID) && BLOCKED_SKILLS.get(mTemplateID).containsKey(sID) && BLOCKED_SKILLS.get(mTemplateID).get(sID).contains(sLV);
    }

    public static final Map<Integer, Integer> SUMMON_SPAWN_CAST_DELAY = new HashMap<Integer, Integer>() {{
        put(ALLURING_MIRROR, 2500);
        put(CHAOS_ALLURING_MIRROR, 1500);
    }};

    public static int getSummonSkillDelay(int mTemplateID) {
        return SUMMON_SPAWN_CAST_DELAY.getOrDefault(mTemplateID, 0);
    }


//  BALROG -------------------------------------------------------------------------------------------------------------
//  Find all Balrog related scripts by searching = # BALROG BOSS SCRIPT

    // OBSTACLE ATOMS
    public static final int BALROG_OBSTACLE_ATOM_VELOCITY = 15;             // Obstacle Atom Velocity
    public static final int BALROG_PURPLE_ATOM_EXECUTION_DELAY = 1000;      // Obstacle Atom Execution Delay
    public static final int BALROG_PURPLE_ATOM_AMOUNT = 4;                  // Max Obstacle Atoms Spawned Per Call
    public static final int BALROG_PURPLE_ATOM_PROP = 35;                   // Obstacle Atom Percent Spawn Chance
    public static final int BALROG_PURPLE_ATOM_DAMAGE = 25;                 // Obstacle Atom Percent HP Damage

    // GENERAL BALROG CONSTANTS
    public static final int BALROG_ENTRY_MAP = 105100100;                   // Balrog Entry Map
    public static final short BALROG_TIME_LIMIT = 1800;                     // Balrog Instance Time Limit
    public static final short BALROG_SPAWN_X = 412;                         // Balrog X Spawn Position
    public static final short BALROG_SPAWN_Y = 258;                         // Balrog Y Spawn Position
    public static final int BALROG_LEFT_ARM = 8830001;                      // Balrog Universal Left Arm Template ID
    public static final int BALROG_RIGHT_ARM = 8830002;                     // Balrog Universal Right Arm Template ID
    public static final int BALROG_FAKE_LEFT_ARM = 8830004;                 // Balrog Universal Fake Left Arm Template ID
    public static final int BALROG_FAKE_RIGHT_ARM = 8830005;                // Balrog Universal Fake Right Arm Template ID
    public static final long BALROG_COOLDOWN = 21600000;                    // Balrog Universal Cooldown Time in Mils


    // NORMAL BALROG CONSTANTS
    public static final Integer[] NORMAL_BALROG_IDS = {                     // All Normal Balrog Boss Template ID's
            8830010, 8830000, 8830001, 8830002, 8830004, 8830005,
    };

    public static final int BALROG_NORMAL_TREASURE_THIEF = 9402045;         // Normal Balrog Loot Mob
    public static final int BALROG_NORMAL_TREASURE_THIEF_HP = 1000000;      // Normal Balrog Loot Mob HP

    public static final int BALROG_NORMAL_BATTLE_MAP = 105100400;           // Normal Balrog Battle Map
    public static final int BALROG_NORMAL_WIN_MAP = 105100401;              // Normal Balrog Victory Map

    public static final int BALROG_NORMAL_BODY_HP = 3600000;                // Normal Balrog Body HP
    public static final int BALROG_NORMAL_ARM_HP = 1800000;                 // Normal Balrog Arm HP

    public static final int BALROG_NORMAL_DAMAGE_SINK = 8830010;            // Normal Balrog Damage Sink Template ID
    public static final int BALROG_NORMAL_BODY = 8830007;                   // Normal Balrog Body Template ID

    // MYSTIC BALROG CONSTANTS
    public static final Integer[] MYSTIC_BALROG_IDS = {                     // All Mystic Balrog Boss Template ID's
            8830010, 8830000, 8830001, 8830002, 8830004, 8830005,
    };

    public static final int BALROG_MYSTIC_TREASURE_THIEF = 9402046;         // Mystic Balrog Loot Mob
    public static final int BALROG_MYSTIC_TREASURE_THIEF_HP = 100000000;    // Mystic Balrog Loot Mob HP

    public static final int BALROG_MYSTIC_BATTLE_MAP = 105100300;           // Mystic Balrog Battle Map
    public static final int BALROG_MYSTIC_WIN_MAP = 105100301;              // Mystic Balrog Victory Map

    public static final long BALROG_MYSTIC_BODY_HP = 180000000000L;         // Mystic Balrog Body HP
    public static final long BALROG_MYSTIC_ARM_HP = 90000000000L;           // Mystic Balrog Arm HP

    public static final int BALROG_MYSTIC_DAMAGE_SINK = 8830010;            // Mystic Balrog Damage Sink Template ID
    public static final int BALROG_MYSTIC_BODY = 8830000;                   // Mystic Balrog Body Template ID

    //  ZAKUM ----------------------------------------------------------------------------------------------------------
    //  Find all Zakum related scripts by searching = # ZAKUM BOSS SCRIPT

    // GENERAL ZAKUM CONSTANTS
    public static final int ZAKUM_ENTRY_MAP = 221042400;                    // Zakum Entry Map
    public static final short ZAKUM_TIME_LIMIT = 1800;                      // Zakum Instance Time Limit
    public static final long ZAKUM_COOLDOWN = 21600000;                     // Zakum Easy/Normal Cooldown Time in Mils
    public static final int ZAKUM_JQ_MAP_1 = 280020000;                     // Zakum First Jump Quest Map
    public static final int ZAKUM_JQ_MAP_2 = 280020001;                     // Zakum Second Jump Quest Map
    public static final int ZAKUM_DOOR_TO_ENTRANCE = 211042300;             // Zakum Door Entrance Map

    // EASY ZAKUM CONSTANTS
    public static final int EASY_ZAKUM_MAP = 280030200;                     // Easy Zakum Battle Map

    // NORMAL ZAKUM CONSTANTS
    public static final int NORMAL_ZAKUM_MAP = 280030100;                   // Normal Zakum Battle Map

    // CHAOS ZAKUM CONSTANTS
    public static final int CHAOS_ZAKUM_MAP = 280030000;                    // Chaos Zakum Battle Map
    public static final int CHAOS_ZAKUM_COOLDOWN = 43200000;                // Chaos Zakum Cooldown Time in Mils
    public static final long ZAKUM_CHAOS_BODY_HP = 360000000000L;           // Chaos Zakum Body HP
    public static final long ZAKUM_CHAOS_ARM_HP = 15000000000L;             // Chaos Zakum Arm HP

    // ZAKUM ARM CONSTANTS
    public static final int[] ZAKUM_ARMS = {                                // Array of All Zakum Arm Template ID's
            // Normal
            8800003, 8800004, 8800005, 8800006, 8800007, 8800008, 8800009, 8800010,
            // Easy
            8800023, 8800024, 8800025, 8800026, 8800027, 8800028, 8800029, 8800030,
            // Chaos
            8800103, 8800104, 8800105, 8800106, 8800107, 8800108, 8800109, 8800110,
    };

    public static final Integer[] EASY_ZAKUM_ARMS = {                       // Array of All Easy Zakum Arm Template ID's
            8800023, 8800024, 8800025, 8800026, 8800027, 8800028, 8800029, 8800030,
    };

    public static final Integer[] NORMAL_ZAKUM_ARMS = {                     // Array of All Norm Zakum Arm Template ID's
            8800003, 8800004, 8800005, 8800006, 8800007, 8800008, 8800009, 8800010,
    };

    public static final Integer[] CHAOS_ZAKUM_ARMS = {                     // Array of All Chaos Zakum Arm Template ID's
            8800103, 8800104, 8800105, 8800106, 8800107, 8800108, 8800109, 8800110,
    };

    public static final int EASY_ZAKUM_ARM_SLAM_CHANCE = 30;               // The Chance of Zakum using OHK Platform AOE
    public static final int EASY_ZAKUM_ARM_SLAM_INTERVAL = 4000;           // The Time in Between Slam Attacks

    public static final int NORMAL_ZAKUM_ARM_SLAM_CHANCE = 45;             // The Chance of Zakum using OHK Platform AOE
    public static final int NORMAL_ZAKUM_ARM_SLAM_INTERVAL = 3500;         // The Time in Between Slam Attacks

    public static final int CHAOS_ZAKUM_ARM_SLAM_CHANCE = 60;              // The Chance of Zakum using OHK Platform AOE
    public static final int CHAOS_ZAKUM_ARM_SLAM_INTERVAL = 3000;          // The Time in Between Slam Attacks

    // ROOT ABYSS -- GENERAL
    public static final int ROOTABYSS_TIME = 1800;

    // CRIMSON QUEEN ---------------------------------------------------------------------------------------------------

    // GENERAL CRIMSON QUEEN CONSTANTS
    public static final int ALLURING_MIRROR = 8920105;
    public static final int CHAOS_ALLURING_MIRROR = 8920005;

}
