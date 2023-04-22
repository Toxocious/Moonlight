package net.swordie.ms.life.mob;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.ExpIncreaseInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyDamageInfo;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.EliteState;
import net.swordie.ms.enums.WeatherEffNoticeType;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.drop.DropInfo;
import net.swordie.ms.life.mob.skill.MobSkill;
import net.swordie.ms.life.mob.skill.MobSkillID;
import net.swordie.ms.life.mob.skill.MobSkillStat;
import net.swordie.ms.life.mob.skill.ShootingMoveStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.util.FileTime;
import org.python.google.common.collect.ArrayListMultimap;
import org.python.google.common.collect.ListMultimap;

public class Mob extends Life {

    private static final Logger log = Logger.getLogger(Mob.class);
    private boolean sealedInsteadDead, patrolMob;
    private int option, effectItemID, range, detectX, senseX, phase, curZoneDataType;
    private int refImgMobID, lifeReleaseOwnerAID, afterAttack, currentAction, scale, eliteGrade, eliteType, targetUserIdFromServer;
    private long hp;
    private int lastDropTime = 0;
    private long mp;
    private byte calcDamageIndex = 1, moveAction, appearType, teamForMCarnival;
    private Position prevPos;
    private Foothold curFoodhold;
    private Foothold homeFoothold;
    private String lifeReleaseOwnerName = "", lifeReleaseMobName = "";
    private ShootingMoveStat shootingMoveStat;
    private ForcedMobStat forcedMobStat;
    private MobTemporaryStat temporaryStat;
    private int firstAttack;
    private int summonType;
    private int category;
    private String mobType = "";
    private int link;
    private double fs;
    private String elemAttr = "";
    private int hpTagColor;
    private int hpTagBgcolor;
    private boolean HPgaugeHide;
    private int rareItemDropLevel;
    private boolean boss;
    private boolean shouldDropItem = false;
    private int hpRecovery;
    private int mpRecovery;
    private boolean undead;
    private int mbookID;
    private boolean noRegen;
    private int chaseSpeed;
    private int explosiveReward;
    private int flySpeed;
    private int dropItemPeriod;
    private boolean invincible;
    private boolean hideName;
    private boolean hideHP;
    private String changeableMobType = "";
    private boolean changeable;
    private boolean noFlip;
    private boolean tower;
    private boolean partyBonusMob;
    private int wp;
    private boolean useReaction;
    private boolean publicReward;
    private boolean minion;
    private boolean forward;
    private boolean isRemoteRange;
    private boolean ignoreFieldOut;
    private boolean ignoreMoveImpact;
    private int summonEffect;
    private boolean skeleton;
    private boolean hideUserDamage;
    private int fixedDamage;
    private boolean individualReward;
    private int removeAfter;
    private boolean notConsideredFieldSet;
    private String fixedMoveDir = "";
    private boolean noDoom;
    private boolean useCreateScript;
    private boolean knockback;
    private boolean blockUserMove;
    private int bodyDisease;
    private int bodyDiseaseLevel;
    private int point;
    private int partyBonusR;
    private boolean removeQuest;
    private int passiveDisease;
    private int coolDamageProb;
    private int coolDamage;
    private int damageRecordQuest;
    private int sealedCooltime;
    private int willEXP;
    private boolean onFieldSetSummon;
    private boolean userControll;
    private boolean noDebuff;
    private boolean targetFromSvr;
    private int charismaEXP;
    private boolean isSplit;
    public boolean isBuffed;
    private Map<Char, Long> damageDone = new HashMap<>();
    private Set<DropInfo> drops = new HashSet<>();
    private List<MobSkill> skills = new ArrayList<>();
    private List<MobSkill> attacks = new ArrayList<>();
    private Map<Integer, Integer> skillUseCount = new HashMap<>();
    private Map<Integer, Integer> attackUseCount = new HashMap<>();
    private Set<Integer> quests = new HashSet<>();
    private Set<Integer> revives = new HashSet<>();
    private Map<Integer, Long> skillCooldowns = new HashMap<>();
    private long nextPossibleSkillTime = 0;
    private List<Tuple<Integer, Integer>> eliteSkills = new ArrayList<>();
    private boolean selfDestruction;
    private List<MobSkill> skillDelays = new CopyOnWriteArrayList<>();
    private Set<ScheduledFuture> dropItemTimed = new HashSet<>();
    private boolean inAttack;
    private boolean isBanMap;
    private int banType = 1;// default
    private int banMsgType = 1;// default
    private String banMsg = "";
    private List<Tuple<Integer, String>> banMap = new ArrayList<>();// field, portal name
    private boolean isEscortMob = false;
    private List<EscortDest> escortDest = new ArrayList<>();
    private int currentDestIndex = 0;
    private int escortStopDuration = 0;
    private Set<Integer> mobSet = new HashSet<>(); // for quests: a list of mobs
    private Set<Integer> parentMobSet = new HashSet<>(); // for quests: which mobs this mob corresponds to
    private int mobSpawnerId;
    private int attackCooldown;
    private long nextSummonPossible;
    private Map<String, Object> properties = new HashMap<String, Object>();

    private List<Rect> mobSkillDelayRects = new ArrayList<>();
    private Map<Integer, Rect> mobZoneRects = new HashMap<>();

    private List<Tuple<Integer, Integer>> passiveSkills = new ArrayList<>();

    private List<Integer> possibleTransformations = new ArrayList<>();
    private long transformCooldown = 0;
    private int transformCooldownDuration = 0;
    private int transformMinHP = 0;
    private int transformMaxHP = 0;
    private boolean transformLinkHP = true;
    // private List<Integer> possibleTransformationSkills = new ArrayList<>();
    // only Pierre seems to use trans for now so let's just manually handle hats
    private int transformDuration = 0;
    private int transformWith = 0;
    private long transformStartTime = 0;
    private Mob preTransformMob = null;

    private ListMultimap<Double, Runnable> runAtHP = ArrayListMultimap.create();

    public ListMultimap<Double, Runnable> getRunAtHPMap() {
        return this.runAtHP;
    }

    public synchronized void addRunnableAtHp(Double perc, Runnable code) {
        runAtHP.put(perc, code);
    }

    public synchronized void executeRunnableAtHp(Double percDamage) {
        // We make a copy in case the runnable also adds another runnable (eg: heal at 0% if certain condition and repeat)
        ListMultimap<Double, Runnable> runAtHPDeepCopy = ArrayListMultimap.create();
        runAtHPDeepCopy.putAll(runAtHP);

        // clear any runnables that are going to execute
        Set<Double> percTriggers = new HashSet<>(runAtHP.keySet());
        for (Double perc : percTriggers) {
            if (percDamage <= perc) {
                while (runAtHP.get(perc).size() > 0) {
                    runAtHP.get(perc).remove(0);
                }
            }
        }

        // execute runnables
        percTriggers = new HashSet<>(runAtHPDeepCopy.keySet());
        for (Double perc : percTriggers) {
            if (percDamage <= perc) {
                while (runAtHPDeepCopy.get(perc).size() > 0) {
                    runAtHPDeepCopy.get(perc).get(0).run();
                    runAtHPDeepCopy.get(perc).remove(0);
                }
            }
        }
    }

    public Mob(int templateId) {
        super(templateId);
        forcedMobStat = new ForcedMobStat();
        temporaryStat = new MobTemporaryStat(this);
        scale = 100;
        calcDamageIndex = 1;
    }

    public Mob deepCopy() {
        Mob copy = new Mob(getTemplateId());
        // start life
        copy.setObjectId(getObjectId());
        copy.setLifeType(getLifeType());
        copy.setTemplateId(getTemplateId());
        copy.setX(getX());
        copy.setY(getY());
        copy.setMobTime(getMobTime());
        copy.setFlip(isFlip());
        copy.setHide(isHide());
        copy.setFh(getFh());
        copy.setCy(getCy());
        copy.setRx0(getRx0());
        copy.setRx1(getRx1());
        copy.setLimitedName(getLimitedName());
        copy.setUseDay(isUseDay());
        copy.setUseNight(isUseNight());
        copy.setHold(isHold());
        copy.setNoFoothold(isNoFoothold());
        copy.setDummy(isDummy());
        copy.setSpine(isSpine());
        copy.setMobTimeOnDie(isMobTimeOnDie());
        copy.setRegenStart(getRegenStart());
        copy.setMobAliveReq(getMobAliveReq());
        // end life
        copy.setSealedInsteadDead(isSealedInsteadDead());
        copy.setOption(getOption());
        copy.setEffectItemID(getEffectItemID());
        copy.setPatrolMob(isPatrolMob());
        copy.setRange(getRange());
        copy.setDetectX(getDetectX());
        copy.setSenseX(getSenseX());
        copy.setPhase(getPhase());
        copy.setCurZoneDataType(getCurZoneDataType());
        copy.setRefImgMobID(getRefImgMobID());
        copy.setLifeReleaseOwnerAID(getLifeReleaseOwnerAID());
        copy.setAfterAttack(getAfterAttack());
        copy.setCurrentAction(getCurrentAction());
        copy.setScale(getScale());
        copy.setEliteGrade(getEliteGrade());
        copy.setEliteType(getEliteType());
        copy.setTargetUserIdFromServer(getTargetUserIdFromServer());
        copy.setHp(getHp());
        copy.setMaxHp(getMaxHp());
        copy.setCalcDamageIndex(getCalcDamageIndex());
        copy.setMoveAction(getMoveAction());
        copy.setAppearType(getAppearType());
        copy.setTeamForMCarnival(getTeamForMCarnival());
        copy.setSelfDestruction(isSelfDestruction());
        if (getPrevPos() != null) {
            copy.setPrevPos(getPrevPos().deepCopy());
        }
        if (getCurFoodhold() != null) {
            copy.setCurFoodhold(getCurFoodhold().deepCopy());
        }
        if (getHomeFoothold() != null) {
            copy.setHomeFoothold(getHomeFoothold().deepCopy());
        }
        copy.setLifeReleaseOwnerName(getLifeReleaseOwnerName());
        copy.setLifeReleaseMobName(getLifeReleaseMobName());
        copy.setShootingMoveStat(null);
        if (getForcedMobStat() != null) {
            copy.setForcedMobStat(getForcedMobStat().deepCopy());
        }
        if (getTemporaryStat() != null) {
            copy.setTemporaryStat(getTemporaryStat().deepCopy());
        }
        copy.setFirstAttack(getFirstAttack());
        copy.setSummonType(getSummonType());
        copy.setCategory(getCategory());
        copy.setMobType(getMobType());
        copy.setLink(getLink());
        copy.setFs(getFs());
        copy.setElemAttr(getElemAttr());
        copy.setHpTagColor(getHpTagColor());
        copy.setHpTagBgcolor(getHpTagBgcolor());
        copy.setHPgaugeHide(isHPgaugeHide());
        copy.setRareItemDropLevel(getRareItemDropLevel());
        copy.setBoss(isBoss());
        copy.setHpRecovery(getHpRecovery());
        copy.setMpRecovery(getMpRecovery());
        copy.setUndead(isUndead());
        copy.setMbookID(getMbookID());
        copy.setNoRegen(isNoRegen());
        copy.setChaseSpeed(getChaseSpeed());
        copy.setExplosiveReward(getExplosiveReward());
        copy.setFlySpeed(getFlySpeed());
        copy.setInvincible(isInvincible());
        copy.setHideName(isHideName());
        copy.setHideHP(isHideHP());
        copy.setChangeableMobType(getChangeableMobType());
        copy.setChangeable(isChangeable());
        copy.setNoFlip(isNoFlip());
        copy.setTower(isTower());
        copy.setPartyBonusMob(isPartyBonusMob());
        copy.setWp(getWp());
        copy.setUseReaction(isUseReaction());
        copy.setPublicReward(isPublicReward());
        copy.setMinion(isMinion());
        copy.setForward(isForward());
        copy.setIsRemoteRange(isRemoteRange());
        copy.setIgnoreFieldOut(isIgnoreFieldOut());
        copy.setIgnoreMoveImpact(isIgnoreMoveImpact());
        copy.setSummonEffect(getSummonEffect());
        copy.setSkeleton(isSkeleton());
        copy.setHideUserDamage(isHideUserDamage());
        copy.setFixedDamage(getFixedDamage());
        copy.setIndividualReward(isIndividualReward());
        copy.setRemoveAfter(getRemoveAfter());
        copy.setNotConsideredFieldSet(isNotConsideredFieldSet());
        copy.setFixedMoveDir(getFixedMoveDir());
        copy.setNoDoom(isNoDoom());
        copy.setUseCreateScript(isUseCreateScript());
        copy.setKnockback(isKnockback());
        copy.setBlockUserMove(isBlockUserMove());
        copy.setBodyDisease(getBodyDisease());
        copy.setBodyDiseaseLevel(getBodyDiseaseLevel());
        copy.setPoint(getPoint());
        copy.setPartyBonusR(getPartyBonusR());
        copy.setRemoveQuest(isRemoveQuest());
        copy.setPassiveDisease(getPassiveDisease());
        copy.setCoolDamageProb(getCoolDamageProb());
        copy.setCoolDamage(getCoolDamage());
        copy.setDamageRecordQuest(getDamageRecordQuest());
        copy.setSealedCooltime(getSealedCooltime());
        copy.setWillEXP(getWillEXP());
        copy.setOnFieldSetSummon(isOnFieldSetSummon());
        copy.setUserControll(isUserControll());
        copy.setNoDebuff(isNoDebuff());
        copy.setTargetFromSvr(isTargetFromSvr());
        copy.setCharismaEXP(getCharismaEXP());
        copy.setMp(getMp());
        copy.setMaxMp(getMaxMp());
        copy.setDrops(getDrops()); // doesn't get mutated, so should be fine
        copy.setBanMap(isBanMap());
        copy.setBanType(getBanType());
        copy.setBanMsgType(getBanMsgType());
        copy.setBanMsg(getBanMsg());
        copy.setBanMapFields(getBanMapFields());
        copy.isBuffed();
        for (MobSkill ms : getSkills()) {
            copy.addSkill(ms);
        }
        for (MobSkill ms : getAttacks()) {
            copy.addAttack(ms);
        }
        for (int rev : getRevives()) {
            copy.addRevive(rev);
        }
        for (int i : getQuests()) {
            copy.addQuest(i);
        }
        for (int i : getMobSet()) {
            copy.addMob(i);
        }
        for (int i : getParentMobSet()) {
            copy.addParentMob(i);
        }
        copy.setEscortMob(isEscortMob());
        copy.setNextSummonPossible(GameConstants.MOB_RESUMMON_COOLDOWN);

        return copy;
    }

    public Set<DropInfo> getDrops() {
        return drops;
    }

    public void setDrops(Set<DropInfo> drops) {
        this.drops = drops;
    }

    public boolean isSealedInsteadDead() {
        return sealedInsteadDead;
    }
    public boolean isBuffed() {
        return isBuffed;
    }
    
    public void setBuffed(boolean buff) {
        this.isBuffed = buff;
    }
    
    public void setSealedInsteadDead(boolean sealedInsteadDead) {
        this.sealedInsteadDead = sealedInsteadDead;
    }

    public ForcedMobStat getForcedMobStat() {
        return forcedMobStat;
    }

    public void setForcedMobStat(ForcedMobStat forcedMobStat) {
        this.forcedMobStat = forcedMobStat;
    }

    public boolean isPatrolMob() {
        return patrolMob;
    }

    public void setPatrolMob(boolean patrolMob) {
        this.patrolMob = patrolMob;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getEffectItemID() {
        return effectItemID;
    }

    public void setEffectItemID(int effectItemID) {
        this.effectItemID = effectItemID;
    }

    public int getDetectX() {
        return detectX;
    }

    public void setDetectX(int detectX) {
        this.detectX = detectX;
    }

    public int getSenseX() {
        return senseX;
    }

    public void setSenseX(int senseX) {
        this.senseX = senseX;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getCurZoneDataType() {
        return curZoneDataType;
    }

    public void setCurZoneDataType(int curZoneDataType) {
        this.curZoneDataType = curZoneDataType;
    }

    public int getRefImgMobID() {
        return refImgMobID;
    }

    public void setRefImgMobID(int refImgMobID) {
        this.refImgMobID = refImgMobID;
    }

    public int getLifeReleaseOwnerAID() {
        return lifeReleaseOwnerAID;
    }

    public void setLifeReleaseOwnerAID(int lifeReleaseOwnerAID) {
        this.lifeReleaseOwnerAID = lifeReleaseOwnerAID;
    }

    public int getAfterAttack() {
        return afterAttack;
    }

    public void setAfterAttack(int afterAttack) {
        this.afterAttack = afterAttack;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getEliteGrade() {
        return eliteGrade;
    }

    public void setEliteGrade(int eliteGrade) {
        this.eliteGrade = eliteGrade;
    }

    public int getEliteType() {
        return eliteType;
    }

    public void setEliteType(int eliteType) {
        this.eliteType = eliteType;
    }

    public int getTargetUserIdFromServer() {
        return targetUserIdFromServer;
    }

    public void setTargetUserIdFromServer(int targetUserIdFromServer) {
        this.targetUserIdFromServer = targetUserIdFromServer;
    }

    public long getHp() {
        return hp;
    }

    /**
     * Returns the current HP of the mob, in percentage in relation to the maximum HP.
     * @return current HP percantage
     */
    public double getHpPerc() {
        return 100 * ((double) getHp() / getMaxHp());
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public byte getCalcDamageIndex() {
        return calcDamageIndex;
    }

    public void setCalcDamageIndex(byte calcDamageIndex) {
        this.calcDamageIndex = calcDamageIndex;
    }

    public byte getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    public byte getAppearType() {
        return appearType;
    }

    public void setAppearType(byte appearType) {
        this.appearType = appearType;
    }

    public byte getTeamForMCarnival() {
        return teamForMCarnival;
    }

    public void setTeamForMCarnival(byte teamForMCarnival) {
        this.teamForMCarnival = teamForMCarnival;
    }

    public Position getPrevPos() {
        return prevPos;
    }

    public void setPrevPos(Position prevPos) {
        this.prevPos = prevPos;
    }

    public Foothold getCurFoodhold() {
        return curFoodhold;
    }

    public void setCurFoodhold(Foothold curFoodhold) {
        this.curFoodhold = curFoodhold;
    }

    public String getLifeReleaseOwnerName() {
        return lifeReleaseOwnerName;
    }

    public void setLifeReleaseOwnerName(String lifeReleaseOwnerName) {
        this.lifeReleaseOwnerName = lifeReleaseOwnerName;
    }

    public String getLifeReleaseMobName() {
        return lifeReleaseMobName;
    }

    public void setLifeReleaseMobName(String lifeReleaseMobName) {
        this.lifeReleaseMobName = lifeReleaseMobName;
    }

    public ShootingMoveStat getShootingMoveStat() {
        return shootingMoveStat;
    }

    public void setShootingMoveStat(ShootingMoveStat shootingMoveStat) {
        this.shootingMoveStat = shootingMoveStat;
    }

    public Foothold getHomeFoothold() {
        return homeFoothold;
    }

    public void setHomeFoothold(Foothold homeFoothold) {
        this.homeFoothold = homeFoothold;
    }

    public long getMaxHp() {
        return getForcedMobStat().getMaxHP();
    }

    public long getExp() {
        return getForcedMobStat().getExp();
    }

    public void setMaxHp(long maxHp) {
        getForcedMobStat().setMaxHP(maxHp);
    }

    public long getMp() {
        return mp;
    }

    public void setMp(long mp) {
        this.mp = mp;
    }

    public long getMaxMp() {
        return getForcedMobStat().getMaxMP();
    }

    public void setMaxMp(long maxMp) {
        getForcedMobStat().setMaxMP(maxMp);
    }

    public int getLevel() {
        return getForcedMobStat().getLevel();
    }

    public void setLevel(int level) {
        getForcedMobStat().setLevel(level);
    }

    public int getPad() {
        return getForcedMobStat().getPad();
    }

    public void setPad(int pad) {
        getForcedMobStat().setPad(pad);
    }

    public int getMad() {
        return getForcedMobStat().getMad();
    }

    public void setMad(int mad) {
        getForcedMobStat().setMad(mad);
    }

    public int getPdr() {
        return getForcedMobStat().getPdr();
    }

    public int getMdr() {
        return getForcedMobStat().getMdr();
    }

    public void setTemporaryStat(MobTemporaryStat temporaryStat) {
        this.temporaryStat = temporaryStat;
    }

    public MobTemporaryStat getTemporaryStat() {
        return temporaryStat;
    }

    public void setFirstAttack(int firstAttack) {
        this.firstAttack = firstAttack;
    }

    public int getFirstAttack() {
        return firstAttack;
    }

    public void setSummonType(int summonType) {
        this.summonType = summonType;
    }

    public int getSummonType() {
        return summonType;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setMobType(String mobType) {
        this.mobType = mobType;
    }

    public String getMobType() {
        return mobType;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setFs(double fs) {
        this.fs = fs;
    }

    public double getFs() {
        return fs;
    }

    public void setElemAttr(String elemAttr) {
        this.elemAttr = elemAttr;
    }

    public String getElemAttr() {
        return elemAttr;
    }

    public void setHpTagColor(int hpTagColor) {
        this.hpTagColor = hpTagColor;
    }

    public int getHpTagColor() {
        return hpTagColor;
    }

    public void setHpTagBgcolor(int hpTagBgcolor) {
        this.hpTagBgcolor = hpTagBgcolor;
    }

    public int getHpTagBgcolor() {
        return hpTagBgcolor;
    }

    public void setHPgaugeHide(boolean HPgaugeHide) {
        this.HPgaugeHide = HPgaugeHide;
    }

    public boolean isHPgaugeHide() {
        return HPgaugeHide;
    }

    public void setRareItemDropLevel(int rareItemDropLevel) {
        this.rareItemDropLevel = rareItemDropLevel;
    }

    public int getRareItemDropLevel() {
        return rareItemDropLevel;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setHpRecovery(int hpRecovery) {
        this.hpRecovery = hpRecovery;
    }

    public int getHpRecovery() {
        return hpRecovery;
    }

    public void setMpRecovery(int mpRecovery) {
        this.mpRecovery = mpRecovery;
    }

    public int getMpRecovery() {
        return mpRecovery;
    }

    public void setUndead(boolean undead) {
        this.undead = undead;
    }

    public boolean isUndead() {
        return undead;
    }

    public void setMbookID(int mbookID) {
        this.mbookID = mbookID;
    }

    public int getMbookID() {
        return mbookID;
    }

    public void setNoRegen(boolean noRegen) {
        this.noRegen = noRegen;
    }

    public boolean isNoRegen() {
        return noRegen;
    }

    public void setChaseSpeed(int chaseSpeed) {
        this.chaseSpeed = chaseSpeed;
    }

    public int getChaseSpeed() {
        return chaseSpeed;
    }

    public void setExplosiveReward(int explosiveReward) {
        this.explosiveReward = explosiveReward;
    }

    public int getExplosiveReward() {
        return explosiveReward;
    }

    public void setFlySpeed(int flySpeed) {
        this.flySpeed = flySpeed;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isInvincible() {
        return invincible;
    }
    
    public int getDropItemPeriod() {
        return dropItemPeriod;
    }

    public void setDropItemPeriod(int d) {
        this.dropItemPeriod = d;
    }    

    public void setHideName(boolean hideName) {
        this.hideName = hideName;
    }

    public boolean isHideName() {
        return hideName;
    }

    public void setHideHP(boolean hideHP) {
        this.hideHP = hideHP;
    }

    public boolean isHideHP() {
        return hideHP;
    }

    public void setChangeableMobType(String changeableMobType) {
        this.changeableMobType = changeableMobType;
    }

    public String getChangeableMobType() {
        return changeableMobType;
    }

    public void setChangeable(boolean changeable) {
        this.changeable = changeable;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setNoFlip(boolean noFlip) {
        this.noFlip = noFlip;
    }

    public boolean isNoFlip() {
        return noFlip;
    }

    public void setTower(boolean tower) {
        this.tower = tower;
    }

    public boolean isTower() {
        return tower;
    }

    public void setPartyBonusMob(boolean partyBonusMob) {
        this.partyBonusMob = partyBonusMob;
    }

    public boolean isPartyBonusMob() {
        return partyBonusMob;
    }

    public void setWp(int wp) {
        this.wp = wp;
    }

    public int getWp() {
        return wp;
    }

    public void setUseReaction(boolean useReaction) {
        this.useReaction = useReaction;
    }

    public boolean isUseReaction() {
        return useReaction;
    }

    public void setPublicReward(boolean publicReward) {
        this.publicReward = publicReward;
    }

    public boolean isPublicReward() {
        return publicReward;
    }

    public void setMinion(boolean minion) {
        this.minion = minion;
    }

    public boolean isMinion() {
        return minion;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isForward() {
        return forward;
    }

    public void setIsRemoteRange(boolean isRemoteRange) {
        this.isRemoteRange = isRemoteRange;
    }

    public boolean isRemoteRange() {
        return isRemoteRange;
    }

    public void setRemoteRange(boolean isRemoteRange) {
        this.isRemoteRange = isRemoteRange;
    }

    public void setIgnoreFieldOut(boolean ignoreFieldOut) {
        this.ignoreFieldOut = ignoreFieldOut;
    }

    public boolean isIgnoreFieldOut() {
        return ignoreFieldOut;
    }

    public void setIgnoreMoveImpact(boolean ignoreMoveImpact) {
        this.ignoreMoveImpact = ignoreMoveImpact;
    }

    public boolean isIgnoreMoveImpact() {
        return ignoreMoveImpact;
    }

    public void setSummonEffect(int summonEffect) {
        this.summonEffect = summonEffect;
    }

    public int getSummonEffect() {
        return summonEffect;
    }

    public void setSkeleton(boolean skeleton) {
        this.skeleton = skeleton;
    }

    public boolean isSkeleton() {
        return skeleton;
    }

    public void setHideUserDamage(boolean hideUserDamage) {
        this.hideUserDamage = hideUserDamage;
    }

    public boolean isHideUserDamage() {
        return hideUserDamage;
    }

    public void setFixedDamage(int fixedDamage) {
        this.fixedDamage = fixedDamage;
    }

    public int getFixedDamage() {
        return fixedDamage;
    }

    public void setIndividualReward(boolean individualReward) {
        this.individualReward = individualReward;
    }

    public boolean isIndividualReward() {
        return individualReward;
    }

    public void setRemoveAfter(int removeAfter) {
        this.removeAfter = removeAfter;
    }

    public int getRemoveAfter() {
        return removeAfter;
    }

    public void setNotConsideredFieldSet(boolean notConsideredFieldSet) {
        this.notConsideredFieldSet = notConsideredFieldSet;
    }

    public boolean isNotConsideredFieldSet() {
        return notConsideredFieldSet;
    }

    public void setFixedMoveDir(String fixedMoveDir) {
        this.fixedMoveDir = fixedMoveDir;
    }

    public String getFixedMoveDir() {
        return fixedMoveDir;
    }

    public void setNoDoom(boolean noDoom) {
        this.noDoom = noDoom;
    }

    public boolean isNoDoom() {
        return noDoom;
    }

    public void setUseCreateScript(boolean useCreateScript) {
        this.useCreateScript = useCreateScript;
    }

    public boolean isUseCreateScript() {
        return useCreateScript;
    }

    public void setKnockback(boolean knockback) {
        this.knockback = knockback;
    }

    public boolean isKnockback() {
        return knockback;
    }

    public void setBlockUserMove(boolean blockUserMove) {
        this.blockUserMove = blockUserMove;
    }

    public boolean isBlockUserMove() {
        return blockUserMove;
    }

    public void setBodyDisease(int bodyDisease) {
        this.bodyDisease = bodyDisease;
    }

    public int getBodyDisease() {
        return bodyDisease;
    }

    public void setBodyDiseaseLevel(int bodyDiseaseLevel) {
        this.bodyDiseaseLevel = bodyDiseaseLevel;
    }

    public int getBodyDiseaseLevel() {
        return bodyDiseaseLevel;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPartyBonusR(int partyBonusR) {
        this.partyBonusR = partyBonusR;
    }

    public int getPartyBonusR() {
        return partyBonusR;
    }

    public void setRemoveQuest(boolean removeQuest) {
        this.removeQuest = removeQuest;
    }

    public boolean isRemoveQuest() {
        return removeQuest;
    }

    public void setPassiveDisease(int passiveDisease) {
        this.passiveDisease = passiveDisease;
    }

    public int getPassiveDisease() {
        return passiveDisease;
    }

    public void setCoolDamageProb(int coolDamageProb) {
        this.coolDamageProb = coolDamageProb;
    }

    public int getCoolDamageProb() {
        return coolDamageProb;
    }

    public void setCoolDamage(int coolDamage) {
        this.coolDamage = coolDamage;
    }

    public int getCoolDamage() {
        return coolDamage;
    }

    public void setDamageRecordQuest(int damageRecordQuest) {
        this.damageRecordQuest = damageRecordQuest;
    }

    public int getDamageRecordQuest() {
        return damageRecordQuest;
    }

    public void setSealedCooltime(int sealedCooltime) {
        this.sealedCooltime = sealedCooltime;
    }

    public int getSealedCooltime() {
        return sealedCooltime;
    }

    public void setWillEXP(int willEXP) {
        this.willEXP = willEXP;
    }

    public int getWillEXP() {
        return willEXP;
    }

    public void setOnFieldSetSummon(boolean onFieldSetSummon) {
        this.onFieldSetSummon = onFieldSetSummon;
    }

    public boolean isOnFieldSetSummon() {
        return onFieldSetSummon;
    }

    public void setUserControll(boolean userControll) {
        this.userControll = userControll;
    }

    public boolean isUserControll() {
        return userControll;
    }

    public void setNoDebuff(boolean noDebuff) {
        this.noDebuff = noDebuff;
    }

    public boolean isNoDebuff() {
        return noDebuff;
    }

    public void setTargetFromSvr(boolean targetFromSvr) {
        this.targetFromSvr = targetFromSvr;
    }

    public boolean isTargetFromSvr() {
        return targetFromSvr;
    }

    public void setCharismaEXP(int charismaEXP) {
        this.charismaEXP = charismaEXP;
    }

    public int getCharismaEXP() {
        return charismaEXP;
    }

    public void setSplit(boolean isSplit) {
        this.isSplit = isSplit;
    }

    public boolean isSplit() {
        return isSplit;
    }

    public Set<Integer> getRevives() {
        return revives;
    }

    public void setRevives(Set<Integer> revives) {
        this.revives = revives;
    }

    public void addRevive(int revive) {
        revives.add(revive);
    }

    public void setBanMap(boolean isBanMap) {
        this.isBanMap = isBanMap;
    }

    public boolean isBanMap() {
        return isBanMap;
    }

    public void setBanType(int banType) {
        this.banType = banType;
    }

    public int getBanType() {
        return banType;
    }

    public void setBanMsgType(int banMsgType) {
        this.banMsgType = banMsgType;
    }

    public int getBanMsgType() {
        return banMsgType;
    }

    public void setBanMsg(String banMsg) {
        this.banMsg = banMsg;
    }

    public String getBanMsg() {
        return banMsg;
    }

    public void setBanMapFields(List<Tuple<Integer, String>> banMap) {
        this.banMap = banMap;
    }

    public List<Tuple<Integer, String>> getBanMapFields() {
        return banMap;
    }

    public void addBanMap(int fieldID, String portal) {
        this.banMap.add(new Tuple<>(fieldID, portal));
    }

    /**
     * Damages a mob.
     *
     * @param totalDamage the total damage that should be applied to the mob
     */
    public synchronized void damage(Char damageDealer, long totalDamage) {
        if (isSplit() && getOriginMob() != null) {
            getOriginMob().damage(damageDealer, totalDamage);
        }
        // kinda hacky, but meh
        if (MobConstants.isZakumBody(getTemplateId())
                && getField().getMobs().stream().anyMatch(m -> MobConstants.isZakumArm(m.getTemplateId()))) {
            return;
        }
        addDamage(damageDealer, totalDamage);
        damageDealer.getJobHandler().handleMobDamaged(damageDealer, this, totalDamage);
        long maxHP = getMaxHp();
        long oldHp = getHp();
        long newHp = oldHp - totalDamage;
        setHp(newHp);
        double percDamage = ((double) newHp / maxHP);
        newHp = newHp > Integer.MAX_VALUE ? Integer.MAX_VALUE : newHp;
        try {
            executeRunnableAtHp(percDamage);
        } catch( Exception e) {
            e.printStackTrace();
        }
        if (oldHp > 0 && newHp <= 0) {
            // Boss sponges
            // TODO horntail kills
            if (getTemplateId() == 8810214 || getTemplateId() == 8810018 || getTemplateId() == 8810118) {
                getField().getMobs().forEach(m -> m.die(true));
            }
            die(true);
            if (damageDealer.hasQuestInProgress(38022) && getTemplateId() == 9300811) {
                damageDealer.getScriptManager().setQRValue(38022, "clear", false);
            }
            if (isBoss() && getHpTagColor() != 0) {
                getField().broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(this)));
            }
        } else if (isBoss() && getHpTagColor() != 0) {
            getField().broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(this)));
        } else {
            getField().broadcastPacket(MobPool.hpIndicator(getObjectId(), (byte) (percDamage * 100)));
        }
     for (ScheduledFuture sf : dropItemTimed) {
	sf.cancel(true);
	}    
     startDropItemSchedule();   
    }

    public synchronized void heal(int amount) {
        long oldHp = getHp();
        long newHp = oldHp + amount;
        if (newHp > getMaxHp()) {
            newHp = getMaxHp();
        } else if (newHp < 0) {
            newHp = 0;
        }
        setHp(newHp);
        long diff = newHp - oldHp;
        if (getField() != null & diff != 0) {
            getField().broadcastPacket(MobPool.damaged(getObjectId(), diff, getTemplateId(), (byte) 0, getHp(), getMaxHp()));
        }
        if (oldHp > 0 && newHp <= 0) {
            die(true);
        }
    }

    public synchronized void healMP(int amount) {
        long oldMp = getMp();
        long newMp = oldMp + amount;
        if (newMp > getMaxMp()) {
            newMp = getMaxMp();
        } else if (newMp < 0) {
            newMp = 0;
        }
        setMp(newMp);
    }

    public synchronized void die(boolean drops) {
        Field field = getField();

        getField().removeLife(getObjectId());
        distributeExp();
        if (getCopyMob() != null) {
            getCopyMob().die(false);
        }
        if (isSplit()) {
            return;
        }
        if (drops) {
            dropDrops(); // xd
        }
        for (Char chr : getDamageDone().keySet()) {
            chr.getQuestManager().handleMobKill(this);
            chr.getTemporaryStatManager().addSoulMPFromMobDeath();
            if (!chr.getAccount().getMonsterCollection().hasMob(getTemplateId())) {
                chr.getAccount().getMonsterCollection().addMobAndUpdateClient(getTemplateId(), chr);
            }
        }
        getField().broadcastPacket(MobPool.leaveField(getObjectId(), DeathType.ANIMATION_DEATH));
        getDamageDone().clear();
        if (field.canSpawnElite() && getEliteType() == 0 && !isNotRespawnable() &&
                Util.succeedProp(GameConstants.ELITE_MOB_SPAWN_CHANCE, 1000)) {
            spawnEliteVersion();
        } else if (getEliteType() == 1) {
            field.incrementEliteKillCount();
            String msg = null;
            if (field.getKilledElites() >= GameConstants.ELITE_BOSS_REQUIRED_KILLS) {
                field.setKilledElites(field.getKilledElites() % GameConstants.ELITE_BOSS_REQUIRED_KILLS);
                int bossTemplate = Util.getRandomFromCollection(GameConstants.ELITE_BOSS_TEMPLATES);
                Mob mob = MobData.getMobDeepCopyById(bossTemplate);
                mob.setEliteType(3);
                mob.setNotRespawnable(true);
                mob.setMaxHp(MobData.getMobDeepCopyById(getTemplateId()).getMaxHp() * GameConstants.ELITE_BOSS_HP_RATE);
                mob.setHp(mob.getMaxHp());
                mob.setHomeFoothold(getCurFoodhold().deepCopy());
                mob.setCurFoodhold(getCurFoodhold().deepCopy());
                mob.setPosition(getPosition().deepCopy());
                mob.setHomePosition(getPosition().deepCopy());
                field.spawnLife(mob, null);
                field.setEliteState(EliteState.EliteBoss);
                field.broadcastPacket(FieldPacket.eliteState(EliteState.EliteBoss, false, GameConstants.ELITE_BOSS_BGM,
                        null, null));
                field.broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob)));
            } else if (field.getKilledElites() >= GameConstants.ELITE_MOB_DARK_NOTIFICATION) {
                msg = "You feel something in the dark energy...";
            } else {
                msg = "The dark energy is still here. It's making the place quite grim.";
            }
            getField().broadcastPacket(WvsContext.weatherEffectNotice(WeatherEffNoticeType.EliteBoss, msg, 8000)); // 8 seconds
        } else if (getEliteType() == 3) {
            field.broadcastPacket(FieldPacket.eliteState(EliteState.None, true, null, null, null));
            field.setEliteState(EliteState.None);
        }
        setChanged();
        notifyObservers();

        // TEST
        reviveMob();
        for (ScheduledFuture sf : dropItemTimed) {
	sf.cancel(true);
	}         
    }

    public void dropDrops() {
        int ownerID = 0;
        Char mostDamageChar = getMostDamageChar();
        int level = mostDamageChar == null ? 0 : mostDamageChar.getLevel();
        short job = 0;
        if (mostDamageChar != null) {
            ownerID = mostDamageChar.getId();
            job = mostDamageChar.getJob();
        }
        int fhID = getFh();
        if (fhID == 0) {
            Foothold fhBelow = getField().findFootHoldBelow(getPosition());
            if (fhBelow != null) {
                fhID = fhBelow.getId();
            }
        }
        Set<DropInfo> dropInfoSet = getDrops();
        // Add consumable/equip drops based on min(charLv, mobLv)
        level = Math.min(level, getForcedMobStat().getLevel());
        dropInfoSet.addAll(ItemConstants.getConsumableMobDrops(level));
        dropInfoSet.addAll(ItemConstants.getEquipMobDrops(job, level));
        // DropRate & MesoRate Increases
        int mostDamageCharDropRate = getMostDamageChar() != null ? getMostDamageChar().getTotalStat(BaseStat.dropR) : 100;
        int mostDamageCharMesoRate = getMostDamageChar() != null ? getMostDamageChar().getTotalStat(BaseStat.mesoR) : 100;
        int dropRateMob = (getTemporaryStat().hasCurrentMobStat(MobStat.Treasure)
                ? getTemporaryStat().getCurrentOptionsByMobStat(MobStat.Treasure).yOption
                : 0); // Item Drop Rate
        int mesoRateMob = (getTemporaryStat().hasCurrentMobStat(MobStat.Treasure)
                ? getTemporaryStat().getCurrentOptionsByMobStat(MobStat.Treasure).zOption
                : 0); // Meso Drop Rate
        int totalMesoRate = mesoRateMob + mostDamageCharMesoRate * GameConstants.MOB_MESO_RATE;
        int totalDropRate = dropRateMob + mostDamageCharDropRate * GameConstants.MOB_DROP_RATE;
        for (Item item : getMostDamageChar().getCashInventory().getItems()) {
            if (ItemConstants.is2XDropCoupon(item.getItemId())) {
                totalDropRate *= 2;
                break;
            }
        }
        getField().drop(getDrops(), getField().getFootholdById(fhID), getPosition(), ownerID, totalMesoRate,
                totalDropRate, getExplosiveReward() != 0);
    }

    public Map<Char, Long> getDamageDone() {
        return damageDone;
    }

    /**
     * Adds a damage amount to the given Char's current damage. Purely used for keeping track of total damage done by
     * a Char.
     *
     * @param chr    the Char the damage originates from
     * @param damage the damage done
     */
    public void addDamage(Char chr, long damage) {
        long cur = 0;
        if (getDamageDone().containsKey(chr)) {
            cur = getDamageDone().get(chr);
        }
        if (damage <= getHp()) {
            cur += damage;
        } else {
            cur += getHp();
        }
        getDamageDone().put(chr, cur);
    }

    public void distributeExp() {
        long exp = getForcedMobStat().getExp();
        long totalDamage = getDamageDone().values().stream().mapToLong(l -> l).sum();
        Map<Party, PartyDamageInfo> damagePercPerParty = new HashMap<>();
        for (Char chr : getDamageDone().keySet()) {
            double damagePerc = getDamageDone().get(chr) / (double) totalDamage;
            int mobExpRate = chr.getLevel() < 10 ? 1 : GameConstants.MOB_EXP_RATE;
            long appliedExpPre = (long) (exp * damagePerc * mobExpRate);
            long appliedExpPost = appliedExpPre;
            ExpIncreaseInfo eei = new ExpIncreaseInfo();

            // Burning Field
            if (getField().getBurningFieldLevel() > 0) {
                int burningFieldBonusExp = (int) (appliedExpPre * getField().getBonusExpByBurningFieldLevel() / 100);
                eei.setRestFieldBonusExp(burningFieldBonusExp);
                eei.setRestFieldExpRate(getField().getBonusExpByBurningFieldLevel());
                appliedExpPost += burningFieldBonusExp;
            }

            // + Exp% MobStats
            if (getTemporaryStat().hasCurrentMobStat(MobStat.Treasure) && getTemporaryStat().getCurrentOptionsByMobStat(MobStat.Treasure).xOption > 0) { // xOption for Exp%
                int expIncrease = getTemporaryStat().getCurrentOptionsByMobStat(MobStat.Treasure).xOption;
                long mobStatBonusExp = ((appliedExpPre * expIncrease) / 100);
                eei.setBaseAddExp((int) mobStatBonusExp);
                appliedExpPost += mobStatBonusExp;
            }
            for (int id : ItemConstants.EXP_2X_COUPON) {
                if (chr.hasItem(id)) {
                    appliedExpPre *= 2;
                    break; //so coupons won't stack
                }
            }

            eei.setLastHit(true);
            eei.setIncEXP(appliedExpPre);
            chr.addExp(appliedExpPost, eei);

            if (Util.succeedProp(GameConstants.NX_DROP_CHANCE)) {
                int nx = (int) (damagePerc * getNxDropAmount());
                chr.addNx(nx);
            }


            Party party = chr.getParty();
            if (party != null) {
                if (!damagePercPerParty.containsKey(party)) {
                    damagePercPerParty.put(party, new PartyDamageInfo(party, this));
                }
                damagePercPerParty.get(party).addDamageInfo(chr, damagePerc);
            }
        }



        for (PartyDamageInfo pdi : damagePercPerParty.values()) {
            pdi.distributeExp();
        }
    }

    public Char getMostDamageChar() {
        Tuple<Char, Long> max = new Tuple<>(null, (long) -1);
        for (Map.Entry<Char, Long> entry : getDamageDone().entrySet()) {
            Char chr = entry.getKey();
            long damage = entry.getValue();
            if (max == null || damage > max.getRight()) {
                max.setLeft(chr);
                max.setRight(damage);
            }
        }
        return max.getLeft();
    }

    public List<MobSkill> getSkills() {
        return skills;
    }

    public void addSkill(MobSkill skill) {
        getSkills().add(skill);
    }

    public int getSkillUseCount(MobSkill skill) {
        return skillUseCount.getOrDefault(skill.getSkillSN(), 0);
    }

    public int getSkillUseCount(int skillId, int slv) {
        MobSkill ms = getSkills().stream().filter(msf -> msf.getSkillID() == skillId && msf.getLevel() == slv).findFirst().orElse(null);
        return ms == null ? 0 : getSkillUseCount(ms);
    }

    public void incrementSkillUseCount(MobSkill skill) {
        skillUseCount.compute(skill.getSkillSN(), (k, v) -> v == null ? 1 : v+1);
    }

    public void incrementSkillUseCount(int skillId, int slv) {
        MobSkill ms = getSkills().stream().filter(msf -> msf.getSkillID() == skillId && msf.getLevel() == slv).findFirst().orElse(null);
        if (ms != null)
            incrementSkillUseCount(ms);
    }

    public List<MobSkill> getAttacks() {
        return attacks;
    }

    public void addAttack(MobSkill mobSkill) {
        getAttacks().add(mobSkill);
    }

    public MobSkill getAttackById(int attackID) {
        return Util.findWithPred(getAttacks(), att -> att.getSkillSN() == attackID);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mob) {
            Mob mob = (Mob) obj;
            return mob.getTemplateId() == getTemplateId() && mob.getObjectId() == getObjectId() && mob.getField().equals(getField());
        }
        return false;
    }

    public Set<Integer> getQuests() {
        return quests;
    }

    public void setQuests(Set<Integer> quests) {
        this.quests = quests;
    }

    public void addQuest(int questID) {
        getQuests().add(questID);
    }


    public boolean isAlive() {
        return getHp() <= 0;
    }

    public void reviveMob() {
        int mobTemplateID = getTemplateId();
        if (mobTemplateID >= 8800103 && mobTemplateID <= 8800110) {
            // Zakum Arms respawn
            EventManager.addEvent(() -> {
                if (getField().getMobs().stream().anyMatch(mb -> Util.arrayContains(BossConstants.ZAKUM_ARMS, mb.getTemplateId()))) {
                    Mob mob = MobData.getMobDeepCopyById(mobTemplateID);
                    mob.setNotRespawnable(true);
                    mob.setPosition(getPosition());
                    getField().spawnLife(mob, null);
                }
                if (getField().getLifeByTemplateId(mobTemplateID+27) instanceof Mob)
                    ((Mob)getField().getLifeByTemplateId(mobTemplateID+27)).die(false);
            }, 20000);
        }
    }



    private Map<Integer, Long> getSkillCooldowns() {
        return skillCooldowns;
    }

    public boolean hasSkillOffCooldown(int skillID, int slv) {
        return System.currentTimeMillis() >= getSkillCooldowns().getOrDefault(skillID | (slv << 16), Long.MIN_VALUE);
    }

    public boolean hasAttackOffCooldown(int attackID) {
        return System.currentTimeMillis() >= getSkillCooldowns().getOrDefault(-attackID, Long.MIN_VALUE);
    }

    public void putSkillCooldown(int skillID, int slv, long nextUseableTime) {
        getSkillCooldowns().put(skillID | (slv << 16), nextUseableTime);
    }

    public void putAttackOnCooldown(int skillID, int delayForNextAttack) {
        getSkillCooldowns().put(-skillID, System.currentTimeMillis() + delayForNextAttack);
    }

    public boolean hasSkillDelayExpired() {
        return System.currentTimeMillis() > getNextPossibleSkillTime();
    }

    /**
     * Sets when a next skill can be used (in ms from current time).
     *
     * @param delay The delay until the next skill can be used
     */
    public void setSkillDelay(long delay) {
        setNextPossibleSkillTime(System.currentTimeMillis() + delay);
    }

    private long getNextPossibleSkillTime() {
        return nextPossibleSkillTime;
    }

    private void setNextPossibleSkillTime(long nextPossibleSkillTime) {
        this.nextPossibleSkillTime = nextPossibleSkillTime;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        setTemporaryStat(new MobTemporaryStat(this));
        Field field = getField();
        Position pos = getPosition();
        Foothold fh = field.getFootholdById(getFh());
        if (fh == null) {
            fh = field.findFootHoldBelow(pos);
        }
        if (fh == null) {
            // Edge case where the mob is spawned on some weird foothold
            return;
        }
        setHomeFoothold(fh.deepCopy());
        setCurFoodhold(fh.deepCopy());
        Char controller = getField().getLifeToControllers().get(this);
        if (onlyChar == null) {
            for (Char chr : field.getChars()) {
                chr.write(MobPool.enterField(this, false));
                chr.write(MobPool.changeController(this, false, controller == chr));
            }
        } else {
            onlyChar.getClient().write(MobPool.enterField(this, false));
            onlyChar.getClient().write(MobPool.changeController(this, false, controller == onlyChar));
        }
    }

    public void spawnEliteVersion() {
        Mob elite = MobData.getMobDeepCopyById(getTemplateId());
        elite.setHomePosition(getPosition().deepCopy());
        elite.setPosition(getPosition().deepCopy());
        elite.setCurFoodhold(getCurFoodhold().deepCopy());
        elite.setHomeFoothold(getCurFoodhold().deepCopy());
        elite.setNotRespawnable(true);
        List<Triple<Integer, Double, Double>> eliteInfos = GameConstants.getEliteInfoByMobLevel(elite.getForcedMobStat().getLevel());
        Triple<Integer, Double, Double> eliteInfo = Util.getRandomFromCollection(eliteInfos);
        int eliteGrade = eliteInfo.getLeft();
        long newHp = (long) (eliteInfo.getMiddle() * elite.getMaxHp());
        long newExp = (long) (eliteInfo.getRight() * elite.getForcedMobStat().getExp());
        elite.setEliteType(1);
        elite.setEliteGrade(eliteGrade);
        Map<Integer, Integer> possibleSkillsMap = SkillData.getEliteMobSkillsByGrade(eliteGrade);
        if (possibleSkillsMap == null) {
            log.error("Could not load elite skills!");
            return;
        }
        List<Tuple<Integer, Integer>> possibleSkills = new ArrayList<>();
        possibleSkillsMap.forEach((k, v) -> possibleSkills.add(new Tuple(k, v)));
        for (int i = 0; i < GameConstants.ELITE_MOB_SKILL_COUNT; i++) {
            Tuple<Integer, Integer> randomSkill = Util.getRandomFromCollection(possibleSkills);
            elite.addEliteSkill(randomSkill.getLeft(), randomSkill.getRight());
            possibleSkills.remove(randomSkill);
        }
        elite.setMaxHp(newHp);
        elite.setHp(newHp);
        elite.getForcedMobStat().setExp(newExp);
        getField().setNextEliteSpawnTime(System.currentTimeMillis() + GameConstants.ELITE_MOB_RESPAWN_TIME * 1000);
        getField().spawnLife(elite, null);
    }

    public void spawnEliteMobRuneOfDarkness() {
        Mob elite = MobData.getMobDeepCopyById(getTemplateId());
        elite.setHomePosition(getPosition().deepCopy());
        elite.setPosition(getPosition().deepCopy());
        elite.setCurFoodhold(getCurFoodhold().deepCopy());
        elite.setHomeFoothold(getCurFoodhold().deepCopy());
        elite.setNotRespawnable(true);
        List<Triple<Integer, Double, Double>> eliteInfos = GameConstants.getEliteInfoByMobLevel(elite.getForcedMobStat().getLevel());
        Triple<Integer, Double, Double> eliteInfo = Util.getRandomFromCollection(eliteInfos);
        int eliteGrade = eliteInfo.getLeft();
        long newHp = (long) (eliteInfo.getMiddle() * elite.getMaxHp());
        long newExp = (long) (eliteInfo.getRight() * elite.getForcedMobStat().getExp());
        elite.setEliteType(1);
        elite.setEliteGrade(eliteGrade);
        Map<Integer, Integer> possibleSkillsMap = SkillData.getEliteMobSkillsByGrade(eliteGrade);
        List<Tuple<Integer, Integer>> possibleSkills = new ArrayList<>();
        possibleSkillsMap.forEach((k, v) -> possibleSkills.add(new Tuple(k, v)));
        for (int i = 0; i < GameConstants.ELITE_MOB_SKILL_COUNT; i++) {
            Tuple<Integer, Integer> randomSkill = Util.getRandomFromCollection(possibleSkills);
            elite.addEliteSkill(randomSkill.getLeft(), randomSkill.getRight());
            possibleSkills.remove(randomSkill);
        }
        elite.setMaxHp(newHp);
        elite.setHp(newHp);
        elite.getForcedMobStat().setExp(newExp);
        getField().spawnLife(elite, null);
    }

    public List<Tuple<Integer, Integer>> getEliteSkills() {
        return eliteSkills;
    }

    public void addEliteSkill(int skillID, int skillLevel) {
        MobSkill ms = new MobSkill();
        ms.setSkillSN(-1);
        ms.setSkillID(skillID);
        ms.setLevel(skillLevel);
        addSkill(ms);
        getEliteSkills().add(new Tuple<>(skillID, skillID));
    }

    public void setSelfDestruction(boolean selfDestruction) {
        this.selfDestruction = selfDestruction;
    }

    public boolean isSelfDestruction() {
        return selfDestruction;
    }

    public List<MobSkill> getSkillDelays() {
        return skillDelays;
    }

    public boolean isInAttack() {
        return inAttack;
    }

    public void setInAttack(boolean inAttack) {
        this.inAttack = inAttack;
    }

    public void onKilledByChar(Char chr) {
        Field field = getField();

        // Combo Counter per Kill
        int newChrComboCount = chr.getComboCounter() + 1;
        chr.write(UserLocal.comboCounter((byte) 1, newChrComboCount, getObjectId(), 2));
        chr.setComboCounter(newChrComboCount);
        chr.comboKillResetTimer();

        // Exp Orb spawning from Mob every 50 combos
        if (chr.getComboCounter() % 50 == 0) {
            Item item = ItemData.getItemDeepCopy(GameConstants.BLUE_EXP_ORB_ID);
            if (chr.getComboCounter() >= GameConstants.COMBO_KILL_REWARD_PURPLE) {
                item = ItemData.getItemDeepCopy(GameConstants.PURPLE_EXP_ORB_ID);
            }
            if (chr.getComboCounter() >= GameConstants.COMBO_KILL_REWARD_RED) {
                item = ItemData.getItemDeepCopy(GameConstants.RED_EXP_ORB_ID);
            }
            if (chr.getComboCounter() >= GameConstants.COMBO_KILL_REWARD_GOLD) {
                item = ItemData.getItemDeepCopy(GameConstants.GOLD_EXP_ORB_ID);
            }
            Drop drop = new Drop(-1, item);
            drop.setMobExp(getForcedMobStat().getExp());
            chr.getField().drop(drop, getPosition().deepCopy());
        }

        // Any special skills
        chr.getJobHandler().handleMobDeath(this);

        // Random portal spawn: is channel field, is not on cd, has min mob level, and field has no portal already
        // TODO: Fix Inferno Wolf Scripts
        if (getField().isChannelField() && chr.getNextRandomPortalTime() <= System.currentTimeMillis()
                && getField().getAverageMobLevel() > GameConstants.MIN_LEVEL_FOR_RANDOM_FIELD_OCCURENCES
                && Util.succeedProp(GameConstants.RANDOM_PORTAL_SPAWN_CHANCE, 1000)
                && field.getLifes().values().stream().noneMatch(l -> l instanceof RandomPortal)) {
            chr.setNextRandomPortalTime(System.currentTimeMillis() + GameConstants.RANDOM_PORTAL_COOLTIME);
            // 50% chance for inferno/yellow portal
            List<Foothold> listOfFootHolds = new ArrayList<>(field.getNonWallFootholds());
            Foothold foothold = Util.getRandomFromCollection(listOfFootHolds);
            Position position = foothold.getRandomPosition();
            RandomPortal.Type portalType = Util.succeedProp(50)
                    ? RandomPortal.Type.PolloFritto
                    : RandomPortal.Type.Inferno;
            RandomPortal randomPortal = new RandomPortal(portalType, position, chr.getId());
            field.addLife(randomPortal);
            chr.write(RandomPortalPool.created(randomPortal));
        }
    }

    public boolean isEscortMob() {
        return isEscortMob;
    }

    public void setEscortMob(boolean isEscortMob) {
        this.isEscortMob = isEscortMob;
    }

    public List<EscortDest> getEscortDest() {
        return escortDest;
    }

    public void addEscortDest(int destPosX, int destPosY, int attr) {
        addEscortDest(destPosX, destPosY, attr, 0, 0);
    }

    public void addEscortDest(int destPosX, int destPosY, int attr, int mass, int stopDuration) {
        escortDest.add(new EscortDest(destPosX, destPosY, attr, mass, stopDuration));
    }

    public int getCurrentDestIndex() {
        return currentDestIndex;
    }

    public void setCurrentDestIndex(int currentDestIndex) {
        this.currentDestIndex = currentDestIndex;
    }

    public int getEscortStopDuration() {
        return escortStopDuration;
    }

    public void setEscortStopDuration(int escortStopDuration) {
        this.escortStopDuration = escortStopDuration;
    }

    public void clearEscortDest() {
        escortDest = new ArrayList<>();
    }

    public void escortFullPath(int oldAttr) {
        getField().broadcastPacket(MobPool.escortFullPath(this, oldAttr, false));
    }

    public boolean isFinishedEscort() {
        return escortDest.size() == 0;
    }

    @Override
    public void notifyControllerChange(Char controller) {
        for (Char chr : getField().getChars()) {
            chr.write(MobPool.changeController(this, false, controller == chr));
        }
    }

    public void encodeInit(OutPacket outPacket) {
        // CMob::Init
        outPacket.encodePosition(getPosition());
        outPacket.encodeByte(getMoveAction());
        int tid = getTemplateId();
        if (tid == 8910000 || tid == 8910100 || tid == 9990033) { // is_banban_boss
            outPacket.encodeByte(0); // fake?
        }
        if (getCurFoodhold() == null) {
            setCurFoodhold(getField().findFootHoldBelow(getPosition()));
            if (getCurFoodhold() == null) {
                setCurFoodhold(getField().getFootholdById(0));
            }
            if (getHomeFoothold() == null) {
                setHomeFoothold(getCurFoodhold());
            }
        }
        outPacket.encodeShort(getCurFoodhold().getId());
        outPacket.encodeShort(getHomeFoothold().getId());
        byte appearType = getAppearType();
        outPacket.encodeByte(appearType);
        if (appearType == -3 || appearType >= 0) {
            // init -> -2, -1 else
            outPacket.encodeInt(getOption());
        }
        outPacket.encodeByte(getTeamForMCarnival());
        outPacket.encodeLong(getHp() > Long.MAX_VALUE ? Long.MAX_VALUE : getHp());
        outPacket.encodeInt(getEffectItemID());
        if (isPatrolMob()) {
            outPacket.encodeInt(getPosition().getX() - getRange());
            outPacket.encodeInt(getPosition().getX() + getRange());
            outPacket.encodeInt(getDetectX());
            outPacket.encodeInt(getSenseX());
        }
        outPacket.encodeInt(getPhase());
        outPacket.encodeInt(getCurZoneDataType());
        outPacket.encodeInt(getRefImgMobID());
        outPacket.encodeInt(0); // ?
        int ownerAID = getLifeReleaseOwnerAID();
        outPacket.encodeByte(ownerAID > 0);
        if (ownerAID > 0) {
            outPacket.encodeInt(ownerAID);
            outPacket.encodeString(getLifeReleaseOwnerName());
            outPacket.encodeString(getLifeReleaseMobName());
        }
        outPacket.encodeInt(getAfterAttack());
        outPacket.encodeInt(getCurrentAction());
        outPacket.encodeInt(0);
        outPacket.encodeByte(isLeft());
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // ?
            outPacket.encodeInt(0); // extra time?
        }
        outPacket.encodeInt(getScale());
        outPacket.encodeInt(getEliteGrade());
        if (getEliteGrade() >= 0) {
            size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // first skillID?
                outPacket.encodeInt(0); // second skillID?
            }
            outPacket.encodeInt(getEliteType()); // 1 normal, 3 elite boss probably
        }
        ShootingMoveStat sms = getShootingMoveStat();
        outPacket.encodeByte(sms != null);
        if (sms != null) {
            sms.encode(outPacket);
        }

        outPacket.encodeByte(0);// bool

        size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // nType
            outPacket.encodeInt(0); // key?
        }

        outPacket.encodeByte(0);// bool
        outPacket.encodeString("");
        outPacket.encodeInt(getTargetUserIdFromServer());
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeArr(new byte[20]);
    }

    public int getNxDropAmount() {
        // yuno
//        if (getExp() == 0) {
//            return 0;
//        }
//        int base = (int) ((Math.sqrt(getMaxHp() / 100D)) * ((double) getMaxHp() / (getExp() * getLevel())));
//        return Util.getRandom(base, (base + base / 10)); // base + 10% random


        // sjonnie
        long hp = getMaxHp();
        ForcedMobStat fms = getForcedMobStat();
        int base = (int) (((fms.getLevel() / 2D) * (Math.pow(hp, (1 / 7D))))) * GameConstants.NX_DROP_MULTIPLIER;
        return Util.getRandom(base, (base + base / 10)); // base + 10% random
    }

    public void handleDamageReflect(Char chr, int skillID, long totalDamage) {
        MobTemporaryStat mts = getTemporaryStat();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        boolean hasIgnoreCounterCts =
                tsm.hasStat(CharacterTemporaryStat.IgnoreAllCounter)
                || tsm.hasStat(CharacterTemporaryStat.IgnoreAllImmune)
                || tsm.hasStat(CharacterTemporaryStat.Invincible);
        boolean ignoreCounter = hasIgnoreCounterCts || (si != null && si.isIgnoreCounter());
        if ((mts.hasCurrentMobStat(MobStat.PCounter) || mts.hasCurrentMobStat(MobStat.MCounter)) && !ignoreCounter) {
            // DR is always P and M
            // nOption = % of hp, mOption = chance to reflect
            Option o = mts.getCurrentOptionsByMobStat(MobStat.PCounter);
            if (o == null) {
                o = mts.getCurrentOptionsByMobStat(MobStat.MCounter);
            }
            int damage = o.nOption;
            int prop = o.mOption;
            if (prop >= 100 || Util.succeedProp(prop)) {
                int hpDamage = damage;
                chr.damage(hpDamage);
                getField().broadcastPacket(UserPacket.userHitByCounter(chr.getId(), hpDamage));
            }
        }
    }

    public void applyHitDiseaseToPlayer(Char chr, byte attackIdx) {
        MobSkill ms = null;
        if (attackIdx == -1 && getBodyDiseaseLevel() > 0) {
            MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(getBodyDisease(), getBodyDiseaseLevel());
            if (msi != null) {
                ms = new MobSkill();
                ms.setDisease(getBodyDisease());
                ms.setLevel(getBodyDiseaseLevel());
            }
        } else {
            ms = getAttackById(attackIdx);
        }
        if (ms != null && ms.getDisease() != 0) {
            ms.applyEffect(chr);
        }
    }

    public void addMob(int mobId) {
        mobSet.add(mobId);
    }

    public void addParentMob(int mobId) {
        parentMobSet.add(mobId);
    }

    public Set<Integer> getMobSet() {
        return mobSet;
    }

    public Set<Integer> getParentMobSet() {
        return parentMobSet;
    }

    public Mob getOriginMob() {
        if (getTemporaryStat().hasCurrentMobStat(MobStat.SeperateSoulC)) {
            return (Mob) getField().getLifeByObjectID(getTemporaryStat().getCurrentOptionsByMobStat(MobStat.SeperateSoulC).rOption);
        }
        return null;
    }

    public Mob getCopyMob() {
        if (getTemporaryStat().hasCurrentMobStat(MobStat.SeperateSoulP)) {
            return (Mob) getField().getLifeByObjectID(getTemporaryStat().getCurrentOptionsByMobStat(MobStat.SeperateSoulP).rOption);
        }
        return null;
    }

    public String getMobInfo() {
        int mesos = 0;
        for (DropInfo di : getDrops()) {
            if (di.isMoney()) {
                mesos = di.getMoney();
                break;
            }
        }
        Char controller = getField().getLifeToControllers().getOrDefault(this, null);
        return String.format("Mob ID: %s | Template ID: %s | Level: %d | HP: %s/%s " +
                        "| MP: %s/%s | Left: %s | PDR: %s | MDR: %s " +
                        "| Controller: %s | Exp : %s | NX: %s | Mesos: %s",
                NumberFormat.getNumberInstance(Locale.US).format(getObjectId()),
                NumberFormat.getNumberInstance(Locale.US).format(getTemplateId()),
                getLevel(),
                NumberFormat.getNumberInstance(Locale.US).format(getHp()),
                NumberFormat.getNumberInstance(Locale.US).format(getMaxHp()),
                NumberFormat.getNumberInstance(Locale.US).format(getMp()),
                NumberFormat.getNumberInstance(Locale.US).format(getMaxMp()),
                isLeft(),
                getPdr(),
                getMdr(),
                controller == null ? "null" : controller.getName(),
                getForcedMobStat().getExp(),
                getNxDropAmount(),
                mesos
        );
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

    public MobSkill getRandomAvailableSkill() {
        if (getSkills() == null || getSkills().size() == 0) {
            return null;
        }
        List<MobSkill> list = new ArrayList<>();
        List<MobSkill> lesserPriority = new ArrayList<>();
        List<MobSkill> greaterPriority = new ArrayList<>();
        for (MobSkill ms : getSkills()) {
            if (canUseSkill(ms) && !BossConstants.isBlockedSkill(getTemplateId(), ms.getSkillID(), ms.getLevel())) {
                if (ms.getPriority() == 2)
                    greaterPriority.add(ms);
                if (ms.getPriority() == 1)
                    lesserPriority.add(ms);
                list.add(ms);
            }
        }
        return Util.getRandomFromCollection(greaterPriority.size() > 0 ? greaterPriority : lesserPriority.size() > 0 ? lesserPriority : list);
    }

    private boolean canUseSkill(MobSkill ms) {
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(ms.getSkillID(), ms.getLevel());
        int reqHp = msi.getSkillStatIntValue(MobSkillStat.hp);
        int useLimit = ms.getUseLimit() > 0 ? ms.getUseLimit() : msi.getUseLimit();
        // skill exists, is off cooldown, and mob is below required hp percentage to cast skill
        return msi != null && hasSkillOffCooldown(ms.getSkillID(), ms.getLevel()) && (reqHp == 0 || getHpPerc() < reqHp) && (useLimit == 0 || getSkillUseCount(ms) < useLimit)
                && (getSkills().size() == 0 || getSkills().stream().noneMatch(s -> s.getSkillSN() == ms.getPreSkillIndex()) || getSkillUseCount(getSkills().stream().filter(s -> s.getSkillSN() == ms.getPreSkillIndex()).findFirst().orElse(null)) >= ms.getPreSkillCount())
                && !CustomConstants.isDojoMap(getField().getId())
                && canUseSkillLogic(ms);
    }

    private boolean canUseSkillLogic(MobSkill ms) {
        int skillID = ms.getSkillID();
        int slv = ms.getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skillID, slv);

        if (skillID == MobSkillID.Undead.getVal()) {
            // disable undead until two states are fixed
            return false;
        }

        if (skillID == MobSkillID.Summon.getVal() || skillID == MobSkillID.Summon2.getVal()) {
            int summonLimit = msi.getSkillStatIntValue(MobSkillStat.limit);
            boolean summonLimitReached = getField().getMobs().stream().filter(m -> m.getMobSpawnerId() == getObjectId()
                    && msi.getInts().contains(m.getTemplateId())).count() >= (summonLimit <= 0 ? 100 : summonLimit);
            if (summonLimitReached)
                return false;

            if ((ms.isAfterDead() && skillID == MobSkillID.Summon2.getVal() && !canResummon())) {
                // Queen Switch Faces Cooldown
                return false;
            }
        }

        if ((skillID == MobSkillID.PhysicalImmune.getVal() || skillID == MobSkillID.MagicImmune.getVal())
                && (getTemporaryStat().hasCurrentMobStat(MobStat.PImmune) || getTemporaryStat().hasCurrentMobStat(MobStat.MImmune))) {
            // Don't cast Physical or Magic Immune if we already have one up, just wastes a cooldown
            return false;
        }
        if (skillID == MobSkillID.MobConsume.getVal()) {
            // Don't try to eat a mob unless the mob exists and in range, and has existed for long enough
            Rect range = getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb()));
            if (getField().getMobs().stream().noneMatch(mb -> range.hasPositionInside(mb.getPosition()) && mb.getTemplateId() == msi.getInts().get(0)
                    && (!mb.hasProperty("summonTimestamp") || System.currentTimeMillis() - ((long) mb.getProperty("summonTimestamp")) > BossConstants.MOB_CONSUME_DELAY)))
                return false;
        }

        if (getTemplateId() == BossConstants.ALLURING_MIRROR || getTemplateId() == BossConstants.CHAOS_ALLURING_MIRROR) {
            if (getField().getMobs().stream().anyMatch(m -> m.getTemplateId() == 8920004 || m.getTemplateId() == 8920104)) {
                return false;
            }
        }

        if (getTemplateId() >= 8800000 && getTemplateId() <= 8800148) {
            return canUseSkillLogicZakum(ms);
        }

        if (getTemplateId() >= 8950000 && getTemplateId() <= 8950003 || getTemplateId() >= 8950100 && getTemplateId() <= 8950103) {
            return canUseSkillLogicLotus(ms);
        }

        if (getTemplateId() >= 9450022) {
            return canUseSkillLogicPrincessNo(ms);
        }

        if (getTemplateId() == 8910000) {
            if (ms.getSkillID() == 170 && getHp() > 300000000000L) {
                return false;
            }
            if (ms.getSkillID() == 203) {
                return false;
            }
        }
        if (getTemplateId() == 8880111) {
            if (ms.getSkillID() == 170 && ms.getLevel() == 47) {
                return false;
            }
        }
        return true;
    }

    private boolean canUseSkillLogicPrincessNo(MobSkill ms) {
        if (ms.getSkillID() == 145) {
            return false;
        }
        if (ms.getSkillID() == 170) {
            return false;
        }
        return true;
    }

    private boolean canUseSkillLogicZakum(MobSkill ms) {
        if (ms.getSkillID() == MobSkillID.Teleport.getVal()) {
            return false;
        }
        if (getTemplateId() == 8800002 || getTemplateId() == 8800022 || getTemplateId() == 8800102) {
            // don't use skills if any arms are alive
            if (!(getTemplateId() == 8800102 && ms.getSkillID() == 201 && ms.getLevel() == 162) && getField().getMobs().stream().anyMatch(mob ->
                    (mob.getTemplateId() >= 8800003 && mob.getTemplateId() <= 8800010
                            || mob.getTemplateId() >= 8800023 && mob.getTemplateId() <= 8800030
                            || mob.getTemplateId() >= 8800103 && mob.getTemplateId() <= 8800110))) {
                return false;
            }
            if (ms.getSkillID() == 201 && ms.getLevel() == 172)
                return false;
        } else if (Util.arrayContains(BossConstants.ZAKUM_ARMS, getTemplateId())) {
            if (ms.getSkillID() == MobSkillID.Stun.getVal() && (getTemplateId() < 8800103 || getField().getAliveCharsCount() < 2)) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.Damage.getVal() && ms.getLevel() == 27) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.Damage.getVal() && ms.getLevel() != 27) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.FieldCommand.getVal()) {
                return false;
            }
        }
        return true;
    }

    private boolean canUseSkillLogicLotus(MobSkill ms) {
        MobTemporaryStat mts = getTemporaryStat();
        if (getTemplateId() == 8950000 || getTemplateId() == 8950100) { // First Stage
            if (ms.getSkillID() == MobSkillID.LaserAttack.getVal()) {
                if (mts.hasCurrentMobStat(MobStat.Laser)) {
                    return false;
                }
            }

            if (ms.getSkillID() == MobSkillID.LaserControl.getVal()) {
                if (!mts.hasCurrentMobStat(MobStat.Laser) || hasSkillOffCooldown(201, 83)) {
                    return false;
                }
            }

            if (ms.getSkillID() == MobSkillID.Summon2.getVal()) {
                if (!mts.hasCurrentMobStat(MobStat.Laser)) {
                    return false;
                }
            }
        }
        if (getTemplateId() == 8950001 || getTemplateId() == 8950101) { // Second Stage
            if (ms.getSkillID() == MobSkillID.AreaPoison.getVal()) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.BounceAttack.getVal() && ms.getLevel() == 6 || ms.getSkillID() == MobSkillID.BounceAttack.getVal() && ms.getLevel() == 14
                    || ms.getSkillID() == MobSkillID.BounceAttack.getVal() && ms.getLevel() == 16) {
                return false;
            }
        }
        if (getTemplateId() == 8950002 || getTemplateId() == 8950102) { // Third Stage
            if (ms.getSkillID() == MobSkillID.AreaPoison.getVal()) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.BounceAttack.getVal() && ms.getLevel() == 13 || ms.getSkillID() == MobSkillID.BounceAttack.getVal() && ms.getLevel() == 16) {
                return false;
            }
            if (ms.getSkillID() == MobSkillID.FireAtRandomAttack.getVal()) {
                return false;
            }
        }
        return true;
    }

    public void blockAttack(ArrayList<Integer> attacks) {
        getField().broadcastPacket(MobPool.mobAttackBlock(this, attacks));
    }

    public void blockAllAttacks() {
        ArrayList<Integer> attacks = new ArrayList<>();
        getAttacks().forEach(ms -> attacks.add(ms.getSkillSN()));
        blockAttack(attacks);
    }

    public void unblockAllAttacks() {
        blockAttack(new ArrayList<>());
    }

    public int getMobSpawnerId() {
        return mobSpawnerId;
    }

    public void setMobSpawnerId(int mobSpawnerId) {
        this.mobSpawnerId = mobSpawnerId;
    }

    public void teleport(int xPos, int yPos) {
        Rect possibleRect = getPosition().getRectAround(new Rect(-xPos, -yPos, xPos, yPos));
        setPosition(new Position(Util.getRandom(possibleRect.getLeft(), possibleRect.getRight()),
                Util.getRandom(possibleRect.getTop(), possibleRect.getBottom())));
        getField().broadcastPacket(MobPool.teleportRequest(this, 3, getPosition()));
    }

    public void teleport(Position position, int skillAfter) {
        setPosition(position);
        getField().broadcastPacket(MobPool.teleportRequest(this, skillAfter, getPosition()));
    }

    public void buff(double multi) {
        ForcedMobStat fms = getForcedMobStat();
        setMaxHp((long) (getMaxHp() * multi));
        setHp((long) (getHp() * multi));
        fms.setExp((long) (getExp() * multi));
        setPad((int) (getPad() + Math.sqrt(multi)));
        setMad((int) (getMad() + Math.sqrt(multi)));
        setScale(130);
        setBuffed(true);
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    /**
     * Sets the amount of time before this mob can summon an "afterdead" summon (like Queen).
     * @param nextSummonPossible amount of seconds until next possible summon
     */
    public void setNextSummonPossible(int nextSummonPossible) {
        this.nextSummonPossible = System.currentTimeMillis() + nextSummonPossible * 1000;
    }

    public boolean canResummon() {
        return nextSummonPossible <= System.currentTimeMillis();
    }
    
    
   public final void cancelDropItem() {
        lastDropTime = 0;
    }

    public final void startDropItemSchedule() {
        dropItemTimed.add(
        EventManager.addFixedRateEvent(() -> doDropItem(System.currentTimeMillis()), (6 * 1000), (6 * 1000), TimeUnit.MILLISECONDS));
    }

    public boolean shouldDrop(long now) {
        return lastDropTime > 0 && lastDropTime + (getDropItemPeriod() * 1000) < now;
    }

    public void doDropItem(long now) {
        final int itemId;
        final int maxDrop;
        switch (getTemplateId()) {
            case 9300907:
                itemId = 4001101;
                maxDrop = 10;
                break;                
            case 9300908:    
                itemId = 4001101;
                maxDrop = 100;
                break;
            default: //until we find out ... what other mobs use this and how to get the ITEMID
                return;
        }
        if (!isAlive() && this != null && this.getField() != null) {
                Item item = ItemData.getItemDeepCopy(itemId);
                Drop drop = new Drop(itemId, item);
                this.getField().drop(drop, getPosition(), getPosition(), true);
                lastDropTime++;
                if (itemId == 4001101) {
                getField().broadcastPacket(FieldPacket.setAchieveRate(lastDropTime * 10));   
                
                if (lastDropTime == 10) {
                getField().setProperty("stage", 2);    
                getField().broadcastPacket(WvsContext.weatherEffectNotice(WeatherEffNoticeType.RiceCakePQ, "You got all 10! Ohh this is the best. Hey, come see me again.", 7000));
                //showWeatherNotice("6 Primrose Seeds, stolen by the pigs, must be recovered... ", WeatherEffNoticeType.RiceCakePQ)    
                }
                }
        }
        if (lastDropTime >= maxDrop) {
        for (ScheduledFuture sf : dropItemTimed) {
	sf.cancel(true);
	}   
        }
    }    
}
