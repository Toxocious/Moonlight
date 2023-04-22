package net.swordie.ms.world.auction.armor;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionEtcArmorType implements AuctionEnum {
    All,
    MechanicGear,
    Android,
    MechanicalHeart,
    DragonEquip,
    Totem;

    public static AuctionEtcArmorType getByVal(int val) {
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
            case All:
                return ItemConstants.isMachineArm(itemID) || ItemConstants.isMachineLeg(itemID) ||
                        ItemConstants.isBodyFrame(itemID) || ItemConstants.isTransistor(itemID) ||
                        ItemConstants.isMachineArm(itemID) || ItemConstants.isMachineLeg(itemID) ||
                        ItemConstants.isBodyFrame(itemID) || ItemConstants.isTransistor(itemID) ||
                        ItemConstants.isAndroid(itemID) || ItemConstants.isHeart(itemID) ||
                        ItemConstants.isDragonMask(itemID) || ItemConstants.isDragonPendant(itemID) ||
                        ItemConstants.isDragonWings(itemID) || ItemConstants.isDragonTail(itemID) ||
                        ItemConstants.isTotem(itemID);
            case MechanicGear:
                return ItemConstants.isMachineArm(itemID) || ItemConstants.isMachineLeg(itemID) ||
                        ItemConstants.isBodyFrame(itemID) || ItemConstants.isTransistor(itemID);
            case Android:
                return ItemConstants.isAndroid(itemID);
            case MechanicalHeart:
                return ItemConstants.isHeart(itemID);
            case DragonEquip:
                return ItemConstants.isDragonMask(itemID) || ItemConstants.isDragonPendant(itemID) ||
                        ItemConstants.isDragonWings(itemID) || ItemConstants.isDragonTail(itemID);
            case Totem:
                return ItemConstants.isTotem(itemID);
        }
        return false;
    }
}
