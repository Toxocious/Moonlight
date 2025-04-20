package net.swordie.ms.enums;

/**
 * Created on 2/28/2018.
 */
public enum InventoryOperation {
    Add(0),
    UpdateQuantity(1),
    Move(2),
    Remove(3),
    ItemExp(4),
    UpdateBagPos(5),
    UpdateBagQuantity(6),
    BagRemove(7),
    BagToBag(8),
    BagNewItem(9),
    BagRemoveSlot(10),
    ;

    private byte val;

    InventoryOperation(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
