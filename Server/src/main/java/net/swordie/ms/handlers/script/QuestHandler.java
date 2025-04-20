package net.swordie.ms.handlers.script;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.MedalReissueResultType;
import net.swordie.ms.enums.QuestType;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.QuestData;
import net.swordie.ms.loaders.containerclasses.QuestInfo;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

public class QuestHandler {

    private static final Logger log = Logger.getLogger(QuestHandler.class);


    @Handler(op = InHeader.USER_QUEST_REQUEST)
    public static void handleUserQuestRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        QuestManager qm = chr.getQuestManager();
        byte type = inPacket.decodeByte();
        int questID = 0;
        int npcTemplateID = 0;
        Position position = null;
        QuestType qt = QuestType.getQTFromByte(type);
        boolean success = false;
        if (qt != null) {
            switch (qt) {
                case QuestReq_AcceptQuest: // Quest start
                case QuestReq_CompleteQuest: // Quest end
                case QuestReq_OpeningScript: // Scripted quest start
                case QuestReq_CompleteScript: // Scripted quest end
                    questID = inPacket.decodeInt();
                    npcTemplateID = inPacket.decodeInt();
                    if (inPacket.getUnreadAmount() > 4) {
                        position = inPacket.decodePosition();
                    }
                    break;
                case QuestReq_ResignQuest: //Quest forfeit
                    questID = inPacket.decodeInt();
                    chr.getQuestManager().removeQuest(questID);
                    break;
                case QuestReq_LaterStep:
                    questID = inPacket.decodeInt();
                    break;
                default:
                    log.error(String.format("Unhandled quest request %s!", qt));
                    break;
            }
        }
        if (questID == 0 || qt == null) {
            chr.chatMessage(String.format("Could not find quest %d.", questID));
            return;
        }
        QuestInfo qi = QuestData.getQuestInfoById(questID);
        switch (qt) {
            case QuestReq_AcceptQuest:
                if (qm.canStartQuest(questID)) {
                    qm.addQuest(QuestData.createQuestFromId(questID));
                    success = true;
                }
                break;
            case QuestReq_CompleteQuest:
                if (qm.hasQuestInProgress(questID)) {
                    Quest quest = qm.getQuests().get(questID);
                    if (quest.isComplete(chr)) {
                        qm.completeQuest(questID);
                        success = true;
                    }
                }
                break;
            case QuestReq_OpeningScript:
                String scriptName = qi.getStartScript();
                if (QuestConstants.isSpamQuest(scriptName) || !qm.canStartQuest(questID)) {
                    // ~_~
                    return;
                }
                if (scriptName == null || scriptName.equalsIgnoreCase("")) {
                    scriptName = String.format("%d%s", questID, ScriptManagerImpl.QUEST_START_SCRIPT_END_TAG);
                }
                chr.getScriptManager().startScript(questID, scriptName, ScriptType.Quest);
                break;
            case QuestReq_CompleteScript:
                scriptName = qi.getEndScript();
                if (!qm.hasQuestInProgress(questID) || !qm.getQuestById(questID).isComplete(chr)) {
                    log.info("Could not complete quest, as the prerequisites haven't been met.");
                    return;
                }
                if (scriptName == null || scriptName.equalsIgnoreCase("")) {
                    scriptName = String.format("%d%s", questID, ScriptManagerImpl.QUEST_COMPLETE_SCRIPT_END_TAG);
                }
                chr.getScriptManager().startScript(questID, scriptName, ScriptType.Quest);
                break;
            case QuestReq_LaterStep:
                if (qi != null && qi.getTransferField() != 0) {
                    Field field = chr.getOrCreateFieldByCurrentInstanceType(qi.getTransferField());
                    chr.warp(field);
                }
                break;
        }
        if (success) {
            chr.write(UserLocal.questResult(QuestType.QuestRes_Act_Success, questID, npcTemplateID, 0, false));
        }
    }

    @Handler(op = InHeader.USER_MEDAL_REISSUE_REQUEST)
    public static void handleUserMedalReissueRequest(Char chr, InPacket inPacket) {
        int questId = inPacket.decodeInt();
        int medalItemId = inPacket.decodeInt();
        ScriptManagerImpl sm = chr.getScriptManager();
        long actualMesoCost;
        int count = 0;
        if (sm.getQRValue(QuestConstants.MEDAL_REISSUE_QUEST).contains("count=")) {
            String countString = sm.getQRValue(QuestConstants.MEDAL_REISSUE_QUEST).replace("count=", "");
            count = Integer.parseInt(countString);
        } else {
            sm.createQuestWithQRValue(QuestConstants.MEDAL_REISSUE_QUEST, "");
        }
        switch (count) {
            case 0:
                actualMesoCost = 100;
                break;
            case 1:
                actualMesoCost = 1000;
                break;
            case 2:
                actualMesoCost = 10000;
                break;
            case 3:
                actualMesoCost = 100000;
                break;
            default:
                actualMesoCost = 1000000;
                break;
        }
        if (QuestData.getQuestInfoById(questId).getMedalItemId() != medalItemId || !(ItemConstants.isMedal(medalItemId))) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to reissue an item {%d} that isn't a medal or tried to reissue a medal from a quest {%d} that doesn't give the given medal", chr.getId(), medalItemId, questId));

        } else if (!sm.hasQuestCompleted(questId)) {
            chr.getOffenseManager().addOffense(String.format("Character %d tried to reissue a medal from a quest {%d} which they have not completed.", chr.getId(), questId));

        } else if (ItemData.getItemDeepCopy(medalItemId) == null || QuestData.getQuestInfoById(questId) == null) {
            chr.write(UserLocal.medalReissueResult(MedalReissueResultType.Unknown, medalItemId));

        } else if (chr.getMoney() < actualMesoCost) {
            chr.write(UserLocal.medalReissueResult(MedalReissueResultType.NoMoney, medalItemId));

        } else if (!chr.canHold(medalItemId)) {
            chr.write(UserLocal.medalReissueResult(MedalReissueResultType.NoSlot, medalItemId));

        } else if (chr.hasItem(medalItemId)) {
            chr.write(UserLocal.medalReissueResult(MedalReissueResultType.AlreadyHas, medalItemId));

        } else {
            count++;
            sm.setQRValue(QuestConstants.MEDAL_REISSUE_QUEST, "count=" + count);
            chr.deductMoney(actualMesoCost);
            chr.addItemToInventory(QuestData.getQuestInfoById(questId).getMedalItemId(), 1);
            chr.write(UserLocal.medalReissueResult(MedalReissueResultType.Success, medalItemId));
        }
        chr.dispose();
    }
}
