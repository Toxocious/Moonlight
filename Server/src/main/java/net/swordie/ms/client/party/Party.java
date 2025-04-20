package net.swordie.ms.client.party;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.Instance;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 3/19/2018.
 */
public class Party implements Encodable {
    private int id;
    private PartyMember[] partyMembers = new PartyMember[6];
    private boolean appliable;
    private String name;
    private int partyLeaderID;
    private World world;
    private Char applyingChar;
    private Instance instance;
    private boolean duoParty;
    private boolean kickMembers;

    public boolean isAppliable() {
        return appliable;
    }

    public void setAppliable(boolean appliable) {
        this.appliable = appliable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PartyMember[] getPartyMembers() {
        return partyMembers;
    }

    public boolean isFull() {
        return Arrays.stream(getPartyMembers()).noneMatch(Objects::isNull);
    }

    public boolean isEmpty() {
        return Arrays.stream(getPartyMembers()).allMatch(Objects::isNull);
    }

    public void encode(OutPacket outPacket) {
        // start PARTYMEMBER
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getCharID() : 0);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeString(pm != null ? pm.getCharName() : "", 13);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getJob() : 0);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getSubSob() : 0);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getLevel() : 0);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null && pm.isOnline() ? pm.getChannel() - 1 : -2 ); // -1 for cash shop
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null && pm.isOnline() ? 1 : 0);
        }
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(0); // new 188
        }
        outPacket.encodeInt(getPartyLeaderID());
        // end PARTYMEMBER struct
        for (PartyMember pm : partyMembers) {
            outPacket.encodeInt(pm != null ? pm.getFieldID() : 0);
        }
        for (PartyMember pm : partyMembers) {
            if (pm != null && pm.getTownPortal() != null) {
                pm.getTownPortal().encode(outPacket, false);
            } else {
                new TownPortal().encode(outPacket, false);
            }
        }
        outPacket.encodeByte(isAppliable() && !isFull());
        outPacket.encodeByte(canKickMembers()); // new 188
        outPacket.encodeString(getName());
        // from here new 188: sub_85A6C4
        // tried putting in some values in here, but didn't see any change
        // Most likely something with duo parties
        outPacket.encodeByte(isDuoParty());
        outPacket.encodeByte(0);
        int size = 0;
        outPacket.encodeByte(size);
        for (int i = 0; i < size; i++) {
            // sub_C0E1E0
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeString("");
            outPacket.encodeInt(0);
        }
        outPacket.encodeByte(false);
        for (int i = 0; i < 2; i++) {
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
            boolean bool = false;
            outPacket.encodeByte(bool);
            if (bool) {
                new AvatarLook().encode(outPacket);
            }
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeLong(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }

    }

    public int getPartyLeaderID() {
        return partyLeaderID;
    }

    public void setPartyLeaderID(int partyLeaderID) {
        this.partyLeaderID = partyLeaderID;
    }

    /**
     * Adds a {@link Char} to this Party. Will do nothing if this Party is full.
     *
     * @param chr The Char to add.
     */
    public void addPartyMember(Char chr) {
        if (isFull()) {
            return;
        }
        PartyMember pm = new PartyMember(chr);
        if (isEmpty()) {
            setPartyLeaderID(chr.getId());
        }
        PartyMember[] partyMembers = getPartyMembers();
        boolean added = false;
        for (int i = 0; i < partyMembers.length; i++) {
            if (partyMembers[i] == null) {
                partyMembers[i] = pm;
                chr.setParty(this);
                added = true;
                break;
            }
        }
        if (added) {
            broadcast(WvsContext.partyResult(PartyResult.joinParty(this, chr.getName())));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TownPortal getTownPortal() {
        PartyMember pm = Arrays.stream(getPartyMembers()).filter(Objects::nonNull)
                .filter(p -> p.getTownPortal() != null)
                .findFirst().orElse(null);
        return pm != null ? pm.getTownPortal() : new TownPortal();
    }

    public PartyMember getPartyLeader() {
        return Arrays.stream(getPartyMembers()).filter(p -> p != null && p.getCharID() == getPartyLeaderID()).findFirst().orElse(null);
    }

    public boolean hasCharAsLeader(Char chr) {
        return getPartyLeaderID() == chr.getId();
    }

    public void disband() {
        broadcast(WvsContext.partyResult(PartyResult.withdrawParty(this, getPartyLeader(), false, false)));
        for (Char chr : getOnlineChars()) {
            chr.setParty(null);
        }
        for (int i = 0; i < getPartyMembers().length; i++) {
            getPartyMembers()[i] = null;
        }
        getWorld().removeParty(this);
        setWorld(null);
    }

    public List<Char> getOnlineChars() {
        return getOnlineMembers().stream().filter(pm -> pm.getChr() != null).map(PartyMember::getChr).collect(Collectors.toList());
    }

    public List<Char> getPartyMembersInField(Char chr) {
        return getOnlineChars().stream().filter(c -> c.getField() == chr.getField()).collect(Collectors.toList());
    }

    public List<PartyMember> getOnlineMembers() {
        return Arrays.stream(getPartyMembers()).filter(pm -> pm != null && pm.isOnline()).collect(Collectors.toList());
    }

    public List<PartyMember> getMembers() {
        return Arrays.stream(getPartyMembers()).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public void updateFull() {
        broadcast(WvsContext.partyResult(PartyResult.loadParty(this)));
    }

    public PartyMember getPartyMemberByID(int charID) {
        return Arrays.stream(getPartyMembers()).filter(p -> p != null && p.getCharID() == charID).findFirst().orElse(null);
    }

    public void broadcast(OutPacket outPacket) {
        for (PartyMember pm : getOnlineMembers()) {
            pm.getChr().write(outPacket);
        }
    }

    public void broadcast(OutPacket outPacket, Char exceptChar) {
        for (PartyMember pm : getOnlineMembers()) {
            if (!pm.getChr().equals(exceptChar)) {
                pm.getChr().write(outPacket);
            }
        }
    }

    public void removePartyMember(PartyMember partyMember) {
        for (int i = 0; i < getPartyMembers().length; i++) {
            PartyMember pm = getPartyMembers()[i];
            if (pm != null && pm.equals(partyMember)) {
                pm.getChr().setParty(null);
                getPartyMembers()[i] = null;
                break;
            }
        }
    }

    public void expel(int expelID) {
        PartyMember leaver = getPartyMemberByID(expelID);
        broadcast(WvsContext.partyResult(PartyResult.withdrawParty(this, leaver, true, true)));
        removePartyMember(leaver);
        updateFull();
    }

    public static Party createNewParty(boolean appliable, String name, World world) {
        Party party = new Party();
        party.setAppliable(appliable);
        party.setName(name);
        party.setWorld(world);
        world.addParty(party);
        return party;
    }

    public int getAvgLevel() {
        Collection<PartyMember> partyMembers = getMembers();
        return partyMembers.stream()
                .mapToInt(pm -> pm.getChr().getLevel())
                .sum() / partyMembers.size();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public Char getApplyingChar() {
        return applyingChar;
    }

    public void setApplyingChar(Char applyingChar) {
        this.applyingChar = applyingChar;
    }

    public boolean isPartyMember(Char chr) {
        return getPartyMemberByID(chr.getId()) != null;
    }

    public void updatePartyMemberInfoByChr(Char chr) {
        if (!isPartyMember(chr)) {
            return;
        }
        getPartyMemberByID(chr.getId()).updateInfoByChar(chr);
        updateFull();
    }

    /**
     * Returns the average party member's level, according to the given Char's field.
     *
     * @param chr the chr to get the map to
     * @return the average level of the party in the Char's field
     */
    public int getAvgPartyLevel(Char chr) {
        Field field = chr.getField();
        return (int) getOnlineMembers().stream().filter(om -> om.getChr().getField() == field)
                .mapToInt(PartyMember::getLevel).average().orElse(chr.getLevel());
    }

    /**
     * Gets a list of party members in the same Field instance as the given Char, excluding the given Char.
     *
     * @param chr the given Char
     * @return a set of Characters that are in the same field as the given Char
     */
    public Set<Char> getPartyMembersInSameField(Char chr) {
        return getOnlineMembers().stream()
                .filter(pm -> pm.getChr() != null && pm.getChr() != chr && pm.getChr().getField() == chr.getField())
                .map(PartyMember::getChr)
                .collect(Collectors.toSet());
    }

    /**
     * Checks if this Party has a member with the given character id.
     *
     * @param charID the charID to look for
     * @return if the corresponding char is in the party
     */
    public boolean hasPartyMember(int charID) {
        return getPartyMemberByID(charID) != null;
    }

    public boolean isDuoParty() {
        return duoParty;
    }

    public void setDuoParty(boolean duoParty) {
        this.duoParty = duoParty;
    }

    public boolean canKickMembers() {
        return kickMembers;
    }

    public void setKickMembers(boolean kickMembers) {
        this.kickMembers = kickMembers;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
