package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.util.Position;

/**
 * Created on 12-1-2019.
 *
 * @author Asura
 */
public class Wreckage extends Life {

    private int ownerId, skillId, duration;

    public Wreckage(int templateId) {
        super(templateId);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static Wreckage getWreckageBy(Char chr, int skillId, Position position, int duration, int type) {
        Wreckage wreckage = new Wreckage(-1);
        wreckage.setOwnerId(chr.getId());
        wreckage.setField(chr.getField());
        wreckage.setSkillId(skillId);
        wreckage.setPosition(position);
        wreckage.setDuration(duration);
        wreckage.setType(type);

        return wreckage;
    }
}
