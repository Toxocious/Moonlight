package net.swordie.ms.constants;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterCollection;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MonsterCollectionData;

import java.util.*;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionGroup {
    private Map<Integer, Integer> mobs = new HashMap<>();
    private boolean rewardClaimed;
    private int reward;
    private int rewardQuantity;


    public MonsterCollectionGroup() {
    }

    public Map<Integer, Integer> getMobs() {
        return mobs;
    }

    public void setMobs(Map<Integer, Integer> mobs) {
        this.mobs = mobs;
    }

    public void addMob(int pos, int mob) {
        getMobs().put(pos, mob);
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public boolean isRewardClaimed() {
        return rewardClaimed;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }

    public void setRewardQuantity(int rewardQuantity) {
        this.rewardQuantity = rewardQuantity;
    }

    public int getRewardQuantity() {
        return rewardQuantity;
    }

    public boolean hasMob(int templateID) {
        return getMobs().values().contains(templateID);
    }

    public boolean hasMobAtPosition(int pos) {
        return getMobs().containsKey(pos);
    }
}
