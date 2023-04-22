package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.Channel;
import net.swordie.ms.world.field.Field;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asura on 7-8-2018.
 */
public class TownPortal {
    private Char chr;
    private Channel channel;
    private int skillid;
    private int duration;
    private ScheduledFuture despawnTimer;

    private Position townPosition;
    private int townFieldId;

    private Position fieldPosition;
    private int fieldFieldId;

    public TownPortal(Char chr, Position townPosition, Position fieldPosition, int townFieldId, int fieldFieldId, int skillid, int duration) {
        this.chr = chr;
        this.channel = chr.getClient().getChannelInstance();
        this.townPosition = townPosition;
        this.fieldPosition = fieldPosition;
        this.townFieldId = townFieldId;
        this.fieldFieldId = fieldFieldId;
        this.skillid = skillid;
        this.duration = duration;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Position getTownPosition() {
        return townPosition;
    }

    public void setTownPosition(Position townPosition) {
        this.townPosition = townPosition;
    }

    public Position getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(Position fieldPosition) {
        this.fieldPosition = fieldPosition;
    }

    public int getTownFieldId() {
        return townFieldId;
    }

    public void setTownFieldId(int townFieldId) {
        this.townFieldId = townFieldId;
    }

    public int getFieldFieldId() {
        return fieldFieldId;
    }

    public void setFieldFieldId(int fieldFieldId) {
        this.fieldFieldId = fieldFieldId;
    }

    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void spawnTownPortal() {
        Field fieldField = channel.getField(getFieldFieldId()); // get fieldField

        if(despawnTimer != null && !despawnTimer.isDone()) {
            despawnTimer.cancel(true);
        }
        despawnTimer = EventManager.addEvent(this::despawnTownPortal, getDuration(), TimeUnit.SECONDS);

        fieldField.broadcastPacket(FieldPacket.townPortalCreated(this, false)); // Broadcasts spawning of the TownPortal to the fieldField.
        fieldField.addTownPortal(this); // Adds TownPortal to the TownPortal list, so it will show the TownPortal to others that enter the map after spawning
        getChr().setTownPortal(this);
    }

    public void despawnTownPortal() {
        if(despawnTimer != null && !despawnTimer.isDone()) {
            despawnTimer.cancel(true);
        }

        // Initialise both Fields
        Field fieldField = channel.getField(getFieldFieldId());
        Field townField = channel.getField(getTownFieldId());

        // Remove portals from the Fields
        fieldField.broadcastPacket(FieldPacket.townPortalRemoved(this, false));
        removeTownPortalInTownField();

        // Remove townPortals from the TP List
        fieldField.removeTownPortal(this);
        townField.removeTownPortal(this);
        getChr().setTownPortal(null);
    }

    public void removeTownPortalInTownField() { // Normal 'TOWN_PORTAL_REMOVED' only removes the field TownPortals, not the town TownPortals
        Field townField = channel.getField(getTownFieldId());
        if(townField.getChars().size() <= 0) {
            chr.write(WvsContext.townPortal(new TownPortal(chr, new Position(), new Position(),
                    999999999, 999999999, getSkillid(), 1)));
        } else {
            townField.broadcastPacket(WvsContext.townPortal(new TownPortal(chr, new Position(), new Position(),
                    999999999, 999999999, getSkillid(), 1)));
        }
    }

    // Called when characters enter the map with an already existing TownPortal
    public void showTownPortal(Field field) {
        if(field.isTown()) {
            chr.write(WvsContext.townPortal(this));
        } else {
            chr.write(FieldPacket.townPortalCreated(this, true));
        }
    }
}
