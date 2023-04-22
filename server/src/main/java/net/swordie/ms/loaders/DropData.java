package net.swordie.ms.loaders;

import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.MobConstants;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.ServerConstants;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.*;

/**
 * Created on 2/21/2018.
 */
public class DropData {
    private static final Logger log = Logger.getLogger(DropData.class);

    private static Map<Integer, Set<DropInfo>> drops = new HashMap<>();

    public static void loadCompleteDropsFromTxt(File file) {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            int mobID = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains("//")) {
                    continue;
                }
                String[] split = line.split(" ");
                /*
                Format:
                mobID (int)
                or:
                itemID (int) chance (int out of 10000) minCount (int) maxCount (int)
                itemID (int) chance (int out of 10000)
                So, single line = mobID, else 2/4 ints
                 */
                if(split.length == 1 && split[0].equalsIgnoreCase("global")) {
                    mobID = -1;
                } else if(split.length == 1 && !split[0].equals("")) {
                    mobID = Integer.parseInt(split[0]);
                } else if(split.length >= 2) {
                    int itemID = Integer.parseInt(split[0]);
                    int chance = Integer.parseInt(split[1]);
                    int minQuant = 1;
                    int maxQuant = 1;
                    if (split.length >= 4 && Util.isNumber(split[3])) { // hack to make you able to comment stuff
                        // min/max count
                        minQuant = Integer.parseInt(split[2]);
                        maxQuant = Integer.parseInt(split[3]);
                    }
                    DropInfo dropInfo = new DropInfo(itemID, chance, minQuant, maxQuant);
                    addDrop(mobID, dropInfo);
                }
            }
            for (Map.Entry<Integer, Set<DropInfo>> drop : getDrops().entrySet()) {
                mobID = drop.getKey();
                for (DropInfo di : drop.getValue()) {
                    log.debug(String.format("(%d, %d, %d, %d, %d),", mobID, di.getItemID(), di.getChance(), di.getMinQuant(), di.getMaxQuant()));
                }
            }
//            for (DropInfo globalDrop : getDropInfoByID(-1)) {
//                for (int key : getDrops().keySet()) {
//                    if (key != -1) {
//                        addDrop(key, globalDrop);
//                    }
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Set<DropInfo> getDropInfoByID(int mobID) {
        Set<DropInfo> drops = getCachedDropInfoById(mobID);
        if (drops == null) {
            drops = getDropInfoByIdFromDB(mobID);
            if (mobID != -1 && mobID < MobConstants.QUEST_MOB_START) {
                drops.addAll(getDropInfoByIdFromDB(-1)); // global drops
            }
            getDrops().put(mobID, drops);
        }
        return drops;
    }

    private static Set<DropInfo> getDropInfoByIdFromDB(int mobID) {
        List l;
        try (Session session = DatabaseManager.getSession()) {
            if (session == null) {
                return new HashSet<>();
            }
            Transaction transaction = session.beginTransaction();
            // String.format for query, just to fill in the class
            // Can't set the FROM clause with a parameter it seems
            javax.persistence.Query query = session.createQuery("FROM DropInfo WHERE mobid = :mobid");
            query.setParameter("mobid", mobID);
            l = ((org.hibernate.query.Query) query).list();
            transaction.commit();
            session.close();
        }
        return l == null ? null : new HashSet<>(l);
    }

    private static Set<DropInfo> getCachedDropInfoById(int mobID) {
        return getDrops().getOrDefault(mobID, null);
    }

    private static void addDrop(int mobID, DropInfo dropInfo) {
        if(!getDrops().containsKey(mobID)) {
            getDrops().put(mobID, new HashSet<>());
        }
        getDrops().get(mobID).add(dropInfo);
    }

    private static Map<Integer, Set<DropInfo>> getDrops() {
        return drops;
    }

    public static void main(String[] args) {
        loadCompleteDropsFromTxt(new File(ServerConstants.RESOURCES_DIR + "/mobDrops.txt"));
    }

    public static void clear() {
        getDrops().clear();
    }
}
