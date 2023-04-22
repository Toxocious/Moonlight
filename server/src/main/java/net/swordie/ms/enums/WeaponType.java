package net.swordie.ms.enums;

import java.util.Arrays;


/**
 * @author Sjonnie
 * Created on 8/30/2018.
 */
public enum WeaponType {
    // WT_ Enum, but renamed some to make more sense
    None(0),
    ShiningRod(21),
    SoulShooter(22),
    Desperado(23),
    ChainSword(24),
    Scepter(25),
    PsyLimiter(26),
    Chain(27), // Cadena
    Gauntlet(28), // Illium
    RitualFan(29), // HoYoung
    OneHandedSword(30),
    OneHandedAxe(31),
    OneHandedMace(32),
    Dagger(33),
    Katara(34),
    Secondary(35),
    Cane(36),
    Wand(37),
    Staff(38),
    Barehand(39),
    TwoHandedSword(40),
    TwoHandedAxe(41),
    TwoHandedMace(42),
    Spear(43),
    Polearm(44),
    Bow(45),
    Crossbow(46),
    Claw(47),
    Knuckle(48),
    Gun(49),
    DualBowgun(52),
    HandCannon(53),
    Katana(54),
    Fan(55),
    BigSword(56),
    LongSword(57),
    ArmCannon(58),
    AncientBow(59),
        ;

    private int val;

    WeaponType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static WeaponType getByVal(int val) {
        return Arrays.stream(values()).filter(wt -> wt.getVal() == val).findAny().orElse(null);
    }
}
