package net.swordie.ms.client.character;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asura on 27-7-2018.
 */
public class MonsterPark {

    private static final Map<Integer, Integer> mobExpHash;
    static {
        mobExpHash = new HashMap<>();
        mobExpHash.put(9800046, 36625); // Roid
        mobExpHash.put(9800047, 39700); // Neo Huroid
        mobExpHash.put(9800045, 42875); // Rurumo
        mobExpHash.put(9800049, 46170); // D. Roy
        mobExpHash.put(9800048, 71730); // Security Camera
        mobExpHash.put(9800050, 97240); // Deet and Roi
        mobExpHash.put(9800051, 53010); // Mossy Snail
        mobExpHash.put(9800052, 56575); // Tree Rod
        mobExpHash.put(9800053, 60225); // Mossy Mushroom
        mobExpHash.put(9800054, 63960); // Primitive Boar
        mobExpHash.put(9800055, 67800); // Stone Bug
        mobExpHash.put(9800056, 215355); // Poison Golem
        mobExpHash.put(9800059, 62080); // Wooden Target Dummy
        mobExpHash.put(9800060, 157185); // Bamboo Warrior
        mobExpHash.put(9800061, 67800); // Grizzly
        mobExpHash.put(9800062, 69730); // Panda
        mobExpHash.put(9800063, 167105); // Tae Roon
        mobExpHash.put(9800064, 76285); // Sage Cat
        mobExpHash.put(9800065, 11580135); // King Sage Cat
        mobExpHash.put(9800066, 241815); // Giant Centipede
        mobExpHash.put(9800150, 71690); // Ginseng Jar
        mobExpHash.put(9800151, 76285); // Bellflower Root
        mobExpHash.put(9800152, 84055); // Mr. Alli
        mobExpHash.put(9800154, 94835); // Kru
        mobExpHash.put(9800153, 193250); // Calico Mack, the Pirate King
        mobExpHash.put(9800155, 97610); // Captain
        mobExpHash.put(9800156, 281085); // Lord Pirate
        mobExpHash.put(9800157, 103125); // Dinogoth
        mobExpHash.put(9800158, 108600); // Dinodon
        mobExpHash.put(9800159, 113960); // Guerrilla Specter
        mobExpHash.put(9800160, 266880); // Magician Specter
        mobExpHash.put(9800161, 119250); // Specter Engineer
        mobExpHash.put(9800162, 124500); // Power Specter
        mobExpHash.put(9800163, 355845); // Reaper Specter
        mobExpHash.put(9800164, 116625); // Harp
        mobExpHash.put(9800165, 121875); // Blood Harp
        mobExpHash.put(9800166, 266880); // Master Harp
        mobExpHash.put(9800167, 127085); // Blue Kentaurus
        mobExpHash.put(9800168, 132225); // Red Kentaurus
        mobExpHash.put(9800170, 288300); // Kentaurus King
        mobExpHash.put(9800169, 137285); // Black Kentaurus
        mobExpHash.put(9800171, 391440); // Griffey
        mobExpHash.put(9800067, 129675); // Dark Klock
        mobExpHash.put(9800068, 134775); // Death Teddy
        mobExpHash.put(9800069, 139800); // Phantom Watch
        mobExpHash.put(9800070, 144785); // Grim Phantom Watch
        mobExpHash.put(9800071, 155475); // Gatekeeper
        mobExpHash.put(9800072, 450135); // Thanatos
        mobExpHash.put(9800172, 142310); // Wild Monkey
        mobExpHash.put(9800173, 150150); // Mama Monkey
        mobExpHash.put(9800174, 160760); // Mean Mama Monkey
        mobExpHash.put(9800175, 348625); // SnowFro the Fruitnificent
        mobExpHash.put(9800176, 171260); // Stone Goblin
        mobExpHash.put(9800177, 176435); // Strong Stone Goblin
        mobExpHash.put(9800178, 508515); // Ganapati
        mobExpHash.put(9800073, 191850); // Overlord A
        mobExpHash.put(9800074, 201975); // Afterlord
        mobExpHash.put(9800075, 445170); // Bergamot
        mobExpHash.put(9800078, 211985); // Maverick Type A
        mobExpHash.put(9800079, 216935); // Maverick Type S
        mobExpHash.put(9800080, 221850); // Imperial Guard
        mobExpHash.put(9800081, 226760); // Royal Guard
        mobExpHash.put(9800082, 662130); // Nibelung
        mobExpHash.put(9800085, 246110); // Werewolf
        mobExpHash.put(9800086, 250875); // Lycanthrope
        mobExpHash.put(9800087, 260360); // Coolie Zombie
        mobExpHash.put(9800088, 269735); // Miner Zombie
        mobExpHash.put(9800089, 279000); // Fire Poison
        mobExpHash.put(9800090, 595585); // Riche
        mobExpHash.put(9800091, 806925); // Ergoth
        mobExpHash.put(9800092, 269735); // Crocky the Gatekeeper
        mobExpHash.put(9800093, 279000); // Reindeer
        mobExpHash.put(9800094, 283610); // Blood Reindeer
        mobExpHash.put(9800095, 288185); // Bearwolf
        mobExpHash.put(9800096, 298010); // Grey Vulture
        mobExpHash.put(9800097, 307835); // Castle Golem
        mobExpHash.put(9800098, 317585); // Prison Guard Boar
        mobExpHash.put(9800099, 943425); // Prison Guard Ani
        mobExpHash.put(9800100, 298010); // Red Dragon Turtle
        mobExpHash.put(9800101, 307835); // Dark Cornian
        mobExpHash.put(9800102, 327260); // Red Wyvern
        mobExpHash.put(9800103, 346500); // Blue Wyvern
        mobExpHash.put(9800104, 365550); // Dark Wyvern
        mobExpHash.put(9800105, 1076355); // Leviathan
        mobExpHash.put(9800106, 346500); // Qualm Monk
        mobExpHash.put(9800107, 365550); // Qualm Guardian
        mobExpHash.put(9800108, 829550); // Dodo
        mobExpHash.put(9800109, 829550); // Lilynouch
        mobExpHash.put(9800110, 375035); // Oblivion Monk Trainee
        mobExpHash.put(9800111, 385910); // Oblivion Guardian
        mobExpHash.put(9800112, 395855); // Chief Oblivion Guardian
        mobExpHash.put(9800113, 1145830); // Lyka
        mobExpHash.put(9800114, 385910); // Official Knight A
        mobExpHash.put(9800115, 390990); // Official Knight B
        mobExpHash.put(9800116, 396280); // Official Knight C
        mobExpHash.put(9800117, 404715); // Official Knight D
        mobExpHash.put(9800118, 412225); // Official Knight E
        mobExpHash.put(9800119, 1186255); // Mihile
        mobExpHash.put(9800120, 1186255); // Oz
        mobExpHash.put(9800121, 588495); // Ifrit
        mobExpHash.put(9800122, 1186255); // Irena
        mobExpHash.put(9800123, 1186255); // Eckhart
        mobExpHash.put(9800124, 1186255); // Hawkeye
        mobExpHash.put(9800179, 417025); // Swollen Stump
        mobExpHash.put(9800180, 419730); // Pillaging Wild Boar
        mobExpHash.put(9800181, 422225); // Pillaging Fire Boar
        mobExpHash.put(9800182, 426565); // Sinister Wooden Mask
        mobExpHash.put(9800183, 428440); // Sinister Rocky Mask
        mobExpHash.put(9800184, 929190); // Ancient Dark Golem
        mobExpHash.put(9800185, 430080); // Swollen Axe Stump
        mobExpHash.put(9800186, 1216460); // Ghostwood Stumpy
    }

    public static int getExpByMobId(int templateId) {
        return mobExpHash.get(templateId) != null ? mobExpHash.get(templateId) : 0;
    }

    public static int getRewardByDay() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY: // Growth Box
                return 2434745;
            case Calendar.MONDAY: // Crafting Box
                return 2434746;
            case Calendar.TUESDAY: // Enhancement Box
                return 2434747;
            case Calendar.WEDNESDAY: // Traits Box
                return 2434748;
            case Calendar.THURSDAY: // Honor Box
                return 2434749;
            case Calendar.FRIDAY: // Mesos Box
                return 2434750;
            case Calendar.SATURDAY: // Monster Park Box
                return 2434751;
            default:
                return 2434745; // Sunday's Growth Box
        }
    }


}
