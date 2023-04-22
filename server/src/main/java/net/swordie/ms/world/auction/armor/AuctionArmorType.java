package net.swordie.ms.world.auction.armor;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionArmorType implements AuctionEnum {
    Hat,
    Top,
    Outfit,
    Bottom,
    Shoes,
    Gloves,
    Shield,
    Cape;

    public static AuctionArmorType getByVal(int val) {
        return values()[val];
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        switch (this) {
            case Hat:
                return ItemConstants.isHat(itemID);
            case Top:
                return ItemConstants.isTop(itemID);
            case Outfit:
                return ItemConstants.isOverall(itemID);
            case Bottom:
                return ItemConstants.isBottom(itemID);
            case Shoes:
                return ItemConstants.isShoe(itemID);
            case Gloves:
                return ItemConstants.isGlove(itemID);
            case Shield:
                return ItemConstants.isShield(itemID);
            case Cape:
                return ItemConstants.isCape(itemID);
        }
        return false;
    }
}
