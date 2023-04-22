package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

public enum BossMatchingType {
    Barlog(1, 105100100),
    Zakum_Easy(2, 211042300),
    Zakum_Normal(3, 211042300),
    Zakum_Chaos(4, 211042300),

//    Urus(5, 970072200), // uses packet MOVE_TO_URSUS_WAITING_FIELD

    Horntail_Easy(27, 240050400),
    Horntail_Normal(5, 240050400),
    Horntail_Chaos(6, 240050400),

    Hilla(7, 262030000),
    Hilla_Hard(8, 262030000),

    Pierre_Normal(9, 105200000),
    Pierre_Chaos(10, 105200000),
    VonBon_Normal(11, 105200000),
    VonBon_Chaos(12, 105200000),
    CrimsonQueen_Normal(13, 105200000),
    CrimsonQueen_Chaos(14, 105200000),
    Vellum_Normal(15, 105200000),
    Vellum_Chaos(16, 105200000),

    VonLeon_EasyNormal(18, 211070000), // Easy / Normal
    VonLeon_Hard(36, 211070000),

    Arkarium_Easy(19, 272020110),
    Arkarium_Normal(20, 272020110),

    PinkBean_Normal(24, 270050000),
    PinkBean_Chaos(25, 270050000),

//    Cygnus_Easy(17, 271040000), // uses packet MOVE_TO_URSUS_WAITING_FIELD
    Cygnus_Normal(26, 271040000),

    Lotus_Normal(29, 350060300),
    Lotus_Hard(28, 350060300),

    Damien_Normal(32, 105300303),
    Damien_Hard(31, 105300303),

    Lucid_Normal(34, 450004000),
    Lucid_Hard(35, 450004000),

    VerusHilla(43, -1),
    BlackMage(44, -1),
    Gloom(45, -1),
    Darknell(46, -1),

    Will_Normal(42, -1),
    Will_Hard(41, -1),

    Magnus_Easy(21, 401000000), // easy
    Magnus_Normal(22, 401060000), // normal/hard
    Magnus_Hard(23, 401060000), // normal/hard

    OMNI_CLN(37, -1),

    Papulatus_Easy(38, 220080000),
    Papulatus_Normal(39, 220080000),
    Papulatus_Chaos(40, 220080000),

    Gollux(106, 863010000),

    PrincessNo(108, 811000008),

    Ranmaru_Normal(107, 211041700),
    Ranmaru_Hard(109, 211041700),

    Julieta(112, -1),
    ;

    private final int val;
    private final int fieldID;

    BossMatchingType(int val, int fieldID) {
        this.val = val;
        this.fieldID = fieldID;
    }

    public static BossMatchingType getByVal(int val) {
        return Util.findWithPred(values(), v -> v.getVal() == val);
    }

    public int getVal() {
        return val;
    }

    public int getFieldID() {
        return fieldID;
    }
}
