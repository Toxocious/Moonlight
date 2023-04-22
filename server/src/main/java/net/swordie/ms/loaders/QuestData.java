package net.swordie.ms.loaders;

import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.progress.*;
import net.swordie.ms.client.character.quest.requirement.*;
import net.swordie.ms.client.character.quest.reward.*;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.QuestInfo;
import org.apache.log4j.LogManager;
import org.w3c.dom.Node;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created on 3/2/2018.
 */
public class QuestData {
    private static Set<QuestInfo> baseQuests = new HashSet<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private static final boolean LOG_UNKS = false;

    public static void loadQuestsFromWZ() {
        String wzDir = String.format("%s/Quest.wz/", ServerConstants.WZ_DIR);
        String checkDir = wzDir + "Check.img.xml";
        File file = new File(checkDir);
        Node root = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(root).get(0);
        for (Node questIDNode : XMLApi.getAllChildren(mainNode)) {
            QuestInfo quest = new QuestInfo();
            int questID = Integer.parseInt(XMLApi.getNamedAttribute(questIDNode, "name"));
            quest.setQuestID(questID);
            for (Node statusNode : XMLApi.getAllChildren(questIDNode)) {
                byte status = Byte.parseByte(XMLApi.getNamedAttribute(statusNode, "name"));
                for (Node infoNode : XMLApi.getAllChildren(statusNode)) {
                    String name = XMLApi.getNamedAttribute(infoNode, "name");
                    String value = XMLApi.getNamedAttribute(infoNode, "value");
                    switch (name) {
                        case "npc":
                            if (status == 0) {
                                quest.setStartNpc(Integer.parseInt(value));
                            } else {
                                quest.setEndNpc(Integer.parseInt(value));
                            }
                            break;
                        case "infoNumber":
                            quest.setInfoNumber(Integer.parseInt(value));
                            break;
                        case "fieldsetkeeptime":
                            quest.setFieldSetKeepTime(Integer.parseInt(value));
                            break;
                        case "subJobFlags":
                            quest.setSubJobFlags(Integer.parseInt(value));
                            break;
                        case "deathCount":
                            quest.setDeathCount(Integer.parseInt(value));
                            break;
                        case "mobDropMeso":
                            quest.setMobDropMeso(Integer.parseInt(value));
                            break;
                        case "morph":
                            quest.setMorph(Integer.parseInt(value));
                            break;
                        case "start":
                            quest.setStart(Long.parseLong(value));
                            break;
                        case "start_t":
                            quest.setStartT(Long.parseLong(value));
                            break;
                        case "end":
                            quest.setEnd(Long.parseLong(value));
                            break;
                        case "end_t":
                            quest.setEndT(Long.parseLong(value));
                            break;
                        case "startscript":
                            quest.setStartScript(value);
                            break;
                        case "endscript":
                            quest.setEndScript(value);
                            break;
                        case "fieldset":
                            quest.setFieldSet(value);
                            break;
                        case "normalAutoStart":
                            quest.setNormalAutoStart(Integer.parseInt(value) != 0);
                            break;
                        case "completeNpcAutoGuide":
                            quest.setCompleteNpcAutoGuide(Integer.parseInt(value) != 0);
                            break;
                        case "autoStart":
                            quest.setAutoStart(Integer.parseInt(value) != 0);
                            break;
                        case "scenarioQuest":
                            quest.setAutoStart(Integer.parseInt(value) != 0);
                            break;
                        case "secret":
                            quest.setSecret(Integer.parseInt(value) != 0);
                            break;
                        case "marriaged":
                            quest.addRequirement(new QuestStartMarriageRequirement());
                            break;
                        case "lvmin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.level, Short.parseShort(value)));
                            break;
                        case "pop":
                        case "fameGradeReq":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.pop, Short.parseShort(value)));
                            break;
                        case "charismaMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.charismaEXP, Short.parseShort(value)));
                            break;
                        case "insightMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.insightEXP, Short.parseShort(value)));
                            break;
                        case "willMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.willEXP, Short.parseShort(value)));
                            break;
                        case "craftMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.craftEXP, Short.parseShort(value)));
                            break;
                        case "senseMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.senseEXP, Short.parseShort(value)));
                            break;
                        case "charm":
                        case "charmMin":
                            quest.addRequirement(new QuestStartMinStatRequirement(Stat.charmEXP, Short.parseShort(value)));
                            break;
                        case "level":
                            quest.addProgressRequirement(new QuestProgressLevelRequirement(Short.parseShort(value)));
                            break;
                        case "lvmax":
                            quest.addRequirement(new QuestStartMaxLevelRequirement(Short.parseShort(value)));
                            break;
                        case "endmeso":
                            quest.addProgressRequirement(new QuestProgressMoneyRequirement(Integer.parseInt(value)));
                            break;
                        case "order":
                        case "notInTeleportItemLimitedField":
                        case "anotherUserORCheck":
                        case "damageOnFalling":
                        case "hpR":
                        case "dayByDay":
                        case "QuestRecordAndOption":
                        case "infoex":
                        case "equipAllNeed":
                        case "interval":
                        case "interval_t":
                        case "dayOfWeek":
                        case "QuestOrOption":
                        case "ItemOrOption":
                        case "dayN":
                        case "anotherUserCheckType":
                        case "anotherUserCheck":
                        case "userInteract":
                        case "petRecallLimit":
                        case "pettamenessmin":
                        case "dayN_t":
                        case "worldmin":
                        case "worldmax":
                        case "petAutoSpeakingLimit":
                        case "name":
                        case "multiKill":
                        case "comboKill":
                        case "job_JP":
                        case "job_TW":
                        case "dayByDay_t":
                        case "runeAct":
                        case "weeklyRepeatResetDayOfWeek":
                        case "weeklyRepeat":
                        case "dressChanged":
                        case "equipSelectNeed":
                        case "infoAccount":
                        case "infoAccountExt":
                        case "breakTimeField":
                        case "multiKillCount":
                        case "randomGroupList":
                        case "randomGroup":
                        case "mbmin":
                        case "duo":
                        case "duoAssistPoint":
                        case "wsrInfo":
                        case "premium":
                        case "dayOfWeek_t":
                        case "nxInfo":
                        case "episodeQuest":
                        case "pvpGrade":
                        case "vipStartGradeMin":
                        case "vipStartGradeMax":
                        case "vipStartAccount":
                        case "dailyCommitment":
                        case "purchasePeriodAbove":
                        case "charisma": // Maybe implement later
                        case "craft": // Maybe implement later
                        case "gender": // it's 2018, so equal opportunity
                        case "buff": // Maybe implement later
                        case "exceptbuff": // Maybe implement later
                            break;
                        case "quest":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                QuestStartCompletionRequirement qcr = new QuestStartCompletionRequirement();
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    if (XMLApi.getFirstChildByNameBF(questNode, "state") == null) {
                                        qcr.setQuestStatus((byte) -1);
                                    }
                                    switch (questName) {
                                        case "id":
                                            qcr.setQuestID(Integer.parseInt(questValue));
                                            break;
                                        case "order":
                                            break;
                                        case "state":
                                            qcr.setQuestStatus(Byte.parseByte(questValue));
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk quest name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                                if (qcr.getQuestStatus() != -1) {
                                    quest.addRequirement(qcr);
                                }
                            }
                            break;
                        case "pet":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                QuestStartItemRequirement qisr = new QuestStartItemRequirement();
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    switch (questName) {
                                        case "id":
                                            qisr.setId(Integer.parseInt(questValue));
                                            break;
                                        case "order":
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk pet name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                                quest.addRequirement(qisr);
                            }
                            break;
                        case "job":
                        case "job ":
                            QuestStartJobRequirement qjr = new QuestStartJobRequirement();
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                qjr.addJobReq(Short.parseShort(XMLApi.getNamedAttribute(idNode, "value")));
                            }
                            quest.addRequirement(qjr);
                            break;
                        case "scenarioQuestList":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                quest.addScenario(Integer.parseInt(XMLApi.getNamedAttribute(idNode, "value")));
                            }
                            break;
                        case "fieldEnter":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                quest.addFieldEnter(Integer.parseInt(XMLApi.getNamedAttribute(idNode, "value")));
                            }
                            break;
                        case "mob":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                QuestProgressMobRequirement qpmr = new QuestProgressMobRequirement();
                                // items are always after mobs
                                qpmr.setOrder(Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name")));
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    switch (questName) {
                                        case "id":
                                            qpmr.setMobID(Integer.parseInt(questValue));
                                            break;
                                        case "count":
                                            qpmr.setRequiredCount(Integer.parseInt(questValue));
                                            break;
                                        case "order":
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk mob name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                                quest.addProgressRequirement(qpmr);
                            }
                            break;
                        case "item":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                QuestStartItemRequirement qir = new QuestStartItemRequirement();
                                QuestProgressItemRequirement qpir = new QuestProgressItemRequirement();
                                qpir.setOrder(Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name")));
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    switch (questName) {
                                        case "id":
                                            if (status == 0) {
                                                qir.setId(Integer.parseInt(questValue));
                                            } else {
                                                qpir.setItemID(Integer.parseInt(questValue));
                                            }
                                            break;
                                        case "count":
                                            if (status == 0) {
                                                qir.setQuantity(Integer.parseInt(questValue));
                                            } else {
                                                qpir.setRequiredCount(Integer.parseInt(questValue));
                                            }
                                            break;
                                        case "order":
                                            break;
                                        case "secret":
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk item name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                                if (status == 0) {
                                    quest.addRequirement(qir);
                                } else {
                                    quest.addProgressRequirement(qpir);
                                }
                            }
                            break;
                        case "skill":
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    switch (questName) {
                                        case "id":
                                            quest.setSkill(Integer.parseInt(questValue));
                                            break;
                                        case "order":
                                        case "acquire":
                                        case "level":
                                        case "levelCondition":
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk skill name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                            }
                            break;
                        case "npcSpeech":
                            String speechValue = "NpcSpeech=";
                            for (Node idNode : XMLApi.getAllChildren(infoNode)) {
                                boolean hasSpeech = false;
                                int templateID = 0, order = 0;
                                for (Node questNode : XMLApi.getAllChildren(idNode)) {
                                    String questName = XMLApi.getNamedAttribute(questNode, "name");
                                    String questValue = XMLApi.getNamedAttribute(questNode, "value");
                                    switch (questName) {
                                        case "script":
                                            quest.addSpeech(questValue);
                                            break;
                                        case "speech":
                                            hasSpeech = true;
                                            break;
                                        case "id":
                                            templateID = Integer.parseInt(questValue);
                                            break;
                                        case "order":
                                            order = Integer.parseInt(questValue);
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk npc speech name %s with value %s", questID, questName, questValue));
                                            }
                                            break;
                                    }
                                }
                                if (hasSpeech && templateID != 0) {
                                    speechValue += templateID + "" + order + "/";
                                    quest.addSpeech(speechValue);
                                }
                            }
                            break;
                        default:
                            if (LOG_UNKS) {
                                log.warn(String.format("(%d) Unk name %s with value %s", questID, name, value));
                            }
                            break;
                    }
                }
            }
            getBaseQuests().add(quest);
        }

        String actDir = wzDir + "Act.img.xml";
        file = new File(actDir);
        root = XMLApi.getRoot(file);
        mainNode = XMLApi.getAllChildren(root).get(0);
        for (Node questIDNode : XMLApi.getAllChildren(mainNode)) {
            int id = Integer.parseInt(XMLApi.getNamedAttribute(questIDNode, "name"));
            QuestInfo quest = getQuestInfo(id);
            for (Node statusNode : XMLApi.getAllChildren(questIDNode)) {
                int status = Integer.parseInt(XMLApi.getNamedAttribute(statusNode, "name"));
                for (Node rewardNode : XMLApi.getAllChildren(statusNode)) {
                    String name = XMLApi.getNamedAttribute(rewardNode, "name");
                    String value = XMLApi.getNamedAttribute(rewardNode, "value");
                    switch (name) {
                        case "transferField":
                            quest.setTransferField(Integer.valueOf(value));
                            break;
                        case "nextQuest":
                            quest.setNextQuest(Integer.parseInt(value));
                            break;
                        case "exp":
                            quest.addReward(new QuestExpReward(Long.parseLong(value)));
                            break;
                        case "money":
                            quest.addReward(new QuestMoneyReward(Long.parseLong(value)));
                            break;
                        case "pop":
                            quest.addReward(new QuestPopReward(Integer.parseInt(value)));
                            break;
                        case "buffItemID":
                            quest.addReward(new QuestBuffItemReward(Integer.parseInt(value), status));
                            break;
                        case "item":
                            for (Node itemNode : XMLApi.getAllChildren(rewardNode)) {
                                QuestItemReward qir = new QuestItemReward();
                                qir.setStatus(status);
                                for (Node itemInfoNode : XMLApi.getAllChildren(itemNode)) {
                                    String itemName = XMLApi.getNamedAttribute(itemInfoNode, "name");
                                    String itemValue = XMLApi.getNamedAttribute(itemInfoNode, "value");
                                    switch (itemName) {
                                        case "id":
                                            qir.setId(Integer.parseInt(itemValue));
                                            break;
                                        case "prop":
                                            qir.setProp(Integer.parseInt(itemValue));
                                            break;
                                        case "count":
                                            qir.setQuantity(Short.parseShort(itemValue));
                                            break;
                                        case "potentialGrade":
                                            qir.setPotentialGrade(itemValue);
                                            break;
                                        case "gender":
                                            qir.setGender(Integer.parseInt(itemValue));
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn(String.format("(%d) Unk item name %s with value %s status %d", id, itemName, itemValue, status));
                                            }
                                            break;
                                    }
                                }
                                quest.addReward(qir);
                            }
                            break;
                        default:
                            if (LOG_UNKS) {
                                log.warn(String.format("(%d) Unk name %s with value %s status %d", id, name, value, status));
                            }
                            break;
                    }
                }
            }
        }
        String qiDir = wzDir + "QuestInfo.img.xml";
        file = new File(qiDir);
        root = XMLApi.getRoot(file);
        mainNode = XMLApi.getAllChildren(root).get(0);
        for (Node questIDNode : XMLApi.getAllChildren(mainNode)) {
            int id = Integer.parseInt(XMLApi.getNamedAttribute(questIDNode, "name"));
            QuestInfo quest = getQuestInfo(id);
            for (Node questInfoNode : XMLApi.getAllChildren(questIDNode)) {
                String name = XMLApi.getNamedAttribute(questInfoNode, "name");
                String value = XMLApi.getNamedAttribute(questInfoNode, "value");
                switch (name) {
                    case "autoComplete":
                        quest.setAutoComplete(Integer.parseInt(value) == 1);
                        break;
                    case "viewMedalItem":
                        quest.setMedalItemId(Integer.parseInt(value));
                        break;
                }
            }
        }
    }

    public static Set<QuestInfo> getBaseQuests() {
        return baseQuests;
    }

    public static void linkMobData() {
        if (getBaseQuests().size() == 0) {
            loadQuestsFromWZ();
        }
        for (QuestInfo qi : getBaseQuests()) {
            for (QuestProgressMobRequirement qpmr :
                    qi.getQuestProgressRequirements()
                            .stream()
                            .filter(q -> q instanceof QuestProgressMobRequirement)
                            .map(q -> (QuestProgressMobRequirement) q)
                            .collect(Collectors.toSet())) { // readability is overrated
                Mob m = MobData.getMobById(qpmr.getMobID());
                if (m != null) {
                    m.addQuest(qi.getQuestID());
                    for (int childId : m.getMobSet()) {
                        Mob childMob = MobData.getMobById(childId);
                        if (childMob != null) {
                            childMob.addQuest(qi.getQuestID());
                        }
                    }
                }
            }
        }
    }

    public static void linkItemData() {
        if (getBaseQuests().size() == 0) {
            loadQuestsFromWZ();
        }
        for (QuestInfo qi : getBaseQuests()) {
            for (QuestProgressItemRequirement qpmr :
                    qi.getQuestProgressRequirements()
                            .stream()
                            .filter(q -> q instanceof QuestProgressItemRequirement)
                            .map(q -> (QuestProgressItemRequirement) q)
                            .collect(Collectors.toSet())) { // readability is overrated
                int itemID = qpmr.getItemID();
                if (ItemConstants.isEquip(itemID)) {
                    // create new ItemInfos just for equips that are required for quests
                    // normally ItemInfo is just for non-equips.
                    ItemInfo ii = new ItemInfo();
                    ii.setItemId(itemID);
                    ii.setInvType(InvType.EQUIP);
                    ii.addQuest(qi.getQuestID());
                    ItemData.addItemInfo(ii);
                } else {
                    ItemInfo item = ItemData.getItemInfoByID(qpmr.getItemID());
                    if (item != null) {
                        item.addQuest(qi.getQuestID());
                    }
                }
            }
        }
    }

    public static void generateDatFiles() {
        log.info("Started generating quest data.");
        long start = System.currentTimeMillis();
        if (getBaseQuests().size() == 0) {
            loadQuestsFromWZ();
        }
        saveAllQuestInfos(String.format("%s/quests", ServerConstants.DAT_DIR));
        log.info(String.format("Completed generating quest data in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveAllQuestInfos(String dir) {
        Util.makeDirIfAbsent(dir);
        for (QuestInfo qi : getBaseQuests()) {
            File file = new File(String.format("%s/%d.dat", dir, qi.getQuestID()));
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeInt(qi.getQuestID());
                dos.writeInt(qi.getStartNpc());
                dos.writeBoolean(qi.isNormalAutoStart());
                dos.writeShort(qi.getQuestStartRequirements().size());
                for (QuestStartRequirement qsr : qi.getQuestStartRequirements()) {
                    dos.writeByte(QuestStartRequirementType.getQPRTByObj(qsr).getVal());
                    qsr.write(dos);
                }
                dos.writeShort(qi.getQuestProgressRequirements().size());
                for (QuestProgressRequirement qpr : qi.getQuestProgressRequirements()) {
                    dos.writeByte(QuestProgressRequirementType.getQPRTByObj(qpr).getVal());
                    qpr.write(dos);
                }
                dos.writeShort(qi.getQuestRewards().size());
                for (QuestReward qr : qi.getQuestRewards()) {
                    dos.writeByte(QuestRewardType.getQPRTByObj(qr).getVal());
                    qr.write(dos);
                }
                dos.writeShort(qi.getFieldEnters().size());
                for (int i : qi.getFieldEnters()) {
                    dos.writeInt(i);
                }
                dos.writeInt(qi.getInfoNumber());
                dos.writeLong(qi.getEnd());
                dos.writeLong(qi.getEndT());
                dos.writeUTF(qi.getStartScript());
                dos.writeUTF(qi.getEndScript());
                dos.writeLong(qi.getStart());
                dos.writeLong(qi.getStartT());
                dos.writeInt(qi.getEndNpc());
                dos.writeInt(qi.getSubJobFlags());
                dos.writeBoolean(qi.isCompleteNpcAutoGuide());
                dos.writeInt(qi.getSkill());
                dos.writeInt(qi.getFieldSetKeepTime());
                dos.writeUTF(qi.getFieldSet());
                dos.writeBoolean(qi.isAutoStart());
                dos.writeInt(qi.getDeathCount());
                dos.writeShort(qi.getScenarios().size());
                for (int i : qi.getScenarios()) {
                    dos.writeInt(i);
                }
                dos.writeShort(qi.getSpeech().size());
                for (String i : qi.getSpeech()) {
                    dos.writeUTF(i);
                }
                dos.writeInt(qi.getMobDropMeso());
                dos.writeInt(qi.getMorph());
                dos.writeBoolean(qi.isSecret());
                dos.writeInt(qi.getTransferField());
                dos.writeInt(qi.getNextQuest());
                dos.writeBoolean(qi.isAutoComplete());
                dos.writeInt(qi.getMedalItemId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static QuestInfo getQuestInfo(int id) {
        return getBaseQuests().stream().filter(qi -> qi.getQuestID() == id).findFirst().orElse(null);
    }

    public static QuestInfo getQuestInfoById(int id) {
        QuestInfo qi = getQuestInfo(id);
        return qi == null ? loadQuestInfoById(id) : qi;
    }

    private static QuestInfo loadQuestInfoById(int id) {
        File file = new File(String.format("%s/quests/%d.dat", ServerConstants.DAT_DIR, id));
        boolean exists = file.exists();
        return exists ? loadQuestInfoByFile(file) : null;
    }

    private static QuestInfo loadQuestInfoByFile(File file) {
        QuestInfo qi = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            qi = new QuestInfo();
            qi.setQuestID(dis.readInt());
            qi.setStartNpc(dis.readInt());
            qi.setNormalAutoStart(dis.readBoolean());
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
                QuestStartRequirementType qsrt = QuestStartRequirementType.getQPRTByVal(dis.readByte());
                qi.addRequirement(qsrt.load(dis));
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                QuestProgressRequirementType qprt = QuestProgressRequirementType.getQPRTByVal(dis.readByte());
                qi.addProgressRequirement(qprt.load(dis));
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                QuestRewardType qr = QuestRewardType.getQPRTByVal(dis.readByte());
                qi.addReward(qr.load(dis));
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                qi.addFieldEnter(dis.readInt());
            }
            qi.setInfoNumber(dis.readInt());
            qi.setEnd(dis.readLong());
            qi.setEndT(dis.readLong());
            qi.setStartScript(dis.readUTF());
            qi.setEndScript(dis.readUTF());
            qi.setStart(dis.readLong());
            qi.setStartT(dis.readLong());
            qi.setEndNpc(dis.readInt());
            qi.setSubJobFlags(dis.readInt());
            qi.setCompleteNpcAutoGuide(dis.readBoolean());
            qi.setSkill(dis.readInt());
            qi.setFieldSetKeepTime(dis.readInt());
            qi.setFieldSet(dis.readUTF());
            qi.setAutoStart(dis.readBoolean());
            qi.setDeathCount(dis.readInt());
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                qi.addScenario(dis.readInt());
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                qi.addSpeech(dis.readUTF());
            }
            qi.setMobDropMeso(dis.readInt());
            qi.setMorph(dis.readInt());
            qi.setSecret(dis.readBoolean());
            qi.setTransferField(dis.readInt());
            qi.setNextQuest(dis.readInt());
            qi.setAutoComplete(dis.readBoolean());
            qi.setMedalItemId(dis.readInt());
            getBaseQuests().add(qi);
        } catch (IOException e) {
            log.error(String.format("IOException when loading %d", qi.getQuestID()));
            e.printStackTrace();
        }
        return qi;
    }

    public static Quest createQuestFromId(int questID) {
        QuestInfo qi = getQuestInfoById(questID);
        Quest quest = new Quest();
        quest.setQRKey(questID);
        if (qi != null) {
            if (qi.isAutoComplete()) {
                quest.setStatus(QuestStatus.Started);
//            quest.completeQuest(); // TODO check what autocomplete actually means
            } else {
                quest.setStatus(QuestStatus.Started);
            }
            for (QuestProgressRequirement qpr : qi.getQuestProgressRequirements()) {
                quest.addQuestProgressRequirement(qpr.deepCopy());
            }
        } else {
            quest.setStatus(QuestStatus.Started);
        }
        return quest;
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getBaseQuests().clear();
    }
}
