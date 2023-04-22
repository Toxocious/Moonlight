package net.swordie.ms.client.character.quest.progress;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

import javax.persistence.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
@Entity
@DiscriminatorValue("item")
public class QuestProgressItemRequirement extends QuestProgressRequirement {


    @Column(name = "unitID")
    private int itemID;
    @Column(name = "requiredCount")
    private int requiredCount;

    public QuestProgressItemRequirement() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    @Override
    public boolean isComplete(Char chr) {
        return chr.hasItemCount(getItemID(), getRequiredCount());
    }

    @Override
    public QuestProgressRequirement deepCopy() {
        QuestProgressItemRequirement qpir = new QuestProgressItemRequirement();
        qpir.setItemID(getItemID());
        qpir.setRequiredCount(getRequiredCount());
        qpir.setOrder(getOrder());
        return qpir;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getItemID());
        dos.writeInt(getRequiredCount());
        dos.writeInt(getOrder());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestProgressItemRequirement qpir = new QuestProgressItemRequirement();
        qpir.setItemID(dis.readInt());
        qpir.setRequiredCount(dis.readInt());
        qpir.setOrder(dis.readInt());
        return qpir;
    }
}
