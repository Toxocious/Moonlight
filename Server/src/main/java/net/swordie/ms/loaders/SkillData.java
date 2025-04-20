package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.enums.WeaponType;
import net.swordie.ms.life.mob.skill.MobSkillStat;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.*;
import net.swordie.ms.util.container.Tuple;
import org.apache.log4j.LogManager;
import org.python.jline.internal.Log;
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

/**
 * Created on 12/20/2017.
 */
public class SkillData {

    private static Map<Integer, SkillInfo> skills = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> eliteMobSkills = new HashMap<>();
    private static Map<Short, Map<Short, MobSkillInfo>> mobSkillInfos = new HashMap<>();
    private static Map<Integer, MakingSkillRecipe> makingSkillRecipes = new HashMap<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private static final boolean LOG_UNKS = false;

    public static void saveSkills(String dir) {
        Util.makeDirIfAbsent(dir);
        for (Map.Entry<Integer, SkillInfo> entry : skills.entrySet()) {
            SkillInfo si = entry.getValue();
            File file = new File(String.format("%s/%d.dat", dir, si.getSkillId()));
            try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
                dataOutputStream.writeInt(si.getSkillId());
                dataOutputStream.writeInt(si.getRootId());
                dataOutputStream.writeInt(si.getMaxLevel());
                dataOutputStream.writeInt(si.getMasterLevel());
                dataOutputStream.writeInt(si.getFixLevel());
                dataOutputStream.writeBoolean(si.isInvisible());
                dataOutputStream.writeBoolean(si.isMassSpell());
                dataOutputStream.writeInt(si.getType());
                dataOutputStream.writeUTF(si.getElemAttr());
                dataOutputStream.writeInt(si.getHyper());
                dataOutputStream.writeInt(si.getHyperStat());
                dataOutputStream.writeInt(si.getVehicleId());
                dataOutputStream.writeInt(si.getReqTierPoint());
                dataOutputStream.writeBoolean(si.isNotCooltimeReset());
                dataOutputStream.writeBoolean(si.isNotIncBuffDuration());
                dataOutputStream.writeInt(si.getNormalGauge());
                dataOutputStream.writeBoolean(si.isBallSkill());
                dataOutputStream.writeBoolean(si.isSummon());
                dataOutputStream.writeBoolean(si.isFinalAttack());
                dataOutputStream.writeBoolean(si.isPsd());
                dataOutputStream.writeShort(si.getSkillStatInfo().size());
                for (Map.Entry<SkillStat, String> ssEntry : si.getSkillStatInfo().entrySet()) {
                    dataOutputStream.writeUTF(ssEntry.getKey().toString());
                    if (ssEntry.getValue() == null) {
                        dataOutputStream.writeUTF("");
                    } else {
                        dataOutputStream.writeUTF(ssEntry.getValue());
                    }
                }
                dataOutputStream.writeShort(si.getRects().size());
                for (Rect r : si.getRects()) {
                    dataOutputStream.writeInt(r.getLeft());
                    dataOutputStream.writeInt(r.getTop());
                    dataOutputStream.writeInt(r.getRight());
                    dataOutputStream.writeInt(r.getBottom());
                }
                dataOutputStream.writeShort(si.getPsdSkills().size());
                for (int i : si.getPsdSkills()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeShort(si.getReqSkills().size());
                for (Map.Entry<Integer, Integer> reqSkill : si.getReqSkills().entrySet()) {
                    dataOutputStream.writeInt(reqSkill.getKey());
                    dataOutputStream.writeInt(reqSkill.getValue());
                }
                dataOutputStream.writeShort(si.getAddAttackSkills().size());
                for (int i : si.getAddAttackSkills()) {
                    dataOutputStream.writeInt(i);
                }
                dataOutputStream.writeShort(si.getExtraSkillInfo().size());
                for (HashMap<Integer, Integer> extraSkillInfoHashMap : si.getExtraSkillInfo()) {
                    dataOutputStream.writeShort(extraSkillInfoHashMap.size());
                    for (Map.Entry<Integer, Integer> extraSkillInfoHashMapEntry : extraSkillInfoHashMap.entrySet()) {
                        dataOutputStream.writeInt(extraSkillInfoHashMapEntry.getKey());
                        dataOutputStream.writeInt(extraSkillInfoHashMapEntry.getValue());
                    }
                }
                dataOutputStream.writeShort(si.getRandomSkills().size());
                for (Map<Integer, Integer> randomSkillListMap : si.getRandomSkills()) {
                    dataOutputStream.writeShort(randomSkillListMap.size());
                    for (Map.Entry<Integer, Integer> randomSkillMapEntry : randomSkillListMap.entrySet()) {
                        dataOutputStream.writeInt(randomSkillMapEntry.getKey());
                        dataOutputStream.writeInt(randomSkillMapEntry.getValue());
                    }
                }
                dataOutputStream.writeShort(si.getPsdWT().size());
                for (Map.Entry<WeaponType, Map<SkillStat, Double>> psdWTEntry : si.getPsdWT().entrySet()) {
                    dataOutputStream.writeByte(psdWTEntry.getKey().getVal());

                    dataOutputStream.writeShort(psdWTEntry.getValue().size());
                    for (Map.Entry<SkillStat, Double> ssEntry : psdWTEntry.getValue().entrySet()) {
                        dataOutputStream.writeUTF(ssEntry.getKey().toString());
                        dataOutputStream.writeDouble(ssEntry.getValue());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadSkillsOfJob(int job) {
        String dir = ServerConstants.DAT_DIR + "/skills";
        File folder = new File(dir);
        for (File f : folder.listFiles()) {
            if (f.getName().matches(String.format("^%d(.)*", job))) {
                loadSkill(f);
            }
        }
    }

    public static void loadAllSkills() {
        long start = System.currentTimeMillis();
        String dir = ServerConstants.DAT_DIR + "/skills";
        File folder = new File(dir);
        Arrays.stream(folder.listFiles()).parallel().forEach(SkillData::loadSkill);
        log.info(String.format("Loaded %s skills from data files in %dms.", getSkillInfos().size(), System.currentTimeMillis() - start));
    }


    public static void loadSkill(File file) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            SkillInfo skillInfo = new SkillInfo();
            skillInfo.setSkillId(dataInputStream.readInt());
            skillInfo.setRootId(dataInputStream.readInt());
            skillInfo.setMaxLevel(dataInputStream.readInt());
            skillInfo.setMasterLevel(dataInputStream.readInt());
            skillInfo.setFixLevel(dataInputStream.readInt());
            skillInfo.setInvisible(dataInputStream.readBoolean());
            skillInfo.setMassSpell(dataInputStream.readBoolean());
            skillInfo.setType(dataInputStream.readInt());
            skillInfo.setElemAttr(dataInputStream.readUTF());
            skillInfo.setHyper(dataInputStream.readInt());
            skillInfo.setHyperStat(dataInputStream.readInt());
            skillInfo.setVehicleId(dataInputStream.readInt());
            skillInfo.setReqTierPoint(dataInputStream.readInt());
            skillInfo.setNotCooltimeReset(dataInputStream.readBoolean());
            skillInfo.setNotIncBuffDuration(dataInputStream.readBoolean());
            skillInfo.setNormalGauge(dataInputStream.readInt());
            skillInfo.setBallSkill(dataInputStream.readBoolean());
            skillInfo.setSummon(dataInputStream.readBoolean());
            skillInfo.setFinalAttack(dataInputStream.readBoolean());
            skillInfo.setPsd(dataInputStream.readBoolean());
            short ssSize = dataInputStream.readShort();
            for (int j = 0; j < ssSize; j++) {
                skillInfo.addSkillStatInfo(SkillStat.getSkillStatByString(
                        dataInputStream.readUTF()), dataInputStream.readUTF());
            }
            short rectSize = dataInputStream.readShort();
            for (int j = 0; j < rectSize; j++) {
                int left = dataInputStream.readInt();
                int top = dataInputStream.readInt();
                int right = dataInputStream.readInt();
                int bottom = dataInputStream.readInt();
                skillInfo.addRect(new Rect(left, top, right, bottom));
            }
            short psdSize = dataInputStream.readShort();
            for (int j = 0; j < psdSize; j++) {
                skillInfo.addPsdSkill(dataInputStream.readInt());
            }
            short reqSkillSize = dataInputStream.readShort();
            for (int j = 0; j < reqSkillSize; j++) {
                skillInfo.addReqSkill(dataInputStream.readInt(), dataInputStream.readInt());
            }
            short addAttackSize = dataInputStream.readShort();
            for (int j = 0; j < addAttackSize; j++) {
                skillInfo.addAddAttackSkills(dataInputStream.readInt());
            }

            short extraSkillInfoSize = dataInputStream.readShort();
            for (int j = 0; j < extraSkillInfoSize; j++) {
                HashMap<Integer, Integer> extraSkillMap = new HashMap<>();
                short extraSkillMapSize = dataInputStream.readShort();
                for (int k = 0; k < extraSkillMapSize; k++) {
                    int skillId = dataInputStream.readInt();
                    int delay = dataInputStream.readInt();
                    extraSkillMap.put(skillId, delay);
                }
                skillInfo.addExtraSkillInfo(extraSkillMap);
            }

            short randomSkillsSize = dataInputStream.readShort();
            for (int j = 0; j < randomSkillsSize; j++) {
                Map<Integer, Integer> randomSkillsMap = new HashMap<>();
                short randomSkillMapSize = dataInputStream.readShort();
                for (int k = 0; k < randomSkillMapSize; k++) {
                    int randomSkillId = dataInputStream.readInt();
                    int delay = dataInputStream.readInt();
                    randomSkillsMap.put(randomSkillId, delay);
                }
                skillInfo.addRandomSkill(randomSkillsMap);
            }

            short psdWTMapSize = dataInputStream.readShort();
            Map<WeaponType, Map<SkillStat, Double>> psdWTMap = new HashMap<>();
            for (int i = 0; i < psdWTMapSize; i++) {
                WeaponType wt = WeaponType.getByVal(dataInputStream.readByte());
                short skillStatMapSize = dataInputStream.readShort();
                Map<SkillStat, Double> skillStatMap = new HashMap<>();
                for (int j = 0; j < skillStatMapSize; j++) {
                    SkillStat ss = SkillStat.getSkillStatByString(dataInputStream.readUTF());
                    double value = dataInputStream.readDouble();
                    skillStatMap.put(ss, value);
                }
                skillInfo.addPsdWT(wt, skillStatMap);
            }

            getSkillInfos().put(skillInfo.getSkillId(), skillInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSkillsFromWz() {
        String[] wzs = new String[]{"Skill.wz", "Skill001.wz", "Skill002.wz"};
        for (String wz : wzs) {
            String wzDir = ServerConstants.WZ_DIR + "/" + wz;
            File dir = new File(wzDir);
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.getName().contains("Dragon") || file.getName().contains("MobSkill")) {
                    continue;
                }
                Node node = XMLApi.getRoot(file);
                if (node == null) {
                    continue;
                }
                List<Node> nodes = XMLApi.getAllChildren(node);
                for (Node mainNode : nodes) {
                    Map<String, String> attributes = XMLApi.getAttributes(mainNode);
                    String rootIdStr = attributes.get("name").replace(".img", "");
                    int rootId;
                    if (Util.isNumber(rootIdStr)) {
                        rootId = Integer.parseInt(rootIdStr);
                    } else {
                        continue;
                    }
                    Set<String> unkVals = new HashSet<>();
                    Node skillChild = XMLApi.getFirstChildByNameBF(mainNode, "skill");
                    for (Node skillNode : XMLApi.getAllChildren(skillChild)) {
                        Map<String, String> skillAttributes = XMLApi.getAttributes(skillNode);
                        String skillIdStr = skillAttributes.get("name").replace(".img", "");
                        int skillId;
                        if (Util.isNumber(skillIdStr)) {
                            SkillInfo skill = new SkillInfo();
                            skill.setRootId(rootId);
                            if (Util.isNumber(skillIdStr)) {
                                skillId = Integer.parseInt(skillIdStr);
                                skill.setSkillId(skillId);
                            } else {
                                if (LOG_UNKS) {
                                    log.warn(skillIdStr + " is not a number.");
                                }
                                continue;
                            }
                            for (Node mainLevelNode : XMLApi.getAllChildren(skillNode)) {
                                String mainName = XMLApi.getNamedAttribute(mainLevelNode, "name");
                                String mainValue = XMLApi.getNamedAttribute(mainLevelNode, "value");
                                int intVal = -1337;
                                if (mainValue != null && Util.isNumber(mainValue)) {
                                    intVal = Integer.parseInt(mainValue);
                                }
                                switch (mainName) {
                                    case "masterLevel":
                                        skill.setMasterLevel(intVal);
                                        break;
                                    case "fixLevel":
                                        skill.setFixLevel(intVal);
                                        break;
                                    case "invisible":
                                        skill.setInvisible(intVal != 0);
                                        break;
                                    case "massSpell":
                                        skill.setMassSpell(intVal != 0);
                                        break;
                                    case "type":
                                        skill.setType(intVal);
                                        break;
                                    case "psd":
                                        skill.setPsd(intVal != 0);
                                        break;
                                    case "psdSkill":
                                        for (Node psdSkillNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            skill.addPsdSkill(Integer.parseInt(XMLApi.getAttributes(psdSkillNode).get("name")));
                                        }
                                        break;
                                    case "elemAttr":
                                        skill.setElemAttr(mainValue);
                                        break;
                                    case "hyper":
                                        skill.setHyper(intVal);
                                        break;
                                    case "hyperStat":
                                        skill.setHyperStat(intVal);
                                        break;
                                    case "vehicleID":
                                        skill.setVehicleId(intVal);
                                        break;
                                    case "notCooltimeReset":
                                        skill.setNotCooltimeReset(intVal != 0);
                                        break;
                                    case "notIncBuffDuration":
                                        skill.setNotIncBuffDuration(intVal != 0);
                                        break;
                                    case "normalGauge":
                                        skill.setNormalGauge(intVal);
                                        break;
                                    case "ball":
                                        skill.setBallSkill(true);
                                        break;
                                    case "summon":
                                        skill.setSummon(true);
                                        break;
                                    case "req":
                                        for (Node reqChild : XMLApi.getAllChildren(mainLevelNode)) {
                                            String childName = XMLApi.getNamedAttribute(reqChild, "name");
                                            String childValue = XMLApi.getNamedAttribute(reqChild, "value");
                                            if ("reqTierPoint".equalsIgnoreCase(childName)) {
                                                skill.setReqTierPoint(Integer.parseInt(childValue));
                                            } else if (Util.isNumber(childName)) {
                                                skill.addReqSkill(Integer.parseInt(childName), Integer.parseInt(childValue));
                                            }
                                        }
                                        break;
                                    case "common":
                                    case "info":
                                    case "info2":
                                        for (Node commonNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            Map<String, String> commonAttr = XMLApi.getAttributes(commonNode);
                                            String nodeName = commonAttr.get("name");
                                            if (nodeName.equals("maxLevel")) {
                                                skill.setMaxLevel(Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "value")));

                                            } else if (nodeName.equalsIgnoreCase("massSpell")) {
                                                skill.setMassSpell(Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "value")) != 0);

                                            } else if (nodeName.contains("lt") && nodeName.length() <= 3) {
                                                Node rbNode = XMLApi.getFirstChildByNameBF(mainLevelNode, nodeName.replace("lt", "rb"));
                                                int left = Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "x"));
                                                int top = Integer.parseInt(XMLApi.getNamedAttribute(commonNode, "y"));
                                                int right = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "x"));
                                                int bottom = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "y"));
                                                skill.addRect(new Rect(left, top, right, bottom));

                                            } else if (nodeName.equals("finalAttack") && mainName.equals("info")) {
                                                skill.setFinalAttack(true);

                                            } else {
                                                SkillStat skillStat = SkillStat.getSkillStatByString(nodeName);
                                                if (skillStat != null) {
                                                    if (skillStat != SkillStat.dot || mainName.equals("common")) { // There's also a 'dot' nodeName in info/info2 which acts only as a boolean
                                                        skill.addSkillStatInfo(skillStat, commonAttr.get("value"));
                                                    }
                                                } else if (!unkVals.contains(nodeName)) {
                                                    if (LOG_UNKS) {
                                                        log.warn("Unknown SkillStat " + nodeName);
                                                    }
                                                    unkVals.add(nodeName);
                                                }
                                            }
                                        }
                                        break;
                                    case "addAttack":
                                        for (Node addAttackNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            Map<String, String> addAttackAttr = XMLApi.getAttributes(addAttackNode);
                                            String nodeName = addAttackAttr.get("name");
                                            String nodeValue = addAttackAttr.get("value");
                                            switch (nodeName) {
                                                case "skillPlus":
                                                    for (Node skillPlusNode : XMLApi.getAllChildren(addAttackNode)) {
                                                        Map<String, String> skillPlusAttr = XMLApi.getAttributes(skillPlusNode);
                                                        String skillPlusNodeName = skillPlusAttr.get("name");
                                                        String skillPlusNodeValue = skillPlusAttr.get("value");
                                                        skill.addAddAttackSkills(Integer.parseInt(skillPlusNodeValue));
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case "extraSkillInfo":
                                        for (Node extraSkillInfoNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            Map<String, String> extraSkillInfoAttr = XMLApi.getAttributes(extraSkillInfoNode);
                                            int extraSkillDelay = 0;
                                            int extraSkillId = -1;
                                            for (Node extraSkillInfoIndividual : XMLApi.getAllChildren(extraSkillInfoNode)) {
                                                Map<String, String> extraSkillAttr = XMLApi.getAttributes(extraSkillInfoIndividual);
                                                String extraSkillName = extraSkillAttr.get("name");
                                                String extraSkillValue = extraSkillAttr.get("value");
                                                switch (extraSkillName) {
                                                    case "delay":
                                                        extraSkillDelay = Integer.parseInt(extraSkillValue);
                                                        break;
                                                    case "skill":
                                                        extraSkillId = Integer.parseInt(extraSkillValue);
                                                        break;
                                                    default:
                                                        if (LOG_UNKS) {
                                                            log.warn(String.format("Unknown Extra Skill Info Name: %s", extraSkillName));
                                                        }
                                                        break;
                                                }
                                            }
                                            if (extraSkillId > 0) {
                                                skill.addExtraSkillInfo(extraSkillId, extraSkillDelay);
                                            }
                                        }
                                        break;
                                    case "randomSkill":
                                        for (Node randomSkillNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            int randomSkillId = 0;
                                            int delay = 0;
                                            Map<Integer, Integer> randomSkillMap = new HashMap<>();
                                            for (Node randomSkillInfoIndividual : XMLApi.getAllChildren(randomSkillNode)) {
                                                Map<String, String> randomSkillInfoAttr = XMLApi.getAttributes(randomSkillInfoIndividual);
                                                String randomSkillInfoName = randomSkillInfoAttr.get("name");
                                                String randomSkillInfoValue = randomSkillInfoAttr.get("value");
                                                switch (randomSkillInfoName) {
                                                    case "prob":
                                                        // probability is being skipped as it's being made needlessly complicated
                                                        // instead we just grab skills with the same probability.
                                                        break;
                                                    case "skillID":
                                                        randomSkillId = Integer.parseInt(randomSkillInfoValue);
                                                        randomSkillMap.put(randomSkillId, delay);
                                                        break;
                                                    case "skillList":
                                                        for (Node randomSkillInfoSkillList : XMLApi.getAllChildren(randomSkillInfoIndividual)) {
                                                            Map<String, String> extraSkillAttr = XMLApi.getAttributes(randomSkillInfoSkillList);
                                                            String randomSkillListSkillId = extraSkillAttr.get("name");
                                                            String randomSkillListSkillDelay = extraSkillAttr.get("value");
                                                            randomSkillId = Integer.parseInt(randomSkillListSkillId);
                                                            delay = Integer.parseInt(randomSkillListSkillDelay);
                                                            randomSkillMap.put(randomSkillId, delay);
                                                        }
                                                        break;
                                                }
                                            }
                                            if (randomSkillId > 0) {
                                                skill.addRandomSkill(randomSkillMap);
                                            }
                                        }
                                        break;
                                    case "psdWT":
                                        for (Node psdWTNode : XMLApi.getAllChildren(mainLevelNode)) {
                                            Map<String, String> psdWTAttr = XMLApi.getAttributes(psdWTNode);
                                            String weaponType = psdWTAttr.get("name");
                                            WeaponType wt = WeaponType.getByVal(Integer.parseInt(weaponType));
                                            if (wt == null) {
                                                log.error(String.format("Unknown WeaponType %s, in SkillID: %d", weaponType, skillId));
                                                continue;
                                            }
                                            Map<SkillStat, Double> skillStatMap = new HashMap<>();
                                            for (Node skillStatNode : XMLApi.getAllChildren(psdWTNode)) {
                                                Map<String, String> skillStatAttr = XMLApi.getAttributes(skillStatNode);
                                                String skillStatStr = skillStatAttr.get("name");
                                                String skillStatValue = skillStatAttr.get("value");
                                                SkillStat skillStat = SkillStat.getSkillStatByString(skillStatStr);
                                                if (skillStat == null) {
                                                    log.error(String.format("Unknown psdWT SkillStat: %s, in SkillID: %d", skillStatStr, skillId));
                                                    continue;
                                                }
                                                if (!Util.isNumber(skillStatValue)) {
                                                    log.error(String.format("Unknown psdWT SkillStat Value: %s, in SkillID: %d", skillStatStr, skillId));
                                                    continue;
                                                }
                                                skillStatMap.put(skillStat, Double.parseDouble(skillStatValue));
                                            }
                                            skill.addPsdWT(wt, skillStatMap);
                                        }
                                        break;
                                }
                            }
                            skills.put(skillId, skill);
                        }
                    }
                }
            }
        }
    }

    public static Map<Integer, SkillInfo> getSkillInfos() {
        return skills;
    }

    public static SkillInfo getSkillInfoById(int skillId) {
        if (!getSkillInfos().containsKey(skillId)) {
            File file = new File(String.format("%s/skills/%d.dat", ServerConstants.DAT_DIR, skillId));
            if (file.exists()) {
                loadSkill(file);
            }
        }
        return getSkillInfos().get(skillId);

    }

    public static Skill getSkillDeepCopyById(int skillId) {
        SkillInfo si = getSkillInfoById(skillId);
        if (si == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setSkillId(si.getSkillId());
        skill.setRootId(si.getRootId());
        skill.setMasterLevel(si.getMaxLevel());
//        skill.setMasterLevel(si.getMasterLevel()); // for now, maybe always?
        skill.setMaxLevel(si.getMaxLevel());
        if (si.getMasterLevel() <= 0) {
            skill.setMasterLevel(skill.getMaxLevel());
            //skill.setCurrentLevel(0);
        }
        if (si.getFixLevel() > 0) {
            skill.setCurrentLevel(si.getFixLevel());
        } else {
            skill.setCurrentLevel(0);
        }
        return skill;
    }

    public static List<Skill> getSkillsByJob(short id) {
        return getSkillsByJob(id, false);
    }

    private static List<Skill> getSkillsByJob(short id, boolean rec) {
        List<Skill> res = new ArrayList<>();
        getSkillInfos().forEach((key, si) -> {
            if (si.getRootId() == id && !si.isInvisible()) {
                res.add(getSkillDeepCopyById(key));
            }
        });
        if (!rec && res.size() == 0) {
            loadSkillsOfJob(id);
            return getSkillsByJob(id, true);
        }
        return res;
    }


    public static Map<Short, Map<Short, MobSkillInfo>> getMobSkillInfos() {
        return mobSkillInfos;
    }

    public static Map<Integer, MakingSkillRecipe> getMakingSkillRecipes() {
        return makingSkillRecipes;
    }

    public static MakingSkillRecipe getRecipeById(int recipeID) {
        if (!getMakingSkillRecipes().containsKey(recipeID)) {
            File file = new File(String.format("%s/recipes/%d.dat", ServerConstants.DAT_DIR, recipeID));
            if (file.exists()) {
                loadRecipe(file);
            }
        }
        return getMakingSkillRecipes().get(recipeID);

    }

    public static void addMobSkillInfo(MobSkillInfo msi) {
        getMobSkillInfos().computeIfAbsent(msi.getId(), k -> new HashMap<>());
        Map<Short, MobSkillInfo> msiLevelMap = getMobSkillInfos().get(msi.getId());
        msiLevelMap.put(msi.getLevel(), msi);
        getMobSkillInfos().put(msi.getId(), msiLevelMap);
    }

    private static void loadEliteMobSkillsFromWZ() {
        String wzDir = ServerConstants.WZ_DIR + "/Skill.wz/EliteMobSkill.img.xml";
        File file = new File(wzDir);
        Node root = XMLApi.getRoot(file);
        Node mainNode = XMLApi.getAllChildren(root).get(0);
        List<Node> nodes = XMLApi.getAllChildren(mainNode);
        for (Node node : nodes) {
            int grade = Integer.parseInt(XMLApi.getNamedAttribute(node, "name"));
            for (Node skillNode : XMLApi.getAllChildren(node)) {
                Node skillIdNode = XMLApi.getFirstChildByNameBF(skillNode, "skill");
                Node skillLevelNode = XMLApi.getFirstChildByNameBF(skillNode, "level");
                int skillID = Integer.parseInt(XMLApi.getNamedAttribute(skillIdNode, "value"));
                int skillLevel = Integer.parseInt(XMLApi.getNamedAttribute(skillLevelNode, "value"));
                addEliteMobSkill(grade, skillID, skillLevel);
            }
        }
    }

    private static void addEliteMobSkill(int grade, int skillID, int skillLevel) {
        if (!eliteMobSkills.containsKey(grade)) {
            eliteMobSkills.put(grade, new HashMap<>());
        }
        eliteMobSkills.get(grade).put(skillID, skillLevel);
    }

    @Saver(varName = "eliteMobSkills")
    private static void saveEliteMobSkills(File file) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(eliteMobSkills.size());
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : eliteMobSkills.entrySet()) {
                dos.writeInt(entry.getKey()); // grade
                dos.writeInt(entry.getValue().size());
                for (Map.Entry<Integer, Integer> innerEntry : eliteMobSkills.get(entry.getKey()).entrySet()) {
                    dos.writeInt(innerEntry.getKey()); // skillID
                    dos.writeInt(innerEntry.getValue()); // skillLevel
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Loader(varName = "eliteMobSkills")
    public static void loadEliteMobSkills(File file, boolean exists) {
        if (!exists) {
            loadEliteMobSkillsFromWZ();
            saveEliteMobSkills(file);
        } else {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                int gradeSize = dis.readInt();
                for (int i = 0; i < gradeSize; i++) {
                    int grade = dis.readInt();
                    int gradeSkillSize = dis.readInt();
                    for (int j = 0; j < gradeSkillSize; j++) {
                        int skillID = dis.readInt();
                        int skillLevel = dis.readInt();
                        addEliteMobSkill(grade, skillID, skillLevel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Integer, Integer> getEliteMobSkillsByGrade(int grade) {
        return eliteMobSkills.getOrDefault(grade, null);
    }

    public static void loadMobSkillsFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Skill001.wz/MobSkill";
        File topFile = new File(wzDir);
        for (File file : topFile.listFiles()) {
            short skillID = Short.parseShort(file.getName().replace(".img.xml", ""));
            Node root = XMLApi.getRoot(file);
            Node mainNode = XMLApi.getAllChildren(root).get(0);
            Node litLevelNode = XMLApi.getFirstChildByNameBF(mainNode, "level");
            List<Node> nodes = XMLApi.getAllChildren(litLevelNode);
            Set<String> unks = new HashSet<>();
            for (Node levelNode : nodes) {
                short level = Short.parseShort(XMLApi.getNamedAttribute(levelNode, "name"));
                MobSkillInfo msi = new MobSkillInfo();
                msi.setId(skillID);
                msi.setLevel(level);
                for (Node skillStatNode : XMLApi.getAllChildren(levelNode)) {
                    String name = XMLApi.getNamedAttribute(skillStatNode, "name");
                    String value = XMLApi.getNamedAttribute(skillStatNode, "value");
                    String x = XMLApi.getNamedAttribute(skillStatNode, "x");
                    String y = XMLApi.getNamedAttribute(skillStatNode, "y");
                    if (Util.isNumber(name)) {
                        msi.addIntToList(Integer.parseInt(value));
                        continue;
                    }
                    switch (name) {
                        case "x":
                            msi.putMobSkillStat(MobSkillStat.x, value);
                            break;
                        case "mpCon":
                            msi.putMobSkillStat(MobSkillStat.mpCon, value);
                            break;
                        case "interval":
                        case "inteval":
                            msi.putMobSkillStat(MobSkillStat.interval, value);
                            break;
                        case "hp":
                        case "HP":
                            msi.putMobSkillStat(MobSkillStat.hp, value);
                            break;
                        case "info":
                            msi.putMobSkillStat(MobSkillStat.info, value);
                            break;
                        case "y":
                            msi.putMobSkillStat(MobSkillStat.y, value);
                            break;
                        case "lt":
                            msi.setLt(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb":
                            msi.setRb(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "lt2":
                            msi.setLt2(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb2":
                            msi.setRb2(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "lt3":
                            msi.setLt3(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "rb3":
                            msi.setRb3(new Position(Integer.parseInt(x), Integer.parseInt(y)));
                            break;
                        case "limit":
                            msi.putMobSkillStat(MobSkillStat.limit, value);
                            break;
                        case "broadCastScreenMsg":
                            msi.putMobSkillStat(MobSkillStat.broadCastScreenMsg, value);
                            break;
                        case "w":
                            msi.putMobSkillStat(MobSkillStat.w, value);
                            break;
                        case "z":
                            msi.putMobSkillStat(MobSkillStat.z, value);
                            break;
                        case "parsing":
                            msi.putMobSkillStat(MobSkillStat.parsing, value);
                            break;
                        case "prop":
                            msi.putMobSkillStat(MobSkillStat.prop, value);
                            break;
                        case "ignoreResist":
                            msi.putMobSkillStat(MobSkillStat.ignoreResist, value);
                            break;
                        case "count":
                            msi.putMobSkillStat(MobSkillStat.count, value);
                            break;
                        case "time":
                            msi.putMobSkillStat(MobSkillStat.time, value);
                            break;
                        case "targetAggro":
                            msi.putMobSkillStat(MobSkillStat.targetAggro, value);
                            break;
                        case "fieldScript":
                            msi.putMobSkillStat(MobSkillStat.fieldScript, value);
                            break;
                        case "elemAttr":
                            msi.putMobSkillStat(MobSkillStat.elemAttr, value);
                            break;
                        case "delay":
                            msi.putMobSkillStat(MobSkillStat.delay, value);
                            break;
                        case "rank":
                            msi.putMobSkillStat(MobSkillStat.rank, value);
                            break;
                        case "HPDeltaR":
                            msi.putMobSkillStat(MobSkillStat.HPDeltaR, value);
                            break;
                        case "summonEffect":
                            msi.putMobSkillStat(MobSkillStat.summonEffect, value);
                            break;
                        case "y2":
                            msi.putMobSkillStat(MobSkillStat.y2, value);
                            break;
                        case "q":
                            msi.putMobSkillStat(MobSkillStat.q, value);
                            break;
                        case "q2":
                            msi.putMobSkillStat(MobSkillStat.q2, value);
                            break;
                        case "s2":
                            msi.putMobSkillStat(MobSkillStat.s2, value);
                            break;
                        case "u":
                            msi.putMobSkillStat(MobSkillStat.u, value);
                            break;
                        case "u2":
                            msi.putMobSkillStat(MobSkillStat.u2, value);
                            break;
                        case "v":
                            msi.putMobSkillStat(MobSkillStat.v, value);
                            break;
                        case "z2":
                            msi.putMobSkillStat(MobSkillStat.z2, value);
                            break;
                        case "w2":
                            msi.putMobSkillStat(MobSkillStat.w2, value);
                            break;
                        case "skillAfter":
                            msi.putMobSkillStat(MobSkillStat.skillAfter, value);
                            break;
                        case "x2":
                            msi.putMobSkillStat(MobSkillStat.x2, value);
                            break;
                        case "script":
                            msi.putMobSkillStat(MobSkillStat.script, value);
                            break;
                        case "attackSuccessProp":
                            msi.putMobSkillStat(MobSkillStat.attackSuccessProp, value);
                            break;
                        case "bossHeal":
                            msi.putMobSkillStat(MobSkillStat.bossHeal, value);
                            break;
                        case "face":
                            msi.putMobSkillStat(MobSkillStat.face, value);
                            break;
                        case "callSkill":
                            msi.putMobSkillStat(MobSkillStat.callSkill, value);
                            break;
                        case "level":
                            msi.putMobSkillStat(MobSkillStat.level, value);
                            break;
                        case "linkHP":
                            msi.putMobSkillStat(MobSkillStat.linkHP, value);
                            break;
                        case "timeLimitedExchange":
                            msi.putMobSkillStat(MobSkillStat.timeLimitedExchange, value);
                            break;
                        case "summonDir":
                            msi.putMobSkillStat(MobSkillStat.summonDir, value);
                            break;
                        case "summonTerm":
                            msi.putMobSkillStat(MobSkillStat.summonTerm, value);
                            break;
                        case "castingTime":
                            msi.putMobSkillStat(MobSkillStat.castingTime, value);
                            break;
                        case "subTime":
                            msi.putMobSkillStat(MobSkillStat.subTime, value);
                            break;
                        case "reduceCasting":
                            msi.putMobSkillStat(MobSkillStat.reduceCasting, value);
                            break;
                        case "additionalTime":
                            msi.putMobSkillStat(MobSkillStat.additionalTime, value);
                            break;
                        case "force":
                            msi.putMobSkillStat(MobSkillStat.force, value);
                            break;
                        case "targetType":
                            msi.putMobSkillStat(MobSkillStat.targetType, value);
                            break;
                        case "forcex":
                            msi.putMobSkillStat(MobSkillStat.forcex, value);
                            break;
                        case "sideAttack":
                            msi.putMobSkillStat(MobSkillStat.sideAttack, value);
                            break;
                        case "afterEffect":
                        case "rangeGap":
                            msi.putMobSkillStat(MobSkillStat.rangeGap, value);
                            break;
                        case "noGravity":
                            msi.putMobSkillStat(MobSkillStat.noGravity, value);
                            break;
                        case "notDestroyByCollide":
                            msi.putMobSkillStat(MobSkillStat.notDestroyByCollide, value);
                            break;
                        case "effect":
                        case "mob":
                        case "mob0":
                        case "hit":
                        case "affected":
                        case "affectedOtherSkill":
                        case "crash":
                        case "effectToUser":
                        case "affected_after":
                        case "fixDamR":
                        case "limitMoveSkill":
                        case "tile":
                        case "footholdRect":
                        case "targetMobType":
                        case "areaWarning":
                        case "arType":
                        case "tremble":
                        case "otherSkill":
                        case "etcEffect":
                        case "etcEffect1":
                        case "etcEffect2":
                        case "etcEffect3":
                        case "bombInfo":
                        case "affected_pre":
                        case "fixDamR_BT":
                        case "affectedPhase":
                        case "screen":
                        case "notMissAttack":
                        case "ignoreEvasion":
                        case "fadeinfo":
                        case "randomTarget":
                        case "option_linkedMob":
                        case "affected0":
                        case "summonOnce":
                        case "head":
                        case "mobGroup":
                        case "exceptRange":
                        case "exchangeAttack":
                        case "range":
                        case "addDam":
                        case "special":
                        case "target":
                        case "fixedPos":
                        case "fixedDir":
                        case "i52":
                        case "start":
                        case "cancleType":
                        case "succeed":
                        case "failed":
                        case "during":
                        case "castingBarHide":
                        case "skillCancelAlways":
                        case "cancleDamage":
                        case "cancleDamageMultiplier":
                        case "bounceBall":
                        case "info2":
                        case "regen":
                        case "kockBackD":
                        case "areaSequenceDelay":
                        case "areaSequenceRandomSplit":
                        case "accelerationEffect":
                        case "repeatEffect":
                        case "brightness":
                        case "brightnessDuration":
                        case "success":
                        case "fail":
                        case "affected_S":
                        case "appear":
                        case "affected_XS":
                        case "disappear":
                        case "command":
                        case "damIncPos": // May be useful
                        case "option_poison": // ?
                        case "phaseUserCount": // I think this is done client side (users hit mapped to phase?)
                            break;
                        default:
                            if (!unks.contains(name)) {
                                if (LOG_UNKS) {
                                    log.warn(String.format("Unkown MobSkillStat %s with value %s (skill %d level %d)", name, value, skillID, level));
                                }
                                unks.add(name);
                            }
                            break;
                    }
                }
                addMobSkillInfo(msi);
            }
        }

    }

    public static void saveMobSkillsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for (Map.Entry<Short, Map<Short, MobSkillInfo>> entry : getMobSkillInfos().entrySet()) {
            short id = entry.getKey();
            for (Map.Entry<Short, MobSkillInfo> entry2 : entry.getValue().entrySet()) {
                short level = entry2.getKey();
                MobSkillInfo msi = entry2.getValue();
                File file = new File(String.format("%s/%d-%d.dat", dir, id, level));
                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                    dos.writeShort(msi.getId());
                    dos.writeShort(msi.getLevel());
                    // wow this is ugly
                    // but it's just a de/encoder, so who cares
                    boolean hasLt = msi.getLt() != null;
                    dos.writeBoolean(hasLt);
                    if (hasLt) {
                        dos.writeInt(msi.getLt().getX());
                        dos.writeInt(msi.getLt().getY());
                    }
                    boolean hasRb = msi.getRb() != null;
                    dos.writeBoolean(hasRb);
                    if (hasRb) {
                        dos.writeInt(msi.getRb().getX());
                        dos.writeInt(msi.getRb().getY());
                    }
                    boolean hasLt2 = msi.getLt2() != null;
                    dos.writeBoolean(hasLt2);
                    if (hasLt2) {
                        dos.writeInt(msi.getLt2().getX());
                        dos.writeInt(msi.getLt2().getY());
                    }
                    boolean hasRb2 = msi.getRb2() != null;
                    dos.writeBoolean(hasRb2);
                    if (hasRb2) {
                        dos.writeInt(msi.getRb2().getX());
                        dos.writeInt(msi.getRb2().getY());
                    }
                    boolean hasLt3 = msi.getLt3() != null;
                    dos.writeBoolean(hasLt3);
                    if (hasLt3) {
                        dos.writeInt(msi.getLt3().getX());
                        dos.writeInt(msi.getLt3().getY());
                    }
                    boolean hasRb3 = msi.getRb3() != null;
                    dos.writeBoolean(hasRb3);
                    if (hasRb3) {
                        dos.writeInt(msi.getRb3().getX());
                        dos.writeInt(msi.getRb3().getY());
                    }
                    dos.writeShort(msi.getMobSkillStats().size());
                    for (Map.Entry<MobSkillStat, String> msiString : msi.getMobSkillStats().entrySet()) {
                        dos.writeByte(msiString.getKey().ordinal());
                        dos.writeUTF(msiString.getValue());
                    }
                    dos.writeShort(msi.getInts().size());
                    for (int i : msi.getInts()) {
                        dos.writeInt(i);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static MobSkillInfo loadMobSkillFromFile(File f) {
        MobSkillInfo msi = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
            msi = new MobSkillInfo();
            msi.setId(dis.readShort());
            msi.setLevel(dis.readShort());
            boolean hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setLt(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setRb(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setLt2(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setRb2(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setLt3(new Position(dis.readInt(), dis.readInt()));
            }
            hasPos = dis.readBoolean();
            if (hasPos) {
                msi.setRb3(new Position(dis.readInt(), dis.readInt()));
            }
            short size = dis.readShort();
            for (int i = 0; i < size; i++) {
                MobSkillStat mss = MobSkillStat.values()[dis.readByte()];
                String value = dis.readUTF();
                msi.putMobSkillStat(mss, value);
            }
            size = dis.readShort();
            for (int i = 0; i < size; i++) {
                msi.addIntToList(dis.readInt());
            }
            addMobSkillInfo(msi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msi;
    }

    private static MobSkillInfo getMobSkillInfoByIdAndLevel(short id, short level) {
        Map<Short, MobSkillInfo> innerMap = getMobSkillInfos().get(id);
        if (innerMap == null || innerMap.get(level) == null) {
            return loadMobSkillInfoByIdAndLevel(id, level);
        } else {
            return innerMap.get(level);
        }
    }

    public static MobSkillInfo getMobSkillInfoByIdAndLevel(int id, int level) {
        return getMobSkillInfoByIdAndLevel((short) id, (short) level);
    }

    private static MobSkillInfo loadMobSkillInfoByIdAndLevel(short id, short level) {
        File file = new File(String.format("%s/mobSkills/%d-%d.dat", ServerConstants.DAT_DIR, id, level));
        if (file.exists()) {
            return loadMobSkillFromFile(file);
        } else {
            log.error(String.format("Could not load mob skill %d (level %d).", id, level));
            return null;
        }
    }

    public static void loadMakingRecipeSkillsFromWz() {
        int[] recipes = {9200, 9201, 9202, 9203, 9204};
        for (Integer recipeCategory : recipes) {
            String wzDir = String.format(ServerConstants.WZ_DIR + "/Skill.wz/Recipe_%d.img.xml", recipeCategory);
            File file = new File(wzDir);
            Node root = XMLApi.getRoot(file);
            Node mainNode = XMLApi.getAllChildren(root).get(0);
            List<Node> nodes = XMLApi.getAllChildren(mainNode);
            for (Node node : nodes) {
                MakingSkillRecipe msr = new MakingSkillRecipe();
                int recipeID = Integer.parseInt(XMLApi.getNamedAttribute(node, "name"));
                msr.setRecipeID(recipeID);
                msr.setReqSkillID(10000 * (recipeID / 10000));
                for (Node recipe : XMLApi.getAllChildren(node)) {
                    String name = XMLApi.getNamedAttribute(recipe, "name");
                    String value = XMLApi.getNamedAttribute(recipe, "value");
                    switch (name) {
                        case "target":
                            for (Node targets : XMLApi.getAllChildren(recipe)) {
                                MakingSkillRecipe.TargetElem tar = new MakingSkillRecipe.TargetElem();
                                for (Node target : XMLApi.getAllChildren(targets)) {
                                    String targetName = XMLApi.getNamedAttribute(target, "name");
                                    int targetValue = Integer.parseInt(XMLApi.getNamedAttribute(target, "value"));
                                    switch (targetName) {
                                        case "item":
                                            tar.setItemID(targetValue);
                                            break;
                                        case "count":
                                            tar.setCount(targetValue);
                                            break;
                                        case "probWeight":
                                            tar.setProbWeight(targetValue);
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn("Unknown target value " + targetName);
                                            }
                                            break;
                                    }
                                }
                                msr.addTarget(tar);
                            }
                            break;
                        case "weatherItem":
                            msr.setWeatherItemID(Integer.parseInt(value));
                            break;
                        case "incSkillProficiency":
                            msr.setIncSkillProficiency(Integer.parseInt(value));
                            break;
                        case "incSkillProficiencyOnFailure":
                            msr.setIncSkillProficiencyOnFailure(Integer.parseInt(value));
                            break;
                        case "incSkillMasterProficiency":
                            msr.setIncSkillMasterProficiency(Integer.parseInt(value));
                            break;
                        case "incSkillMasterProficiencyOnFailure":
                            msr.setIncSkillMasterProficiencyOnFailure(Integer.parseInt(value));
                            break;
                        case "incFatigability":
                            msr.setIncFatigability(Integer.parseInt(value));
                            break;
                        case "addedCoolProb":
                            msr.setAddedCoolProb(Integer.parseInt(value));
                            break;
                        case "coolTimeSec":
                            msr.setCoolTimeSec(Integer.parseInt(value));
                            break;
                        case "addedTimeTaken":
                            msr.setAddedSecForMaxGauge(Integer.parseInt(value));
                            break;
                        case "period":
                            msr.setExpiredPeriod(Integer.parseInt(value));
                            break;
                        case "premium":
                            msr.setPremiumItem(Integer.parseInt(value) != 0);
                            break;
                        case "needOpenItem":
                            msr.setNeedOpenItem(Integer.parseInt(value) != 0);
                            break;
                        case "reqSkillLevel":
                            msr.setRecommandedSkillLevel(Integer.parseInt(value));
                            break;
                        case "reqSkillProficiency":
                            msr.setReqSkillProficiency(Integer.parseInt(value));
                            break;
                        case "reqMeso":
                            msr.setReqMeso(Integer.parseInt(value));
                            break;
                        case "reqMapObjectTag":
                            msr.setReqMapObjectTag(value);
                            break;
                        case "recipe":
                            for (Node ingredients : XMLApi.getAllChildren(recipe)) {
                                int itemID = -1, count = -1;
                                for (Node ingredient : XMLApi.getAllChildren(ingredients)) {
                                    String ingredientName = XMLApi.getNamedAttribute(ingredient, "name");
                                    int ingredientValue = Integer.parseInt(XMLApi.getNamedAttribute(ingredient, "value"));
                                    switch (ingredientName) {
                                        case "item":
                                            itemID = ingredientValue;
                                            break;
                                        case "count":
                                            count = ingredientValue;
                                            break;
                                        default:
                                            if (LOG_UNKS) {
                                                log.warn("Unknown ingredient value " + ingredientName);
                                            }
                                            break;
                                    }
                                }
                                if (itemID != -1 && count != -1) {
                                    msr.addIngredient(itemID, count);
                                }
                            }
                            break;
                        default:
                            if (LOG_UNKS) {
                                log.warn("Unknown recipe value " + name);
                            }
                            break;
                    }
                }
                makingSkillRecipes.put(recipeID, msr);
            }
        }
    }

    public static void saveMakingRecipeSkillsToDat(String dir) {
        Util.makeDirIfAbsent(dir);
        for (MakingSkillRecipe msr : getMakingSkillRecipes().values()) {
            int recipeID = msr.getRecipeID();
            File file = new File(String.format("%s/%d.dat", dir, recipeID));
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeInt(recipeID);
                dos.writeShort(msr.getTarget().size());
                for (MakingSkillRecipe.TargetElem target : msr.getTarget()) {
                    dos.writeInt(target.getItemID());
                    dos.writeInt(target.getCount());
                    dos.writeInt(target.getProbWeight());
                }
                dos.writeInt(msr.getWeatherItemID());
                dos.writeInt(msr.getIncSkillProficiency());
                dos.writeInt(msr.getIncSkillProficiencyOnFailure());
                dos.writeInt(msr.getIncFatigability());
                dos.writeInt(msr.getIncSkillMasterProficiency());
                dos.writeInt(msr.getIncSkillMasterProficiencyOnFailure());
                dos.writeBoolean(msr.isNeedOpenItem());
                dos.writeInt(msr.getReqSkillID());
                dos.writeInt(msr.getRecommandedSkillLevel());
                dos.writeInt(msr.getReqSkillProficiency());
                dos.writeInt(msr.getReqMeso());
                dos.writeUTF(msr.getReqMapObjectTag());
                dos.writeShort(msr.getIngredient().size());
                for (Tuple<Integer, Integer> ingredient : msr.getIngredient()) {
                    dos.writeInt(ingredient.getLeft());
                    dos.writeInt(ingredient.getRight());
                }
                dos.writeInt(msr.getAddedCoolProb());
                dos.writeInt(msr.getCoolTimeSec());
                dos.writeInt(msr.getAddedSecForMaxGauge());
                dos.writeInt(msr.getExpiredPeriod());
                dos.writeBoolean(msr.isPremiumItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadRecipe(File file) {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            MakingSkillRecipe msr = new MakingSkillRecipe();
            msr.setRecipeID(dataInputStream.readInt());
            short targets = dataInputStream.readShort();
            for (int j = 0; j < targets; j++) {
                MakingSkillRecipe.TargetElem target = new MakingSkillRecipe.TargetElem();
                target.setItemID(dataInputStream.readInt());
                target.setCount(dataInputStream.readInt());
                target.setProbWeight(dataInputStream.readInt());
                msr.addTarget(target);
            }
            msr.setWeatherItemID(dataInputStream.readInt());
            msr.setIncSkillProficiency(dataInputStream.readInt());
            msr.setIncSkillProficiencyOnFailure(dataInputStream.readInt());
            msr.setIncFatigability(dataInputStream.readInt());
            msr.setIncSkillMasterProficiency(dataInputStream.readInt());
            msr.setIncSkillMasterProficiencyOnFailure(dataInputStream.readInt());
            msr.setNeedOpenItem(dataInputStream.readBoolean());
            msr.setReqSkillID(dataInputStream.readInt());
            msr.setRecommandedSkillLevel(dataInputStream.readInt());
            msr.setReqSkillProficiency(dataInputStream.readInt());
            msr.setReqMeso(dataInputStream.readInt());
            msr.setReqMapObjectTag(dataInputStream.readUTF());
            short ingredients = dataInputStream.readShort();
            for (int j = 0; j < ingredients; j++) {
                msr.addIngredient(dataInputStream.readInt(), dataInputStream.readInt());
            }
            msr.setAddedCoolProb(dataInputStream.readInt());
            msr.setCoolTimeSec(dataInputStream.readInt());
            msr.setAddedSecForMaxGauge(dataInputStream.readInt());
            msr.setExpiredPeriod(dataInputStream.readInt());
            msr.setPremiumItem(dataInputStream.readBoolean());
            getMakingSkillRecipes().put(msr.getRecipeID(), msr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateDatFiles() {
        log.info("Started generating skill data.");
        long start = System.currentTimeMillis();
        loadSkillsFromWz();
        saveSkills(ServerConstants.DAT_DIR + "/skills");
        log.info(String.format("Completed generating skill data in %dms.", System.currentTimeMillis() - start));
        log.info("Started generating mob skill data.");
        start = System.currentTimeMillis();
        loadMobSkillsFromWz();
        saveMobSkillsToDat(ServerConstants.DAT_DIR + "/mobSkills");
        log.info(String.format("Completed generating mob skill data in %dms.", System.currentTimeMillis() - start));
        log.info("Started generating recipe skill data.");
        start = System.currentTimeMillis();
        loadMakingRecipeSkillsFromWz();
        saveMakingRecipeSkillsToDat(ServerConstants.DAT_DIR + "/recipes");
        log.info(String.format("Completed generating recipe skill data in %dms.", System.currentTimeMillis() - start));

    }
    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getSkillInfos().clear();
        getMobSkillInfos().clear();
        loadAllSkills();
    }
}
