package net.swordie.ms.client.jobs.adventurer.thief;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Beginner;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Thief extends Beginner {

    // Rogue
    public static final int HASTE = 4001005; //Buff
    public static final int DARK_SIGHT = 4001003; //Buff


    // V Skill
    public static final int SHADOW_WALKER = 400001023;

    private int darkSightCount = 0;

    public Thief(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.THIEF.getJobId();
    }

    public void giveShadowMeld() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(BladeMaster.SHADOW_MELD)) {
            if (tsm.getOptByCTSAndSkill(IndiePAD, BladeMaster.SHADOW_MELD) == null) {
                Skill skill = chr.getSkill(BladeMaster.SHADOW_MELD);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());

                if (!chr.hasSkillOnCooldown(skill.getSkillId())) {
                    Option o1 = new Option();
                    Option o2 = new Option();
                    o1.nOption = 100;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(CriticalBuff, o1);
                    o2.nReason = skill.getSkillId();
                    o2.nValue = si.getValue(indiePad, slv);
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndiePAD, o2); //Indie
                    tsm.sendSetStatPacket();
                    chr.addSkillCoolTime(skill.getSkillId(), 5000);
                }
            }
        }
    }


    private Skill getAdvDarkSightSkill() {
        Skill skill = null;
        if (chr.hasSkill(Shadower.ADVANCED_DARK_SIGHT_SHAD)) {
            skill = chr.getSkill(Shadower.ADVANCED_DARK_SIGHT_SHAD);
        } else if (chr.hasSkill(BladeMaster.ADVANCED_DARK_SIGHT_DB)) {
            skill = chr.getSkill(BladeMaster.ADVANCED_DARK_SIGHT_DB);
        }
        return skill;
    }

    private void procAdvDarkSight() {
        if (!chr.hasSkill(DARK_SIGHT) || getAdvDarkSightSkill() == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getAdvDarkSightSkill();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int proc = si.getValue(x, slv);
        int maintainProc = si.getValue(prop, slv);
        boolean isInDarkSight = tsm.hasStat(DarkSight);
        if (isInDarkSight && !(Util.succeedProp(maintainProc) || tsm.hasStatBySkillId(Shadower.SMOKE_SCREEN))) {
            tsm.removeStat(DarkSight, true);
        } else if (Util.succeedProp(proc) || (skill.getSkillId() == Shadower.ADVANCED_DARK_SIGHT_SHAD) && tsm.hasStatBySkillId(Shadower.SMOKE_SCREEN)) {
            handleSkill(chr, tsm, DARK_SIGHT, chr.getSkillLevel(DARK_SIGHT), null, new SkillUseInfo());
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
            slv = (byte) skill.getCurrentLevel();
            skillID = SkillConstants.getActualSkillIDfromSkillID(skill.getSkillId());
        }
        if (!SkillConstants.isSummon(attackInfo.skillId) && hasHitMobs) {
            // Venom & Toxic Venom Passives
            applyPassiveDoTSkillsOnMob(attackInfo);
        }

        // Proc Adv Dark Sight
        if (!SkillConstants.isSummon(attackInfo.skillId)) {
            procAdvDarkSight();
        }


        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {


            case BladeMaster.SUDDEN_RAID_DB:
                chr.reduceSkillCoolTime(BladeMaster.FINAL_CUT, (long) (chr.getRemainingCoolTime(BladeMaster.FINAL_CUT) * 0.2F));
                // Fallthrough intended
            case Shadower.SUDDEN_RAID_SHAD:
            case NightLord.SUDDEN_RAID_NL:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;

            case Shadower.BOOMERANG_STAB:
            case BladeMaster.FLYING_ASSAULTER:
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
                break;


        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private Skill getPassiveDoTSkill() { // TODO  Shadower & DB (Toxic) Venom doesn't work? shows icon but doesn't do damage
        Skill skill = null;
        if (chr.hasSkill(NightLord.VENOM_NL)) {
            skill = chr.getSkill(NightLord.VENOM_NL);
        }
        if (chr.hasSkill(Shadower.VENOM_SHAD)) {
            skill = chr.getSkill(Shadower.VENOM_SHAD);
        }
        if (chr.hasSkill(BladeMaster.VENOM_DB)) {
            skill = chr.getSkill(BladeMaster.VENOM_DB);
        }
        if (chr.hasSkill(NightLord.TOXIC_VENOM_NL)) {
            skill = chr.getSkill(NightLord.TOXIC_VENOM_NL);
        }
        if (chr.hasSkill(Shadower.TOXIC_VENOM_SHAD)) {
            skill = chr.getSkill(Shadower.TOXIC_VENOM_SHAD);
        }
        if (chr.hasSkill(BladeMaster.TOXIC_VENOM_DB)) {
            skill = chr.getSkill(BladeMaster.TOXIC_VENOM_DB);
        }
        return skill;
    }

    private void applyPassiveDoTSkillsOnMob(AttackInfo attackInfo) {
        if (getPassiveDoTSkill() == null) {
            return;
        }
        Skill skill = getPassiveDoTSkill();
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
                mts.createAndAddBurnedInfo(chr, skill.getSkillId(), slv, si.getValue(dotSuperpos, slv));
            }
        }
    }


    @Override
    public int getFinalAttackSkill() {
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
            case NightLord.HEROS_WILL_NL:
            case Shadower.HEROS_WILL_SHAD:
            case BladeMaster.HEROS_WILL_DB:
                tsm.removeAllDebuffs();
                break;
            case HASTE:
            case BladeMaster.SELF_HASTE:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                break;
            case DARK_SIGHT:
                darkSightCount = 0;
                // Fall through intended
            case SHADOW_WALKER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case NightLord.CLAW_BOOSTER:
            case Shadower.DAGGER_BOOSTER:
            case BladeMaster.KATARA_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case NightLord.SHADOW_PARTNER_NL:
            case Shadower.SHADOW_PARTNER_SHAD:
            case BladeMaster.MIRROR_IMAGE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ShadowPartner, o1);
                break;
            case BladeMaster.MAPLE_WARRIOR_DB:
            case NightLord.MAPLE_WARRIOR_NL:
            case Shadower.MAPLE_WARRIOR_SHAD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case NightLord.DARK_FLARE_NL:
            case Shadower.DARK_FLARE_SHAD:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setAttackActive(true);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.Attack);
                chr.getField().spawnSummon(summon);
                break;
            case BladeMaster.EPIC_ADVENTURE_DB:
            case NightLord.EPIC_ADVENTURE_NL:
            case Shadower.EPIC_ADVENTURE_SHAD:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        switch (skillId) {
            case DARK_SIGHT:
                return 0;
        }

        return super.alterCooldownSkill(skillId);
    }

    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {

        }

        super.handleSkillRemove(chr, skillID);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == DarkSight && tsm.getOption(DarkSight).rOption == DARK_SIGHT) {
            SkillInfo si = SkillData.getSkillInfoById(DARK_SIGHT);
            int slv = chr.getSkillLevel(DARK_SIGHT);
            int totalCooltime = (si.getValue(cooltime, slv) * 1000) * darkSightCount;
            chr.addSkillCoolTime(DARK_SIGHT, totalCooltime);
        }

        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (chr.hasSkill(BladeMaster.SHADOW_MELD)) {
            if (hitInfo.hpDamage <= 0) {
                giveShadowMeld();
            }
        }

        if (chr.hasSkill(DARK_SIGHT)) {
            if (hitInfo.hpDamage <= 0 && tsm.getOptByCTSAndSkill(DarkSight, DARK_SIGHT) != null) {
                darkSightCount++;
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        switch (level) {
            case 20:
                if (chr.getSubJob() == 1) // Dual Blade Sub Job
                handleJobAdvance(JobConstants.JobEnum.BLADE_RECRUIT.getJobId());
                break;
            case 30:
                qm.completeQuest(1421);
                break;
            case 60:
                qm.completeQuest(1441);
                break;
            case 100:
                qm.completeQuest(1456);
                break;
        }
    }

    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item fruitKnife = ItemData.getItemDeepCopy(1332063);
        chr.addItemToInventory(fruitKnife);

        Item garnier = ItemData.getItemDeepCopy(1472061);
        chr.addItemToInventory(garnier);

        for (int i = 0; i < 3; i++) {
            Item subi = ItemData.getItemDeepCopy(2070000);
            subi.setQuantity(500);
            chr.addItemToInventory(subi);
        }
    }
}