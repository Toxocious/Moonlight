package net.swordie.ms.client.character.skills.temp;

import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.EnergyCharged;

/**
 * Created on 2/3/2018.
 */
public class TemporaryStatBase {
    private Option option;
    private FileTime lastUpdated;
    protected int expireTerm;
    private boolean dynamicTermSet;

    public TemporaryStatBase(boolean dynamicTermSet) {
        option = new Option();
        option.nOption = 0;
        option.rOption = 0;
        lastUpdated = FileTime.currentTime();
        this.dynamicTermSet = dynamicTermSet;
    }

    public Option getOption() {
        return option;
    }

    public FileTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(FileTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getExpireTerm() {
        return expireTerm * 1000;
    }

    public void setExpireTerm(int expireTerm) {
        this.expireTerm = expireTerm;
    }

    public boolean isDynamicTermSet() {
        return dynamicTermSet;
    }

    public void setDynamicTermSet(boolean dynamicTermSet) {
        this.dynamicTermSet = dynamicTermSet;
    }

    public int getMaxValue() {
        return 10000;
    }

    public boolean isActive() {
        return getOption().nOption >= 10000;
    }

    public boolean hasExpired() {
        return hasExpired(System.currentTimeMillis());
    }

    public boolean hasExpired(long time) {
        boolean result = false;
        if (isDynamicTermSet()) {
            result = getExpireTerm() > time - getLastUpdated().toMillis();
        }
        return result;
    }

    public int getNOption() {
        return getOption().nOption;
    }

    public int getROption() {
        return getOption().rOption;
    }

    public void reset() {
        getOption().nOption = 0;
        getOption().rOption = 0;
        setLastUpdated(System.currentTimeMillis());
    }

    private void setLastUpdated(long epochMillis) {
        FileTime.fromEpochMillis(epochMillis);
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getOption().nOption);
        outPacket.encodeInt(getOption().rOption);
        outPacket.encodeByte(isDynamicTermSet());
        outPacket.encodeInt(getExpireTerm());
        if (isDynamicTermSet()) {
            outPacket.encodeShort(getExpireTerm() / 1000); // in seconds
        }
        if (getOption().rOption == Adele.GRAVE_PROCLAMATION) {
            outPacket.encodeInt(getOption().xOption);
            outPacket.encodeInt(getOption().yOption);
        }
    }

    public void setNOption(int i) {
        getOption().nOption = i;
    }

    public void setROption(int reason) {
        getOption().rOption = reason;
    }

    public void setXOption(int i) {
        getOption().xOption = i;
    }

    public void setYOption(int i) {
        getOption().yOption = i;
    }
}
