package net.swordie.ms.client.character;

import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.BroadcastMsgType;

import java.util.List;

/**
 * Created by Asura on 17-6-2018.
 */
public class BroadcastMsg {
    private BroadcastMsgType broadcastMsgType;
    private Item item;
    private String string;
    private String string2;
    private String string3;
    private int arg1;
    private int arg2;
    private int arg3;
    private Char chr;

    public void encode(OutPacket outPacket) {
        BroadcastMsgType bmt = getBroadcastMsgType();
        outPacket.encodeByte(bmt.getVal());
        if (bmt == BroadcastMsgType.SlideNotice || bmt == BroadcastMsgType.Unk26) {
            if (getString() == null || getString().isEmpty()) {
                outPacket.encodeByte(0);
            } else {
                outPacket.encodeByte(getArg1());
            }
        }
        if (bmt != BroadcastMsgType.TryRegisterAutoStartQuest && bmt != BroadcastMsgType.TryRegisterAutoStartQuest_NoAnnouncement
                && bmt != BroadcastMsgType.Unk27) {
            outPacket.encodeString(getString());
        }
        if (bmt.isSmega()) {
            // vm sub also used in other chat functions
            chr.encodeChatInfo(outPacket, getString());
        }

        switch (bmt) {
            case Megaphone:
            case MegaphoneNoMessage:
            case Unk36:
            case Unk37:
            case Unk38:
                outPacket.encodeByte(getArg1()); // Channel
                outPacket.encodeByte(getArg2()); // Mega Ear
                break;
            case ItemMegaphone:
                outPacket.encodeByte(getArg1()); // Channel
                outPacket.encodeByte(getArg2()); // Mega Ear
                outPacket.encodeInt(0); // new unk
                outPacket.encodeByte(getArg3()); // Boolean  Item: Yes/No
                if(getArg3() != 0) {
                    getItem().encode(outPacket); // Item encode
                    outPacket.encodeString(""); // overwrite Item Name
                }
                break;
            case ItemMegaphoneNoItem:
                outPacket.encodeByte(getArg1()); // Channel
                break;
            case TripleMegaphone:
                outPacket.encodeByte(getArg1()); // StringList size
                if(getArg1() > 1) {
                    outPacket.encodeString(getString2()); // String 2
                    chr.encodeChatInfo(outPacket, getString2());
                }
                if(getArg1() > 2) {
                    outPacket.encodeString(getString3()); // String 3
                    chr.encodeChatInfo(outPacket, getString3());
                }
                outPacket.encodeByte(getArg2()); // Channel
                outPacket.encodeByte(getArg3()); // Mega Ear
                break;
            case BlowWeather:
                outPacket.encodeInt(getArg1());
                break;
            case BalloonMessage:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2()); // second
                outPacket.encodeByte(getChr() != null);
                if (getChr() != null) {
                    outPacket.encodeArr(getChr().getAvatarData().getAvatarLook().getPackedCharacterLook());
                }
                break;
            case WhiteYellow_ItemInfo:
                outPacket.encodeByte(getArg1()); // Boolean  Item: Yes/No
                if(getArg1() != 0) {
                    getItem().encode(outPacket); // Item encode
                }
                break;
            case Yellow:
            case Yellow_2:
                getItem().encode(outPacket); // Item encode
                break;
            case Unk26:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                break;
            case Unk28:
                outPacket.encodeInt(getArg1());
                outPacket.encodeInt(getArg2());
                outPacket.encodeString(getString2());
                getItem().encode(outPacket);
                break;
            case Unk29:
            case Unk30:
                outPacket.encodeString(getString2());
                getItem().encode(outPacket);
                break;
            case BlueChat_ItemInfo:
            case BlueChat_ItemInfo_2:
                outPacket.encodeInt(getArg1()); // item Id
                break;
            case GM_ErrorMessage:
                outPacket.encodeInt(getArg1()); // npc Id
                break;
            case YellowChatFiled_ItemInfo:
                outPacket.encodeInt(getArg1()); // item Id
                outPacket.encodeByte(getArg2()); // boolean: show item
                if (getArg2() != 0) {
                    getItem().encode(outPacket);
                }
                break;
            case RedWithChannelInfo:
                outPacket.encodeInt(getArg1()); //  chr Id
                // "#channel" will grab  Chr's  Channel
                break;
            case PopUpNotice:
                outPacket.encodeInt(getArg1()); // width
                outPacket.encodeInt(getArg2()); // height
                break;
            case Unk34:
                outPacket.encodeInt(getArg1()); // item Id
                break;
            case TryRegisterAutoStartQuest:
                outPacket.encodeInt(getArg1()); // Quest Id
                outPacket.encodeInt(getArg2()); // Time Out
                break;
            case TryRegisterAutoStartQuest_NoAnnouncement:
                outPacket.encodeInt(getArg1()); // Quest Id
                break;
        }
    }

    public static BroadcastMsg itemMegaphone(String string, byte channel, boolean whisperEar, boolean containsItem,
                                             Item item, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.ItemMegaphone);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1((byte) (channel - 1));
        broadcastMsg.setArg2((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setArg3((byte) (containsItem ? 1 : 0));
        broadcastMsg.setChr(chr);
        broadcastMsg.setItem(item);

        return broadcastMsg;
    }

    public static BroadcastMsg tripleMegaphone(List<String> stringList, byte channel, boolean whisperEar, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.TripleMegaphone);

        broadcastMsg.setArg1((byte) stringList.size());
        broadcastMsg.setString(stringList.get(0));
        if(stringList.size() > 1) {
            broadcastMsg.setString2(stringList.get(1));
        }
        if(stringList.size() > 2) {
            broadcastMsg.setString3(stringList.get(2));
        }
        broadcastMsg.setArg2((byte) (channel - 1));
        broadcastMsg.setArg3((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setChr(chr);

        return broadcastMsg;
    }

    public static BroadcastMsg megaphone(String string, byte channel, boolean whisperEar, Char chr) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.Megaphone);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1((byte) (channel - 1));
        broadcastMsg.setArg2((byte) (whisperEar ? 1 : 0));
        broadcastMsg.setChr(chr);

        return broadcastMsg;
    }

    public static BroadcastMsg notice(String string) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.Notice);

        broadcastMsg.setString(string);

        return broadcastMsg;
    }

    public static BroadcastMsg popUpMessage(String string) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.PopUpMessage);

        broadcastMsg.setString(string);

        return broadcastMsg;
    }

    public static BroadcastMsg popUpNotice(String string, int width, int height) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.PopUpNotice);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(width);
        broadcastMsg.setArg2(height);

        return broadcastMsg;
    }

    public static BroadcastMsg blueChatWithItemInfo(String string, Item item) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.BlueChat_ItemInfo);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(item.getItemId());
        broadcastMsg.setItem(item);

        return broadcastMsg;
    }

    public static BroadcastMsg errorMessage(String string, int npcId) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.GM_ErrorMessage);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(npcId);

        return broadcastMsg;
    }

    public static BroadcastMsg yellowFilled(String string, Item item, boolean show) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.YellowChatFiled_ItemInfo);

        broadcastMsg.setString(string);
        broadcastMsg.setItem(item);
        broadcastMsg.setArg1(show ? 1 : 0);

        return broadcastMsg;
    }

    public static BroadcastMsg slideNotice(String string, boolean show) {
        BroadcastMsg broadcastMsg = new BroadcastMsg();
        broadcastMsg.setBroadcastMsgType(BroadcastMsgType.SlideNotice);

        broadcastMsg.setString(string);
        broadcastMsg.setArg1(show ? 1 : 0);

        return broadcastMsg;
    }

    public BroadcastMsgType getBroadcastMsgType() {
        return broadcastMsgType;
    }

    public void setBroadcastMsgType(BroadcastMsgType broadcastMsgType) {
        this.broadcastMsgType = broadcastMsgType;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public String getString3() {
        return string3;
    }

    public void setString3(String string3) {
        this.string3 = string3;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    public int getArg3() {
        return arg3;
    }

    public void setArg3(int arg3) {
        this.arg3 = arg3;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }
}
