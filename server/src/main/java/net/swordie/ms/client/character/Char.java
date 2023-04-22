package net.swordie.ms.client.character;

import net.swordie.ms.Server;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.LinkSkill;
import net.swordie.ms.client.User;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.alliance.AllianceResult;
import net.swordie.ms.client.anticheat.OffenseManager;
import net.swordie.ms.client.character.avatar.AvatarData;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.avatar.BeautyAlbum;
import net.swordie.ms.client.character.cards.MonsterBookInfo;
import net.swordie.ms.client.character.damage.DamageCalc;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.info.*;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.monsterbattle.MonsterBattleLadder;
import net.swordie.ms.client.character.monsterbattle.MonsterBattleMobInfo;
import net.swordie.ms.client.character.monsterbattle.MonsterBattleRankInfo;
import net.swordie.ms.client.character.potential.CharacterPotential;
import net.swordie.ms.client.character.potential.CharacterPotentialMan;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestEx;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.character.union.Union;
import net.swordie.ms.client.character.union.UnionMember;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.friend.FriendFlag;
import net.swordie.ms.client.friend.FriendRecord;
import net.swordie.ms.client.friend.FriendshipRingRecord;
import net.swordie.ms.client.friend.result.FriendResult;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.guild.GuildMember;
import net.swordie.ms.client.guild.result.GuildResult;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.JobManager;
import net.swordie.ms.client.jobs.adventurer.pirate.Corsair;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.flora.Ark;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.client.jobs.legend.Luminous;
import net.swordie.ms.client.jobs.legend.Phantom;
import net.swordie.ms.client.jobs.resistance.WildHunterInfo;
import net.swordie.ms.client.jobs.resistance.demon.DemonAvenger;
import net.swordie.ms.client.jobs.sengoku.Hayato;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.client.party.PartyResult;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.db.converters.InlinedIntArrayConverter;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.*;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.ClientSocket;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.*;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.loaders.*;
import net.swordie.ms.loaders.containerclasses.AndroidInfo;
import net.swordie.ms.loaders.containerclasses.ItemInfo;
import net.swordie.ms.loaders.containerclasses.ItemSet;
import net.swordie.ms.scripts.ScriptInfo;
import net.swordie.ms.scripts.ScriptManagerImpl;
import net.swordie.ms.scripts.ScriptType;
import net.swordie.ms.util.*;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.Channel;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.*;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.gach.GachaponManager;
import net.swordie.ms.world.shop.NpcShopDlg;
import net.swordie.ms.world.shop.NpcShopItem;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.enums.ChatType.SpeakerChannel;
import static net.swordie.ms.enums.ChatType.SystemNotice;
import static net.swordie.ms.enums.InvType.EQUIP;
import static net.swordie.ms.enums.InvType.EQUIPPED;
import static net.swordie.ms.enums.InventoryOperation.*;

/**
 * Created on 11/17/2017.
 */
@Entity
@Table(name = "characters")
public class Char {

    @Transient
    private static final Logger log = Logger.getLogger(Char.class);

    @Transient
    private Client client;
    private int rewardPoints;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private int userId;

    @JoinColumn(name = "questManager")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private QuestManager questManager;

    @JoinColumn(name = "equippedInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory equippedInventory = new Inventory(EQUIPPED, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "equipInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory equipInventory = new Inventory(EQUIP, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "consumeInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory consumeInventory = new Inventory(InvType.CONSUME, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "etcInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory etcInventory = new Inventory(InvType.ETC, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "installInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory installInventory = new Inventory(InvType.INSTALL, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "cashInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory cashInventory = new Inventory(InvType.CASH, GameConstants.MAX_INV_SLOTS);

    @JoinColumn(name = "avatarData")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AvatarData avatarData;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuncKeyMap> funcKeyMaps;

    @JoinColumn(name = "hairInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory hairInventory = new Inventory(InvType.HAIR, 3);

    @JoinColumn(name = "faceInventory")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Inventory faceInventory = new Inventory(InvType.FACE, 3);

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BeautyAlbum> beautyAlbum = new ArrayList<>();

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Skill> skills;

    @JoinColumn(name = "ownerID")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Friend> friends;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CharacterPotential> potentials;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Familiar> familiars;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Macro> macros = new ArrayList<>();

    @JoinColumn(name = "guild")
    @OneToOne(cascade = CascadeType.ALL)
    private Guild guild;

    @JoinColumn(name = "monsterBook")
    @OneToOne(cascade = CascadeType.ALL)
    private MonsterBookInfo monsterBookInfo;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<StolenSkill> stolenSkills;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ChosenSkill> chosenSkills;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MatrixRecord> matrixRecords = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hyperrockfields", joinColumns = @JoinColumn(name = "charId"))
    @Column(name = "fieldid")
    @OrderColumn(name = "ord")
    private int[] hyperrockfields = new int[13];

    @Column(name = "monsterparkcount")
    private byte monsterParkCount;

    private int partyID = 0; // Just for DB purposes
    private int previousFieldID;
    private int location;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "skillcooltimes", joinColumns = @JoinColumn(name = "charId"))
    @MapKeyColumn(name = "skillid")
    @Column(name = "nextusabletime")
    private Map<Integer, Long> skillCoolTimes = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "towerchairs", joinColumns = @JoinColumn(name = "charId"))
    @Column(name = "chairid")
    @OrderColumn(name = "ord")
    private int[] towerChairs;

    @ElementCollection
    @CollectionTable(name = "quests_ex", joinColumns = @JoinColumn(name = "charId"))
    @MapKeyColumn(name = "questID")
    @Column(name = "qrValue")
    private Map<Integer, String> questsExStorage;

    @JoinColumn(name = "charId")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotTimeReward> hotTimeRewards = new ArrayList<>();

    @Transient
    private CharacterPotentialMan potentialMan;
    @Transient
    private Map<Integer, QuestEx> questRecordEx;
    @Transient
    private Ranking ranking;
    @Transient
    private int combatOrders;
    @Transient
    private List<ItemPot> itemPots;
    @Transient
    private List<Pet> pets;
    @Transient
    private List<FriendRecord> friendRecords;
    @Transient
    private List<ExpConsumeItem> expConsumeItems;
    @Transient
    private List<MonsterBattleMobInfo> monsterBattleMobInfos;
    @Transient
    private MonsterBattleLadder monsterBattleLadder;
    @Transient
    private MonsterBattleRankInfo monsterBattleRankInfo;
    @Transient
    private Position position;
    @Transient
    private Position oldPosition;
    @Transient
    private Field field;
    @Transient
    private byte moveAction;
    @Transient
    private TemporaryStatManager temporaryStatManager;
    @Transient
    private GachaponManager gachaponManager;
    @Transient
    private Job jobHandler;
    @Transient
    private MarriageRecord marriageRecord;
    @Transient
    private WildHunterInfo wildHunterInfo;
    @Transient
    private ZeroInfo zeroInfo;
    @Transient
    private int nickItem;
    @Transient
    private DamageSkinSaveData damageSkin = new DamageSkinSaveData();
    @Transient
    private DamageSkinSaveData premiumDamageSkin = new DamageSkinSaveData();
    @Transient
    private boolean partyInvitable;
    @Transient
    private ScriptManagerImpl scriptManagerImpl = new ScriptManagerImpl(this);
    @Transient
    private int driverID;
    @Transient
    private int passengerID;
    @Transient
    private int chocoCount;
    @Transient
    private int activeEffectItemID;
    @Transient
    private int monkeyEffectItemID;
    @Transient
    private int completedSetItemID;
    @Transient
    private short fieldSeatID;
    @Transient
    private PortableChair chair;
    @Transient
    private short foothold;
    @Transient
    private int tamingMobLevel;
    @Transient
    private int tamingMobExp;
    @Transient
    private int tamingMobFatigue;
    @Transient
    private MiniRoom miniRoom;
    @Transient
    private String ADBoardRemoteMsg;
    @Transient
    private boolean inCouple;
    @Transient
    private CoupleRecord couple;
    @Transient
    private FriendshipRingRecord friendshipRingRecord;
    @Transient
    private int evanDragonGlide;
    @Transient
    private int kaiserMorphRotateHueExtern;
    @Transient
    private int kaiserMorphPrimiumBlack;
    @Transient
    private int kaiserMorphRotateHueInnner;
    @Transient
    private int makingMeisterSkillEff;
    @Transient
    private FarmUserInfo farmUserInfo;
    @Transient
    private int customizeEffect;
    @Transient
    private String customizeEffectMsg;
    @Transient
    private byte soulEffect;
    @Transient
    private FreezeHotEventInfo freezeHotEventInfo;
    @Transient
    private int eventBestFriendAID;
    @Transient
    private int mesoChairCount;
    @Transient
    private boolean beastFormWingOn;
    @Transient
    private int activeNickItemID;
    @Transient
    private int mechanicHue;
    @Transient
    private boolean online;
    @Transient
    private Party party;
    @Transient
    private Instance instance;
    @Transient
    private int bulletIDForAttack;
    @Transient
    private NpcShopDlg shop;
    @Transient // yes
    private User user;
    @Transient // yes
    private Account account;
    @Transient
    private Client chatClient;
    @Transient
    private DamageCalc damageCalc;
    @Transient
    private boolean buffProtector;
    @Transient
    private int comboCounter;
    @Transient
    private ScheduledFuture comboKillResetTimer;
    @Transient
    private ScheduledFuture timeLimitTimer;
    @Transient
    private int deathCount = -1;
    @Transient
    private long runeStoneCooldown;
    @Transient
    private MemorialCubeInfo memorialCubeInfo;
    @Transient
    private Familiar activeFamiliar;
    @Transient
    private boolean skillCDBypass = false;
    // TODO Move this to CharacterStat?
    @Transient
    private Map<BaseStat, Long> baseStats = new HashMap<>();
    @Transient
    private Map<BaseStat, Set<Integer>> nonAddBaseStats = new HashMap<>();
    @Transient
    private boolean changingChannel;
    @Transient
    private TownPortal townPortal;
    @Transient
    private TradeRoom tradeRoom;
    @Transient
    private boolean battleRecordOn;
    @Transient
    private long nextRandomPortalTime;
    @Transient
    private Map<Integer, Integer> currentDirectionNode;
    @Transient
    private String lieDetectorAnswer = "";
    @Transient
    private long lastLieDetector = 0;
    // TOOD: count and log lie detector passes and fails
    @Transient
    private boolean tutor = false;
    @Transient
    private int transferField = 0;
    @Transient
    private int transferFieldReq = 0;
    @Transient
    private String blessingOfFairy = null;
    @Transient
    private String blessingOfEmpress = null;
    @Transient
    private Map<Integer, Integer> hyperPsdSkillsCooltimeR = new HashMap<>();
    @Transient
    private boolean isInvincible;
    @Transient
    private boolean talkingToNpc;
    @Convert(converter = InlinedIntArrayConverter.class)
    private List<Integer> quickslotKeys;
    @Transient
    private Android android;
    @Transient
    private boolean skillInfoMode = false;
    @Transient
    private boolean debugMode = true;
    @Transient
    private List<NpcShopItem> buyBack = new ArrayList<>();
    @Transient
    private Map<Integer, PsychicArea> psychicAreas = new HashMap<>();
    @Transient
    private Map<Integer, ForceAtom> forceAtoms = new HashMap<>();
    @Transient
    private Map<Integer, SecondAtom> secondAtoms = new HashMap<>();
    @Transient
    private int forceAtomKeyCounter = 1;
    @Transient
    private int secondAtomKeyCounter = 0;
    @Transient
    private Char copy;
    @Transient
    private Map<Integer, AffectedArea> followAffectedAreas = new HashMap<>();
    @Transient
    private boolean showDamageCalc;
    @Transient
    private Map<Skill, WeaponType> psdWTBonus = new HashMap<>();
    @Transient
    private boolean hide;
    @Transient
    private Map<Integer, Integer> activeSetEffects = new HashMap<>();
    @Transient
    private Map<BaseStat, Integer> setBaseStats = new HashMap<>();
    @Transient
    private Map<BaseStat, Set<Integer>> setNonAddBaseStats = new HashMap<>();

    @Transient
    private ScheduledFuture keyDownTimer;

    @Transient
    private Map<Short, ScheduledFuture> recoverySchedules = new ConcurrentHashMap<>();

    public ScheduledFuture getKeyDownTimer() {
        return keyDownTimer;
    }

    public void setKeyDownTimer(ScheduledFuture keyDownTimer) {
        this.keyDownTimer = keyDownTimer;
    }

    public void cancelKeyDownTimer() {
        if (getKeyDownTimer() != null && !getKeyDownTimer().isDone()) {
            getKeyDownTimer().cancel(true);
        }
    }

    public final void removeSchedulers() {
        removeRecoverySchedulers();
        cancelKeyDownTimer();
        if (comboKillResetTimer != null && !comboKillResetTimer.isDone()) {
            comboKillResetTimer.cancel(true);
        }
        getJobHandler().cancelTimers();
    }

    public Char() {
        this("", 0, 0, 0, (short) 0, 0, (byte) -1, (byte) 0, 0, 0, new int[]{});
    }

    public Char(String name, int keySettingType, int eventNewCharSaleJob, int job, short curSelectedSubJob, int curSelectedRace,
                byte gender, byte skin, int face, int hair, int[] items) {
        avatarData = new AvatarData();
        avatarData.setAvatarLook(new AvatarLook());
        AvatarLook avatarLook = avatarData.getAvatarLook();
        avatarLook.setGender(gender);
        avatarLook.setSkin(skin);
        avatarLook.setFace(face);
        avatarLook.setHair(hair);
        Map<Byte, Integer> hairEquips = new HashMap<>();
        for (int itemId : items) {
            Equip equip = ItemData.getEquipDeepCopyFromID(itemId, false);
            if (equip != null && ItemConstants.isEquip(itemId)) {
                hairEquips.put((byte) ItemConstants.getBodyPartFromItem(itemId, 2), itemId);
                if ("Wp".equals(equip.getiSlot())) {
                    if (!equip.isCash()) {
                        avatarLook.setWeaponId(itemId);
                    } else {
                        avatarLook.setWeaponStickerId(itemId);
                    }
                }
            }
        }
        avatarLook.setHairEquips(hairEquips);
        avatarLook.setJob(job);
        if (curSelectedRace == 15) {
            getAvatarData().setZeroAvatarLook(new AvatarLook());
            AvatarLook BetaAvatarLook = getAvatarData().getZeroAvatarLook();
            BetaAvatarLook.setGender(1);
            BetaAvatarLook.setSkin(avatarLook.getSkin());
            BetaAvatarLook.setFace(21290);
            BetaAvatarLook.setHair(37623);
            BetaAvatarLook.setZeroBetaLook(true);
            avatarLook.setWeaponId(1572000);
            BetaAvatarLook.setWeaponId(1562000);
            BetaAvatarLook.getHairEquips().put((byte) BodyPart.Top.getVal(), 1052606);
            BetaAvatarLook.getHairEquips().put((byte) BodyPart.Shoes.getVal(), 1072814);
            BetaAvatarLook.getHairEquips().put((byte) BodyPart.Cape.getVal(), 1102552);
        }
        CharacterStat characterStat = new CharacterStat(name, job);
        getAvatarData().setCharacterStat(characterStat);
        characterStat.setGender(gender);
        characterStat.setSkin(skin);
        characterStat.setFace(items.length > 0 ? items[0] : 0);
        characterStat.setHair(items.length > 1 ? items[1] : 0);
        characterStat.setSubJob(curSelectedSubJob);
        ranking = new Ranking();
        pets = new ArrayList<>();
        questManager = new QuestManager(this);
        itemPots = new ArrayList<>();
        friendRecords = new ArrayList<>();
        expConsumeItems = new ArrayList<>();
        skills = new HashSet<>();
        temporaryStatManager = new TemporaryStatManager(this);
        gachaponManager = new GachaponManager();
        friends = new HashSet<>();
        monsterBookInfo = new MonsterBookInfo();
        potentialMan = new CharacterPotentialMan(this);
        familiars = new HashSet<>();
        hyperrockfields = new int[]{
                999999999,
                999999999,
                999999999,

                999999999,
                999999999,
                999999999,

                999999999,
                999999999,
                999999999,

                999999999,
                999999999,
                999999999,

                999999999,
        };
        towerChairs = new int[6];
        monsterParkCount = 0;
        currentDirectionNode = new HashMap<>();
        potentials = new HashSet<>();
        questRecordEx = new HashMap<>();
        funcKeyMaps = new ArrayList<>();
//        monsterBattleMobInfos = new ArrayList<>();
//        monsterBattleLadder = new MonsterBattleLadder();
//        monsterBattleRankInfo = new MonsterBattleRankInfo();
    }

    public static Char getFromDBById(int userId) {
        Char chr = (Char) DatabaseManager.getObjFromDB(Char.class, userId);
        User user = null;
        for (World world : Server.getInstance().getWorlds()) {
            user = world.getUserById(chr.getUserId());
            if (user != null) {
                // ensures the char is the same instance as the one the server has in its cache
                chr = user.getCharById(userId);
                break;
            }
        }
        return chr;
    }

    public static Char getFromDBByName(String name) {
        log.info(String.format("%s: Trying to get Char by name (%s).", LocalDateTime.now(), name));
        // DAO?
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Char chr WHERE chr.avatarData.characterStat.name = :name");
        query.setParameter("name", name);
        List l = ((org.hibernate.query.Query) query).list();
        Char chr = null;
        if (l != null && l.size() > 0) {
            chr = (Char) l.get(0);
        }
        transaction.commit();
        session.close();
        return chr;
    }

    public static Char getFromDBByNameAndWorld(String name, int worldId) {
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Char chr " +
                "WHERE chr.avatarData.characterStat.name = :name AND chr.avatarData.characterStat.worldIdForLog = :world");
        query.setParameter("name", name);
        query.setParameter("world", worldId);
        List l = ((org.hibernate.query.Query) query).list();
        Char chr = null;
        if (l != null && l.size() > 0) {
            chr = (Char) l.get(0);
        }
        transaction.commit();
        session.close();
        return chr;
    }

    public AvatarData getAvatarData() {
        return avatarData;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public Integer getId() {
        return id;
    }

    public void setAvatarData(AvatarData avatarData) {
        this.avatarData = avatarData;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public Inventory getEquippedInventory() {
        return equippedInventory;
    }

    public void addItemToInventory(InvType type, Item item, boolean hasCorrectBagIndex) {
        addItemToInventory(type, item, hasCorrectBagIndex, false);
    }

    public void addItemToInventory(InvType type, Item item, boolean hasCorrectBagIndex, boolean byPet) {
        if (item == null) {
            return;
        }
        Inventory inventory = getInventoryByType(type);
        if (inventory == null || inventory.isFull()) {
            throw new IllegalStateException("Cannot add item to a full inventory.");
        }
        ItemInfo ii = ItemData.getItemInfoByID(item.getItemId());
        int quantity = item.getQuantity();
        Item existingItem = inventory.getItemByItemIDAndStackable(item.getItemId());
        boolean rec = false;
        if (existingItem != null && existingItem.getInvType().isStackable() && existingItem.getQuantity() < ii.getSlotMax()) {
            if (quantity + existingItem.getQuantity() > ii.getSlotMax()) {
                quantity = ii.getSlotMax() - existingItem.getQuantity();
                item.setQuantity(item.getQuantity() - quantity);
                rec = true;
            }
            existingItem.addQuantity(quantity);
            write(WvsContext.inventoryOperation(!byPet, false,
                    UpdateQuantity, (short) existingItem.getBagIndex(), (byte) -1, 0, existingItem));
            Item copy = item.deepCopy();
            copy.setQuantity(quantity);
            if (rec) {
                addItemToInventory(item);
            }
        } else {
            if (!hasCorrectBagIndex) {
                item.setBagIndex(inventory.getFirstOpenSlot());
            }
            Item itemCopy = null;
            if (item.getInvType().isStackable() && ii != null && item.getQuantity() > ii.getSlotMax()) {
                itemCopy = item.deepCopy();
                quantity = quantity - ii.getSlotMax();
                itemCopy.setQuantity(quantity);
                item.setQuantity(ii.getSlotMax());
                rec = true;
            }
            inventory.addItem(item);
            if (ItemConstants.isArcaneSymbol(item.getItemId())) {
                ((Equip) item).initSymbolStats((short) 1, getJob());
            }
            write(WvsContext.inventoryOperation(!byPet, false,
                    Add, (short) item.getBagIndex(), (byte) -1, 0, item));
            if (rec) {
                addItemToInventory(itemCopy);
            }
        }
        setBulletIDForAttack(calculateBulletIDForAttack());
    }

    public void addItemToInventory(Item item) {
        addItemToInventory(item.getInvType(), item, false);
    }

    public void setEquippedInventory(Inventory equippedInventory) {
        this.equippedInventory = equippedInventory;
    }

    public Inventory getEquipInventory() {
        return equipInventory;
    }

    public void setEquipInventory(Inventory equipInventory) {
        this.equipInventory = equipInventory;
    }

    public Inventory getConsumeInventory() {
        return consumeInventory;
    }

    public void setConsumeInventory(Inventory consumeInventory) {
        this.consumeInventory = consumeInventory;
    }

    public Inventory getEtcInventory() {
        return etcInventory;
    }

    public void setEtcInventory(Inventory etcInventory) {
        this.etcInventory = etcInventory;
    }

    public Inventory getInstallInventory() {
        return installInventory;
    }

    public void setInstallInventory(Inventory installInventory) {
        this.installInventory = installInventory;
    }

    public Inventory getCashInventory() {
        return cashInventory;
    }

    public void setCashInventory(Inventory cashInventory) {
        this.cashInventory = cashInventory;
    }

    /**
     * Encodes this Char's info inside a given {@link OutPacket}, with given info.
     *
     * @param outPacket The OutPacket this method should encode to.
     * @param mask      Which info should be encoded.
     */
    public void encode(OutPacket outPacket, DBChar mask) {
        // CharacterData::Decode
        outPacket.encodeLong(mask.get());

        outPacket.encodeByte(getCombatOrders());
        for (int i = 0; i < GameConstants.MAX_PET_AMOUNT; i++) {
            if (i < getPets().size()) {
                outPacket.encodeInt(getPets().get(i).getActiveSkillCoolTime());
            } else {
                outPacket.encodeInt(-1);
            }
        }
        outPacket.encodeByte(0); // unk, not in kmst
        byte sizeByte = 0;
        outPacket.encodeByte(sizeByte);
        for (int i = 0; i < sizeByte; i++) {
            outPacket.encodeInt(0);
        }

        int sizee = 0;
        outPacket.encodeInt(sizee);
        for (int i = 0; i < sizee; i++) {
            outPacket.encodeInt(0); // nKey
            outPacket.encodeLong(0); // pInfo
        }
        boolean boolan = false;
        outPacket.encodeByte(boolan); // again unsure
        if (boolan) {
            outPacket.encodeByte(0);
            sizee = 0;
            outPacket.encodeInt(sizee);
            for (int i = 0; i < sizee; i++) {
                outPacket.encodeLong(0);
            }
            for (int i = 0; i < sizee; i++) {
                outPacket.encodeLong(0);
            }
        }

        if (mask.isInMask(DBChar.Character)) {
            getAvatarData().getCharacterStat().encode(outPacket);
            outPacket.encodeByte(getFriendRecords().size());
            boolean hasBlessingOfFairy = getBlessingOfFairy() != null;
            outPacket.encodeByte(hasBlessingOfFairy);
            if (hasBlessingOfFairy) {
                outPacket.encodeString(getBlessingOfFairy());
            }
            boolean hasBlessingOfEmpress = getBlessingOfEmpress() != null;
            outPacket.encodeByte(hasBlessingOfEmpress);
            if (hasBlessingOfEmpress) {
                outPacket.encodeString(getBlessingOfEmpress());
            }
            outPacket.encodeByte(false); // ultimate explorer, deprecated
        }
        if (mask.isInMask(DBChar.Money)) {
            outPacket.encodeLong(getMoney());
        }

        if (mask.isInMask(DBChar.ItemSlotConsume) || mask.isInMask(DBChar.ExpConsumeItem)) {
            outPacket.encodeInt(getExpConsumeItems().size());
            for (ExpConsumeItem eci : getExpConsumeItems()) {
                eci.encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.ItemSlotConsume) || mask.isInMask(DBChar.ShopBuyLimit)) {
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
                outPacket.encodeLong(0);
            }
        }

        if (mask.isInMask(DBChar.InventorySize)) {
            outPacket.encodeByte(getEquipInventory().getSlots());
            outPacket.encodeByte(getConsumeInventory().getSlots());
            outPacket.encodeByte(getEtcInventory().getSlots());
            outPacket.encodeByte(getInstallInventory().getSlots());
            outPacket.encodeByte(getCashInventory().getSlots());
        }

        if (mask.isInMask(DBChar.EquipExtension)) {
            outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME)); // extra pendant
        }
        if (mask.isInMask(DBChar.ItemSlotEquip)) {
            boolean onlyEquipped = false;
            outPacket.encodeByte(onlyEquipped); // ?
            List<Item> equippedItems = new ArrayList<>(getEquippedInventory().getItems());
            equippedItems.sort(Comparator.comparingInt(Item::getBagIndex));
            // Normal equipped items
            for (Item item : equippedItems) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() > BodyPart.BPBase.getVal() && item.getBagIndex() < BodyPart.BPEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Cash equipped items
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.CBPBase.getVal() && item.getBagIndex() < BodyPart.CBPEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex() - 100);
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            if (!onlyEquipped) {
                // Equip inventory
                for (Item item : getEquipInventory().getItems()) {
                    Equip equip = (Equip) item;
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
                outPacket.encodeShort(0);
            }
            // NonBPEquip::Decode (10 inventory decodes)
            // Evan
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.EvanBase.getVal() && item.getBagIndex() < BodyPart.EvanEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Mech
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.MechBase.getVal() && item.getBagIndex() < BodyPart.MechEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Android
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.APBase.getVal() && item.getBagIndex() < BodyPart.APEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Angelic Buster
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.DUBase.getVal() && item.getBagIndex() < BodyPart.DUEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Bits
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.BitsBase.getVal() && item.getBagIndex() < BodyPart.BitsEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Zero
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.ZeroBase.getVal() && item.getBagIndex() < BodyPart.ZeroEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Maybe zero beta cash?
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.MBPBase.getVal() && item.getBagIndex() < BodyPart.MBPEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Arcane
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.AFBase.getVal() && item.getBagIndex() < BodyPart.AFEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Totems
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.TotemBase.getVal() && item.getBagIndex() < BodyPart.TotemEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);
            // Haku
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.HakuStart.getVal() && item.getBagIndex() < BodyPart.HakuEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);

            // VirtualEquipInventory::Decode (Android)
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                if (item.getBagIndex() >= BodyPart.MechBase.getVal() && item.getBagIndex() < BodyPart.MechEnd.getVal()) {
                    outPacket.encodeShort(equip.getBagIndex());
                    equip.encode(outPacket);
                }
            }
            outPacket.encodeShort(0);

            sizee = 0;
            outPacket.encodeInt(0);
//			outPacket.encodeInt(sizee);
//			for (int i = 0; i < sizee; i++) {
//				outPacket.encodeLong(0);
//				// sub_BE9F10
//				outPacket.encodeShort(0);
//				outPacket.encodeShort(0);
//				outPacket.encodeShort(0);
//				outPacket.encodeString("");
//				outPacket.encodeInt(0);
//				outPacket.encodeLong(0);
//			}
        }
        if (mask.isInMask(DBChar.ItemSlotInstall)) {
            outPacket.encodeShort(0); // 20001~20048
            outPacket.encodeShort(0); // 20049~20051
        }
        if (mask.isInMask(DBChar.ItemSlotConsume)) {
            for (Item item : getConsumeInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotInstall)) {
            for (Item item : getInstallInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotEtc)) {
            for (Item item : getEtcInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.ItemSlotCash)) {
            for (Item item : getCashInventory().getItems()) {
                outPacket.encodeByte(item.getBagIndex());
                item.encode(outPacket);
            }
            outPacket.encodeByte(0);
        }
        // BagDatas
        if (mask.isInMask(DBChar.ItemSlotConsume)) {
            // TODO
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotInstall)) {
            // TODO
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.ItemSlotEtc)) {
            // TODO
            outPacket.encodeInt(0);
        }
        // End bagdatas
        if (mask.isInMask(DBChar.CoreAura)) {
            int val = 0;
            outPacket.encodeInt(val);
            for (int i = 0; i < val; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
            }
        }
        // start new 188
        if (mask.isInMask(DBChar.Unk40000000)) { // Something to do with skills
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeLong(0); // 1st half is id, 2nd half level?
                outPacket.encodeLong(0); // time
            }
        }
        // end new 188
        if (mask.isInMask(DBChar.ItemPot)) {
            boolean hasItemPot = getItemPots() != null;
            outPacket.encodeByte(hasItemPot);
            if (hasItemPot) {
                for (int i = 0; i < getItemPots().size(); i++) {
                    getItemPots().get(i).encode(outPacket);
                    outPacket.encodeByte(i != getItemPots().size() - 1);
                }
            }
        }

        if (mask.isInMask(DBChar.SkillRecord)) {
            boolean encodeSkills = getSkills().size() > 0;
            outPacket.encodeByte(encodeSkills);
            if (encodeSkills) {
                Set<Skill> skills = getSkills();
                outPacket.encodeShort(skills.size());
                for (Skill skill : skills) {
                    outPacket.encodeInt(skill.getSkillId());
                    outPacket.encodeInt(skill.getCurrentLevel());
                    outPacket.encodeFT(FileTime.fromType(FileTime.Type.MAX_TIME));
                    if (SkillConstants.isSkillNeedMasterLevel(skill.getSkillId())) {
                        outPacket.encodeInt(skill.getMasterLevel());
                    }
                }
            } else {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeInt(0); // sValue
                }
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }

                short size3 = 0;
                outPacket.encodeShort(size3);
                for (int i = 0; i < size3; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeFT(new FileTime(0)); // pInfo
                }
                short size4 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }

                short size5 = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nTI
                    outPacket.encodeInt(0); // sValue
                }
                short size6 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nTI
                }
            }
            Set<LinkSkill> linkSkills = getLinkSkills();
            int size = linkSkills.size();
            outPacket.encodeShort(size);
            for (LinkSkill linkSkill : linkSkills) {
                outPacket.encodeInt(linkSkill.getLinkSkillID());
                outPacket.encodeShort(linkSkill.getLevel() - 1);
            }
            linkSkills = getAccount().getLinkSkills();
            outPacket.encodeInt(linkSkills.size());
            for (LinkSkill linkSkill : linkSkills) {
                outPacket.encodeInt(linkSkill.getOriginID());
                outPacket.encodeInt(linkSkill.getUsingID());
                outPacket.encodeInt(linkSkill.getLinkSkillID());
                outPacket.encodeShort(linkSkill.getLevel());
                outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME)); // ftLastAssigned
                outPacket.encodeInt(0); // count
            }
        }


        if (mask.isInMask(DBChar.SkillCooltime)) {
            long curTime = System.currentTimeMillis();
            Map<Integer, Long> cooltimes = getSkillCoolTimes();
            outPacket.encodeShort(cooltimes.size());
            cooltimes.forEach((key, value) -> {
                outPacket.encodeInt(key); // nSkillId
                outPacket.encodeInt((int) ((value - curTime) / 1000)); // nSkillCooltime
            });
        }

        if (mask.isInMask(DBChar.QuestRecord)) {
            // modified/deleted, not completed anyway
            boolean removeAllOldEntries = true;
            outPacket.encodeByte(removeAllOldEntries);
            short size = (short) getQuestManager().getQuestsInProgress().size();
            outPacket.encodeShort(size);
            for (Quest quest : getQuestManager().getQuestsInProgress()) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeString(quest.getQRValue());
            }
            if (!removeAllOldEntries) {
                // blacklisted quests
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i = 0; i < size2; i++) {
                    outPacket.encodeInt(0); // nQRKey
                }
            }
            size = 0;
            outPacket.encodeShort(size);
            // Not sure what this is for
            for (int i = 0; i < size; i++) {
                outPacket.encodeString("");
                outPacket.encodeString("");
            }
        }
        if (mask.isInMask(DBChar.QuestComplete)) {
            boolean removeAllOldEntries = true;
            outPacket.encodeByte(removeAllOldEntries);
            Set<Quest> completedQuests = getQuestManager().getCompletedQuests();
            outPacket.encodeShort(completedQuests.size());
            for (Quest quest : completedQuests) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeFT(quest.getCompletedTime()); // Timestamp of completion
            }
            if (!removeAllOldEntries) {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // nQRKey?
                }
            }
        }
        if (mask.isInMask(DBChar.MinigameRecord)) {
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                new MiniGameRecord().encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.CoupleRecord)) {
            //outPacket.encodeShort(0); // 0x400
            Triple<List<FriendshipRingRecord>, List<FriendshipRingRecord>, List<FriendshipRingRecord>> aRing = getRings(true);
            List<FriendshipRingRecord> cRing = aRing.getLeft();
            outPacket.encodeShort(cRing.size());
            for (FriendshipRingRecord ring : cRing) { // 33
                outPacket.encodeInt(ring.getPartnerId());
                outPacket.encodeString(ring.getFriendShipName(), 13);
                outPacket.encodeLong(ring.getFriendshipItemSN());
                outPacket.encodeLong(ring.getFriendshipPairItemSN());
            }

            List<FriendshipRingRecord> fRing = aRing.getMiddle();
            outPacket.encodeShort(fRing.size());
            for (FriendshipRingRecord ring : fRing) { // 37
                outPacket.encodeInt(ring.getPartnerId());
                outPacket.encodeString(ring.getFriendShipName(), 13);
                outPacket.encodeLong(ring.getFriendshipItemSN());
                outPacket.encodeLong(ring.getFriendshipPairItemSN());
                outPacket.encodeInt(ring.getItemID());
            }
            int marriageSize = 0;
            outPacket.encodeShort(marriageSize);
            for (int i = 0; i < marriageSize; i++) {
                new MarriageRecord().encode(outPacket);
            }
        }

        if (mask.isInMask(DBChar.MapTransfer)) {
            for (int i = 0; i < 5; i++) {
                outPacket.encodeInt(999999999); // Teleport Rock
            }
            for (int i = 0; i < 10; i++) {
                outPacket.encodeInt(999999999); // Vip Teleport Rock
            }
            for (int i = 0; i < 13; i++) {
                outPacket.encodeInt(999999999); // Premium Vip Teleport Rock
            }
            for (int i = 0; i < 13; i++) {
                outPacket.encodeInt(999999999); // Hyper Teleport Rock
            }
        }


        if (mask.isInMask(DBChar.FamiliarCodex)) {
            outPacket.encodeInt(104512);
            outPacket.encodeShort(2000);
            outPacket.encodeShort(30);
            outPacket.encodeInt(getFamiliars().size());
            outPacket.encodeInt(6);
            outPacket.encodeByte(5);
            outPacket.encodeShort(13929);
            outPacket.encodeShort(54720);

            //Equipped Familiars?
            outPacket.encodeInt(-1);
            outPacket.encodeInt(-1);
            outPacket.encodeInt(-1);

            outPacket.encodeByte(-1);

            for (int i = 0; i < GameConstants.FAMILIAR_BADGE_SLOTS; i++) {
                outPacket.encodeByte(-1);
            }
        }

        if (mask.isInMask(DBChar.Familiar)) { //v213
            boolean fam = false;
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            if (fam) {
                for (Familiar familiar : getFamiliars()) {
                    familiar.encode(outPacket, this);
                }
            } else {
                for (Familiar familiar : getFamiliars()) {
                    outPacket.encodeInt(0);
                }
            }
        }
        if (mask.isInMask(DBChar.FamiliarCodex)) {
            boolean bool = false;
            int size = 0;
            outPacket.encodeByte(bool);
            outPacket.encodeInt(size);
            if (bool) {
                for (int i = 0; i < size; i++) {
                    outPacket.encodeArr(new byte[22]);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
            }
        }


        if (mask.isInMask(DBChar.QuestRecordEx)) {
            outPacket.encodeShort(getQuestManager().getEx().size());
            for (Quest quest : getQuestManager().getEx()) {
                outPacket.encodeInt(quest.getQRKey());
                outPacket.encodeString(quest.getQRValue());
            }
        }

        if (mask.isInMask(DBChar.Avatar)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); // sValue
                new AvatarLook().encode(outPacket);
            }
        }

        if (mask.isInMask(DBChar.ActiveDamageSkin)) { // new 196 wtf?
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeShort(0);
            }
        }

        boolean bool = true; // bNxRecordAccessAuth
        outPacket.encodeByte(bool); // new 196 //v214

        if (bool && mask.isInMask(DBChar.Unk10000000000)) { //v214
            outPacket.encodeInt(0);
        }
        // New 188
        if (mask.isInMask(DBChar.Unk100000000000)) { //v214
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }

        // End 188
        if (mask.isInMask(DBChar.WildHunterInfo)) {
            if (JobConstants.isWildHunter(getAvatarData().getCharacterStat().getJob())) {
                // could make WildHunterInfo an entity for this
                QuestManager qm = getQuestManager();
                WildHunterInfo whi = getWildHunterInfo();
                Quest chosenQuest = qm.getQuestById(QuestConstants.WILD_HUNTER_JAGUAR_CHOSEN_ID);
                int toID = -1;
                if (chosenQuest == null) {
                    chosenQuest = new Quest(QuestConstants.WILD_HUNTER_JAGUAR_CHOSEN_ID, QuestStatus.Started);
                    qm.addQuest(chosenQuest);
                } else if (Util.isNumber(chosenQuest.getQRValue())){
                    toID = Integer.parseInt(chosenQuest.getQRValue());
                }
                whi.setIdx((byte) toID);
                whi.setRidingType((byte) toID);
                chosenQuest.setQrValue("" + toID);
                getWildHunterInfo().encode(outPacket); // GW_WildHunterInfo::Decode
            }
        }
        if (mask.isInMask(DBChar.ZeroInfo)) {
            if (JobConstants.isZero(getAvatarData().getCharacterStat().getJob())) {
                if (getZeroInfo() == null) {
                    initZeroInfo();
                }
                getZeroInfo().encode(outPacket, this); // ZeroInfo::Decode
            }
        }
        if (mask.isInMask(DBChar.ShopBuyLimit)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                // Encode shop buy limit

            }
        }

        // new 200
        if (mask.isInMask(DBChar.ActiveDamageSkin)) {
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                int size2 = 0;
                outPacket.encodeShort(size2);
                int val = 0;
                outPacket.encodeInt(val);
                if (val > 0) {
                    for (int j = 0; j < size2; j++) {
                        // sub
                        outPacket.encodeInt(0);
                        outPacket.encodeShort(0);
                        outPacket.encodeInt(0);
                        outPacket.encodeShort(0);
                        outPacket.encodeLong(0);
                    }
                }

            }
        }
        if (mask.isInMask(DBChar.StolenSkills)) {
            if (JobConstants.isPhantom(getAvatarData().getCharacterStat().getJob())) {
                for (int i = 0; i < 15; i++) {
                    StolenSkill stolenSkill = getStolenSkillByPosition(i);
                    outPacket.encodeInt(stolenSkill == null ? 0 : stolenSkill.getSkillid());
                }
            } else {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);

                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.ChosenSkills)) {
            if (JobConstants.isPhantom(getAvatarData().getCharacterStat().getJob())) {
                for (int i = 1; i <= 5; i++) { //Shifted by +1 to accomodate the Skill Management Tabs
                    ChosenSkill chosenSkill = getChosenSkillByPosition(i);
                    outPacket.encodeInt(chosenSkill == null
                            ? 0
                            : isChosenSkillInStolenSkillList(chosenSkill.getSkillId())
                            ? chosenSkill.getSkillId()
                            : 0
                    );
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    outPacket.encodeInt(0);
                }
            }
        }

        if (mask.isInMask(DBChar.CharacterPotential)) { // Character potential
            outPacket.encodeShort(getPotentials().size());
            for (CharacterPotential cp : getPotentials()) {
                cp.encode(outPacket);
            }
        }
        if (mask.isInMask(DBChar.SoulCollection)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0); //
                outPacket.encodeInt(0); //
            }
        }
        sizee = 0;
        outPacket.encodeInt(sizee);
        for (int i = 0; i < sizee; i++) {
            outPacket.encodeString("");
            // sub_73A1A0
            outPacket.encodeInt(0);
            outPacket.encodeString("");
            int size = 0;
            outPacket.encodeInt(size);
            for (int j = 0; j < size; j++) {
                outPacket.encodeByte(0);
            }
        }
        outPacket.encodeByte(0); // idk

        if (mask.isInMask(DBChar.Character)) {
            outPacket.encodeInt(0); // honor level, deprecated
            outPacket.encodeInt(getHonorExp()); // honor exp
        }
        if (mask.isInMask(DBChar.Unk200000000)) {
            boolean shouldIEncodeThis = false;
            outPacket.encodeByte(shouldIEncodeThis);
            if (shouldIEncodeThis) {
                short size = 0;
                outPacket.encodeShort(size);
                for (int i = 0; i < size; i++) {
                    short category = 0;
                    outPacket.encodeShort(category);
                    short size2 = 0;
                    outPacket.encodeShort(size2);
                    for (int i2 = 0; i2 < size2; i2++) {
                        outPacket.encodeInt(0); // nItemId
                        outPacket.encodeInt(0); // nCount
                    }
                }
            } else {
                short size2 = 0;
                outPacket.encodeShort(size2);
                for (int i2 = 0; i2 < size2; i2++) {
                    outPacket.encodeShort(1); // nCategory
                    outPacket.encodeInt(1302000); // nItemId
                    outPacket.encodeInt(3); // nCount
                }

            }
        }
        if (mask.isInMask(DBChar.ReturnEffectInfo)) {
//            getReturnEffectInfo().encode(outPacket); // ReturnEffectInfo::Decode
            outPacket.encodeByte(0);
        }
        if (mask.isInMask(DBChar.DressUpInfo)) {
            new DressUpInfo().encode(outPacket); // GW_DressUpInfo::Decode
        }
        if (mask.isInMask(DBChar.ActiveDamageSkin)) {
            outPacket.encodeInt(getDamageSkin().getDamageSkinID());
            outPacket.encodeInt(getPremiumDamageSkin().getDamageSkinID());
            outPacket.encodeLong(0); // ftLastChanged?
            outPacket.encodeString(getActiveDamageSkin().getDescription());
            outPacket.encodeInt(getActiveDamageSkin().getDamageSkinID());
        }
        if (mask.isInMask(DBChar.CoreInfo)) {
            // GW_Core
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(-1); // nPos
                // sub
                outPacket.encodeInt(-1); // nCoreID
                outPacket.encodeInt(-1); // nLeftCount
            }

            size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(-1); // nPos
                outPacket.encodeInt(-1); // nCoreID
                outPacket.encodeInt(-1); // nLeftCount
            }
        }

        if (mask.isInMask(DBChar.FarmPotential)) {
            new FarmPotential().encode(outPacket); // FARM_POTENTIAL::Decode
        }
        if (mask.isInMask(DBChar.FarmUserInfo)) {
            new FarmUserInfo().encode(outPacket); // FarmUserInfo::Decode
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        if (mask.isInMask(DBChar.MemorialCubeInfo)) {
            if (getMemorialCubeInfo() != null) {
                getMemorialCubeInfo().encode(outPacket);
            } else {
                new MemorialCubeInfo().encode(outPacket); // MemorialCubeInfo::Decode
            }
        }
        // new 200
        if (mask.isInMask(DBChar.MemorialFlameInfo)) {
            outPacket.encodeLong(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }

        if (mask.isInMask(DBChar.LikePoint)) {
            new LikePoint().encode(outPacket);
        }
        if (mask.isInMask(DBChar.RunnerGameRecord)) {
            new RunnerGameRecord().encode(outPacket); // RunnerGameRecord::Decode
        }
        outPacket.encodeInt(0); // no idea where these came from (200)
        outPacket.encodeInt(0);
        if (mask.isInMask(DBChar.Unk8000000000000)) { // new 196
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(-1);
                outPacket.encodeByte(-1);
                outPacket.encodeByte(-1);
                outPacket.encodeByte(-1);
            }
            outPacket.encodeInt(-1);
            outPacket.encodeLong(-1);
        }
        short sizeO = 0; // some thing that opens on login?
        outPacket.encodeShort(sizeO);
        for (int i = 0; i < sizeO; i++) {
            outPacket.encodeInt(i);
            outPacket.encodeString("Effect/Direction11.img/effect/meet/frame0/0");
        }
        if (mask.isInMask(DBChar.MonsterCollection)) {
            Set<MonsterCollectionExploration> mces = getAccount().getMonsterCollection().getMonsterCollectionExplorations();
            outPacket.encodeShort(mces.size());
            for (MonsterCollectionExploration mce : mces) {
                outPacket.encodeInt(mce.getPosition());
                outPacket.encodeString(mce.getValue(true));
            }
        }
        boolean farmOnline = false;
        outPacket.encodeByte(farmOnline);
        int sizeInt = 0;
        // CharacterData::DecodeTextEquipInfo
        outPacket.encodeInt(sizeInt);
        for (int i = 0; i < sizeInt; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeString("");
        }
        if (mask.isInMask(DBChar.Unk10000000000000)) { // new 196
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.VMatrix)) { // new 188
            List<MatrixRecord> matrixRecords = getSortedMatrixRecords();
            outPacket.encodeInt(matrixRecords.size());
            for (MatrixRecord mr : matrixRecords) {
                outPacket.encode(mr);
            }
            // next part is 196

            List<MatrixRecord> activeRecords = matrixRecords.stream().filter(MatrixRecord::isActive).collect(Collectors.toList());
            outPacket.encodeInt(activeRecords.size());
            for (MatrixRecord mr : activeRecords) {
                outPacket.encodeInt(matrixRecords.indexOf(mr));
                outPacket.encodeInt(mr.getPosition()); // slotPos
                outPacket.encodeInt(0); // nLevel
                outPacket.encodeByte(0); // bHide
            }
        }

        if (mask.isInMask(DBChar.Achievement)) { // new 188 //v214
            outPacket.encodeInt(20);
            outPacket.encodeInt(21);

            // sub v214
            outPacket.encodeInt(22);
            outPacket.encodeInt(23);
            outPacket.encodeInt(24);
            outPacket.encodeLong(1379001);
            // || 214

            outPacket.encodeLong(1379001);
            outPacket.encodeLong(27);

            int size = 1;
            outPacket.encodeInt(size + 1);
            // achievements
            // iter 1, just to open the UI
            //   outPacket.encodeLong(1); // Removed?
            outPacket.encodeInt(1); // achievement id
            outPacket.encodeByte(-1);
            outPacket.encodeByte(2);
            outPacket.encodeFT(FileTime.currentTime());
            outPacket.encodeString("");
            // -->> v214
            // iter 2
            for (int i = 0; i < size; i++) {
                //    outPacket.encodeLong(563003); // id 3 digit, sub-mission 3 digit
                outPacket.encodeInt(563); // achievement id
                outPacket.encodeByte(1); // sub-mission
                outPacket.encodeByte(2); // achievement state
                outPacket.encodeFT(FileTime.currentTime()); // ftCompleteTime
                outPacket.encodeString("rune_stone_use_result_success=1");
            }

            size = 1;
            outPacket.encodeInt(size);
            // medals
            for (int i = 0; i < size; i++) {
                //   outPacket.encodeInt(2); // id
                outPacket.encodeInt(6); // num
                outPacket.encodeByte(1); // state (0 = not unlocked, 1 = achieved, 2 = current selected for insignia)
                outPacket.encodeFT(FileTime.currentTime()); // unlock time
            }
        }

        if (mask.isInMask(DBChar.ItemSlotEtc)) { // new 196
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeLong(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);

            }
        }
        // new 203
        if (mask.isInMask(DBChar.Unk200000000000000)) {
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            // hardcoded 5
            for (int i = 0; i < 5; i++) {
                bool = false;
                outPacket.encodeByte(bool);
                if (bool) {
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);

                    outPacket.encodeInt(0);

                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);

                }
            }
        }
        //new v209
        if (mask.isInMask(DBChar.Unk200000000000000)) {
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            // hardcoded 5
            for (int i = 0; i < 5; i++) {
                bool = false;
                outPacket.encodeByte(bool);
                if (bool) {
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);

                    outPacket.encodeInt(0);

                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);

                }
            }
        }
        if (mask.isInMask(DBChar.Unk8000000000000000)) {
            bool = false;
            outPacket.encodeByte(false);
            if (bool) {
                // sub_905BD0
                outPacket.encodeByte(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
            }
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                // sub_9059E0
                outPacket.encodeByte(0);
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
            size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                // sub_905D50
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeLong(0);
            }
        }
        if (mask.isInMask(DBChar.Familiar)) {
            int size = 0;
            outPacket.encodeInt(size);

            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
            }

            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0);
            }
            outPacket.encodeShort(0);

            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(0);
            }

            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeString("");
            }
        }
        if (mask.isInMask(DBChar.Unk800000000000)) {
            outPacket.encodeByte(0);
        }

        if (mask.isInMask(DBChar.Unk400000)) {
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                // sub
                outPacket.encodeShort(0);
                outPacket.encodeShort(0);
            }
            size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                // sub
                outPacket.encodeShort(0);
                outPacket.encodeInt(0);
            }
        }
        if (mask.isInMask(DBChar.Unk1000000000000000)) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);

            for (int i = 0; i < 6; i++) {
                outPacket.encodeInt(0);
            }

            for (int i = 0; i < 4; i++) {
                outPacket.encodeInt(0);
            }

            outPacket.encodeLong(0);
            outPacket.encodeByte(0);

            outPacket.encodeByte(0);
        }

        if (mask.isInMask(DBChar.MemorialFlameInfo)) {
            short size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeShort(233);
                outPacket.encodeShort(543);
            }
        }

        if (mask.isInMask(DBChar.RedLeafInfo)) {
            // red leaf information
            outPacket.encodeInt(getAccount().getId());
            outPacket.encodeInt(getId());
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeArr(new byte[32]); // red leaf info structure probably
        }

        // new 188 all the way from here until the end of the function
        if (mask.isInMask(DBChar.ActiveDamageSkin)) { //DBChar.Avatar ?? correct according to IDA ?
            bool = false;
            outPacket.encodeByte(bool);
            if (bool) {
                new AvatarLook().encode(outPacket);
            }
        }

        if (mask.isInMask(DBChar.ActiveDamageSkin)) { //v214 Correct
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            int size = 0;
            outPacket.encodeShort(size);
            for (int i = 0; i < size; i++) {
                // sub_8EE8D0
                outPacket.encodeInt(13713);
                outPacket.encodeInt(12381);
                outPacket.encodeString("aaaaa", 13);
                outPacket.encodeInt(3333);
            }
            size = 0;
            outPacket.encodeShort(0);
            for (int i = 0; i < size; i++) {
                // sub_8EE8D0
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("", 13);
                outPacket.encodeInt(0);
            }
        }
    }


    public void dropItem(int itemId, int x, int y) {
        Field field = getField();
        Drop drop = new Drop(field.getNewObjectID());
        drop.setItem(ItemData.getItemDeepCopy(itemId));
        Position position = new Position(x, y);
        drop.setPosition(position);
        field.drop(drop, position, true);
    }

    public List<MatrixRecord> getSortedMatrixRecords() {
        return getMatrixRecords().stream()
                .sorted(Comparator.comparingLong(MatrixRecord::getId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Char)) {
            return false;
        }
        Char chr = (Char) other;
        return chr.getId() == getId() && chr.getName().equals(getName());
    }

    public BeautyAlbum getStyleBySlotId(int slotId) {
        return getBeautyAlbum().stream().filter(style -> style.getSlotID() == slotId).findFirst().orElse(null);
    }


    public void addStyleToBeautyAlbum(BeautyAlbum beautyAlbum) {
        getBeautyAlbum().add(beautyAlbum);
    }

    public void removeStyleToBeautyAlbum(BeautyAlbum beautyAlbum) {
        getBeautyAlbum().remove(beautyAlbum);
    }

    public List<BeautyAlbum> getBeautyAlbum() {
        return beautyAlbum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName());
    }

    private String getBlessingOfEmpress() {
        return blessingOfEmpress;
    }

    public void setBlessingOfEmpress(String blessingOfEmpress) {
        this.blessingOfEmpress = blessingOfEmpress;
    }

    private String getBlessingOfFairy() {
        return blessingOfFairy;
    }

    public void setBlessingOfFairy(String blessingOfFairy) {
        this.blessingOfFairy = blessingOfFairy;
    }

    public void setCombatOrders(int combatOrders) {
        this.combatOrders = combatOrders;
    }

    public int getCombatOrders() {
        return combatOrders;
    }

    public QuestManager getQuestManager() {
        if (questManager.getChr() == null) {
            questManager.setChr(this);
        }
        return questManager;
    }

    public void setQuests(QuestManager questManager) {
        this.questManager = questManager;
    }

    public List<ItemPot> getItemPots() {
        return null;
    }

    public void setItemPots(List<ItemPot> itemPots) {
        this.itemPots = itemPots;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<FriendRecord> getFriendRecords() {
        return friendRecords;
    }

    public void setFriendRecords(List<FriendRecord> friendRecords) {
        this.friendRecords = friendRecords;
    }

    public long getMoney() {
        return getAvatarData().getCharacterStat().getMoney();
    }

    public List<ExpConsumeItem> getExpConsumeItems() {
        return expConsumeItems;
    }

    public void setExpConsumeItems(List<ExpConsumeItem> expConsumeItems) {
        this.expConsumeItems = expConsumeItems;
    }

    public List<MonsterBattleMobInfo> getMonsterBattleMobInfos() {
        return monsterBattleMobInfos;
    }

    public void setMonsterBattleMobInfos(List<MonsterBattleMobInfo> monsterBattleMobInfos) {
        this.monsterBattleMobInfos = monsterBattleMobInfos;
    }

    public MonsterBattleLadder getMonsterBattleLadder() {
        return monsterBattleLadder;
    }

    public void setMonsterBattleLadder(MonsterBattleLadder monsterBattleLadder) {
        this.monsterBattleLadder = monsterBattleLadder;
    }

    public MonsterBattleRankInfo getMonsterBattleRankInfo() {
        return monsterBattleRankInfo;
    }

    public void setMonsterBattleRankInfo(MonsterBattleRankInfo monsterBattleRankInfo) {
        this.monsterBattleRankInfo = monsterBattleRankInfo;
    }

    public List<Inventory> getInventories() {
        return new ArrayList<>(Arrays.asList(getEquippedInventory(), getEquipInventory(),
                getConsumeInventory(), getEtcInventory(), getInstallInventory(), getCashInventory()));
    }

    public Inventory getInventoryByType(InvType invType) {
        switch (invType) {
            case EQUIPPED:
                return getEquippedInventory();
            case EQUIP:
                return getEquipInventory();
            case CONSUME:
                return getConsumeInventory();
            case ETC:
                return getEtcInventory();
            case INSTALL:
                return getInstallInventory();
            case CASH:
                return getCashInventory();
            case HAIR:
                return getHairInventory();
            case FACE:
                return getFaceInventory();
            default:
                return null;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getFieldID() {
        return (int) getAvatarData().getCharacterStat().getPosMap();
    }

    public void setFieldID(int fieldID) {
        getAvatarData().getCharacterStat().setPosMap(fieldID);
    }



    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setField(Field field) {
        this.field = field;
        setFieldID(field.getId());
    }

    public Triple<List<FriendshipRingRecord>, List<FriendshipRingRecord>, List<FriendshipRingRecord>> getRings(boolean equip) {

        List<FriendshipRingRecord> crings = new ArrayList<>(), frings = new ArrayList<>(), mrings = new ArrayList<>();
        FriendshipRingRecord ring;
        for (Item ite : getEquippedInventory().getItems()) {
            Equip item = (Equip) ite;
            if (item.getRing() != null) {
                ring = item.getRing();
                ring.setEquipped(true);
                if (ItemConstants.isEffectRing(item.getItemId())) {
                    if (equip) {
                        if (ItemConstants.isCrushRing(item.getItemId())) {
                            crings.add(ring);
                        } else if (ItemConstants.isFriendshipRing(item.getItemId())) {
                            frings.add(ring);
                        } else if (ItemConstants.isWeddingRing(item.getItemId())) {
                            mrings.add(ring);
                        }
                    } else {
                        if (crings.isEmpty() && ItemConstants.isCrushRing(item.getItemId())) {
                            crings.add(ring);
                        } else if (frings.isEmpty() && ItemConstants.isFriendshipRing(item.getItemId())) {
                            frings.add(ring);
                        } else if (mrings.isEmpty() && ItemConstants.isWeddingRing(item.getItemId())) {
                            mrings.add(ring);
                        } //for 3rd person the actual slot doesnt matter, so we'll use this to have both shirt/ring same?
                        //however there seems to be something else behind this, will have to sniff someone with shirt and ring, or more conveniently 3-4 of those
                    }
                }
            }
        }
        if (equip) {
            for (Item ite : getEquipInventory().getItems()) {
                Equip item = (Equip) ite;
                if (item.getRing() != null && ItemConstants.isCrushRing(item.getItemId())) {
                    ring = item.getRing();
                    ring.setEquipped(false);
                    if (ItemConstants.isFriendshipRing(item.getItemId())) {
                        frings.add(ring);
                    } else if (ItemConstants.isCrushRing(item.getItemId())) {
                        crings.add(ring);
                    } else if (ItemConstants.isWeddingRing(item.getItemId())) {
                        mrings.add(ring);
                    }
                }
            }
        }
        Collections.sort(frings, new FriendshipRingRecord.RingComparator());
        Collections.sort(crings, new FriendshipRingRecord.RingComparator());
        Collections.sort(mrings, new FriendshipRingRecord.RingComparator());
        return new Triple<>(crings, frings, mrings);
    }


    public Field getField() {
        return field;
    }


    /**
     * Sets the job of this Char with a given id. Does nothing if the id is invalid.
     * If it is valid, will set this Char's job, add all Skills that the job should have by default,
     * and sends the info to the client.
     *
     * @param id
     */
    public void setJob(int id) {
        JobConstants.JobEnum job = JobConstants.JobEnum.getJobById((short) id);
        if (job == null) {
            return;
        }
        getAvatarData().getCharacterStat().setJob(id);
        Job handler = JobManager.getJobById((short) id, this);
        if (!handler.getClass().equals(getJobHandler().getClass())) {
            // only change handlers if the job path changes
            setJobHandler(handler);
        }
        List<Skill> skills = SkillData.getSkillsByJob((short) id);
        skills.forEach(s -> addSkill(s, true));
        getClient().write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
        notifyChanges();
        if (id == JobConstants.JobEnum.EVAN_1.getJobId() || id == JobConstants.JobEnum.EVAN_2.getJobId() || id == JobConstants.JobEnum.EVAN_3.getJobId() || id == JobConstants.JobEnum.EVAN_4.getJobId()) {
            getField().broadcastPacket(DragonPool.createDragon(getDragon()));
        }
    }

    public short getJob() {
        return getAvatarData().getCharacterStat().getJob();
    }

    /**
     * Sets the SP to the current job level.
     *
     * @param num The new SP amount.
     */
    public void setSpToCurrentJob(int num) {
        if (JobConstants.isExtendSpJob(getJob())) {
            byte jobLevel = (byte) JobConstants.getJobLevel(getJob());
            getAvatarData().getCharacterStat().getExtendSP().setSpToJobLevel(jobLevel, num);
        } else {
            getAvatarData().getCharacterStat().setSp(num);
        }
    }

    /**
     * Sets the SP to the job level according to the current level.
     *
     * @param num The amount of SP to add
     */
    public void addSpToJobByCurrentLevel(int num) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        if (JobConstants.isExtendSpJob(getJob())) {
            byte jobLevel = (byte) JobConstants.getJobLevelByCharLevel(getJob(), getLevel());
            num += cs.getExtendSP().getSpByJobLevel(jobLevel);
            getAvatarData().getCharacterStat().getExtendSP().setSpToJobLevel(jobLevel, num);
        } else {
            num += cs.getSp();
            getAvatarData().getCharacterStat().setSp(num);
        }
    }


    public void addSpToJobByCurrentJob(int num) {
        byte jobLevel = (byte) JobConstants.getJobLevel(getJob());
        int currentSP = getAvatarData().getCharacterStat().getExtendSP().getSpByJobLevel(jobLevel);
        setSpToCurrentJob(currentSP + num);

        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.sp, getAvatarData().getCharacterStat().getExtendSP());
        write(WvsContext.statChanged(stats));
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Adds a {@link Skill} to this Char. Changes the old Skill if the Char already has a Skill
     * with the same id. Removes the skill if the given skill's id is 0.
     *
     * @param skill The Skill this Char should get.
     */
    public void addSkill(Skill skill) {
        addSkill(skill, false);
    }

    /**
     * Adds a {@link Skill} to this Char. Changes the old Skill if the Char already has a Skill
     * with the same id. Removes the skill if the given skill's id is 0.
     *
     * @param skill                The Skill this Char should get.
     * @param addRegardlessOfLevel if this is true, the skill will not be removed from the char, even if the cur level
     *                             of the given skill is 0.
     */
    public void addSkill(Skill skill, boolean addRegardlessOfLevel) {
        if (getId() == 0) {
            log.warn("Did not add skill " + skill + ", as the current Char does not exist in the DB.");
            return;
        }
        if (!addRegardlessOfLevel && skill.getCurrentLevel() == 0) {
            removeSkill(skill.getSkillId());
            return;
        }
        skill.setCharId(getId());
        boolean isPassive = SkillConstants.isPassiveSkill(skill.getSkillId());
        boolean isChanged;
        if (getSkills().stream().noneMatch(s -> s.getSkillId() == skill.getSkillId())) {
            getSkills().add(skill);
            isChanged = true;
        } else {
            Skill oldSkill = getSkill(skill.getSkillId());
            isChanged = oldSkill.getCurrentLevel() != skill.getCurrentLevel();
            if (isPassive && isChanged) {
                removeFromBaseStatCache(oldSkill);
            }
            oldSkill.setCurrentLevel(skill.getCurrentLevel());
            oldSkill.setMasterLevel(skill.getMasterLevel());
        }
        // Change cache accordingly
        if (isPassive && isChanged) {
            addToBaseStatCache(skill);
        }
    }

    /**
     * Removes a Skill from this Char.
     *
     * @param skillID the id of the skill that should be removed
     */
    public void removeSkill(int skillID) {
        Skill skill = Util.findWithPred(getSkills(), s -> s.getSkillId() == skillID);
        if (skill != null) {
            if (SkillConstants.isPassiveSkill(skillID)) {
                removeFromBaseStatCache(skill);
            }
            getSkills().remove(skill);
        }
        checkPsdWTBonuses();
    }

    /**
     * Removes a Skill from this Char.
     * Sends change skill record to remove the skill from the client.
     *
     * @param skillID the id of the skill that should be removed
     */
    public void removeSkillAndSendPacket(int skillID) {
        Skill skill = getSkill(skillID);
        if (skill != null) {
            removeSkill(skillID);
            skill.setCurrentLevel(-1);
            skill.setMasterLevel(-1);
            write(WvsContext.changeSkillRecordResult(Collections.singletonList(skill), true, false, false, false));
        }
    }

    /**
     * Initializes the BaseStat cache, by going through all the needed passive stat changers.
     */
    public void initBaseStats() {
        getBaseStats().clear();
        Map<BaseStat, Long> stats = getBaseStats();
        stats.put(BaseStat.cr, 5L);
        stats.put(BaseStat.crDmg, 0L);
        stats.put(BaseStat.pdd, 9L);
        stats.put(BaseStat.mdd, 9L);
        stats.put(BaseStat.acc, 11L);
        stats.put(BaseStat.eva, 8L);
        stats.put(BaseStat.buffTimeR, 100L);
        stats.put(BaseStat.dropR, 100L);
        stats.put(BaseStat.mesoR, 100L);
        stats.put(BaseStat.costmpR, 100L);
        getSkills().stream().filter(skill -> SkillConstants.isPassiveSkill_NoPsdSkillsCheck(skill.getSkillId())).
                forEach(this::addToBaseStatCache);
        checkPsdWTBonuses();
    }

    /**
     * Adds a Skill's info to the current base stat cache.
     *
     * @param skill The skill to add
     */
    public void addToBaseStatCache(Skill skill) {
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
//        chatMessage("[addToBaseStat] id: %s", Integer.toString(si.getSkillId()));
        if (SkillConstants.isPassiveSkill(skill.getSkillId())) {
            Map<BaseStat, Integer> stats = si.getBaseStatValues(this, skill.getCurrentLevel());
            stats.forEach(this::addBaseStat);
        }
        if (si.isPsd() && si.getSkillStatInfo().containsKey(SkillStat.coolTimeR)) {
            for (int psdSkill : si.getPsdSkills()) {
                getHyperPsdSkillsCooltimeR().put(psdSkill, si.getValue(SkillStat.coolTimeR, 1));
            }
        }
        checkPsdWTBonuses();
    }

    /**
     * Adds the bonus for the specified WeaponType of the psdWT Skill.
     *
     * @param skill The skill's bonuses to add
     * @param wt    The skill's weapon Type bonuses to check for.
     */
    public void addPsdWTToBaseStatCache(Skill skill, WeaponType wt) {
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Map<SkillStat, Double> ssMap = si.getSkillStatsByWT(wt);
        Map<BaseStat, Integer> stats = new HashMap<>();
        ssMap.forEach((k, v) -> {
            {
                Tuple<BaseStat, Integer> bs = si.getBaseStatValue(k, this, v.intValue());
                stats.put(bs.getLeft(), bs.getRight());
            }
        });
        stats.forEach(this::addBaseStat);
    }

    /**
     * Removes a Skill's info from the current base stat cache.
     *
     * @param skill The skill to remove
     */
    public void removeFromBaseStatCache(Skill skill) {
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Map<BaseStat, Integer> stats = si.getBaseStatValues(this, skill.getCurrentLevel());
        stats.forEach(this::removeBaseStat);
    }

    /**
     * Removes all bonuses from every Weapon Type in psdWT skills
     *
     * @param skill
     */
    public void removePsdWTFromBaseStatCache(Skill skill) {
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Map<BaseStat, Integer> stats = new HashMap<>();

        for (Map.Entry<SkillStat, Double> entry : si.getSkillStatsByWT(getPsdWTBonus().get(skill)).entrySet()) {
            SkillStat ss = entry.getKey();
            int value = entry.getValue().intValue();
            Tuple<BaseStat, Integer> bs = si.getBaseStatValue(ss, this, value);
            stats.put(bs.getLeft(), bs.getRight());
        }
        stats.forEach(this::removeBaseStat);
    }

    /**
     * Returns whether or not this Char has a {@link Skill} with a given id.
     *
     * @param id The id of the Skill.
     * @return Whether or not this Char has a Skill with the given id.
     */
    public boolean hasSkill(int id) {
        return getSkills().stream().anyMatch(s -> s.getSkillId() == id) && getSkill(id, false).getCurrentLevel() > 0;
    }

    public void checkPsdWTBonuses() {
        Equip weapon = (Equip) getEquippedInventory().getItemBySlot(BodyPart.Weapon.getVal());
        // Not wearing a weapon
        if (weapon == null) {
            for (Skill psdWTSkill : getPsdWTSkills()) {
                SkillInfo si = SkillData.getSkillInfoById(psdWTSkill.getSkillId());
                if (si == null) {
                    continue;
                }
                if (getPsdWTBonus().containsKey(psdWTSkill)) {
                    removePsdWTFromBaseStatCache(psdWTSkill);
                    getPsdWTBonus().remove(psdWTSkill);
                }
            }
            return;
        }
        WeaponType wt = ItemConstants.getWeaponType(weapon.getItemId());
        for (Skill psdWTSkill : getPsdWTSkills()) {
            SkillInfo si = SkillData.getSkillInfoById(psdWTSkill.getSkillId());
            if (si == null) {
                continue;
            }

            // if chr already has the bonus from skill&WT
            if (getPsdWTBonus().containsKey(psdWTSkill) && getPsdWTBonus().get(psdWTSkill).equals(wt)) {
                continue;
            }

            if (getPsdWTBonus().containsKey(psdWTSkill)) {
                // given chr already has the bonus
                removePsdWTFromBaseStatCache(psdWTSkill);
                getPsdWTBonus().remove(psdWTSkill);
            }
            if (!getPsdWTBonus().containsKey(psdWTSkill) || !getPsdWTBonus().get(psdWTSkill).equals(wt)) {
                // given chr does not have the bonus from that skill, or does not have the bonus from that weaponType
                addPsdWTToBaseStatCache(psdWTSkill, wt);
                getPsdWTBonus().put(psdWTSkill, wt);
            }
        }

        // Check if user gains a bonus from a removed Skill,  if so  remove the bonus
        getPsdWTBonus().keySet().stream().filter(s -> !hasSkill(s.getSkillId())).forEach(this::removePsdWTFromBaseStatCache);
        getPsdWTBonus().keySet().removeIf(s -> !hasSkill(s.getSkillId()));
    }

    public List<Skill> getPsdWTSkills() {
        return getSkills().stream().filter(s -> SkillData.getSkillInfoById(s.getSkillId()) != null && SkillData.getSkillInfoById(s.getSkillId()).isPsdWTSkill()).collect(Collectors.toList());
    }

    public Map<Skill, WeaponType> getPsdWTBonus() {
        return psdWTBonus;
    }

    public void setPsdWTBonus(Map<Skill, WeaponType> psdWTBonus) {
        this.psdWTBonus = psdWTBonus;
    }

    /**
     * Gets a {@link Skill} of this Char with a given id.
     *
     * @param id The id of the requested Skill.
     * @return The Skill corresponding to the given id of this Char, or null if there is none.
     */
    public Skill getSkill(int id) {
        // TODO: grab original if it's a "linked skill", like WH's 2 different Wild Arrow Blast
        return getSkill(id, false);
    }

    /**
     * Gets a {@link Skill} with a given ID. If <code>createIfNull</code> is true, creates the Skill
     * if it doesn't exist yet.
     * If it is false, will return null if this Char does not have the given Skill.
     *
     * @param id           The id of the requested Skill.
     * @param createIfNull Whether or not this method should create the Skill if it doesn't exist.
     * @return The Skill that the Char has, or <code>null</code> if there is no such skill and
     * <code>createIfNull</code> is false.
     */
    public Skill getSkill(int id, boolean createIfNull) {
        for (Skill s : getSkills()) {
            if (s.getSkillId() == id) {
                return s;
            }
        }
        return createIfNull ? createAndReturnSkill(id) : null;
    }

    public void encodeChat(OutPacket outPacket, String msg) {
        outPacket.encodeString(getName());
        outPacket.encodeString(msg);
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeInt(getId());
    }


    public int getSkillLevel(int skillID) {
        Skill skill = getSkill(skillID);
        if (skill != null) {
            return skill.getCurrentLevel();
        }
        return 0;
    }

    /**
     * Gets the given SkillStat's Value.
     *
     * @param skillStat SkillStat to get the value from.
     * @param skillId   Specified skillId to grab the SkillInfo from.
     * @return value of the given SkillStat of the given Skill Id
     */
    public int getSkillStatValue(SkillStat skillStat, int skillId) {
        if (hasSkill(skillId)) {
            SkillInfo si = SkillData.getSkillInfoById(skillId);
            return si.getValue(skillStat, getSkillLevel(skillId));
        }
        return 0;
    }

    public int getRemainRecipeUseCount(int recipeID) {
        if (SkillConstants.isMakingSkillRecipe(recipeID)) {
            return getSkillLevel(recipeID);
        }
        return 0;
    }

    /**
     * Creates a new {@link Skill} for this Char.
     *
     * @param id The skillID of the Skill to be created.
     * @return The new Skill.
     */
    private Skill createAndReturnSkill(int id) {
        Skill skill = SkillData.getSkillDeepCopyById(id);
        addSkill(skill);
        return skill;
    }

    public void setStat(Stat charStat, int amount) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        switch (charStat) {
            case str:
                cs.setStr(amount);
                break;
            case dex:
                cs.setDex(amount);
                break;
            case inte:
                cs.setInt(amount);
                break;
            case luk:
                cs.setLuk(amount);
                break;
            case hp:
                cs.setHp(amount);
                break;
            case mhp:
                cs.setMaxHp(amount);
                if (JobConstants.isDemonAvenger(getJob())) {
                    ((DemonAvenger) getJobHandler()).sendHpUpdate();
                }
                break;
            case mp:
                cs.setMp(amount);
                break;
            case mmp:
                cs.setMaxMp(amount);
                break;
            case ap:
                cs.setAp(amount);
                break;
            case level:
                cs.setLevel(amount);
                notifyChanges();
                break;
            case skin:
                cs.setSkin(amount);
                break;
            case face:
                cs.setFace(amount);
                break;
            case hair:
                cs.setHair(amount);
                break;
            case pop:
                cs.setPop(amount);
                break;
            case charismaEXP:
                cs.setCharismaExp(amount);
                break;
            case charmEXP:
                cs.setCharmExp(amount);
                break;
            case craftEXP:
                cs.setCraftExp(amount);
                break;
            case insightEXP:
                cs.setInsightExp(amount);
                break;
            case senseEXP:
                cs.setSenseExp(amount);
                break;
            case willEXP:
                cs.setWillExp(amount);
                break;
            case fatigue:
                cs.setFatigue(amount);
                break;
        }
    }

    public void deductPoints(int questId, int cost) {
        switch (questId) {
            case QuestConstants.DOJO_COUNT
                    -> setDojoPoints(getDojoPoints() - cost);
        }
    }

    public boolean checkPoint(int questId, int cost) {
        return switch (questId) {
            case QuestConstants.DOJO_COUNT
                    -> getDojoPoints() >= cost;
            case QuestConstants.UNION_COIN
                    -> getUnion() != null && getUnion().getUnionCoin() >= cost;
            default -> false;
        };
    }

    public boolean checkExQuestValue(int questId, String exKey, int exValue) {
        return switch (questId) {
            default -> {
                var quest = getQuestManager().getQuestById(questId);
                yield quest != null && quest.getIntProperty(exKey) >= exValue;
            }
        };
    }



    public void updatePartyHP() {
        Party party = getParty();
        if (party != null) {
            for (PartyMember pm : party.getOnlineMembers()) {
                if (pm != null) {
                    Char pmChr = pm.getChr();
                    if (pmChr.getId() != getId() && pmChr.getClient().getChannel() == getClient().getChannel() && pm.getChr().getFieldID() == getFieldID()) {
                        pmChr.write(UserRemote.receiveHP(this));
                    }
                }
            }
        }
    }

    public void receivePartyHP() {
        Party party = getParty();
        if (party != null) {
            for (PartyMember pm : party.getOnlineMembers()) {
                Char pmChr = pm.getChr();
                if (pmChr.getId() != getId() && pmChr.getClient().getChannel() == getClient().getChannel() && pm.getChr().getFieldID() == getFieldID()) {
                    write(UserRemote.receiveHP(pmChr));
                }
            }
        }
    }

    /**
     * Notifies all groups (such as party, guild) about all your changes, such as level and job.
     */
    private void notifyChanges() {
        Party party = getParty();
        if (party != null) {
            party.updatePartyMemberInfoByChr(this);
            party.broadcast(WvsContext.partyResult(PartyResult.userMigration(party)));
            updatePartyHP();
            receivePartyHP();
        }
        Guild guild = getGuild();
        if (guild != null) {
            GuildMember gm = guild.getMemberByCharID(getId());
            if (gm != null) {
                gm.setLevel(getLevel());
                gm.setJob(getJob());
                guild.broadcast(WvsContext.guildResult(GuildResult.changeLevelOrJob(guild, gm)));
                Alliance ally = guild.getAlliance();
                if (ally != null) {
                    ally.broadcast(WvsContext.allianceResult(AllianceResult.changeLevelOrJob(ally, guild, gm)));
                }
            }
        }
    }

    /**
     * Gets a raw Stat from this Char, unaffected by things such as equips and skills.
     *
     * @param charStat The requested Stat
     * @return the requested stat's value
     */
    public int getStat(Stat charStat) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        switch (charStat) {
            case str:
                return cs.getStr();
            case dex:
                return cs.getDex();
            case inte:
                return cs.getInt();
            case luk:
                return cs.getLuk();
            case hp:
                return cs.getHp();
            case mhp:
                return cs.getMaxHp();
            case mp:
                return cs.getMp();
            case mmp:
                return cs.getMaxMp();
            case ap:
                return cs.getAp();
            case level:
                return cs.getLevel();
            case skin:
                return cs.getSkin();
            case face:
                return cs.getFace();
            case hair:
                return cs.getHair();
            case pop:
                return cs.getPop();
            case charismaEXP:
                return cs.getCharismaExp();
            case charmEXP:
                return cs.getCharmExp();
            case craftEXP:
                return cs.getCraftExp();
            case insightEXP:
                return cs.getInsightExp();
            case senseEXP:
                return cs.getSenseExp();
            case willEXP:
                return cs.getWillExp();
            case fatigue:
                return cs.getFatigue();
            case job:
                return cs.getJob();
        }
        return -1;
    }

    /**
     * Adds a Stat to this Char.
     *
     * @param charStat which Stat to add
     * @param amount   the amount of Stat to add
     */
    public void addStat(Stat charStat, int amount) {
        setStat(charStat, getStat(charStat) + amount);
    }

    /**
     * Adds a Stat to this Char, and immediately sends the packet to the client notifying the change.
     *
     * @param charStat which Stat to change
     * @param amount   the amount of Stat to add
     */
    public void addStatAndSendPacket(Stat charStat, int amount) {
        setStatAndSendPacket(charStat, getStat(charStat) + amount);
    }


    public int getCarta() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.CARTAS_PEARL);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.CARTAS_PEARL);
            quest.setProperty("sw", 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty("sw"));
    }

    public void setCarta(int ear) {
        Quest quest = getQuestManager().getQuestById(QuestConstants.CARTAS_PEARL);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.CARTAS_PEARL);
            quest.setProperty("sw", 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty("sw", ear);
        write(WvsContext.questRecordExMessage(quest));
    }

    /**
     * Adds a Stat to this Char, and immediately sends the packet to the client notifying the change.
     *
     * @param charStat which Stat to change
     * @param value    the value of Stat to set
     */
    public void setStatAndSendPacket(Stat charStat, int value) {
        setStat(charStat, value);
        Map<Stat, Object> stats = new HashMap<>();
        switch (charStat) {
            case skin:
            case fatigue:
                stats.put(charStat, (byte) getStat(charStat));
                break;
            case str:
            case dex:
            case inte:
            case luk:
            case ap:
            case job:
                stats.put(charStat, (short) getStat(charStat));
                break;
            case hp:
            case mhp:
            case mp:
            case mmp:
            case face:
            case hair:
            case pop:
            case charismaEXP:
            case insightEXP:
            case willEXP:
            case craftEXP:
            case senseEXP:
            case charmEXP:
            case eventPoints:
            case level:
                stats.put(charStat, getStat(charStat));
                break;
        }
        write(WvsContext.statChanged(stats, getSubJob()));
    }

    /**
     * Adds a certain amount of money to the current character. Also sends the
     * packet to update the client's state.
     *
     * @param amount The amount of money to add. May be negative.
     */
    public void addMoney(long amount) {
        addMoney(amount, (byte) 0);
    }

    public void addMoney(long amount, int exclRequest) {
        CharacterStat cs = getAvatarData().getCharacterStat();
        long money = cs.getMoney();
        long newMoney = money + amount;
        if (newMoney >= 0) {
            newMoney = Math.min(GameConstants.MAX_MONEY, newMoney);
            Map<Stat, Object> stats = new HashMap<>();
            cs.setMoney(newMoney);
            stats.put(Stat.money, newMoney);
            write(WvsContext.statChanged(stats, (byte) exclRequest));
        }
    }


    /**
     * The same as addMoney, but negates the amount.
     *
     * @param amount The money to deduct. May be negative.
     */
    public void deductMoney(long amount) {
        addMoney(-amount);
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Position oldPosition) {
        this.oldPosition = oldPosition;
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    public byte getMoveAction() {
        return moveAction;
    }

    /**
     * Sends a message to this Char through the ScriptProgress packet.
     *
     * @param msg The message to display.
     */
    public void chatScriptMessage(String msg) {
        write(UserPacket.scriptProgressMessage(msg));
    }

    /**
     * Sends a message to this Char with a default colour {@link ChatType#SystemNotice}.
     *
     * @param msg The message to display.
     */
    public void chatMessage(String msg) {
        chatMessage(SystemNotice, msg);
    }

    /**
     * Sends a formatted message to this Char with a default color {@link ChatType#SystemNotice}.
     *
     * @param msg  The message to display
     * @param args The format arguments
     */
    public void chatMessage(String msg, Object... args) {
        chatMessage(SystemNotice, msg, args);
    }

    /**
     * Sends a message to this Char with a given {@link ChatType colour}.
     *
     * @param clr The Colour this message should be in.
     * @param msg The message to display.
     */
    public void chatMessage(ChatType clr, String msg) {
        if (isDebugMode() || clr != ChatType.Mob) {
            // As most debug info is printed in ChatType  Mob,
            // this is a hacky way to turn 'debug' mode  on/off
            write(UserLocal.chatMsg(clr, msg));
        }
    }

    /**
     * Sends a formatted message to this Char with a given {@link ChatType colour}.
     *
     * @param clr  The Colour this message should be in.
     * @param msg  The message to display.
     * @param args The format arguments
     */
    public void chatMessage(ChatType clr, String msg, Object... args) {
        if (isDebugMode() || clr != ChatType.Mob) {
            // As most debug info is printed in ChatType  Mob,
            // this is a hacky way to turn 'debug' mode  on/off
            write(UserLocal.chatMsg(clr, String.format(msg, args)));
        }
    }


    /**
     * Unequips an {@link Item}. Ensures that the hairEquips and both inventories get updated.
     *
     * @param item The Item to equip.
     */
    public void unequip(Item item) {
        Equip equip = (Equip) item;
        Inventory inv = getEquippedInventory();
        AvatarLook al = getAvatarData().getAvatarLook();
        int itemID = equip.getAnvilId() == 0 ? item.getItemId() : equip.getAnvilId();
        getInventoryByType(EQUIPPED).removeItem(item);
        getInventoryByType(EQUIP).addItem(item);
        int pos = item.getBagIndex();
        if (pos > BodyPart.BPBase.getVal() && pos < BodyPart.CBPEnd.getVal()) {
            boolean isCash = item.isCash();
            Equip overrideItem;
            // get corresponding cash item
            if (isCash) {
                overrideItem = (Equip) inv.getItemBySlot(pos - 100);
            } else {
                overrideItem = (Equip) inv.getItemBySlot(pos + 100);
            }
            int overrideItemId = overrideItem == null ? -1 :
                    overrideItem.getAnvilId() == 0 ? overrideItem.getItemId() : overrideItem.getAnvilId();
            al.removeItem((byte) pos, itemID, overrideItemId, isCash);
        } else if (pos >= BodyPart.TotemBase.getVal() && pos < BodyPart.TotemEnd.getVal()) {
            al.removeItem((byte) (pos - 5000), itemID, -1, false);
        }
        byte maskValue = AvatarModifiedMask.AvatarLook.getVal();
        getField().broadcastPacket(UserRemote.avatarModified(this, maskValue, (byte) 0), this);
        if (getTemporaryStatManager().hasStat(CharacterTemporaryStat.SoulMP) && ItemConstants.isWeapon(item.getItemId())) {
            getTemporaryStatManager().removeStat(CharacterTemporaryStat.SoulMP, false);
            getTemporaryStatManager().removeStat(CharacterTemporaryStat.FullSoulMP, false);
            getTemporaryStatManager().sendResetStatPacket();
        }
        List<Skill> skills = new ArrayList<>();
        for (ItemSkill itemSkill : ItemData.getEquipById(item.getItemId()).getItemSkills()) {
            Skill skill = getSkill(itemSkill.getSkill());
            skill.setCurrentLevel(0);
            removeSkill(itemSkill.getSkill());
            skill.setCurrentLevel(-1); // workaround to remove skill from window without a cc
            skills.add(skill);
        }
        if (skills.size() > 0) {
            getClient().write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
        }
        int equippedSummonSkill = ItemConstants.getEquippedSummonSkillItem(item.getItemId(), getJob());
        if (equippedSummonSkill != 0) {
            getField().removeSummon(equippedSummonSkill, getId());

            getTemporaryStatManager().removeStatsBySkill(equippedSummonSkill);
            getTemporaryStatManager().removeStatsBySkill(getTemporaryStatManager().getOption(CharacterTemporaryStat.RepeatEffect).rOption);
        }
        if (ItemConstants.isAndroid(itemID) || ItemConstants.isMechanicalHeart(itemID)) {
            if (getAndroid() != null) {
                getField().removeLife(getAndroid());
            }
            setAndroid(null);
        }
        if (equip.getSetItemID() != 0) {
            // update set effects
            removeSetEffect(equip.getSetItemID());

            recalcStats(EnumSet.of(BaseStat.mhp, BaseStat.mmp));
        }
    }

    /**
     * Equips an {@link Item}. Ensures that the hairEquips and both inventories get updated.
     *
     * @param item The Item to equip.
     */
    public boolean equip(Item item, int oldPos, int newPos) {
        Equip equip = (Equip) item;
        Inventory inv = getEquippedInventory();
        if (equip.hasSpecialAttribute(EquipSpecialAttribute.Vestige)) {
            return false;
        }
        boolean equipStatChanged = false;
        if (equip.isEquipTradeBlock()) {
            equip.setTradeBlock(true);
            equip.setEquipTradeBlock(false);
            equip.setEquippedDate(FileTime.currentTime());
            equip.addAttribute(EquipAttribute.Untradable);
            equipStatChanged = true;
        }
        if (equip.hasAttribute(EquipAttribute.UntradableAfterTransaction)) {
            equip.removeAttribute(EquipAttribute.UntradableAfterTransaction);
            equip.addAttribute(EquipAttribute.Untradable); //For non CS items may or may not break stuff
            equipStatChanged = true;
        }
        AvatarLook al = getAvatarData().getAvatarLook();
        Item swapItem = inv.getItemBySlot(newPos);
        getInventoryByType(EQUIP).removeItem(item);
        getInventoryByType(EQUIPPED).addItem(item);
        if (swapItem != null) {
            unequip(swapItem);
            swapItem.setBagIndex(oldPos);
        }
        Integer itemID = equip.getAnvilId() == 0 ? item.getItemId() : equip.getAnvilId();
        int absPos = Math.abs(newPos);
        if (absPos > BodyPart.BPBase.getVal() && absPos < BodyPart.CBPEnd.getVal()) {
            boolean isCash = item.isCash();
            byte pos = (byte) (isCash ? absPos - 100 : absPos);
            Equip overrideItem;
            // get corresponding cash item
            if (isCash) {
                overrideItem = (Equip) inv.getItemBySlot(absPos - 100);
            } else {
                overrideItem = (Equip) inv.getItemBySlot(absPos + 100);
            }
            Integer overrideItemId = overrideItem == null ? -1 :
                    overrideItem.getAnvilId() == 0 ? overrideItem.getItemId() : overrideItem.getAnvilId();
            Map<Byte, Integer> hairEquips = al.getHairEquips();
            Map<Byte, Integer> unseenEquips = al.getUnseenEquips();
            // only add if not part of your own body
            if (ItemConstants.isWeapon(itemID) && pos == BodyPart.Weapon.getVal()) {
                if (isCash) {
                    al.setWeaponStickerId(itemID);
                    //unseenEquips.put(pos, itemID);
                } else {
                    al.setWeaponId(itemID);
                    hairEquips.put(pos, itemID);
                }
            } else if (isCash) {
                if (overrideItemId >= 0) {
                    hairEquips.remove(pos);
                    unseenEquips.put(pos, overrideItemId);
                }
                hairEquips.put(pos, itemID);
            } else {
                if (overrideItemId >= 0) {
                    unseenEquips.put(pos, itemID);
                } else {
                    hairEquips.put(pos, itemID);
                }
                if (pos == BodyPart.Shield.getVal()) {
                    al.setSubWeaponId(itemID);
                }
            }
        } else if (absPos >= BodyPart.TotemBase.getVal() && absPos < BodyPart.TotemEnd.getVal()) {
            al.getTotems().add(itemID);
        }
        if (!equip.hasAttribute(EquipAttribute.NoNonCombatStatGain) && equip.getCharmEXP() != 0) {
            addStatAndSendPacket(Stat.charmEXP, equip.getCharmEXP());
            equip.addAttribute(EquipAttribute.NoNonCombatStatGain);
            equipStatChanged = true;
        }
        List<Skill> skills = new ArrayList<>();
        for (ItemSkill itemSkill : ItemData.getEquipById(equip.getItemId()).getItemSkills()) {
            Skill skill = SkillData.getSkillDeepCopyById(itemSkill.getSkill());
            int slv = itemSkill.getSlv();
            // support for Tower of Oz rings
            if (equip.getLevel() > 0) {
                slv = (byte) Math.min(equip.getLevel(), skill.getMaxLevel());
            }
            skill.setCurrentLevel(slv);
            skills.add(skill);
            addSkill(skill);
        }
        if (skills.size() > 0) {
            getClient().write(WvsContext.changeSkillRecordResult(skills, true, false, false, false));
        }
        int equippedSummonSkill = ItemConstants.getEquippedSummonSkillItem(equip.getItemId(), getJob());
        if (equippedSummonSkill != 0) {
            getJobHandler().handleSkill(this, getTemporaryStatManager(), equippedSummonSkill, (byte) 1, null, new SkillUseInfo());
        }
        if (getField() != null) {
            byte maskValue = AvatarModifiedMask.AvatarLook.getVal();
            getField().broadcastPacket(UserRemote.avatarModified(this, maskValue, (byte) 0), this);
        }
        //initSoulMP(equip.getSoulSocketId());
        // check android status
        if (ItemConstants.isAndroid(itemID) || ItemConstants.isMechanicalHeart(itemID)) {
            initAndroid(true);
            if (getAndroid() != null) {
                getField().spawnLife(getAndroid(), null);
            }
        }
        if (equip.getSetItemID() != 0) {
            // update set effects
            addSetEffect(equip.getSetItemID());

            // reset set base stats
            recalcStats(EnumSet.of(BaseStat.mhp, BaseStat.mmp));
        }

        return equipStatChanged;
    }

    public TemporaryStatManager getTemporaryStatManager() {
        return temporaryStatManager;
    }

    public void setTemporaryStatManager(TemporaryStatManager temporaryStatManager) {
        this.temporaryStatManager = temporaryStatManager;
    }

    public GachaponManager getGachaponManager() {
        return gachaponManager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJobHandler(Job jobHandler) {
        this.jobHandler = jobHandler;
    }

    public Job getJobHandler() {
        return jobHandler;
    }

    public Map<Short, ScheduledFuture> getRecoverySchedules() {
        return recoverySchedules;
    }

    public void removeRecoverySchedulers() {
        for (ScheduledFuture sf : getRecoverySchedules().values()) {
            if (sf != null && !sf.isDone()) {
                sf.cancel(true);
            }
        }
        getRecoverySchedules().clear();
    }


    public FuncKeyMap getFuncKeyMap() {
        return funcKeyMaps.get(0);
    }

    public List<FuncKeyMap> getFuncKeyMaps() {
        return funcKeyMaps;
    }

    public void initFuncKeyMaps(int keySettingType, boolean beastTamer) {
        int amount = beastTamer ? 5 : 1;
        for (int i = 0; i < amount; i++) {
            FuncKeyMap funcKeyMap = FuncKeyMap.getDefaultMapping(keySettingType);
            funcKeyMaps.add(funcKeyMap);
        }
    }

    /**
     * Creates a {@link Rect} with regard to this character. Adds all values to this Char's
     * position.
     *
     * @param rect The rectangle to use.
     * @return The new rectangle.
     */
    public Rect getRectAround(Rect rect) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        return new Rect(x + rect.getLeft(), y + rect.getTop(), x + rect.getRight(), y + rect.getBottom());
    }

    /**
     * Returns the Equip equipped at a certain {@link BodyPart}.
     *
     * @param bodyPart The requested bodyPart.
     * @return The Equip corresponding to <code>bodyPart</code>. Null if there is none.
     */
    public Item getEquippedItemByBodyPart(BodyPart bodyPart) {
        List<Item> items = getEquippedInventory().getItemsByBodyPart(bodyPart);
        return items.size() > 0 ? items.get(0) : null;
    }

    public boolean isLeft() {
        return moveAction > 0 && (moveAction % 2) == 1;
    }

    public MarriageRecord getMarriageRecord() {
        return marriageRecord;
    }

    public void setMarriageRecord(MarriageRecord marriageRecord) {
        this.marriageRecord = marriageRecord;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        if (this.hide == hide) {
            return;
        }
        this.hide = hide;
        chatMessage(String.format("Hide is %s.", (hide ? "enabled" : "disabled")));
        write(FieldPacket.setHideEffect(hide));
        if (getField() == null) {
            return;
        }
        if (hide) {
            OutPacket leavePacket = UserPool.userLeaveField(this);
            for (Char other : getField().getChars()) {
                if (other.getId() != getId() && other.getUser().getAccountType().ordinal() < getUser().getAccountType().ordinal()) {
                    other.write(leavePacket);
                }
            }
        } else {
            OutPacket enterPacket = UserPool.userEnterField(this);
            for (Char other : getField().getChars()) {
                if (other.getId() != getId() && other.getUser().getAccountType().ordinal() < getUser().getAccountType().ordinal()) {
                    other.write(enterPacket);
                }
            }
        }
    }


    /**
     * Returns a {@link Field} based on the current {@link FieldInstanceType} of this Char (channel,
     * expedition, party or solo).
     *
     * @return The Field corresponding to the current FieldInstanceType.
     */
    public Field getOrCreateFieldByCurrentInstanceType(int fieldID) {
        Field res = null;
        if (getInstance() == null) {
            res = getClient().getChannelInstance().getField(fieldID);
        } else {
            res = getInstance().getField(fieldID);
            res.setRuneStone(null);
        }
        return res;
    }

    public boolean isEquipped(int id) { return getInventoryByType(InvType.EQUIPPED).getItems().stream().filter(item -> item.getItemId() == id).count() > 0; }


    /**
     * Warps this Char to a given field at the starting portal.
     *
     * @param fieldId the ID of the field to warp to
     */
    public void warp(int fieldId) {
        warp(getOrCreateFieldByCurrentInstanceType(fieldId));
    }

    /**
     * Warps this Char to a given field at the given portal. If the portal doesn't exist, takes the starting portal.
     *
     * @param fieldId  the ID of the field to warp to
     * @param portalId the ID of the portal where the Char should spawn
     */
    public void warp(int fieldId, int portalId) {
        Field field = getOrCreateFieldByCurrentInstanceType(fieldId);
        Portal portal = field.getPortalByID(portalId);
        if (portal == null) {
            portal = field.getDefaultPortal();
        }
        warp(field, portal);
    }

    public Inventory getHairInventory() {
        return hairInventory;
    }


    public Inventory getFaceInventory() {
        return faceInventory;
    }


    public boolean isInValidState() {
        return !isDead() || !isTalkingToNpc() || getTradeRoom() == null || getField() != null;
    }

    /**
     * Warps this character to a given field, at the starting position.
     * See {@link #warp(Field, Portal) warp}.
     *
     * @param toField The field to warp to.
     */
    public void warp(Field toField) {
        warp(toField, toField.getPortalByName("sp"), false);
    }

    /**
     * Warps this Char to a given {@link Field}, with the Field's "sp" portal as spawn position.
     *
     * @param toField       The Field to warp to.
     * @param characterData Whether or not the character data should be encoded.
     */
    public void warp(Field toField, boolean characterData) {
        if (toField == null) {
            toField = getOrCreateFieldByCurrentInstanceType(100000000);
        }
        warp(toField, toField.getPortalByName("sp"), characterData);
    }

    /**
     * Warps this Char to a given {@link Field} and {@link Portal}. Will not include character data.
     *
     * @param toField  The Field to warp to.
     * @param toPortal The Portal to spawn at.
     */
    public void warp(Field toField, Portal toPortal) {
        warp(toField, toPortal, false);
    }

    /**
     * Warps this character to a given field, at a given portal.
     * Ensures that the previous map does not contain this Char anymore, and that the new field
     * does.
     * Ensures that all Lifes are immediately spawned for the new player.
     *
     * @param toField The {@link Field} to warp to.
     * @param portal  The {@link Portal} where to spawn at.
     */
    public void warp(Field toField, Portal portal, boolean characterData) {
        getAvatarData().getCharacterStat().setPortal(portal.getId());
        if (toField == null) {
            return;
        }

        checkAndRemoveExpiredItems();

        TemporaryStatManager tsm = getTemporaryStatManager();
        for (AffectedArea aa : tsm.getAffectedAreas()) {
            if (!(aa.isFollowOwner() && aa.getOwner() == this) && aa.getRemoveSkill()) {
                tsm.removeStatsBySkill(aa.getSkillID());
            }
        }
        tsm.getAffectedAreas().clear();

        Field currentField = getField();
        boolean toChannelField = false;
        if (currentField != null) {
            currentField.removeChar(this);
            toChannelField = currentField.isChangeToChannelOnLeave();
            if (toChannelField) {
                if (getInstance() != null) {
                    getInstance().clear();
                }
                setInstance(null);
            }
        }
        setField(toField);

        setChair(null);
        getAvatarData().getCharacterStat().setPortal(portal.getId());
        setPosition(new Position(portal.getX(), portal.getY()));
        initFriendStatus();
        getClient().getChannelInstance().broadcastPacket(WvsContext.broadcastMessage(4, getClient().getChannel(), "Welcome to v214 development!", false));
        getClient().write(Stage.setField(this, toField, getClient().getChannel(), false, 0, characterData, hasBuffProtector(),
                (byte) (portal != null ? portal.getId() : 0), false, 100, null, true, -1));
        toField.addChar(this);
        showProperUI(currentField != null ? currentField.getId() : -1, toField.getId());

        if (isHide()) {
            write(FieldPacket.setHideEffect(true));
        }

        if (characterData) {
          //  initSoulMP();
            Party party = getParty();
            if (party != null) {
                write(WvsContext.partyResult(PartyResult.loadParty(party)));
            }
            if (getGuild() != null) {
                write(WvsContext.guildResult(GuildResult.loadGuild(getGuild())));
                if (getGuild().getAlliance() != null) {
                    write(WvsContext.allianceResult(AllianceResult.loadDone(getGuild().getAlliance())));
                    write(WvsContext.allianceResult(AllianceResult.loadGuildDone(getGuild().getAlliance())));
                }
            }
            //	Union union = getUnion();
            //Set<Char> eligibleChars = union.getEligibleUnionChars();
            //UnionBoard activeBoard = union.getBoardByPreset(getActiveUnionPreset());
            //	QuestManager qm = getQuestManager();
            //	if (qm.hasQuestCompleted(QuestConstants.UNION_START_QUEST)) {
            //		write(CUIHandler.unionAssignResult(getUnion().getUnionRank(), eligibleChars, activeBoard,
            //			null, null, null)); // todo: decide how u want to handle mobile/lab members
            //	Quest q = qm.getQuestById(QuestConstants.UNION_RANK);
            //	if (q == null) {
            //		q = QuestData.createQuestFromId(QuestConstants.UNION_RANK);
            //		qm.addQuest(q);
            //	}
            //	q.setProperty("rank", getUnion().getUnionRank());
            //	write(WvsContext.questRecordExMessage(q));
            //	}
            write(WvsContext.setMaplePoints(getUser().getNxPrepaid()));
        }
        toField.spawnLifesForChar(this);

        if (JobConstants.isEvan(getJob()) && getJob() != JobConstants.JobEnum.EVAN_NOOB.getJobId()) {
            ((Evan) getJobHandler()).spawnMir();
        }
        if (JobConstants.isKanna(getJob())) {
            if (Kanna.hasFoxMan(this)) {
                write(FieldPacket.enterFieldFoxMan(this));
            } else {
                ((Kanna) getJobHandler()).spawnHaku();
            }
        }
        if (JobConstants.isPhantom(getJob())) {
            ((Phantom) getJobHandler()).setCardAmount(((Phantom) getJobHandler()).getCardAmount());
        }
        if (JobConstants.isCorsair(getJob()) && hasSkill(Corsair.TARGET_LOCK) && !hasSkillOnCooldown(Corsair.TARGET_LOCK)) {
            write(UserLocal.skillCooltimeSetM(Corsair.TARGET_LOCK, 0)); // needs to be sent, to reset the passive effect
        }
        ForceAtom fa = null;
        if (getTemporaryStatManager().hasStatBySkillId(Job.GUIDED_ARROW)) {
            fa = getForceAtomByKey(getTemporaryStatManager().getOptByCTSAndSkill(GuidedArrow, Job.GUIDED_ARROW).xOption);
        }
        clearForceAtomMap();
        if (fa != null) {
            ForceAtomInfo fai = fa.getFaiList().get(0);
            fai.setKey(getNewForceAtomKey());
            fa.setFaiList(Collections.singletonList(fai));
            Option o = new Option();
            o.xOption = fa.getFaiList().get(0).getKey();
            o.tOption = (int) getTemporaryStatManager().getRemainingTime(GuidedArrow, Job.GUIDED_ARROW);
            o.setInMillis(true);
            tsm.putCharacterStatValue(GuidedArrow, o, true);
            tsm.sendSetStatPacket();
            createForceAtom(fa);
        }
        for (Summon summon : getSummons()) {
            int skillId = summon.getSkillID();
            if (summon.getMoveAbility().changeFieldWithOwner() && skillId != Hayato.IAIJUTSU_PHANTOM_BLADE) {
                summon.setObjectId(-1);
                toField.spawnSummon(summon);
            } else {
                tsm.removeStatsBySkill(summon.getSkillID());
            }
        }
        if (JobConstants.isNightWalker(getJob())) {
            for (Summon shadowBat : ((NightWalker) getJobHandler()).shadowBatList) {
                shadowBat.setObjectId(-1);
                toField.spawnAddSummon(shadowBat);
            }
            ((NightWalker) getJobHandler()).shadowBatList.clear();
        }
        for (AffectedArea aura : getFollowAffectedAreas().values()) {
            aura.setObjectId(-1);
            aura.setRect(getRectAround(isLeft() ? aura.getSkillRect() : aura.getSkillRect().horizontalFlipAround(0)));
            toField.spawnAffectedAreaAndRemoveOld(aura);
        }
        for (int skill : Job.REMOVE_ON_WARP) {
            if (tsm.hasStatBySkillId(skill)) {
                tsm.removeStatsBySkill(skill);
            }
        }
        notifyChanges();
        if (getDeathCount() > 0) {
            write(UserLocal.deathCountInfo(getDeathCount()));
        }
        if (field.getEliteState() == EliteState.EliteBoss) {
            write(FieldPacket.eliteState(EliteState.EliteBoss, true, GameConstants.ELITE_BOSS_BGM, null, null));
        }

        if (getField() != null) {
            if (getActiveFamiliar() != null) {
                getField().broadcastPacket(CFamiliar.familiarEnterField(getId(), true, getActiveFamiliar(), true, false));
            }
            for (Char c : toField.getChars()) {
                if (c == null) {
                    continue;
                }
                if (c.getActiveFamiliar() != null) {
                    write(CFamiliar.familiarEnterField(c.getId(), true, c.getActiveFamiliar(), true, false));
                }
                c.initPets();
            }
        }

        Dragon dragon = getDragon();
        if (dragon != null) {
            toField.spawnLife(dragon, null);
        }
        Android android = getAndroid();
        if (android != null) {
            toField.spawnLife(android, null);
        }
        for (Mob mob : toField.getMobs()) {
            mob.addObserver(getScriptManager());
        }
        if (getInstance() == null) {
            write(FieldPacket.setQuickMoveInfo(GameConstants.getQuickMoveInfos().stream().filter(qmi -> !qmi.isNoInstances() || getField().isChannelField()).collect(Collectors.toList())));
        }
        if (JobConstants.isAngelicBuster(getJob())) {
            write(UserLocal.setDressChanged(true, true));
        }
        if (getInstance() != null && getInstance().getRemainingTime() > 0) {
            write(FieldPacket.clock(ClockPacket.secondsClock(getInstance().getRemainingTime())));
        }
        showSkillOnOffEffect();
    }

    public void initFriendStatus() {
        for (Friend f : getFriends()) {
            Char friendChr = getWorld().getCharByID(f.getFriendID());
            if (friendChr != null) {
                f.setChr(friendChr);
                friendChr.getFriendByCharID(getId()).setChr(this);
            }
            f.setFlag(friendChr != null
                    ? FriendFlag.FriendOnline
                    : FriendFlag.FriendOffline);
        }
        for (Friend f : getUser().getFriends()) {
            User friendAcc = getWorld().getUserById(f.getFriendAccountID());
            if (friendAcc != null && friendAcc.getCurrentChr() != null) {
                f.setChr(friendAcc.getCurrentChr());
                Friend me = friendAcc.getFriendByUserID(getAccount().getId());
                if (me != null) {
                    me.setChr(this);
                }
            }
            f.setFlag(friendAcc != null
                    ? FriendFlag.AccountFriendOnline
                    : FriendFlag.AccountFriendOffline);
        }
    }

    public Set<Summon> getSummons() {
        Set<Summon> summons = new HashSet<>();
        TemporaryStatManager tsm = getTemporaryStatManager();
        for (Option option : tsm.getOptions(IndieSummon)) {
            summons.add(option.summon);
        }
        return summons;
    }

    public Map<Integer, AffectedArea> getFollowAffectedAreas() {
        return followAffectedAreas;
    }

    public void setFollowAffectedAreas(Map<Integer, AffectedArea> followAffectedAreas) {
        this.followAffectedAreas = followAffectedAreas;
    }

    public void addFollowAffectedArea(AffectedArea affectedArea) {
        getFollowAffectedAreas().put(affectedArea.getSkillID(), affectedArea);
    }

    public AffectedArea getFollowAffectedAreaBySkillID(int skillID) {
        return getFollowAffectedAreas().getOrDefault(skillID, null);
    }

    public World getWorld() {
        return getClient().getWorld();
    }

    /**
     * Adds a given amount of exp to this Char. Immediately checks for level-up possibility, and
     * sends the updated
     * stats to the client. Allows multi-leveling.
     *
     * @param amount The amount of exp to add.
     */
    public void addExp(long amount) {
        ExpIncreaseInfo eii = new ExpIncreaseInfo();
        eii.setLastHit(true);
        eii.setIncEXP(amount);
        addExp(amount, eii);
    }

    /**
     * Adds exp to this Char. Will calculate the extra exp gained from buffs and the exp rate of the server.
     * Also takes an argument to show this info to the client. Will not send anything if this argument (eii) is null.
     *
     * @param amount The amount of exp to add
     * @param eii    The info to send to the client
     */
    public void addExp(long amount, ExpIncreaseInfo eii) {
        if (amount <= 0) {
            return;
        }
        if (getGuild() != null) {
            getGuild().addCommitmentToChar(this, (int) Math.min(amount, Integer.MAX_VALUE)); // independant of any xp buffs
        }
        long incExp = eii == null ? amount : eii.getIncEXP();
        int expFromExpR = (int) (incExp * (getTotalStat(BaseStat.expR) / 100D));
        amount += expFromExpR;
        int level = getLevel();
        CharacterStat cs = getAvatarData().getCharacterStat();
        long curExp = cs.getExp();
        if (level >= GameConstants.charExp.length - 1) {
            return;
        }
        long newExp = curExp + amount;
        Map<Stat, Object> stats = new HashMap<>();
        while (newExp >= GameConstants.charExp[level] && level < GameConstants.charExp.length) {
            newExp -= GameConstants.charExp[level];
            addStat(Stat.level, 1);
            stats.put(Stat.level, getStat(Stat.level));
            getJobHandler().handleLevelUp();
            level++;
            getField().broadcastPacket(UserRemote.effect(getId(), Effect.levelUpEffect()));
            heal(getMaxHP());
            healMP(getMaxMP());
        }
        cs.setExp(newExp);
        stats.put(Stat.exp, newExp);
        if (eii != null) {
            eii.setIndieBonusExp(expFromExpR);
            write(WvsContext.incExpMessage(eii));
        }
        getClient().write(WvsContext.statChanged(stats));
    }

    /**
     * Adds a given amount of exp to this Char, however it does not display the Exp Message.
     * Immediately checks for level-up possibility, and sends the updated
     * stats to the client. Allows multi-leveling.
     *
     * @param amount The amount of exp to add.
     */
    public void addExpNoMsg(long amount) {
        addExp(amount, null);
    }

    /**
     * Sets the {@link Char} to the given level non-recursively.
     *
     * @param level The level to set to.
     */
    public void setLevel(int level) {
        if (level >= 0) {
            setStat(Stat.level, level);
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.level, level);
            stats.put(Stat.exp, (long) 0);
            getClient().write(WvsContext.statChanged(stats));
            getJobHandler().handleLevelUp();
            getField().broadcastPacket(UserRemote.effect(getId(), Effect.levelUpEffect()));
        }
    }

    /**
     * Adds a single level to this Char.
     */
    public void addLevel() {
        addLevels(1);
    }

    /**
     * Adds a given amount of levels to this Char.
     *
     * @param levelsToAdd The amount of levels to add.
     */
    public void addLevels(int levelsToAdd) {
        int currentLevel = getLevel();
        int targetLevel = currentLevel + levelsToAdd;
        if (targetLevel >= currentLevel) {
            while (currentLevel < targetLevel) {
                currentLevel++;
                setStat(Stat.level, targetLevel);
                Map<Stat, Object> stats = new HashMap<>();
                stats.put(Stat.level, currentLevel);
                stats.put(Stat.exp, (long) 0);
                getClient().write(WvsContext.statChanged(stats));
                getJobHandler().handleLevelUp();
                getField().broadcastPacket(UserRemote.effect(getId(), Effect.levelUpEffect()));
            }
        }
    }

    /**
     * Adds levels to {@link Char} until the given level recursively.
     *
     * @param targetLevel The level to level up to recursively.
     */
    public void addLevelsTo(int targetLevel) {
        for (int level = getLevel(); level <= targetLevel; level++) {
            Map<Stat, Object> stats = new HashMap<>();
            stats.put(Stat.level, level);
            stats.put(Stat.exp, (long) 0);
            getClient().write(WvsContext.statChanged(stats));
            getJobHandler().handleLevelUp();
            getField().broadcastPacket(UserRemote.effect(getId(), Effect.levelUpEffect()));
        }
    }

    public void addTraitExp(Stat traitStat, int amount) {
        if (amount <= 0) {
            return;
        }
        Map<Stat, Object> stats = new HashMap<>();
        addStat(traitStat, amount);
        stats.put(traitStat, getStat(traitStat));
        stats.put(Stat.dayLimit, getAvatarData().getCharacterStat().getNonCombatStatDayLimit());
        write(WvsContext.statChanged(stats));
        write(WvsContext.incNonCombatStatEXPMessage(traitStat, amount));
    }

    /**
     * Writes a packet to this Char's client.
     *
     * @param outPacket The OutPacket to write.
     */
    public void write(OutPacket outPacket) {
        if (getClient() != null) {
            getClient().write(outPacket);
        }
    }

    public ExpIncreaseInfo getExpIncreaseInfo() {
        return new ExpIncreaseInfo();
    }

    public WildHunterInfo getWildHunterInfo() {
        return wildHunterInfo;
    }


    public void setWildHunterInfo(WildHunterInfo wildHunterInfo) {
        this.wildHunterInfo = wildHunterInfo;
    }

    public ZeroInfo getZeroInfo() {
        return zeroInfo;
    }

    public void setZeroInfo(ZeroInfo zeroInfo) {
        this.zeroInfo = zeroInfo;
    }

    public int getNickItem() {
        return nickItem;
    }

    public void setNickItem(int nickItem) {
        this.nickItem = nickItem;
    }

    public void setDamageSkin(int itemID) {
        setDamageSkin(new DamageSkinSaveData(ItemConstants.getDamageSkinIDByItemID(itemID), itemID, false,
                StringData.getItemStringById(itemID)));
    }

    public void setDamageSkin(DamageSkinSaveData damageSkin) {
        this.damageSkin = damageSkin;
    }

    public DamageSkinSaveData getDamageSkin() {
        if (damageSkin == null) {
            return DamageSkinSaveData.DEFAULT_SKIN;
        }
        return damageSkin;
    }

    public DamageSkinSaveData getPremiumDamageSkin() {
        if (premiumDamageSkin == null) {
            return DamageSkinSaveData.DEFAULT_SKIN;
        }
        return premiumDamageSkin;
    }

    public DamageSkinSaveData getActiveDamageSkin() {
        DamageSkinSaveData ds = getDamageSkin();
        DamageSkinSaveData pds = getPremiumDamageSkin();
        if (pds != null && pds.getDamageSkinID() != 0) {
            return pds;
        }
        if (ds != null && pds.getDamageSkinID() != 0) {
            return ds;
        }
        return DamageSkinSaveData.DEFAULT_SKIN;
    }

    public void setPremiumDamageSkin(DamageSkinSaveData premiumDamageSkin) {
        this.premiumDamageSkin = premiumDamageSkin;
    }

    public void setPremiumDamageSkin(int itemID) {
        setPremiumDamageSkin(new DamageSkinSaveData(ItemConstants.getDamageSkinIDByItemID(itemID), itemID, false,
                StringData.getItemStringById(itemID)));
    }

    public void setPartyInvitable(boolean partyInvitable) {
        this.partyInvitable = partyInvitable;
    }

    /**
     * Returns if this Char can be invited to a party.
     *
     * @return Whether or not this Char can be invited to a party.
     */
    public boolean isPartyInvitable() {
        return partyInvitable;
    }


    public int getRecordFromQuestEx(int questID, String property) {
        Quest quest = getQuestManager().getQuestById(questID);
        if (quest == null) {
            quest = QuestData.createQuestFromId(questID);
            quest.setProperty(property, 0);
            getQuestManager().addQuest(quest);
        }
        if (quest.getProperty(property) == null) {
            quest.setProperty(property, 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty(property));
    }

    public void setRuneCooldownVisual(long cd) {
        TemporaryStatManager tsm = getTemporaryStatManager();
        Option o1 = new Option();
        o1.rOption = RuneStone.SEALED_RUNE_POWER;
        o1.tOption = (int) cd;
        tsm.putCharacterStatValue(CharacterTemporaryStat.RuneCooltime, o1);
        tsm.sendSetStatPacket();
    }



    /**
     * Returns if this character is currently in its beta state.
     *
     * @return true if this Char is in a beta state.
     */
    public boolean isZeroBeta() {
        return getZeroInfo() != null && getZeroInfo().isZeroBetaState();
    }


    /**
     * Zero only.
     * Goes into Beta form if Alpha, and into Alpha if Beta.
     */
    public void swapZeroState() {
        if (!(JobConstants.isZero(getJob())) || getZeroInfo() == null) {
            return;
        }
        ZeroInfo oldInfo = getZeroInfo().deepCopy();
        ZeroInfo currentInfo = getZeroInfo();
        CharacterStat cs = getAvatarData().getCharacterStat();
        currentInfo.setZeroBetaState(!oldInfo.isZeroBetaState());
        currentInfo.setSubHP(cs.getHp());
        currentInfo.setSubMHP(cs.getMaxHp());
        currentInfo.setSubMP(cs.getMp());
        currentInfo.setSubMMP(cs.getMaxMp());
        cs.setHp(oldInfo.getSubHP());
        cs.setMaxHp(oldInfo.getSubMHP());
        cs.setMp(oldInfo.getSubMP());
        cs.setMaxMp(oldInfo.getSubMMP());
        Map<Stat, Object> updatedStats = new HashMap<>();
        updatedStats.put(Stat.hp, cs.getHp());
        updatedStats.put(Stat.mhp, cs.getMaxHp());
        updatedStats.put(Stat.mp, cs.getMp());
        updatedStats.put(Stat.mmp, cs.getMaxMp());
        write(WvsContext.statChanged(updatedStats));
        if (getRecordFromQuestEx(9000001, "zeroState") == 0) {
            setQuestRecordEx(9000001, "zeroState", 1);
        } else {
            setQuestRecordEx(9000001, "zeroState", 0);
        }
        write(WvsContext.zeroInfo(currentInfo, this));
    }

    /**
     * Initializes zero info with HP values.
     */
    public void initZeroInfo() {
        ZeroInfo zeroInfo = new ZeroInfo();
        CharacterStat cs = getAvatarData().getCharacterStat();
        zeroInfo.setSubHP(cs.getHp());
        zeroInfo.setSubMHP(cs.getMaxHp());
        zeroInfo.setSubMP(cs.getMp());
        zeroInfo.setSubMMP(cs.getMaxMp());
        setZeroInfo(zeroInfo);
    }

    public ScriptManagerImpl getScriptManager() {
        return scriptManagerImpl;
    }

    /**
     * Adds a {@link Drop} to this Char.
     *
     * @param drop The Drop that has been picked up.
     */
    public boolean addDrop(Drop drop, boolean byPet) {
        if (drop.isMoney()) {
            addMoney(drop.getMoney(), (byte) (byPet ? 0 : 1));
            getQuestManager().handleMoneyGain(drop.getMoney());
            write(WvsContext.dropPickupMessage(drop.getMoney(), (short) 0));
            return true;
        } else {
            Item item = drop.getItem();
            int itemID = item.getItemId();
            boolean isConsume = false;
            boolean isRunOnPickUp = false;
            if (itemID == GameConstants.BLUE_EXP_ORB_ID
                    || itemID == GameConstants.PURPLE_EXP_ORB_ID
                    || itemID == GameConstants.RED_EXP_ORB_ID
                    || itemID == GameConstants.GOLD_EXP_ORB_ID) {
                long expGain = (long) (drop.getMobExp() * GameConstants.getExpOrbExpModifierById(itemID));

                write(UserPacket.effect(Effect.fieldItemConsumed((int) (expGain > Integer.MAX_VALUE ? Integer.MAX_VALUE : expGain))));
                addExpNoMsg(expGain);

                // Exp Orb Buff On Pickup
                TemporaryStatManager tsm = getTemporaryStatManager();
                ItemBuffs.giveItemBuffsFromItemID(this, tsm, itemID);
            }
            if (!ItemConstants.isEquip(itemID)) {
                ItemInfo ii = ItemData.getItemInfoByID(itemID);
                isConsume = ii.getSpecStats().getOrDefault(SpecStat.consumeOnPickup, 0) != 0;
                isRunOnPickUp = ii.getSpecStats().getOrDefault(SpecStat.runOnPickup, 0) != 0;
            }
            if (isConsume) {
                consumeItemOnPickup(item);
                dispose();
                return true;
            } else if (isRunOnPickUp) {
                String script = String.valueOf(itemID);
                ItemInfo ii = ItemData.getItemInfoByID(itemID);
                if (ii.getScript() != null && !"".equals(ii.getScript())) {
                    script = ii.getScript();
                }
                getScriptManager().startScript(itemID, script, ScriptType.Item);
                return true;
            } else if (getInventoryByType(item.getInvType()).canPickUp(item)) {
                if (item instanceof Equip) {
                    Equip equip = (Equip) item;
                    if (equip.hasAttribute(EquipAttribute.UntradableAfterTransaction)) {
                        equip.removeAttribute(EquipAttribute.UntradableAfterTransaction);
                        equip.addAttribute(EquipAttribute.Untradable);
                    }
                }
                addItemToInventory(item.getInvType(), item, false, byPet);
                write(WvsContext.dropPickupMessage(item, (short) item.getQuantity()));
                return true;
            } else {
                write(WvsContext.dropPickupMessage(0, 0, (byte) -1, (short) 0, (short) 0));
                return false;
            }
        }
    }

    private void consumeItemOnPickup(Item item) {
        int itemID = item.getItemId();
        if (ItemConstants.isMobCard(itemID)) {
            MonsterBookInfo mbi = getMonsterBookInfo();
            int id = 0;
            if (!mbi.hasCard(itemID)) {
                mbi.addCard(itemID);
                id = itemID;
            }
            write(WvsContext.monsterBookSetCard(id));
        }
    }

    /**
     * Returns the Char's name.
     *
     * @return The Char's name.
     */
    public String getName() {
        return getAvatarData().getCharacterStat().getName();
    }

    /**
     * Checks whether or not this Char has a given quest in progress.
     *
     * @param questReq The quest ID of the requested quest.
     * @return Whether or not this char is in progress with the quest.
     */
    public boolean hasQuestInProgress(int questReq) {
        return getQuestManager().hasQuestInProgress(questReq);
    }

    /**
     * Disposes this Char, allowing it to send packets to the server again.
     */
    public void dispose() {
        setTalkingToNpc(false);
        write(WvsContext.exclRequest());
    }

    public void dispose(String msg) {
        chatMessage(msg);
        dispose();
    }

    /**
     * Returns the current HP of this Char.
     *
     * @return the current HP of this Char.
     */
    public int getHP() {
        return getStat(Stat.hp);
    }

    /**
     * Returns the current MP of this Char.
     *
     * @return the current MP of this Char.
     */
    public int getMP() {
        return getStat(Stat.mp);
    }

    /**
     * Gets the max hp of this Char.
     *
     * @return The max hp of this Char
     */
    public int getMaxHP() {
        return Math.min(GameConstants.MAX_HP_MP, getTotalStat(BaseStat.mhp));
    }

    /**
     * Gets the max mp of this Char.
     *
     * @return The max mp of this Char
     */
    public int getMaxMP() {
        return Math.min(GameConstants.MAX_HP_MP, getTotalStat(BaseStat.mmp));
    }

    /**
     * Gets the current percentage of HP of this Char.
     *
     * @return
     */
    public double getCurrentHPPerc() {
        return 100 * (((double) getHP()) / getMaxHP());
    }

    /**
     * Gets the current percentage of MP of this Char.
     *
     * @return
     */
    public double getCurrentMPPerc() {
        return 100 * (((double) getMP()) / getMaxMP());
    }

    /**
     * Gets the amount that is 1% of this Char's Max HP
     *
     * @return
     */

    public int getHPPerc() {
        return getHPPerc(1);
    }

    /**
     * Gets the amount that is 'amount'% of this Char's Max HP
     *
     * @param amount
     * @return
     */

    public int getHPPerc(int amount) {
        return (int) (amount * (getMaxHP() / 100D));
    }

    public int getMPPerc(int amount) {
        return (int) (amount * (getMaxMP() / 100D));
    }

    /**
     * Heals this Char's HP for a certain amount. Caps off at maximum HP.
     *
     * @param amount The amount to heal.
     */
    public void heal(int amount) {
        heal(amount, false);
    }

    public void heal(int amount, boolean showEffect) {
        if (amount >= 0 && getTemporaryStatManager().getOptByCTSAndSkill(BattlePvPHelenaMark, DemonAvenger.DEMONIC_FRENZY) != null) { // limits skill & item healing to 1% of the Char's MaxHP
            amount = (int) (amount > getMaxHP() / 100D ? getMaxHP() / 100D : amount);
        }
        int curHP = getHP();
        int maxHP = getMaxHP();
        int newHP = curHP + amount > maxHP ? maxHP : curHP + amount < 0 ? 0 : curHP + amount;

        if (showEffect && newHP != curHP) {
            write(UserPacket.effect(Effect.changeHPEffect(newHP - curHP)));
            getField().broadcastPacket(UserRemote.effect(getId(), Effect.changeHPEffect(newHP - curHP)));
        }

        Map<Stat, Object> stats = new HashMap<>();
        setStat(Stat.hp, newHP);
        stats.put(Stat.hp, newHP);
        write(WvsContext.statChanged(stats));
        if (getParty() != null) {
            getParty().broadcast(UserRemote.receiveHP(this), this);
        }
    }

    /**
     * "Heals" this Char's MP for a certain amount. Caps off at maximum MP.
     *
     * @param amount The amount to heal.
     */
    public void healMP(int amount) {
        int curMP = getMP();
        int maxMP = getMaxMP();
        int newMP = curMP + amount > maxMP ? maxMP : curMP + amount < 0 ? 0 : curMP + amount;
        Map<Stat, Object> stats = new HashMap<>();
        setStat(Stat.mp, newMP);
        stats.put(Stat.mp, newMP);
        write(WvsContext.statChanged(stats));
    }


    /**
     * Consumes a single {@link Item} from this Char's {@link Inventory}. Will remove the Item if it
     * has a quantity of 1.
     *
     * @param item The Item to consume, which is currently in the Char's inventory.
     */
    public void consumeItem(Item item) {
        consumeItem(item, false);
    }

    /**
     * Consumes a single {@link Item} from this Char's {@link Inventory}. Will remove the Item if it
     * has a quantity of 1.
     *
     * @param item      The Item to consume, which is currently in the Char's inventory.
     * @param removable true: enables to remove throwing item from slot
     */
    public void consumeItem(Item item, boolean removable) {
        Inventory inventory = getInventoryByType(item.getInvType());
        // data race possible
        boolean throwingItem = ItemConstants.isThrowingItem(item.getItemId());
        if (!throwingItem && item.getQuantity() <= 1 || throwingItem && removable) {
            item.setQuantity(0);
            short bagIndex = (short) item.getBagIndex();
            inventory.removeItem(item);
            item.drop();
            if (item.getInvType() == EQUIPPED) {
                if (bagIndex > BodyPart.BPBase.getVal() && bagIndex < BodyPart.CBPEnd.getVal()
                        || bagIndex >= BodyPart.TotemBase.getVal() && bagIndex < BodyPart.TotemEnd.getVal()) {
                    if (!ItemConstants.isTotem(item.getItemId())) {
                        boolean isCash = item.isCash();
                        Equip overrideItem;
                        // get corresponding cash item
                        if (isCash) {
                            overrideItem = (Equip) inventory.getItemBySlot(bagIndex - 100);
                        } else {
                            overrideItem = (Equip) inventory.getItemBySlot(bagIndex + 100);
                        }
                        int overrideItemId = overrideItem == null ? -1 :
                                overrideItem.getAnvilId() == 0 ? overrideItem.getItemId() : overrideItem.getAnvilId();
                        getAvatarData().getAvatarLook().removeItem((byte) bagIndex, item.getItemId(), overrideItemId, isCash);
                    } else {
                        getAvatarData().getAvatarLook().removeItem((byte) (bagIndex - 5000), item.getItemId(), -1, false);
                    }
                }
                bagIndex = (short) -bagIndex;
            }
            write(WvsContext.inventoryOperation(true, false, Remove, bagIndex, (byte) 0, 0, item));
        } else {
            item.setQuantity(item.getQuantity() - 1);
            write(WvsContext.inventoryOperation(true, false, UpdateQuantity, (short) item.getBagIndex(), (byte) -1, 0, item));
        }
        setBulletIDForAttack(calculateBulletIDForAttack());
    }

    /**
     * Removes a certain amount of an item from this Char.
     * @param item the item to remove
     * @param quantity the amount to remove
     */
    public void consumeItem(Item item, int quantity) {
        int consumed = quantity > item.getQuantity() ? 0 : item.getQuantity() - quantity;
        item.setQuantity(consumed + 1); // +1 because 1 gets consumed by consumeItem(item)
        consumeItem(item);
    }


    /**
     * TODO: should replace this one with below one
     * @param id
     * @param quantity
     */
    public void consumeItem(int id, int quantity) {
        consumeItem(id, quantity, false);
    }

    /**
     * Consumes an item of this Char with the given id. Will do nothing if the Char doesn't have the
     * Item.
     * Only works for non-Equip (i.e., type is not EQUIPPED or EQUIP, CASH is fine) items.
     * Calls {@link #consumeItem(Item)} if an Item is found.
     *
     * @param id       The Item's id.
     * @param quantity The amount to consume.
     * @param removable true: enables to remove throwing item from slot
     */
    public void consumeItem(int id, int quantity, boolean removable) {
        Item checkItem = ItemData.getItemDeepCopy(id);
        Item item = getInventoryByType(checkItem.getInvType()).getItemByItemID(id);
        if (item != null) {
            int consumed = quantity > item.getQuantity() ? 0 : item.getQuantity() - quantity;
            item.setQuantity(consumed + 1); // +1 because 1 gets consumed by consumeItem(item)
            consumeItem(item, removable);
        }
    }

    public void consumeItemBySlot(InvType invType, int slot, int quantity, boolean removable) {
        Item item = getInventoryByType(invType).getItemBySlot(slot);
        if (item != null) {
            int consumed = quantity > item.getQuantity() ? 0 : item.getQuantity() - quantity;
            item.setQuantity(consumed + 1); // +1 because 1 gets consumed by consumeItem(item)
            consumeItem(item, removable);
        }
    }


    public boolean hasItem(int itemID) {
        return getInventories().stream().anyMatch(inv -> inv.containsItem(itemID));
    }

    public boolean hasItemCount(int itemID, int count) {
        Inventory inv = getInventoryByType(ItemData.getItemDeepCopy(itemID).getInvType());
        return inv.getItems().stream()
                .filter(i -> i.getItemId() == itemID)
                .mapToInt(Item::getQuantity)
                .sum() >= count;
    }

    public short getLevel() {
        return getAvatarData().getCharacterStat().getLevel();
    }

    public boolean isMarried() {
        // TODO
        return false;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        if (guild != null) {
            if (getClient() != null) {
                // to ensure that the same instance of a guild is retrieved for all characters
                this.guild = getClient().getWorld().getGuildByID(guild.getId());
            } else {
                // for offline character, it's just about the ID, so exact instance doesn't matter
                this.guild = guild;
            }
        } else {
            this.guild = null;
        }
    }

    public int getTotalChuc() {
        int total = 0;
        for (Item item : getEquippedInventory().getItems()) {
            Equip equip = (Equip) item;
            if (ItemConstants.isOverall(equip.getItemId())) {
                total = 2 * equip.getChuc();
            } else {
                total = equip.getChuc();
            }
        }
        return total;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getChocoCount() {
        return chocoCount;
    }

    public void setChocoCount(int chocoCount) {
        this.chocoCount = chocoCount;
    }

    public int getActiveEffectItemID() {
        return activeEffectItemID;
    }

    public void setActiveEffectItemID(int activeEffectItemID) {
        this.activeEffectItemID = activeEffectItemID;
    }

    public int getMonkeyEffectItemID() {
        return monkeyEffectItemID;
    }

    public void setMonkeyEffectItemID(int monkeyEffectItemID) {
        this.monkeyEffectItemID = monkeyEffectItemID;
    }

    public int getCompletedSetItemID() {
        return completedSetItemID;
    }

    public void setCompletedSetItemID(int completedSetItemID) {
        this.completedSetItemID = completedSetItemID;
    }

    public short getFieldSeatID() {
        return -1;
    }

    public void setFieldSeatID(short fieldSeatID) {
        this.fieldSeatID = fieldSeatID;
    }

    public PortableChair getChair() {
        return chair;
    }

    public void setChair(PortableChair chair) {
        this.chair = chair;
    }

    public short getFoothold() {
        return foothold;
    }

    public void setFoothold(short foothold) {
        this.foothold = foothold;
    }

    public int getTamingMobLevel() {
        return tamingMobLevel;
    }

    public void setTamingMobLevel(int tamingMobLevel) {
        this.tamingMobLevel = tamingMobLevel;
    }

    public int getTamingMobExp() {
        return tamingMobExp;
    }

    public void setTamingMobExp(int tamingMobExp) {
        this.tamingMobExp = tamingMobExp;
    }

    public int getTamingMobFatigue() {
        return tamingMobFatigue;
    }

    public void setTamingMobFatigue(int tamingMobFatigue) {
        this.tamingMobFatigue = tamingMobFatigue;
    }

    public MiniRoom getMiniRoom() {
        return miniRoom;
    }

    public void setMiniRoom(MiniRoom miniRoom) {
        this.miniRoom = miniRoom;
    }

    public String getADBoardRemoteMsg() {
        return ADBoardRemoteMsg;
    }

    public void setADBoardRemoteMsg(String ADBoardRemoteMsg) {
        this.ADBoardRemoteMsg = ADBoardRemoteMsg;
    }

    public boolean isInCouple() {
        return inCouple;
    }

    public void setInCouple(boolean inCouple) {
        this.inCouple = inCouple;
    }

    public CoupleRecord getCouple() {
        return couple;
    }

    public void setCouple(CoupleRecord couple) {
        this.couple = couple;
    }

    public boolean hasFriendshipItem() {
        return false;
    }

    public FriendshipRingRecord getFriendshipRingRecord() {
        return friendshipRingRecord;
    }

    public void setFriendshipRingRecord(FriendshipRingRecord friendshipRingRecord) {
        this.friendshipRingRecord = friendshipRingRecord;
    }

    public int getComboCounter() {
        return comboCounter;
    }

    public void setComboCounter(int comboCounter) {
        this.comboCounter = comboCounter;
    }

    public int getEvanDragonGlide() {
        return evanDragonGlide;
    }

    public void setEvanDragonGlide(int evanDragonGlide) {
        this.evanDragonGlide = evanDragonGlide;
    }

    public int getKaiserMorphRotateHueExtern() {
        return kaiserMorphRotateHueExtern;
    }

    public void setKaiserMorphRotateHueExtern(int kaiserMorphRotateHueExtern) {
        this.kaiserMorphRotateHueExtern = kaiserMorphRotateHueExtern;
    }

    public int getKaiserMorphPrimiumBlack() {
        return kaiserMorphPrimiumBlack;
    }

    public void setKaiserMorphPrimiumBlack(int kaiserMorphPrimiumBlack) {
        this.kaiserMorphPrimiumBlack = kaiserMorphPrimiumBlack;
    }

    public int getKaiserMorphRotateHueInnner() {
        return kaiserMorphRotateHueInnner;
    }

    public void setKaiserMorphRotateHueInnner(int kaiserMorphRotateHueInnner) {
        this.kaiserMorphRotateHueInnner = kaiserMorphRotateHueInnner;
    }

    public int getMakingMeisterSkillEff() {
        return makingMeisterSkillEff;
    }

    public void setMakingMeisterSkillEff(int makingMeisterSkillEff) {
        this.makingMeisterSkillEff = makingMeisterSkillEff;
    }

    public FarmUserInfo getFarmUserInfo() {
        if (farmUserInfo == null) {
            return new FarmUserInfo();
        }
        return farmUserInfo;
    }

    public void setFarmUserInfo(FarmUserInfo farmUserInfo) {
        this.farmUserInfo = farmUserInfo;
    }

    public int getCustomizeEffect() {
        return customizeEffect;
    }

    public void setCustomizeEffect(int customizeEffect) {
        this.customizeEffect = customizeEffect;
    }

    public String getCustomizeEffectMsg() {
        return customizeEffectMsg;
    }

    public void setCustomizeEffectMsg(String customizeEffectMsg) {
        this.customizeEffectMsg = customizeEffectMsg;
    }

    public byte getSoulEffect() {
        return soulEffect;
    }

    public void setSoulEffect(byte soulEffect) {
        this.soulEffect = soulEffect;
    }

    public FreezeHotEventInfo getFreezeHotEventInfo() {
        if (freezeHotEventInfo == null) {
            return new FreezeHotEventInfo();
        }
        return freezeHotEventInfo;
    }

    public void setFreezeHotEventInfo(FreezeHotEventInfo freezeHotEventInfo) {
        this.freezeHotEventInfo = freezeHotEventInfo;
    }

    public int getEventBestFriendAID() {
        return eventBestFriendAID;
    }

    public void setEventBestFriendAID(int eventBestFriendAID) {
        this.eventBestFriendAID = eventBestFriendAID;
    }

    public int getMesoChairCount() {
        return mesoChairCount;
    }

    public void setMesoChairCount(int mesoChairCount) {
        this.mesoChairCount = mesoChairCount;
    }

    public boolean isBeastFormWingOn() {
        return beastFormWingOn;
    }

    public void setBeastFormWingOn(boolean beastFormWingOn) {
        this.beastFormWingOn = beastFormWingOn;
    }

    public int getActiveNickItemID() {
        return activeNickItemID;
    }

    public void setActiveNickItemID(int activeNickItemID) {
        this.activeNickItemID = activeNickItemID;
    }

    public int getMechanicHue() {
        return mechanicHue;
    }

    public void setMechanicHue(int mechanicHue) {
        this.mechanicHue = mechanicHue;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        boolean changed = online != this.online;

        this.online = online;
        if (getGuild() != null) {
            setGuild(getGuild()); // Hack to ensure that all chars have the same instance of a guild
            Guild g = getGuild();
            GuildMember gm = g.getMemberByCharID(getId());
            if (gm != null) {
                gm.setOnline(online);
                gm.setChr(online ? this : null);
                Alliance ally = getGuild().getAlliance();
                if (ally != null) {
                    ally.broadcast(WvsContext.allianceResult(
                            AllianceResult.notifyLoginOrLogout(ally, g, gm, changed)), this);
                } else {
                    getGuild().broadcast(WvsContext.guildResult(
                            GuildResult.notifyLoginOrLogout(g, gm, online, changed)), this);
                }
            }
        }
        if (getParty() != null) {
            PartyMember pm = getParty().getPartyMemberByID(getId());
            if (pm != null) {
                pm.setChr(online ? this : null);
                getParty().broadcast(WvsContext.partyResult(PartyResult.userMigration(getParty())));
                getParty().updateFull();
            }
        }


        for (Friend f : getOnlineFriends()) {
            boolean account = f.isAccount();
            Char chr = f.getChr();
            Friend me;
            if (account) {
                me = chr.getUser().getFriendByUserID(getAccount().getId());
            } else {
                me = chr.getFriendByCharID(getId());
            }
            if (me != null) {
                me.setChr(online ? this : null);
                me.setFlag(account ?
                        online ? FriendFlag.AccountFriendOnline : FriendFlag.AccountFriendOffline
                        : online ? FriendFlag.FriendOnline : FriendFlag.FriendOffline);
                chr.write(WvsContext.friendResult(FriendResult.updateFriend(me)));
            }
        }
    }



    public final void updateLoginStatus() {
        notifyChanges();
        setOnline(false);
    }



    public void partyOnline(boolean online) {
        this.online = online;
        if (getParty() != null) {
            PartyMember pm = getParty().getPartyMemberByID(getId());
            if (pm != null) {
                pm.setChr(online ? this : null);
            }
        }
    }

    public void setParty(Party party) {
        if (party != null) {
            setPartyID(party.getId());
        } else {
            setPartyID(0);
        }
        this.party = party;
    }

    public Party getParty() {
        return party;
    }

    /**
     * Logs a User fully out (after crash/request to go to world select).
     */
    public void logout() {
        punishLieDetectorEvasion();
        log.info("Logging out " + getName());
        if (getField().getForcedReturn() != GameConstants.NO_MAP_ID) {
            setFieldID(getField().getForcedReturn());
        }
        if (getTradeRoom() != null) {
            Char other = getTradeRoom().getOther();
            getTradeRoom().cancelTrade();
            other.chatMessage("Your trade partner disconnected.");
        }

        updateLoginStatus();
        getField().removeChar(this);
        getUser().setCurrentChr(null);
        getClient().getChannelInstance().removeChar(this);
        Server.getInstance().removeUser(getUser()); // don't unstuck, as that would save the account (twice)
        getJobHandler().cancelTimers();
        DatabaseManager.saveToDB(getUser());
        Server.getInstance().removeUser(user);
        partyOnline(false);
        scriptManagerImpl.getScripts().values().forEach(ScriptInfo::reset);
        setChangingChannel(false);
        removeSchedulers();
        if (JobConstants.isAdele(getJob())) {
            ((Adele) getJobHandler()).clearAetherTimer();
        }
        if (JobConstants.isArk(getJob())) {
            ((Ark) getJobHandler()).clearSpectraTimer();
        }
    }

    public int getSubJob() {
        return getAvatarData().getCharacterStat().getSubJob();
    }

    public void setInstance(Instance instance) {
        if (this.instance != null && instance == null) {
            this.instance.stopEvents();
        }
        this.instance = instance;
    }

    public Instance getInstance() {
        if (party != null && party.getInstance() != null) {
            return party.getInstance();
        }
        return instance;
    }

    private void showProperUI(int fromField, int toField) {
        if (GameConstants.getMaplerunnerField(toField) > 0 && GameConstants.getMaplerunnerField(fromField) <= 0) {
            write(FieldPacket.openUI(UIType.PLATFORM_STAGE_LEAVE));
        } else if (GameConstants.getMaplerunnerField(fromField) > 0 && GameConstants.getMaplerunnerField(toField) <= 0) {
            write(FieldPacket.closeUI(UIType.PLATFORM_STAGE_LEAVE));
        }
    }

    public int calculateBulletIDForAttack() {
        Item weapon = getEquippedInventory().getFirstItemByBodyPart(BodyPart.Weapon);
        if (weapon == null) {
            return 0;
        }
        Predicate<Item> p;
        int id = weapon.getItemId();

        if (ItemConstants.isClaw(id)) {
            p = i -> ItemConstants.isThrowingStar(i.getItemId());
        } else if (ItemConstants.isBow(id)) {
            p = i -> ItemConstants.isBowArrow(i.getItemId());
        } else if (ItemConstants.isXBow(id)) {
            p = i -> ItemConstants.isXBowArrow(i.getItemId());
        } else if (ItemConstants.isGun(id)) {
            p = i -> ItemConstants.isBullet(i.getItemId());
        } else {
            return 0;
        }
        Item i = getConsumeInventory().getItems().stream().sorted(Comparator.comparing(Item::getBagIndex)).filter(p).findFirst().orElse(null);
        return i != null ? i.getItemId() : 0;
    }

    public int getBulletIDForAttack() {
        return bulletIDForAttack;
    }

    public void setBulletIDForAttack(int bulletIDForAttack) {
        this.bulletIDForAttack = bulletIDForAttack;
    }

    public void setShop(NpcShopDlg shop) {
        this.shop = shop;
    }

    public NpcShopDlg getShop() {
        return shop;
    }

    /**
     * Checks if this Char can hold an Item in their inventory, assuming that its quantity is 1.
     *
     * @param id the item's itemID
     * @return whether or not this Char can hold an item in their inventory
     */
    public boolean canHold(int id) {
        boolean canHold;
        if (ItemConstants.isEquip(id)) {  //Equip
            canHold = getEquipInventory().getSlots() > getEquipInventory().getItems().size();
        } else {    //Item
            ItemInfo ii = ItemData.getItemInfoByID(id);
            Inventory inv = getInventoryByType(ii.getInvType());
            Item curItem = inv.getItemByItemID(id);
            canHold = (curItem != null && curItem.getQuantity() + 1 < ii.getSlotMax()) || inv.getSlots() > inv.getItems().size();
        }
        return canHold;
    }

    public boolean canHold(int id, int quantity) {
        Item item = ItemData.getItemDeepCopy(id);
        item.setQuantity(quantity);
        List<Item> items = new ArrayList<>();
        items.add(item);
        return canHold(items);
    }

    /**
     * Recursive function that checks if this Char can hold a list of items in their inventory.
     *
     * @param items the list of items this char should be able to hold
     * @return whether or not this Char can hold the list of items
     */
    public boolean canHold(List<Item> items) {
        return canHold(items, deepCopyForInvCheck());
    }

    private boolean canHold(List<Item> items, Char deepCopiedChar) {
        // explicitly use a Char param to avoid accidentally adding items
        if (items.size() == 0) {
            return true;
        }
        Item item = items.get(0);
        if (canHold(item.getItemId())) {
            Inventory inv = deepCopiedChar.getInventoryByType(item.getInvType());
            inv.addItem(item);
            items.remove(item);
            return deepCopiedChar.canHold(items, deepCopiedChar);
        } else {
            return false;
        }

    }

    private Char deepCopyForInvCheck() {
        Char chr = new Char();
        chr.setEquippedInventory(getEquippedInventory().deepCopy());
        chr.setEquipInventory(getEquipInventory().deepCopy());
        chr.setConsumeInventory(getConsumeInventory().deepCopy());
        chr.setEtcInventory(getEtcInventory().deepCopy());
        chr.setInstallInventory(getInstallInventory().deepCopy());
        chr.setCashInventory(getCashInventory().deepCopy());
        return chr;
    }

    /**
     * Returns the set of personal (i.e., non-account) friends of this Char.
     *
     * @return The set of personal friends
     */
    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    /**
     * Returns the total list of friends of this Char + the owning Account's friends.
     *
     * @return The total list of friends
     */
    public Set<Friend> getAllFriends() {
        Set<Friend> res = new HashSet<>(getFriends());
        res.addAll(getUser().getFriends());
        return res;
    }

    public Friend getFriendByCharID(int charID) {
        return getFriends().stream().filter(f -> f.getFriendID() == charID).findAny().orElse(null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void removeFriend(Friend friend) {
        if (friend != null) {
            getFriends().remove(friend);
        }
    }

    public void removeFriendByID(int charID) {
        removeFriend(getFriendByCharID(charID));
    }

    public void addFriend(Friend friend) {
        if (getFriendByCharID(friend.getFriendID()) == null) {
            getFriends().add(friend);
        }
    }

    public void setChatClient(Client chatClient) {
        this.chatClient = chatClient;
    }

    public Client getChatClient() {
        return chatClient;
    }

    public List<Macro> getMacros() {
        return macros;
    }

    public void setMacros(List<Macro> macros) {
        this.macros = macros;
    }

    public void encodeDamageSkins(OutPacket outPacket) {
        DamageSkinSaveData defaultSkin = DamageSkinSaveData.DEFAULT_SKIN;
        outPacket.encodeByte(true); // hasDamageSkins. Always true in this design.
        // check ida for structure
        if (getDamageSkin() != null) {
            getDamageSkin().encode(outPacket);
        } else {
            defaultSkin.encode(outPacket);
        }
        if (getPremiumDamageSkin() != null) {
            getPremiumDamageSkin().encode(outPacket);
        } else {
            defaultSkin.encode(outPacket);
        }
        outPacket.encodeShort(GameConstants.DAMAGE_SKIN_MAX_SIZE); // slotCount
        outPacket.encodeShort(getAccount().getDamageSkins().size());
        for (DamageSkinSaveData dssd : getAccount().getDamageSkins()) {
            dssd.encode(outPacket);
        }
    }

    public boolean canAddMoney(long reqMoney) {
        return getMoney() + reqMoney > 0 && getMoney() + reqMoney < GameConstants.MAX_MONEY;
    }

    public void addPet(Pet pet) {
        getPets().add(pet);
    }

    public void removePet(Pet pet) {
        getPets().remove(pet);
    }

    public void initPets() {
        for (PetItem pi : getCashInventory().getItems()
                .stream()
                .filter(i -> i instanceof PetItem && ((PetItem) i).getActiveState() > 0)
                .map(i -> (PetItem) i).collect(Collectors.toList())) {
            Pet p = getPets().stream().filter(pet -> pet.getItem().equals(pi)).findAny().orElse(null);
            if (p == null) {
                // only create a new pet if the active state is > 0 (active), but isn't added to our own list yet
                p = pi.createPet(this);
                addPet(p);
            }
            getField().broadcastPacket(UserLocal.petActivateChange(p, true, (byte) 0));
        }
    }

    public Pet getPetByIdx(int idx) {
        return getPets().stream()
                .filter(p -> p.getIdx() == idx)
                .findAny()
                .orElse(null);
    }

    public Pet getPetById(int id) {
        for (Pet pet : getPets()) {
            if (pet.getItem().getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public int getFirstPetIdx() {
        int chosenIdx = -1;
        for (int i = 0; i < GameConstants.MAX_PET_AMOUNT; i++) {
            Pet p = getPetByIdx(i);
            if (p == null) {
                chosenIdx = i;
                break;
            }
        }
        return chosenIdx;
    }

    /**
     * Initializes the equips' enchantment stats.
     */
    public void initEquips() {
        for (Equip e : getEquippedInventory().getItems().stream().map(e -> (Equip) e).collect(Collectors.toList())) {
            e.recalcEnchantmentStats();
        }
        for (Equip e : getEquipInventory().getItems().stream().map(e -> (Equip) e).collect(Collectors.toList())) {
            e.recalcEnchantmentStats();
        }
    }



    public MonsterBookInfo getMonsterBookInfo() {
        return monsterBookInfo;
    }

    public void setMonsterBookInfo(MonsterBookInfo monsterBookInfo) {
        this.monsterBookInfo = monsterBookInfo;
    }

    public void setDamageCalc(DamageCalc damageCalc) {
        this.damageCalc = damageCalc;
    }

    public DamageCalc getDamageCalc() {
        return damageCalc;
    }

    // TODO: Apply set effects.

    public void recalcStats(Set<BaseStat> stats) {
        if (stats.contains(BaseStat.mhp) || stats.contains(BaseStat.mhpR)) {
            int newMHP = getTotalStat(BaseStat.mhp);
//            int curMHP = this.maxHP.getAndSet(newMHP);
            if (newMHP < getHP()) {
                setStatAndSendPacket(Stat.hp, newMHP);
            }
            if (JobConstants.isDemonAvenger(getJob())) {
                ((DemonAvenger) getJobHandler()).sendHpUpdate();
            }
        }
        if (stats.contains(BaseStat.mmp) || stats.contains(BaseStat.mmpR)) {
            int newMMP = getTotalStat(BaseStat.mmp);
//            int curMMP = this.maxMP.getAndSet(newMMP);
            if (newMMP < getMP()) {
                setStatAndSendPacket(Stat.mp, newMMP);
            }
        }
        stats.clear();
    }

    /**
     * Gets the current amount of a given stat the character has. Includes things such as skills, items, etc...
     *
     * @param baseStat the requested stat
     * @return the amount of stat
     */
    private double getTotalStatAsDouble(BaseStat baseStat) {
        // TODO cache this completely
        double stat = 0;
        if (baseStat == null) {
            return stat;
        }
        if (baseStat.isNonAdditiveStat()) {
            // stats like ied, final damage
            Set<Integer> statSet = new HashSet<>();
            // Stat gained by passives
            if (getNonAddBaseStats().get(baseStat) != null) {
                statSet.addAll(getNonAddBaseStats().get(baseStat));
            }
            // Stat gained by buffs
            if (getTemporaryStatManager().getNonAddBaseStats().get(baseStat) != null) {
                statSet.addAll(getTemporaryStatManager().getNonAddBaseStats().get(baseStat));
            }
            // Stat gained by equips
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                // maybe add canEquip here
                statSet.addAll(equip.getNonAddBaseStat(baseStat));
            }
            // Stat gained by set effects
            if (getSetNonAddBaseStats().get(baseStat) != null) {
                statSet.addAll(getSetNonAddBaseStats().get(baseStat));
            }
            // Character potential
            for (CharacterPotential cp : getPotentials()) {
                Skill skill = cp.getSkill();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                Map<BaseStat, Integer> stats = si.getBaseStatValues(this, skill.getCurrentLevel());
                statSet.add(stats.getOrDefault(baseStat, 0));
            }
            switch (baseStat) {
                case fd:
                    stat = 100;
                    for (int s : statSet) {
                        stat *= 1 + s / 100d;
                    }
                    stat -= 100;
                    break;
                case ied:
                    stat = 100;
                    for (int s : statSet) {
                        stat *= 1 - s / 100d;
                    }
                    stat = 100 - stat;
                    break;
            }
        } else {
            // Stat allocated by sp
            stat += baseStat.toStat() == null ? 0 : getStat(baseStat.toStat());
            // Stat gained by passives
            stat += getBaseStats().getOrDefault(baseStat, 0L);
            // Stat gained by buffs
            int ctsStat = getTemporaryStatManager().getBaseStats().getOrDefault(baseStat, 0);
            stat += ctsStat;
            // Stat gained by the stat's corresponding "per level" value
            if (baseStat.getLevelVar() != null) {
                stat += getTotalStatAsDouble(baseStat.getLevelVar()) * getLevel();
            }
            // Stat gained by equips
            for (Item item : getEquippedInventory().getItems()) {
                Equip equip = (Equip) item;
                stat += equip.getBaseStat(baseStat);
            }
            // Stat gained by set effects
            stat += getSetBaseStats().getOrDefault(baseStat, 0);
            // Stat gained by the stat's corresponding rate value
            if (baseStat.getRateVar() != null) {
                stat += stat * (getTotalStat(baseStat.getRateVar()) / 100D);
            }
            // --- Everything below this doesn't get affected by the rate var
            // Character potential
            for (CharacterPotential cp : getPotentials()) {
                Skill skill = cp.getSkill();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                Map<BaseStat, Integer> stats = si.getBaseStatValues(this, skill.getCurrentLevel());
                stat += stats.getOrDefault(baseStat, 0);
            }
        }
        return stat;
    }

    public int getTotalStat(BaseStat stat) {
        return (int) getTotalStatAsDouble(stat);
    }

    /**
     * Gets a total list of basic stats that a character has, including from skills, items, etc...
     *
     * @return the total list of basic stats
     */
    public Map<BaseStat, Integer> getTotalBasicStats() {
        Map<BaseStat, Integer> stats = new HashMap<>();
        for (BaseStat bs : BaseStat.values()) {
            stats.put(bs, getTotalStat(bs));
        }
        return stats;
    }

    /**
     * Sets whether or not this user has chosen to use up an item to protect their buffs upon next respawn.
     *
     * @param buffProtector buff protectability
     */
    public void setBuffProtector(boolean buffProtector) {
        this.buffProtector = buffProtector;
    }

    /**
     * Returns whether this user has chosen to activate a buff protector for their next respawn.
     *
     * @return buff protectability
     */
    public boolean hasBuffProtector() {
        return buffProtector;
    }

    /**
     * Returns the item the user has for protecting buffs.
     *
     * @return the Item the user has for prtoecting buffs, or null if there is none.
     */
    public Item getBuffProtectorItem() {
        int[] buffItems = {5133000, 5133001, 4143000};
        Item item = null;
        for (int id : buffItems) {
            item = getConsumeInventory().getItemByItemID(id);
            if (item == null) {
                item = getCashInventory().getItemByItemID(id);
            }
            if (item != null) {
                // just break when an item was found.
                break;
            }
        }
        return item;
    }

    /**
     * Resets the combo kill's timer. Interrupts the previous timer if there was one.
     */
    public void comboKillResetTimer() {
        if (comboKillResetTimer != null && !comboKillResetTimer.isDone()) {
            comboKillResetTimer.cancel(true);
        }
        comboKillResetTimer = EventManager.addEvent(() -> setComboCounter(0), GameConstants.COMBO_KILL_RESET_TIMER, TimeUnit.SECONDS);
    }

    public Map<Integer, Long> getSkillCoolTimes() {
        return skillCoolTimes;
    }

    public void addSkillCoolTime(int skillId, long nextusabletime) {
        getSkillCoolTimes().put(skillId, nextusabletime);
        write(UserLocal.skillCooltimeSetM(skillId, (int) (nextusabletime - Util.getCurrentTimeLong())));
    }

    public void addSkillCoolTime(int skillId, int cooldownTimeMS) {
        addSkillCoolTime(skillId, Util.getCurrentTimeLong() + cooldownTimeMS);
    }

    public void removeSkillCoolTime(int skillId) {
        getSkillCoolTimes().remove(skillId);
    }

    public void resetSkillCoolTime(int skillId) {
        if (hasSkillOnCooldown(skillId)) {
            addSkillCoolTime(skillId, 0);
            write(UserLocal.skillCooltimeSetM(skillId, 0));
        }
    }

    public void reduceSkillCoolTime(int skillId, long amountInMS) {
        if (hasSkillOnCooldown(skillId)) {
            long nextUsableTime = getSkillCoolTimes().get(skillId);
            addSkillCoolTime(skillId, nextUsableTime - amountInMS);
            write(UserLocal.skillCooltimeSetM(skillId, (int) ((nextUsableTime - amountInMS) - System.currentTimeMillis() < 0 ? 0 : (nextUsableTime - amountInMS) - System.currentTimeMillis())));
        }
    }

    public long getRemainingCoolTime(int skillId) {
        if (hasSkillOnCooldown(skillId)) {
            return getSkillCoolTimes().getOrDefault(skillId, System.currentTimeMillis()) - System.currentTimeMillis();
        }
        return 0L;
    }

    /**
     * Checks whether or not a skill is currently on cooldown.
     *
     * @param skillID the skill's id to check
     * @return whether or not a skill is currently on cooldown
     */
    public boolean hasSkillOnCooldown(int skillID) {
        return System.currentTimeMillis() < getSkillCoolTimes().getOrDefault(skillID, 0L);
    }

    /**
     * Checks if a skill is allowed to be cast, according to its cooltime. If it is allowed, it immediately sets
     * the cooltime and stores the next moment where the skill is allowed. Skills without cooltime are always allowed.
     *
     * @param skillID the skill id of the skill to put on cooldown
     * @return whether or not the skill was allowed
     */
    public boolean checkAndSetSkillCooltime(int skillID) {
        if (hasSkillOnCooldown(skillID)) {
            return false;
        } else {
            Skill skill = getSkill(skillID);
            if (skill != null && SkillData.getSkillInfoById(skillID).hasCooltime()) {
                setSkillCooldown(skillID, (byte) skill.getCurrentLevel());
            }
            return true;
        }
    }

    /**
     * Sets a skill's cooltime according to their property in the WZ files, and stores the moment where the skill
     * comes off of cooldown.
     *
     * @param skillID the skill's id to set
     * @param slv     the current skill level
     */
    public void setSkillCooldown(int skillID, int slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        if (si != null) {
            int cdInSec = si.getValue(SkillStat.cooltime, slv);
            int cdInMillis = cdInSec > 0 ? cdInSec * 1000 : si.getValue(SkillStat.cooltimeMS, slv);
            int alteredcd = getJobHandler().alterCooldownSkill(skillID);
            if (alteredcd >= 0) {
                cdInMillis = alteredcd;
            }
            // RuneStone of Skill
            if (getTemporaryStatManager().hasStatBySkillId(RuneStone.LIBERATE_THE_RUNE_OF_SKILL) && cdInMillis > 5000 && !si.isNotCooltimeReset()) {
                cdInMillis = 5000;
            }
            if (!hasSkillCDBypass() && cdInMillis > 0) {
                addSkillCoolTime(skillID, System.currentTimeMillis() + cdInMillis);
            }
        }
    }

    public CharacterPotentialMan getPotentialMan() {
        return potentialMan;
    }

    public Set<CharacterPotential> getPotentials() {
        return potentials;
    }

    public void setPotentials(Set<CharacterPotential> potentials) {
        this.potentials = potentials;
    }

    public int getHonorExp() {
        return getAvatarData().getCharacterStat().getHonorExp();
    }

    public void setHonorExp(int honorExp) {
        getAvatarData().getCharacterStat().setHonorExp(honorExp);
    }

    /**
     * Adds honor exp to this Char, and sends a packet to the client with the new honor exp.
     * Honor exp added may be negative, but the total honor exp will never go below 0.
     *
     * @param exp the exp to add (may be negative)
     */
    public void addHonorExp(int exp) {
        setHonorExp(Math.max(0, getHonorExp() + exp));
        write(WvsContext.characterHonorExp(getHonorExp()));
    }

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public Set<LinkSkill> getLinkSkills() {
        return getAccount().getLinkSkills().stream()
                .filter(ls -> ls.getUsingID() == getId())
                .collect(Collectors.toSet());
    }

    /**
     * Adds a skill to this Char. If the Char already has this skill, just changes the levels.
     *
     * @param skillID      the skill's id to add
     * @param currentLevel the current level of the skill
     * @param masterLevel  the master level of the skill
     */
    public void addSkill(int skillID, int currentLevel, int masterLevel) {
        Skill skill = SkillData.getSkillDeepCopyById(skillID);
        if (skill == null && !SkillConstants.isMakingSkillRecipe(skillID)) {
            log.error("No such skill found.");
            return;
        }
        skill.setCurrentLevel(currentLevel);
        skill.setMasterLevel(masterLevel);
        addSkill(skill);
        write(WvsContext.changeSkillRecordResult(skill));
    }

    public long getRuneCooldown() {
        return runeStoneCooldown;
    }

    public void setRuneCooldown(long runeCooldown) {
        this.runeStoneCooldown = runeCooldown;
    }

    public MemorialCubeInfo getMemorialCubeInfo() {
        return memorialCubeInfo;
    }

    public void setMemorialCubeInfo(MemorialCubeInfo memorialCubeInfo) {
        this.memorialCubeInfo = memorialCubeInfo;
    }

    public Set<Familiar> getFamiliars() {
        return familiars;
    }

    public void setFamiliars(Set<Familiar> familiars) {
        this.familiars = familiars;
    }

    public boolean hasFamiliar(int familiarID) {
        return getFamiliars().stream().anyMatch(f -> f.getFamiliarID() == familiarID);
    }

    public Familiar getFamiliarByID(int familiarID) {
        return getFamiliars().stream().filter(f -> f.getFamiliarID() == familiarID).findAny().orElse(null);
    }

    public void addFamiliar(Familiar familiar) {
        getFamiliars().add(familiar);
    }

    public void removeFamiliarByID(int familiarID) {
        removeFamiliar(getFamiliarByID(familiarID));
    }

    public void removeFamiliar(Familiar familiar) {
        if (familiar != null) {
            getFamiliars().remove(familiar);
        }
    }

    public void setActiveFamiliar(Familiar activeFamiliar) {
        this.activeFamiliar = activeFamiliar;
    }

    public Familiar getActiveFamiliar() {
        return activeFamiliar;
    }

    public boolean hasSkillCDBypass() {
        return skillCDBypass;
    }

    public void setSkillCDBypass(boolean skillCDBypass) {
        this.skillCDBypass = skillCDBypass;
    }


    public Set<StolenSkill> getStolenSkills() {
        return stolenSkills;
    }

    public void setStolenSkills(Set<StolenSkill> stolenSkills) {
        this.stolenSkills = stolenSkills;
    }

    public void addStolenSkill(StolenSkill stolenSkill) {
        getStolenSkills().add(stolenSkill);
    }

    public void removeStolenSkill(StolenSkill stolenSkill) {
        if (stolenSkill != null) {
            getStolenSkills().remove(stolenSkill);
        }
    }

    public StolenSkill getStolenSkillByPosition(int position) {
        return getStolenSkills().stream().filter(ss -> ss.getPosition() == position).findAny().orElse(null);
    }

    public StolenSkill getStolenSkillBySkillId(int skillId) {
        return getStolenSkills().stream().filter(ss -> ss.getSkillid() == skillId).findAny().orElse(null);
    }


    public Set<ChosenSkill> getChosenSkills() {
        return chosenSkills;
    }

    public void setChosenSkills(Set<ChosenSkill> chosenSkills) {
        this.chosenSkills = chosenSkills;
    }

    public void addChosenSkill(ChosenSkill chosenSkill) {
        getChosenSkills().add(chosenSkill);
    }

    public void removeChosenSkill(ChosenSkill chosenSkill) {
        if (chosenSkill != null) {
            getChosenSkills().remove(chosenSkill);
        }
    }

    public ChosenSkill getChosenSkillByPosition(int position) {
        return getChosenSkills().stream().filter(ss -> ss.getPosition() == position).findAny().orElse(null);
    }

    public boolean isChosenSkillInStolenSkillList(int skillId) {
        return getStolenSkills().stream().filter(ss -> ss.getSkillid() == skillId).findAny().orElse(null) != null;
    }

    public Map<BaseStat, Long> getBaseStats() {
        return baseStats;
    }

    public Map<BaseStat, Set<Integer>> getNonAddBaseStats() {
        return nonAddBaseStats;
    }

    /**
     * Adds a BaseStat's amount to this Char's BaseStat cache.
     *
     * @param bs     The BaseStat
     * @param amount the amount of BaseStat to add
     */
    public void addBaseStat(BaseStat bs, int amount) {
        if (bs != null) {
            if (bs.isNonAdditiveStat()) {
                if (!getNonAddBaseStats().containsKey(bs)) {
                    getNonAddBaseStats().put(bs, new HashSet<>());
                }
                getNonAddBaseStats().get(bs).add(amount);
//                chatMessage("[addBaseStat] key: %s value: %s", bs.toString(), Integer.toString(amount));
            } else {
                getBaseStats().put(bs, getBaseStats().getOrDefault(bs, 0L) + amount);
//                chatMessage("[addBaseStat else] key: %s value: %s", bs.toString(), Integer.toString(amount));
            }
        }
    }

    /**
     * Removes a BaseStat's amount from this Char's BaseStat cache.
     *
     * @param bs     The BaseStat
     * @param amount the amount of BaseStat to remove
     */
    public void removeBaseStat(BaseStat bs, int amount) {
        addBaseStat(bs, -amount);
    }

    public void addItemToInventory(int id, int quantity) {
        if (ItemConstants.isEquip(id)) {  //Equip
            Equip equip = ItemData.getEquipDeepCopyFromID(id, false);
            addItemToInventory(equip.getInvType(), equip, false);
            getClient().write(WvsContext.inventoryOperation(true, false,
                    Add, (short) equip.getBagIndex(), (byte) -1, 0, equip));

        } else {    //Item
            Item item = ItemData.getItemDeepCopy(id);
            item.setQuantity(quantity);
            addItemToInventory(item);
            getClient().write(WvsContext.inventoryOperation(true, false,
                    Add, (short) item.getBagIndex(), (byte) -1, 0, item));

        }
    }

    public int getSpentHyperSp() {
        int sp = 0;
        for (int skillID = 80000400; skillID <= 80000418; skillID++) {
            Skill skill = getSkill(skillID);
            if (skill != null) {
                sp += SkillConstants.getTotalNeededSpForHyperStatSkill(skill.getCurrentLevel());
            }
        }
        return sp;
    }

    public int getSpentPassiveHyperSkillSp() {
        int sp = 0;
        for (Skill skill : getSkills()) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            if (si.getHyper() == 1) {
                sp += skill.getCurrentLevel();
            }
        }
        return sp;
    }

    public int getSpentActiveHyperSkillSp() {
        int sp = 0;
        for (Skill skill : getSkills()) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            if (si.getHyper() == 2) {
                sp += skill.getCurrentLevel();
            }
        }
        return sp;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int[] getHyperRockFields() {
        return hyperrockfields;
    }

    public void setHyperRockFields(int[] hyperrockfields) {
        this.hyperrockfields = hyperrockfields;
    }

    public int[] getTowerChairs() {
        return towerChairs;
    }

    public void setTowerChairs(int[] towerChairs) {
        this.towerChairs = towerChairs;
    }

    public boolean isChangingChannel() {
        return changingChannel;
    }

    public void setChangingChannel(boolean changingChannel) {
        this.changingChannel = changingChannel;
    }

    public int getPartyID() {
        return partyID;
    }

    public void setPartyID(int partyID) {
        this.partyID = partyID;
    }

    public byte getMonsterParkCount() {
        return monsterParkCount;
    }

    public void setMonsterParkCount(byte monsterParkCount) {
        this.monsterParkCount = monsterParkCount;
    }

    public TownPortal getTownPortal() {
        return townPortal;
    }

    public void setTownPortal(TownPortal townPortal) {
        this.townPortal = townPortal;
    }

    public TradeRoom getTradeRoom() {
        return tradeRoom;
    }

    public void setTradeRoom(TradeRoom tradeRoom) {
        this.tradeRoom = tradeRoom;
    }

    public void damage(int damage) {
        damage(damage, false);
    }

    public void damage(int damage, boolean showHitAboveHead) {
        HitInfo hi = new HitInfo();
        hi.hpDamage = damage;
        if (showHitAboveHead) {
            write(UserPacket.effect(Effect.changeHPEffect(-damage)));
        }
        getJobHandler().handleHit(getClient(), hi);
    }

    public void die() {
        setStatAndSendPacket(Stat.hp, 0);
        write(UserLocal.openUIOnDead(true, getBuffProtectorItem() != null, false, false, false, ReviveType.NORMAL, 0));
    }

    public void changeChannel(byte channelId) {
        changeChannelAndWarp(channelId, getFieldID());
    }

    public void changeChannelAndWarp(byte channelId, int fieldId) {
        setChangingChannel(true);
        Field field = getField();
        if (getFieldID() != fieldId) {
            setField(getOrCreateFieldByCurrentInstanceType(fieldId));
        }
        DatabaseManager.saveToDB(getAccount());
        int worldID = getClient().getChannelInstance().getWorldId().getVal();
        World world = Server.getInstance().getWorldById(worldID);
        field.removeChar(this);
        Channel channel = world.getChannelById(channelId);
        channel.addClientInTransfer(channelId, getId(), getClient());
        short port = (short) channel.getPort();
        write(ClientSocket.migrateCommand(true, port));
    }

    public void changeChannelAndWarp(int channel, int fieldID) {
        Client c = getClient();
        c.setOldChannel(c.getChannel());
        changeChannelAndWarp((byte) channel, fieldID);
    }

    @Override
    public String toString() {
        return "Char{" +
                "(" + super.toString() +
                ")id=" + id +
                ", name=" + getName() +
                '}';
    }

    public void setBattleRecordOn(boolean battleRecordOn) {
        this.battleRecordOn = battleRecordOn;
    }

    public boolean isBattleRecordOn() {
        return battleRecordOn;
    }

    public void checkAndRemoveExpiredItems() {
        Inventory[] inventories = new Inventory[]{getEquippedInventory(), getEquipInventory(), getConsumeInventory(),
                getEtcInventory(), getInstallInventory(), getCashInventory()};
        Set<Item> expiredItems = new HashSet<>();
        for (Inventory inv : inventories) {
            expiredItems.addAll(
                    inv.getItems().stream()
                            .filter(item -> item.getDateExpire().isExpired())
                            .collect(Collectors.toSet())
            );
        }
        List<Integer> expiredItemIDs = expiredItems.stream().map(Item::getItemId).collect(Collectors.toList());
        write(WvsContext.message(MessageType.GENERAL_ITEM_EXPIRE_MESSAGE, expiredItemIDs));
        for (Item item : expiredItems) {
            consumeItem(item);
            if (item.getItemId() == ItemConstants.GAGAGUCCI) {
                int qid = QuestConstants.PVAC_DATA;
                if (getRecordFromQuestEx(qid, "vac") == 1) {
                    setQuestRecordEx(qid, "vac", 0);
                    chatMessage("Pvac has been disabled since your item has expired");
                }
            }
        }
    }

    public boolean isGuildMaster() {
        return getGuild() != null && getGuild().getLeaderID() == getId();
    }

    /**
     * Checks if this Char has any of the given quests in progress. Also true if the size of the given set is 0.
     *
     * @param quests the set of quest ids to check
     * @return whether or not this Char has any of the given quests
     */
    public boolean hasAnyQuestsInProgress(Set<Integer> quests) {
        return quests.size() == 0 || quests.stream().anyMatch(this::hasQuestInProgress);
    }

    public int getPreviousFieldID() {
        return previousFieldID == 0 || previousFieldID == 999999999 ? 100000000 : previousFieldID;
    }

    public void setPreviousFieldID(int previousFieldID) {
        this.previousFieldID = previousFieldID;
    }

    public long getNextRandomPortalTime() {
        return nextRandomPortalTime;
    }

    public void setNextRandomPortalTime(long nextRandomPortalTime) {
        this.nextRandomPortalTime = nextRandomPortalTime;
    }

    public Set<MatrixRecord> getMatrixRecords() {
        return matrixRecords;
    }

    public void setMatrixRecords(Set<MatrixRecord> matrixRecords) {
        this.matrixRecords = matrixRecords;
    }

    public int getNodeShards() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.NODESHARD_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.NODESHARD_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty("count"));
    }

    public void setNodeShards(int nodeShards) {
        Quest quest = getQuestManager().getQuestById(QuestConstants.NODESHARD_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.NODESHARD_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty("count", nodeShards);
        write(WvsContext.questRecordExMessage(quest));
    }

    public int getDojoPoints() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.DOJO_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.DOJO_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty("count"));
    }

    public void setDojoPoints(int dojoPoints) {
        Quest quest = getQuestManager().getQuestById(QuestConstants.DOJO_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.DOJO_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty("count", dojoPoints);
        write(WvsContext.questRecordExMessage(quest));
    }

    public int getPQPoints() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.PQPOINT_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.PQPOINT_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty("count"));
    }

    public void setPQPoints(int PQPoints) {
        Quest quest = getQuestManager().getQuestById(QuestConstants.PQPOINT_COUNT);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.PQPOINT_COUNT);
            quest.setProperty("count", 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty("count", PQPoints);
        write(WvsContext.questRecordExMessage(quest));
    }

    public void setMVPMileage(int mvp) {
        Quest quest = getQuestManager().getQuestById(QuestConstants.MVP_MILEAGE);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.MVP_MILEAGE);
            quest.setProperty("amount", 0);
            quest.setProperty("todayAmount", 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty("todayAmount_20190910", mvp);
        quest.setProperty("amount", mvp);
        write(WvsContext.questWorldShareMessage(quest));
    }

    public int getMVPMileage() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.MVP_MILEAGE);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.MVP_MILEAGE);
            quest.setProperty("amount", 0);
            getQuestManager().addQuest(quest);
        }
        return Integer.parseInt(quest.getProperty("amount"));
    }

    public void setQuestRecordEx(int questID, String property, int Amount) {
        Quest quest = getQuestManager().getQuestById(questID);
        if (quest == null) {
            quest = QuestData.createQuestFromId(questID);
            quest.setProperty(property, 0);
            getQuestManager().addQuest(quest);
        }
        quest.setProperty(property, Amount);
        write(WvsContext.questRecordExMessage(quest));
    }

    public int getZeroWeaponRank() {
        Quest quest = getQuestManager().getQuestById(QuestConstants.ZERO_DATA);
        if (quest == null) {
            quest = QuestData.createQuestFromId(QuestConstants.ZERO_DATA);
            quest.setProperty("wepRank", 0);
            getQuestManager().addQuest(quest);
        }
        if (quest.getProperty("wepRank") == null) {
            quest.setProperty("wepRank", 0);
        }
        return Integer.parseInt(quest.getProperty("wepRank"));
    }



    public void addNodeShards(int shards) {
        setNodeShards(getNodeShards() + shards);
    }

    public void addDojoPoints(int dojoPoints) {
        setDojoPoints(getDojoPoints() + dojoPoints);
    }

    public void addPqPoints(int pqPoints) {
        setPQPoints(getPQPoints() + pqPoints);
    }

    public UnionMember createUnionMember() {
        return new UnionMember(1, this, null);
    }

    public Union getUnion() {
        return getAccount().getUnion();
    }

    public void clearCurrentDirectionNode() {
        this.currentDirectionNode.clear();
    }

    public int getCurrentDirectionNode(int node) {
        Integer direction = currentDirectionNode.getOrDefault(node, null);
        if (direction == null) {
            currentDirectionNode.put(node, 0);
        }
        return currentDirectionNode.get(node);
    }

    public void increaseCurrentDirectionNode(int node) {
        Integer direction = currentDirectionNode.getOrDefault(node, null);
        if (direction == null) {
            currentDirectionNode.put(node, 1);
        } else {
            currentDirectionNode.put(node, direction + 1);
        }
    }

    public void punishLieDetectorEvasion() {
        if (getLieDetectorAnswer().length() > 0) {
            failedLieDetector();
        }
    }

    public String getLieDetectorAnswer() {
        return lieDetectorAnswer;
    }

    public void setLieDetectorAnswer(String answer) {
        lieDetectorAnswer = answer;
    }

    public void failedLieDetector() {
        setLieDetectorAnswer("");
        chatMessage(SpeakerChannel, "You have failed the Lie Detector test.");

        getClient().write(WvsContext.antiMacroResult(null, AntiMacro.AntiMacroResultType.AntiMacroRes_Fail.getVal(), AntiMacro.AntiMacroType.AntiMacroFieldRequest.getVal()));

        // TODO: handle fail
    }

    public void passedLieDetector() {
        setLieDetectorAnswer("");
        chatMessage(SpeakerChannel, "You have passed the Lie Detector test!");

        getClient().write(WvsContext.antiMacroResult(null, AntiMacro.AntiMacroResultType.AntiMacroRes_Success.getVal(), AntiMacro.AntiMacroType.AntiMacroFieldRequest.getVal()));

        // TODO: handle pass
    }

    public boolean sendLieDetector() {
        return sendLieDetector(false);
    }

    public boolean sendLieDetector(boolean force) {
        // LD ran too recently (15 min)
        if (!force && lastLieDetector != 0 && System.currentTimeMillis() - lastLieDetector < 900_000L) {
            return false;
        }

        // TODO: don't allow more than 3 refreshes

        lieDetectorAnswer = "";
        String font = AntiMacro.FONTS[Util.getRandom(AntiMacro.FONTS.length - 1)];

        String options = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 1; i <= 6; i++) {
            if (Util.getRandom(1) == 0) {
                options = options.toUpperCase();
            } else {
                options = options.toLowerCase();
            }

            lieDetectorAnswer += options.charAt(Util.getRandom(options.length() - 1));
        }

        try {
            AntiMacro am = new AntiMacro(font, lieDetectorAnswer);
            lastLieDetector = System.currentTimeMillis();

            byte[] image = am.generateImage(196, 44, Color.BLACK, AntiMacro.getRandomColor());
            getClient().write(WvsContext.antiMacroResult(image, AntiMacro.AntiMacroResultType.AntiMacroRes.getVal(), AntiMacro.AntiMacroType.AntiMacroFieldRequest.getVal()));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public OffenseManager getOffenseManager() {
        return getUser().getOffenseManager();
    }

    /**
     * Applies the mp consumption of a skill.
     *
     * @param skillID the skill's id
     * @param slv     the current skill level
     * @return whether the consumption was successful (unsuccessful = not enough mp)
     */
    public boolean applyMpCon(int skillID, int slv) {
        int curMp = getStat(Stat.mp);
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        if (si == null) {
            return true;
        }
        int mpCon = (int) (si.getValue(SkillStat.mpCon, slv) * getBaseStats().getOrDefault(BaseStat.costmpR, (long) 100) / 100D);
        boolean hasEnough = curMp >= mpCon;
        if (hasEnough// Kinesis Doesn't use MP
                && !JobConstants.isKinesis(getJob())

                // Luminous' Dark Skills do not consume MP if in Equilibrium mode
                && !(JobConstants.isLuminous(getJob())
                && getTemporaryStatManager().hasStat(Larkness)
                && getTemporaryStatManager().getOption(Larkness).rOption == Luminous.EQUILIBRIUM2
                && SkillConstants.isLarknessDarkSkill(skillID))

                && getTemporaryStatManager().getOptByCTSAndSkill(IndieBooster, Kanna.SPIRITS_DOMAIN) == null
        ) {

            addStatAndSendPacket(Stat.mp, -mpCon);
        }
        return hasEnough;
    }

    public boolean hasTutor() {
        return tutor;
    }

    public void hireTutor(boolean set) {
        tutor = set;
        write(UserLocal.hireTutor(set));
    }

    /**
     * Shows tutor automated message (the client is taking the message information from wz).
     *
     * @param id       the id of the message.
     * @param duration message duration
     */
    public void tutorAutomatedMsg(int id, int duration) {
        if (!tutor) {
            hireTutor(true);
        }
        write(UserLocal.tutorMsg(id, duration));
    }

    /**
     * Shows tutor custom message (you decide which message the tutor will say).
     *
     * @param message  your custom message
     * @param width    size of the message box
     * @param duration message duration
     */
    public void tutorCustomMsg(String message, int width, int duration) {
        if (!tutor) {
            hireTutor(true);
        }
        write(UserLocal.tutorMsg(message, width, duration));
    }

    public void setTransferField(int fieldID) {
        this.transferField = fieldID;
        this.transferFieldReq = fieldID == 0 ? 0 : getField().getId();
    }

    public int getTransferField() {
        return transferField;
    }

    public int getTransferFieldReq() {
        return transferFieldReq;
    }

    public void setMakingSkillLevel(int skillID, int level) {
        Skill skill = getSkill(skillID);
        if (skill != null) {
            skill.setCurrentLevel((level << 24) + getMakingSkillProficiency(skillID));
            addSkill(skill);
            write(WvsContext.changeSkillRecordResult(skill));
        }
    }

    public int getMakingSkillLevel(int skillID) {
        return (getSkillLevel(skillID) >> 24) <= 0 ? 0 : getSkillLevel(skillID) >> 24;
    }

    public void setMakingSkillProficiency(int skillID, int proficiency) {
        Skill skill = getSkill(skillID);
        if (skill != null) {
            skill.setCurrentLevel((getMakingSkillLevel(skillID) << 24) + proficiency);
            addSkill(skill);
            write(WvsContext.changeSkillRecordResult(skill));
        }
    }

    public int getMakingSkillProficiency(int skillID) {
        return (getSkillLevel(skillID) & 0xFFFFFF) <= 0 ? 0 : getSkillLevel(skillID) & 0xFFFFFF;
    }

    public void addMakingSkillProficiency(int skillID, int amount) {
        int makingSkillID = SkillConstants.recipeCodeToMakingSkillCode(skillID);
        int level = getMakingSkillLevel(makingSkillID);

        int neededExp = SkillConstants.getNeededProficiency(level);
        if (neededExp <= 0) {
            return;
        }
        int exp = getMakingSkillProficiency(makingSkillID);
        if (exp >= neededExp) {
            write(UserLocal.chatMsg(ChatType.GameDesc, "You can't gain any more Herbalism mastery until you level your skill."));
            write(UserLocal.chatMsg(ChatType.GameDesc, "See the appropriate NPC in Ardentmill to level up."));
            setMakingSkillProficiency(makingSkillID, neededExp);
            return;
        }
        int newExp = exp + amount;
        write(UserLocal.chatMsg(ChatType.GameDesc, SkillConstants.getMakingSkillName(makingSkillID) + "'s mastery increased. (+" + amount + ")"));
        if (newExp >= neededExp) {
            write(UserLocal.noticeMsg("You've accumulated " + SkillConstants.getMakingSkillName(makingSkillID) + " mastery. See an NPC in town to level up.", true));
            setMakingSkillProficiency(makingSkillID, neededExp);
        } else {
            setMakingSkillProficiency(makingSkillID, newExp);
        }
    }

    public void makingSkillLevelUp(int skillID) {
        int level = getMakingSkillLevel(skillID);
        int neededExp = SkillConstants.getNeededProficiency(level);
        if (neededExp <= 0) {
            return;
        }
        int exp = getMakingSkillProficiency(skillID);
        if (exp >= neededExp) {
            setMakingSkillProficiency(skillID, 0);
            setMakingSkillLevel(skillID, level + 1);
            Stat trait = Stat.craftEXP;
            switch (skillID) {
                case 92000000:
                    trait = Stat.senseEXP;
                    break;
                case 92010000:
                    trait = Stat.willEXP;
                    break;
            }
            addTraitExp(trait, (int) Math.pow(2, (level + 1) + 2));
            write(FieldPacket.fieldEffect(FieldEffect.playSound("profession/levelup", 100)));
        }
    }

    public int getTotalAf() {
        return getEquippedInventory().getItems().stream().mapToInt(item -> ((Equip) item).getArc()).sum();
    }

    public void addNx(int nx) {
        User user = getUser();
        user.addNXPrepaid(nx);
        if (nx != 0) {
            write(UserPacket.progressMessageFont(ProgressMessageFontType.Normal, 16, ProgressMessageColourType.SkyBlue, 300, String.format("You have gained %,d NX.", nx)));
            write(WvsContext.setMaplePoints(user.getNxPrepaid()));
        }
    }

    public void deductNX(int nx) {
        User user = getUser();
        user.addNXPrepaid(nx);
        if (nx != 0) {
            write(UserPacket.scriptProgressMessage(String.format("You have lost %,d NX.", nx)));
            write(UserPacket.progressMessageFont(ProgressMessageFontType.Normal, 16, ProgressMessageColourType.Red, 300, String.format("You have lost %,d NX.", nx)));
            write(WvsContext.setMaplePoints(user.getNxPrepaid()));
        }
    }

    // No idea if this correct
    public void addDP(int dp) {
        User user = getUser();
        user.setDonationPoints(dp);
        if (dp != 0) {
            write(UserPacket.progressMessageFont(ProgressMessageFontType.Normal, 16, ProgressMessageColourType.White, 300, String.format("You have gained %,d DP.", dp)));
            write(WvsContext.setMaplePoints(user.getDonationPoints()));
        }
    }

    public void initBlessingSkillNames() {
        Account account = getAccount();
        Char fairyChar = null;
        for (Char chr : account.getCharacters()) {
            if (!chr.equals(this)
                    && chr.getLevel() >= 10
                    && (fairyChar == null || chr.getLevel() > fairyChar.getLevel())) {
                fairyChar = chr;
            }
        }
        if (fairyChar != null) {
            setBlessingOfFairy(fairyChar.getName());
        }
        Char empressChar = null;
        for (Char chr : account.getCharacters()) {
            if (!chr.equals(this)
                    && (JobConstants.isCygnusKnight(chr.getJob()) || JobConstants.isMihile(chr.getJob())
                    && chr.getLevel() >= 5
                    && (empressChar == null || chr.getLevel() > empressChar.getLevel()))) {
                empressChar = chr;
            }
        }
        if (empressChar != null) {
            setBlessingOfEmpress(empressChar.getName());
        }
    }

    public void initBlessingSkills() {
        Char fairyChar = getAccount().getCharByName(getBlessingOfFairy());
        if (fairyChar != null) {
            addSkill(SkillConstants.getFairyBlessingByJob(getJob()),
                    Math.min(20, fairyChar.getLevel() / 10), 20);
        }
        Char empressChar = getAccount().getCharByName(getBlessingOfEmpress());
        if (empressChar != null) {
            addSkill(SkillConstants.getEmpressBlessingByJob(getJob()),
                    Math.min(30, empressChar.getLevel() / 5), 30);
        }
    }

    public Map<Integer, Integer> getHyperPsdSkillsCooltimeR() {
        return hyperPsdSkillsCooltimeR;
    }

    public void setHyperPsdSkillsCooltimeR(Map<Integer, Integer> hyperPsdSkillsCooltimeR) {
        this.hyperPsdSkillsCooltimeR = hyperPsdSkillsCooltimeR;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public void setQuickslotKeys(List<Integer> quickslotKeys) {
        this.quickslotKeys = quickslotKeys;
    }

    public List<Integer> getQuickslotKeys() {
        return quickslotKeys;
    }

    public Dragon getDragon() {
        Dragon dragon = null;
        if (getJobHandler() instanceof Evan) {
            dragon = ((Evan) getJobHandler()).getDragon();
        }
        return dragon;
    }

    public void renewDragon() {
        Dragon dragon = null;
        if (dragon != null) {
            getField().broadcastPacket(DragonPool.removeDragon(dragon));
            dragon = null;
        }
        if (dragon != null) {
            getField().broadcastPacket(DragonPool.createDragon(dragon));
        }
    }

    public void rebuildQuestExValues(boolean save) {
        if (save) {
            for (Map.Entry<Integer, QuestEx> questEx : questRecordEx.entrySet()) {
                String qrValue = "";
                for (Map.Entry<String, String> quest : questEx.getValue().getValues().entrySet()) {
                    qrValue += String.format("%s=%s;", quest.getKey(), quest.getValue());
                }
                questsExStorage.put(questEx.getKey(), qrValue.substring(0, qrValue.length() - 1));
            }
        } else {
            for (Map.Entry<Integer, String> questEx : questsExStorage.entrySet()) {
                String[] qrValues = questEx.getValue().split(";");
                QuestEx quest = new QuestEx(questEx.getKey());
                for (String qrValue : qrValues) {
                    String[] val = qrValue.split("=");
                    quest.setValue(val[0], val[1]);
                }
                questRecordEx.put(quest.getQuestID(), quest);
            }
        }
    }

    public boolean setQuestEx(int questID, String key, String value) {
        return setQuestEx(questID, key, value, false);
    }

    public boolean setQuestEx(int questID, String key, String value, boolean onMigrate) {
        if (key == null || key.isEmpty() || key.equals("")) {
            return false;
        }
        if (value.equals("DayN")) {
            return false;
        }
        QuestEx str = getQuestRecordEx().getOrDefault(questID, null);
        if (str == null) {
            getQuestRecordEx().put(questID, new QuestEx(questID));
            str = getQuestRecordEx().getOrDefault(questID, null);
            if (str == null) {
                return false;
            }
        }
        if (str.setValue(key, value)) {
            if (!onMigrate)
                rebuildQuestExValues(true);
            return true;
        }
        return false;
    }

    public String getQuestEx(int questID, String key) {
        QuestEx str = getQuestRecordEx().getOrDefault(questID, null);
        if (str != null) {
            return str.getValue(key);
        }
        return null;
    }

    public QuestEx getQuestEx(int questID) {
        return getQuestRecordEx().getOrDefault(questID, null);
    }

    public Map<Integer, QuestEx> getQuestRecordEx() {
        return questRecordEx;
    }

    /**
     * Checks if this Char has a skill with at least a given level.
     *
     * @param skillID the skill to get
     * @param slv     the minimum skill level
     * @return whether or not this Char has the skill with the given skill level
     */
    public boolean hasSkillWithSlv(int skillID, short slv) {
        Skill skill = getSkill(skillID);
        return skill != null && skill.getCurrentLevel() >= slv;
    }

    public Set<Friend> getOnlineFriends() {
        Set<Friend> friends = new HashSet<>(getFriends());
        friends.addAll(getUser().getFriends());
        friends = friends.stream().filter(Friend::isOnline).collect(Collectors.toSet());
        return friends;
    }

    public boolean isTalkingToNpc() {
        return talkingToNpc;
    }

    public void setTalkingToNpc(boolean talkingToNpc) {
        this.talkingToNpc = talkingToNpc;
    }

    public void useStatChangeItem(Item item, boolean consume) {
        TemporaryStatManager tsm = getTemporaryStatManager();
        int itemID = item.getItemId();
        Map<SpecStat, Integer> specStats = ItemData.getItemInfoByID(itemID).getSpecStats();
        if (specStats.size() > 0) {
            ItemBuffs.giveItemBuffsFromItemID(this, tsm, itemID);
        } else {
            switch (itemID) {
                case 2050004: // All cure
                    tsm.removeAllDebuffs();
                    break;
                default:
                    chatMessage(ChatType.Mob, String.format("Unhandled stat change item %d", itemID));
            }
        }
        if (consume) {
            consumeItem(item);
        }
        dispose();
    }

    public void incrementUnionRank() {
        Union union = getUnion();
        int curUnionRank = union.getUnionRank();
        if (curUnionRank == 0) {
            union.setUnionRank(101);
        } else {
            if (union.getUnionRank() % 100 == 5) {
                union.setUnionRank(curUnionRank + 95);
            }
            union.setUnionRank(curUnionRank + 1);
        }
        Quest quest = getQuestManager().getQuests().get(QuestConstants.UNION_RANK);
        quest.setProperty("rank", union.getUnionRank());
        write(WvsContext.questRecordExMessage(quest));
    }

    public int getActiveUnionPreset() {
        QuestManager qm = getQuestManager();
        Quest quest = qm.getQuestById(QuestConstants.UNION_PRESET);
        if (quest == null) {
            qm.addQuest(QuestConstants.UNION_PRESET);
            quest = qm.getQuestById(QuestConstants.UNION_PRESET);
            quest.setProperty("presetNo", 0);
            write(WvsContext.questRecordExMessage(quest));
        }
        return quest.getIntProperty("presetNo");
    }

    public void setActiveUnionPreset(int preset) {
        QuestManager qm = getQuestManager();
        Quest quest = qm.getQuestById(QuestConstants.UNION_PRESET);
        if (quest == null) {
            qm.addQuest(QuestConstants.UNION_PRESET);
            quest = qm.getQuestById(QuestConstants.UNION_PRESET);
        }
        quest.setProperty("presetNo", preset);
        write(WvsContext.questRecordExMessage(quest));
    }

    public void encodeSymbolData(OutPacket outPacket) {
        Set<Item> equips = new HashSet<>(getEquippedInventory().getItems());
        equips.addAll(getEquipInventory().getItems());
        Set<Equip> symbols = equips.stream()
                .filter(i -> ItemConstants.isArcaneSymbol(i.getItemId()))
                .map(i -> (Equip) i)
                .collect(Collectors.toSet());
        for (Equip symbol : symbols) {
            int bagIndex = symbol.getInvType() == EQUIPPED ? -symbol.getBagIndex() : symbol.getBagIndex();
            outPacket.encodeInt(bagIndex);
            symbol.encodeSymbolData(outPacket);
        }
        outPacket.encodeInt(0); // indicate end of previous structure
    }

    public Item getItemBySn(long itemSn) {
        Inventory[] invs = new Inventory[]{getEquippedInventory(), getEquipInventory(), getConsumeInventory(),
                getEtcInventory(), getInstallInventory(), getCashInventory()};
        Item item = null;
        for (Inventory i : invs) {
            item = i.getItemBySN(itemSn);
            if (item != null) {
                break;
            }
        }
        return item;
    }


    public void addCharacterPotentials() {
        CharacterPotentialMan cpm = getPotentialMan();
        Map<Byte, CharacterPotential> potentialMap = new HashMap<>();
        for (CharacterPotential cp : getPotentials()) {
            potentialMap.put(cp.getKey(), cp);
        }
        List<CharacterPotential> potentials = CharacterPotentialMan.generateRandomPotential(3, cpm.getGrade(), true, potentialMap);
        for (CharacterPotential cp : potentials) {
            cpm.addPotential(cp);
        }
    }

    public void encodeChatInfo(OutPacket outPacket, String msg) {
        // vm'd sub
        outPacket.encodeString(getName());
        outPacket.encodeString(msg);
        outPacket.encodeInt(getAccount().getId());
        outPacket.encodeInt(getId());
        outPacket.encodeByte(getAccount().getWorldId()); // ?
        outPacket.encodeInt(getId());
    }

    public boolean isSkillInfoMode() {
        return skillInfoMode;
    }

    public void setSkillInfoMode(boolean skillInfoMode) {
        this.skillInfoMode = skillInfoMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void initDamageSkin() {
        QuestManager qm = getQuestManager();
        Quest q = qm.getQuestById(QuestConstants.DAMAGE_SKIN);
        if (q != null) {
            DamageSkinSaveData dssd = getAccount().getDamageSkinBySkinID(Integer.parseInt(q.getQRValue()));
            setDamageSkin(dssd);
        }
    }

    public List<NpcShopItem> getBuyBack() {
        return buyBack;
    }

    public void addItemToBuyBack(Item item) {
        NpcShopItem nsi = new NpcShopItem();
        nsi.setItemID(item.getItemId());
        nsi.setItem(item);
        nsi.setBuyBack(true);
        int cost;
        if (ItemConstants.isEquip(item.getItemId())) {
            cost = ((Equip) item).getPrice();
        } else {
            cost = ItemData.getItemInfoByID(item.getItemId()).getPrice() * item.getQuantity();
        }
        nsi.setPrice(cost);
        nsi.setQuantity((short) item.getQuantity());
        getBuyBack().add(nsi);
    }

    public NpcShopItem getBuyBackItemBySlot(int slot) {
        NpcShopItem nsi = null;
        if (slot >= 0 && slot < getBuyBack().size()) {
            return getBuyBack().get(slot);
        }
        return nsi;
    }

    public void removeBuyBackItem(NpcShopItem nsi) {
        getBuyBack().remove(nsi);
    }

    public int getLocation() {
        return location;
    }

    public Android getAndroid() {
        return android;
    }

    public void setAndroid(Android android) {
        this.android = android;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public Map<Integer, PsychicArea> getPsychicAreas() {
        return psychicAreas;
    }

    public void setPsychicAreas(Map<Integer, PsychicArea> psychicAreas) {
        this.psychicAreas = psychicAreas;
    }

    public void addPsychicArea(int localPsychicAreaKey, PsychicArea psychicArea) {
        getPsychicAreas().put(localPsychicAreaKey, psychicArea);
    }

    public void removePsychicArea(int localPsychicAreaKey) {
        getPsychicAreas().remove(localPsychicAreaKey);
    }

    public Map<Integer, ForceAtom> getForceAtoms() {
        return forceAtoms;
    }

    public Map<Integer, SecondAtom> getSecondAtoms() {
        return secondAtoms;
    }


    /**
     * Initializes this Char's Android according to their heart + android equips. Will not do anything if an Android
     * already exists.
     *
     * @param override Whether or not to override the old Android if one exists.
     */
    public void initAndroid(boolean override) {
        if (getAndroid() == null || override) {
            Item heart = getEquippedItemByBodyPart(BodyPart.MechanicalHeart);
            Item android = getEquippedItemByBodyPart(BodyPart.Android);
            if (heart != null && android != null && ((Equip) heart).getAndroidGrade() + 3 >= ((Equip) android).getAndroidGrade()) {
                int androidId = ((Equip) android).getAndroid();
                AndroidInfo androidInfo = EtcData.getAndroidInfoById(androidId);
                if (getAndroid() != null) {
                    getField().removeLife(getAndroid());
                }
                Android newAndroid = new Android(this, androidInfo);
                if (getPosition() != null) {
                    newAndroid.setPosition(getPosition().deepCopy());
                }
                setAndroid(newAndroid);
            }
        }
    }

    public void setForceAtoms(Map<Integer, ForceAtom> forceAtoms) {
        this.forceAtoms = forceAtoms;
    }

    public void addForceAtom(ForceAtom forceAtom) {
        forceAtom.getKeys().forEach(k -> getForceAtoms().put(k, forceAtom));
    }

    public void addForceAtomByKey(int faKey, ForceAtom forceAtom) {
        getForceAtoms().put(faKey, forceAtom);
    }

    public void removeForceAtomByKey(int key) {
        getForceAtoms().remove(key);
    }

    public ForceAtom getForceAtomByKey(int key) {
        return getForceAtoms().getOrDefault(key, null);
    }

    public void recreateforceAtom(int faKey, ForceAtom forceAtom) {
        addForceAtomByKey(faKey, forceAtom);
        ForceAtomInfo fai = forceAtom.getFaiByKey(faKey);
        ForceAtom fa = new ForceAtom(forceAtom);
        fa.setFaiList(Collections.singletonList(fai));
        getField().broadcastPacket(FieldPacket.createForceAtom(fa));
    }

    public void createForceAtom(ForceAtom forceAtom) {
        createForceAtom(forceAtom, true);
    }

    public void createForceAtom(ForceAtom forceAtom, boolean broadcastToField) {
        if (broadcastToField) {
            getField().broadcastPacket(FieldPacket.createForceAtom(forceAtom));
        } else {
            write(FieldPacket.createForceAtom(forceAtom));
        }
        addForceAtom(forceAtom);
    }

    public void createSecondAtom(List<SecondAtom> secondAtoms) {
        createSecondAtom(secondAtoms, false, true);
    }

    public void createSecondAtom(List<SecondAtom> secondAtoms, boolean infinity) {
        createSecondAtom(secondAtoms, infinity, true);
    }

    public void createSecondAtom(List<SecondAtom> secondAtoms, boolean multi, boolean broadcastToField) {
        if (broadcastToField) {
            getField().broadcastPacket(multi ? SecondAtomPacket.createSecondAtoms(this.getId(), secondAtoms) : SecondAtomPacket.createSecondAtom(this.getId(), secondAtoms));
        } else {
            write(multi ? SecondAtomPacket.createSecondAtom(this.getId(), secondAtoms) : SecondAtomPacket.createSecondAtom(this.getId(), secondAtoms));
        }
        addSecondAtom(secondAtoms);
    }


    public void addSecondAtom(List<SecondAtom> secondAtoms) {
        secondAtoms.forEach(k -> getSecondAtoms().put(secondAtomKeyCounter, k));
    }

    public void clearForceAtomMap() {
        getForceAtoms().clear();
        setForceAtomKeyCounter(1);
    }

    public void clearSecondAtoms() {
        getSecondAtoms().clear();
        setSecondAtomKeyCounter(1);
    }

    public int getForceAtomKeyCounter() {
        return forceAtomKeyCounter;
    }

    public void setForceAtomKeyCounter(int forceAtomKeyCounter) {
        this.forceAtomKeyCounter = forceAtomKeyCounter;
    }

    public void setSecondAtomKeyCounter(int forceAtomKeyCounter) {
        this.secondAtomKeyCounter = forceAtomKeyCounter;
    }

    public int getNewForceAtomKey() {
        return forceAtomKeyCounter++;
    }

    public int getNewSecondAtomKey() {
        if (secondAtomKeyCounter > 1000000000) {
            secondAtomKeyCounter = 0;
        }
        return secondAtomKeyCounter++;
    }

    public void setCopy(Char copy) {
        this.copy = copy;
    }

    public Char getCopy() {
        return copy;
    }

    public boolean isInAPartyWith(Char otherChr) {
        if (otherChr == this) {
            return true;
        }
        if (otherChr.getParty() == null || getParty() == null) {
            return false;
        }
        return otherChr.getPartyID() == getPartyID();
    }

    public boolean isDead() {
        return getHP() <= 0;
    }

    public void showSkillOnOffEffect() {

        // Wind Archer
        int questId = QuestConstants.SKILL_COMMAND_LOCK_ARK; // questId 1544
        Quest quest = getQuestManager().getQuestById(questId);
        if (quest == null) {
            return;
        }
        getField().broadcastPacket(UserPool.skillOnOffEffect(getId(), quest.getQRValue().equalsIgnoreCase("")));
    }

    public int getFirstOpenMatrixSlot() {
        // TODO: check for max slot count
        List<MatrixRecord> activeRecords = getMatrixRecords().stream().filter(MatrixRecord::isActive).sorted(Comparator.comparingInt(MatrixRecord::getPosition)).collect(Collectors.toList());
        int pos = -1;
        boolean found = false;
        for (MatrixRecord mr : activeRecords) {
            int newPos = mr.getPosition();
            if (newPos - pos > 1) {
                // gap in positions
                pos = newPos - 1;
                found = true;
                break;
            }
            pos = newPos;
        }
        return found ? pos : pos + 1;
    }

    public WeaponType getWeaponType() {
        return ItemConstants.getWeaponType(getEquippedItemByBodyPart(BodyPart.Weapon).getItemId());
    }

    public boolean isShowDamageCalc() {
        return showDamageCalc;
    }

    public void setShowDamageCalc(boolean showDamageCalc) {
        this.showDamageCalc = showDamageCalc;
    }

    public boolean canEquip(Item item) {
        if (item instanceof Equip && !((Equip) item).isVestige()) {
            Equip equip = (Equip) item;
            int lv = getLevel();
            CharacterStat cs = getAvatarData().getCharacterStat();
            int str = cs.getStr();
            int inte = cs.getInt();
            int dex = cs.getDex();
            int luk = cs.getLuk();
            short job = getJob();
            short rJob = equip.getrJob();
            boolean matchingJob = rJob == 0;
            if (!matchingJob) {
                boolean warrior = (rJob & 1) != 0;
                boolean magician = (rJob & 1 << 1) != 0;
                boolean bowman = (rJob & 1 << 2) != 0;
                boolean thief = (rJob & 1 << 3) != 0;
                boolean pirate = (rJob & 1 << 4) != 0;
                matchingJob = (warrior && JobConstants.isWarriorEquipJob(job)) ||
                        (magician && JobConstants.isMageEquipJob(job)) ||
                        (bowman && JobConstants.isArcherEquipJob(job)) ||
                        (thief && JobConstants.isThiefEquipJob(job)) ||
                        (pirate && JobConstants.isPirateEquipJob(job));
            }
            return equip.getrLevel() <= lv
                    && equip.getrDex() <= dex
                    && (equip.getrStr() <= str || JobConstants.isDemonAvenger(job))
                    && equip.getrInt() <= inte
                    && equip.getrLuk() <= luk && matchingJob;
        }
        return false;
    }


    public void giveStartingItems() {
        addHotTimeReward(2436226, HotTimeRewardType.GAME_ITEM, 1, 0, 0,0,"A gift from the Admin of v214.");
        addHotTimeReward(2436226, HotTimeRewardType.MESO, 1, 10000000,0, 0,"A gift from the Admin of v214.");
        addHotTimeReward(2436226, HotTimeRewardType.EXPERIENCE, 1, 0, 1000,0,"A gift from the Admin of v214.");
        addHotTimeReward(2436226, HotTimeRewardType.MAPLE_POINT, 1, 0, 0,10000,"A gift from the Admin of v214.");
        addHotTimeReward(5002142, HotTimeRewardType.GAME_ITEM, 1, 0, 0,0,"A gift from the Admin of v214.");

    }

    public List<HotTimeReward> getHotTimeRewards() {
        return hotTimeRewards;
    }

    public void setHotTimeRewards(List<HotTimeReward> hotTimeRewards) {
        this.hotTimeRewards = hotTimeRewards;
    }

    public void addHotTimeReward(int itemID, HotTimeRewardType type, int quantity, int meso, int exp, int maplepoint, String description) {
        HotTimeReward reward = new HotTimeReward();
        reward.setCharId(getId());
        reward.setItemId(itemID);
        reward.setStartTime(reward.getStartTime());
        reward.setEndTime(FileTime.fromDate(reward.getStartTime().toLocalDateTime().plusDays(30)));
        reward.setRewardType(type);
        reward.setQuantity(quantity);
        reward.setMesoAmount(meso);
        reward.setExpAmount(exp);
        reward.setMaplePointAmount(maplepoint);
        reward.setDescription(description);
        DatabaseManager.saveToDB(reward);
        checkHotTimeRewards();
        // TODO: if reward type is something other than game, apply quantity to specific quantity type
    }

    public void removeHotTimeReward(HotTimeReward reward) {
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE HotTimeReward WHERE id = :id");
        query.setParameter("id", reward.getId());
        query.executeUpdate();
        transaction.commit();
    }


    public void forceUpdateSecondary(Item oldSecondary, Item newSecondary) {
        if (oldSecondary != null) {
            consumeItem(oldSecondary);
        }
        int secondaryBagIndex = BodyPart.Shield.getVal();
        newSecondary.setBagIndex(secondaryBagIndex);
        getEquippedInventory().addItem(newSecondary);
        getAvatarData().getAvatarLook().getHairEquips().put((byte) secondaryBagIndex, newSecondary.getItemId());
        newSecondary.updateToChar(this);
    }

    public void checkHotTimeRewards() {
        // check if the character has gifts
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM HotTimeReward WHERE charid = :charid");
        query.setParameter("charid", getId());
        List results = ((org.hibernate.query.Query) query).list();
        transaction.commit();
        setHotTimeRewards(results);

        if (getHotTimeRewards().size() > 0) {
            Stack<HotTimeReward> rewardsToSend = new Stack<>();
            for (HotTimeReward hotTimeReward : getHotTimeRewards()) {
                rewardsToSend.push(hotTimeReward);
            }
            write(WvsContext.sendHotTimeReward(HotTimeRewardSendType.REWARD, rewardsToSend));
        }
    }

    public void enterNewStageField() {
        getTemporaryStatManager().removeAllStats(true);
        getField().removeChar(this);
        setOnline(false);
    }

    public Map<Integer, Integer> getActiveSetEffects() {
        return activeSetEffects;
    }

    public void initSetEffects() {
        getActiveSetEffects().clear();
        getSetBaseStats().clear();
        getSetNonAddBaseStats().clear();
        for (Equip e : getEquippedInventory().getItems().stream().map(e -> (Equip) e).collect(Collectors.toList())) {
            int setID = e.getSetItemID();
            if (setID != 0) {
                addSetEffect(setID);
            }
        }
    }

    public void updateSetEffect(int setId, int fromLevel, int toLevel) {
        if (toLevel > fromLevel) { // increased set level
            ItemSet is = ItemData.getItemSetById(setId);
            // check if item set has stats at new level
            if (is.getEffects().containsKey(toLevel)) {
                for (Map.Entry<BaseStat, Double> stats : is.getEffects().get(toLevel).entrySet()) {
                    addSetBaseStat(stats.getKey(), stats.getValue().intValue());
                }
            }
        } else if (toLevel < fromLevel) { // decreased set level
            ItemSet is = ItemData.getItemSetById(setId);
            if (is.getEffects().containsKey(fromLevel)) {
                for (Map.Entry<BaseStat, Double> stats : is.getEffects().get(fromLevel).entrySet()) {
                    removeSetBaseStat(stats.getKey(), stats.getValue().intValue());
                }
            }
        }
    }

    /**
     * Adds a set to the current active set effects.
     *
     * @param setID
     */
    public void addSetEffect(int setID) {
        if (getActiveSetEffects().containsKey(setID)) {
            int oldCount = getActiveSetEffects().get(setID);
            int newCount = oldCount + 1;
            getActiveSetEffects().put(setID, newCount);
            updateSetEffect(setID, oldCount, newCount);
        } else {
            getActiveSetEffects().put(setID, 1);
            updateSetEffect(setID, 0, 1);
        }
    }

    /**
     * Removes a set from the current active set effects.
     *
     * @param setID
     */
    public void removeSetEffect(int setID) {
        if (getActiveSetEffects().containsKey(setID)) {
            int oldCount = getActiveSetEffects().get(setID);
            int newCount = oldCount - 1;
            if (newCount <= 0) {
                getActiveSetEffects().remove(setID);
                updateSetEffect(setID, oldCount, newCount);
            } else {
                getActiveSetEffects().put(setID, newCount);
                updateSetEffect(setID, oldCount, newCount);
            }
        }
    }

    public Map<BaseStat, Integer> getSetBaseStats() { return setBaseStats; }

    public Map<BaseStat, Set<Integer>> getSetNonAddBaseStats() { return setNonAddBaseStats; }

    public void addSetBaseStat(BaseStat bs, int value) {
        if (bs.isNonAdditiveStat()) {
            if (!getSetNonAddBaseStats().containsKey(bs)) {
                getSetNonAddBaseStats().put(bs, new HashSet<>());
            }
            getSetNonAddBaseStats().get(bs).add(value);
        } else {
            getSetBaseStats().put(bs, getSetBaseStats().getOrDefault(bs, 0) + value);
        }
    }

    public void removeSetBaseStat(BaseStat bs, int value) {
        addSetBaseStat(bs, -value);
    }
}