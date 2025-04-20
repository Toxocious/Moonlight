package net.swordie.ms.client.jobs.adventurer.warrior;

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
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Hero extends Warrior {
    public static final int RAGE = 1101006;
    public static final int WEAPON_BOOSTER_FIGHTER = 1101004;
    public static final int COMBO_ATTACK = 1101013;
    public static final int FINAL_ATTACK_FIGHTER = 1100002;
    public static final int COMBO_FURY = 1101012;
    public static final int COMBO_FURY_DOWN = 1100012;


    public static final int COMBO_SYNERGY = 1110013;
    public static final int SELF_RECOVERY = 1110000;
    public static final int PANIC = 1111003;
    public static final int SHOUT = 1111008;
    public static final int SHOUT_DOWN = 1111014;


    public static final int ADVANCED_FINAL_ATTACK = 1120013;
    public static final int PUNCTURE = 1121015;
    public static final int MAGIC_CRASH_HERO = 1121016;
    public static final int HEROS_WILL_HERO = 1121011;
    public static final int ADVANCED_COMBO = 1120003;
    public static final int ENRAGE = 1121010;
    public static final int ADVANCED_COMBO_REINFORCE = 1120043;
    public static final int MAPLE_WARRIOR_HERO = 1121000;
    public static final int CRY_VALHALLA = 1121054; //Lv150
    public static final int EPIC_ADVENTURE_HERO = 1121053; //Lv200


    // V skills
    public static final int BURNING_SOUL_BLADE = 400011001;
    public static final int BURNING_SOUL_BLADE_STATIONARY = 400011002;
    public static final int WORLDREAVER = 400011027;
    public static final int COMBO_INSTINCT = 400011073;
    public static final int COMBO_INSTINCT_SLASH_STRAIGHT = 400011076;
    public static final int COMBO_INSTINCT_SLASH_DOWN = 400011075;
    public static final int COMBO_INSTINCT_SLASH_UP = 400011074;


    private static final List<Integer> comboInstinctAttack = Arrays.asList(
            COMBO_INSTINCT_SLASH_DOWN,
            COMBO_INSTINCT_SLASH_UP,
            COMBO_INSTINCT_SLASH_STRAIGHT
    );
    private long soulBladeTime;
    private ScheduledFuture selfRecoveryTimer;


    public Hero(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            if (selfRecoveryTimer != null && !selfRecoveryTimer.isDone()) {
                selfRecoveryTimer.cancel(true);
            }
            selfRecovery();
        }
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isHero(id);
    }


    private void addCombo() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        if (tsm.hasStat(ComboCounter) && chr.hasSkill(COMBO_ATTACK)) {
            Skill skill = chr.getSkill(COMBO_ATTACK);
            int slv = skill.getCurrentLevel();
            int count = getComboCount();
            if (count < getMaxCombo()) {
                count++;
                if (chr.hasSkill(ADVANCED_COMBO) && !tsm.hasStatBySkillId(COMBO_INSTINCT)) {
                    SkillInfo acsi = SkillData.getSkillInfoById(ADVANCED_COMBO);
                    int acslv = chr.getSkillLevel(ADVANCED_COMBO);
                    if (slv > 0 && Util.succeedProp(acsi.getValue(prop, acslv))) {
                        count++;
                    }
                }
            }
            if (count > getMaxCombo()) {
                count = getMaxCombo();
            }

            o1.nOption = count;
            o1.rOption = skill.getSkillId();
            tsm.putCharacterStatValue(ComboCounter, o1);
            tsm.sendSetStatPacket();
        }
    }

    private void removeCombo(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(ComboCounter)) {
            return;
        }
        Option o = new Option();
        int currentCount = getComboCount();
        int newCount = currentCount - count;
        o.nOption = newCount < 0 ? 0 : newCount;
        o.rOption = COMBO_ATTACK;
        tsm.putCharacterStatValue(ComboCounter, o);
        tsm.sendSetStatPacket();
    }

    private int getComboProp() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = null;
        if (chr.hasSkill(COMBO_SYNERGY)) {
            skill = chr.getSkill(COMBO_SYNERGY);
        } else if (chr.hasSkill(COMBO_ATTACK)) {
            skill = chr.getSkill(COMBO_ATTACK);
        }
        if (skill == null) {
            return 0;
        }
        int proc = SkillData.getSkillInfoById(skill.getSkillId()).getValue(prop, skill.getCurrentLevel());
        if (tsm.hasStatBySkillId(COMBO_INSTINCT)) {
            proc -= 50;
        }
        return proc < 0 ? 0 : proc;
    }

    private int getComboCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(ComboCounter)) {
            return tsm.getOption(ComboCounter).nOption;
        }
        return -1;
    }

    private int getMaxCombo() {
        int num = 0;
        if (chr.hasSkill(COMBO_ATTACK)) {
            num = 6;
        }
        if (chr.hasSkill(ADVANCED_COMBO)) {
            num = 11;
        }
        return num;
    }

    public Skill getComboAttackSkill() {
        Skill skill = null;
        if (chr.hasSkill(ADVANCED_COMBO)) {
            skill = chr.getSkill(ADVANCED_COMBO);
        } else if (chr.hasSkill(COMBO_SYNERGY)) {
            skill = chr.getSkill(COMBO_SYNERGY);
        } else if (chr.hasSkill(COMBO_ATTACK)) {
            skill = chr.getSkill(COMBO_ATTACK);
        }

        return skill;
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

    public Summon getSoulBlade() {
        return chr.getField().getSummons().stream().filter(s -> s.getChr() == chr && s.getSkillID() == BURNING_SOUL_BLADE_STATIONARY).findAny().orElse(null);
    }

    public void changeSoulBlade() {
        if (chr.getField().getSummons().stream().anyMatch(s -> s.getSkillID() == BURNING_SOUL_BLADE_STATIONARY && s.getChr() == chr)) {
            chr.getField().removeSummon(BURNING_SOUL_BLADE_STATIONARY, chr.getId());
            Skill skill = chr.getSkill(BURNING_SOUL_BLADE);
            SkillInfo si = null;
            if (skill != null) {
                si = SkillData.getSkillInfoById(BURNING_SOUL_BLADE);
            }
            Summon summon = Summon.getSummonByNoCTS(chr, BURNING_SOUL_BLADE, si.getCurrentLevel());
            summon.setMoveAbility(MoveAbility.Walk);
            summon.setSummonTerm((int) ((soulBladeTime - Util.getCurrentTimeLong())) / 1000);
            if (summon.getSummonTerm() <= 0) {
                summon.setSummonTerm(1);
            }
            chr.getField().spawnSummon(summon);
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

        if (hasHitMobs) {
            //Combo
            if (tsm.hasStat(ComboCounter) && !isComboIgnoreSkill(attackInfo.skillId)) {
                int comboProp = getComboProp();
                if (Util.succeedProp(comboProp)) {
                    addCombo();
                }
            }
        }
        // Instinctual Combo
        if (tsm.hasStatBySkillId(COMBO_INSTINCT) && (attackInfo.skillId == 1121008 || attackInfo.skillId == 1120017)) { // Raging Blow
            for (int comboInstinctSkillId : comboInstinctAttack) {
                chr.write(UserLocal.userBonusAttackRequest(comboInstinctSkillId));
            }
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case COMBO_FURY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        addCombo();
                        if (!mob.isBoss()) {
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case COMBO_FURY_DOWN:
                if (hasHitMobs) {
                    int mobId = attackInfo.mobAttackInfo.get(0).mobId;
                    Life mob = chr.getField().getLifeByObjectID(mobId);
                    if (mob instanceof Mob) {
                        chr.write(UserPacket.effect(Effect.showHookEffect(attackInfo.skillId, chr.getLevel(), 1, 0,
                                attackInfo.left, mobId, mob.getX(), mob.getY())));
                        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showHookEffect(attackInfo.skillId, chr.getLevel(), 1,
                                0, attackInfo.left, mobId, mob.getX(), mob.getY())));
                    }
                }
                break;
            case PANIC:
                removeCombo(!tsm.hasStat(ComboCostInc) ? 2 : tsm.getOption(ComboCostInc).nOption + 2);
                panicComboCostInc();
                if (hasHitMobs) {
                    int dur = si.getValue(time, slv);

                    o1.nOption = -si.getValue(w, slv);
                    o1.rOption = skill.getSkillId();
                    o1.tOption = dur;
                    o2.nOption = -si.getValue(w, slv);
                    o2.rOption = skill.getSkillId();
                    o2.tOption = dur / 2;

                    o3.nOption = -si.getValue(x, slv);
                    o3.rOption = skill.getSkillId();
                    o3.tOption = dur;
                    o4.nOption = -si.getValue(x, slv);
                    o4.rOption = skill.getSkillId();
                    o4.tOption = dur / 2;
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.PAD, mob.isBoss() ? o2 : o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MAD, mob.isBoss() ? o2 : o1);
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            mts.addStatOptionsAndBroadcast(MobStat.ACC, mob.isBoss() ? o4 : o3);
                            mts.addStatOptionsAndBroadcast(MobStat.Blind, mob.isBoss() ? o4 : o3);
                        }
                    }
                }
                break;
            case SHOUT:
                if (hasHitMobs) {
                    removeCombo(si.getValue(y, slv));
                }
                break;
            case SHOUT_DOWN:
                Skill orig = chr.getSkill(SHOUT);
                slv = orig.getCurrentLevel();
                si = SkillData.getSkillInfoById(SHOUT_DOWN);
                o1.nOption = -10;
                o1.rOption = SHOUT_DOWN;
                o1.tOption = si.getValue(time, slv);

                o2.nOption = 1;
                o2.rOption = SHOUT_DOWN;
                o2.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (mob.isBoss()) {
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                    } else {
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o2);
                    }
                }
                removeCombo(1);
                chr.setSkillCooldown(orig.getSkillId(), slv);
                break;
            case PUNCTURE:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                removeCombo(si.getValue(y, slv));
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();

                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case WORLDREAVER:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    long totalDmg = Arrays.stream(mai.damages).sum();
                    if (mob == null || totalDmg < mob.getHp()) {
                        continue;
                    }
                    chr.getField().broadcastPacket(MobPool.specialSelectedEffectBySkill(mob, skillID, chr.getId()));

                }
                removeCombo(si.getValue(y, slv));
                o1.nValue = si.getValue(y, slv) * 10; // 6 combo orbs * 10% FD
                o1.nReason = attackInfo.skillId;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                tsm.sendSetStatPacket();
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void panicComboCostInc() {
        if (!chr.hasSkill(PANIC)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(PANIC);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        int count = 1;
        if (tsm.hasStat(ComboCostInc)) {
            count = tsm.getOption(ComboCostInc).nOption;
            if (count < 8) {
                count++;
            }
        }
        o.nOption = count;
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(subTime, slv);
        tsm.putCharacterStatValue(ComboCostInc, o);
        tsm.sendSetStatPacket();
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_FIGHTER)) {
            skill = chr.getSkill(FINAL_ATTACK_FIGHTER);

        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK); // Hero Adv FA
        }

        return skill;
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


    private boolean isComboIgnoreSkill(int skillID) {
        return skillID == SHOUT ||
                skillID == SHOUT_DOWN ||
                skillID == PANIC ||
                skillID == COMBO_FURY ||
                skillID == COMBO_FURY_DOWN ||
                skillID == PUNCTURE ||
                skillID == COMBO_INSTINCT_SLASH_DOWN ||
                skillID == COMBO_INSTINCT_SLASH_UP ||
                skillID == COMBO_INSTINCT_SLASH_STRAIGHT;
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
            case RAGE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerGuard, o2);
                break;
            case COMBO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(ComboCounter, o1);
                break;
            case ENRAGE:
                removeCombo(1);
                int fd = si.getValue(x, slv);
                int mobsHit = si.getValue(mobCount, slv);
                o1.nOption = (fd * 100) + mobsHit;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.xOption = fd;
                tsm.putCharacterStatValue(Enrage, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCrDamMin, o2);
                o3.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCr, o3);
                break;
            case CRY_VALHALLA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nOption = 100;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indiePad, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o4);
                break;
            case BURNING_SOUL_BLADE:
                if (field.getSummons().stream().anyMatch(s -> s.getSkillID() == BURNING_SOUL_BLADE_STATIONARY && s.getChr() == chr)) {
                    field.removeSummon(BURNING_SOUL_BLADE_STATIONARY, chr.getId());
                    summon = Summon.getSummonByNoCTS(chr, BURNING_SOUL_BLADE, slv);
                    summon.setMoveAbility(MoveAbility.Walk);
                    summon.setSummonTerm((int) ((soulBladeTime - Util.getCurrentTimeLong())) / 1000);
                    if (summon.getSummonTerm() <= 0) {
                        summon.setSummonTerm(1);
                    }
                    field.spawnSummon(summon);
                } else if (field.getSummons().stream().anyMatch(s -> s.getSkillID() == BURNING_SOUL_BLADE && s.getChr() == chr)) {
                    field.removeSummon(BURNING_SOUL_BLADE, chr.getId());
                    summon = Summon.getSummonByNoCTS(chr, BURNING_SOUL_BLADE_STATIONARY, slv);
                    summon.setMoveAbility(MoveAbility.Stop);
                    summon.setSummonTerm((int) ((soulBladeTime - Util.getCurrentTimeLong())) / 1000);
                    if (summon.getSummonTerm() <= 0) {
                        summon.setSummonTerm(1);
                    }
                    field.spawnSummon(summon);
                } else {
                    soulBladeTime = Util.getCurrentTimeLong() + 1000 * si.getValue(time, slv);
                    summon = Summon.getSummonBy(chr, BURNING_SOUL_BLADE, slv);
                    summon.setMoveAbility(MoveAbility.Walk);

                    field.spawnSummon(summon);
                }
                break;
            case COMBO_INSTINCT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ComboInstinct, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    private void selfRecovery() {
        if (chr != null && chr.getField() != null && chr.hasSkill(SELF_RECOVERY)) {
            Skill skill = chr.getSkill(SELF_RECOVERY);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int healHP = si.getValue(hp, slv);
            int healMP = si.getValue(mp, slv);
            if (!chr.isDead()) {
                chr.heal(healHP, true);
                chr.healMP(healMP);
            }
        }
        selfRecoveryTimer = EventManager.addEvent(this::selfRecovery, 6, TimeUnit.SECONDS);
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
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleRemoveCTS(cts);

        //Hero - Combo Synergy
        if (chr.hasSkill(COMBO_SYNERGY)) {
            SkillInfo csi = SkillData.getSkillInfoById(COMBO_SYNERGY);
            int slv = csi.getCurrentLevel();
            int comboprop = csi.getValue(subProp, slv);
            if (Util.succeedProp(comboprop)) {
                addCombo();
            }
        }
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
//                handleJobAdvance(JobConstants.JobEnum.CRUSADER.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.HERO.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {

        if (selfRecoveryTimer != null) {
            selfRecoveryTimer.cancel(false);
        }
        super.cancelTimers();
    }
}
