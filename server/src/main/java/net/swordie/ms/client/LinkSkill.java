package net.swordie.ms.client;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created on 6/7/2018.
 */
@Entity
@Table(name = "linkskills")
public class LinkSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int originID;
    private int usingID;
    private int linkSkillID;
    private int level;

    public LinkSkill() {
    }

    public LinkSkill(int originID, int usingID, int linkSkillID, int level) {
        this.originID = originID;
        this.usingID = usingID;
        this.linkSkillID = linkSkillID;
        this.level = level;
    }

    public int getUsingID() {
        return usingID;
    }

    public void setUsingID(int usingID) {
        this.usingID = usingID;
    }

    public int getLinkSkillID() {
        return linkSkillID;
    }

    public void setLinkSkillID(int linkSkillID) {
        this.linkSkillID = linkSkillID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOriginID() {
        return originID;
    }

    public void setOriginID(int originID) {
        this.originID = originID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkSkill linkSkill = (LinkSkill) o;
        return originID == linkSkill.originID
                && linkSkillID == linkSkill.linkSkillID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(usingID, linkSkillID);
    }
}
