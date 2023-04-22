package net.swordie.ms.constants;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionMobInfo;

import java.util.*;

/**
 * @author Sjonnie
 * Created on 7/22/2018.
 */
public class MonsterCollectionRegion {
    private Map<Integer, MonsterCollectionSession> monsterCollectionSessions = new HashMap<>();
    private boolean claimedReward;

    public MonsterCollectionRegion() {
    }

    public Map<Integer, MonsterCollectionSession> getMonsterCollectionSessions() {
        return monsterCollectionSessions;
    }

    public void setMonsterCollectionSessions(Map<Integer, MonsterCollectionSession> monsterCollectionSessions) {
        this.monsterCollectionSessions = monsterCollectionSessions;
    }

    public void addMob(MonsterCollectionMobInfo mcmi) {
        int session = mcmi.getSession();
        if (!monsterCollectionSessions.containsKey(session)) {
            monsterCollectionSessions.put(session, new MonsterCollectionSession());
        }
        monsterCollectionSessions.get(session).addMob(mcmi);
    }

    public boolean hasMob(int templateID, MonsterCollectionMobInfo mcmi) {
        return monsterCollectionSessions.containsKey(mcmi.getSession()) &&
                monsterCollectionSessions.get(mcmi.getSession()).hasMob(templateID, mcmi);
    }

    public void sendCompleteInfo(Char chr, int regionKey) {
        for (Map.Entry<Integer, MonsterCollectionSession> entry : monsterCollectionSessions.entrySet()) {
            entry.getValue().sendCompletionInfo(chr, 100000 + regionKey * 100 + entry.getKey());
        }
    }

    public boolean isClaimedReward() {
        return claimedReward;
    }

    public void setClaimedReward(boolean claimedReward) {
        this.claimedReward = claimedReward;
    }
}
