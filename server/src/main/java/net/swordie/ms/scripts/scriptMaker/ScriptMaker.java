package net.swordie.ms.scripts.scriptMaker;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.npc.NpcScriptInfo;
import net.swordie.ms.loaders.*;
import net.swordie.ms.loaders.containerclasses.ReactorInfo;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.shop.BuyLimitInfo;
import net.swordie.ms.world.shop.NpcShopDlg;
import net.swordie.ms.world.shop.NpcShopItem;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * @author Sjonnie
 * Created on 2/18/2019.
 */
public class ScriptMaker {

    private static final Logger log = Logger.getLogger(ScriptMaker.class);
    public static final boolean MAY_OVERRIDE_FILES = false;

    private ScriptType type;
    private int id;
    private int fieldId;
    private String scriptName;
    private StringBuilder stringBuilder;
    private ScriptPacketParser spp = new ScriptPacketParser(this);
    private Char chr = new Char();
    private NpcScriptInfo nsi = new NpcScriptInfo();
    private boolean isInDirectionMode;
    private boolean antiSpam;
    private Map<Integer, Map<Integer, Integer>> npcs = new HashMap<>();
    private Set<Integer> selfStartedQuests = new HashSet<>();
    private Set<String> writtenFiles = new HashSet<>();

    public static void main(String[] args) {
        File file = new File(ServerConstants.DIR + "/Mihile_Tutorial_1-10.msb");
        if (!file.exists()) {
            System.err.println("Could not find that file!");
            return;
        }
        File[] files;
        if (file.isDirectory()) {
            files = file.listFiles();
        } else {
            files = new File[]{file};
        }
        StringData.load();
        DatabaseManager.init();
        long start;
        for (File f : files) {
            start = System.currentTimeMillis();
            log.info("Started generating from file " + f.getName());
            ScriptMaker sm = new ScriptMaker();
            List<InPacket> packets = analyzeFile(f);
            log.info("Took " + (System.currentTimeMillis() - start) + "ms to analyze " + f.getName());
            start = System.currentTimeMillis();
            //sm.makeShopsFromPackets(packets); // For Shops
            sm.makeScriptsFromPackets(packets);//  For NPCs without shops
            log.info("Took " + (System.currentTimeMillis() - start) + "ms to make the scripts for file " + f.getName());
        }
        System.exit(0);
    }

    private void makeShopsFromPackets(List<InPacket> inPackets) {
        StringBuilder sb = new StringBuilder("insert into shopitems (shopid, itemid, buylimit, tabindex, itemperiod," +
                " price, tokenitemid, tokenprice, pointquestid, pointprice, starcoin, levellimited, showlevmin, showlevmax," +
                " questid, questexkey, questexvalue, quantity, unitprice, maxperslot) values\r\n");
        Set<Integer> shopIds = new HashSet<>();
        for (InPacket inPacket : inPackets) {
            inPacket.decodeShort();
            if (inPacket.isLoopback()) {
                OutHeader header = OutHeader.getOutHeaderByOp(inPacket.getHeader());
                if (header == null) {
                    continue;
                }
                switch (header) {
                    case SHOP_OPEN:
                        NpcShopDlg nsd = new NpcShopDlg();
                        nsd.setNpcTemplateID(inPacket.decodeInt());
                        boolean hasPetTemplate = inPacket.decodeByte() != 0;
                        if (hasPetTemplate) {
                            int petTemplate = inPacket.decodeInt();
                        }
                        nsd.setSelectNpcItemID(inPacket.decodeInt());
                        int npcTemplateId = inPacket.decodeInt();
                        if (NpcData.getShopById(nsd.getNpcTemplateID()) != null || shopIds.contains(nsd.getNpcTemplateID())) {
                            System.out.println("Already have shop " + nsd.getNpcTemplateID());
                            continue;
                        }
                        shopIds.add(nsd.getNpcTemplateID());
                        sb.append("\r\n# ").append(StringData.getNpcStringById(nsd.getNpcTemplateID())).append(" (")
                                .append(nsd.getNpcTemplateID()).append(")\r\n");
                        nsd.setStarCoin(inPacket.decodeInt());
                        nsd.setShopVerNo(inPacket.decodeInt());
                        int idk = inPacket.decodeInt();
                        // start gms only
                        if (inPacket.decodeByte() != 0) {
                            byte size = inPacket.decodeByte();
                            for (int i = 0; i < size; i++) {
                                // just a guess that this is for quests
                                int questId = inPacket.decodeInt(); // questID?
                                String qrKey = inPacket.decodeString(); // questKey?
                            }
                        }
                        // end gms only
                        short itemSize = inPacket.decodeShort();
                        for (int i = 0; i < itemSize; i++) {
                            NpcShopItem nsi = new NpcShopItem();
                            nsi.setBuyLimit(inPacket.decodeInt()); // stock for the player

                            nsi.setItemID(inPacket.decodeInt());
                            nsi.setTabIndex(inPacket.decodeInt());
                            nsi.setBuyLimit(inPacket.decodeInt());
                            nsi.setItemPeriod(inPacket.decodeInt()); // time usage of item, in minutes (0 = no time limit)
                            int idk1 = inPacket.decodeInt(); // idk, 0 doesn't crash, 300 does, 100100 doesn't
//        inPacket.decodeInt(getDiscountPerc());
                            nsi.setPrice(inPacket.decodeInt());
                            nsi.setTokenItemID(inPacket.decodeInt());
                            nsi.setTokenPrice(inPacket.decodeInt());
                            nsi.setPointQuestID(inPacket.decodeInt());
                            nsi.setPointPrice(inPacket.decodeInt());
                            nsi.setStarCoin(inPacket.decodeInt());
                            if (inPacket.decodeByte() != 0) {
                                inPacket.decodeInt();
                                inPacket.decodeByte();
                                inPacket.decodeByte();
                                inPacket.decodeString();
                                inPacket.decodeInt();
                                inPacket.decodeString();
                                inPacket.decodeLong();
                                inPacket.decodeLong();
                                inPacket.decodeString();
                                int size = inPacket.decodeInt();
                                // inPacket.decodeInt();
                                for (int j = 0; j < size; j++) {
                                    inPacket.decodeLong();
                                }

                            }
                            inPacket.decodeInt();
                            inPacket.decodeInt();
                            nsi.setLevelLimited(inPacket.decodeInt());
                            nsi.setShowLevMin(inPacket.decodeShort());
                            nsi.setShowLevMax(inPacket.decodeShort());
                            // buyLimitInfo start
                            byte type = inPacket.decodeByte();
                            if (type == 1 || type == 3 || type == 4) {
                                int size = inPacket.decodeInt();
                                for (int j = 0; j < size; j++) {
                                    inPacket.decodeLong(); // ft
                                }
                            }
                            // ~
                            inPacket.decodeByte(); // bIsDisabled
                            FileTime start = FileTime.fromLong(inPacket.decodeLong()); // ftSellStart
                            FileTime end = FileTime.fromLong(inPacket.decodeLong()); // ftSellEnd
                            inPacket.decodeInt(); // ? setting it to >0 will make the item not show up
                            inPacket.decodeShort(); // ?
                            inPacket.decodeByte(); // bWorldBlock
                            nsi.setQuestID(inPacket.decodeInt());
                            nsi.setQuestExKey(inPacket.decodeString());
                            nsi.setQuestExValue(inPacket.decodeInt());
                            inPacket.decodeInt(); // ?
                            if (!ItemConstants.isRechargable(nsi.getItemID())) {
                                nsi.setQuantity(inPacket.decodeShort());
                            } else {
                                nsi.setUnitPrice(inPacket.decodeLong());
                            }
                            nsi.setMaxPerSlot(inPacket.decodeShort());
                            inPacket.decodeLong(); // ft
                            inPacket.decodeByte();
                            // sub with idk stuff in it
                            inPacket.decodeInt();
                            inPacket.decodeInt();
                            inPacket.decodeInt();
                            inPacket.decodeInt();
                            inPacket.decodeArr(32); // decodeBuffer(32), npc id (4) + int (4) x4? Example id = 78 96 8F 00 (9410168)
                            // end sub
                            boolean buyBack = inPacket.decodeByte() != 0;
                            if (buyBack) {
                                System.err.println("Too lazy to decode items in this");
                            }
                            if (!ItemConstants.getRechargeablesList().contains(nsi.getItemID())) {
                                sb.append(String.format("(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, " +
                                                "%s, %s, %s, %s, %s, \'%s\', %s, %s, %s, %s), # %s%n", nsd.getNpcTemplateID(),
                                        nsi.getItemID(), nsi.getBuyLimit(), nsi.getTabIndex(), nsi.getItemPeriod(),
                                        nsi.getPrice(), nsi.getTokenItemID(), nsi.getTokenPrice(), nsi.getPointQuestID(),
                                        nsi.getPointPrice(), nsi.getStarCoin(), nsi.getLevelLimited(), nsi.getShowLevMin(),
                                        nsi.getShowLevMax(), nsi.getQuestID(), nsi.getQuestExKey(), nsi.getQuestExValue(),
                                        nsi.getQuantity(), nsi.getUnitPrice(), nsi.getMaxPerSlot(), StringData.getItemStringById(nsi.getItemID())));
                            }
                        }
                        break;
                }
            }
        }
        System.out.println(sb);
    }


    private void makeScriptsFromPackets(List<InPacket> packets) {
        for (InPacket inPacket : packets) {
            inPacket.decodeShort();
            if (inPacket.isLoopback()) {
                OutHeader header = OutHeader.getOutHeaderByOp(inPacket.getHeader());
                if (header == null) {
                    continue;
                }
                switch (header) {
                    case SET_FIELD:
                        spp.handleSetField(inPacket);
                        break;
                    case SCRIPT_MESSAGE:
                        spp.handleScriptMessage(inPacket);
                        break;
                    case SET_IN_GAME_DIRECTION_MODE:
                        spp.handleInGameDirectionMode(inPacket);
                        break;
                    case IN_GAME_DIRECTION_EVENT:
                        spp.handleInGameDirectionEvent(inPacket);
                        break;
                    case OPEN_UI:
                        spp.handleOpenUI(inPacket);
                        break;
                    case CLOSE_UI:
                        spp.handleCloseUI(inPacket);
                        break;
                    case FIELD_EFFECT:
                        spp.handleFieldEffect(inPacket);
                        break;
                    case EFFECT:
                        spp.handleEffect(inPacket);
                        break;
                    case BALLOON_MSG:
                        spp.handleBalloonMsg(inPacket);
                        break;
                    case PROGRESS_MESSAGE_FONT:
                        spp.handleProgressMessageFont(inPacket);
                        break;
                    case NPC_ENTER_FIELD:
                        spp.handleNpcEnterField(inPacket);
                        break;
                    case NPC_CHANGE_CONTROLLER:
                        spp.handleNpcChangeController(inPacket);
                        break;
                    case NPC_VIEW_OR_HIDE:
                        spp.handleNpcViewOrHide(inPacket);
                        break;
                    case NPC_SET_FORCE_MOVE:
                        spp.handleNpcSetForceMove(inPacket);
                        break;
                    case NPC_SET_FORCE_FLIP:
                        spp.handleNpcSetForceFlip(inPacket);
                        break;
                    case NPC_SET_SPECIAL_ACTION:
                        spp.handleNpcSetSpecialAction(inPacket);
                        break;
                    case NPC_RESET_SPECIAL_ACTION:
                        spp.handleNpcResetSpecialAction(inPacket);
                        break;
                    case MESSAGE:
                        spp.handleMessage(inPacket);
                        break;
                    case SET_MAP_TAGGED_OBJECT_VISISBLE:
                        spp.handleSetMapTaggedObjectVisible(inPacket);
                        break;
                }
            } else {
                InHeader header = InHeader.getInHeaderByOp(inPacket.getHeader());
                if (header == null) {
                    continue;
                }
                switch (header) {
                    // "starter" packets
                    case USER_PORTAL_SCRIPT_REQUEST:
                        spp.handlePortalScriptRequest(inPacket);
                        break;
                    case USER_TRANSFER_FIELD_REQUEST:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleTransferFieldRequest(inPacket);
                        break;
                    case USER_SELECT_NPC:
                        spp.handleUserSelectNpc(inPacket);
                        break;
                    case USER_SCRIPT_ITEM_USE_REQUEST:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleUserScriptItemUseRequest(inPacket);
                        break;
                    case USER_QUEST_REQUEST:
                        // create script is done inside
                        spp.handleUserQuestRequest(inPacket, ScriptMaker.MAY_OVERRIDE_FILES);
                        break;
                    case DIRECTION_NODE_COLLISION:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleDirectionNodeCollision(inPacket);
                        break;
                    case REACTOR_CLICK:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleReactorClick(inPacket);
                        break;
                    case LIBRARY_START_SCRIPT:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleLibraryStartScript(inPacket);
                        break;
                    case UI_SCRIPT_REQUEST:
                        createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        spp.handleUiScriptRequest(inPacket);
                        break;
                    case USER_MOVE:
                        if (!isInDirectionMode) {
//                            createScript(ScriptMaker.MAY_OVERRIDE_FILES);
                        }
                        break;
                }
            }
        }
    }


    private static List<InPacket> analyzeFile(File file) {
        List<InPacket> inPackets = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            short version = readShort(dis);
            String localEndpoint = readString(dis);
            short localPort = readShort(dis);
            String remoteEndPoint = readString(dis);
            short remotePort = readShort(dis);

            byte locale = dis.readByte();
            short build = readShort(dis);
            String patchLocation = readString(dis);

            while (true) {
                // no length encoded :(
                long timeStamp = readLong(dis);
                int size = readInt(dis);
                short opcode = readShort(dis);
                boolean loopback = !dis.readBoolean();
                byte[] content = readArr(dis, size);
                byte[] total = new byte[2 + content.length];
                total[0] = (byte) (opcode & 0xFF);
                total[1] = (byte) ((opcode >> 8) & 0xFF);
                System.arraycopy(content, 0, total, 2, content.length);
                int preDecodeIV = readInt(dis);
                int postDecodeIV = readInt(dis);
                InPacket inPacket = new InPacket(total);
                inPacket.setLoopback(loopback);
                inPackets.add(inPacket);
            }
        } catch (EOFException e) {
            // no length specified, so this is intended.
            System.out.println("End of file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inPackets;
    }

    private static short readShort(DataInputStream dis) throws IOException {
        short s = (short) (dis.readByte() & 0xFF);
        s += (dis.readByte() & 0xFF) << 8;
        return s;
    }

    private static int readInt(DataInputStream dis) throws IOException {
        int s = (dis.readByte() & 0xFF);
        s += (dis.readByte() & 0xFF) << 8;
        s += (dis.readByte() & 0xFF) << 16;
        s += (dis.readByte() & 0xFF) << 24;
        return s;
    }

    private static long readLong(DataInputStream dis) throws IOException {
        long s = (dis.readByte() & 0xFF);
        s += (dis.readByte() & 0xFF) << 8;
        s += (dis.readByte() & 0xFF) << 16;
        s += (dis.readByte() & 0xFF) << 24;
        s += (long) (dis.readByte() & 0xFF) << 32;
        s += (long) (dis.readByte() & 0xFF) << 40;
        s += (long) (dis.readByte() & 0xFF) << 48;
        s += (long) (dis.readByte() & 0xFF) << 56;
        return s;
    }


    private static String readString(DataInputStream dis) throws IOException {
        int size = dis.readByte();
        byte[] arr = new byte[size];
        for (int i = 0; i < size; i++) {
            arr[i] = dis.readByte();
        }
        return new String(arr);
    }

    private static byte[] readArr(DataInputStream dis, int size) throws IOException {
        byte[] arr = new byte[size];
        for (int i = 0; i < size; i++) {
            arr[i] = dis.readByte();
        }
        return arr;
    }

    public ScriptType getType() {
        return type;
    }

    public void setType(ScriptType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public ScriptPacketParser getSpp() {
        return spp;
    }

    public void setSpp(ScriptPacketParser spp) {
        this.spp = spp;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
        if (!npcs.containsKey(fieldId)) {
            npcs.put(fieldId, new HashMap<>());
        }
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Field getField() {
        return FieldData.getFieldById(getFieldId());
    }

    public Char getChr() {
        return chr;
    }

    public void createScript(boolean mayOverride) {
        if (scriptName == null || "".equals(scriptName) || stringBuilder == null || stringBuilder.toString().length() == 0) {
            if (antiSpam) {
                System.err.println("No script to create!");
                antiSpam = true;
            }
            reset();
            return;
        }
        antiSpam = false;
        File file = new File(String.format("%s/%s/%s.py",
                ServerConstants.SCRIPT_DIR, getType().getDir(), getScriptName()));
        Field field = FieldData.getFieldById(getFieldId());
        String name = "";
        int id = getId();
        switch (getType()) {
            case None:
                break;
            case Npc:
                name = StringData.getNpcStringById(id);
                break;
            case Field:
            case FirstEnterField:
                name = StringData.getMapStringById(id);
                break;
            case Portal:
                name = field.getPortalByID(id) == null ? "null" : field.getPortalByID(id).getName();
                break;
            case Reactor:
                ReactorInfo ri = ReactorData.getReactorInfoByID(id);
                name = ri != null ? ri.getName() : null;
                break;
            case Item:
                name = StringData.getItemStringById(id);
                break;
            case Quest:
                name = StringData.getQuestStringById(id);
                break;
        }
        if (writtenFiles.contains(file.getName()) || (file.exists()) && !mayOverride) {
            System.out.println("File already exists! Making a duplicate and printing it here.");
            System.out.println(getType().getDir() + "/" + getScriptName());
            System.out.println(String.format("# id %d (%s), field %d", id, name, getFieldId()));
            System.out.println(stringBuilder.toString());
            int idx = 0;
            while (!file.exists() || writtenFiles.contains(file.getName())) {
                file = new File(String.format("%s/%s/%s_%d.py",
                        ServerConstants.SCRIPT_DIR, getType().getDir(), getScriptName(), idx));
            }
        }
        writtenFiles.add(file.getName());
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(String.format("# id %d (%s), field %d%n", id, name, getFieldId()));
            fw.write(stringBuilder.toString());
            System.out.println("Created script " + getType().getDir() + "/" + getScriptName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reset();
    }

    public void reset() {
        setType(null);
        setScriptName(null);
        setId(0);
        setNsi(new NpcScriptInfo());
        setStringBuilder(new StringBuilder());
    }

    public NpcScriptInfo getNsi() {
        return nsi;
    }

    public void setNsi(NpcScriptInfo nsi) {
        this.nsi = nsi;
    }

    public void addLine(String line, Object... args) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder();
        }
        line = String.format(line, args);
        line = line.replaceAll("\\r", "\\\\r");
        line = line.replaceAll("\\n", "\\\\n");
        stringBuilder.append(line).append("\r\n");
    }

    public void setInDirectionMode(boolean inDirectionMode) {
        isInDirectionMode = inDirectionMode;
    }

    public boolean isInDirectionMode() {
        return isInDirectionMode;
    }

    public boolean addNpc(int objId, int templateId) {
        Map<Integer, Integer> map = npcs.get(getFieldId());
        boolean exists = map.containsKey(objId);
        if (!exists) {
            map.put(objId, templateId);
        }
        return !exists;
    }

    public int getNpcTemplate(int objId) {
        return npcs.getOrDefault(getFieldId(), new HashMap<>()).getOrDefault(objId, -1);
    }

    public void renewNpcs() {
//         npcs = new HashMap<>();
    }

    public void addSelfStartedQuest(int questId) {
        selfStartedQuests.add(questId);
    }

    public boolean hasSelfStartedQuest(int questId) {
        return selfStartedQuests.contains(questId);
    }
}
