package net.swordie.ms.client.jobs.cygnus;

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
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Mihile extends Job {
    public static final int IMPERIAL_RECALL = 50001245;

    public static final int ROYAL_GUARD_STACK_BUFF = 51001005;
    public static final int ROYAL_GUARD = 51001006; //Special Buff/Attack
    public static final int ROYAL_GUARD_2 = 51001007;
    public static final int ROYAL_GUARD_3 = 51001008;
    public static final int ROYAL_GUARD_4 = 51001009;
    public static final int ROYAL_GUARD_5 = 51001010;

    public static final int SWORD_BOOSTER = 51101003; //Buff
    public static final int RALLY = 51101004; //Buff

    public static final int ENDURING_SPIRIT = 51111004; //Buff
    public static final int SOUL_LINK = 51111008; //Special Buff (ON/OFF)
    public static final int MAGIC_CRASH = 51111005; //Special Skill (Debuff Mobs)
    public static final int ADVANCED_ROYAL_GUARD = 51110009; //Upgrade on Royal Guard
    public static final int SELF_RECOVERY = 51110000;

    public static final int ROILING_SOUL = 51121006; //Buff (ON/OFF)
    public static final int FOUR_POINT_ASSAULT = 51121007; //Special Attack (Accuracy Debuff)
    public static final int RADIANT_CROSS = 51121009; //Special Attack (Accuracy Debuff)    Creates an Area of Effect
    public static final int RADIANT_CROSS_SPREAD = 51120057; //Area of Effect,  After Radiant Cross
    public static final int CALL_OF_CYGNUS_MIHILE = 51121005; //Buff
    public static final int SOUL_ASYLUM = 51120003;
    public static final int ENDURING_SPIRIT_STEEL_SKIN = 51120044;
    public static final int ENDURING_SPIRIT_PREPARATION = 51120045;
    public static final int ENDURING_SPIRIT_PERSIST = 51120043;
    public static final int FOUR_POINT_ASSAULT_OPPORTUNITY = 51120050;

    //Final Attack
    public static final int FINAL_ATTACK_MIHILE = 51100002;
    public static final int ADVANCED_FINAL_ATTACK_MIHILE = 51120002;


    public static final int CHARGING_LIGHT = 51121052;
    public static final int QUEEN_OF_TOMORROW = 51121053;
    public static final int SACRED_CUBE = 51121054;

    public static final int SHIELD_OF_LIGHT = 400011011;
    public static final int SWORD_OF_LIGHT = 400011032;
    public static final int SWORD_OF_LIGHT_1 = 400011033;
    public static final int SWORD_OF_LIGHT_2 = 400011034;
    public static final int SWORD_OF_LIGHT_3 = 400011035;
    public static final int SWORD_OF_LIGHT_4 = 400011036;
    public static final int SWORD_OF_LIGHT_5 = 400011037;
    public static final int SWORD_OF_LIGHT_PASSIVE = 400011067;
    public static final int RADIANT_SOUL = 400011083;

    private static final int[] addedSkills = new int[]{

    };

    private ScheduledFuture soulLinkBuffsTimer;
    private ScheduledFuture soulLinkHPRegenTimer;
    private ScheduledFuture selfRecoveryTimer;
    private ScheduledFuture shielfOfLightTimer;

    public Mihile(Char chr) {
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
        return id == JobConstants.JobEnum.NAMELESS_WARDEN.getJobId() || JobConstants.isMihile(id);
    }


    public void giveShieldOfLightBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(RhoAias) || !chr.hasSkill(SHIELD_OF_LIGHT) || chr.getParty() == null) {
            shielfOfLightTimer.cancel(true);
            return;
        }
        Skill skill = chr.getSkill(SHIELD_OF_LIGHT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        Rect rect = chr.getRectAround(si.getFirstRect());

        Set<Char> chrList = chr.getParty().getPartyMembersInSameField(chr);
        for (Char pChr : chrList) {
            TemporaryStatManager pTSM = pChr.getTemporaryStatManager();
            Option o = new Option();

            if (!rect.hasPositionInside(pChr.getPosition())) {
                pTSM.removeStatsBySkill(skill.getSkillId());
                continue;
            }
            o.nOption = si.getValue(x, slv); // dmg reduction
            o.rOption = skill.getSkillId();
            o.tOption = 2;
            o.xOption = chr.getId(); // Mihile Chr Id
            pTSM.putCharacterStatValue(RhoAias, o);
            pTSM.sendSetStatPacket();
        }

    }

    public void hitShieldOfLight() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option prevOption = tsm.getOption(RhoAias);
        Option o1 = new Option();

        o1.nOption = prevOption.nOption; // dmg reduction
        o1.rOption = prevOption.rOption;
        o1.tOption = (int) tsm.getRemainingTime(RhoAias, SHIELD_OF_LIGHT);
        o1.xOption = chr.getId(); // Mihile Chr Id
        o1.cOption = ++prevOption.cOption; // total Hits absorbed
        o1.bOption = --prevOption.bOption; // Shield Hits Left
        o1.wOption = prevOption.wOption; // Shield Stage
        if (o1.bOption <= 0) {
            o1.wOption = ++prevOption.wOption; // Shield Stage
            o1.bOption = getShieldOfLightHitCount();
        }
        if (o1.wOption > 3) {
            tsm.removeStatsBySkill(SHIELD_OF_LIGHT);
            return;
        }
        o1.setInMillis(true);
        tsm.putCharacterStatValue(RhoAias, o1, true);
        tsm.sendSetStatPacket();
    }

    private int getShieldOfLightHitCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int curStage = tsm.hasStat(RhoAias) ? tsm.getOption(RhoAias).wOption : 0;
        SkillInfo si = SkillData.getSkillInfoById(SHIELD_OF_LIGHT);
        int slv = chr.getSkillLevel(SHIELD_OF_LIGHT);
        switch (curStage) {
            case 1:
                return si.getValue(y, slv);
            case 2:
                return si.getValue(w, slv);
            case 3:
                return si.getValue(z, slv);

            default:
                return 0;
        }
    }

    private void giveEndShieldBuff(int totalHitsAbsorbed) {
        if (!chr.hasSkill(SHIELD_OF_LIGHT)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(SHIELD_OF_LIGHT);
        int slv = chr.getSkillLevel(SHIELD_OF_LIGHT);
        int fdGiven = si.getValue(w2, slv) + totalHitsAbsorbed;

        Option o = new Option();
        o.nValue = fdGiven;
        o.nReason = SHIELD_OF_LIGHT;
        o.tTerm = si.getValue(q2, slv);
        tsm.putCharacterStatValue(IndiePMdR, o);
        tsm.sendSetStatPacket();
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (cts) {
            case RhoAias:
                if (tsm.getOption(cts).xOption == chr.getId()) {
                    int totalHitsAbsorbed = tsm.hasStat(RhoAias) ? tsm.getOption(RhoAias).cOption : 0;
                    EventManager.addEvent(() -> giveEndShieldBuff(totalHitsAbsorbed), 40, TimeUnit.MILLISECONDS);
                }
                break;
        }
    }

    private void increaseRoyalGuardStackBuffDuration() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(SWORD_OF_LIGHT);
        int slv = chr.getSkillLevel(SWORD_OF_LIGHT);
        if (tsm.hasStat(RoyalGuardState)) {
            Option prevOption = tsm.getOption(RoyalGuardState);
            Option o1 = new Option();

            o1.nOption = prevOption.nOption;
            o1.xOption = prevOption.xOption;
            o1.bOption = prevOption.bOption;
            o1.rOption = prevOption.rOption;
            o1.tOption = (int) tsm.getRemainingTime(RoyalGuardState, o1.rOption) + (si.getValue(q, slv) * 1000);
            if (o1.tOption > 12000) {
                o1.tOption = 12000;
            }
            o1.setInMillis(true);
            tsm.removeStatsBySkill(ROYAL_GUARD_STACK_BUFF);

            tsm.putCharacterStatValue(RoyalGuardState, o1, true);
            tsm.sendSetStatPacket();
        }
    }

    private void doRoyalGuard() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();

        o1.nOption = 1;
        o1.rOption = ROYAL_GUARD;
        o1.tOption = calcRoyalGuardTime();
        o1.setInMillis(true);
        tsm.putCharacterStatValue(RoyalGuardPrepare, o1);
        tsm.sendSetStatPacket();
    }

    private int calcRoyalGuardTime() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int royalGuardTime = 1450;
        int curStack = (tsm.hasStat(RoyalGuardState) ? tsm.getOption(RoyalGuardState).xOption : 0);
        royalGuardTime -= (curStack * 200);
        if (tsm.hasStatBySkillId(SACRED_CUBE)) {
            royalGuardTime += 500;
        }
        return royalGuardTime;
    }

    private void successfulGuard() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(RoyalGuardPrepare, false);
        giveRoyalGuardBuff();
        giveSoulAsylumBuff();
        giveRoyalGuardStackedBuff();
        doRoyalGuardAttack();
        if (chr.hasSkillOnCooldown(SWORD_OF_LIGHT) && chr.hasSkillOnCooldown(SWORD_OF_LIGHT) && !chr.hasSkillOnCooldown(SWORD_OF_LIGHT_PASSIVE)) {
            chr.write(UserLocal.userBonusAttackRequest(SWORD_OF_LIGHT_PASSIVE));
        }
    }

    private void giveRoyalGuardBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = ROYAL_GUARD;
        o.tOption = 4;
        tsm.putCharacterStatValue(NotDamaged, o);
        tsm.sendSetStatPacket();
    }

    private void giveSoulAsylumBuff() {
        if (chr.hasSkill(SOUL_ASYLUM)) {
            Skill skill = chr.getSkill(SOUL_ASYLUM);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            o.nOption = si.getValue(y, slv);
            o.rOption = skill.getSkillId();
            o.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(ReduceHitDmgOnlyHPR, o);
            tsm.sendSetStatPacket();
        }
    }

    private void giveRoyalGuardStackedBuff() { //TempStat  Shield Attack is Effect
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        int amount = 1;
        if (tsm.hasStat(RoyalGuardState)) {
            amount = tsm.getOption(RoyalGuardState).xOption;
            if (amount < royalGuardMaxCounter()) {
                amount++;
            }
        }
        o.nOption = amount;
        o.xOption = amount;
        o.bOption = 4;
        o.rOption = ROYAL_GUARD_STACK_BUFF;
        o.tOption = 12;
        tsm.putCharacterStatValue(RoyalGuardState, o);
        o1.nOption = getRoyalGuardAttPower();
        o1.rOption = ROYAL_GUARD_STACK_BUFF;
        o1.tOption = 12;
        tsm.putCharacterStatValue(PAD, o1);
        tsm.sendSetStatPacket();
    }

    private int royalGuardMaxCounter() {
        int num = 3;
        if (chr.hasSkill(ROYAL_GUARD)) {
            num = 3;
        }
        if (chr.hasSkill(ADVANCED_ROYAL_GUARD)) {
            num = 5;
        }
        return num;
    }

    private int getRoyalGuardAttPower() {
        int pad = 0;
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOption(RoyalGuardState).xOption == 1) {
            pad = 10;
        }
        if (tsm.getOption(RoyalGuardState).xOption == 2) {
            pad = 15;
        }
        if (tsm.getOption(RoyalGuardState).xOption == 3) {
            pad = 20;
        }
        if (tsm.getOption(RoyalGuardState).xOption == 4) {
            pad = 30;
        }
        if (tsm.getOption(RoyalGuardState).xOption == 5) {
            pad = 45;
        }
        return pad;
    }

    private void giveSoulLinkBuffs() { // gives IndieDamR as well
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr != null && chr.hasSkill(SOUL_LINK) && tsm.hasStat(MichaelSoulLink)) {
            Skill skill = chr.getSkill(SOUL_LINK);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
            if (!chr.isLeft()) {
                rect = rect.horizontalFlipAround(chr.getPosition().getX());
            }
            Party party = chr.getParty();
            int partySize = 0;


            // Party Member's buffs
            if (party != null) {

                for (PartyMember partyMember : party.getOnlineMembers()) {
                    Char partyChr = partyMember.getChr();
                    TemporaryStatManager partyTSM = partyChr.getTemporaryStatManager();
                    int partyChrX = partyChr.getPosition().getX();
                    int partyChrY = partyChr.getPosition().getY();

                    if (partyChr.getId() == chr.getId() || JobConstants.isMihile(partyChr.getJob())) {   // Mihile doesn't get the PartyMember's Buffs
                        continue;
                    }

                    if (partyChrX >= rect.getLeft() && partyChrY >= rect.getTop()       // if  Party Members in Range
                            && partyChrX <= rect.getRight() && partyChrY <= rect.getBottom()) {

                        Option o1 = new Option();
                        Option o2 = new Option();
                        Option o3 = new Option();
                        Option o4 = new Option();

                        o1.nOption = 1;
                        o1.rOption = skill.getSkillId();
                        o1.tOption = 2;
                        o1.cOption = chr.getId(); // Owner of Soul Link (Mihile's chr Id)
                        partyTSM.putCharacterStatValue(MichaelSoulLink, o1);

                        if (chr.getSkill(ROYAL_GUARD) != null && tsm.hasStatBySkillId(ROYAL_GUARD) && !partyTSM.hasStatBySkillId(ROYAL_GUARD)) {
                            o2.nReason = ROYAL_GUARD;
                            o2.nValue = (int) (getRoyalGuardAttPower() * ((double) si.getValue(x, slv) / 100));
                            o2.tTerm = 12;
                            partyTSM.putCharacterStatValue(IndiePAD, o2);
                            partyTSM.putCharacterStatValue(IndieMAD, o2);
                        }
                        if (chr.getSkill(ENDURING_SPIRIT) != null && tsm.hasStatBySkillId(ENDURING_SPIRIT) && !partyTSM.hasStatBySkillId(ENDURING_SPIRIT)) {
                            Skill enduringSpirit = chr.getSkill(ENDURING_SPIRIT);
                            SkillInfo esInfo = SkillData.getSkillInfoById(enduringSpirit.getSkillId());
                            byte esLevel = (byte) enduringSpirit.getCurrentLevel();

                            // Enduring Spirit - DEF
                            o3.nReason = ENDURING_SPIRIT;
                            o3.nValue = (int) (esInfo.getValue(x, esLevel) * ((double) si.getValue(w, slv) / 100));
                            o3.tTerm = esInfo.getValue(time, esLevel);
                            partyTSM.putCharacterStatValue(IndiePDDR, o3);

                            // Enduring Spirit - AsrR
                            o4.nReason = ENDURING_SPIRIT;
                            o4.nValue = (int) (esInfo.getValue(y, esLevel) * ((double) si.getValue(y, slv) / 100));
                            o4.tTerm = esInfo.getValue(time, esLevel);
                            partyTSM.putCharacterStatValue(IndieAsrR, o4);
                        }
                        partyTSM.sendSetStatPacket();
                        partySize++;

                    } else {    // if  Party Members outside Range
                        partyTSM.removeStatsBySkill(SOUL_LINK);
                        partyTSM.removeStatsBySkill(ROYAL_GUARD);
                        partyTSM.removeStatsBySkill(ENDURING_SPIRIT);
                        partyTSM.sendResetStatPacket();
                    }
                }
            }

            // Mihile's Buffs
            Option o5 = new Option();
            o5.nReason = SOUL_LINK + 100; // for invisible Icon
            o5.nValue = si.getValue(indieDamR, slv) * partySize;
            tsm.putCharacterStatValue(IndieDamR, o5);
            tsm.sendSetStatPacket();

            soulLinkBuffsTimer = EventManager.addEvent(this::giveSoulLinkBuffs, 1, TimeUnit.SECONDS);
        }
        tsm.removeStatsBySkill(SOUL_LINK + 100);
        tsm.sendResetStatPacket();
    }

    public void soulLinkHPRegen() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr != null && chr.hasSkill(SOUL_LINK) && tsm.hasStat(MichaelSoulLink)) {
            Skill skill = chr.getSkill(SOUL_LINK);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int delay = si.getValue(dot, slv);
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(s, slv))));
            soulLinkHPRegenTimer = EventManager.addEvent(this::soulLinkHPRegen, delay, TimeUnit.SECONDS);
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case FOUR_POINT_ASSAULT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv) + (chr.hasSkill(FOUR_POINT_ASSAULT_OPPORTUNITY) ? 20 : 0))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    }
                }
                break;
            case RADIANT_CROSS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(y, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.ACC, o1);
                    }
                }
                if (chr.hasSkill(RADIANT_CROSS_SPREAD)) {
                    Field field = chr.getField();
                    SkillInfo rca = SkillData.getSkillInfoById(RADIANT_CROSS_SPREAD);
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setSkillID(RADIANT_CROSS_SPREAD);
                    aa.setPosition(chr.getPosition());
                    Rect rect = aa.getPosition().getRectAround(si.getRects().get(0));
                    if (!chr.isLeft()) {
                        rect = rect.horizontalFlipAround(chr.getPosition().getX());
                    }
                    aa.setRect(rect);
                    aa.setFlip(!attackInfo.left);
                    aa.setDelay((short) 7); //spawn delay
                    field.spawnAffectedAreaAndRemoveOld(aa);
                }
                break;
            case RADIANT_CROSS_SPREAD:
                int dur = si.getValue(time, slv);
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = dur;
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = dur / 2;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, mob.isBoss() ? o2 : o1);
                }
                break;
            case CHARGING_LIGHT:
                o1.nOption = 10; // Because WzFile doesn't have a variable for it.
                o1.rOption = skillID;
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
            case SWORD_OF_LIGHT_1:
            case SWORD_OF_LIGHT_2:
            case SWORD_OF_LIGHT_3:
            case SWORD_OF_LIGHT_4:
            case SWORD_OF_LIGHT_5:
                si = SkillData.getSkillInfoById(SWORD_OF_LIGHT);
                slv = chr.getSkillLevel(SWORD_OF_LIGHT);
                int duration = si.getValue(time, slv);
                o1.nOption = -30;
                o1.rOption = skillID;
                o1.tOption = duration;
                o2.nOption = -30;
                o2.rOption = skillID;
                o2.tOption = duration / 2;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, mob.isBoss() ? o2 : o1);
                }
                increaseRoyalGuardStackBuffDuration();
                chr.addSkillCoolTime(SWORD_OF_LIGHT, SkillData.getSkillInfoById(SWORD_OF_LIGHT).getValue(cooltime, chr.getSkillLevel(SWORD_OF_LIGHT)) * 1000);
                chr.addSkillCoolTime(SWORD_OF_LIGHT_PASSIVE, 5000);
                break;
            case SWORD_OF_LIGHT_PASSIVE:
                o1.nOption = -si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv) / 2;
                o2.nOption = -si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, mob.isBoss() ? o1 : o2);
                }
                chr.addSkillCoolTime(SWORD_OF_LIGHT_PASSIVE, 5000);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void doRoyalGuardAttack() {
        chr.write(UserLocal.royalGuardAttack(true));
    }

    @Override
    public int getFinalAttackSkill() {
        Skill skill = getFinalAtkSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int proc = si.getValue(prop, slv);
            if (Util.succeedProp(proc)) {
                return skill.getSkillId();
            }
        }
        return 0;
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_MIHILE)) {
            skill = chr.getSkill(FINAL_ATTACK_MIHILE);
        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK_MIHILE)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK_MIHILE);
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
        Option o4 = new Option();
        switch (skillID) {
            case MAGIC_CRASH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                for (Life life : chr.getField().getLifesInRect(rect)) {
                    if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                        Mob mob = (Mob) life;
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        if (Util.succeedProp(si.getValue(prop, slv))) {
                            mts.removeBuffs();
                            mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                        }
                    }
                }
                break;

            case ROYAL_GUARD:   //BuffStat 'ShieldAttack'  has something to do with this skill
            case ROYAL_GUARD_2:
            case ROYAL_GUARD_3:
            case ROYAL_GUARD_4:
            case ROYAL_GUARD_5: // lasts 1450 millisec
                // 51001006  skill Id
                doRoyalGuard();
                chr.addSkillCoolTime(ROYAL_GUARD_STACK_BUFF, 6000);
                break;
            case SWORD_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case RALLY:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                break;
            case ENDURING_SPIRIT:
                o1.nValue = si.getValue(x, slv) + (chr.hasSkill(ENDURING_SPIRIT_STEEL_SKIN) ? 20 : 0);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv) + (chr.hasSkill(ENDURING_SPIRIT_PERSIST) ? 20 : 0);
                tsm.putCharacterStatValue(IndiePDDR, o1);
                o2.nValue = si.getValue(y, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv) + (chr.hasSkill(ENDURING_SPIRIT_PERSIST) ? 20 : 0);
                tsm.putCharacterStatValue(IndieAsrR, o2);
                o3.nValue = si.getValue(z, slv) + (chr.hasSkill(ENDURING_SPIRIT_PREPARATION) ? 10 : 0);
                o3.nReason = skillID;
                o3.tTerm = si.getValue(time, slv) + (chr.hasSkill(ENDURING_SPIRIT_PERSIST) ? 20 : 0);
                tsm.putCharacterStatValue(IndieTerR, o3);
                break;
            case SOUL_LINK: // (ON/OFF)
                if (tsm.hasStatBySkillId(SOUL_LINK)) {
                    tsm.removeStatsBySkill(SOUL_LINK);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = 1;
                    o1.rOption = SOUL_LINK;
                    o1.cOption = chr.getId();
                    tsm.putCharacterStatValue(MichaelSoulLink, o1);
                }
                if (soulLinkBuffsTimer != null && !soulLinkBuffsTimer.isDone()) {
                    soulLinkBuffsTimer.cancel(true);
                }
                giveSoulLinkBuffs();

                if (soulLinkHPRegenTimer != null && !soulLinkHPRegenTimer.isDone()) {
                    soulLinkHPRegenTimer.cancel(true);
                }
                soulLinkHPRegen();
                break;
            case ROILING_SOUL:
                int fd = si.getValue(x, slv);
                int mobsHit = si.getValue(mobCount, slv);
                o1.nOption = (fd * 100) + mobsHit;
                o1.rOption = skillID;
                o1.xOption = fd;
                tsm.putCharacterStatValue(Enrage, o1); // combination of fd% & MobHit
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(EnrageCrDamMin, o2);
                break;
            case CALL_OF_CYGNUS_MIHILE:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case QUEEN_OF_TOMORROW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case SACRED_CUBE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMhpR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o3);
                o4.nOption = 1;
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamAbsorbShield, o4);
                break;
            case SHIELD_OF_LIGHT:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                    return;
                }
                o1.nOption = si.getValue(x, slv); // dmg reduction
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.xOption = chr.getId(); // Mihile Chr Id
                o1.cOption = 0; // total Hits absorbed
                o1.bOption = 16; // Shield Hits Left
                o1.wOption = 1; // Shield Stage
                tsm.putCharacterStatValue(RhoAias, o1);

                if (shielfOfLightTimer != null && !shielfOfLightTimer.isDone()) {
                    shielfOfLightTimer.cancel(true);
                }
                shielfOfLightTimer = EventManager.addFixedRateEvent(this::giveShieldOfLightBuff, 500, 500);
                break;
            case RADIANT_SOUL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SwordOfLight, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieCr, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indiePadR, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePADR, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o4);
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
            chr.heal(healHP, true);
            chr.healMP(healMP);
        }
        selfRecoveryTimer = EventManager.addEvent(this::selfRecovery, 4, TimeUnit.SECONDS);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(RoyalGuardPrepare)) {
            successfulGuard();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    // Character creation related methods ---------------------------------------------------------------------------------------------
    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void cancelTimers() {
        if (soulLinkBuffsTimer != null) {
            soulLinkBuffsTimer.cancel(false);
        }
        if (soulLinkHPRegenTimer != null) {
            soulLinkHPRegenTimer.cancel(false);
        }
        if (selfRecoveryTimer != null) {
            selfRecoveryTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.MIHILE_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.MIHILE_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.MIHILE_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.MIHILE_1.getJobId());
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Newborn Light (Medal)
        Item medal = ItemData.getItemDeepCopy(1142399);
        chr.addItemToInventory(medal);

        // Apprentice Knight of Light Robe
        Item overall = ItemData.getItemDeepCopy(1052444);
        chr.addItemToInventory(overall);

        // Beginner Warrior's Sword
        Item sword = ItemData.getItemDeepCopy(1302077);
        chr.addItemToInventory(sword);

        // Soul Shield of Protection
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1098000)); // SS Protection
    }
}
