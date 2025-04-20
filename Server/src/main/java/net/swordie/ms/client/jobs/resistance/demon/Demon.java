package net.swordie.ms.client.jobs.resistance.demon;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.loaders.SkillData;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Demon extends Job {

    //Demon Skills
    public static final int DARK_WINDS = 30010110;
    public static final int DEMONIC_BLOOD = 30010185;
    public static final int SECRET_ASSEMBLY = 30001281;

    // Demon V skill
    public static final int DEFENDER_OF_THE_DEMON = 400001013;
    public static final int DEFENDER_OF_THE_DEMON_MASTEMA_MARK = 400001016;

    private static final int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
            DARK_WINDS,
            DEMONIC_BLOOD,
    };

    private long mastemaMarkTime;

    public Demon(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.DEMON.getJobId();
    }


    public void giveMastemasMark() {
        if (!chr.hasSkill(DEFENDER_OF_THE_DEMON)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(DEFENDER_OF_THE_DEMON);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        o.nOption = 1;
        o.rOption = DEFENDER_OF_THE_DEMON_MASTEMA_MARK;
        o.tOption = si.getValue(q, slv);
        o.xOption = 2;
        tsm.putCharacterStatValue(MastemasMark, o);
        tsm.sendSetStatPacket();
        mastemaMarkTime = System.currentTimeMillis() + (1000 * si.getValue(q, slv));
    }


    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
        }

        super.handleAttack(c, attackInfo);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }


    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case DemonAvenger.BATTLE_PACT_DA:
            case DemonSlayer.BATTLE_PACT_DS:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case DemonAvenger.MAPLE_WARRIOR_DA:
            case DemonSlayer.MAPLE_WARRIOR_DS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case DemonSlayer.DEMONIC_FORTITUDE_DS:
            case DemonAvenger.DEMONIC_FORTITUDE_DA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;


        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            switch (skillId) {
            }
        }
        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();


        // Mastema's Mark
        if (tsm.hasStatBySkillId(DEFENDER_OF_THE_DEMON_MASTEMA_MARK)) {
            if (hitInfo.hpDamage > 0) {
                Skill skill = chr.getSkill(DEFENDER_OF_THE_DEMON);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int slv = skill.getCurrentLevel();
                int dmgIntakeReduction = si.getValue(s, slv);
                int count = tsm.getOption(MastemasMark).xOption;

                hitInfo.hpDamage -= (hitInfo.hpDamage * dmgIntakeReduction) / 100;

                if (count > 0) {
                    count--;
                    o1.setInMillis(true);
                    o1.nOption = 1;
                    o1.rOption = DEFENDER_OF_THE_DEMON_MASTEMA_MARK;
                    o1.tOption = (int) (mastemaMarkTime - System.currentTimeMillis());
                    o1.xOption = count;
                    tsm.putCharacterStatValue(MastemasMark, o1);
                    tsm.sendSetStatPacket();
                } else {
                    tsm.removeStatsBySkill(DEFENDER_OF_THE_DEMON_MASTEMA_MARK);
                }
            }
        }

        super.handleHit(c, inPacket, hitInfo);
    }

    // Character creation related methods ---------------------------------------------------------------------------------------------
    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(927000000);
    }

    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }
}
