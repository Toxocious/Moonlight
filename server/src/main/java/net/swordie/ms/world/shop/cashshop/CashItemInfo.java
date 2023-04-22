package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.PetItem;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created on 4/23/2018.
 */
@Entity
@Table(name = "cashiteminfos")
public class CashItemInfo implements Encodable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int accountID;
    private int characterID;
    private int commodityID;
    private String buyCharacterID;

    private int paybackRate;
    private double discount;
    private int orderNo;
    private int productNo;
    private boolean refundable;
    private byte sourceFlag;
    private boolean storeBank;
    private int position;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemid")
    private Item item;

    public void encode(OutPacket outPacket) {
        // size 102
        outPacket.encodeLong(item.getId());
        outPacket.encodeInt(getAccountID());
        outPacket.encodeInt(getCharacterID());
        outPacket.encodeInt(item.getItemId());
        outPacket.encodeInt(getCommodityID());
        outPacket.encodeShort(item.getQuantity());
        outPacket.encodeString(getBuyCharacterID(), 13); // gifter
        outPacket.encodeFT(item.getDateExpire());
        outPacket.encodeInt(getPaybackRate());
        outPacket.encodeLong((long) getDiscount());
        outPacket.encodeInt(getOrderNo());
        outPacket.encodeInt(getProductNo());
        outPacket.encodeByte(isRefundable());
        outPacket.encodeByte(getSourceFlag());
        outPacket.encodeByte(isStoreBank());
        // GW_CashItemOption::Decode
        outPacket.encodeLong(item.isCash() ? 0 : item.getId());
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME));
        if (item instanceof Equip) {
            Equip equip = (Equip) item;
            outPacket.encodeInt(equip.getGrade());
            int totalOptSize = 3;
            for (int i = 0; i < totalOptSize; i++) {
                if (i < equip.getOptions().size()) {
                    outPacket.encodeInt(equip.getOptions().get(i));
                } else {
                    outPacket.encodeInt(0);
                }
            }
        } else {
            outPacket.encodeInt(0);
            int totalOptSize = 3;
            for (int i = 0; i < totalOptSize; i++) {
                outPacket.encodeInt(0);
            }
        }
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getItemID() {
        return item.getItemId();
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public int getQuantity() {
        return item.getQuantity();
    }

    public String getBuyCharacterID() {
        return buyCharacterID;
    }

    public void setBuyCharacterID(String buyCharacterID) {
        this.buyCharacterID = buyCharacterID;
    }

    public int getPaybackRate() {
        return paybackRate;
    }

    public void setPaybackRate(int paybackRate) {
        this.paybackRate = paybackRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public byte getSourceFlag() {
        return sourceFlag;
    }

    public void setSourceFlag(byte sourceFlag) {
        this.sourceFlag = sourceFlag;
    }

    public boolean isStoreBank() {
        return storeBank;
    }

    public void setStoreBank(boolean storeBank) {
        this.storeBank = storeBank;
    }

    public long getCashItemSN() {
        return getItem() == null ? 0 : getItem().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashItemInfo that = (CashItemInfo) o;
        return getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(item.getId());
    }

    /**
     * Transforms this CashItemInfo to its corresponding Item
     *
     * @return the corresponding Item
     */
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Creates a CashItemInfo from a given cash Item. If the Item is not a cash Item, returns null.
     *
     * @param chr  the chr to which the items belongs to
     * @param item the item from which the CashItemInfo should be created from
     * @return corresponding CashItemInfo
     */
    public static CashItemInfo fromItem(Char chr, Item item) {
        if (!item.isCash() && !(item instanceof PetItem)) {
            return null;
        }
        CashItemInfo cii = new CashItemInfo();
        cii.setAccountID(chr.getUserId());
        cii.setCommodityID(1); // could grab this from cashshop sql
        cii.setItem(item);
        return cii;
    }
}