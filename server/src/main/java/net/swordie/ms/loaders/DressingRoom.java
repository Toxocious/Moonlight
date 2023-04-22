package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.constants.ItemConstants;
import org.apache.log4j.LogManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DressingRoom {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();


    public static List<Equip> hats = new ArrayList<>();
    public static List<Equip> tops = new ArrayList<>();
    public static List<Equip> bottoms = new ArrayList<>();
    public static List<Equip> overalls = new ArrayList<>();
    public static List<Equip> shoes = new ArrayList<>();
    public static List<Equip> gloves = new ArrayList<>();
    public static List<Equip> capes = new ArrayList<>();
    public static List<Equip> weapons = new ArrayList<>();
    public static List<Equip> faceAccessory = new ArrayList<>();
    public static List<Equip> eyeAccessory = new ArrayList<>();
    public static List<Equip> rings = new ArrayList<>();

    public enum DressingRoomType {
        Hats(0),
        Tops(1),
        Bottoms(2),
        Overalls(3),
        Shoes(4),
        Gloves(5),
        Capes(6),
        Weapons(7),
        FaceAccessory(8),
        EyeAccessory(9),
        Rings(10);

        private final int id;

        DressingRoomType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static DressingRoomType getTypeById(int id) {
            return Arrays.stream(values()).filter(fae -> fae.getId() == id).findFirst().orElse(null);
        }
    }

    public DressingRoom() {
    }

    public static void load() {
        long start = System.currentTimeMillis();
        File dir =  new File(String.format("%s/equips/", ServerConstants.DAT_DIR));
        for (File file : dir.listFiles()) {
            Equip equip = ItemData.readEquipFromFile(file);

            if (equip.isCash() && !ItemConstants.isRemovedFromCashShop(equip.getItemId())) {
                if (ItemConstants.isHat(equip.getItemId())) {
                    hats.add(equip);
                } else if (ItemConstants.isTop(equip.getItemId())) {
                    tops.add(equip);
                } else if (ItemConstants.isBottom(equip.getItemId())) {
                    bottoms.add(equip);
                } else if (ItemConstants.isOverall(equip.getItemId())) {
                    overalls.add(equip);
                } else if (ItemConstants.isShoe(equip.getItemId())) {
                    shoes.add(equip);
                } else if (ItemConstants.isGlove(equip.getItemId())) {
                    gloves.add(equip);
                } else if (ItemConstants.isCape(equip.getItemId())) {
                    capes.add(equip);
                } else if (ItemConstants.isWeapon(equip.getItemId())) {
                    weapons.add(equip);
                } else if (ItemConstants.isFaceAccessory(equip.getItemId())) {
                    faceAccessory.add(equip);
                } else if (ItemConstants.isEyeAccessory(equip.getItemId())) {
                    eyeAccessory.add(equip);
                } else if (ItemConstants.isRing(equip.getItemId())) {
                    rings.add(equip);
                }
            }
        }
        log.info(String.format("Loaded Dressing Room in %dms.", System.currentTimeMillis() - start));
    }

    public static List<Equip> getHats() {
        return hats;
    }

    public static void setHats(List<Equip> hats) {
        DressingRoom.hats = hats;
    }

    public static List<Equip> getTops() {
        return tops;
    }

    public static void setTops(List<Equip> tops) {
        DressingRoom.tops = tops;
    }

    public static List<Equip> getBottoms() {
        return bottoms;
    }

    public static void setBottoms(List<Equip> bottoms) {
        DressingRoom.bottoms = bottoms;
    }

    public static List<Equip> getOveralls() {
        return overalls;
    }

    public static void setOveralls(List<Equip> overalls) {
        DressingRoom.overalls = overalls;
    }

    public static List<Equip> getShoes() {
        return shoes;
    }

    public static void setShoes(List<Equip> shoes) {
        DressingRoom.shoes = shoes;
    }

    public static List<Equip> getGloves() {
        return gloves;
    }

    public static void setGloves(List<Equip> gloves) {
        DressingRoom.gloves = gloves;
    }

    public static List<Equip> getCapes() {
        return capes;
    }

    public static void setCapes(List<Equip> capes) {
        DressingRoom.capes = capes;
    }

    public static List<Equip> getWeapons() {
        return weapons;
    }

    public static void setWeapons(List<Equip> weapons) {
        DressingRoom.weapons = weapons;
    }

    public static List<Equip> getFaceAccessory() {
        return faceAccessory;
    }

    public static void setFaceAccessory(List<Equip> faceAccessory) {
        DressingRoom.faceAccessory = faceAccessory;
    }

    public static List<Equip> getEyeAccessory() {
        return eyeAccessory;
    }

    public static void setEyeAccessory(List<Equip> faceAccessory) {
        DressingRoom.eyeAccessory = eyeAccessory;
    }

    public static List<Equip> getRings() {
        return rings;
    }

    public static void setRings(List<Equip> rings) {
        DressingRoom.rings = rings;
    }


    /*
    {"Accessory", "Android", "Cap", "Cape", "Coat", "Dragon", "Face", "Glove",
     "Longcoat", "Mechanic", "Pants", "PetEquip", "Ring", "Shield", "Shoes", "Totem", "Weapon", "MonsterBook",
     "ArcaneForce"};
     */
}
