package net.swordie.ms.enums;

import java.util.Arrays;

public enum DimensionalPortalTownType
{
    SixPathCrossway(0, 104020000, "Six Path Crossway"),
    Henesys(1, 100000000, "Henesys"),
    Ellinia(2, 101000000, "Ellinia"),
    Perion(3, 102000000, "Perion"),
    KerningCity(4, 103000000, "Kerning City"),
    LithHarbor(5, 104000000, "Lith Harbor"),
    Sleepywood(6, 105000000, "Sleepywood"),
    Nautilus(7, 120000000, "Nautilus"),
    Ereve(8, 130000000, "Ereve"),
    Rien(9, 140000000, "Rien"),
    Orbis(10, 200000000, "Orbis"),
    ElNath(11,211000000, "El Nath"),
    Ludibrium(12, 220000000, "Ludibrium"),
    OmegaSector(13, 221000000, "Omega Sector"),
    KoreanFolkTown(14, 222000000, "Korean Folk Town"),
    Aquarium(15, 230000000, "Aquarium"),
    Leafre(16, 240000000, "Leafre"),
    MuLung(17, 250000000, "Mu Lung"),
    HerbTown(18, 251000000, "Herb Town"),
    Ariant(19, 260000000, "Ariant"),
    Magatia(20, 261000000, "Magatia"),
    Edelstein(21, 310000000, "Edelstein"),
    Elluel(22, 101050000, "Elluel"),
    Kritias(23, 241000000, "Kritias"),
    Haven(24, 310070000, "Haven"),
    DesertedCamp(26, 105300000, "Deserted Camp"),
    SavageTerminal(27, 402000000, "Savage Terminal");


    private int val;
    private int mapID;
    private String desc;
    DimensionalPortalTownType(int val, int mapID, String desc)
    {
        this.val = val;
        this.mapID = mapID;
        this.desc = desc;
    }
    public int getVal() {return val;}
    public int getMapID() {return mapID; }
    public String getDesc() {return desc; }

    public static DimensionalPortalTownType getByVal(int val) {
        return Arrays.stream(values()).filter(dpt -> dpt.getVal() == val).findAny().orElse(null);
    }
}
