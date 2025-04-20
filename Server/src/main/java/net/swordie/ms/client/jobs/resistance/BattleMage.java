package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class BattleMage extends Citizen {

    public static final int SECRET_ASSEMBLY = 30001281;

    public static final int CONDEMNATION = 32001014; //Special Buff (ON/OFF)
    public static final int HASTY_AURA = 32001016; //Buff (Unlimited Duration)

    public static final int CONDEMNATION_I = 32100010; //Special Buff (ON/OFF)
    public static final int DRAINING_AURA = 32101009; //Buff (Unlimited Duration)
    public static final int STAFF_BOOST = 32101005; //Buff
    public static final int DARK_CHAIN = 32101001; //Special Attack (Stun Debuff)

    public static final int CONDEMNATION_II = 32110017; //Special Buff (ON/OFF)
    public static final int BLUE_AURA = 32111012; //Buff (Unlimited Duration
    public static final int DARK_SHOCK = 32111016; //Buff (ON/OFF)
    public static final int DARK_SHOCK_EXPLOSION = 32110020;

    public static final int CONDEMNATION_III = 32120019; //Special Buff (ON/OFF)
    public static final int DARK_GENESIS = 32121004; //Special Attack (Stun Debuff) (Special Properties if on Cooldown)
    public static final int DARK_GENESIS_FA = 32121011; // Final Attack  attack if DarkGenesis is on CD
    public static final int DARK_AURA = 32121017; //Buff (Unlimited Duration)
    public static final int WEAKENING_AURA = 32121018; //Buff (Unlimited Duration)
    public static final int PARTY_SHIELD = 32121006;
    public static final int BATTLE_RAGE = 32121010; //Buff (ON/OFF)
    public static final int MAPLE_WARRIOR_BAM = 32121007; //Buff
    public static final int HEROS_WILL_BAM = 32121008;

    public static final int FOR_LIBERTY_BAM = 32121053;
    public static final int MASTER_OF_DEATH = 32121056;

    // V Skills
    public static final int AURA_SCYTHE = 400021006;
    public static final int ALTAR_OF_ANNIHILATION = 400021047;
    public static final int GRIM_HARVEST = 400021069;

    private ScheduledFuture blueAuraTimer;

    private static final int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
    };


    public static final int[] auras = new int[]{
            HASTY_AURA,
            DRAINING_AURA,
            BLUE_AURA,
            DARK_AURA,
            WEAKENING_AURA,
            AURA_SCYTHE,
    };

    private long drainAuraCD = Long.MIN_VALUE;
    private ScheduledFuture weaknessAuraTimer;
    private ScheduledFuture drainingAuraPassiveTimer;
    private List<Summon> annihilationAltarList = new ArrayList<>();
    private int hitCountBoss = 0;
    private ScheduledFuture mpCostPerSec;

    public BattleMage(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            EventManager.addFixedRateEvent(this::incrementAltarAnnihilationCount, 25, 25, TimeUnit.SECONDS);
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBattleMage(id);
    }



    private void activateAuraMPCost(int skillId, int slv) {
        if (mpCostPerSec != null && !mpCostPerSec.isDone()) {
            mpCostPerSec.cancel(true);
        }
        mpCostPerSec = EventManager.addFixedRateEvent(() -> chr.applyMpCon(skillId, slv), 1000, 1000);
    }

    private void spawnDeath(int skillID, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Field field = chr.getField();

        Summon death = Summon.getSummonByNoCTS(chr, skillID, slv);
        death.setSummonTerm(0);
        death.setAssistType(AssistType.AttackCounter);
        field.spawnSummon(death);

        o1.nReason = skillID;
        o1.nValue = 1;
        o1.summon = death;
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSummon, o1, true);
        tsm.sendSetStatPacket();
    }
    public void removeCondemnationBuff(Summon summon) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (summon != null && tsm.hasStat(CharacterTemporaryStat.BMageDeath)) {
            tsm.removeStatsBySkill(summon.getSkillID());
        }
    }

    private void cancelBlueAuraTimer() {
        if (blueAuraTimer != null && !blueAuraTimer.isDone()) {
            blueAuraTimer.cancel(true);
        }
    }

    public void applyBlueAuraDispel() {
        if (chr != null) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Skill skill = chr.getSkill(BLUE_AURA);
            if (chr.hasSkill(32120062)) { //Blue Aura - Dispel Magic
                if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, skill.getSkillId()) != null || tsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, AURA_SCYTHE) != null) {
                    tsm.removeAllDebuffs();
                } else {
                    cancelBlueAuraTimer();
                }
            }
        }
    }

    private void incrementAltarAnnihilationCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        changeAltarAnnihilationCount(tsm.getOption(CharacterTemporaryStat.MassDestructionRockets).nOption + 1);
    }

    private void changeAltarAnnihilationCount(int count) {
        if (!chr.hasSkill(ALTAR_OF_ANNIHILATION)) {
            return;
        }
        Skill skill = chr.getSkill(ALTAR_OF_ANNIHILATION);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int maxCount = si.getValue(y, slv);

        count = count > maxCount ? maxCount : count < 0 ? 0 : count;
        updateAlterAnnihilationCount(count);
    }

    private void updateAlterAnnihilationCount(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = count;
        o.rOption = ALTAR_OF_ANNIHILATION;
        tsm.putCharacterStatValue(CharacterTemporaryStat.MassDestructionRockets, o);
        tsm.sendSetStatPacket();
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
        if (hasHitMobs) {
            if (attackInfo.skillId != CONDEMNATION
                    && attackInfo.skillId != CONDEMNATION_I
                    && attackInfo.skillId != CONDEMNATION_II
                    && attackInfo.skillId != CONDEMNATION_III) {
                if (si != null && si.getElemAttr().equalsIgnoreCase("d") && tsm.getOptByCTSAndSkill(CharacterTemporaryStat.AttackCountX, MASTER_OF_DEATH) != null) {
                    decreaseCondemnationCooldown();
                }
                incrementCondemnation(attackInfo);
            }
            drainAuraActiveHPRecovery(attackInfo);
            if (attackInfo.skillId != DARK_SHOCK_EXPLOSION) {
                doDarkShockBonusAttack(attackInfo);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case DARK_CHAIN:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case DARK_SHOCK:
                if (!tsm.hasStat(CharacterTemporaryStat.DarkLighting)) {
                    return;
                }
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.DarkLightning, o1);
                }
                break;
            case DARK_GENESIS:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case GRIM_HARVEST:
                createGrimHarvestForceAtom(attackInfo);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void doDarkShockBonusAttack(AttackInfo attackInfo) {
        Field field = chr.getField();
        List<Integer> mobObjIdList = new ArrayList<>();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            if (mts.hasCurrentMobStat(MobStat.DarkLightning)) {
                mobObjIdList.add(mob.getObjectId());
                mts.removeMobStat(MobStat.DarkLightning, true);
            }
        }
        if (mobObjIdList.size() > 0) {
            chr.write(UserLocal.userBonusAttackRequest(DARK_SHOCK_EXPLOSION, mobObjIdList));
        }
    }

    private void createGrimHarvestForceAtom(AttackInfo attackInfo) {
        Summon summon = chr.getField().getSummonByChrAndSkillId(chr, GRIM_HARVEST);
        ForceAtomEnum fae = ForceAtomEnum.GRIM_HARVEST;
        Rect rect = new Rect(
                new Position(
                        -1500,
                        -1500),
                new Position(
                        1500,
                        1500)
        );
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 2, 2,
                    0, 1300, Util.getCurrentTime(), 1, 0, new Position());
            fai.setBossMob(mob != null && mob.isBoss());
            chr.createForceAtom(new ForceAtom(true, chr.getId(), mob.getObjectId(), fae,
                    false, mob.getObjectId(), GRIM_HARVEST, fai, mob.getPosition().getRectAround(rect), 0, 0,
                    summon.getPosition(), GRIM_HARVEST, summon.getPosition(), 0), false);
        }
    }

    private void extendGrimHarvest(boolean isBoss) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        long remainingTime = tsm.getRemainingTime(CharacterTemporaryStat.IndieSummon, GRIM_HARVEST);
        long addedTimeMS = isBoss ? 2000 : 200;

        Option o1 = new Option();
        o1.nValue = 1;
        o1.nReason = GRIM_HARVEST;
        o1.tTerm = (int) (remainingTime + addedTimeMS);
        o1.summon = tsm.getOptByCTSAndSkill(CharacterTemporaryStat.IndieSummon, GRIM_HARVEST).summon;
        o1.setInMillis(true);
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSummon, o1, true);
        tsm.sendSetStatPacket();
    }

    public void recallGrimHarvest() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        if (field != null) {
            Summon summon = Summon.getSummonByNoCTS(chr, GRIM_HARVEST, chr.getSkillLevel(GRIM_HARVEST));
            summon.setMoveAbility(MoveAbility.Stop);
            summon.setAssistType(AssistType.Attack);
            summon.setSummonTerm((int) ((tsm.getRemainingTime(CharacterTemporaryStat.IndieSummon, GRIM_HARVEST)) / 1000));
            field.spawnSummon(summon);
        }
    }


    public void handleForceAtomCollision(int faKey, int skillId, int mobObjId, Position position, InPacket inPacket) {
        switch (skillId) {
            case GRIM_HARVEST:
                ForceAtom fa = chr.getForceAtomByKey(faKey);
                ForceAtomInfo fai = fa.getFaiList().stream().filter(sfai -> sfai.getKey() == faKey).findFirst().orElse(null);
                if (fai == null) {
                    return;
                }
                boolean isBoss = fai.isBossMob();
                extendGrimHarvest(isBoss);
                break;
        }

        super.handleForceAtomCollision(faKey, skillId, mobObjId, position, inPacket);
    }

    private void incrementCondemnation(AttackInfo attackInfo) {
        Field field = chr.getField();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (!tsm.hasStat(CharacterTemporaryStat.BMageDeath)) {
            return;
        }
        int killCount = tsm.getOption(CharacterTemporaryStat.BMageDeath).nOption;
        Skill skill = chr.getSkill(getCondemnationSkillId());
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            long dmgOnMob = Arrays.stream(mai.damages).sum();
            if (mob == null) {
                continue;
            }
            if (mob.isBoss()) {
                if (hitCountBoss < 1) {
                    hitCountBoss++;
                } else {
                    hitCountBoss = 0;
                    if (killCount < getCondemnationKillReq()) {
                        killCount++;
                    } else {
                        killCount = doCondemnationAttack(killCount);
                    }
                }
            } else {
                if (mob.getHp() <= dmgOnMob) {
                    if (killCount < getCondemnationKillReq()) {
                        killCount++;
                    } else {
                        killCount = doCondemnationAttack(killCount);
                    }

                }
            }
        }
        setCondemnationCount(killCount);
    }

    private void decreaseCondemnationCooldown() {
        Skill skill = chr.getSkill(MASTER_OF_DEATH);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        chr.reduceSkillCoolTime(JobConstants.JobEnum.BATTLE_MAGE_1.getJobId(), si.getValue(z, slv));
    }

    private void setCondemnationCount(int killCount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = killCount;
        o.rOption = getCondemnationSkillId();
        tsm.putCharacterStatValue(CharacterTemporaryStat.BMageDeath, o);
        chr.chatMessage("killCount = " + o.nOption);
        tsm.sendSetStatPacket();
    }

    private int doCondemnationAttack(int killCount) {
        if (!chr.hasSkillOnCooldown(JobConstants.JobEnum.BATTLE_MAGE_1.getJobId())) {
            chr.getField().broadcastPacket(Summoned.summonedAssistAttackRequest(getDeath(), 0));
            chr.addSkillCoolTime(JobConstants.JobEnum.BATTLE_MAGE_1.getJobId(), getCondemnationCooldown() * 1000);
            killCount = 0;
        }
        return killCount;
    }

    private int getCondemnationCooldown() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(getCondemnationSkillId());

        return si.getValue(time, 1);
    }

    private int getCondemnationKillReq() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(getCondemnationSkillId());

        // Master of Death Buff
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.AttackCountX, MASTER_OF_DEATH) != null) {
            return 1;
        }

        return si.getValue(x, 1);
    }

    private int getCondemnationSkillId() {
        int skillId = 0;

        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_1.getJobId()) {
            skillId = CONDEMNATION;
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_2.getJobId()) {
            skillId = CONDEMNATION_I;
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_3.getJobId()) {
            skillId = CONDEMNATION_II;
        }
        if (chr.getJob() == JobConstants.JobEnum.BATTLE_MAGE_4.getJobId()) {
            skillId = CONDEMNATION_III;
        }
        return skillId;
    }

    private Summon getDeath() {
        return chr.getField().getSummons().stream().filter(s -> s.getSkillID() == CONDEMNATION
                        || s.getSkillID() == CONDEMNATION_I
                        || s.getSkillID() == CONDEMNATION_II
                        || s.getSkillID() == CONDEMNATION_III
                        && s.getChr() == chr)
                .findAny().orElse(null);
    }

    private void drainAuraActiveHPRecovery(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(DRAINING_AURA);
        if (skill == null) {
            return;
        }
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int healCoolTime = 1000 * si.getValue(subTime, slv);
        int healingrate = si.getValue(x, slv);
        List<Char> pChrList = new ArrayList<>();
        if (chr.getParty() != null) {
            pChrList.addAll(chr.getParty().getPartyMembersInSameField(chr));
        }
        pChrList.add(chr);
        long totaldmg = Arrays.stream(Util.getRandomFromCollection(attackInfo.mobAttackInfo).damages).sum();
        int restoration = (int) ((healingrate * totaldmg) / 100D);
        for (Char pChr : pChrList) {
            TemporaryStatManager ptsm = pChr.getTemporaryStatManager();
            if ((ptsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, skill.getSkillId()) != null || ptsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, AURA_SCYTHE) != null) &&
                    !pChr.hasSkillOnCooldown(JobConstants.JobEnum.BATTLE_MAGE_2.getJobId())) {
                pChr.chatMessage("Healed " + restoration + " by Draining Aura's Active effect");
                pChr.heal(restoration);
                pChr.addSkillCoolTime(JobConstants.JobEnum.BATTLE_MAGE_2.getJobId(), healCoolTime);
            }
        }
    }

    private void drainAuraPassiveHPRecovery() {
        Skill skill = chr.getSkill(DRAINING_AURA);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (skill == null || !tsm.hasStatBySkillId(DRAINING_AURA)) {
            if (drainingAuraPassiveTimer != null) {
                drainingAuraPassiveTimer.cancel(true);
            }
            return;
        }
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        drainingAuraPassiveTimer = EventManager.addFixedRateEvent(() -> chr.heal(si.getValue(hp, slv)), 4, 4, TimeUnit.SECONDS);
    }

    public void applyWeakenAuraOnMob() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(WEAKENING_AURA);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o = new Option();
        int delay = si.getValue(y, slv);
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, skill.getSkillId()) != null || tsm.getOptByCTSAndSkill(CharacterTemporaryStat.BMageAura, AURA_SCYTHE) != null) {
            Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
            if (!chr.isLeft()) {
                rect = rect.horizontalFlipAround(chr.getPosition().getX());
            }
            Field field = chr.getField();
            List<Mob> mobs = field.getMobsInRect(rect);
            for (Mob mob : mobs) {
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                o.nOption = -si.getValue(x, slv);
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
                mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
            }
            weaknessAuraTimer = EventManager.addEvent(this::applyWeakenAuraOnMob, delay, TimeUnit.SECONDS);
        }
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        //if (cts == AuraScythe) { // Quality of Life |  call the last Aura active before Aura Scythe was called
        //int lastAuraSkillId = tsm.getOption(AuraScythe).xOption;
        //if (lastAuraSkillId != 0) {
        //    handleBuff(chr.getClient(), null, lastAuraSkillId, (byte) chr.getSkillLevel(lastAuraSkillId));
        //}
        if (cts == CharacterTemporaryStat.BMageAura) {
            int skillid = tsm.getOption(CharacterTemporaryStat.BMageAura).rOption;
            switch (skillid) {
                case DRAINING_AURA:
                    if (drainingAuraPassiveTimer != null && !mpCostPerSec.isDone()) {
                        drainingAuraPassiveTimer.cancel(true);
                    }
                case HASTY_AURA:
                case BLUE_AURA:
                case DARK_AURA:
                case WEAKENING_AURA:
                case AURA_SCYTHE:
                    if (mpCostPerSec != null && !mpCostPerSec.isDone()) {
                        mpCostPerSec.cancel(true);
                    }
                    break;
            }
        }

        super.handleRemoveCTS(cts);
    }

    @Override
    public int getFinalAttackSkill() {
        SkillInfo si = SkillData.getSkillInfoById(DARK_GENESIS_FA);
        if (chr.getSkill(DARK_GENESIS) != null) {
            int slv = chr.getSkill(DARK_GENESIS).getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                return DARK_GENESIS_FA;
            }
        }
        return 0;
    }

    public static void removeAuras(TemporaryStatManager tsm) {
        for (int aura : auras) {
            tsm.removeStatsBySkill(aura);
        }
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
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        Option o8 = new Option();
        switch (skillID) {
            case PARTY_SHIELD:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                aa.setDelay((short) 16);
                chr.getField().spawnAffectedArea(aa);

                break;
            case HEROS_WILL_BAM:
                tsm.removeAllDebuffs();
                break;
            case CONDEMNATION:
            case CONDEMNATION_I:
            case CONDEMNATION_II:
            case CONDEMNATION_III:
                o1.nOption = 0;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.BMageDeath, o1);
                spawnDeath(skillID, slv);
                break;
            case STAFF_BOOST:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o1);
                break;
            case HASTY_AURA:
                removeAuras(tsm);
                AffectedArea hastyAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(hastyAura);
                activateAuraMPCost(skillID, slv);
                break;
            case DRAINING_AURA:
                removeAuras(tsm);
                AffectedArea drainAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(drainAura);
                activateAuraMPCost(skillID, slv);
                if (drainingAuraPassiveTimer != null && !drainingAuraPassiveTimer.isDone()) {
                    drainingAuraPassiveTimer.cancel(true);
                }
                drainAuraPassiveHPRecovery();
                break;
            case BLUE_AURA:
                removeAuras(tsm);
                AffectedArea blueAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(blueAura);
                cancelBlueAuraTimer();
                applyBlueAuraDispel(); //Hyper
                blueAuraTimer = EventManager.addFixedRateEvent(this::applyBlueAuraDispel, 0, 5, TimeUnit.SECONDS);
                activateAuraMPCost(skillID, slv);
                break;
            case DARK_AURA:
                removeAuras(tsm);
                AffectedArea darkAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(darkAura);
                activateAuraMPCost(skillID, slv);
                break;
            case WEAKENING_AURA:
                removeAuras(tsm);
                o3.nOption = 1;
                o3.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.BMageAura, o3);
                AffectedArea weakeningAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(weakeningAura);
                activateAuraMPCost(skillID, slv);
                break;
            case AURA_SCYTHE:
                //int lastAuraSkillId = tsm.hasStat(BMageAura) ? tsm.getOption(BMageAura).rOption : 0;
                removeAuras(tsm);
                o7.nOption = 1;
                o7.rOption = skillID;
                o7.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.BMageAura, o7);
                o8.nOption = 1;
                o8.rOption = skillID;
                o8.tOption = si.getValue(time, slv);
                //o8.xOption = lastAuraSkillId;
                tsm.putCharacterStatValue(CharacterTemporaryStat.AuraScythe, o8);

                weakeningAura = AffectedArea.getAura(chr, skillID, slv);
                field.spawnAura(weakeningAura);

                SkillInfo hastyAuraSI = SkillData.getSkillInfoById(HASTY_AURA);
                byte hastyAuraSLV = (byte) chr.getSkillLevel(HASTY_AURA);
                if (chr.hasSkill(HASTY_AURA)) {
                    o1.nReason = skillID;
                    o1.nValue = hastyAuraSI.getValue(indieSpeed, hastyAuraSLV);
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSpeed, o1);
                    o2.nReason = skillID;
                    o2.nValue = -1;
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o2);
                }

                SkillInfo blueAuraSI = SkillData.getSkillInfoById(BLUE_AURA);
                byte blueAuraSLV = (byte) chr.getSkillLevel(BLUE_AURA);
                if (chr.hasSkill(BLUE_AURA)) {
                    o3.nReason = skillID;
                    o3.nValue = blueAuraSI.getValue(asrR, blueAuraSLV);
                    o3.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndieAsrR, o3);
                    o4.nReason = skillID;
                    o4.nValue = blueAuraSI.getValue(terR, blueAuraSLV);
                    o4.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndieTerR, o4);
                    o5.nOption = blueAuraSI.getValue(y, blueAuraSLV);
                    o5.rOption = skillID;
                    o5.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IgnoreMobDamR, o5);
                    applyBlueAuraDispel(); //Hyper
                    blueAuraTimer = EventManager.addFixedRateEvent(this::applyBlueAuraDispel, 0, 5, TimeUnit.SECONDS);
                }

                SkillInfo darkAuraSI = SkillData.getSkillInfoById(DARK_AURA);
                byte darkAuraSLV = (byte) chr.getSkillLevel(DARK_AURA);
                if (chr.hasSkill(DARK_AURA)) {
                    o6.nReason = skillID;
                    o6.nValue = darkAuraSI.getValue(indieDamR, darkAuraSLV);
                    o6.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o6);
                }
                activateAuraMPCost(skillID, slv);
                break;
            case DARK_SHOCK:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.DarkLighting, o1);
                break;
            case BATTLE_RAGE:
                int mobsHit = si.getValue(mobCount, slv);
                int fd = si.getValue(x, slv);
                o1.nOption = (fd * 100) + mobsHit; // dmg 20,  mobsHit 01
                o1.rOption = skillID;
                o1.xOption = fd;
                tsm.putCharacterStatValue(CharacterTemporaryStat.Enrage, o1);
                o3.nOption = si.getValue(z, slv);
                o3.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.EnrageCr, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.EnrageCrDamMin, o4);
                break;
            case MAPLE_WARRIOR_BAM:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStatR, o1);
                break;
            case FOR_LIBERTY_BAM:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                break;
            case MASTER_OF_DEATH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.AttackCountX, o1);
                break;
            case GRIM_HARVEST:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.Attack);
                summon.setSummonTerm(80);
                field.spawnSummon(summon);
                break;
            case ALTAR_OF_ANNIHILATION:
                if (tsm.getOption(CharacterTemporaryStat.MassDestructionRockets).nOption < 1) {
                    chr.chatMessage("You don't have enough Altar stacks.");
                    return;
                }

                if (getAnnihilationAltarList().size() >= 4) {
                    field.removeLife(getAnnihilationAltarList().get(0));
                }
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.CreateShootObj);
                summon.setFlip(true);
                field.spawnAddSummon(summon);
                getAnnihilationAltarList().add(summon);
                changeAltarAnnihilationCount(tsm.getOption(CharacterTemporaryStat.MassDestructionRockets).nOption - 1);

                // TODO: not sure if this is how the CD works. omitted for now.
                // After initial summon, next 3 have 25 sec CD.
                //if (getAnnihilationAltarList().get(1) != null) {
                //    chr.chatMessage("You can summon your next altar in 25 seconds.");
                //    chr.addSkillCoolTime(skillID, 25 * 1000);
                //}

                o1.nValue = 1;
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                o1.summon = summon;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSummon, o1, true);
                chr.applyMpCon(skillID, slv);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (weaknessAuraTimer != null) {
            weaknessAuraTimer.cancel(false);
        }
        cancelBlueAuraTimer();
        if (drainingAuraPassiveTimer != null && !drainingAuraPassiveTimer.isDone()) {
            drainingAuraPassiveTimer.cancel(true);
        }
        if (mpCostPerSec != null && !mpCostPerSec.isDone()) {
            mpCostPerSec.cancel(true);
        }
        super.cancelTimers();
    }


    public List<Summon> getAnnihilationAltarList() {
        return annihilationAltarList;
    }

    public void setAnnihilationAltarList(List<Summon> annihilationAltarList) {
        this.annihilationAltarList = annihilationAltarList;
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.BATTLE_MAGE_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.BATTLE_MAGE_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.BATTLE_MAGE_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item beginnerStaff = ItemData.getItemDeepCopy(1382100);
        chr.addItemToInventory(beginnerStaff);

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352950)); // Magic Marble (Secondary)
    }
}