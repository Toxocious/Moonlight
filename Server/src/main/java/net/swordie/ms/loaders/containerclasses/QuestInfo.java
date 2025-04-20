package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.client.character.quest.progress.QuestProgressRequirement;
import net.swordie.ms.client.character.quest.reward.QuestReward;
import net.swordie.ms.client.character.quest.requirement.QuestStartRequirement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 3/2/2018.
 */
public class QuestInfo {
    private int questID;
    private int startNpc;
    private boolean normalAutoStart;
    private Set<QuestStartRequirement> questStartRequirements = new HashSet<>();
    private Set<QuestProgressRequirement> questProgressRequirements = new HashSet<>();
    private Set<QuestReward> questRewards = new HashSet<>();
    private Set<Integer> fieldEnters = new HashSet<>();
    private int infoNumber;
    private long end;
    private long endT;
    private String startScript;
    private String endScript;
    private long start;
    private long startT;
    private int endNpc;
    private int subJobFlags;
    private boolean completeNpcAutoGuide;
    private int skill;
    private int fieldSetKeepTime;
    private String fieldSet;
    private boolean autoStart;
    private int deathCount;
    private List<Integer> scenarios = new ArrayList<>();
    private List<String> speech = new ArrayList<>();
    private int mobDropMeso;
    private int morph;
    private boolean secret;
    private int transferField;
    private int nextQuest;
    private boolean autoComplete;
    private int medalItemId;

    public Set<QuestReward> getQuestRewards() {
        return questRewards;
    }

    public void setQuestID(int questID) {
        this.questID = questID;
    }

    public int getQuestID() {
        return questID;
    }

    public void setStartNpc(int startNpc) {
        this.startNpc = startNpc;
    }

    public int getStartNpc() {
        return startNpc;
    }

    public void setNormalAutoStart(boolean normalAutoStart) {
        this.normalAutoStart = normalAutoStart;
    }

    public boolean isNormalAutoStart() {
        return normalAutoStart;
    }

    public Set<QuestStartRequirement> getQuestStartRequirements() {
        return questStartRequirements;
    }

    public void addRequirement(QuestStartRequirement qr) {
        getQuestStartRequirements().add(qr);
    }

    public void setInfoNumber(int infoNumber) {
        this.infoNumber = infoNumber;
    }

    public int getInfoNumber() {
        return infoNumber;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getEnd() {
        return end;
    }

    public void setEndT(long endT) {
        this.endT = endT;
    }

    public long getEndT() {
        return endT;
    }

    public void setStartScript(String startScript) {
        this.startScript = startScript;
    }

    public String getStartScript() {
        return startScript == null ? "" : startScript;
    }

    public void setEndScript(String endScript) {
        this.endScript = endScript;
    }

    public String getEndScript() {
        return endScript == null ? "" : endScript;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStart() {
        return start;
    }

    public void setStartT(long startT) {
        this.startT = startT;
    }

    public long getStartT() {
        return startT;
    }

    public void setEndNpc(int endNpc) {
        this.endNpc = endNpc;
    }

    public int getEndNpc() {
        return endNpc;
    }

    public Set<QuestProgressRequirement> getQuestProgressRequirements() {
        return questProgressRequirements;
    }

    public void addProgressRequirement(QuestProgressRequirement qpr) {
        getQuestProgressRequirements().add(qpr);
    }

    public void addReward(QuestReward qir) {
        getQuestRewards().add(qir);
    }

    public void setSubJobFlags(int subJobFlags) {
        this.subJobFlags = subJobFlags;
    }

    public int getSubJobFlags() {
        return subJobFlags;
    }

    public Set<Integer> getFieldEnters() {
        return fieldEnters;
    }

    public void addFieldEnter(int fieldID) {
        getFieldEnters().add(fieldID);
    }

    public void setCompleteNpcAutoGuide(boolean completeNpcAutoGuide) {
        this.completeNpcAutoGuide = completeNpcAutoGuide;
    }

    public boolean isCompleteNpcAutoGuide() {
        return completeNpcAutoGuide;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getSkill() {
        return skill;
    }

    public void setFieldSetKeepTime(int fieldSetKeepTime) {
        this.fieldSetKeepTime = fieldSetKeepTime;
    }

    public int getFieldSetKeepTime() {
        return fieldSetKeepTime;
    }

    public void setFieldSet(String fieldSet) {
        this.fieldSet = fieldSet;
    }

    public String getFieldSet() {
        return fieldSet == null ? "" : fieldSet;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void addSpeech(String script) {
        getSpeech().add(script);
    }

    public List<String> getSpeech() {
        return speech;
    }

    public void setSpeech(List<String> speech) {
        this.speech = speech;
    }

    public void addScenario(int value) {
        getScenarios().add(value);
    }

    public List<Integer> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Integer> scenarios) {
        this.scenarios = scenarios;
    }

    public void setMobDropMeso(int mobDropMeso) {
        this.mobDropMeso = mobDropMeso;
    }

    public int getMobDropMeso() {
        return mobDropMeso;
    }

    public void setMorph(int morph) {
        this.morph = morph;
    }

    public int getMorph() {
        return morph;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setTransferField(int transferField) {
        this.transferField = transferField;
    }

    public int getTransferField() {
        return transferField;
    }

    public void setNextQuest(int nextQuest) {
        this.nextQuest = nextQuest;
    }

    public int getNextQuest() {
        return nextQuest;
    }

    public void setAutoComplete(boolean autoComplete) {
        this.autoComplete = autoComplete;
    }

    public boolean isAutoComplete() {
        return autoComplete;
    }

    public int getMedalItemId() {
        return medalItemId;
    }

    public void setMedalItemId(int medalItem) {
        this.medalItemId = medalItem;
    }
}
