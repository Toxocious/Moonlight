package net.swordie.ms.world.auction.cash;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.world.auction.AuctionEnum;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionCashType implements AuctionEnum {
    All,
    Boost,
    Game,
    Outfits,
    Beauty,
    Pet,
    Etc,
    ;

    public static AuctionCashType getByVal(int val) {
        return val >= 0 && val < values().length ? values()[val] : All;
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case Boost:
                return AuctionBoostType.getByVal(val);
            case Game:
                return AuctionGameType.getByVal(val);
            case Outfits:
                return AuctionOutfitType.getByVal(val);
            case Beauty:
                return AuctionBeautyType.getByVal(val);
            case Pet:
                return AuctionPetType.getByVal(val);
            case Etc:
                return AuctionCashEtcType.getByVal(val);
        }
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        return false;
    }
}
