package net.swordie.ms.client.jobs.adventurer.pirate;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.List;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.IndieCr;

public class Jett extends Pirate {
    public static final int GALACTIC_MIGHT = 5081023; //Buff


    public static final int STARLINE_TWO = 5701010; //Special Attack (Stun Debuff)
    public static final int BOUNTY_CHASER = 5701013; //Buff


    public static final int SLIPSTREAM_SUIT = 5711024; //Buff
    public static final int TURRET_DEPLOYMENT = 5711001; //Summon


    public static final int BIONIC_MAXIMIZER = 5721054;
    public static final int HIGH_GRAVITY = 5721066; //Buff
    public static final int MAPLE_WARRIOR_JETT = 5721000; //Buff
    public static final int HEROS_WILL_JETT = 5721002;
    public static final int EPIC_ADVENTURER_JETT = 5721053;


    // V skills
    public static final int ALLIED_FURY = 400051054;
    public static final int SUBORBITAL_SUMMON_SMALL_4 = 400051032;
    public static final int SUBORBITAL_SUMMON_SMALL_3 = 400051031;
    public static final int SUBORBITAL_SUMMON_SMALL_2 = 400051030;
    public static final int SUBORBITAL_SUMMON_SMALL_1 = 400051029;
    public static final int SUBORBITAL_SUMMON_BIG = 400051028;
    public static final int GRAVITY_CRUSH = 400051014;


    private static final List<Integer> suborbitalSummons = new ArrayList<Integer>() {{
        add(SUBORBITAL_SUMMON_BIG);
        add(SUBORBITAL_SUMMON_SMALL_1);
        add(SUBORBITAL_SUMMON_SMALL_2);
        add(SUBORBITAL_SUMMON_SMALL_3);
        add(SUBORBITAL_SUMMON_SMALL_4);
    }};


    public Jett(Char chr) {
        super(chr);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isJett(id);
    }



    public void flipSuborbitalSummon(InPacket inPacket) {
        if (inPacket.getUnreadAmount() <= 0) {
            return;
        }
        Field field = chr.getField();
        boolean toSplitter = inPacket.decodeByte() == 0; //  0 = ->Splitter   |   1 = ->Amplifier
        for (int summonSkillId : suborbitalSummons) {
            Summon summon = field.getSummonByChrAndSkillId(chr, summonSkillId);
            if (summon == null) {
                return;
            }
            summon.setHide(!summon.isHide());
        }
    }


    public void handleKeyDownSkill(Char chr, int skillID, InPacket inPacket) {
        super.handleKeyDownSkill(chr, skillID, inPacket);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Skill skill = chr.getSkill(skillID);
        int skillId = 0;
        SkillInfo si = null;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillId = skill.getSkillId();
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillId) {

        }
    }

    public void incGravityCrushDmg(Mob mob, long damage) {
        Summon summon = chr.getField().getSummonByChrAndSkillId(chr, GRAVITY_CRUSH);
        summon.setGravityCrushDmg(summon.getGravityCrushDmg() + Math.min(damage, mob.getHp()) > summon.getGravityCrushMaxDmg() ? summon.getGravityCrushMaxDmg() : summon.getGravityCrushDmg() + Math.min(damage, mob.getHp()));
        chr.getField().broadcastPacket(Summoned.gravityCrushDamage(summon));
    }

    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case STARLINE_TWO:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(hcTime, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(hcProp, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case GRAVITY_CRUSH: // TODO  Explode on Command
                Field field = chr.getField();
                if (field.getSummonByChrAndSkillId(chr, GRAVITY_CRUSH) == null) {
                    return;
                }
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = si.getValue(time, slv);
                o1.summon = field.getSummonByChrAndSkillId(chr, GRAVITY_CRUSH);
                o1.chr = chr;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        }
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case BOUNTY_CHASER:
                o1.nOption = si.getValue(dexX, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEX, o1);
                o2.nOption = si.getValue(strX, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(STR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieDamR, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o4);
                break;
            case SLIPSTREAM_SUIT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEXR, o1);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ACCR, o3);
                break;
            case HIGH_GRAVITY:
                o1.nReason = skillID;
                o1.nValue = si.getValue(prop, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieAllStat, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAllStat, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3);
                break;
            case BIONIC_MAXIMIZER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nValue = si.getValue(y, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAsrR, o2);
                tsm.putCharacterStatValue(IndieTerR, o2);
                o3.nOption = si.getValue(w, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o3);
                o4.nValue = si.getValue(indieCr, slv);
                o4.nReason = skillID;
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o4);
                break;
            case TURRET_DEPLOYMENT: //Stationary, Attacks
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnAddSummon(summon);
                break;
            case GRAVITY_CRUSH:
                Position position = inPacket.decodePosition();
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setPosition(position);
                summon.setGravityCrushMaxDmg(si.getValue(selfDestructionPlus, slv)); // TODO  multiply with  Rand within chr Range
                field.spawnSummon(summon);

                field.broadcastPacket(Summoned.gravityCrushMaxDamage(summon));
                break;
            case SUBORBITAL_SUMMON_BIG:
                for (int summonSkillId : suborbitalSummons) {
                    summon = Summon.getSummonBy(chr, summonSkillId, slv);
                    summon.setMoveAbility(MoveAbility.Walk);
                    summon.setAssistType(AssistType.GroupAttack);
                    summon.setHide(summonSkillId == skillID);
                    field.spawnSummon(summon);
                }
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
                case GRAVITY_CRUSH:
                    return 0;
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.JETT_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.JETT_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.JETT_4.getJobId());
//                break;
//        }
    }


    @Override
    public void cancelTimers() {
        super.cancelTimers();

    }

    @Override
    public void addMissingSkills(Char chr) {
        super.addMissingSkills(chr);
    }


    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();


        addMissingSkills(chr);

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();


    }
}