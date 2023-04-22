package net.swordie.ms.world.shop;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.ItemGrade;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * Created on 3/27/2018.
 */
@Entity
@Table(name = "shopitems")
public class NpcShopItem {
    @Id
    @GeneratedValue
    private long id;
    private int shopID;
    @Transient
    private Item item;
    private int itemID;
    private int price;
    private int tokenItemID;
    private int tokenPrice;
    private int pointQuestID;
    private int pointPrice;
    private int starCoin;
    private int questExID;
    private String questExKey;
    private int questExValue;
    private int itemPeriod;
    private int levelLimited;
    private int showLevMin;
    private int showLevMax;
    private int questID;
    @Convert(converter = FileTimeConverter.class)
    private FileTime sellStart;
    @Convert(converter = FileTimeConverter.class)
    private FileTime sellEnd;
    private int tabIndex;
    private boolean worldBlock;
    private int potentialGrade;
    private int buyLimit;
    @Transient
    private BuyLimitInfo buyLimitInfo;
    private short quantity;
    private long unitPrice;
    private short maxPerSlot;
    private int discountPerc;
    @Transient
    private boolean isQuest;
    @Transient
    private boolean isBuyBack;

    public NpcShopItem() {
        sellStart = FileTime.fromType(FileTime.Type.ZERO_TIME);
        sellEnd = FileTime.fromType(FileTime.Type.MAX_TIME);
        maxPerSlot = 1000;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getBuyLimit() == 0 ? -1 : getBuyLimit()); // -1 = infinite, 0 = nothing left.

        outPacket.encodeInt(getItemID());
        outPacket.encodeInt(getTabIndex());
        outPacket.encodeInt(getBuyLimit());
        outPacket.encodeInt(getItemPeriod()); // time usage of item, in minutes (0 = no time limit)
        outPacket.encodeInt(getPotentialGrade()); // TODO confirm
//        outPacket.encodeInt(getDiscountPerc());
        outPacket.encodeInt(getPrice());
        outPacket.encodeInt(getTokenItemID());
        outPacket.encodeInt(getTokenPrice());
        outPacket.encodeInt(getPointQuestID());
        outPacket.encodeInt(getPointPrice());
        outPacket.encodeInt(getStarCoin());
        boolean bool = false;
        outPacket.encodeByte(bool);
        if (bool) {
            outPacket.encodeInt(0);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            outPacket.encodeString("");
            outPacket.encodeInt(0);
            outPacket.encodeString("");
            outPacket.encodeLong(0);
            outPacket.encodeLong(0);
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeLong(0);
            }

        }
        outPacket.encodeInt(-1);
        outPacket.encodeInt(-1);
        outPacket.encodeInt(getLevelLimited());
        outPacket.encodeShort(getShowLevMin());
        outPacket.encodeShort(getShowLevMax());
        if (getBuyLimitInfo() != null) {
            getBuyLimitInfo().encode(outPacket);
        } else {
            new BuyLimitInfo().encode(outPacket);
        }
        outPacket.encodeByte(false); // bIsDisabled
        outPacket.encodeFT(getSellStart());
        outPacket.encodeFT(getSellEnd());
        outPacket.encodeInt(0); // ? setting it to >0 will make the item not show up
        outPacket.encodeShort(getTabIndex());
        outPacket.encodeByte(isWorldBlock());
        outPacket.encodeInt(getQuestExID()); // Adventure: id=100161, key=lv | Alliance: id=500345, key=grade
        outPacket.encodeString(getQuestExKey());
        outPacket.encodeInt(getQuestExValue());
        outPacket.encodeInt(0); // ?
        outPacket.encodeByte(0); //new v208
        if (!ItemConstants.isRechargable(getItemID())) {
            outPacket.encodeShort(getQuantity());
        } else {
            outPacket.encodeLong(getUnitPrice());
        }
        outPacket.encodeShort(getMaxPerSlot());
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME));
        outPacket.encodeByte(getDiscountPerc());
        if (isQuest()) {
            byte type = 0;
            outPacket.encodeByte(type);
            if (type == 1) {
                outPacket.encodeByte(0);
            }
        }
        // sub with idk stuff in it
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeArr(new byte[32]); // decodeBuffer(32), npc id (4) + int (4) x4? Example id = 78 96 8F 00 (9410168)
        // end sub
        outPacket.encodeByte(isBuyBack());
        if (isBuyBack()) {
            item.encode(outPacket);
        }
    }

    public int getItemID() {
        return itemID;
    }


    /**
     * Sets the item id of this item.
     * @param itemID The id of this item
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of this item, in mesos. If both this and token price are set, the item will not be displayed.
     * @param price The price of this item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public int getTokenItemID() {
        return tokenItemID;
    }

    /**
     * Sets the token id. Token ids start with 431. Items that aren't tokens won't get their token displayed.
     * @param tokenItemID The id of the token
     */
    public void setTokenItemID(int tokenItemID) {
        this.tokenItemID = tokenItemID;
    }

    public int getTokenPrice() {
        return tokenPrice;
    }

    /**
     * Sets the token price of this item. If both this and mesos price are set, the item will not be displayed.
     * @param tokenPrice The token price of this item.
     */
    public void setTokenPrice(int tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    public int getPointQuestID() {
        return pointQuestID;
    }

    public void setPointQuestID(int pointQuestID) {
        this.pointQuestID = pointQuestID;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    public int getStarCoin() {
        return starCoin;
    }

    public void setStarCoin(int starCoin) {
        this.starCoin = starCoin;
    }

    public int getQuestExID() {
        return questExID;
    }

    public void setQuestExID(int questExID) {
        this.questExID = questExID;
    }

    public String getQuestExKey() {
        return questExKey != null ? questExKey : "";
    }

    public void setQuestExKey(String questExKey) {
        this.questExKey = questExKey;
    }

    public int getQuestExValue() {
        return questExValue;
    }

    public void setQuestExValue(int questExValue) {
        this.questExValue = questExValue;
    }

    public int getItemPeriod() {
        return itemPeriod;
    }

    public void setItemPeriod(int itemPeriod) {
        this.itemPeriod = itemPeriod;
    }

    public int getLevelLimited() {
        return levelLimited;
    }

    public void setLevelLimited(int levelLimited) {
        this.levelLimited = levelLimited;
    }

    public int getShowLevMin() {
        return showLevMin;
    }

    public void setShowLevMin(int showLevMin) {
        this.showLevMin = showLevMin;
    }

    public int getShowLevMax() {
        return showLevMax;
    }

    public void setShowLevMax(int showLevMax) {
        this.showLevMax = showLevMax;
    }

    public int getQuestID() {
        return questID;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public FileTime getSellStart() {
        if (sellStart == null) {
            return FileTime.fromType(FileTime.Type.ZERO_TIME);
        }
        return sellStart;
    }

    public void setSellStart(FileTime sellStart) {
        this.sellStart = sellStart;
    }

    public FileTime getSellEnd() {
        if (sellStart == null) {
            return FileTime.fromType(FileTime.Type.MAX_TIME);
        }
        return sellEnd;
    }

    public void setSellEnd(FileTime sellEnd) {
        this.sellEnd = sellEnd;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    /**
     * Sets the tab index of this item.
     * @param tabIndex the tab index of this item.
     */
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public boolean isWorldBlock() {
        return worldBlock;
    }

    /**
     * Sets whether or not this item should be displayed on this world.
     * @param worldBlock whether or not this item should be displayed on this world.
     */
    public void setWorldBlock(boolean worldBlock) {
        this.worldBlock = worldBlock;
    }

    public int getPotentialGrade() {
        return potentialGrade;
    }

    /**
     * Sets the potential grade of this item (see {@link ItemGrade}). Will do nothing if this item is not an equip.
     * @param potentialGrade The potential grade of this item
     */
    public void setPotentialGrade(int potentialGrade) {
        this.potentialGrade = potentialGrade;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    /**
     * Sets the buy limit of this item.
     * @param buyLimit The buy limit of this item.
     */
    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    public BuyLimitInfo getBuyLimitInfo() {
        return buyLimitInfo;
    }

    public void setBuyLimitInfo(BuyLimitInfo buyLimitInfo) {
        this.buyLimitInfo = buyLimitInfo;
    }

    public short getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity this item should be given with.
     * @param quantity The quantity of this item
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public short getMaxPerSlot() {
        return maxPerSlot;
    }

    /**
     * Sets the maximum amount of items the user can buy of these at once.
     * @param maxPerSlot
     */
    public void setMaxPerSlot(short maxPerSlot) {
        this.maxPerSlot = maxPerSlot;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getDiscountPerc() {
        return discountPerc;
    }

    /**
     * Sets the discount percentage of this item, from 0 to 100.
     * @param discountPerc The discount percentage of this item
     */
    public void setDiscountPerc(int discountPerc) {
        this.discountPerc = discountPerc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public boolean isQuest() {
        return isQuest;
    }

    public void setQuest(boolean quest) {
        isQuest = quest;
    }

    public boolean isBuyBack() {
        return isBuyBack;
    }

    public void setBuyBack(boolean buyBack) {
        isBuyBack = buyBack;
    }
}
