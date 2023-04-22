package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.util.Position;
import net.swordie.ms.world.field.Field;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Asura on 27-7-2018.
 */
public class OpenGate {
    private Char chr;
    private Position position;
    private Party party;
    private byte gateId;
    private int duration; // seconds
    private ScheduledFuture openGateDuration;


    public OpenGate(Char chr, Position position, Party party, byte gateId, int duration) {
        this.chr = chr;
        this.position = position;
        this.party = party;
        this.gateId = gateId;
        this.duration = duration;
    }


    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public byte getGateId() {
        return gateId;
    }

    public void setGateId(byte gateId) {
        this.gateId = gateId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void spawnOpenGate(Field field) {
        if (field.getOpenGates() != null) {
            List<OpenGate> openGatesWithSameChr = field.getOpenGates()
                    .stream()
                    .filter(og -> og.getChr().getId() == getChr().getId())
                    .collect(Collectors.toList());
            if (openGatesWithSameChr != null && openGatesWithSameChr.size() >= 2) {
                OpenGate openGate = openGatesWithSameChr.get(0);
                openGate.despawnOpenGate(field);
            }
        }

        field.broadcastPacket(FieldPacket.openGateCreated(this));
        field.addOpenGate(this);

        if (openGateDuration != null && !openGateDuration.isDone()) {
            openGateDuration.cancel(true);
        }
        openGateDuration = EventManager.addEvent(() -> despawnOpenGate(field), getDuration(), TimeUnit.SECONDS);
    }

    public void despawnOpenGate(Field field) {
        field.broadcastPacket(FieldPacket.openGateRemoved(this));
        field.removeOpenGate(this);
        if (openGateDuration != null) {
            openGateDuration.cancel(true);
        }
    }

    public void showOpenGate(Field field) {
        field.broadcastPacket(FieldPacket.openGateCreated(this));
    }

}
