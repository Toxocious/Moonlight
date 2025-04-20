package net.swordie.ms.scripts.scriptMaker;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.life.npc.NpcMessageType;
import net.swordie.ms.life.npc.NpcScriptInfo;
import net.swordie.ms.loaders.*;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.QuestInfo;
import net.swordie.ms.loaders.containerclasses.ReactorInfo;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Portal;
import net.swordie.ms.world.field.fieldeffect.FieldEffectType;
import net.swordie.ms.world.field.fieldeffect.GreyFieldType;
import org.apache.log4j.Logger;

import java.util.List;

import static net.swordie.ms.life.npc.NpcMessageType.*;

/**
 * @author Sjonnie
 * Created on 2/18/2019.
 */
public class ScriptPacketParser {
    private static final Logger log = Logger.getLogger(ScriptPacketParser.class);

    private ScriptMaker sm;

    public ScriptPacketParser(ScriptMaker scriptMaker) {
        this.sm = scriptMaker;
    }

    public void handlePortalScriptRequest(InPacket inPacket) {
        Field field = FieldData.getFieldById(sm.getFieldId());
        byte portalID = inPacket.decodeByte();
        String portalName = inPacket.decodeString();
        Portal portal = field.getPortalByName(portalName);
        String script;
        if (portal != null) {
            portalID = (byte) portal.getId();
            script = "".equals(portal.getScript()) ? portalName : portal.getScript();
        } else {
            System.err.println("Could not find that portal.");
            return;
        }
        sm.createScript(ScriptMaker.MAY_OVERRIDE_FILES);
        sm.setType(ScriptType.Portal);
        sm.setId(portalID);
        sm.setScriptName(script);
    }

    public void handleSetField(InPacket inPacket) {
        int fieldID;
        int res = -1;
        byte[] fieldIdArr = new byte[4];
        int len = inPacket.getLength();
        fieldIdArr[0] = inPacket.decodeByte();
        fieldIdArr[1] = inPacket.decodeByte();
        fieldIdArr[2] = inPacket.decodeByte();
        fieldIdArr[3] = inPacket.decodeByte();
        for (int i = 4; i < len; i++) {
            // megalul, too lazy to fully decode it, just grab the last valid fieldID.
            fieldID = (fieldIdArr[0] & 0xFF)
                    + ((fieldIdArr[1] << 8) & 0xFF00)
                    + ((fieldIdArr[2] << 16) & 0xFF0000)
                    + ((fieldIdArr[3] << 24) & 0xFF000000);
            if (fieldID >= 100000000 && FieldData.getFieldById(fieldID) != null) {
                res = fieldID;
            }
            fieldIdArr[0] = fieldIdArr[1];
            fieldIdArr[1] = fieldIdArr[2];
            fieldIdArr[2] = fieldIdArr[3];
            fieldIdArr[3] = inPacket.decodeByte();
        }
        if (sm.getStringBuilder() != null && sm.getStringBuilder().toString().length() > 0) {
            sm.addLine("sm.warp(%d)", res);
        }
        sm.createScript(ScriptMaker.MAY_OVERRIDE_FILES);
        Field field = FieldData.getFieldById(res);
        if (field == null) {
            log.error("Unkown field " + res);
            return;
        }
        sm.setFieldId(res);
        sm.setId(res);
        String script = null;
        if (field.getOnUserEnter() != null && !"".equals(field.getOnUserEnter())) {
            script = field.getOnUserEnter();
        } else if (field.getOnFirstUserEnter() != null && !"".equals(field.getOnFirstUserEnter())) {
            script = field.getOnFirstUserEnter();
        } else if (field.getFieldScript() != null && !"".equals(field.getFieldScript())) {
            script = field.getFieldScript();
        }
        sm.setScriptName(script);
        System.out.println("SET_FIELD: " + res);
        sm.setType(ScriptType.Field);
        sm.renewNpcs();
    }

    public void handleTransferFieldRequest(InPacket inPacket) {
        if (inPacket.getUnreadAmount() < 11) {
            System.out.println("Not enough data for handleTransferFieldRequest");
            return;
        }
        byte fieldKey = inPacket.decodeByte();
        int targetField = inPacket.decodeInt();
        int x = inPacket.decodeShort();
        int y = inPacket.decodeShort();
        String portalName = inPacket.decodeString();
        if (portalName != null && !"".equals(portalName)) {
            Field field = sm.getField();
            Portal portal = field.getPortalByName(portalName);
            if (portal != null && portal.getScript() != null && !portal.getScript().equals("")) {
                sm.setType(ScriptType.Portal);
                sm.setId(portal.getId());
                sm.setScriptName(portal.getScript());
            }
        }
    }

    public void handleUserSelectNpc(InPacket inPacket) {
        if (inPacket.getUnreadAmount() < 8) {
            System.out.println("HandleUserSelectNpc doesn't have enough data.");
            return;
        }
        Field field = sm.getField();
        int npcID = inPacket.decodeInt();
        Position playerPos = inPacket.decodePosition();
        Life life = field.getLifeByObjectID(npcID);
        if (life == null) {
            int id = sm.getNpcTemplate(npcID);
            if (id != -1) {
                life = NpcData.getNpcDeepCopyById(sm.getNpcTemplate(npcID));
            }
        }
        if (!(life instanceof Npc)) {
            System.out.println("Could not find that npc.");
            return;
        }
        Npc npc = (Npc) life;
        int templateID = npc.getTemplateId();
        if (npc.getTrunkGet() > 0 || npc.getTrunkPut() > 0) {
            return;
        }
        String script = npc.getScripts().get(0);
        if (script == null) {
            return;
        }
        sm.createScript(ScriptMaker.MAY_OVERRIDE_FILES);
        sm.setType(ScriptType.Npc);
        sm.setId(templateID);
        sm.setScriptName(script);
    }

    public void handleUserScriptItemUseRequest(InPacket inPacket) {
        inPacket.decodeInt(); // tick
        short slot = inPacket.decodeShort();
        int itemID = inPacket.decodeInt();
        int quant = inPacket.decodeInt();
        String script = String.valueOf(itemID);
        ItemInfo ii = ItemData.getItemInfoByID(itemID);
        if (ii.getScript() != null && !"".equals(ii.getScript())) {
            script = ii.getScript();
        }
        sm.setType(ScriptType.Item);
        sm.setId(itemID);
        sm.setScriptName(script);
    }

    public void handleUserQuestRequest(InPacket inPacket, boolean mayOverride) {
        if (inPacket.getUnreadAmount() == 0) {
            System.out.println("HandleUserQuestRequest doesn't have enough data.");
            return;
        }
        byte type = inPacket.decodeByte();
        int questID = 0;
        int npcTemplateID = 0;
        Position position = null;
        QuestType qt = QuestType.getQTFromByte(type);
        boolean success = false;
        if (qt != null) {
            switch (qt) {
                case QuestReq_AcceptQuest: // Quest start
                case QuestReq_CompleteQuest: // Quest end
                case QuestReq_OpeningScript: // Scripted quest start
                case QuestReq_CompleteScript: // Scripted quest end
                    questID = inPacket.decodeInt();
                    npcTemplateID = inPacket.decodeInt();
                    if (inPacket.getUnreadAmount() > 4) {
                        position = inPacket.decodePosition();
                    }
                    break;
                case QuestReq_ResignQuest: //Quest forfeit
                    questID = inPacket.decodeInt();
                    break;
                case QuestReq_LaterStep:
                    questID = inPacket.decodeInt();
                    break;
                default:
                    log.error(String.format("Unhandled quest request \"%s\"!", qt));
                    break;
            }
        }
        if (questID == 0 || qt == null) {
            log.error(String.format("Could not find quest %d.", questID));
            return;
        }
        if (questID == 7707 || questID == 36102) {
            return;
        }
        QuestInfo qi = QuestData.getQuestInfoById(questID);
        switch (qt) {
            case QuestReq_AcceptQuest:
            case QuestReq_CompleteQuest:
                sm.addSelfStartedQuest(questID);
                break;
            case QuestReq_OpeningScript:
                sm.createScript(mayOverride);
                String scriptName = qi.getStartScript();
                if (scriptName == null || scriptName.equalsIgnoreCase("")) {
                    scriptName = String.format("%d%s", questID, ScriptManagerImpl.QUEST_START_SCRIPT_END_TAG);
                }
                sm.setType(ScriptType.Quest);
                sm.setId(questID);
                sm.setScriptName(scriptName);
                break;
            case QuestReq_CompleteScript:
                sm.createScript(mayOverride);
                scriptName = qi.getEndScript();
                if (scriptName == null || scriptName.equalsIgnoreCase("")) {
                    scriptName = String.format("%d%s", questID, ScriptManagerImpl.QUEST_COMPLETE_SCRIPT_END_TAG);
                }
                sm.setType(ScriptType.Quest);
                sm.setId(questID);
                sm.setScriptName(scriptName);
                break;
            case QuestReq_LaterStep:
                break;
        }
    }

    public void handleDirectionNodeCollision(InPacket inPacket) {

        Char chr = sm.getChr();
        if (chr == null || chr.getField() == null) {
            return;
        }
        Field field = sm.getField();

        int node = inPacket.decodeInt();
        List<String> directionNode = field.getDirectionNode(node);
        if (directionNode == null) {
            return;
        }
        String script = directionNode.get(chr.getCurrentDirectionNode(node));
        if (script == null) {
            return;
        }
        chr.increaseCurrentDirectionNode(node);

        sm.setType(ScriptType.Field);
        sm.setId(sm.getFieldId());
        sm.setScriptName(script);
    }

    public void handleReactorClick(InPacket inPacket) {
        Field field = sm.getField();
        int objID = inPacket.decodeInt();
        int idk = inPacket.decodeInt();
        byte type = inPacket.decodeByte();
        Life life = field.getLifeByObjectID(objID);
        if (!(life instanceof Reactor)) {
            log.error("Could not find reactor with objID " + objID);
            return;
        }
        Reactor reactor = (Reactor) life;
        int templateID = reactor.getTemplateId();
        ReactorInfo ri = ReactorData.getReactorInfoByID(templateID);
        String action = ri.getAction();

        sm.setType(ScriptType.Reactor);
        sm.setId(templateID);
        sm.setScriptName(action);
    }

    public void handleLibraryStartScript(InPacket inPacket) {

        int bookId = inPacket.decodeByte();
        int questID = QuestConstants.DIMENSION_LIBRARY + bookId;
        sm.setType(ScriptType.Quest);
        sm.setId(questID);
        sm.setScriptName("q" + questID + "s");
    }

    public void handleUiScriptRequest(InPacket inPacket) {

        short typeVal = inPacket.decodeShort(); // Unknown
        int fieldID = inPacket.decodeInt();
        // No normal script: use a self-made one
        UiScript us = UiScript.getByVal(typeVal);
        sm.setType(ScriptType.Npc);
        sm.setId(typeVal);
        if (us == null) {
            log.error("Unknown UiScript " + typeVal);
            return;
        }
        switch (us) {
            case UnionRaidStart:
                sm.setScriptName("unionRaid_IN");
                break;
            default:
                log.error("Unhandled UiScript " + us);
        }
    }

    public void handleScriptMessage(InPacket inPacket) {
        NpcScriptInfo nsi = new NpcScriptInfo();
        nsi.setSpeakerType(inPacket.decodeByte());
        nsi.setTemplateID(inPacket.decodeInt());
        int overrideTemplate = 0;
        if (inPacket.decodeByte() != 0) {
            overrideTemplate = inPacket.decodeInt();
        }
        nsi.setOverrideSpeakerTemplateID(overrideTemplate);
        byte type = inPacket.decodeByte();
        NpcMessageType nmt = NpcMessageType.getByVal(type);
        if (nmt == null) {
            log.error("Unknown nmt " + type + ", packet = " + inPacket);
            return;
        }
        nsi.setParam(inPacket.decodeShort());
        nsi.setColor(inPacket.decodeByte());
        int oldOverride = sm.getNsi().getInnerOverrideSpeakerTemplateID();
        checkAndSetNsiChanges(nsi);

        switch (nmt) {
            case Say:
            case SayOk:
            case SayNext:
            case SayPrev:
                if ((nsi.getParam() & 4) != 0) {
                    int ostid = inPacket.decodeInt();
                    nsi.setInnerOverrideSpeakerTemplateID(ostid);
                    if (ostid != 0 && ostid != oldOverride) {
                        String npcName = StringData.getNpcStringById(ostid);
                        sm.addLine("sm.setInnerOverrideSpeakerTemplateID(%d) # %s", ostid, npcName);
                        sm.getNsi().setInnerOverrideSpeakerTemplateID(ostid);
                    }
                }
                nsi.setText(inPacket.decodeString());
                boolean prevPossible = inPacket.decodeByte() != 0;
                boolean nextPossible = inPacket.decodeByte() != 0;
                if (prevPossible && nextPossible) {
                    nmt = Say;
                } else if (prevPossible && !nextPossible) {
                    nmt = SayPrev;
                } else if (!prevPossible && nextPossible) {
                    nmt = SayNext;
                } else {
                    nmt = SayOk;
                }
                nmt.setDelay(inPacket.decodeInt());
                switch (nmt) {
                    case Say:
                        sm.addLine("sm.sendSay(\"%s\")", nsi.getText());
                        break;
                    case SayPrev:
                        sm.addLine("sm.sendPrev(\"%s\")", nsi.getText());
                        break;
                    case SayNext:
                        sm.addLine("sm.sendNext(\"%s\")", nsi.getText());
                        break;
                    case SayOk:
                        sm.addLine("sm.sendSayOkay(\"%s\")", nsi.getText());
                        break;
                }
                break;
            case AskMenu:
            case AskYesNo:
                if ((nsi.getParam() & 4) != 0) {
                    int ostid = inPacket.decodeInt();
                    nsi.setInnerOverrideSpeakerTemplateID(ostid);
                    if (ostid != 0 && ostid != oldOverride) {
                        String npcName = StringData.getNpcStringById(ostid);
                        sm.addLine("sm.setInnerOverrideSpeakerTemplateID(%d) # %s", ostid, npcName);
                        sm.getNsi().setInnerOverrideSpeakerTemplateID(ostid);
                    }
                }
                nsi.setText(inPacket.decodeString());
                switch (nmt) {
                    case AskMenu:
                        sm.addLine("res = sm.sendNext(\"%s\")", nsi.getText());
                        break;
                    case AskYesNo:
                        sm.addLine("res = sm.sendAskYesNo(\"%s\")", nsi.getText());
                        break;
                }
                break;
            case AskAccept:
            case AskAccept2:
                if (nmt == AskAccept || (nmt == AskAccept2 && (nsi.getParam() & 4) != 0)) {
                    int ostid = inPacket.decodeInt();
                    nsi.setInnerOverrideSpeakerTemplateID(ostid);
                    if (ostid != 0 && ostid != oldOverride) {
                        String npcName = StringData.getNpcStringById(ostid);
                        sm.addLine("sm.setInnerOverrideSpeakerTemplateID(%d) # %s", ostid, npcName);
                        sm.getNsi().setInnerOverrideSpeakerTemplateID(ostid);
                    }
                }
                nsi.setText(inPacket.decodeString());
                sm.addLine("res = sm.sendAskAccept(\"%s\")", nsi.getText());
                break;
            case SayImage:
                String[] images = new String[inPacket.decodeByte()];
                StringBuilder imageStr = new StringBuilder("{");
                for (int i = 0; i < images.length; i++) {
                    images[i] = inPacket.decodeString();
                    imageStr.append(images[i]).append(", ");
                }
                imageStr.append("]");
                sm.addLine("images = \"%s\"", imageStr);
                sm.addLine("sm.sendSayImage(images)", imageStr);
                break;
            case AskText:
                if ((nsi.getParam() & 4) != 0) {
                    int ostid = inPacket.decodeInt();
                    nsi.setInnerOverrideSpeakerTemplateID(ostid);
                    if (ostid != 0 && ostid != oldOverride) {
                        String npcName = StringData.getNpcStringById(ostid);
                        sm.addLine("sm.setInnerOverrideSpeakerTemplateID(%d) # %s", ostid, npcName);
                        sm.getNsi().setInnerOverrideSpeakerTemplateID(ostid);
                    }
                }
                nsi.setText(inPacket.decodeString());
                nsi.setDefaultText(inPacket.decodeString());
                nsi.setMin(inPacket.decodeInt());
                nsi.setMax(inPacket.decodeInt());
                sm.addLine("res = sm.sendAskText(\"%s\", \"%s\", %d, %d)", nsi.getText(), nsi.getDefaultText(), nsi.getMin(), nsi.getMax());
                break;
            case AskNumber:
                nsi.setText(inPacket.decodeString());
                nsi.setDefaultNumber(inPacket.decodeInt());
                nsi.setMin(inPacket.decodeInt());
                nsi.setMax(inPacket.decodeInt());
                sm.addLine("res = sm.sendAskNumber(\"%s\", %d, %d, %d)", nsi.getText(), nsi.getDefaultNumber(),
                        nsi.getMin(), nsi.getMax());
                break;
            case AskAvatar:
                nsi.setAngelicBuster(inPacket.decodeByte() != 0);
                nsi.setZeroBeta(inPacket.decodeByte() != 0);
                nsi.setText(inPacket.decodeString());
                int[] options = new int[inPacket.decodeByte()];
                StringBuilder optString = new StringBuilder("[");
                for (int i = 0; i < options.length; i++) {
                    options[i] = inPacket.decodeInt();
                    optString.append(options[i]).append(", ");
                }
                optString.append("]");
                sm.addLine("options = \"%s\"", optString);
                sm.addLine("sm.sendAskAvatar(\"%s\", %s, %s, %s)", nsi.getText(),
                        nsi.isAngelicBuster() ? "True" : "False", nsi.isZeroBeta() ? "True" : "False",
                        "options");
                break;
            default:
                log.error("Unhandled nmt " + nmt);
        }
    }
    
    private void checkAndSetNsiChanges(NpcScriptInfo nsi) {
        NpcScriptInfo oldNsi = sm.getNsi();
        if (nsi.getSpeakerType() != oldNsi.getSpeakerType()) {
            sm.addLine("sm.setSpeakerType(%d)", nsi.getSpeakerType());
            oldNsi.setSpeakerType(nsi.getSpeakerType());
        }
        if (nsi.getTemplateID() != oldNsi.getTemplateID() && nsi.getTemplateID() != 0) {
            String npcName = StringData.getNpcStringById(nsi.getTemplateID());
            sm.addLine("sm.setSpeakerID(%d) # %s", nsi.getTemplateID(), npcName);
            oldNsi.setTemplateID(nsi.getTemplateID());
        }
        if (nsi.getOverrideSpeakerTemplateID() != oldNsi.getOverrideSpeakerTemplateID()) {
            String npcName = StringData.getNpcStringById(nsi.getOverrideSpeakerTemplateID());
            sm.addLine("sm.setSpeakerID(%d) # %s", nsi.getOverrideSpeakerTemplateID(), npcName);
            oldNsi.setOverrideSpeakerTemplateID(nsi.getOverrideSpeakerTemplateID());
        }
        if (nsi.getParam() != oldNsi.getParam()) {
            sm.addLine("sm.setParam(%d)", nsi.getParam());
            oldNsi.setParam(nsi.getParam());
        }
        if (nsi.getColor() != oldNsi.getColor()) {
            sm.addLine("sm.setColor(%d)", nsi.getColor());
            oldNsi.setColor(nsi.getColor());
        }
    }

    public void handleInGameDirectionMode(InPacket inPacket) {
        boolean lockUI = inPacket.decodeByte() != 0;
        boolean blackFrame = inPacket.decodeByte() != 0;
        if (lockUI) {
            boolean forceMouseOver = inPacket.decodeByte() != 0;
        }
        sm.setInDirectionMode(lockUI);
        sm.addLine("sm.lockInGameUI(%s, %s)", bool(lockUI), bool(blackFrame));
    }

    public void handleInGameDirectionEvent(InPacket inPacket) {
        byte val = inPacket.decodeByte();
        InGameDirectionEventType igdrType = InGameDirectionEventType.getByVal(val);
        if (igdrType == null) {
            System.out.printf("Unk igdr %d! Inpacket = %s%n", val, inPacket);
            return;
        }
        switch (igdrType) {
            case ForcedAction:
                int action = inPacket.decodeInt();
                int direction = inPacket.decodeInt();
                sm.addLine("sm.forcedAction(%d, %d)", action, direction);
                break;
            case Delay:
                sm.addLine("sm.sendDelay(%d)", inPacket.decodeInt());
                break;
            case EffectPlay:
                String effectUOL = inPacket.decodeString();
                int duration = inPacket.decodeInt();
                int x = inPacket.decodeInt();
                int y = inPacket.decodeInt();
                int z = 0;
                if (inPacket.decodeByte() != 0) {
                    z = inPacket.decodeInt();
                }
                int npcID = 0;
                byte arg4 = 0;
                byte arg5 = 0;
                if (inPacket.decodeByte() != 0) {
                    npcID = inPacket.decodeInt();
                    arg4 = inPacket.decodeByte();
                    arg5 = inPacket.decodeByte();
                }
                sm.addLine("sm.showEffect(\"%s\", %d, %d, %d, %d, %d, %d, %d)", effectUOL, duration, x, y, z, npcID,
                        arg4, arg5);
                break;
            case ForcedInput:
                sm.addLine("sm.forcedInput(%d)", inPacket.decodeInt());
                break;
            case PatternInputRequest:
                sm.addLine("sm.patternInputRequest(\"%s\", %d, %d, %d)", inPacket.decodeString(), inPacket.decodeInt(),
                        inPacket.decodeInt(), inPacket.decodeInt());
                break;
            case CameraMove:
                boolean back = inPacket.decodeByte() != 0;
                int pixelPerSec = inPacket.decodeInt();
                x = 0;
                y = 0;
                if (!back) {
                    x = inPacket.decodeInt();
                    y = inPacket.decodeInt();
                }
                sm.addLine("sm.moveCamera(%s, %d, %d, %d)", bool(back), pixelPerSec, x, y);
                break;
            case CameraOnCharacter:
                sm.addLine("sm.setCameraOnNpc(%d)", inPacket.decodeInt());
                break;
            case CameraZoom:
                sm.addLine("sm.zoomCamera(%d, %d, %d, %d, %d)", inPacket.decodeInt(), inPacket.decodeInt(),
                        inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt());
                break;
            case VansheeMode:
                sm.addLine("sm.hideUser(%s)", bool(inPacket.decodeByte() != 0));
                break;
            case FaceOff:
                sm.addLine("sm.faceOff(%d)", inPacket.decodeInt());
                break;
            case Monologue:
                sm.addLine("sm.sayMonologue(\"%s\", %s)", inPacket.decodeString(), inPacket.decodeByte());
                break;
            case MonologueScroll:
                sm.addLine("sm.monologueScroll(\"%s\", %s, %d, %d, %d)", inPacket.decodeString(), bool(inPacket.decodeByte() != 0),
                        inPacket.decodeShort(), inPacket.decodeInt(), inPacket.decodeInt());
                break;
            case AvatarLookSet:
                int[] equips = new int[inPacket.decodeByte()];
                StringBuilder equipStr = new StringBuilder("[");
                for (int i = 0; i < equips.length; i++) {
                    equips[i] = inPacket.decodeInt();
                    equipStr.append(equips[i]).append(", ");
                }
                equipStr.append("]");
                sm.addLine("equips = %s", equipStr);
                sm.addLine("sm.avatarLookSet(equips)");
                break;
            case RemoveAdditionalEffect:
            case RemoveAdditionalEffect_2:
                sm.addLine("sm.removeAdditionalEffect()");
                break;
            case ForcedMove:
                // == 1 = move left, == 2 = move right
                sm.addLine("sm.forcedMove(%s, %d)", bool(inPacket.decodeInt() == 1), inPacket.decodeInt());
                break;
            case ForcedFlip:
                sm.addLine("sm.forcedFlip(%s)", bool(inPacket.decodeInt()));
                break;
            default:
                log.error("Unhandled igdrType " + igdrType + ", packet = " + inPacket);
        }
    }

    private String bool(boolean b) {
        return b ? "True" : "False";
    }

    private String bool(int b) {
        return b != 0 ? "True" : "False";
    }

    public void handleOpenUI(InPacket inPacket) {
        sm.addLine("sm.openUI(%d)", inPacket.decodeInt());
    }

    public void handleCloseUI(InPacket inPacket) {
        sm.addLine("sm.closeUI(%d)", inPacket.decodeInt());
    }

    public void handleEffect(InPacket inPacket) {
        byte type = inPacket.decodeByte();
        UserEffectType uet = UserEffectType.getByVal(type);
        if (uet == null) {
            log.error("Unknown user effect type " + type + ", packet = " + inPacket);
            return;
        }
        switch (uet) {
            case SkillSpecial:
                int skillId = inPacket.decodeInt();
                break;
            case EffectUOL:
                String str = inPacket.decodeString();
                byte arg1 = inPacket.decodeByte();
                int arg2 = inPacket.decodeInt();
                int arg3 = inPacket.decodeInt();
                int itemID = 0;
                if (arg3 == 2) {
                    itemID = inPacket.decodeInt();
                }
                sm.addLine("sm.showEffect(\"%s\", %d, %d)", str, arg2, arg3);
                break;
            case AvatarOriented:
                sm.addLine("sm.avatarOriented(\"%s\")", inPacket.decodeString());
                break;
            case ReservedEffect:
                sm.addLine("sm.reservedEffect(%s, %d, %d, \"%s\")", bool(inPacket.decodeByte()),
                        inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeString());
                break;
            case PlayExclSoundWithDownBGM:
                sm.addLine("sm.playExclSoundWithDownBGM(\"%s\", %s)", inPacket.decodeString(), inPacket.decodeInt());
                break;
            case BlindEffect:
                sm.addLine("sm.blindEffect(%s)", bool(inPacket.decodeByte()));
                break;
            case FadeInOut:
                sm.addLine("sm.fadeInOut(%d, %d, %d, %d)", inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt(),
                        inPacket.decodeInt());
                break;
            case ReservedEffectRepeat:
                String eff = inPacket.decodeString();
                boolean hasDuration = inPacket.decodeByte() == 0;
                boolean show = false;
                int x = 0;
                int y = 0;
                int duration = 0;
                boolean unk = false;
                if (!hasDuration) {
                    unk = inPacket.decodeByte() != 0;
                    if (unk) {
                        show = inPacket.decodeByte() != 0;
                        x = inPacket.decodeInt();
                        y = inPacket.decodeInt();
                    }
                } else {
                    duration = inPacket.decodeByte();
                    x = inPacket.decodeInt();
                    y = inPacket.decodeInt();
                }
                sm.addLine("sm.reservedEffectRepeat(\"%s\", %s, %s, %d, %d, %d)",
                        eff, bool(unk), bool(show), x, y, duration);
                break;
            case SpeechBalloon:
                boolean normal = inPacket.decodeByte() != 0;
                int idx = inPacket.decodeInt();
                int linkType = inPacket.decodeInt();
                String speech = inPacket.decodeString();
                int time = inPacket.decodeInt();
                int align = inPacket.decodeInt();
                x = inPacket.decodeInt();
                y = inPacket.decodeInt();
                int z = inPacket.decodeInt();
                int lineSpace = inPacket.decodeInt();
                int npcTemplateId = 0;
                if (!"".equals(speech)) {
                    npcTemplateId = inPacket.decodeInt();
                }
                int idk = inPacket.decodeInt();
                sm.addLine("sm.speechBalloon(%s, %d, %d, \"%s\", %d, %d, %d, %d, %d, %d, %d, %d)", bool(normal), idx,
                        linkType, speech, time, align, x, y, z, lineSpace, npcTemplateId, idk);
                break;
            case TextEffect:
                sm.addLine("sm.createFieldTextEffect(\"%s\", %d, %d, %d, %d, %d, %d, %d, %d, %d, %d)",
                        inPacket.decodeString(), inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt(),
                        inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt(),
                        inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeInt());
                break;
            // ignored uets
            case Quest:
            case QuestComplete:
            case PickUpItem:
            case SkillAffected:
            case SkillUse:
            case SkillUseBySummoned:
            case ResetOnStateForOnOffSkill:
                break;
            default:
                log.error("Unhandled user effect type " + uet + ", packet = " + inPacket);
        }
    }

    public void handleFieldEffect(InPacket inPacket) {
        byte type = inPacket.decodeByte();
        FieldEffectType fet = FieldEffectType.getByVal(type);
        if (fet == null) {
            log.error("Unknown field effect type " + type + ", packet = " + inPacket);
            return;
        }
        switch (fet) {
            case ScreenAutoLetterBox:
                sm.addLine("sm.showFieldEffect(\"%s\", %d)", inPacket.decodeString(), inPacket.decodeInt());
                break;
            case TopScreen:
                sm.addLine("sm.sm.getOffFieldEffectFromWz(\"%s\")", inPacket.decodeString());
                break;
            case TopScreenDelayed:
                sm.addLine("sm.getOffFieldEffectFromWz(\"%s\", %d)", inPacket.decodeString(), inPacket.decodeInt());
                break;
            case ScreenDelayed:
                sm.addLine("showFieldBackgroundEffect(\"%s\", %d)", inPacket.decodeString(), inPacket.decodeInt());
                break;
            case Overlap:
                sm.addLine("sm.showFade(%d)", inPacket.decodeInt());
                break;
            case OverlapDetail:
                sm.addLine("sm.showFadeTransition(%d, %d, %d)", inPacket.decodeInt(), inPacket.decodeInt(),
                        inPacket.decodeInt());
                break;
            case ColorChange:
                sm.addLine("sm.setFieldColour(%d, %d, %d, %d, %d)", GreyFieldType.byVal(inPacket.decodeShort()),
                        inPacket.decodeShort(), inPacket.decodeShort(), inPacket.decodeShort(), inPacket.decodeInt());
                break;
            case Blind:
                sm.addLine("sm.blind(%s, %d, %d, %d, %d, %d)", bool(inPacket.decodeByte()), inPacket.decodeShort(),
                        inPacket.decodeShort(), inPacket.decodeShort(), inPacket.decodeShort(), inPacket.decodeInt());
                break;
            case GreyScale:
                sm.addLine("sm.setFieldGrey(GreyFieldType.%s, %s)", GreyFieldType.byVal(inPacket.decodeShort()),
                        bool(inPacket.decodeByte()));
                break;
            case RemoveOverlapDetail:
                sm.addLine("sm.removeOverlapScreen(%d)", inPacket.decodeInt());
                break;
            case PlaySound:
                sm.addLine("sm.playSound(\"%s\", %d)", inPacket.decodeString(), inPacket.decodeInt());
                break;
            case OnOffLayer:
                type = inPacket.decodeByte();    // nType (0 = On, 1 = Move, 2 = Off)
                int duration = inPacket.decodeInt();     // tDuration
                String key = inPacket.decodeString();// sKey
                int x, y, z;
                if (type == 1) {
                    x = inPacket.decodeInt(); // nDX
                    y = inPacket.decodeInt(); // nDY
                    sm.addLine("sm.moveLayer(%d, \"%s\", %d, %d)", duration, key, x, y);
                } else if (type == 0) {
                    x = inPacket.decodeInt(); // nRX
                    y = inPacket.decodeInt(); // nRY
                    z = inPacket.decodeInt(); // nZ
                    String origin = inPacket.decodeString(); // pOrigin
                    int org = inPacket.decodeInt(); // nOrigin
                    boolean postRender = inPacket.decodeByte() != 0; // bPostRender
                    int idk = inPacket.decodeInt();
                    boolean idk2 = inPacket.decodeByte() != 0;
                    sm.addLine("sm.onLayer(%d, \"%s\", %d, %d, %d, \"%s\", %d, %s, %d, %s)", duration, key,
                            x, y, z, origin, org, bool(postRender), idk, bool(idk2));
                } else if (type == 2) {
                    sm.addLine("sm.offLayer(%d, \"%s\", %s)", duration, key, bool(inPacket.decodeByte()));
                }
                break;
            case ChangeBGM:
                sm.addLine("sm.changeBGM(\"%s\", %d, %d)", inPacket.decodeString(), inPacket.decodeInt(), inPacket.decodeInt());
                break;
            case BGMVolumeOnly:
                sm.addLine("sm.bgmVolumeOnly(%s)", bool(inPacket.decodeByte()));
                break;
            case BGMVolume:
                sm.addLine("sm.bgmVolume(%d, %d)", inPacket.decodeInt(), inPacket.decodeInt());
                break;
            case SpineScreen:
                boolean binary = inPacket.decodeByte() != 0;
                boolean loop = inPacket.decodeByte() != 0;
                boolean postRender = inPacket.decodeByte() != 0;
                int endDelay = inPacket.decodeInt();
                String path = inPacket.decodeString();
                String aniName = inPacket.decodeString();
                boolean hasKeyName = inPacket.decodeByte() != 0;
                String keyName = "";
                if (hasKeyName) {
                    keyName = inPacket.decodeString();
                }
                sm.addLine("sm.spineScreen(%s, %s, %s, %d, \"%s\",\"%s\",\"%s\")", bool(binary), bool(loop), bool(postRender),
                        endDelay, path, aniName, keyName);
                break;
            case OffSpineScreen:
                keyName = inPacket.decodeString();
                int spineType = inPacket.decodeInt();
                int alphaTime = 0;
                aniName = "";
                if (spineType == 1) {
                    alphaTime = inPacket.decodeInt();
                } else if (spineType == 2) {
                    aniName = inPacket.decodeString();
                }
                sm.addLine("sm.offSpineScreen(\"%s\", %d, \"%s\", %d)", keyName, spineType, aniName, alphaTime);
                break;
            default:
                log.error("Unhandled field effect type " + fet + ", packet = " + inPacket);
        }
    }

    public void handleBalloonMsg(InPacket inPacket) {
        sm.addLine("sm.balloonMsg(\"%s\")", inPacket.decodeString());
    }

    public void handleProgressMessageFont(InPacket inPacket) {
        sm.addLine("sm.progressMessageFont(%d, %d, %d, %d, \"%s\")", inPacket.decodeInt(), inPacket.decodeInt(),
                inPacket.decodeInt(), inPacket.decodeInt(), inPacket.decodeString());
    }

    public void handleNpcEnterField(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        int templateId = inPacket.decodeInt();
        Position pos = inPacket.decodePosition();
        if (sm.addNpc(objId, templateId) && sm.isInDirectionMode()) {
            sm.addLine("sm.spawnNpc(%d, %d, %d)", templateId, pos.getX(), pos.getY());
        }
        sm.addNpc(objId, templateId);
    }

    public void handleNpcChangeController(InPacket inPacket) {
        boolean controller = inPacket.decodeByte() != 0;
        int objId = inPacket.decodeInt();
        if (inPacket.getUnreadAmount() > 0) {
            int templateId = inPacket.decodeInt();
            Position pos = inPacket.decodePosition();
            if (sm.addNpc(objId, templateId) && sm.isInDirectionMode()) {
                sm.addLine("sm.spawnNpc(%d, %d, %d)", templateId, pos.getX(), pos.getY());
            }
            sm.addNpc(objId, templateId);
        }
    }

    public void handleNpcViewOrHide(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        int id = sm.getNpcTemplate(objId);
        if (id >= 0) {
            sm.addLine("sm.hideNpcByTemplateIdhi(%d, %s, %s)", id, bool(inPacket.decodeByte() == 0),
                    bool(inPacket.decodeByte() == 0));
        } else {
            log.error("Unknown npc id");
        }
    }

    public void handleNpcSetForceMove(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        int dir = inPacket.decodeInt();
        int dist = inPacket.decodeInt();
        int speed = inPacket.decodeInt();
        int id = sm.getNpcTemplate(objId);
        if (id >= 0) {
            sm.addLine("sm.moveNpcByTemplateId(%d, %s, %d, %d)", id, bool(dir == -1), dist, speed);
        } else {
            log.error("Unknown npc id");
        }
    }

    public void handleNpcSetForceFlip(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        int dir = inPacket.decodeInt();
        int id = sm.getNpcTemplate(objId);
        if (id >= 0) {
            sm.addLine("sm.flipNpcByTemplateId(%d, %s)", id, bool(dir == -1));
        } else {
            log.error("Unknown npc id");
        }
    }

    public void handleNpcSetSpecialAction(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        String str = inPacket.decodeString();
        int duration = inPacket.decodeInt();
        int id = sm.getNpcTemplate(objId);
        if (id >= 0) {
            sm.addLine("sm.showNpcSpecialActionByTemplateId(%d, \"%s\", %d)", id, str, duration);
        } else {
            log.error("Unknown npc id");
        }
    }

    public void handleNpcResetSpecialAction(InPacket inPacket) {
        int objId = inPacket.decodeInt();
        int id = sm.getNpcTemplate(objId);
        if (id >= 0) {
            sm.addLine("sm.resetNpcSpecialActionByTemplateId(%d)", id);
        } else {
            log.error("Unknown npc id");
        }
    }

    public void handleMessage(InPacket inPacket) {
        byte type = inPacket.decodeByte();
        MessageType mt = MessageType.getByVal(type);
        if (mt == null) {
            log.error("Unknown message type " + mt + ", packet = " + inPacket);
            return;
        }
        switch (mt) {
            case QUEST_RECORD_MESSAGE:
                int key = inPacket.decodeInt();
                if (sm.hasSelfStartedQuest(key)) {
                    return;
                }
                String keyName = key + "";
                if (key == sm.getId()) {
                    keyName = "parentID";
                }
                byte status = inPacket.decodeByte();
                switch (status) {
                    case 1: // Started
                        sm.addLine("sm.startQuest(%s)", keyName);
                        break;
                    case 2: // Completed
                        sm.addLine("sm.completeQuestNoCheck(%s)", keyName);
                        break;
                }
                break;
            case QUEST_RECORD_EX_MESSAGE:
                key = inPacket.decodeInt();
                String value = inPacket.decodeString();
                if (sm.hasSelfStartedQuest(key)) {
                    return;
                }
                keyName = key + "";
                if (key == sm.getId()) {
                    keyName = "parentID";
                }
                sm.addLine("sm.createQuestWithQRValue(%s, \"%s\")", keyName, value);
                break;
        }
    }

    public void handleSetMapTaggedObjectVisible(InPacket inPacket) {
        byte size = inPacket.decodeByte(); // always 1?

        String tag = inPacket.decodeString();
        boolean visible = inPacket.decodeByte() != 0;
        int manual = inPacket.decodeInt();
        int delay = inPacket.decodeInt();

        sm.addLine("sm.setMapTaggedObjectVisible(\"%s\", %s, %d, %d)", tag, bool(visible), manual, delay);
    }
}
