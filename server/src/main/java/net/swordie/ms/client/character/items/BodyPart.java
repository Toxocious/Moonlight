package net.swordie.ms.client.character.items;

import java.util.Arrays;

/**
 * Created on 1/7/2018.
 */
public enum BodyPart {
    BPBase(0),
    Hair(0),
    Hat(1),
    FaceAccessory(2),
    EyeAccessory(3),
    Earrings(4),
    Top(5),
    Overall(5), // Top and overall share the same body part
    Bottom(6),
    Shoes(7),
    Gloves(8),
    Cape(9),
    Shield(10), // Includes things such as katara, 2ndary
    Weapon(11),
    Ring1(12),
    Ring2(13),
    PetWear1(14),
    Ring3(15),
    Ring4(16),
    Pendant(17),
    TamingMob(18),
    Saddle(19),
    MobEquip(20),
    Medal(49),
    Belt(50),
    Shoulder(51),
    PetWear2(24),
    PetWear3(25),
    PocketItem(52),
    Android_OLD(27),
    MechanicalHeart_OLD(28),
    Badge(56),
    Emblem(61),
    Extended0(31),
    Extended1(32),
    Extended2(33),
    Extended3(34),
    Extended4(35),
    Extended5(36),
    Extended6(37),
    Android(53),
    MechanicalHeart(54),
    MonsterBook(55),
    ExtendedPendant(65),
    Sticker(100),
    BPEnd(66), // 1~65
    CBPBase(101), // CASH
    CBPEnd(166), // 101~165
    PetConsumeHPItem(200),
    PetConsumeMPItem(201),
    EvanBase(1000),
    EvanHat(1000),
    EvanPendant(1001),
    EvanWing(1002),
    EvanShoes(1003),
    EvanEnd(1004),
    MechBase(1100),
    MachineEngine(1100),
    MachineArm(1101),
    MachineLeg(1102),
    MachineFrame(1103),
    MachineTransistor(1104),
    MechEnd(1105),
    APBase(1200),
    APHat(1200),
    APCape(1201),
    APFaceAccessory(1202),
    APTop(1203),
    APOverall(1203),
    APBottom(1204),
    APShoes(1205),
    APGloves(1206),
    APEnd(1207),
    DUBase(1300),
    DUHat(1300),
    DUCape(1301),
    DUFaceAccessory(1302),
    DUGloves(1304),
    DUEyeAccessory(1305),
    DUEarrings(1306),
    DUTop(1307),
    DUOverall(1307),
    DUBottom(1308),
    DUShoes(1309),
    DUEnd(1310),
    BitsBase(1400), // 1400~1424
    BitsEnd(1425),
    ZeroBase(1500),
    ZeroEyeAccessory(1500),
    ZeroHat(1501),
    ZeroFaceAccessory(1502),
    ZeroEarrings(1503),
    ZeroCape(1504),
    ZeroTop(1505),
    ZeroOverall(1505),
    ZeroGloves(1506),
    ZeroWeapon(1507),
    ZeroBottom(1508),
    ZeroShoes(1509),
    ZeroRing1(1510),
    ZeroRing2(1511),
    ZeroPendant1(1512), // ?
    ZeroPendant2(1513), // ?
    ZeroEnd(1514),
    AFBase(1600),
    AFVanishinJourney(1600),
    AFChuChu(1601),
    AFLachelein(1602),
    AFArcana(1603),
    AFMorass(1604),
    AFEsfera(1605),
    AFEnd(1606),
    TotemBase(5000),
    Totem1(5000),
    Totem2(5001),
    Totem3(5002),
    TotemEnd(5003),
    MBPBase(5100),
    MBPHat(5101),
    MBPCape(5102),
    MBPTop(5103),
    MBPOverall(5103),
    MBPGloves(5104),
    MBPShoes(5105),
    MBPWeapon(5106),
    MBPEnd(5106), // ?
    HakuStart(5200),
    HakuFan(5200),
    HakuEnd(5201),
    VEIBase(20000),
    VEIEnd(20024),
    SlotIndexNotDefined(15440);

    private int val;

    BodyPart(int val) {
        this.val = val;
    }

    public static BodyPart getByVal(int bodyPartVal) {
        return Arrays.stream(values()).filter(bp -> bp.getVal() == bodyPartVal).findAny().orElse(null);
    }

    public int getVal() {
        return val;
    }
}
