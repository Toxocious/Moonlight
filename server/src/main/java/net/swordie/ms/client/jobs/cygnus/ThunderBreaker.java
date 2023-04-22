package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.List;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class ThunderBreaker extends Noblesse {

    public static final int ELEMENTAL_HARMONY_STR = 10000246;

    public static final int LIGHTNING_ELEMENTAL = 15001022; //Buff (Charge) //Stackable Charge
    public static final int ELECTRIFIED = 15000023;

    public static final int KNUCKLE_BOOSTER = 15101022; //Buff
    public static final int LIGHTNING_BOOST = 15100025;

    public static final int GALE = 15111022; //Special Attack (Charge)
    public static final int LINK_MASTERY = 15110025; //Special Passive
    public static final int LIGHTNING_LORD = 15110026;

    public static final int ARC_CHARGER = 15121004; //Buff
    public static final int SPEED_INFUSION = 15121005; //Buff
    public static final int CALL_OF_CYGNUS_TB = 15121000; //Buff
    public static final int TYPHOON = 15120003;
    public static final int THUNDER_GOD = 15120008;

    public static final int GLORY_OF_THE_GUARDIANS_TB = 15121053;
    public static final int PRIMAL_BOLT = 15121054;

    // V Skill
    public static final int LIGHTNING_CASCADE = 400051007;
    public static final int LIGHTNING_CASCADE_ATTACK = 400051013;
    public static final int SHARK_TORPEDO = 400051016;
    public static final int TRIDENT_STRIKE = 400051044;
    public static final int TRIDENT_STRIKE_AFTER_HITS = 400051045;


    private static final int[] addedSkills = new int[]{
            ELEMENTAL_HARMONY_STR,
    };

    private static final int[] lightningBuffs = new int[]{
            LIGHTNING_ELEMENTAL,
            ELECTRIFIED,
            LIGHTNING_BOOST,
            LIGHTNING_LORD,
            THUNDER_GOD,
    };

    private int lastAttackSkill = 0;
    private byte arcChargeCDCount;

    public ThunderBreaker(Char chr) {
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
        return JobConstants.isThunderBreaker(id);
    }


    private int getTotalChargeProp() {
        int total = 0;
        for (int buff : lightningBuffs) {
            if (chr.hasSkill(buff)) {
                Skill skill =  chr.getSkill(buff);
                if (skill != null) {
                    SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                    int slv = skill.getCurrentLevel();
                    total += si.getValue(prop, slv);

                }
            }
        }
        if (total > 100) {
            total = 100;
        }
        return total;
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
        int chargeProp = getTotalChargeProp();
        if (tsm.hasStat(CygnusElementSkill)
                && hasHitMobs
                && Util.succeedProp(chargeProp)
                && attackInfo.skillId != SHARK_TORPEDO) {
            incrementLightningElemental(tsm);
        }
        if (skill != null) {
            linkedSkillLogic(skillID);
        }
        if (!chr.hasSkillOnCooldown(LIGHTNING_CASCADE_ATTACK) && chr.hasSkill(LIGHTNING_CASCADE) && hasHitMobs) {
            chr.write(UserLocal.lightningUnionSubAttack(skillID, chr.getSkillLevel(LIGHTNING_CASCADE)));
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case GALE:
            case TYPHOON:
                int chargeStack = tsm.getOption(IgnoreTargetDEF).mOption;
                if ((tsm.getOptByCTSAndSkill(IndieDamR, GALE) == null) || (tsm.getOptByCTSAndSkill(IndieDamR, TYPHOON) == null)) {
                    o1.nReason = skillID;
                    o1.nValue = chargeStack * si.getValue(y, slv);
                    o1.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                    tsm.sendSetStatPacket();
                }
                chr.resetSkillCoolTime(TYPHOON);
                chr.resetSkillCoolTime(GALE);
                break;
            case LIGHTNING_CASCADE:
            case LIGHTNING_CASCADE_ATTACK:
                si = SkillData.getSkillInfoById(LIGHTNING_CASCADE);
                slv = chr.getSkillLevel(LIGHTNING_CASCADE);
                int cooldown = si.getValue(y, slv);
                chr.addSkillCoolTime(LIGHTNING_CASCADE_ATTACK, cooldown * 1000);
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void linkedSkillLogic(int skillId) {
        if (lastAttackSkill == skillId) {
            return;
        }

        SkillInfo si = SkillData.getSkillInfoById(lastAttackSkill);
        lastAttackSkill = skillId;
        if (si == null || si.getAddAttackSkills().stream().noneMatch(aas -> aas == skillId)) {
            return;
        }

        if (chr.hasSkill(TRIDENT_STRIKE)) {
            incrementTridentStrikeCounter();
        }
    }

    private void incrementTridentStrikeCounter() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(TRIDENT_STRIKE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int counter = 1;
        if (tsm.hasStat(TridentStrike)) {
            counter = tsm.getOption(TridentStrike).nOption;
            if (counter < si.getValue(x, slv)) {
                counter++;
            } else {
                counter = 0;
            }
        }
        giveTridentStrikeCTS(counter);
    }

    private void giveTridentStrikeCTS(int counter) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(TRIDENT_STRIKE);
        Option o = new Option();
        if (counter > 0) {
            o.nOption = counter;
            o.rOption = TRIDENT_STRIKE;
            tsm.putCharacterStatValue(TridentStrike, o);
        } else {
            si.getExtraSkillInfo().forEach((map -> map.forEach((k, v) -> EventManager.addEvent(() -> chr.write(UserLocal.userBonusAttackRequest(k)), v))));
            tsm.removeStatsBySkill(TRIDENT_STRIKE);
        }
        tsm.sendSetStatPacket();
    }

    private void incrementLightningElemental(TemporaryStatManager tsm) {
        int amount = 1;
        if (tsm.hasStat(IgnoreTargetDEF)) {
            amount = tsm.getOption(IgnoreTargetDEF).mOption;
            if (amount < getMaxCharge()) {
                amount++;
            }
        }
        updateLightningElemental(tsm, amount);
    }

    private void updateLightningElemental(TemporaryStatManager tsm, int amount) {
        Option o = new Option();
        Skill skill = chr.getSkill(LIGHTNING_ELEMENTAL);
        SkillInfo leInfo = SkillData.getSkillInfoById(skill.getSkillId());
        SkillInfo pbInfo = SkillData.getSkillInfoById(PRIMAL_BOLT);
        int slv = skill.getCurrentLevel();

        o.nOption = (tsm.hasStat(StrikerHyperElectric) ? (pbInfo.getValue(x, slv)) : (leInfo.getValue(x, slv))) * amount;
        o.mOption = amount;
        o.rOption = LIGHTNING_ELEMENTAL;
        o.tOption = 30;
        tsm.putCharacterStatValue(IgnoreTargetDEF, o);
        tsm.sendSetStatPacket();
        reduceArcChargerCoolTime();
    }

    private void reduceArcChargerCoolTime() {
        Skill skill = chr.getSkill(ARC_CHARGER);
        if (skill == null || arcChargeCDCount >= 5) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        arcChargeCDCount++;
        chr.reduceSkillCoolTime(ARC_CHARGER, (si.getValue(y, slv) * 1000));
    }

    private Skill getLightningChargeSkill() {
        Skill skill = null;
        for (int lightningSkill : lightningBuffs) {
            if (chr.hasSkill(lightningSkill)) {
                skill = chr.getSkill(lightningSkill);
            }
        }
        return skill;
    }

    private int getChargeProp() {
        Skill skill = getLightningChargeSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            return si.getValue(prop, slv);
        }
        return 0;
    }

    private int getMaxCharge() {
        int num = 0;
        for (int skill : lightningBuffs) {
            if (chr.hasSkill(skill)) {
                num++;
            }
        }
        return num;
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
        switch (skillID) {
            case LIGHTNING_ELEMENTAL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CygnusElementSkill, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(x, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
                break;
            case KNUCKLE_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case ARC_CHARGER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                arcChargeCDCount = 0;
                break;
            case SPEED_INFUSION:
                o1.nReason = skillID;
                o1.nValue = -2;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case CALL_OF_CYGNUS_TB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case LINK_MASTERY:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamR, o1);
                break;
            case GLORY_OF_THE_GUARDIANS_TB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                var stat = IndieDamR;

                if (chr.getParty() != null) {
                    final List<Char> chrList = chr.getParty().getPartyMembersInField(chr);
                    for (Char pChr : chrList) {
                        var pTSM = pChr.getTemporaryStatManager();
                        if (JobConstants.isCygnusKnight(pChr.getJob())) {
                            pTSM.putCharacterStatValue(stat, o1);
                            pTSM.sendSetStatPacket();
                        }
                    }
                } else {
                    tsm.putCharacterStatValue(stat, o1);
                }
                break;
            case PRIMAL_BOLT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(StrikerHyperElectric, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                chr.resetSkillCoolTime(TYPHOON);
                chr.resetSkillCoolTime(GALE);
                break;
            case LIGHTNING_CASCADE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(LightningCascade, o1);

                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        switch (skillId) {
            case SHARK_TORPEDO:
                int amount = tsm.getOption(IgnoreTargetDEF).mOption;
                if (amount < 2) {
                    chr.chatMessage("You need more Lightning Charges to use this skill.");
                    return;
                }
                updateLightningElemental(tsm, amount - si.getValue(x, slv));
                break;
        }
        super.handleShootObj(chr, skillId, slv);
    }

    @Override
    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case GALE:
            case TYPHOON:
                if (tsm.hasStat(StrikerHyperElectric)) {
                    return 0;
                }
        }
        return super.alterCooldownSkill(skillId);
    }

    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        short level = chr.getLevel();
        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.THUNDER_BREAKER_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.THUNDER_BREAKER_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.THUNDER_BREAKER_4.getJobId());
//                break;
            case 120:
                giveCallOfCygnus(CALL_OF_CYGNUS_TB);
                break;
        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item steelKnuckler = ItemData.getItemDeepCopy(1482110);
        chr.addItemToInventory(steelKnuckler);
    }
}
