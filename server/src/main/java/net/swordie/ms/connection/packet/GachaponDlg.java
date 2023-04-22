package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.gach.result.GachaponDlgType;
import net.swordie.ms.world.gach.result.GachaponResult;

import java.util.List;

import static net.swordie.ms.world.gach.result.GachaponResult.SUCCESS;

public class GachaponDlg {
    public static OutPacket openGach(GachaponDlgType dlgType, List<String> messages, List<Tuple<Item, Short>> items, List<Integer> hotItems) {
        OutPacket outPacket = new OutPacket(OutHeader.GACH_OPEN);
        outPacket.encodeByte(1);// don't really know what it should be but if it <= 0 the game will crash.
        outPacket.encodeByte(dlgType.getType());

        outPacket.encodeByte(messages.size());
        for (String message : messages) {
            outPacket.encodeString(message);
        }


        outPacket.encodeShort(items.size());// length (just for testing).
        for (Tuple<Item, Short> i : items) {
            Item item = i.getLeft();
            outPacket.encodeInt(item.getItemId());
            outPacket.encodeShort(i.getRight());
            item.encode(outPacket);
        }

        // start loop 2
        outPacket.encodeByte(hotItems.size());
        for (Integer hotItem : hotItems) {
            outPacket.encodeByte(dlgType.getType());// not really sure.. maybe order
            outPacket.encodeInt(hotItem);
        }
        return outPacket;
    }

    public static OutPacket gachResult(GachaponResult result) { return gachResult(result, null, (short) 0); }
    public static OutPacket gachResult(GachaponResult result, Item item, short quantity) {
        OutPacket outPacket = new OutPacket(OutHeader.GACH_RESULT);
        outPacket.encodeByte(result.getValue());
        if (result == SUCCESS) {
            outPacket.encodeInt(item.getItemId());
            outPacket.encodeShort(quantity);
            item.encode(outPacket);
        }// IF type == 2 => String (according to IDA).
        return outPacket;
    }
}
