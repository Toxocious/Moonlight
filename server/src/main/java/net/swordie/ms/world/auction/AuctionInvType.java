package net.swordie.ms.world.auction;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.auction.armor.AuctionArmorFirstType;
import net.swordie.ms.world.auction.cash.AuctionCashType;
import net.swordie.ms.world.auction.etc.AuctionEtcType;
import net.swordie.ms.world.auction.use.AuctionConsumeEtcType;
import net.swordie.ms.world.auction.use.AuctionUseType;
import net.swordie.ms.world.auction.weapon.AuctionWeaponFirstType;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public enum AuctionInvType implements AuctionEnum {
    All(-1),
    Armor(0),
    Weapons(1),
    Consume(2),
    Cash(3),
    Etc(4);

    private int val;

    AuctionInvType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static AuctionInvType getByVal(int val) {
        return Util.findWithPred(values(), type -> type.getVal() == val);
    }

    @Override
    public boolean isMatching(Item item) {
        int itemID = item.getItemId();
        InvType type = item.getInvType();
        switch (this) {
            case All:
                return true;
            case Armor:
                return ItemConstants.isEquip(itemID) && !ItemConstants.isWeapon(itemID)
                        && !ItemConstants.isSecondary(itemID) && !ItemConstants.isShield(itemID);
            case Weapons:
                return ItemConstants.isWeapon(itemID) || ItemConstants.isSecondary(itemID) || ItemConstants.isShield(itemID);
            case Consume:
                return type == InvType.CONSUME;
            case Cash:
                return item.isCash();
            case Etc:
                return type == InvType.ETC || type == InvType.INSTALL;
            default:
                return false;
        }
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case Armor:
                return AuctionArmorFirstType.getByVal(val);
            case Weapons:
                return AuctionWeaponFirstType.getByVal(val);
            case Consume:
                return AuctionUseType.getByVal(val);
            case Cash:
                return AuctionCashType.getByVal(val);
            case Etc:
                return AuctionEtcType.getByVal(val);
        }
        return null;
    }
}
