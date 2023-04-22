package net.swordie.ms.life.pet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.PetItem;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.life.Life;
import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/20/2017.
 */
public class Pet extends Life {
    private int id;
    private final int ownerID;
    private int idx;
    private String name;
    private long petLockerSN;
    private int hue = -1;
    private short giantRate = 100;
    private boolean transformed;
    private boolean reinforced;
    private PetItem item;

    public Pet(int templateId, int ownerID) {
        super(templateId);
        this.ownerID = ownerID;
    }

    public int getActiveSkillCoolTime() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getTemplateId());
        outPacket.encodeString(getName());
        outPacket.encodeLong(getItem().getId());
        outPacket.encodePosition(getPosition());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getFh());
        outPacket.encodeInt(getHue());
        outPacket.encodeShort(0); // 202
        outPacket.encodeShort(getGiantRate());
        outPacket.encodeByte(isTransformed());
        outPacket.encodeByte(isReinforced());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPetLockerSN() {
        return petLockerSN;
    }

    public void setPetLockerSN(long petLockerSN) {
        this.petLockerSN = petLockerSN;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public short getGiantRate() {
        return giantRate;
    }

    public void setGiantRate(short giantRate) {
        this.giantRate = giantRate;
    }

    public boolean isTransformed() {
        return transformed;
    }

    public void setTransformed(boolean transformed) {
        this.transformed = transformed;
    }

    public boolean isReinforced() {
        return reinforced;
    }

    public void setReinforced(boolean reinforced) {
        this.reinforced = reinforced;
    }

    public void setItem(PetItem item) {
        this.item = item;
    }

    public PetItem getItem() {
        return item;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        onlyChar.write(UserLocal.petActivateChange(this, true, (byte) 0));
    }

    @Override
    public void broadcastLeavePacket() {
        getField().broadcastPacket(UserLocal.petActivateChange(this, false, (byte) 0));
    }

    public int getOwnerID() {
        return ownerID;
    }
}
