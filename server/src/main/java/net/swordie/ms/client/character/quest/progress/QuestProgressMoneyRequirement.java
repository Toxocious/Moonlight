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
@DiscriminatorValue("money")
public class QuestProgressMoneyRequirement extends QuestProgressRequirement {

    @Column(name = "requiredCount")
    private int money;

    public QuestProgressMoneyRequirement() {
    }

    public QuestProgressMoneyRequirement(int money){
        this.money = money;
    }

    @Override
    public boolean isComplete(Char chr) {
        return chr.getMoney() >= getMoney();
    }

    @Override
    public QuestProgressRequirement deepCopy() {
        QuestProgressMoneyRequirement qpmr = new QuestProgressMoneyRequirement();
        qpmr.setMoney(getMoney());
        qpmr.setOrder(getOrder());
        return qpmr;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getMoney());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestProgressMoneyRequirement(dis.readInt());
    }

    public void addMoney(int money) {
        setMoney(getMoney() + money);
    }
}
