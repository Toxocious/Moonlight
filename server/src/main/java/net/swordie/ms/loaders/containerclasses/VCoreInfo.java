package net.swordie.ms.loaders.containerclasses;

/**
 * @author Sjonnie
 * Created on 10/1/2018.
 */
public class VCoreInfo {
    private int skillID;
    private int iconID;
    private int maxLevel;
    private int cooltime;
    private int count;
    private String type = "";
    private int validTime;
    private String effectType = "";
    private int slv;
    private double prop;

    public VCoreInfo() {
    }

    public VCoreInfo(int skillID, int iconID, int masterLevel) {
        this.skillID = skillID;
        this.iconID = iconID;
        this.maxLevel = masterLevel;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setCooltime(int cooltime) {
        this.cooltime = cooltime;
    }

    public int getCooltime() {
        return cooltime;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public String getEffectType() {
        return effectType;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public int getSlv() {
        return slv;
    }

    public void setProp(double prop) {
        this.prop = prop;
    }

    public double getProp() {
        return prop;
    }

    public boolean isSoloNode() {
        return !isEnforce();
    }

    public boolean isSkill() {
        return getIconID() >= 10000000 && getIconID() < 20000000;
    }

    public boolean isEnforce() {
        return getIconID() >= 20000000 && getIconID() < 30000000;
    }

    public boolean isSpecial() {
        return getIconID() >= 30000000 && getIconID() < 40000000;
    }
}
