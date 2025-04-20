package net.swordie.ms.loaders.containerclasses;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 7/23/2018.
 */
@Entity
@Table(name = "monster_collection")
public class MonsterCollectionMobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int mobID;
    private int region;
    private int session;
    private int position;

    public MonsterCollectionMobInfo() {
    }

    public MonsterCollectionMobInfo(int mobID, int region, int session, int position) {
        this.mobID = mobID;
        this.region = region;
        this.session = session;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobID() {
        return mobID;
    }

    public void setMobID(int mobID) {
        this.mobID = mobID;
    }

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
