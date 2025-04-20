package net.swordie.ms.world.auction.armor;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionArmorFirstType implements AuctionEnum {
    All,
    Armor,
    Accessory,
    Etc;

    public static AuctionArmorFirstType getByVal(int val) {
        return values()[val];
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case Armor:
                return AuctionArmorType.getByVal(val);
            case Accessory:
                return AuctionAccType.getByVal(val);
            case Etc:
                return AuctionEtcArmorType.getByVal(val);
        }
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        switch (this) {
            case All:
                return ItemConstants.isEquip(itemID) && !ItemConstants.isWeapon(itemID);
            case Armor:
                return ItemConstants.isArmor(itemID) && !ItemConstants.isWeapon(itemID) && !ItemConstants.isAccessory(itemID);
            case Accessory:
                return ItemConstants.isAccessory(itemID);
            case Etc:
                return ItemConstants.isEquip(itemID) && !(ItemConstants.isWeapon(itemID) || ItemConstants.isAccessory(itemID)
                    || ItemConstants.isArmor(itemID));
        }
        return false;
    }
}
