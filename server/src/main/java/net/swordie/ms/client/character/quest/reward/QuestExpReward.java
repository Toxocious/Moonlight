package net.swordie.ms.client.character.quest.reward;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestExpReward implements QuestReward {

    private long exp;

    public QuestExpReward(long exp) {
        this.exp = exp;
    }

    public QuestExpReward() {

    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getExp() {
        return exp;
    }

    @Override
    public void giveReward(Char chr) {
        chr.addExp(getExp());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeLong(getExp());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestExpReward(dis.readLong());
    }
}
