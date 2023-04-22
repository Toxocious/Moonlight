package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionState {
    Init(0), // Currently selling
    Bid(1), // Bid done, cancel bid
    Reserve(2), // Successful bid, failure to receive
    Sold(3),
    Expire(4),
    PriceDifference(5),
    OutbidDone(6),
    BidSuccessDone(7),
    SoldDone(8),
    Done(9),
    ;
    private int val;

    AuctionState(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
