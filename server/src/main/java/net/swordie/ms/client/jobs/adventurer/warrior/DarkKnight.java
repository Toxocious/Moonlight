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
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.LeaveType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class DarkKnight extends Warrior {
    public static final int EVIL_EYE = 1301013;
    public static final int HYPER_BODY = 1301007;
    public static final int IRON_WILL = 1301006;
    public static final int SPEAR_SWEEP = 1301012;
    public static final int WEAPON_BOOSTER_SPEARMAN = 1301004;
    public static final int FINAL_ATTACK_SPEARMAN = 1300002;


    public static final int EVIL_EYE_OF_DOMINATION = 1310013;
    public static final int CROSS_SURGE = 1311015;
    public static final int EVIL_EYE_SHOCK = 1311014;
    public static final int EVIL_EYE_HEX_REINFORCE = 1320045;
    public static final int EVIL_EYE_AURA_REINFORCE = 1320044;
    public static final int HEX_OF_THE_EVIL_EYE = 1310016;
    public static final int LORD_OF_DARKNESS = 1310009;


    public static final int DARK_THIRST = 1321054; //Lv150
    public static final int SACRIFICE = 1321015; //Resets summon
    public static final int MAPLE_WARRIOR_DARK_KNIGHT = 1321000;
    public static final int REVENGE_OF_THE_EVIL_EYE = 1320011;
    public static final int FINAL_PACT_INFO = 1320016;
    public static final int FINAL_PACT = 1320019;
    public static final int FINAL_PACT_REDUCE_TARGET = 1320047;
    public static final int FINAL_PACT_DAMAGE = 1320046;
    public static final int MAGIC_CRASH_DRK = 1321014;
    public static final int HEROS_WILL_DRK = 1321010;
    public static final int GUNGNIR_DESCENT = 1321013;
    public static final int EPIC_ADVENTURE_DRK = 1321053; //Lv200


    // V skills
    public static final int RADIANT_EVIL = 400011054;


    private Summon evilEye;
    private long finalPactTimer;

    public DarkKnight(Char chr) {
        super(chr);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isDarkKnight(id);
    }

    private void spawnEvilEye(int skillID, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        evilEye = Summon.getSummonBy(chr, skillID, slv);
        evilEye.setFlyMob(true);
        evilEye.setMoveAbility(MoveAbility.Fly);
        evilEye.setAssistType(AssistType.Attack);
        field.spawnSummon(evilEye);
    }

    private void removeEvilEye() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStatsBySkill(EVIL_EYE);
        tsm.removeStat(Beholder, true);
        chr.getField().broadcastPacket(Summoned.summonedRemoved(evilEye, LeaveType.ANIMATION));
    }

    public void healByEvilEye() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(EVIL_EYE) && tsm.hasStatBySkillId(EVIL_EYE)) {
            Skill skill = chr.getSkill(EVIL_EYE);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int amountHealed = si.getValue(hp, slv);
            if (chr.hasSkill(EVIL_EYE_AURA_REINFORCE)) {
                amountHealed = (int) (chr.getMaxHP() * 0.1F);
            }
            chr.heal(amountHealed);
        }
    }

    public void giveHexOfTheEvilEyeBuffs() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Skill skill = chr.getSkill(HEX_OF_THE_EVIL_EYE);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        if (tsm.getOptByCTSAndSkill(EPDD, HEX_OF_THE_EVIL_EYE) == null) {
            o1.nOption = si.getValue(epad, slv) + (chr.hasSkill(EVIL_EYE_HEX_REINFORCE) ? 30 : 0);
            o1.rOption = skill.getSkillId();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(EPAD, o1);

            o2.nOption = si.getValue(epdd, slv);
            o2.rOption = skill.getSkillId();
            o2.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(EPDD, o2);

            o3.nReason = skill.getSkillId();
            o3.nValue = si.getValue(indieCr, slv);
            o3.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieCr, o3);

            o4.nOption = si.getValue(acc, slv);
            o4.rOption = skill.getSkillId();
            o4.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(ACC, o4);
            tsm.putCharacterStatValue(EVA, o4);
            tsm.sendSetStatPacket();
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

        if (hasHitMobs) {

            //Lord of Darkness
            lordOfDarkness();
            // Final Pact
            finalPactKillCountLogic(attackInfo);
            //Dark Thirst
            darkThirst();
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case SPEAR_SWEEP:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = 5; // no time given
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if (!mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case EVIL_EYE:
                chr.reduceSkillCoolTime(SACRIFICE, 300); // Sacrifice SkillInfo:  Successful Evil Eye attacks reduce Sacrifice CD by 0.3
                switch (attackInfo.summonSpecialSkillId) {
                    case EVIL_EYE_SHOCK:
                        skill = chr.getSkill(EVIL_EYE_SHOCK);
                        si = SkillData.getSkillInfoById(skill.getSkillId());
                        slv = skill.getCurrentLevel();

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
                        chr.setSkillCooldown(attackInfo.summonSpecialSkillId, chr.getSkillLevel(attackInfo.summonSpecialSkillId));
                        break;
                }
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void darkThirst() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOptByCTSAndSkill(IndiePAD, DARK_THIRST) != null) {
            Skill skill = chr.getSkill(DARK_THIRST);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int heal = si.getValue(x, slv);
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
        }
    }

    private void lordOfDarkness() {
        if (chr.hasSkill(LORD_OF_DARKNESS)) {
            Skill skill = chr.getSkill(LORD_OF_DARKNESS);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                int heal = si.getValue(x, slv);
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
            }
        }
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_SPEARMAN)) {
            skill = chr.getSkill(FINAL_ATTACK_SPEARMAN);
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
            case IRON_WILL:
                o1.nOption = si.getValue(pdd, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DEF, o1);
                break;
            case HYPER_BODY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxHP, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(MaxMP, o2);
                break;
            case CROSS_SURGE:
                int totalHP = c.getChr().getMaxHP();
                int currentHP = c.getChr().getHP();
                o1.nReason = skillID;
                o1.nValue = (int) ((si.getValue(x, slv) * ((double) currentHP) / totalHP));
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nOption = (int) Math.min((0.08 * totalHP - currentHP), si.getValue(z, slv));
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o2);
                break;
            case EVIL_EYE:
                spawnEvilEye(skillID, slv);
                if (chr.hasSkill(EVIL_EYE_OF_DOMINATION)) {
                    o1.nOption = 1;
                    o1.sOption = EVIL_EYE_OF_DOMINATION;
                    o1.ssOption = 0;
                    tsm.putCharacterStatValue(Beholder, o1);
                }
                break;
            case SACRIFICE:
                if (chr.getField().getSummons().stream().anyMatch(s -> s.getChr() == chr && s.getSkillID() == EVIL_EYE)) {
                    removeEvilEye();
                    chr.resetSkillCoolTime(GUNGNIR_DESCENT);

                    o2.nReason = skillID;
                    o2.nValue = si.getValue(x, slv);
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
                    o3.nReason = skillID;
                    o3.nValue = si.getValue(indieBDR, slv);
                    o3.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieBDR, o3);

                    chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(y, slv))));
                }
                break;
            case DARK_THIRST:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
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
                case GUNGNIR_DESCENT:
                    if (tsm.hasStatBySkillId(SACRIFICE) || tsm.hasStat(Reincarnation)) {
                        return 0;
                    }
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == Reincarnation) {
            if (tsm.getOption(Reincarnation).xOption > 0) {
                chr.die();
            }
        } else if (cts == IndieSummon && tsm.hasStat(Beholder) && tsm.getOption(IndieSummon).summon.getSkillID() == EVIL_EYE) {
            tsm.removeStat(Beholder, true);
        }
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);
        //Dark Knight - Revenge of the Evil Eye
        if (chr.hasSkill(REVENGE_OF_THE_EVIL_EYE)) {
            Skill skill = chr.getSkill(REVENGE_OF_THE_EVIL_EYE);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            int heal = si.getValue(x, slv);
            if (chr.getField().getSummons().stream().anyMatch(s -> s.getChr() == chr && s.getSkillID() == EVIL_EYE)
                    && !chr.hasSkillOnCooldown(REVENGE_OF_THE_EVIL_EYE)
                    && Util.succeedProp(proc)) {
                chr.getField().broadcastPacket(Summoned.summonBeholderRevengeAttack(evilEye, hitInfo.templateID));
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
                chr.addSkillCoolTime(REVENGE_OF_THE_EVIL_EYE, si.getValue(cooltime, slv) * 1000);
            }
        }
    }

    public void reviveByFinalPact() {
        if (!chr.hasSkill(FINAL_PACT_INFO) || chr.hasSkillOnCooldown(FINAL_PACT_INFO)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(FINAL_PACT_INFO);
        int slv = chr.getSkillLevel(FINAL_PACT_INFO);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();

        int mobsToDefeat = si.getValue(z, slv);

        chr.heal(chr.getMaxHP());
        chr.healMP(chr.getMaxMP());

        o1.nOption = 1;
        o1.rOption = FINAL_PACT;
        o1.tOption = si.getValue(time, slv);
        o1.xOption = (int) (chr.hasSkill(FINAL_PACT_REDUCE_TARGET) ? mobsToDefeat * 0.7F : mobsToDefeat);
        tsm.putCharacterStatValue(Reincarnation, o1);
        o2.nOption = 1;
        o2.rOption = FINAL_PACT;
        o2.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(NotDamaged, o2);
        if (chr.hasSkill(FINAL_PACT_DAMAGE)) { // hyper passive skill boost
            o3.nReason = FINAL_PACT;
            o3.nValue = 30;
            o3.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieDamR, o3);
        }
        tsm.sendSetStatPacket();

        finalPactTimer = Util.getCurrentTimeLong() + (si.getValue(time, slv * 1000));
        chr.addSkillCoolTime(FINAL_PACT_INFO, si.getValue(cooltime, slv) * 1000);
        chr.resetSkillCoolTime(GUNGNIR_DESCENT);
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showFinalPactEffect(FINAL_PACT, slv, 0, true)));
        chr.write(UserPacket.effect(Effect.showFinalPactEffect(FINAL_PACT, slv, 0, true)));
    }

    private void finalPactKillCountLogic(AttackInfo attackInfo) {
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            if (mob.isBoss()) {
                decrementFinalPactKillCount();
            } else if (Arrays.stream(mai.damages).sum() > mob.getHp()) {
                decrementFinalPactKillCount();
            }
        }
    }

    private void decrementFinalPactKillCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(FINAL_PACT_INFO) || !tsm.hasStat(Reincarnation)) {
            return;
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int curKillCount = tsm.getOption(Reincarnation).xOption;
        int durationLeft = (int) ((finalPactTimer - Util.getCurrentTimeLong()) / 1000);
        if (curKillCount > 0) {
            curKillCount--;
        }
        o1.nOption = 1;
        o1.rOption = FINAL_PACT;
        o1.tOption = durationLeft;
        o1.xOption = curKillCount;
        tsm.putCharacterStatValue(Reincarnation, o1, true);
        o2.nOption = 1;
        o2.rOption = FINAL_PACT;
        o2.tOption = durationLeft;
        tsm.putCharacterStatValue(NotDamaged, o2, true);
        if (chr.hasSkill(FINAL_PACT_DAMAGE)) { // hyper passive skill boost
            o3.nReason = FINAL_PACT;
            o3.nValue = 30;
            o3.tTerm = durationLeft;
            tsm.putCharacterStatValue(IndieDamR, o3, true);
        }
        tsm.sendSetStatPacket();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.DRAGON_KNIGHT.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.DARK_KNIGHT.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {

        super.cancelTimers();
    }
}


