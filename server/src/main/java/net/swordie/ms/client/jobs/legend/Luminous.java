package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.LarknessManager;
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
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Luminous extends Job {
    public static final int SUNFIRE = 20040216;
    public static final int ECLIPSE = 20040217;
    public static final int EQUILIBRIUM = 20040219;
    public static final int EQUILIBRIUM2 = 20040220;
    public static final int INNER_LIGHT = 20040221;
    public static final int FLASH_BLINK = 20041222;
    public static final int CHANGE_LIGHT_DARK = 20041239;

    // dual skills - level each other up
    public static final int FLASH_SHOWER = 27001100;
    public static final int ABYSSAL_DROP = 27001201;

    // affinities
    public static final int LIGHT_AFFINITY = 27000106;
    public static final int DARK_AFFINITY = 27000207;

    public static final int MAGIC_BOOSTER = 27101004; //Buff
    public static final int BLACK_BLESSING = 27100003;

    public static final int SHADOW_SHELL = 27111004; //Buff
    public static final int RAY_OF_REDEMPTION = 27111101; // Attack + heals party members
    public static final int DUSK_GUARD = 27111005; //Buff
    public static final int PHOTIC_MEDITATION = 27111006; //Buff
    public static final int LUNAR_TIDE = 27110007;
    public static final int DEATH_SCYTHE = 27111303;
    public static final int PRESSURE_VOID = 27101202;

    public static final int DARK_CRESCENDO = 27121005; //Buff
    public static final int ARCANE_PITCH = 27121006; //Buff
    public static final int MAPLE_WARRIOR_LUMI = 27121009; //Buff
    public static final int ENDER = 27121303;
    public static final int DARKNESS_MASTERY = 27120008;
    public static final int HEROS_WILL_LUMI = 27121010;

    public static final int EQUALIZE = 27121054;
    public static final int HEROIC_MEMORIES_LUMI = 27121053;
    public static final int ARMAGEDDON = 27121052; //Stun debuff

    // V
    public static final int DOOR_OF_LIGHT = 400021005;
    public static final int AETHER_CONDUIT_L = 400021041;
    public static final int AETHER_CONDUIT_D = 400021049;
    public static final int AETHER_CONDUIT_EQ = 400021050;
    public static final int BAPTISM_OF_LIGHT_AND_DARKNESS = 400021071;

    private static final int[] addedSkills = new int[]{
            EQUILIBRIUM,
    };

    private static final int[] aetherForms = new int[]{
            AETHER_CONDUIT_L,
            AETHER_CONDUIT_D,
            AETHER_CONDUIT_EQ,
    };

    public Luminous(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }

            if (chr.getTemporaryStatManager().getLarknessManager() == null) {
                chr.getTemporaryStatManager().setLarknessManager(new LarknessManager(chr));
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isLuminous(id);
    }


    private void giveLunarTideBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(LUNAR_TIDE)) {
            Option o1 = new Option();
            Option o2 = new Option();
            SkillInfo lti = SkillData.getSkillInfoById(LUNAR_TIDE);
            Skill skill = chr.getSkill(LUNAR_TIDE);
            int slv = skill.getCurrentLevel();
            int maxMP = c.getChr().getStat(Stat.mmp);
            int curMP = c.getChr().getStat(Stat.mp);
            int maxHP = c.getChr().getStat(Stat.mhp);
            int curHP = c.getChr().getStat(Stat.hp);
            double ratioHP = ((double) curHP / maxHP);
            double ratioMP = ((double) curMP) / maxMP;

            //if (ratioHP > ratioMP) {
            if (ratioHP > ratioMP) {
                //Crit Rate      HP is Greater than MP
                o1.nOption = 2;
                o1.rOption = LUNAR_TIDE;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(prop, slv);     //only gives 10% for w/e reason but the SkillStat is correct
                o2.rOption = LUNAR_TIDE;
                tsm.putCharacterStatValue(CriticalBuff, o2);
            } else {
                //Damage         MP is Greater than HP
                o1.nOption = 1;
                o1.rOption = LUNAR_TIDE;
                tsm.putCharacterStatValue(LifeTidal, o1);
                o2.nOption = lti.getValue(x, slv);
                o2.rOption = LUNAR_TIDE;
                tsm.putCharacterStatValue(DamR, o2);
            }
            tsm.sendSetStatPacket();
        }
    }

    public void changeBlackBlessingCount(boolean increment) {
        Option o = new Option();
        Option o2 = new Option();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(BLACK_BLESSING)) {
            return;
        }
        int amount = 1;
        if (tsm.hasStat(BlessOfDarkness)) {
            amount = tsm.getOption(BlessOfDarkness).nOption;

            if (increment) {
                if (amount < 3) {
                    chr.write(UserPacket.effect(Effect.skillSpecial(BLACK_BLESSING)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(BLACK_BLESSING)));
                    amount++;
                }
            } else {
                if (amount > 0) {
                    amount--;
                }
            }
        }

        int orbmad;
        switch (amount) {
            case 1:
                orbmad = 15;
                break;
            case 2:
                orbmad = 24;
                break;
            case 3:
                orbmad = 30;
                break;
            default:
                orbmad = 0;
                break;
        }

        if (amount > 0) {
            o.nOption = amount;
            o.rOption = BLACK_BLESSING;
            tsm.putCharacterStatValue(BlessOfDarkness, o);
            o2.nOption = orbmad;
            o2.rOption = BLACK_BLESSING;
            tsm.putCharacterStatValue(MAD, o2);
            tsm.sendSetStatPacket();
        } else {
            tsm.removeStatsBySkill(BLACK_BLESSING);
            tsm.sendResetStatPacket();
        }
    }

    public int getMoreEquilibriumTime() {
        int eqTime = 0;
        SkillInfo eqi = SkillData.getSkillInfoById(DARKNESS_MASTERY);
        if (chr.hasSkill(DARKNESS_MASTERY)) {
            eqTime += eqi.getValue(time, chr.getSkillLevel(DARKNESS_MASTERY));
        }
        return eqTime;
    }


    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        LarknessManager lm = tsm.getLarknessManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (
                chr.getJob() != 2004
                        && tsm.hasStat(Larkness)
                        && ((lm.isDark() && SkillConstants.isLarknessDarkSkill(attackInfo.skillId))
                        || (!lm.isDark() && SkillConstants.isLarknessLightSkill(attackInfo.skillId)))
                        && (tsm.getOption(Larkness).rOption != EQUILIBRIUM2)
        ) {

            if (si != null && si.getValue(gauge, slv) > 0) {
                lm.changeGauge(si.getValue(gauge, slv));
            }
        }

        if (hasHitMobs) {
            if (!tsm.hasStat(Larkness)) {
                lm.changeMode();
            }
            // Dark Crescendo
            if (tsm.hasStat(StackBuff)) {
                int crescendoProp = getCrescendoProp();
                if (skill != null && Util.succeedProp(crescendoProp)) {
                    incrementDarkCrescendo(tsm);
                }
            }
        }
        giveLunarTideBuff();
        giveEquilibriumHPDrain(attackInfo.skillId);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case ARMAGEDDON:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case RAY_OF_REDEMPTION:
                chr.heal(chr.getMaxHP()); // 800% Recovery
                break;
            case ENDER:
                if (chr.hasSkill(BAPTISM_OF_LIGHT_AND_DARKNESS)) {
                    incrementSwordsOfConsciousness();
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void incrementSwordsOfConsciousness() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int curStack = tsm.hasStat(SwordsOfConsciousness) ? tsm.getOption(SwordsOfConsciousness).nOption : 0;
        changeSwordsOfConsciousness(curStack + 1);
    }

    private void changeSwordsOfConsciousness(int swords) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillId = BAPTISM_OF_LIGHT_AND_DARKNESS;
        Option o = new Option();
        if (swords <= 12) {
            o.nOption = swords;
            o.rOption = skillId;
            tsm.putCharacterStatValue(SwordsOfConsciousness, o);
            tsm.sendSetStatPacket();
        } else {
            tsm.removeStatsBySkill(skillId);
            chr.resetSkillCoolTime(skillId);
        }
    }

    private void giveEquilibriumHPDrain(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (tsm.getLarknessManager().isEquilibrium() && SkillConstants.isLarknessLightSkill(skillId) && chr.hasSkill(EQUILIBRIUM)) {
            Skill skill = chr.getSkill(EQUILIBRIUM);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            chr.heal(chr.getHPPerc(si.getValue(x, 1)));
        }
    }

    private void incrementDarkCrescendo(TemporaryStatManager tsm) {
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo crescendoInfo = SkillData.getSkillInfoById(DARK_CRESCENDO);
        Skill skill = chr.getSkill(DARK_CRESCENDO);
        int slv = skill.getCurrentLevel();
        int amount = 1;
        if (tsm.hasStat(StackBuff)) {
            amount = tsm.getOption(StackBuff).mOption;
            if (amount < getMaxDarkCrescendoStack()) {
                amount++;
            }
        }
        o.setInMillis(true);
        o.nOption = (amount * crescendoInfo.getValue(damR, slv));
        o.rOption = DARK_CRESCENDO;
        o.tOption = (int) tsm.getRemainingTime(StackBuff, DARK_CRESCENDO);
        o.mOption = amount;
        o.setInMillis(true);
        tsm.putCharacterStatValue(StackBuff, o, true);
        tsm.sendSetStatPacket();
    }

    private int getCrescendoProp() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(DARK_CRESCENDO).getValue(prop, skill.getCurrentLevel());
    }

    private int getMaxDarkCrescendoStack() {
        Skill skill = null;
        if (chr.hasSkill(DARK_CRESCENDO)) {
            skill = chr.getSkill(DARK_CRESCENDO);
        }
        return skill == null ? 0 : SkillData.getSkillInfoById(skill.getSkillId()).getValue(x, skill.getCurrentLevel());
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
        LarknessManager lm = tsm.getLarknessManager();
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case HEROS_WILL_LUMI:
                tsm.removeAllDebuffs();
                break;
            case MAGIC_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case SHADOW_SHELL:
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                // no bOption for Luminous' AntiMagicShell
                tsm.putCharacterStatValue(AntiMagicShell, o1);
                break;
            case DUSK_GUARD:
                o2.nValue = si.getValue(indiePdd, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDEF, o2);
                break;
            case PHOTIC_MEDITATION:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                break;
            case DARK_CRESCENDO:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.mOption = 1;
                tsm.putCharacterStatValue(StackBuff, o1);
                break;
            case ARCANE_PITCH:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case MAPLE_WARRIOR_LUMI:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case EQUALIZE:
                lm.goEquilibriumMode();
                break;
            case HEROIC_MEMORIES_LUMI:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case DOOR_OF_LIGHT:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case AETHER_CONDUIT_L:
            case AETHER_CONDUIT_D:
            case AETHER_CONDUIT_EQ:
                slv = chr.getSkillLevel(AETHER_CONDUIT_L);
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                field.spawnAffectedAreaAndRemoveOld(aa);
                chr.setSkillCooldown(AETHER_CONDUIT_L, slv);
                break;
            case BAPTISM_OF_LIGHT_AND_DARKNESS:
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.SequenceAttack);
                field.spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case ENDER:
            case DEATH_SCYTHE:
                if (tsm.getLarknessManager().isEquilibrium()) {
                    return 0;
                }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == Larkness && tsm.getLarknessManager().isEquilibrium()) {
            EventManager.addEvent(this::getOutOfEquilibrium, 30, TimeUnit.MILLISECONDS);
        }
    }

    public void getOutOfEquilibrium() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        LarknessManager lm = tsm.getLarknessManager();
        lm.changeMode();
        lm.changeGauge(50);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (tsm.getOption(Larkness).rOption == EQUILIBRIUM) {
            return;
        } else {
            if (tsm.hasStat(BlessOfDarkness) && chr.hasSkill(BLACK_BLESSING) && hitInfo.hpDamage > 0) {
                Skill skill = chr.getSkill(BLACK_BLESSING);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int slv = skill.getCurrentLevel();
                changeBlackBlessingCount(false); // deduct orbs as player gets hit
                int dmgAbsorbed = si.getValue(x, slv);
                hitInfo.hpDamage = (int) (hitInfo.hpDamage * ((double) dmgAbsorbed / 100));
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(AntiMagicShell)) {
            tsm.removeAllDebuffs();
            deductShadowShell();
        }

    }

    private void deductShadowShell() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(SHADOW_SHELL)) {
            return;
        }
        Skill skill = chr.getSkill(SHADOW_SHELL);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Option o = new Option();
        if (tsm.hasStat(AntiMagicShell)) {
            int shadowShellCount = tsm.getOption(AntiMagicShell).nOption;

            if (shadowShellCount > 0) {
                shadowShellCount--;
            }

            if (shadowShellCount <= 0) {
                tsm.removeStatsBySkill(skill.getSkillId());
                tsm.sendResetStatPacket();
            } else {
                o.nOption = shadowShellCount;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AntiMagicShell, o);
                tsm.sendSetStatPacket();
            }
            chr.write(UserPacket.effect(Effect.skillSpecial(skill.getSkillId())));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(skill.getSkillId())));
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);

//        chr.getAvatarData().getCharacterStat().setPosMap(927020080);

        Item orb = ItemData.getItemDeepCopy(1352400);
        chr.addItemToInventory(orb);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.LUMINOUS_2.getJobId());
//                chr.getQuestManager().completeQuest(25510);
//                chr.addSpToJobByCurrentJob(5);
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.LUMINOUS_3.getJobId());
//                chr.getQuestManager().completeQuest(25511);
//                chr.addSpToJobByCurrentJob(20);
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.LUMINOUS_4.getJobId());
//                chr.getQuestManager().completeQuest(25512);
//                chr.addSpToJobByCurrentJob(5);
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.LUMINOUS_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();



        // Stats
        chr.addSpToJobByCurrentJob(5);

        // Skills
        chr.addSkill(FLASH_SHOWER, 3, 20);
        chr.addSkill(ABYSSAL_DROP, 3, 20);
        chr.addSkill(LIGHT_AFFINITY, 1, 5);
        chr.addSkill(DARK_AFFINITY, 1, 5);
        chr.addSkill(SUNFIRE, 1, 1);
        chr.addSkill(ECLIPSE, 1, 1);
        chr.addSkill(INNER_LIGHT, 1, 1);
        chr.addSkill(FLASH_BLINK, 1, 1);

        // Items

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352400)); // Light Orb
    }
}