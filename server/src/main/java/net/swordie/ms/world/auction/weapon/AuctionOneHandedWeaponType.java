package net.swordie.ms.world.auction.weapon;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionOneHandedWeaponType implements AuctionEnum {
    All,
    ShiningRod,
    SoulShooter,
    Desperado,
    WhipBlade,
    Sword,
    Axe,
    Blunt,
    Dagger,
    Katara,
    Cane,
    Wand,
    Staff,
    PsyLimiter,
    Chain,
    LucentGauntlet,
    Scepter;

    public static AuctionOneHandedWeaponType getByVal(int val) {
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
                return prefix == WeaponType.ShiningRod.getVal()
                    || prefix == WeaponType.SoulShooter.getVal()
                    || prefix == WeaponType.Desperado.getVal()
                    || prefix == WeaponType.ChainSword.getVal()
                    || prefix == WeaponType.OneHandedSword.getVal()
                    || prefix == WeaponType.OneHandedAxe.getVal()
                    || prefix == WeaponType.OneHandedMace.getVal()
                    || prefix == WeaponType.Dagger.getVal()
                    || prefix == WeaponType.Katara.getVal()
                    || prefix == WeaponType.Cane.getVal()
                    || prefix == WeaponType.Wand.getVal()
                    || prefix == WeaponType.Staff.getVal()
                    || prefix == WeaponType.PsyLimiter.getVal()
                    || prefix == WeaponType.Chain.getVal()
                    || prefix == WeaponType.Gauntlet.getVal()
                    || prefix == WeaponType.Scepter.getVal();
            case ShiningRod:
                return prefix == WeaponType.ShiningRod.getVal();
            case SoulShooter:
                return prefix == WeaponType.SoulShooter.getVal();
            case Desperado:
                return prefix == WeaponType.Desperado.getVal();
            case WhipBlade:
                return prefix == WeaponType.ChainSword.getVal();
            case Sword:
                return prefix == WeaponType.OneHandedSword.getVal();
            case Axe:
                return prefix == WeaponType.OneHandedAxe.getVal();
            case Blunt:
                return prefix == WeaponType.OneHandedMace.getVal();
            case Dagger:
                return prefix == WeaponType.Dagger.getVal();
            case Katara:
                return prefix == WeaponType.Katara.getVal();
            case Cane:
                return prefix == WeaponType.Cane.getVal();
            case Wand:
                return prefix == WeaponType.Wand.getVal();
            case Staff:
                return prefix == WeaponType.Staff.getVal();
            case PsyLimiter:
                return prefix == WeaponType.PsyLimiter.getVal();
            case Chain:
                return prefix == WeaponType.Chain.getVal();
            case LucentGauntlet:
                return prefix == WeaponType.Gauntlet.getVal();
            case Scepter:
                return prefix == WeaponType.Scepter.getVal();
        }
        return false;
    }
}
