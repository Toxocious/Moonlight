package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.constants.SkillConstants;

import javax.persistence.*;

/**
 * Created by Asura on 13-6-2018.
 */
@Entity
@Table(name = "chosenskills")
public class ChosenSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int skillId;
    private int position;

    public ChosenSkill() {

    }

    public ChosenSkill(int id, int skillId, int position) {
        this.id = id;
        this.skillId = skillId;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static int getPositionByImpecSkillId(int impecSkillid) {
        return SkillConstants.getSMJobIdByImpecSkillId(impecSkillid);
    }

    public static void setChosenSkill(Char chr, int stolenSkillId, int impecSkillId) {
        int position = getPositionByImpecSkillId(impecSkillId);

        if(chr.getChosenSkillByPosition(position) != null) {
            chr.removeChosenSkill(chr.getChosenSkillByPosition(position));
        }

        ChosenSkill chosenSkill = new ChosenSkill(0, stolenSkillId, position);
        chr.addChosenSkill(chosenSkill);
    }
}
