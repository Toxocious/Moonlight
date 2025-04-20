package net.swordie.ms.constants;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionMobInfo;
import net.swordie.ms.util.Util;

import javax.persistence.*;
import java.util.*;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionSession {
    private Map<Integer, MonsterCollectionGroup> monsterCollectionGroups = new HashMap<>();
    private boolean rewardClaimed;
    private int reward;
    private int rewardQuantity;

    public MonsterCollectionSession() {
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Map<Integer, MonsterCollectionGroup> getMonsterCollectionGroups() {
        return monsterCollectionGroups;
    }

    public void setMonsterCollectionGroups(Map<Integer, MonsterCollectionGroup> monsterCollectionGroups) {
        this.monsterCollectionGroups = monsterCollectionGroups;
    }

    public boolean isRewardClaimed() {
        return rewardClaimed;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }

    public void addMob(MonsterCollectionMobInfo mcmi) {
        int group = mcmi.getPosition() / 5;
        if (!monsterCollectionGroups.containsKey(group)) {
            monsterCollectionGroups.put(group, new MonsterCollectionGroup());
        }
        monsterCollectionGroups.get(group).addMob(mcmi.getPosition() % 5, mcmi.getMobID());
    }

    public void setRewardQuantity(int rewardQuantity) {
        this.rewardQuantity = rewardQuantity;
    }

    public int getRewardQuantity() {
        return rewardQuantity;
    }

    public boolean hasMob(int templateID, MonsterCollectionMobInfo mcmi) {
        int group = mcmi.getPosition() / 5;
        return monsterCollectionGroups.containsKey(group) &&
                monsterCollectionGroups.get(group).hasMob(templateID);
    }

    public void sendCompletionInfo(Char chr, int sessionKey) {
        // I am not proud of this, but it works perfectly
        // an octet per mob. Probably 3 different properties, but can't figure those out atm
        // <sessionid>=aaab bbcc cddd <repeat>
        // example: "0=892892892892892892889289289289289289289289289289";
        int[] total = new int[6];
        StringBuilder stringBuilder = new StringBuilder();
        // 25 mobs per page
        for (int i = 0; i < GameConstants.MOBS_PER_PAGE; i++) {
            int group = i / GameConstants.MOBS_PER_GROUP;
            boolean hasMob = getMonsterCollectionGroups().containsKey(group) &&
                    getMonsterCollectionGroups().get(group).hasMobAtPosition(i % 5);
            char active = hasMob ? '1' : '0';
            stringBuilder.append(active); // probably 3 different properties, so keeping them seperated
            stringBuilder.append(active);
            stringBuilder.append(active);
        }
        // total is 6 ints = 6 * 32. Mob info = 25 * 3. 6 * 32 - 25 * 3 = 117.
        for (int i = 0; i < 117; i++) {
            stringBuilder.append("0"); // TODO Indicate which rewards are available
        }
        String binaryString = stringBuilder.toString();
        // put them back into ints
        int INT_SIZE = Integer.SIZE;
        for (int i = 0; i < total.length; i++) {
            // 32 bits per integer
            total[i] = Integer.parseInt(binaryString.substring((i * INT_SIZE) + 1, (i * INT_SIZE) + INT_SIZE), 2);
            // When do we get unsigned java ints
            if (binaryString.charAt(i * INT_SIZE) == '1') {
                total[i] |= 0x80000000;
            }
        }
        // and then convert them back into a hexstring
        stringBuilder = new StringBuilder(sessionKey % 100 + "=");
        for (int i = 0; i < total.length; i++) {
            stringBuilder.append(Util.leftPaddedString(8, '0', Integer.toHexString(total[i])));
        }
        chr.write(UserLocal.collectionRecordMessage(sessionKey, stringBuilder.toString()));
    }
}
