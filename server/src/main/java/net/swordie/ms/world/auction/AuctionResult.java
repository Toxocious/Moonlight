package net.swordie.ms.world.auction;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.AuctionResultCode;
import net.swordie.ms.enums.AuctionState;
import net.swordie.ms.util.FileTime;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sjonnie
 * Created on 11/21/2018.
 */
public class AuctionResult implements Encodable {
    private AuctionType type;
    private AuctionResultCode code = AuctionResultCode.None;
    private int auctionId;
    private boolean bool1;
    private boolean bool2;
    private AuctionItem item;
    private Set<AuctionItem> items = new HashSet<>();

    private AuctionResult(){}

    public AuctionResult(AuctionType type) {
        this.type = type;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(type.getVal());
        outPacket.encodeInt(code.getVal()); // nResultCode
        outPacket.encodeInt(auctionId); // nAuctionId
        switch (type) {
            case SearchItems:
            case SearchMarketPlace:
                outPacket.encodeByte(bool1); // bOverride
                outPacket.encodeByte(bool2);
                outPacket.encodeInt(items.size());
                for (AuctionItem item : items) {
                    outPacket.encode(item);
                }
                break;
            case WishList:
            case LoadSellItems:
                items.add(AuctionItem.testItem());
                outPacket.encodeInt(items.size());
                for (AuctionItem item : items) {
                    outPacket.encode(item);
                }
                break;
            case LoadSoldItems:
                outPacket.encodeInt(items.size());
                for (AuctionItem item : items) {
                    item.encodeHistory(outPacket);
                    outPacket.encodeByte(item.getState() != AuctionState.Done);
                    if (item.getState() != AuctionState.Done) {
                        item.encode(outPacket);
                    }
                }
                break;
            case Unk60:
                outPacket.encodeInt(items.size());
                for (AuctionItem ai : items) {
                    Item item = ai.getItem();
                    outPacket.encodeInt(item.getItemId());
                    outPacket.encodeFT(ai.getRegDate());
                    outPacket.encodeInt(0); // ignored
                    outPacket.encodeFT(ai.getEndDate());
                    outPacket.encodeInt(0); // ignored
                }
                break;
            case ListItem:
                // Just uses the first 3 args
                break;
            case SellItemAdd:
                outPacket.encodeByte(item.getState() != AuctionState.Done);
                if (item != null) {
                    outPacket.encode(item);
                }
                break;
            case SoldItemAdd:
                outPacket.encodeFT(item.getEndDate());
                outPacket.encodeByte(item.getItem() != null);
                if (item.getItem() != null) {
                    item.encodeHistory(outPacket);
                    outPacket.encodeByte(item.getState() != AuctionState.Done);
                    if (item.getState() != AuctionState.Done) {
                        outPacket.encode(item);
                    }
                }
                break;
            case WishlistAdd:
            case Unk73:
                outPacket.encodeByte(item.getItem() != null);
                if (item.getItem() != null) {
                    outPacket.encode(item);
                }
                break;
        }
    }

    public static AuctionResult showSearchItems(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.SearchItems);
        ar.bool1 = true; // overrides the old results
        ar.items = items;
        return ar;
    }

    public static AuctionResult showMarketplaceItems(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.SearchMarketPlace);
        ar.bool1 = true; // overrides the old results
        ar.items = items;
        return ar;
    }

    public static AuctionResult showMarketplaceItems(Set<AuctionItem> items, AuctionResultCode code) {
        AuctionResult ar = new AuctionResult(AuctionType.SearchMarketPlace);
        ar.bool1 = true; // overrides the old results
        ar.items = items;
        ar.code = code;
        return ar;
    }

    public static AuctionResult showWishlist(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.WishList);
        ar.items = items;
        return ar;
    }

    public static AuctionResult showSellingItems(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.LoadSellItems);
        ar.items = items;
        return ar;
    }

    public static AuctionResult listItem(AuctionItem item) {
        AuctionResult ar = new AuctionResult(AuctionType.LoadSellItems);
        ar.item = item;
        return ar;
    }

    public static AuctionResult showSoldItems(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.LoadSoldItems);
        ar.items = items;
        return ar;
    }

    public static AuctionResult sellItem(boolean success, int auctionId) {
        AuctionResult ar = new AuctionResult(AuctionType.ListItem);
        ar.bool1 = success;
        ar.auctionId = auctionId;
        return ar;
    }

    public static AuctionResult purchaseItem(AuctionType at, int auctionId) {
        AuctionResult ar = new AuctionResult(at);
        ar.auctionId = auctionId;
        return ar;
    }

    public static AuctionResult unk60(Set<AuctionItem> items) {
        AuctionResult ar = new AuctionResult(AuctionType.LoadSoldItems);
        ar.items = items;
        return ar;
    }

    public static AuctionResult unk70(AuctionItem item) {
        AuctionResult ar = new AuctionResult(AuctionType.SellItemAdd);
        ar.auctionId = item.getId();
        ar.item = item;
        return ar;
    }

    public static AuctionResult unk71(AuctionItem item) {
        AuctionResult ar = new AuctionResult(AuctionType.SoldItemAdd);
        ar.auctionId = item.getId();
        ar.item = item;
        return ar;
    }

    public static AuctionResult unk72(AuctionItem item) {
        AuctionResult ar = new AuctionResult(AuctionType.WishlistAdd);
        ar.auctionId = item.getId();
        ar.item = item;
        return ar;
    }

    public static AuctionResult unk73(AuctionItem item) {
        AuctionResult ar = new AuctionResult(AuctionType.Unk73);
        ar.auctionId = item.getId();
        ar.item = item;
        return ar;
    }

    public static AuctionResult error(AuctionType at, AuctionResultCode code) {
        AuctionResult ar = new AuctionResult(at);
        ar.code = code;
        return ar;
    }

    public static AuctionResult initialize() {
        AuctionResult ar = new AuctionResult(AuctionType.Initialize);
        ar.code = AuctionResultCode.None;
        return ar;
    }



}
