package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.AuctionResultCode;
import net.swordie.ms.enums.AuctionState;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.World;
import net.swordie.ms.world.auction.*;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class AuctionHandler {

    private static final Logger log = Logger.getLogger(AuctionHandler.class);


    @Handler(op = InHeader.AUCTION_REQUEST)
    public static void handleAuctionRequest(Char chr, InPacket inPacket) {
        int type = inPacket.decodeInt();
        AuctionType at = AuctionType.getType(type);
        if (at == null) {
            chr.write(FieldPacket.auctionResult(AuctionResult.error(AuctionType.SearchItems, AuctionResultCode.Unknown)));
            log.error(String.format("Unknown auction request type %d", type));
            return;
        }
        Account acc = chr.getAccount();
        World world = chr.getWorld();
        AuctionItem ai;
        switch (at) {
            case Initialize:
                chr.write(FieldPacket.auctionResult(AuctionResult.showSoldItems(acc.getCompletedAuctionItems())));
                chr.write(FieldPacket.auctionResult(AuctionResult.showSellingItems(acc.getSellingAuctionItems())));

                // TODO: wishlist -> AuctionResult.wishList
                chr.write(FieldPacket.auctionResult(AuctionResult.initialize()));

                chr.write(FieldPacket.auctionResult(AuctionResult.showMarketplaceItems(world.getAuctionMarketPlaceItems(), AuctionResultCode.Load)));
                chr.write(FieldPacket.auctionResult(AuctionResult.showSearchItems(world.getAuctionRecentListings())));
                break;
            case SearchItems:
            case SearchMarketPlace:
                boolean expired = at == AuctionType.SearchMarketPlace;
                inPacket.decodeByte(); // ?
                AuctionInvType auctionInvType = AuctionInvType.getByVal(inPacket.decodeInt());
                if (at == AuctionType.SearchItems) {
                    inPacket.decodeInt(); // ?
                }
                String query1 = inPacket.decodeString();
                String query2 = inPacket.decodeString();
                AuctionEnum subType = auctionInvType.getSubByVal(inPacket.decodeInt()).getSubByVal(inPacket.decodeInt());
                int minLv = inPacket.decodeInt();
                int maxLv = inPacket.decodeInt();
                long minPrice = inPacket.decodeLong();
                long maxPrice = inPacket.decodeLong();
                AuctionPotType apt = AuctionPotType.getByVal(inPacket.decodeInt());
                boolean andSearch = inPacket.decodeByte() != 0;
                int addOptionSize = inPacket.decodeInt();
                // TODO
                Set<AuctionItem> items = world.getAuctionItemsWithFilter(query1.toLowerCase(), query2.toLowerCase(),
                        subType, minLv, maxLv, minPrice, maxPrice, apt, andSearch, expired);
                if (at == AuctionType.SearchItems) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.showSearchItems(items)));
                } else {
                    chr.write(FieldPacket.auctionResult(AuctionResult.showMarketplaceItems(items)));
                }
                break;
            case WishList:
              //  items = new HashSet<>();
            //    items.add(AuctionItem.testItem());
                break;
            case ListItem:
                inPacket.decodeInt(); // auction type
                int itemId = inPacket.decodeInt();
                int quant = inPacket.decodeInt();
                long unitPrice = inPacket.decodeLong();
                int listHours = inPacket.decodeInt(); // always 24?
                InvType invType = InvType.getInvTypeByVal(inPacket.decodeByte());
                int pos = inPacket.decodeInt();
                Item item = chr.getInventoryByType(invType).getItemBySlot(pos);
                if (item == null || item.getItemId() != itemId) { // Should allow Untradeable Items to be listed now?
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                long cost = GameConstants.AUCTION_DEPOSIT_AMOUNT;
                if (cost > chr.getMoney() || unitPrice < GameConstants.AUCTION_MIN_PRICE
                        || unitPrice > GameConstants.AUCTION_MAX_PRICE) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.InsufficientFunds)));
                    return;
                }
                chr.deductMoney(GameConstants.AUCTION_DEPOSIT_AMOUNT);
                chr.consumeItem(item, quant);
                item = item.deepCopy();
                item.setQuantity(quant);
                DatabaseManager.saveToDB(item);
                ai = acc.createAndAddAuctionByItem(item, chr, unitPrice);
                chr.write(FieldPacket.auctionResult(AuctionResult.listItem(ai)));
                // Intended fallthrough to display all items again
            case LoadSellItems:
                items = acc.getSellingAuctionItems();
                chr.write(FieldPacket.auctionResult(AuctionResult.showSellingItems(items)));
                break;
            case AuctionCancel:
                int auctionId = inPacket.decodeInt();
                ai = acc.getAuctionById(auctionId);
                if (ai == null || ai.getState() != AuctionState.Init) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                ai.setEndDate(FileTime.currentTime());
                ai.setState(AuctionState.Expire);
                DatabaseManager.saveToDB(ai);
                // show items again
                items = acc.getSellingAuctionItems();
                chr.write(FieldPacket.auctionResult(AuctionResult.showSellingItems(items)));
                break;
            case LoadSoldItems:
                items = acc.getCompletedAuctionItems();
                chr.write(FieldPacket.auctionResult(AuctionResult.showSoldItems(items)));
                break;
            case Reclaim:
                auctionId = inPacket.decodeInt();
                ai = acc.getAuctionById(auctionId);
                if (ai == null || (ai.getState() != AuctionState.Reserve && ai.getState() != AuctionState.Expire)) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                item = ai.getItem();
                invType = item.getInvType();
                if (chr.getInventoryByType(invType).isFull()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.InsufficientSlots)));
                    return;
                }
                ai.setState(ai.getState() == AuctionState.Expire ? AuctionState.Done : AuctionState.BidSuccessDone);
                chr.addItemToInventory(item);
                items = acc.getCompletedAuctionItems();
                DatabaseManager.saveToDB(ai);
                chr.write(FieldPacket.auctionResult(AuctionResult.showSoldItems(items)));
                break;
            case ItemListAgain:
                auctionId = inPacket.decodeInt();
                ai = acc.getAuctionById(auctionId);
                if (ai == null || ai.getState() != AuctionState.Expire) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                cost = GameConstants.AUCTION_DEPOSIT_AMOUNT;
                if (cost > chr.getMoney()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.InsufficientFunds)));
                    return;
                }
                chr.deductMoney(GameConstants.AUCTION_DEPOSIT_AMOUNT);
                AuctionItem newAi = ai.deepCopy();
                chr.write(FieldPacket.auctionResult(AuctionResult.listItem(newAi)));
                chr.getWorld().addAuction(ai, true);
                acc.addAuction(ai);
                DatabaseManager.saveToDB(ai);
                items = acc.getCompletedAuctionItems();
                chr.write(FieldPacket.auctionResult(AuctionResult.showSoldItems(items)));
                break;
            case PurchaseSingle:
                auctionId = inPacket.decodeInt();
                ai = world.getAuctionById(auctionId);
                if (ai == null || ai.getState() != AuctionState.Init || ai.getAccountID() == acc.getId()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                cost = ai.getDirectPrice();
                if (cost > chr.getMoney()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.InsufficientFunds)));
                    return;
                }
                ai.setBidUserID(chr.getId());
                ai.setBidUsername(chr.getName());
                ai.setState(AuctionState.Sold);
                ai.setEndDate(FileTime.currentTime());
                ai.setSoldQuantity(ai.getSoldQuantity() + 1);
                DatabaseManager.saveToDB(ai);
                AuctionItem sellItem;
                sellItem = acc.createAndAddAuctionByItem(ai.getItem(), chr, ai.getPrice());
                sellItem.setDirectPrice(ai.getDirectPrice());
                sellItem.setEndDate(FileTime.currentTime());
                sellItem.setState(AuctionState.Reserve);
                DatabaseManager.saveToDB(sellItem);
                chr.write(FieldPacket.auctionResult(AuctionResult.purchaseItem(at, auctionId)));
                break;
            case PurchaseMultiple:
                auctionId = inPacket.decodeInt();
                long price = inPacket.decodeLong();
                int buyQuant = inPacket.decodeInt();
                ai = world.getAuctionById(auctionId);
                if (ai == null || ai.getQuantity() <= 0 || ai.getState() != AuctionState.Init
                        || ai.getAccountID() == acc.getId()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                buyQuant = Math.min(buyQuant, ai.getQuantity());
                cost = ai.getDirectPrice() * buyQuant;
                if (cost > chr.getMoney()) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.InsufficientFunds)));
                    return;
                }
                chr.deductMoney(cost);
                ai.setSoldQuantity(ai.getSoldQuantity() + buyQuant);
                if (ai.getQuantity() == buyQuant) {
                    ai.setBidUserID(chr.getId());
                    ai.setBidUsername(chr.getName());
                    ai.setState(AuctionState.Sold);
                    ai.setEndDate(FileTime.currentTime());
                    sellItem = acc.createAndAddAuctionByItem(ai.getItem(), chr, ai.getPrice());
                    sellItem.setDirectPrice(ai.getDirectPrice() * buyQuant);
                    sellItem.setEndDate(FileTime.currentTime());
                    sellItem.setState(AuctionState.Reserve);
                } else {
                    ai.getItem().setQuantity(ai.getQuantity() - buyQuant);
                    Item splitItem = ai.getItem().deepCopy();
                    splitItem.setQuantity(buyQuant);
                    sellItem = acc.createAndAddAuctionByItem(splitItem, chr, ai.getPrice());
                    sellItem.setDirectPrice(ai.getDirectPrice() * buyQuant);
                    sellItem.setEndDate(FileTime.currentTime());
                    sellItem.setState(AuctionState.Reserve);
                }
                DatabaseManager.saveToDB(ai);
                DatabaseManager.saveToDB(sellItem);
                chr.write(FieldPacket.auctionResult(AuctionResult.purchaseItem(at, auctionId)));
                break;
            case Collect:
                auctionId = inPacket.decodeInt();
                ai = acc.getAuctionById(auctionId);
                if (ai == null || ai.getState() != AuctionState.Sold) {
                    chr.write(FieldPacket.auctionResult(AuctionResult.error(at, AuctionResultCode.NotExist)));
                    return;
                }
                // Could also handle bids here if implemented
                // Just assuming that only buyout is possible right now
                long collectMoney = (long) (ai.getDeposit() + (ai.getDirectPrice() * ai.getSoldQuantity()) * GameConstants.AUCTION_TAX);
                ai.setState(AuctionState.SoldDone);
                DatabaseManager.saveToDB(ai);
                chr.addMoney(collectMoney);
                items = acc.getCompletedAuctionItems();
                chr.write(FieldPacket.auctionResult(AuctionResult.showSoldItems(items)));
                break;
            default:
                log.warn(String.format("Unhandled auction type %s", at));

        }
    }
}
