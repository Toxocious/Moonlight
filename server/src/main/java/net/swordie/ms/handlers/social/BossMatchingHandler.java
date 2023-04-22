package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.enums.BossMatchingType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;

public class BossMatchingHandler {
    @Handler(op = InHeader.CHECK_BOSS_PARTY_BY_SCRIPT)
    public static void handleCheckBossPartyByScript(Client c, InPacket inPacket) {
        int queueType = inPacket.decodeInt();
        int selectionNumber = inPacket.decodeInt();
        BossMatchingType selection = BossMatchingType.getByVal(selectionNumber);
        c.getChr().chatMessage("selected: %d", selectionNumber);
        int difficulty = inPacket.decodeInt(); // 0, 1, 2, 3 - Easy, Normal, Hard, Chaos

        Char chr = c.getChr();

        if (selection.getFieldID() == -1 || chr.getField() == null || chr.getInstance() != null
                || chr.getFieldID() == selection.getFieldID()) {
//            chr.write(UserLocal.bossPartyCheckDone(5));
            chr.chatMessage("Can't teleport to boss now!");
            return;
        }

        Party party = chr.getParty();
        if (party != null && party.getPartyLeaderID() == chr.getId()) {
            for (Char pm : party.getPartyMembersInField(chr)) {
                pm.warp(selection.getFieldID());
            }
        } else {
            chr.warp(selection.getFieldID());
        }
    }
}
