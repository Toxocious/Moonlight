package net.swordie.ms.client.party;

import net.swordie.ms.client.character.Char;

/**
 * Created on 3/19/2018.
 */
public class PartyMember {
    private Char chr;
    private int partyBossCharacterID;
    private TownPortal townPortal;
    private int charID;
    private String name;
    private short job;
    private short subJob;
    private int level;
    private int channel;
    private int fieldID;

    // 4 + 13 + 4 + 4 + 4 + 4 + 4 + 4

    public PartyMember(Char chr) {
        this.chr = chr;
        updateInfoByChar(chr);
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    public String getCharName() {
        return name;
    }

    public short getJob() {
        return job;
    }

    public short getSubSob() {
        return subJob;
    }

    public int getLevel() {
        return level;
    }

    public boolean isOnline() {
        return chr != null && chr.isOnline();
    }

    public void setChr(Char chr) {
        this.chr = chr;
        updateInfoByChar(chr);
    }

    public Char getChr() {
        return chr;
    }

    public int getPartyBossCharacterID() {
        return partyBossCharacterID;
    }

    public void setPartyBossCharacterID(int partyBossCharacterID) {
        this.partyBossCharacterID = partyBossCharacterID;
    }

    public int getChannel() {
        return channel;
    }

    public int getFieldID() {
        return fieldID;
    }

    public TownPortal getTownPortal() {
        return townPortal;
    }

    public void setTownPortal(TownPortal townPortal) {
        this.townPortal = townPortal;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartyMember && ((PartyMember) obj).getChr().equals(getChr());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(short job) {
        this.job = job;
    }

    public void setSubJob(short subJob) {
        this.subJob = subJob;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }

    public void updateInfoByChar(Char chr) {
        if (chr != null) {
            this.chr = chr;
            setCharID(chr.getId());
            setName(chr.getName());
            setJob(chr.getJob());
            setSubJob((short) chr.getSubJob());
            setLevel(chr.getLevel());
            setChannel(chr.getClient() == null ? -1 : chr.getClient().getChannel());
            setFieldID(chr.getFieldID());
        } else {
            setFieldID(0);
            setChannel(-1);
        }
    }
}
