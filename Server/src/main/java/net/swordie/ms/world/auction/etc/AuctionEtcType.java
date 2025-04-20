package net.swordie.ms.world.auction.etc;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.world.auction.AuctionEnum;
import net.swordie.ms.world.auction.use.AuctionRecoveryType;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionEtcType implements AuctionEnum {
    All,
    Chair,
    Profession,
    ;

    public static AuctionEtcType getByVal(int val) {
        return val >= 0 && val < values().length ? values()[val] : All;
    }

    @Override
    public AuctionEnum getSubByVal(int val) {
        switch (this) {
            case All:
                return All;
            case Chair:
                return AuctionChairType.getByVal(val);
            case Profession:
                return AuctionProfessionType.getByVal(val);
        }
        return null;
    }

    @Override
    public boolean isMatching(Item item) {
        return false;
    }
}
