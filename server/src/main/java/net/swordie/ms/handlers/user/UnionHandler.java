package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.union.Union;
import net.swordie.ms.client.character.union.UnionBoard;
import net.swordie.ms.client.character.union.UnionMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.CUIHandler;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.loaders.QuestData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UnionHandler {

    private static final Logger log = Logger.getLogger(UnionHandler.class);


    @Handler(op = InHeader.UNION_REQUEST)
    public static void handleUnionRequest(Char chr, InPacket inPacket) {
        Account acc = chr.getAccount();
        Union union = chr.getUnion();
        // only take lv 60+ 3rd job characters
        Set<Char> eligibleChars = union.getEligibleUnionChars();
        UnionBoard activeBoard = union.getBoardByPreset(chr.getActiveUnionPreset());
        chr.write(CUIHandler.unionResult(union.getUnionCoin(), union.getUnionRank(), eligibleChars, activeBoard,
                null, null, null)); // todo: decide how u want to handle mobile/lab members
        chr.write(CUIHandler.unionCoin(union.getUnionCoin()));
    }

    @Handler(op = InHeader.UNION_ASSIGN_REQUEST)
    public static void handleUnionAssignRequest(Char chr, InPacket inPacket) {
        Union union = chr.getUnion();
        int preset = inPacket.decodeInt();
        if (!union.hasPresetUnlocked(preset)) {
            chr.getOffenseManager().addOffense("Tried changing locked preset " + preset + ", cur max = " + union.getPresets());
            chr.dispose();
            return;
        }
        chr.setActiveUnionPreset(preset);
        int count = inPacket.decodeInt();
        String synergyGrid = "";
        for (int i = 0; i < count; i++) {
            synergyGrid += String.format("%d=%d%s", i, inPacket.decodeInt(), i == 7 ? "" : ";");
        }
        QuestManager qm = chr.getQuestManager();
        Quest q = qm.getQuestById(QuestConstants.UNION_SYNERGY_BOARD);
        if (q == null) {
            q = QuestData.createQuestFromId(QuestConstants.UNION_SYNERGY_BOARD);
            qm.addQuest(q);
        }
        if (!q.getQRValue().equals(synergyGrid)) {
            q.setQrValue(synergyGrid);
            chr.write(WvsContext.questRecordExMessage(q));
        }
        count = inPacket.decodeInt();
        inPacket.decodeByte(); // Unknown
        inPacket.decodeByte(); // Unknown
        int labCount = inPacket.decodeByte();
        for (int i = 0; i < labCount; i++) {
            inPacket.decodeByte(); // Unknown
        }
        Account account = chr.getAccount();
        for (int i = 0; i < count; i++) {
            UnionMember.setCharGridPos(inPacket, preset, account);
        }
        for (int i = 0; i < labCount; i++) {
            UnionMember.setCharGridPos(inPacket, preset, account);
            // lab job = 10010900, lab enhanced job = 10010910
        }
        Set<Char> eligibleChars = union.getEligibleUnionChars();
        UnionBoard activeBoard = union.getBoardByPreset(chr.getActiveUnionPreset());

        chr.write(CUIHandler.unionAssignResult(union.getUnionRank(), eligibleChars, activeBoard,
                null, null, null));
    }

    @Handler(op = InHeader.UNION_PRESET_CHANGE)
    public static void handleUnionPresetChange(Char chr, InPacket inPacket) {
        Union union = chr.getUnion();
        int preset = inPacket.decodeInt();
        if (!union.hasPresetUnlocked(preset)) {
            chr.getOffenseManager().addOffense("Adding union preset they do not have (" + preset + ", max preset " + union.getPresets() + ")");
            chr.dispose();
            return;
        }
        boolean unlocked = inPacket.decodeByte() != 0; // unsure
        List<Integer> synGrid = new ArrayList<>();
        for (int i = 0; i < Union.MAX_STATS; i++) {
            int syn = inPacket.decodeInt();
            if (syn < 0 || syn >= Union.MAX_STATS) {
                chr.getOffenseManager().addOffense("Added invalid or duplicate union stat (expected 0~7, got " +
                        syn + ", grid up til this point = " + synGrid + ")");
                chr.dispose();
                return;
            }
            synGrid.add(syn);
        }
        UnionBoard ub = union.getBoardByPreset(preset);
        ub.setSynergyGrid(synGrid);
        chr.setActiveUnionPreset(preset);
        int count = inPacket.decodeInt();
        Account account = chr.getAccount();
        for (int i = 0; i < count; i++) {
            UnionMember.setCharGridPos(inPacket, preset, account);
        }
        Set<Char> eligibleChars = union.getEligibleUnionChars();
        UnionBoard activeBoard = union.getBoardByPreset(chr.getActiveUnionPreset());

        chr.write(CUIHandler.unionAssignResult(union.getUnionRank(), eligibleChars, activeBoard,
                null, null, null));
    }

    @Handler(op = InHeader.UNION_PRESET_INFO_REQUEST)
    public static void handleUnionPresetInfoRequest(Char chr, InPacket inPacket) {
        int preset = inPacket.decodeInt();
        Union union = chr.getUnion();
        UnionBoard ub = union.getBoardByPreset(preset);
        boolean unlocked = union.hasPresetUnlocked(preset);
        chr.write(WvsContext.unionPresetInfoResult(preset, unlocked, ub));
    }

    @Handler(op = InHeader.UNION_RAID_RETREAT)
    public static void handleUnionRaidRetreat(Char chr, InPacket inPacket) {
        inPacket.decodeInt(); // tRequestTime if i had to guess
        if (chr.getFieldID() == 921172000) {
            chr.warp(921172200);
        }
    }
}
