package net.swordie.ms.constants;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.enums.*;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.pet.PetSkill;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.EtcData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.EquipDrop;
import org.apache.log4j.LogManager;
import net.swordie.ms.util.Util;

import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.enums.InvType.EQUIP;

/**
 * Created on 12/12/2017.
 */
public class ItemConstants {
    public static final int EMPTY_SOCKET_ID = 3;
    public static final short INACTIVE_SOCKET = 0;
    public static final short MIN_LEVEL_FOR_SOUL_SOCKET = 75;
    public static final int SOUL_ENCHANTER_BASE_ID = 2590000;
    public static final int SOUL_ITEM_BASE_ID = 2591000;
    public static final int MAX_SOUL_CAPACITY = 1000;
    public static final int MOB_DEATH_SOUL_MP_COUNT = 150;
    public static final int MOB_CARD_BASE_ID = 2380000;
    public static final int FAMILIAR_PREFIX = 996;
    public static final int SPELL_TRACE_ID = 4001832;
    public static final int RAND_CHAOS_MAX = 5;
    public static final int INC_RAND_CHAOS_MAX = 10;
    public static final int MAX_ARCANE_SYMBOL_LEVEL = 20;

    public static final int GAGAGUCCI = 5680047;

    public static final byte MAX_SKIN = 13;
    public static final int MIN_HAIR = 30000;
    public static final int MAX_HAIR = 69999;
    public static final int MIN_FACE = 20000;
    public static final int MAX_FACE = 59999;

    static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static final int THIRD_LINE_CHANCE = 50;
    public static final int PRIME_LINE_CHANCE = 15;

    public static final int HYPER_TELEPORT_ROCK = 5040004;

    public static final int RED_CUBE = 5062009;
    public static final int BLACK_CUBE = 5062010;
    public static final int OCCULT_CUBE = 2711000;
    public static final int MASTER_CRAFTSMANS_CUBE = 2711003;
    public static final int MEISTERS_CUBE = 2711004;
    public static final int VIOLET_CUBE = 5062024;

    public static final int BONUS_POT_CUBE = 5062500;
    public static final int SPECIAL_BONUS_POT_CUBE = 5062501;
    public static final int WHITE_BONUS_POT_CUBE = 5062503;

    public static final int NEBILITE_BASE_ID = 3060000;

    public static final int HORNTAIL_NECKLACE[] = {
            1122000, // Horntail Necklace
            1122076, // Chaos Horntail Necklace
            1122151, // Chaos Horntail Necklace (+2)
            1122249, // Dream Horntail Necklace
            1122278, // Mystic Horntail Necklace
    };

    public static final List<Byte> WEAPON_TYPES = Arrays.asList(new Byte[] {
            0, 30, 31, 32, 33, 37, 38, 40, 41, 42, 43, 44,
            45, 46, 47, 48, 49, 39, 34, 52, 53, 35, 36,
            21, 22, 54, 55, 23, 24, 56, 57, 25, 26, 58,
            27, 28, 59, 29
    });

    public static final int EXP_2X_COUPON[] = {
            5680484,
            5680342,
            5680275,
            5211122,
            5211121,
            5211120,
            5211049,
            5211048,
            5211047,
            5211046,
            5211000
    };

    public static final short MAX_HAMMER_SLOTS = 2;

    public static final int NODESTONE = 2436324; // 2435719;

    public static boolean isBlockedCSItem(int itemID) {
        switch (itemID) {
            case 1002186:
            case 1032024:
            case 1004109:
            case 1012289:
            case 1022048:
            case 1072153:
            case 1082102:
            case 1092056:
            case 1102039:
            case 1092064:
            case 1092067:
            case 1702099:
            case 1702190:
            case 1702220:
                return true;
            default:
                return false;
        }
    }

    public static ItemGrade getHighestTierForInGameCube(int itemID) {
        // should've got this from 'cursor'?
        switch (itemID) {
            case 2710007:
            case 2711000: // Occult Cube
            case 2711001:
            case 2711009: // Occult Cube (tradeBlock)
            case 2711011: // Occult Cube (accountSharable)
                return ItemGrade.Epic;
            case 2710000:
            case 2710002:
            case 2710005:
            case 2711002:
            case 2711003: // Master Craftsman's Cube
            case 2711005: // Master Craftsman's Cube (tradeBlock)
            case 2711007:
            case 2711008:
            case 2711010:
            case 2711012: // Master Craftsman's Cube (accountSharable)
            case 2711014:
            case 2711015:
            case 2711016:
                return ItemGrade.Unique;
            case 2710003:
            case 2711004: // Meister's Cube
            case 2711006: // Meister's Cube (tradeBlock)
            case 2711013: // Meister's Cube (accountSharable)
                return ItemGrade.Legendary;
        }
        return ItemGrade.Rare;
    }



    private static final Integer[] soulPotList = new Integer[]{32001, 32002, 32003, 32004, 32005, 32006, 32011, 32012, // flat
            32041, 32042, 32043, 32044, 32045, 32046, 32051, 32052}; // rate

    private static Integer[] ssbRotation = new Integer[]{

            //1005457, 1053515, 1103231, 1702969, 1115180, 1115091, 1005403, 1005404, 1053474, 1053475, 1702948, 1103215, 1073381, 1012699, 1005328, 1005329, 1050517, 1051587, 1073343, 1103178, 1702908, 1042379, 1062247, 1005089, 1050479, 1051546, 1702809, 1004487, 1004488, 1050365, 1051435, 1702902, 1053411, 1005416, 1005417, 1053491, 1053492, 1702954, 1005412, 1050531, 1051602, 1073362, 1103219, 1702951, 1005409, 1005410, 1012702, 1012703, 1053447, 1702950

            1003404, 1022027, 1012044, 1050497, 1012645, 1005255, 1053382, 1053381, 1005231, 1005232, 1082693, 1053055, 1004796, 1052587, 1082493, 1802365, 1053082, 1402250, 1042349, 1005325, 1053404, 1073341, 1103176, 1003945, 1042360, 1053109, 1073181, 1082713, 1004862, 1004863, 1053110, 1073182, 1082714, 1005256, 1005257, 1004042, 1103058, 1052923, 1053099, 1004589, 1005353
    };

    private static Integer[] ssbRareRotation = new Integer[]{ // TODO add rare
            1072517, 1050422, 1012009, 1053378, 1053377, 1005276, 1005275, 1004467, 1053351, 1053352, 1051591, 1005353, 1005280, 1005281, 1005015, 1012082, 1004029, 1005167, 1053315, 1062055, 1004503
    };

    private static final int TUC_IGNORE_ITEMS[] = {
            1113231, // Master Ring SS
            1114301, // Reboot Vengeful Ring
            1114302, // Synergy Ring
            1114303, // Cosmos Ring
            1114304, // Reboot Cosmos Ring
            1114305, // Chaos Ring
    };

    public static final int NON_KMS_BOSS_SETS[] = {
            127, // Amaterasu
            128, // Oyamatsumi
            129, // Ame-no-Uzume
            130, // Tsukuyomi
            131, // Susano-o
            315, // Cracked Gollux
            316, // Solid Gollux
            317, // Reinforced Gollux
            318, // Superior Gollux
            328, // Sweetwater
    };

    public static final int NON_KMS_BOSS_ITEMS[] = {
            1032224, // Sweetwater Earrings
            1022211, // Sweetwater Monocle
            1012438, // Sweetwater Tattoo
            1152160, // Sweetwater Shoulder
            1132247, // Sweetwater Belt
            1122269, // Sweetwater Pendant
    };

    // Spell tracing
    private static final int BASE_ST_COST = 30;
    private static final int INNOCENCE_ST_COST = 1337;
    private static final int CLEAN_SLATE_ST_COST = 200;

    // Flames
    public static final double WEAPON_FLAME_MULTIPLIER[] = { 1.0, 2.2, 3.65, 5.35, 7.3, 8.8, 10.25 };
    public static final double WEAPON_FLAME_MULTIPLIER_BOSS_WEAPON[] = { 1.0, 1.0, 3.0, 4.4, 6.05, 8.0, 10.25 }; // Boss weapons do not ever roll stat level 1/2.
    public static final short EQUIP_FLAME_LEVEL_DIVIDER = 40;
    public static final short EQUIP_FLAME_LEVEL_DIVIDER_EXTENDED = 20;

    public static final int EXCEPTIONAL_EX_ALLOWED[] = {
            1152155, // Scarlet Shoulder
            1113015, // Secret Ring
    };

    // Self-made drops per mob
    public static final Map<Integer, Set<DropInfo>> consumableDropsPerLevel = new HashMap<>();
    public static final Map<ItemJob, Map<Integer, Set<DropInfo>>> equipDropsPerLevel = new HashMap<>();

    static {
        initConsumableDrops();
        initEquipDrops();
    }

    private static void initConsumableDrops() {
        consumableDropsPerLevel.put(0, Util.makeSet(
                new DropInfo(2000000, 500), // Red Potion
                new DropInfo(2000003, 500)  // Blue Potion
        ));
        consumableDropsPerLevel.put(20, Util.makeSet(
                new DropInfo(2000002, 500), // White Potion
                new DropInfo(2000006, 500)  // Mana Elixir
        ));
        consumableDropsPerLevel.put(40, Util.makeSet(
                new DropInfo(2001527, 500), // Unagi
                new DropInfo(2022000, 500)  // Pure Water
        ));
        consumableDropsPerLevel.put(60, Util.makeSet(
                new DropInfo(2001527, 500), // Unagi
                new DropInfo(2022000, 500)  // Pure Water
        ));
        consumableDropsPerLevel.put(80, Util.makeSet(
                new DropInfo(2001001, 500), // Ice Cream Pop
                new DropInfo(2001002, 500)  // Pure Water
        ));
        consumableDropsPerLevel.put(100, Util.makeSet(
                new DropInfo(2020012, 500), // Melting Cheese
                new DropInfo(2020013, 500), // Reindeer Milk
                new DropInfo(2020014, 500), // Sunrise Dew
                new DropInfo(2020015, 500), // Sunset Dew
                new DropInfo(2050004, 100)   // All Cure
        ));
    }

    private static void initEquipDrops() {
        List<EquipDrop> drops = (List<EquipDrop>) DatabaseManager.getObjListFromDB(EquipDrop.class);
        for (EquipDrop drop : drops) {
            ItemJob job = drop.getJob();
            int level = drop.getLevel();
            if (!equipDropsPerLevel.containsKey(job)) {
                equipDropsPerLevel.put(job, new HashMap<>());
            }
            Map<Integer, Set<DropInfo>> jobMap = equipDropsPerLevel.get(job);
            if (!jobMap.containsKey(level)) {
                jobMap.put(level, new HashSet<>());
            }
            Set<DropInfo> set = jobMap.get(level);
            int itemId = drop.getId();
            int chance = 100;
            if (ItemConstants.isWeapon(itemId)) {
                chance = 30;
            }
            set.add(new DropInfo(drop.getId(), chance));
        }
    }

    public static int getGenderFromId(int nItemID) {
        int result;

        if (nItemID / 1000000 != 1 && getItemPrefix(nItemID) != 254 || getItemPrefix(nItemID) == 119 || getItemPrefix(nItemID) == 168)
            return 2;
        switch (nItemID / 1000 % 10) {
            case 0:
                result = 0;
                break;
            case 1:
                result = 1;
                break;
            default:
                return 2;
        }
        return result;
    }

    public static int getBodyPartFromItem(int nItemID, int gender) {
        List<Integer> arr = getBodyPartArrayFromItem(nItemID, gender);
        int result = arr.size() > 0 ? arr.get(0) : 0;
        return result;
    }

    public static List<Integer> getBodyPartArrayFromItem(int itemID, int genderArg) {
        int gender = getGenderFromId(itemID);
        EquipPrefix prefix = EquipPrefix.getByVal(getItemPrefix(itemID));
        List<Integer> bodyPartList = new ArrayList<>();
        if (prefix == null || (prefix != EquipPrefix.Emblem && prefix != EquipPrefix.Bit &&
                gender != 2 && genderArg != 2 && gender != genderArg)) {
            return bodyPartList;
        }
        switch (prefix) {
            case Hat:
                bodyPartList.add(BodyPart.Hat.getVal());
                bodyPartList.add(BodyPart.EvanHat.getVal());
                bodyPartList.add(BodyPart.APHat.getVal());
                bodyPartList.add(BodyPart.DUHat.getVal());
                bodyPartList.add(BodyPart.ZeroHat.getVal());
                break;
            case FaceAccessory:
                bodyPartList.add(BodyPart.FaceAccessory.getVal());
                bodyPartList.add(BodyPart.APFaceAccessory.getVal());
                bodyPartList.add(BodyPart.DUFaceAccessory.getVal());
                bodyPartList.add(BodyPart.ZeroFaceAccessory.getVal());
                break;
            case EyeAccessory:
                bodyPartList.add(BodyPart.EyeAccessory.getVal());
                bodyPartList.add(BodyPart.ZeroEyeAccessory.getVal());
                bodyPartList.add(1305);
                break;
            case Earrings:
                bodyPartList.add(BodyPart.Earrings.getVal());
                bodyPartList.add(BodyPart.ZeroEarrings.getVal());
                bodyPartList.add(1306);
                break;
            case Top:
            case Overall:
                bodyPartList.add(BodyPart.Top.getVal());
                bodyPartList.add(BodyPart.APTop.getVal());
                bodyPartList.add(BodyPart.DUTop.getVal());
                bodyPartList.add(BodyPart.ZeroTop.getVal());
                bodyPartList.add(1307);
                break;
            case Bottom:
                bodyPartList.add(BodyPart.Bottom.getVal());
                bodyPartList.add(BodyPart.APBottom.getVal());
                bodyPartList.add(BodyPart.ZeroBottom.getVal());
                bodyPartList.add(1308);
                break;
            case Shoes:
                bodyPartList.add(BodyPart.Shoes.getVal());
                bodyPartList.add(BodyPart.APShoes.getVal());
                bodyPartList.add(BodyPart.ZeroShoes.getVal());
                bodyPartList.add(1309);
                break;
            case Gloves:
                bodyPartList.add(BodyPart.Gloves.getVal());
                bodyPartList.add(BodyPart.APGloves.getVal());
                bodyPartList.add(BodyPart.DUGloves.getVal());
                bodyPartList.add(BodyPart.ZeroGloves.getVal());
                break;
            case Shield:
            case Katara:
            case SecondaryWeapon:
            case Lapis:
                bodyPartList.add(BodyPart.Shield.getVal());
                break;
            case Lazuli:
                bodyPartList.add(BodyPart.Weapon.getVal());
                break;
            case Cape:
                bodyPartList.add(BodyPart.Cape.getVal());
                bodyPartList.add(BodyPart.APCape.getVal());
                bodyPartList.add(BodyPart.DUCape.getVal());
                bodyPartList.add(BodyPart.ZeroCape.getVal());
                break;
            case Ring:
                bodyPartList.add(BodyPart.Ring1.getVal());
                bodyPartList.add(BodyPart.Ring2.getVal());
                bodyPartList.add(BodyPart.Ring3.getVal());
                bodyPartList.add(BodyPart.Ring4.getVal());
                bodyPartList.add(BodyPart.ZeroRing1.getVal());
                bodyPartList.add(BodyPart.ZeroRing2.getVal());
                break;
            case Pendant:
                bodyPartList.add(BodyPart.Pendant.getVal());
                bodyPartList.add(BodyPart.ExtendedPendant.getVal());
                break;
            case Belt:
                bodyPartList.add(BodyPart.Belt.getVal());
                break;
            case Medal:
                bodyPartList.add(BodyPart.Medal.getVal());
                break;
            case Shoulder:
                bodyPartList.add(BodyPart.Shoulder.getVal());
                break;
            case PocketItem:
                bodyPartList.add(BodyPart.PocketItem.getVal());
                break;
            case MonsterBook:
                bodyPartList.add(BodyPart.MonsterBook.getVal());
                break;
            case Badge:
                bodyPartList.add(BodyPart.Badge.getVal());
                break;
            case Emblem:
                bodyPartList.add(BodyPart.Emblem.getVal());
                break;
            case Totem:
                bodyPartList.add(BodyPart.Totem1.getVal());
                bodyPartList.add(BodyPart.Totem2.getVal());
                bodyPartList.add(BodyPart.Totem3.getVal());
                break;
            case MachineEngine:
                bodyPartList.add(BodyPart.MachineEngine.getVal());
                break;
            case MachineArm:
                bodyPartList.add(BodyPart.MachineArm.getVal());
                break;
            case MachineLeg:
                bodyPartList.add(BodyPart.MachineLeg.getVal());
                break;
            case MachineFrame:
                bodyPartList.add(BodyPart.MachineFrame.getVal());
                break;
            case MachineTransistor:
                bodyPartList.add(BodyPart.MachineTransistor.getVal());
                break;
            case Android:
                bodyPartList.add(BodyPart.Android.getVal());
                break;
            case MechanicalHeart:
                bodyPartList.add(BodyPart.MechanicalHeart.getVal());
                break;
            case Bit:
                for (int id = BodyPart.BitsBase.getVal(); id <= BodyPart.BitsEnd.getVal(); id++) {
                    bodyPartList.add(id);
                }
                break;
            case ArcaneSymbol:
                for (int id = 1600; id <= 1605; id++) {
                    bodyPartList.add(id);
                }
                break;
            case PetWear:
                bodyPartList.add(BodyPart.PetWear1.getVal());
                bodyPartList.add(BodyPart.PetWear2.getVal());
                bodyPartList.add(BodyPart.PetWear3.getVal());
                break;
            // case 184: // unknown, equip names are untranslated and google search results in hekaton screenshots
            // case 185:
            // case 186:
            // case 187:
            // case 188:
            // case 189:
            case TamingMob:
                bodyPartList.add(BodyPart.TamingMob.getVal());
                break;
            case Saddle:
                bodyPartList.add(BodyPart.Saddle.getVal());
                break;
            case EvanHat:
                bodyPartList.add(BodyPart.EvanHat.getVal());
                break;
            case EvanPendant:
                bodyPartList.add(BodyPart.EvanPendant.getVal());
                break;
            case EvanWing:
                bodyPartList.add(BodyPart.EvanWing.getVal());
                break;
            case EvanShoes:
                bodyPartList.add(BodyPart.EvanShoes.getVal());
                break;
            default:
                if (ItemConstants.isLongOrBigSword(itemID) || ItemConstants.isWeapon(itemID)) {
                    bodyPartList.add(BodyPart.Weapon.getVal());
                    if(ItemConstants.isFan(itemID)) {
                        bodyPartList.add(BodyPart.HakuFan.getVal());
                    } else {
                        bodyPartList.add(BodyPart.ZeroWeapon.getVal());
                    }
                } else {
                    log.debug("Unknown type? id = " + itemID);
                }
                break;
        }
        return bodyPartList;

    }

    private static int getItemPrefix(int nItemID) {
        return nItemID / 10000;
    }

    private static boolean isLongOrBigSword(int nItemID) {
        return getItemPrefix(nItemID) == EquipPrefix.Lapis.getVal() || getItemPrefix(nItemID) == EquipPrefix.Lazuli.getVal();
    }

    private static boolean isFan(int nItemID) {
        return getItemPrefix(nItemID) == EquipPrefix.Fan.getVal();
    }

    public static WeaponType getWeaponType(int itemID) {
        if (itemID / 1000000 != 1) {
            return null;
        }
        return WeaponType.getByVal(getItemPrefix(itemID) % 100);
    }

    public static boolean isThrowingItem(int itemID) {
        return isThrowingStar(itemID) || isBullet(itemID) || isBowArrow(itemID);
    }

    public static boolean isThrowingStar(int itemID) {
        return getItemPrefix(itemID) == 207;
    }

    public static boolean isBullet(int itemID) {
        return getItemPrefix(itemID) == 233;
    }

    public static boolean isBowArrow(int itemID) {
        return itemID / 1000 == 2060;
    }

    public static boolean isFamiliar(int itemID) {
        return getItemPrefix(itemID) == 287;
    }

    public static boolean isEnhancementScroll(int scrollID) {
        return scrollID / 100 == 20493;
    }

    public static boolean isHat(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Hat.getVal();
    }

    public static boolean isWeapon(int itemID) {
        return itemID >= 1210000 && itemID < 1600000 || itemID / 10000 == 170;
    }

    public static boolean isSecondary(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.SecondaryWeapon.getVal();
    }

    private static boolean isKatara(int itemId) {
        return getItemPrefix(itemId) == EquipPrefix.Katara.getVal();
    }

    public static boolean isShield(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Shield.getVal();
    }

    public static boolean isAccessory(int itemID) {
        return (itemID >= 1010000 && itemID < 1040000) || (itemID >= 1122000 && itemID < 1153000) ||
                (itemID >= 1112000 && itemID < 1113000) || (itemID >= 1670000 && itemID < 1680000);
    }

    public static boolean isFaceAccessory(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.FaceAccessory.getVal();
    }

    public static boolean isEyeAccessory(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.EyeAccessory.getVal();
    }

    public static boolean isEarrings(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Earrings.getVal();
    }

    public static boolean isTop(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Top.getVal();
    }

    public static boolean isOverall(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Overall.getVal();
    }

    public static boolean isBottom(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Bottom.getVal();
    }

    public static boolean isShoe(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Shoes.getVal();
    }

    public static boolean isGlove(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Gloves.getVal();
    }

    public static boolean isCape(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Cape.getVal();
    }


    public static boolean isArmor(int itemID) {
        return !isAccessory(itemID) && !isWeapon(itemID);
    }

    public static boolean isRing(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Ring.getVal();
    }

    public static boolean isPendant(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Pendant.getVal();
    }

    public static boolean isBelt(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Belt.getVal();
    }

    public static boolean isMedal(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Medal.getVal();
    }

    public static boolean isShoulder(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Shoulder.getVal();
    }

    public static boolean isPocketItem(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.PocketItem.getVal();
    }

    public static boolean is2XDropCoupon(int itemId) { return itemId <= 5360057 && itemId >= 5360000; }

    public static boolean isMonsterBook(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.MonsterBook.getVal();
    }

    public static boolean isBadge(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Badge.getVal();
    }

    public static boolean isEmblem(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Emblem.getVal();
    }

    public static boolean isTotem(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Totem.getVal();
    }

    public static boolean isAndroid(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.Android.getVal();
    }

    public static boolean isMechanicalHeart(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.MechanicalHeart.getVal();
    }

    public static boolean isRebirthFlame(int itemId) { return itemId >= 2048700 && itemId < 2048800; }

    public static boolean isNebulite(int itemId) { return getItemPrefix(itemId) == 306; }

    public static boolean isWeaponSlot(int itemId) {
        return isWeapon(itemId) || isSecondary(itemId);
    }

    public static boolean canEquipTypeHavePotential(int itemid) {
        return isRing(itemid) ||
                isPendant(itemid) ||
                isWeapon(itemid) ||
                isBelt(itemid) ||
                isHat(itemid) ||
                isFaceAccessory(itemid) ||
                isEyeAccessory(itemid) ||
                isOverall(itemid) ||
                isTop(itemid) ||
                isBottom(itemid) ||
                isShoe(itemid) ||
                isEarrings(itemid) ||
                isShoulder(itemid) ||
                isGlove(itemid) ||
                isEmblem(itemid) ||
                isBadge(itemid) ||
                isShield(itemid) ||
                isSecondary(itemid) ||
                isCape(itemid) ||
                isMechanicalHeart(itemid);
    }

    public static boolean canEquipHavePotential(Equip equip) {
        return !equip.isCash() &&
                canEquipTypeHavePotential(equip.getItemId()) &&
                !equip.isNoPotential() &&
                (ItemData.getEquipById(equip.getItemId()).getTuc() >= 1 || isTucIgnoreItem(equip.getItemId()));
    }

    public static boolean canEquipHaveFlame(Equip equip) {
        return !equip.isCash() && (isPendant(equip.getItemId()) ||
                (isWeapon(equip.getItemId()) && !isKatara(equip.getItemId()) && !isSecondary(equip.getItemId()) && !isShield(equip.getItemId())) ||
                isBelt(equip.getItemId()) ||
                isHat(equip.getItemId()) ||
                isFaceAccessory(equip.getItemId()) ||
                isEyeAccessory(equip.getItemId()) ||
                isOverall(equip.getItemId()) ||
                isTop(equip.getItemId()) ||
                isBottom(equip.getItemId()) ||
                isShoe(equip.getItemId()) ||
                isEarrings(equip.getItemId()) ||
                Util.makeSet(EXCEPTIONAL_EX_ALLOWED).contains(equip.getItemId()) ||
                isGlove(equip.getItemId()) ||
                isCape(equip.getItemId()) ||
                isPocketItem(equip.getItemId()));
    }

    public static boolean canEquipGoldHammer(Equip equip) {
        Equip defaultEquip = ItemData.getEquipById(equip.getItemId());
        return !(Util.makeSet(HORNTAIL_NECKLACE).contains(equip.getItemId()) ||
                equip.getIuc() >= defaultEquip.getIUCMax() ||
                defaultEquip.getTuc() <= 0); // No upgrade slots by default
    }

    public static boolean isGoldHammer(Item item) {
        return getItemPrefix(item.getItemId()) == 247;
    }

    /**
     * Gets potential tier for a line.
     * Accounts prime lines too.
     * @param line Potential line.
     * @param grade Our current potential grade.
     */
    public static ItemGrade getLineTier(int line, ItemGrade grade) {
        if (line == 0 || Util.succeedProp(PRIME_LINE_CHANCE)) {
            return grade;
        }
        return ItemGrade.getOneTierLower(grade.getVal());
    }

    /**
     * Determines whether a nebulite can be mounted on an equip.
     * @param equip Equip item.
     * @param nebulite The nebulite to mount on the equip.
     */
    public static boolean nebuliteFitsEquip(Equip equip, Item nebulite) {
        int nebuliteId = nebulite.getItemId();
        Map<ScrollStat, Integer> vals = ItemData.getItemInfoByID(nebuliteId).getScrollStats();
        if (vals.size() == 0) {
            return false;
        }
        ItemOptionType type = ItemOptionType.getByVal(vals.getOrDefault(ScrollStat.optionType, 0));
        int equipId = equip.getItemId();
        switch(type) {
            case AnyEquip:
                return true;
            case Weapon: // no emblems for nebs here
                return isWeapon(equipId) || isShield(equipId);
            case AnyExceptWeapon:
                return !isWeapon(equipId) && !isShield(equipId);
            case ArmorExceptGlove:
                return isBelt(equipId) || isHat(equipId) || isOverall(equipId) || isTop(equipId) || isBottom(equipId) || isShoe(equipId) || isCape(equipId);
            case Accessory:
                return isRing(equipId) || isPendant(equipId) || isFaceAccessory(equipId) || isEyeAccessory(equipId) || isEarrings(equipId) || isShoulder(equipId);
            case Hat:
                return isHat(equipId);
            case Top:
                return isTop(equipId) || isOverall(equipId);
            case Bottom:
                return isBottom(equipId) || isOverall(equipId);
            case Glove:
                return isGlove(equipId);
            case Shoes:
                return isShoe(equipId);
            default:
                return false;
        }
    }

    public static List<ItemOption> getOptionsByEquip(Equip equip, boolean bonus, int line) {
        int id = equip.getItemId();
        Collection<ItemOption> data = ItemData.getItemOptions().values();
        ItemGrade grade = getLineTier(line, ItemGrade.getGradeByVal(bonus ? equip.getBonusGrade() : equip.getBaseGrade()));

        // need a list, as we take a random item from it later on
        List<ItemOption> res = data.stream().filter(
                        io -> io.getOptionType() == ItemOptionType.AnyEquip.getVal() &&
                                io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                .collect(Collectors.toList());
        if (isWeapon(id) || isShield(id) || isEmblem(id)) {
            // TODO: block boss% on emblem
            res.addAll(data.stream().filter(
                    io -> io.getOptionType() == ItemOptionType.Weapon.getVal()
                            &&  io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus
            ).collect(Collectors.toList()));
        } else {
            res.addAll(data.stream().filter(
                            io -> io.getOptionType() == ItemOptionType.AnyExceptWeapon.getVal()
                                    && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                    .collect(Collectors.toList()));
            if (isRing(id) || isPendant(id) || isFaceAccessory(id) || isEyeAccessory(id) || isEarrings(id)) {
                res.addAll(data.stream().filter(
                                io -> io.getOptionType() == ItemOptionType.Accessory.getVal()
                                        && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                        .collect(Collectors.toList()));
            } else {
                if (isHat(id)) {
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.Hat.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isTop(id) || isOverall(id)) {
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.Top.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isBottom(id) || isOverall(id)) {
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.Bottom.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isGlove(id)) {
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.Glove.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                } else {
                    // gloves are not counted for this one
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.ArmorExceptGlove.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
                if (isShoe(id)) {
                    res.addAll(data.stream().filter(
                                    io -> io.getOptionType() == ItemOptionType.Shoes.getVal()
                                            && io.hasMatchingGrade(grade.getVal()) && io.isBonus() == bonus)
                            .collect(Collectors.toList()));
                }
            }
        }
        return res.stream().filter(io -> io.getReqLevel() <= equip.getrLevel() + equip.getiIncReq()).collect(Collectors.toList());
    }

    public static List<Integer> getWeightedOptionsByEquip(Equip equip, boolean bonus, int line) {
        List<Integer> res = new ArrayList<>();
        List<ItemOption> data = getOptionsByEquip(equip, bonus, line);
        for (ItemOption io : data) {
            for (int i = 0; i < io.getWeight(); i++) {
                res.add(io.getId());
            }
        }
        return res;
    }

    public static int getRandomOption(Equip equip, boolean bonus, int line) {
        List<Integer> data = getWeightedOptionsByEquip(equip, bonus, line);
        return data.get(Util.getRandom(data.size()));
    }

    public static int getTierUpChance(int id, short grade) {
        if (grade <= 0 || grade >= 4) {
            return 0;
        }
        boolean fixed = false;
        int res = 0;
        switch(id) {
            case ItemConstants.OCCULT_CUBE:
            case ItemConstants.MASTER_CRAFTSMANS_CUBE:
                res = 10; // 10, 5, (2)
                break;
            case ItemConstants.RED_CUBE:
            case ItemConstants.BONUS_POT_CUBE:
            case ItemConstants.MEISTERS_CUBE:
                res = 15; // 15, 7, 3
                break;
            case ItemConstants.BLACK_CUBE:
            case ItemConstants.WHITE_BONUS_POT_CUBE:
                res = 25; // 25, 12, 6
                break;
            case ItemConstants.VIOLET_CUBE:
                res = 30; // 30, 15, 7
                break;
        }
        if (!fixed) {
            res /= Math.pow(2, grade - 1);
        }
        return res;
    }

    public static boolean isEquip(int id) {
        return id / 1000000 == 1;
    }

    public static boolean isClaw(int id) {
        return getItemPrefix(id) == 147;
    }

    public static boolean isBow(int id) {
        return getItemPrefix(id) == 145;
    }

    public static boolean isXBow(int id) {
        return getItemPrefix(id) == 146;
    }

    public static boolean isGun(int id) {
        return getItemPrefix(id) == 149;
    }

    public static boolean isXBowArrow(int id) {
        return id / 1000 == 2061;
    }

    public static InvType getInvTypeByItemID(int itemID) {
        if(isEquip(itemID)) {
            return EQUIP;
        } else {
            ItemInfo ii = ItemData.getItemInfoByID(itemID);
            if(ii == null) {
                return null;
            }
            return ii.getInvType();
        }
    }

    public static Set<Integer> getRechargeablesList() {
        Set<Integer> itemList = new HashSet<>();
        // all throwing stars
        for(int i = 2070000; i <= 2070016; i++) {
            itemList.add(i);
        }
        itemList.add(2070018);
        itemList.add(2070019);
        itemList.add(2070023);
        itemList.add(2070024);
        itemList.add(2070026);
        // all bullets
        for(int i = 2330000; i <= 2330007; i++) {
            itemList.add(i);
        }
        itemList.add(2330008);
        itemList.add(2330016);
        itemList.add(2331000);
        itemList.add(2332000);
        return itemList;
    }

    public static boolean isRechargable(int itemId) {
        return isThrowingStar(itemId) || isBullet(itemId);
    }

    public static int getDamageSkinIDByItemID(int itemID) {
        switch(itemID) {
            case 2431965:  // Basic Damage Skin
                return 0;
            case 2433271:  // Basic Damage Skin
                return 0;
            case 2438159:  // Basic Damage Skin
                return 0;
            case 2438872:  // Basic Damage Skin
                return 0;
            case 2438871:  // Basic Damage Skin
                return 0;
            case 2431966:  // Digitized Damage Skin
                return 1;
            case 2432084:  // Digitized Damage Skin
                return 1;
            case 2433260:  // Digitized Damage Skin
                return 1;
            case 2434239:  // Digitized Damage Skin
                return 1;
            case 2435172:  // Digitized Damage Skin
                return 1;
            case 2438160:  // Digitized Damage Skin
                return 1;
            case 2431967:  // Kritias Damaged Skin
                return 2;
            case 2438161:  // Kritias Damage Skin
                return 2;
            case 2432131:  // Party Quest Damage Skin
                return 3;
            case 2437009:  // Party Quest Damage Skin
                return 3;
            case 2438162:  // Party Quest Damage Skin
                return 3;
            case 2432153:  // Hard-Hitting Damage Skin
                return 4;
            case 2432638:  // Hard-hitting Damage Skin
                return 4;
            case 2432659:  // Hard-hitting Damage Skin
                return 4;
            case 2433261:  // Hard-Hitting Damage Skin
                return 4;
            case 2436688:  // Hard-hitting Damage Skin
                return 4;
            case 2438163:  // Hard-hitting Damage Skin
                return 4;
            case 2432154:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2432637:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2432658:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2433264:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2435045:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2436100:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2438164:  // Sweet Tea Cake Damage Skin
                return 5;
            case 2438165:  // Club Henesys Damage Skin
                return 6;
            case 2432207:  // Club Henesys' damage Skin:
                return 6;
            case 2432354:  // Merry Christmas Damage Skin
                return 7;
            case 2434975:  // Merry Christmas Damage Skin
                return 7;
            case 2432355:  // Snowflake Damage Skin
                return 8;
            case 2432972:  // Snowflake Damage Skin
                return 8;
            case 2434976:  // Snowflake Damage Skin
                return 8;
            case 2435173:  // Snowflake Damage Skin
                return 8;
            case 2438167:  // Snowflake Damage Skin
                return 8;
            case 2432465:  // Alicia's Damage Skin
                return 9;
            case 2438168:  // Alicia's Damage Skin
                return 9;
            case 2432479:  // Dorothy's Damage Skin
                return 10;
            case 2438169:  // Dorothy's Damage Skin
                return 10;
            case 2432526:  // Keyboard Warrior Damage Skin
                return 11;
            case 2432639:  // Keyboard Warrior Damage Skin
                return 11;
            case 2432660:  // Keyboard Warrior Damage Skin
                return 11;
            case 2433262:  // Keyboard Warrior Damage Skin
                return 11;
            case 2435174:  // Keyboard Warrior Damage Skin
                return 11;
            case 2436038:  // Keyboard Warrior Damage Skin
                return 11;
            case 2438170:  // Keyboard Warrior Damage Skin
                return 11;
            case 2432532:  // Gentle Springtime Breeze Damage Skin
                return 12;
            case 2435102:  // Gentle Springtime Breeze Damage Skin
                return 12;
            case 2435478:  // Gentle Springtime Breeze Damage Skin
                return 12;
            case 2438171:  // Gentle Springtime Breeze Damage Skin
                return 12;
            case 2433107:  // Damage Skin - Springtime Breeze
                return 12;
            case 2432592:  // Singles Army Damage Skin
                return 13;
            case 2433263:  // Singles Army Damage Skin
                return 13;
            case 2438172:  // Singles Army Damage Skin
                return 13;
            case 2433160:  // Lonely Single Damage Skin
                return 13;
            case 2432640:  // Reminiscence Damage Skin
                return 14;
            case 2432661:  // Reminiscence Damage Skin
                return 14;
            case 2433265:  // Reminiscence Damage Skin
                return 14;
            case 2435175:  // Reminiscence Damage Skin
                return 14;
            case 2438173:  // Reminiscence Damage Skin
                return 14;
            case 2438461:  // Reminiscence Damage Skin
                return 14;
            case 2432710:  // Orange Mushroom Damage Skin
                return 15;
            case 2433266:  // Orange Mushroom Damage Skin
                return 15;
            case 2433919:  // Orange Mushroom Damage Skin
                return 15;
            case 2435170:  // Orange Mushroom Damage Skin
                return 15;
            case 2436035:  // Orange Mushroom Damage Skin
                return 15;
            case 2438174:  // Orange Mushroom Damage Skin
                return 15;
            case 2432836:  // Crown Damage Skin
                return 16;
            case 2434980:  // Crown Damage Skin
                return 16;
            case 2435176:  // Crown Damage Skin
                return 16;
            case 2438175:  // Crown Damage Skin
                return 16;
            case 2432973:  // Monotone Damage Skin
                return 17;
            case 2433158:  // Monotone Damage Skin
                return 17;
            case 2433897:  // Monotone Damage Skin
                return 17;
            case 2435177:  // Monotone Damage Skin
                return 17;
            case 2435834:  // Monotone Damage Skin
                return 17;
            case 2438176:  // Monotone Damage Skin
                return 17;
            case 2433063:  // Star Planet Damage Skin
                return 18;
            case 2435510:  // Star Planet Damage Skin
                return 18;
            case 2438177:  // Star Planet Damage Skin
                return 18;
            case 2439572:  // Star Planet Damage Skin
                return 18;
            case 2433456:  // Hangul Day Damage Skin
                return 19;
            case 2438179:  // Hangul Day Damage Skin
                return 19;
            case 2433715:  // Striped Damage Skin
                return 23;
            case 2434979:  // Striped Damage Skin
                return 23;
            case 2435024:  // Striped Damage Skin
                return 23;
            case 2435571:  // Striped Damage Skin
                return 23;
            case 2436101:  // Striped Damage Skin
                return 23;
            case 2438181:  // Striped Damage Skin
                return 23;
            case 2433804:  // Couples Army Damage Skin
                return 24;
            case 2435101:  // Couples Army Damage Skin
                return 24;
            case 2435168:  // Couples Army Damage Skin
                return 24;
            case 2438182:  // Couples Army Damage Skin
                return 24;
            case 2433913:  // Yeti and Pepe Damage Skin
                return 26;
            case 2435025:  // Yeti and Pepe Damage Skin
                return 26;
            case 2436036:  // Yeti and Pepe Damage Skin
                return 26;
            case 2438184:  // Yeti and Pepe Damage Skin
                return 26;
            case 2433980:  // Slime and Mushroom Damage Skin
                return 27;
            case 2434741:  // Slime and Mushroom Damage Skin
                return 27;
            case 2435026:  // Slime and Mushroom Damage Skin
                return 27;
            case 2437527:  // Slime and Mushroom Damage Skin
                return 27;
            case 2438185:  // Slime and Mushroom Damage Skin
                return 27;
            case 2433981:  // Pink Bean Damage Skin
                return 28;
            case 2434742:  // Pink Bean Damage Skin
                return 28;
            case 2438186:  // Pink Bean Damage Skin
                return 28;
            case 2438421:  // Pink Bean Damage Skin
                return 28;
            case 2434248:  // Rainbow Boom Damage Skin
                return 34;
            case 2435027:  // Rainbow Boom Damage Skin
                return 34;
            case 2435117:  // Rainbow Boom Damage Skin
                return 34;
            case 2435477:  // Rainbow Boom Damage Skin
                return 34;
            case 2438188:  // Rainbow Boom Damage Skin
                return 34;
            case 2433362:  // Night Sky Damage Skin
                return 35;
            case 2433666:  // Mashmellow Damage Skin
                return 36;
            case 2434274:  // Marshmallow Damage Skin
                return 36;
            case 2435029:  // Marshmallow Damage Skin
                return 36;
            case 2435490:  // Marshmallow Damage Skin
                return 36;
            case 2438190:  // Marshmallow Damage Skin
                return 36;
            case 2434289:  // Mu Lung Dojo Damage Skin
                return 37;
            case 2438191:  // Mu Lung Dojo Damage Skin
                return 37;
            case 2434390:  // Teddy Damage Skin
                return 38;
            case 2436099:  // Teddy Damage Skin
                return 38;
            case 2437712:  // Teddy Damage Skin
                return 38;
            case 2438192:  // Teddy Ursus Damage Skin
                return 38;
            case 2434391:  // Mighty Ursus Damage Skin
                return 39;
            case 2436034:  // Mighty Ursus Damage Skin
                return 39;
            case 2438193:  // Mighty Ursus Damage Skin
                return 39;
            case 2438194:  // Scorching Heat Damage Skin
                return 40;
            case 5680395:  // Scorching Heat Damage Skin
                return 40;
            case 2434528:  // USA Damage Skin
                return 41;
            case 2438195:  // USA Damage Skin
                return 41;
            case 2434529:  // Churro Damage Skin
                return 42;
            case 2438196:  // Churro Damage Skin
                return 42;
            case 2434530:  // Singapore Night Damage Skin
                return 43;
            case 2438197:  // Singapore Night Damage Skin
                return 43;
            case 2438198:  // Scribbler Damage Skin
                return 44;
            case 2436531:  // Scribbler Damage Skin
                return 44;
            case 2434546:  // Scribbler Damage Skin
                return 44;
            case 2433571:  // Scribble Crush Damage Skin
                return 44;
            case 2434374:  // Autumn Festival Full Moon Damage Skin
                return 45;
            case 2434574:  // Full Moon Damage Skin
                return 45;
            case 2438199:  // Full Moon Damage Skin
                return 45;
            case 2434575:  // Sunny Damage Skin
                return 46;
            case 2435171:  // Sunny Damage Skin
                return 46;
            case 2435727:  // Sunny Damage Skin
                return 46;
            case 2438200:  // Sunny Damage Skin
                return 46;
            case 2432804:  // Princess No Damage Skin
                return 47;
            case 2434654:  // Murgoth Damage Skin
                return 48;
            case 2435325:  // Murgoth Damage Skin
                return 48;
            case 2437727:  // Murgoth Damage Skin
                return 48;
            case 2438202:  // Murgoth Damage Skin
                return 48;
            case 2435326:  // Nine-Tailed Fox Damage Skin
                return 49;
            case 2432749:  // Zombie Damage Skin:
                return 50;
            case 2434710:  // MVP Special Damage Skin
                return 51;
            case 2438205:  // MVP Special Damage Skin
                return 51;
            case 2434824:  // Monster Park Damage Skin
                return 53;
            case 2438207:  // Monster Park Damage Skin
                return 53;
            case 2436041:  // Heroes Phantom Damage Skin
                return 78;
            case 2435665:  // Heroes Phantom Damage Skin
                return 78;
            case 2435043:  // Heroes Phantom Damage Skin
                return 78;
            case 2438211:  // Heroes Phantom Damage Skin
                return 77;
            case 2436042:  // Heroes Mercedes Damage Skin
                return 78;
            case 2435666:  // Heroes Mercedes Damage Skin
                return 78;
            case 2435044:  // Heroes Mercedes Damage Skin
                return 78;
            case 2438212:  // Heroes Mercedes Damage Skin
                return 78;
            case 2435046:  // Fireworks Damage Skin
                return 79;
            case 2436097:  // Fireworks Damage Skin
                return 79;
            case 2437710:  // Fireworks Damage Skin
                return 79;
            case 2434499:  // Autumn Festival Fireworks Damage Skin
                return 79;
            case 2435047:  // Heart Balloon Damage Skin
                return 80;
            case 2435140:  // Neon Sign Damage Skin
                return 81;
            case 2435836:  // Neon Sign Damage Skin
                return 81;
            case 2438215:  // Neon Sign Damage Skin
                return 81;
            case 2435141:  // Freeze Tag Damage Skin
                return 82;
            case 2437244:  // Freeze Tag Damage Skin
                return 82;
            case 2438216:  // Freeze Tag Damage Skin
                return 82;
            case 2435179:  // Candy Damage Skin
                return 83;
            case 2436096:  // Candy Damage Skin
                return 83;
            case 2438217:  // Candy Damage Skin
                return 83;
            case 2435162:  // Antique Gold Damage Skin
                return 84;
            case 2435157:  // Calligraphy Damage Skin
                return 85;
            case 2436098:  // Calligraphy Damage Skin
                return 85;
            case 2438219:  // Calligraphy Damage Skin
                return 85;
            case 2435158:  // Explosion Damage Skin
                return 86;
            case 2435835:  // Explosion Damage Skin
                return 86;
            case 2438220:  // Explosion Damage Skin
                return 86;
            case 2435159:  // Snow-wing Damage Skin
                return 87;
            case 2436687:  // Snow-wing Damage Skin
                return 87;
            case 2438221:  // Snow-wing Damage Skin
                return 87;
            case 2435160:  // Miho Damage Skin
                return 88;
            case 2436044:  // Miho Damage Skin
                return 88;
            case 2438222:  // Miho Damage Skin
                return 88;
            case 2435182:  // Musical Score Damage Skin
                return 90;
            case 2435725:  // Musical Score Damage Skin
                return 90;
            case 2438224:  // Musical Score Damage Skin
                return 90;
            case 2433709:  // Moon Bunny Damage Skin
                return 91;
            case 2434081:  // Moon Bunny Damage Skin
                return 91;
            case 2435166:  // Moon Bunny Damage Skin
                return 91;
            case 2435850:  // Moon Bunny Damage Skin
                return 91;
            case 2438225:  // Moon Bunny Damage Skin
                return 91;
            case 2435184:  // Forest of Tenacity Damage Skin
                return 92;
            case 2438226:  // Forest of Tenacity Damage Skin
                return 92;
            case 2435222:  // Festival Tortoise Damage Skin
                return 93;
            case 2436530:  // Festival Tortoise Damage Skin
                return 93;
            case 2438227:  // Festival Tortoise Damage Skin
                return 93;
            case 2438228:  // April Fools' Damage Skin
                return 94;
            case 2435293:  // April Fools' Damage Skin
                return 94;
            case 2438229:  // Blackheart Day Damage Skin
                return 95;
            case 2435313:  // Blackheart Day Damage Skin
                return 95;
            case 2438233:  // Sparkling April Fools' Damage Skin
                return 96;
            case 2435473:  // Bubble April Fools' Damage Skin
                return 96;
            case 2435331:  // Bubble April Fools' Damage Skin
                return 96;
            case 2438231:  // Retro April Fools' Damage Skin
                return 97;
            case 2435332:  // Retro April Fools' Damage Skin
                return 97;
            case 2438232:  // Monochrome April Fools' Damage Skin
                return 98;
            case 2435849:  // Monochrome April Fools' Damage Skin
                return 98;
            case 2435333:  // Monochrome April Fools' Damage Skin
                return 98;
            case 2435334:  // Sparkling April Fools' Damage Skin
                return 99;
            case 2435474:  // Sparkling April Fools' Damage Skin
                return 99;
            case 2435316:  // Haste Damage Skin
                return 100;
            case 2438234:  // Haste Damage Skin
                return 100;
            case 2438235:  // 12th Anniversary Maple Leaf Damage Skin
                return 101;
            case 2435408:  // 12th Anniversary Maple Leaf Damage Skin
                return 101;
            case 2438759:  // 12th Anniversary Damage Skin
                return 101;
            case 2435427:  // Cyber Damage Skin
                return 102;
            case 2438236:  // Cyber Damage Skin
                return 102;
            case 2435428:  // Cosmic Damage Skin
                return 103;
            case 2435839:  // Cosmic Damage Skin
                return 103;
            case 2438237:  // Cosmic Damage Skin
                return 103;
            case 2438238:  // Choco Donut Damage Skin
                return 104;
            case 2435429:  // Choco Donut Damage Skin
                return 104;
            case 2438240:  // Monster Balloon Damage Skin
                return 104;
            case 2435456:  // Lovely Damage Skin:
                return 105;
            case 2435461:  // Balloon Damage Skin
                return 106;
            case 2435493:  // Monster Balloon Damage Skin
                return 106;
            case 2438241:  // Bubble April Fools' Damage Skin
                return 107;
            case 2438242:  // Sparkling April Fools' Damage Skin
                return 108;
            case 2435424:  // Henesys Damage Skin
                return 109;
            case 2438243:  // Henesys Damage Skin
                return 109;
            case 2435959:  // Henesys Damage Skin
                return 109;
            case 2435958:  // Leafre Damage Skin
                return 110;
            case 2435425:  // Leafre Damage Skin
                return 110;
            case 2438244:  // Leafre Damage Skin
                return 110;
            case 2435431:  // Algebraic Damage Skin
                return 111;
            case 2438245:  // Algebraic Damage Skin
                return 111;
            case 2435430:  // Blue Flame Damage Skin
                return 112;
            case 2438246:  // Blue Flame Damage Skin
                return 112;
            case 2438761:  // Blue Flame Damage Skin
                return 112;
            case 2435432:  // Purple Damage Skin
                return 113;
            case 2438247:  // Purple Damage Skin
                return 113;
            case 2435433:  // Nanopixel Damage Skin
                return 114;
            case 2438248:  // Nanopixel Damage Skin
                return 114;
            case 2435521:  // Crystal Damage Skin
                return 116;
            case 2435523:  // Chocolate Damage Skin
                return 118;
            case 2435524:  // Spark Damage Skin
                return 119;
            case 2436561:  // Spark Damage Skin
                return 119;
            case 2438249:  // Royal Damage Skin
                return 120;
            case 2435538:  // Royal Damage Skin
                return 120;
            case 2435832:  // Chrome Damage Skin (Ver. 1)
                return 121;
            case 2438250:  // Chrome Damage Skin (Ver. 1)
                return 121;
            case 2435833:  // Neon Lights Damage Skin
                return 122;
            case 2436360:  // Neon Lights Damage Skin
                return 122;
            case 2438251:  // Neon Lights Damage Skin
                return 122;
            case 2438252:  // Spades Damage Skin
                return 123;
            case 2435840:  // Gilded Damage Skin
                return 124;
            case 2438253:  // Gilded Damage Skin
                return 124;
            case 2435841:  // Batty Damage Skin
                return 125;
            case 2438254:  // Batty Damage Skin
                return 125;
            case 2438255:  // Monochrome April Fools' Damage Skin
                return 126;
            case 2435972:  // Vanishing Journey Damage Skin
                return 127;
            case 2438256:  // Vanishing Journey Damage Skin
                return 127;
            case 2436023:  // Chu Chu Damage Skin
                return 128;
            case 2438257:  // Chu Chu Damage Skin
                return 128;
            case 2436024:  // Lachelein Damage Skin
                return 129;
            case 2438258:  // Lachelein Damage Skin
                return 129;
            case 2436026:  // Poison Flame Damage Skin
                return 130;
            case 2438259:  // Poison Flame Damage Skin
                return 130;
            case 2436027:  // Blue Shock Damage Skin
                return 131;
            case 2438260:  // Blue Shock Damage Skin
                return 131;
            case 2436028:  // Music Power Damage Skin
                return 132;
            case 2438261:  // Music Power Damage Skin
                return 132;
            case 2436029:  // Collage Power Damage Skin
                return 133;
            case 2436045:  // Starlight Aurora Damage Skin
                return 134;
            case 2438263:  // Starlight Aurora Damage Skin
                return 134;
            case 2436085:  // Chestnut Damage Skin
                return 135;
            case 2437709:  // Chestnut Damage Skin
                return 135;
            case 2438264:  // Chestnut Damage Skin
                return 135;
            case 2438265:  // Twilight Damage Skin
                return 136;
            case 2436083:  // Twilight Damage Skin
                return 136;
            case 2437707:  // Twilight Damage Skin
                return 136;
            case 2438266:  // Unyielding Fury Damage Skin
                return 137;
            case 2436084:  // Unyielding Fury Damage Skin
                return 137;
            case 2437708:  // Unyielding Fury Damage Skin
                return 137;
            case 2436103:  // Gilded Moonlight Damage Skin
                return 138;
            case 2438158:  // Gilded Moonlight Damage Skin
                return 138;
            case 2438267:  // Gilded Moonlight Damage Skin
                return 138;
            case 2438268:  // Hangul Day Traditional Damage Skin
                return 139;
            case 2436131:  // Hangul Day Traditional Damage Skin
                return 139;
            case 2438269:  // Gingko Leaf Damage Skin
                return 140;
            case 2436140:  // Gingko Leaf Damage Skin
                return 140;
            case 2438270:  // Detective Damage Skin
                return 141;
            case 2436206:  // Detective Damage Skin
                return 141;
            case 2436182:  // Silly Ghost Damage Skin
                return 142;
            case 2436212:  // Hallowkitty Damage Skin
                return 143;
            case 2437851:  // Hallowkitty Damage Skin
                return 143;
            case 2438272:  // Pew Pew Damage Skin
                return 144;
            case 2436268:  // Steamed Bun Damage Skin
                return 145;
            case 2436258:  // Relic Damage Skin
                return 146;
            case 2438274:  // Relic Damage Skin
                return 146;
            case 2438275:  // Hieroglyph Damage Skin
                return 147;
            case 2436259:  // Hieroglyph Damage Skin
                return 147;
            case 2438276:  // Breakthrough Damage Skin
                return 148;
            case 2436400:  // Breakthrough Damage Skin
                return 148;
            case 2438282:  // Returned Legend Damage Skin
                return 155;
            case 2436553:  // Returned Legend Damage Skin
                return 155;
            case 2437697:  // Returned Legend Damage Skin
                return 155;
            case 2438283:  // Mecha Damage Skin
                return 156;
            case 2436560:  // Mecha Damage Skin
                return 156;
            case 2438284:  // Foamy Friends Damage Skin
                return 157;
            case 2436578:  // Foamy Friends Damage Skin
                return 157;
            case 2437767:  // Foamy Friends Damage Skin
                return 157;
            case 2438285:  // Magpie's Feather Damage Skin
                return 158;
            case 2436611:  // Magpie's Feather Damage Skin
                return 158;
            case 2438286:  // Persimmon Tree Damage Skin
                return 159;
            case 2436596:  // Crystalline Damage Skin
                return 160;
            case 2436679:  // Arcana Damage Skin
                return 161;
            case 2438287:  // Arcana Damage Skin
                return 161;
            case 2438288:  // Imperial Damage Skin
                return 162;
            case 2436680:  // Imperial Damage Skin
                return 162;
            case 2438289:  // Fafnir Damage Skin
                return 163;
            case 2436681:  // Fafnir Damage Skin
                return 163;
            case 2436682:  // AbsoLab Damage Skin
                return 164;
            case 2438290:  // AbsoLab Damage Skin
                return 164;
            case 2438293:  // Honeybee Damage Skin
                return 167;
            case 2436785:  // Honeybee Damage Skin
                return 167;
            case 2438294:  // Evolution Damage Skin
                return 168;
            case 2436810:  // Evolution Damage Skin
                return 168;
            case 2436951:  // Constellation Damage Skin
                return 169;
            case 2438295:  // Constellation Damage Skin
                return 169;
            case 2437768:  // Constellation Damage Skin
                return 169;
            case 2438296:  // Extraterrestrial Damage Skin
                return 170;
            case 2436952:  // Extraterrestrial Damage Skin
                return 170;
            case 2438297:  // Frozen Treat Damage Skin
                return 171;
            case 2436953:  // Frozen Treat Damage Skin
                return 171;
            case 2438298:  // Solar Eclipse Damage Skin
                return 172;
            case 2437022:  // Solar Eclipse Damage Skin
                return 172;
            case 2437769:  // Solar Eclipse Damage Skin
                return 172;
            case 2438299:  // Prism Damage Skin
                return 173;
            case 2437023:  // Prism Damage Skin
                return 173;
            case 2437024:  // Starry Sky Damage Skin
                return 174;
            case 2438300:  // Starry Sky Damage Skin
                return 174;
            case 2438301:  // Party Quest Damage Skin
                return 175;
            case 2438302:  // Cadena Damage Skin
                return 176;
            case 2437164:  // Cadena Damage Skin
                return 176;
            case 2438032:  // Cadena Damage Skin
                return 176;
            case 2438925:  // Cadena Damage Skin
                return 176;
            case 2438303:  // Black Rose Damage Skin
                return 177;
            case 2437238:  // Black Rose Damage Skin
                return 177;
            case 2438306:  // Stormcloud Damage Skin
                return 180;
            case 2437495:  // Stormcloud Damage Skin
                return 180;
            case 2438307:  // Drizzly Damage Skin
                return 181;
            case 2437496:  // Rainfall Damage Skin
                return 181;
            case 2438308:  // Luxe Damage Skin
                return 182;
            case 2438309:  // Palm Frond Damage Skin
                return 183;
            case 2437515:  // Palm Frond Damage Skin
                return 183;
            case 2438310:  // Illium Damage Skin
                return 184;
            case 2437482:  // Illium Damage Skin
                return 184;
            case 2438926:  // Illium Damage Skin
                return 184;
            case 2438311:  // Lightning Damage Skin
                return 185;
            case 2437691:  // Lightning Damage Skin
                return 185;
            case 2438604:  // Lightning Damage Skin
                return 185;
            case 2438312:  // MapleStory Damage Skin
                return 186;
            case 2437716:  // MapleStory Damage Skin (KR)
                return 186;
            case 2438313:  // Rice Cake Damage Skin
                return 187;
            case 2437735:  // Rice Cake Damage Skin
                return 187;
            case 2438314:  // Popcorn Damage Skin
                return 188;
            case 2437736:  // Popcorn Damage Skin
                return 188;
            case 2438315:  // Pew Pew Damage Skin (Ver. 2)
                return 189;
            case 2437854:  // Pew Pew Damage Skin (Ver. 2)
                return 189;
            case 2437877:  // Master Crimson Damage Skin
                return 190;
            case 2438143:  // Transcendent of Time Damage Skin
                return 191;
            case 2438144:  // Superstar Damage Skin
                return 192;
            case 2438352:  // Ark Damage Skin
                return 193;
            case 2438353:  // Ark Damage Skin
                return 193;
            case 2438924:  // Ark Damage Skin
                return 193;
            case 2438378:  // Woof Woof Damage Skin
                return 194;
            case 2438379:  // Heartthrob Damage Skin
                return 195;
            case 2438413:  // Discovery Damage Skin
                return 196;
            case 2438414:  // Discovery Damage Skin
                return 196;
            case 2438415:  // Esfera Damage Skin
                return 197;
            case 2438416:  // Esfera Damage Skin
                return 197;
            case 2438417:  // Heavenly Damage Skin
                return 198;
            case 2438418:  // Heavenly Damage Skin
                return 198;
            case 2438419:  // Hybrid Damage Skin
                return 199;
            case 2438420:  // Hybrid Damage Skin
                return 199;
            case 2438460:  // Red Circuit Damage Skin
                return 200;
            case 2438485:  // Red Circuit Damage Skin
                return 200;
            case 2438491:  // Choco Bonbon Damage Skin
                return 201;
            case 2438492:  // Choco Bonbon Damage Skin
                return 201;
            case 2438529:  // Twelve Branches Damage Skin
                return 202;
            case 2438530:  // Twelve Branches Damage Skin
                return 202;
            case 2438637:  // Doodle Damage Skin
                return 204;
            case 2438676:  // Soccer Uniform Damage Skin
                return 205;
            case 2438713:  // Soccer Uniform Damage Skin
                return 205;
            case 2438881:  // Monster Damage Skin
                return 206;
            case 2438880:  // Monster Damage Skin
                return 206;
            case 2438884:  // 14th Street Damage Skin
                return 207;
            case 2438885:  // 14th Street Damage Skin
                return 207;
            case 2439256:  // Shinsoo Damage Skin
                return 209;
            case 2439298:  // Shinsoo Damage Skin
                return 209;
            case 2439264:  // Foggy Damage Skin
                return 210;
            case 2439265:  // Foggy Damage Skin
                return 210;
            case 2439336:  // Foggy Damage Skin
                return 211;
            case 2439337:  // Foggy Damage Skin
                return 211;
            case 2439277:  // Alliance Damage Skin
                return 212;
            case 2439338:  // Alliance Damage Skin
                return 212;
            case 2439381:  // Master Stellar Damage Skin
                return 213;
            case 2439392:  // Labyrinth Flame Damage Skin
                return 214;
            case 2439393:  // Labyrinth Flame Damage Skin
                return 214;
            case 2439394:  // Labyrinth Flame Damage Skin
                return 215;
            case 2439395:  // Labyrinth Flame Damage Skin
                return 215;
            case 2439407:  // Living Chain Damage Skin
                return 216;
            case 2439408:  // Living Chain Damage Skin
                return 216;
            case 2439616:  // Challenge Damage Skin: Season 1
                return 218;
            case 2439617:  // Challenge Damage Skin: Season 1
                return 218;
            case 2439652:  // Harvest Damage Skin
                return 219;
            case 2439665:  // Harvest Damage Skin
                return 219;
            case 2439683:  // Hangul Day Damage Skin (KR)
                return 221;
            case 2439684:  // Hangul Day Damage Skin (KR)
                return 221;
            case 2439685:  // Hangul Day Traditional Damage Skin (KR)
                return 222;
            case 2439686:  // Hangul Day Traditional Damage Skin (KR)
                return 222;
            case 2439769:  // Hallowkitty Damage Skin V2
                return 223;
            case 2439768:  // Hallowkitty Damage Skin V2
                return 223;
            case 2439925:  // Detective Yettson and Peplock Damage Skin
                return 224;
            case 2439926:  // Detective Yettson and Peplock Damage Skin
                return 224;
            case 2439927:  // Pew Pew Damage Skin (Ver. 3)
                return 225;
            case 2439928:  // Pew Pew Damage Skin (Ver. 3)
                return 225;
            case 2432591:  // Cherry Blossoms Damage Skin
                return 1000;

            case 2436133:  // Chick Damage Skin
                return 1134;
            case 2436645:  // Intense Damage Skin
                return 1247;
            case 2436132:  // Illumination Damage Skin
                return 1133;
            case 2436644:  // Color Pop Damage Skin (30 Day)
                return 1246;
            case 2436134:  // Item has no name
                return 1135;
            case 2436646:  // Intense Damage Skin (30 Day)
                return 1248;
            case 2436643:  // Color Pop Damage Skin
                return 1245;
            case 2436653:  // Reverse Damage Skin
                return 1239;
            case 2436652:  // Ink Damage Skin (30 Day)
                return 1238;
            case 2436655:  // Neon Easter Egg Damage Skin
                return 1241;
            case 2437167:  // Note Damage Skin (30 Day)
                return 1274;
            case 2436654:  // Reverse Damage Skin (30 Day)
                return 1240;
            case 2437166:  // Note Damage Skin
                return 1273;
            case 2434601:  // Invisible Damage Skin
                return 1050;
            case 2436136:  // 'Magical' Bottle for Souls
                return 1136;
            case 2436651:  // Ink Damage Skin
                return 1237;
            case 2433588:  // Chinese Spring Fireworks Damage Skin
                return 1023;
            case 2436657:  // Watercolor Damage Skin
                return 1243;
            case 2437169:  // Crayon Damage Skin (30 Day)
                return 1276;
            case 2436656:  // Neon Easter Egg Damage Skin (30 Day)
                return 1242;
            case 2437168:  // Crayon Damage Skin
                return 1275;
            case 2436658:  // Watercolor Damage Skin (30 Day)
                return 1244;
            case 2433081:  // Halloween Damage Skin
                return 1006;
            case 2434619:  // Nine-Tailed Fox Damage Skin
                return 1057;
            case 2438661:  // Mustache Damage Skin
                return 1333;
            case 2630153:  // Coral Reef Damage Skin
                return 1368;
            case 3801113:  // Item has no name
                return 182;
            case 2630156:  // Shimmerlight Damage Skin
                return 1369;
            case 2438659:  // Stamp Damage Skin
                return 1332;
            case 2438671:  // High Noon Damage Skin
                return 1337;
            case 2433038:  // 분필 �?�미지스킨
                return 1010;
            case 2434570:  // Tot's Damage Skin
                return 1056;
            case 2439700:  // Black Damage Skin
                return 1359;
            case 2439697:  // 3D Effect Damage Skin
                return 1358;
            case 2434663:  // Donut Damage Skin
                return 1059;
            case 2434662:  // Jelly Bean Damage Skin
                return 1058;
            case 2434147:  // Irena's Band Damage Skin
                return 1042;
            case 2434157:  // Damien's Band Damage Skin
                return 1043;
            case 2434664:  // Soft Serve Damage Skin
                return 1060;
            case 2436721:  // Sheep Damage Skin
                return 1022;
            case 2439805:  // Golden Tinsel Damage Skin
                return 1363;
            case 2435196:  // Crow Damage Skin
                return 1079;
            case 2435193:  // Krakian Damage Skin
                return 1077;
            case 2439800:  // Jolly Holiday Damage Skin
                return 1362;
            case 2435195:  // Corrupted Magician Damage Skin
                return 1076;
            case 2435194:  // Crimson Knight Damage Skin
                return 1075;
            case 2437701:  // Kaleidoscope Damage Skin (30 Day)
                return 1301;
            case 2437700:  // Kaleidoscope Damage Skin
                return 1300;
            case 2437703:  // Winter Night Skin (30 Day)
                return 1303;
            case 2433113:  // Chinese Marshmallow Damage Skin
                return 1008;
            case 2435673:  // Cygnus Water Warrior Damage Skin
                return 1098;
            case 2435674:  // Resistance Water Warrior Damage Skin
                return 1099;
            case 2433184:  // Wicked Witch Damage Skin
                return 1016;
            case 2630304:  // Rock Spirit Damage Skin
                return 1371;
            case 2433214:  // Noise Damage Skin
                return 1017;
            case 2436229:  // Cozy Christmas Damage Skin (30 Day)
                return 1142;
            case 2436741:  // Chinese Text Damage Skin (30 Day)
                return 1251;
            case 2436228:  // Lucid Butterfly Damage Skin
                return 1141;
            case 2436740:  // Japanese Kanji Character Damage Skin
                return 1250;
            case 2436743:  // Chinese Text Damage Skin (30 Day)
                return 1253;
            case 2436230:  // Cozy Christmas Damage Skin
                return 1143;
            case 2436742:  // Chinese Text Damage Skin
                return 1252;
            case 2436227:  // Lucid Butterfly Damage Skin (30 Day)
                return 1140;
            case 2435213:  // Antellion Damage Skin
                return 1080;
            case 2436749:  // Knife Wound Damage Skin (30 Day)
                return 1259;
            case 2436748:  // Knife Wound Damage Skin
                return 1258;
            case 2436745:  // Chinese Text Damage Skin (30 Day)
                return 1255;
            case 2436744:  // Chinese Text Damage Skin
                return 1254;
            case 2436747:  // Roman Numeral Damage Skin (30 Day)
                return 1257;
            case 2436746:  // Roman Numeral Damage Skin
                return 1256;
            case 2437269:  // Maple Damage Skin (30 Day)
                return 1278;
            case 2437268:  // Maple Damage Skin
                return 1277;
            case 2437271:  // Embroidery Damage Skin (30 Day)
                return 1280;
            case 2437270:  // Embroidery Damage Skin
                return 1279;
            case 2630301:  // Bunny Blossom Damage Skin
                return 1370;
            case 2433183:  // Super Spooky Damage Skin
                return 1015;
            case 2433182:  // Jack o' Lantern Damage Skin
                return 1018;
            case 2437274:  // Dice Master Damage Skin
                return 1281;
            case 2433252:  // Dragon's Fire Damage Skin
                return 1011;
            case 2436832:  // aa
                return 1263;
            case 2437856:  // Frigid Ice Damage Skin
                return 1312;
            case 2433251:  // Violetta's Charming Damage Skin
                return 1012;
            case 2433775:  // Orchid Damage Skin
                return 1032;
            case 2433269:  // Golden Damage Skin
                return 1001;
            case 2433268:  // Zombie Damage Skin
                return 1003;
            case 2630393:  // 모멘텀 �?�미지스킨 (보존용)
                return 1373;
            case 2433270:  // Jett Damage Skin
                return 1005;
            case 2433777:  // Black Heaven Damage Skin
                return 1031;
            case 2433776:  // Lotus Damage Skin
                return 1033;
            case 2433267:  // Blood Damage Skin
                return 1002;
            case 2630390:  // 빙고 �?�미지스킨 (보존용)
                return 1372;
            case 2439373:  // Bitty Baby Feet Damage Skin
                return 1351;
            case 2436300:  // Sweetheart Choco Damage Skin
                return 1144;
            case 2438348:  // Baseball Jacket Damage Skin
                return 1321;
            case 2436808:  // Aspire Industries Damage Skin
                return 1260;
            case 2438347:  // Tweed Damage Skin
                return 1320;
            case 2433236:  // Chalk Damage Skin
                return 1019;
            case 2439894:  // Lunar New Year Damage Skin
                return 1364;
            case 2439376:  // Pink Princess Damage Skin
                return 1352;
            case 2436831:  // Petal Damage Skin
                return 1262;
            case 2436830:  // Gifts of the Ryuul Damage Skin
                return 1261;
            case 2439897:  // Valentine Damage Skin
                return 1365;
            case 2435802:  // Dragon Fireworks Damage Skin
                return 1100;
            case 2433829:  // White Heaven Rain Damage Skin
                return 1035;
            case 2433828:  // White Heaven Sun Damage Skin
                return 1034;
            case 2433831:  // White Heaven Snow Damage Skin
                return 1037;
            case 2433830:  // White Heaven Rainbow Damage Skin
                return 1036;
            case 2432803:  // Princess No Damage Skin (30-Days)
                return 1004;
            case 2435374:  // Monkey Damage Skin
                return 1070;
            case 2433833:  // White Heaven Wind Damage Skin
                return 1039;
            case 2433832:  // White Heaven Lightning Damage Skin
                return 1038;
            case 2434868:  // Christmas Lights Damage Skin
                return 1062;
            case 2435380:  // Math Symbol Damage Skin
                return 1065;
            case 2434871:  // Chess Damage Skin
                return 1063;
            case 2435382:  // Secret Question Mark Damage Skin
                return 1067;
            case 2434877:  // Secret Damage Skin_Special Character
                return 1066;
            case 2434873:  // Secret Damage Skin_Music
                return 1064;
            case 2435335:  // Candles Damage Skin
                return 1081;
            case 2434817:  // Cube Damage Skin
                return 1068;
            case 2434818:  // One Winter Night Damage Skin
                return 1069;
            case 2435336:  // Cupcakes Damage Skin
                return 1082;
            case 2438929:  // Droplet Damage Skin
                return 1339;
            case 2438931:  // 14th Anniversary Damage Skin
                return 1341;
            case 2438930:  // Gummy Bear Damage Skin
                return 1340;
            case 2439442:  // Custom Puppy Damage Skin
                return 1353;
            case 2439523:  // 8-Bit Damage Skin
                return 1354;
            case 2433901:  // Beasts of Fury Damage Skin
                return 1007;
            case 2435949:  // Too Spooky Damage Skin
                return 1119;
            case 2433900:  // Night Sky Damage Skin
                return 1020;
            case 2435948:  // Halloween Town Damage Skin
                return 1118;
            case 2437484:  // Custom Kitty Damage Skin
                return 1286;
            case 2433903:  // Lovely Damage Skin\r\n
                return 1028;
            case 2435951:  // Item has no name
                return 1121;
            case 2433902:  // Beasts of Fury Damage Skin
                return 1021;
            case 2435950:  // Floofy Bichon Damage Skin
                return 1120;
            case 2435957:  // Snow Monster Damage Skin
                return 1127;
            case 2435956:  // War of Roses Damage Skin
                return 1126;
            case 2433905:  // Heart Balloon Damage Skin
                return 1026;
            case 2435953:  // Item has no name
                return 1123;
            case 2437489:  // Ribbon Damage Skin (30 Day)
                return 1289;
            case 2433904:  // Dried Out Damage Skin
                return 1027;
            case 2435952:  // Item has no name
                return 1122;
            case 2437488:  // Ribbon Damage Skin
                return 1288;
            case 2433907:  // Antique Fantasy Damage Skin\r\n
                return 1024;
            case 2435955:  // Wandering Soul Damage Skin
                return 1125;
            case 2437491:  // Acorn Damage Skin (30 Day)
                return 1291;
            case 2433906:  // Scribble Crush Damage Skin
                return 1025;
            case 2435954:  // Masque's Puzzle Damage Skin
                return 1124;
            case 2437490:  // Acorn Damage Skin
                return 1290;
            case 2436477:  // XOXO Damage Skin (30 Day)
                return 1233;
            case 2436476:  // Full of Stars Damage Skin
                return 1232;
            case 2436479:  // Full of Stars Damage Skin (30 Day)
                return 1235;
            case 2439551:  // Devil Font Damage Skin
                return 1355;
            case 2436478:  // Full of Hearts Damage Skin (30 Day)
                return 1234;
            case 2436984:  // Treasures of Eluna Damage Skin
                return 1271;
            case 2436475:  // Full of Hearts Damage Skin
                return 1231;
            case 2436474:  // XOXO Damage Skin
                return 1230;
            case 2436986:  // Item has no name
                return 1272;
            case 2438469:  // Skull Damage Skin
                return 1323;
            case 2435908:  // Item has no name
                return 1117;
            case 2434375:  // Bonfire Damage Skin
                return 1045;
            case 2438471:  // Valentine's Day Damage Skin
                return 1324;
            case 2435905:  // Cat Paw Damage Skin
                return 1114;
            case 2435907:  // Item has no name
                return 1116;
            case 2438467:  // Graffiti Damage Skin
                return 1322;
            case 2435906:  // Cat Face Damage Skin
                return 1115;
            case 2438477:  // Pastel Easter Egg Damage Skin
                return 1326;
            case 2438473:  // White Chocolate Damage Skin
                return 1325;
            case 2433883:  // Earth Day Damage Skin
                return 1040;
            case 2435489:  // Sheriff Damage Skin
                return 1086;
            case 2435488:  // Lingling Damage Skin
                return 1085;
            case 3801003:  // Item has no name
                return 90;
            case 2435511:  // Remnant of the Goddess Damage Skin
                return 1029;
            case 3800993:  // Item has no name
                return 1055;
            case 2437052:  // Tropical Sunset Damage Skin (30 Day)
                return 1270;
            case 2437049:  // Summer Sands Damage Skin
                return 1267;
            case 2437051:  // Tropical Sunset Damage Skin
                return 1269;
            case 2437050:  // Summer Sands Damage Skin (30 Day)
                return 1268;
            case 2439554:  // Trick or Treat Damage Skin
                return 1356;
            case 2437524:  // Snow Crystal Damage Skin (30 Day)
                return 1295;
            case 2437521:  // Christmas Cane Damage Skin
                return 1292;
            case 2437523:  // Snow Crystal Damage Skin
                return 1294;
            case 2437522:  // Christmas Cane Damage Skin (30 Day)
                return 1293;
            case 2435487:  // Nene Damage Skin
                return 1084;
            case 2435486:  // TuTu Damage Skin
                return 1083;
            case 2434533:  // Blood Damage Skin
                return 1051;
            case 2434534:  // Zombie Damage Skin
                return 1052;
            case 2435565:  // Heroes Aran Damage Skin
                return 1095;
            case 2435567:  // Heroes Evan Damage Skin
                return 1097;
            case 2435566:  // Heroes Luminous Damage Skin
                return 1096;
            case 2439157:  // Abrup's Snowstorm Damage Skin
                return 1345;
            case 2434545:  // Hayato Damage Skin
                return 1054;
            case 2434544:  // Kanna Damage Skin
                return 1053;
            case 2435568:  // Heroes Shade Damage Skin
                return 1094;
            case 2439164:  // Fembris Damage Skin
                return 1346;
            case 2438655:  // Golden Damage Skin
                return 1331;
            case 2439167:  // Frostflail Yeti
                return 1347;
            case 2436089:  // Highlighter Damage Skin
                return 1132;
            case 2438085:  // Hong Bao Damage Skin
                return 1315;
            case 2438596:  // Cake Icing Damage Skin
                return 1329;
            case 2438087:  // Vengeful Nyen Damage Skin
                return 1317;
            case 2438086:  // Nyen Damage Skin
                return 1316;
            case 2438592:  // Round 'n' Round Damage Skin
                return 1327;
            case 2438594:  // Garden Damage Skin
                return 1328;
            case 2438089:  // Red-Orange Damage Skin
                return 1319;
            case 2438088:  // Zodiac Dog Damage Skin
                return 1318;
            case 2434004:  // Alishan Damage Skin
                return 1041;
            case 2435543:  // Epic Lulz Damage Skin
                return 1090;
            case 2435542:  // Item has no name
                return 1089;
            case 2436563:  // Rocket Damage Skin
                return 1236;
            case 2435549:  // Item has no name
                return 1088;
            case 2435548:  // Item has no name
                return 1087;
            case 2439132:  // Popsicle Damage Skin
                return 1349;
            case 2435545:  // Summer Damage Skin
                return 1092;
            case 2439129:  // Summer Sea Damage Skin
                return 1348;
            case 2435544:  // Item has no name
                return 1091;
            case 2435546:  // Blaster Damage Skin
                return 1093;

            default:
                return 0;
        }
    }

    public static boolean isMasteryBook(int itemId) {
        return getItemPrefix(itemId) == 229;
    }

    public static boolean isPet(int itemId) {
        return getItemPrefix(itemId) == 500;
    }

    public static boolean isSoulEnchanter(int itemID) {
        return itemID / 1000 == 2590;
    }

    public static boolean isSoul(int itemID) {
        return itemID / 1000 == 2591;
    }

    public static short getSoulOptionFromSoul(int itemId) {
        short id = 0;
        switch(itemId) {

        }
        return id;
    }

    public static int getRandomSoulOption() {
        return Util.getRandomFromCollection(soulPotList);
    }

    public static int getRandomRareSSB() {
        return Util.getRandomFromCollection(ssbRareRotation);
    }


    public static int getRandomSSB() {
        return Util.getRandomFromCollection(ssbRotation);
    }





    public static boolean isMobCard(int itemID) {
        return getItemPrefix(itemID) == 238;
    }

    public static boolean isCollisionLootItem(int itemID) {
        switch (itemID) {
            case GameConstants.BLUE_EXP_ORB_ID: // Blue
            case GameConstants.PURPLE_EXP_ORB_ID: // Purple
            case GameConstants.RED_EXP_ORB_ID: // Red
            case GameConstants.GOLD_EXP_ORB_ID: // Gold
                return true;

            default:
                return false;
        }
    }

    public static boolean isUpgradable(int itemID) {
        BodyPart bodyPart = BodyPart.getByVal(getBodyPartFromItem(itemID, 0));
        if (bodyPart == null || getItemPrefix(itemID) == EquipPrefix.SecondaryWeapon.getVal()) {
            return false;
        }
        switch (bodyPart) {
            case Ring1:
            case Ring2:
            case Ring3:
            case Ring4:
            case Pendant:
            case ExtendedPendant:
            case Weapon:
            case Belt:
            case Hat:
            case FaceAccessory:
            case EyeAccessory:
            case Top:
            case Bottom:
            case Shoes:
            case Earrings:
            case Shoulder:
            case Gloves:
            case Badge:
            case Shield:
            case Cape:
            case MechanicalHeart:
                return true;
            default:
                return false;
        }
    }

    public static List<ScrollUpgradeInfo> getScrollUpgradeInfosByEquip(Equip equip) {
        // not the most beautiful way to do this, but I'd like to think that it's pretty easy to understand
        BodyPart bp = BodyPart.getByVal(ItemConstants.getBodyPartFromItem(equip.getItemId(), 0));
        List<ScrollUpgradeInfo> scrolls = new ArrayList<>();
        int rLevel = equip.getrLevel() + equip.getiIncReq();
        int rJob = equip.getrJob();
        Set<EnchantStat> possibleStat = new HashSet<>();
        int plusFromLevel;
        int[] chances;
        int[] attStats = new int[0];
        int[] stat;
        int[] armorHp = new int[]{5, 20, 30, 70, 120};
        int[] armorDef = new int[]{1, 2, 4, 7, 10};
        boolean armor = false;
        if (bp == BodyPart.Weapon) {
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if ((rJob & RequiredJob.Warrior.getVal()) > 0) { // warrior
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if ((rJob & RequiredJob.Magician.getVal()) > 0) { // mage
                possibleStat.add(EnchantStat.MAD);
                possibleStat.add(EnchantStat.INT);
            } else if ((rJob & RequiredJob.Bowman.getVal()) > 0) { // bowman
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.DEX);
            } else if ((rJob & RequiredJob.Thief.getVal()) > 0 || (rJob & RequiredJob.Pirate.getVal()) > 0) { // thief/pirate
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.PAD);
                possibleStat.add(EnchantStat.MAD);
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30, 15};
            attStats = new int[]{1, 2, 3, 5, 7, 9};
            stat = new int[]{0, 0, 1, 2, 3, 4};
        } else if (bp == BodyPart.Gloves) {
            plusFromLevel = rLevel <= 70 ? 0 : 1;
            if ((rJob & RequiredJob.Magician.getVal()) > 0) {
                possibleStat.add(EnchantStat.MAD);
            } else {
                possibleStat.add(EnchantStat.PAD);
            }
            possibleStat.add(EnchantStat.DEF);
            chances = new int[]{100, 70, 30};
            attStats = new int[]{0, 1, 2, 3};
            stat = new int[]{3, 0, 0, 0};
        } else if (ItemConstants.isAccessory(equip.getItemId())) {
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if ((rJob & RequiredJob.Warrior.getVal()) > 0) { // warrior
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if ((rJob & RequiredJob.Magician.getVal()) > 0) { // mage
                possibleStat.add(EnchantStat.INT);
            } else if ((rJob & RequiredJob.Bowman.getVal()) > 0) { // bowman
                possibleStat.add(EnchantStat.DEX);
            } else if ((rJob & RequiredJob.Thief.getVal()) > 0 || (rJob & RequiredJob.Pirate.getVal()) > 0) { // thief/pirate
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30};
            stat = new int[]{1, 1, 2, 3, 5};
        } else {
            armor = true;
            plusFromLevel = rLevel >= 120 ? 2 : rLevel >= 60 ? 1 : 0;
            if ((rJob & RequiredJob.Warrior.getVal()) > 0) { // warrior
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.MHP);
            } else if ((rJob & RequiredJob.Magician.getVal()) > 0) { // mage
                possibleStat.add(EnchantStat.INT);
            } else if ((rJob & RequiredJob.Bowman.getVal()) > 0) { // bowman
                possibleStat.add(EnchantStat.DEX);
            } else if ((rJob & RequiredJob.Thief.getVal()) > 0 || (rJob & RequiredJob.Pirate.getVal()) > 0) { // thief/pirate
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.LUK);
            } else {
                possibleStat.add(EnchantStat.STR);
                possibleStat.add(EnchantStat.DEX);
                possibleStat.add(EnchantStat.INT);
                possibleStat.add(EnchantStat.LUK);
                possibleStat.add(EnchantStat.MHP);
            }
            chances = new int[]{100, 70, 30};
            stat = new int[]{1, 2, 3, 5, 7};
        }
        for (int i = 0; i < chances.length; i++) { // 4 scroll tiers for weapons
            int tier = i + plusFromLevel;
            TreeMap<EnchantStat, Integer> stats = new TreeMap<>();
            for (EnchantStat es : possibleStat) {
                int val;
                if (es.isAttackType()) {
                    val = attStats[tier];
                } else if (es.isHpOrMp()){
                    val = stat[tier] * 50;
                } else {
                    val = stat[tier];
                }
                if (val != 0) {
                    stats.put(es, val);
                }
            }
            if (armor) {
                stats.put(EnchantStat.DEF, armorDef[tier] + stats.getOrDefault(EnchantStat.DEF, 0));
                stats.put(EnchantStat.MHP, armorHp[tier] + stats.getOrDefault(EnchantStat.MHP, 0));
            }
            String title = chances[i] + "% ";
            title += bp == BodyPart.Weapon ? "Attack" : "Stat";
            ScrollUpgradeInfo sui = new ScrollUpgradeInfo(i, title, SpellTraceScrollType.Normal, 0, stats,
                    BASE_ST_COST + rLevel * (tier + 1), chances[i]);
            scrolls.add(sui);
        }
        if (equip.hasUsedSlots()) {
            scrolls.add(new ScrollUpgradeInfo(4, "Innocence Scroll 30%",
                    SpellTraceScrollType.Innocence, 0, new TreeMap<>(), INNOCENCE_ST_COST, 30));
            scrolls.add(new ScrollUpgradeInfo(5, "Clean Slate Scroll 5%",
                    SpellTraceScrollType.CleanSlate, 0, new TreeMap<>(), CLEAN_SLATE_ST_COST, 5));
        }
        return scrolls;
    }

    public static boolean isArcaneSymbol(int itemId) {
        return itemId / 10000 == 171;
    }

    // is_tuc_ignore_item(int nItemID)
    static boolean isTucIgnoreItem(int itemID) {
        return (isSecondary(itemID) || isEmblem(itemID) || Arrays.asList(TUC_IGNORE_ITEMS).contains(itemID));
    }

    public static PetSkill getPetSkillFromID(int itemID) {
        switch (itemID) {
            case 5190000:
                return PetSkill.ITEM_PICKUP;
            case 5190001:
                return PetSkill.AUTO_HP;
            case 5190002:
                return PetSkill.EXPANDED_AUTO_MOVE;
            case 5190003:
                return PetSkill.AUTO_MOVE;
            case 5190004:
                return PetSkill.EXPIRED_PICKUP;
            case 5190005:
                return PetSkill.IGNORE_ITEM;
            case 5190006:
                return PetSkill.AUTO_MP;
            case 5190007:
                return PetSkill.RECALL;
            case 5190008:
                return PetSkill.AUTO_SPEAKING;
            case 5190009:
                return PetSkill.AUTO_ALL_CURE;
            case 5190010:
                return PetSkill.AUTO_BUFF;
            case 5190011:
                return PetSkill.AUTO_FEED;
            case 5190012:
                return PetSkill.FATTEN_UP;
            case 5190013:
                return PetSkill.PET_SHOP;
            case 5190014:
                return PetSkill.FATTEN_UP;
            case 5191000:
                return PetSkill.ITEM_PICKUP;
            case 5191001:
                return PetSkill.AUTO_HP;
            case 5191002:
                return PetSkill.EXPANDED_AUTO_MOVE;
            case 5191003:
                return PetSkill.ITEM_PICKUP;
        }
        return null;
    }

    // Gets the hardcoded starforce capacities Nexon introduced for equips above level 137.
    // The cap for stars is in GetHyperUpgradeCapacity (E8 ? ? ? ? 0F B6 CB 83 C4 0C, follow `call`),
    // therefore it needs to be manually implemented on the server side.
    // Nexon's decision was very poor, but will require client edits to revert.
    static int getItemStarLimit(int itemID) {
        switch (itemID) {
            case 1072870: // Sweetwater Shoes
            case 1082556: // Sweetwater Gloves
            case 1102623: // Sweetwater Cape
            case 1132247: // Sweetwater Belt
                if (ServerConstants.VERSION >= 197) {
                    return 15;
                }
            case 1182060: // Ghost Ship Exorcist
            case 1182273: // Sengoku Hakase Badge
                if (ServerConstants.VERSION >= 199) {
                    return 22;
                }
        }
        return ServerConstants.VERSION >= 197 ? 25 : 15;
    }

    public static int getEquippedSummonSkillItem(int itemID, short job) {
        switch (itemID) {
            case 1112585:// Angelic Blessing
                return (SkillConstants.getNoviceSkillRoot(job) * 10000) + 1085;
            case 1112586:// Dark Angelic Blessing
                return (SkillConstants.getNoviceSkillRoot(job) * 10000) + 1087;
            case 1112594:// Snowdrop Angelic Blessing
                return (SkillConstants.getNoviceSkillRoot(job) * 10000) + 1090;
            case 1112663:// White Angelic Blessing
                return (SkillConstants.getNoviceSkillRoot(job) * 10000) + 1179;
            case 1112735:// White Angelic Blessing 2
                return 80001154;
            case 1113020:// Lightning God Ring
                return 80001262;
            case 1113173:// Lightning God Ring 2
                return 80011178;
            // Heaven Rings
            case 1112932:// Guard Ring
                return 80011149;
            case 1114232:// Sun Ring
                return 80010067;
            case 1114233:// Rain Ring
                return 80010068;
            case 1114234:// Rainbow Ring
                return 80010069;
            case 1114235:// Snow Ring
                return 80010070;
            case 1114236:// Lightning Ring
                return 80010071;
            case 1114237:// Wind Ring
                return 80010072;
        }
        return 0;
    }

    public static boolean isRecipeOpenItem(int itemID) {
        return itemID / 10000 == 251;
    }

    public static boolean isEtc(int itemID) {
        return itemID / 1000000 == 4;
    }

    public static boolean isConsume(int itemID) {
        return itemID / 1000000 == 2;
    }

    public static boolean isInstall(int itemID) {
        return itemID / 1000000 == 3;
    }

    public static boolean isChair(int itemID) {
        return itemID / 10000 == 301 || itemID / 1000 == 5204;
    }

    public static boolean isTextChair(int itemID) {
        return itemID / 1000 == 3014;
    }

    public static boolean isTowerChair(int itemID) {
        return itemID / 1000 == 3017;
    }

    public static boolean isMesoChair(int itemID) {
        return itemID == 3015650 || itemID == 3015651 || itemID == 3015440 || itemID == 3015897;
    }

    public static boolean isMachineArm(int itemID) {
        return itemID / 10000 == 162;
    }

    public static boolean isMachineLeg(int itemID) {
        return itemID / 10000 == 163;
    }

    public static boolean isBodyFrame(int itemID) {
        return itemID / 10000 == 164;
    }

    public static boolean isTransistor(int itemID) {
        return itemID / 10000 == 165;
    }

    public static boolean isHeart(int itemID) {
        return itemID / 10000 == 167;
    }

    public static boolean isDragonMask(int itemID) {
        return itemID / 10000 == 194;
    }

    public static boolean isDragonPendant(int itemID) {
        return itemID / 10000 == 195;
    }

    public static boolean isDragonWings(int itemID) {
        return itemID / 10000 == 196;
    }

    public static boolean isDragonTail(int itemID) {
        return itemID / 10000 == 197;
    }

    public static boolean isCard(int itemID) {
        return itemID / 10000 == 135 && itemID - 1350000 >= 2100 && itemID - 1350000 < 2200;
    }

    public static boolean isOrb(int itemID) {
        return itemID / 10000 == 135 && itemID - 1350000 >= 2400 && itemID - 1350000 < 2500;
    }

    public static boolean isDragonSoul(int itemID) {
        return itemID / 10000 == 135 && itemID - 1350000 >= 2500 && itemID - 1350000 < 2600;
    }

    public static boolean isSoulRing(int itemID) {
        return itemID / 10000 == 135 && itemID - 1350000 >= 2600 && itemID - 1350000 < 2700;
    }

    public static boolean isMagnum(int itemID) {
        return itemID / 10000 == 135 && itemID - 1350000 >= 2700 && itemID - 1350000 < 2800;
    }

    public static boolean isHeroMedal(int itemID) {
        return itemID / 10 == 135220;
    }

    public static boolean isPaladinRosario(int itemID) {
        return itemID / 10 == 135221;
    }

    public static boolean isDarkKnightChain(int itemID) {
        return itemID / 10 == 135222;
    }

    public static boolean isFpBook(int itemID) {
        return itemID / 10 == 135223;
    }

    public static boolean isIlBook(int itemID) {
        return itemID / 10 == 135224;
    }

    public static boolean isClericBook(int itemID) {
        return itemID / 10 == 135225;
    }

    public static boolean isBowmasterFeather(int itemID) {
        return itemID / 10 == 135226;
    }

    public static boolean isCrossbowMasterThimble(int itemID) {
        return itemID / 10 == 135227;
    }

    public static boolean isShadowerSheath(int itemID) {
        return itemID / 10 == 135228;
    }

    public static boolean isNightlordPouch(int itemID) {
        return itemID / 10 == 135229;
    }

    public static boolean isViperWristband(int itemID) {
        return itemID / 10 == 135290;
    }

    public static boolean isCaptainSight(int itemID) {
        return itemID / 10 == 135291;
    }

    public static boolean isCannonGunpowder(int itemID) {
        return itemID / 10 == 135292 || itemID / 10 == 135298;
    }

    public static boolean isBattleMageOrb(int itemID) {
        return itemID / 10 == 135295;
    }

    public static boolean isWildHunterArrowHead(int itemID) {
        return itemID / 10 == 135296;
    }

    public static boolean isAranPendulum(int itemID) {
        return itemID / 10 == 135293;
    }

    public static boolean isEvanPaper(int itemID) {
        return itemID / 10 == 135294;
    }

    public static boolean isCygnusGem(int itemID) {
        return itemID / 10 == 135297;
    }

    public static Set<DropInfo> getConsumableMobDrops(int level) {
        level = Math.min(100, (level / 20) * 20); // round it to the nearest 20th level + max of level 100
        return consumableDropsPerLevel.getOrDefault(level, new HashSet<>());
    }

    public static Set<DropInfo> getEquipMobDrops(short job, int level) {
        level = Math.min(140, (level / 10) * 10); // round it to the nearest 10th level + max of level 140
        ItemJob itemJob = GameConstants.getItemJobByJob(job);
        if (itemJob == null) {
            itemJob = ItemJob.BEGINNER;
        }
        return equipDropsPerLevel.getOrDefault(itemJob, new HashMap<>()).getOrDefault(level, new HashSet<>());
    }

    public static boolean isFamiliarSkillItem(int itemID) {
        return getItemPrefix(itemID) == 286;
    }

    public static boolean isRecoveryHPItem(int itemID) {
        return getItemPrefix(itemID) == 200;
    }

    public static boolean isRecoveryCureItem(int itemID) {
        return getItemPrefix(itemID) == 205;
    }

    public static boolean isRecoveryItem(int itemID) {
        return isRecoveryCureItem(itemID) || isRecoveryHPItem(itemID);
    }


    public static boolean isScroll(int itemID) {
        return getItemPrefix(itemID) == 204;
    }

    public static boolean isSkillBook(int itemID) {
        return getItemPrefix(itemID) == 229;
    }

    public static int getRequiredSymbolExp(int curLevel) {
        if (curLevel == 0 || curLevel >= MAX_ARCANE_SYMBOL_LEVEL) {
            return 0;
        }
        return curLevel * curLevel + 11;
    }

    public static long getSymbolMoneyReqByLevel(int level) {
        return 12440000 + level * 6600000;
    }

    public static int getSymbolAfByLevel(int level) {
        return 20 + level * 10;
    }

    public static int getSymbolStatByLevel(int level) {
        return 200 + level * 100;
    }

    public static int getSymbolXenonStatByLevel(int level) {
        return 78 + level * 39;
    }

    public static int getSymbolDaHpByLevel(int level) {
        return (2450) + level * 1750;
    }

    public static boolean isCashWeapon(int itemID) {
        return getItemPrefix(itemID) == EquipPrefix.CashWeapon.getVal();
    }

    public static boolean isIntensePowerCrystal(int itemId) {
        return itemId == 4001886;
    }

    public static String getDetailedItemCategoryString(int itemId) {
        if (itemId >= 1000000 && itemId <= 1009999) {
            return "Cap";
        }
        if (itemId >= 1040000 && itemId <= 1049999) {
            return "Coat";
        }
        if (itemId >= 1050000 && itemId <= 1059999) {
            return "Longcoat";
        }
        if (itemId >= 1060000 && itemId <= 1069999) {
            return "Pants";
        }
        if (itemId >= 1070000 && itemId <= 1079999) {
            return "Shoes";
        }
        if (itemId >= 1080000 && itemId <= 1089999) {
            return "Glove";
        }
        if (itemId >= 1090000 && itemId <= 1099999) {
            return "Shield";
        }
        if (itemId >= 1100000 && itemId <= 1109999) {
            return "Cape";
        }
        if (itemId >= 1010000 && itemId <= 1019999) {
            return "Face Accessory";
        }
        if (itemId >= 1020000 && itemId <= 1029999) {
            return "Eyewear";
        }
        if (itemId >= 1030000 && itemId <= 1039999) {
            return "Earrings";
        }
        if (itemId >= 1110000 && itemId <= 1119999) {
            return "Ring";
        }
        if (itemId >= 1120000 && itemId <= 1129999) {
            return "Pendant";
        }
        if (itemId >= 1130000 && itemId <= 1139999) {
            return "Belt";
        }
        if (itemId >= 1140000 && itemId <= 1149999) {
            return "Medal";
        }
        if (itemId >= 1150000 && itemId <= 1159999) {
            return "Shoulder Accessory";
        }
        if (itemId >= 1160000 && itemId <= 1169999) {
            return "Pocket Item";
        }
        if (itemId >= 1180000 && itemId <= 1189999) {
            return "Badge";
        }
        if (itemId >= 1190000 && itemId <= 1190199) {
            return "Emblem";
        }
        if (itemId >= 1190200 && itemId <= 1190299) {
            return "Power Source";
        }
        if (itemId >= 1610000 && itemId <= 1659999) {
            return "Mechanic Gear";
        }
        if (itemId >= 1660000 && itemId <= 1669999) {
            return "Android";
        }
        if (itemId >= 1670000 && itemId <= 1679999) {
            return "Mechanical Heart";
        }
        if (itemId >= 1800000 && itemId <= 1809999) {
            return "Pet Equipment";
        }
        if (itemId >= 1940000 && itemId <= 1979999) {
            return "Dragon Equipment";
        }
        if (itemId >= 1212000 && itemId <= 1212999) {
            return "Shining Rod";
        }





        if (itemId >= 1222000 && itemId <= 1222999) {
            return "Soul Shooter";
        }
        if (itemId >= 1232000 && itemId <= 1232999) {
            return "Desperado";
        }
        if (itemId >= 1242000 && itemId <= 1242999) {
            return "Whip Blade";
        }
        if (itemId >= 1300000 && itemId <= 1309999) {
            return "One-handed Sword";
        }
        if (itemId >= 1310000 && itemId <= 1319999) {
            return "One-handed Axe";
        }
        if (itemId >= 1320000 && itemId <= 1329999) {
            return "One-handed Blunt Weapon";
        }
        if (itemId >= 1330000 && itemId <= 1339999) {
            return "Dagger";
        }
        if (itemId >= 1340000 && itemId <= 1349999) {
            return "Katara";
        }
        if (itemId >= 1360000 && itemId <= 1369999) {
            return "Cane";
        }
        if (itemId >= 1370000 && itemId <= 1379999) {
            return "Wand";
        }
        if (itemId >= 1380000 && itemId <= 1389999) {
            return "Staff";
        }
        if (itemId >= 1262000 && itemId <= 1262999) {
            return "Psy-limiter";
        }




        if (itemId >= 1400000 && itemId <= 1409999) {
            return "Two-handed Sword";
        }
        if (itemId >= 1410000 && itemId <= 1419999) {
            return "Two-Handed Axe";
        }
        if (itemId >= 1420000 && itemId <= 1429999) {
            return "Two-Handed Blunt";
        }
        if (itemId >= 1430000 && itemId <= 1439999) {
            return "Spear";
        }
        if (itemId >= 1440000 && itemId <= 1449999) {
            return "Pole Arm";
        }
        if (itemId >= 1450000 && itemId <= 1459999) {
            return "Bow";
        }
        if (itemId >= 1460000 && itemId <= 1469999) {
            return "Crossbow";
        }
        if (itemId >= 1470000 && itemId <= 1479999) {
            return "Claw";
        }
        if (itemId >= 1480000 && itemId <= 1489999) {
            return "Knuckle";
        }
        if (itemId >= 1490000 && itemId <= 1499999) {
            return "Gun";
        }
        if (itemId >= 1520000 && itemId <= 1529999) {
            return "Dual Bowgun";
        }
        if (itemId >= 1530000 && itemId <= 1539999) {
            return "Hand Cannon";
        }





        if (itemId >= 1352000 && itemId <= 1352099) {
            return "Magic Arrow";
        }
        if (itemId >= 1352100 && itemId <= 1352199) {
            return "Card";
        }
        if (itemId >= 1353100 && itemId <= 1353199) {
            return "Fox Marble";
        }
        if (itemId >= 1352400 && itemId <= 1352499) {
            return "Orb";
        }
        if (itemId >= 1352200 && itemId <= 1352209) {
            return "Medal";
        }
        if (itemId >= 1352210 && itemId <= 1352219) {
            return "Rosary";
        }
        if (itemId >= 1352220 && itemId <= 1352229) {
            return "Iron Chain";
        }
        if (itemId >= 1352230 && itemId <= 1352259) {
            return "Spellbook";
        }
        if (itemId >= 1352260 && itemId <= 1352269) {
            return "Arrow Fletching";
        }
        if (itemId >= 1352270 && itemId <= 1352279) {
            return "Bow Thimble";
        }
        if (itemId >= 1352280 && itemId <= 1352289) {
            return "Dagger Scabbard";
        }
        if (itemId >= 1352290 && itemId <= 1352299) {
            return "Charm";
        }
        if (itemId >= 1352900 && itemId <= 1352909) {
            return "Wrist Band";
        }
        if (itemId >= 1352910 && itemId <= 1352919) {
            return "Far Sight";
        }
        if (itemId >= 1352930 && itemId <= 1352939) {
            return "Ballast";
        }
        if (itemId >= 1352940 && itemId <= 1352949) {
            return "Document";
        }
        if (itemId >= 1352950 && itemId <= 1352959) {
            return "Magic Marble";
        }
        if (itemId >= 1352960 && itemId <= 1352969) {
            return "Arrowhead";
        }
        if (itemId >= 1353000 && itemId <= 1353099) {
            return "Controller";
        }
        if (itemId >= 1352700 && itemId <= 1352799) {
            return "Magnum";
        }
        if (itemId >= 1352500 && itemId <= 1352599) {
            return "Nova Essence";
        }
        if (itemId >= 1352600 && itemId <= 1352699) {
            return "Soul Ring";
        }
        if (itemId >= 1353200 && itemId <= 1353299) {
            return "Chess Piece";
        }
        if (itemId >= 1352970 && itemId <= 1352979) {
            return "Jewel";
        }





        if (itemId >= 1352920 && itemId <= 1352929 || itemId >= 1352980 && itemId <= 1352989) {
            return "Powder Keg";
        }


        if (itemId >= 2000000 && itemId <= 2001499 || itemId >= 2003000 && itemId <= 2003499 || itemId >= 2010000 && itemId <= 2010999 || itemId >= 2020000 && itemId <= 2020999) {
            return "Recovery Item Potion";
        }

        if (itemId >= 2050000 && itemId <= 2059999) {
            return "Abnormal Status Recovery Potion";
        }

        if (itemId >= 2003500 && itemId <= 2003999) {
            return "Alchemy Boost Potion";
        }

        if (itemId >= 2004000 && itemId <= 2004999) {
            return "Alchemy Buff Potion";
        }


        if (itemId >= 2040000 && itemId <= 2041999 || itemId >= 2046200 && itemId <= 2046899 || itemId >= 2047900 && itemId <= 2047999 || itemId >= 2049200 && itemId <= 2049299) {
            return "Armor|Accessory Scroll";
        }

        if (itemId >= 2042000 && itemId <= 2046199 || itemId >= 2046900 && itemId <= 2046999 || itemId >= 2047800 && itemId <= 2047899) {
            return "Weapon Scroll";
        }

        if (itemId >= 2049300 && itemId <= 2049599 || itemId >= 2049700 && itemId <= 2049799) {
            return "Enhancement|Potential Scroll";
        }

        if (itemId >= 2049000 && itemId <= 2049199) {
            return "Clean Slate|Soul Weapon Scroll";
        }

        if (itemId >= 2048700 && itemId <= 2048799) {
            return "Rebirth Flame";
        }

        if (itemId >= 2048000 && itemId <= 2048099 || itemId >= 2048800 && itemId <= 2048899) {
            return "Pet Scroll";
        }

        if (itemId >= 2047000 && itemId <= 2047399 || itemId >= 2048100 && itemId <= 2048699 || itemId >= 2048900 && itemId <= 2048999 || itemId >= 2049600 && itemId <= 2049699 || itemId >= 2049000 && itemId <= 2049999) {
            return "Misc. Scroll";
        }

        if (itemId >= 2510000 && itemId <= 2510999) {
            return "Equipment Recipe";
        }

        if (itemId >= 2511000 && itemId <= 2511999) {
            return "Accessory Recipe";
        }

        if (itemId >= 2512000 && itemId <= 2512999) {
            return "Alchemy Recipe";
        }

        if (itemId >= 2280000 && itemId <= 2289999) {
            return "Skillbook";
        }

        if (itemId >= 2030000 && itemId <= 2031999) {
            return "Transport Item";
        }

        if (itemId >= 2060000 && itemId <= 2061999 || itemId >= 2070000 && itemId <= 2079999) {
            return "Arrow/Throwing Star";
        }

        if (itemId >= 2021000 && itemId <= 2029999 || itemId >= 2080000 && itemId <= 2109999 || itemId >= 2120000 && itemId <= 2999999) {
            return "Etc";
        }

        if (itemId >= 5000000 && itemId <= 5999999) {
            return "Cash";
        }

        if (itemId >= 3010000 && itemId <= 3013999) {
            return "Chair";
        }

        return "";
    }

    public static boolean isEffectRing(int itemid) {
        return isFriendshipRing(itemid) || isCrushRing(itemid) || isWeddingRing(itemid);
    }

    public static boolean isFriendshipRing(int itemid) {
        switch (itemid) {
            case 1112016:
            case 1112800:
            case 1112801:
            case 1112802:
            case 1112810:
            case 1112811:
            case 1112812:
            case 1112814:
            case 1112815:
            case 1112816:
            case 1112817:
            case 1112818:
                return true;
            case 1112803:
            case 1112804:
            case 1112805:
            case 1112806:
            case 1112807:
            case 1112808:
            case 1112809:

            case 1112813:
                // case 1112814:
                //    case 1112815:
        }
        return false;
    }

    public static boolean isCrushRing(int itemid) {
        switch (itemid) {
            case 1112001:
            case 1112002:
            case 1112003:
            case 1112005:
            case 1112006:
            case 1112007:
            case 1112012:
            case 1112013:
            case 1112014:
            case 1112015:
                return true;
            case 1112004:
            case 1112008:
            case 1112009:
            case 1112010:
            case 1112011:

        }
        return false;
    }

    public static boolean isWeddingRing(int itemid) {
        switch (itemid) {
            case 1112300:
            case 1112301:
            case 1112302:
            case 1112303:
            case 1112304:
            case 1112305:
            case 1112306:
            case 1112307:
            case 1112308:
            case 1112309:
            case 1112310:
            case 1112311:
            case 1112315:
            case 1112316:
            case 1112317:
            case 1112318:
            case 1112319:
            case 1112320:
            case 1112803:
            case 1112806:
            case 1112807:
            case 1112808:
            case 1112809:
                return true;
        }
        return false;
    }

    public static Boolean isRemovedFromCashShop(int id) { // add item ids you want gone from the dressing room
        switch (id) {
            case 1112016, 1112800, 1112801, 1112802, 1112810, 1112811, 1112812, 1112814, 1112815, 1112816, 1112817, 1112818,
                 1112803, 1112804, 1112807, 1112808, 1112809, 1112813, 1112001, 1112002, 1112003, 1112005, 1112006, 1112007,
                 1112012, 1112013, 1112014, 1112015, 1112004, 1112008, 1112009, 1112010, 1112011, 1112300, 1112301, 1112302,
                 1112303, 1112304, 1112305, 1112306, 1112307, 1112308, 1112309, 1112310, 1112311, 1112315, 1112316, 1112317,
                 1112318, 1112319, 1112320, 1112023, 1112964 -> {
                return true;
            }
        }
        return false;
    }
}
