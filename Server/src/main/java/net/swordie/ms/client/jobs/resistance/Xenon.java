package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
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
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Xenon extends Job {
    public static final int SUPPLY_SURPLUS = 30020232;
    public static final int MULTILATERAL_I = 30020234;
    public static final int MODAL_SHIFT = 30021236;
    public static final int LIBERTY_BOOSTERS = 30021236;
    public static final int MIMIC_PROTOCOL = 30020240;
    public static final int PROMESSA_ESCAPE = 30021235;

    public static final int CIRCUIT_SURGE = 36001002; //Buff
    public static final int PINPOINT_SALVO = 36001005; //Special Attack

    public static final int XENON_BOOSTER = 36101004; //Buff
    public static final int EFFICIENCY_STREAMLINE = 36101003; //Buff
    public static final int ION_THRUST = 36101001; //Special Attack
    public static final int PINPOINT_SALVO_REDESIGN_A = 36100010; //Special Attack Upgrade  (Passive Upgrade)

    public static final int HYBRID_DEFENSES = 36111003; //Buff
    public static final int AEGIS_SYSTEM = 36111004; //Special Buff (ON/OFF)
    public static final int AEGIS_SYSTEM_ATOM = 36110004; //Special Buff (ON/OFF)
    public static final int MANIFEST_PROJECTOR = 36111006; //Special Buff (Special Duration)
    public static final int EMERGENCY_RESUPPLY = 36111008; //Special Skill
    public static final int PINPOINT_SALVO_REDESIGN_B = 36110012; //Special Attack Upgrade  (Passive Upgrade)
    public static final int TRIANGULATION = 36110005;

    public static final int HYPOGRAM_FIELD_FORCE_FIELD = 36121002;
    public static final int HYPOGRAM_FIELD_PENETRATE = 36121013;
    public static final int HYPOGRAM_FIELD_SUPPORT = 36121014;
    public static final int HYPOGRAM_FIELD_PERSIST = 36120051; // hyper passive
    public static final int TEMPORAL_POD = 36121007;
    public static final int OOPARTS_CODE = 36121003; //Buff
    public static final int MAPLE_WARRIOR_XENON = 36121008; //Buff
    public static final int PINPOINT_SALVO_PERFECT_DESIGN = 36120015; //Sp. Attack Upgrade  (Passive Upgrade)
    public static final int HEROS_WILL_XENON = 36121009;

    public static final int ORBITAL_CATACLYSM = 36121052;
    public static final int AMARANTH_GENERATOR = 36121054;
    public static final int ENTANGLISH_LASH = 36121053;

    public static final int OMEGA_BLASTER = 400041007;
    public static final int CORE_OVERLOAD_BUFF = 400041029;
    public static final int CORE_OVERLOAD_ATTACK = 400041031;
    public static final int HYPOGRAM_FIELD_FUSION = 400041044;

    private int supply;
    private int supplyProp;
    private int hybridDefenseCount;
    private ScheduledFuture supplyTimer;

    private static final int[] addedSkills = new int[]{
            SUPPLY_SURPLUS,
            MULTILATERAL_I,
            MODAL_SHIFT,
            LIBERTY_BOOSTERS,
            MIMIC_PROTOCOL,
            PROMESSA_ESCAPE,
    };


    public Xenon(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            supplyProp = SkillData.getSkillInfoById(SUPPLY_SURPLUS).getValue(prop, 1);

            if (supplyTimer != null && !supplyTimer.isDone()) {
                supplyTimer.cancel(true);
            }
            supplyTimer = EventManager.addEvent((Runnable) this::incrementSupply, 4, TimeUnit.SECONDS);
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isXenon(id);
    }


    public void useOmegaBlasterAttack() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOptByCTSAndSkill(OmegaBlaster, OMEGA_BLASTER) != null && tsm.getOptByCTSAndSkill(OmegaBlaster, OMEGA_BLASTER).nOption == -1) {
            Skill skill = chr.getSkill(OMEGA_BLASTER);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            Option o1 = new Option();

            long secPerXSec = si.getValue(y, slv) * 1000;
            long cap = si.getValue(z, slv) * 1000;

            long chargingDuration = Util.getCurrentTimeLong() - tsm.getOptByCTSAndSkill(OmegaBlaster, OMEGA_BLASTER).startTime;
            long addedDuration = (chargingDuration / secPerXSec) * 1000;
            long fullDuration = 5000 + (addedDuration > cap ? cap : addedDuration);

            o1.nOption = 1;
            o1.rOption = skill.getSkillId();
            o1.tOption = (int) fullDuration;
            o1.setInMillis(true);
            tsm.putCharacterStatValue(OmegaBlaster, o1);
            tsm.putCharacterStatValue(IndieNotDamaged, o1); // invincibility
        } else {
            tsm.removeStat(OmegaBlaster, true);
        }
        tsm.sendSetStatPacket();
    }

    public void applySupplyCost(int skillID, int slv, SkillInfo si) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (skillID == PINPOINT_SALVO || skillID == PINPOINT_SALVO_REDESIGN_A || skillID == PINPOINT_SALVO_REDESIGN_B || skillID == PINPOINT_SALVO_PERFECT_DESIGN) {
            return;
        }
        if (tsm.hasStat(AmaranthGenerator) || tsm.hasStat(OverloadMode)) {
            return;
        }
        if (si == null) {
            return;
        }
        if (si.getValue(powerCon, slv) > 0) {
            supply -= si.getValue(powerCon, slv);
            supply = Math.max(0, supply);
        }
        updateSupply();
    }

    private void incrementSupply() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        incrementSupply(1);
        supplyTimer = EventManager.addEvent((Runnable) this::incrementSupply, (tsm.hasStat(OverloadMode) ? 2 : 4), TimeUnit.SECONDS);
    }

    public void incrementSupply(int amount) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int maxSupply = 20;
        if (tsm.hasStat(OverloadMode)) {
            maxSupply = 40;
        }
        if (supply < maxSupply) {
            supply = tsm.getOption(SurplusSupply).nOption;
            supply += amount;
            supply = Math.min(maxSupply, supply);
            updateSupply();
        }
    }

    private void updateSupply() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = supply;
        tsm.putCharacterStatValue(SurplusSupply, o);

        if (tsm.hasStat(OverloadMode)) {
            Option o1 = new Option();
            o1.nReason = CORE_OVERLOAD_BUFF + 100;
            o1.nValue = supply;
            tsm.putCharacterStatValue(IndieDamR, o1);
        }

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
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs && attackInfo.skillId != 0 && attackInfo.skillId != TRIANGULATION) {
            // Triangulation
            applyTriangulationOnMob(attackInfo);

            if (attackInfo.skillId != PINPOINT_SALVO
                    && attackInfo.skillId != PINPOINT_SALVO_REDESIGN_A
                    && attackInfo.skillId != PINPOINT_SALVO_REDESIGN_B
                    && attackInfo.skillId != PINPOINT_SALVO_PERFECT_DESIGN
                    && attackInfo.skillId != AEGIS_SYSTEM_ATOM
                    && attackInfo.skillId != TRIANGULATION
                    && attackInfo.skillId != CORE_OVERLOAD_ATTACK
                    && attackInfo.skillId != HYPOGRAM_FIELD_FORCE_FIELD
                    && attackInfo.skillId != HYPOGRAM_FIELD_PENETRATE
                    && attackInfo.skillId != HYPOGRAM_FIELD_FUSION) {
                coreOverloadSkillManaConsumption();
                if (!chr.hasSkillOnCooldown(PINPOINT_SALVO)) {
                    createPinPointSalvoForceAtom();
                }
            }
        }
        if (skillID != ION_THRUST) {
            applySupplyCost(skillID, (byte) slv, si);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ENTANGLISH_LASH:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.removeBuffs();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                }
                break;
            case ORBITAL_CATACLYSM:
                o1.nOption = -si.getValue(x, slv);
                o1.rOption = skillID;
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
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tTerm = si.getValue(y, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    public void applyTriangulationOnMob(AttackInfo attackInfo) {
        if (!chr.hasSkill(TRIANGULATION)) {
            return;
        }
        Skill skill = chr.getSkill(TRIANGULATION);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(TRIANGULATION);
        int proc = si.getValue(prop, slv);
        Option o1 = new Option();
        int amount = 1;
        o1.rOption = TRIANGULATION;
        o1.wOption = chr.getId();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();

            if (Util.succeedProp(proc)) {
                if (mts.hasCurrentMobStat(MobStat.Explosion)) {
                    amount = mts.getCurrentOptionsByMobStat(MobStat.Explosion).nOption;
                    if (amount <= 3) {
                        amount++;
                    }

                    mts.removeMobStat(MobStat.Explosion, false);
                }

                o1.nOption = amount;
                mts.addStatOptionsAndBroadcast(MobStat.Explosion, o1);
            }
        }
    }

    private void createPinPointSalvoForceAtom() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(TriflingWhimOnOff)) {
            return;
        }
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(PINPOINT_SALVO);
        int slv = chr.getSkillLevel(PINPOINT_SALVO);
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        ForceAtomEnum fae = ForceAtomEnum.XENON_ROCKET_3;
        for (int i = 0; i < si.getValue(bulletCount, slv); i++) {
            Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
            int angle = new Random().nextInt(90) + 45;
            int fImpact = new Random().nextInt(3) + 18;
            int sImpact = new Random().nextInt(3) + 28;
            int delay = new Random().nextInt(200) + 50;
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), fImpact, sImpact,
                    angle, delay, Util.getCurrentTime(), 1, 0,
                    new Position());
            faiList.add(forceAtomInfo);
            targetList.add(mob != null ? mob.getObjectId() : 0);
        }
        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                true, targetList, PINPOINT_SALVO, faiList, rect, 0, 300,
                new Position(), PINPOINT_SALVO, new Position(), 0));
        chr.addSkillCoolTime(PINPOINT_SALVO, si.getValue(x, slv) * 1000);
    }

    public int getPinPointSkill() {
        int skill = PINPOINT_SALVO;
        if (chr.hasSkill(PINPOINT_SALVO_REDESIGN_A)) {
            skill = PINPOINT_SALVO_REDESIGN_A;
        }
        if (chr.hasSkill(PINPOINT_SALVO_REDESIGN_B)) {
            skill = PINPOINT_SALVO_REDESIGN_B;
        }
        if (chr.hasSkill(PINPOINT_SALVO_PERFECT_DESIGN)) {
            skill = PINPOINT_SALVO_PERFECT_DESIGN;
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
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
            coreOverloadSkillManaConsumption();
        }
        applySupplyCost(skillID, slv, si);
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case EMERGENCY_RESUPPLY:
                int rechargedSupply = si.getValue(x, slv);
                if (rechargedSupply + supply > 20) {
                    rechargedSupply = 20;
                }
                incrementSupply(rechargedSupply);
                break;
            case HEROS_WILL_XENON:
                tsm.removeAllDebuffs();
                break;
            case CIRCUIT_SURGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
            case XENON_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case EFFICIENCY_STREAMLINE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                break;
            case HYBRID_DEFENSES:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o1);
                hybridDefenseCount = si.getValue(x, slv);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.mOption = hybridDefenseCount;
                tsm.putCharacterStatValue(StackBuff, o1);
                break;
            case AEGIS_SYSTEM:
                if (tsm.hasStat(XenonAegisSystem)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(XenonAegisSystem, o1);
                }
                break;
            case PINPOINT_SALVO:
                if (tsm.hasStat(TriflingWhimOnOff)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(TriflingWhimOnOff, o1);
                }
                break;
            case MANIFEST_PROJECTOR:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case OOPARTS_CODE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePMdR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBDR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o2);
                break;
            case MAPLE_WARRIOR_XENON:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case AMARANTH_GENERATOR:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AmaranthGenerator, o1);
                if (!tsm.hasStat(OverloadMode)) {
                    incrementSupply(20);
                }
                break;
            case HYPOGRAM_FIELD_FUSION:
            case HYPOGRAM_FIELD_FORCE_FIELD:
            case HYPOGRAM_FIELD_PENETRATE:
            case HYPOGRAM_FIELD_SUPPORT:
                Position position = inPacket.decodePosition();
                Summon summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setPosition(position);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                if (si == null) {
                    si = SkillData.getSkillInfoById(skillID);
                }
                summon.setSummonTerm(si.getValue(time, slv) + (chr.hasSkill(HYPOGRAM_FIELD_PERSIST) && skillID != HYPOGRAM_FIELD_FUSION ? 10 : 0));
                field.spawnSummon(summon);
                if (skillID == HYPOGRAM_FIELD_FUSION || skillID == HYPOGRAM_FIELD_SUPPORT) {
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                    aa.setDelay((short) 2);
                    aa.setPosition(chr.getPosition());
                    aa.setRect(position.getRectAround(si.getFirstRect()));
                    aa.setDuration((si.getValue(time, slv) + (chr.hasSkill(HYPOGRAM_FIELD_PERSIST) && skillID != HYPOGRAM_FIELD_FUSION ? 10 : 0)) * 1000);
                    field.spawnAffectedArea(aa);
                }
                if (skillID != HYPOGRAM_FIELD_FUSION) {
                    chr.addSkillCoolTime(HYPOGRAM_FIELD_FORCE_FIELD, 30000);
                    chr.addSkillCoolTime(HYPOGRAM_FIELD_PENETRATE, 30000);
                    chr.addSkillCoolTime(HYPOGRAM_FIELD_SUPPORT, 30000);
                }
                break;
            case TEMPORAL_POD:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setField(chr.getField());
                aa.setRect(chr.getPosition().getRectAround(si.getFirstRect()));
                aa.setDelay((short) 15);
                aa.setDuration(59580);
                aa.setChrLv(0);
                field.spawnAffectedArea(aa);
                aa.activateTimer(1000, 1000);
                chr.write(UserLocal.SitTimeCapsule());
                break;
            case CORE_OVERLOAD_BUFF:
                o1.nOption = si.getValue(w, slv);
                o1.rOption = skillID;
                o1.xOption = 91; // from sniff, stayed the same but unsure what it means
                tsm.putCharacterStatValue(OverloadMode, o1);
                updateSupply();
                break;
            case OMEGA_BLASTER: // Charging
                o1.nOption = -1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(OmegaBlaster, o1);
        }
        tsm.sendSetStatPacket();
    }

    private void coreOverloadSkillManaConsumption() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(CORE_OVERLOAD_BUFF);
        if (!tsm.hasStat(OverloadMode) || skill == null || !chr.hasSkill(CORE_OVERLOAD_BUFF)) {
            return;
        }
        if (chr.getMP() < 2 * (chr.getMaxMP() / 100D)) {
            handleSkillRemove(chr, CORE_OVERLOAD_BUFF);
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int mpConsumptionPerSkill = (int) (chr.getMaxMP() / 100D) * si.getValue(q, slv);
        chr.healMP(-mpConsumptionPerSkill);
    }

    public void coreOverloadManaConsumption() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(CORE_OVERLOAD_BUFF);
        if (!tsm.hasStat(OverloadMode) || skill == null || !chr.hasSkill(CORE_OVERLOAD_BUFF)) {
            return;
        }
        if (chr.getMP() < 2 * (chr.getMaxMP() / 100D)) {
            handleSkillRemove(chr, CORE_OVERLOAD_BUFF);
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        chr.healMP(-si.getValue(y, slv));
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        if (chr.hasSkill(HYBRID_DEFENSES)) {
            Skill skill = chr.getSkill(HYBRID_DEFENSES);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

            if (tsm.getOptByCTSAndSkill(StackBuff, HYBRID_DEFENSES) != null) {
                if (hitInfo.hpDamage > 0) {
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.mOption = hybridDefenseCount;
                    tsm.putCharacterStatValue(StackBuff, o1);
                    o2.nOption -= si.getValue(y, slv);
                    o2.rOption = skill.getSkillId();
                    tsm.putCharacterStatValue(EVAR, o2);

                    tsm.sendSetStatPacket();
                } else {
                    hybridDefenseCount--;
                    if (hybridDefenseCount <= 0) {
                        tsm.removeStatsBySkill(HYBRID_DEFENSES);
                        tsm.sendResetStatPacket();
                        return;
                    }
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.mOption = hybridDefenseCount;
                    tsm.putCharacterStatValue(StackBuff, o1);
                    o2.nOption -= 0;
                    o2.rOption = skill.getSkillId();
                    tsm.putCharacterStatValue(EVAR, o2);
                    tsm.sendSetStatPacket();
                }

            }
        }

        Skill aegis = chr.getSkill(AEGIS_SYSTEM);
        if (tsm.hasStat(XenonAegisSystem) && aegis != null) {
            SkillInfo si = SkillData.getSkillInfoById(AEGIS_SYSTEM);
            int slv = aegis.getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                int mobID = hitInfo.mobID;
                ForceAtomEnum fae = ForceAtomEnum.XENON_ROCKET_1;
                int curTime = Util.getCurrentTime();
                List<ForceAtomInfo> faiList = new ArrayList<>();
                List<Integer> mobList = new ArrayList<>();
                int extraAtomFromFusion = 0;
                if (chr.hasSkill(HYPOGRAM_FIELD_FUSION) && tsm.getOptByCTSAndSkill(IndieDamR, HYPOGRAM_FIELD_FUSION) != null) {
                    SkillInfo fsi = SkillData.getSkillInfoById(HYPOGRAM_FIELD_FUSION);
                    byte fslv = (byte) chr.getSkillLevel(HYPOGRAM_FIELD_FUSION);
                    extraAtomFromFusion = fsi.getValue(x, fslv);
                }
                int atomCount = si.getValue(x, slv) + extraAtomFromFusion;
                Random random = new Random();
                for (int i = 0; i < atomCount; i++) {
                    int firstImpact = 5 + random.nextInt(6);
                    int secondImpact = 5 + random.nextInt(6);
                    int angle = random.nextInt(180);
                    ForceAtomInfo fai = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                            angle, 0, curTime, 0, AEGIS_SYSTEM_ATOM, new Position(0, 0));
                    faiList.add(fai);
                    mobList.add(mobID);
                }
                chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae, true,
                        mobList, AEGIS_SYSTEM_ATOM, faiList, null, 0, 0,
                        null, 0, null, 0));
            }
        }

        if (Util.succeedProp(supplyProp)) {
            incrementSupply(1);
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {
            case CORE_OVERLOAD_BUFF:
                tsm.removeStatsBySkill(CORE_OVERLOAD_BUFF + 100);
                tsm.removeStatsBySkill(skillID);
                supply = supply > 20 ? 20 : supply;
                updateSupply();
                break;
        }

        super.handleSkillRemove(chr, skillID);
    }

    @Override
    public void cancelTimers() {
        if (supplyTimer != null) {
            supplyTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        /*
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(310010000); // edelstein hideout
        cs.setLevel(10);
        cs.setJob(3600);
        cs.setStr(4);
        cs.setInt(4);
        cs.setDex(4);
        cs.setLuk(4);
        cs.setAp(4 + chr.getLevel() * 5);
        cs.setMaxHp(cs.getMaxMp() + 50);
        byte jobLevel = (byte) JobConstants.getJobLevel(chr.getJob());
        int currentSP = cs.getExtendSP().getSpByJobLevel(jobLevel);
        cs.getExtendSP().setSpToJobLevel(jobLevel, currentSP + 5);
         */
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.XENON_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.XENON_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.XENON_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
        handleJobAdvance(JobConstants.JobEnum.XENON_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353001)); // Dual Core Controller (Secondary)

    }
}

