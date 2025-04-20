package net.swordie.ms.client.character.quest;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.quest.requirement.QuestStartCompletionRequirement;
import net.swordie.ms.client.character.quest.requirement.QuestStartRequirement;
import net.swordie.ms.client.character.quest.reward.QuestBuffItemReward;
import net.swordie.ms.client.character.quest.reward.QuestItemReward;
import net.swordie.ms.client.character.quest.reward.QuestReward;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.QuestData;
import net.swordie.ms.loaders.containerclasses.QuestInfo;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static net.swordie.ms.enums.ChatType.Mob;
import static net.swordie.ms.enums.QuestStatus.*;

/**
 * Created on 12/20/2017.
 */
@Entity
@Table(name = "questmanagers")
public class QuestManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
    @ElementCollection
@CollectionTable(name="<name_of_join_table>")
@MapKeyColumn(name="<name_of_map_key_in_table>")
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @CollectionTable(name = "questlists")
    @MapKeyColumn(name = "questID")
    private Map<Integer, Quest> questList;
    @Transient
    private Char chr;

    public QuestManager() {

    }

    public QuestManager(Char chr) {
        questList = new HashMap<>();
        this.chr = chr;
    }

    public Collection<Quest> getEx() {
        return getQuests().values();
    }

    public Set<Quest> getCompletedQuests() {
        return getQuests().entrySet().stream().filter(entry -> entry.getValue().getStatus() == Completed).
                map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    public Set<Quest> getQuestsInProgress() {
        return getQuests().entrySet().stream().filter(entry -> entry.getValue().getStatus() == Started).
                map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    public int getSize() {
        return questList.size();
    }

    public Map<Integer, Quest> getQuests() {
        return questList;
    }

    public boolean hasQuestInProgress(int questID) {
        Quest quest = getQuests().get(questID);
        return quest != null && quest.getStatus() == Started;
    }

    /**
     * Checks if a quest has been completed, i.e. the status is COMPLETE.
     *
     * @param questID the quest's id to check
     * @return quest completeness
     */
    public boolean hasQuestCompleted(int questID) {
        Quest quest = getQuests().get(questID);
        return quest != null && quest.getStatus() == Completed;
    }

    /**
     * Checks if a quest is complete. This means that a quest's status is STARTED, and that all the requirements to
     * complete it have been met.
     *
     * @param questID the quest's id to check
     * @return completeness
     */
    public boolean isComplete(int questID) {
        Quest quest = getQuests().get(questID);
        return hasQuestInProgress(questID) && quest.isComplete(chr);
    }

    public void addQuest(Quest quest) {
        addQuest(quest, true);
    }

    public void addCustomQuest(Quest quest) {
        addQuest(quest, false);
    }

    /**
     * Adds a new {@link Quest} to this QuestManager's quests. If it already exists, doesn't do anything.
     * Use {@link #replaceQuest(Quest)} if a given quest should be overridden.
     *
     * @param quest            The Quest to add.
     * @param addRewardsFromWz Whether or not to addRewards from the WzFiles
     */
    private void addQuest(Quest quest, boolean addRewardsFromWz) {
        if (!getQuests().containsKey(quest.getQRKey())) {
            getQuests().put(quest.getQRKey(), quest);
            if (quest.getProperties() == null || quest.getProperties().size() == 0) {
                chr.write(WvsContext.questRecordMessage(quest));
            } else {
                chr.write(WvsContext.questRecordExMessage(quest));
            }
            if (quest.getStatus() == QuestStatus.Completed) {
                chr.chatMessage(Mob, "[Info] Completed Quest " + quest.getQRKey());
//                chr.getAvatarData().getCharacterStat().gainPierce(GameConstants.QUEST_PIERCE);
            } else {
                chr.chatMessage(Mob, "[Info] Accepted Quest " + quest.getQRKey());
                if (addRewardsFromWz) {
                    QuestInfo qi = QuestData.getQuestInfoById(quest.getQRKey());
                    if (qi != null) {
                        for (QuestReward qr : qi.getQuestRewards()) {
                            if ((qr instanceof QuestItemReward && ((QuestItemReward) qr).getStatus() == 0) || (qr instanceof QuestBuffItemReward && ((QuestBuffItemReward) qr).getStatus() == 0)) {
                                qr.giveReward(getChr());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Adds a new {@link Quest} to this QuestManager's quest. If it already exists, overrides the old one with the new one.
     *
     * @param quest The Quest to add/replace.
     */
    public void replaceQuest(Quest quest) {
        getQuests().put(quest.getQRKey(), quest);
        chr.write(WvsContext.questRecordMessage(quest));
    }

    /**
     * Returns whether or not a {@link Char} can start a given quest.
     *
     * @param questID The Quest's ID to check.
     * @return Whether or not the Char can start the quest.
     */
    public boolean canStartQuest(int questID) {
        QuestInfo qi = QuestData.getQuestInfoById(questID);
        if (qi == null) {
            return true;
        }
        Set<QuestStartRequirement> questReqs = qi.getQuestStartRequirements().stream()
                .filter(qsr -> qsr instanceof QuestStartCompletionRequirement)
                .collect(Collectors.toSet());
        boolean hasQuest = questReqs.size() == 0 ||
                questReqs.stream().anyMatch(q -> q.hasRequirements(chr));
        return hasQuest && qi.getQuestStartRequirements().stream()
                .filter(qsr -> !(qsr instanceof QuestStartCompletionRequirement))
                .allMatch(qsr -> qsr.hasRequirements(chr));
    }

    public Char getChr() {
        return chr;
    }

    /**
     * Completes a quest. Assumes the check for in-progressness has already been done, so this method can be used
     * to complete quests that the Char does not actually have.
     *
     * @param questID The quest ID to finish.
     */
    public void completeQuest(int questID) {
        QuestInfo questInfo = QuestData.getQuestInfoById(questID);
        Quest quest = getQuests().get(questID);
        if (quest == null) {
            quest = QuestData.createQuestFromId(questID);
            if (quest == null) {
                return;
            }
            addQuest(quest);
        }
        quest.setStatus(QuestStatus.Completed);
        quest.setCompletedTime(FileTime.currentTime());
        chr.chatMessage(Mob, "[Info] Completed Quest " + quest.getQRKey());
//        chr.getAvatarData().getCharacterStat().gainPierce(GameConstants.QUEST_PIERCE);
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.questCompleteEffect()));
        chr.write(UserPacket.effect(Effect.questCompleteEffect()));
        chr.write(WvsContext.questRecordMessage(quest));
        if (questInfo != null) {
            for (QuestReward qr : questInfo.getQuestRewards()) {
                if (!(qr instanceof QuestItemReward) || ((QuestItemReward) qr).getStatus() != 0) {
                    qr.giveReward(chr);
                }
            }
        }
    }

    public void handleMobKill(Mob mob) {
        for (int questID : mob.getQuests()) {
            Quest q = getQuests().get(questID);
            if (q != null && !q.isComplete(chr)) {
                q.handleMobKill(mob.getTemplateId());
                chr.write(WvsContext.questRecordMessage(q));
            }
        }
    }

    public void handleMoneyGain(int money) {
        for (Quest q : getQuestsInProgress()) {
            if (q.hasMoneyReq()) {
                q.addMoney(money);
                chr.write(WvsContext.questRecordMessage(q));
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    /**
     * Removes a given quest from this QuestManager, and notifies the client of this change. Does nothing if the Char
     * does not currently have the quest.
     *
     * @param questID the id of the quest that should be removed
     */
    public void removeQuest(int questID) {
        Quest q = getQuests().get(questID);
        if (q != null) {
            q.setStatus(NotStarted);
            getQuests().remove(questID);
            chr.write(WvsContext.questRecordMessage(q));
        }
    }

    /**
     * Adds a quest to this QuestManager with a given id. If there is no quest with that id, does nothing.
     *
     * @param id the quest's id to add
     */
    public void addQuest(int id) {
        Quest q = QuestData.createQuestFromId(id);
        if (q != null) {
            addQuest(q);
        }
    }

    public Quest getQuestById(int questId) {
        return getQuests().getOrDefault(questId, null);
    }

    public Quest getOrCreateQuestById(int questId) {
        Quest quest = getQuestById(questId);
        if (quest == null) {
            addQuest(questId);
        }
        quest = getQuestById(questId);
        return quest;
    }
}
