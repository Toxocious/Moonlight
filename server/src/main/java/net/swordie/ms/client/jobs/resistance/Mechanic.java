package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserPool;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
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
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.Mechanic;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.enums.ForceAtomEnum.*;

/**
 * Created on 12/14/2017.
 */
public class Mechanic extends Citizen {

    public static final int MECH_VEHICLE = 1932016;

    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int MECHANIC_DASH = 30001068;
    public static final int HIDDEN_PEACE = 30000227;

    public static final int HUMANOID_MECH = 35001002; //Mech Suit
    public static final int ROCKET_BOOSTER = 35001006;
    public static final int TANK_MECH = 35111003; //Tank Mech Suit

    public static final int MECHANIC_RAGE = 35101006; //Buff
    public static final int PERFECT_ARMOR = 35101007; //Buff (ON/OFF)
    public static final int OPEN_PORTAL_GX9 = 35101005; //Special Skill
    public static final int ROBO_LAUNCHER_RM7 = 35101012; //Summon
    public static final int HOMING_BEACON = 35101002;

    public static final int ROCK_N_SHOCK = 35111002; //Special Summon
    public static final int ROLL_OF_THE_DICE = 35111013; //Special Buff
    public static final int SUPPORT_UNIT_HEX = 35111008; //Summon
    public static final int ADV_HOMING_BEACON = 35110017;

    public static final int BOTS_N_TOTS = 35121009; //Special Summon
    public static final int BOTS_N_TOTS_SUB_SUMMON = 35121011; // Summon that spawn from the main BotsNtots
    public static final int MAPLE_WARRIOR_MECH = 35121007; //Buff
    public static final int ENHANCED_SUPPORT_UNIT = 35120002;
    public static final int HEROS_WILL_MECH = 35121008;
    public static final int HOMING_BEACON_RESEARCH = 35120017;
    public static final int ROLL_OF_THE_DICE_DD = 35120014; //Special Buff

    public static final int FOR_LIBERTY_MECH = 35121053;
    public static final int FULL_SPREAD = 35121055;
    public static final int DISTORTION_BOMB = 35121052;

    public static final int DOOMSDAY_DEVICE = 400051009;
    public static final int MOBILE_MISSILE_BATTERY = 400051017;
    public static final int FULL_METAL_BARRAGE = 400051041;
    public static final int GIANT_ROBOT_SG_88 = 35121003;


    private static final int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
            MECHANIC_DASH,
            HIDDEN_PEACE,
    };

    private static final int[] homingBeacon = new int[]{
            HOMING_BEACON,
            ADV_HOMING_BEACON,
            HOMING_BEACON_RESEARCH,
    };

    private ScheduledFuture supportUnitTimer;
    private byte gateId = 0;

    public Mechanic(Char chr) {
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
        return JobConstants.isMechanic(id);
    }


    public void healFromSupportUnit(Summon summon) {
        Char summonOwner = summon.getChr();
        if (summonOwner.hasSkill(ENHANCED_SUPPORT_UNIT) || summonOwner.hasSkill(SUPPORT_UNIT_HEX)) {
            SkillInfo si = SkillData.getSkillInfoById(SUPPORT_UNIT_HEX);
            int slv = summonOwner.getSkill(SUPPORT_UNIT_HEX).getCurrentLevel();
            int healrate = si.getValue(hp, slv);
            c.getChr().heal((int) (c.getChr().getMaxHP() * ((double) healrate / 100)));
        }
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case DISTORTION_BOMB: // TODO  AA 38's
                /*
                AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                aa.setAaType((byte) 0);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setFlip(!attackInfo.left);
                chr.getField().spawnAffectedArea(aa);
                */
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void createMicroMissileForceAtoms(int skillId, int slv) {
        if (!chr.hasSkill(skillId)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        Field field = chr.getField();
        ForceAtomEnum fae = INVISIBLE_ATOM;
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        int totalMobCount = si.getValue(mobCount, slv);
        for (int i = 0; i < totalMobCount; i++) {
            Mob mob = Util.getRandomFromCollection(field.getMobs());
            if (mob == null) {
                targetList.add(0);
                continue;
            }
            targetList.add(Util.getRandomFromCollection(field.getMobs()).getObjectId());
        }
        int totalMissileCount = si.getValue(bulletCount, slv);
        for (int i = 0; i < totalMissileCount; i++) {
            int fImpact = new Random().nextInt(36) + 15;
            int sImpact = new Random().nextInt(3) + 5;
            int delay = new Random().nextInt(500) + 2000;
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, sImpact, new Random().nextInt(360), delay, Util.getCurrentTime(), 0, 0, new Position());
            faiList.add(fai);
        }
        ForceAtom fa = new ForceAtom(chr.getId(), fae, targetList, skillId, faiList);
        chr.createForceAtom(fa);
    }

    private void createHumanoidMechRocketForceAtom() { // Humanoid Rockets are spread around
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById((chr.hasSkill(ADV_HOMING_BEACON) ? ADV_HOMING_BEACON : HOMING_BEACON));
        int slv = getHomingBeaconSkill().getCurrentLevel();
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Mob> mobs = field.getMobsInRect(rect);
        if (mobs.size() <= 0) {
            return;
        }
        ForceAtomEnum fae = getHomingBeaconForceAtomEnum();
        for (int i = 0; i < getHomingBeaconBulletCount(); i++) {
            Mob mob = Util.getRandomFromCollection(mobs);
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 30, 25,
                    0, 200 + (i * 2), Util.getCurrentTime(), 1, 0,
                    new Position());
            chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                    true, mob.getObjectId(), HOMING_BEACON, forceAtomInfo, rect, 90, 30,
                    mob.getPosition(), 0, mob.getPosition(), 0));
        }

    }

    private void createTankMechRocketForceAtom() { // Tank Rockets are focused on 1 enemy
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById((chr.hasSkill(ADV_HOMING_BEACON) ? ADV_HOMING_BEACON : HOMING_BEACON));
        int slv = getHomingBeaconSkill().getCurrentLevel();
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        if (field.getMobsInRect(rect).size() <= 0) {
            return;
        }
        Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
        if (field.getBossMobsInRect(rect).size() > 0) {
            mob = Util.getRandomFromCollection(field.getBossMobsInRect(rect));
        }

        ForceAtomEnum fae = getHomingBeaconForceAtomEnum();
        for (int i = 0; i < getHomingBeaconBulletCount(); i++) {
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 30, 25,
                    0, 200, Util.getCurrentTime(), 1, 0,
                    new Position());
            chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                    true, mob.getObjectId(), HOMING_BEACON, forceAtomInfo, rect, 90, 30,
                    mob.getPosition(), 0, mob.getPosition(), 0));
        }
    }

    private ForceAtomEnum getHomingBeaconForceAtomEnum() {
        switch (getHomingBeaconSkill().getSkillId()) {
            case ADV_HOMING_BEACON:
                return MECH_MEGA_ROCKET_1;
            case HOMING_BEACON_RESEARCH:
                return MECH_MEGA_ROCKET_2;
            case HOMING_BEACON:
            default:
                return MECH_ROCKET;
        }
    }

    private Skill getHomingBeaconSkill() {
        Skill skill = null;
        for (int skillId : homingBeacon) {
            if (chr.hasSkill(skillId)) {
                skill = chr.getSkill(skillId);
            }
        }

        return skill;
    }

    private int getHomingBeaconBulletCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int forceAtomCount = 0;
        for (int skillId : homingBeacon) {
            if (chr.hasSkill(skillId)) {
                Skill skill = chr.getSkill(skillId);
                SkillInfo si = SkillData.getSkillInfoById(skillId);
                int slv = skill.getCurrentLevel();
                forceAtomCount += si.getValue(bulletCount, slv);
            }
        }
        if (tsm.getOptByCTSAndSkill(BombTime, FULL_SPREAD) != null) {
            forceAtomCount += chr.hasSkill(FULL_SPREAD) ? SkillData.getSkillInfoById(FULL_SPREAD).getValue(x, chr.getSkill(FULL_SPREAD).getCurrentLevel()) : 0;
        }
        return forceAtomCount;
    }


    private void cancelSupportUnitTimer() {
        if (supportUnitTimer != null && !supportUnitTimer.isDone()) {
            supportUnitTimer.cancel(true);
        }
    }

    private void applySupportUnitDebuffOnMob(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(SUPPORT_UNIT_HEX) || tsm.getOptByCTSAndSkill(IndieSummon, skillId) == null) {
            cancelSupportUnitTimer();
            return;
        }
        Skill skill = chr.getSkill(skillId);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        SkillInfo suhInfo = SkillData.getSkillInfoById(SUPPORT_UNIT_HEX);
        int slv = skill.getCurrentLevel();

        Option o = new Option();
        o.nOption = -suhInfo.getValue(w, chr.getSkill(SUPPORT_UNIT_HEX).getCurrentLevel()); // enhancement doesn't contain the debuff info
        o.rOption = skill.getSkillId();
        o.tOption = 6;
        Field field = chr.getField();
        for (Mob mob : field.getMobs()) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
            mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
        }
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
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case OPEN_PORTAL_GX9:
                int duration = si.getValue(time, slv);
                OpenGate openGate = new OpenGate(chr, chr.getPosition(), chr.getParty(), gateId, duration);
                if (gateId == 0) {
                    gateId = 1;
                } else if (gateId == 1) {
                    gateId = 0;
                }
                openGate.spawnOpenGate(field);
                break;
            case HOMING_BEACON: //4
            case ADV_HOMING_BEACON: // 4thJob upgrade +5 -> 9
                if (tsm.hasStat(Mechanic) && tsm.getOption(Mechanic).nOption <= 0) {
                    createHumanoidMechRocketForceAtom();
                } else if (tsm.hasStat(Mechanic) && tsm.getOption(Mechanic).nOption == 1) {
                    createTankMechRocketForceAtom();
                }
                break;
            case HEROS_WILL_MECH:
                tsm.removeAllDebuffs();
                break;
            case HUMANOID_MECH:
                tsm.putCharacterStatValue(IndieSpeed, skillID, 20, 0);
                tsm.putCharacterStatValue(EMHP, skillID, si.getValue(emhp, slv), 0);
                tsm.putCharacterStatValue(EMMP, skillID, si.getValue(emmp, slv), 0);
                tsm.putCharacterStatValue(EPAD, skillID, si.getValue(epad, slv), 0);
                tsm.putCharacterStatValue(EPDD, skillID, si.getValue(epdd, slv), 0);

                o1.nOption = 0;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Mechanic, o1);
                tsm.sendSetStatPacket();

                tsb.setNOption(MECH_VEHICLE);
                tsb.setROption(skillID + 100);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                break;
            case TANK_MECH:
                tsm.putCharacterStatValue(EMHP, skillID, si.getValue(emhp, slv), 0);
                tsm.putCharacterStatValue(EMMP, skillID, si.getValue(emmp, slv), 0);
                tsm.putCharacterStatValue(EPAD, skillID, si.getValue(epad, slv), 0);
                tsm.putCharacterStatValue(EPDD, skillID, si.getValue(epdd, slv), 0);

                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(Mechanic, o1);
                tsm.sendSetStatPacket();

                tsb.setNOption(MECH_VEHICLE);
                tsb.setROption(skillID + 100);
                tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                break;
            case MECHANIC_RAGE:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case PERFECT_ARMOR:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(PowerGuard, o1);
                }
                break;
            case ROLL_OF_THE_DICE:
                int diceThrow1 = new Random().nextInt(6) + 1;

                chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));

                if (diceThrow1 < 2) {
                    chr.reduceSkillCoolTime(skillID, (1000 * si.getValue(cooltime, slv)) / 2);
                    return;
                }

                o1.nOption = diceThrow1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                tsm.throwDice(diceThrow1);
                tsm.putCharacterStatValue(Dice, o1);
                break;
            case ROLL_OF_THE_DICE_DD:
                diceThrow1 = new Random().nextInt(6) + 1;
                int diceThrow2 = new Random().nextInt(6) + 1;
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
                if (diceThrow1 == 1 && diceThrow2 == 1 && diceThrow3 == 1) {
                    return;
                }
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Dice, o1);
                break;
            case MAPLE_WARRIOR_MECH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case ENHANCED_SUPPORT_UNIT:
                o2.nReason = skillID;
                o2.nValue = si.getValue(z, slv);
                o2.tTerm = 80;
                tsm.putCharacterStatValue(IndieDamR, o2);
                // Fallthrough intended
            case SUPPORT_UNIT_HEX:

                Summon summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setAttackActive(false);
                field.spawnSummon(summon);

                cancelSupportUnitTimer();
                supportUnitTimer = EventManager.addFixedRateEvent(() -> applySupportUnitDebuffOnMob(skillID), 0, si.getValue(x, slv), TimeUnit.SECONDS);
                break;
            case ROBO_LAUNCHER_RM7:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case ROCK_N_SHOCK:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                field.spawnAddSummon(summon);
                List<Summon> rockNShockList = field.getSummons().stream().filter(s -> s.getSkillID() == ROCK_N_SHOCK && s.getChr() == chr).collect(Collectors.toList());
                if (rockNShockList.size() == 3) {
                    field.broadcastPacket(UserPool.teslaTriangle(chr.getId(), rockNShockList));
                    chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                }
                break;
            case BOTS_N_TOTS:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.Summon);
                summon.setAttackActive(false);
                field.spawnSummon(summon);
                break;
            case FOR_LIBERTY_MECH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case FULL_SPREAD:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BombTime, o1);
                break;
            case DOOMSDAY_DEVICE:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setAssistType(AssistType.MultiSkills);
                field.spawnSummon(summon);
                break;
            case MOBILE_MISSILE_BATTERY:
                summon = Summon.getSummonByNoCTS(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                field.spawnSummon(summon);
                createMicroMissileForceAtoms(skillID, slv);
                break;
            case FULL_METAL_BARRAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                o2.nOption = 100;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnorePCounter, o2);
                break;
            case GIANT_ROBOT_SG_88:
                summon = Summon.getSummonByNoCTS(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.AttackCounter);
                summon.setBeforeFirstAttack(true);
                field.spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        if (skillId == ROCK_N_SHOCK) {
            return 0;
        }

        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (supportUnitTimer != null) {
            supportUnitTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.MECHANIC_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.MECHANIC_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.MECHANIC_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item pistol = ItemData.getItemDeepCopy(1492109);
        chr.addItemToInventory(pistol);

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352700)); // Magnum (Secondary)

        Item bullet = ItemData.getItemDeepCopy(2330000);
        bullet.setQuantity(1600);
        chr.addItemToInventory(bullet);
    }
}
