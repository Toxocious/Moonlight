package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 7/7/2018.
 */
public enum CashShopInfoType {
    Categories(3),
    Banner(4),
    TopSellers(5),
    SpecialItems(6),
    FeaturedItems(8),
    SpecialSale(9),
    Cart(10),
    BannerMsg(12);

    private int val;

    CashShopInfoType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
