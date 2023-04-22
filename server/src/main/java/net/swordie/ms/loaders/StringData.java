package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.loaders.containerclasses.SkillStringInfo;
import org.apache.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.*;

/**
 * Created on 1/11/2018.
 */
public class StringData {
    public static Map<Integer, SkillStringInfo> skillString = new HashMap<>();
    public static Map<Integer, String> itemStrings = new HashMap<>();
    public static Map<Integer, String> mapStrings = new HashMap<>();
    public static Map<Integer, String> mobStrings = new HashMap<>(); // name + health
    public static Map<Integer, String> npcStrings = new HashMap<>();
    public static Map<Integer, String> questStrings = new HashMap<>();

    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    public static Map<Integer, String> getItemStrings() {
        return itemStrings;
    }

    public static Map<Integer, String> getMapStrings() {
        return mapStrings;
    }

    public static Map<Integer, String> getMobStrings() {
        return mobStrings;
    }

    public static Map<Integer, String> getNpcStrings() {
        return npcStrings;
    }

    public static Map<Integer, String> getQuestStrings() {
        return questStrings;
    }

    public static void loadItemStringsFromWz() {
        log.info("Started loading item strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/";
        String[] files = new String[]{"Cash", "Consume", "Eqp", "Ins", "Pet", "Etc"};
        for(String fileDir : files) {
            File file = new File(wzDir + fileDir + ".img.xml");
            Document doc = XMLApi.getRoot(file);
            Node node = doc;
            List<Node> nodes = XMLApi.getAllChildren(node);
            for (Node topNode : nodes) {
                if(!fileDir.equalsIgnoreCase("eqp") &&
                        !fileDir.equalsIgnoreCase("etc")) {
                    for (Node mainNode : XMLApi.getAllChildren(topNode)) {
                        int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                        Node nameNode = XMLApi.getFirstChildByNameBF(mainNode, "name");
                        if (nameNode != null) {
                            String name = XMLApi.getNamedAttribute(nameNode, "value");
                            itemStrings.put(id, name);
                        }
                    }
                } else if(fileDir.equalsIgnoreCase("etc")) {
                    for (Node category : XMLApi.getAllChildren(topNode)) {
                        for (Node mainNode : XMLApi.getAllChildren(category)) {
                            int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                            if(XMLApi.getFirstChildByNameBF(mainNode, "name") != null) {
                                String name = XMLApi.getNamedAttribute(XMLApi.getFirstChildByNameBF(mainNode, "name"), "value");
                                itemStrings.put(id, name);
                            }
                        }
                    }
                } else {
                    for(Node n : XMLApi.getAllChildren(topNode)) {
                        for (Node category : XMLApi.getAllChildren(n)) {
                            for (Node mainNode : XMLApi.getAllChildren(category)) {
                                int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                                Node nameNode = XMLApi.getFirstChildByNameBF(mainNode, "name");
                                if (nameNode != null) {
                                    String name = XMLApi.getNamedAttribute(nameNode, "value");
                                    itemStrings.put(id, name);
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info(String.format("Loaded item strings from wz in %dms.", System.currentTimeMillis() - start));
    }

    public static void loadSkillStringsFromWz() {
        log.info("Started loading skill strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/Skill.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        Node node = doc;
        List<Node> nodes = XMLApi.getAllChildren(node);
        for (Node topNode : nodes) {
            for(Node mainNode : XMLApi.getAllChildren(topNode)) {
                Node bookNameNode = XMLApi.getFirstChildByNameBF(mainNode, "bookName");
                if (bookNameNode != null) {
                    continue;
                }
                SkillStringInfo ssi = new SkillStringInfo();
                Node nameNode = XMLApi.getFirstChildByNameBF(mainNode, "name");
                if (nameNode != null) {
                    ssi.setName(XMLApi.getNamedAttribute(nameNode, "value"));
                }
                Node descNode = XMLApi.getFirstChildByNameBF(mainNode, "desc");
                if (descNode != null) {
                    ssi.setDesc(XMLApi.getNamedAttribute(descNode, "value"));
                }
                Node hNode = XMLApi.getFirstChildByNameBF(mainNode, "h");
                if (hNode != null) {
                    ssi.setH(XMLApi.getNamedAttribute(hNode, "value"));
                } else {
                    Node h1Node = XMLApi.getFirstChildByNameBF(mainNode, "h1");
                    if (h1Node != null) {
                        ssi.setH(XMLApi.getNamedAttribute(h1Node, "value"));
                    }
                }
                skillString.put(Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name")), ssi);
            }
        }
        log.info(String.format("Loaded skill strings in %dms.", System.currentTimeMillis() - start));
    }

    public static void loadMobStringsFromWz() {
        log.info("Started loading mob strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/Mob.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        List<Node> nodes = XMLApi.getAllChildren(doc);
        for (Node topNode : nodes) {
            for (Node mainNode : XMLApi.getAllChildren(topNode)) {
                int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                for (Node infoNode : XMLApi.getAllChildren(mainNode)) {
                    String name = XMLApi.getNamedAttribute(infoNode, "name");
                    String value = XMLApi.getNamedAttribute(infoNode, "value");
                    switch (name) {
                        case "name":
                            getMobStrings().put(id, value);
                            break;
                    }
                }
            }
        }
        log.info(String.format("Loaded mob strings in %dms.", System.currentTimeMillis() - start));
    }

    public static void loadNpcStringsFromWz() {
        log.info("Started loading npc strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/Npc.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        List<Node> nodes = XMLApi.getAllChildren(doc);
        for (Node topNode : nodes) {
            for (Node mainNode : XMLApi.getAllChildren(topNode)) {
                int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                for (Node infoNode : XMLApi.getAllChildren(mainNode)) {
                    String name = XMLApi.getNamedAttribute(infoNode, "name");
                    String value = XMLApi.getNamedAttribute(infoNode, "value");
                    switch (name) {
                        case "name":
                            getNpcStrings().put(id, value);
                            break;
                    }
                }
            }
        }
        log.info(String.format("Loaded npc strings in %dms.", System.currentTimeMillis() - start));
    }

    public static void loadMapStringsFromWz() {
        log.info("Started loading quest strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/String.wz/Map.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        List<Node> nodes = XMLApi.getAllChildren(doc);
        for (Node topNode : nodes) {
            for (Node areaNode : XMLApi.getAllChildren(topNode)) {
                for (Node mainNode : XMLApi.getAllChildren(areaNode)) {
                    int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
                    String mapName = "Unk";
                    String streetName = "Unk";
                    for (Node infoNode : XMLApi.getAllChildren(mainNode)) {
                        String name = XMLApi.getNamedAttribute(infoNode, "name");
                        String value = XMLApi.getNamedAttribute(infoNode, "value");
                        switch (name) {
                            case "mapName":
                                mapName = value;
                                break;
                            case "streetName":
                                streetName = value;
                                break;
                        }
                    }
                    getMapStrings().put(id, String.format("%s : %s", streetName, mapName));
                }
            }
        }
        log.info(String.format("Loaded map strings in %dms.", System.currentTimeMillis() - start));

    }

    private static void loadQuestStringsFromWz() {

        log.info("Started loading quest strings from wz.");
        long start = System.currentTimeMillis();
        String wzDir = ServerConstants.WZ_DIR + "/Quest.wz/QuestInfo.img.xml";
        File file = new File(wzDir);
        Document doc = XMLApi.getRoot(file);
        List<Node> nodes = XMLApi.getAllChildren(doc);
        for (Node mainNode : XMLApi.getAllChildren(nodes.get(0))) {
            int id = Integer.parseInt(XMLApi.getNamedAttribute(mainNode, "name"));
            String questName = "Unk";
            for (Node infoNode : XMLApi.getAllChildren(mainNode)) {
                String name = XMLApi.getNamedAttribute(infoNode, "name");
                String value = XMLApi.getNamedAttribute(infoNode, "value");
                switch (name) {
                    case "name":
                        questName = value;
                        break;
                    // some more info available here, ignoring it for now
                }
            }
            getQuestStrings().put(id, String.format("%s", questName));
        }
        log.info(String.format("Loaded quest strings in %dms.", System.currentTimeMillis() - start));
    }

    public static Map<Integer, SkillStringInfo> getSkillString() {
        return skillString;
    }

    public static void generateDatFiles() {
        log.info("Started generating string data.");
        long start = System.currentTimeMillis();
        loadSkillStringsFromWz();
        loadItemStringsFromWz();
        loadMobStringsFromWz();
        loadNpcStringsFromWz();
        loadMapStringsFromWz();
        loadQuestStringsFromWz();
        saveSkillStrings(ServerConstants.DAT_DIR + "/strings");
        saveItemStrings(ServerConstants.DAT_DIR + "/strings");
        saveMobStrings(ServerConstants.DAT_DIR + "/strings");
        saveNpcStrings(ServerConstants.DAT_DIR + "/strings");
        saveMapStrings(ServerConstants.DAT_DIR + "/strings");
        saveQuestStrings(ServerConstants.DAT_DIR + "/strings");
        log.info(String.format("Completed generating string data in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveQuestStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "/quests.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(questStrings.size());
            for(Map.Entry<Integer, String> entry : questStrings.entrySet()) {
                int id = entry.getKey();
                String name = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveSkillStrings(String dir) {
        Util.makeDirIfAbsent(dir);
//        String fileDir = dir + "/skills";
//        Util.makeDirIfAbsent(fileDir);
        File file = new File(dir + "/skills.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(getSkillString().size());
            for(Map.Entry<Integer, SkillStringInfo> entry : getSkillString().entrySet()) {
                int id = entry.getKey();
                SkillStringInfo ssi = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(ssi.getName() == null ? "" : ssi.getName());
                dataOutputStream.writeUTF(ssi.getDesc());
                dataOutputStream.writeUTF(ssi.getH());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSkillStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/skills.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                SkillStringInfo ssi = new SkillStringInfo();
                ssi.setName(dataInputStream.readUTF());
                ssi.setDesc(dataInputStream.readUTF());
                ssi.setH(dataInputStream.readUTF());
                getSkillString().put(id, ssi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded skill strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveItemStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "/items.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(itemStrings.size());
            for(Map.Entry<Integer, String> entry : itemStrings.entrySet()) {
                int id = entry.getKey();
                String ssi = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(ssi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadItemStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/items.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                itemStrings.put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded item strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    public static void loadQuestStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/quests.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                questStrings.put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded quest strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveMobStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "/mobs.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(getMobStrings().size());
            for(Map.Entry<Integer, String> entry : getMobStrings().entrySet()) {
                int id = entry.getKey();
                String name = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadMobStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/mobs.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                getMobStrings().put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded mob strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveNpcStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "/npcs.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(getNpcStrings().size());
            for(Map.Entry<Integer, String> entry : getNpcStrings().entrySet()) {
                int id = entry.getKey();
                String name = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadNpcStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/npcs.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                getNpcStrings().put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded npc strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    private static void saveMapStrings(String dir) {
        Util.makeDirIfAbsent(dir);
        File file = new File(dir + "/maps.dat");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(getMapStrings().size());
            for(Map.Entry<Integer, String> entry : getMapStrings().entrySet()) {
                int id = entry.getKey();
                String name = entry.getValue();
                dataOutputStream.writeInt(id);
                dataOutputStream.writeUTF(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadMapStrings() {
        long start = System.currentTimeMillis();
        File file = new File(ServerConstants.DAT_DIR + "/strings/maps.dat");
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                int id = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                getMapStrings().put(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded item strings from data file in %dms.", System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        generateDatFiles();
//        generateTextFiles();
    }


    public static SkillStringInfo getSkillStringById(int id) {
        return getSkillString().getOrDefault(id, null);
    }

    public static String getItemStringById(int id) {
        return getItemStrings().getOrDefault(id, null);
    }

    public static String getMobStringById(int id) {
        return getMobStrings().getOrDefault(id, null);
    }

    public static String getNpcStringById(int id) {
        return getNpcStrings().getOrDefault(id, null);
    }

    public static String getMapStringById(int id) {
        return getMapStrings().getOrDefault(id, null);
    }

    public static String getQuestStringById(int id) {
        return getQuestStrings().getOrDefault(id, null);
    }


    public static Map<Integer, String> getItemStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : itemStrings.entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            if(name == null) {
                continue;
            }
            String ssName = name.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, name);
            }
        }
        return res;
    }

    public static Map<Integer, String> getQuestStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : questStrings.entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            if(name == null) {
                continue;
            }
            String ssName = name.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, name);
            }
        }
        return res;
    }


    public static Map<Integer, SkillStringInfo> getSkillStringByName(String query) {
        Map<Integer, SkillStringInfo> res = new HashMap<>();
        for (Map.Entry<Integer, SkillStringInfo> entry : StringData.getSkillString().entrySet()) {
            int id = entry.getKey();
            SkillStringInfo ssi = entry.getValue();
            if(ssi.getName() == null) {
                continue;
            }
            String ssName = ssi.getName().toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, ssi);
            }
        }
        return res;
    }

    public static Map<Integer, String> getMobStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : getMobStrings().entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            if(name == null) {
                continue;
            }
            String ssName = name.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, name);
            }
        }
        return res;
    }

    public static Map<Integer, String> getNpcStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : getNpcStrings().entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            if(name == null) {
                continue;
            }
            String ssName = name.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, name);
            }
        }
        return res;
    }

    public static Map<Integer, String> getMapStringByName(String query) {
        query = query.toLowerCase();
        Map<Integer, String> res = new HashMap<>();
        for (Map.Entry<Integer, String> entry : getMapStrings().entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            if(name == null) {
                continue;
            }
            String ssName = name.toLowerCase();
            if (ssName.contains(query)) {
                res.put(id, name);
            }
        }
        return res;
    }

    public static void clear() {
        getSkillString().clear();
        getItemStrings().clear();
        getMobStrings().clear();
        getNpcStrings().clear();
        getMapStrings().clear();
    }

    public static void load() {
        loadItemStrings();
        loadSkillStrings();
        loadMobStrings();
        loadNpcStrings();
        loadMapStrings();
        loadQuestStrings();
    }

    public static void generateTextFiles() {
        // big hack, but that's okay
        load();
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, SkillStringInfo> sortedSkillTree = new TreeMap<>(Comparator.comparingInt(Integer::intValue));
        List<Map<Integer, String>> mapList = new ArrayList<>();
        mapList.add(getMobStrings());
        mapList.add(getNpcStrings());
        mapList.add(getMapStrings());
        String[] names = new String[]{"Mob", "Npc", "Map"};
        sortedSkillTree.putAll(getSkillString());
        for (Map.Entry<Integer, SkillStringInfo> entry : sortedSkillTree.entrySet()) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue().getName()).append("\r\n");
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(ServerConstants.RESOURCES_DIR + "/Skill.txt")))) {
            pw.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeMap<Integer, String> sortedTree = new TreeMap<>(Comparator.comparingInt(Integer::intValue));
        int i = 0;
        for (Map<Integer, String> map : mapList) {
            sb = new StringBuilder();
            sortedTree.clear();
            sortedTree.putAll(map);
            String fileName = names[i++] + ".txt";
            for (Map.Entry<Integer, String> entry : sortedTree.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\r\n");
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(new File(ServerConstants.RESOURCES_DIR + "/" + fileName)))) {
                pw.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        names = new String[]{"Eqp", "Use", "Ins", "Etc", "Cash"};
        mapList.clear();
        for (int j = 0; j < names.length; j++) {
            mapList.add(new TreeMap<>(Comparator.comparingInt(Integer::intValue)));
        }
        for (Map.Entry<Integer, String> entry : itemStrings.entrySet()) {
            // put them into buckets, one bucket per item category
            mapList.get(Math.max(0, entry.getKey() / 1000000 - 1)).put(entry.getKey(), entry.getValue());
        }
        i = 0;
        for (Map<Integer, String> map : mapList) {
            sb = new StringBuilder();
            String fileName = names[i++] + ".txt";
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\r\n");
            }
            try (PrintWriter pw = new PrintWriter(new FileWriter(new File(ServerConstants.RESOURCES_DIR + "/" + fileName)))) {
                pw.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
