package net.swordie.ms.client;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.EventCoolDown;
import net.swordie.ms.client.character.MonsterCollection;
import net.swordie.ms.client.character.damage.DamageSkinSaveData;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.union.Union;
import net.swordie.ms.client.character.union.UnionBoard;
import net.swordie.ms.client.trunk.Trunk;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.EventType;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.auction.AuctionItem;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing an Account, which is a world-specific "User" class.
 *
 * Created by Tim on 4/30/2017.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Transient
    private static final Logger log = Logger.getLogger(Account.class);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int worldId;
    @JoinColumn(name = "trunkID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Trunk trunk;
    @JoinColumn(name = "monsterCollectionID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MonsterCollection monsterCollection;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<DamageSkinSaveData> damageSkins = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<Char> characters = new HashSet<>();
    // nxCredit is from mobs, so is account (world) specific.
    private int nxCredit;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "accID")
    private Set<LinkSkill> linkSkills = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "unionid")
    private Union union;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "accid")
    private List<EventCoolDown> cooldowns = new ArrayList<>();

    @Transient
    private Set<AuctionItem> auctionItems;
    @Transient
    private User user;
    @Transient
    private Char currentChr;

    public Account(User user, int worldId) {
        this.user = user;
        this.worldId = worldId;
        this.trunk = new Trunk(GameConstants.DEFAULT_TRUNK_SIZE);
        this.monsterCollection = new MonsterCollection();
        this.damageSkins = new HashSet<>();
        this.characters = new HashSet<>();
        this.linkSkills = new HashSet<>();
        this.union = new Union(2, 101);
        this.cooldowns = new ArrayList<EventCoolDown>();
        union.setAccount(this);
        damageSkins.add(new DamageSkinSaveData(0, 2433271, false, "The default damage skin."));
    }

    public Account(){
    }

    public static Account getFromDBById(int accountID) {
        return (Account) DatabaseManager.getObjFromDB(Account.class, accountID);
    }

    public static Account getFromDBByIp(String ip) {
        Account acc = null;
        try(Session session = DatabaseManager.getSession()) {
            Transaction transaction = session.beginTransaction();
            // String.format for query, just to fill in the class
            // Can't set the FROM clause with a parameter it seems
            Query query = session.createQuery("FROM Account WHERE registerip = :ip");
            query.setParameter("ip", ip);
            List l = ((org.hibernate.query.Query) query).list();
            if (l != null && l.size() > 0) {
                acc = (Account) l.get(0);
            }
            transaction.commit();
            session.close();
        }
        return acc;
    }

    public int getId() {
        return id;
    }

    public Set<Char> getCharacters() {
        return characters;
    }

    public Union getUnion() {
        return union;
    }

    public void addCharacter(Char character) {
       getCharacters().add(character);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<DamageSkinSaveData> getDamageSkins() {
        return damageSkins;
    }

    public void setDamageSkins(Set<DamageSkinSaveData> damageSkins) {
        this.damageSkins = damageSkins;
    }

    public void addDamageSkin(DamageSkinSaveData dssd) {
        if(getDamageSkinByItemID(dssd.getItemID()) == null) {
            getDamageSkins().add(dssd);
        }
    }

    public void removeDamageSkin(DamageSkinSaveData dssd) {
        if(dssd != null) {
            getDamageSkins().remove(dssd);
        }
    }

    public void removeDamageSkin(int itemID) {
        removeDamageSkin(getDamageSkinByItemID(itemID));
    }

    public void addDamageSkinByItemID(int itemID) {
        addDamageSkin(new DamageSkinSaveData(ItemConstants.getDamageSkinIDByItemID(itemID), itemID, false,
                StringData.getItemStringById(itemID)));
    }

    public DamageSkinSaveData getDamageSkinByItemID(int itemID) {
        return getDamageSkins().stream().filter(d -> d.getItemID() == itemID).findAny().orElse(null);
    }

    public DamageSkinSaveData getDamageSkinBySkinID(int skinID) {
        return getDamageSkins().stream().filter(d -> d.getDamageSkinID() == skinID).findAny().orElse(null);
    }

    public Trunk getTrunk() {
        if(trunk == null) {
            trunk = new Trunk(GameConstants.DEFAULT_TRUNK_SIZE);
        }
        return trunk;
    }

    public void setTrunk(Trunk trunk) {
        this.trunk = trunk;
    }

    public int getNxCredit() {
        return nxCredit;
    }

    public void setNxCredit(int nxCredit) {
        this.nxCredit = nxCredit;
    }

    public void addLinkSkill(LinkSkill linkSkill) {
        removeLinkSkillByOwnerID(linkSkill.getUsingID());
        getLinkSkills().add(linkSkill);
    }

    public void addLinkSkill(Char originChar, int usingID, int linkedSkill) {
        int level = SkillConstants.getLinkSkillLevelByCharLevel(originChar.getLevel());
        LinkSkill ls = new LinkSkill(originChar.getId(), usingID, linkedSkill, level);
        addLinkSkill(ls);
    }

    public void removeLinkSkillByOwnerID(int ownerID) {
        getLinkSkills().stream().filter(l -> l.getUsingID() == ownerID).findFirst()
                .ifPresent(ls -> getLinkSkills().remove(ls));
    }

    public Set<LinkSkill> getLinkSkills() {
        return linkSkills;
    }

    public void setLinkSkills(Set<LinkSkill> linkSkills) {
        this.linkSkills = linkSkills;
    }

    public void addNXCredit(int credit) {
        int newCredit = getNxCredit() + credit;
        if (newCredit >= 0) {
            setNxCredit(newCredit);
        }
    }

    public void deductNXCredit(int credit) {
        addNXCredit(-credit);
    }

    public MonsterCollection getMonsterCollection() {
        if (monsterCollection == null) {
            monsterCollection = new MonsterCollection();
        }
        return monsterCollection;
    }

    public void setMonsterCollection(MonsterCollection monsterCollection) {
        this.monsterCollection = monsterCollection;
    }

    public boolean hasCharacter(int charID) {
        // doing a .contains on getCharacters() does not work, even if the hashcode is just a hash of the id
        return getCharById(charID) != null;
    }

    public Char getCharById(int id) {
        return Util.findWithPred(getCharacters(), chr -> chr.getId() == id);
    }

    public Char getCharByName(String name) {
        return Util.findWithPred(getCharacters(), chr -> chr.getName().equals(name));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWorldId() {
        return worldId;
    }

    public void setWorldId(int worldId) {
        this.worldId = worldId;
    }

    public Char getCurrentChr() {
        return currentChr;
    }

    public void setCurrentChr(Char currentChr) {
        this.currentChr = currentChr;
    }

    public int getTotalLvOfAllChrs() {
        return getCharacters().stream().mapToInt(Char::getLevel).sum();
    }

    public Set<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public Set<AuctionItem> getCompletedAuctionItems() {
        return getAuctionItems().stream()
                .filter(it -> it.getEndDate().isExpired())
                .collect(Collectors.toSet());
    }

    public Set<AuctionItem> getSellingAuctionItems() {
        return getAuctionItems().stream()
                .filter(it -> !it.getEndDate().isExpired())
                .collect(Collectors.toSet());
    }

    public void setAuctionItems(Set<AuctionItem> auctionItems) {
        this.auctionItems = auctionItems;
    }

    public AuctionItem getAuctionById(int auctionId) {
        return Util.findWithPred(getAuctionItems(), ai -> ai.getId() == auctionId);
    }

    public void addAuction(AuctionItem item) {
        getAuctionItems().add(item);
    }

    public AuctionItem createAndAddAuctionByItem(Item item, Char sellingChar, long price) {
        AuctionItem ai = new AuctionItem();
        ai.setItem(item);
        ai.setRegDate(FileTime.currentTime());
        ai.setEndDate(FileTime.fromDate(FileTime.currentTime().toLocalDateTime().plusHours(GameConstants.AUCTION_LIST_TIME)));
        ai.setAccountID(getId());
        ai.setCharID(sellingChar.getId());
        ai.setCharName(sellingChar.getName());
        ai.setDirectPrice(price);
        ai.setItemType(item.getInvType().getVal());
        ai.setItemName(StringData.getItemStringById(item.getItemId()));
        ai.setDeposit(GameConstants.AUCTION_DEPOSIT_AMOUNT);
        addAuction(ai);
        sellingChar.getClient().getWorld().addAuction(ai, true);

        return ai;
    }

    public void initAuctions() {
        // Not done via db to ensure the instances between world and acc are the same
        setAuctionItems(getCurrentChr().getWorld().getAuctionsByAccountID(getId()));
    }

    public LinkSkill getLinkSkillByLinkSkillId(int linkSkillID) {
        return Util.findWithPred(getLinkSkills(), s -> s.getLinkSkillID() == linkSkillID);
    }

    public void removeChar(Char chr) {
        removeLinkSkillByOwnerID(chr.getId());
        for (UnionBoard ub : getUnion().getUnionBoards()) {
            ub.removeMemberByCharId(chr.getId());
        }
        getCharacters().remove(chr);
    }

    public EventCoolDown getCoolDownByType(int type) {
        return cooldowns.stream().filter(eventCoolDown -> eventCoolDown.getEventType() == type).collect(Collectors.toList()).get(0);
    }

    public int getEventAmountDone(int type){
        EventCoolDown cd = cooldowns.stream().filter(eventCoolDown -> eventCoolDown.getEventType() == type).findFirst().orElse(null);
        if (cd == null) {
            return 0;
        }
        if (System.currentTimeMillis() > cd.getNextResetTime()) {
            cd.setAmountDone(0);
        }
        return cd.getAmountDone();
    }

    public void addCoolDown(int type, int amountDone, long nextReset) {
        EventCoolDown coolDown = this.cooldowns.stream().filter(eventCoolDown -> eventCoolDown.getEventType() == type).findFirst().orElse(null);
        if (coolDown == null) {
            coolDown = new EventCoolDown(type, amountDone, nextReset);
            cooldowns.add(coolDown);
        } else {
            coolDown.setNextResetTime(nextReset);
            coolDown.setAmountDone(getEventAmountDone(type) + amountDone);
        }
        DatabaseManager.saveToDB(coolDown);
        DatabaseManager.modifyObjectFromDB(EventCoolDown.class, coolDown.getId(), "accid", this.getId());
    }

    public void clearCoolDowns() {
        for (EventCoolDown cd : cooldowns) {
            cd.setAmountDone(0);
            cd.setNextResetTime(0);
            DatabaseManager.modifyObjectFromDB(EventCoolDown.class, cd.getId(), "nextresettime", 0);
        }
    }

    public void addCooldownTime(EventType eventType, int cooldownTime) {
        addCoolDown(eventType.getVal(), 1,  System.currentTimeMillis() + cooldownTime);
    }

}
