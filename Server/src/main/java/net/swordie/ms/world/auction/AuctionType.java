package net.swordie.ms.world.auction;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 9/27/2018.
 */
public enum AuctionType {
    Initialize(0),
    ItemList_2(1),
    AuctionCancel_2(2),
    Purchase_2(3),
    Bid(4),
    PurchaseReq(5),
    ReqSuccess(6),
    SearchSuccess(7),
    UnkError(8),
    UnkError_2(9),
    ListItem(10),
    ItemListAgain(11),
    AuctionCancel(12),
    UnkError_3(13),

    PurchaseSingle(20), // PurchaseSuccess
    PurchaseMultiple(21), // PurchaseSuccess
    Collect(30),
    Reclaim(31),
    SearchItems(40),
    SearchMarketPlace(41),
    WishListAdd(45), // WishlistSuccess
    WishList(46),
    WishListRemove(47),
    LoadSellItems(50),
    LoadSoldItems(51),
    Unk60(60),
    SellItemAdd(70), // sellItem
    SoldItemAdd(71),
    WishlistAdd(72), // wishlist
    Unk73(73),

    ;

    private int val;

    AuctionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static AuctionType getType(int val) {
        return Util.findWithPred(values(), type -> type.getVal() == val);
    }
}
