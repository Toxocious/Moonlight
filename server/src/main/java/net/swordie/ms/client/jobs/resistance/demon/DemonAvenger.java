package net.swordie.ms.client.jobs.resistance.demon;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.BaseStat;
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

import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class DemonAvenger extends Demon {
    public static final int STAR_FORCE_CONVERSION = 30010232;
    public static final int HYPER_POTION_MASTERY = 30010231;
    public static final int EXCEED = 30010230;
    public static final int BLOOD_PACT = 30010242;


    public static final int EXCEED_DOUBLE_SLASH_1 = 31011000; //Special Attack
    public static final int EXCEED_DOUBLE_SLASH_2 = 31011004; //Special Attack
    public static final int EXCEED_DOUBLE_SLASH_3 = 31011005; //Special Attack
    public static final int EXCEED_DOUBLE_SLASH_4 = 31011006; //Special Attack
    public static final int EXCEED_DOUBLE_SLASH_PURPLE = 31011007; //Special Attack
    public static final int LIFE_SAP = 31010002; //Passive Life Drain
    public static final int OVERLOAD_RELEASE = 31011001; // Special Buff        //TODO TempStat: ExceedOverload


    public static final int EXCEED_DEMON_STRIKE_1 = 31201000; //Special Attack
    public static final int EXCEED_DEMON_STRIKE_2 = 31201007; //Special Attack
    public static final int EXCEED_DEMON_STRIKE_3 = 31201008; //Special Attack
    public static final int EXCEED_DEMON_STRIKE_4 = 31201009; //Special Attack
    public static final int EXCEED_DEMON_STRIKE_PURPLE = 31201010; //Special Attack
    public static final int BATTLE_PACT_DA = 31201002; //Buff
    public static final int BAT_SWARM = 31201001;


    public static final int EXCEED_LUNAR_SLASH_1 = 31211000; //Special Attack
    public static final int EXCEED_LUNAR_SLASH_2 = 31211007; //Special Attack
    public static final int EXCEED_LUNAR_SLASH_3 = 31211008; //Special Attack
    public static final int EXCEED_LUNAR_SLASH_4 = 31211009; //Special Attack
    public static final int EXCEED_LUNAR_SLASH_PURPLE = 31211010; //Special Attack
    public static final int VITALITY_VEIL = 31211001;
    public static final int SHIELD_CHARGE_RUSH = 31211002;
    public static final int SHIELD_CHARGE = 31211011; //Special Attack (Stun Debuff)
    public static final int ADVANCED_LIFE_SAP = 31210006; //Passive Life Drain
    public static final int PAIN_DAMPENER = 31210005;


    public static final int EXCEED_EXECUTION_1 = 31221000; //Special Attack
    public static final int EXCEED_EXECUTION_2 = 31221009; //Special Attack
    public static final int EXCEED_EXECUTION_3 = 31221010; //Special Attack
    public static final int EXCEED_EXECUTION_4 = 31221011; //Special Attack
    public static final int EXCEED_EXECUTION_PURPLE = 31221012; //Special Attack
    public static final int NETHER_SHIELD = 31221001; //Special Attack
    public static final int NETHER_SHIELD_ATOM = 31221014; //atom
    public static final int NETHER_SLICE = 31221002; // Special Attack (DefDown Debuff)
    public static final int BLOOD_PRISON = 31221003; // Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_DA = 31221008; //Buff
    public static final int INFERNAL_EXCEED = 31220007;
    public static final int DEMONIC_FORTITUDE_DA = 31221053;
    public static final int THOUSAND_SWORDS = 31221052;
    public static final int FORBIDDEN_CONTRACT = 31221054;
    public static final int WARD_EVIL = 31211003; //Buff
    public static final int DIABOLIC_RECOVERY = 31211004; //Buff


    // V skills
    public static final int DEMONIC_FRENZY = 400011010;
    public static final int DEMONIC_FRENZY_AA = 400010010;
    public static final int DEMONIC_BLAST_HOLDDOWN = 400011038;
    public static final int DEMONIC_BLAST_ATTACK_1 = 400011062;
    public static final int DEMONIC_BLAST_ATTACK_2 = 400011063;
    public static final int DEMONIC_BLAST_ATTACK_3 = 400011064;
    public static final int DIMENSIONAL_SWORD_SUMMON = 400011090;
    public static final int DIMENSIONAL_SWORD_ATTACK = 400011102;


    private static final int[] addedSkills = new int[]{
            EXCEED,
            BLOOD_PACT,
            HYPER_POTION_MASTERY,
            STAR_FORCE_CONVERSION,
    };

    private int lastExceedSkill;
    private ScheduledFuture diabolicRecoveryTimer;
    private ScheduledFuture drainHpTimer;


    public DemonAvenger(Char chr) {
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
        return JobConstants.isDemonAvenger(id);
    }


    public void diabolicRecoveryHPRecovery() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(CharacterTemporaryStat.DiabolikRecovery)) {
            if (diabolicRecoveryTimer != null && !diabolicRecoveryTimer.isDone()) {
                diabolicRecoveryTimer.cancel(true);
            }
            return;
        }

        Skill skill = chr.getSkill(DIABOLIC_RECOVERY);
        if (skill != null) {
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int recovery = si.getValue(x, slv);
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / recovery)));
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

    public void sendHpUpdate() {
        // Used for client side damage calculation for DAs
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 3; // Hp -> damage conversion
        o.mOption = chr.getMaxHP();
        tsm.putCharacterStatValue(CharacterTemporaryStat.LifeTidal, o);
        tsm.sendSetStatPacket();
    }

    private void drainHPByDemonicBlast() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(DEMONIC_BLAST_HOLDDOWN) || !tsm.hasStatBySkillId(DEMONIC_BLAST_HOLDDOWN)) {
            if (drainHpTimer != null && !drainHpTimer.isDone()) {
                drainHpTimer.cancel(true);
            }
            return;
        }
        Skill skill = chr.getSkill(DEMONIC_BLAST_HOLDDOWN);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int hpDrained = (int) ((double) (chr.getHP() * si.getValue(x, slv)) / 100D);
        chr.heal(-hpDrained, true);
    }

    public void giveDemonFrenzy() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(DEMONIC_FRENZY) || tsm.getOptByCTSAndSkill(CharacterTemporaryStat.BattlePvPHelenaMark, DEMONIC_FRENZY) == null) {
            return;
        }
        Skill skill = chr.getSkill(DEMONIC_FRENZY);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        if (chr.getCurrentHPPerc() >= (1 + si.getValue(q2, slv)) && Util.succeedProp(55)) { // As it's random
            spillBlood();
            chr.heal(-si.getValue(y, slv), true);

            Option o = new Option();
            Option prevOpt = tsm.getOption(CharacterTemporaryStat.BattlePvPHelenaMark);
            o.nOption = prevOpt.nOption;
            o.rOption = prevOpt.rOption;
            o.wOption = prevOpt.wOption + 1;
            o.xOption = o.wOption / 3 > 30 ? 30 : o.wOption / 3;
            tsm.putCharacterStatValue(CharacterTemporaryStat.BattlePvPHelenaMark, o);
            tsm.sendSetStatPacket();
        }
    }

    private void spillBlood() {
        SkillInfo si = SkillData.getSkillInfoById(DEMONIC_FRENZY_AA);
        int slv = chr.getSkillLevel(DEMONIC_FRENZY);
        AffectedArea aa = AffectedArea.getPassiveAA(chr, DEMONIC_FRENZY_AA, (byte) chr.getSkillLevel(DEMONIC_FRENZY));
        aa.setPosition(chr.getPosition());
        aa.setDuration(si.getValue(time, slv) * 1000);
        aa.setRect(new Position(aa.getPosition().getX(), aa.getPosition().getY() + 50).getRectAround(si.getFirstRect()));
        chr.getField().spawnAffectedArea(aa);
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
        //DA HP Cost System
        applyHpCostForDA(SkillConstants.getActualSkillIDfromSkillID(attackInfo.skillId));

        if (hasHitMobs) {
            //Nether Shield Recreation
            if (attackInfo.skillId == NETHER_SHIELD_ATOM) {
                //recreateNetherShieldForceAtom(attackInfo);
            }

            //Life Sap & Advanced Life Sap
            if (attackInfo.skillId != DEMONIC_FRENZY) {
                lifeSapHealing();
            }
        }
        int hpRecovered = 5;

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case DEMONIC_BLAST_ATTACK_3:
                hpRecovered += 8;
            case DEMONIC_BLAST_ATTACK_2:
                hpRecovered += 7;
            case DEMONIC_BLAST_ATTACK_1:
                if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.HiddenPossession, DEMONIC_FRENZY) != null) {
                    hpRecovered = 100 * hpRecovered;
                }
                chr.heal((int) ((double) (chr.getMaxHP() * hpRecovered) / 100D));
                tsm.removeStatsBySkill(DEMONIC_BLAST_HOLDDOWN);
                break;
            case BLOOD_PRISON:
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
            case SHIELD_CHARGE:
                o1.nOption = 1;
                o1.rOption = SkillConstants.getActualSkillIDfromSkillID(skillID);
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case NETHER_SLICE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptions(MobStat.PDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                }
                break;
            case THOUSAND_SWORDS:
                for (int i = 0; i < 4; i++) {
                    incrementOverloadCount(skillID, tsm);
                }
                break;
            case VITALITY_VEIL:
                int amounthealed = si.getValue(y, slv);
                int healamount = (int) ((chr.getMaxHP()) / ((double) 100 / amounthealed));
                chr.heal(healamount);
                break;

            case EXCEED_DOUBLE_SLASH_1:
            case EXCEED_DOUBLE_SLASH_2:
            case EXCEED_DOUBLE_SLASH_3:
            case EXCEED_DOUBLE_SLASH_4:
            case EXCEED_DOUBLE_SLASH_PURPLE:

            case EXCEED_DEMON_STRIKE_1:
            case EXCEED_DEMON_STRIKE_2:
            case EXCEED_DEMON_STRIKE_3:
            case EXCEED_DEMON_STRIKE_4:
            case EXCEED_DEMON_STRIKE_PURPLE:

            case EXCEED_LUNAR_SLASH_1:
            case EXCEED_LUNAR_SLASH_2:
            case EXCEED_LUNAR_SLASH_3:
            case EXCEED_LUNAR_SLASH_4:
            case EXCEED_LUNAR_SLASH_PURPLE:

            case EXCEED_EXECUTION_1:
            case EXCEED_EXECUTION_2:
            case EXCEED_EXECUTION_3:
            case EXCEED_EXECUTION_4:
            case EXCEED_EXECUTION_PURPLE:
                giveExceedOverload(SkillConstants.getActualSkillIDfromSkillID(attackInfo.skillId));
                incrementOverloadCount(SkillConstants.getActualSkillIDfromSkillID(attackInfo.skillId), tsm);
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    public void changeDimensionalSword() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        if (chr.hasSkill(DIMENSIONAL_SWORD_SUMMON) && tsm.hasStat(CharacterTemporaryStat.IndiePMdR) && chr.getField().getSummons().stream().anyMatch(s -> s.getChr() == chr && s.getSkillID() == DIMENSIONAL_SWORD_SUMMON)) {
            long remainingTime = tsm.getRemainingTime(CharacterTemporaryStat.IndiePMdR, DIMENSIONAL_SWORD_SUMMON);
            chr.getField().removeSummon(DIMENSIONAL_SWORD_SUMMON, chr.getId());
            tsm.removeStatsBySkill(DIMENSIONAL_SWORD_SUMMON);
            o1.nOption = 1;
            o1.rOption = DIMENSIONAL_SWORD_ATTACK;
            o1.tOption = (int) ((remainingTime) / 5);
            o1.setInMillis(true);
            tsm.putCharacterStatValue(CharacterTemporaryStat.DevilishPower, o1);
            tsm.sendSetStatPacket();
        }
    }


    private void createNetherShieldForceAtom() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(NETHER_SHIELD);
        int slv = chr.getSkillLevel(NETHER_SHIELD);
        Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Mob> mobs = field.getMobsInRect(rect);
        if (mobs.size() <= 0) {
            return;
        }
        Mob mob = Util.getRandomFromCollection(mobs);
        int mobID = mob.getObjectId(); //
        ForceAtomEnum fae = ForceAtomEnum.NETHER_SHIELD;
        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 40,
                0, 500, Util.getCurrentTime(), 1, 0,
                new Position(0, -100));
        ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                true, mobID, NETHER_SHIELD_ATOM, forceAtomInfo, new Rect(), 0, 300,
                mob.getPosition(), NETHER_SHIELD_ATOM, mob.getPosition(), 0);
        fa.setMaxRecreationCount(si.getValue(z, slv));
        chr.createForceAtom(fa);
    }

    public void giveExceedOverload(int skillid) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = skillid;
        o.rOption = skillid;
        o.tOption = 8;
        tsm.putCharacterStatValue(CharacterTemporaryStat.ExceedOverload, o);
        tsm.sendSetStatPacket();
    }

    public void incrementOverloadCount(int skillid, TemporaryStatManager tsm) {
        Option o = new Option();
        int amount = 1;
        if (tsm.hasStat(CharacterTemporaryStat.OverloadCount)) {
            amount = tsm.getOption(CharacterTemporaryStat.OverloadCount).nOption;
            if (amount < getMaxExceed()) {
                if (skillid != lastExceedSkill && lastExceedSkill != 0) {
                    amount++;
                }
                amount++;
            }
        }
        amount = amount > getMaxExceed() ? getMaxExceed() : amount;
        lastExceedSkill = skillid;
        o.nOption = amount;
        o.rOption = EXCEED;
        tsm.putCharacterStatValue(CharacterTemporaryStat.OverloadCount, o);
        tsm.sendSetStatPacket();
    }

    private void resetExceed(TemporaryStatManager tsm) {
        tsm.removeStatsBySkill(EXCEED);
        tsm.sendResetStatPacket();
    }

    private int getMaxExceed() {
        int num = 20;
        if (chr.hasSkill(31220044)) { //Hyper Skill Boost [ Reduce Overload ]
            num = 18;
        }
        return num;
    }

    @Override
    public int getFinalAttackSkill() {
        if (chr.hasSkill(INFERNAL_EXCEED)) {
            Skill skill = chr.getSkill(INFERNAL_EXCEED);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                return INFERNAL_EXCEED;
            }
        }
        return 0;
    }

    public void lifeSapHealing() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(LIFE_SAP)) {
            Skill skill = chr.getSkill(LIFE_SAP);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int proc = si.getValue(prop, slv);
            int amounthealed = si.getValue(x, slv);
            if (chr.hasSkill(ADVANCED_LIFE_SAP)) {
                amounthealed = SkillData.getSkillInfoById(ADVANCED_LIFE_SAP).getValue(x, chr.getSkill(ADVANCED_LIFE_SAP).getCurrentLevel());
            }
            if (chr.hasSkill(PAIN_DAMPENER)) {
                amounthealed -= SkillData.getSkillInfoById(PAIN_DAMPENER).getValue(x, chr.getSkill(PAIN_DAMPENER).getCurrentLevel());
            }
            int exceedamount = tsm.getOption(CharacterTemporaryStat.OverloadCount).nOption;
            int exceedpenalty = (int) Math.floor(exceedamount / 5);
            amounthealed -= exceedpenalty;
            if (Util.succeedProp(proc)) {
                int healamount = (int) ((chr.getMaxHP()) / ((double) 100 / amounthealed));
                chr.heal(healamount);
            }
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
        switch (skillID) {
            case NETHER_SHIELD:
                createNetherShieldForceAtom();
                break;
            case OVERLOAD_RELEASE:
                int overloadCount = tsm.getOption(CharacterTemporaryStat.OverloadCount).nOption;
                double overloadRate = (double) overloadCount / getMaxExceed();

                o2.nValue = (int) (overloadRate * si.getValue(indiePMdR, slv));
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o2);

                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Exceed, o3);

                resetExceed(tsm);
                chr.heal((int) (overloadRate * chr.getMaxHP()));
                break;
            case WARD_EVIL:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.DamageReduce, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(z, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieAsrR, o2);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieTerR, o2);
                break;
            case DIABOLIC_RECOVERY:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieMHPR, o1);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.DiabolikRecovery, o2);
                if (diabolicRecoveryTimer != null && !diabolicRecoveryTimer.isDone()) {
                    diabolicRecoveryTimer.cancel(true);
                }
                int duration = si.getValue(w, slv);
                diabolicRecoveryTimer = EventManager.addFixedRateEvent(this::diabolicRecoveryHPRecovery, 0, duration, TimeUnit.SECONDS);
                break;
            case FORBIDDEN_CONTRACT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                break;
            case DEFENDER_OF_THE_DEMON:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setPosition(chr.getPosition());
                field.spawnSummon(summon);
                break;
            case DIMENSIONAL_SWORD_SUMMON:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setPosition(chr.getPosition());
                field.spawnSummon(summon);
                o1.nValue = 1;
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o1);
                break;
            case DEMONIC_FRENZY:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(s, slv); // Dmg Reduction%
                    o1.rOption = skillID;
                    // xOption  is  Final Dmg%
                    tsm.putCharacterStatValue(CharacterTemporaryStat.BattlePvPHelenaMark, o1);
                }
                break;
            case DEMONIC_BLAST_HOLDDOWN:
                o1.nValue = 1;
                o1.nReason = skillID;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieKeyDownTime, o1);
                if (drainHpTimer != null && !drainHpTimer.isDone()) {
                    drainHpTimer.cancel(true);
                }
                drainHpTimer = EventManager.addFixedRateEvent(this::drainHPByDemonicBlast, 0, 1, TimeUnit.SECONDS);
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
                case DemonSlayer.DEMON_CRY:
                    if (tsm.hasStat(CharacterTemporaryStat.InfinityForce)) {
                        return si.getValue(s, slv) * 1000;
                    }
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }

    private void hpRCostDASkills(int skillID) {
        if (skillID == NETHER_SHIELD_ATOM
                || !chr.hasSkill(skillID)
                || skillID == 0
                || skillID == DEFENDER_OF_THE_DEMON
                || skillID == DEFENDER_OF_THE_DEMON_MASTEMA_MARK
                || skillID == DIMENSIONAL_SWORD_SUMMON
                || skillID == DIMENSIONAL_SWORD_ATTACK) {
            return;
        }
        Skill skill = chr.getSkill(SkillConstants.getActualSkillIDfromSkillID(skillID));
        if (skill != null) {
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int hpRCost = si.getValue(hpRCon, slv);
            if (hpRCost > 0) {
                int skillcost = (int) (chr.getMaxHP() / ((double) 100 / hpRCost));
                if (chr.getHP() < skillcost) {
                    return;
                }
                chr.heal(-skillcost);
            }
        }
    }

    private void applyHpCostForDA(int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (skillID == NETHER_SHIELD_ATOM || skillID == 0 || !chr.hasSkill(skillID) || (tsm.getOption(CharacterTemporaryStat.IndieDamR).nReason == FORBIDDEN_CONTRACT)) {
            return;
        }
        Skill skill = chr.getSkill(SkillConstants.getActualSkillIDfromSkillID(skillID));
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int hpRCost = si.getValue(hpRCon, slv);
        if (hpRCost > 0) {
            int skillcost = (int) (chr.getMaxHP() / ((double) 100 / hpRCost));
            if (chr.getHP() < skillcost) {
                return;
            }
            chr.heal(-skillcost);
        }
    }

    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);

        // Demonic Frenzy
        if (hitInfo.hpDamage > 0) {
            if (chr.hasSkill(DEMONIC_FRENZY)) {
                Skill skill = chr.getSkill(DEMONIC_FRENZY);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                hitInfo.hpDamage = (int) (((double) (100 - si.getValue(s, slv)) / 100) * hitInfo.hpDamage);
            }
        }
    }


    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        switch (chr.getLevel()) {
            case 30 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099006);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099007)); // DA Will
            }
            case 60 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099007);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099008)); // DA Authority
            }
            case 100 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099008);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099009)); // DA Extremes
            }
        }
    }

    @Override
    public void cancelTimers() {
        if (diabolicRecoveryTimer != null) {
            diabolicRecoveryTimer.cancel(true);
        }
        if (drainHpTimer != null) {
            drainHpTimer.cancel(true);
        }
        super.cancelTimers();
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item doomScepter = chr.getEquippedInventory().getItemByItemID(1322123); // Doom Scepter - Mace from Demon Start
        chr.consumeItem(doomScepter);

        Item oldPatience = chr.getEquippedInventory().getItemByItemID(1099001); // Old DS Patience
        chr.forceUpdateSecondary(oldPatience, ItemData.getItemDeepCopy(1099006)); // New DA Patience

        Item primaryWeapon = ItemData.getItemDeepCopy(1232001); // Blue Ravager
        int bagIndex = BodyPart.Weapon.getVal();
        primaryWeapon.setBagIndex(bagIndex);
        chr.getEquippedInventory().addItem(primaryWeapon);
        primaryWeapon.updateToChar(chr);
    }
}
