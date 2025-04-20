package net.swordie.ms.handlers.item;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.AndroidPacket;
import net.swordie.ms.connection.packet.ItemOperation;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.enums.DBChar;
import net.swordie.ms.enums.FieldOption;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.InventoryOperation;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import org.apache.log4j.Logger;

import java.util.*;

import static net.swordie.ms.enums.InvType.EQUIP;
import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.InventoryOperation.*;

public class InventoryHandler {

    private static final Logger log = Logger.getLogger(InventoryHandler.class);


    @Handler(op = InHeader.USER_CHANGE_SLOT_POSITION_REQUEST)
    public static void handleUserChangeSlotPositionRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt();
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        short oldPos = inPacket.decodeShort();
        short newPos = inPacket.decodeShort();
        short quantity = inPacket.decodeShort();
        if (chr.getHP() <= 0 || oldPos == newPos) {
            chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
            return;
        }
//        log.debug("Equipped old: " + chr.getEquippedInventory());
//        log.debug("Equip old: " + chr.getEquipInventory());
        InvType invTypeFrom = invType == EQUIP ? oldPos < 0 ? EQUIPPED : EQUIP : invType;
        InvType invTypeTo = invType == EQUIP ? newPos < 0 ? EQUIPPED : EQUIP : invType;
        Item item = chr.getInventoryByType(invTypeFrom).getItemBySlot(oldPos);
        if (item.getDateExpire().isExpired()) {
            chr.checkAndRemoveExpiredItems();
            chr.dispose();
            return;
        }
        if (item == null) {
            chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
            return;
        }
        String itemBefore = item.toString();
        if (newPos == 0) { // Drop
            Field field = chr.getField();
            if ((field.getFieldLimit() & FieldOption.DropLimit.getVal()) > 0) {
                chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                return;
            }
            boolean fullDrop;
            Drop drop;
            if (!item.getInvType().isStackable() || quantity >= item.getQuantity()
                    || ItemConstants.isThrowingItem(item.getItemId())) {
                // Whole item is dropped (equip/stackable items with all their quantity)
                fullDrop = true;
                chr.getInventoryByType(invTypeFrom).removeItem(item);
                item.drop();
                drop = new Drop(-1, item);
            } else {
                // Part of the stack is dropped
                fullDrop = false;
                Item dropItem = ItemData.getItemDeepCopy(item.getItemId());
                dropItem.setQuantity(quantity);
                item.removeQuantity(quantity);
                drop = new Drop(-1, dropItem);
            }

            /*drop.setOwnType((byte) 2); // make public
            drop.setDropbyPlayer(true);*/

            //drop.setOwnerID(chr.getId());
            int x = chr.getPosition().getX();
            int y = chr.getPosition().getY();
            Foothold fh = chr.getField().findFootHoldBelow(new Position(x, y - GameConstants.DROP_HEIGHT));
            chr.getField().drop(drop, chr.getPosition(), new Position(x, fh.getYFromX(x)));
            drop.setCanBePickedUpByPet(false);
            if (fullDrop) {
                c.write(WvsContext.inventoryOperation(true, false, Remove, oldPos, newPos, 0, item));
            } else {
                c.write(WvsContext.inventoryOperation(true, false, UpdateQuantity, oldPos, newPos, 0, item));
            }
        } else {
            if (oldPos >= 0 && newPos >= 0) {
                Inventory invFrom = chr.getInventoryByType(invTypeFrom);
                Inventory invTo = chr.getInventoryByType(invTypeTo);
                if (oldPos > 0 && oldPos <= invFrom.getSlots() && newPos <= invTo.getSlots()) {
                    Item swapItem = invTo.getItemBySlot(newPos);
                    //if (swapItem != null) {
//                log.debug("SwapItem before: " + swapItem);
                    //}
                    if (swapItem == null || item.getItemId() != swapItem.getItemId() || !item.getInvType().isStackable()
                            || ItemConstants.isThrowingItem(item.getItemId()) || item.isCash() || swapItem.isCash()
                            || !item.getDateExpire().equals(swapItem.getDateExpire())) {
                        item.setBagIndex(newPos);
                        if (swapItem != null) {
                            swapItem.setBagIndex(oldPos);
                        }
                        c.write(WvsContext.inventoryOperation(true, false, Move, oldPos, newPos, 0, item));
                    } else if (invTypeTo.isStackable()) {
                        ItemInfo ii = ItemData.getItemInfoByID(swapItem.getItemId());
                        if (ii == null) { // this is not supposed to occur tho
                            log.error("[InventoryHandler] ItemInfo:"+swapItem.getItemId()+" not found.");
                            chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                            return;
                        }
                        List<ItemOperation> changeLog = new ArrayList<>();
                        ItemOperation source = new ItemOperation();
                        source.item = item;
                        source.pos = oldPos;
                        ItemOperation target = new ItemOperation();
                        target.type = UpdateQuantity;
                        target.item = swapItem;
                        target.pos = newPos;
                        if (item.getQuantity() + swapItem.getQuantity() > ii.getSlotMax()) {
                            source.type = UpdateQuantity;
                            changeLog.add(source);
                            item.setQuantity(item.getQuantity() + swapItem.getQuantity() - ii.getSlotMax());
                            swapItem.setQuantity(ii.getSlotMax());
                        } else {
                            source.type = Remove;
                            changeLog.add(source);
                            swapItem.setQuantity(item.getQuantity() + swapItem.getQuantity());
                            chr.getInventoryByType(invTypeFrom).removeItem(item);
                            item.drop();
                        }
                        changeLog.add(target);
                        chr.write(WvsContext.inventoryOperation(1, false, changeLog));
                    }
                } else {
                    chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                }
            } else if (invType == EQUIP && invTypeFrom != invTypeTo) {
                Inventory equippedInv = chr.getEquippedInventory();
                Inventory equipInv = chr.getEquipInventory();
                int beforeSizeOn = equippedInv.getItems().size();
                int beforeSize = equipInv.getItems().size();
                // TODO: check uniqueness of equip before we allow the player to equip this
                //if (swapItem == null) {
                //    chr.consumeItem(item); //Should Delete Duped items before anything happens hacky fix
                //}
                boolean equipStatChanged = false;
                Item swapItem = equipInv.getItemBySlot(newPos);
                if (invTypeFrom == EQUIPPED && swapItem == null) {
                    chr.unequip(item);
                    //        chr.chatMessage("Unequip");
                } else {
                    Item overallCheck;
                    if (!chr.canEquip(item)) { // is this needed?
                        chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("You need more stat to equip that item.")));
                        chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                        return;
                    } else if (ItemConstants.isArcaneSymbol(item.getItemId()) && equippedInv.hasItem(item.getItemId())) {
                        // Only one type of arcane symbol can be equipped at one time.
                        chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                        return;
                    } else if (chr.getEquipInventory().isFull() && !item.isCash() && ItemConstants.isOverall(item.getItemId()) && equippedInv.getItemBySlot(BodyPart.Bottom.getVal()) != null) {
                        chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("You don't have enough room in your Inventory.")));
                        chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                        return;
                    } else {
                        int checkPos = (-newPos >= BodyPart.CBPBase.getVal() && -newPos < BodyPart.CBPEnd.getVal()) ? -(newPos + 100) : -newPos;
                        if (!ItemConstants.getBodyPartArrayFromItem(item.getItemId(), chr.getAvatarData().getCharacterStat().getGender()).contains(checkPos)) {
                            chr.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("That item cannot be equipped here.\r\nPlease equip it in the correct slot.\r\nor you tried dragging the item\r\nplease double click instead.")));
                            chr.write(WvsContext.inventoryOperation(1, false, Collections.emptyList()));
                            return;
                        }
                    }
                    equipStatChanged = chr.equip(item, oldPos, newPos); // swap
                }
                int afterSizeOn = equippedInv.getItems().size();
                int afterSize = equipInv.getItems().size();
                if (afterSize + afterSizeOn != beforeSize + beforeSizeOn) {
                    throw new RuntimeException("Data duplication!");
                }
                item.setBagIndex(newPos);
                c.write(WvsContext.inventoryOperation(true, false, Move, oldPos, newPos, 0, item));
                if (invTypeTo == EQUIPPED) {
                    Item equipCheck;
                    if (!item.isCash() && (ItemConstants.isOverall(item.getItemId())
                            && (equipCheck = equippedInv.getItemBySlot(BodyPart.Bottom.getVal())) != null)
                            || (ItemConstants.isBottom(item.getItemId())
                            && (equipCheck = equippedInv.getItemBySlot(BodyPart.Overall.getVal())) != null
                            && ItemConstants.isOverall(equipCheck.getItemId()))) { // no checks for avatar
                        short oldPos2 = (short) -equipCheck.getBagIndex();
                        chr.unequip(equipCheck);
                        short newPos2 = (short) equipInv.getFirstOpenSlot();
                        equipCheck.setBagIndex(newPos2);
                        c.write(WvsContext.inventoryOperation(true, false, Move, oldPos2, newPos2, 0, equipCheck));
                    }
                }
                if (equipStatChanged) {
                    item.updateToChar(chr); // update only equip's stat has changed
                } else {
                    chr.checkPsdWTBonuses();
                }
//                log.debug("Equipped after: " + chr.getEquippedInventory());
//                log.debug("Equip after: " + chr.getEquipInventory());
                chr.setBulletIDForAttack(chr.calculateBulletIDForAttack());
                if (newPos < 0
                        && -newPos >= BodyPart.APBase.getVal() && -newPos < BodyPart.APEnd.getVal()
                        && chr.getAndroid() != null) {
                    // update android look
                    chr.getField().broadcastPacket(AndroidPacket.modified(chr.getAndroid()));
                }
            }
        }
        chr.write(WvsContext.characterModified(chr));
    }

    @Handler(op = InHeader.USER_GATHER_ITEM_REQUEST)
    public static void handleUserGatherItemRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        Char chr = c.getChr();
        Inventory inv = chr.getInventoryByType(invType);
        List<Item> items = new ArrayList<>(inv.getItems());
        items.sort(Comparator.comparingInt(Item::getBagIndex));
        for (Item item : items) {
            int firstSlot = inv.getFirstOpenSlot();
            if (firstSlot < item.getBagIndex()) {
                short oldPos = (short) item.getBagIndex();
                item.setBagIndex(firstSlot);
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Move,
                        oldPos, (short) item.getBagIndex(), 0, item));
            }

        }
        c.write(WvsContext.gatherItemResult(invType.getVal()));
        chr.dispose();
    }

    @Handler(op = InHeader.USER_SORT_ITEM_REQUEST)
    public static void handleUserSortItemRequest(Client c, InPacket inPacket) {
        inPacket.decodeInt(); // tick
        InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
        Char chr = c.getChr();
        Inventory inv = chr.getInventoryByType(invType);
        List<Item> items = new ArrayList<>(inv.getItems());
        items.sort(Comparator.comparingInt(Item::getItemId));
        for (Item item : items) {
            if (item.getBagIndex() != items.indexOf(item) + 1) {
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Remove,
                        (short) item.getBagIndex(), (short) 0, -1, item));
            }
        }
        for (Item item : items) {
            int index = items.indexOf(item) + 1;
            if (item.getBagIndex() != index) {
                item.setBagIndex(index);
                chr.write(WvsContext.inventoryOperation(true, false, InventoryOperation.Add,
                        (short) item.getBagIndex(), (short) 0, -1, item));
            }
        }
        c.write(WvsContext.sortItemResult(invType.getVal()));
        chr.dispose();
    }
}