package net.swordie.ms.enums;

import java.util.Arrays;

public enum ChairType {
    None(0),
    NormalChair(1), // 301XXXX or 5204XXX(CashChair)
    // custom chairs (info/customChair)
    TimeChair(2),
    PopChair(3),
    StarForceChair(4),
    TrickOrTreatChair(5),
    CelebChair(6),
    RandomChair(7),
    TraitsChair(8),
    IdentityChair(9),
    MirrorChair(10),
    PopButtonChair(11),
    RollingHouseChair(12),
    AndroidChair(13),
    MannequinChair(14),
    RotatedSleepingBagChair(15),
    EventPointChair(16),
    HashTagChair(17),
    PetChair(18),
    CharLvChair(19),
    ScoreChair(20),
    ArcaneForceChair(21),
    ScaleAvatarChair(22),
    // special chairs
    TowerChair(23), // 3017XXX
    TextChair(24), // 3014XXX
    MesoChair(25), // mesoChair
    LvChair(26), // lvChairInfo (Legion Chair)
    // custom chairs
    WasteChair(27),
    _2019RollingHouseChair(28);

    private final short val;

    ChairType(int val) {
        this.val = (short) val;
    }

    public short getVal() {
        return val;
    }

    public static ChairType getByVal(int val) {
        return Arrays.stream(values())
                .filter(type -> type.getVal() == val).findFirst().orElse(None);
    }

    public static ChairType getByName(String name) {
        return Arrays.stream(values())
                .filter(type -> type.toString().toLowerCase().contains(name.toLowerCase()))
                .findFirst().orElse(None);
    }
}
