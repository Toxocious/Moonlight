package net.swordie.ms.client.jobs.legend;

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
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Aran extends Job {
    public static final int COMBO_ABILITY = 21000000;
    public static final int COMBAT_STEP = 20001295;
    public static final int REGAINED_MEMORY = 20000194;
    public static final int RETURN_TO_RIEN = 20001296;

    public static final int POLEARM_BOOSTER = 21001003; //Buff
    public static final int BODY_PRESSURE = 21001008; //Buff (ON/OFF)

    public static final int SNOW_CHARGE = 21101006; //Buff
    public static final int DRAIN = 21101005; //Special Skill (HP Recovery) (ON/OFF)

    public static final int MAHA_BLESSING = 21111012; //Buff
    public static final int ADRENALINE_RUSH = 21110016; //at 1000 combo activated
    public static final int AERO_SWING = 21110026; //Passive that activates when Combo'ing in Air TODO

    public static final int MAPLE_WARRIOR_ARAN = 21121000; //Buff
    public static final int HEROS_WILL_ARAN = 21121008;
    public static final int ADV_COMBO_ABILITY = 21110000;

    public static final int HEROIC_MEMORIES_ARAN = 21121053;
    public static final int ADRENALINE_BURST = 21121058;
    public static final int MAHAS_DOMAIN = 21121068; //AoE Effect
    public static final int MAHAS_DOMAIN_SKILL_USE = 21121057; //AoE Effect

    //Final Attack
    public static final int FINAL_ATTACK = 21100010;
    public static final int ADVANCED_FINAL_ATTACK = 21120012;

    //Attacking Skills:
    public static final int SMASH_WAVE = 21001009;
    public static final int SMASH_WAVE_COMBO = 21000004;

    public static final int SMASH_SWING_1 = 21001010;
    public static final int SMASH_SWING_2 = 21000006;
    public static final int SMASH_SWING_3 = 21000007;
    public static final int SMASH_SWING_2_FINAL_BLOW = 21120025;

    public static final int FINAL_CHARGE = 21101011;
    public static final int FINAL_CHARGE_COMBO = 21100002; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_CHARGE_TILE = 21100018;

    public static final int FINAL_TOSS = 21101016;
    public static final int FINAL_TOSS_COMBO = 21100012;

    public static final int ROLLING_SPIN = 21101017;
    public static final int ROLLING_SPIN_COMBO = 21100013; //Special Attack (Stun Debuff) (Special Skill from Key-Command)

    public static final int GATHERING_HOOK = 21111019;
    public static final int GATHERING_HOOK_COMBO = 21110018;

    public static final int FINAL_BLOW = 21111021;
    public static final int FINAL_BLOW_COMBO = 21110020; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_BLOW_SMASH_SWING_COMBO = 21110028; //Special Attack (Stun Debuff) (Special Skill from Key-Command)
    public static final int FINAL_BLOW_ADRENALINE_SHOCKWAVE = 21110027; //Shockwave after final blow when in Adrenaline Rush

    public static final int JUDGEMENT_DRAW = 21111017;
    public static final int JUDGEMENT_DRAW_COMBO_DOWN = 21110011; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_LEFT = 21110024; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)
    public static final int JUDGEMENT_DRAW_COMBO_RIGHT = 21110025; //Special Attack (Freeze Debuff) (Special Skill from Key-Command)

    public static final int BEYOND_BLADE_1 = 21120022;
    public static final int BEYOND_BLADE_2 = 21121016;
    public static final int BEYOND_BLADE_3 = 21121017;


    // V skill
    public static final int MAHAS_FURY_BUFF = 400011016;
    public static final int MAHAS_FURY_ATTACK = 400011020;
    public static final int MAHAS_CARNAGE = 400011031;
    public static final int MAHAS_CARNAGE_COMBO = 400010030;
    public static final int FENRIR_CRASH = 400010070;
    public static final int FENRIR_CRASH_ADRENALINE = 400010071;

    //Finisher
    public static final int FINISHER_HUNTER_PREY = 21120019;
    public static final int FINISHER_STORM_OF_FEAR = 21120023;

    // Passive Hyper
    public static final int SURGING_ADRENALINE = 21120064;

    private ScheduledFuture mahasFuryTimer;



    public static int getOriginalSkillByID(int skillID) {
        switch (skillID) {
            case SMASH_WAVE_COMBO:
                return SMASH_WAVE;

            case FINAL_BLOW_COMBO:
            case FINAL_BLOW_SMASH_SWING_COMBO:
                return FINAL_BLOW;

        }
        return skillID; // no original skill linked with this one
    }


    private static final int[] addedSkills = new int[]{
            REGAINED_MEMORY,
            RETURN_TO_RIEN,
    };


    private int combo;

    public Aran(Char chr) {
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
        return JobConstants.isAran(id);
    }


    private void giveAdrenalinRushBuff(TemporaryStatManager tsm) {
        Skill skill = chr.getSkill(ADRENALINE_RUSH);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(ADRENALINE_RUSH);
        int slv = skill.getCurrentLevel();

        if (chr.hasSkill(ADRENALINE_RUSH)) {
            Option o = new Option();
            o.nOption = 1;
            o.rOption = ADRENALINE_RUSH;
            o.tOption = si.getValue(time, slv) + (chr.hasSkill(SURGING_ADRENALINE) && chr.getLevel() >= 180 ? 5000 : 0);
            o.cOption = 1;
            tsm.putCharacterStatValue(CharacterTemporaryStat.AdrenalinBoost, o);
            tsm.sendSetStatPacket();
        }
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        if (cts == AdrenalinBoost) {
            setComboCountAfterAdrenaline();
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
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs) {
            if (chr.hasSkill(ADRENALINE_RUSH) && tsm.getOption(CharacterTemporaryStat.ComboAbilityBuff).nOption > 999 && !tsm.hasStat(CharacterTemporaryStat.AdrenalinBoost)) {
                giveAdrenalinRushBuff(tsm);
            }
            incrementComboAbility(tsm, attackInfo);
            aranDrain();
            snowCharge(attackInfo);

            if (chr.hasSkill(DRAIN) && tsm.getOptByCTSAndSkill(CharacterTemporaryStat.AranDrain, DRAIN) != null) {
                chr.heal(chr.getHPPerc(1)); // 1%
            }
        }
        doSwingStudiesAddAttack(tsm);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case FINISHER_HUNTER_PREY:
                int t = si.getValue(subTime, slv);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = t;
                tsm.putCharacterStatValue(CharacterTemporaryStat.AranBoostEndHunt, o1);
                tsm.sendSetStatPacket();
                break;
            case FINAL_CHARGE_COMBO:
                si = SkillData.getSkillInfoById(FINAL_CHARGE);
                slv = chr.getSkillLevel(FINAL_CHARGE);
                o1.nOption = 1;
                o1.rOption = FINAL_CHARGE_COMBO;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                AffectedArea aa = AffectedArea.getPassiveAA(chr, FINAL_CHARGE, (byte) slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getLastRect()));
                if (!attackInfo.left) {
                    aa.setRect(aa.getRect().moveRight());
                }
                chr.getField().spawnAffectedArea(aa);
                break;
            case FINAL_CHARGE_TILE:
                si = SkillData.getSkillInfoById(FINAL_CHARGE);
                slv = chr.getSkillLevel(FINAL_CHARGE);
                o1.nOption = 1;
                o1.rOption = FINAL_CHARGE_COMBO;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
                break;
            case ROLLING_SPIN_COMBO:
                si = SkillData.getSkillInfoById(ROLLING_SPIN);
                slv = chr.getSkillLevel(ROLLING_SPIN);
                o1.nOption = 1;
                o1.rOption = ROLLING_SPIN_COMBO;
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
            case FINAL_BLOW_SMASH_SWING_COMBO:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int prop = 30; //Prop value never given, so I decided upon 30%.
                    int time = 3; //Time value never given, so I decided upon 3 seconds.
                    o1.nOption = 1;
                    o1.rOption = FINAL_CHARGE_COMBO; // Final Blow doens't have a mob stat effect
                    o1.tOption = time;
                    if (Util.succeedProp(prop)) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case JUDGEMENT_DRAW_COMBO_DOWN:
            case JUDGEMENT_DRAW_COMBO_LEFT:
            case JUDGEMENT_DRAW_COMBO_RIGHT:
                si = SkillData.getSkillInfoById(JUDGEMENT_DRAW);
                slv = chr.getSkillLevel(JUDGEMENT_DRAW);
                o1.nOption = 1;
                o1.rOption = getOriginalSkillByID(skillID);
                o1.tOption = si.getValue(hcTime, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if (Util.succeedProp(si.getValue(hcProp, slv))) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                    } else {
                        Skill judgementDrawSkill = chr.getSkill(JUDGEMENT_DRAW);
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, judgementDrawSkill);
                    }
                }
                break;
            case MAHAS_DOMAIN_SKILL_USE:
                SkillInfo mdi = SkillData.getSkillInfoById(MAHAS_DOMAIN);
                aa = AffectedArea.getPassiveAA(chr, MAHAS_DOMAIN, (byte) slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(mdi.getFirstRect()));
                chr.getField().spawnAffectedArea(aa);
                aa.activateTimer(1000, 1000);
                break;
            case MAHAS_CARNAGE_COMBO:
                si = SkillData.getSkillInfoById(MAHAS_CARNAGE);
                slv = (byte) chr.getSkillLevel(MAHAS_CARNAGE);
                int cd = si.getValue(cooltime, slv);
                if (tsm.hasStat(CharacterTemporaryStat.MahasFury)) {
                    cd = (int) ((cd * si.getValue(x, slv)) / 100D);
                }
                chr.addSkillCoolTime(MAHAS_CARNAGE, Util.getCurrentTimeLong() + cd * 1000);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementComboAbility(TemporaryStatManager tsm, AttackInfo attackInfo) {
        Option o = new Option();
        SkillInfo comboInfo = SkillData.getSkillInfoById(COMBO_ABILITY);
        int amount = 1;
        if (!chr.hasSkill(COMBO_ABILITY)) {
            return;
        }
        if (tsm.hasStat(CharacterTemporaryStat.ComboAbilityBuff)) {
            amount = tsm.getOption(CharacterTemporaryStat.ComboAbilityBuff).nOption;
            if (amount < comboInfo.getValue(s2, chr.getSkill(COMBO_ABILITY).getCurrentLevel())) {
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    amount++;
                }
            }

        }
        if (amount >= 50) {

        } else if (amount >= 100) {

        } else if (amount >= 150) {

        }

        //if (chr.hasSkill(ADVA))
        o.nOption = amount;
        o.rOption = COMBO_ABILITY;
        o.tOption = 0;
        tsm.putCharacterStatValue(CharacterTemporaryStat.ComboAbilityBuff, o);
        setCombo(amount);
    }

    public void DecrementComboAbility() {
        Option o = new Option();
        SkillInfo comboInfo = SkillData.getSkillInfoById(COMBO_ABILITY);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int amount = 0;
        if (!chr.hasSkill(COMBO_ABILITY)) {
            return;
        }
        if (tsm.hasStat(CharacterTemporaryStat.ComboAbilityBuff)) {
            amount = tsm.getOption(CharacterTemporaryStat.ComboAbilityBuff).nOption;
            amount -= 10;
        }

        o.nOption = amount;
        o.rOption = COMBO_ABILITY;
        o.tOption = 0;
        tsm.putCharacterStatValue(CharacterTemporaryStat.ComboAbilityBuff, o);
        setCombo(amount);
    }


    private void setComboCountAfterAdrenaline() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 500;
        o.rOption = COMBO_ABILITY;
        o.tOption = 0;
        tsm.putCharacterStatValue(CharacterTemporaryStat.ComboAbilityBuff, o);
        tsm.sendSetStatPacket();
        setCombo(500);
    }

    private void doSwingStudiesAddAttack(TemporaryStatManager tsm) {
        Option o = new Option();
        if (chr.hasSkill(21100015)) {
            o.nOption = 1;
            o.rOption = 21100015;
            o.tOption = 5;
            tsm.putCharacterStatValue(CharacterTemporaryStat.NextAttackEnhance, o);
            tsm.sendSetStatPacket();
        }
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        this.combo = Math.max(0, combo);
        tsm.getOption(CharacterTemporaryStat.ComboAbilityBuff).nOption = getCombo();
        chr.write(WvsContext.modComboResponse(getCombo(), !tsm.hasStat(CharacterTemporaryStat.AdrenalinBoost)));
    }

    public void aranDrain() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(CharacterTemporaryStat.AranDrain)) {
            Skill skill = chr.getSkill(DRAIN);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int healrate = si.getValue(x, slv);
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / healrate)));
        }
    }

    public void snowCharge(AttackInfo attackInfo) {
        if (!chr.hasSkill(SNOW_CHARGE)) {
            return;
        }
        Skill skill = chr.getSkill(SNOW_CHARGE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = chr.getSkill(skill.getSkillId()).getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(y, slv);
        o1.mOption = 1;
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.WeaponCharge, SNOW_CHARGE) != null) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                o1.nOption = (mob.isBoss() ? -(si.getValue(q, slv) / 2) : -si.getValue(q, slv));
                mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
            }
        }
    }

    private void cancelMahasFuryTimer() {
        if (mahasFuryTimer != null && !mahasFuryTimer.isDone()) {
            mahasFuryTimer.cancel(true);
        }
    }

    private void doMahasFuryAttack() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(MAHAS_FURY_BUFF) || !tsm.hasStat(CharacterTemporaryStat.MahasFury)) {
            cancelMahasFuryTimer();
            return;
        }
        chr.write(UserLocal.userBonusAttackRequest(MAHAS_FURY_ATTACK));
    }

    @Override
    public int getFinalAttackSkill() {
        Skill skill = getFinalAtkSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int proc = si.getValue(prop, slv);

            if (Util.succeedProp(proc)) {
                int fas = 0;
                if (chr.hasSkill(FINAL_ATTACK)) {
                    fas = FINAL_ATTACK;
                }
                if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
                    fas = ADVANCED_FINAL_ATTACK;
                }
                return fas;
            }
        }
        return 0;
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK)) {
            skill = chr.getSkill(FINAL_ATTACK);
        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK);
        }
        return skill;
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
        switch (skillID) {
            case ADRENALINE_BURST:
                tsm.getOption(CharacterTemporaryStat.ComboAbilityBuff).nOption = 1000;
                giveAdrenalinRushBuff(tsm);
                tsm.sendSetStatPacket();
                break;
            case HEROS_WILL_ARAN:
                tsm.removeAllDebuffs();
                break;
            case POLEARM_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o1);
                break;
            case BODY_PRESSURE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(CharacterTemporaryStat.BodyPressure, o1);
                }
                break;
            case DRAIN:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(CharacterTemporaryStat.AranDrain, o1);
                }
                break;
            case SNOW_CHARGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.WeaponCharge, o1);
                break;
            case MAHA_BLESSING:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieMAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePAD, o2);
                break;
            case MAPLE_WARRIOR_ARAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_ARAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                var stat = CharacterTemporaryStat.IndieDamR;
                if (chr.getParty() != null) {
                    final List<Char> chrList = chr.getParty().getPartyMembersInField(chr);
                    for (Char pChr : chrList) {
                        var pTSM = pChr.getTemporaryStatManager();
                        if (JobConstants.isHeroJob(pChr.getJob())) {
                            pTSM.putCharacterStatValue(stat, o1);
                            pTSM.sendSetStatPacket();
                        }
                    }
                } else {
                    tsm.putCharacterStatValue(stat, o1);
                }
                break;
            case MAHAS_FURY_BUFF:
                if (chr.getField().getAffectedAreas().stream()
                        .anyMatch(aa -> aa.getSkillID() == MAHAS_DOMAIN
                                && aa.getOwnerID() == chr.getId())) {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.MahasFury, o1);
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(indiePadR, slv);
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePADR, o2);
                    int addedCombo = si.getValue(z, slv);
                    setCombo(getCombo() + addedCombo > 999 ? 999 : getCombo() + addedCombo);
                    AffectedArea mahasDomain = chr.getField().getAffectedAreas().stream()
                            .filter(aa -> aa.getSkillID() == MAHAS_DOMAIN
                                    && aa.getOwnerID() == chr.getId())
                            .findFirst().orElse(null);
                    if (mahasDomain != null) {
                        chr.getField().removeLife(mahasDomain.getObjectId(), true);
                    }
                    cancelMahasFuryTimer();
                    mahasFuryTimer = EventManager.addFixedRateEvent(this::doMahasFuryAttack, 0, si.getValue(x, slv), TimeUnit.SECONDS);
                }
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case MAHAS_CARNAGE:
                SkillInfo si = SkillData.getSkillInfoById(MAHAS_CARNAGE);
                int slv = chr.getSkillLevel(MAHAS_CARNAGE);
                if (tsm.hasStat(CharacterTemporaryStat.MahasFury)) {
                    return (int) ((si.getValue(cooltime, slv) / (100D / si.getValue(x, slv))) * 1000);
                }
        }
        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }


    // Job and Leveling methods
    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
//        CharacterStat cs = chr.getAvatarData().getCharacterStat();
//        cs.setPosMap(914000000);
    }


    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.ARAN_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.ARAN_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.ARAN_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.ARAN_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Add Skills
        chr.addSkill(COMBAT_STEP, 1, 1);
        chr.addSkill(RETURN_TO_RIEN, 1, 1);

        // Medal - Awakened Aran
        Item medal = ItemData.getItemDeepCopy(1142129);
        chr.addItemToInventory(medal);
        chr.addSpToJobByCurrentJob(3);
    }
}
