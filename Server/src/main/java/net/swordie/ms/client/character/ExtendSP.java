package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.Util;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2/18/2017.
 */
@Entity
@Table(name = "extendsp")
public class ExtendSP {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "extendSP_id")
    private List<SPSet> spSet;

    public ExtendSP() {
        this(0);
    }

    public ExtendSP(int subJobs) {
        spSet = new ArrayList<>();
        for(int i = 1; i <= subJobs; i++) {
            spSet.add(new SPSet((byte) i, 0));
        }
    }

    public List<SPSet> getSpSet() {
        return spSet;
    }

    public int getTotalSp() {
        return spSet.stream().mapToInt(SPSet::getSp).sum();
    }

    public void setSpSet(List<SPSet> spSet) {
        this.spSet = spSet;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getSpSet().size());
        for(SPSet spSet : getSpSet()) {
            outPacket.encodeByte(spSet.getJobLevel());
            outPacket.encodeInt(spSet.getSp());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSpToJobLevel(int jobLevel, int sp) {
        getSpSet().stream().filter(sps -> sps.getJobLevel() == jobLevel).findFirst().ifPresent(spSet -> spSet.setSp(sp));
    }

    public int getSpByJobLevel(byte jobLevel) {
        SPSet spSet = Util.findWithPred(getSpSet(), sps -> sps.getJobLevel() == jobLevel);
        if(spSet != null) {
            return spSet.getSp();
        }
        return -1;
    }
}
