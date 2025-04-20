package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * @author Sjonnie
 * Created on 8/2/2018.
 */
public enum DimensionalPortalType {
    AriantColiseum(0, 980010000, "Ariant Coliseum"),
    Dojo(1, 925020000, "Mu Lung Dojo"),
    GonzoGallery(2, 956000000, "Gonzo Gallery"),
    MonsterCarnival(3, 980000000, "Monster Carnival"),
    GhostShipSea(4, 0, "Ghost Ship Party Quest"),
    NettsPyramid(5, 926010000, "Nett's Pyramid"),
    DustyPlatform(6, 0, "Dusty Platform"),
    Happyville(7, 209000000, "Happyville"),
    GoldenTemple(8, 0, "Golden Temple"), // Unused
    MoonBunnyPQ(9, 0, "Moon Bunny Party Quest"),
    KerningCityPQ(10, 0, "Kerning City Party Quest"),
    LudiPQ(11, 0, "Ludibrium Party Quest"),
    ElinPQ(12, 0, "Ellin Forest Party Quest"),
    OrbisPQ(13, 0, "Orbis Party Quest"),
    PiratePQ(14, 0, "Pirate Party Quest"),
    RandJPQ(15, 0, "Romeo and Juliet Party Quest"),
    ElNathPQ(16, 0, "Hoblin King Party Quest"),
    DragonRiderPQ(17, 0, "Dragon Rider Party Quest"),
    PurpleTown(18, 0, "Purple Town"),
    HalloweenGPQ(19, 0, "Halloween Guild Party Quest"),
    PurpleTown2(20, 0, "Purple Town 2"),
    KentaPQ(21, 0, "Kenta Party Quest"),
    LionKingPQ(22, 0, "Lion King Party Quest"),
    IceKnightPQ(23, 0, "Ice Knight Party Quest"),
    MapleAlliance(25, 913050010, "Maple Alliance"),
    PurpleTown3(26, 0, "Purple Town 2"),
    Azwan(27, 0, "Azwan"),
    GoldenTemple2(28, 252000000, "Golden Temple"),
    Spiegelmann(29, 0, "Spiegelmann"),
    PvP(30, 0, "Player Versus Player"),
    Zipangu(31, 0, "Zipangu"),
    Evolution(32, 957000000, "Evolution System"),
    DimensionalInvasion(33, 940020000, "Dimensional Invasion"),
    BronzeGuestHouse(34, 0, "Bronze Guest House"),
    SilverGuestHouse(35, 0, "Silver Guest House"),
    GoldGuestHouse(36, 0, "Gold Guest House"),
    TangyoonCooking(37, 0, "Tangyoon Cooking"),
    CrimsonwoodKeep(38, 610030010, "Crimsonwood Keep"),
    GrandAthanaeum(39, 302000000, "The Grand Athanaeum"),
    SpecialMission(40, 0, "Special Mission"),
    PartyQuestHub(41, 910002000, "Party Quest Hub"), // Spiegelmann's quest house
    RootAbyss(42, 910700200, "Root Abyss"),
    TowerOfOz(43, 992000000, "Tower of Oz"),
    FriendStory(44, 0, "Friend Story"),
    MonsterPark(45, 951000000, "Monster Park"),
    WeirdMob(46, 0, "Weird Mob"),
    Ursus(47, 0, "Ursus"),
    Cygnus(49, 0, "Cygnus Empress"),
    Maplerunner(50, 993001000, "Maple Runner");

    private int val;
    private int mapID;
    private String desc;

    DimensionalPortalType(int val, int mapID, String desc) {
        this.val = val;
        this.mapID = mapID;
        this.desc = desc;
    }

    public int getVal() {
        return val;
    }

    public int getMapID() {
        return mapID;
    }

    public String getDesc() {
        return desc;
    }

    public static DimensionalPortalType getByVal(int val) {
        return Arrays.stream(values()).filter(dpt -> dpt.getVal() == val).findAny().orElse(null);
    }
}
