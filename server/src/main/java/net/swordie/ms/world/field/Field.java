package net.swordie.ms.world.field;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.client.jobs.resistance.OpenGate;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.handlers.TaskMan;
import net.swordie.ms.life.*;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.npc.Npc;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.scripts.ScriptManager;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.bosses.gollux.FallingCatcher;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleInRowInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.time;

/**
 * Created on 12/14/2017.
 */
public class Field {
    private static final Logger log = Logger.getLogger(Field.class);
    private Rect rect;
    private int vrTop, vrLeft, vrBottom, vrRight;
    private double mobRate;
    private int id;
    private FieldType fieldType;
    private long fieldLimit;
    private int returnMap, forcedReturn, createMobInterval, timeOut, timeLimit, lvLimit, lvForceMove;
    private int consumeItemCoolTime, link;
    private boolean town, swim, fly, reactorShuffle, expeditionOnly, partyOnly, needSkillForFly;
    private Set<Portal> portals;
    private Set<Foothold> footholds;
    private Map<Integer, Life> lifes;
    private List<Char> chars;
    private Map<Life, Char> lifeToControllers;
    private Map<Life, ScheduledFuture> lifeSchedules;
    private String onFirstUserEnter = "", onUserEnter = "";
    private int fixedMobCapacity;
    private int objectIDCounter = 1000000;
    private boolean userFirstEnter = false;
    private String fieldScript = "";
    private ScriptManagerImpl scriptManager;
    private long lastUpdateTime;
    private ScheduledFuture fieldUpdater;
    private RuneStone runeStone;
    private ScheduledFuture runeStoneHordesTimer;
    private int burningFieldLevel;
    private long nextEliteSpawnTime = System.currentTimeMillis();
    private int killedElites;
    private EliteState eliteState;
    private int bossMobID;
    private boolean kishin;
    private List<Integer> noRespawnList = new ArrayList<>();
    private List<OpenGate> openGateList = new ArrayList<>();
    private List<TownPortal> townPortalList = new ArrayList<>();
    private boolean isChannelField;
    private Map<Integer, List<String>> directionInfo;
    private Clock clock;
    private int channel;
    private int averageMobLevel;
    private boolean hasVrInfo;
    private int highestFhYValue;
    private Position reviveCurFieldOfNoTransferPoint;
    private boolean individualMobPool, reviveCurField, reviveCurFieldOfNoTransfer;
    private boolean changeToChannelOnLeave;
    private Map<String, Object> properties;
    private int barrier;
    private int barrierArc;
    private boolean respawn = true;
    private String weatherMsg;
    private long lastRuneUsedTime;
    private int weatherItemID;
    private long weatherEndTime;
    private byte[] weatherAvatarLook;


    private long nextZakArmSmash = System.currentTimeMillis();
    private boolean zakPlatformsVisible = false;
    private int zakArmSmashMobs = 10;

    public Field(int fieldID) {
        this.id = fieldID;
        this.rect = new Rect();
        this.portals = new HashSet<>();
        this.footholds = new HashSet<>();
        this.lifes = new ConcurrentHashMap<>();
        this.chars = new CopyOnWriteArrayList<>();
        this.lifeToControllers = new HashMap<>();
        this.lifeSchedules = new HashMap<>();
        this.directionInfo = new HashMap<>();
        this.properties = new HashMap<>();
        this.fixedMobCapacity = GameConstants.DEFAULT_FIELD_MOB_CAPACITY; // default
        hasVrInfo = vrLeft != 0 || vrTop != 0 || vrRight != 0 || vrBottom != 0;
    }

    public void startFieldScript() {
        startScript(getFieldScript());
    }

    public void startScript(String script) {
        if (!"".equalsIgnoreCase(script)) {
            if (getScriptManager() == null) {
                setScriptManager(new ScriptManagerImpl(this));
            }
            log.debug(String.format("Starting field script %s.", script));
            scriptManager.startScript(getId(), script, ScriptType.Field);
        }
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getVrTop() {
        return vrTop;
    }

    public void setVrTop(int vrTop) {
        setHasVrInfo(vrTop != 0); // edge case if there's fields with an actual vrTop of 0?
        this.vrTop = vrTop;
    }

    public int getVrLeft() {
        return vrLeft;
    }

    public void setReviveCurFieldOfNoTransferPoint(Position reviveCurFieldOfNoTransferPoint) {
        this.reviveCurFieldOfNoTransferPoint = reviveCurFieldOfNoTransferPoint;
    }

    public void setReviveCurField(boolean reviveCurField) {
        this.reviveCurField = reviveCurField;
    }

    public boolean isReviveCurField() {
        return reviveCurField;
    }

    public void setReviveCurFieldOfNoTransfer(boolean reviveCurFieldOfNoTransfer) {
        this.reviveCurFieldOfNoTransfer = reviveCurFieldOfNoTransfer;
    }

    public boolean isReviveCurFieldOfNoTransfer() {
        return reviveCurFieldOfNoTransfer;
    }


    public Position getReviveCurFieldOfNoTransferPoint() {
        return reviveCurFieldOfNoTransferPoint;
    }

    public void setVrLeft(int vrLeft) {
        this.vrLeft = vrLeft;
    }

    public int getVrBottom() {
        return vrBottom;
    }

    public void setVrBottom(int vrBottom) {
        this.vrBottom = vrBottom;
    }

    public int getVrRight() {
        return vrRight;
    }

    public void setVrRight(int vrRight) {
        this.vrRight = vrRight;
    }

    public int getHeight() {
        return getVrTop() - getVrBottom();
    }

    public int getWidth() {
        return getVrRight() - getVrLeft();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public long getFieldLimit() {
        return fieldLimit;
    }

    public void setFieldLimit(long fieldLimit) {
        this.fieldLimit = fieldLimit;
    }

    public Set<Portal> getPortals() {
        return portals;
    }

    public void setPortals(Set<Portal> portals) {
        this.portals = portals;
    }

    public void addPortal(Portal portal) {
        getPortals().add(portal);
    }

    public int getReturnMap() {
        return returnMap;
    }

    public void setReturnMap(int returnMap) {
        this.returnMap = returnMap;
    }

    public int getForcedReturn() {
        return forcedReturn;
    }

    public void setForcedReturn(int forcedReturn) {
        this.forcedReturn = forcedReturn;
    }

    public double getMobRate() {
        return mobRate;
    }

    public void setMobRate(double mobRate) {
        this.mobRate = mobRate;
    }

    public int getCreateMobInterval() {
        return createMobInterval;
    }

    public void setCreateMobInterval(int createMobInterval) {
        this.createMobInterval = createMobInterval;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getLvLimit() {
        return lvLimit;
    }

    public void setLvLimit(int lvLimit) {
        this.lvLimit = lvLimit;
    }

    public int getLvForceMove() {
        return lvForceMove;
    }

    public void setLvForceMove(int lvForceMove) {
        this.lvForceMove = lvForceMove;
    }

    public int getConsumeItemCoolTime() {
        return consumeItemCoolTime;
    }

    public void setConsumeItemCoolTime(int consumeItemCoolTime) {
        this.consumeItemCoolTime = consumeItemCoolTime;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public boolean isSwim() {
        return swim;
    }

    public void setSwim(boolean swim) {
        this.swim = swim;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public boolean isReactorShuffle() {
        return reactorShuffle;
    }

    public void setReactorShuffle(boolean reactorShuffle) {
        this.reactorShuffle = reactorShuffle;
    }

    public boolean isExpeditionOnly() {
        return expeditionOnly;
    }

    public void setExpeditionOnly(boolean expeditionONly) {
        this.expeditionOnly = expeditionONly;
    }

    public boolean isPartyOnly() {
        return partyOnly;
    }

    public void setPartyOnly(boolean partyOnly) {
        this.partyOnly = partyOnly;
    }

    public boolean isNeedSkillForFly() {
        return needSkillForFly;
    }

    public void setNeedSkillForFly(boolean needSkillForFly) {
        this.needSkillForFly = needSkillForFly;
    }

    public String getOnFirstUserEnter() {
        return onFirstUserEnter;
    }

    public void setOnFirstUserEnter(String onFirstUserEnter) {
        this.onFirstUserEnter = onFirstUserEnter;
    }

    public String getOnUserEnter() {
        return onUserEnter;
    }

    public void setOnUserEnter(String onUserEnter) {
        this.onUserEnter = onUserEnter;
    }

    public Portal getPortalByName(String name) {
        return Util.findWithPred(getPortals(), portal -> portal.getName().equals(name));
    }

    public Portal getPortalByID(int id) {
        return Util.findWithPred(getPortals(), portal -> portal.getId() == id);
    }

    public RuneStone getRuneStone() {
        return runeStone;
    }

    public void setRuneStone(RuneStone runeStone) {
        this.runeStone = runeStone;
    }

    public int getBurningFieldLevel() {
        return burningFieldLevel;
    }

    public void setBurningFieldLevel(int burningFieldLevel) {
        this.burningFieldLevel = burningFieldLevel;
    }

    public Foothold findLowestFootHoldBelow(Position pos) {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> fh.getX1() <= pos.getX() && fh.getX2() >= pos.getX()).collect(Collectors.toSet());
        Foothold res = null;
        int lastY = Integer.MAX_VALUE;
        for (Foothold fh : footholds) {
            int y = fh.getYFromX(pos.getX());
            if (res == null && y >= pos.getY()) {
                res = fh;
                lastY = y;
            } else {
                if (y > lastY && y >= pos.getY()) {
                    res = fh;
                    lastY = y;
                }
            }
        }
        return res == null ? new Foothold(-1, -1, -1) : res;
    }

    public Tuple<Foothold, Foothold> getMinMaxNonWallFH() {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> !fh.isWall()).collect(Collectors.toSet());
        Foothold left = footholds.iterator().next(), right = footholds.iterator().next(); // retun vals

        for (Foothold fh : footholds) {
            if (fh.getX1() < left.getX1()) {
                left = fh;
            } else if (fh.getX1() > right.getX1()) {
                right = fh;
            }
        }
        return new Tuple<>(left, right);
    }
    public Foothold findFootHoldBelow(Position pos) {
        Set<Foothold> footholds = getFootholds().stream().filter(fh -> fh.getX1() <= pos.getX() && fh.getX2() >= pos.getX()).collect(Collectors.toSet());
        Foothold res = null;
        int lastY = Integer.MAX_VALUE;
        for (Foothold fh : footholds) {
            int y = fh.getYFromX(pos.getX());
            if (res == null && y >= pos.getY()) {
                res = fh;
                lastY = y;
            } else {
                if (y < lastY && y >= pos.getY()) {
                    res = fh;
                    lastY = y;
                }
            }
        }
        return res;
    }

    public Set<Foothold> getFootholds() {
        return footholds;
    }

    public void setFootholds(Set<Foothold> footholds) {
        this.footholds = footholds;
    }

    public void addFoothold(Foothold foothold) {
        getFootholds().add(foothold);
    }

    public void setFixedMobCapacity(int fixedMobCapacity) {
        this.fixedMobCapacity = fixedMobCapacity;
    }

    public int getFixedMobCapacity() {
        return fixedMobCapacity;
    }

    public Map<Integer, Life> getLifes() {
        return lifes;
    }

    public void addLife(Life life) {
        if (life.getObjectId() < 0) {
            life.setObjectId(getNewObjectID());
        }
        if (!getLifes().values().contains(life)) {
            getLifes().put(life.getObjectId(), life);
            life.setField(this);
            if (life instanceof Mob) {
                if (getScriptManager() != null) {
                    life.addObserver(getScriptManager());
                }
                for (Char chr : getChars()) {
                    life.addObserver(chr.getScriptManager());
                }
            }
        }
    }

    public void removeLife(int id) {
        Life life = getLifeByObjectID(id);
        if (life == null) {
            return;
        }
        getLifes().remove(life.getObjectId());
        getLifeToControllers().remove(life);
        getLifeSchedules().remove(life);
    }

    public void spawnSummon(Summon summon) {
        Summon oldSummon = (Summon) getLifes().values().stream()
                .filter(s -> s instanceof Summon &&
                        ((Summon) s).getChr() == summon.getChr() &&
                        ((Summon) s).getSkillID() == summon.getSkillID())
                .findFirst().orElse(null);
        if (oldSummon != null) {
            removeLife(oldSummon.getObjectId(), false);
        }
        spawnLife(summon, null);
    }

    public void spawnAddSummon(Summon summon) { //Test
        spawnLife(summon, null);
    }

    public void removeSummon(int skillID, int chrID) {
        Summon summon = (Summon) getLifes().values().stream()
                .filter(s -> s instanceof Summon &&
                        ((Summon) s).getChr().getId() == chrID &&
                        ((Summon) s).getSkillID() == skillID)
                .findFirst().orElse(null);
        if (summon != null) {
            removeLife(summon.getObjectId(), false);
        }
    }

    public void spawnWreckage(Char chr, Wreckage wreckage) {
        addLife(wreckage);
        broadcastPacket(FieldPacket.addWreckage(wreckage, getWreckageByChrId(chr.getId()).size()));
        EventManager.addEvent(() -> removeWreckage(chr, wreckage), wreckage.getDuration(), TimeUnit.MILLISECONDS);
    }
    

    public void removeWreckage(Char chr, Wreckage wreckage) {
        removeWreckage(chr, Arrays.asList(wreckage));
    }

    public void removeWreckage(Char chr, List<Wreckage> wreckageList) {
        broadcastPacket(FieldPacket.delWreckage(chr, wreckageList));
        for (Wreckage wreckage : wreckageList) {
            removeLife(wreckage);
        }
    }

    public void spawnLife(Life life, Char onlyChar) {
        addLife(life);
        if (getChars().size() > 0) {
            Char controller = null;
            if (getLifeToControllers().containsKey(life)) {
                controller = getLifeToControllers().get(life);
            }
            if (controller == null) {
                setRandomController(life);
            }
            life.broadcastSpawnPacket(onlyChar);
        }
    }

    private void setRandomController(Life life) {
        // No chars -> set controller to null, so a controller will be assigned next time someone enters this field
        Char controller = null;
        if (getChars().size() > 0) {
            controller = Util.getRandomFromCollection(getChars());
            life.notifyControllerChange(controller);
            if (life instanceof Mob && CustomConstants.AUTO_AGGRO) {
                broadcastPacket(MobPool.forceChase(life.getObjectId(), false));
            }
        }
        putLifeController(life, controller);
    }

    public void removeLife(Life life) {
        removeLife(life.getObjectId(), false);
    }

    public Foothold getFootholdById(int fh) {
        return getFootholds().stream().filter(f -> f.getId() == fh).findFirst().orElse(null);
    }

    public List<Char> getChars() {
        return chars;
    }

    public Char getCharByID(int id) {
        return getChars().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void addChar(Char chr) {
        if (!getChars().contains(chr)) {
            getChars().add(chr);
            if (!isUserFirstEnter()) {
                String script = null;
                if (hasUserFirstEnterScript()) {
                    script = getOnFirstUserEnter();
                } else if (CustomFUEFieldScripts.getByVal(getId()) != null) {
                    script = CustomFUEFieldScripts.getByVal(getId()).toString();
                }
                if (script != null) {
                    chr.chatMessage("First enter script!");
                    chr.getScriptManager().startScript(getId(), script, ScriptType.FirstEnterField);
                    setUserFirstEnter(true);
                }
            }
            execUserEnterScript(chr);
            update(true, false);
        }
        OutPacket enterPacket = UserPool.userEnterField(chr);
        if (!chr.isHide()) {
            broadcastPacket(enterPacket, chr);
        } else {
            for (Char other : getChars()) {
                try {
                    if (!other.getId().equals(chr.getId()) && chr.getUser().getAccountType().ordinal() <= other.getUser().getAccountType().ordinal()) {
                        other.write(enterPacket);
                    }

                } catch (Exception ignore) {
                }
            }
        }
        long curTime = System.currentTimeMillis();
        if (weatherItemID / 10000 == 512 && curTime < weatherEndTime) {
            chr.write(FieldPacket.blowWeather(weatherItemID, weatherMsg, (int) Math.max(1, (weatherEndTime - curTime) / 1000), weatherAvatarLook));
        }
    }


    public void update(boolean enter, boolean reset) {
        if (enter) {
            if (fieldUpdater == null) {
                // fieldUpdater = EventManager.addFixedRateEvent(() -> update(false, false), 1000, 1000);
                fieldUpdater = TaskMan.FieldUpdate.getInstance().addFixedRateEvent(() -> update(false, false), 1000, 1000);
            }
            return;
        } else if (!reset && getChars().isEmpty()) {
            fieldUpdater.cancel(false);
            fieldUpdater = null;
            return;
        }
        long curTime = System.currentTimeMillis();
        if (weatherItemID / 10000 == 512 && weatherEndTime < curTime) {
            this.weatherItemID = 0;
            this.weatherMsg = null;
            broadcastPacket(FieldPacket.removeBlowWeather());
        }
        this.lastUpdateTime = curTime;
    }


    public void blowWeather(int itemID, String message, int seconds, byte[] packedAvatarLook) {
        broadcastPacket(FieldPacket.removeBlowWeather());
        this.weatherItemID = itemID;
        this.weatherMsg = message;
        this.weatherEndTime = System.currentTimeMillis() + 1000 * seconds;
        this.weatherAvatarLook = packedAvatarLook;
        broadcastPacket(FieldPacket.blowWeather(itemID, message, seconds, packedAvatarLook));
    }

    private boolean hasUserFirstEnterScript() {
        return getOnFirstUserEnter() != null && !getOnFirstUserEnter().equalsIgnoreCase("");
    }

    public void broadcastPacket(OutPacket outPacket, Char exceptChr) {
        getChars().stream().filter(chr -> !chr.equals(exceptChr)).forEach(
                chr -> chr.write(outPacket)
        );
    }

    public void removeChar(Char chr) {
        // remove char's dragon
        Dragon dragon = chr.getDragon();
        if (dragon != null) {
            removeLife(dragon);
        }
        // remove char's android
        Android android = chr.getAndroid();
        if (android != null) {
            removeLife(android);
        }

        if (getSummons().stream().anyMatch(frenzy -> frenzy.getTemplateId() == Job.MONOLITH)) {
            removeLife(getSummons().stream().filter(frenzy -> frenzy.getTemplateId() == Job.MONOLITH).findFirst().orElse(null));
            if (hasKishin() && getSummons().stream().noneMatch(frenzy -> frenzy.getTemplateId() == Kanna.KISHIN_SHOUKAN)) {
                setKishin(false);
            }
        }


        getChars().remove(chr);
        OutPacket leavePacket = UserPool.userLeaveField(chr);
        if (!chr.isHide()) {
            broadcastPacket(leavePacket, chr);
        } else {
            for (Char other : getChars()) {
                try {
                    if (!other.getId().equals(chr.getId()) && chr.getUser().getAccountType().ordinal() <= other.getUser().getAccountType().ordinal()) {
                        other.write(leavePacket);
                    }
                } catch (Exception ignore) {
                }
            }
        }

        // change controllers for which the chr was the controller of
        for (Map.Entry<Life, Char> entry : getLifeToControllers().entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(chr)) {
                setRandomController(entry.getKey());
            }
        }
        // remove summons of that char & remove field attacks of that char
        List<Integer> removedList = new ArrayList<>();
        for (int key : getLifes().keySet()) {
            Life life = getLifes().get(key);
            if (life instanceof FieldAttackObj) {
                FieldAttackObj fao = (FieldAttackObj) life;
                if (fao.getOwnerID() == chr.getId() && fao.getTemplateId() == BowMaster.ARROW_PLATTER) {
                    removedList.add(life.getObjectId());
                }
            }
        }
        for (Summon summon : chr.getSummons()) {
            removeLife(summon);
        }
        for (AffectedArea aura : chr.getFollowAffectedAreas().values()) { // Auras
            removeLife(aura);
        }
        if (JobConstants.isNightWalker(chr.getJob())) {
            NightWalker nwJob = (NightWalker) chr.getJobHandler();
            nwJob.shadowBatList = nwJob.getCurrentShadowBats();
            for (Summon summon : nwJob.shadowBatList) {
                removeLife(summon);
            }
        }
        for (int id : removedList) {
            chr.chatMessage(getLifes().containsKey(id) + "");
            removeLife(id, false);
            chr.chatMessage("field == " + getId() + ", removed " + id);
        }
    }



    public Map<Life, Char> getLifeToControllers() {
        return lifeToControllers;
    }

    public void setLifeToControllers(Map<Life, Char> lifeToControllers) {
        this.lifeToControllers = lifeToControllers;
    }

    public void putLifeController(Life life, Char chr) {
        getLifeToControllers().put(life, chr);
    }

    public Life getLifeByObjectID(int objectId) {
        return getLifes().getOrDefault(objectId, null);
    }

    public Life getLifeByTemplateId(int templateId) {
        return getLifes().values().stream().filter(l -> l.getTemplateId() == templateId).findFirst().orElse(null);
    }

    public void spawnLifesForChar(Char chr) {
        for (Char c : getChars()) {
            if (!c.equals(chr) && !c.isHide() || c.getUser().getAccountType().ordinal() <= chr.getUser().getAccountType().ordinal()) {
                if (c.getAvatarData() != null && c.getAvatarData().getCharacterStat() != null) {
                    chr.write(UserPool.userEnterField(c));
                }
            }
        }
        for (Life life : getLifes().values()) {
            spawnLife(life, chr);
        }
        if (getRuneStone() != null) {
            chr.write(FieldPacket.runeStoneAppear(getRuneStone()));
        }
        if (getOpenGates() != null && getOpenGates().size() > 0) {
            for (OpenGate openGate : getOpenGates()) {
                openGate.showOpenGate(this);
            }
        }
        if (getTownPortalList() != null && getTownPortalList().size() > 0) {
            for (TownPortal townPortal : getTownPortalList()) {
                townPortal.showTownPortal(this);
            }
        }
        if (getClock() != null) {
            getClock().showClock(chr);
        }
    }

    @Override
    public String toString() {
        return "" + getId();
    }

    public void respawn(Mob mob) {
        mob.setHp(mob.getMaxHp());
        mob.setMp(mob.getMaxMp());
        mob.setPosition(mob.getHomePosition().deepCopy());
        spawnLife(mob, null);
    }

    public void broadcastPacket(OutPacket outPacket) {
        for (Char c : getChars()) {
            c.write(outPacket);
        }
    }

    private void broadcastPacket(OutPacket outPacket, Predicate<? super Char> predicate) {
        getChars().stream().filter(predicate).forEach(chr -> chr.write(outPacket));
    }

    public void spawnAura(AffectedArea aura) {
        Char owner = aura.getOwner();
        owner.addFollowAffectedArea(aura);
        spawnAffectedAreaAndRemoveOld(aura);
    }

    public void removeAura(AffectedArea aura) {
        Char owner = aura.getOwner();
        owner.getFollowAffectedAreas().remove(aura.getSkillID());
        removeLife(aura);
    }

    public void spawnAffectedArea(AffectedArea aa) {
        addLife(aa);
        SkillInfo si = SkillData.getSkillInfoById(aa.getSkillID());
        int duration = aa.getDuration();
        if (si != null && duration == 0) {
            duration = si.getValue(time, aa.getSlv()) * 1000;
        }
        if (duration > 0) {
            ScheduledFuture sf = EventManager.addEvent(() -> removeLife(aa.getObjectId(), true), duration + aa.getDelay() * 100);
            addLifeSchedule(aa, sf);
        }
        broadcastPacket(FieldPacket.affectedAreaCreated(aa));
        getChars().forEach(chr -> aa.getField().checkCharInAffectedAreas(chr));
        getMobs().forEach(mob -> aa.getField().checkMobInAffectedAreas(mob));
    }

    public void spawnAffectedAreaAndRemoveOld(AffectedArea aa) {
        AffectedArea oldAA = (AffectedArea) getLifes().values().stream()
                .filter(l -> l instanceof AffectedArea &&
                        ((AffectedArea) l).getCharID() == aa.getCharID() &&
                        ((AffectedArea) l).getSkillID() == aa.getSkillID())
                .findFirst().orElse(null);
        if (oldAA != null) {
            removeLife(oldAA.getObjectId(), false);
        }
        spawnAffectedArea(aa);
    }


    public void RemoveOldAffectedArea(AffectedArea aa) {
        AffectedArea oldAA = (AffectedArea) getLifes().values().stream()
                .filter(l -> l instanceof AffectedArea &&
                        ((AffectedArea) l).getCharID() == aa.getCharID() &&
                        ((AffectedArea) l).getSkillID() == aa.getSkillID())
                .findFirst().orElse(null);
        if (oldAA != null) {
            removeLife(oldAA.getObjectId(), false);
        }
    }

    private <T> Set<T> getLifesByClass(Class clazz) {
        return (Set<T>) getLifes().values().stream()
                .filter(l -> l.getClass().equals(clazz))
                .collect(Collectors.toSet());
    }

    public Set<Mob> getMobs() {
        return getLifesByClass(Mob.class);
    }

    public Set<Summon> getSummons() {
        return getLifesByClass(Summon.class);
    }

    public Set<Drop> getDrops() {
        return getLifesByClass(Drop.class);
    }

    public Set<MobGen> getMobGens() {
        return getLifesByClass(MobGen.class);
    }

    public Set<AffectedArea> getAffectedAreas() {
        return getLifesByClass(AffectedArea.class);
    }

    public Set<Reactor> getReactors() {
        return getLifesByClass(Reactor.class);
    }

    public Set<Npc> getNpcs() {
        return getLifesByClass(Npc.class);
    }

    public Set<FieldAttackObj> getFieldAttackObjects() {
        return getLifesByClass(FieldAttackObj.class);
    }

    public Set<Wreckage> getWreckage() {
        return getLifesByClass(Wreckage.class);
    }

    public List<Wreckage> getWreckageByChrId(int chrId) {
        return getWreckage().stream().filter(w -> w.getOwnerId() == chrId).collect(Collectors.toList());
    }

    public void setObjectIDCounter(int idCounter) {
        objectIDCounter = idCounter;
    }

    public int getNewObjectID() {
        return objectIDCounter++;
    }

    public List<Life> getLifesInRect(Rect rect) {
        List<Life> lifes = new ArrayList<>();
        for (Life life : getLifes().values()) {
            Position position = life.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                lifes.add(life);
            }
        }
        return lifes;
    }

    public List<Char> getCharsInRect(Rect rect) {
        List<Char> chars = new ArrayList<>();
        for (Char chr : getChars()) {
            Position position = chr.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                chars.add(chr);
            }
        }
        return chars;
    }

    private void removeBlowWeatherUpdate() {
            long curTime = System.currentTimeMillis();
            if (weatherItemID / 10000 == 512 && weatherEndTime < curTime) {
                this.weatherItemID = 0;
                this.weatherMsg = null;
                broadcastPacket(FieldPacket.removeBlowWeather());
            }
        }

    public List<PartyMember> getPartyMembersInRect(Char chr, Rect rect) {
        Party party = chr.getParty();
        List<PartyMember> partyMembers = new ArrayList<>();
        for (PartyMember partyMember : party.getOnlineMembers()) {
            Position position = partyMember.getChr().getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                partyMembers.add(partyMember);
            }
        }
        return partyMembers;
    }

    public List<Mob> getMobsInRect(Rect rect) {
        List<Mob> mobs = new ArrayList<>();
        for (Mob mob : getMobs()) {
            Position position = mob.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                mobs.add(mob);
            }
        }
        return mobs;
    }

    public List<Mob> getBossMobsInRect(Rect rect) {
        List<Mob> mobs = new ArrayList<>();
        for (Mob mob : getMobs()) {
            if (mob.isBoss()) {
                Position position = mob.getPosition();
                int x = position.getX();
                int y = position.getY();
                if (x >= rect.getLeft() && y >= rect.getTop()
                        && x <= rect.getRight() && y <= rect.getBottom()) {
                    mobs.add(mob);
                }
            }
        }
        return mobs;
    }

    public List<Drop> getDropsInRect(Rect rect) {
        List<Drop> drops = new ArrayList<>();
        for (Drop drop : getDrops()) {
            Position position = drop.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                drops.add(drop);
            }
        }
        return drops;
    }

    public List<Summon> getSummonsInRect(Rect rect) {
        List<Summon> summons = new ArrayList<>();
        for (Summon summon : getSummons()) {
            Position position = summon.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                summons.add(summon);
            }
        }
        return summons;
    }

    public List<AffectedArea> getAffectAreasInRect(Rect rect) {
        List<AffectedArea> aas = new ArrayList<>();
        for (AffectedArea aa : getAffectedAreas()) {
            Position position = aa.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                aas.add(aa);
            }
        }
        return aas;
    }

    public List<Wreckage> getWreckageByChrIdInRect(int chrId, Rect rect) {
        List<Wreckage> wreckageList = new ArrayList<>();
        for (Wreckage wreckage : getWreckageByChrId(chrId)) {
            Position position = wreckage.getPosition();
            int x = position.getX();
            int y = position.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                wreckageList.add(wreckage);
            }
        }
        return wreckageList;
    }

    public Summon getSummonByChrAndSkillId(Char chr, int skillId) {
        return getSummons().stream().filter(s -> s.getSkillID() == skillId && s.getChr() == chr).findFirst().orElse(null);
    }

    public Summon getSummonByChrAndSkillIdInRect(Char chr, int skillId, Rect rect) {
        return getSummonsInRect(rect).stream().filter(s -> s.getSkillID() == skillId && s.getChr() == chr).findFirst().orElse(null);
    }

    public synchronized void removeLife(int id, boolean fromSchedule) {
        Life life = getLifeByObjectID(id);
        if (life == null) {
            return;
        }
        removeLife(id);
        removeSchedule(life, fromSchedule);
        life.broadcastLeavePacket();
    }



    public synchronized void removeDrop(int dropID, int pickupUserID, boolean fromSchedule, int petID) {
        Life life = getLifeByObjectID(dropID);
        if (life instanceof Drop) {
            if (petID >= 0) {
                broadcastPacket(DropPool.dropLeaveField(DropLeaveType.PetPickup, pickupUserID, life.getObjectId(),
                        (short) 0, petID, 0));
            } else if (pickupUserID != 0) {
                broadcastPacket(DropPool.dropLeaveField(dropID, pickupUserID));
            } else {
                broadcastPacket(DropPool.dropLeaveField(DropLeaveType.Fade, pickupUserID, life.getObjectId(),
                        (short) 0, 0, 0));
            }
                removeLife(dropID, fromSchedule);
            }
        }


    public Map<Life, ScheduledFuture> getLifeSchedules() {
        return lifeSchedules;
    }

    public void addLifeSchedule(Life life, ScheduledFuture scheduledFuture) {
        getLifeSchedules().put(life, scheduledFuture);
    }

    public void removeSchedule(Life life, boolean fromSchedule) {
        if (!getLifeSchedules().containsKey(life)) {
            return;
        }
        if (!fromSchedule) {
            getLifeSchedules().get(life).cancel(false);
        }
        getLifeSchedules().remove(life);
    }

    public void checkMobInAffectedAreas(Mob mob) {
        for (AffectedArea aa : getAffectedAreas()) {
            if (aa.getRect().hasPositionInside(mob.getPosition())) {
                aa.handleMobInside(mob);
            }
        }
    }

    public void checkCharInAffectedAreas(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        for (AffectedArea aa : getAffectedAreas()) {
            if (aa.isFollowOwner()) { // Follow Owner AA
                Char owner = aa.getOwner();
                Rect rect = aa.getSkillRect();
                aa.setRect(owner.getRectAround(owner.isLeft() ? rect : rect.horizontalFlipAround(0)));
            }
            boolean isInsideAA = aa.getRect().hasPositionInside(chr.getPosition());
            if (isInsideAA) {
                aa.handleCharInside(chr);
            } else if (tsm.hasAffectedArea(aa) && !isInsideAA) {
                tsm.removeAffectedArea(aa);
            }
        }
    }

    public void drop(Drop drop, Position posFrom, Position posTo) {
        drop(drop, posFrom, posTo, false);
    }

    /**
     * Drops an item to this map, given a {@link Drop}, a starting Position and an ending Position.
     * Immediately broadcasts the drop packet.
     *
     * @param drop              The Drop to drop.
     * @param posFrom           The Position that the drop starts off from.
     * @param posTo             The Position where the drop lands.
     * @param ignoreTradability if the drop should ignore tradability (i.e., untradable items won't disappear)
     */
    public void drop(Drop drop, Position posFrom, Position posTo, boolean ignoreTradability) {
        boolean isTradable = true;
        Item item = drop.getItem();
        if (item != null) {
            ItemInfo itemInfo = ItemData.getItemInfoByID(item.getItemId());
            // must be tradable, and if not an equip, not a quest item
            isTradable = ignoreTradability || (item.isTradable() && (ItemConstants.isEquip(item.getItemId()) || itemInfo != null && !itemInfo.isQuest()));
        }
        drop.setPosition(posTo);
        if (isTradable) {
            addLife(drop);
            getLifeSchedules().put(drop,
                    EventManager.addEvent(() -> removeDrop(drop.getObjectId(), 0, true, -1),
                            GameConstants.DROP_REMAIN_ON_GROUND_TIME, TimeUnit.SECONDS));
        } else {
            drop.setObjectId(getNewObjectID()); // just so the client sees the drop
        }
        // Check for collision items such as exp orbs from combo kills
       // if (!isTradable) {
          //  broadcastPacket(DropPool.dropEnterField(drop, posFrom, 0, DropEnterType.Default));
         if (drop.getItem() != null && ItemConstants.isCollisionLootItem(drop.getItem().getItemId())) {
            broadcastPacket(DropPool.dropEnterFieldCollisionPickUp(drop, posFrom, 0));
        } else {
            for (Char chr : getChars()) {
                if (!chr.getClient().getWorld().isReboot() || drop.canBePickedUpBy(chr)) {
                    broadcastPacket(DropPool.dropEnterField(drop, posFrom, posTo, 0, drop.canBePickedUpBy(chr)));
                }
            }
        }

    }

    /**
     * Drops a {@link Drop} according to a given {@link DropInfo DropInfo}'s specification.
     *
     * @param dropInfo The
     * @param posFrom  The Position that hte drop starts off from.
     * @param posTo    The Position where the drop lands.
     * @param ownerID  The owner's character ID. Will not be able to be picked up by Chars that are not the owner.
     */
    public void drop(DropInfo dropInfo, Position posFrom, Position posTo, int ownerID, boolean explosive) {
        int itemID = dropInfo.getItemID();
        Item item;
        Drop drop = new Drop(-1);
        drop.setPosition(posTo);
        drop.setOwnerID(ownerID);
        drop.setExplosiveDrop(explosive);
        Set<Integer> quests = new HashSet<>();
        if (itemID != 0) {
            item = ItemData.getItemDeepCopy(itemID, true);
            if (item != null) {
                item.setQuantity(dropInfo.getQuantity());
                drop.setItem(item);
                ItemInfo ii = ItemData.getItemInfoByID(itemID);
                if (ii != null && ii.isQuest()) {
                    quests = ii.getQuestIDs();
                }
            } else {
                log.error("Was not able to find the item to drop! id = " + itemID);
                return;
            }
        } else {
            drop.setMoney(dropInfo.getMoney());
        }
        addLife(drop);
        drop.setExpireTime(FileTime.fromDate(LocalDateTime.now().plusSeconds(GameConstants.DROP_REMOVE_OWNERSHIP_TIME)));
        getLifeSchedules().put(drop,
                EventManager.addEvent(() -> removeDrop(drop.getObjectId(), 0, true, -1),
                        GameConstants.DROP_REMAIN_ON_GROUND_TIME, TimeUnit.SECONDS));
        EventManager.addEvent(() -> drop.setOwnerID(0), GameConstants.DROP_REMOVE_OWNERSHIP_TIME, TimeUnit.SECONDS);
        for (Char chr : getChars()) {
            if (chr.hasAnyQuestsInProgress(quests)) {
                broadcastPacket(DropPool.dropEnterField(drop, posFrom, posTo, ownerID, drop.canBePickedUpBy(chr)));
            }
        }
    }

    /**
     * Drops a Set of {@link DropInfo}s from a base Position.
     *
     * @param dropInfos The Set of DropInfos.
     * @param position  The Position the initial Drop comes from.
     * @param ownerID   The owner's character ID.
     */
    public void drop(Set<DropInfo> dropInfos, Position position, int ownerID) {
        drop(dropInfos, findFootHoldBelow(position), position, ownerID, 0, 0, false);
    }

    public void drop(Drop drop, Position position) {
        drop(drop, position, false);
    }

    /**
     * Drops a {@link Drop} at a given Position. Calculates the Position that the Drop should land at.
     *
     * @param drop        The Drop that should be dropped.
     * @param position    The Position it is dropped from.
     * @param fromReactor if it quest item the item will disapear
     */
    public void drop(Drop drop, Position position, boolean fromReactor) {
        int x = position.getX();
        Position posTo = new Position(x, findFootHoldBelow(position).getYFromX(x));
        drop(drop, position, posTo, fromReactor);
    }

    /**
     * Drops a Set of {@link DropInfo}s, locked to a specific {@link Foothold}.
     * Not all drops are guaranteed to be dropped, as this method calculates whether or not a Drop should drop, according
     * to the DropInfo's prop chance.
     *
     * @param dropInfos The Set of DropInfos that should be dropped.
     * @param fh        The Foothold this Set of DropInfos is bound to.
     * @param position  The Position the Drops originate from.
     * @param ownerID   The ID of the owner of all drops.
     * @param mesoRate  The added meso rate of the character.
     * @param dropRate  The added drop rate of the character.
     */
    public void drop(Set<DropInfo> dropInfos, Foothold fh, Position position, int ownerID, int mesoRate, int dropRate, boolean explosive) {
        int x = position.getX();
        int minX = fh == null ? position.getX() : fh.getX1();
        int maxX = fh == null ? position.getX() : fh.getX2();
        int diff = 0;
        for (DropInfo dropInfo : dropInfos) {
            if (dropInfo.willDrop(dropRate)) {
                x = (x + diff) > maxX ? maxX - 10 : (x + diff) < minX ? minX + 10 : x + diff;
                Position posTo;
                if (fh == null) {
                    posTo = position.deepCopy();
                } else {
                    posTo = new Position(x, fh.getYFromX(x));
                }
                // Copy the drop info for money, as we chance the amount that's in there.
                // Not copying -> original dropinfo will keep increasing in mesos
                DropInfo copy = null;
                if (dropInfo.isMoney()) {
                    copy = dropInfo.deepCopy();
                    copy.setMoney((int) (dropInfo.getMoney() * (mesoRate / 100D)));
                }
                drop(copy != null ? copy : dropInfo, position, posTo, ownerID, explosive);
                diff = diff < 0 ? Math.abs(diff - GameConstants.DROP_DIFF) : -(diff + GameConstants.DROP_DIFF);
                dropInfo.generateNextDrop();
            }
        }
    }

    public List<Portal> getClosestPortal(Rect rect) {
        List<Portal> portals = new ArrayList<>();
        for (Portal portals2 : getPortals()) {
            int x = portals2.getX();
            int y = portals2.getY();
            if (x >= rect.getLeft() && y >= rect.getTop()
                    && x <= rect.getRight() && y <= rect.getBottom()) {
                portals.add(portals2);
            }
        }
        return portals;
    }

    public Char getCharByName(String name) {
        return getChars().stream().filter(chr -> chr.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void execUserEnterScript(Char chr) {
        chr.clearCurrentDirectionNode();
        if (getOnUserEnter() == null) {
            return;
        }
        String script = null;
        if (!getOnUserEnter().equalsIgnoreCase("")) {
            script = getOnUserEnter();
        } else if (CustomFieldScripts.getByVal(getId()) != null) {
            script = CustomFieldScripts.getByVal(getId()).toString();
        }
        if (script != null) {
            chr.getScriptManager().startScript(getId(), script, ScriptType.Field);
        }
    }

    public boolean isUserFirstEnter() {
        return userFirstEnter;
    }

    public void setUserFirstEnter(boolean userFirstEnter) {
        this.userFirstEnter = userFirstEnter;
    }

    public int getAliveMobCount() {
        // not using getMobs() to only have to iterate `lifes' once
        return getLifes().values().stream()
                .filter(life -> life instanceof Mob && ((Mob) life).isAlive())
                .collect(Collectors.toList())
                .size();
    }

    public int getAliveCharsCount() {
        // not using getMobs() to only have to iterate `lifes' once
        return (int) getChars().stream().filter(chr -> chr.getHP() > 0).count();
    }

    public int getAliveMobCount(int mobID) {
        // not using getMobs() to only have to iterate `lifes' once
        return getLifes().values().stream()
                .filter(life -> life instanceof Mob && life.getTemplateId() == mobID && ((Mob) life).isAlive())
                .collect(Collectors.toList())
                .size();
    }

    public String getFieldScript() {
        return fieldScript;
    }

    public void setFieldScript(String fieldScript) {
        this.fieldScript = fieldScript;
    }

    public Mob spawnMobWithAppearType(int id, int x, int y, int appearType, int option) {
        Mob mob = MobData.getMobDeepCopyById(id);
        Position pos = new Position(x, y);
        mob.setPosition(pos.deepCopy());
        mob.setPrevPos(pos.deepCopy());
        mob.setPosition(pos.deepCopy());
        mob.setNotRespawnable(true);
        mob.setAppearType((byte) appearType);
        mob.setOption(option);
        if (mob.getField() == null) {
            mob.setField(this);
        }
        spawnLife(mob, null);
        return mob;
    }

    public Mob spawnMob(int id, int x, int y, boolean respawnable, long hp) {
        Mob mob = MobData.getMobDeepCopyById(id);
        Position pos = new Position(x, y);
        mob.setPosition(pos.deepCopy());
        mob.setPrevPos(pos.deepCopy());
        mob.setPosition(pos.deepCopy());
        mob.setNotRespawnable(!respawnable);
        if (hp > 0) {
            mob.setHp(hp);
            mob.setMaxHp(hp);
        }
        if (mob.getField() == null) {
            mob.setField(this);
        }
        Foothold fh = findFootHoldBelow(pos);
        mob.setCurFoodhold(fh);
        mob.setHomeFoothold(fh);
        spawnLife(mob, null);
        if (MobConstants.isTimedDropMob(mob.getTemplateId())) {
        mob.startDropItemSchedule();
        }
        return mob;
    }


    public void useRuneStone(Client c, RuneStone runeStone) {
        Char chr = c.getChr();
        chr.write(FieldPacket.runeActSuccess());
        broadcastPacket(FieldPacket.runeStoneDisappear(chr.getId()));
        chr.write(FieldPacket.runeStoneSkillAck(runeStone.getRuneType()));

        setRuneStone(null);
        this.lastRuneUsedTime = System.currentTimeMillis();
    }

    public void runeStoneHordeEffect(int mobRateMultiplier, int duration) {
        double prevMobRate = getMobRate();
        setMobRate(getMobRate() * mobRateMultiplier); //Temporary increase in mob Spawn
        if (runeStoneHordesTimer != null && !runeStoneHordesTimer.isDone()) {
            runeStoneHordesTimer.cancel(true);
        }
        runeStoneHordesTimer = EventManager.addEvent(() -> setMobRate(prevMobRate), duration, TimeUnit.SECONDS);
    }

    public int getBonusExpByBurningFieldLevel() {
        return burningFieldLevel * GameConstants.BURNING_FIELD_BONUS_EXP_MULTIPLIER_PER_LEVEL; //Burning Field Level * The GameConstant
    }

    public void showBurningLevel() {
        String string = "#fn ExtraBold##fs26#          Burning Field has been destroyed.          ";
        if (getBurningFieldLevel() > 0) {
            string = "#fn ExtraBold##fs26#          Burning Stage " + getBurningFieldLevel() + ": " + getBonusExpByBurningFieldLevel() + "% Bonus EXP!          ";
        }
        Effect effect = Effect.createFieldTextEffect(string, 50, 2000, 4,
                new Position(0, -200), 1, 4, TextEffectType.BurningField, 0, 0);
        broadcastPacket(UserPacket.effect(effect));
    }

    public void increaseBurningLevel() {
        setBurningFieldLevel(getBurningFieldLevel() + 1);
    }

    public void decreaseBurningLevel() {
        setBurningFieldLevel(getBurningFieldLevel() - 1);
    }

    public void startBurningFieldTimer() {
        if (getMobGens().size() > 0
                && getMobs().stream().mapToInt(m -> m.getForcedMobStat().getLevel()).min().orElse(0) >= GameConstants.BURNING_FIELD_MIN_MOB_LEVEL) {
            setBurningFieldLevel(GameConstants.BURNING_FIELD_LEVEL_ON_START);
            EventManager.addFixedRateEvent(this::changeBurningLevel, 0, GameConstants.BURNING_FIELD_TIMER, TimeUnit.MINUTES); //Every X minutes runs 'changeBurningLevel()'
        }
    }

    public void changeBurningLevel() {
        boolean showMessage = true;

        if (getBurningFieldLevel() <= 0) {
            showMessage = false;
        }

        //If there are players on the map,  decrease the level  else  increase the level
        if (getChars().size() > 0 && getBurningFieldLevel() > 0) {
            decreaseBurningLevel();

        } else if (getChars().size() <= 0 && getBurningFieldLevel() < GameConstants.BURNING_FIELD_MAX_LEVEL) {
            increaseBurningLevel();
            showMessage = true;
        }

        if (showMessage) {
            showBurningLevel();
        }
    }

    public void setNextEliteSpawnTime(long nextEliteSpawnTime) {
        this.nextEliteSpawnTime = nextEliteSpawnTime;
    }

    public long getNextEliteSpawnTime() {
        return nextEliteSpawnTime;
    }

    public boolean canSpawnElite() {
        return isChannelField()
                && getAverageMobLevel() > GameConstants.MIN_LEVEL_FOR_RANDOM_FIELD_OCCURENCES
                && (getEliteState() == null || getEliteState() == EliteState.None);
    }

    public int getKilledElites() {
        return killedElites;
    }

    public void setKilledElites(int killedElites) {
        this.killedElites = killedElites;
    }

    public void incrementEliteKillCount() {
        setKilledElites(getKilledElites() + 1);
    }

    public void setEliteState(EliteState eliteState) {
        this.eliteState = eliteState;
    }

    public EliteState getEliteState() {
        return eliteState;
    }

    public List<Foothold> getNonWallFootholds() {
        return getFootholds().stream().filter(fh -> !fh.isWall()).collect(Collectors.toList());
    }

    public void setBossMobID(int bossMobID) {
        this.bossMobID = bossMobID;
    }

    public int getBossMobID() {
        return bossMobID;
    }

    public Portal getDefaultPortal() {
        Portal p = getPortalByName("sp");
        return p == null ? getPortalByID(0) : p;
    }

    private ScriptManager getScriptManager() {
        return scriptManager;
    }

    public void setScriptManager(ScriptManagerImpl scriptManager) {
        this.scriptManager = scriptManager;
    }

    /**
     * Goes through all MobGens, and spawns a Mob from it if allowed to do so. Only generates when there are Chars
     * on this Field, or if the field is being initialized.
     *
     * @param init if this is the first time that this method is called.
     */
    public void generateMobs(boolean init) {
        if (init || getChars().size() > 0) {
            boolean buffed = (this.getChannel() >= GameConstants.BUFFED_CH_ST && this.getChannel() <= GameConstants.BUFFED_CH_END);
            int currentMobs = getMobs().size();
            List<MobGen> shuffledMobs = new ArrayList<>(getMobGens());
            // shuffle so the mobs spawn on random positions, instead of a fixed order
            Collections.shuffle(shuffledMobs);
            for (MobGen mg : shuffledMobs) {
                if (mg.canSpawnOnField(this)) {                     
                    mg.spawnMob(this, buffed);
                    currentMobs++;    
                    if ((getFieldLimit() & FieldOption.NoMobCapacityLimit.getVal()) == 0
                            && currentMobs > getFixedMobCapacity()) {
                        break;
                    }
                }
            }
        }
        // No fixed rate to ensure kishin-ness keeps being checked
        double kishinMultiplier = hasKishin() ? GameConstants.KISHIN_MOB_RATE_MULTIPLIER : 1;
        EventManager.addEvent(() -> generateMobs(false),
                (long) (GameConstants.BASE_MOB_RESPAWN_RATE / (getMobRate() * kishinMultiplier)));
    }

    public int getMobCapacity() {
        return (int) (getFixedMobCapacity() * (hasKishin() ? GameConstants.KISHIN_MOB_MULTIPLIER : 0.5));
    }

    public boolean hasKishin() {
        return kishin;
    }

    public void setKishin(boolean kishin) {
        this.kishin = kishin;
    }

    public List<OpenGate> getOpenGates() {
        return openGateList;
    }

    public List<Integer> getNoRespawn() {
        return noRespawnList;
    }    
    
    public void setNoRespawn(List<Integer> mobid) {
         noRespawnList = mobid;
    } 
    
    public void clearRespawn() {
        noRespawnList = null;
    }    
    
    public void setOpenGates(List<OpenGate> openGateList) {
        this.openGateList = openGateList;
    }

    public void addOpenGate(OpenGate openGate) {
        getOpenGates().add(openGate);
    }

    public void removeOpenGate(OpenGate openGate) {
        getOpenGates().remove(openGate);
    }

    public boolean isChannelField() {
        return isChannelField;
    }

    public void setChannelField(boolean channelField) {
        this.isChannelField = channelField;
    }

    public List<TownPortal> getTownPortalList() {
        return townPortalList;
    }

    public void setTownPortalList(List<TownPortal> townPortalList) {
        this.townPortalList = townPortalList;
    }

    public void addTownPortal(TownPortal townPortal) {
        getTownPortalList().add(townPortal);
    }

    public void removeTownPortal(TownPortal townPortal) {
        getTownPortalList().remove(townPortal);
    }

    public TownPortal getTownPortalByChrId(int chrId) {
        return getTownPortalList().stream().filter(tp -> tp.getChr().getId() == chrId).findAny().orElse(null);
    }

    public void increaseReactorState(Char chr, int templateId, int stateLength) {
        Life life = getLifeByTemplateId(templateId);
        if (life instanceof Reactor) {
            Reactor reactor = (Reactor) life;
            reactor.increaseState();
            chr.write(ReactorPool.reactorChangeState(reactor, (short) 0, (byte) stateLength));
        }
    }

    public Map<Integer, List<String>> getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(Map<Integer, List<String>> directionInfo) {
        this.directionInfo = directionInfo;
    }

    public List<String> getDirectionNode(int node) {
        return directionInfo.getOrDefault(node, null);
    }

    public void addDirectionInfo(int node, List<String> scripts) {
        directionInfo.put(node, scripts);
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getAverageMobLevel() {
        return averageMobLevel;
    }

    public void setAverageMobLevel(int averageMobLevel) {
        this.averageMobLevel = averageMobLevel;
    }

    public boolean hasVrInfo() {
        return hasVrInfo;
    }

    public void setHasVrInfo(boolean hasVrInfo) {
        this.hasVrInfo = hasVrInfo;
    }

    public boolean isOutsideArea(Position position) {
        if (isFly()) {
            return false; // just let users fly back up
        }
        if (hasVrInfo()) {
            return position.getX() < getVrLeft() || position.getX() > getVrRight()
                    || position.getY() < getVrTop() || position.getY() > getVrBottom();
        }
        return position.getY() > getHighestFhYValue() + 1000;
    }

    public int getHighestFhYValue() {
        return highestFhYValue;
    }

    public void setHighestFhYValue(int highestFhYValue) {
        this.highestFhYValue = highestFhYValue;
    }

    public int calcHighestFhYValue() {
        int max = Integer.MIN_VALUE;
        for (Foothold fh : getFootholds()) {
            if (fh.getY1() > max) {
                max = fh.getY1();
            }
            if (fh.getY2() > max) {
                max = fh.getY2();
            }
        }
        return max;
    }

    public List<Foothold> getFootholdsByGroupId(int groupId) {
        return getFootholds().stream().filter(fh -> fh.getGroupId() == groupId).collect(Collectors.toList());
    }

    public Set<Foothold> getFootholdsInRect(Rect rect) {
        Set<Foothold> fields = new HashSet<>();
        for (Foothold fh : getFootholds()) {
            if (rect.hasPositionInside(new Position(fh.getX1(), fh.getY2()))
                    || rect.hasPositionInside(new Position(fh.getX2(), fh.getY2()))) {
                fields.add(fh);
            }
        }
        return fields;
    }

    public boolean isChangeToChannelOnLeave() {
        return changeToChannelOnLeave;
    }

    public void setChangeToChannelOnLeave(boolean changeToChannelOnLeave) {
        this.changeToChannelOnLeave = changeToChannelOnLeave;
    }
    
    public final void setRespawn(final boolean fm) {
        this.respawn = fm;
    }

    public final boolean getRespawn() {
        return respawn;
    }    

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean hasProperty(String key) {
        return getProperties().containsKey(key);
    }

    public Object getProperty(String key) {
        return getProperties().get(key);
    }

    public void setProperty(String key, Object value) {
        getProperties().put(key, value);
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public int getBarrier() {
        return barrier;
    }

    public void setBarrier(int barrier) {
        this.barrier = barrier;
    }

    public void setBarrierArc(int barrierArc) {
        this.barrierArc = barrierArc;
    }

    public int getBarrierArc() {
        return barrierArc;
    }

    // START OF ZAKUM FIELD RELATED METHODS ----------------------------------------------------------------------------
    public void toggleZakumPlatforms(boolean visible) {
        String[] platformNames = new String[]{
                "zdc1", "zdc2", "zdc3", "zdc4", "zdc5",
                "zdc6", "zdc7", "zdc8", "zdc9", "zdc10",
                "zdc11", "zdc12", "zdc13", "zdc14", "zdc15",
                "zdc16", "zdc17", "zdc18"
        };
        Position[] platformPositions = new Position[]{
                new Position(-464, -186), new Position(-388, -187), new Position(-310, -184), new Position(-514, -102), new Position(-439, -101), // 1-5
                new Position(-362, -99), new Position(-512, -7), new Position(-436, -5), new Position(-358, -8), new Position(350, -189), // 6-10
                new Position(426, -190), new Position(504, -187), new Position(384, -99), new Position(464, -102), new Position(546, -101), // 11-15
                new Position(363, -7), new Position(439, -5), new Position(517, -8)                                  // 16-18
        };
        broadcastPacket(FieldPacket.footholdAppear(platformNames, visible, platformPositions));
        zakPlatformsVisible = visible;
    }

    public boolean isZakPlatformsVisible() {
        return zakPlatformsVisible;
    }

    public void clearDrops() {
        Set<Drop> fieldDrops = new HashSet<Drop>(getDrops());
        for (Drop drop : fieldDrops) {
            removeDrop(drop.getObjectId(), 0, false, 0);
        }
    }

    public void setNextZakArmSmash(long nextTime) {
        nextZakArmSmash = nextTime;
    }

    public long getNextZakArmSmash() {
        return nextZakArmSmash;
    }

    public void setZakArmSmashMobs(int count) {
        zakArmSmashMobs = count;
    }

    public int getZakArmSmashMobs() {
        return zakArmSmashMobs;
    }

    // END OF ZAKUM FIELD RELATED METHODS ------------------------------------------------------------------------------

    public void clearObtacle() {
        broadcastPacket(FieldPacket.clearObtacle());
    }

    public void createObstacleAtom(Field field, ObtacleAtomEnum oae, int key, int damage, int velocity, int amount, int proc) {
        createObstacleAtom(field, oae, key, damage, velocity, 0, amount, proc);
    }

    public void createObstacleAtom(Field field, ObtacleAtomEnum oae, int key, int damage, int velocity, int angle, int amount, int proc) {
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

        broadcastPacket(FieldPacket.createObtacle(ObtacleAtomCreateType.NORMAL, obtacleInRowInfo, obtacleRadianInfo, obtacleAtomInfosSet));
    }

    public void createObstacleAtomLowestEndPoint(Field field, ObtacleAtomEnum oae, int key, int damage, int velocity, int angle, int amount, int proc) {
        int xLeft = field.getVrLeft();
        int yTop = field.getVrTop();

        ObtacleInRowInfo obtacleInRowInfo = new ObtacleInRowInfo(4, false, 5000, 0, 0, 0);
        ObtacleRadianInfo obtacleRadianInfo = new ObtacleRadianInfo(4, 0, 0, 0, 0);
        Set<ObtacleAtomInfo> obtacleAtomInfosSet = new HashSet<>();

        for (int i = 0; i < amount; i++) {
            if (Util.succeedProp(proc)) {
                int randomX = new Random().nextInt(field.getWidth()) + xLeft;
                Position position = new Position(randomX, yTop);
                Foothold foothold = field.findLowestFootHoldBelow(position);
                if (foothold != null) {
                    int footholdY = foothold.getYFromX(position.getX());
                    int height = position.getY() - footholdY;
                    height = height < 0 ? -height : height;

                    obtacleAtomInfosSet.add(new ObtacleAtomInfo(oae.getType(), key, position, new Position(), oae.getHitBox(),
                            damage, 0, 0, height, 0, velocity, height, angle));
                }
            }
        }

        broadcastPacket(FieldPacket.createObtacle(ObtacleAtomCreateType.NORMAL, obtacleInRowInfo, obtacleRadianInfo, obtacleAtomInfosSet));
    }

    public void createFallingCatcherAtCoords(String name, int index, int x, int y) {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(new Position(x, y));
        broadcastPacket(FieldPacket.createFallingCatcher(new FallingCatcher(name, index, positions)));
    }

    public void createFallingCatcherOnCharacter(Char chr, String name, int index) {
        ArrayList<Position> positions = new ArrayList<Position>();
        positions.add(chr.getPosition());
        chr.getField().broadcastPacket(FieldPacket.createFallingCatcher(new FallingCatcher(name, index, positions)));
    }

    public void dropItemsAlongLine(int[] items, int range, int startPosX, int startPosY, long msDelay) {
        if (items.length <= 0) {
            return; // avoid divide by zero error
        }
        Tuple<Foothold, Foothold> lrFh = getMinMaxNonWallFH();

        range = Math.max(range, items.length);
        int offset = Math.max((range / items.length) * 2, 3); // we want offset >= 3 || multiply by 2 so that the drops go past the start point
        for (int i = 0; i < items.length; i++) {
            int endPosX = startPosX - range + (offset * i);
            endPosX = Math.max(endPosX, lrFh.getLeft().getX1()); // left is lowest x val
            endPosX = Math.min(endPosX, lrFh.getRight().getX1()); // right is highest x val

            if (items[i] <= 0) { // some fucker
                continue;
            }

            if (items[i] > 999999) { // item
                dropItem(items[i], startPosX, startPosY, endPosX, startPosY);
            } else { // meso
                dropMeso(items[i], startPosX, startPosY, endPosX, startPosY);
            }

            //Thread.sleep(Math.max(msDelay, 0)); // todo figure out this gay delay packet
        }
    }

    public void dropItem(int itemId, int startPosX, int startPosY, int endPosX, int endPosY) {
        Drop drop = new Drop(getNewObjectID());
        drop.setItem(ItemData.getItemDeepCopy(itemId));
        Position startPos = new Position(startPosX, startPosY);
        Position endPos = new Position(endPosX, endPosY);
        drop(drop, startPos, endPos, true);
    }

    public void dropMeso(int mesoAmount, int startPosX, int startPosY, int endPosX, int endPosY) {
        Drop drop = new Drop(getNewObjectID(), mesoAmount);
        Position startPos = new Position(startPosX, startPosY);
        Position endPos = new Position(endPosX, endPosY);
        drop(drop, startPos, endPos, true);
    }

}
