package net.swordie.ms.client.character.quest.reward;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.loaders.DatSerializable;
import net.swordie.ms.loaders.ItemData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestItemReward implements QuestReward{
    private int id;
    private short quantity;
    private String potentialGrade;
    private int status;
    private int prop;
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    @Override
    public void giveReward(Char chr) {
        Item item = ItemData.getItemDeepCopy(getId());
        item.setQuantity(getQuantity());
        if (getQuantity() < 0) {
            chr.consumeItem(item.getItemId(), -getQuantity());
        } else {
            chr.addItemToInventory(item);
        }
    }

    public void setPotentialGrade(String potentialGrade) {
        this.potentialGrade = potentialGrade;
    }

    public String getPotentialGrade() {
        return potentialGrade;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public int getProp() {
        return prop;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getId());
        dos.writeShort(getQuantity());
        dos.writeUTF(getPotentialGrade());
        dos.writeInt(getStatus());
        dos.writeInt(getProp());
        dos.writeInt(getGender());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestItemReward qir = new QuestItemReward();
        qir.setId(dis.readInt());
        qir.setQuantity(dis.readShort());
        qir.setPotentialGrade(dis.readUTF());
        qir.setStatus(dis.readInt());
        qir.setProp(dis.readInt());
        qir.setGender(dis.readInt());
        return qir;
    }
}
