package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.client.character.items.PetItem;
import net.swordie.ms.enums.InvType;
import net.swordie.ms.life.pet.PetSkill;
import net.swordie.ms.loaders.StringData;

/**
 * Created on 4/14/2018.
 */
public class PetInfo {
    private int itemID;
    private InvType invType;
    private int life;
    private int setItemID;
    private int limitedLife;
    private int evolutionID;
    private int type;
    private int evolReqItemID;
    private int evolNo;
    private int evol1;
    private int evol2;
    private int evol3;
    private int evol4;
    private int evol5;
    private int probEvol1;
    private int probEvol2;
    private int probEvol3;
    private int probEvol4;
    private int probEvol5;
    private int evolReqPetLvl;
    private boolean allowOverlappedSet;
    private boolean noRevive;
    private boolean noScroll;
    private boolean cash;
    private boolean giantPet;
    private boolean permanent;
    private boolean pickupItem;
    private boolean interactByUserAction;
    private boolean longRange;
    private boolean multiPet;
    private boolean autoBuff;
    private boolean starPlanetPet;
    private boolean evol;
    private boolean autoReact;
    private boolean pickupAll;
    private boolean sweepForDrop;
    private boolean consumeMP;
    private String runScript;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setInvType(InvType invType) {
        this.invType = invType;
    }

    public InvType getInvType() {
        return invType;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setAllowOverlappedSet(boolean allowOverlappedSet) {
        this.allowOverlappedSet = allowOverlappedSet;
    }

    public boolean isAllowOverlappedSet() {
        return allowOverlappedSet;
    }

    public void setNoRevive(boolean noRevive) {
        this.noRevive = noRevive;
    }

    public boolean isNoRevive() {
        return noRevive;
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public boolean isNoScroll() {
        return noScroll;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
    }

    public boolean isCash() {
        return cash;
    }

    public void setSetItemID(int setItemID) {
        this.setItemID = setItemID;
    }

    public int getSetItemID() {
        return setItemID;
    }

    public void setGiantPet(boolean giantPet) {
        this.giantPet = giantPet;
    }

    public boolean isGiantPet() {
        return giantPet;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPickupItem(boolean pickupItem) {
        this.pickupItem = pickupItem;
    }

    public boolean isPickupItem() {
        return pickupItem;
    }

    public void setInteractByUserAction(boolean interactByUserAction) {
        this.interactByUserAction = interactByUserAction;
    }

    public boolean isInteractByUserAction() {
        return interactByUserAction;
    }

    public void setLongRange(boolean longRange) {
        this.longRange = longRange;
    }

    public boolean isLongRange() {
        return longRange;
    }

    public void setMultiPet(boolean multiPet) {
        this.multiPet = multiPet;
    }

    public boolean isMultiPet() {
        return multiPet;
    }

    public void setLimitedLife(int limitedLife) {
        this.limitedLife = limitedLife;
    }

    public int getLimitedLife() {
        return limitedLife;
    }

    public void setAutoBuff(boolean autoBuff) {
        this.autoBuff = autoBuff;
    }

    public boolean isAutoBuff() {
        return autoBuff;
    }

    public void setEvolutionID(int evolutionID) {
        this.evolutionID = evolutionID;
    }

    public int getEvolutionID() {
        return evolutionID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }

    public String getRunScript() {
        return runScript;
    }

    public void setStarPlanetPet(boolean starPlanetPet) {
        this.starPlanetPet = starPlanetPet;
    }

    public boolean isStarPlanetPet() {
        return starPlanetPet;
    }

    public void setEvol(boolean evol) {
        this.evol = evol;
    }

    public boolean isEvol() {
        return evol;
    }

    public void setEvol1(int evol1) {
        this.evol1 = evol1;
    }

    public int getEvol1() {
        return evol1;
    }

    public void setEvolReqItemID(int evolReqItemID) {
        this.evolReqItemID = evolReqItemID;
    }

    public int getEvolReqItemID() {
        return evolReqItemID;
    }

    public void setEvolNo(int evolNo) {
        this.evolNo = evolNo;
    }

    public int getEvolNo() {
        return evolNo;
    }

    public void setEvol4(int evol4) {
        this.evol4 = evol4;
    }

    public int getEvol4() {
        return evol4;
    }

    public void setEvol3(int evol3) {
        this.evol3 = evol3;
    }

    public int getEvol3() {
        return evol3;
    }

    public void setEvol2(int evol2) {
        this.evol2 = evol2;
    }

    public int getEvol2() {
        return evol2;
    }

    public void setProbEvol1(int probEvol1) {
        this.probEvol1 = probEvol1;
    }

    public int getProbEvol1() {
        return probEvol1;
    }

    public void setProbEvol2(int probEvol2) {
        this.probEvol2 = probEvol2;
    }

    public int getProbEvol2() {
        return probEvol2;
    }

    public void setProbEvol3(int probEvol3) {
        this.probEvol3 = probEvol3;
    }

    public int getProbEvol3() {
        return probEvol3;
    }

    public void setProbEvol4(int probEvol4) {
        this.probEvol4 = probEvol4;
    }

    public int getProbEvol4() {
        return probEvol4;
    }

    public void setEvolReqPetLvl(int evolReqPetLvl) {
        this.evolReqPetLvl = evolReqPetLvl;
    }

    public int getEvolReqPetLvl() {
        return evolReqPetLvl;
    }

    public void setAutoReact(boolean autoReact) {
        this.autoReact = autoReact;
    }

    public boolean isAutoReact() {
        return autoReact;
    }

    public void setEvol5(int evol5) {
        this.evol5 = evol5;
    }

    public int getEvol5() {
        return evol5;
    }

    public void setProbEvol5(int probEvol5) {
        this.probEvol5 = probEvol5;
    }

    public int getProbEvol5() {
        return probEvol5;
    }

    public void setPickupAll(boolean pickupAll) {
        this.pickupAll = pickupAll;
    }

    public boolean isPickupAll() {
        return pickupAll;
    }

    public void setSweepForDrop(boolean sweepForDrop) {
        this.sweepForDrop = sweepForDrop;
    }

    public boolean isSweepForDrop() {
        return sweepForDrop;
    }

    public void setConsumeMP(boolean consumeMP) {
        this.consumeMP = consumeMP;
    }

    public boolean isConsumeMP() {
        return consumeMP;
    }

    public PetItem createPetItem() {
        PetItem pi = new PetItem();

        pi.setRepleteness((byte) 100);
        pi.setItemId(getItemID());
        pi.setInvType(getInvType());
        pi.setQuantity(1);
        pi.setName(StringData.getItemStringById(getItemID()));
        pi.setLevel((byte) 15);
        pi.setPetHue(-1);
        pi.addPetSkill(PetSkill.ITEM_PICKUP); // each pet has this now
        if(isAutoBuff()) {
            pi.addPetSkill(PetSkill.AUTO_BUFF);
        }
        if(isAutoReact()) {
            pi.addPetSkill(PetSkill.AUTO_FEED); // correct one?
        }
        if(isSweepForDrop()) {
            pi.addPetSkill(PetSkill.AUTO_MOVE);
        }
        if(isLongRange()) {
            pi.addPetSkill(PetSkill.EXPANDED_AUTO_MOVE);
        }

        return pi;
    }
}
