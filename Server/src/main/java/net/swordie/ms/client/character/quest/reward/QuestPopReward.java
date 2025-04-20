package net.swordie.ms.client.character.quest.reward;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/6/2018.
 */
public class QuestPopReward implements QuestReward {

    private int pop;

    public QuestPopReward(int pop) {
        this.pop = pop;
    }

    public QuestPopReward() {

    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    @Override
    public void giveReward(Char chr) {
        chr.addStat(Stat.pop, getPop());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(getPop());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestPopReward(dis.readInt());
    }
}
