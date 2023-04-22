package net.swordie.ms.world.field;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.enums.ClockType;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.scripts.ScriptManagerImpl;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Asura
 * Created on 14/09/2018.
 */
public class Clock {
    private ClockType clockType;
    private Field field;
    private int seconds;
    private long timeInMillis; // Time (in millis) when the Clock will be removed
    private ScheduledFuture clockRemovalTimer;
    private int warpFieldId;
    private int portalId;
    private String warpMethod;

    public Clock(ClockType clockType, Field field, int seconds) {
        this.clockType = clockType;
        this.field = field;
        this.seconds = seconds;
        this.timeInMillis = (seconds*1000) + System.currentTimeMillis();

        createClock();
    }

    // Warp Clock
    public Clock(ClockType clockType, Field field, int seconds, String warpMethod, int warpFieldId, int portalId) {
        this.clockType = clockType;
        this.field = field;
        this.seconds = seconds;
        this.timeInMillis = (seconds*1000) + System.currentTimeMillis();
        this.warpFieldId = warpFieldId;
        this.warpMethod = warpMethod;
        this.portalId = portalId;

        createClock();
    }

    public void createClock() {
        if (field.getClock() != null) {
            field.getClock().removeClock();
        }
        switch (getClockType()) {
            case SecondsClock:
                field.broadcastPacket(FieldPacket.clock(ClockPacket.secondsClock(seconds)));
                break;
            case StopWatch:
                field.broadcastPacket(FieldPacket.clock(ClockPacket.stopWatch(seconds)));
                break;
        }
        field.setClock(this);
        clockRemovalTimer = EventManager.addEvent(this::removeClock, seconds, TimeUnit.SECONDS);
    }

    public void showClock(Char chr) {
        if(field.getClock() != null) {
            switch (field.getClock().getClockType()) {
                case SecondsClock:
                    chr.write(FieldPacket.clock(ClockPacket.secondsClock((int) getRemainingTime())));
                    break;
                case StopWatch:
                    chr.write(FieldPacket.clock(ClockPacket.stopWatch((int) getRemainingTime())));
                    break;
            }
        }
    }

    public int getRemainingTime() {
        return (int) ((timeInMillis - System.currentTimeMillis()) / 1000);
    }

    public void removeClock() {
        if(field.getClock() != null) {
            if (warpFieldId > 0) {
                for (Char chr : field.getChars()) {
                    ScriptManagerImpl sm = chr.getScriptManager();
                    switch (getWarpMethod()) {
                        case "warp":
                            sm.warp(getWarpFieldId(), getPortalId());
                            break;
                        case "warpInstanceIn":
                            sm.warpInstanceIn(getWarpFieldId(), getPortalId());
                            break;
                        case "warpInstanceOut":
                            sm.warpInstanceOut(getWarpFieldId(), getPortalId());
                            break;
                    }
                }
            }
            field.broadcastPacket(FieldPacket.clock(ClockPacket.removeClock()));
            clockRemovalTimer.cancel(true);
            field.setClock(null);
        }
    }


    public ClockType getClockType() {
        return clockType;
    }

    public void setClockType(ClockType clockType) {
        this.clockType = clockType;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getWarpFieldId() {
        return warpFieldId;
    }

    public void setWarpFieldId(int warpFieldId) {
        this.warpFieldId = warpFieldId;
    }

    public int getPortalId() {
        return portalId;
    }

    public void setPortalId(int portalId) {
        this.portalId = portalId;
    }

    public String getWarpMethod() {
        return warpMethod;
    }

    public void setWarpMethod(String warpMethod) {
        this.warpMethod = warpMethod;
    }
}
