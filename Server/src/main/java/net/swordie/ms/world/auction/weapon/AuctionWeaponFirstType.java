package net.swordie.ms.world.auction.weapon;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionWeaponFirstType implements AuctionEnum {
    All,
    OneHanded,
    TwoHanded,
    Secondary;

    public static AuctionWeaponFirstType getByVal(int val) {
        return values()[val];
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case OneHanded:
                return AuctionOneHandedWeaponType.getByVal(val);
            case TwoHanded:
                return AuctionTwoHandedWeaponType.getByVal(val);
            case Secondary:
                return AuctionSecondaryType.getByVal(val);
        }
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        // just takes the sub-matching one anyway
        return true;
    }
}
