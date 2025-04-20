package net.swordie.ms.client.character.items;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static net.swordie.ms.enums.InvType.CASH;
import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.InventoryOperation.Add;

/**
 * GW_ItemSlotBase
 * Created on 11/23/2017.
 */
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable, Encodable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    protected int itemId;
    protected int bagIndex;
    protected long cashItemSerialNumber;
    @Convert(converter = FileTimeConverter.class)
    protected FileTime dateExpire = FileTime.fromType(FileTime.Type.MAX_TIME);
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "invType")
    protected InvType invType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    protected Type type;
    protected boolean isCash;
    protected int quantity;
    private String owner = "";

    public boolean isTradable() {
        return !ItemData.getItemInfoByID(getItemId()).isTradeBlock();
    }

    public enum Type {
        EQUIP(1),
        ITEM(2),
        PET(3);

        private byte val;

        Type(byte val) {
            this.val = val;
        }

        Type(int val) {
            this((byte) val);
        }

        public byte getVal() {
            return val;
        }

        public static Type getTypeById(int id) {
            return Arrays.stream(Type.values()).filter(type -> type.getVal() == id).findFirst().orElse(null);
        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void drop() {
        setBagIndex(0);
    }

    public void addQuantity(int amount) {
        if (amount > 0 && amount + getQuantity() > 0) {
            setQuantity(getQuantity() + amount);
        }
    }

    public void removeQuantity(int amount) {
        if (amount > 0) {
            setQuantity(Math.max(0, getQuantity() - amount));
        }
    }

    public Item() {
    }

    public Item(int itemId, int bagIndex, long cashItemSerialNumber, FileTime dateExpire, InvType invType,
                boolean isCash, Type type) {
        this.itemId = itemId;
        this.bagIndex = bagIndex;
        this.cashItemSerialNumber = cashItemSerialNumber;
        this.dateExpire = dateExpire;
        this.invType = invType;
        this.isCash = isCash;
        this.type = type;
    }

    public Item deepCopy() {
        Item item = new Item();
        item.setItemId(getItemId());
        item.setBagIndex(getBagIndex());
        item.setCashItemSerialNumber(getCashItemSerialNumber());
        item.setDateExpire(getDateExpire().deepCopy());
        item.setInvType(getInvType());
        item.setCash(isCash());
        item.setType(getType());
        item.setOwner(getOwner());
        item.setQuantity(getQuantity());
        return item;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBagIndex() {
        return bagIndex;
    }

    public void setBagIndex(int bagIndex) {
        this.bagIndex = Math.abs(bagIndex);
    }

    public long getCashItemSerialNumber() {
        return getId();
    }

    public FileTime getDateExpire() {
        return dateExpire;
    }

    public InvType getInvType() {
        return invType;
    }

    public Type getType() {
        return type;
    }

    public boolean isCash() {
        return isCash || getInvType() == CASH;
    }

    public void encode(OutPacket outPacket) {
        int itemId = getItemId();
        outPacket.encodeByte(getType().getVal());
        // GW_ItemSlotBase
        outPacket.encodeInt(itemId);
        outPacket.encodeByte(isCash());
        if (isCash()) {
            outPacket.encodeLong(getId());
        }
        outPacket.encodeFT(getDateExpire());
        outPacket.encodeInt(-1); // bagIndex if it's in a bag
        outPacket.encodeByte(0); // new 196
        if (getType() == Type.ITEM) { // bundle
            outPacket.encodeShort(getQuantity()); // nQuantity
            outPacket.encodeString(getOwner()); // sOwner
            outPacket.encodeShort(0); // flag
            if (ItemConstants.isThrowingStar(itemId) || ItemConstants.isBullet(itemId) ||
                    ItemConstants.isFamiliar(itemId) || ItemConstants.isIntensePowerCrystal(itemId) ||
                    itemId / 10000 == 288) { // idk what the last one for is, there's no items that begin with this
                outPacket.encodeLong(getId()); // not id, basically an option
            }
            outPacket.encodeInt(0); // new 196
        }
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCashItemSerialNumber(long cashItemSerialNumber) {
        this.cashItemSerialNumber = cashItemSerialNumber;
    }

    public void setDateExpire(FileTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public void setInvType(InvType invType) {
        this.invType = invType;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCash(boolean cash) {
        isCash = cash;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", ItemId: " + getItemId() + ", Qty: " + getQuantity() + ", InvType: " + getInvType()
                + ", BagIndex: " + getBagIndex();
    }

    /**
     * Sends a packet to the given Char to show that this Item has updated.
     * @param chr The Char to give the update to
     */
    public void updateToChar(Char chr) {
        short bagIndex = (short) (getInvType() == EQUIPPED ? - getBagIndex() : getBagIndex());
        chr.write(WvsContext.inventoryOperation(true, false, Add,
                bagIndex, (short) 0, 0, this));
        chr.checkPsdWTBonuses();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id != 0 && id == item.id && item.itemId == item.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId);
    }
}
