package net.swordie.ms.client.jobs.adventurer.pirate;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Buccaneer extends Pirate {
    public static final int TORNADO_UPPERCUT = 5101012; //Special Attack
    public static final int KNUCKLE_BOOSTER = 5101006; //Buff
    public static final int ENERGY_CHARGE = 5100015; //Energy Gauge


    public static final int ROLL_OF_THE_DICE_BUCC = 5111007; //Buff
    public static final int ENERGY_BURST = 5111002; //Special Attack
    public static final int ENERGY_BURST_CHARGED = 5111013;
    public static final int STATIC_THUMPER = 5111012; //Special Attack
    public static final int STUN_MASTERY = 5110000;
    public static final int SUPERCHARGE = 5110014;


    public static final int OCTOPUNCH = 5121007; //Special Attack
    public static final int NAUTILUS_STRIKE_BUCC = 5121013; //Special Attack
    public static final int NAUTILUS_STRIKE_BUCC_FA = 5120021; // Final Attack
    public static final int DRAGON_STRIKE = 5121001; //Special Attack
    public static final int BUCCANEER_BLAST = 5121016; //Special Attack
    public static final int MAPLE_WARRIOR_BUCC = 5121000; //Buff
    public static final int PIRATE_REVENGE_BUCC = 5120011;
    public static final int ULTRA_CHARGE = 5120018;
    public static final int ROLL_OF_THE_DICE_BUCC_ADDITION = 5120044;
    public static final int ROLL_OF_THE_DICE_BUCC_SAVING_GRACE = 5120043;
    public static final int ROLL_OF_THE_DICE_BUCC_ENHANCE = 5120045;
    public static final int HEROS_WILL_BUCC = 5121008;
    public static final int EPIC_ADVENTURER_BUCC = 5121053;
    public static final int POWER_UNITY = 5121052;
    public static final int STIMULATING_CONVERSATION = 5121054;
    public static final int ROLL_OF_THE_DICE_BUCC_DD = 5120012;
    public static final int TIME_LEAP = 5121010; //Special Move / Buff
    public static final int SPEED_INFUSION = 5121009; //Buff
    public static final int CROSSBONES = 5121015; //Buff


    // V skills
    public static final int MELTDOWN = 400051002;
    public static final int MELTDOWN_ENERGY_ORB = 400051003;
    public static final int LORD_OF_THE_DEEP = 400051015;
    public static final int SERPENT_VORTEX = 400051042;


    private ScheduledFuture stimulatingConversationTimer;
    private ScheduledFuture lordOfTheDeepTimer;
    private ScheduledFuture serpentTimer;

    public Buccaneer(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            incrementSerpentVortex();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBuccaneer(id);
    }

    private void lordOfTheDeepPassiveEnergyConsumption() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStatBySkillId(LORD_OF_THE_DEEP) || !chr.hasSkill(LORD_OF_THE_DEEP)) {
            return;
        }

        Skill skill = chr.getSkill(LORD_OF_THE_DEEP);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int passiveEnergyConsumption = si.getValue(y, slv);
        int energy = tsm.getOption(EnergyCharged).nOption;
        if (energy < passiveEnergyConsumption) {
            tsm.removeStatsBySkill(LORD_OF_THE_DEEP);
            lordOfTheDeepTimer.cancel(true);
        } else {
            int newEnergy = energy - passiveEnergyConsumption;
            updateViperEnergy(newEnergy);
            lordOfTheDeepTimer = EventManager.addEvent(this::lordOfTheDeepPassiveEnergyConsumption, 1, TimeUnit.SECONDS);
        }
    }

    private void incrementSerpentVortex() {
        serpentTimer = EventManager.addFixedRateEvent(this::increaseSerpentVortex, 10, 10, TimeUnit.SECONDS);
    }

    private void incrementStimulatingConversation() {
        if (!chr.hasSkill(STIMULATING_CONVERSATION)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(Stimulate)) {
            Skill skill = chr.getSkill(STIMULATING_CONVERSATION);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            updateViperEnergy(tsm.getOption(EnergyCharged).nOption + si.getValue(x, slv));
            stimulatingConversationTimer = EventManager.addEvent(this::incrementStimulatingConversation, 4, TimeUnit.SECONDS);
        }
    }

    private void increaseSerpentVortex() { // Serpent Vortex  Counter uses same CTS as MassDestructionRockets.
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int count = 1;
        if (tsm.hasStat(MassDestructionRockets)) {
            count = tsm.getOption(MassDestructionRockets).nOption;
            if (count < 6) {
                count++;
            }
        }
        updateSerpentVortex(count);
    }

    private void updateSerpentVortex(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = count;
        o.rOption = 0;
        tsm.putCharacterStatValue(MassDestructionRockets, o);
        tsm.sendSetStatPacket();
    }


    private void lordOfTheDeepActiveEnergyConsumption(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStatBySkillId(LORD_OF_THE_DEEP) || !chr.hasSkill(LORD_OF_THE_DEEP)) {
            return;
        }

        Skill skill = chr.getSkill(LORD_OF_THE_DEEP);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        boolean isBossMob = false;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob != null && mob.isBoss()) {
                isBossMob = true;
                break;
            }
        }
        int slv = skill.getCurrentLevel();
        int activeEnergyConsumption = si.getValue(x, slv);
        if (isBossMob) {
            activeEnergyConsumption = (int) (activeEnergyConsumption * (100 - si.getValue(z, slv)) / 100D);
        }
        int energy = tsm.getOption(EnergyCharged).nOption;
        if (energy < activeEnergyConsumption) {
            tsm.removeStatsBySkill(LORD_OF_THE_DEEP);
        } else {
            int newEnergy = energy - activeEnergyConsumption;
            updateViperEnergy(newEnergy);
        }
    }

    private void powerUnity() {
        if (!chr.hasSkill(POWER_UNITY)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Skill skill = chr.getSkill(POWER_UNITY);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int amount = 1;
        if (tsm.hasStat(UnityOfPower)) {
            amount = tsm.getOption(UnityOfPower).nOption;
            if (amount < 4) {
                amount++;
            }
        }
        o1.nOption = amount;
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(UnityOfPower, o1);
        tsm.sendSetStatPacket();
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

    private void applyStunMasteryOnMob(AttackInfo attackInfo) {
        if (!chr.hasSkill(STUN_MASTERY)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(STUN_MASTERY);
        int slv = si.getCurrentLevel();
        Option o1 = new Option();
        o1.nOption = 1;
        o1.rOption = STUN_MASTERY;
        o1.tOption = 3;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(subProp, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null || mob.isBoss()) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
            }
        }
    }

    private void changeViperEnergy(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int energy = getEnergyIncrement();
        if (tsm.getViperEnergyCharge() == 0) {
            if (tsm.hasStat(EnergyCharged)) {
                energy = tsm.getOption(EnergyCharged).nOption;
                if (energy < getMaximumEnergy()) {
                    energy += getEnergyIncrement();
                }
                if (energy > getMaximumEnergy()) {
                    energy = getMaximumEnergy();
                }
            }
            chr.write(UserPacket.effect(Effect.skillAffected(getViperEnergySkill().getSkillId(), (byte) 1, 0)));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(getViperEnergySkill().getSkillId(), (byte) 1, 0)));
        } else {
            energy = deductViperEnergyCost(skillId);
        }
      updateViperEnergy(energy); // disabling fixes e38 for now
    }

    private int deductViperEnergyCost(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int energy = tsm.getOption(EnergyCharged).nOption;
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        if (si == null) {
            return energy;
        }
        energy = energy - si.getValue(forceCon, 1);

        return energy;
    }

    private void updateViperEnergy(int energy) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.EnergyCharged);
        tsb.setNOption(energy < 0 ? 0 : (energy > getMaximumEnergy() ? getMaximumEnergy() : energy));
        tsb.setROption(0);
        tsm.putCharacterStatValue(EnergyCharged, tsb.getOption());
        if (energy >= getMaximumEnergy()) {
            tsm.setViperEnergyCharge(getViperEnergySkill().getSkillId());
        } else if (energy <= 0) {
            tsm.setViperEnergyCharge(0);
        }
        tsm.sendSetStatPacket();
    }

    private int getEnergyIncrement() {
        Skill skill = getViperEnergySkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        return si.getValue(x, slv);
    }

    private int getMaximumEnergy() {
        Skill skill = getViperEnergySkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        return si.getValue(z, slv);
    }

    private Skill getViperEnergySkill() {
        Skill skill = null;
        if (chr.hasSkill(ENERGY_CHARGE)) {
            skill = chr.getSkill(ENERGY_CHARGE);
        }
        if (chr.hasSkill(SUPERCHARGE)) {
            skill = chr.getSkill(SUPERCHARGE);
        }
        if (chr.hasSkill(ULTRA_CHARGE)) {
            skill = chr.getSkill(ULTRA_CHARGE);
        }
        return skill;
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

        // Stun Mastery
        applyStunMasteryOnMob(attackInfo);
        // Viper Energy
        if (!JobConstants.isPhantom(chr.getJob())) {
            if (attackInfo.skillId != LORD_OF_THE_DEEP) {
                changeViperEnergy(attackInfo.skillId);
            } else {
                // Lord of the Deep
                lordOfTheDeepActiveEnergyConsumption(attackInfo);
            }
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case ENERGY_BURST:
            case ENERGY_BURST_CHARGED:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case POWER_UNITY:
                powerUnity();
                break;
            case DRAGON_STRIKE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
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
            case SERPENT_VORTEX:
                updateSerpentVortex(tsm.hasStat(MassDestructionRockets) ? (tsm.getOption(MassDestructionRockets).nOption - 1) : 0);
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(NAUTILUS_STRIKE_BUCC) && chr.hasSkillOnCooldown(NAUTILUS_STRIKE_BUCC)) {
            return NAUTILUS_STRIKE_BUCC_FA;
        }
        return 0;
    }


    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case MELTDOWN_ENERGY_ORB:
                Option prevOpt = tsm.getOption(MeltDown);
                Option o = new Option();
                o.tOption = (int) tsm.getRemainingTime(MeltDown, MELTDOWN);
                o.nOption = prevOpt.nOption - 1;
                if (o.nOption == 0) {
                    o.nOption = -1;
                }
                o.setInMillis(true);

                tsm.putCharacterStatValue(MeltDown, o, true);
                tsm.sendSetStatPacket();
                break;
        }
        super.handleShootObj(chr, skillId, slv);
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case TIME_LEAP:
                long nextAvailableTime = System.currentTimeMillis() + (si.getValue(time, slv) * 1000);
                chr.getScriptManager().createQuestWithQRValue(chr, GameConstants.TIME_LEAP_QR_KEY, String.valueOf(nextAvailableTime), false);
                if (chr.getQuestManager().getQuestById(GameConstants.TIME_LEAP_QR_KEY).getQRValue() == null
                        || Long.parseLong(chr.getQuestManager().getQuestById(GameConstants.TIME_LEAP_QR_KEY).getQRValue()) < System.currentTimeMillis()) {
                    for (int skillId : chr.getSkillCoolTimes().keySet()) {
                        if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset() && SkillData.getSkillInfoById(skillId).getHyper() == 0) {
                            chr.resetSkillCoolTime(skillId);
                        }
                    }
                }
                break;
            case SPEED_INFUSION:
                o1.nReason = skillID;
                o1.nValue = -1;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case CROSSBONES:
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePadR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o2);
                break;
            case STIMULATING_CONVERSATION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stimulate, o1);
                o2.nValue = si.getValue(indieDamR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                if (stimulatingConversationTimer != null && !stimulatingConversationTimer.isDone()) {
                    stimulatingConversationTimer.cancel(true);
                }
                stimulatingConversationTimer = EventManager.addEvent(this::incrementStimulatingConversation, 4, TimeUnit.SECONDS);
                break;
            case LORD_OF_THE_DEEP:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DevilishPower, o1);
                lordOfTheDeepTimer = EventManager.addEvent(this::lordOfTheDeepPassiveEnergyConsumption, 1, TimeUnit.SECONDS);
                break;
            case MELTDOWN:
                updateViperEnergy(getMaximumEnergy());
                o1.nOption = si.getValue(w, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MeltDown, o1);
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                break;
            case ROLL_OF_THE_DICE_BUCC_DD:
                boolean isCharged = tsm.getViperEnergyCharge() > 0;
                int upbound = 6;
                if ((chr.hasSkill(ROLL_OF_THE_DICE_BUCC_DD) && chr.hasSkill(ROLL_OF_THE_DICE_BUCC_ADDITION)) ||
                        (chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_DD) && chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ADDITION))) {
                    upbound = 7;
                }
                int diceThrow1 = new Random().nextInt(upbound) + 1;
                int diceThrow2 = new Random().nextInt(upbound) + 1;

                if ((chr.hasSkill(ROLL_OF_THE_DICE_BUCC_ENHANCE) || chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ENHANCE)) && Util.succeedProp(40)) {
                    diceThrow1 = new Random().nextInt(4) + 4;
                    diceThrow2 = new Random().nextInt(4) + 4;
                }

                int diceThrow3 = 1;
                if (chr.hasSkill(Job.LOADED_DICE)) {
                    if (chr.getQuestManager().getQuestById(GameConstants.LOADED_DICE_SELECTION) == null) {
                        chr.getScriptManager().createQuestWithQRValue(GameConstants.LOADED_DICE_SELECTION, "1");
                    }
                    diceThrow3 = Integer.parseInt(chr.getScriptManager().getQRValue(GameConstants.LOADED_DICE_SELECTION));

                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), -1, 1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow3, -1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow1, 0, true)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow2, -1, true)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), -1, 2, false)));

                    o1.nOption = (diceThrow1 * 100) + (diceThrow2 * 10) + diceThrow3; // if rolled: 3, 5, and 6 the LoadedDice nOption = 356
                    tsm.throwDice(diceThrow1, diceThrow2, diceThrow3);
                } else {
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow2, true)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow2, true)));

                    o1.nOption = (diceThrow1 * 10) + diceThrow2; // if rolled: 3 and 5, the DoubleDown nOption = 35
                    tsm.throwDice(diceThrow1, diceThrow2);
                }
                if (diceThrow1 == 1 || diceThrow2 == 1 || diceThrow3 == 1) {
                    chr.reduceSkillCoolTime(skillID, (1000 * si.getValue(cooltime, slv)) / 2);
                }
                // Saving Grace Hyper Passive Skill
                if ((chr.hasSkill(ROLL_OF_THE_DICE_BUCC_SAVING_GRACE) || (chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_SAVING_GRACE))) && diceThrow1 != diceThrow2) {
                    if (Util.succeedProp(40)) {
                        chr.resetSkillCoolTime(skillID);
                    }
                }
                if (diceThrow1 == 1 && diceThrow2 == 1 && diceThrow3 == 1) {
                    return;
                }
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Dice, o1);
                if (isCharged) {
                    updateViperEnergy(tsm.getOption(EnergyCharged).nOption);
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
                case LORD_OF_THE_DEEP:
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
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.MARAUDER.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.BUCCANEER.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {
        if (stimulatingConversationTimer != null) {
            stimulatingConversationTimer.cancel(false);
        }
        if (lordOfTheDeepTimer != null) {
            lordOfTheDeepTimer.cancel(false);
        }
        if (serpentTimer != null) {
            serpentTimer.cancel(false);
        }
        super.cancelTimers();

    }
}