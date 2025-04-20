package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.loaders.SkillData;

import javax.persistence.*;

/**
 * Created by Asura on 13-6-2018.
 */
@Entity
@Table(name = "stolenskills")
public class StolenSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int skillid;
    private int position;
    private byte currentlv;

    public StolenSkill() {

    }

    public StolenSkill(int id, int skillid, int position, byte currentlv) {
        this.id = id;
        this.skillid = skillid;
        this.position = position;
        this.currentlv = currentlv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillid() {
        return skillid;
    }

    public void setSkillid(int skillid) {
        this.skillid = skillid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public byte getCurrentlv() {
        return currentlv;
    }

    public void setCurrentlv(byte currentlv) {
        this.currentlv = currentlv;
    }


    public static void setSkill(Char chr, int skillId, int position, byte currentLv) {
        Skill skill = SkillData.getSkillDeepCopyById(skillId);
        skill.setCurrentLevel(currentLv);
        chr.addSkill(skill);

        StolenSkill stolenSkill = new StolenSkill(0, skillId, position, currentLv);
        chr.addStolenSkill(stolenSkill);
    }

    public static void removeSkill(Char chr, int skillId) {
        StolenSkill stolenSkill = chr.getStolenSkillBySkillId(skillId);
        if(stolenSkill == null) {
            return;
        }
        chr.removeStolenSkill(stolenSkill);
        if(chr.hasSkill(skillId)) {
            Skill skill = SkillData.getSkillDeepCopyById(skillId);
            skill.setCurrentLevel(0);
            chr.addSkill(skill);
        }
    }

    public static int getFirstEmptyPosition(Char chr, int skillId) {

        //Used to calculate the position to assign the stolen skill to
        int smJobID = SkillConstants.getStealSkillManagerTabFromSkill(skillId);
        int maxPos = SkillConstants.getMaxPosBysmJobID(smJobID);
        int startingPos = SkillConstants.getStartPosBysmJobID(smJobID);

        for(int i = startingPos; i <= (startingPos+maxPos); i++) {
            if(chr.getStolenSkillByPosition(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public static int getPositionForTab(int position, int skillId) {

        //Used to calculate the position to assign the stolen skill to
        int smJobID = SkillConstants.getStealSkillManagerTabFromSkill(skillId);
        int maxPos = SkillConstants.getMaxPosBysmJobID(smJobID);
        int startingPos = SkillConstants.getStartPosBysmJobID(smJobID);

        return position-startingPos;
    }

    public static int getPositionPerTabFromStolenSkill(StolenSkill stolenSkill) {

        //Used to calculate the position to assign the stolen skill to
        int smJobID = SkillConstants.getStealSkillManagerTabFromSkill(stolenSkill.getSkillid());
        int maxPos = SkillConstants.getMaxPosBysmJobID(smJobID);
        int startingPos = SkillConstants.getStartPosBysmJobID(smJobID);

        return stolenSkill.getPosition() - startingPos;
    }
}
