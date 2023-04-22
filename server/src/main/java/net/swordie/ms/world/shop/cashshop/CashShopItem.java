package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.Server;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created on 4/21/2018.
 */
@Entity
@Table(name = "cs_items")
public class CashShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int itemID;
    private int stock;
    @Enumerated(EnumType.ORDINAL)
    private CashShopItemFlag shopItemFlag = CashShopItemFlag.None;
    private int idk1;
    private int idk2;
    private int oldPrice;
    private int newPrice;
    @Convert(converter = FileTimeConverter.class)
    private FileTime idkTime1;
    @Convert(converter = FileTimeConverter.class)
    private FileTime idkTime2;
    @Convert(converter = FileTimeConverter.class)
    private FileTime idkTime3;
    @Convert(converter = FileTimeConverter.class)
    private FileTime idkTime4;
    private int idk3;
    private int bundleQuantity;
    private int availableDays;
    private short buyableWithMaplePoints;
    private short buyableWithCredit;
    private short buyableWithPrepaid;
    private short likable;
    private short meso;
    private short favoritable;
    private int gender;
    private int likes;
    private int requiredLevel;
    private String idk10;
    private int idk11;
    private int idk13;
    private int idk14;
    private String category;
    @Transient
    private int subCategory;
    @Transient
    private int parent;
    @Transient
    private CashShopCategory cashShopCategory;

    public CashShopItem() {
        idkTime1 = FileTime.currentTime();
        idkTime2 = FileTime.fromType(FileTime.Type.MAX_TIME);
        idkTime3 = FileTime.currentTime();
        idkTime4 = FileTime.fromType(FileTime.Type.MAX_TIME);
        stock = 100;
        buyableWithMaplePoints = 1;
        buyableWithCredit = 1;
        buyableWithPrepaid = 1;
        likable = 1;
        favoritable = 1;
        gender = 2;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCashShopCategory() == null ? 0 : getCashShopCategory().getIdx());
        outPacket.encodeInt(getSubCategory());
        outPacket.encodeInt(getParent());

        outPacket.encodeString(Server.getInstance().getCashShop().getBannerUrl());
        outPacket.encodeInt(getId());
        outPacket.encodeInt(getItemID());
        outPacket.encodeInt(getStock());
        outPacket.encodeInt(getShopItemFlag().ordinal());
        outPacket.encodeInt(getIdk1());
        outPacket.encodeInt(getIdk2());
        outPacket.encodeInt(getOldPrice() == 0 ? getNewPrice() : getOldPrice());

        outPacket.encodeFT(getIdkTime1());
        outPacket.encodeFT(getIdkTime2());
        outPacket.encodeFT(getIdkTime3());
        outPacket.encodeFT(getIdkTime4());

        outPacket.encodeInt(getNewPrice());
        outPacket.encodeInt(1); //getIdk3());
        outPacket.encodeInt(getBundleQuantity());
        outPacket.encodeInt(getAvailableDays());

        outPacket.encodeShort(getBuyableWithMaplePoints()); // with maple point
        outPacket.encodeShort(getBuyableWithCredit()); // with credit
        outPacket.encodeShort(getBuyableWithPrepaid()); // with prepaid
        outPacket.encodeShort(getLikable());
        outPacket.encodeShort(getMeso());
        outPacket.encodeShort(getFavoritable());

        outPacket.encodeInt(getGender());
        outPacket.encodeInt(getLikes());
        outPacket.encodeInt(getRequiredLevel());

        outPacket.encodeString(getIdk10());

        outPacket.encodeInt(0); //getIdk11());
        outPacket.encodeInt(30); //getStock());
        outPacket.encodeInt(31); //getIdk13());
        outPacket.encodeInt(41); //getIdk14());

        outPacket.encodeByte(false); // has favorited, maybe implement later
        outPacket.encodeByte(false); // has liked, maybe implement later

        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            // Package stuff, just leave it for now
            outPacket.encodeInt(1);
            outPacket.encodeInt(2);
            outPacket.encodeInt(3);
            outPacket.encodeInt(4);
            outPacket.encodeInt(5);
            outPacket.encodeInt(6);
            outPacket.encodeInt(7);
            outPacket.encodeInt(8);
            outPacket.encodeInt(9);
        }
        outPacket.encodeByte(false); // ?
    }



    public void playEncode(OutPacket outPacket) {
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);

        outPacket.encodeString(Server.getInstance().getCashShop().getBannerUrl());
        outPacket.encodeInt(1022260); //
        outPacket.encodeInt(1302000); // Item ID
        outPacket.encodeInt(1); // stock
        outPacket.encodeInt(0); // 1 = event, 2 = new, 3 = sale, 4 = hot, 5 = limited, 6 = black friday sale, 7 = account limitation, 8 = char limitation
        outPacket.encodeInt(1);
        outPacket.encodeInt(2);
        outPacket.encodeInt(10000); // original price

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ldt = now.plusDays(1);
        LocalDateTime ldt1 = ldt.withHour(1);
        LocalDateTime ldt2 = ldt.withHour(2).minusDays(1);
        LocalDateTime ldt3 = ldt.withHour(3);
        LocalDateTime ldt4 = ldt.withHour(4);

        outPacket.encodeFT(ldt1);
        outPacket.encodeFT(ldt2);
        outPacket.encodeFT(ldt3);
        outPacket.encodeFT(ldt4);

        outPacket.encodeInt(10000); // new price
        outPacket.encodeInt(0);
        outPacket.encodeInt(20); // quantity
        outPacket.encodeInt(90); // available days

        outPacket.encodeShort(1);
        outPacket.encodeShort(2);
        outPacket.encodeShort(3);
        outPacket.encodeShort(4);
        outPacket.encodeShort(0); // Something with meso
        outPacket.encodeShort(6);

        outPacket.encodeInt(2); // gender, 0 = male, 1 = female, 2 = neither, 3 = not buyable
        outPacket.encodeInt(1347); // likes
        outPacket.encodeInt(1);

        outPacket.encodeString("");

        outPacket.encodeInt(1);
        outPacket.encodeInt(2);
        outPacket.encodeInt(3);
        outPacket.encodeInt(4);

        outPacket.encodeByte(0); // has favorited
        outPacket.encodeByte(1); // has liked

        int size = 1;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(1);
            outPacket.encodeInt(2);
            outPacket.encodeInt(3);
            outPacket.encodeInt(4);
            outPacket.encodeInt(5);
            outPacket.encodeInt(6);
            outPacket.encodeInt(7);
            outPacket.encodeInt(8);
            outPacket.encodeInt(9);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public CashShopItem setItemID(int itemID) {
        this.itemID = itemID;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CashShopItemFlag getShopItemFlag() {
        return shopItemFlag;
    }

    public void setShopItemFlag(CashShopItemFlag shopItemFlag) {
        this.shopItemFlag = shopItemFlag;
    }

    public int getIdk1() {
        return idk1;
    }

    public void setIdk1(int idk1) {
        this.idk1 = idk1;
    }

    public int getIdk2() {
        return idk2;
    }

    public void setIdk2(int idk2) {
        this.idk2 = idk2;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public FileTime getIdkTime1() {
        return idkTime1;
    }

    public void setIdkTime1(FileTime idkTime1) {
        this.idkTime1 = idkTime1;
    }

    public FileTime getIdkTime2() {
        return idkTime2;
    }

    public void setIdkTime2(FileTime idkTime2) {
        this.idkTime2 = idkTime2;
    }

    public FileTime getIdkTime3() {
        return idkTime3;
    }

    public void setIdkTime3(FileTime idkTime3) {
        this.idkTime3 = idkTime3;
    }

    public FileTime getIdkTime4() {
        return idkTime4;
    }

    public void setIdkTime4(FileTime idkTime4) {
        this.idkTime4 = idkTime4;
    }

    public int getIdk3() {
        return idk3;
    }

    public void setIdk3(int idk3) {
        this.idk3 = idk3;
    }

    public int getBundleQuantity() {
        return bundleQuantity;
    }

    public void setBundleQuantity(int bundleQuantity) {
        this.bundleQuantity = bundleQuantity;
    }

    public int getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(int availableDays) {
        this.availableDays = availableDays;
    }

    public short getBuyableWithMaplePoints() {
        return buyableWithMaplePoints;
    }

    public void setBuyableWithMaplePoints(short buyableWithMaplePoints) {
        this.buyableWithMaplePoints = buyableWithMaplePoints;
    }

    public short getBuyableWithCredit() {
        return buyableWithCredit;
    }

    public void setBuyableWithCredit(short buyableWithCredit) {
        this.buyableWithCredit = buyableWithCredit;
    }

    public short getBuyableWithPrepaid() {
        return buyableWithPrepaid;
    }

    public void setBuyableWithPrepaid(short buyableWithPrepaid) {
        this.buyableWithPrepaid = buyableWithPrepaid;
    }

    public short getLikable() {
        return likable;
    }

    public void setLikable(short likable) {
        this.likable = likable;
    }

    public short getMeso() {
        return meso;
    }

    public void setMeso(short meso) {
        this.meso = meso;
    }

    public short getFavoritable() {
        return favoritable;
    }

    public void setFavoritable(short favoritable) {
        this.favoritable = favoritable;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getIdk10() {
        return idk10;
    }

    public void setIdk10(String idk10) {
        this.idk10 = idk10;
    }

    public int getIdk11() {
        return idk11;
    }

    public void setIdk11(int idk11) {
        this.idk11 = idk11;
    }

    public int getIdk13() {
        return idk13;
    }

    public void setIdk13(int idk13) {
        this.idk13 = idk13;
    }

    public int getIdk14() {
        return idk14;
    }

    public void setIdk14(int idk14) {
        this.idk14 = idk14;
    }

    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CashShopItem{" +
                "itemID=" + itemID +
                ", newPrice=" + newPrice +
                ", category='" + category + '\'' +
                ", cashShopCategory=" + cashShopCategory +
                '}';
    }
    public void setCashShopCategory(CashShopCategory cashShopCategory) {
        this.cashShopCategory = cashShopCategory;
    }

    public CashShopCategory getCashShopCategory() {
        return cashShopCategory;
    }

    public CashItemInfo toCashItemInfo(Account account) {
        CashItemInfo cii = new CashItemInfo();
        cii.setAccountID(account.getId());
        Item item = ItemData.getItemDeepCopy(getItemID());
        item.setQuantity((short) (getBundleQuantity() == 0 ? 1 : getBundleQuantity()));
        cii.setItem(item);
        cii.setCommodityID(getId());
        if (getAvailableDays() > 0) {
            item.setDateExpire(FileTime.fromDate(LocalDateTime.now().plusDays(getAvailableDays())));
        }
        return cii;
    }

    private enum CashShopItemFlag {
        None,
        Event,
        New,
        Sale,
        Hot,
        Limited,
        BlackFriday,
        AccountLimited,
        CharLimited
    }


}
