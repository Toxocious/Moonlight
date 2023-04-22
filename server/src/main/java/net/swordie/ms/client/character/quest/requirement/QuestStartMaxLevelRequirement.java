package net.swordie.ms.client.character.quest.requirement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created on 3/2/2018.
 */
public class QuestStartMaxLevelRequirement implements QuestStartRequirement {

    private short level;

    public QuestStartMaxLevelRequirement(short level) {
        this.level = level;
    }

    public QuestStartMaxLevelRequirement() {

    }

    private short getLevel() {
        return level;
    }

    @Override
    public boolean hasRequirements(Char chr) {
        return chr.getLevel() <= getLevel();
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(getLevel());
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        return new QuestStartMaxLevelRequirement(dis.readShort());
    }
}
