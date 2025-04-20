package net.swordie.ms.handlers.script;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.InGameDirectionEvent;
import net.swordie.ms.connection.packet.RandomPortal;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.npc.NpcMessageType;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.*;

public class ScriptHandler {

    private static final Logger log = Logger.getLogger(ScriptHandler.class);


    @Handler(op = InHeader.USER_SCRIPT_MESSAGE_ANSWER)
    public static void handleUserScriptMessageAnswer(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        ScriptManagerImpl smi = chr.getScriptManager();
        byte lastType = inPacket.decodeByte();
        NpcMessageType nmt = smi.getNpcScriptInfo().getMessageType();
        if (nmt == null) {
            nmt = lastType < NpcMessageType.values().length
                    ? Arrays.stream(NpcMessageType.values()).filter(n -> n.getVal() == lastType).findAny().orElse(NpcMessageType.None)
                    : NpcMessageType.None;
        }
        if (nmt != NpcMessageType.Monologue) {
            byte action = inPacket.decodeByte();
            int answer = 0;
            boolean hasAnswer = false;
            String ans = null;
            if (nmt == NpcMessageType.AskIngameDirection) {
                InGameDirectionAsk answerType = InGameDirectionAsk.getByVal(action);
                if (answerType == null || answerType == InGameDirectionAsk.NOT) {
                    return;
                }
                boolean success = inPacket.decodeByte() == 1;// bSuccess
                if (answerType == InGameDirectionAsk.CAMERA_MOVE_TIME && success) {
                    int answerVal = inPacket.decodeInt();
                    chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.delay(answerVal)));
                    return;
                }
                chr.getScriptManager().handleAction(nmt, (byte) 1, answerType.getVal());
                return;
            }
            if (nmt == NpcMessageType.AskText && action == 1) {
                ans = inPacket.decodeString();
            } else if (inPacket.getUnreadAmount() >= 4) {
                answer = inPacket.decodeInt();
                hasAnswer = true;
            }
            if (nmt == NpcMessageType.AskAvatar || nmt == NpcMessageType.AskAvatarZero) {
                inPacket.decodeByte();
                hasAnswer = inPacket.decodeByte() != 0;
                if (hasAnswer) {
                    answer = inPacket.decodeByte();
                }
            }
            if (nmt == NpcMessageType.AskText && action != 0) {
                chr.getScriptManager().handleAction(nmt, action, ans);
            } else if ((nmt != NpcMessageType.AskNumber && nmt != NpcMessageType.AskMenu
                    && nmt != NpcMessageType.AskAvatar && nmt != NpcMessageType.AskAvatarZero
                    && nmt != NpcMessageType.AskSlideMenu) || hasAnswer) {
                // else -> User pressed escape in a selection (choice) screen
                if(nmt == NpcMessageType.AskSlideMenu)
                {
                    chr.warp(DimensionalPortalTownType.getByVal(answer).getMapID());
                    smi.stop(ScriptType.Portal);
                    smi.stop(ScriptType.Npc);
                    smi.stop(ScriptType.Reactor);
                    smi.stop(ScriptType.Quest);
                    smi.stop(ScriptType.Item);
                    chr.dispose();
                }
                chr.getScriptManager().handleAction(nmt, action, answer);
            } else {
                // User pressed escape in a selection (choice) screen
                chr.getScriptManager().dispose(false);
            }
        } else {
            chr.getScriptManager().handleAction(nmt, (byte) 1, 1); // Doesn't use  response nor answer
        }
    }

    @Handler(op = InHeader.DIRECTION_NODE_COLLISION)
    public static void handleDirectionNodeCollision(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        if (chr == null || chr.getField() == null) {
            return;
        }
        Field field = chr.getField();

        int node = inPacket.decodeInt();
        List<String> directionNode = field.getDirectionNode(node);
        if (directionNode == null) {
            return;
        }
        String script = directionNode.get(chr.getCurrentDirectionNode(node));
        if (script == null) {
            return;
        }
        chr.increaseCurrentDirectionNode(node);
        chr.getScriptManager().setCurNodeEventEnd(false);
        chr.getScriptManager().startScript(field.getId(), script, ScriptType.Field);
    }

    @Handler(op = InHeader.USER_CONTENTS_MAP_REQUEST)
    public static void handleUserContentsMapRequest(Char chr, InPacket inPacket) {
        inPacket.decodeShort();
        // TODO: verify levels and that the maps are actually in the contents guide
        int fieldID = inPacket.decodeInt();
        Field field = chr.getOrCreateFieldByCurrentInstanceType(fieldID);
        if (field == null || (field.getFieldLimit() & FieldOption.TeleportItemLimit.getVal()) > 0) {
            chr.chatMessage("You may not warp to that map.");
            chr.dispose();
            return;
        }
        chr.warp(field);
    }

    @Handler(op = InHeader.USER_RUN_SCRIPT)
    public static void handleUserRunScript(Char chr, InPacket inPacket) {
        SendTypeFromClient type = SendTypeFromClient.getByVal(inPacket.decodeShort());
        if (type == SendTypeFromClient.PlatformerStageExit) {
            int fieldID = inPacket.decodeInt();
            if (chr.getFieldID() == fieldID && GameConstants.getMaplerunnerField(fieldID) > 0) {
                chr.warp(chr.getOrCreateFieldByCurrentInstanceType(GameConstants.FOREST_OF_TENACITY));
            }
        }
    }

    @Handler(op = InHeader.ENTER_RANDOM_PORTAL_REQUEST)
    public static void handleEnterRandomPortalRequest(Char chr, InPacket inPacket) {
        int portalObjID = inPacket.decodeInt();
        Life life = chr.getField().getLifeByObjectID(portalObjID);
        if (!(life instanceof RandomPortal)) {
            chr.chatMessage("Could not find that portal.");
            return;
        }
        RandomPortal randomPortal = (RandomPortal) life;
        if (randomPortal.getCharID() != chr.getId()) {
            chr.chatMessage("A mysterious force is blocking your way.");
            chr.dispose();
            return;
        }
        RandomPortal.Type type = randomPortal.getAppearType();
        String script = type.getScript();
        chr.getScriptManager().startScript(randomPortal.getAppearType().ordinal(), randomPortal.getObjectId(),
                script, ScriptType.Portal);
        chr.dispose();
    }

    @Handler(op = InHeader.LIBRARY_START_SCRIPT)
    public static void handleLibraryStartScript(Char chr, InPacket inPacket) {
        int bookId = inPacket.decodeByte();
        if (chr.getQuestManager().hasQuestCompleted(32662)) {
            int questID = QuestConstants.DIMENSION_LIBRARY + bookId;
            chr.getScriptManager().startScript(questID, "q" + questID + "s", ScriptType.Quest);
        }
    }

    @Handler(op = InHeader.CROSS_HUNTER_COMPLETE_REQUEST)
    public static void handleCrossHunterCompleteRequest(Char chr, InPacket inPacket) {
        short tab = inPacket.decodeShort();
        HashMap<Item, Integer> itemMap = new HashMap<>();

        if (chr.getScriptManager().getQRValue(QuestConstants.SILENT_CRUSADE_WANTED_TAB_1 + tab).contains("r=1")) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to complete Silent Crusade Chapter %d, whilst already having claimed the reward.", chr.getId(), tab + 1));
            chr.dispose();
            return;
        }

        switch (tab) {
            case 0: // Chapter 1
                itemMap.put(ItemData.getItemDeepCopy(3700031), 1);  // Apprentice Hunter
                itemMap.put(ItemData.getItemDeepCopy(4310029), 10); // Crusader Coins  x10
                break;
            case 1: // Chapter 2
                itemMap.put(ItemData.getItemDeepCopy(3700032), 1);  // Capable Hunter
                itemMap.put(ItemData.getItemDeepCopy(4001832), 100);// Spell Traces  x100
                itemMap.put(ItemData.getItemDeepCopy(4310029), 15); // Crusader Coins  x15
                break;
            case 2: // Chapter 3
                itemMap.put(ItemData.getItemDeepCopy(3700033), 1);  // Veteran Hunter
                itemMap.put(ItemData.getItemDeepCopy(2430668), 1);  // Silent Crusade Mastery Book
                itemMap.put(ItemData.getItemDeepCopy(4310029), 20); // Crusader Coins  x20
                break;
            case 3: // Chapter 4
                itemMap.put(ItemData.getItemDeepCopy(3700034), 1);  // Superior Hunter
                itemMap.put(ItemData.getItemDeepCopy(4001832), 500);// Spell Traces  x500
                itemMap.put(ItemData.getItemDeepCopy(4310029), 30); // Crusader Coins  x30
                break;
        }

        if (!chr.canHold(new ArrayList<>(itemMap.keySet()))) {
            chr.chatMessage("Please make some space in your inventory.");
            chr.dispose();
            return;
        }

        chr.getScriptManager().setQRValue(QuestConstants.SILENT_CRUSADE_WANTED_TAB_1 + tab, "r=1");
        for (Map.Entry<Item, Integer> entry : itemMap.entrySet()) {
            Item item = entry.getKey();
            item.setQuantity(entry.getValue());
            chr.addItemToInventory(item);
        }
        chr.dispose();
    }

    @Handler(op = InHeader.CROSS_HUNTER_SHOP_REQUEST)
    public static void handleCrossHunterShopRequest(Char chr, InPacket inPacket) {
        if (inPacket.getUnreadAmount() == 0) {
            // shop showing
            return;
        }

        short itemIndexInShop = inPacket.decodeShort();
        int itemId = inPacket.decodeInt();
        short itemQuantity = inPacket.decodeShort();

        int crusaderCoin = 4310029;
        List<Integer> coinCostList = Arrays.asList(50, 40, 60, 255, 170, 85, 170, 85, 10);
        List<Integer> itemList = Arrays.asList(1132111, 1152069, 1122157, 1012331, 1022148, 1032156, 1122208, 1132182, 2030026);

        if (!itemList.contains(itemId)) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to trade an item {%d} that is not in the shop list.", chr.getId(), itemId));

        } else if (itemList.get(itemIndexInShop) != itemId) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to trade an item {%d} that is not in the given position {%d}.", chr.getId(), itemId, itemIndexInShop));

        } else if (ItemConstants.isEquip(itemId) && itemQuantity > 1) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to get a quantity {%d} that is more than 1 Silent Crusade equip {%d}.", chr.getId(), itemQuantity, itemId));

        } else if (itemIndexInShop >= itemList.size()) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to get an item from a shopIndex {%d} that is more than or equal to the amount of items in the shop {%d}.", chr.getId(), itemIndexInShop, itemList.size()));

        } else if (!chr.hasItemCount(crusaderCoin, (coinCostList.get(itemIndexInShop)) * itemQuantity)) {
            chr.chatMessage("You don't have enough Crusader Coins.");

        } else if (!chr.canHold(itemId)) {
            chr.chatMessage("You don't have any inventory space.");

        } else {
            chr.consumeItem(crusaderCoin, coinCostList.get(itemIndexInShop));
            chr.addItemToInventory(itemList.get(itemIndexInShop), 1);
        }
        chr.dispose();
    }

    @Handler(op = InHeader.UI_SCRIPT_REQUEST)
    public static void handleUiScriptRequest(Char chr, InPacket inPacket) {
        short typeVal = inPacket.decodeShort();
        int fieldID = inPacket.decodeInt();
        // No normal script: use a self-made one
        UiScript us = UiScript.getByVal(typeVal);
        if (us == null) {
            log.error("Unknown UiScript " + typeVal);
            return;
        }
        switch (us) {
            case UnionRaidStart:
                chr.getScriptManager().startScript(9010106, "unionRaid_IN", ScriptType.Npc);
                break;
            default:
                log.error("Unhandled UiScript " + us);
        }
    }

    @Handler(op = InHeader.GOLLUX_MINI_MAP_LEAVE_REQUEST)
    public static void handleGolluxMiniMapLeaveRequest(Char chr) {
        if (chr.getParty() == null) {
            return;
        }
        chr.getScriptManager().startScript(0, "GiantBossQuit1", ScriptType.Portal);
    }
}
