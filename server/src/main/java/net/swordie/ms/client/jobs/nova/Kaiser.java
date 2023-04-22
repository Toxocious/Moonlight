package net.swordie.ms.client.jobs.nova;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.StopForceAtom;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
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
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Kaiser extends Job {

    public static final int GUARDIANS_RETURN = 60001296;
    public static final int VERTICAL_GRAPPLE = 60001218;
    public static final int TRANSFIGURATION = 60000219; //Morph Gauge (SmashStack)
    public static final int DRAGON_LINK = 60001225;

    public static final int TEMPEST_BLADES_THREE = 61101002;
    public static final int TEMPEST_BLADES_THREE_FF = 61110211;
    public static final int BLAZE_ON = 61101004; //Buff

    public static final int FINAL_FORM_THIRD = 61111008; //Buff 3rd Job
    public static final int STONE_DRAGON = 61111002; //Summon (Speed Debuff)
    public static final int STONE_DRAGON_FINAL_FORM = 61111220; //Summon (Speed Debuff)
    public static final int CURSEBITE = 61111003; //Buff
    public static final int SELF_RECOVERY = 61110006;

    public static final int FINAL_FORM_FOURTH = 61120008; //Buff 4rd Job
    public static final int TEMPEST_BLADES_FIVE = 61120007;
    public static final int TEMPEST_BLADES_FIVE_FF = 61121217;
    public static final int GRAND_ARMOR = 61121009; //Buff
    public static final int NOVA_WARRIOR_KAISER = 61121014; //Buff
    public static final int NOVA_TEMPERANCE_KAISER = 61121015;

    public static final int FINAL_TRANCE = 61121053;
    public static final int KAISERS_MAJESTY = 61121054;

    // V skills
    public static final int NOVA_GUARDIANS = 400011012;
    public static final int NOVA_GUARDIANS_2 = 400011013;
    public static final int NOVA_GUARDIANS_3 = 400011014;

    public static final int BLADEFALL_ATTACK = 400011058;
    public static final int BLADEFALL_ATTACK_FF = 400011059;
    public static final int BLADEFALL_TILE = 400011060;
    public static final int BLADEFALL_TILE_FF = 400011061;

    public static final int DRACO_SURGE_ATTACK = 400011079;
    public static final int DRACO_SURGE_ATTACK_FF = 400011080;
    public static final int DRACO_SURGE_SHOOTOBJ = 400011081;
    public static final int DRACO_SURGE_SHOOTOBJ_FF = 400011082;


    //Attacking Skills
    public static final int DRAGON_SLASH_1 = 61001000; //First Swing
    public static final int DRAGON_SLASH_2 = 61001004; //2nd Swing
    public static final int DRAGON_SLASH_3 = 61001005; //Last Swing
    public static final int DRAGON_SLASH_1_FINAL_FORM = 61120219; //Swing Final Form

    public static final int FLAME_SURGE = 61001101;
    public static final int FLAME_SURGE_FINAL_FORM = 61111215;

    public static final int IMPACT_WAVE = 61101100;
    public static final int IMPACT_WAVE_FINAL_FORM = 61111216;
    public static final int PIERCING_BLAZE = 61101101; //Special Attack (Stun Debuff)
    public static final int PIERCING_BLAZE_FINAL_FORM = 61111217;

    public static final int WING_BEAT = 61111100; //Special Attack (Speed Debuff)
    public static final int WING_BEAT_FINAL_FORM = 61111111;
    public static final int PRESSURE_CHAIN = 61111101; //Special Attack (Stun Debuff)
    public static final int PRESSURE_CHAIN_FINAL_FORM = 61111219;

    public static final int GIGA_WAVE = 61121100; //Special Attack (Speed Debuff)
    public static final int GIGA_WAVE_FINAL_FORM = 61121201;
    public static final int GIGA_WAVE_BURDEN = 61120044;
    public static final int INFERNO_BREATH = 61121105;
    public static final int INFERNO_BREATH_FINAL_FORM = 61121222;
    public static final int INFERNO_BREATH_BURN = 61120047;
    public static final int DRAGON_BARRAGE = 61121102;
    public static final int DRAGON_BARRAGE_FINAL_FORM = 61121203;
    public static final int BLADE_BURST = 61121104;
    public static final int BLADE_BURST_FINAL_FORM = 61121221;


    //Realign Skills
    public static final int REALIGN_ATTACKER_MODE = 60001217; //Unlimited Duration
    public static final int REALIGN_DEFENDER_MODE = 60001216; //Unlimited Duration

    public static final int REALIGN_ATTACKER_MODE_I = 61100008;
    public static final int REALIGN_DEFENDER_MODE_I = 61100005;

    public static final int REALIGN_ATTACKER_MODE_II = 61110010;
    public static final int REALIGN_DEFENDER_MODE_II = 61110005;

    public static final int REALIGN_ATTACKER_MODE_III = 61120013;
    public static final int REALIGN_DEFENDER_MODE_III = 61120010;

    private ScheduledFuture selfRecoveryTimer;

    private static final int[] addedSkills = new int[]{
            REALIGN_ATTACKER_MODE,
            REALIGN_DEFENDER_MODE,
            VERTICAL_GRAPPLE,
            TRANSFIGURATION,
            DRAGON_LINK,
            GUARDIANS_RETURN,
    };

    public Kaiser(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (selfRecoveryTimer != null && !selfRecoveryTimer.isDone()) {
                selfRecoveryTimer.cancel(true);
            }
            selfRecovery();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isKaiser(id);
    }

    public void giveRealignAttackBuffs() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int[] realignattacks = new int[]{
                REALIGN_ATTACKER_MODE,
                REALIGN_ATTACKER_MODE_I,
                REALIGN_ATTACKER_MODE_II,
                REALIGN_ATTACKER_MODE_III,
        };
        int zPadX = 0;
        int zCr = 0;
        int zBdR = 0;
        for (int realignattack : realignattacks) {
            if (chr.hasSkill(realignattack)) {
                Skill skill = chr.getSkill(realignattack);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                zPadX += si.getValue(padX, slv);
                zCr += si.getValue(cr, slv);
                zBdR += si.getValue(bdR, slv);
            }
        }
        o1.nOption = zPadX;
        o1.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(PAD, o1);
        o2.nOption = zCr;
        o2.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(CriticalBuff, o2);
        o3.nOption = zBdR;
        o3.rOption = REALIGN_ATTACKER_MODE;
        tsm.putCharacterStatValue(HayatoBoss, o3);
        tsm.sendSetStatPacket();
    }

    public void giveRealignDefendBuffs() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int[] realigndefends = new int[]{
                REALIGN_DEFENDER_MODE,
                REALIGN_DEFENDER_MODE_I,
                REALIGN_DEFENDER_MODE_II,
                REALIGN_DEFENDER_MODE_III,
        };
        int zDef = 0;
        int zAcc = 0;
        int zMHPR = 0;
        for (int realigndefend : realigndefends) {
            if (chr.hasSkill(realigndefend)) {
                Skill skill = chr.getSkill(realigndefend);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                zDef += si.getValue(pddX, slv);
                zAcc += si.getValue(accX, slv);
                zMHPR += si.getValue(mhpR, slv);
            }
        }
        o1.nOption = zDef;
        o1.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(DEF, o1);
        o2.nOption = zAcc;
        o2.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(ACC, o2);
        o3.nOption = zMHPR;
        o3.rOption = REALIGN_DEFENDER_MODE;
        tsm.putCharacterStatValue(HayatoHPR, o3);
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
        if (hasHitMobs) {
            si = SkillData.getSkillInfoById(attackInfo.skillId);
            if (si != null) {
                incrementMorphGauge(tsm, (si.getNormalGauge() * attackInfo.mobAttackInfo.size()));
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case PIERCING_BLAZE:
                SkillInfo pbi = SkillData.getSkillInfoById(PIERCING_BLAZE);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = pbi.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(pbi.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case PIERCING_BLAZE_FINAL_FORM:
                SkillInfo pbffi = SkillData.getSkillInfoById(PIERCING_BLAZE_FINAL_FORM);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = pbffi.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case WING_BEAT:
                SkillInfo wbi = SkillData.getSkillInfoById(WING_BEAT);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(wbi.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case WING_BEAT_FINAL_FORM:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case PRESSURE_CHAIN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 3;
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
                break;
            case PRESSURE_CHAIN_FINAL_FORM:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 3;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case DRACO_SURGE_ATTACK_FF:
                slv = chr.getSkillLevel(DRACO_SURGE_ATTACK);
                chr.setSkillCooldown(DRACO_SURGE_ATTACK, slv);
            case GIGA_WAVE:
            case GIGA_WAVE_FINAL_FORM:
            case DRACO_SURGE_ATTACK:
                o1.nOption = -30;
                o1.rOption = GIGA_WAVE;
                o1.tOption = 4 + (chr.hasSkill(GIGA_WAVE_BURDEN) ? 5 : 0);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case STONE_DRAGON:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(subTime, slv);
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
            case STONE_DRAGON_FINAL_FORM:
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
                        mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    }
                }
                break;
            case INFERNO_BREATH:
            case INFERNO_BREATH_FINAL_FORM:
                SkillInfo rca = SkillData.getSkillInfoById(INFERNO_BREATH);
                for (Position position : attackInfo.positions) {
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setDuration((rca.getValue(cooltime, slv) + (chr.hasSkill(INFERNO_BREATH_BURN) ? 10 : 0)) * 1000);
                    aa.setSkillID(INFERNO_BREATH);
                    aa.setPosition(position);
                    Rect rect = aa.getPosition().getRectAround(rca.getFirstRect());
                    if (!attackInfo.left) {
                        rect = rect.horizontalFlipAround(chr.getPosition().getX());
                    }
                    aa.setRect(rect);
                    aa.setDelay((short) 7); //spawn delay
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void incrementMorphGauge(TemporaryStatManager tsm, int increment) {
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        SkillInfo gaugeInfo = SkillData.getSkillInfoById(TRANSFIGURATION);
        int amount = 1;
        int stage = 0;
        if (chr.hasSkill(TRANSFIGURATION)) {
            amount = tsm.getOption(SmashStack).nOption;
            if (amount <= (getKaiserGauge())) {
                if (amount + increment > getKaiserGauge()) {
                    amount = getKaiserGauge();
                } else {
                    amount = tsm.getOption(SmashStack).nOption + increment;
                }
            }
            if (amount >= gaugeInfo.getValue(s, 1)) {
                stage = 1;
            }
            if (amount >= (gaugeInfo.getValue(v, 1)) - 1) {
                stage = 2;
            }
        }
        o.nOption = amount;
        o.rOption = TRANSFIGURATION + 100;
        tsm.putCharacterStatValue(SmashStack, o);
        o1.nOption = (stage * gaugeInfo.getValue(prop, 1));
        o1.rOption = TRANSFIGURATION + 100;
        tsm.putCharacterStatValue(Stance, o1);
        o2.nOption = (stage * gaugeInfo.getValue(psdJump, 1));
        o2.rOption = TRANSFIGURATION + 100;
        tsm.putCharacterStatValue(Jump, o2);
        o3.nOption = (stage * gaugeInfo.getValue(psdSpeed, 1));
        o3.rOption = TRANSFIGURATION + 100;
        tsm.putCharacterStatValue(Speed, o3);
        o4.nReason = TRANSFIGURATION + 100;
        o4.nValue = (stage * gaugeInfo.getValue(actionSpeed, 1));
        tsm.putCharacterStatValue(IndieBooster, o4); //Indie
        tsm.sendSetStatPacket();
    }

    private void resetGauge() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStatsBySkill(TRANSFIGURATION + 100);
    }

    private int getKaiserGauge() {
        int maxGauge;
        switch (chr.getJob()) {
            case 6100:
                maxGauge = SkillData.getSkillInfoById(TRANSFIGURATION).getValue(s, 1);
                break;
            case 6110:
                maxGauge = SkillData.getSkillInfoById(TRANSFIGURATION).getValue(u, 1);
                break;
            case 6111:
            case 6112:
                maxGauge = SkillData.getSkillInfoById(TRANSFIGURATION).getValue(v, 1);
                break;
            default:
                maxGauge = 0;
        }
        return maxGauge;
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
        Summon summon;
        Field field = chr.getField();
        StopForceAtom stopForceAtom = new StopForceAtom();
        Item item = chr.getEquippedInventory().getItemBySlot(11);
        int weaponID = item.getItemId();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        switch (skillID) {
            case NOVA_TEMPERANCE_KAISER:
                tsm.removeAllDebuffs();
                break;
            case REALIGN_ATTACKER_MODE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    tsm.removeStatsBySkill(REALIGN_DEFENDER_MODE);
                    giveRealignAttackBuffs();
                }
                break;
            case REALIGN_DEFENDER_MODE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    tsm.removeStatsBySkill(REALIGN_ATTACKER_MODE);
                    giveRealignDefendBuffs();
                }
                break;
            case BLAZE_ON:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nValue = si.getValue(indiePad, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case CURSEBITE:
                o1.nOption = si.getValue(asrR, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o1);
                o2.nOption = si.getValue(terR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o2);
                break;
            case GRAND_ARMOR:
                o1.nOption = si.getValue(v, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case NOVA_WARRIOR_KAISER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case TEMPEST_BLADES_THREE:
                if (tsm.getOption(StopForceAtomInfo).nOption != 1 && tsm.hasStat(StopForceAtomInfo)) {
                    tsm.removeStat(StopForceAtomInfo, true);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                List<Integer> angles = Arrays.asList(0, 0, 0);
                stopForceAtom.setCount(3);
                stopForceAtom.setIdx(1);
                stopForceAtom.setWeaponId(weaponID);
                stopForceAtom.setAngleInfo(angles);
                tsm.setStopForceAtom(stopForceAtom);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                chr.addSkillCoolTime(TEMPEST_BLADES_THREE, si.getValue(cooltime, slv) * 1000);
                break;
            case TEMPEST_BLADES_THREE_FF: //Final Form
                if (tsm.getOption(StopForceAtomInfo).nOption != 3 && tsm.hasStat(StopForceAtomInfo)) {
                    tsm.removeStat(StopForceAtomInfo, true);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 3;
                o1.rOption = skillID;
                angles = Arrays.asList(0, 0, 0);
                stopForceAtom.setCount(3);
                stopForceAtom.setIdx(3);
                stopForceAtom.setWeaponId(weaponID);
                stopForceAtom.setAngleInfo(angles);
                tsm.setStopForceAtom(stopForceAtom);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                si = SkillData.getSkillInfoById(TEMPEST_BLADES_THREE);
                slv = (byte) chr.getSkillLevel(TEMPEST_BLADES_THREE);
                chr.addSkillCoolTime(TEMPEST_BLADES_THREE, si.getValue(cooltime, slv) * 1000);
                break;
            case TEMPEST_BLADES_FIVE:
                if (tsm.getOption(StopForceAtomInfo).nOption != 2 && tsm.hasStat(StopForceAtomInfo)) {
                    tsm.removeStat(StopForceAtomInfo, true);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 2;
                o1.rOption = skillID;
                angles = Arrays.asList(0, 0, 0, 0, 0);
                stopForceAtom.setCount(5);
                stopForceAtom.setIdx(2);
                stopForceAtom.setWeaponId(weaponID);
                stopForceAtom.setAngleInfo(angles);
                tsm.setStopForceAtom(stopForceAtom);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                chr.addSkillCoolTime(TEMPEST_BLADES_FIVE, si.getValue(cooltime, slv) * 1000);
                chr.addSkillCoolTime(TEMPEST_BLADES_THREE, si.getValue(cooltime, slv) * 1000);
                break;
            case TEMPEST_BLADES_FIVE_FF: //Final Form
                if (tsm.getOption(StopForceAtomInfo).nOption != 4 && tsm.hasStat(StopForceAtomInfo)) {
                    tsm.removeStat(StopForceAtomInfo, true);
                    tsm.sendResetStatPacket();
                }
                o1.nOption = 4;
                o1.rOption = skillID;
                angles = Arrays.asList(0, 0, 0, 0, 0);
                stopForceAtom.setCount(5);
                stopForceAtom.setIdx(4);
                stopForceAtom.setWeaponId(weaponID);
                stopForceAtom.setAngleInfo(angles);
                tsm.setStopForceAtom(stopForceAtom);
                tsm.putCharacterStatValue(StopForceAtomInfo, o1);
                si = SkillData.getSkillInfoById(TEMPEST_BLADES_FIVE);
                slv = (byte) chr.getSkillLevel(TEMPEST_BLADES_FIVE);
                chr.addSkillCoolTime(TEMPEST_BLADES_FIVE, si.getValue(cooltime, slv) * 1000);
                chr.addSkillCoolTime(TEMPEST_BLADES_THREE, si.getValue(cooltime, slv) * 1000);
                break;
            case FINAL_FORM_THIRD:
            case FINAL_TRANCE:
            case FINAL_FORM_FOURTH:
                si = SkillData.getSkillInfoById(FINAL_FORM_THIRD);
                slv = (byte) chr.getSkillLevel(FINAL_FORM_THIRD);
                if (tsm.hasStat(StopForceAtomInfo)) {
                    tsm.removeStat(StopForceAtomInfo, true);
                    tsm.sendResetStatPacket();
                }
                o6.nOption = skillID == FINAL_FORM_THIRD ? 1200 : 1211;
                o6.rOption = skillID;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Morph, o6);
                o1.nValue = skillID == FINAL_FORM_THIRD ? si.getValue(cr, slv) : si.getValue(v, slv);
                o1.nReason = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o2.nReason = skillID;
                o2.nValue = skillID == FINAL_FORM_THIRD ? si.getValue(indiePMdR, slv) : si.getValue(w, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                o3.nOption = si.getValue(jump, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o3);
                o4.nValue = 100;
                o4.nReason = skillID;
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStance, o4);
                o5.nOption = si.getValue(speed, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o5);
                o7.nOption = 100;
                o7.rOption = skillID;
                o7.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreAllCounter, o7);
                tsm.putCharacterStatValue(IgnoreAllImmune, o7);
                resetGauge();
                break;
            case KAISERS_MAJESTY:
                o1.nReason = skillID;
                o1.nValue = -1;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                o3.nOption = 100;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreAllCounter, o3);
                tsm.putCharacterStatValue(IgnoreAllImmune, o3);
                for (int skillId : chr.getSkillCoolTimes().keySet()) {
                    if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset() && SkillData.getSkillInfoById(skillId).getHyper() == 0) {
                        chr.resetSkillCoolTime(skillId);
                    }
                }
                break;
            case STONE_DRAGON:
            case STONE_DRAGON_FINAL_FORM:
                Position position = inPacket.decodePosition();
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setPosition(position);
                summon.setCurFoothold((short) chr.getField().findFootHoldBelow(position).getId());
                field.spawnSummon(summon);
                break;
            case NOVA_GUARDIANS:
                List<Integer> skillList = Arrays.asList(NOVA_GUARDIANS, NOVA_GUARDIANS_2, NOVA_GUARDIANS_3);

                for (int skillId : skillList) {
                    summon = Summon.getSummonBy(c.getChr(), skillId, slv);
                    summon.setFlyMob(false);
                    summon.setMoveAbility(MoveAbility.FixVMove);
                    summon.setAssistType(AssistType.TeleportToMobs);
                    int random = new Random().nextInt(500) - 250;
                    Position position2 = new Position(chr.getPosition().getX() + random, chr.getPosition().getY());
                    summon.setCurFoothold((short) chr.getField().findFootHoldBelow(position2).getId());
                    summon.setPosition(position2);
                    field.spawnSummon(summon);
                }
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void createFlyingSwordForceAtom(InPacket inPacket) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int stopForceAtomNOption = tsm.getOption(StopForceAtomInfo).nOption;
        int skillId = inPacket.decodeInt();
        int targetCount = inPacket.decodeInt();

        int maxCount = 3;
        if (stopForceAtomNOption == 2 || stopForceAtomNOption == 4) {
            maxCount = 5;
        }
        List<ForceAtomInfo> faiList = new ArrayList<>();
        List<Integer> targetList = new ArrayList<>();

        ForceAtomEnum fae = skillId < BLADEFALL_ATTACK ? ForceAtomEnum.KAISER_WEAPON_THROW_1 : ForceAtomEnum.KAISER_V_WEAPON_THROW_1;
        int atomSkillId = TEMPEST_BLADES_THREE;

        switch (stopForceAtomNOption) {
            case 3:
                fae = skillId < BLADEFALL_ATTACK ? ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_1 : ForceAtomEnum.KAISER_V_WEAPON_THROW_MORPH_1;
                atomSkillId = TEMPEST_BLADES_THREE_FF;
                break;
            case 2:
                fae = skillId < BLADEFALL_ATTACK ? ForceAtomEnum.KAISER_WEAPON_THROW_2 : ForceAtomEnum.KAISER_V_WEAPON_THROW_2;
                atomSkillId = TEMPEST_BLADES_FIVE;
                break;
            case 4:
                fae = skillId < BLADEFALL_ATTACK ? ForceAtomEnum.KAISER_WEAPON_THROW_MORPH_2 : ForceAtomEnum.KAISER_V_WEAPON_THROW_MORPH_2;
                atomSkillId = TEMPEST_BLADES_FIVE_FF;
                break;
        }

        for (int i = 0; i < targetCount; i++) {
            targetList.add(inPacket.decodeInt());
            int firstImpact = new Random().nextInt(5) + 20;
            int secondImpact = new Random().nextInt(5) + 25;
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), firstImpact, secondImpact, 0, 600, Util.getCurrentTime(), 0, 0, new Position());
            if (skillId >= BLADEFALL_ATTACK) {
                fai.setDisappearDelay(2000);
            }
            faiList.add(fai);
        }
        for (int i = targetCount; i < maxCount; i++) {
            targetList.add(Util.getRandomFromCollection(targetList));
            int firstImpact = new Random().nextInt(5) + 20;
            int secondImpact = new Random().nextInt(5) + 25;
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), firstImpact, secondImpact, 0, 600, Util.getCurrentTime(), 0, 0, new Position());
            if (skillId >= BLADEFALL_ATTACK) {
                fai.setDisappearDelay(2000);
            }
            faiList.add(fai);
        }

        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                true, targetList, skillId < BLADEFALL_ATTACK ? atomSkillId : skillId, faiList, new Rect(), 0, 300,
                new Position(), skillId < BLADEFALL_ATTACK ? atomSkillId : skillId, new Position(), 0));
        tsm.removeStat(StopForceAtomInfo, true);

        if (skillId == BLADEFALL_ATTACK || skillId == BLADEFALL_ATTACK_FF) {
            chr.setSkillCooldown(BLADEFALL_ATTACK, chr.getSkillLevel(BLADEFALL_ATTACK));
        }
    }

    private void createBladeFallTiles(InPacket inPacket) {
        int skillId = inPacket.decodeInt();
        if (skillId == BLADEFALL_ATTACK || skillId == BLADEFALL_ATTACK_FF) {
            Position position = inPacket.decodePositionInt();
            int option = inPacket.decodeInt();

            SkillInfo si = SkillData.getSkillInfoById(BLADEFALL_ATTACK);
            int slv = (byte) chr.getSkillLevel(BLADEFALL_ATTACK);

            AffectedArea aa = AffectedArea.getPassiveAA(chr, skillId + 2, slv);
            aa.setPosition(position);
            aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
            aa.setDuration(1000);
            aa.setOption(option);
            chr.getField().spawnAffectedArea(aa);
        }
    }

    public void handleForceAtomCollision(int faKey, int skillId, int mobObjId, Position position, InPacket inPacket) {
        if (skillId == 0 && inPacket.getUnreadAmount() > 0) {
            createBladeFallTiles(inPacket);
        }

        super.handleForceAtomCollision(faKey, skillId, mobObjId, position, inPacket);
    }

    private void selfRecovery() {
        if (chr != null && chr.getField() != null && chr.hasSkill(SELF_RECOVERY)) {
            Skill skill = chr.getSkill(SELF_RECOVERY);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int healHP = (int) ((double) (chr.getMaxHP() * si.getValue(x, slv)) / 100F);
            int healMP = (int) ((double) (chr.getMaxMP() * si.getValue(x, slv)) / 100F);

            chr.heal(healHP, true);
            chr.healMP(healMP);
        }
        selfRecoveryTimer = EventManager.addEvent(this::selfRecovery, 8, TimeUnit.SECONDS);
    }

    public void recallNovaGuardians() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        List<Integer> skillList = Arrays.asList(NOVA_GUARDIANS, NOVA_GUARDIANS_2, NOVA_GUARDIANS_3);
        Field field = chr.getField();

        for (int skillId : skillList) {
            Summon summon = Summon.getSummonByNoCTS(c.getChr(), skillId, (byte) chr.getSkillLevel(NOVA_GUARDIANS));
            summon.setFlyMob(false);
            summon.setMoveAbility(MoveAbility.FixVMove);
            summon.setAssistType(AssistType.TeleportToMobs);
            int random = new Random().nextInt(500) - 250;
            Position position2 = new Position(chr.getPosition().getX() + random, chr.getPosition().getY());
            summon.setCurFoothold((short) chr.getField().findFootHoldBelow(position2).getId());
            summon.setPosition(position2);
            summon.setSummonTerm((int) ((tsm.getRemainingTime(IndieSummon, NOVA_GUARDIANS)) / 1000));
            field.spawnSummon(summon);
        }
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (selfRecoveryTimer != null) {
            selfRecoveryTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        /*
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setLevel(10);
        cs.setJob(6100);
        cs.setStr(70);
        Item secondary = ItemData.getItemDeepCopy(1352500);
        secondary.setBagIndex(10);
        chr.getAvatarData().getAvatarLook().getHairEquips().put((byte) 10, secondary.getItemId());
        chr.setSpToCurrentJob(5);
        chr.getEquippedInventory().addItem(secondary);
        */
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        switch (chr.getLevel()) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.KAISER_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.KAISER_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.KAISER_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
        handleJobAdvance(JobConstants.JobEnum.KAISER_1.getJobId());
        chr.addSpToJobByCurrentJob(5);
        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Skills
        // Items
        Item secondary =  ItemData.getItemDeepCopy(1352500); // Nova's Essence (Secondary)
        chr.forceUpdateSecondary(null, secondary);
    }
}