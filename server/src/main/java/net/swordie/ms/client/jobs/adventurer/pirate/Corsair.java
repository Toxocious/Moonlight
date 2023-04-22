package net.swordie.ms.client.jobs.adventurer.pirate;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.GuidedBullet;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Corsair extends Pirate {
    public static final int INFINITY_BLAST = 5201008; //Buff
    public static final int SCURVY_SUMMONS = 5201012; //Summon
    public static final int GUN_BOOSTER = 5201003; //Buff


    public static final int ALL_ABOARD = 5210015; //Summon
    public static final int ROLL_OF_THE_DICE_SAIR = 5211007; //Buff
    public static final int OCTO_CANNON = 5211014; //Summon


    public static final int PARROTARGETTING = 5221015; //Special Attack
    public static final int NAUTILUS_STRIKE_SAIR = 5221013; //Special Attack
    public static final int MAPLE_WARRIOR_SAIR = 5221000; //Buff
    public static final int PIRATE_REVENGE_SAIR = 5220012;
    public static final int ROLL_OF_THE_DICE_SAIR_DD = 5220014;
    public static final int ROLL_OF_THE_DICE_SAIR_ADDITION = 5220044;
    public static final int ROLL_OF_THE_DICE_SAIR_SAVING_GRACE = 5220043;
    public static final int ROLL_OF_THE_DICE_SAIR_ENHANCE = 5220045;
    public static final int HEROS_WILL_SAIR = 5221010;
    public static final int MAJESTIC_PRESENCE = 5220020;
    public static final int AHOY_MATEYS = 5220019;
    public static final int EPIC_ADVENTURER_SAIR = 5221053;
    public static final int WHALERS_POTION = 5221054;
    public static final int BROADSIDE = 5221022; // Summon
    public static final int JOLLY_ROGER = 5221018; //Buff
    public static final int QUICKDRAW = 5221021; //Buff


    // V skills
    public static final int BULLET_BARRAGE = 400051006;
    public static final int TARGET_LOCK = 400051021;
    public static final int NAUTILUS_ASSAULT = 400051040;

    private int corsairSummonID = 0;
    private long bulletBarrageTime;
    private int curDurationIncCount = 0;

    public Corsair(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isCorsair(id);
    }


    private void corsairSummons() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        List<Integer> set = new ArrayList<>();
        if (chr.hasSkill(ALL_ABOARD)) {
            set.add(5210015);
            set.add(5210016);
            set.add(5210017);
            set.add(5210018);
        } else {
            set.add(5201012);
            set.add(5201013);
            set.add(5201014);
        }

        if (corsairSummonID != 0) {
            set.remove((Integer) corsairSummonID);
        }
        if (chr.hasSkill(AHOY_MATEYS)) {
            Skill skill = chr.getSkill(AHOY_MATEYS);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int random = Util.getRandomFromCollection(set);
            corsairSummonID = random;
            Summon summon = Summon.getSummonBy(chr, random, (byte) 1);
            Field field = chr.getField();
            summon.setFlyMob(false);
            summon.setMoveAbility(MoveAbility.WalkSmart);
            summon.setAssistType(AssistType.Jaguar); // 10
            field.spawnSummon(summon);

            switch (random) {
                case 5210015:
                    o1.nValue = si.getValue(z, slv);
                    o1.nReason = random;
                    o1.tTerm = 120;
                    tsm.putCharacterStatValue(EnrageCrDamMin, o1);
                    break;
                case 5210016:
                    o1.nValue = si.getValue(s, slv);
                    o1.nReason = random;
                    o1.tTerm = 120;
                    tsm.putCharacterStatValue(IndieCr, o1);
                    break;
                case 5210017:
                    o1.nValue = si.getValue(x, slv);
                    o1.nReason = random;
                    o1.tTerm = 120;
                    tsm.putCharacterStatValue(IndieMHPR, o1);
                    tsm.putCharacterStatValue(IndieMMPR, o1);
                    tsm.putCharacterStatValue(IndieSpeed, o1);
                    break;
                case 5210018:
                    o1.nOption = si.getValue(y, slv);
                    o1.rOption = random;
                    o1.tOption = 120;
                    tsm.putCharacterStatValue(DamageReduce, o1);
                    break;
            }
            o2.nValue = si.getValue(w, slv);
            o2.nReason = skill.getSkillId();
            o2.tTerm = 120;
            tsm.putCharacterStatValue(IndiePAD, o2);
            tsm.sendSetStatPacket();
        }
    }

    public void extendBulletBarrageDuration() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(BulletParty) || !chr.hasSkill(BULLET_BARRAGE)) {
            return;
        }
        Skill skill = chr.getSkill(BULLET_BARRAGE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Option o1 = new Option();

        int maxDurationIncCount = si.getValue(y, slv);
        if (curDurationIncCount < maxDurationIncCount) {
            int remainingDur = (int) ((bulletBarrageTime - Util.getCurrentTimeLong()) / 1000);

            o1.nOption = 1;
            o1.rOption = skill.getSkillId();
            o1.tOption = remainingDur + 1;
            tsm.putCharacterStatValue(BulletParty, o1);
            tsm.sendSetStatPacket();
            curDurationIncCount++;
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

        // Quickdraw
        activateQuickdraw();

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case PARROTARGETTING:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {

                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);

                    net.swordie.ms.client.character.skills.GuidedBullet gb = (GuidedBullet) tsm.getTSBByTSIndex(TSIndex.GuidedBullet);

                    if (tsm.hasStat(GuidedBullet)) {
                        Mob prevMob = (Mob) chr.getField().getLifeByObjectID(gb.getMobID());
                        if (prevMob != null) {
                            MobTemporaryStat oldMobMTS = prevMob.getTemporaryStat();
                            if (oldMobMTS.hasCurrentMobStatBySkillId(skillID)) {
                                oldMobMTS.removeMobStat(MobStat.AddDamParty, true);
                            }
                        }
                    }

                    gb.setNOption(1);
                    gb.setROption(1);
                    gb.setMobID(mai.mobId);
                    gb.setUserID(chr.getId());
                    tsm.putCharacterStatValue(GuidedBullet, gb.getOption());
                    tsm.sendSetStatPacket();
                }
                break;
            case NAUTILUS_STRIKE_SAIR:
                chr.reduceSkillCoolTime(SCURVY_SUMMONS, (long) (chr.getRemainingCoolTime(SCURVY_SUMMONS) * 0.5F));
                chr.reduceSkillCoolTime(BROADSIDE, (long) (chr.getRemainingCoolTime(BROADSIDE) * 0.5F));
                chr.reduceSkillCoolTime(ROLL_OF_THE_DICE_SAIR, (long) (chr.getRemainingCoolTime(ROLL_OF_THE_DICE_SAIR) * 0.5F));
                break;
            case TARGET_LOCK:
                chr.addSkillCoolTime(attackInfo.skillId, si.getValue(cooltime, slv) * 1000);
                EventManager.addEvent(() -> chr.write(UserLocal.skillCooltimeSetM(TARGET_LOCK, 0)), si.getValue(cooltime, slv), TimeUnit.SECONDS);
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }


    private void activateQuickdraw() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(QUICKDRAW)) {
            return;
        }
        Option o = new Option();
        SkillInfo quickdrawInfo = SkillData.getSkillInfoById(QUICKDRAW);
        int slv = 10;
        if (tsm.getOption(QuickDraw).nOption == 2) {
            tsm.removeStatsBySkill(QUICKDRAW);
        } else if (Util.succeedProp(quickdrawInfo.getValue(prop, slv))) {
            o.nOption = 1;
            o.rOption = QUICKDRAW;
            tsm.putCharacterStatValue(QuickDraw, o);
            tsm.sendSetStatPacket();
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(MAJESTIC_PRESENCE)) {
            return Cannonneer.BARREL_ROULETTE_FA;
        }
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
        Option o5 = new Option();
        switch (skillID) {
            case INFINITY_BLAST:
                break;
            case QUICKDRAW:
                o1.nOption = 2;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(QuickDraw, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(damR, slv);
                o2.tTerm = 10;
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;
            case JOLLY_ROGER:
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieStance, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieAsrR, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAsrR, o4);
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieTerR, slv);
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieTerR, o5);
                break;
            case WHALERS_POTION:
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
            case OCTO_CANNON: //Stationary, Attacks
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                if (skillID == Jett.TURRET_DEPLOYMENT) {
                    field.spawnAddSummon(summon);
                } else {
                    field.spawnSummon(summon);
                }
                break;
            case ALL_ABOARD: //Moves, Attacks
                tsm.removeStatsBySkill(AHOY_MATEYS);
                corsairSummons();
                // Fallthrough intended
            case SCURVY_SUMMONS: //Moves, Attacks
                corsairSummons();
                break;
            case BROADSIDE: // TODO  doesn't attack
                List<Integer> summonList = Arrays.asList(
                        5221022, // Black Bark
                        5220023, // Shulynch
                        5220024, // Dondlass
                        5220025  // Lord Jonathan
                );

                summon = Summon.getSummonByNoCTS(chr, Util.getRandomFromCollection(summonList), slv);
                summon.setMoveAction((byte) 5);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.CreateB2BodyRequests);
                field.spawnAddSummon(summon);
                break;
            case BULLET_BARRAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BulletParty, o1);
                bulletBarrageTime = Util.getCurrentTimeLong() + (si.getValue(time, slv) * 1000);
                curDurationIncCount = 0;
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
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.OUTLAW.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.CORSAIR.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {
        super.cancelTimers();

    }
}
