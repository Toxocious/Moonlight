package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MiniRoom;
import net.swordie.ms.client.character.TradeRoom;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.MiniRoomType;
import net.swordie.ms.enums.RoomLeaveType;
import net.swordie.ms.handlers.header.OutHeader;

public class MiniroomPacket {

    public static OutPacket enterTrade(TradeRoom tradeRoom, Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.EnterTrade.getVal());
        outPacket.encodeByte(4);
        outPacket.encodeByte(2);

        outPacket.encodeByte(1); // client is always on the right side

        Char other = tradeRoom.getOtherChar(chr);
        outPacket.encodeByte(0); // trade partner is always on the left
        other.getAvatarData().getAvatarLook().encode(outPacket);
        outPacket.encodeString(other.getName());
        outPacket.encodeShort(other.getJob());

        outPacket.encodeByte(1);
        chr.getAvatarData().getAvatarLook().encode(outPacket);
        outPacket.encodeString(chr.getName());
        outPacket.encodeShort(chr.getJob());

        outPacket.encodeByte(-1); // end indicator

        return outPacket;
    }


    public static OutPacket putItem(int user, int pos, Item item) {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.PlaceItem.getVal());
        outPacket.encodeByte(user);
        outPacket.encodeByte(pos);
        item.encode(outPacket);

        return outPacket;
    }

    public static OutPacket putMoney(int user, long money) {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.SetMesos.getVal());
        outPacket.encodeByte(user);
        outPacket.encodeLong(money);

        return outPacket;
    }

    public static OutPacket tradeRestraintItem() {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.TradeRestraintItem.getVal());

        return outPacket;
    }

    public static OutPacket tradeInvite(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.TradeInviteRequest.getVal());
        outPacket.encodeByte(4);
        outPacket.encodeString(chr.getName());
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket cancelTrade() {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.ExitTrade.getVal());
        outPacket.encodeByte(0); // other user cancelled
        outPacket.encodeByte(RoomLeaveType.TRLeave_TradeFail_Denied.getVal());

        return outPacket;
    }


    public static OutPacket tradeComplete() {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.ExitTrade.getVal());
        outPacket.encodeByte(1);
        outPacket.encodeByte(RoomLeaveType.TRLeave_TradeDone.getVal());

        return outPacket;
    }

    public static OutPacket tradeConfirm() {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.Trade.getVal());

        return outPacket;
    }

    public static OutPacket chat(Char chr, int pos, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.MINI_ROOM_BASE_DLG);

        outPacket.encodeByte(MiniRoomType.Chat.getVal());
        outPacket.encodeByte(25); // ?
        outPacket.encodeByte(pos);
        outPacket.encodeString(msg);
        outPacket.encodeInt(0);
        chr.encodeChatInfo(outPacket, msg);
        return outPacket;
    }
}