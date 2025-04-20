package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.loaders.containerclasses.Cosmetic;
import org.apache.log4j.LogManager;

import java.io.File;
import java.util.*;

public class StyleRoom {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static List<List<Cosmetic>> maleHair = new ArrayList<>();
    public static List<List<Cosmetic>> femaleHair = new ArrayList<>();
    public static List<List<Cosmetic>> specialHair = new ArrayList<>();
    public static List<List<Cosmetic>> maleFace = new ArrayList<>();
    public static List<List<Cosmetic>> femaleFace = new ArrayList<>();

    public static Set<Integer> mHairBase = new HashSet<>(
            Arrays.asList(30000, 33000, 35000, 36000, 40000, 43000, 45000, 46000/*, 60000*/)
    );

    public static Set<Integer> fHairBase = new HashSet<>(
            Arrays.asList(31000, 34000, 37000, 38000, 41000, 44000, 47000, 48000/*, 61000*/)
    );

    public static Set<Integer> sHairBase = new HashSet<>(
            Arrays.asList(32000, 39000, 42000)
    );

    public static Set<Integer> mFaceBase = new HashSet<>(
            Arrays.asList(20000, 23000, 25000, 27000, 50000, 53000)
    );

    public static Set<Integer> fFaceBase = new HashSet<>(
            Arrays.asList(21000, 24000, 26000, 28000, 51000, 54000)
    );

    public final Integer itemsPerPage = 50;

    public enum StyleRoomType {
        Hair(0),
        Face(1);

        private final int id;

        StyleRoomType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static StyleRoom.StyleRoomType getTypeById(int id) {
            return Arrays.stream(values()).filter(i -> i.getId() == id).findFirst().orElse(null);
        }
    }

    public StyleRoom() {
    }

    public static void load() {
        long start = System.currentTimeMillis();

        // Load all Hair and Face from XML wz
        String wzDir = ServerConstants.WZ_DIR + "/Character.wz";
        String[] subMaps = new String[] {"Hair", "Face"};
        for (String map : subMaps) {
            File subDir = new File(String.format("%s/%s", wzDir, map));
            File[] files = subDir.listFiles();
            HashSet<String> set = new HashSet<>();

            if (files != null) {
                for (File file : files) {
                    String name = file.getName().substring(3, file.getName().indexOf('.'));
                    set.add(name);
                }
            }

            if (map.equals("Hair")) {
                maleHair = LoadHair(set, mHairBase);
                femaleHair = LoadHair(set, fHairBase);
                specialHair = LoadHair(set, sHairBase);
            } else if (map.equals("Face")) {
                maleFace = LoadFace(set, mFaceBase);
                femaleFace = LoadFace(set, fFaceBase);
            }
        }

        log.info(String.format("Loaded Style Room in %dms.", System.currentTimeMillis() - start));
    }

    public static List<List<Cosmetic>> LoadHair(Set<String> setString, Set<Integer> hairSet) {
        // extremely lazy way of doing this, probably need to do mod calculations, but I'm hella lazy
        List<List<Cosmetic>> fullHairList = new ArrayList<>();
        for (Integer baseId : hairSet) {
            String full = Integer.toString(baseId);
            String prefix = full.substring(0, 2);
            List<Cosmetic> hairList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                String paddedId = String.format("%02d", i);
                String fullString = prefix + paddedId + "0";
                int fullId = Integer.parseInt(fullString);
                if (setString.contains(fullString)) {
                    hairList.add(new Cosmetic(fullId, StringData.getItemStringById(fullId)));
                }
            }
            if (hairList.size() > 0) {
                fullHairList.add(hairList);
            }
        }

        return fullHairList;
    }

    public static List<List<Cosmetic>> LoadFace(Set<String> setString, Set<Integer> faceSet) {

        List<List<Cosmetic>> fullFaceList = new ArrayList<>();
        for (Integer baseId : faceSet) {
            String full = Integer.toString(baseId);
            String prefix = full.substring(0, 2);
            List<Cosmetic> faceList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                String paddedId = String.format("%02d", i);
                String fullString = prefix + "0" + paddedId;
                int fullId = Integer.parseInt(fullString);
                if (setString.contains(fullString)) {
                    faceList.add(new Cosmetic(fullId, StringData.getItemStringById(fullId)));
                }
            }
            if (faceList.size() > 0) {
                fullFaceList.add(faceList);
            }
        }

        return fullFaceList;
    }



    public static List<List<Cosmetic>> getMaleHair() {
        return maleHair;
    }

    public static List<List<Cosmetic>> getFemaleHair() {
        return femaleHair;
    }

    public static List<List<Cosmetic>> getSpecialHair() {
        return specialHair;
    }

    public static List<List<Cosmetic>> getMaleFace() {
        return maleFace;
    }

    public static List<List<Cosmetic>> getFemaleFace() {
        return femaleFace;
    }

    public static void main(String[] args) {
        load();
    }
}


/*
# Hair
# Male: 30000, 33000, 35000, 36000, 40000, 43000, 45000, 46000, 60000
# Female: 31000, 34000, 37000, 38000, 41000, 44000, 47000, 48000, 61000
# Unisex/Special: 32000, 39000, 42000

# Face
# Male: 20000, 23000, 25000, 27000, 50000, 53000
# Female: 21000, 24000, 26000, 28000, 51000, 54000

XX: Prefix
Y: Color
ZZ: Actual face
 */
