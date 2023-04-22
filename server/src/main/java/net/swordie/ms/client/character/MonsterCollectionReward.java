package net.swordie.ms.client.character;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Sjonnie
 * Created on 7/23/2018.
 */
@Entity
@Table(name = "monster_collection_rewards")
public class MonsterCollectionReward implements Serializable {
    @Id
    private int region;
    @Id
    private int session; // -1 == region (medal)
    @Id
    @Column(name = "groupid")
    private int group; // -1 == session

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
