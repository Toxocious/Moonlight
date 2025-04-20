package net.swordie.ms.loaders;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.loaders.containerclasses.ReactorInfo;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.XMLApi;

import java.io.*;
import java.util.HashMap;

/**
 * Created on 4/21/2018.
 */
public class ReactorData {

    private static final boolean LOG_UNKS = false;
    private static final Logger log = Logger.getLogger(ReactorData.class);
    private static HashMap<Integer, ReactorInfo> reactorInfo = new HashMap<>();

    private static void loadReactorsFromWZ() {
        String wzDir = ServerConstants.WZ_DIR + "/Reactor.wz";
        File dir = new File(wzDir);
        File[] files = dir.listFiles();
        for (File file : files) {
            int id = Integer.parseInt(file.getName().replace(".img.xml", ""));
            ReactorInfo ri = new ReactorInfo();
            ri.setId(id);
            Node node = XMLApi.getAllChildren(XMLApi.getRoot(file)).get(0);
            Node questNode = XMLApi.getFirstChildByNameBF(node, "quest");
            if (questNode != null) {
                ri.setQuest(Integer.parseInt(XMLApi.getNamedAttribute(questNode, "value")));
            }
            Node actionNode = XMLApi.getFirstChildByNameBF(node, "action");
            if (actionNode != null) {
                ri.setAction(XMLApi.getNamedAttribute(actionNode, "value"));
            }
            Node mainInfoNode = XMLApi.getFirstChildByNameBF(node, "info");
            for (Node infoNode : XMLApi.getAllChildren(mainInfoNode)) {
                String name = XMLApi.getNamedAttribute(infoNode, "name");
                String value = XMLApi.getNamedAttribute(infoNode, "value");
                switch (name) {
                    case "info":
                        ri.setInfo(value);
                        break;
                    case "lt":
                        int x1 = Integer.parseInt(XMLApi.getNamedAttribute(infoNode, "x"));
                        int y1 = Integer.parseInt(XMLApi.getNamedAttribute(infoNode, "y"));
                        Node rbNode = XMLApi.getFirstChildByNameBF(mainInfoNode, "rb");
                        int x2 = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "x"));
                        int y2 = Integer.parseInt(XMLApi.getNamedAttribute(rbNode, "y"));
                        ri.setRect(new Rect(x1, y1, x2, y2));
                        break;
                    case "name":
                        ri.setName(value);
                        break;
                    case "viewName":
                        ri.setViewName(value);
                        break;
                    case "link":
                        ri.setLink(Integer.parseInt(value));
                        break;
                    case "level":
                        ri.setLevel(Integer.parseInt(value));
                        break;
                    case "resetTime":
                        ri.setResetTime(Integer.parseInt(value));
                        break;
                    case "hitCount":
                        ri.setHitCount(Integer.parseInt(value));
                        break;
                    case "overlapHit":
                        ri.setOverlapHit(Integer.parseInt(value));
                        break;
                    case "overlapHitTime":
                        ri.setOverlapHitTime(Integer.parseInt(value));
                        break;
                    case "actMark":
                        ri.setActMark(Integer.parseInt(value));
                        break;
                    case "dcMark":
                        ri.setDcMark("1".equalsIgnoreCase(value));
                        break;
                    case "activateByTouch":
                        ri.setActivateByTouch("1".equalsIgnoreCase(value));
                        break;
                    case "removeInFieldSet":
                        ri.setRemoveInFieldSet("1".equalsIgnoreCase(value));
                        break;
                    case "notHitable":
                        ri.setNotHittable("1".equalsIgnoreCase(value));
                        break;
                    case "notFatigue":
                        ri.setNotFatigue("1".equalsIgnoreCase(value));
                        break;
                    case "backTile":
                    case "frontTile":
                    case "forcedViewName":
                    case "rb":
                        break;
                    default:
                        if (LOG_UNKS) {
                            log.warn(String.format("[Reactor] Unhandled info node id %d, %s, value %s", ri.getId(), name, value));
                        }
                }
            }
            addReactorInfo(ri);
        }
    }

    private static void saveReactors(String dir) {
        Util.makeDirIfAbsent(dir);
        for (ReactorInfo ri : getReactorInfo().values()) {
            File file = new File(String.format("%s/%d.dat", dir, ri.getId()));
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                dos.writeInt(ri.getId());
                dos.writeInt(ri.getLink());
                dos.writeInt(ri.getLevel());
                dos.writeInt(ri.getResetTime());
                dos.writeInt(ri.getOverlapHitTime());
                dos.writeInt(ri.getActMark());
                dos.writeInt(ri.getHitCount());
                dos.writeInt(ri.getOverlapHit());
                dos.writeInt(ri.getQuest());
                dos.writeUTF(ri.getInfo());
                dos.writeUTF(ri.getName());
                dos.writeUTF(ri.getViewName());
                dos.writeUTF(ri.getAction());
                dos.writeBoolean(ri.isNotFatigue());
                dos.writeBoolean(ri.isDcMark());
                dos.writeBoolean(ri.isRemoveInFieldSet());
                dos.writeBoolean(ri.isActivateByTouch());
                dos.writeBoolean(ri.isNotHittable());
                dos.writeBoolean(ri.getRect() != null);
                if (ri.getRect() != null) {
                    Rect r = ri.getRect();
                    dos.writeShort(r.getLeft());
                    dos.writeShort(r.getTop());
                    dos.writeShort(r.getRight());
                    dos.writeShort(r.getBottom());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ReactorInfo loadReactorFromFile(File file) {
        ReactorInfo ri = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            ri = new ReactorInfo();
            ri.setId(dis.readInt());
            ri.setLink(dis.readInt());
            ri.setLevel(dis.readInt());
            ri.setResetTime(dis.readInt());
            ri.setOverlapHitTime(dis.readInt());
            ri.setActMark(dis.readInt());
            ri.setHitCount(dis.readInt());
            ri.setOverlapHit(dis.readInt());
            ri.setQuest(dis.readInt());
            ri.setInfo(dis.readUTF());
            ri.setName(dis.readUTF());
            ri.setViewName(dis.readUTF());
            ri.setAction(dis.readUTF());
            ri.setNotFatigue(dis.readBoolean());
            ri.setDcMark(dis.readBoolean());
            ri.setRemoveInFieldSet(dis.readBoolean());
            ri.setActivateByTouch(dis.readBoolean());
            ri.setNotHittable(dis.readBoolean());
            boolean hasRect = dis.readBoolean();
            if (hasRect) {
                Rect r = new Rect();
                r.setLeft(dis.readShort());
                r.setTop(dis.readShort());
                r.setRight(dis.readShort());
                r.setBottom(dis.readShort());
                ri.setRect(r);
            }
            addReactorInfo(ri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ri;
    }

    public static ReactorInfo getReactorInfoByID(int id) {
        ReactorInfo ri = getReactorInfo().get(id);
        return ri == null ? loadReactorByID(id) : ri;
    }

    public static Reactor getReactorByID(int id) {
        Reactor r = new Reactor(id);
        r.init();
        return r;
    }

    private static ReactorInfo loadReactorByID(int id) {
        File file = new File(String.format("%s/reactors/%d.dat", ServerConstants.DAT_DIR, id));
        if(file.exists()) {
            return loadReactorFromFile(file);
        } else {
            return null;
        }
    }

    private static HashMap<Integer, ReactorInfo> getReactorInfo() {
        return reactorInfo;
    }

    private static void addReactorInfo(ReactorInfo ri) {
        getReactorInfo().put(ri.getId(), ri);
    }

    public static void generateDatFiles() {
        log.info("Started generating reactor data.");
        long start = System.currentTimeMillis();
        loadReactorsFromWZ();
        saveReactors(String.format("%s/reactors", ServerConstants.DAT_DIR));
        log.info(String.format("Completed generating reactor data in %dms.", System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        generateDatFiles();
    }

    public static void clear() {
        getReactorInfo().clear();
    }
}
