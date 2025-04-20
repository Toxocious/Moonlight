package net.swordie.ms.loaders;

import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.MonsterCollectionGroup;
import net.swordie.ms.constants.MonsterCollectionRegion;
import net.swordie.ms.constants.MonsterCollectionSession;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionGroupRewardInfo;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionMobInfo;
import net.swordie.ms.loaders.containerclasses.MonsterCollectionSessionRewardInfo;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.util.container.Tuple;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

/**
 * @author Sjonnie
 * Created on 7/23/2018.
 */
public class MonsterCollectionData {
    private static final Logger log = Logger.getLogger(MonsterCollectionData.class);

    private static Map<Integer, MonsterCollectionRegion> monsterCollectionInfo = new HashMap<>();
    private static Map<Integer, Triple<Integer, Integer, Integer>> monsterInfo = new HashMap<>();
    // reward from group -> hours for exploration
    private static Map<Integer, Integer> rewardToMinutes = new HashMap<>();
    // reward from group -> (reward id, chance)
    private static Map<Integer, Tuple<Integer, Integer>> explorationRewards = new HashMap<>();

    static {
        rewardToMinutes.put(2434929, 30);
        rewardToMinutes.put(2434930, 60 * 3);
        rewardToMinutes.put(2434931, 60 * 12);
        rewardToMinutes.put(2434932, 60 * 24);
        rewardToMinutes.put(2434958, 60 * 3);
        rewardToMinutes.put(2434959, 60 * 3);
    }

    public static void loadFromSQL() {
        long start = System.currentTimeMillis();
        Session session = DatabaseManager.getSession();
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("from MonsterCollectionSessionRewardInfo");
        List<MonsterCollectionSessionRewardInfo> sessionRewardInfos = new ArrayList<>(q.list());
        q = session.createQuery("from MonsterCollectionGroupRewardInfo");
        List<MonsterCollectionGroupRewardInfo> groupRewardInfos = new ArrayList<>(q.list());
        q = session.createQuery("from MonsterCollectionMobInfo");
        List<MonsterCollectionMobInfo> mobInfos = new ArrayList<>(q.list());
        t.commit();
        session.close();
        mobInfos.forEach(mi -> {
            put(mi);
            monsterInfo.put(mi.getMobID(), new Triple(mi.getRegion(), mi.getSession(), mi.getPosition()));
        });
        for (MonsterCollectionSessionRewardInfo mcsri : sessionRewardInfos) {
            monsterCollectionInfo.get(mcsri.getRegion()).getMonsterCollectionSessions().get(mcsri.getSession())
                    .setReward(mcsri.getRewardID());
            monsterCollectionInfo.get(mcsri.getRegion()).getMonsterCollectionSessions().get(mcsri.getSession())
                    .setRewardQuantity(mcsri.getQuantity());
        }
        for (MonsterCollectionGroupRewardInfo mcgri : groupRewardInfos) {
            monsterCollectionInfo.get(mcgri.getRegion()).getMonsterCollectionSessions().get(mcgri.getSession())
                    .getMonsterCollectionGroups().get(mcgri.getGroupID())
                    .setReward(mcgri.getRewardID());
            monsterCollectionInfo.get(mcgri.getRegion()).getMonsterCollectionSessions().get(mcgri.getSession())
                    .getMonsterCollectionGroups().get(mcgri.getGroupID())
                    .setRewardQuantity(mcgri.getQuantity());
        }
        log.info("Loaded MonsterCollectionData in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void put(MonsterCollectionMobInfo mcmi) {
        if (!monsterCollectionInfo.containsKey(mcmi.getRegion())) {
            monsterCollectionInfo.put(mcmi.getRegion(), new MonsterCollectionRegion());
        }
        monsterCollectionInfo.get(mcmi.getRegion()).addMob(mcmi);
    }


    public static MonsterCollectionMobInfo getMobInfoByID(int templateID) {
        Triple<Integer, Integer, Integer> info = monsterInfo.get(templateID);
        if (info == null) {
            return null;
        }
        return new MonsterCollectionMobInfo(templateID, info.getLeft(), info.getMiddle(), info.getRight());
    }

    public static int getRequiredMobs(int region, int session, int group) {
        return monsterCollectionInfo.get(region).getMonsterCollectionSessions().get(session)
                .getMonsterCollectionGroups().get(group).getMobs().size();
    }

    private static MonsterCollectionSession getSession(int region, int session) {
        return monsterCollectionInfo.get(region).getMonsterCollectionSessions().get(session);
    }

    private static MonsterCollectionGroup getGroup(int region, int session, int group) {
        return getSession(region, session).getMonsterCollectionGroups().get(group);
    }

    public static Tuple<Integer, Integer> getReward(int region, int session, int group) {
        if (group == -1) {
            MonsterCollectionSession mcs = getSession(region, session);
            return new Tuple<>(mcs.getReward(), mcs.getRewardQuantity());
        }
        MonsterCollectionGroup mcg = getGroup(region, session, group);
        return new Tuple<>(mcg.getReward(), mcg.getRewardQuantity());
    }

    public static int getExplorationMinutes(int region, int session, int group) {
        int reward = getGroup(region, session, group).getReward();
        return rewardToMinutes.get(reward);
    }
}
