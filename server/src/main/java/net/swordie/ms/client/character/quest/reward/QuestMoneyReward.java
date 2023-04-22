package net.swordie.ms.client.character.quest.reward;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestMoneyReward implements QuestReward {
    private long money;

    public QuestMoneyReward(long money) {
        this.money = money;
    }

    public QuestMoneyReward() {

    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }


    @Override
    public void giveReward(Char chr) {
        chr.addMoney(money);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(getMoney());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestMoneyReward(dis.readLong());
    }
}
