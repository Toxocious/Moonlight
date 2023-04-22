package net.swordie.ms.constants;

import net.swordie.ms.Server;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.avatar.AvatarData;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.UserPool;
import net.swordie.ms.discord.DiscordWebhook;
import net.swordie.ms.enums.EnchantStat;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;

import java.util.Arrays;
import java.util.List;

public class CustomConstants {
    //Buffed mobs
    public static final int BUFFED_MOB_HP_MULTIPLIER = 1000;
    public static final int BUFFED_MOB_SCALE = 120; //Default scale is 100
    public static final int BUFFED_MOB_DAMAGE_MULTIPLIER = 10;

    public static final boolean AUTO_AGGRO = false;
    public static final int EXTRA_MOB_SPEED = 80;
    public static final int AFK_TIMEOUT = 1000 * 300; //5 minutes
    public static final int DOJO_MAPS_PREFFIX = 925;
    public static final int COMMERCI_MAPS_PREFFIX = 865000;
    public static final int MIN_LEVEL_FOR_PQ = 120;
    public static final boolean MAX_SKILLS = false;

    public static final int EXTRA_NX_PERC_FOR_FIRST_PT_MEMBER = 10;
    public static final double EXTRA_NX_PERC_FOR_PT_MEMBER = 2.5;
    public static final int EXTRA_EXP_PERC_FOR_FIRST_PT_MEMBER = 50;
    public static final double EXTRA_EXP_PERC_FOR_PT_MEMBER = 10;

    public static final double PARTY_LEECHER_NX_PCT = 0.15;

    public static final boolean LEVEL_UP_SKILL_DISABLED = false;
    public static final boolean DISABLE_HITOKIRI_STRIKE_1_HIT = true;
    public static final boolean IGNORE_BULLET_CON = false; //just until recharging fixed

    public static final boolean TITAN_FORGING = true;
    public static final boolean RANDOM_ADD_NEB = true;

    public static final boolean DISABLE_INNO_CSS_SPELL_TRACE = true;

    public static final boolean AUTOMATIC_CHARACTER_CARDS = true;


    /* -------- PART OF DROP/MESO CAP -------- */
    public static final int MAX_MESODROP_RATE_PER_EQUIP = 50;
    public static final int MAX_MESODROP_RATE_ACROSS_EQUIPS_PRE_250 = 120;
    public static final int MAX_MESODROP_RATE_ACROSS_EQUIPS_POST_250_BASE = 215;
    public static final int MAX_MESODROP_RATE_ACROSS_EQUIPS_POST_250_RATE = 25;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_200 = 200;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_225 = 300;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_250 = 400;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_251 = 500;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_252 = 520;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_253 = 540;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_254 = 560;
    public static final int MAX_MESODROP_RATE_BELOW_LEVEL_255 = 580;
    public static final int MAX_MESODROP_RATE = 600;

    public static double getDojoPointsMultiplierByMapID(int mapId) {
        switch (mapId) {
            case 925070100:
                return 0.45;
            case 925070200:
                return 0.45;
            case 925070300:
                return 0.45;
            case 925070400:
                return 0.65;
            case 925070500:
                return 1.25;
            case 925070600:
                return 2.16;
            default:
                return 1;
        }
    }

    public static double getNxMultiplierForDojoByMapID(int mapId) {
        switch (mapId) {
            case 925070100:
                return 1.2;
            case 925070200:
                return 1.2;
            case 925070300:
                return 1.2;
            case 925070400:
                return 1;
            case 925070500:
                return 1;
            case 925070600:
                return 0.8;
            default:
                return 0.3;
        }
    }

    public static boolean isNoNxMap(int mapId) { return mapId / 1000000 == DOJO_MAPS_PREFFIX && mapId < 925100000 || mapId == 706041450;}

    public static boolean isDojoMap(int mapId) { return mapId / 1000000 == DOJO_MAPS_PREFFIX && mapId < 925100000; }

    public static boolean isKritiasInvasionMap(int mapId) { return mapId >= 241010000 && mapId < 241019999; }

    public static boolean isKritiasInvasionBossMap(int mapId) { return mapId >= 241010100 && mapId < 241010150; }

    public static boolean isKritiasNoDropMap(int mapId) { return mapId >= 241010000 && mapId < 241010099 || mapId >= 241010151 && mapId < 241019999; }

    public static boolean isCommerciMap(int mapId) { return mapId / 1000 == COMMERCI_MAPS_PREFFIX && mapId < 925100000; }

    public static boolean aggroException (int mapId) {
        switch (mapId) {
            case GameConstants.MOON_BUNNY_PQ_MAP:
                return true;
            default:
                return false;
        }
    }

    public static boolean isNoDropsMap(int mapId) {
        return isDojoMap(mapId) || mapId == GameConstants.NETTS_PYRAMID_PQ_MAP || isCommerciMap(mapId) || isEvoMap(mapId) || mapId == 990000500 ||
                mapId >= 322000000 && mapId < 329050000 || isKritiasNoDropMap(mapId);
    }

    public static boolean isNoDropsMob(int templateId) {
        switch (templateId) {
            case 8920004:
            case 8920104:
                return true;
            default:
                return false;
        }
    }

    public static final int SOUL_RIFT_POCKET_ITEM = 1162034;

    public static final int[] NO_SCISSOR_ITEMS = new int[] {

    };

    public static final int[] NO_SHARING_TAG_ITEMS = new int[] {
            SOUL_RIFT_POCKET_ITEM
    };

    public static final int[] YES_SHARING_TAG_ITEMS = new int[] {

    };

    public boolean canConsumeSharingTag(int itemId) {
        return Arrays.stream(YES_SHARING_TAG_ITEMS).anyMatch(i -> i == itemId);
    }

    public static final int[] STARTING_ITEMS= new int[]{
            1004427,1102777,1113169,1012512,1022240,1032250,1122302,1132280,1152175,
            5680260,2430404,2430403,1142359,2023380,5530448,5040004
    };

    public static final int START_MONEY = 300000; //300k

    public static int SAFE_KEY = 0;

    public static boolean isMonsterParkExtremeMap(int mapId) {
        return mapId >= 951000200 && mapId < 951000300;
    }

    public static boolean isPartyQuestMap(int mapId)  {
        return mapId >= 922010000 && mapId < 922020000 || mapId >= 925000000 && mapId < 925200000
                || mapId >= 92100000 && mapId < 922000000 || mapId >= 933020000 && mapId < 933030000
                || mapId >= 923040100 && mapId < 923050000 || mapId >= 933010000 && mapId < 933020000
                || mapId == 926010100;
    }

    public static boolean isGuildQuestMap(int mapId)  {
        return mapId >= 990000300 && mapId < 990001000;
    }

    public static boolean isEvoMap(int mapId) {
        return mapId >= 957000000 && mapId < 957900000;
    }

    public static void drawPoint(Char chr, Position pos) {
        chr.getField().getChars().stream().filter(chra ->
                chra.getName().equalsIgnoreCase("Top Right")
                        || chra.getName().equalsIgnoreCase("Top Left")
                        || chra.getName().equalsIgnoreCase("Bottom Left")
                        || chra.getName().equalsIgnoreCase("Bottom Right")).forEach(chra -> chr.write(UserPool.userLeaveField(chra)));

        Char other1 = new Char();
        int id1 = -Integer.MAX_VALUE + 1;
        other1.setId(id1);
        other1.setPosition(pos);
        other1.setMoveAction(chr.getMoveAction());
        // other1.setFoothold(chr.getFoothold());
        other1.setAvatarData(new AvatarData());
        other1.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
        CharacterStat oldCs1 = chr.getAvatarData().getCharacterStat();
        CharacterStat cs1 = new CharacterStat();
        cs1.setCharacterId(id1);
        cs1.setCharacterIdForLog(id1);
        cs1.setName("Top Right");
        cs1.setJob(chr.getJob());
        cs1.setLevel(chr.getLevel());
        cs1.setGender(oldCs1.getGender());
        cs1.setFace(oldCs1.getFace());
        cs1.setHair(oldCs1.getHair());
        cs1.setSkin(oldCs1.getSkin());
        other1.setJobHandler(chr.getJobHandler());
        other1.setTemporaryStatManager(chr.getTemporaryStatManager());
        other1.getAvatarData().setCharacterStat(cs1);
        other1.setField(chr.getField());
        chr.setCopy(other1);
        chr.write(UserPool.userLeaveField(other1));
        chr.write(UserPool.userEnterField(other1));
    }

    public static void drawRectAroundChr(Char chr, Rect rect) {
        Char other1 = new Char();
        int id1 = -Integer.MAX_VALUE + 1;
        other1.setId(id1);
        other1.setPosition(new Position(rect.getRight(), rect.getTop()));
        other1.setMoveAction(chr.getMoveAction());
        // other1.setFoothold(chr.getFoothold());
        other1.setAvatarData(new AvatarData());
        other1.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
        CharacterStat oldCs1 = chr.getAvatarData().getCharacterStat();
        CharacterStat cs1 = new CharacterStat();
        cs1.setCharacterId(id1);
        cs1.setCharacterIdForLog(id1);
        cs1.setName("Top Right");
        cs1.setJob(chr.getJob());
        cs1.setLevel(chr.getLevel());
        cs1.setGender(oldCs1.getGender());
        cs1.setFace(oldCs1.getFace());
        cs1.setHair(oldCs1.getHair());
        cs1.setSkin(oldCs1.getSkin());
        other1.setJobHandler(chr.getJobHandler());
        other1.setTemporaryStatManager(chr.getTemporaryStatManager());
        other1.getAvatarData().setCharacterStat(cs1);
        other1.setField(chr.getField());
        chr.setCopy(other1);
        chr.write(UserPool.userLeaveField(other1));
        chr.write(UserPool.userEnterField(other1));

        Char other2 = new Char();
        int id2 = -Integer.MAX_VALUE + 2;
        other2.setId(id2);
        other2.setPosition(new Position(rect.getLeft(), rect.getTop()));
        other2.setMoveAction(chr.getMoveAction());
        // other2.setFoothold(chr.getFoothold());
        other2.setAvatarData(new AvatarData());
        other2.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
        CharacterStat oldCs2 = chr.getAvatarData().getCharacterStat();
        CharacterStat cs2 = new CharacterStat();
        cs2.setCharacterId(id2);
        cs2.setCharacterIdForLog(id2);
        cs2.setName("Top Left");
        cs2.setJob(chr.getJob());
        cs2.setLevel(chr.getLevel());
        cs2.setGender(oldCs2.getGender());
        cs2.setFace(oldCs2.getFace());
        cs2.setHair(oldCs2.getHair());
        cs2.setSkin(oldCs2.getSkin());
        other2.setJobHandler(chr.getJobHandler());
        other2.setTemporaryStatManager(chr.getTemporaryStatManager());
        other2.getAvatarData().setCharacterStat(cs2);
        other2.setField(chr.getField());
        chr.setCopy(other2);
        chr.write(UserPool.userLeaveField(other2));
        chr.write(UserPool.userEnterField(other2));

        Char other3 = new Char();
        int id3 = -Integer.MAX_VALUE + 3;
        other3.setId(id3);
        other3.setPosition(new Position(rect.getLeft(), rect.getBottom()));
        other3.setMoveAction(chr.getMoveAction());
        // other3.setFoothold(chr.getFoothold());
        other3.setAvatarData(new AvatarData());
        other3.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
        CharacterStat oldCs3 = chr.getAvatarData().getCharacterStat();
        CharacterStat cs3 = new CharacterStat();
        cs3.setCharacterId(id3);
        cs3.setCharacterIdForLog(id3);
        cs3.setName("Bottom Left");
        cs3.setJob(chr.getJob());
        cs3.setLevel(chr.getLevel());
        cs3.setGender(oldCs3.getGender());
        cs3.setFace(oldCs3.getFace());
        cs3.setHair(oldCs3.getHair());
        cs3.setSkin(oldCs3.getSkin());
        other3.setJobHandler(chr.getJobHandler());
        other3.setTemporaryStatManager(chr.getTemporaryStatManager());
        other3.getAvatarData().setCharacterStat(cs3);
        other3.setField(chr.getField());
        chr.setCopy(other3);
        chr.write(UserPool.userLeaveField(other3));
        chr.write(UserPool.userEnterField(other3));

        Char other4 = new Char();
        int id4 = -Integer.MAX_VALUE + 4;
        other4.setId(id4);
        other4.setPosition(new Position(rect.getRight(), rect.getBottom()));
        other4.setMoveAction(chr.getMoveAction());
        // other4.setFoothold(chr.getFoothold());
        other4.setAvatarData(new AvatarData());
        other4.getAvatarData().setAvatarLook(chr.getAvatarData().getAvatarLook());
        CharacterStat oldCs4 = chr.getAvatarData().getCharacterStat();
        CharacterStat cs4 = new CharacterStat();
        cs4.setCharacterId(id4);
        cs4.setCharacterIdForLog(id4);
        cs4.setName("Bottom Right");
        cs4.setJob(chr.getJob());
        cs4.setLevel(chr.getLevel());
        cs4.setGender(oldCs4.getGender());
        cs4.setFace(oldCs4.getFace());
        cs4.setHair(oldCs4.getHair());
        cs4.setSkin(oldCs4.getSkin());
        other4.setJobHandler(chr.getJobHandler());
        other4.setTemporaryStatManager(chr.getTemporaryStatManager());
        other4.getAvatarData().setCharacterStat(cs4);
        other4.setField(chr.getField());
        chr.setCopy(other4);
        chr.write(UserPool.userLeaveField(other4));
        chr.write(UserPool.userEnterField(other4));
    }

    public static void sendSmegaToDiscord(String medalString, String chrName, String text) {
        if (!ServerConstants.LOCAL_HOST_SERVER && !Server.getInstance().MAINTENANCE_MODE) {
            DiscordWebhook smegaForward = new DiscordWebhook("https://discordapp.com/api/webhooks/663920713568223233/fmzWx9D_9MNOWLu4Q9BtkvxoujOpZbcJY8oJ_rLyyZ8GD75aE9VggsINJSCtGP8WxwR0");
            smegaForward.setUsername(medalString + chrName);
            smegaForward.setContent(text.replace("@", "@\u200B"));
            try {
                smegaForward.execute();
            } catch (java.io.IOException ioe) {
            }
        }
    }

    public static void sendTripleSmegaToDiscord(String medalString, String chrName, List<String> stringList) {
        if (!ServerConstants.LOCAL_HOST_SERVER && !Server.getInstance().MAINTENANCE_MODE) {
            stringList.forEach(s -> {
                DiscordWebhook smegaForward = new DiscordWebhook("https://discordapp.com/api/webhooks/663920713568223233/fmzWx9D_9MNOWLu4Q9BtkvxoujOpZbcJY8oJ_rLyyZ8GD75aE9VggsINJSCtGP8WxwR0");
                smegaForward.setUsername(medalString + chrName);
                smegaForward.setContent(s.replace(medalString, "").replace(chrName, "").replace("@", "@\u200B"));
                try {
                    smegaForward.execute();
                } catch (java.io.IOException ioe) {
                }
            });
        }
    }

    public static void sendAvatarSmegaToDiscord(String chrName, List<String> stringList) {
        sendSmegaToDiscord("", chrName, String.join("", stringList));
    }

    public static OutPacket customHalloweenRanking(String... s) {
        OutPacket outPacket = new OutPacket(OutHeader.HALLOWEEN_CANDY_RANKING_RESULT);
        outPacket.encodeByte(0); // != 0 ?
        outPacket.encodeInt(s.length);
        Arrays.stream(s).forEach(string -> {
            outPacket.encodeShort(0);
            outPacket.encodeString(string);
        });
        return outPacket;
    }
}
