package net.swordie.ms.world.auction.weapon;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionTwoHandedWeaponType implements AuctionEnum {
    All,
    Sword,
    Axe,
    Blunt,
    Spear,
    Polearm,
    Bow,
    Crossbow,
    Claw,
    Knuckle,
    Gun,
    DualBowgun,
    HandCannon,
    ArmCannon,
    Katana,
    Fan;

    public static AuctionTwoHandedWeaponType getByVal(int val) {
        return values()[val];
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        int prefix = itemID / 10000;
        switch (this) {
            case All:
                return prefix == WeaponType.TwoHandedSword.getVal()
                    || prefix == WeaponType.TwoHandedAxe.getVal()
                    || prefix == WeaponType.TwoHandedMace.getVal()
                    || prefix == WeaponType.Spear.getVal()
                    || prefix == WeaponType.Polearm.getVal()
                    || prefix == WeaponType.Bow.getVal()
                    || prefix == WeaponType.Crossbow.getVal()
                    || prefix == WeaponType.Claw.getVal()
                    || prefix == WeaponType.Knuckle.getVal()
                    || prefix == WeaponType.Gun.getVal()
                    || prefix == WeaponType.DualBowgun.getVal()
                    || prefix == WeaponType.HandCannon.getVal()
                    || prefix == WeaponType.ArmCannon.getVal()
                    || prefix == WeaponType.Katana.getVal()
                    || prefix == WeaponType.Fan.getVal();
            case Sword:
                return prefix == WeaponType.TwoHandedSword.getVal();
            case Axe:
                return prefix == WeaponType.TwoHandedAxe.getVal();
            case Blunt:
                return prefix == WeaponType.TwoHandedMace.getVal();
            case Spear:
                return prefix == WeaponType.Spear.getVal();
            case Polearm:
                return prefix == WeaponType.Polearm.getVal();
            case Bow:
                return prefix == WeaponType.Bow.getVal();
            case Crossbow:
                return prefix == WeaponType.Crossbow.getVal();
            case Claw:
                return prefix == WeaponType.Claw.getVal();
            case Knuckle:
                return prefix == WeaponType.Knuckle.getVal();
            case Gun:
                return prefix == WeaponType.Gun.getVal();
            case DualBowgun:
                return prefix == WeaponType.DualBowgun.getVal();
            case HandCannon:
                return prefix == WeaponType.HandCannon.getVal();
            case ArmCannon:
                return prefix == WeaponType.ArmCannon.getVal();
            case Katana:
                return prefix == WeaponType.Katana.getVal();
            case Fan:
                return prefix == WeaponType.Fan.getVal();
        }
        return false;
    }
}
