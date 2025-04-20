package net.swordie.ms.world.auction.armor;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionAccType implements AuctionEnum {
    All,
    FaceAcc,
    Eye,
    Earring,
    Ring,
    Pendant,
    Belt,
    Medal,
    Shoulder,
    Pocket,
    Badge,
    Emblem,
    PowerSource;

    public static AuctionAccType getByVal(int val) {
        return values()[val];
    }


    @Override
    public AuctionEnum getSubByVal(int val) {
        return val >= 0 && val < values().length ? values()[val] : All;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        switch (this) {
            case All:
                return ItemConstants.isAccessory(itemID);
            case FaceAcc:
                return ItemConstants.isFaceAccessory(itemID);
            case Eye:
                return ItemConstants.isEyeAccessory(itemID);
            case Earring:
                return ItemConstants.isEarrings(itemID);
            case Ring:
                return ItemConstants.isRing(itemID);
            case Pendant:
                return ItemConstants.isPendant(itemID);
            case Belt:
                return ItemConstants.isBelt(itemID);
            case Medal:
                return ItemConstants.isMedal(itemID);
            case Shoulder:
                return ItemConstants.isShoulder(itemID);
            case Pocket:
                return ItemConstants.isPocketItem(itemID);
            case Badge:
                return ItemConstants.isBadge(itemID);
            case Emblem:
                return ItemConstants.isEmblem(itemID);
            case PowerSource: // what is this?
                return false;
        }
        return false;
    }
}
