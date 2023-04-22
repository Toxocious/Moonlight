package net.swordie.ms.client.jobs.resistance;

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
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;

import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Blaster extends Citizen {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int DETONATE_H = 37001004;
    public static final int DETONATE_V = 37000005;

    public static final int ARM_CANNON_BOOST = 37101003;

    public static final int HAMMER_SMASH = 37111000;
    public static final int HAMMER_SMASH_CHARGE = 37110001;
    public static final int BOBBING = 37101001;
    public static final int BOBBING_ATTACK = 37100002;

    public static final int MAPLE_WARRIOR_BLASTER = 37121006;
    public static final int HEROS_WILL_BLASTER = 37121007;
    public static final int WEAVING = 37111003;
    public static final int WEAVING_ATTACK = 37110004;

    public static final int FOR_LIBERTY_BLASTER = 37121053;
    public static final int CANNON_OVERDRIVE = 37121054;
    public static final int HYPER_MAGNUM_PUNCH = 37121052;

    public static final int ROCKET_PUNCH = 400011017;
    public static final int ROCKET_PUNCH_EXTRA_SKILL = 400011019;
    public static final int GATLING_PUNCH = 400011028;
    public static final int BULLET_BLAST = 400011091;


    public static final int REVOLVING_BLAST = 37121004;

    //Revolving Cannon
    public static final int REVOLVING_CANNON_RELOAD = 37000010;
    public static final int REVOLVING_CANNON = 37001001;
    public static final int REVOLVING_CANNON_2 = 37100008;
    public static final int REVOLVING_CANNON_3 = 37000009;

    public static final int REVOLVING_CANNON_MASTERY = 37000007;
    public static final int REVOLVING_CANNON_PLUS = 37100007;
    public static final int REVOLVING_CANNON_PLUS_II = 37110007;
    public static final int REVOLVING_CANNON_PLUS_III = 37120008;

    public static final int BUNKER_BUSTER_EXPLOSION = 37000008;
    public static final int BUNKER_BUSTER_EXPLOSION_3 = 37001002;
    public static final int BUNKER_BUSTER_EXPLOSION_4 = 37000011;
    public static final int BUNKER_BUSTER_EXPLOSION_5 = 37000012;
    public static final int BUNKER_BUSTER_EXPLOSION_6 = 37000013;


    //Blast Shield
    public static final int BLAST_SHIELD = 37000006;
    public static final int SHIELD_TRAINING = 37110008;
    public static final int SHIELD_TRAINING_II = 37120009;
    public static final int VITALITY_SHIELD = 37121005;


    //Combo Training
    public static final int COMBO_TRAINING = 37110009;
    public static final int COMBO_TRAINING_II = 37120012;


    private static final int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
    };

    private static final int[] ammoUsingSkills = new int[]{
            DETONATE_H,
            DETONATE_V,
            REVOLVING_CANNON_MASTERY,
    };

    public boolean isAmmoUsingSkill(int skillID) {
        return Arrays.stream(ammoUsingSkills).anyMatch(b -> b == skillID);
    }

    private ScheduledFuture blastShieldTimer;
    private int lastAttack = 0;

    public Blaster(Char chr) {
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
        return JobConstants.isBlaster(id);
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
        incrementComboTraining(skillID, tsm);
        if (isAmmoUsingSkill(attackInfo.skillId)) {
            useCylinderSkill(attackInfo.skillId, hasHitMobs);
        }
        if (tsm.getOptByCTSAndSkill(ExtraSkillCTS, ROCKET_PUNCH) != null && attackInfo.skillId != ROCKET_PUNCH_EXTRA_SKILL) {
            registerRocketPunchExtraHit();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
           /* case HAMMER_SMASH_CHARGE:
                System.out.println("Spawn AA");
                SkillInfo hmc = SkillData.getSkillInfoById(HAMMER_SMASH);
                AffectedArea hmci = AffectedArea.getPassiveAA(chr, HAMMER_SMASH, (byte) slv);
                hmci.setPosition(chr.getPosition());
                hmci.setRect(hmci.getPosition().getRectAround(hmc.getRects().get(0)));
                hmci.setDelay((short) 5);
                chr.getField().spawnAffectedArea(hmci);
                break;*/
            case BUNKER_BUSTER_EXPLOSION_3:
            case BUNKER_BUSTER_EXPLOSION_4:
            case BUNKER_BUSTER_EXPLOSION_5:
            case BUNKER_BUSTER_EXPLOSION_6:
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = (tsm.hasStat(RWMaximizeCannon) ? 1 : 7);
                tsm.putCharacterStatValue(RWOverHeat, o1);
                tsm.sendSetStatPacket();
                break;
            case HYPER_MAGNUM_PUNCH:
                o1.nOption = 5;
                o1.rOption = skillID;
                o1.tOption = 10;
                tsm.putCharacterStatValue(RWMagnumBlow, o1);
                tsm.sendSetStatPacket();
                break;
            case BOBBING_ATTACK:
            case WEAVING_ATTACK:
                int realSkillId = attackInfo.skillId == BOBBING_ATTACK ? BOBBING : WEAVING;
                si = SkillData.getSkillInfoById(realSkillId);
                slv = (byte) chr.getSkillLevel(realSkillId);
                o1.nOption = si.getValue(w, slv);
                o1.rOption = realSkillId;
                o1.tOption = 1500;
                o1.setInMillis(true);
                tsm.putCharacterStatValue(RWMovingEvar, o1);
                tsm.sendSetStatPacket();
                break;
                case BUNKER_BUSTER_EXPLOSION:
                changeCylinder(getAmmo(), 0, getSkillUsedAmmo());
                break;
            case HAMMER_SMASH:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 10;
                    o1.rOption = HAMMER_SMASH_CHARGE;
                    o1.tOption = 10;
                    mts.addStatOptionsAndBroadcast(MobStat.RWLiftPress, o1);
                }
                break;
            case REVOLVING_BLAST:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 25;
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
        }
        super.handleAttack(c, attackInfo);
    }

    private void registerRocketPunchExtraHit() {
        chr.write(UserLocal.userBonusAttackRequest(ROCKET_PUNCH_EXTRA_SKILL));
    }

    private void useCylinderSkill(int skillId, boolean hit) {
        switch (skillId) {
            case DETONATE_H:
            case DETONATE_V:
                changeCylinder(getAmmo() - 1, getGauge(), getSkillUsedAmmo());
                break;
            case REVOLVING_CANNON:
            case REVOLVING_CANNON_2:
            case REVOLVING_CANNON_3:
                //case REVOLVING_CANNON_MASTERY:
                changeCylinder(getAmmo() - 1, getGauge() + (hit ? 1 : 0), getSkillUsedAmmo());
                break;
        }
        if (getAmmo() <= 0) {
            reloadCylinder();
        }
    }

    private void changeCylinder(int ammo, int gauge, int skillUsedAmmo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if ((ammo >= 0 && ammo <= getMaxAmmo()) || (gauge >= 0 && gauge <= getMaxGuage())) {
            o.nOption = 1;
            o.bOption = ammo;
            if (tsm.hasStat(RWOverHeat)) { //not sure how to do this correctly lel
                o.cOption = 0;
            } else {
                o.cOption = gauge <= getMaxGuage() ? gauge : getMaxGuage();
            }
            o.xOption = skillUsedAmmo;
            tsm.putCharacterStatValue(RWCylinder, o);
            tsm.sendSetStatPacket();
        }
    }

    private int getAmmo() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(RWCylinder)) {
            return tsm.getOption(RWCylinder).bOption;
        }
        return 0;
    }

    private int getGauge() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(RWCylinder)) {
            return tsm.getOption(RWCylinder).cOption;
        }
        return 0;
    }

    private int getSkillUsedAmmo() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(RWCylinder)) {
            return tsm.getOption(RWCylinder).xOption;
        }
        return 0;
    }
    private int getMaxAmmo() {
        int maxAmmo = 3;
        if (chr.hasSkill(REVOLVING_CANNON_PLUS)) {
            maxAmmo = 4;
        }
        if (chr.hasSkill(REVOLVING_CANNON_PLUS_II)) {
            maxAmmo = 5;
        }
        if (chr.hasSkill(REVOLVING_CANNON_PLUS_III)) {
            maxAmmo = 6;
        }
        return maxAmmo;
    }


    private int getMaxGuage() {
        int maxAmmo = 3;
        if (chr.hasSkill(REVOLVING_CANNON_PLUS)) {
            maxAmmo = 4;
        }
        if (chr.hasSkill(REVOLVING_CANNON_PLUS_II)) {
            maxAmmo = 5;
        }
        if (chr.hasSkill(REVOLVING_CANNON_PLUS_III)) {
            maxAmmo = 6;
        }
        return maxAmmo;
    }

    public void reloadCylinder() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = REVOLVING_CANNON_RELOAD;
        o.bOption = getMaxAmmo(); //ammo
        o.cOption = getGauge(); //gauge
        tsm.putCharacterStatValue(RWCylinder, o);
        tsm.sendSetStatPacket();
    }

    public void reInitAmmo() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = REVOLVING_CANNON_RELOAD;
        o.bOption = getAmmo(); //ammo
        o.cOption = getGauge(); //gauge
        tsm.putCharacterStatValue(RWCylinder, o);
        tsm.sendSetStatPacket();
    }

    private void incrementComboTraining(int skillId, TemporaryStatManager tsm) {
        if (SkillData.getSkillInfoById(skillId) == null || SkillData.getSkillInfoById(skillId).isInvisible()) {
            return;
        }
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Skill skill = getComboTrainingSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            byte slv = (byte) chr.getSkillLevel(skill.getSkillId());
            int amount = 1;
            if (tsm.hasStat(RWCombination)) {
                if (lastAttack == skillId) {
                    return;
                }
                amount = tsm.getOption(RWCombination).nOption;
                if (amount < si.getValue(x, slv)) {
                    amount++;
                }
            }
            lastAttack = skillId;
            o.nOption = amount;
            o.rOption = COMBO_TRAINING;
            o.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(RWCombination, o);
            if (skill.getSkillId() == COMBO_TRAINING_II) {
                o1.nValue = si.getValue(q, slv) * amount;
                o1.nReason = COMBO_TRAINING;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
            }
            if (amount >= si.getValue(w, slv)) {
                o2.nValue = -1;
                o2.nReason = COMBO_TRAINING;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2);
            }
            tsm.sendSetStatPacket();
        }
    }

    private Skill getComboTrainingSkill() {
        Skill skill = null;
        if (chr.hasSkill(COMBO_TRAINING)) {
            skill = chr.getSkill(COMBO_TRAINING);
        }
        if (chr.hasSkill(COMBO_TRAINING_II)) {
            skill = chr.getSkill(COMBO_TRAINING_II);
        }
        return skill;
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
        if (isAmmoUsingSkill(skillID)) {
            useCylinderSkill(skillID, false);
        }
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        switch (skillID) {
            case REVOLVING_CANNON_RELOAD:
                reloadCylinder();
                break;
            case REVOLVING_CANNON_3:
            case REVOLVING_CANNON_2:
            case REVOLVING_CANNON:
                break;
            case HEROS_WILL_BLASTER:
                tsm.removeAllDebuffs();
                break;
            case ARM_CANNON_BOOST:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case MAPLE_WARRIOR_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FOR_LIBERTY_BLASTER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case CANNON_OVERDRIVE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(RWMaximizeCannon, o1);
                break;
            case VITALITY_SHIELD:
                if (!tsm.hasStat(RWBarrier)) {
                    return;
                }
                int dissipatedShield = tsm.getOption(RWBarrier).xOption - tsm.getOption(RWBarrier).nOption;
                chr.heal(dissipatedShield + (int) (chr.getMaxHP() * si.getValue(x, slv) / 100F));

                tsm.removeStat(RWBarrier, false);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(RWBarrierHeal, o1);
                break;
            case ROCKET_PUNCH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ExtraSkillCTS, o1);
                break;
            case HAMMER_SMASH_CHARGE:
                SkillInfo hmc = SkillData.getSkillInfoById(HAMMER_SMASH_CHARGE);
                AffectedArea hmci = AffectedArea.getPassiveAA(chr, HAMMER_SMASH_CHARGE, (byte) slv);
                hmci.setPosition(chr.getPosition());
                hmci.setRect(hmci.getPosition().getRectAround(hmc.getRects().get(0)));
                hmci.setDelay((short) 5);
                chr.getField().spawnAffectedArea(hmci);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (hitInfo.hpDamage > 0 && chr.hasSkill(BLAST_SHIELD) && !tsm.hasStat(RWBarrier)) {
            generateBlastShield(hitInfo.hpDamage);
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private Skill getBlastShieldSkill() {
        Skill skill = null;
        if (chr.hasSkill(BLAST_SHIELD)) {
            skill = chr.getSkill(BLAST_SHIELD);
        }
        if (chr.hasSkill(SHIELD_TRAINING)) {
            skill = chr.getSkill(SHIELD_TRAINING);
        }
        if (chr.hasSkill(SHIELD_TRAINING_II)) {
            skill = chr.getSkill(SHIELD_TRAINING_II);
        }
        return skill;
    }

    private void generateBlastShield(int hpDmg) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Option o = new Option();
        Option o1 = new Option();
        Skill skill = getBlastShieldSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();

        o.nOption = (int) ((si.getValue(x, slv) * hpDmg) / 100F);
        o.rOption = BLAST_SHIELD;
        o.xOption = (int) ((si.getValue(x, slv) * hpDmg) / 100F);
        tsm.putCharacterStatValue(RWBarrier, o);
        o1.nReason = BLAST_SHIELD;
        o1.nValue = 100;
        tsm.putCharacterStatValue(IndieStance, o1);
        tsm.sendSetStatPacket();
        if (blastShieldTimer != null && !blastShieldTimer.isDone()) {
            blastShieldTimer.cancel(true);
        }
        blastShieldTimer = EventManager.addFixedRateEvent(this::diminishBlastShield, 0, 5, TimeUnit.SECONDS);
    }

    private void diminishBlastShield() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(RWBarrier)) {
            if (blastShieldTimer != null && !blastShieldTimer.isDone()) {
                blastShieldTimer.cancel(true);
            }
            return;
        }
        Option o = new Option();
        Option o1 = new Option();
        Skill skill = getBlastShieldSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        byte slv = (byte) skill.getCurrentLevel();
        int curShieldStrength = tsm.getOption(RWBarrier).nOption;
        int newShieldStrength = (int) (curShieldStrength * si.getValue(y, slv) / 100F) - si.getValue(z, slv);
        if (newShieldStrength <= 0) {
            tsm.removeStatsBySkill(BLAST_SHIELD);
            return;
        }
        o.nOption = newShieldStrength;
        o.rOption = BLAST_SHIELD;
        tsm.putCharacterStatValue(RWBarrier, o);
        o1.nReason = BLAST_SHIELD;
        o1.nValue = 100;
        tsm.putCharacterStatValue(IndieStance, o1);
        tsm.sendSetStatPacket();
    }

    public void handleKeyDownSkill(Char chr, int skillID, InPacket inPacket) {
        super.handleKeyDownSkill(chr, skillID, inPacket);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Skill skill = chr.getSkill(skillID);
        int skillId = skillID;
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
            case BULLET_BLAST:
                o1.nOption = 1;
                o1.rOption = BULLET_BLAST;
                o1.tOption = 5;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
        }
    }

    public void handleCancelKeyDownSkill(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {
            case BULLET_BLAST:
                tsm.removeStatsBySkill(skillID);
                break;
            default:
                super.handleCancelKeyDownSkill(chr, skillID);
        }
    }


    @Override
    public void cancelTimers() {
        if (blastShieldTimer != null) {
            blastShieldTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.BLASTER_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.BLASTER_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.BLASTER_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Hand Buster
        Item handBuster = ItemData.getItemDeepCopy(1582000);
        chr.addItemToInventory(handBuster);

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353400)); // Charges (Secondary)

        reloadCylinder();
    }
}