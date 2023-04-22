package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.scripts.ScriptType;

/**
 * Created on 12/14/2017.
 */
public class Citizen extends Job {
    public static final int CRYSTAL_THROW = 30001000;
    public static final int INFLITRATE = 30001001;
    public static final int POTION_MASTERY = 30000002;

    private static final int[] addedSkills = new int[]{
            CRYSTAL_THROW,
            INFLITRATE,
            POTION_MASTERY
    };

    public Citizen(Char chr) {
        super(chr);

        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setRootId(3000);
                    skill.setMasterLevel(3);
                    skill.setMaxLevel(3);
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {

        super.handleAttack(c, attackInfo);
    }

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.CITIZEN.getJobId();
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
        chr.getScriptManager().startScript(0, "job_resistance", ScriptType.Npc);
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
        Item mechanicalChair = ItemData.getItemDeepCopy(3010538);
        chr.addItemToInventory(mechanicalChair);
    }
}
