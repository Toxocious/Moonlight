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
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Paladin extends Warrior {
    public static final int WEAPON_BOOSTER_PAGE = 1201004;
    public static final int FINAL_ATTACK_PAGE = 1200002;
    public static final int CLOSE_COMBAT = 1201013;
    public static final int ELEMENTAL_CHARGE = 1200014;
    public static final int FLAME_CHARGE = 1201011;
    public static final int DIVINE_SHIELD = 1210016;
    public static final int BLIZZARD_CHARGE = 1201012;


    public static final int PARASHOCK_GUARD = 1211014;
    public static final int COMBAT_ORDERS = 1211011;
    public static final int LIGHTNING_CHARGE = 1211008;
    public static final int HP_RECOVERY = 1211010;
    public static final int DIVINE_CHARGE = 1221004;
    public static final int THREATEN = 1211013;


    public static final int ELEMENTAL_FORCE = 1221015;
    public static final int THREATEN_PERSIST = 1220043;
    public static final int THREATEN_OPPORTUNITY = 1220044;
    public static final int THREATEN_ENHANCE = 1220045;
    public static final int HEAVENS_HAMMER = 1221011;
    public static final int ADVANCED_CHARGE = 1220010;
    public static final int MAPLE_WARRIOR_PALADIN = 1221000;
    public static final int BLAST = 1221009;
    public static final int GUARDIAN = 1221016;
    public static final int MAGIC_CRASH_PALLY = 1221014;
    public static final int HEROS_WILL_PALA = 1221012;
    public static final int SACROSANCTITY = 1221054; //Lv140
    public static final int SMITE_SHIELD = 1221052; //Lv160
    public static final int EPIC_ADVENTURE_PALA = 1221053; //Lv190


    // V skills
    public static final int HAMMERS_OF_THE_RIGHTEOUS = 400011052;
    public static final int GRAND_GUARDIAN = 400011072;
    public static final int HAMMERS_OF_THE_RIGHTEOUS_2 = 400011053;
    public static final int DIVINE_ECHO = 400011003;

    private int lastCharge = 0;
    private long divineShieldTime;
    private ScheduledFuture parashockGuardTimer;
    private ScheduledFuture divineEchoTimer;

    public Paladin(Char chr) {
        super(chr);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isPaladin(id);
    }


    private void setDivineEchoLinkedChr(int chrId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        o1.nOption = chrId;
        o1.rOption = DIVINE_ECHO;
        o1.tOption = (int) tsm.getRemainingTime(DivineEcho, DIVINE_ECHO);
        o1.xOption = chr.getId();
        o1.setInMillis(true);
        tsm.putCharacterStatValue(DivineEcho, o1, true);
        tsm.sendSetStatPacket();
    }

    private Char getDivineEchoLinkedChr() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(DivineEcho) && tsm.getOption(DivineEcho).nOption != chr.getId()) {
            return chr.getField().getCharByID(tsm.getOption(DivineEcho).nOption);
        }
        return null;
    }

    private void giveDivineEchoLinkedChrBuffs(Char linkedChr, CharacterTemporaryStat cts, Option option) {
        if (linkedChr == null) {
            return;
        }
        TemporaryStatManager linkedTSM = linkedChr.getTemporaryStatManager();
        int skillId = option.rOption > 0 ? option.rOption : option.nReason;
        option.chr = chr;
        linkedChr.write(UserPacket.effect(Effect.skillUse(skillId, chr.getLevel(), chr.getSkillLevel(skillId), 0)));
        linkedChr.getField().broadcastPacket(UserRemote.effect(linkedChr.getId(), Effect.skillUse(skillId, chr.getLevel(), chr.getSkillLevel(skillId), 0)));
        linkedTSM.putCharacterStatValue(cts, option);
        linkedTSM.sendSetStatPacket();
    }

    public static void removeDivineEchoLinkedBuffs(Char linkedChr) {
        TemporaryStatManager linkedTSM = linkedChr.getTemporaryStatManager();
        Map<Integer, CharacterTemporaryStat> linkedSkills = new HashMap<Integer, CharacterTemporaryStat>() {{
            put(WEAPON_BOOSTER_PAGE, IndieBooster);
            put(ELEMENTAL_FORCE, IndiePMdR);
            put(SACROSANCTITY, NotDamaged);
        }};
        for (Map.Entry<Integer, CharacterTemporaryStat> entry : linkedSkills.entrySet()) {
            int skillId = entry.getKey();
            CharacterTemporaryStat cts = entry.getValue();
            if (linkedTSM.hasStatBySkillId(skillId) && linkedTSM.getOption(cts).chr != linkedChr) {
                linkedTSM.removeStatsBySkill(skillId);
            }
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

            case GRAND_GUARDIAN:
                o1.nOption = 1;
                o1.rOption = skillId;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(GrandGuardian, o1);
                o2.nValue = -(si.getValue(x, slv));
                o2.nReason = skillId;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieHitDamageInclHPR, o2);
                o3.nValue = 100;
                o3.nReason = skillId;
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieNoKnockBack, o3);
                break;
        }
    }

    public void increaseGrandGuardianState() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(GrandGuardian)) {
            Option o = new Option();
            o.nOption = 2;
            o.rOption = GRAND_GUARDIAN;
            o.tOption = (int) tsm.getRemainingTime(GrandGuardian, GRAND_GUARDIAN);
            o.setInMillis(true);
            tsm.putCharacterStatValue(GrandGuardian, o, true);
            tsm.sendSetStatPacket();
        }
    }

    public void decreaseHPByGrandGuardian() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(GrandGuardian)) {
            SkillInfo si = SkillData.getSkillInfoById(GRAND_GUARDIAN);
            int slv = chr.getSkillLevel(GRAND_GUARDIAN);
            int hpConsumed = (int) (((double) chr.getMaxHP() * 1.5) / 100F);    //TODO (int) ((double) (chr.getMaxHP() * si.getValue(t, slv)) / 100F);
            chr.heal(-hpConsumed);
        }
    }

    private void giveParashockGuardBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(PARASHOCK_GUARD) && tsm.getOptByCTSAndSkill(EVA, PARASHOCK_GUARD) != null) {
            Skill skill = chr.getSkill(PARASHOCK_GUARD);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
            if (!chr.isLeft()) {
                rect = rect.horizontalFlipAround(chr.getPosition().getX());
            }
            Party party = chr.getParty();

            if (party != null) {
                for (PartyMember partyMember : party.getOnlineMembers()) {
                    Char partyChr = partyMember.getChr();
                    TemporaryStatManager partyTSM = partyChr.getTemporaryStatManager();
                    int partyChrX = partyChr.getPosition().getX();
                    int partyChrY = partyChr.getPosition().getY();

                    if (partyChr.getId() == chr.getId()) {
                        continue;
                    }

                    if (partyChrX >= rect.getLeft() && partyChrY >= rect.getTop()       // if  Party Members in Range
                            && partyChrX <= rect.getRight() && partyChrY <= rect.getBottom()) {

                        Option o4 = new Option();
                        Option o5 = new Option();
                        o4.nOption = si.getValue(x, slv);
                        o4.rOption = skill.getSkillId();
                        o4.tOption = 2;
                        partyTSM.putCharacterStatValue(Guard, o4);
                        o5.nOption = chr.getId();
                        o5.rOption = skill.getSkillId();
                        o5.tOption = 2;
                        o5.bOption = 1;
                        partyTSM.putCharacterStatValue(KnightsAura, o5);
                        partyTSM.sendSetStatPacket();
                    } else {
                        partyTSM.removeStatsBySkill(skill.getSkillId());
                        partyTSM.sendResetStatPacket();
                    }
                }
            }
            Option o = new Option();
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            o.nOption = chr.getId();
            o.rOption = skill.getSkillId();
            o.bOption = 1;
            tsm.putCharacterStatValue(KnightsAura, o);
            o1.nReason = skill.getSkillId();
            o1.nValue = si.getValue(indiePad, slv);
            tsm.putCharacterStatValue(IndiePAD, o1);
            o2.nReason = skill.getSkillId();
            o2.nValue = si.getValue(z, slv);
            tsm.putCharacterStatValue(IndiePDDR, o2);
            o3.nOption = -si.getValue(x, slv);
            o3.rOption = skill.getSkillId();
            tsm.putCharacterStatValue(Guard, o3);

            parashockGuardTimer = EventManager.addEvent(this::giveParashockGuardBuff, 1, TimeUnit.SECONDS);
        } else {
            tsm.removeStatsBySkill(PARASHOCK_GUARD);
            tsm.sendResetStatPacket();
        }
    }


    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {

            case GRAND_GUARDIAN:
                tsm.removeStatsBySkill(skillID);
                break;
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
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case CLOSE_COMBAT:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case FLAME_CHARGE:
                giveChargeBuff(skill.getSkillId());
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case BLIZZARD_CHARGE:
                o1.nOption = -20;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                giveChargeBuff(skill.getSkillId());
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
            case LIGHTNING_CHARGE:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                giveChargeBuff(skill.getSkillId());
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        if (!mob.isBoss()) {
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    } else {
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case DIVINE_CHARGE:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Seal, o1);
                    }
                }
                giveChargeBuff(skill.getSkillId());
                break;
            case BLAST:
                int charges = tsm.getOption(ElementalCharge).mOption;
                if (charges == SkillData.getSkillInfoById(ELEMENTAL_CHARGE).getValue(z, 1)) {
                    if (tsm.getOptByCTSAndSkill(IndieDamR, BLAST) == null) {
                        resetCharges();
                        int t = si.getValue(time, slv);
                        o1.nOption = si.getValue(cr, slv);
                        o1.rOption = skillID;
                        o1.tOption = t;
                        tsm.putCharacterStatValue(CriticalBuff, o1);
                        o2.nReason = skillID;
                        o2.nValue = si.getValue(ignoreMobpdpR, slv);
                        o2.tTerm = t;
                        tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
                        o3.nReason = skillID;
                        o3.nValue = si.getValue(damR, slv);
                        o3.tTerm = t;
                        tsm.putCharacterStatValue(IndieDamR, o3);
                        tsm.sendSetStatPacket();
                    }
                }
                break;
            case SMITE_SHIELD:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Smite, o1);
                }
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void giveChargeBuff(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        SkillInfo chargeInfo = SkillData.getSkillInfoById(ELEMENTAL_CHARGE);
        int amount = 1;
        if (tsm.hasStat(ElementalCharge)) {
            amount = tsm.getOption(ElementalCharge).mOption;
            if (lastCharge == skillId) {
                return;
            }
            if (amount < chargeInfo.getValue(z, 1)) {
                amount++;
            }
        }
        int slv = 1;
        if (chr.hasSkill(ADVANCED_CHARGE)) {
            chargeInfo = SkillData.getSkillInfoById(ADVANCED_CHARGE);
            slv = (byte) chr.getSkillLevel(ADVANCED_CHARGE);
        }
        lastCharge = skillId;
        o.nOption = chr.hasSkill(ADVANCED_CHARGE) ? amount * chargeInfo.getValue(x, slv) : 1; // damR  - upgraded by Adv Charge
        o.rOption = ELEMENTAL_CHARGE;
        o.tOption = (10 * chargeInfo.getValue(time, slv)); // elemental charge  // 10x actual duration
        o.mOption = amount;
        o.wOption = amount * chargeInfo.getValue(y, slv); // attack power  - upgraded by Adv Charge
        o.uOption = amount * 2; // AsrR
        o.zOption = amount * 2; // hit dmg
        tsm.putCharacterStatValue(ElementalCharge, o);
        if (chr.hasSkill(HAMMERS_OF_THE_RIGHTEOUS)) {
            Option o1 = new Option();
            o1.nOption = amount;
            o1.rOption = HAMMERS_OF_THE_RIGHTEOUS;
            tsm.putCharacterStatValue(HammerOfTheRighteous, o1);
        }
        tsm.sendSetStatPacket();
    }

    private void resetCharges() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(ElementalCharge, false);
        tsm.sendResetStatPacket();
    }


    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_PAGE)) {
            skill = chr.getSkill(FINAL_ATTACK_PAGE);
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
            case HP_RECOVERY:
                hpRecovery();
                break;
            case THREATEN:
                Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                o1.nOption = -si.getValue(x, slv) - (chr.hasSkill(THREATEN_ENHANCE) ? 20 : 0);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv) + (chr.hasSkill(THREATEN_PERSIST) ? 20 : 0);

                o2.nOption = -si.getValue(z, slv) - (chr.hasSkill(THREATEN_ENHANCE) ? 20 : 0);
                o2.rOption = skillID;
                o2.tOption = si.getValue(subTime, slv) + (chr.hasSkill(THREATEN_PERSIST) ? 20 : 0);
                for (Life life : chr.getField().getLifesInRect(rect)) {
                    if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                        Mob mob = (Mob) life;
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        if (Util.succeedProp(si.getValue(prop, slv) + (chr.hasSkill(THREATEN_OPPORTUNITY) ? 25 : 0))) {
                            mts.addStatOptions(MobStat.PAD, o1);
                            mts.addStatOptions(MobStat.MAD, o1);
                            mts.addStatOptions(MobStat.PDR, o1);
                            mts.addStatOptions(MobStat.MDR, o1);
                            mts.addStatOptionsAndBroadcast(MobStat.Darkness, o2);
                        }
                    }
                }
                break;
            case GUARDIAN:
                chr.heal(chr.getMaxHP());

                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                tsm.sendSetStatPacket();

                Party party = chr.getParty();
                if (party != null) {
                    Field field = chr.getField();
                    rect = chr.getPosition().getRectAround(si.getRects().get(0));
                    if (!chr.isLeft()) {
                        rect = rect.horizontalFlipAround(chr.getPosition().getX());
                    }
                    List<PartyMember> eligblePartyMemberList = field.getPartyMembersInRect(chr, rect).stream().
                            filter(pml -> pml.getChr().getId() != chr.getId() &&
                                    pml.getChr().getHP() <= 0).
                            collect(Collectors.toList());

                    if (eligblePartyMemberList.size() > 0) {
                        Char randomPartyChr = Util.getRandomFromCollection(eligblePartyMemberList).getChr();
                        TemporaryStatManager partyTSM = randomPartyChr.getTemporaryStatManager();
                        randomPartyChr.heal(randomPartyChr.getMaxHP());
                        partyTSM.putCharacterStatValue(NotDamaged, o1);
                        partyTSM.sendSetStatPacket();
                        randomPartyChr.write(UserPacket.effect(Effect.skillAffected(skillID, (byte) 1, 0)));
                        randomPartyChr.getField().broadcastPacket(UserRemote.effect(randomPartyChr.getId(), Effect.skillAffected(skillID, (byte) 1, 0)));
                    }
                }

                break;
            case COMBAT_ORDERS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CombatOrders, o1);
                break;
            case PARASHOCK_GUARD:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(EVA, o1); // Check for the main Buff method

                if (parashockGuardTimer != null && !parashockGuardTimer.isDone()) {
                    parashockGuardTimer.cancel(true);
                }
                giveParashockGuardBuff();
                break;
            case ELEMENTAL_FORCE:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePMdR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o1);
                break;
            case BLAST:
                o1.nOption = si.getValue(cr, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(damR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(ignoreMobpdpR, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o3);
                break;
            case SACROSANCTITY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case DIVINE_ECHO:
                Rect rect2 = chr.getRectAround(si.getLastRect());
                if (chr.getParty() == null) {
                    o1.nOption = chr.getId();
                } else {
                    List<Char> chrList = chr.getParty().getPartyMembersInField(chr).stream().filter(pChr -> rect2.hasPositionInside(pChr.getPosition())).collect(Collectors.toList());
                    if (chrList.size() <= 0) {
                        o1.nOption = chr.getId();
                    } else {
                        o1.nOption = Util.getRandomFromCollection(chrList).getId(); // Party member ChrId
                    }
                }
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.xOption = chr.getId();
                tsm.putCharacterStatValue(DivineEcho, o1);
                tsm.sendSetStatPacket();
                if (divineEchoTimer != null && !divineEchoTimer.isDone()) {
                    divineEchoTimer.cancel(true);
                }
                // TODO  Search for Party members in Rect
                break;
            case HAMMERS_OF_THE_RIGHTEOUS_2:
                si = SkillData.getSkillInfoById(HAMMERS_OF_THE_RIGHTEOUS);
                slv = chr.getSkillLevel(HAMMERS_OF_THE_RIGHTEOUS);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(v, slv);
                tsm.putCharacterStatValue(BigHammerOfTheRighteous, o1);
                chr.addSkillCoolTime(HAMMERS_OF_THE_RIGHTEOUS, si.getValue(cooltime, slv) * 1000);
                break;

        }
        tsm.sendSetStatPacket();
    }

    private void hpRecovery() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (chr.hasSkill(HP_RECOVERY)) {
            Skill skill = chr.getSkill(HP_RECOVERY);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int recovery = si.getValue(x, slv);
            int amount = 10;

            if (tsm.hasStat(Restoration)) {
                amount = tsm.getOption(Restoration).nOption;
                if (amount < 300) {
                    amount = amount + 10;
                }
            }

            o.nOption = amount;
            o.rOption = skill.getSkillId();
            o.tOption = si.getValue(time, slv);
            int heal = (recovery + 10) - amount > 10 ? (recovery + 10) - amount : 10;
            chr.heal((int) (chr.getMaxHP() / ((double) 100 / heal)));
            tsm.putCharacterStatValue(Restoration, o);
            tsm.sendSetStatPacket();
        }
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            switch (skillId) {
                case HAMMERS_OF_THE_RIGHTEOUS:
                    if (tsm.hasStat(HammerOfTheRighteous)) {
                        return 0;
                    }
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == BlessingArmor) {
            SkillInfo si = SkillData.getSkillInfoById(DIVINE_SHIELD);
            int slv = chr.getSkillLevel(DIVINE_SHIELD);
            chr.addSkillCoolTime(DIVINE_SHIELD, Util.getCurrentTimeLong() + (si.getValue(cooltime, slv) * 1000));
        } else if (cts == ElementalCharge) {
            tsm.removeStat(HammerOfTheRighteous, true);
            tsm.removeStat(BigHammerOfTheRighteous, true);
        }
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);

        //Paladin - Divine Shield
        if (chr.hasSkill(DIVINE_SHIELD) && !chr.hasSkillOnCooldown(DIVINE_SHIELD)) {
            Skill skill = chr.getSkill(DIVINE_SHIELD);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int shieldprop = si.getValue(prop, slv);
            if (Util.succeedProp(shieldprop) || tsm.hasStat(BlessingArmor)) {
                if (!tsm.hasStat(BlessingArmor)) {
                    divineShieldTime = Util.getCurrentTimeLong() + (si.getValue(time, slv) * 1000);
                }
                decrementDivineShieldCount();
            }
        }

        //Paladin - Shield Mastery
        if (chr.hasSkill(1210001)) { //If Wearing a Shield
            if (hitInfo.hpDamage == 0 && hitInfo.mpDamage == 0) {
                // Guarded
                int mobID = hitInfo.mobID;
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mobID);
                if (mob != null) {
                    Skill skill = chr.getSkill(1210001);
                    int slv = skill.getCurrentLevel();
                    SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                    int proc = si.getValue(subProp, slv);
                    Option o = new Option();
                    o.nOption = 1;
                    o.rOption = skill.getSkillId();
                    o.tOption = 3;  // Value isn't given
                    if (Util.succeedProp(proc) && !mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o);
                    }
                }
            }
        }
    }

    private void decrementDivineShieldCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(DIVINE_SHIELD);
        SkillInfo si = SkillData.getSkillInfoById(DIVINE_SHIELD);
        int slv = skill.getCurrentLevel();
        Option o1 = new Option();
        Option o2 = new Option();
        int counter = 10;
        if (tsm.hasStat(BlessingArmor)) {
            counter = tsm.getOption(BlessingArmor).nOption;
            if (counter > 1) {
                counter--;
            } else {
                tsm.removeStatsBySkill(skill.getSkillId());
                chr.addSkillCoolTime(skill.getSkillId(), Util.getCurrentTimeLong() + (si.getValue(cooltime, slv) * 1000));
                return;
            }
        }
        o1.nOption = counter;
        o1.rOption = skill.getSkillId();
        o1.tOption = (int) (divineShieldTime - Util.getCurrentTimeLong()) / 1000;
        tsm.putCharacterStatValue(BlessingArmor, o1);
        o2.nOption = si.getValue(epad, slv);
        o2.rOption = skill.getSkillId();
        o2.tOption = (int) (divineShieldTime - Util.getCurrentTimeLong()) / 1000;
        tsm.putCharacterStatValue(BlessingArmorIncPAD, o2);
        tsm.sendSetStatPacket();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.WHITE_KNIGHT.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.PALADIN.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {

        if (parashockGuardTimer != null) {
            parashockGuardTimer.cancel(false);
        }
        if (divineEchoTimer != null) {
            divineEchoTimer.cancel(false);
        }
        super.cancelTimers();
    }
}
