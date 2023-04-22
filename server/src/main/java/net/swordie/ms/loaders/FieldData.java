package net.swordie.ms.loaders;

import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.FieldType;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.world.field.*;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.ServerConstants;
import org.apache.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.*;

/**
 * Created on 12/21/2017.
 */
public class FieldData {

    private static List<Field> fields = new ArrayList<>();
    private static List<Integer> worldMapFields = new ArrayList<>();
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private static final boolean LOG_UNKS = false;

    public static void main(String[] args) {
        generateDatFiles();
    }

    private static void saveFields(String dir) {
        Util.makeDirIfAbsent(dir);
        for (Field field : getFields()) {
            File file = new File(String.format("%s/%d.dat", dir, field.getId()));
            try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
                dataOutputStream.writeInt(field.getId());
                dataOutputStream.writeInt(field.getFieldType().getVal());
                dataOutputStream.writeBoolean(field.isTown());
                dataOutputStream.writeBoolean(field.isSwim());
                dataOutputStream.writeLong(field.getFieldLimit());
                dataOutputStream.writeInt(field.getReturnMap());
                dataOutputStream.writeInt(field.getForcedReturn());
                dataOutputStream.writeDouble(field.getMobRate());
                dataOutputStream.writeBoolean(field.isFly());
                dataOutputStream.writeUTF(field.getOnFirstUserEnter());
                dataOutputStream.writeUTF(field.getOnUserEnter());
                dataOutputStream.writeUTF(field.getFieldScript());
                dataOutputStream.writeBoolean(field.isReactorShuffle());
                dataOutputStream.writeBoolean(field.isExpeditionOnly());
                dataOutputStream.writeBoolean(field.isPartyOnly());
                dataOutputStream.writeBoolean(field.isNeedSkillForFly());
                dataOutputStream.writeInt(field.getFixedMobCapacity());
                dataOutputStream.writeInt(field.getCreateMobInterval());
                dataOutputStream.writeInt(field.getTimeOut());
                dataOutputStream.writeInt(field.getTimeLimit());
                dataOutputStream.writeInt(field.getLvLimit());
                dataOutputStream.writeInt(field.getLvForceMove());
                dataOutputStream.writeInt(field.getConsumeItemCoolTime());
                dataOutputStream.writeInt(field.getLink());
                dataOutputStream.writeInt(field.getBossMobID());
                dataOutputStream.writeInt(field.getVrTop());
                dataOutputStream.writeInt(field.getVrLeft());
                dataOutputStream.writeInt(field.getVrBottom());
                dataOutputStream.writeInt(field.getVrRight());
                dataOutputStream.writeInt(field.getBarrier());
                dataOutputStream.writeInt(field.getBarrierArc());

                dataOutputStream.writeBoolean(field.isReviveCurField());
                dataOutputStream.writeBoolean(field.isReviveCurFieldOfNoTransfer());
                Position revivePoint = field.getReviveCurFieldOfNoTransferPoint();
                dataOutputStream.writeBoolean(revivePoint != null);
                if (revivePoint != null) {
                    dataOutputStream.writeShort(revivePoint.getX());
                    dataOutputStream.writeShort(revivePoint.getY());
                }


                dataOutputStream.writeShort(field.getFootholds().size());
                for (Foothold fh : field.getFootholds()) {
                    dataOutputStream.writeInt(fh.getId());
                    dataOutputStream.writeInt(fh.getLayerId());
                    dataOutputStream.writeInt(fh.getGroupId());
                    dataOutputStream.writeInt(fh.getX1());
                    dataOutputStream.writeInt(fh.getY1());
                    dataOutputStream.writeInt(fh.getX2());
                    dataOutputStream.writeInt(fh.getY2());
                    dataOutputStream.writeInt(fh.getNext());
                    dataOutputStream.writeInt(fh.getPrev());
                    dataOutputStream.writeInt(fh.getForce());
                    dataOutputStream.writeBoolean(fh.isForbidFallDown());
                }
                dataOutputStream.writeShort(field.getPortals().size());
                for (Portal p : field.getPortals()) {
                    dataOutputStream.writeInt(p.getId());
                    dataOutputStream.writeInt(p.getType().getVal());
                    dataOutputStream.writeUTF(p.getName());
                    dataOutputStream.writeInt(p.getTargetMapId());
                    dataOutputStream.writeUTF(p.getTargetPortalName());
                    dataOutputStream.writeInt(p.getX());
                    dataOutputStream.writeInt(p.getY());
                    dataOutputStream.writeInt(p.getHorizontalImpact());
                    dataOutputStream.writeInt(p.getVerticalImpact());
                    dataOutputStream.writeUTF(p.getScript());
                    dataOutputStream.writeBoolean(p.isOnlyOnce());
                    dataOutputStream.writeInt(p.getDelay());
                }
                dataOutputStream.writeShort(field.getLifes().size());
                for (Life l : field.getLifes().values()) {
                    dataOutputStream.writeUTF(l.getLifeType());
                    dataOutputStream.writeInt(l.getTemplateId());
                    dataOutputStream.writeInt(l.getX());
                    dataOutputStream.writeInt(l.getY());
                    dataOutputStream.writeInt(l.getMobTime());
                    dataOutputStream.writeBoolean(l.isFlip());
                    dataOutputStream.writeBoolean(l.isHide());
                    dataOutputStream.writeInt(l.getFh());
                    dataOutputStream.writeInt(l.getCy());
                    dataOutputStream.writeInt(l.getRx0());
                    dataOutputStream.writeInt(l.getRx1());
                    dataOutputStream.writeUTF(l.getLimitedName());
                    dataOutputStream.writeBoolean(l.isUseDay());
                    dataOutputStream.writeBoolean(l.isUseNight());
                    dataOutputStream.writeBoolean(l.isHold());
                    dataOutputStream.writeBoolean(l.isNoFoothold());
                    dataOutputStream.writeBoolean(l.isDummy());
                    dataOutputStream.writeBoolean(l.isSpine());
                    dataOutputStream.writeBoolean(l.isMobTimeOnDie());
                    dataOutputStream.writeInt(l.getRegenStart());
                    dataOutputStream.writeInt(l.getMobAliveReq());
                }
                dataOutputStream.writeShort(field.getDirectionInfo().size());
                for (Map.Entry<Integer, List<String>> direction : field.getDirectionInfo().entrySet()) {
                    dataOutputStream.writeInt(direction.getKey());
                    dataOutputStream.writeShort(direction.getValue().size());
                    for (String directionInfo : direction.getValue()) {
                        dataOutputStream.writeUTF(directionInfo);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadNPCFromSQL() {
        long start = System.currentTimeMillis();
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();

        Query loadNpcQuery = session.createNativeQuery("SELECT * FROM npc");

        List<Object[]> results =loadNpcQuery.getResultList();

        for(Object[] r : results) {
            Npc n = NpcData.getNpcDeepCopyById((Integer)r[1]);
            Field f = getFieldById( (Integer)r[2] );

            Position p = new Position();
            p.setX((Integer)r[3]);
            p.setY((Integer)r[4]);

            n.setPosition(p);
            n.setCy((Integer)r[5]);
            n.setRx0((Integer)r[6]);
            n.setRx1((Integer)r[7]);
            n.setFh((Integer)r[8]);

            f.addLife(n);
        }


        transaction.commit();
        session.close();
        log.info("Loaded Custom NPCs in " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void loadFieldInfoFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Map002.wz/Map"; // Map002 since v212
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.listFiles() == null) {
                continue;
            }
            for (File mapFile : file.listFiles()) {
                Document doc = XMLApi.getRoot(mapFile);
                Node node = XMLApi.getAllChildren(doc).get(0);
                if (node == null) {
                    continue;
                }
                int id = Integer.parseInt(XMLApi.getAttributes(node).get("name").replace(".img", ""));
                Field field = new Field(id);
                Node infoNode = XMLApi.getFirstChildByNameBF(node, "info");
                for (Node n : XMLApi.getAllChildren(infoNode)) {
                    Map<String, String> attr = XMLApi.getAttributes(n);
                    String name = attr.get("name");
                    String value = attr.get("value");
                    switch (name) {
                        case "town":
                            field.setTown(Integer.parseInt(value) != 0);
                            break;
                        case "swim":
                            field.setSwim(Integer.parseInt(value) != 0);
                            break;
                        case "fieldLimit":
                            field.setFieldLimit(Long.parseLong(value));
                            break;
                        case "returnMap":
                            field.setReturnMap(Integer.parseInt(value));
                            break;
                        case "forcedReturn":
                            field.setForcedReturn(Integer.parseInt(value));
                            break;
                        case "mobRate":
                            field.setMobRate(Double.parseDouble(value));
                            break;
                        case "fly":
                            field.setFly(Integer.parseInt(value) != 0);
                            break;
                        case "onFirstUserEnter":
                            field.setOnFirstUserEnter(value);
                            break;
                        case "onUserEnter":
                            field.setOnUserEnter(value);
                            break;
                        case "fieldScript":
                            field.setFieldScript(value);
                            break;
                        case "reactorShuffle":
                            field.setReactorShuffle(Integer.parseInt(value) != 0);
                            break;
                        case "expeditionOnly":
                            field.setExpeditionOnly(Integer.parseInt(value) != 0);
                            break;
                        case "partyOnly":
                            field.setPartyOnly(Integer.parseInt(value) != 0);
                            break;
                        case "isNeedSkillForFly":
                            field.setNeedSkillForFly(Integer.parseInt(value) != 0);
                            break;
                        case "fixedMobCapacity":
                            field.setFixedMobCapacity(Integer.parseInt(value));
                            break;
                        case "createMobInterval":
                            field.setCreateMobInterval(Integer.parseInt(value));
                            break;
                        case "timeOut":
                            field.setTimeOut(Integer.parseInt(value));
                            break;
                        case "timeLimit":
                            field.setTimeLimit(Integer.parseInt(value));
                            break;
                        case "lvLimit":
                            field.setLvLimit(Integer.parseInt(value));
                            break;
                        case "lvForceMove":
                            field.setLvForceMove(Integer.parseInt(value));
                            break;
                        case "consumeItemCoolTime":
                            field.setConsumeItemCoolTime(Integer.parseInt(value));
                            break;
                        case "link":
                            field.setLink(Integer.parseInt(value));
                            break;
                        case "bossMobID":
                            field.setBossMobID(Integer.parseInt(value));
                            break;
                        case "VRTop":
                            field.setVrTop(Integer.parseInt(value));
                            break;
                        case "VRLeft":
                            field.setVrLeft(Integer.parseInt(value));
                            break;
                        case "VRBottom":
                            field.setVrBottom(Integer.parseInt(value));
                            break;
                        case "VRRight":
                            field.setVrRight(Integer.parseInt(value));
                            break;
                        case "fieldType":
                            if (value.equals("")) {
                                field.setFieldType(FieldType.DEAFULT);
                            } else {
                                FieldType fieldType = FieldType.getByVal(Integer.parseInt(value));
                                if (fieldType == null) {
                                    field.setFieldType(FieldType.DEAFULT);
                                    break;
                                }
                                field.setFieldType(fieldType);
                            }
                            break;
                        case "barrier":
                            field.setBarrier(Integer.parseInt(value));
                            break;
                        case "barrierArc":
                            field.setBarrierArc(Integer.parseInt(value));
                            break;
                        case "reviveCurField":
                            field.setReviveCurField(Integer.parseInt(value) != 0);
                            break;
                        case "ReviveCurFieldOfNoTransfer":
                            field.setReviveCurFieldOfNoTransfer(Integer.parseInt(value) != 0);
                            break;
                        case "ReviveCurFieldOfNoTransferPoint":
                            field.setReviveCurFieldOfNoTransferPoint(new Position(
                                    Integer.parseInt(XMLApi.getNamedAttribute(n, "x")),
                                    Integer.parseInt(XMLApi.getNamedAttribute(n, "y"))));
                            break;
                    }
                }
                if (field.getFieldType() == null) {
                    field.setFieldType(FieldType.DEAFULT);
                }
                Node fhNode = XMLApi.getFirstChildByNameBF(node, "foothold");
                if (fhNode != null) {
                    for (Node layerIDNode : XMLApi.getAllChildren(fhNode)) {
                        int layerID = Integer.parseInt(XMLApi.getNamedAttribute(layerIDNode, "name"));
                        for (Node groupIDNode : XMLApi.getAllChildren(layerIDNode)) {
                            int groupID = Integer.parseInt(XMLApi.getNamedAttribute(groupIDNode, "name"));
                            for (Node idNode : XMLApi.getAllChildren(groupIDNode)) {
                                int fhId = Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name"));
                                Foothold fh = new Foothold(fhId, layerID, groupID);
                                for (Node n : XMLApi.getAllChildren(idNode)) {
                                    String name = XMLApi.getNamedAttribute(n, "name");
                                    String value = XMLApi.getNamedAttribute(n, "value");
                                    switch (name) {
                                        case "x1":
                                            fh.setX1(Integer.parseInt(value));
                                            break;
                                        case "y1":
                                            fh.setY1(Integer.parseInt(value));
                                            break;
                                        case "x2":
                                            fh.setX2(Integer.parseInt(value));
                                            break;
                                        case "y2":
                                            fh.setY2(Integer.parseInt(value));
                                            break;
                                        case "next":
                                            fh.setNext(Integer.parseInt(value));
                                            break;
                                        case "prev":
                                            fh.setPrev(Integer.parseInt(value));
                                            break;
                                        case "force":
                                            fh.setForce(Integer.parseInt(value));
                                            break;
                                        case "forbidFallDown":
                                            fh.setForbidFallDown(Integer.parseInt(value) != 0);
                                            break;
                                    }
                                }
                                field.addFoothold(fh);
                            }
                        }
                    }
                }
                Node portalNode = XMLApi.getFirstChildByNameBF(node, "portal");
                if (portalNode != null) {
                    for (Node idNode : XMLApi.getAllChildren(portalNode)) {
                        int portalId = Integer.parseInt(XMLApi.getNamedAttribute(idNode, "name"));
                        Portal portal = new Portal(portalId);
                        for (Node n : XMLApi.getAllChildren(idNode)) {
                            String name = XMLApi.getNamedAttribute(n, "name");
                            String value = XMLApi.getNamedAttribute(n, "value");
                            switch (name) {
                                case "pt":
                                    portal.setType(PortalType.getTypeByInt(Integer.parseInt(value)));
                                    break;
                                case "pn":
                                    portal.setName(value);
                                    break;
                                case "tm":
                                    portal.setTargetMapId(Integer.parseInt(value));
                                    break;
                                case "tn":
                                    portal.setTargetPortalName(value);
                                    break;
                                case "x":
                                    portal.setX(Integer.parseInt(value));
                                    break;
                                case "y":
                                    portal.setY(Integer.parseInt(value));
                                    break;
                                case "horizontalImpact":
                                    portal.setHorizontalImpact(Integer.parseInt(value));
                                    break;
                                case "verticalImpact":
                                    portal.setVerticalImpact(Integer.parseInt(value));
                                    break;
                                case "script":
                                    portal.setScript(value);
                                    break;
                                case "onlyOnce":
                                    portal.setOnlyOnce(Integer.parseInt(value) != 0);
                                    break;
                                case "delay":
                                    portal.setDelay(Integer.parseInt(value));
                                    break;
                            }
                        }
                        field.addPortal(portal);
                    }
                }
                Node lifeNode = XMLApi.getFirstChildByNameBF(node, "life");
                if (lifeNode != null) {
                    List<Node> idNodes = new ArrayList<>();
                    if (XMLApi.getFirstChildByNameBF(lifeNode, "isCategory") != null) {
                        for (Node catNode : XMLApi.getAllChildren(lifeNode)) {
                            if (!XMLApi.getNamedAttribute(catNode, "name").equals("isCategory")) {
                                idNodes.addAll(XMLApi.getAllChildren(catNode));
                            }
                        }
                    } else {
                        idNodes = XMLApi.getAllChildren(lifeNode);
                    }
                    for (Node idNode : idNodes) {
                        Life life = new Life(0);
                        for (Node n : XMLApi.getAllChildren(idNode)) {
                            String name = XMLApi.getNamedAttribute(n, "name");
                            String value = XMLApi.getNamedAttribute(n, "value");
                            switch (name) {
                                case "type":
                                    life.setLifeType(value);
                                    break;
                                case "id":
                                    life.setTemplateId(Integer.parseInt(value));
                                    break;
                                case "x":
                                    life.setX(Integer.parseInt(value));
                                    break;
                                case "y":
                                    life.setY(Integer.parseInt(value));
                                    break;
                                case "mobTime":
                                    life.setMobTime(Integer.parseInt(value));
                                    break;
                                case "f":
                                    life.setFlip(Integer.parseInt(value) != 0);
                                    break;
                                case "hide":
                                    life.setHide(Integer.parseInt(value) != 0);
                                    break;
                                case "fh":
                                    life.setFh(Integer.parseInt(value));
                                    break;
                                case "cy":
                                    life.setCy(Integer.parseInt(value));
                                    break;
                                case "rx0":
                                    life.setRx0(Integer.parseInt(value));
                                    break;
                                case "rx1":
                                    life.setRx1(Integer.parseInt(value));
                                    break;
                                case "limitedname":
                                    life.setLimitedName(value);
                                    break;
                                case "useDay":
                                    life.setUseDay(Integer.parseInt(value) != 0);
                                    break;
                                case "useNight":
                                    life.setUseNight(Integer.parseInt(value) != 0);
                                    break;
                                case "hold":
                                    life.setHold(Integer.parseInt(value) != 0);
                                    break;
                                case "nofoothold":
                                    life.setNoFoothold(Integer.parseInt(value) != 0);
                                    break;
                                case "dummy":
                                    life.setDummy(Integer.parseInt(value) != 0);
                                    break;
                                case "spine":
                                    life.setSpine(Integer.parseInt(value) != 0);
                                    break;
                                case "mobTimeOnDie":
                                    life.setMobTimeOnDie(Integer.parseInt(value) != 0);
                                    break;
                                case "regenStart":
                                    life.setRegenStart(Integer.parseInt(value));
                                    break;
                                case "mobAliveReq":
                                    life.setMobAliveReq(Integer.parseInt(value));
                                    break;
                                default:
                                    if (LOG_UNKS) {
                                        log.warn("unknown life property " + name + " with value " + value);
                                    }
                                    break;
                            }
                        }
                        field.addLife(life);
                    }
                }
                Node reactorNode = XMLApi.getFirstChildByNameBF(node, "reactor");
                if (reactorNode != null) {
                    for (Node reactorIdNode : XMLApi.getAllChildren(reactorNode)) {
                        Reactor reactor = new Reactor(0);
                        reactor.setLifeType("r");
                        for (Node valNode : XMLApi.getAllChildren(reactorIdNode)) {
                            String name = XMLApi.getNamedAttribute(valNode, "name");
                            String value = XMLApi.getNamedAttribute(valNode, "value");
                            int iVal = Util.isNumber(value) ? Integer.parseInt(value) : 0;
                            switch (name) {
                                case "id":
                                    reactor.setTemplateId(iVal);
                                    break;
                                case "x":
                                    Position curPos = reactor.getHomePosition();
                                    if (curPos == null) {
                                        curPos = new Position();
                                    }
                                    curPos.setX(iVal);
                                    reactor.setX(iVal);
                                    reactor.setHomePosition(curPos);
                                    break;
                                case "y":
                                    curPos = reactor.getHomePosition();
                                    if (curPos == null) {
                                        curPos = new Position();
                                    }
                                    curPos.setY(iVal);
                                    reactor.setY(iVal);
                                    reactor.setHomePosition(curPos);
                                    break;
                                case "reactorTime":
                                    reactor.setMobTime(iVal);
                                    break;
                                case "f":
                                    reactor.setFlip(iVal != 0);
                                    break;
                                case "name":
                                    reactor.setLimitedName(value);
                                    break;
                                case "phantomForest":
                                    reactor.setPhantomForest(iVal != 0);
                                    break;
                                default:
                                    if (LOG_UNKS) {
                                        log.warn(String.format("Unknown reactor property %s with value %s", name, value));
                                    }
                            }
                        }
                        field.addLife(reactor);
                    }
                }
                Node directionInfoNode = XMLApi.getFirstChildByNameBF(node, "directionInfo");
                if (directionInfoNode != null) {
                    for (Node idNode : XMLApi.getAllChildren(directionInfoNode)) {
                        String name = XMLApi.getNamedAttribute(idNode, "name");
                        for (Node n : XMLApi.getAllChildren(idNode)) {
                            // there are more values but only the client use it we need only eventQ
                            if (XMLApi.getNamedAttribute(n, "name").equals("EventQ")) {
                                List<String> directionInfo = new ArrayList<>();
                                for (Node event : XMLApi.getAllChildren(n)) {
                                    directionInfo.add(XMLApi.getNamedAttribute(event, "value"));
                                }
                                field.addDirectionInfo(Integer.parseInt(name), directionInfo);
                            }
                        }
                    }
                }
                getFields().add(field);
            }
        }
    }


    public static List<Field> getFields() {
        return fields;
    }

    public static List<Integer> getWorldMapFields() { return worldMapFields; }

    public static Field getFieldById(int id) {
        for (Field f : getFields()) {
            if (f.getId() == id) {
                return f;
            }
        }
        return getFieldFromFile(id);
    }

    private static Field getFieldFromFile(int id) {
        String fieldDir = ServerConstants.DAT_DIR + "/fields/" + id + ".dat";
        File file = new File(fieldDir);
        if (!file.exists()) {
//            log.error("Could not find a field with id " + id);
            return null;
        } else {
            return readFieldFromFile(file);
        }
    }

    private static Field readFieldFromFile(File file) {
        Field field = null;
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            field = new Field(dataInputStream.readInt());
            field.setFieldType(FieldType.getByVal(dataInputStream.readInt()));
            field.setTown(dataInputStream.readBoolean());
            field.setSwim(dataInputStream.readBoolean());
            field.setFieldLimit(dataInputStream.readLong());
            field.setReturnMap(dataInputStream.readInt());
            field.setForcedReturn(dataInputStream.readInt());
            field.setMobRate(dataInputStream.readDouble());
            field.setFly(dataInputStream.readBoolean());
            field.setOnFirstUserEnter(dataInputStream.readUTF());
            field.setOnUserEnter(dataInputStream.readUTF());
            field.setFieldScript(dataInputStream.readUTF());
            field.setReactorShuffle(dataInputStream.readBoolean());
            field.setExpeditionOnly(dataInputStream.readBoolean());
            field.setPartyOnly(dataInputStream.readBoolean());
            field.setNeedSkillForFly(dataInputStream.readBoolean());
            field.setFixedMobCapacity(dataInputStream.readInt());
            field.setCreateMobInterval(dataInputStream.readInt());
            field.setTimeOut(dataInputStream.readInt());
            field.setTimeLimit(dataInputStream.readInt());
            field.setLvLimit(dataInputStream.readInt());
            field.setLvForceMove(dataInputStream.readInt());
            field.setConsumeItemCoolTime(dataInputStream.readInt());
            field.setLink(dataInputStream.readInt());
            field.setBossMobID(dataInputStream.readInt());
            field.setVrTop(dataInputStream.readInt());
            field.setVrLeft(dataInputStream.readInt());
            field.setVrBottom(dataInputStream.readInt());
            field.setVrRight(dataInputStream.readInt());
            field.setBarrier(dataInputStream.readInt());
            field.setBarrierArc(dataInputStream.readInt());

            field.setReviveCurField(dataInputStream.readBoolean());
            field.setReviveCurFieldOfNoTransfer(dataInputStream.readBoolean());
            if (dataInputStream.readBoolean()) {
                field.setReviveCurFieldOfNoTransferPoint(
                        new Position(dataInputStream.readShort(), dataInputStream.readShort()));
            }


            short fhSize = dataInputStream.readShort();
            for (int j = 0; j < fhSize; j++) {
                Foothold fh = new Foothold(
                        dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readInt()
                );
                fh.setX1(dataInputStream.readInt());
                fh.setY1(dataInputStream.readInt());
                fh.setX2(dataInputStream.readInt());
                fh.setY2(dataInputStream.readInt());
                fh.setNext(dataInputStream.readInt());
                fh.setPrev(dataInputStream.readInt());
                fh.setForce(dataInputStream.readInt());
                fh.setForbidFallDown(dataInputStream.readBoolean());
                field.addFoothold(fh);
            }
            short portalSize = dataInputStream.readShort();
            for (int j = 0; j < portalSize; j++) {
                Portal p = new Portal(dataInputStream.readInt());
                p.setType(PortalType.getTypeByInt(dataInputStream.readInt()));
                p.setName(dataInputStream.readUTF());
                p.setTargetMapId(dataInputStream.readInt());
                p.setTargetPortalName(dataInputStream.readUTF());
                p.setX(dataInputStream.readInt());
                p.setY(dataInputStream.readInt());
                p.setHorizontalImpact(dataInputStream.readInt());
                p.setVerticalImpact(dataInputStream.readInt());
                p.setScript(dataInputStream.readUTF());
                p.setOnlyOnce(dataInputStream.readBoolean());
                p.setDelay(dataInputStream.readInt());
                field.addPortal(p);
            }
            short lifeSize = dataInputStream.readShort();
            for (int j = 0; j < lifeSize; j++) {
                Life l = new Life(0);
                l.setLifeType(dataInputStream.readUTF());
                l.setTemplateId(dataInputStream.readInt());
                l.setX(dataInputStream.readInt());
                l.setY(dataInputStream.readInt());
                l.setHomePosition(new Position(l.getX(), l.getY()));
                l.setMobTime(dataInputStream.readInt());
                l.setFlip(dataInputStream.readBoolean());
                l.setHide(dataInputStream.readBoolean());
                l.setFh(dataInputStream.readInt());
                l.setCy(dataInputStream.readInt());
                l.setRx0(dataInputStream.readInt());
                l.setRx1(dataInputStream.readInt());
                l.setLimitedName(dataInputStream.readUTF());
                l.setUseDay(dataInputStream.readBoolean());
                l.setUseNight(dataInputStream.readBoolean());
                l.setHold(dataInputStream.readBoolean());
                l.setNoFoothold(dataInputStream.readBoolean());
                l.setDummy(dataInputStream.readBoolean());
                l.setSpine(dataInputStream.readBoolean());
                l.setMobTimeOnDie(dataInputStream.readBoolean());
                l.setRegenStart(dataInputStream.readInt());
                l.setMobAliveReq(dataInputStream.readInt());
                if ("m".equalsIgnoreCase(l.getLifeType())) {
                    MobGen mobGen = l.createMobGenFromLife();
                    field.addLife(mobGen);
                } else if ("n".equalsIgnoreCase(l.getLifeType())) {
                    Npc npc = l.createNpcFromLife();
                    field.addLife(npc);
                } else if ("r".equalsIgnoreCase(l.getLifeType())) {
                    Reactor reactor = l.createReactorFromLife();
                    field.addLife(reactor);
                } else {
                    field.addLife(l);
                }
            }
            short directionSize = dataInputStream.readShort();
            for (int j = 0; j < directionSize; j++) {
                int key = dataInputStream.readInt();
                List<String> directions = new ArrayList<>();
                short directionsSize = dataInputStream.readShort();
                for (int k = 0; k < directionsSize; k++) {
                    directions.add(dataInputStream.readUTF());
                }
                field.addDirectionInfo(key, directions);
            }
            if (!field.hasVrInfo()) {
                field.setHighestFhYValue(field.calcHighestFhYValue());
            }
            getFields().add(field);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return field;
    }

    private static void loadWorldMapFromWz() {
        String wzDir = ServerConstants.WZ_DIR + "/Map.wz/WorldMap";
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            Document doc = XMLApi.getRoot(file);
            Node node = XMLApi.getAllChildren(doc).get(0);
            if (node == null || !file.getName().contains("WorldMap")) {
                continue;
            }
            Node mapList = XMLApi.getFirstChildByNameBF(node, "MapList");
            for (Node n : XMLApi.getAllChildren(Objects.requireNonNull(mapList))) {
                Node infoNode = XMLApi.getFirstChildByNameBF(n, "mapNo");
                for (Node info : XMLApi.getAllChildren(Objects.requireNonNull(infoNode))) {
                    Map<String, String> attr = XMLApi.getAttributes(info);
                    int fieldId = Integer.parseInt(attr.get("value"));
                    if (!worldMapFields.contains(fieldId)) {
                        worldMapFields.add(fieldId);
                    }
                }
            }
        }
    }

    private static void saveWorldMap(String dir) {
        File file = new File(dir);
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file))) {
            dataOutputStream.writeInt(worldMapFields.size());
            for (int fieldId : worldMapFields) {
                dataOutputStream.writeInt(fieldId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadWorldMap() {
        long start = System.currentTimeMillis();
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(ServerConstants.DAT_DIR + "/worldMap.dat"))) {
            int size = dataInputStream.readInt();
            for (int i = 0; i < size; i++) {
                worldMapFields.add(dataInputStream.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(String.format("Loaded world map fields from data file in %dms.", System.currentTimeMillis() - start));
    }

    public static void generateDatFiles() {
        log.info("Started generating field data.");
        long start = System.currentTimeMillis();
        loadFieldInfoFromWz();
        saveFields(ServerConstants.DAT_DIR + "/fields");
        loadWorldMapFromWz();
        saveWorldMap(ServerConstants.DAT_DIR + "/worldMap.dat");
        log.info(String.format("Completed generating field data in %dms.", System.currentTimeMillis() - start));
    }

    public static Field getFieldCopyById(int id) {
        return getFieldCopyById(id, false);
    }

    public static Field getFieldCopyById(int id, boolean isChannelField) {
        Field field = getFieldById(id);
        if (field == null) {
            log.error("Could not find field " + id);
            return null;
        }
        Field copy = new Field(id);
        copy.setChannelField(isChannelField);
        copy.setFieldType(field.getFieldType());
        copy.setTown(field.isTown());
        copy.setSwim(field.isSwim());
        copy.setFieldLimit(field.getFieldLimit());
        copy.setReturnMap(field.getReturnMap());
        copy.setForcedReturn(field.getForcedReturn());
        copy.setMobRate(field.getMobRate());
        copy.setFly(field.isFly());
        copy.setOnFirstUserEnter(field.getOnFirstUserEnter());
        copy.setOnUserEnter(field.getOnUserEnter());
        copy.setFieldScript(field.getFieldScript());
        copy.setReactorShuffle(field.isReactorShuffle());
        copy.setExpeditionOnly(field.isExpeditionOnly());
        copy.setPartyOnly(field.isPartyOnly());
        copy.setNeedSkillForFly(field.isNeedSkillForFly());
        if (field.getFixedMobCapacity() != 0) {
            copy.setFixedMobCapacity(field.getFixedMobCapacity());
        }
        copy.setCreateMobInterval(field.getCreateMobInterval());
        copy.setTimeOut(field.getTimeOut());
        copy.setTimeLimit(field.getTimeLimit());
        copy.setLvLimit(field.getLvLimit());
        copy.setLvForceMove(field.getLvForceMove());
        copy.setConsumeItemCoolTime(field.getConsumeItemCoolTime());
        copy.setLink(field.getLink());
        for (Foothold fh : field.getFootholds()) {
            copy.addFoothold(fh.deepCopy());
        }
        for (Portal p : field.getPortals()) {
            copy.addPortal(p.deepCopy());
        }
        for (Life l : field.getLifes().values()) {
            copy.addLife(l.deepCopy());
        }
        copy.setObjectIDCounter(field.getNewObjectID());
        copy.setVrTop(field.getVrTop());
        copy.setVrLeft(field.getVrLeft());
        copy.setVrBottom(field.getVrBottom());
        copy.setVrRight(field.getVrRight());
        copy.setBarrier(field.getBarrier());
        copy.setBarrierArc(field.getBarrierArc());
        copy.setReviveCurField(field.isReviveCurField());
        copy.setReviveCurFieldOfNoTransfer(field.isReviveCurFieldOfNoTransfer());
        copy.setReviveCurFieldOfNoTransferPoint(field.getReviveCurFieldOfNoTransferPoint());
        copy.startBurningFieldTimer();
        int mobGens = field.getMobGens().size();
        copy.setFixedMobCapacity((int) (mobGens * GameConstants.DEFAULT_FIELD_MOB_RATE_BY_MOBGEN_COUNT));
        copy.generateMobs(true);
        if (!isChannelField) {
            copy.update(false, true);
        }
        copy.setDirectionInfo(field.getDirectionInfo());
        copy.startFieldScript();
        copy.setHighestFhYValue(field.getHighestFhYValue());
        int lv = 0;
        for (MobGen mobGen : field.getMobGens()) {
            lv += mobGen.getMob().getLevel();
        }
        if (mobGens > 0) {
            lv /= mobGens;
        }
        copy.setAverageMobLevel(lv);
        copy.setRuneStone(new RuneStone().getRandomRuneStone(copy));
        copy.setProperties(new HashMap<>());
        return copy;
    }

    public static void clear() {
        getFields().clear();
        getWorldMapFields().clear();
    }
}
