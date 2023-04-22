package net.swordie.ms.world.auction.cash;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionOutfitType implements AuctionEnum {
    All,
    Coupon,
    Weapons,
    Hat,
    Cape,
    Outfit,
    Top,
    Bottom,
    Shoes,
    Gloves,
    FaceAcc,
    Earrings,
    Ring,
    EyeAcc,
    Effect,
    ;

    public static AuctionEnum getByVal(int val) {
        return val >= 0 && val < values().length ? values()[val] : All;
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        return false;
    }}
