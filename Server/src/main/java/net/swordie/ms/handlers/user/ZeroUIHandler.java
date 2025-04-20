package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;

public class ZeroUIHandler {

    @Handler(op = InHeader.INHERITANCE_INFO_REQUEST)
    public static void handleWeaponInfo(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        int rank = chr.getZeroWeaponRank();
        int levelReq = 100 + (rank * 10);
        int newLazuli = 1572000 + (rank + 1);
        int newLapis = 1562000 + (rank + 1);
        boolean canOpen = true;
        if (rank >= 7) {
            canOpen = false;
        }
        //Char chr, int rank, int levelReq, int newLazuli, int newLapis, int newRank)
        chr.write(WvsContext.inheritanceInfoResult(canOpen, rank, levelReq, newLazuli, newLapis, rank + 1, 0, 0));
    }

    @Handler(op = InHeader.INHERITANCE_UPGRADE_REQUEST)
    public static void handleUpgradeWeapon(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        inPacket.decodeInt();
        inPacket.decodeShort();
        inPacket.decodeByte();
        int rank = inPacket.decodeInt();
        int levelReq = switch (rank) {
            case 1 -> 100;
            case 2 -> 110;
            case 3 -> 120;
            case 4 -> 130;
            case 5 -> 140;
            case 6 -> 150;
            case 7 -> 170;
            case 8 -> 180;
            case 9 -> 190;
            case 10 -> 200;
            default -> 100;
        };
        if (chr.getLevel() < levelReq) {
            chr.write(UserLocal.noticeMsg("You aren't high enough level to upgrade your weapon you must be at least level " + levelReq + " for the next rank", true));
            return;
        }
        Inventory equipInv = chr.getEquippedInventory();
        Item oldEquip = equipInv.getItemBySlot(11);
        Item oldEquip2 = equipInv.getItemBySlot(10);
        if (oldEquip != null && oldEquip2 != null) {
            equipInv.removeItem(oldEquip);
            equipInv.removeItem(oldEquip2);
            oldEquip.updateToChar(chr);
            oldEquip2.updateToChar(chr);
            chr.getAvatarData().getAvatarLook().removeItem((byte) 10, oldEquip.getItemId(), -1, false);
            chr.getAvatarData().getAvatarLook().removeItem((byte) 11, oldEquip2.getItemId(), -1, false);
            oldEquip.drop();
            oldEquip2.drop();
        }
        chr.getScriptManager().giveAndEquipZero(1572000 + rank, 1562000 + rank); // secondary

        AvatarLook mainLook = chr.getAvatarData().getAvatarLook();
        AvatarLook zeroLook = chr.getAvatarData().getZeroAvatarLook();
        mainLook.setWeaponId(1572000 + rank);
        mainLook.setSubWeaponId(0);
        zeroLook.setWeaponId(1562000 + rank);
        zeroLook.setSubWeaponId(0);
        zeroLook.setZeroBetaLook(true);

        chr.setQuestRecordEx(9000001, "wepRank", rank);
        DatabaseManager.saveToDB(chr.getUser());

        // chr.changeChannelAndWarp(chr.getClient().getChannel(), chr.getFieldID());
        if (rank + 1 < 8) {
            chr.write(WvsContext.inheritanceInfoResult(true, rank, levelReq, 1572000 + rank + 1, 1562000 + rank + 1, rank + 1, 0, 0));
        }
    }

    @Handler(op = InHeader.EGO_EQUIP_CREATE_UPGRADE_ITEM_COST_REQUEST)
    public static void unk(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        chr.write(WvsContext.unkZero());
        chr.write(WvsContext.unkZero2());
    }

    @Handler(op = InHeader.EGO_EQUIP_TALK_REQUEST)
    public static void equpTalkRequest(Client c, InPacket inPacket) {
    }

}
