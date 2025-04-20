package net.swordie.ms.scripts;

import net.swordie.ms.Server;
import net.swordie.ms.ServerConfig;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.alliance.AllianceResult;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.MonsterPark;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.damage.DamageSkinType;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Inventory;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.items.ItemBuffs;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.potential.CharacterPotentialMan;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.scene.Scene;
import net.swordie.ms.client.character.skills.MatrixRecord;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.character.union.Union;
import net.swordie.ms.client.character.union.UnionMember;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.guild.result.GuildType;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.trunk.TrunkOpen;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Reactor;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.life.npc.NpcMessageType;
import net.swordie.ms.life.npc.NpcScriptInfo;
import net.swordie.ms.loaders.*;
import net.swordie.ms.loaders.containerclasses.Cosmetic;
import net.swordie.ms.loaders.containerclasses.VCoreInfo;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.*;
import net.swordie.ms.world.field.bosses.gollux.FallingCatcher;
import net.swordie.ms.world.field.bosses.gollux.GolluxMiniMapFieldClearType;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.field.fieldeffect.GreyFieldType;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleInRowInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;
import net.swordie.ms.world.shop.NpcShopDlg;
import org.apache.log4j.LogManager;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.script.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.RideVehicle;
import static net.swordie.ms.enums.ChatType.*;
import static net.swordie.ms.life.npc.NpcMessageType.*;

/**
 * Created on 2/19/2018.
 *
 * @see ScriptManager
 */
public class ScriptManagerImpl implements ScriptManager {

    public static final String SCRIPT_ENGINE_NAME = "python";
    private static final String SCRIPT_ENGINE_EXTENSION = ".py";
    private static final String DEFAULT_SCRIPT = "undefined";
    public static final String QUEST_START_SCRIPT_END_TAG = "s";
    public static final String QUEST_COMPLETE_SCRIPT_END_TAG = "e";
    private static final String INTENDED_NPE_MSG = "Intended NPE by forceful script stop.";
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();

    private static final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NAME);

    private Char chr;
    private Field field;
    private final boolean isField;
    private NpcScriptInfo npcScriptInfo;
    private Map<ScriptType, ScriptInfo> scripts;
    private int returnField = 0;
    private ScriptType lastActiveScriptType;
    private Map<ScriptType, Future> evaluations = new HashMap<>();
    private Set<ScheduledFuture> events = new HashSet<>();
    private ScriptMemory memory = new ScriptMemory();
    private boolean curNodeEventEnd;
    private static final Lock fileReadLock = new ReentrantLock();
    private FieldTransferInfo fieldTransferInfo = new FieldTransferInfo();
    private Field fieldReference;

    private ScriptManagerImpl(Char chr, Field field) {
        this.chr = chr;
        this.field = field;
        this.npcScriptInfo = new NpcScriptInfo();
        this.scripts = new HashMap<>();
        this.isField = chr == null;
        this.lastActiveScriptType = ScriptType.None;
    }

    public ScriptManagerImpl(Char chr) {
        this(chr, chr.getField());
    }

    public ScriptManagerImpl(Field field) {
        this(null, field);
    }

    public void setFieldReference(Field fld) {
        fieldReference = fld;
    }

    public Field getFieldReference() {
        if (fieldReference != null) {
            return fieldReference;
        }
        return field;
    }

    private Bindings getBindingsByType(ScriptType scriptType) {
        ScriptInfo si = getScriptInfoByType(scriptType);
        return si == null ? null : si.getBindings();
    }

    public ScriptInfo getScriptInfoByType(ScriptType scriptType) {
        return scripts.getOrDefault(scriptType, null);
    }

    @Override
    public Char getChr() {
        return chr;
    }

    public String getScriptNameByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getScriptName();
    }

    public Invocable getInvocableByType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType).getInvocable();
    }

    public int getParentIDByScriptType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType) != null ? getScriptInfoByType(scriptType).getParentID() : 2007;
    }

    public int getObjectIDByScriptType(ScriptType scriptType) {
        return getScriptInfoByType(scriptType) != null ? getScriptInfoByType(scriptType).getObjectID() : 0;
    }

    public void startScript(String scriptName, String scriptType) {
        ScriptType st = null;
        for (ScriptType type : ScriptType.values()) {
            if (type.toString().equalsIgnoreCase(scriptType)) {
                st = type;
                break;
            }
        }
        if (st == null) {
            StringBuilder str = new StringBuilder();
            for (ScriptType t : ScriptType.values()) {
                str.append(t.toString()).append(", ");
            }
            String res = str.substring(0, str.length() - 2);
            chr.chatMessage(String.format("Unknown script type %s, known types: %s", scriptType, res));
            return;
        }
        chr.getScriptManager().startScript(0, scriptName, st);
    }

    public void startScript(int parentID, ScriptType scriptType) {
        startScript(parentID, 0, scriptType);
    }

    public void startScript(int parentID, String scriptName, ScriptType scriptType) {
        startScript(parentID, 0, scriptName, scriptType);
    }

    public void startScript(int parentID, int objID, ScriptType scriptType) {
        startScript(parentID, objID, parentID + ".py", scriptType);
    }

    public void startScript(int parentID, int objID, String scriptName, ScriptType scriptType) {
        if (scriptType == ScriptType.None || (scriptType == ScriptType.Quest && !isQuestScriptAllowed())) {
            log.debug(String.format("Did not allow script %s to go through (type %s)  |  Active Script Type: %s", scriptName, scriptType, getLastActiveScriptType()));
            return;
        }
        setLastActiveScriptType(scriptType);
        if (isActive(scriptType) && (scriptType != ScriptType.Field && scriptType != ScriptType.FirstEnterField)) { // because Field Scripts don't get disposed.
            chr.chatMessage(String.format("Already running a script of the same type (%s, id %d)! Type @check if this" +
                    " is not intended.", scriptType.getDir(), getScriptInfoByType(scriptType).getParentID()));
            log.debug(String.format("Could not run script %s because one of the same type is already running (%s, type %s)",
                    scriptName, getScriptInfoByType(scriptType).getScriptName(), scriptType));
            return;
        }
        if (!isField()) {
            chr.chatMessage(Mob, String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
            log.debug(String.format("Starting script %s, scriptType %s.", scriptName, scriptType));
        }
        resetParam();
        Bindings bindings = getBindingsByType(scriptType);
        if (bindings == null) {
            bindings = scriptEngine.createBindings();
            bindings.put("sm", this);
            bindings.put("chr", chr);
        }
        bindings.put("field", chr == null ? field : chr.getField());
        bindings.put("parentID", parentID);
        bindings.put("scriptType", scriptType);
        bindings.put("objectID", objID);
        if (scriptType == ScriptType.Reactor) {
            bindings.put("reactor", chr.getField().getLifeByObjectID(objID));
        }
        if (scriptType == ScriptType.Quest) {
            bindings.put("startQuest",
                    scriptName.charAt(scriptName.length() - 1) == QUEST_START_SCRIPT_END_TAG.charAt(0)); // biggest hack eu
        }
        ScriptInfo scriptInfo = new ScriptInfo(scriptType, bindings, parentID, scriptName);
        if (scriptType == ScriptType.Npc) {
            getNpcScriptInfo().setTemplateID(parentID);
        }
        scriptInfo.setObjectID(objID);
        getScripts().put(scriptType, scriptInfo);
        EventManager.addEvent(() -> startScript(scriptName, scriptType), 0); // makes the script execute async
    }

    private boolean isQuestScriptAllowed() {
        return getLastActiveScriptType() == ScriptType.None;
    }

    private void notifyMobDeath(Mob mob) {
        if (isActive(ScriptType.FirstEnterField)) {
            getScriptInfoByType(ScriptType.FirstEnterField).addResponse(mob);
        } else if (isActive(ScriptType.Field)) {
            getScriptInfoByType(ScriptType.Field).addResponse(mob);
        }
    }

    private void startScript(String name, ScriptType scriptType) {
        String dir = String.format("%s/%s/%s%s", ServerConstants.SCRIPT_DIR,
                scriptType.getDir().toLowerCase(), name, SCRIPT_ENGINE_EXTENSION);
        boolean exists = new File(dir).exists();
        if (!exists) {
            log.error(String.format("[Error] Could not find script %s/%s", scriptType.getDir().toLowerCase(), name));
            if (chr != null) {
                chr.chatMessage(Mob, String.format("[Script] Could not find script %s/%s", scriptType.getDir().toLowerCase(), name));
            }
            dir = String.format("%s/%s/%s%s", ServerConstants.SCRIPT_DIR,
                    scriptType.getDir().toLowerCase(), DEFAULT_SCRIPT, SCRIPT_ENGINE_EXTENSION);
        }
        ScriptInfo si = getScriptInfoByType(scriptType);
        si.setActive(true);
        CompiledScript cs;
        getScriptInfoByType(scriptType).setFileDir(dir);
        StringBuilder script = new StringBuilder();
        ScriptEngine se = scriptEngine;
        Bindings bindings = getBindingsByType(scriptType);
        si.setInvocable((Invocable) se);
        try {
            fileReadLock.lock();
            script.append(Util.readFile(dir, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
            lockInGameUI(false); // so players don't get stuck if a script fails
        } finally {
            fileReadLock.unlock();
        }
        try {
            cs = ((Compilable) se).compile(script.toString());
            cs.eval(bindings);
        } catch (ScriptException e) {
            if (!e.getMessage().contains(INTENDED_NPE_MSG)) {
                log.error(String.format("Unable to compile script %s!", name));
                e.printStackTrace();
                lockInGameUI(false); // so players don't get stuck if a script fails
            }
        } finally {
            if (si.isActive() && name.equals(si.getScriptName()) &&
                    ((scriptType != ScriptType.Field && scriptType != ScriptType.FirstEnterField)
                            || (chr != null && chr.getFieldID() == si.getParentID()))) {
                // gracefully stop script if it's still active with the same script info (scriptName, or scriptName +
                // current chr fieldID == fieldscript's fieldID if scriptType == Field).
                // This makes it so field scripts won't cancel new field scripts when having a warp() in them.
                stop(scriptType);
            }
            FieldTransferInfo fti = getFieldTransferInfo();
            if (!fti.isInit()) {
                if (fti.isField()) {
                    fti.warp(field);
                } else {
                    fti.warp(chr);
                }
            }
        }
    }

    public void stop(ScriptType scriptType) {
        setSpeakerID(0);
        if (getLastActiveScriptType() == scriptType) {
            setLastActiveScriptType(ScriptType.None);
        }
        ScriptInfo si = getScriptInfoByType(scriptType);
        if (si != null) {
            si.reset();
        }
        getNpcScriptInfo().reset();
        getMemory().clear();
        if (chr != null) {
            chr.dispose();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mob) {
            notifyMobDeath((Mob) o);
        }
    }

    public void handleAction(NpcMessageType lastType, byte response, int answer) {
        handleAction(getLastActiveScriptType(), lastType, response, answer, null);
    }

    public void handleAction(NpcMessageType lastType, byte response, String text) {
        handleAction(getLastActiveScriptType(), lastType, response, 0, text);
    }

    public void handleAction(ScriptType scriptType, NpcMessageType lastType, byte response, int answer, String text) {
        switch (response) {
            case -1:
            case 5:
                stop(scriptType);
                break;
            default:
                ScriptMemory sm = getMemory();
                if (lastType.isPrevPossible() && response == 0) {
                    // back button pressed
                    NpcScriptInfo prev = sm.getPreviousScriptInfo();
                    chr.write(ScriptMan.scriptMessage(prev, prev.getMessageType()));
                } else {
                    if (getMemory().isInMemory()) {
                        NpcScriptInfo next = sm.getNextScriptInfo();
                        chr.write(ScriptMan.scriptMessage(next, next.getMessageType()));
                    } else {
                        ScriptInfo si = getScriptInfoByType(scriptType);
                        if (isActive(scriptType)) {
                            switch (lastType.getResponseType()) {
                                case Response:
                                    si.addResponse((int) response);
                                    break;
                                case Answer:
                                    si.addResponse(answer);
                                    break;
                                case Text:
                                    si.addResponse(text);
                                    break;
                            }
                        }
                    }
                }
        }
    }

    public boolean isActive(ScriptType scriptType) {
        return getScriptInfoByType(scriptType) != null && getScriptInfoByType(scriptType).isActive();
    }

    public NpcScriptInfo getNpcScriptInfo() {
        return npcScriptInfo;
    }

    public Map<ScriptType, ScriptInfo> getScripts() {
        return scripts;
    }

    public int getParentID() {
        int res = 0;
        for (ScriptType type : ScriptType.values()) {
            if (getScriptInfoByType(type) != null) {
                res = getScriptInfoByType(type).getParentID();
            }
        }
        return res;
    }

    public boolean isField() {
        return isField;
    }

    public Field getField() {
        return field;
    }

    public ScriptType getLastActiveScriptType() {
        return lastActiveScriptType;
    }

    public void setLastActiveScriptType(ScriptType lastActiveScriptType) {
        this.lastActiveScriptType = lastActiveScriptType;
    }

    public FieldTransferInfo getFieldTransferInfo() {
        return fieldTransferInfo;
    }

    // Start of the sends/asks -----------------------------------------------------------------------------------------

    @Override
    public int sendSay(String text) {
        if (getLastActiveScriptType() == ScriptType.None) {
            return 0;
        }
        return sendGeneralSay(text, Say);
    }

    /**
     * Helper function that ensures that selections have the appropriate type (AskMenu).
     *
     * @param text
     * @param nmt
     */
    private int sendGeneralSay(String text, NpcMessageType nmt) throws NullPointerException {
        getNpcScriptInfo().setText(text);
        String checkText = text.replaceAll("[\r\n]", "");
        if (checkText.matches("(.)*#[lL][0-9]+#(.)*")) {
            nmt = AskMenu;
        }
        getNpcScriptInfo().setMessageType(nmt);
        chr.write(ScriptMan.scriptMessage(getNpcScriptInfo(), nmt));
        getMemory().addMemoryInfo(getNpcScriptInfo());
        Object response = null;
        if (isActive(getLastActiveScriptType())) {
            response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        }
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    @Override
    public int sendNext(String text) {
        return sendGeneralSay(text, SayNext);
    }

    @Override
    public int sendPrev(String text) {
        return sendGeneralSay(text, SayPrev);
    }

    @Override
    public int sendSayOkay(String text) {
        return sendGeneralSay(text, SayOk);
    }

    @Override
    public int sendSayImage(String image) {
        return sendSayImage(new String[]{image});
    }

    @Override
    public int sendSayImage(String[] images) {
        getNpcScriptInfo().setImages(images);
        getNpcScriptInfo().setMessageType(SayImage);
        return sendGeneralSay("", SayImage);
    }

    @Override
    public boolean sendAskYesNo(String text) {
        return sendGeneralSay(text, AskYesNo) != 0;
    }

    @Override
    public boolean sendAskAccept(String text) {
        return sendGeneralSay(text, AskAccept) != 0;
    }

    @Override
    public String sendAskText(String text, String defaultText, short minLength, short maxLength) throws NullPointerException {
        getNpcScriptInfo().setMin(minLength);
        getNpcScriptInfo().setMax(maxLength);
        getNpcScriptInfo().setDefaultText(defaultText);
        getNpcScriptInfo().setText(text);
        getNpcScriptInfo().setMessageType(AskText);
        chr.write(ScriptMan.scriptMessage(getNpcScriptInfo(), AskText));
        getMemory().addMemoryInfo(getNpcScriptInfo());
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException("Intended");
        }
        return (String) response;
    }

    @Override
    public int sendAskNumber(String text, int defaultNum, int min, int max) {
        getNpcScriptInfo().setDefaultNumber(defaultNum);
        getNpcScriptInfo().setMin(min);
        getNpcScriptInfo().setMax(max);
        return sendGeneralSay(text, AskNumber);
    }

    @Override
    public int sendInitialQuiz(byte type, String title, String problem, String hint, int min, int max, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if (type != 1) {
            nsi.setTitle(title);
            nsi.setProblemText(problem);
            nsi.setHintText(hint);
            nsi.setMin(min);
            nsi.setMax(max);
            nsi.setTime(time);
        }
        return sendGeneralSay(title, InitialQuiz);
    }

    @Override
    public int sendInitialSpeedQuiz(byte type, int quizType, int answer, int correctAnswers, int remaining, int time) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setType(type);
        if (type != 1) {
            nsi.setQuizType(quizType);
            nsi.setAnswer(answer);
            nsi.setCorrectAnswers(correctAnswers);
            nsi.setRemaining(remaining);
            nsi.setTime(time);
        }
        return sendGeneralSay("", InitialSpeedQuiz);
    }

    @Override
    public int sendICQuiz(byte type, String text, String hintText, int time) {
        getNpcScriptInfo().setType(type);
        getNpcScriptInfo().setHintText(hintText);
        getNpcScriptInfo().setTime(time);
        return sendGeneralSay(text, ICQuiz);
    }

    @Override
    public int sendAskAvatar(String text, boolean angelicBuster, boolean zeroBeta, int... options) {
        getNpcScriptInfo().setAngelicBuster(angelicBuster);
        getNpcScriptInfo().setZeroBeta(zeroBeta);
        getNpcScriptInfo().setOptions(options);
        return sendGeneralSay(text, AskAvatar);
    }

    public int sendAskSlideMenu(int dlgType) {
        getNpcScriptInfo().setDlgType(dlgType);
        return sendGeneralSay("", AskSlideMenu);
    }

    public int sendAskSelectMenu(int dlgType, int defaultSelect) {
        return sendAskSelectMenu(dlgType, defaultSelect, new String[]{});
    }

    public int sendAskSelectMenu(int dlgType, int defaultSelect, String[] text) {
        getNpcScriptInfo().setDlgType(dlgType);
        getNpcScriptInfo().setDefaultSelect(defaultSelect);
        getNpcScriptInfo().setSelectText(text);
        return sendGeneralSay("", AskSelectMenu);
    }


    // Start of param methods ------------------------------------------------------------------------------------------

    public void setParam(int param) {
        getNpcScriptInfo().setParam((short) param);
    }

    public void setColor(int color) {
        getNpcScriptInfo().setColor((byte) color);
    }

    public void resetParam() {
        getNpcScriptInfo().resetParam();
    }

    public void removeEscapeButton() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.NotCancellable);
    }

    public void addEscapeButton() {
        if (getNpcScriptInfo().hasParam(NpcScriptInfo.Param.NotCancellable)) {
            getNpcScriptInfo().removeParam(NpcScriptInfo.Param.NotCancellable);
        }
    }

    public void flipSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.FlipSpeaker);
    }

    public void flipDialogue() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.OverrideSpeakerID);
    }

    public void flipDialoguePlayerAsSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.PlayerAsSpeakerFlip);
    }

    public void setPlayerAsSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.PlayerAsSpeaker);
    }

    public void setBoxChat() {
        setBoxChat(true);
    }

    public void setBoxChat(boolean color) { // true = Standard BoxChat  |  false = Zero BoxChat
        getNpcScriptInfo().setColor((byte) (color ? 1 : 0));
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.BoxChat);
    }

    public void setBoxOverrideSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.BoxChatOverrideSpeaker);
    }

    public void setIntroBoxChat(int npcID) {
        setSpeakerID(npcID);
        getNpcScriptInfo().setColor((byte) 1);
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.BoxChatOverrideSpeakerNoEndChat);
    }

    public void setNpcOverrideBoxChat(int npcID) {
        setSpeakerID(npcID);
        getNpcScriptInfo().setColor((byte) 1);
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.BoxChatOverrideSpeakerNoEndChat);
    }

    public void flipBoxChat() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.FlipBoxChat);
    }

    public void boxChatPlayerAsSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.BoxChatAsPlayer);
    }

    public void flipBoxChatPlayerAsSpeaker() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.FlipBoxChatAsPlayer);
    }

    public void flipBoxChatPlayerNoEscape() {
        getNpcScriptInfo().addParam(NpcScriptInfo.Param.FlipBoxChatAsPlayerNoEscape);
    }

    public final String options(final String... vals) {
        final StringBuilder menu = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            menu.append("#L").append(i).append("#").append(vals[i]).append("#l").append(i == vals.length - 1 ? "" : "\r\n");
        }
        return menu.toString();
    }


    // Start helper methods for scripts --------------------------------------------------------------------------------

    @Override
    public void dispose() {
        dispose(false);
    }

    public void dispose(boolean stop) {
        if (chr != null) {
            chr.setTalkingToNpc(false);
        }
        getNpcScriptInfo().reset();
        getMemory().clear();
        stop(ScriptType.Npc);
        stop(ScriptType.Portal);
        stop(ScriptType.Item);
        stop(ScriptType.Quest);
        stop(ScriptType.Reactor);
        if (getLastActiveScriptType() == ScriptType.Field) {
            // only fields are able to stop themselves, otherwise things like npcs would stop field scripts
            // like magnus leave script would make the orbs disappear if you don't actually leave
            stop(ScriptType.Field);
        }
        if (stop) {
            throw new NullPointerException(INTENDED_NPE_MSG); // makes the underlying script stop
        }
        setCurNodeEventEnd(false);
    }

    public void dispose(ScriptType scriptType) {
        getMemory().clear();
        stop(scriptType);
    }

    public Position getPosition(int objId) {
        return chr.getField().getLifeByObjectID(objId).getPosition();
    }


    // Character Stat-related methods ----------------------------------------------------------------------------------

    @Override
    public String getJob() {
        return JobConstants.getJobEnumById(chr.getJob()).toString();
    }

    @Override
    public void setJob(short jobID) {
        chr.setJob(jobID);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.job, jobID);
        chr.getClient().write(WvsContext.statChanged(stats, chr.getSubJob()));
    }

    public int getVotePoints() {
        return chr.getUser().getVotePoints();
    }

    public void addVotePoints(int amount) {
        int total = getVotePoints() + amount;
        if (total >= 0) {
            setVotePoints(total);
        }
    }

    public void deductVotePoints(int amount) {
        addVotePoints(-amount);
    }

    public void setVotePoints(int vPoints) {
        chr.getUser().setVotePoints(vPoints);
    }

    public int getDonationPoints() {
        return chr.getUser().getDonationPoints();
    }

    public void deductDonationPoints(int amount) {
        addDonationPoints(-amount);
    }

    public void setDonationPoints(int val) {
        chr.getUser().setDonationPoints(val);
    }

    public void addDonationPoints(int amount) {
        int total = getDonationPoints() + amount;
        if (total >= 0) {
            setDonationPoints(total);
        }
    }

    public void addSP(int amount) {
        addSP(amount, false);
    }

    @Override
    public void addSP(int amount, boolean jobAdv) {
        byte jobLevel = (byte) JobConstants.getJobLevel(chr.getJob());
        int currentSP = chr.getAvatarData().getCharacterStat().getExtendSP().getSpByJobLevel(jobLevel);
        setSP(currentSP + amount);
        if (jobAdv) {
            chr.write(WvsContext.incSpMessage(chr.getJob(), (byte) amount));
        }
    }

    @Override
    public void setSP(int amount) {
        chr.setSpToCurrentJob(amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void addAP(int amount) {
        int currentAP = chr.getAvatarData().getCharacterStat().getAp();
        setAP(currentAP + amount);
    }

    @Override
    public void setAP(int amount) {
        chr.setStat(Stat.ap, (short) amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.ap, (short) amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void resetAP(boolean hpMp) {
        resetAP(hpMp, (short) 0);
    }

    @Override
    public void resetAP(boolean hpMp, short jobId) {
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.str, chr.getAvatarData().getCharacterStat().getStr());
        stats.put(Stat.dex, chr.getAvatarData().getCharacterStat().getDex());
        stats.put(Stat.luk, chr.getAvatarData().getCharacterStat().getLuk());
        stats.put(Stat.inte, chr.getAvatarData().getCharacterStat().getInt());
        stats.put(Stat.ap, chr.getAvatarData().getCharacterStat().getAp());

        // Identify Primary Stat, special case only exist for Thief (Xenon) & Pirates (Also including Xenon)
        Stat primaryStat = JobConstants.isWarriorEquipJob(jobId) ? Stat.str : JobConstants.isArcherEquipJob(jobId) ? Stat.dex : JobConstants.isMageEquipJob(jobId) ? Stat.inte : JobConstants.isThiefEquipJob(jobId) ? Stat.luk : Stat.ap;
        if (JobConstants.isXenon(jobId) || JobConstants.isAdventurerPirate(jobId) && !JobConstants.isBuccaneer(jobId) && !JobConstants.isCorsair(jobId) && !JobConstants.isCannonShooter(jobId)) {
            primaryStat = Stat.ap; // If we are a xenon or a 1st job adventurer pirate put points back into AP
        } else if (JobConstants.isBuccaneer(jobId) || JobConstants.isThunderBreaker(jobId) || JobConstants.isShade(jobId) || JobConstants.isCannonShooter(jobId)) {
            primaryStat = Stat.str; // Handle STR pirates
        } else if (JobConstants.isPirateEquipJob(jobId)) {
            primaryStat = Stat.dex; // if none of the above conditions apply & we're a pirate, only remaining choice is DEX, otherwise we leave primary stat as AP
        }

        int buffer = 0; // Difference between the stat's current value and its minimum (0 for AP, 4 for the four traditional stats)
        for (Map.Entry<Stat, Object> stat : stats.entrySet()) {
            if (stat.getKey() != primaryStat) {
                buffer = ((short) stat.getValue() - (stat.getKey() != Stat.ap ? 4 : 0));
                if (buffer > 0) {
                    stat.setValue((short) ((short) stat.getValue() - buffer));
                    stats.put(primaryStat, (short) ((short) stats.get(primaryStat) + buffer));
                    chr.setStat(stat.getKey(), (short) stats.get(stat.getKey()));
                    chr.setStat(primaryStat, (short) stats.get(primaryStat));
                }
            }
        }
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void setSTR(short amount) {
        chr.setStat(Stat.str, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.str, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void setINT(short amount) {
        chr.setStat(Stat.inte, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.inte, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void setDEX(short amount) {
        chr.setStat(Stat.dex, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.dex, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void setLUK(short amount) {
        chr.setStat(Stat.luk, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.luk, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    public void addMaxHP(int amount) {
        chr.addStatAndSendPacket(Stat.mhp, amount);
    }

    @Override
    public void setMaxHP(int amount) {
        chr.setStat(Stat.mhp, amount);
        chr.setStat(Stat.hp, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mhp, amount);
        stats.put(Stat.hp, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    public void addMaxMP(int amount) {
        chr.addStatAndSendPacket(Stat.mmp, amount);
    }

    @Override
    public void setMaxMP(int amount) {
        chr.setStat(Stat.mmp, amount);
        chr.setStat(Stat.mp, amount);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mmp, amount);
        stats.put(Stat.mp, amount);
        chr.getClient().write(WvsContext.statChanged(stats));
    }

    @Override
    public void jobAdvance(short jobID) {
        setJob(jobID);
        addAP(5); //Standard added AP upon Job Advancing
        addSP(5); //Standard added SP upon Job Advancing
    }

    @Override
    public void giveExp(long expGiven) {
        chr.addExp(expGiven);
    }

    @Override
    public void giveExpNoMsg(long expGiven) {
        chr.addExpNoMsg(expGiven);
    }

    @Override
    public void changeCharacterLook(int look) {
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        if (look < 100) { // skin
            al.setSkin(look);
            chr.setStatAndSendPacket(Stat.skin, look);
        } else if (20000 <= look && look < 29999 || 50000 <= look && look < 59999) {
            al.setFace(look);
            chr.setStatAndSendPacket(Stat.face, look);
        } else if (30000 <= look && look < 49999 || 60000 <= look && look < 69999) {
            al.setHair(look);
            chr.setStatAndSendPacket(Stat.hair, look);
        } else {
            log.error(String.format("Tried changing a look with invalid id (%d)", look));
            return;
        }
        byte maskValue = AvatarModifiedMask.AvatarLook.getVal();
        chr.getField().broadcastPacket(UserRemote.avatarModified(chr, maskValue, (byte) 0), chr);
    }

    public static void test(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    public void giveSkill(int skillId) {
        giveSkill(skillId, 1);
    }

    public void giveSkill(int skillId, int slv) {
        giveSkill(skillId, slv, slv);
    }

    @Override
    public void giveSkill(int skillId, int slv, int maxLvl) {
        chr.addSkill(skillId, slv, maxLvl);
    }


    @Override
    public void removeSkill(int skillId) {
        chr.removeSkillAndSendPacket(skillId);
    }

    public int getSkillByItem() {
        return getSkillByItem(getParentID());
    }

    public int getSkillByItem(int itemId) {
        ItemInfo itemInfo = ItemData.getItemInfoByID(itemId);
        return itemInfo.getSkillId();
    }

    public boolean hasSkill(int skillId) {
        return chr.hasSkill(skillId);
    }

    public void heal() {
        chr.heal(chr.getMaxHP());
        chr.healMP(chr.getMaxMP());
    }

    public void setLevel(int level) {
        chr.setStatAndSendPacket(Stat.level, level);
        chr.setStatAndSendPacket(Stat.exp, 0);
        chr.getJobHandler().handleLevelUp();
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
    }

    public void addLevel(int level) {
        int curLevel = chr.getLevel();
        for (int i = curLevel + 1; i <= curLevel + level; i++) {
            chr.setStat(Stat.level, i);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.level, i);
            stats.put(Stat.exp, (long) 0);
            chr.getClient().write(WvsContext.statChanged(stats));
            chr.getJobHandler().handleLevelUp();
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.levelUpEffect()));
        }
    }

    public void lockInGameUI(boolean lock) {
        lockInGameUI(lock, true);
    }

    public void lockInGameUI(boolean lock, boolean blackFrame) {
        if (chr != null) {
            chr.write(UserLocal.setInGameDirectionMode(lock, blackFrame, false));
        }
    }

    public void curNodeEventEnd(boolean enable) {
        setCurNodeEventEnd(enable);
        chr.write(FieldPacket.curNodeEventEnd(enable));
    }

    public void setCurNodeEventEnd(boolean curNodeEventEnd) {
        this.curNodeEventEnd = curNodeEventEnd;
    }

    public void setTemporarySkillSet(int skillSet) {
    }

    public void setStandAloneMode(boolean enable) {
    }

    public void setDirectionMode(boolean show, int unk) {
        chr.write(UserLocal.setDirectionMode(show, unk));
    }

    public void lockUI() {
        curNodeEventEnd(true);
        setTemporarySkillSet(0);
        lockInGameUI(true, false);
    }

    public void unlockUI() {
        setTemporarySkillSet(0);
        lockInGameUI(false, true);
    }

    public void lockForIntro() {
        lockUI();
        setStandAloneMode(true);
    }

    public void unlockForIntro() {
        setStandAloneMode(false);
        unlockUI();
    }

    public void progressMessageFont(int fontNameType, int fontSize, int fontColorType, int fadeOutDelay, String message) {
        ProgressMessageFontType type = ProgressMessageFontType.getByVal(fontNameType);
        ProgressMessageColourType colour = ProgressMessageColourType.getByVal(fontColorType);
        if (colour == null || type == null) {
            log.warn(String.format("Could not find fontType %d or ColourType %d", fontNameType, fontColorType));
            return;
        }
        progressMessageFont(type, fontSize, colour, fadeOutDelay, message);
    }

    public void progressMessageFont(ProgressMessageFontType fontType, int fontSize, ProgressMessageColourType colour, int fadeOutDelay, String msg) {
        chr.write(UserPacket.progressMessageFont(fontType, fontSize, colour, fadeOutDelay, msg));
    }

    public void localEmotion(int emotion, int duration, boolean byItemOption) {
        chr.write(UserLocal.emotion(emotion, duration, byItemOption));
    }


    // Field-related methods -------------------------------------------------------------------------------------------

    @Override
    public void warp(int id) {
        warp(id, 0);
    }

    public void warp(int id, boolean executeAfterScript) {
        warp(id, 0, executeAfterScript, false);
    }

    @Override
    public void warp(int id, int pid) {
        warp(id, pid, true, false);
    }

    public void warp(int id, int pid, boolean instanceField) {
        warp(id, pid, true, instanceField);
    }

    public void warp(int mid, int pid, boolean executeAfterScript, boolean instanceField) {
        if (executeAfterScript) {
            FieldTransferInfo fti = getFieldTransferInfo();
            fti.setFieldId(mid);
            fti.setPortal(pid);
            fti.setIsInstanceField(instanceField);
        } else {
            chr.warp(mid, pid);
        }
    }

    public void changeChannelAndWarp(int channel, int fieldID, boolean executeAfterScript, boolean instanceField) {
        if (executeAfterScript) {
            FieldTransferInfo fti = getFieldTransferInfo();
            fti.setChannel(channel);
            fti.setFieldId(fieldID);
            fti.setIsInstanceField(instanceField);
        } else {
            Client c = chr.getClient();
            c.setOldChannel(c.getChannel());
            chr.changeChannelAndWarp((byte) channel, fieldID);
        }
    }

    public void warpField(int fieldId) {
        warp(fieldId, 0);
    }

    public void warpField(int fieldId, int portalId) {
        // only warp after script has ended
        FieldTransferInfo fti = getFieldTransferInfo();
        fti.setFieldId(fieldId);
        fti.setPortal(portalId);
    }

    public void changeChannelAndWarp(int channel, int fieldID) {
        changeChannelAndWarp(channel, fieldID, true, false);
    }

    @Override
    public int getFieldID() {
        return chr.getField().getId();
    }

    public void warpInstanceOut() {
        warpInstance(-1, false, 0, false);
    }

    public void warpInstanceIn(int id, int portal) {
        warpInstance(id, true, portal, false);
    }

    public void warpInstanceIn(int id, int portalId, boolean partyAllowed) {
        warpInstance(id, true, portalId, partyAllowed);
    }

    public void warpInstanceOut(int id, int portal) {
        warpInstance(id, false, portal, false);
    }

    @Override
    public void warpInstanceIn(int id) {
        warpInstance(id, true, 0, false);
    }

    public void warpInstanceIn(int id, boolean partyAllowed) {
        warpInstance(id, true, 0, partyAllowed);
    }

    @Override
    public void warpInstanceOut(int id) {
        warpInstance(id, false, 0, false);
    }

    private void warpInstance(int fieldId, boolean in, int portalId, boolean partyAllowed) {
        Instance instance;
        if (in) {
            // warp party in if there is a party and party is allowed, solo instance otherwise
            Party party = chr.getParty();
            if (party == null || !partyAllowed) {
                instance = new Instance(chr);
            } else {
                instance = new Instance(party);
            }
            // setup the instance & warp
            instance.setup(fieldId, portalId);
        } else {
            instance = chr.getInstance();
            stopEvents();
            if (instance == null) {
                // no info, just warp them
                chr.setDeathCount(-1);
                chr.warp(fieldId, portalId);
            } else {
                // remove chr from eligible instance members
                int forcedReturn;
                int forcedReturnPortal;
                if (fieldId >= 0) {
                    forcedReturn = fieldId;
                    forcedReturnPortal = -1;
                } else {
                    forcedReturn = instance.getForcedReturn();
                    forcedReturnPortal = instance.getForcedReturnPortalId();
                }
                instance.removeChar(chr);
                chr.setDeathCount(-1);
                if (forcedReturnPortal >= 0) {
                    chr.warp(forcedReturn, forcedReturnPortal);
                } else {
                    chr.warp(forcedReturn);
                }
                // if eligible members' size is 0, clear the instance
                if (instance.getChars().size() == 0) {
                    instance.clear();
                }
            }
        }
    }

    public void setInstanceTime(int seconds) {
        setInstanceTime(seconds, 0);
    }

    public void setInstanceTime(int seconds, int forcedReturnFieldId) {
        Instance instance = chr.getInstance();
        if (instance != null) {
            if (forcedReturnFieldId != 0) {
                instance.setForcedReturn(forcedReturnFieldId);
            }
            if (instance.getRemainingTime() < System.currentTimeMillis()) {
                // don't override old timeout value
                instance.setTimeout(seconds);
            }
        }
    }

    @Override
    public int getReturnField() {
        //Do this to prevent infinite returnField Loop
        if (chr.getField().getId() == returnField) {
            return 100000000;
        }
        return returnField;
    }

    @Override
    public void setReturnField(int returnField) {
        this.returnField = returnField;
    }

    @Override
    public void setReturnField() {
        setReturnField(chr.getFieldID());
    }

    @Override
    public boolean hasMobsInField() {
        return hasMobsInField(chr.getFieldID());
    }

    public Mob waitForMobDeath() {
        Object response = null;
        if (isActive(ScriptType.FirstEnterField)) {
            response = getScriptInfoByType(ScriptType.FirstEnterField).awaitResponse();
        } else if (isActive(ScriptType.Field)) {
            response = getScriptInfoByType(ScriptType.Field).awaitResponse();
        }
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (Mob) response;
    }

    public Mob waitForMobDeath(int... possibleMobs) {
        Mob mob = waitForMobDeath();
        while (true) {
            if (mob == null) {
                throw new NullPointerException(INTENDED_NPE_MSG);
            } else {
                for (int mobID : possibleMobs) {
                    if (mob.getTemplateId() == mobID) {
                        return mob;
                    }
                }
                mob = waitForMobDeath();
            }
        }
    }

    @Override
    public boolean hasMobsInField(int fieldid) {
        return getAmountOfMobsInField(fieldid) > 0;
    }

    public boolean hasMobsInField(int fieldid, int mobTemplateId) {
        return getAmountOfMobsInField(fieldid, mobTemplateId) > 0;
    }

    @Override
    public int getAmountOfMobsInField() {
        return getAmountOfMobsInField(chr.getFieldID());
    }

    @Override
    public int getAmountOfMobsInField(int fieldid) {
        Field field = chr.getOrCreateFieldByCurrentInstanceType(fieldid);
        return field.getMobs().size();
    }

    public int getAmountOfMobsInField(int fieldid, int mobTemplateId) {
        Field field = chr.getOrCreateFieldByCurrentInstanceType(fieldid);
        return (int) field.getMobs().stream().filter(mob -> mob.getTemplateId() == mobTemplateId).count();
    }

    public void killMobs() {
        List<Mob> mobs = new ArrayList<>(chr.getField().getMobs());
        for (Mob mob : mobs) {
            mob.die(false);
        }
    }

    public void showWeatherNoticeToField(String text, WeatherEffNoticeType type) {
        showWeatherNoticeToField(text, type, 7000); // 7 seconds
    }

    public void showWeatherNoticeToField(String text, WeatherEffNoticeType type, int duration) {
        Field field = chr.getField();
        field.broadcastPacket(WvsContext.weatherEffectNotice(type, text, duration));
    }

    public void showEffectToField(String dir) {
        showEffectToField(dir, 0);
    }

    public void showEffectToField(String dir, int delay) {
        Field field = chr.getField();
        field.broadcastPacket(UserPacket.effect(Effect.effectFromWZ(dir, false, delay, 4, 0)));
    }

    public void showFieldEffect(String dir) {
        showFieldEffect(dir, 0);
    }

    @Override
    public void showFieldEffect(String dir, int delay) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.getFieldEffectFromWz(dir, delay)));
    }

    @Override
    public void showObjectFieldEffect(String objectEffectName) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.getFieldEffectFromObject(objectEffectName)));
    }

    public void showFieldEffectToField(String dir) {
        showFieldEffect(dir, 0);
    }

    public void showFieldEffectToField(String dir, int delay) {
        Field field = chr.getField();
        field.broadcastPacket(FieldPacket.fieldEffect(FieldEffect.getFieldEffectFromWz(dir, delay)));
    }

    public void showOffFieldEffect(String dir) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.getOffFieldEffectFromWz(dir, 0)));
    }

    public void changeBGM(String dir, int startTime, int idk) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.changeBGM(dir, startTime, idk)));
    }

    public void bgmVolumeOnly(boolean volumeOnly) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.bgmVolumeOnly(volumeOnly)));
    }

    public void bgmVolume(int volume, int fadingDuration) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.bgmVolume(volume, fadingDuration)));
    }

    public void showFieldBackgroundEffect(String dir) {
        showFieldBackgroundEffect(dir, 0);
    }

    public void showFieldBackgroundEffect(String dir, int delay) {
        Field field = chr.getField();
        chr.write(FieldPacket.fieldEffect(FieldEffect.getFieldBackgroundEffectFromWz(dir, delay)));
    }

    public void showFadeTransition(int duration, int fadeInTime, int fadeOutTime) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.takeSnapShotOfClient2(fadeInTime, duration, fadeOutTime, true)));
    }

    public void showFade(int duration) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.takeSnapShotOfClient(duration)));
    }

    public void setFieldColour(GreyFieldType colorFieldType, short red, short green, short blue, int time) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.setFieldColor(colorFieldType, red, green, blue, time)));
    }

    public void setFieldGrey(GreyFieldType colorFieldType, boolean show) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.setFieldGrey(colorFieldType, show)));
    }

    public void removeOverlapScreen(int duration) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.removeOverlapScreen(duration)));
    }

    public void onLayer(int duration, String key, int x, int y, int z, String origin, int org, boolean postRender, int idk, boolean repeat) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.onOffLayer(0, duration, key, x, y, z, origin, org, postRender, idk, repeat)));
    }

    public void moveLayer(int duration, String key, int x, int y) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.onOffLayer(1, duration, key, x, y, 0, null, 0, false, 0, false)));
    }

    public void offLayer(int duration, String key, boolean repeat) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.onOffLayer(2, duration, key, 0, 0, 0, null, 0, false, 0, repeat)));
    }

    public void spineScreen(boolean binary, boolean loop, boolean postRender, int endDelay, String path,
                            String animationName, String keyName) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.spineScreen(binary, loop, postRender, endDelay, path, animationName, keyName)));
    }

    public void offSpineScreen(String keyName, int type, String aniName, int alphaTime) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.offSpineScreen(keyName, type, aniName, alphaTime)));
    }

    @Override
    public void dropItem(int itemId, int x, int y) {
        Field field = chr.getField();
        Drop drop = new Drop(field.getNewObjectID());
        drop.setItem(ItemData.getItemDeepCopy(itemId));
        Position position = new Position(x, y);
        drop.setPosition(position);
        field.drop(drop, position, true);
    }

    @Override
    public void teleportInField(Position position) {
        chr.write(FieldPacket.teleport(position, chr));
    }

    @Override
    public void teleportInField(int x, int y) {
        teleportInField(new Position(x, y));
    }

    @Override
    public void teleportToPortal(int portalId) {
        Portal portal = chr.getField().getPortalByID(portalId);
        if (portal != null) {
            Position position = new Position(portal.getX(), portal.getY());
            chr.write(FieldPacket.teleport(position, chr));
        }
    }

    public Drop getDropInRect(int itemID, Rect rect) {
        Field field = getField();
        if (field == null) {
            field = chr.getField();
        }
        return field.getDropsInRect(rect).stream()
                .filter(drop -> drop.getItem() != null && drop.getItem().getItemId() == itemID)
                .findAny().orElse(null);
    }

    @Override
    public Drop getDropInRect(int itemID, int rectRange) {
        return getDropInRect(itemID, new Rect(
                new Position(
                        chr.getPosition().getX() - rectRange,
                        chr.getPosition().getY() - rectRange),
                new Position(
                        chr.getPosition().getX() + rectRange,
                        chr.getPosition().getY() + rectRange))
        );

    }

    public void changeFoothold(String footholdName, boolean show) {
        getChr().getField().broadcastPacket(FieldPacket.footholdAppear(footholdName, show));
    }

    public void createFallingCatcher(String templateStr, int hpR, int amount, int chance) {
        Field field = chr.getField();
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            if (Util.succeedProp(chance)) {
                positions.add(Util.getRandomFromCollection(field.getFootholds()).getRandomPosition());
            }
        }
        createFallingCatcher(templateStr, hpR, positions);
    }

    public void createFallingCatcher(String templateStr, int hpR, List<Position> positions) {
        FallingCatcher fallingCatcher = new FallingCatcher(templateStr, hpR, positions);
        chr.getField().broadcastPacket(FieldPacket.createFallingCatcher(fallingCatcher));
    }

    public void setAchieveRate(int Rate) {
        chr.getField().broadcastPacket(FieldPacket.setAchieveRate(Rate));
    }

    // Life-related methods --------------------------------------------------------------------------------------------


    // NPC methods
    @Override
    public void spawnNpc(int npcId, int x, int y) {
        Npc npc = NpcData.getNpcDeepCopyById(npcId);
        Position position = new Position(x, y);
        npc.setPosition(position);
        npc.setCy(y);
        npc.setRx0(x + 50);
        npc.setRx1(x - 50);
        npc.setFh(chr.getField().findFootHoldBelow(new Position(x, y - 2)).getId());
        npc.setNotRespawnable(true);
        if (npc.getField() == null) {
            npc.setField(field);
        }

        chr.getField().spawnLife(npc, chr);
    }

    @Override
    public void spawnNpc(int npcId, int x, int y, boolean flip) {
        Npc npc = NpcData.getNpcDeepCopyById(npcId);
        Position position = new Position(x, y);
        npc.setPosition(position);
        npc.setCy(y);
        npc.setRx0(x + 50);
        npc.setRx1(x - 50);
        npc.setFlip(flip);
        npc.setFh(chr.getField().findFootHoldBelow(new Position(x, y - 2)).getId());
        npc.setNotRespawnable(true);
        if (npc.getField() == null) {
            npc.setField(field);
        }

        chr.getField().spawnLife(npc, chr);
    }

    @Override
    public void removeNpc(int npcId) {
        chr.getField().getNpcs().stream()
                .filter(npc -> npc.getTemplateId() == npcId)
                .findFirst()
                .ifPresent(npc -> chr.getField().removeLife(npc));
    }

    @Override
    public void openNpc(int npcId) {
        Npc npc = NpcData.getNpcDeepCopyById(npcId);
        String script;
        if (npc.getScripts().size() > 0) {
            script = npc.getScripts().get(0);
        } else {
            script = String.valueOf(npc.getTemplateId());
        }
        chr.getScriptManager().startScript(npc.getTemplateId(), npcId, script, ScriptType.Npc);
    }

    @Override
    public void openShop(int shopID) {
        NpcShopDlg nsd = NpcData.getShopById(shopID);
        if (nsd != null) {
            chr.setShop(nsd);
            chr.write(ShopDlg.openShop(chr, 0, nsd));
        } else {
            if (chr.getUser().getAccountType() != AccountType.Player) {
                chat(String.format("Could not find shop with id %d.", shopID));
            }
            log.error(String.format("Could not find shop with id %d.", shopID));
        }
    }

    @Override
    public void openTrunk(int npcTemplateID) {
        chr.write(FieldPacket.trunkDlg(new TrunkOpen(npcTemplateID, chr.getAccount().getTrunk())));
    }

    @Override
    public void setSpeakerID(int templateID) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.removeParam(NpcScriptInfo.Param.PlayerAsSpeaker);
        boolean isNotCancellable = nsi.hasParam(NpcScriptInfo.Param.NotCancellable);
        nsi.setTemplateID(templateID);
        if (isNotCancellable) {
            nsi.addParam(NpcScriptInfo.Param.NotCancellable);
        }
    }

    public void setInnerOverrideSpeakerTemplateID(int templateID) {
        getNpcScriptInfo().setInnerOverrideSpeakerTemplateID(templateID);
    }

    @Override
    public void setSpeakerType(byte speakerType) {
        NpcScriptInfo nsi = getNpcScriptInfo();
        nsi.setSpeakerType(speakerType);
    }

    public void hideNpcByTemplateId(int npcTemplateId, boolean hide) {
        hideNpcByTemplateId(npcTemplateId, hide, hide);
    }

    @Override
    public void hideNpcByTemplateId(int npcTemplateId, boolean hideTemplate, boolean hideNameTag) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(npcTemplateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcTemplateId));
            return;
        }
        chr.write(NpcPool.npcViewOrHide(life.getObjectId(), !hideTemplate, !hideNameTag));
    }

    public void hideNpcByObjectId(int npcObjId, boolean hide) {
        hideNpcByObjectId(npcObjId, hide, hide);
    }

    @Override
    public void hideNpcByObjectId(int npcObjId, boolean hideTemplate, boolean hideNameTag) {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(npcObjId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcObjId));
            return;
        }
        chr.write(NpcPool.npcViewOrHide(life.getObjectId(), !hideTemplate, !hideNameTag));
    }

    @Override
    public void moveNpcByTemplateId(int npcTemplateId, boolean left, int distance, int speed) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(npcTemplateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcTemplateId));
            return;
        }
        chr.write(NpcPool.npcSetForceMove(life.getObjectId(), left, distance, speed));
    }

    @Override
    public void moveNpcByObjectId(int npcObjId, boolean left, int distance, int speed) {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(npcObjId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcObjId));
            return;
        }
        chr.write(NpcPool.npcSetForceMove(life.getObjectId(), left, distance, speed));
    }

    @Override
    public void flipNpcByTemplateId(int npcTemplateId, boolean left) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(npcTemplateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcTemplateId));
            return;
        }
        chr.write(NpcPool.npcSetForceFlip(life.getObjectId(), left));
    }

    @Override
    public void flipNpcByObjectId(int npcObjId, boolean left) {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(npcObjId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcObjId));
            return;
        }
        chr.write(NpcPool.npcSetForceFlip(life.getObjectId(), left));
    }

    public void showNpcSpecialActionByTemplateId(int npcTemplateId, String effectName) {
        showNpcSpecialActionByTemplateId(npcTemplateId, effectName, 0);
    }

    @Override
    public void showNpcSpecialActionByTemplateId(int npcTemplateId, String effectName, int duration) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(npcTemplateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcTemplateId));
            return;
        }
        chr.write(NpcPool.npcSetSpecialAction(life.getObjectId(), effectName, duration));
    }

    public void showNpcSpecialActionByObjectId(int npcObjId, String effectName) {
        showNpcSpecialActionByObjectId(npcObjId, effectName, 0);

    }

    @Override
    public void showNpcSpecialActionByObjectId(int npcObjId, String effectName, int duration) {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(npcObjId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcObjId));
            return;
        }
        chr.write(NpcPool.npcSetSpecialAction(life.getObjectId(), effectName, duration));
    }

    public void resetNpcSpecialActionByTemplateId(int templateId) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(templateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", templateId));
            return;
        }
        resetNpcSpecialActionByObjectId(life.getObjectId());
    }

    public void resetNpcSpecialActionByObjectId(int objectId) {
        chr.write(NpcPool.npcResetSpecialAction(objectId));
    }

    public int getNpcObjectIdByTemplateId(int npcTemplateId) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(npcTemplateId);
        if (!(life instanceof Npc)) {
            log.error(String.format("npc %d is null or not an instance of Npc", npcTemplateId));
            return 0;
        }
        return life.getObjectId();
    }


    // Mob methods
    @Override
    public void spawnMob(int id) {
        spawnMob(id, 0, 0, false);
    }

    @Override
    public void spawnMob(int id, boolean respawnable) {
        spawnMob(id, 0, 0, respawnable);
    }

    @Override
    public void spawnMobOnChar(int id) {
        spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), false);
    }

    @Override
    public void spawnMobOnChar(int id, boolean respawnable) {
        spawnMob(id, chr.getPosition().getX(), chr.getPosition().getY(), respawnable);
    }

    @Override
    public void spawnMob(int id, int x, int y, boolean respawnable) {
        spawnMob(id, x, y, respawnable, 0);
    }

    public void spawnMob(int id, int x, int y) {
        spawnMob(id, x, y, false, 0);
    }

    public void spawnMob(int id, int x, int y, long hp) {
        spawnMob(id, x, y, false, hp);
    }

    public void spawnMob(int id, int x, int y, boolean respawnable, long hp) {
        chr.getField().spawnMob(id, x, y, respawnable, hp);
    }

    public void spawnMobWithAppearType(int id, int x, int y, int appearType, int option) {
        chr.getField().spawnMobWithAppearType(id, x, y, appearType, option);
    }

    @Override
    public void removeMobByObjId(int id) {
        chr.getField().removeLife(id);
        chr.getField().broadcastPacket(MobPool.leaveField(id, DeathType.ANIMATION_DEATH));
    }

    @Override
    public void removeMobByTemplateId(int id) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(id);
        if (life == null) {
            log.error(String.format("Could not find Mob by template id %d.", id));
            return;
        }
        removeMobByObjId(life.getObjectId());
    }

    public boolean isFinishedEscort(int templateID) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(templateID);
        if (!(life instanceof Mob)) {
            chr.dispose();
            return false;
        }
        Mob mob = (Mob) life;
        boolean finished = mob.isFinishedEscort();
        if (!finished) {
            chr.dispose();
        }
        return finished;
    }

    @Override
    public void showHP(int templateID) {
        chr.getField().getMobs().stream()
                .filter(m -> m.getTemplateId() == templateID)
                .findFirst()
                .ifPresent(mob -> chr.getField().broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob))));
    }

    @Override
    public void showHP() {
        chr.getField().getMobs().stream()
                .filter(m -> m.getHp() > 0)
                .findFirst()
                .ifPresent(mob -> chr.getField().broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob))));
    }


    // Reactor methods
    @Override
    public void removeReactor() {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(getObjectIDByScriptType(ScriptType.Reactor));
        if (life instanceof Reactor) {
            field.removeLife(life.getObjectId(), false);
        }
    }

    @Override
    public void spawnReactor(int reactorId, int x, int y) {
        Field field = chr.getField();
        Reactor reactor = ReactorData.getReactorByID(reactorId);
        Position position = new Position(x, y);
        reactor.setPosition(position);
        field.addLife(reactor);
    }

    @Override
    public boolean hasReactors() {
        Field field = chr.getField();
        return field.getReactors().size() > 0;
    }

    @Override
    public int getReactorQuantity() {
        Field field = chr.getField();
        return field.getReactors().size();
    }


    public int getReactorState(int reactorId) {
        Field field = chr.getField();
        Life life = field.getLifeByTemplateId(reactorId);
        if (life != null && life instanceof Reactor) {
            Reactor reactor = (Reactor) life;
            return reactor.getState();
        }
        return -1;
    }

    public void increaseReactorState(int reactorId, int stateLength) {
        chr.getField().increaseReactorState(chr, reactorId, stateLength);
    }

    public void changeReactorState(int reactorId, byte state, short delay, byte stateLength) {
        Field field = chr.getField();
        Reactor reactor = field.getReactors().stream()
                .filter(r -> r.getObjectId() == getObjectIDByScriptType(ScriptType.Reactor))
                .findAny().orElse(null);
        if (reactor == null) {
            return;
        }
        reactor.setState(state);
        chr.write(ReactorPool.reactorChangeState(reactor, delay, stateLength));
    }

    // NX
    public void addNX(int nx) {
        chr.addNx(nx);
    }

    public void deductNX(int nx) {
        chr.deductNX(nx);
    }


    // Party-related methods -------------------------------------------------------------------------------------------

    @Override
    public Party getParty() {
        return chr.getParty();
    }

    @Override
    public int getPartySize() {
        return getParty().getMembers().size();
    }

    @Override
    public boolean isPartyLeader() {
        return chr.getParty() != null && chr.getParty().getPartyLeaderID() == chr.getId();
    }

    public boolean checkParty() {
        return checkParty(null, 0);
    }

    @Override
    public boolean checkParty(EventType event, int level) {
        if (chr.getParty() == null) {
            chatRed("You are not in a party.");
            return false;
        } else if (!isPartyLeader()) {
            chatRed("You are not the party leader.");
            return false;
        }
        boolean res = true;
        Char leader = chr.getParty().getPartyLeader().getChr();
        if (leader == null) {
            chatRed("Your leader is currently offline.");
            res = false;
        } else {
            int fieldID = leader.getFieldID();
            for (PartyMember pm : chr.getParty().getPartyMembers()) {
                if (pm != null) {
                    String name = pm.getCharName();
                    Char pmChr = pm.getChr();
                    if (pmChr == null || !pm.isOnline()) {
                        chatRed(name + " is not online.");
                        res = false;
                    } else if (pmChr.getLevel() < level) {
                        chatRed(name + " is not a high enough level.");
                        res = false;
                    } else if (pmChr.getField() != chr.getField()) {
                        chatRed(name + " is not in the same map as you.");
                        res = false;
                    } else if (pmChr.getInstance() != null) {
                        // kinda overlaps with the above check, but to prevent weird stuff from happening
                        chatRed(name + " is already in their own instance.");
                        res = false;
                    } else if (event != null) {
                        if (pm.getChr().getScriptManager().getMillisecondsUntilEventReset(event) > 0) {
                            if (!name.equals(chr.getName())) {
                                chatRed(name + " has already reach their run limit.");
                            }
                            pm.getChr().chatMessage(SystemNotice, "You cannot run this boss for another " + pm.getChr().getScriptManager().getMillisecondsUntilEventReset(event) / 60000 + " minutes.");
                            res = false;
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<Char> getOnlinePartyMembers() {
        Party party = getParty();
        if (party == null) {
            return new ArrayList<>();
        }
        return party.getOnlineChars();
    }

    public List<Char> getPartyMembersInSameField(Char chr) {
        Party party = getParty();
        if (party == null) {
            return new ArrayList<>();
        }
        List<Char> list = new ArrayList<>(party.getPartyMembersInSameField(chr));
        list.add(chr);
        return new ArrayList<>(list);
    }

    public void addCharacterPotentials() {
        CharacterPotentialMan cpm = chr.getPotentialMan();
        Map<Byte, CharacterPotential> potentialMap = new HashMap<>();
        for (CharacterPotential cp : chr.getPotentials()) {
            potentialMap.put(cp.getKey(), cp);
        }
        List<CharacterPotential> potentials = CharacterPotentialMan.generateRandomPotential(3, cpm.getGrade(), true, potentialMap);
        for (CharacterPotential cp : potentials) {
            cpm.addPotential(cp);
        }
    }


    // Guild/Alliance related methods -------------------------------------------------------------------------------------------

    @Override
    public void showGuildCreateWindow() {
        chr.write(WvsContext.guildResult(GuildResult.msg(GuildType.Req_InputGuildName)));
    }

    @Override
    public boolean checkAllianceName(String name) {
        World world = chr.getClient().getWorld();
        return world.getAlliance(name) == null;
    }

    public void incrementMaxGuildMembers(int amount) {
        Guild guild = chr.getGuild();
        guild.setMaxMembers(guild.getMaxMembers() + amount);
        guild.broadcast(WvsContext.guildResult(GuildResult.incMaxMemberNum(guild)));
    }

    public void createAlliance(String name, Char other) {
        Alliance alliance = new Alliance();
        alliance.setName(name);
        alliance.addGuild(chr.getGuild());
        alliance.addGuild(other.getGuild());
        GuildMember chrMember = chr.getGuild().getMemberByCharID(chr.getId());
        chrMember.setAllianceGrade(1);
        GuildMember otherMember = other.getGuild().getMemberByCharID(other.getId());
        otherMember.setAllianceGrade(2);
        DatabaseManager.saveToDB(alliance);
        chr.getGuild().setAlliance(alliance);
        other.getGuild().setAlliance(alliance);
        alliance.broadcast(WvsContext.allianceResult(AllianceResult.createDone(alliance)));
        chr.deductMoney(5000000);
    }


    // Chat-related methods --------------------------------------------------------------------------------------------

    @Override
    public void chat(String text) {
        chatRed(text);
    }

    @Override
    public void chatRed(String text) {
        chr.chatMessage(SystemNotice, text);
    }

    public void chatAdmin(String text) {
        chr.chatMessage(AdminChat, text);
    }

    @Override
    public void chatBlue(String text) {
        chr.chatMessage(Notice2, text);
    }

    public void systemMessage(String message) {
        chr.write(WvsContext.message(MessageType.SYSTEM_MESSAGE, 0, message, (byte) 0));
    }

    @Override
    public void chatScript(String text) {
        chr.chatScriptMessage(text);
    }

    public void showWeatherNotice(String text, WeatherEffNoticeType type) {
        showWeatherNotice(text, type, 7000); // 7 seconds
    }

    @Override
    public void showWeatherNotice(String text, WeatherEffNoticeType type, int duration) {
        chr.write(WvsContext.weatherEffectNotice(type, text, duration));
    }


    // Inventory-related methods ---------------------------------------------------------------------------------------

    @Override
    public void giveNX(int nx) {
        chr.addNx(nx);
    }

    @Override
    public void giveMesos(long mesos) {
        chr.addMoney(mesos);
        chr.write(WvsContext.incMoneyMessage((int) mesos));
    }

    @Override
    public void deductMesos(long mesos) {
        chr.deductMoney(mesos);
        chr.write(WvsContext.incMoneyMessage(-mesos));
    }

    @Override
    public long getMesos() {
        return chr.getMoney();
    }

    /**
     * Give an item which has the expiration time
     * @param id
     * @param quantity for stackable items
     * @param fixedDate
     * @param value if fixedDate is false, set expiration time in minutes, if true, set expiration date in yyyyMMddHHmm or yyyyMMddHH, yyyyMMdd format (eg. 202002011200)
     */
    public void giveItemWithExpireDate(int id, int quantity, boolean fixedDate, Object value) {
        Item item = ItemData.getItemDeepCopy(id);
        if (item == null) {
            return;
        }
        if (!ItemConstants.isEquip(id) && !ItemConstants.isPet(id)) {
            item.setQuantity(quantity);
        }
        LocalDateTime date;
        if (fixedDate) {
            if (value instanceof LocalDateTime) {
                date = (LocalDateTime) value;
            } else {
                String dateStr = value instanceof String ? (String) value : String.valueOf(value);
                String pattern = dateStr.length() == 12 ? "yyyyMMddHHmm" : dateStr.length() == 10 ? "yyyyMMddHH" : "yyyyMMdd";
                date = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
            }
        } else {
            date = LocalDateTime.now().plusMinutes(value instanceof Long ? (Long) value : (Integer) value);
        }
        item.setDateExpire(FileTime.fromDate(date));
        chr.addItemToInventory(item);
    }

    @Override
    public void giveItem(int id) {
        giveItem(id, 1);
    }

    @Override
    public void giveItem(int id, int quantity) {
        chr.addItemToInventory(id, quantity);
    }

    public void giveAndEquip(int id) {
        if (!ItemConstants.isEquip(id)) {
            giveItem(id);
        }
        Item equip = ItemData.getItemDeepCopy(id);
        if (equip == null) {
            return;
        }
        // replace the old equip if there was any
        Inventory equipInv = chr.getEquipInventory();
        int bodyPart = ItemConstants.getBodyPartFromItem(id, chr.getAvatarData().getAvatarLook().getGender());
        Item oldEquip = equipInv.getItemBySlot(bodyPart);
        if (oldEquip != null) {
            int newSlot = chr.getEquippedInventory().getFirstOpenSlot();
            chr.unequip(oldEquip);
            oldEquip.setBagIndex(newSlot);
            oldEquip.updateToChar(chr);
        }
        equip.setBagIndex(bodyPart);
        chr.equip(equip, 0, 0);
        equip.updateToChar(chr);
    }

    public void giveAndEquipZero(int id, int id2) {
        Item equip = ItemData.getItemDeepCopy(id);
        Item equip2 = ItemData.getItemDeepCopy(id2);
        if (equip == null || equip2 == null) {
            return;
        }
        // replace the old equip if there was any

        Equip toUpdate = (Equip) equip;
        Equip toUpdate2 = (Equip) equip2;
        toUpdate.setBagIndex(11);
        toUpdate2.setBagIndex(10);
        chr.getEquippedInventory().addItem(toUpdate);
        chr.getEquippedInventory().addItem(toUpdate2);
        toUpdate.updateToChar(chr);
        toUpdate2.updateToChar(chr);
    }


    @Override
    public boolean hasItem(int id) {
        return hasItem(id, 1);
    }

    @Override
    public boolean isEquipped(int id) {
        return chr.getInventoryByType(InvType.EQUIPPED).getItems().stream().filter(item -> item.getItemId() == id).count() > 0;
    }

    @Override
    public boolean hasItem(int id, int quantity) {
        return getQuantityOfItem(id) >= quantity;
    }

    public void consumeItem() {
        consumeItem(getScriptInfoByType(ScriptType.Item).getParentID());
    }

    @Override
    public void consumeItem(int itemID) {
        chr.consumeItem(itemID, 1);
    }

    @Override
    public void consumeItem(int itemID, int amount) {
        chr.consumeItem(itemID, amount);
    }

    @Override
    public void useItem(int id) {
        ItemBuffs.giveItemBuffsFromItemID(chr, chr.getTemporaryStatManager(), id);
    }

    @Override
    public int getQuantityOfItem(int id) {
        if (ItemConstants.isEquip(id)) {
            Item equip = chr.getInventoryByType(InvType.EQUIP).getItemByItemID(id);
            if (equip == null) {
                return 0;
            }
            return equip.getQuantity();
        } else {
            Item item2 = ItemData.getItemDeepCopy(id);
            InvType invType = item2.getInvType();
            Item item = chr.getInventoryByType(invType).getItemByItemID(id);
            if (item == null) {
                return 0;
            }
            return item.getQuantity();
        }
    }

    @Override
    public boolean canHold(int id) {
        return chr.canHold(id);
    }

    @Override
    public boolean canHold(int id, int quantity) {
        return chr.canHold(id, quantity);
    }

    @Override
    public int getEmptyInventorySlots(InvType invType) {
        return chr.getInventoryByType(invType).getEmptySlots();
    }

    @Override
    public void giveGift(int itemId) {

    }

    // Quest-related methods -------------------------------------------------------------------------------------------

    @Override
    public void completeQuest(int questID) {
        if (hasQuest(questID) && isComplete(questID)) {
            completeQuestNoCheck(questID);
        }
    }

    @Override
    public void completeQuestNoCheck(int questID) {
        chr.getQuestManager().completeQuest(questID);
    }

    @Override
    public void completeQuestNoRewards(int id) {
        QuestManager qm = chr.getQuestManager();
        Quest quest = qm.getQuests().get(id);
        if (quest == null) {
            quest = QuestData.createQuestFromId(id);
        }
        quest.setCompletedTime(FileTime.currentTime());
        quest.setStatus(QuestStatus.Completed);
        qm.addQuest(quest);
        chr.write(WvsContext.questRecordMessage(quest));
        chr.chatMessage(String.format("Quest %d completed by completeQuestNoRewards", id));
    }

    @Override
    public void startQuestNoCheck(int id) {
        QuestManager qm = chr.getQuestManager();
        qm.addQuest(QuestData.createQuestFromId(id));
        chr.chatMessage(String.format("Quest %d started by startQuestNoCheck", id));
    }

    @Override
    public void startQuest(int id) {
        QuestManager qm = chr.getQuestManager();
        if (qm.canStartQuest(id)) {
            qm.addQuest(QuestData.createQuestFromId(id));
        }
    }

    @Override
    public boolean hasQuest(int id) {
        return chr.getQuestManager().hasQuestInProgress(id);
    }

    @Override
    public boolean hasQuestCompleted(int id) {
        return chr.getQuestManager().hasQuestCompleted(id);
    }

    public boolean hasHadQuest(int id) {
        return hasQuest(id) || hasQuestCompleted(id);
    }

    public void createQuestWithQRValue(int questId, String qrValue, boolean ex) {
        createQuestWithQRValue(chr, questId, qrValue, ex);
    }

    public void createQuestWithQRValue(int questId, String qrValue) {
        createQuestWithQRValue(chr, questId, qrValue, true);
    }

    public void createQuestWithQRValue(Char chr, int questId, String qrValue) {
        createQuestWithQRValue(chr, questId, qrValue, true);
    }

    public void createQuestWithQRValue(Char character, int questId, String qrValue, boolean ex) {
        QuestManager qm = character.getQuestManager();
        Quest quest = qm.getQuests().get(questId);
        if (quest == null) {
            quest = new Quest(questId, QuestStatus.Started);
            quest.setQrValue(qrValue);
            qm.addCustomQuest(quest);
        }
        quest.setQrValue(qrValue);
        updateQRValue(questId, ex);
    }

    public void deleteQuest(int questId) {
        deleteQuest(chr, questId);
    }

    public void deleteQuest(Char character, int questId) {
        QuestManager qm = chr.getQuestManager();
        Quest quest = qm.getQuests().get(questId);
        if (quest == null) {
            return;
        }
        qm.removeQuest(quest.getQRKey());
    }

    public String getQRValue(int questId) {
        return getQRValue(chr, questId);
    }

    public String getQRValue(int questId, String questKey) {
        Quest quest = chr.getQuestManager().getQuests().get(questId);
        if (quest == null) {
            return "";
        }
        return quest.getProperty(questKey);
    }

    public String getQRValue(Char chr, int questId) {
        Quest quest = chr.getQuestManager().getQuests().get(questId);
        if (quest == null) {
            return "";
        }
        return quest.getQRValue();
    }

    public boolean hasQuestWithValue(int qrKey, String str) {
        Quest quest = chr.getQuestManager().getQuestById(qrKey);
        if (quest == null) {
            return false;
        }
        return quest.getQRValue().contains(str);
    }

    public void setQRValue(int questId, String qrValue) {
        setQRValue(questId, qrValue, true);
    }

    public void setQRValue(int questId, String key, String value) {
        QuestManager qm = chr.getQuestManager();
        Quest quest = qm.getQuestById(questId);
        if (quest == null) {
            quest = QuestData.createQuestFromId(questId);
            qm.addQuest(quest);
        }
        quest.setProperty(key, value);
        chr.write(WvsContext.questRecordExMessage(quest));
    }

    public void setQRValue(int questId, String qrValue, boolean ex) {
        setQRValue(chr, questId, qrValue, ex);
    }

    public void setQRValue(Char chr, int questId, String qrValue, boolean ex) {
        Quest quest = chr.getQuestManager().getQuests().get(questId);
        if (quest == null) {
            quest = QuestData.createQuestFromId(questId);
            chr.getQuestManager().addQuest(quest);
        }
        quest.setQrValue(qrValue);
        updateQRValue(questId, ex);
    }

    public void addQRValue(int questId, String qrValue) {
        addQRValue(questId, qrValue, true);
    }

    public void addQRValue(int questId, String qrValue, boolean ex) {
        String qrVal = getQRValue(questId);
        if (qrVal.equals("") || qrVal.equals("Quest is Null")) {
            createQuestWithQRValue(questId, qrValue);
            return;
        }
        setQRValue(questId, qrValue + ";" + qrVal);
        updateQRValue(questId, ex);
    }

    public boolean isComplete(int questID) {
        return chr.getQuestManager().isComplete(questID);
    }

    public void updateQRValue(int questId, boolean ex) {
        Quest quest = chr.getQuestManager().getQuests().get(questId);
        if (quest == null) {
            log.error(String.format("The user does not have the quest %d.", questId));
            return;
        }
        if (ex) {
            chr.write(WvsContext.questRecordExMessage(quest));
        } else {
            chr.write(WvsContext.questRecordMessage(quest));
        }
    }

    public String getCurrentDateAsString() {
        return FileTime.currentTime().toYYMMDD();
    }


    // Party Quest-related methods -------------------------------------------------------------------------------------

    public void incrementMonsterParkCount() {
        chr.setMonsterParkCount((byte) (chr.getMonsterParkCount() + 1));
    }

    public byte getMonsterParkCount() {
        return chr.getMonsterParkCount();
    }

    public String getDay() {
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
    }

    public int getMPExpByMobId(int templateId) {
        return MonsterPark.getExpByMobId(templateId);
    }

    public int getMPReward() {
        return MonsterPark.getRewardByDay();
    }

    public long getPQExp() {
        return getPQExp(chr);
    }

    public long getPQExp(Char chr) {
        return GameConstants.PARTY_QUEST_EXP_FORMULA(chr);
    }


    // Boss-related methods --------------------------------------------------------------------------------------------

    @Override
    public void setDeathCount(int deathCount) {
        Instance info = chr.getInstance();
        if (info == null) {
            chr.setDeathCount(deathCount);
            chr.write(UserLocal.deathCountInfo(deathCount));
        } else {
            for (Char chr : info.getChars()) {
                chr.setDeathCount(deathCount);
                chr.write(UserLocal.deathCountInfo(deathCount));
            }
        }
    }

    public void createObstacleAtom(ObtacleAtomEnum oae, int key, int damage, int velocity, int amount, int proc) {
        createObstacleAtom(oae, key, damage, velocity, 0, amount, proc);
    }

    @Override
    public void createObstacleAtom(ObtacleAtomEnum oae, int key, int damage, int velocity, int angle, int amount, int proc) {
        Field field = chr.getField();
        int xLeft = field.getVrLeft();
        int yTop = field.getVrTop();

        ObtacleInRowInfo obtacleInRowInfo = new ObtacleInRowInfo(4, false, 5000, 0, 0, 0);
        ObtacleRadianInfo obtacleRadianInfo = new ObtacleRadianInfo(4, 0, 0, 0, 0);
        Set<ObtacleAtomInfo> obtacleAtomInfosSet = new HashSet<>();

        for (int i = 0; i < amount; i++) {
            if (Util.succeedProp(proc)) {
                int randomX = new Random().nextInt(field.getWidth()) + xLeft;
                Position position = new Position(randomX, yTop);
                Foothold foothold = field.findFootHoldBelow(position);
                if (foothold != null) {
                    int footholdY = foothold.getYFromX(position.getX());
                    int height = position.getY() - footholdY;
                    height = height < 0 ? -height : height;

                    obtacleAtomInfosSet.add(new ObtacleAtomInfo(oae.getType(), key, position, new Position(), oae.getHitBox(),
                            damage, 0, 0, height, 0, velocity, height, angle));
                }
            }
        }

        field.broadcastPacket(FieldPacket.createObtacle(ObtacleAtomCreateType.NORMAL, obtacleInRowInfo, obtacleRadianInfo, obtacleAtomInfosSet));
    }

    public void stopEvents() {
        Set<ScheduledFuture> events = getEvents();
        events.forEach(st -> st.cancel(true));
        events.clear();
        Field field;
        if (chr != null) {
            field = chr.getField();
        } else {
            field = this.field;
        }
        field.broadcastPacket(FieldPacket.clock(ClockPacket.removeClock()));
    }

    private Set<ScheduledFuture> getEvents() {
        return events;
    }

    public void addEvent(ScheduledFuture event) {
        getEvents().add(event);
    }

    public void showGolluxMiniMap() {
        List<Integer> fieldIdList = Arrays.asList(
                863010100,    // Road to Gollux

                863010200,    // Lower Right leg
                863010210,    // Upper Right Leg
                863010220,    // Lower Left Leg
                863010230,    // Upper Left Leg
                863010240,    // Abdomen

                863010300,    // Lower Left Torso
                863010310,    // Upper Left Torso
                863010320,    // Upper Right Arm
                863010330,    // Right Shoulder

                863010400,    // Lower Right Torso
                863010410,    // Upper Right Torso
                863010420,    // Upper Left Arm
                863010430,    // Left Shoulder

                863010500,    // Heart

                863010600    // Head
        );
        Map<String, GolluxMiniMapFieldClearType> gFieldMap = new HashMap<>();
        gFieldMap.put("clearType", GolluxMiniMapFieldClearType.Defeated);

        for (int gFieldId : fieldIdList) {
            Field gField = chr.getInstance().getFields().get(gFieldId);
            if (gField == null) {
                continue;
            }

            if (gField.hasProperty("Killed") && (boolean) gField.getProperty("Killed")) {
                gFieldMap.put(gFieldId + "", GolluxMiniMapFieldClearType.Defeated);
            } else if (gField.hasProperty("Spawned") && (boolean) gField.getProperty("Spawned")) {
                gFieldMap.put(gFieldId + "", GolluxMiniMapFieldClearType.Attacked);
            } else {
                gFieldMap.put(gFieldId + "", GolluxMiniMapFieldClearType.Unvisited);
            }
        }

        chr.getInstance().broadcast(FieldPacket.golluxMiniMap(gFieldMap));
    }

    public void golluxPortalOpen(String happeningName) {
        chr.getField().broadcastPacket(FieldPacket.golluxPortalOpen(happeningName));
    }


    // Character Temporary Stat-related methods ------------------------------------------------------------------------

    @Override
    public void giveCTS(CharacterTemporaryStat cts, int nOption, int rOption, int time) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = nOption;
        o.rOption = rOption;
        o.tOption = time;
        tsm.putCharacterStatValue(cts, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public void removeCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(cts, false);
    }

    @Override
    public void removeBuffBySkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStatsBySkill(skillId);
    }

    @Override
    public boolean hasCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(cts);
    }

    @Override
    public int getnOptionByCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return hasCTS(cts) ? tsm.getOption(cts).nOption : 0;
    }

    @Override
    public void rideVehicle(int mountID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);

        tsb.setNOption(mountID);
        tsb.setROption(0);
        tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
        tsm.sendSetStatPacket();
    }


    // InGameDirectionEvent methods ------------------------------------------------------------------------------------

    @Override
    public int moveCamera(boolean back, int speed, int x, int y) {
        getNpcScriptInfo().setMessageType(NpcMessageType.AskIngameDirection);
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraMove(back, speed, new Position(x, y))));
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    public void moveCamera(int speed, int x, int y) {
        moveCamera(false, speed, x, y);
    }

    public void moveCameraBack(int speed) {
        moveCamera(true, speed, chr.getPosition().getX(), chr.getPosition().getY());
    }

    public void zoomCameraNoResponse(int time, int scale, int timePos, int x, int y) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraZoom(time, scale, timePos, new Position(x, y))));
    }

    public int zoomCamera(int time, int scale, int timePos, int x, int y) {
        getNpcScriptInfo().setMessageType(NpcMessageType.AskIngameDirection);
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraZoom(time, scale, timePos, new Position(x, y))));
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    @Override
    public int zoomCamera(int inZoomDuration, int scale, int x, int y) {
        getNpcScriptInfo().setMessageType(NpcMessageType.AskIngameDirection);
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraZoom(inZoomDuration, scale, 1000, new Position(x, y))));
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    public void zoomCameraNoResponse(int zoomInDuration, int scale, int x, int y) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraZoom(zoomInDuration, scale, 1000, new Position(x, y))));
    }

    @Override
    public void resetCamera() {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraOnCharacter(0))); // 0 resets the Camera
    }

    public void setCameraOnNpc(int npcTemplateId) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.cameraOnCharacter(npcTemplateId)));
    }

    @Override
    public int sendDelay(int delay) {
        getNpcScriptInfo().setMessageType(NpcMessageType.AskIngameDirection);
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.delay(delay)));
        if (getLastActiveScriptType() == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    @Override
    public void doEventAndSendDelay(int delay, String methodName, Object... args) {
        invoke(chr.getScriptManager(), methodName, args);
        sendDelay(delay);
    }

    @Override
    public void forcedMove(boolean left, int distance) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.forcedMove(left, distance)));
    }

    @Override
    public void forcedFlip(boolean left) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.forcedFlip(left)));
    }

    @Override
    public void forcedAction(int type, int duration) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.forcedAction(type, duration)));
    }

    @Override
    public void forcedInput(int type) {
        ForcedInputType fit = ForcedInputType.getByVal(type);
        if (fit == null) {
            log.error(String.format("Unknown Forced Input Type %d", type));
            return;
        }
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.forcedInput(type)));
    }

    public void patternInputRequest(String pattern, int act, int requestCount, int time) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.patternInputRequest(pattern, act, requestCount, time)));
    }

    @Override
    public void hideUser(boolean hide) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.vansheeMode(hide)));
    }

    public void showEffect(String path, int duration, int x, int y) {
        showEffect(path, duration, x, y, 0, 0, true, 0);
    }

    @Override
    public void showEffect(String path, int duration, int x, int y, int z, int npcIdForExtend, boolean onUser, int idk2) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(path, duration, new Position(x, y), z, npcIdForExtend, onUser, idk2)));
    }

    public void showEffectOnPosition(String path, int duration, int x, int y) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(path, duration,
                new Position(x, y), 0, 1, false, 0)));
    }

    public void showBalloonMsgOnNpc(String path, int duration, int x, int y, int templateID) {
        int objectID = getNpcObjectIdByTemplateId(templateID);
        if (objectID == 0) return;
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(path, duration,
                new Position(x, y), 0, objectID, false, 0)));
    }

    public void showBalloonMsgOnNpc(String path, int duration, int templateID) {
        showBalloonMsgOnNpc(path, duration, 0, -100, templateID);
    }

    public void showNpcEffectOnPosition(String path, int x, int y, int templateID) {
        int objectID = getNpcObjectIdByTemplateId(templateID);
        if (objectID == 0) return;
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(path, 0,
                new Position(x, y), 0, objectID, false, 0)));
    }

    public void showBalloonMsg(String path, int duration) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.effectPlay(path, duration,
                new Position(0, -100), 0, 0, true, 0)));
    }

    public int sayMonologue(String text, boolean isEnd) {
        getNpcScriptInfo().setMessageType(NpcMessageType.Monologue);
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.monologue(text, isEnd)));
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    public void avatarLookSet(int[] equipIDs) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.avatarLookSet(equipIDs)));
    }

    public void removeAdditionalEffect() {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.removeAdditionalEffect()));
    }

    public void faceOff(int faceItemID) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.faceOff(faceItemID)));
    }

    public void monologueScroll(String msg, boolean stayModal, short align, int updateSpeedTime, int decTic) {
        chr.write(UserLocal.inGameDirectionEvent(InGameDirectionEvent.monologueScroll(msg, stayModal, align,
                updateSpeedTime, decTic)));
    }

    // Clock methods ---------------------------------------------------------------------------------------------------

    public Clock createStopWatch(int seconds) {
        return new Clock(ClockType.StopWatch, chr.getField(), seconds);
    }

    public Clock createClock(int seconds) {
        return new Clock(ClockType.SecondsClock, chr.getField(), seconds);
    }

    public void createClock(int hours, int minutes, int seconds) {
        chr.write(FieldPacket.clock(ClockPacket.hmsClock((byte) hours, (byte) minutes, (byte) seconds)));
        addEvent(EventManager.addEvent(this::removeClock, seconds + minutes * 60 + hours * 3600, TimeUnit.SECONDS));
    }

    public void removeClock() {
        chr.write(FieldPacket.clock(ClockPacket.removeClock()));
    }


    // Other methods ---------------------------------------------------------------------------------------------------

    @Override
    public boolean addDamageSkin(int itemID) {
        Account acc = chr.getAccount();
        DamageSkinType error = null;
        if (acc.getDamageSkins().size() >= GameConstants.DAMAGE_SKIN_MAX_SIZE) {
            error = DamageSkinType.Res_Fail_SlotCount;
        } else if (acc.getDamageSkinByItemID(itemID) != null) {
            error = DamageSkinType.Res_Fail_AlreadyExist;
        }
        if (error != null) {
            chr.write(UserLocal.damageSkinSaveResult(DamageSkinType.Req_Reg, error, null));
        } else {
            QuestManager qm = chr.getQuestManager();
            Quest q = qm.getQuests().getOrDefault(QuestConstants.DAMAGE_SKIN, null);
            if (q == null) {
                q = new Quest(QuestConstants.DAMAGE_SKIN, QuestStatus.Started);
                qm.addQuest(q);
            }
            chr.consumeItem(itemID, 1);
            DamageSkinSaveData dssd = DamageSkinSaveData.getByItemID(itemID);
            q.setQrValue(String.valueOf(dssd.getDamageSkinID()));
            acc.addDamageSkin(dssd);
            chr.setDamageSkin(dssd);
            chr.write(UserLocal.damageSkinSaveResult(DamageSkinType.Req_Reg,
                    DamageSkinType.Res_Success, chr));
            chr.write(UserPacket.setDamageSkin(chr));
            chr.write(WvsContext.questRecordMessage(q));
        }
        return error == null;
    }

    @Override
    public void openUI(UIType uiType) {
        int id = uiType.getVal();
        openUI(id);
    }

    public void openUI(int id) {
        chr.write(FieldPacket.openUI(id));
    }

    @Override
    public void openDimensionalMirror() {
        chr.write(FieldPacket.openDimensionalMirror());
    }

    @Override
    public void closeUI(UIType uiType) {
        int id = uiType.getVal();
        closeUI(id);
    }

    public void closeUI(int id) {
        chr.write(FieldPacket.closeUI(id));
    }

    @Override
    public void showClearStageExpWindow(long expGiven) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.showClearStageExpWindow((int) expGiven)));
        giveExpNoMsg(expGiven);
    }

    public void removeBlowWeather() {
        chr.write(FieldPacket.removeBlowWeather());
    }

    public void blowWeather(int itemID, String message) {
        removeBlowWeather();// removing old one if exists.
        chr.write(FieldPacket.blowWeather(itemID, message, 10, null));
    }

    public void playSound(String sound) {
        playSound(sound, 100);
    }// default

    public void playSound(String sound, int vol) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.playSound(sound, vol)));
    }

    public void blind(int enable, int x, int color, int time) {
        blind(enable, x, color, 0, 0, time);
    }

    public void blind(int enable, int x, int color, int unk1, int unk2, int time) {
        chr.write(FieldPacket.fieldEffect(FieldEffect.blind(enable, x, color, unk1, unk2, time)));
    }

    @Override
    public int getRandomIntBelow(int upBound) {
        return new Random().nextInt(upBound);
    }

    public void showEffect(String dir) {
        showEffect(dir, 0);
    }

    public void showEffect(String dir, int delay) {
        showEffect(dir, 4, delay);
    }

    public void showScene(String xmlPath, String sceneName, String sceneNumber) {
        Scene scene = new Scene(chr, xmlPath, sceneName, sceneNumber);
        scene.createScene();
    }

    @Override
    public void showEffect(String dir, int placement, int delay) {
        chr.write(UserPacket.effect(Effect.effectFromWZ(dir, false, delay, placement, 0)));
    }

    public void avatarOriented(String effectPath) {
        chr.write(UserPacket.effect(Effect.avatarOriented(effectPath)));
    }

    public void reservedEffect(String effectPath) {
        chr.write(UserPacket.effect(Effect.reservedEffect(effectPath)));

        String[] splitted = effectPath.split("/");
        String sceneName = splitted[splitted.length - 2];
        String sceneNumber = splitted[splitted.length - 1];
        String xmlPath = effectPath.replace("/" + sceneName, "").replace("/" + sceneNumber, "").replace("Effect/", "Effect.wz/");

        int fieldID = new Scene(chr, xmlPath, sceneName, sceneNumber).getTransferField();
        if (fieldID != 0) {
            chr.setTransferField(fieldID);
        }
    }

    public void reservedEffect(boolean screenCoord, int x, int y, String effectName) {
        chr.write(UserPacket.effect(Effect.reservedEffect(screenCoord, x, y, effectName)));
    }

    public void reservedEffectRepeat(String effectPath, boolean start) {
        chr.write(UserPacket.effect(Effect.reservedEffectRepeat(effectPath, start)));
    }

    public void reservedEffectRepeat(String effectName, boolean idk, boolean show, int x, int y, int duration) {
        chr.write(UserPacket.effect(Effect.reservedEffectRepeat(effectName, idk, show, x, y, duration)));
    }

    public void reservedEffectRepeat(String effectPath) {
        reservedEffectRepeat(effectPath, true);
    }

    public void playExclSoundWithDownBGM(String soundPath, int volume) {
        chr.write(UserPacket.effect(Effect.playExclSoundWithDownBGM(soundPath, volume)));
    }

    public void blindEffect(boolean blind) {
        chr.write(UserPacket.effect(Effect.blindEffect(blind)));
    }

    public void fadeInOut(int fadeIn, int delay, int fadeOut, int alpha) {
        chr.write(UserPacket.effect(Effect.fadeInOut(fadeIn, delay, fadeOut, alpha)));
    }

    public void createFieldTextEffect(String msg, int letterDelay, int showTime, int clientPosition,
                                      int x, int y, int align, int lineSpace, int type,
                                      int enterType, int leaveType) {
        TextEffectType tet = TextEffectType.values()[type];
        chr.write(UserPacket.effect(Effect.createFieldTextEffect(msg, letterDelay, showTime, clientPosition, new Position(x, y),
                align, lineSpace, tet, enterType, leaveType)));
    }

    public void speechBalloon(boolean normal, int idx, int linkType, String speech, int time, int align, int x,
                              int y, int z, int lineSpace, int npcTemplateId, int idk) {
        chr.write(UserPacket.effect(Effect.speechBalloon(normal, idx, linkType, speech, time, align, x, y, z, lineSpace, npcTemplateId, idk)));
    }

    public String formatNumber(String number) {
        return Util.formatNumber(number);
    }

    private Object invoke(Object invokeOn, String methodName, Object... args) {
        List<Class<?>> classList = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
        Class<?>[] classes = classList.stream().map(Util::convertBoxedToPrimitiveClass).toArray(Class<?>[]::new);
        Method func;
        try {
            func = getClass().getMethod(methodName, classes);
            return func.invoke(invokeOn, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void invokeForParty(String methodName, Object... args) {
        for (PartyMember pm : chr.getParty().getMembers()) {
            boolean fromDB = false;
            Char chr = pm.getChr();
            if (chr == null) {
                chr = Char.getFromDBById(pm.getCharID());
                fromDB = true;
            }
            invoke(chr.getScriptManager(), methodName, args);
            if (fromDB) {
                DatabaseManager.saveToDB(chr);
            }
        }
    }

    public ScheduledFuture invokeAfterDelay(long delay, String methodName, Object... args) {
        Object[] funcArgs = args;
        if ("warp".equals(methodName)) {
            // kinda hacky method to make warps execute immediately when invoking after delay
            funcArgs = new Object[args.length + 1];
            System.arraycopy(args, 0, funcArgs, 0, args.length);
            funcArgs[funcArgs.length - 1] = false;
        }
        Object[] a = funcArgs;
        ScheduledFuture sf = EventManager.addEvent(() -> invoke(this, methodName, a), delay);
        addEvent(sf);
        return sf;
    }

    public ScheduledFuture invokeAtFixedRate(long initialDelay, long delayBetweenExecutions,
                                             int executes, String methodName, Object... args) {
        ScheduledFuture scheduledFuture;
        if (executes == 0) {
            scheduledFuture = EventManager.addFixedRateEvent(() -> invoke(this, methodName, args), initialDelay,
                    delayBetweenExecutions);
        } else {
            scheduledFuture = EventManager.addFixedRateEvent(() -> invoke(this, methodName, args), initialDelay,
                    delayBetweenExecutions, executes);
        }
        addEvent(scheduledFuture);
        return scheduledFuture;
    }

    @Override
    public int playVideoByScript(String videoPath) {
        getNpcScriptInfo().setMessageType(NpcMessageType.PlayMovieClip);
        chr.write(UserLocal.videoByScript(videoPath, false));
        Object response = getScriptInfoByType(getLastActiveScriptType()).awaitResponse();
        if (response == null) {
            throw new NullPointerException(INTENDED_NPE_MSG);
        }
        return (int) response;
    }

    public void setFuncKeyByScript(boolean add, int action, int key) {
        chr.write(UserLocal.setFuncKeyByScript(add, action, key));
        chr.getFuncKeyMap().putKeyBinding(key, add ? (byte) 1 : (byte) 0, action);
    }

    public void setMapTaggedObjectVisible(String key, boolean visible, int manual, int delay) {
        chr.write(MapLoadable.setMapTaggedObjectVisisble(new MapTaggedObject(key, visible, manual, delay)));
    }

    public void addPopUpSay(int npcID, int duration, String message, String effect) {
        chr.write(UserLocal.addPopupSay(npcID, duration, message, effect));
    }

    public void moveParticleEff(String type, int startX, int startY, int endX, int endY, int moveTime, int totalCount, int oneSprayMin, int oneSprayMax) {
        chr.write(UserLocal.moveParticleEff(type, new Position(startX, startY), new Position(endX, endY), moveTime, totalCount, oneSprayMin, oneSprayMax));
    }

    public void levelUntil(int toLevel) {
        chr.addLevelsTo(toLevel);
        /*
        short level = chr.getLevel();
        if (level >= toLevel) {
            return;
        }
        while (level < toLevel) {
            addLevel(1);
            level++;
        }
         */
    }

    public void balloonMsg(String message) {
        chr.write(UserLocal.balloonMsg(message, 100, 3, null));
    }


    public void openNodestone(int id) {
        if (!chr.getQuestManager().hasQuestCompleted(QuestConstants.FIFTH_JOB_QUEST)) {
            chr.chatMessage("You need to be 5th job to open this item.");
            return;
        }
        if (id == 2436037) {
            MatrixRecord mr = new MatrixRecord();
            mr.setIconID(10000000);
            mr.setMaxLevel(25);
            mr.setSlv(1);
            mr.setSkillID1(400001000);
            chr.getMatrixRecords().add(mr);
            chr.write(WvsContext.nodestoneOpenResult(mr));
            chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
        } else if (id == 2438411 || id == 2438412) {
            MatrixRecord mr = new MatrixRecord();
            mr.setIconID(10000024);
            mr.setMaxLevel(25);
            mr.setSlv(1);
            mr.setSkillID1(400001039);
            chr.getMatrixRecords().add(mr);
            chr.write(WvsContext.nodestoneOpenResult(mr));
            chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
        } else {
            List<VCoreInfo> infos = new ArrayList<>(VCoreData.getPossibilitiesByJob(chr.getJob()));
            int rng = Util.getRandom(99);
            if (rng < GameConstants.NODE_ENFORCE_CHANCE) {
                infos = infos.stream().filter(VCoreInfo::isEnforce).collect(Collectors.toList());
            } else if (rng - GameConstants.NODE_ENFORCE_CHANCE < GameConstants.NODE_SKILL_CHANCE) {
                infos = infos.stream().filter(VCoreInfo::isSkill).collect(Collectors.toList());
            } else {
                infos = infos.stream().filter(VCoreInfo::isSpecial).collect(Collectors.toList());
            }
            MatrixRecord mr = new MatrixRecord();
            for (int i = 0; i < 3; i++) {
                VCoreInfo vci = Util.getRandomFromCollection(infos);
                infos.remove(Util.findWithPred(infos, v -> v.getIconID() == vci.getIconID()));
                switch (i) {
                    case 0:
                        mr.setIconID(vci.getIconID());
                        mr.setMaxLevel(vci.getMaxLevel());
                        mr.setSkillID1(vci.getSkillID());
                        mr.setSlv(1);
                        if (vci.isSoloNode()) {
                            i = 3;
                        } else {
                            infos = infos.stream().filter(v -> !v.isSoloNode()).collect(Collectors.toList());
                        }
                        break;
                    case 1:
                        mr.setSkillID2(vci.getSkillID());
                        break;
                    case 2:
                        mr.setSkillID3(vci.getSkillID());
                        break;
                }
            }
            chr.getMatrixRecords().add(mr);
            chr.write(WvsContext.nodestoneOpenResult(mr));
            chr.write(WvsContext.matrixUpdate(chr.getSortedMatrixRecords(), false, 0, 0));
        }
    }
    public void hireTutor(boolean set) {
        chr.hireTutor(set);
    }

    public void tutorAutomatedMsg(int id) {
        tutorAutomatedMsg(id, 10000);
    }

    public void tutorAutomatedMsg(int id, int duration) {
        chr.tutorAutomatedMsg(id, duration);
    }

    public void tutorCustomMsg(String message, int width, int duration) {
        chr.tutorCustomMsg(message, width, duration);
    }

    public boolean hasTutor() {
        return chr.hasTutor();
    }

    public int getMakingSkillLevel(int skillID) {
        return chr.getMakingSkillLevel(skillID);
    }

    public boolean isAbleToLevelUpMakingSkill(int skillID) {
        int neededProficiency = SkillConstants.getNeededProficiency(chr.getMakingSkillLevel(skillID));
        if (neededProficiency <= 0) {
            return false;
        }
        return chr.getMakingSkillProficiency(skillID) >= neededProficiency;
    }

    public void makingSkillLevelUp(int skillID) {
        chr.makingSkillLevelUp(skillID);
    }

    private ScriptMemory getMemory() {
        return memory;
    }

    public int getUnionCoin() {
        return chr.getUnion().getUnionCoin();
    }

    public void addUnionCoin(int amount) {
        QuestManager qm = chr.getQuestManager();
        Union union = chr.getUnion();
        union.addUnionCoin(amount);
        Quest quest = qm.getOrCreateQuestById(QuestConstants.UNION_COIN);
        quest.setProperty("lastTime", FileTime.currentTime().toYYMMDDHHMMSS());
        quest.setProperty("coin", union.getUnionCoin());
        chr.write(WvsContext.questRecordExMessage(quest));
    }

    public int getUnionRank() {
        return chr.getUnion().getUnionRank();
    }

    private int getUnionRankIndex() {
        int high = getUnionRank() / 100;
        int low = getUnionRank() % 100;
        return (low - 1) + (high - 1) * 5;
    }

    private boolean isMaxUnionRank() {
        return getUnionRank() == 405;
    }

    public String getUnionRankName() {
        return UnionMember.ranks[getUnionRankIndex()];
    }

    public String getUnionNextRankName() {
        return UnionMember.ranks[isMaxUnionRank() ? getUnionRankIndex() : getUnionRankIndex() + 1];
    }

    public int getUnionCoinReq() {
        return UnionMember.reqCoin[getUnionRankIndex()];
    }

    public int getUnionLevelReq() {
        return UnionMember.reqLev[getUnionRankIndex()];
    }

    public int getUnionLevel() {
        int total = 0;
        for (Char chr : chr.getUnion().getEligibleUnionChars()) {
            total += chr.getLevel();
        }
        return total;
    }

    public int getUnionCharacterCount() {
        return chr.getUnion().getEligibleUnionChars().size();
    }

    public int getUnionAssignedCharacterCount() {
        return chr.getUnion().getActiveUnionChars(chr.getActiveUnionPreset()).size();
    }

    public int getUnionAssignedMaxCharacterCount() {
        return UnionMember.attackerCount[getUnionRankIndex()];
    }

    public int getUnionAssignedNextMaxCharacterCount() {
        return UnionMember.attackerCount[isMaxUnionRank() ? getUnionRankIndex() : getUnionRankIndex() + 1];
    }

    public void incrementUnionRank() {
        chr.incrementUnionRank();
    }

    public void addStorageSlots(byte amount) {
        chr.getAccount().getTrunk().addSlots(amount);
    }

    public void addInventorySlotsByInvType(byte amount, byte type) {
        chr.getInventoryByType(InvType.getInvTypeByVal(type)).addSlots(amount);
    }

    public int getSlotsLeftToAddByInvType(byte type) {
        return GameConstants.MAX_INV_SLOTS - chr.getInventoryByType(InvType.getInvTypeByVal(type)).getSlots();
    }

    // Event Related Methods -------------------------------------------------------------------------------------------

    public void addCooldownTimeForParty(EventType type, int time) {
        for (PartyMember partyMember : chr.getParty().getPartyMembers()) {
            if (partyMember != null && partyMember.getChr() != null) {
                partyMember.getChr().getAccount().addCooldownTime(type, time);
            }
        }
    }

    public boolean partyHasCoolDown(EventType type, int amountAllowed) {
        if (chr.getUser().getAccountType() == AccountType.Admin) {
            return false;
        }
        if (ServerConstants.RESTART_MINUTES > 0 && (Server.getInstance().MAINTENANCE_ACTIVE || Server.getInstance().MAINTENANCE_MODE || (System.currentTimeMillis() - Server.getInstance().upTime) >= (ServerConstants.RESTART_MINUTES - 30) * 60000L))
            return true;
        if (chr.getParty() == null) {
            return true;
        }
        for (PartyMember partyMember : chr.getParty().getPartyMembers()) {
            if (partyMember != null && partyMember.getChr() != null && partyMember.getChr().getScriptManager().getEventAmountDone(type) >= amountAllowed) {
                return true;
            }
        }
        return false;
    }

    public String getTimeUntilEventReset(EventType type) {
//        if (ServerConstants.RESTART_MINUTES > 0 && (Server.getInstance().MAINTENANCE_ACTIVE || Server.getInstance().MAINTENANCE_MODE || (System.currentTimeMillis() - Server.getInstance().upTime) >= ((ServerConstants.RESTART_MINUTES - 30) * 60000L)))
//            return "server restarting soon";
        long msTillReset = getMillisecondsUntilEventReset(type);
        long days = TimeUnit.MILLISECONDS.toDays(msTillReset);
        long hours = TimeUnit.MILLISECONDS.toHours(msTillReset) % TimeUnit.DAYS.toHours(1);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(msTillReset) % TimeUnit.HOURS.toMinutes(1);
        return "" + (days > 0 ? days + " day(s) " : "") + (hours > 0 ? hours + " hour(s) " : "") + (minutes > 0 ? minutes + " minute(s) " : "");
    }

    public String getPartyCooldownRemainingString(EventType type) {
        StringBuilder returnString = new StringBuilder("");
        if (chr.getParty() == null) {
            returnString.append("You have ").append(getTimeUntilEventReset(type)).append(" left on your cooldown.");
        } else {
            Arrays.stream(chr.getParty().getPartyMembers()).filter(Objects::nonNull).map(PartyMember::getChr).forEach(chrcd -> {
                if (chrcd.getScriptManager().getMillisecondsUntilEventReset(type) > 0) {
                    boolean ldr = chrcd == chr;
                    returnString.append("\r\n");
                    if (ldr)
                        returnString.append("You have ");
                    else
                        returnString.append(chrcd.getName()).append(" has ");
                    returnString.append(chrcd.getScriptManager().getTimeUntilEventReset(type)).append("left on ");
                    if (ldr)
                        returnString.append("your ");
                    else
                        returnString.append("their ");
                    returnString.append("cooldown.");
                }
            });
        }

        return returnString.toString();
    }

    public int getEventAmountDone(EventType type) {
        return chr.getAccount().getEventAmountDone(type.getVal());
    }

    public long getMillisecondsUntilEventReset(EventType type) {
        long remainingTime = getEventAmountDone(type) == 0 ? 0 : chr.getAccount().getCoolDownByType(type.getVal()).getNextResetTime() - System.currentTimeMillis();
        return remainingTime < 0 ? 0 : remainingTime;
    }

    // End of Event Related Methods ------------------------------------------------------------------------------------
    // [START] Custom Methods

    public String getServerName() {
        return ServerConfig.SERVER_NAME;
    }

    public void warpToHub() {
        chr.warp(GameConstants.PLAYER_HUB_MAP);
    }

    public void doJobStart() {
        chr.getJobHandler().handleJobStart();
    }

    public void doJobEnd() {
        chr.getJobHandler().handleJobEnd();
    }

    // [END] Custom Methods

    public Tuple<Integer, List<Equip>> getDressingRoomEquips(int type, int fromIndex, int limit) {
        List<Equip> equips;
        DressingRoom.DressingRoomType drt = DressingRoom.DressingRoomType.getTypeById(type);

        switch (drt) {
            case Hats -> equips = DressingRoom.getHats();
            case Tops -> equips = DressingRoom.getTops();
            case Bottoms -> equips = DressingRoom.getBottoms();
            case Overalls -> equips = DressingRoom.getOveralls();
            case Shoes -> equips = DressingRoom.getShoes();
            case Gloves -> equips = DressingRoom.getGloves();
            case Capes -> equips = DressingRoom.getCapes();
            case Weapons -> equips = DressingRoom.getWeapons();
            case FaceAccessory -> equips = DressingRoom.getFaceAccessory();
            case EyeAccessory -> equips = DressingRoom.getEyeAccessory();
            case Rings -> equips = DressingRoom.getRings();
            default -> throw new IllegalStateException("Unexpected value: " + drt);
        }

        int toIndex = fromIndex + limit;

        if (toIndex > equips.size()) {
            toIndex = equips.size();
        }

        return new Tuple<>(equips.size(), equips.subList(fromIndex, toIndex));
    }

    public Tuple<Integer, Map<Integer, String>> getDressingRoomEquipsSearch(String query) {
        Map<Integer, String> map = StringData.getItemStringByName(query.toLowerCase());
        Set<Integer> nonEquips = new HashSet<>();
        for (int itemId : map.keySet()) {
            if (!ItemConstants.isEquip(itemId) ||
                    !ItemData.getItemDeepCopy(itemId).isCash() ||
                    ItemConstants.isRemovedFromCashShop(itemId)) {
                nonEquips.add(itemId);
            }
        }
        for (int itemId : nonEquips) {
            map.remove(itemId);
        }

        return new Tuple<>(map.size(), map);
    }

    public int getDressingRoomEquipType(int itemId) {
        if (ItemConstants.isHat(itemId)) {
            return DressingRoom.DressingRoomType.Hats.getId();
        } else if (ItemConstants.isTop(itemId)) {
            return DressingRoom.DressingRoomType.Tops.getId();
        } else if (ItemConstants.isBottom(itemId)) {
            return DressingRoom.DressingRoomType.Bottoms.getId();
        } else if (ItemConstants.isOverall(itemId)) {
            return DressingRoom.DressingRoomType.Overalls.getId();
        } else if (ItemConstants.isShoe(itemId)) {
            return DressingRoom.DressingRoomType.Shoes.getId();
        } else if (ItemConstants.isGlove(itemId)) {
            return DressingRoom.DressingRoomType.Gloves.getId();
        } else if (ItemConstants.isCape(itemId)) {
            return DressingRoom.DressingRoomType.Capes.getId();
        } else if (ItemConstants.isWeapon(itemId)) {
            return DressingRoom.DressingRoomType.Weapons.getId();
        }

        return 0;
    }

    public void printStyle(Cosmetic cosmetic) {
        System.out.printf("id: %d, name: %s", cosmetic.getId(), cosmetic.getName());
    }
}
