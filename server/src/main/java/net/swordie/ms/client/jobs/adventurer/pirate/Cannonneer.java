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
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
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
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Cannonneer extends Pirate {
    public static final int BLAST_BACK = 5011002; //Special Attack


    public static final int CANNON_BOOSTER = 5301002; //Buff
    public static final int MONKEY_MAGIC = 5301003; //Buff


    public static final int MONKEY_WAVE = 5311002; //Special Attack
    public static final int BARREL_ROULETTE_FA = 5310004;
    public static final int LUCK_OF_THE_DIE = 5311005; //Buff
    public static final int MONKEY_FURY = 5311010; // set DoT
    public static final int BARREL_ROULETTE = 5311004; //Buff


    public static final int LUCK_OF_THE_DIE_DD = 5320007;
    public static final int MONKEY_MALITIA_PERSIST = 5320044; //Summon
    public static final int MONKEY_MALITIA_ENHANCE = 5320045; //Summon
    public static final int NAUTILUS_STRIKE_CANNON = 5321001; //Special Attack / Buff
    public static final int MAPLE_WARRIOR_CANNON = 5321005; //Buff
    public static final int HEROS_WILL_CANNON = 5321006;
    public static final int EPIC_ADVENTURER_CANNON = 5321053;
    public static final int ROLLING_RAINBOW = 5321052;
    public static final int BUCKSHOT = 5321054;
    public static final int MEGA_MONKEY_MAGIC = 5320008;
    public static final int PIRATE_SPIRIT = 5321010; //Buff
    public static final int MONKEY_MALITIA = 5321004; //Summon
    public static final int ANCHOR_AWEIGH = 5321003; //Summon


    // V skills
    public static final int SPECIAL_MONKEY_SIDEKICK_3 = 400051053;
    public static final int SPECIAL_MONKEY_SIDEKICK_2 = 400051052;
    public static final int SPECIAL_MONKEY_SIDEKICK = 400051038;
    public static final int BIG_HUGE_GIGANTIC_ROCKET = 400051008;
    public static final int NUCLEAR_OPTION_TILE = 400051026;
    public static final int NUCLEAR_OPTION_EXPLOSION = 400051025;
    public static final int NUCLEAR_OPTION = 400051024;


    private static final List<Integer> specialMonkeySideKickIds = new ArrayList<Integer>() {{
        add(SPECIAL_MONKEY_SIDEKICK);
        add(SPECIAL_MONKEY_SIDEKICK_2);
        add(SPECIAL_MONKEY_SIDEKICK_3);
    }};


    private ScheduledFuture cannonTimer;

    public Cannonneer(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            incrementCannonOfMassDestruction();
        }
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isCannonShooter(id);
    }

    private void giveBarrelRouletteBuff(int roulette) {   //TODO
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(BARREL_ROULETTE);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        chr.write(UserPacket.effect(Effect.skillAffectedSelect(BARREL_ROULETTE, slv, roulette, false)));
        switch (roulette) {
            case 1: // Extra Attack (Final Attack)
                //Handled, See Final Attack Handler
                break;
            case 2: // Max CritDmg
                o.nValue = si.getValue(s, slv);
                o.nReason = skill.getSkillId();
                o.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(EnrageCrDamMin, o);
                break;
            case 3: // Slow Debuff
                //Handled, See Attack Handler
                break;
            case 4: // DoT
                //Handled, See Attack Handler
                break;
        }
    }

    private void incrementCannonOfMassDestruction() {
        cannonTimer = EventManager.addFixedRateEvent(this::increaseCannonOfMassDestruction, 25, 25, TimeUnit.SECONDS);
    }

    private void increaseCannonOfMassDestruction() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int count = 1;
        if (tsm.hasStat(MassDestructionRockets)) {
            count = tsm.getOption(MassDestructionRockets).nOption;
            if (count < 3) {
                count++;
            }
        }
        updateCannonOfMassDestruction(count);
    }

    private void updateCannonOfMassDestruction(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = count;
        o.rOption = 0;
        tsm.putCharacterStatValue(MassDestructionRockets, o);
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

    private void applyBarrelRouletteDebuffOnMob(AttackInfo attackInfo) {
        if (chr.hasSkill(BARREL_ROULETTE)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Skill skill = chr.getSkill(BARREL_ROULETTE);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            Option o = new Option();
            o.nOption = -20;
            o.rOption = skill.getSkillId();
            o.tOption = si.getValue(v, slv);
            if (tsm.hasStat(Roulette)) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 4) {  //DoT Debuff
                        mts.createAndAddBurnedInfo(chr, skill);
                    } else if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 3) {  //Slow Debuff
                        int slowProc = si.getValue(w, slv);
                        if (Util.succeedProp(slowProc)) {
                            mts.addStatOptionsAndBroadcast(MobStat.Speed, o);
                        }
                    }
                }
            }
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

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();

        // Barrel Roulette
        applyBarrelRouletteDebuffOnMob(attackInfo);
        // Monkey Wave Ignore KeyDown Time
        if (chr.hasSkill(MONKEY_WAVE)) {
            Skill mwskill = chr.getSkill(MONKEY_WAVE);
            SkillInfo mwsi = SkillData.getSkillInfoById(MONKEY_WAVE);
            byte mwslv = (byte) mwskill.getCurrentLevel();
            if (Util.succeedProp(mwsi.getValue(w, mwslv)) && !(tsm.getOption(KeyDownTimeIgnore).nOption > 0) && attackInfo.skillId != 5310008) {
                o1.nOption = 1;
                o1.rOption = 5310008;
                o1.tOption = 15; // doesn't have an assigned skillStat
                tsm.putCharacterStatValue(KeyDownTimeIgnore, o1);
                tsm.sendSetStatPacket();
            }
        }

        switch (attackInfo.skillId) {
            case BLAST_BACK:
                o1.nOption = si.getValue(z, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case MONKEY_WAVE:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                if (tsm.hasStat(KeyDownTimeIgnore) && tsm.getOption(KeyDownTimeIgnore).nOption > 0) {
                    tsm.removeStatsBySkill(5310008);
                    tsm.removeStat(KeyDownTimeIgnore, true);
                    tsm.sendResetStatPacket();
                }
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(subTime, slv);
                tsm.putCharacterStatValue(IncCriticalDam, o2);
                tsm.sendSetStatPacket();
                break;
            case MONKEY_FURY:
                o1.nOption = si.getValue(z, slv);
                o1.rOption = skillID;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            return;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamSkill, o1);
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case NAUTILUS_STRIKE_CANNON:
                chr.reduceSkillCoolTime(LUCK_OF_THE_DIE, (long) (chr.getRemainingCoolTime(LUCK_OF_THE_DIE) * 0.5F));
                chr.reduceSkillCoolTime(LUCK_OF_THE_DIE_DD, (long) (chr.getRemainingCoolTime(LUCK_OF_THE_DIE_DD) * 0.5F));
                chr.reduceSkillCoolTime(BARREL_ROULETTE, (long) (chr.getRemainingCoolTime(BARREL_ROULETTE) * 0.5F));
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(BARREL_ROULETTE);
        if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 1 && Util.succeedProp(si.getValue(z, chr.getSkill(BARREL_ROULETTE).getCurrentLevel()))) {
            return BARREL_ROULETTE_FA;
        }
        return 0;
    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case BIG_HUGE_GIGANTIC_ROCKET:
                updateCannonOfMassDestruction(tsm.hasStat(MassDestructionRockets) ? (tsm.getOption(MassDestructionRockets).nOption - 1) : 0);
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
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case MONKEY_MAGIC:
            case MEGA_MONKEY_MAGIC:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieAcc, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieAllStat, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAllStat, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieEva, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieEVA, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieJump, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieJump, o4);
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieMhp, slv);
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHP, o5);
                o6.nReason = skillID;
                o6.nValue = si.getValue(indieMmp, slv);
                o6.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMP, o6);
                o7.nReason = skillID;
                o7.nValue = si.getValue(indieSpeed, slv);
                o7.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o7);
                break;
            case BARREL_ROULETTE:
                int roulette = new Random().nextInt(4) + 1;
                o1.nOption = roulette;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Roulette, o1);
                giveBarrelRouletteBuff(roulette);
                break;
            case PIRATE_SPIRIT:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o1);
                break;
            case BUCKSHOT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BuckShot, o1);
                break;
            case ROLLING_RAINBOW: //Stationary, Attacks
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setFlip(!chr.isLeft());
                field.spawnSummon(summon);
                break;
            case MONKEY_MALITIA: //Stationary, Attacks
                int[] summons = new int[]{
                        MONKEY_MALITIA,
                        5320011
                };
                for (int summonZ : summons) {
                    summon = Summon.getSummonBy(c.getChr(), summonZ, slv);
                    summon.setFlyMob(false);
                    summon.setMoveAbility(MoveAbility.Stop);
                    summon.setSummonTerm(summon.getSummonTerm() + (chr.hasSkill(MONKEY_MALITIA_PERSIST) ? 10 : 0));
                    if (chr.hasSkill(MONKEY_MALITIA_ENHANCE)) {
                        field.spawnAddSummon(summon);
                    } else {
                        field.spawnSummon(summon);
                    }
                }
                break;
            case ANCHOR_AWEIGH: //Stationary, Pulls mobs
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                Position position = new Position(chr.isLeft() ? chr.getPosition().getX() - 250 : chr.getPosition().getX() + 250, chr.getPosition().getY());
                summon.setCurFoothold((short) chr.getField().findFootHoldBelow(position).getId());
                summon.setPosition(position);
                field.spawnSummon(summon);
                break;
            case SPECIAL_MONKEY_SIDEKICK:
                for (int summonSkillId : specialMonkeySideKickIds) {
                    summon = Summon.getSummonBy(chr, summonSkillId, slv);
                    summon.setFlyMob(false);
                    summon.setMoveAction((byte) 0);
                    summon.setMoveAbility(MoveAbility.SmartFollow);
                    summon.setAssistType(AssistType.SequenceAttack);
                    summon.setLinkedSummonSkillIds(specialMonkeySideKickIds);
                    field.spawnSummon(summon);
                }
                break;
            case NUCLEAR_OPTION:
                o1.nReason = 1;
                o1.nValue = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(NuclearOption, o1); // NuclearOption is moved/removed
                break;
            case NUCLEAR_OPTION_EXPLOSION:
                tsm.removeStatsBySkill(NUCLEAR_OPTION);
                position = inPacket.decodePositionInt();
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(position);
                field.spawnAffectedArea(aa);

                aa = AffectedArea.getPassiveAA(chr, NUCLEAR_OPTION_TILE, slv);
                aa.setPosition(position);
                aa.setRect(aa.getPosition().getRectAround(SkillData.getSkillInfoById(NUCLEAR_OPTION_EXPLOSION).getFirstRect()));
                field.spawnAffectedArea(aa);
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
    }

    @Override
    public void handleJobStart() {


        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
        chr.addItemToInventory(ItemData.getItemDeepCopy(1532000)); // Novice Cannon
    }

    @Override
    public void cancelTimers() {
        if (cannonTimer != null) {
            cannonTimer.cancel(false);
        }
        super.cancelTimers();

    }
}
