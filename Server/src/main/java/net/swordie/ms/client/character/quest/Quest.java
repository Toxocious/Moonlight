package net.swordie.ms.client.character.quest;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.quest.progress.QuestProgressItemRequirement;
import net.swordie.ms.client.character.quest.progress.QuestProgressMobRequirement;
import net.swordie.ms.client.character.quest.progress.QuestProgressMoneyRequirement;
import net.swordie.ms.client.character.quest.progress.QuestProgressRequirement;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created on 12/20/2017.
 */
@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int QRKey;
    private String qrValue;

    @Column(name = "status")
    private QuestStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "questID")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<QuestProgressRequirement> progressRequirements;

    @Convert(converter = FileTimeConverter.class)
    private FileTime completedTime;

    @Transient
    private Map<String, String> properties = new HashMap<>();

    public Quest() {
        progressRequirements = new ArrayList<>();
    }

    public Quest(int QRKey, QuestStatus status) {
        this();
        this.QRKey = QRKey;
        this.status = status;
    }

    public int getQRKey() {
        return QRKey;
    }

    public void setQRKey(int QRKey) {
        this.QRKey = QRKey;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

    public Quest deepCopy() {
        Quest quest = new Quest();
        quest.setQRKey(getQRKey());
        for(QuestProgressRequirement qpr : getProgressRequirements()) {
            quest.addQuestProgressRequirement(qpr);
        }
        quest.setStatus(getStatus());
        return quest;
    }

    public List<QuestProgressRequirement> getProgressRequirements() {
        return progressRequirements;
    }

    public void addQuestProgressRequirement(QuestProgressRequirement qpr) {
        getProgressRequirements().add(qpr);
    }

    public List<QuestProgressMobRequirement> getMobReqs() {
        return getProgressRequirements().stream()
                .filter(qpr -> qpr instanceof QuestProgressMobRequirement)
                .map(qpr -> (QuestProgressMobRequirement) qpr)
                .collect(Collectors.toList());
    }

    public List<QuestProgressItemRequirement> getItemReqs() {
        return getProgressRequirements().stream()
                .filter(qpr -> qpr instanceof QuestProgressItemRequirement)
                .map(qpr -> (QuestProgressItemRequirement) qpr)
                .collect(Collectors.toList());
    }

    public QuestProgressMobRequirement getMobReqByMobID(int mobID) {
        return getMobReqs().stream().filter(qpmr -> qpmr.getMobID() == mobID).findFirst().orElse(null);
    }

    public boolean hasMobReq(int mobID) {
        return getMobReqByMobID(mobID) != null;
    }

    public FileTime getCompletedTime() {
        return completedTime;
    }

    public void completeQuest() {
        setStatus(QuestStatus.Completed);
        setCompletedTime(FileTime.currentTime());
    }

    public void setCompletedTime(FileTime completedTime) {
        this.completedTime = completedTime;
    }

    public boolean isComplete(Char chr) {
        return getProgressRequirements().stream().allMatch(pr -> pr.isComplete(chr));
    }

    public void handleMobKill(int mobID) {
        Mob mob = MobData.getMobById(mobID);
        if (mob == null) {
            return;
        }
        // get requirement for which this mob (or a containing QuestCountGroup) is a part of
        QuestProgressMobRequirement qpmr = (QuestProgressMobRequirement) getProgressRequirements()
                .stream()
                .filter(q -> q instanceof QuestProgressMobRequirement
                        && ((((QuestProgressMobRequirement) q).getMobID() == mobID)
                            || mob.getParentMobSet().contains(((QuestProgressMobRequirement) q).getMobID())))
                .findFirst().get();
        // should never return null, as this method should only be called when this quest indeed has this mob
        if(qpmr.getCurrentCount() < qpmr.getRequiredCount()) {
            qpmr.incCurrentCount(1);
        }
    }

    @Override
    public String toString() {
        return String.format("%d, %s", getQRKey(), getQRValue());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasMoneyReq() {
        return getProgressRequirements().stream().anyMatch(q -> q instanceof QuestProgressMoneyRequirement);
    }

    public void addMoney(int money) {
        getProgressRequirements().stream()
                .filter(q -> q instanceof QuestProgressMoneyRequirement)
                .map(q -> (QuestProgressMoneyRequirement) q)
                .findAny().ifPresent(qpmr -> qpmr.addMoney(money));
    }

    public String getQRValue() {
        if (qrValue != null && !qrValue.equalsIgnoreCase("")) {
            return qrValue;
        } else {
            StringBuilder sb = new StringBuilder();
            if (getProgressRequirements() == null) {
                return "";
            }
            List<QuestProgressMobRequirement> requirements = new ArrayList<>(getMobReqs());
            requirements.sort(Comparator.comparingInt(QuestProgressMobRequirement::getOrder));
            for(QuestProgressMobRequirement qpmr : requirements) {
                sb.append(Util.leftPaddedString(3, '0', qpmr.getValue()));
            }
            return sb.toString();
        }
    }

    public void setQrValue(String qrValue) {
        this.qrValue = qrValue;
    }

    public void convertQRValueToProperties() {
        String val = getQRValue();
        String[] props = val.split(";");
        for (String prop : props) {
            String[] keyVal = prop.split("=");
            if (keyVal.length == 2) {
                setProperty(keyVal[0], keyVal[1]);
            }
        }
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperty(String key, String value) {
        getProperties().put(key, value);
        setQRValueToProperties();
    }

    public void setProperty(String key, int value) {
        getProperties().put(key, String.valueOf(value));
        setQRValueToProperties();
    }

    private void setQRValueToProperties() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : getProperties().entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
        }
        setQrValue(stringBuilder.toString());
    }

    public String getProperty(String key) {
        convertQRValueToProperties();
        return getProperties().getOrDefault(key, null);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(getProperties().getOrDefault(key, "0"));
    }
}
