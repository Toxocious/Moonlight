package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.util.container.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MechAviv on 23/11/2018.
 */
public class MakingSkillRecipe {
    private int recipeID;
    private List<TargetElem> target = new ArrayList<>();// rewards
    private int weatherItemID;
    private int incSkillProficiency;//
    private int incSkillProficiencyOnFailure;
    private int incFatigability;
    private int incSkillMasterProficiency;
    private int incSkillMasterProficiencyOnFailure;
    private boolean needOpenItem;
    private int reqSkillID;
    private int recommandedSkillLevel;
    private int reqSkillProficiency;
    private int reqMeso;
    private String reqMapObjectTag = "";
    private List<Tuple<Integer, Integer>> ingredient = new ArrayList<>();// itemid, count
    private int addedCoolProb;
    private int coolTimeSec;
    private int addedSecForMaxGauge;
    private int expiredPeriod;
    private boolean premiumItem;

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public List<TargetElem> getTarget() {
        return target;
    }

    public void setTarget(List<TargetElem> target) {
        this.target = target;
    }

    public void addTarget(TargetElem target) {
        this.target.add(target);
    }

    public int getWeatherItemID() {
        return weatherItemID;
    }

    public void setWeatherItemID(int weatherItemID) {
        this.weatherItemID = weatherItemID;
    }

    public int getIncSkillProficiency() {
        return incSkillProficiency;
    }

    public void setIncSkillProficiency(int incSkillProficiency) {
        this.incSkillProficiency = incSkillProficiency;
    }

    public int getIncSkillProficiencyOnFailure() {
        return incSkillProficiencyOnFailure;
    }

    public void setIncSkillProficiencyOnFailure(int incSkillProficiencyOnFailure) {
        this.incSkillProficiencyOnFailure = incSkillProficiencyOnFailure;
    }

    public int getIncFatigability() {
        return incFatigability;
    }

    public void setIncFatigability(int incFatigability) {
        this.incFatigability = incFatigability;
    }

    public int getIncSkillMasterProficiency() {
        return incSkillMasterProficiency;
    }

    public void setIncSkillMasterProficiency(int incSkillMasterProficiency) {
        this.incSkillMasterProficiency = incSkillMasterProficiency;
    }

    public int getIncSkillMasterProficiencyOnFailure() {
        return incSkillMasterProficiencyOnFailure;
    }

    public void setIncSkillMasterProficiencyOnFailure(int incSkillMasterProficiencyOnFailure) {
        this.incSkillMasterProficiencyOnFailure = incSkillMasterProficiencyOnFailure;
    }

    public boolean isNeedOpenItem() {
        return needOpenItem;
    }

    public void setNeedOpenItem(boolean needOpenItem) {
        this.needOpenItem = needOpenItem;
    }

    public int getReqSkillID() {
        return reqSkillID;
    }

    public void setReqSkillID(int reqSkillID) {
        this.reqSkillID = reqSkillID;
    }

    public int getRecommandedSkillLevel() {
        return recommandedSkillLevel;
    }

    public void setRecommandedSkillLevel(int recommandedSkillLevel) {
        this.recommandedSkillLevel = recommandedSkillLevel;
    }

    public int getReqSkillProficiency() {
        return reqSkillProficiency;
    }

    public void setReqSkillProficiency(int reqSkillProficiency) {
        this.reqSkillProficiency = reqSkillProficiency;
    }

    public int getReqMeso() {
        return reqMeso;
    }

    public void setReqMeso(int reqMeso) {
        this.reqMeso = reqMeso;
    }

    public String getReqMapObjectTag() {
        return reqMapObjectTag;
    }

    public void setReqMapObjectTag(String reqMapObjectTag) {
        this.reqMapObjectTag = reqMapObjectTag;
    }

    public List<Tuple<Integer, Integer>> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Tuple<Integer, Integer>> ingredient) {
        this.ingredient = ingredient;
    }

    public void addIngredient(int itemID, int count) {
        this.ingredient.add(new Tuple<>(itemID, count));
    }

    public int getAddedCoolProb() {
        return addedCoolProb;
    }

    public void setAddedCoolProb(int addedCoolProb) {
        this.addedCoolProb = addedCoolProb;
    }

    public int getCoolTimeSec() {
        return coolTimeSec;
    }

    public void setCoolTimeSec(int coolTimeSec) {
        this.coolTimeSec = coolTimeSec;
    }

    public int getAddedSecForMaxGauge() {
        return addedSecForMaxGauge;
    }

    public void setAddedSecForMaxGauge(int addedSecForMaxGauge) {
        this.addedSecForMaxGauge = addedSecForMaxGauge;
    }

    public int getExpiredPeriod() {
        return expiredPeriod;
    }

    public void setExpiredPeriod(int expiredPeriod) {
        this.expiredPeriod = expiredPeriod;
    }

    public boolean isPremiumItem() {
        return premiumItem;
    }

    public void setPremiumItem(boolean premiumItem) {
        this.premiumItem = premiumItem;
    }

    public boolean isAbleToBeUsedBy(Char chr) {
        int makingSkillLevel = chr.getMakingSkillLevel(getReqSkillID());
        int makingSkillProficiency = chr.getMakingSkillProficiency(getReqSkillID());

        if (makingSkillLevel <= 0 || makingSkillProficiency < getReqSkillProficiency()) {
            return false;
        }
        if (isNeedOpenItem() && chr.getRemainRecipeUseCount(getRecipeID()) <= 0) {
            chr.write(UserLocal.noticeMsg("This recipe cannot be used.", true));
            return false;
        }
        if (getRecommandedSkillLevel() == SkillConstants.MAKING_SKILL_MASTER_LEVEL && makingSkillLevel < SkillConstants.MAKING_SKILL_MASTER_LEVEL) {
            chr.write(UserLocal.noticeMsg("You must be a Master Craftsman to craft this.", true));
            return false;
        }
        if (getRecommandedSkillLevel() == SkillConstants.MAKING_SKILL_MEISTER_LEVEL && makingSkillLevel < SkillConstants.MAKING_SKILL_MEISTER_LEVEL) {
            chr.write(UserLocal.noticeMsg("You must be a Meister to craft this.", true));
            return false;
        }
        if (chr.getStat(Stat.fatigue) >= 200) {
            chr.write(UserLocal.noticeMsg("You are too fatigued.", true));
            return false;
        }
        for (TargetElem target : getTarget()) {
            if (!chr.canHold(target.itemID)) {// should handle count too
                chr.write(UserLocal.noticeMsg("Please make some space in your inventory.", true));
                return false;
            }
        }
        // TODO: handle check if the player have req items to craft.
        // TODO: handle cooldown repices.

        if (chr.getMoney() >= getReqMeso()) {
            return true;
        }
        return false;
    }

    public int getIncProficiency(Char chr, boolean success) {
        int makingSkillLevel = chr.getMakingSkillLevel(getReqSkillID());
        if (makingSkillLevel >= SkillConstants.MAKING_SKILL_EXPERT_LEVEL) {
            int recipeID = getRecipeID();
            if (!SkillConstants.isDecompositionRecipe(recipeID) && !SkillConstants.isSynthesizeRecipe(recipeID)) {
                double proficiency = 0.0;
                if (success) {
                    if (getIncSkillMasterProficiency() > 0) {
                        return getIncSkillMasterProficiency();
                    }
                    double recommandedSkillLevel = getRecommandedSkillLevel();
                    int flag = 3;
                    double rate = 1.0;
                    while (true) {
                        if ((flag & 1) != 0) {
                            rate *= recommandedSkillLevel;
                        }
                        flag >>= 1;
                        if (flag <= 0) {
                            break;
                        }
                        recommandedSkillLevel *= recommandedSkillLevel;
                    }
                    proficiency = Math.max(getRecommandedSkillLevel() - 7.0, 0.0) * rate;
                    if (isNeedOpenItem() && getReqSkillID() != 92040000) {
                        return (int) (proficiency + 700);
                    }
                } else {
                    if (getIncSkillMasterProficiencyOnFailure() > 0) {
                        return getIncSkillMasterProficiencyOnFailure();
                    }
                }
                return (int) proficiency;
            }
            return 0;
        }
        if (success) {
            if (getIncSkillProficiency() > 0) {
                return getIncSkillProficiency();
            }
            return getProficiency(makingSkillLevel, success);
        }
        if (getIncSkillProficiencyOnFailure() > 0) {
            return getIncSkillProficiencyOnFailure();
        }
        return getProficiency(makingSkillLevel, success);
    }

    private int getProficiency(int makingSkillLevel, boolean success) {
        if (SkillConstants.isDecompositionRecipe(getRecipeID())) {
            return 0;
        }
        int unk = getRecommandedSkillLevel() - makingSkillLevel;
        double proficiency = 0;
        switch (getReqSkillID()) {
            case 92000000:
            case 92010000:
                proficiency = Math.pow(1.05, unk) * 40.0;
                break;
            case 92020000:
            case 92030000:
                proficiency = Math.pow(1.05, unk) * 100.0;
                break;
            case 92040000:
                proficiency = Math.pow(1.05, unk) * 60.0;
                break;
        }
        if (success) {
            proficiency += proficiency;
        }
        return (int) proficiency;
    }

    public static int getSuccessProb(int reqSkillID, int recommandedSkillLevel, int skillLevel) {
        if (reqSkillID == 92000000 || reqSkillID == 92010000) {
            return 100;
        }
        int rate = Math.max(0, 100 - (recommandedSkillLevel - skillLevel) * 20);
        if (rate >= 100) {
            rate = 100;
        }
        return rate;
    }

    public static class TargetElem {
        private int itemID;
        private int count;
        private int probWeight;

        public int getItemID() {
            return itemID;
        }

        public void setItemID(int itemID) {
            this.itemID = itemID;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getProbWeight() {
            return probWeight;
        }

        public void setProbWeight(int probWeight) {
            this.probWeight = probWeight;
        }
    }
}
