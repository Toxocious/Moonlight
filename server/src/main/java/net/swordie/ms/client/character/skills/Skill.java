package net.swordie.ms.client.character.skills;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Map;

/**
 * Created on 12/20/2017.
 */
@Entity
@Table(name = "skills")
public class Skill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int charId;
    private int skillId;
    private int rootId;
    private int maxLevel;
    private int currentLevel;
    private int masterLevel;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = Math.max(0, currentLevel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public void setMasterLevel(int masterLevel) {
        this.masterLevel = masterLevel;
    }

    public int getMasterLevel() {
        return masterLevel;
    }

    @Override
    public String toString() {
        return "id = " + getSkillId() + ", cur = " + getCurrentLevel() + ", master = " + getMasterLevel();
    }
}
