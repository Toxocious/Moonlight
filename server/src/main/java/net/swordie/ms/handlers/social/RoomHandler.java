package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.anticheat.Offense;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.TradeRoom;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.MiniroomPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.MiniRoomType;
import net.swordie.ms.enums.PopularityResultType;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class RoomHandler {

    private static final Logger log = Logger.getLogger(RoomHandler.class);

    @Handler(op = InHeader.MINI_ROOM)
    public static void handleMiniRoom(Char chr, InPacket inPacket) {
        if (chr.getClient().getWorld().isReboot()) {
            log.error(String.format("Character %d attempted to use trade in reboot world.", chr.getId()));
            chr.dispose();
            return;
        }
        chr.dispose();
        byte type = inPacket.decodeByte(); // MiniRoom Type value
        MiniRoomType mrt = MiniRoomType.getByVal(type);
        if (mrt == null) {
            log.error(String.format("Unknown miniroom type %d", type));
            return;
        }
        TradeRoom tradeRoom = chr.getTradeRoom();
        switch (mrt) {
            case PlaceItem:
            case PlaceItem_2:
            case PlaceItem_3:
            case PlaceItem_4:
                byte invType = inPacket.decodeByte();
                short bagIndex = inPacket.decodeShort();
                short quantity = inPacket.decodeShort();
                byte tradeSlot = inPacket.decodeByte(); // trade window slot number

                Item item = chr.getInventoryByType(InvType.getInvTypeByVal(invType)).getItemBySlot(bagIndex);
                if (item.getQuantity() < quantity) {
                    chr.getOffenseManager().addOffense(String.format("Character {%d} tried to trade an item {%d} with a higher quantity {%s} than the item has {%d}.", chr.getId(), item.getItemId(), quantity, item.getQuantity()));
                    return;
                }
                if (!item.isTradable()) {
                    chr.getOffenseManager().addOffense(String.format("Character {%d} tried to trade an item {%d} whilst it was trade blocked.", chr.getId(), item.getItemId()));
                    return;
                }
                if (chr.getTradeRoom() == null) {
                    chr.chatMessage("You are currently not trading.");
                    return;
                }
                //Item offer = ItemData.getItemDeepCopy(item.getItemId());
                Item offer = item.deepCopy(); // So stats transfer Over
                offer.setQuantity(quantity);
                if (tradeRoom.canAddItem(chr)) {
                    int consumed = quantity > item.getQuantity() ? 0 : item.getQuantity() - quantity;
                    item.setQuantity(consumed + 1); // +1 because 1 gets consumed by consumeItem(item)
                    chr.consumeItem(item);
                    tradeRoom.addItem(chr, tradeSlot, offer);
                }
                Char other = tradeRoom.getOtherChar(chr);
                chr.write(MiniroomPacket.putItem(0, tradeSlot, offer));
                other.write(MiniroomPacket.putItem(1, tradeSlot, offer));

                break;
            case SetMesos:
            case SetMesos_2:
            case SetMesos_3:
            case SetMesos_4:
                long money = inPacket.decodeLong();
                if (tradeRoom == null) {
                    chr.chatMessage("You are currently not trading.");
                    return;
                }
                if (money < 0 || money > chr.getMoney()) {
                    chr.getOffenseManager().addOffense(String.format("Character %d tried to add an invalid amount of mesos(%d, own money: %d)",
                            chr.getId(), money, chr.getMoney()));
                    return;
                }
                chr.deductMoney(money);
                chr.addMoney(tradeRoom.getMoney(chr));
                tradeRoom.putMoney(chr, money);
                other = tradeRoom.getOtherChar(chr);
                chr.write(MiniroomPacket.putMoney(0, money));
                other.write(MiniroomPacket.putMoney(1, money));
                break;
            case Trade:
            case TradeConfirm:
            case TradeConfirm2:
            case TradeConfirm3:
                other = tradeRoom.getOtherChar(chr);
                other.write(MiniroomPacket.tradeConfirm());
                if (tradeRoom.hasConfirmed(other)) {
                    boolean success = tradeRoom.completeTrade();
                    if (success) {
                        chr.write(MiniroomPacket.tradeComplete());
                        other.write(MiniroomPacket.tradeComplete());
                    } else {
                        tradeRoom.cancelTrade();
                        tradeRoom.getChr().write(MiniroomPacket.cancelTrade());
                        tradeRoom.getOther().write(MiniroomPacket.cancelTrade());
                    }
                    chr.setTradeRoom(null);
                    other.setTradeRoom(null);
                } else {
                    tradeRoom.addConfirmedPlayer(chr);
                }
                break;
            case Chat:
                inPacket.decodeInt(); // tick
                String msg = inPacket.decodeString();
                if (tradeRoom == null) {
                    chr.chatMessage("You are currently not in a room.");
                    return;
                }
                // this is kinda weird atm, so no different colours
                String msgWithName = String.format("%s : %s", chr.getName(), msg);
                chr.write(MiniroomPacket.chat(chr,1, msgWithName));
                tradeRoom.getOtherChar(chr).write(MiniroomPacket.chat(chr,0, msgWithName));
                break;
            case Accept:
                if (tradeRoom == null) {
                    chr.chatMessage("Your trade partner cancelled the trade.");
                    return;
                }
                chr.write(MiniroomPacket.enterTrade(tradeRoom, chr));
                other = tradeRoom.getOtherChar(chr); // initiator
                other.write(MiniroomPacket.enterTrade(tradeRoom, other));

                // Start Custom ----------------------------------------------------------------------------------------
              //  String[] inventoryNames = new String[]{
               //         "eqp",
               //         "use",
               //         "etc",
               //         "setup",
               //         "cash",};
               // for (String invName : inventoryNames) {
              //      chr.write(MiniroomPacket.chat(1, String.format("%s has %d free %s slots", other.getName(), other.getInventoryByType(InvType.getInvTypeByString(invName)).getEmptySlots(), invName)));
               //     other.write(MiniroomPacket.chat(1, String.format("%s has %d free %s slots", chr.getName(), chr.getInventoryByType(InvType.getInvTypeByString(invName)).getEmptySlots(), invName)));
              //  }
                // End Custom ------------------------------------------------------------------------------------------

                break;
            case TradeInviteRequest:
                int charID = inPacket.decodeInt();
                other = chr.getField().getCharByID(charID);
                if (other == null) {
                    chr.chatMessage("Could not find that player.");
                    return;
                }
                if (other.getTradeRoom() != null) {
                    chr.chatMessage("That player is already trading.");
                    return;
                }
                other.write(MiniroomPacket.tradeInvite(chr));
                tradeRoom = new TradeRoom(chr, other);
                chr.setTradeRoom(tradeRoom);
                other.setTradeRoom(tradeRoom);
                break;
            case InviteResultStatic: // always decline?
                if (tradeRoom != null) {
                    other = tradeRoom.getOtherChar(chr);
                    other.chatMessage(String.format("%s has declined your trade invite.", chr.getName()));
                    other.setTradeRoom(null);
                }
                chr.setTradeRoom(null);
                break;
            case ExitTrade:
                if (tradeRoom != null) {
                    tradeRoom.cancelTrade();
                    tradeRoom.getOtherChar(chr).write(MiniroomPacket.cancelTrade());
                }
                break;
            case TradeConfirmRemoteResponse:
                // just an ack by the client?
                break;
            default:
                log.error(String.format("Unhandled miniroom type %s", mrt));
        }
    }

    @Handler(op = InHeader.USER_GIVE_POPULARITY_REQUEST)
    public static void handleUserGivePopularityRequest(Char chr, InPacket inPacket) {
        int targetChrId = inPacket.decodeInt();
        boolean increase = inPacket.decodeByte() != 0;

        Field field = chr.getField();
        Char targetChr = field.getCharByID(targetChrId);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();

        if (targetChr == null) { // Faming someone who isn't in the map or doesn't exist
            chr.write(WvsContext.givePopularityResult(PopularityResultType.InvalidCharacterId, targetChr, 0, increase));
            chr.dispose();
        } else if (chr.getLevel() < GameConstants.MIN_LEVEL_TO_FAME || targetChr.getLevel() < GameConstants.MIN_LEVEL_TO_FAME) { // Chr or TargetChr is too low level
            chr.write(WvsContext.givePopularityResult(PopularityResultType.LevelLow, targetChr, 0, increase));
            chr.dispose();
        } else if (!cs.getNextAvailableFameTime().isExpired()) { // Faming whilst Chr already famed within the FameCooldown time
            chr.write(WvsContext.givePopularityResult(PopularityResultType.AlreadyDoneToday, targetChr, 0, increase));
            chr.dispose();
        } else if (targetChrId == chr.getId()) {
            chr.getOffenseManager().addOffense(Offense.Type.Warning,
                    String.format("Character %d tried to fame themselves", chr.getId()));
        } else {
            targetChr.addStatAndSendPacket(Stat.pop, (increase ? 1 : -1));
            int curPop = targetChr.getAvatarData().getCharacterStat().getPop();
            chr.write(WvsContext.givePopularityResult(PopularityResultType.Success, targetChr, curPop, increase));
            targetChr.write(WvsContext.givePopularityResult(PopularityResultType.Notify, chr, curPop, increase));
            cs.setNextAvailableFameTime(FileTime.fromDate(LocalDateTime.now().plusHours(GameConstants.FAME_COOLDOWN)));
            if (increase) {
                Effect.showFameGradeUp(targetChr);
            }
        }
    }

    @Handler(op = InHeader.LIKE_POINT)
    public static void handleLikePoint(Client c, InPacket inPacket) {
        //TODO
    }
}
