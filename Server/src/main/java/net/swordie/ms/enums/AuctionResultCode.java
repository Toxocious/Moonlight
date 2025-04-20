package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 1/18/2019.
 */
public enum AuctionResultCode {
    None(0),
    Unknown(100),
    Unknown2(101),
    NotExist(102),
    InsufficientSlots(103),
    CannotPurchase(104),
    CannotPartialPurchase(105),
    InsufficientFunds(106),
    Load(1000),
    ;

    private int val;

    AuctionResultCode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
