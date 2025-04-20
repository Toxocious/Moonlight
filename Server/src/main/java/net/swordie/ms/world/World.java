package net.swordie.ms.world;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.User;
import net.swordie.ms.client.alliance.Alliance;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.enums.AuctionState;
import net.swordie.ms.enums.WorldId;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.auction.AuctionEnum;
import net.swordie.ms.world.auction.AuctionItem;
import net.swordie.ms.world.auction.AuctionPotType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 11/2/2017.
 */
public class World {
    //WORLDITEM struct

    private WorldId worldId;
    private int worldState, worldEventEXP_WSE, worldEventDrop_WSE, boomUpEventNotice;
    private boolean starplanet;
    private String name, worldEventDescription;
    private List<Channel> channels;
    private Map<Integer, Party> parties = new HashMap<>();
    private Map<Integer, Guild> guilds = new HashMap<>();
    private Map<Integer, Alliance> alliances = new HashMap<>();
    private Map<Integer, Set<AuctionItem>> accIdToAuctions = new HashMap<>();
    private Map<Integer, AuctionItem> auctionIdToAuction = new HashMap<>();
    private int partyIDCounter = 1;
    private boolean charCreateBlock;
    private boolean reboot;

    public World(WorldId worldId, String name, int worldState, String worldEventDescription, int worldEventEXP_WSE,
                 int worldEventDrop_WSE, int boomUpEventNotice, int amountOfChannels, boolean starplanet, boolean reboot) {
        this.worldId = worldId;
        this.name = name;
        this.worldState = worldState;
        this.worldEventDescription = worldEventDescription;
        this.worldEventEXP_WSE = worldEventEXP_WSE;
        this.worldEventDrop_WSE = worldEventDrop_WSE;
        this.boomUpEventNotice = boomUpEventNotice;
        List<Channel> channelList = new ArrayList<>();
        for (int i = 1; i <= amountOfChannels; i++) {
            channelList.add(new Channel(worldId, i));
        }
        this.channels = channelList;
        this.starplanet = starplanet;
        this.reboot = reboot;
        initAuctionHouse();
    }

    public World(WorldId worldId, String name, int amountOfChannels, String worldEventMsg) {
        this(worldId, name, 0, worldEventMsg, 100, 100,
                0, amountOfChannels, false, false);
        initGuilds();
    }

    private void initGuilds() {
        List<Guild> guilds = (List<Guild>) DatabaseManager.getObjListFromDB(Guild.class);
        for (Guild g : guilds) {
            addGuild(g);
        }
    }

    private void initAuctionHouse() {
        List<AuctionItem> auctions = (List<AuctionItem>) DatabaseManager.getObjListFromDB(AuctionItem.class);
        for (AuctionItem ai : auctions) {
            addAuction(ai, false);
        }
    }

    public WorldId getWorldId() {
        return worldId;
    }

    public String getName() {
        return name;
    }

    public int getWorldState() {
        return worldState;
    }

    public int getWorldEventEXP_WSE() {
        return worldEventEXP_WSE;
    }

    public int getWorldEventDrop_WSE() {
        return worldEventDrop_WSE;
    }

    public int getBoomUpEventNotice() {
        return boomUpEventNotice;
    }

    public boolean isStarPlanet() {
        return starplanet;
    }

    public String getWorldEventDescription() {
        return worldEventDescription;
    }

    public void setWorldEventDescription(String worldEventDescription) {
        this.worldEventDescription = worldEventDescription;
    }

    public Channel getChannelById(byte id) {
        return getChannels().stream().filter(c -> c.getChannelId() == id).findFirst().orElse(null);
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public ServerStatus getStatus() {
        return ServerStatus.NORMAL;
    }

    public Char getCharByName(String name) {
        for (Channel c : getChannels()) {
            Char chr = c.getCharByName(name);
            if (chr != null) {
                return chr;
            }
        }
        return null;
    }

    public Char getCharByID(int id) {
        for (Channel c : getChannels()) {
            Char chr = c.getCharById(id);
            if (chr != null) {
                return chr;
            }
        }
        return null;
    }

    public Map<Integer, Party> getParties() {
        return parties;
    }

    public void addParty(Party p) {
        int id = getPartyIdAndIncrement(); // sequential IDs should be fine here
        getParties().put(id, p);
        p.setId(id);
        if (p.getWorld() == null) {
            p.setWorld(this);
        }
    }

    public void removeParty(Party p) {
        getParties().remove(p.getId());
    }

    public Party getPartybyId(int partyID) {
        return getParties().get(partyID);
    }

    public Map<Integer, Guild> getGuilds() {
        return guilds;
    }

    public Set<AuctionItem> getAuctionMarketPlaceItems() {
        return getAuctionHouse().stream().filter(auctionItem -> auctionItem.getState() == AuctionState.Sold).collect(Collectors.toSet());
    }

    public Set<AuctionItem> getAuctionRecentListings() {
        return getAuctionHouse().stream().filter(auctionItem -> !auctionItem.getEndDate().isExpired()).collect(Collectors.toSet());
    }

    public Collection<Guild> getGuildsWithCriteria(int levMin, int levMax, int sizeMin, int sizeMax, int avgLevMin, int avgLevMax) {
        Collection<Guild> guilds = getGuilds().values();
        Set<Guild> res = new HashSet<>(guilds);
        for (Guild g : guilds) {
            //calculate average level of guild members
            int averageLevel = g.getAverageMemberLevel();
            if (levMin != 0 && levMin > g.getReqLevel() + 1 //getReqLevel is automatically set to 0
                    || levMax != 0 && levMax < g.getReqLevel()
                    || sizeMin != 0 && sizeMin > g.getMembers().size()
                    || sizeMax != 0 && sizeMax < g.getMembers().size()
                    || avgLevMin != 0 && avgLevMin > averageLevel
                    || avgLevMax != 0 && avgLevMax < averageLevel) {
                res.remove(g);
            }
        }
        return res;
    }

    public Collection<Guild> getGuildsByString(int searchType, boolean exactWord, String searchTerm) {
        Collection<Guild> guilds = getGuilds().values();
        Set<Guild> res = new HashSet<>(guilds);
        searchTerm = searchTerm.toLowerCase();
        for (Guild g : guilds) {
            if (searchType == 1) {
                String guildName = g.getName().toLowerCase();
                String leaderName = g.getGuildLeader().getName().toLowerCase();
                if ((exactWord && !guildName.equals(searchTerm) && !leaderName.equals(searchTerm)
                || (!exactWord && !guildName.contains(searchTerm) && !leaderName.contains(searchTerm)))) {
                        res.remove(g);
                }
            } else {
                String name = searchType == 2
                        ? g.getName()
                        : searchType == 3
                        ? g.getGuildLeader().getName()
                        : "";
                if ((exactWord && !name.equals(searchTerm)) || (!exactWord && !name.contains(searchTerm))) {
                    res.remove(g);
                }
            }
        }
        return res;
    }

  //  public Guild getGuildByID(int id) {
   //     return getGuilds().get(id);
  //  }

    public Guild getGuildByID(int id) {
        for(Map.Entry<Integer,Guild> _g : getGuilds().entrySet())
        {
            if(id == _g.getValue().getId()){
                return _g.getValue();
            }
        }
        return null;
    }

    public Guild getGuildByName(String name) {
        return Util.findWithPred(guilds.values(), g -> g.getName().equalsIgnoreCase(name));
    }

    public Guild loadGuildFromDB(int id) {
        return (Guild) DatabaseManager.getObjFromDB(Guild.class, id);
    }

    public int getPartyIdAndIncrement() {
        return partyIDCounter++;
    }

    public int getPartyIDCounter() {
        return partyIDCounter;
    }

    public void setPartyIDCounter(int partyIDCounter) {
        this.partyIDCounter = partyIDCounter;
    }

    public User getUserById(int accID) {
        for (Channel c : getChannels()) {
            User res = c.getUserById(accID);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    public void broadcastPacket(OutPacket outPacket) {
        for (Channel channel : getChannels()) {
            channel.broadcastPacket(outPacket);
        }
    }

    public boolean isCharCreateBlock() {
        return charCreateBlock;
    }

    public void setCharCreateBlock(boolean charCreateBlock) {
        this.charCreateBlock = charCreateBlock;
    }

    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    public boolean isFull() {
        boolean full = true;
        for (Channel channel : getChannels()) {
            if (channel.getChars().size() < channel.MAX_SIZE) {
                full = false;
                break;
            }
        }
        return full;
    }

    public Map<Integer, Alliance> getAlliances() {
        return alliances;
    }

    public void addGuild(Guild guild) {
        getGuilds().put(guild.getId(), guild);
    }

    private void addAlliance(Alliance ally) {
        getAlliances().put(ally.getId(), ally);
        // Initialize guilds to be the same instance as the ones we currently have
        Set<Guild> guilds = new HashSet<>();
        for (Guild guild : ally.getGuilds()) {
            Guild ourGuild = getGuildByID(guild.getId());
            ourGuild.setAlliance(ally);
            guilds.add(ourGuild);
        }
        ally.setGuilds(guilds);
    }

    public Alliance getAlliance(int id) {
        Alliance ally = getAlliances().getOrDefault(id, null);
        if (ally == null) {
            ally = (Alliance) DatabaseManager.getObjFromDB(Alliance.class, id);
            if (ally != null) {
                addAlliance(ally);
            }
        }
        return ally;
    }

    public Alliance getAlliance(String name) {
        Alliance ally = getAlliances().values().stream().filter(a -> a.getName().equals(name)).findAny().orElse(null);
        if (ally == null) {
            ally = (Alliance) DatabaseManager.getObjFromDB(Alliance.class, name);
            if (ally != null) {
                addAlliance(ally);
            }
        }
        return ally;
    }

    public void clearCache() {
        for (Channel channel : getChannels()) {
            channel.clearCache();
        }
    }

    public Collection<AuctionItem> getAuctionHouse() {
        return auctionIdToAuction.values();
    }

    public Set<AuctionItem> getAuctionItemsWithFilter(String query1, String query2, AuctionEnum innerType, int minLv,
                                                      int maxLv, long minPrice, long maxPrice, AuctionPotType apt,
                                                      boolean andSearch, boolean expired) {
        return getAuctionHouse().stream().filter(ai -> {
            if ((expired && (!ai.getEndDate().isExpired() || ai.getState() != AuctionState.SoldDone)) ||
                    (!expired && (ai.getEndDate().isExpired() || ai.getState() != AuctionState.Init))) {
                return false;
            }
            Item item = ai.getItem();
            String name = ai.getItemName().toLowerCase().replaceAll(" ", "");
            Equip equip = null;
            if (item instanceof Equip) {
                equip = (Equip) item;
            }
            boolean nonEquipCheck = (name.toLowerCase().contains(query1) || name.contains(query2)) && innerType.isMatching(item)
                    && ai.getPrice() >= minPrice && ai.getPrice() <= maxPrice;
            if (equip != null && nonEquipCheck) {
                nonEquipCheck = equip.getrLevel() >= minLv && equip.getrLevel() <= maxLv &&
                        apt.isMatching(equip);
            }
            return nonEquipCheck;
        }).collect(Collectors.toSet());

    }

    public void addAuction(AuctionItem ai, boolean saveToDb) {
        if (saveToDb) {
            DatabaseManager.saveToDB(ai);
        }
        if (!accIdToAuctions.containsKey(ai.getAccountID())) {
            accIdToAuctions.put(ai.getAccountID(), new HashSet<>());
        }
        accIdToAuctions.get(ai.getAccountID()).add(ai);
        auctionIdToAuction.put(ai.getId(), ai);
    }

    public Set<AuctionItem> getAuctionsByAccountID(int id) {
        return accIdToAuctions.getOrDefault(id, new HashSet<>());
    }

    public AuctionItem getAuctionById(int auctionId) {
        return auctionIdToAuction.get(auctionId);
    }

    public void shutdown() {
        for (Channel channel : getChannels()) {
            System.err.println("Shutting down channel " + channel.getChannelId() + "...");
            channel.shutdown();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.err.println("Accounts have been saved.");
        for (AuctionItem ai : getAuctionHouse()) {
            DatabaseManager.saveToDB(ai);
        }
        for (Alliance ally : getAlliances().values()) {
            // also saves guilds
            DatabaseManager.saveToDB(ally);
        }
    }

    public void removeGuild(Guild guild) {
        getGuilds().remove(guild.getId());
        DatabaseManager.deleteFromDB(guild);
    }

    public void removeAlliance(Alliance alliance) {
        getAlliances().remove(alliance.getId());
        DatabaseManager.deleteFromDB(alliance);
    }
}
