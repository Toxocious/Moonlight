package net.swordie.ms.world.field;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.loaders.FieldData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.world.field.FieldInstanceType.PARTY;
import static net.swordie.ms.world.field.FieldInstanceType.SOLO;

/**
 * Created on 23-4-2019.
 *
 * @author Asura
 */
public class Instance {

    private Char chr;
    private Party party;
    // expedition
    // guild
    private int enterFieldId;
    private int enterPortalId;
    private int forcedReturn;
    private Map<Integer, Field> fields = new HashMap<>();
    private Map<String, Object> properties = new HashMap<>();
    private FieldInstanceType instanceType;
    private ScheduledFuture warpOutTimer;
    private long warpOutTimeout;
    private List<Char> chars = new ArrayList<>();
    private int forcedReturnPortalId;

    public Instance(Char chr) {
        this.chr = chr;
        instanceType = SOLO;
    }

    public Instance(Party party) {
        this.party = party;
        instanceType = PARTY;
    }

    /**
     * Makes a Char reenter in this instance.
     * @param chr the Char that should enter this instance
     */
    public void reEnter(Char chr) {
        if (canReEnter(chr)) {
            chr.warp(getEnterFieldId(), getEnterPortalId());
        }
    }

    /**
     * Returns whether or not the given Char can re-enter this Instance.
     * @param chr the Char to check
     * @return whether or not the given Char can re-enter this Instance.
     */
    public boolean canReEnter(Char chr) {
        return getChars().contains(chr);
    }

    /**
     * Sets up this Instance. Creates a List of Chars for this Instance, and warps them to the given Field.
     * @param fieldId the Field's id to warp to
     * @param portalId the Portal's id to warp to
     */
    public void setup(int fieldId, int portalId) {
        Field checkField = FieldData.getFieldCopyById(fieldId);
        if (checkField == null) {
            throw new IllegalArgumentException("Invalid Field id " + fieldId);
        }
        if (party != null) {
            chars = new ArrayList<>(party.getOnlineChars());
        } else {
            chars = new ArrayList<>();
            chars.add(chr);
        }
        for (Char chr : getChars()) {
            chr.setInstance(this);
            chr.warp(fieldId, portalId);
        }
        setEnterFieldId(fieldId);
        setEnterPortalId(portalId);
        setForcedReturn(checkField.getForcedReturn());
    }


    /**
     * Returns a List of eligible Chars for this Instance.
     * @return list of eligible Chars
     */
    public List<Char> getChars() {
        return chars;
    }

    /**
     * Removes a Char from the eligible Char list.
     * @param chr the Char to remove
     */
    public void removeChar(Char chr) {
        chars.remove(chr);
        chr.setInstance(null);
    }

    /**
     * Returns the Char of this Instance, if it is a SOLO instance. Null otherwise.
     * @return Char of this instance
     */
    public Char getChr() {
        return chr;
    }

    /**
     * Returns the Party of this Instance, if it is a PARTY instance. Null otherwise.
     * @return
     */
    public Party getParty() {
        return party;
    }

    /**
     * Returns a List of active Fields in this Instance.
     * @return List of active Fields
     */
    public Map<Integer, Field> getFields() {
        return fields;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public FieldInstanceType getInstanceType() {
        return instanceType;
    }

    /**
     * Returns the forced return Field of this Instance, for when a Char is forced out of this Instance.
     * @return the forced return Field
     */
    public int getForcedReturn() {
        return forcedReturn;
    }

    /**
     * Returns the initial enter Field of this Instance.
     * @return the initial enter Field of this Instance.
     */
    public int getEnterFieldId() {
        return enterFieldId;
    }

    /**
     * Returns the initial enter Portal of this Instance.
     * @return the initial enter Portal of this Instance.
     */
    public int getEnterPortalId() {
        return enterPortalId;
    }

    /**
     * Returns the timer of the event that will warp everyone out of this Instance.
     * @return warp out event timer
     */
    public ScheduledFuture getWarpOutTimer() {
        return warpOutTimer;
    }

    /**
     * Adds a property to this Instance.
     * @param key the property's key
     * @param value the property's value
     */
    public void addProperty(String key, Object value) {
        getProperties().put(key, value);
    }

    /**
     * Checks if this Instance has a property.
     * @param key the key to check
     * @return whether or not this Instance has the property
     */
    public boolean hasProperty(String key) {
        return getProperties().containsKey(key);
    }

    /**
     * Gets the value of a property, or null if there is none.
     * @param key the key of the property
     * @return the value of the property, or null if there is none
     */
    public Object getProperty(String key) {
        return getProperties().getOrDefault(key, null);
    }

    /**
     * Returns whether or not this is a party's instance.
     * @return whether or not this is a party's instance.
     */
    public boolean isParty() {
        return getInstanceType() == PARTY;
    }

    /**
     * Returns whether or not this is a solo instance.
     * @return whether or not this is a solo instance.
     */
    public boolean isSolo() {
        return getInstanceType() == SOLO;
    }

    /**
     * Returns the Field according to the given field id. If there is currently no such Field, tries to make one and
     * add it to the active Field list.
     * @param fieldID the field's id to get
     * @return the corresponding active Field
     */
    public Field getField(int fieldID) {
        Field field;
        if (getFields().containsKey(fieldID)) {
            field = getFields().get(fieldID);
        } else {
            field = FieldData.getFieldCopyById(fieldID);
            getFields().put(field.getId(), field);
        }
        return field;
    }

    /**
     * Sets the forced return Field of this Instance.
     * @param forcedReturn the forced return field's id
     */
    public void setForcedReturn(int forcedReturn) {
        this.forcedReturn = forcedReturn;
    }

    /**
     * Sets the enter Field of this Instance
     * @param enterFieldId the enter field's id
     */
    public void setEnterFieldId(int enterFieldId) {
        this.enterFieldId = enterFieldId;
    }

    /**
     * Sets the enter Portal of this Instance
     * @param enterPortalId the enter portal's id
     */
    public void setEnterPortalId(int enterPortalId) {
        this.enterPortalId = enterPortalId;
    }

    /**
     * Clears all information from this Instance, stops any events, and warps every eligible Char to the forced return
     * Field.
     */
    public void clear() {
        int fieldId = getForcedReturn();
        int portalId = getForcedReturnPortalId();
        boolean useFieldReturn = fieldId == 0;
        for (Char chr : getChars()) {
            Field field = chr.getField();
            chr.setDeathCount(-1);
            chr.getScriptManager().stopEvents(); // Stops the FixedRate Event from the Field Script
            chr.setInstance(null);
            if (useFieldReturn) {
                chr.warp(field.getForcedReturn());
            } else if (portalId == -1){
                chr.warp(fieldId);
            } else {
                chr.warp(fieldId, portalId);
            }
        }
        stopEvents();
        fields.clear();
    }

    /**
     * Stops all events of this Instance, and each of the eligible Char's ScriptManager's events.
     */
    public void stopEvents() {
        if (getWarpOutTimer() != null) {
            getWarpOutTimer().cancel(true);
        }
        for (Char chr : getChars()) {
            chr.getScriptManager().stopEvents();
        }
    }

    /**
     * Sets the timeout of this Instance, after which every Char will be forced out. Creates a Clock for everyone.
     * @param seconds the amount of seconds until every Char is forced out
     */
    public void setTimeout(int seconds) {
        Char chr = getChars().get(0);
        int returnMap = chr.getField().getForcedReturn();
        if (warpOutTimer != null) {
            warpOutTimer.cancel(true);
        }
        warpOutTimer = EventManager.addEvent(this::clear, seconds, TimeUnit.SECONDS);
        warpOutTimeout = System.currentTimeMillis() + seconds * 1000;
        broadcast(FieldPacket.clock(ClockPacket.secondsClock(seconds)));
    }

    /**
     * Returns the amount of seconds until this Instance closes.
     * @return the remaining time
     */
    public int getRemainingTime() {
        return (int) ((warpOutTimeout - System.currentTimeMillis()) / 1000);
    }

    /**
     * Broadcasts a Packet to everyone in this Instance.
     * @param outPacket the Packet to send
     */
    public void broadcast(OutPacket outPacket) {
        for (Char chr : getChars()) {
            chr.write(outPacket);
        }
    }

    public int getForcedReturnPortalId() {
        return forcedReturnPortalId;
    }

    public void setForcedReturnPortalId(int forcedReturnPortalId) {
        this.forcedReturnPortalId = forcedReturnPortalId;
    }
}
