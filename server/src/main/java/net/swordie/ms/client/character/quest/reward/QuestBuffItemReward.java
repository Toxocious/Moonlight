package net.swordie.ms.client.character.quest.reward;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.ItemBuffs;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QuestBuffItemReward implements QuestReward {
    private int buffItemID;
    private int status;

    public QuestBuffItemReward(int buffItemID, int status) {
        this.buffItemID = buffItemID;
        this.status = status;
    }

    public QuestBuffItemReward() { }

    public int getBuffItemID() {
        return buffItemID;
    }

    public void setBuffItemID(int buffItemID) {
        this.buffItemID = buffItemID;
    }

    public void setStatus(int status) { this.status = status; }

    public int getStatus() { return status; }

    @Override
    public void giveReward(Char chr) {
        ItemBuffs.giveItemBuffsFromItemID(chr, chr.getTemporaryStatManager(), getBuffItemID());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getBuffItemID());
        dos.writeInt(getStatus());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestBuffItemReward(dis.readInt(), dis.readInt());
    }
}
