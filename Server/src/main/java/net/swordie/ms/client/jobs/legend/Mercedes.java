package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Mercedes extends Job {
    //Link Skill = return skill

    public static final int ELVEN_GRACE = 20020112;
    public static final int UPDRAFT = 20020111;
    public static final int ELVEN_HEALING = 20020109;

    public static final int DUAL_BOWGUN_BOOSTER = 23101002; //Buff

    public static final int STUNNING_STRIKES = 23111000; //Special Attack
    public static final int UNICORN_SPIKE = 23111002; //Special Attack
    public static final int IGNIS_ROAR = 23110004; //Buff
    public static final int WATER_SHIELD = 23111005; //Buff
    public static final int ELEMENTAL_KNIGHTS_BLUE = 23111008; //Summon
    public static final int ELEMENTAL_KNIGHTS_RED = 23111009; //Summon
    public static final int ELEMENTAL_KNIGHTS_PURPLE = 23111010; //Summon


    public static final int ROLLING_MOONSAULT = 23121011;

    public static final int SPIKES_ROYALE = 23121002;  //Special Attack
    public static final int STAGGERING_STRIKES = 23120013; //Special Attack
    public static final int ANCIENT_WARDING = 23121004; //Buff
    public static final int MAPLE_WARRIOR_MERC = 23121005; //Buff
    public static final int LIGHTNING_EDGE = 23121003; //Debuff mobs
    public static final int HEROS_WILL_MERC = 23121008;
    public static final int ISHTAR_RING = 23121000;

    public static final int HEROIC_MEMORIES_MERC = 23121053;
    public static final int ELVISH_BLESSING = 23121054;
    public static final int WRATH_OF_ENLIL = 23121052;

    //Final Attack
    public static final int FINAL_ATTACK_DBG = 23100006;
    public static final int ADVANCED_FINAL_ATTACK = 23120012;

    public static final int SPIRIT_OF_ELLUEL = 400031007;
    public static final int SYLVIDIAS_FLIGHT = 400031017;
    public static final int BREATH_OF_IRKALLA = 400031011;

    private static final int[] addedSkills = new int[]{
            ELVEN_GRACE,
            UPDRAFT,
            ELVEN_HEALING,
    };

    private static final int[] summonAttacks = new int[]{
            ELEMENTAL_KNIGHTS_BLUE,
            ELEMENTAL_KNIGHTS_RED,
            ELEMENTAL_KNIGHTS_PURPLE,
    };

    private int eleKnightSummonID = 1;
    private int lastAttackSkill = 0;
    private List<Summon> summonList = new ArrayList<>();
    private ScheduledFuture elvenHealingTimer;

    public Mercedes(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (elvenHealingTimer != null && !elvenHealingTimer.isDone()) {
                elvenHealingTimer.cancel(true);
            }
            healByElvenHealing();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isMercedes(id);
    }


    private void summonEleKnights() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        List<Integer> set = new ArrayList<>();
        set.add(ELEMENTAL_KNIGHTS_BLUE);
        set.add(ELEMENTAL_KNIGHTS_RED);
        set.add(ELEMENTAL_KNIGHTS_PURPLE);

        if (eleKnightSummonID != 0) {
            set.remove((Integer) eleKnightSummonID);
        }
        int random = Util.getRandomFromCollection(set);
        eleKnightSummonID = random;
        Summon summon = Summon.getSummonBy(chr, random, (byte) 1);
        Field field = chr.getField();
        summon.setMoveAbility(MoveAbility.Fly);
        summon.setSummonTerm(0);

        summonList.add(summon);
        if (summonList.size() > 2) {
            field.removeLife(summonList.get(0));
            tsm.removeStatsBySkill(summonList.get(0).getSkillID());
            summonList.remove(0);
        }

        field.spawnSummon(summon);

        SkillInfo si = SkillData.getSkillInfoById(ELEMENTAL_KNIGHTS_BLUE);
        int slv = chr.getSkill(ELEMENTAL_KNIGHTS_BLUE).getCurrentLevel();
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
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        incrementIgnisRoarStackCount(tsm, attackInfo);
        if (!chr.hasSkillOnCooldown(BREATH_OF_IRKALLA) && tsm.getOptByCTSAndSkill(IndieSummon, SPIRIT_OF_ELLUEL) != null && Util.succeedProp(50)) {
            chr.write(UserLocal.userBonusAttackRequest(BREATH_OF_IRKALLA));
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case STUNNING_STRIKES:
                SkillInfo stunningStrikes = SkillData.getSkillInfoById(STUNNING_STRIKES);
                int proc = stunningStrikes.getValue(prop, slv);
                o1.nOption = 1;
                o1.rOption = STUNNING_STRIKES;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(proc)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if (!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case STAGGERING_STRIKES:
                SkillInfo staggeringStrikes = SkillData.getSkillInfoById(STAGGERING_STRIKES);
                int procc = staggeringStrikes.getValue(prop, slv);
                o1.nOption = 1;
                o1.rOption = STAGGERING_STRIKES;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(procc)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        if (!mob.isBoss()) {
                            MobTemporaryStat mts = mob.getTemporaryStat();
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case UNICORN_SPIKE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1); // ?
                    }
                }
                break;
            case SPIKES_ROYALE:
                o1.nOption = -si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                }
                break;
            case LIGHTNING_EDGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamSkill, o1);
                }
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(subTime, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    }
                }
                break;
            case ELEMENTAL_KNIGHTS_RED:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case BREATH_OF_IRKALLA:
                chr.addSkillCoolTime(BREATH_OF_IRKALLA, Util.getCurrentTimeLong() + 10000);
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void incrementIgnisRoarStackCount(TemporaryStatManager tsm, AttackInfo attackInfo) {
        if (Util.makeSet(summonAttacks).contains(attackInfo.skillId)
                || ((getFinalAtkSkill() != null) && attackInfo.skillId == getFinalAtkSkill().getSkillId())
                || attackInfo.skillId == lastAttackSkill) {
            return;
        }
        Option o = new Option();
        Skill skill = chr.getSkill(IGNIS_ROAR);
        if (skill == null) {
            return;
        }
        SkillInfo ignisRoarInfo = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(lastAttackSkill);
        lastAttackSkill = attackInfo.skillId;
        if (si == null || si.getAddAttackSkills().stream().noneMatch(aas -> aas == attackInfo.skillId)) {
            return;
        }

        if (attackInfo.skillId != SPIKES_ROYALE) {
            chr.reduceSkillCoolTime(SPIKES_ROYALE, 1000);
        }
        if (attackInfo.skillId != UNICORN_SPIKE) {
            chr.reduceSkillCoolTime(UNICORN_SPIKE, 1000);
        }
        if (attackInfo.skillId != WRATH_OF_ENLIL) {
            chr.reduceSkillCoolTime(WRATH_OF_ENLIL, 1000);
        }
        chr.reduceSkillCoolTime(BREATH_OF_IRKALLA, 1000);

        int amount = 1;
        if (tsm.hasStat(AddAttackCount)) {
            amount = tsm.getOption(AddAttackCount).nOption;
            if (amount < ignisRoarInfo.getValue(y, slv)) {
                amount++;
            }
        }
        if (tsm.hasStatBySkillId(SYLVIDIAS_FLIGHT)) {
            amount = ignisRoarInfo.getValue(y, slv);
        }
        o.nOption = amount;
        o.rOption = IGNIS_ROAR;
        o.tOption = ignisRoarInfo.getValue(subTime, slv);
        tsm.putCharacterStatValue(AddAttackCount, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public int getFinalAttackSkill() {
        Skill faSkill = getFinalAtkSkill();
        if (faSkill != null) {
            SkillInfo si = SkillData.getSkillInfoById(faSkill.getSkillId());
            int slv = faSkill.getCurrentLevel();
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                return faSkill.getSkillId();
            }
        }
        return 0;
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_DBG)) {
            skill = chr.getSkill(FINAL_ATTACK_DBG);
        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK);
        }
        return skill;
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
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case HEROS_WILL_MERC:
                tsm.removeAllDebuffs();
                break;
            case DUAL_BOWGUN_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case IGNIS_ROAR:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnisRore, o1);
                o2.nValue = si.getValue(indiePad, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case WATER_SHIELD:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamAbsorbShield, o3);   //IgnoreMobDamR
                break;
            case ANCIENT_WARDING:
                o1.nOption = si.getValue(emhp, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMHP, o1);
                o2.nValue = si.getValue(indiePadR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;
            case MAPLE_WARRIOR_MERC:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_MERC:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case ELVISH_BLESSING:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o2);
                break;
            case ELEMENTAL_KNIGHTS_BLUE:
                summonEleKnights();
                break;

            case SPIRIT_OF_ELLUEL:
                for (int i = skillID; i < skillID + 3; i++) {
                    summon = Summon.getSummonBy(chr, i, slv);
                    summon.setFlyMob(false);
                    summon.setAvatarLook(chr.getAvatarData().getAvatarLook());
                    summon.setMoveAbility(MoveAbility.WalkClone);
                    summon.setActionDelay((i - (skillID - 1)) * 400);
                    summon.setMovementDelay((i - (skillID - 1)) * 30);
                    field.spawnSummon(summon);
                }
                break;
            case SYLVIDIAS_FLIGHT:
                TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
                if (tsm.hasStat(RideVehicle)) {
                    tsm.removeStat(RideVehicle, true);
                }
                tsb.setNOption(si.getVehicleId());
                tsb.setROption(skillID);
                tsb.setExpireTerm(si.getValue(time, slv));
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieStance, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieDamReduceR, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamReduceR, o3);
                break;
        }
        tsm.sendSetStatPacket();
    }

    private void healByElvenHealing() {
        if (chr != null && chr.getField() != null && chr.hasSkill(ELVEN_HEALING)) {
            Skill skill = chr.getSkill(ELVEN_HEALING);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int healHP = (int) ((double) (chr.getMaxHP() * si.getValue(x, slv)) / 100F);
            int healMP = (int) ((double) (chr.getMaxMP() * si.getValue(x, slv)) / 100F);

            chr.heal(healHP, true);
            chr.healMP(healMP);
        }
        elvenHealingTimer = EventManager.addEvent(this::healByElvenHealing, 4, TimeUnit.SECONDS);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (elvenHealingTimer != null) {
            elvenHealingTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getAvatarLook().setDrawElfEar(true);
//        chr.getAvatarData().getCharacterStat().setPosMap(910150000);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.MERCEDES_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.MERCEDES_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.MERCEDES_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.MERCEDES_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

//        chr.addSkill(20021181, 1, 1);
        chr.removeSkill(20021166); // Beginner Stunning Strike
        chr.removeSkill(20021181); // Beginner Flash Jump

        // Medal - A Hero, No More
        Item medal = ItemData.getItemDeepCopy(1142336);
        chr.addItemToInventory(medal);

        // Secondary - Magic Arrows
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352000)); // Magic Arrows
    }
}
