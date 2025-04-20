package net.swordie.ms.enums;

import java.util.Arrays;
import java.util.List;

public enum DimensionalMirrorType {
    MuLungDojo(1, 0, "Challenge Mu Lung Dojo and see how strong you REALLY are.", 105, 0, false, Arrays.asList(1082392, 1082393, 1082394), 925020000),
    EreveConference(2, 1, "The Maple Alliance has been formed at the Continental Conference. Battle the Black Mage in 'Black Heaven' and 'Heroes of Maple'!", 75, 0, false, Arrays.asList(1142769, 1142804, 3015030, 3700350, 3017016), 913050010),
    Azwan(3, 2, "Eliminate the remainder of Hilla's forces in the Azwan area", 120, 0, false, Arrays.asList(1162009, 2430690, 1032136), 262000000),
    EvoLab(4, 3, "Ready for the wonders of the Evolution System?", 105, 1801, false, Arrays.asList(1162013, 4000999, 2431789, 2431790), 957000000),
    DimensionInvasion(5, 4, "Defeat the invaders before it's too late!", 140, 0, false, Arrays.asList(1082488, 1082489, 1142527, 1142528, 1142529, 1142530), 940020000),
    CrimsonHeartCastle(6,5, "A message from Masteria... What's going on?", 130, 31241, false, Arrays.asList(1142619, 2431789, 2431790), 301000000),
    GrandAthenaeum(7, 6, "Head to the Grand Athenaeum to get a glimpse of the past.", 100, 0, false, Arrays.asList(1122263, 2431789), 302000000),
    PartyQuestEntry(8, 7,"It's more fun when you play with friends! Create a party to participate in a special mission.", 50, 0, true, Arrays.asList(1003762, 1022073, 1132013, 1022175, 2430335, 2432131), 910002000),
    TowerOfOz(9, 8, "Conquer the underwater tower known as the Tower f Oz to help Alicia's Soul.", 140, 42009, false, Arrays.asList(2028263, 2432465), 992000000),
    FriendStory(10, 9, "Experience FriendStory, the first spin-off of MapleStory!", 100, 32707, false, Arrays.asList(1182079, 3015119, 3010875, 2432776, 2432788), 100000004),
    MonsterPark(11, 10, "Massive EXP and different daily rewards await you!", 105, 0, false, Arrays.asList(2434746, 2434747, 2434748, 2434749, 2434750, 2434751, 2434745), 951000000),
    RootAbyss(12, 11, "Battle the seal guardians to weaken them. The future of Maple World is in your hands", 125, 30000, false, Arrays.asList(1003715, 1003716, 1003717, 1003718), 105200000),
    Ursus(13, 12, "Think you can take on Ursus the Destroyer?", 100, 0, true, Arrays.asList(3015279, 2434509, 2434389, 3700334, 1142879), 970072200),
    TwistedAquaRoad(14, 500, "Do you want to move to Twisted Aqua Road?", 200, 17100, false, Arrays.asList(), 860000000),
    PrincessNo(15, 501, "Do you want to challenge Princess No?", 140, 58913, true, Arrays.asList(2432755, 2432754, 2432753, 3010864), 811000999),
    Alishan(16, 502, "Do you want to move to Alishan?", 33, 55234, false, Arrays.asList(1202160, 2434004), 749080900),
    EventHall(17, 503, "Do you want to move to the Even Hall?", 1, 0, false, Arrays.asList(), 820000000),
    AlienVisitor(18, 504, "Do you want to move to Alien Visitor", 200, 0, false, Arrays.asList(), 861000000),
    Commerci(19, 505, "Do you want to move to Commerci Republic?", 140, 17600, false, Arrays.asList(), 865010200),
    Momijigaoka(20, 506, "Do you want to move to Momijigaoka?", 13, 0, false, Arrays.asList(), 807000000),
    BlackGate(21, 507, "Do you want to move to Blackgate City?", 100, 0, false, Arrays.asList(), 610050000),
    Afterlands(22, 508, "Do you want to move to Afterlands?", 75, 63020, false, Arrays.asList(1202237, 1202238, 1202239, 1202240), 867113100),
    MushroomShrine(23, 509,  "Do you want to move to Mushroom shrine where strange stories are hidden.", 10, 0, false, Arrays.asList(1102887, 2047983, 2047982, 2047981, 4000999), 800000000),
    AbrupBaseCamp(24, 510, "The people of Abrup are waiting for you.", 33, 64010, false, Arrays.asList(1005191, 2439151, 1143117, 3018143, 1143118, 2028372, 2048716), 867200111);

    private int Pos;
    private int ID;
    private String desc;
    private int reqLv;
    private int questID; //must first complete xxx quest 0 for none
    private boolean isParty;
    private List<Integer> items;
    private int warpTo;
    DimensionalMirrorType(int Pos, int ID, String desc, int reqLv, int questID, boolean isParty, List<Integer> items, int warpTo) {
        this.Pos = Pos;
        this.ID = ID;
        this.desc = desc;
        this.reqLv =reqLv;
        this.questID = questID;
        this.isParty = isParty;
        this.items = items;
        this.warpTo = warpTo;
    }

    public int getPos(){return Pos;}
    public int getID() {
        return ID;
    }

    public String getDesc() {
        return desc;
    }

    public int getReqLv() {
        return reqLv;
    }

    public int getQuestID() {
        return questID;
    }

    public boolean isParty() {
        return isParty;
    }

    public List<Integer> getItems() {
        return items;
    }
    public int getWarpTo(){return warpTo;}
//public static DimensionalMirrorType getByVal(int val) {
      //  return Arrays.stream(values()).filter(dpt -> dpt.getVal() == val).findAny().orElse(null);
   // }
}
