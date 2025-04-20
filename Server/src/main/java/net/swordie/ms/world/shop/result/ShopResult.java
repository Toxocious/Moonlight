package net.swordie.ms.world.shop.result;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.world.shop.NpcShopDlg;
import net.swordie.ms.world.shop.NpcShopItem;

import java.util.List;

/**
 * Created on 3/29/2018.
 */
public class ShopResult implements Encodable {

    private ShopResultType type;
    private NpcShopDlg shop;
    private int arg1;
    private int arg2;
    private int arg3;
    private List<NpcShopItem> buyBack;

    public ShopResult(ShopResultType type) {
        this.type = type;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(type.getVal());
        switch (type) {
            case Buy:
                outPacket.encodeByte(arg1 != 0); // repurchaseItem
                if (arg1 != 0) {
                    outPacket.encodeInt(arg1);
                } else {
                    outPacket.encodeInt(arg2); // ItemID
                    outPacket.encodeInt(arg3); // Stock
                    outPacket.encodeInt(0); // nStarCoinUpdate?
                }
                break;
            case Update:
                shop.encode(outPacket, buyBack);
                break;
            case Success:
                outPacket.encodeInt(arg1); // nItemID
                break;
            default:
                outPacket.encodeInt(arg1);
                break;
        }
    }

    public static ShopResult buy(int repurchaseItem, int someUpdateItem, int starCoinUpdate) {
        ShopResult sr = new ShopResult(ShopResultType.Buy);

        sr.arg1 = repurchaseItem;
        sr.arg2 = someUpdateItem;
        sr.arg3 = starCoinUpdate;

        return sr;
    }

    public static ShopResult msg(ShopResultType srt) {
        return new ShopResult(srt);
    }

    public static ShopResult intMsg(ShopResultType srt, int val) {
        ShopResult sr = new ShopResult(srt);

        sr.arg1 = val;

        return sr;
    }

    public static ShopResult sellSuccess(int soldItemId) {
        ShopResult sr = new ShopResult(ShopResultType.Success);

        sr.arg1 = soldItemId;

        return sr;
    }

    public static ShopResult update(Char chr, NpcShopDlg nsd) {
        ShopResult sr = new ShopResult(ShopResultType.Update);

        sr.shop = nsd;
        sr.buyBack = chr.getBuyBack();

        return sr;
    }
}
