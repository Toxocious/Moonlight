package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.enums.CashShopActionType;
import net.swordie.ms.enums.CashShopInfoType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.shop.cashshop.CashItemInfo;
import net.swordie.ms.world.shop.cashshop.CashShop;
import net.swordie.ms.world.shop.cashshop.CashShopCategory;
import net.swordie.ms.world.shop.cashshop.CashShopItem;

import java.util.List;

/**
 * Created on 4/23/2018.
 */
public class CCashShop {
    public static OutPacket queryCashResult(Char chr) {
        User user = chr.getUser();
        Account account = chr.getAccount();

        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_QUERY_CASH_RESULT);

        outPacket.encodeInt(account.getNxCredit());
        outPacket.encodeInt(user.getMaplePoints());
        outPacket.encodeInt(chr.getRewardPoints());
        outPacket.encodeInt(user.getNxPrepaid());

        return outPacket;
    }

    public static OutPacket cashItemResBuyDone(CashItemInfo cashItemInfo, FileTime registerDate, CashItemInfo receiveBonus,
                                               int someInt) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_Buy_Done.getVal());
        cashItemInfo.encode(outPacket);
        boolean hasRegisterDate = registerDate != null;
        outPacket.encodeInt(hasRegisterDate ? 1 : 0);
        if (hasRegisterDate) {
            outPacket.encodeFT(registerDate);
        }
        boolean hasReceiveBonus = receiveBonus != null;
        outPacket.encodeByte(hasReceiveBonus);
        if (receiveBonus != null) {
            receiveBonus.encode(outPacket);
        }
        boolean hasSomeInt = someInt != 0;
        outPacket.encodeByte(hasSomeInt);
        if (hasSomeInt) {
            outPacket.encodeInt(someInt);
        }

        return outPacket;
    }

    public static OutPacket surpriseBox(CashItemInfo reward, CashItemInfo box, boolean Rare) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_IDK9);

        outPacket.encodeByte(CashItemType.Premium_Style.getVal());
        outPacket.encodeLong(box.getCashItemSN()); // probs SN doesn't seem like a date
        outPacket.encodeInt(box.getQuantity());//Box Quantity
        outPacket.encodeInt(box.getItemID());
        box.encode(outPacket);
        // sub_28F7660 -------------
        outPacket.encodeInt(reward.getItemID());
        outPacket.encodeByte(1); //Quantity?
        outPacket.encodeByte(Rare); //

        return outPacket;
    }


    public static OutPacket error() {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_Buy_Failed.getVal());
        outPacket.encodeByte(137);
        outPacket.encodeShort(0);

        return outPacket;
    }

    public static OutPacket webShopOrderGetList_Done(List<CashItemInfo> firstList, List<Item> secondList) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_WebShopOrderGetList_Done.getVal());
        outPacket.encodeShort(firstList.size());
        for (CashItemInfo cii : firstList) {
            cii.encode(outPacket);
        }
        outPacket.encodeShort(secondList.size());
        for (Item item : secondList) {
            item.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket bannerInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.Banner.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(3); // size

        new CashShopItem().setItemID(1002140).encode(outPacket);
        new CashShopItem().setItemID(1002140).encode(outPacket);
        new CashShopItem().setItemID(1002140).encode(outPacket);

        return outPacket;
    }

    public static OutPacket topSellerInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.TopSellers.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(3); // size

        new CashShopItem().setItemID(1072285).encode(outPacket);
        new CashShopItem().setItemID(1072418).encode(outPacket);
        new CashShopItem().setItemID(1072431).encode(outPacket);

        return outPacket;
    }

    public static OutPacket specialItemInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.SpecialItems.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(3); // size

        new CashShopItem().setItemID(1042033).encode(outPacket);
        new CashShopItem().setItemID(1051076).encode(outPacket);
        new CashShopItem().setItemID(1072431).encode(outPacket);

        return outPacket;
    }

    public static OutPacket featuredItemInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.FeaturedItems.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(3); // size

        new CashShopItem().setItemID(1051120).encode(outPacket);
        new CashShopItem().setItemID(1051099).encode(outPacket);
        new CashShopItem().setItemID(1051205).encode(outPacket);

        return outPacket;
    }

    public static OutPacket specialSaleInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.SpecialSale.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(1); // size

        new CashShopItem().setItemID(5040004).encode(outPacket);

        return outPacket;
    }

    public static OutPacket cartInfo(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.Cart.getVal());
        outPacket.encodeByte(1); // 0 does not encode anything, 2 does the same as 1 (encoding wise)
        outPacket.encodeByte(0); // size

        return outPacket;
    }

    public static OutPacket categoryInfo(CashShop cashShop) {
        List<CashShopCategory> categories = cashShop.getCategories();
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.Categories.getVal());
        outPacket.encodeByte(1);
        outPacket.encodeByte(categories == null ? 0 : categories.size());

        categories.forEach(cat -> cat.encode(outPacket));

        return outPacket;
    }

    public static OutPacket bannerMsg(CashShop cashShop, List<String> messages) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeByte(CashShopInfoType.BannerMsg.getVal());
        outPacket.encodeByte(messages == null ? 0 : messages.size());

        messages.forEach(msg -> {
            outPacket.encodeString(msg);
            outPacket.encodeLong(0);
            outPacket.encodeLong(0);
        });

        return outPacket;
    }

    public static OutPacket oneTen(CashShop cashShop) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_INFO);

        outPacket.encodeInt(1022259);
        outPacket.encodeInt(1022259);
        outPacket.encodeInt(1022259);

        return outPacket;
    }

    public static OutPacket openCategoryResult(CashShop cashShop, int categoryIdx) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_ACTION);

        outPacket.encodeByte(CashShopActionType.ShowCategory.getVal());
        outPacket.encodeByte(1);
        List<CashShopItem> items = cashShop.getItemsByCategoryIdx(categoryIdx);
        if (items == null) {
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeByte(items.size());
            items.forEach(item -> item.encode(outPacket));
        }

        return outPacket;
    }

    public static OutPacket resMoveLtoSDone(Item item) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_MoveLtoS_Done.getVal());
        outPacket.encodeByte(true); // bExclRequestSent
        outPacket.encodeShort(item.getBagIndex());
        item.encode(outPacket);
        outPacket.encodeInt(0); // List of SNs (longs)
        outPacket.encodeByte(0); // Bonus cash item (CashItemInfo::Decode)

        return outPacket;
    }

    public static OutPacket resMoveStoLDone(CashItemInfo cii, Item extraMovedItem) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_MoveStoL_Done.getVal());
        boolean extraItem = extraMovedItem != null;
        outPacket.encodeByte(extraItem);
        if (extraItem) {
            outPacket.encodeLong(extraMovedItem.getId());
            outPacket.encodeInt(extraMovedItem.getItemId());
        }
        outPacket.encode(cii);

        return outPacket;
    }

    public static OutPacket loadLockerDone(Account account) {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_LoadLocker_Done.getVal());
        Trunk trunk = account.getTrunk();
        List<CashItemInfo> locker = trunk.getLocker();
        int lockerSize = locker.size();
        boolean isOverMaxSlots = lockerSize > GameConstants.MAX_LOCKER_SIZE;
        outPacket.encodeByte(isOverMaxSlots);
        if (isOverMaxSlots) {
            outPacket.encodeInt(lockerSize - GameConstants.MAX_LOCKER_SIZE);
        }
        outPacket.encodeShort(lockerSize);
        locker.forEach(item -> {
            item.encode(outPacket);
        });
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encode(new Item()); // bonus items
        }
        outPacket.encodeShort(GameConstants.MAX_LOCKER_SIZE);
        outPacket.encodeShort(account.getUser().getCharacterSlots());
        outPacket.encodeShort(0);
        outPacket.encodeShort(account.getCharacters().size());

        return outPacket;
    }

    public static OutPacket fullInventoryMsg() {
        OutPacket outPacket = new OutPacket(OutHeader.CASH_SHOP_CASH_ITEM_RESULT);

        outPacket.encodeByte(CashItemType.Res_MoveLtoS_Failed.getVal());
        outPacket.encodeByte(10);

        return outPacket;
    }
}