package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.enums.ItemJob;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 1/2/2019.
 */
@Entity
@Table(name = "equip_drops")
public class EquipDrop {
    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private ItemJob job;
    private int level;

    public EquipDrop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemJob getJob() {
        return job;
    }

    public void setJob(ItemJob job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
