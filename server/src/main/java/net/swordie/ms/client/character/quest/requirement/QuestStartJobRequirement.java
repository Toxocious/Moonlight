package net.swordie.ms.client.character.quest.requirement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.DatSerializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 3/2/2018.
 */
public class QuestStartJobRequirement implements QuestStartRequirement {
    private Set<Short> jobReq;

    public QuestStartJobRequirement() {
        this.jobReq = new HashSet<>();
    }

    public Set<Short> getJobReq() {
        return jobReq;
    }

    public void addJobReq(short job) {
        getJobReq().add(job);
    }

    @Override
    public boolean hasRequirements(Char chr) {
        return getJobReq().contains(chr.getJob());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(getJobReq().size());
        for(short s : getJobReq()) {
            dos.writeShort(s);
        }
    }

    @Override
    public DatSerializable load(DataInputStream dis) throws IOException {
        QuestStartJobRequirement qsjr = new QuestStartJobRequirement();
        short size = dis.readShort();
        for (int i = 0; i < size; i++) {
            qsjr.addJobReq(dis.readShort());
        }
        return qsjr;
    }
}
