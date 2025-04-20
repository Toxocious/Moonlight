package net.swordie.ms.client.jobs.sengoku;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.Arrays;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Hayato extends Job {

    //Blade Energy
    public static final int QUICK_DRAW = 40011288;
    public static final int NORMAL_STANCE_BONUS = 40011291;
    public static final int QUICK_DRAW_STANCE_BONUS = 40011292;
    public static final int SUMMER_RAIN = 40011289;
    public static final int HITOKIRI_HUNDRED_STRIKE = 40011290;
    public static final int MASTER_OF_BLADES = 40010000;
    public static final int SHIMADA_HEART = 40010067;
    public static final int RETURN_OF_THE_FIVE_PLANETS = 40011227;

    public static final int BATTOUJUTSU_ADVANCE = 41001010; //not sure what this skill does

    public static final int KATANA_BOOSTER = 41101005; //Buff
    public static final int MILITARY_MIGHT = 41101003; //Buff

    public static final int WILLOW_DODGE = 41110006;
    public static final int MERCILESS_BLADE = 41110007;
    public static final int WARRIOR_HEART = 41110009;

    public static final int IRON_SKIN = 41121003; //Buff
    public static final int AKATSUKI_HERO_HAYATO = 41121005; //Buff
    public static final int TORNADO_BLADE = 41121017; //Attack (Stun Debuff)
    public static final int HITOKIRI_STRIKE = 41121002; //Crit% buff
    public static final int EYE_FOR_AN_EYE = 41121015; //  ON/OFF
    public static final int JINSOKU = 41120006;
    public static final int BLOODLETTER = 41120007;
    public static final int SUDDEN_STRIKE = 41121018;
    public static final int AKATSUKI_BLOSSOMS = 41121004;


    public static final int GOD_OF_BLADES = 41121054;
    public static final int PRINCESS_VOW_HAYATO = 41121053;
    public static final int FALCONS_HONOR = 41121052;

    // V skills
    public static final int BATTOUJUTSU_ZANKOU = 400011026;
    public static final int IAIJUTSU_PHANTOM_BLADE = 400011029;
    public static final int BATTOUJUTSU_ULTIMATE_WILL = 400011104;


    //BattouJutsu Linked Skills
    public static final int SURGING_BLADE_BATTOUJUTSU = 41001014;
    public static final int SHOURYUUSEN_BATTOUJUTSU = 41001015;
    public static final int RISING_SLASH_BATTOUJUTSU = 41101014;
    public static final int FALCON_DIVE_BATTOUJUTSU = 41101015;
    public static final int DANKUUSEN_BATTOUJUTSU = 41111018;
    public static final int SWEEPING_SWORD_BATTOUJUTSU = 41111017;
    public static final int TORNADO_BLADE_BATTOUJUTSU = 41121020;
    public static final int SUDDEN_STRIKE_BATTOUJUTSU = 41121021;

    private static final int[] addedSkills = new int[]{
            QUICK_DRAW,
            SUMMER_RAIN,
            MASTER_OF_BLADES,
            SHIMADA_HEART,
            RETURN_OF_THE_FIVE_PLANETS
    };

    private int swordEnergy = 0;

    public int getSwordEnergy() {
        return swordEnergy;
    }

    public void setSwordEnergy(int swordEnergy) {
        this.swordEnergy = swordEnergy;
        chr.write(UserLocal.modHayatoCombo(swordEnergy));
    }

    public Hayato(Char chr) {
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
        return JobConstants.isHayato(id);
    }


    private boolean isInQuickDrawStance() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(HayatoStance) && tsm.getOption(HayatoStance).nOption == 1;
    }

    public void gainSwordEnergyFromGodOfBladesSummon(int swordEnergy) {
        int endingSwordEnergy = swordEnergy > 800 ? 800 : swordEnergy;
        incSwordEnergy(endingSwordEnergy);
        chr.write(UserPacket.effect(Effect.effectFromWZ("Skill/40001.img/skill/400011104/special0", !chr.isLeft(), 0, 0, 0)));
    }

    private void setHayatoStanceBonus() {
        if (chr.hasSkill(QUICK_DRAW)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            tsm.removeStat(HayatoStanceBonus, true);
            Option o = new Option();
            o.nOption = 1;
            o.rOption = isInQuickDrawStance() ? QUICK_DRAW_STANCE_BONUS : NORMAL_STANCE_BONUS;
            tsm.putCharacterStatValue(HayatoStanceBonus, o);
            tsm.sendSetStatPacket();
        }
    }

    private void quickDrawStanceBonus() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStatsBySkill(NORMAL_STANCE_BONUS + 100);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int hayatoBD = 6;
        int hayatoBooster = -1;
        int hayatoCrit = 30;

        if (getSwordEnergy() >= 200) {
            hayatoBD += 0;
            hayatoCrit += 5;
        }
        if (getSwordEnergy() >= 400) {
            hayatoBD += 2;
            hayatoCrit += 5;
        }
        if (getSwordEnergy() >= 700) {
            hayatoBD += 0;
            hayatoCrit += 5;
        }
        if (getSwordEnergy() >= 1000) {
            hayatoBD += 2;
            hayatoCrit += 5;
        }

        //BossDmg
        o1.nOption = hayatoBD;
        o1.rOption = QUICK_DRAW_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(HayatoBoss, o1);

        //Crit Rate
        o2.nOption = hayatoCrit;
        o2.rOption = QUICK_DRAW_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(HayatoCr, o2);

        //Booster
        o3.nOption = hayatoBooster;
        o3.rOption = QUICK_DRAW_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(HayatoBooster, o3);
        tsm.sendSetStatPacket();
    }

    private void normalStanceBonus() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStatsBySkill(QUICK_DRAW_STANCE_BONUS + 100);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        int hayatoPAD = 1;
        int hayatoMHPR = 20;
        int hayatoIED = 9;
        int hayatoStance = 80;

        if (getSwordEnergy() >= 200) {
            hayatoPAD += 1;
            hayatoIED += 4;
        }
        if (getSwordEnergy() >= 400) {
            hayatoPAD += 2;
            hayatoIED += 4;
        }
        if (getSwordEnergy() >= 700) {
            hayatoPAD += 2;
            hayatoIED += 4;
        }
        if (getSwordEnergy() >= 1000) {
            hayatoPAD += 2;
            hayatoIED += 4;
        }

        //PAD
        o1.nOption = hayatoPAD;
        o1.rOption = NORMAL_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(HayatoPAD, o1);

        //MHP
        //MMP
        o2.nOption = hayatoMHPR;
        o2.rOption = NORMAL_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(HayatoHPR, o2);
        tsm.putCharacterStatValue(HayatoMPR, o2);

        //IED
        o3.nOption = hayatoIED;
        o3.rOption = NORMAL_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(IgnoreTargetDEF, o3);

        //Stance
        o4.nOption = hayatoStance;
        o4.rOption = NORMAL_STANCE_BONUS + 100;
        tsm.putCharacterStatValue(Stance, o4);
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
        if (attackInfo.skillId != IAIJUTSU_PHANTOM_BLADE
                && attackInfo.skillId != BATTOUJUTSU_ULTIMATE_WILL) {
            incrementSwordEnergy(attackInfo);
        }

        if (isInQuickDrawStance()) {
            quickDrawStanceBonus();
            quickDrawStunBonus(attackInfo);
        } else {
            normalStanceBonus();
        }

        if (hasHitMobs) {
            applyDOTOnMob(attackInfo);
            warriorHeartHealHP();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case TORNADO_BLADE:
            case TORNADO_BLADE_BATTOUJUTSU:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
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
                break;
            case SUDDEN_STRIKE:
            case SUDDEN_STRIKE_BATTOUJUTSU:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
                }
                break;
            case SUMMER_RAIN:
            case HITOKIRI_HUNDRED_STRIKE:
                if ((tsm.getOptByCTSAndSkill(IndieDamR, SUMMER_RAIN) == null) || (tsm.getOptByCTSAndSkill(IndieDamR, HITOKIRI_HUNDRED_STRIKE) == null)) {
                    o1.nReason = attackInfo.skillId;
                    o1.nValue = 15;
                    o1.tTerm = 120;
                    tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                    tsm.sendSetStatPacket();
                    setSwordEnergy(0);
                }
                break;
            case HITOKIRI_STRIKE:
                if (tsm.getOptByCTSAndSkill(IndieCr, HITOKIRI_STRIKE) == null) {
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(prop, slv);
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieCr, o1);
                    tsm.sendSetStatPacket();
                }
                break;
            case IAIJUTSU_PHANTOM_BLADE:
                if (getSwordEnergy() < 200) {
                    chr.chatMessage("You don't have enough sword energy for this skill.");
                    return;
                }
                incSwordEnergy(-200);
                o1.nOption = (tsm.hasStat(IaijutsuBlade) ? tsm.getOption(IaijutsuBlade).nOption : 0) + 1;
                o1.rOption = skillID;
                o1.tOption = 15;
                if (o1.nOption > 5) {
                    o1.nOption = 5;
                }
                tsm.putCharacterStatValue(IaijutsuBlade, o1);
                o2.nValue = 20 * o1.nOption;
                o2.nReason = skillID;
                o2.tTerm = 15;
                tsm.putCharacterStatValue(IndiePMdR, o2);
                tsm.sendSetStatPacket();
                break;
            case BATTOUJUTSU_ULTIMATE_WILL:
                Summon gobSummon = getGodOfBladesSummon();
                if (gobSummon == null) {
                    return;
                }
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    long totaldmg = Arrays.stream(mai.damages).sum();
                    if (totaldmg >= mob.getHp()) {
                        gobSummon.setCount(gobSummon.getCount() + 8 > 800 ? 800 : gobSummon.getCount() + 8);
                        chr.write(MobPool.specialEffectBySkill(mob, attackInfo.skillId, chr.getId(), (short) 1000));
                    }
                }
                chr.getField().broadcastPacket(Summoned.effect(gobSummon, 3));
                gobSummon.setCount(gobSummon.getCount() + 10 > 800 ? 800 : gobSummon.getCount() + 10);
                break;
            case FALCONS_HONOR:
            case BATTOUJUTSU_ZANKOU:
                incSwordEnergy(si.getValue(x, slv));
                break;
        }

        super.handleAttack(c, attackInfo);
        setHayatoStanceBonus();
    }

    private Summon getGodOfBladesSummon() {
        return chr.getField().getSummonByChrAndSkillId(chr, BATTOUJUTSU_ULTIMATE_WILL);
    }

    private void applyDOTOnMob(AttackInfo attackInfo) {
        if (chr.hasSkill(BLOODLETTER)) {
            Skill skill = chr.getSkill(BLOODLETTER);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        } else if (chr.hasSkill(MERCILESS_BLADE)) {
            Skill skill = chr.getSkill(MERCILESS_BLADE);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(proc)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }
    }

    private void warriorHeartHealHP() {
        if (chr.hasSkill(WARRIOR_HEART)) {
            Skill skill = chr.getSkill(WARRIOR_HEART);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            int healrate = si.getValue(x, slv);
            int healhp = (int) ((chr.getMaxHP() / ((double) 100 / healrate)));

            //Get chance to heal on  #Crit hits
            if (Util.succeedProp(proc)) {
                chr.heal(healhp);
            }
        }
    }

    private void quickDrawStunBonus(AttackInfo attackInfo) {
        Option o = new Option();
        int stunProc = 30;
        if (getSwordEnergy() >= 200) {
            stunProc += 5;
        }
        if (getSwordEnergy() >= 400) {
            stunProc += 5;
        }
        if (getSwordEnergy() >= 700) {
            stunProc += 5;
        }
        if (getSwordEnergy() == 1000) {
            stunProc += 5;
        }

        o.nOption = 1;
        o.rOption = QUICK_DRAW;
        o.tOption = 3;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(stunProc)) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null || mob.isBoss()) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                mts.addStatOptionsAndBroadcast(MobStat.Stun, o);
            }
        }
    }

    private void incrementSwordEnergy(AttackInfo attackInfo) {
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            long totaldmg = Arrays.stream(mai.damages).sum();
            if (totaldmg >= mob.getHp() || mob.isBoss() || isInQuickDrawStance()) {

                //reward BladeEnergy
                if (isInQuickDrawStance()) {
                    incSwordEnergy(2);
                } else {
                    incSwordEnergy(5);
                }
            }
        }
    }

    public void incSwordEnergy(int swordEnergy) {
        setSwordEnergy(getSwordEnergy() + swordEnergy > 1000 ? 1000 : (getSwordEnergy() + swordEnergy < 0 ? 0 : getSwordEnergy() + swordEnergy));
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        switch (skillID) {
            case AKATSUKI_BLOSSOMS:
                tsm.removeAllDebuffs();
                break;
            case QUICK_DRAW:
                int cost = si.getValue(z, slv);
                if (!isInQuickDrawStance()) {
                    if (getSwordEnergy() < cost) {
                        chr.chatMessage(ChatType.SystemNotice, String.format("You need %d sword energy to switch into quick draw stance.", cost));
                        return;
                    } else {
                        incSwordEnergy(-cost);
                    }
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(HayatoStance, o1);
                break;
            case BATTOUJUTSU_ADVANCE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BattoujutsuAdvance, o1);
                o2.nReason = skillID;
                o2.nValue = 8;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;
            case KATANA_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case MILITARY_MIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(y, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o1); //Indie
                o3.nOption = si.getValue(speed, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o3);
                o4.nOption = si.getValue(jump, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o4);
                o5.nOption = si.getValue(padX, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PAD, o5);
                break;
            case AKATSUKI_HERO_HAYATO:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case EYE_FOR_AN_EYE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(EyeForEye, o1);
                }
                break;
            case PRINCESS_VOW_HAYATO:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case GOD_OF_BLADES:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1); //Indie
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case IAIJUTSU_PHANTOM_BLADE:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setAssistType(AssistType.AttackCounter);
                summon.setAvatarLook(chr.getAvatarData().getAvatarLook());
                chr.getField().spawnSummon(summon);
                break;
            case BATTOUJUTSU_ULTIMATE_WILL:
                if (getSwordEnergy() < si.getValue(s, slv)) {
                    chr.chatMessage("You don't have enough sword energy for this skill.");
                    return;
                }
                incSwordEnergy(-si.getValue(s, slv));
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAbility(MoveAbility.Stop);
                chr.getField().spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();
        setHayatoStanceBonus();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        //Dodge
        if (hitInfo.hpDamage <= 0 && hitInfo.mpDamage <= 0) {
            jinsoku();
            incrementWillowDodge();
        }
        super.handleHit(c, inPacket, hitInfo);
    }


    public void incrementWillowDodge() {
        Skill skill = chr.getSkill(WILLOW_DODGE);
        if (skill == null) {
            return;
        }
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(WILLOW_DODGE);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int amount = 1;
        if (tsm.hasStat(WillowDodge)) {
            amount = tsm.getOption(WillowDodge).xOption;
            if (amount < si.getValue(x, slv)) {
                amount++;
            }
        }
        o.nOption = amount * si.getValue(damR, slv);
        o.rOption = WILLOW_DODGE;
        o.tOption = 20;
        o.xOption = amount;
        tsm.putCharacterStatValue(WillowDodge, o);
        tsm.sendSetStatPacket();
    }

    public void jinsoku() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (chr.hasSkill(JINSOKU)) {
            Skill skill = chr.getSkill(JINSOKU);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(t, slv);
            if (Util.succeedProp(proc)) {
                o.nOption = si.getValue(y, slv);
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jinsoku, o);
                tsm.sendSetStatPacket();
            }
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(807100000);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        switch (chr.getLevel()) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.HAYATO_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.HAYATO_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.HAYATO_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
    }
}
