package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.skills.MatrixRecord;
import net.swordie.ms.loaders.containerclasses.VCoreInfo;
import net.swordie.ms.loaders.containerclasses.VNodeInfo;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/**
 * @author Sjonnie
 * Created on 10/1/2018.
 */
public class VCoreData {
    private static final Logger log = Logger.getLogger(VCoreData.class);

    // job -> [(skillID, iconID)]
    private static Map<Integer, List<VCoreInfo>> jobSkills = new HashMap<>();
    private static Map<String, Set<Integer>> nameToJob = new HashMap<>();
    // type (1-skill, 2-enforcement, 3-special) -> level -> VNodeInfo
    private static Map<Integer, Map<Integer, VNodeInfo>> nodeInfos = new HashMap<>();

    static {
        nameToJob.put("warrior", new HashSet<>());
        nameToJob.put("magician", new HashSet<>());
        nameToJob.put("archer", new HashSet<>());
        nameToJob.put("rogue", new HashSet<>());
        nameToJob.put("pirate", new HashSet<>());
        nameToJob.put("none", new HashSet<>());
        nameToJob.put("all", new HashSet<>());
        nameToJob.get("warrior").addAll(Arrays.asList(112, 122, 132, 1112, 2112, 3112, 3122, 3712, 4112, 5112, 6112, 10112, 15112));
        nameToJob.get("magician").addAll(Arrays.asList(212, 222, 232, 1212, 2218, 2712, 3212, 4212, 11212, 14212/*, 15212*/));
        nameToJob.get("archer").addAll(Arrays.asList(312, 322, 1312, 2312, 3312));
        nameToJob.get("rogue").addAll(Arrays.asList(412, 422, 132, 434, 1412, 2412, 3612/*, 6412*/));
        nameToJob.get("pirate").addAll(Arrays.asList(512, 522, 532, 572, 1512, 2512, 3512, 3612, 6512/*, 15512*/));
        nameToJob.get("all").addAll(nameToJob.get("warrior"));
        nameToJob.get("all").addAll(nameToJob.get("magician"));
        nameToJob.get("all").addAll(nameToJob.get("archer"));
        nameToJob.get("all").addAll(nameToJob.get("rogue"));
        nameToJob.get("all").addAll(nameToJob.get("pirate"));
    }

    public static void loadVCoreDataFromWz() {
        String wzDir = String.format("%s/Etc.wz/", ServerConstants.WZ_DIR);
        String vCoreDir = wzDir + "VCore.img.xml";
        File file = new File(vCoreDir);
        Node root = XMLApi.getRoot(file);
        Node coreData = XMLApi.getFirstChildByNameBF(root, "CoreData");
        for (Node iconIdNode : XMLApi.getAllChildren(coreData)) {
            VCoreInfo vci = new VCoreInfo();
            vci.setIconID(Integer.parseInt(XMLApi.getNamedAttribute(iconIdNode, "name")));
            Node connectSkillNode = XMLApi.getFirstChildByNameBF(iconIdNode, "connectSkill");
            if (connectSkillNode == null) {
                Node spCoreOption = XMLApi.getFirstChildByNameBF(iconIdNode, "spCoreOption");
                if (spCoreOption == null) {
                    // id 40000000 doesn't have this
                    continue;
                }
                for (Node n1 : XMLApi.getAllChildren(spCoreOption)) {
                    String name1 = XMLApi.getNamedAttribute(n1, "name");
                    int intVal = 0;
                    switch (name1) {
                        case "cond":
                            for (Node n2 : XMLApi.getAllChildren(n1)) {
                                String name = XMLApi.getNamedAttribute(n2, "name");
                                String value = XMLApi.getNamedAttribute(n2, "value");
                                if (Util.isNumber(value) && !value.contains(".")) {
                                    intVal = Integer.parseInt(value);
                                }
                                switch (name) {
                                    case "cooltime":
                                        vci.setCooltime(intVal);
                                        break;
                                    case "count":
                                        vci.setCount(intVal);
                                        break;
                                    case "type":
                                        vci.setType(value);
                                        break;
                                    case "validTime":
                                        vci.setValidTime(intVal);
                                        break;
                                    case "prop":
                                        vci.setProp(Double.parseDouble(value));
                                        break;
                                }
                            }
                            break;
                        case "effect":
                            for (Node n2 : XMLApi.getAllChildren(n1)) {
                                String name = XMLApi.getNamedAttribute(n2, "name");
                                String value = XMLApi.getNamedAttribute(n2, "value");
                                if (Util.isNumber(value)) {
                                    intVal = Integer.parseInt(value);
                                }
                                switch (name) {
                                    case "skill_id":
                                        vci.setSkillID(intVal);
                                        break;
                                    case "skill_level":
                                        vci.setSlv(intVal);
                                        break;
                                    case "type":
                                        vci.setEffectType(value);
                                        break;
                                }
                            }
                            break;
                    }
                }
            } else {
                vci.setSkillID(Integer.parseInt(XMLApi.getNamedAttribute(XMLApi.getAllChildren(connectSkillNode).get(0), "value")));
            }
            Node maxLevel = XMLApi.getFirstChildByNameBF(iconIdNode, "maxLevel");
            vci.setMaxLevel(Integer.parseInt(XMLApi.getNamedAttribute(maxLevel, "value")));
            Node jobNode = XMLApi.getFirstChildByNameBF(iconIdNode, "job");
            for (Node n : XMLApi.getAllChildren(jobNode)) {
                String val = XMLApi.getNamedAttribute(n, "value");
                if (Util.isNumber(val)) {
                    addInfo(Integer.parseInt(val), vci);
                } else {
                    Set<Integer> jobs = nameToJob.get(val);
                    for (int job : jobs) {
                        addInfo(job, vci);
                    }
                }
            }
        }
        Node enforcementTopNode = XMLApi.getFirstChildByNameBF(root, "Enforcement");
        for (Node typeNode : XMLApi.getAllChildren(enforcementTopNode)) {
            String typeName = XMLApi.getNamedAttribute(typeNode, "name");
            int type = -1;
            switch (typeName) {
                case "Enforce":
                    type = 2;
                    break;
                case "Skill":
                    type = 1;
                    break;
                case "Special":
                    type = 3;
                    break;
                case "expCore":
                    type = 4;
                    break;
            }
            for (Node levelNode : XMLApi.getAllChildren(typeNode)) {
                int level = Integer.parseInt(XMLApi.getNamedAttribute(levelNode, "name"));
                VNodeInfo vni = new VNodeInfo();
                for (Node propNode : XMLApi.getAllChildren(levelNode)) {
                    String name = XMLApi.getNamedAttribute(propNode, "name");
                    int val = Integer.parseInt(XMLApi.getNamedAttribute(propNode, "value"));
                    switch (name) {
                        case "expEnforce":
                            vni.setExpEnforce(val);
                            break;
                        case "extract":
                            vni.setExtract(val);
                            break;
                        case "nextExp":
                            vni.setNextExp(val);
                            break;
                    }
                }
                addNodeInfo(type, level, vni);
            }
        }
    }

    public static void saveVCoreData(String dir) {
        File file = new File(String.format("%s/vcore_data.dat", dir));
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeShort(jobSkills.size());
            for (Map.Entry<Integer, List<VCoreInfo>> entry : jobSkills.entrySet()) {
                dos.writeInt(entry.getKey());
                dos.writeShort(entry.getValue().size());
                for (VCoreInfo vci : entry.getValue()) {
                    dos.writeInt(vci.getSkillID());
                    dos.writeInt(vci.getIconID());
                    dos.writeInt(vci.getMaxLevel());
                    dos.writeInt(vci.getCooltime());
                    dos.writeInt(vci.getCount());
                    dos.writeInt(vci.getValidTime());
                    dos.writeInt(vci.getSlv());
                    dos.writeDouble(vci.getProp());
                    dos.writeUTF(vci.getType());
                    dos.writeUTF(vci.getEffectType());
                }
            }
            dos.writeShort(nodeInfos.size());
            for (Map.Entry<Integer, Map<Integer, VNodeInfo>> entry1 : nodeInfos.entrySet()) {
                dos.writeInt(entry1.getKey());
                dos.writeShort(entry1.getValue().size());
                for (Map.Entry<Integer, VNodeInfo> entry2 : entry1.getValue().entrySet()) {
                    dos.writeInt(entry2.getKey());
                    VNodeInfo vni = entry2.getValue();
                    dos.writeInt(vni.getExpEnforce());
                    dos.writeInt(vni.getExtract());
                    dos.writeInt(vni.getNextExp());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadVCoreData() {
        String dir = ServerConstants.DAT_DIR;
        File file = new File(String.format("%s/vcore_data.dat", dir));
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
                int job = dis.readInt();
                int size2 = dis.readShort();
                List<VCoreInfo> vcis = new ArrayList<>();
                for (int j = 0; j < size2; j++) {
                    VCoreInfo vci = new VCoreInfo();
                    vci.setSkillID(dis.readInt());
                    vci.setIconID(dis.readInt());
                    vci.setMaxLevel(dis.readInt());
                    vci.setCooltime(dis.readInt());
                    vci.setCount(dis.readInt());
                    vci.setValidTime(dis.readInt());
                    vci.setSlv(dis.readInt());
                    vci.setProp(dis.readDouble());
                    vci.setType(dis.readUTF());
                    vci.setEffectType(dis.readUTF());
                    if (vci.getSkillID() == 400001039) // remove True Arachnid Reflection from list
                        continue;
                    vcis.add(vci);
                }
                jobSkills.put(job, vcis);
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                int type = dis.readInt();
                int size2 = dis.readShort();
                for (int j = 0; j < size2; j++) {
                    int level = dis.readInt();
                    VNodeInfo vni = new VNodeInfo();
                    vni.setExpEnforce(dis.readInt());
                    vni.setExtract(dis.readInt());
                    vni.setNextExp(dis.readInt());
                    addNodeInfo(type, level, vni);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addInfo(int job, VCoreInfo vci) {
        if (!jobSkills.containsKey(job)) {
            jobSkills.put(job, new ArrayList<>());
        }
        jobSkills.get(job).add(vci);
    }

    private static void addNodeInfo(int type, int level, VNodeInfo info) {
        if (!nodeInfos.containsKey(type)) {
            nodeInfos.put(type, new HashMap<>());
        }
        nodeInfos.get(type).put(level, info);
    }

    public static List<VCoreInfo> getPossibilitiesByJob(int job) {
        return jobSkills.getOrDefault(job, null);
    }

    public static Map<Integer, List<VCoreInfo>> getJobSkills() {
        return jobSkills;
    }

    public static VNodeInfo getNodeInfo(MatrixRecord mr) {
        return getNodeInfo(mr.getIconID(), mr.getSlv());
    }

    public static VNodeInfo getNodeInfo(int iconID, int level) {
        return nodeInfos.get(iconID / 10000000).get(level);
    }

    public static void generateDatFiles() {
        long start = System.currentTimeMillis();
        log.info("Started generating VCore data.");
        loadVCoreDataFromWz();
        saveVCoreData(ServerConstants.DAT_DIR);
        log.info("Completed generating VCore data in " + (System.currentTimeMillis() - start) + "ms.");
    }

    public static void main(String[] args) {
        generateDatFiles();
    }
}
