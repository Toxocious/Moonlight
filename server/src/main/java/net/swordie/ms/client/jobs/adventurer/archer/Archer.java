package net.swordie.ms.client.jobs.adventurer.archer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Beginner;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Archer extends Beginner {

    // V Skills
    public static final int FURY_OF_THE_WILD = 400001012;

    public Archer(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.BOWMAN.getJobId();
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
            case BowMaster.HOOKSHOT_BM:
            case Marksman.HOOKSHOT_MM:
                if (attackInfo.mobAttackInfo.size() > 0) {
                    int mobId = attackInfo.mobAttackInfo.get(0).mobId;
                    Life mob = chr.getField().getLifeByObjectID(mobId);
                    if (mob instanceof Mob) {
                        chr.write(UserPacket.effect(Effect.showHookEffect(skillID, chr.getLevel(), slv, 0,
                                attackInfo.left, mobId, mob.getX(), mob.getY())));
                        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showHookEffect(skillID, chr.getLevel(), slv,
                                0, attackInfo.left, mobId, mob.getX(), mob.getY())));
                    }
                }
                break;
        }

        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
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

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(BowMaster.FINAL_ATTACK_BOW)) {
            skill = chr.getSkill(BowMaster.FINAL_ATTACK_BOW);
        }
        if (chr.hasSkill(Marksman.FINAL_ATTACK_XBOW)) {
            skill = chr.getSkill(Marksman.FINAL_ATTACK_XBOW);
        }
        if (chr.hasSkill(BowMaster.ADVANCED_FINAL_ATTACK_BOW)) {
            skill = chr.getSkill(BowMaster.ADVANCED_FINAL_ATTACK_BOW);
        }
        return skill;
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
            case BowMaster.SOUL_ARROW_BOW:
            case Marksman.SOUL_ARROW_XBOW:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o1);
                o2.nOption = si.getValue(epad, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPAD, o2);
                break;
            case BowMaster.BOW_BOOSTER:
            case Marksman.XBOW_BOOSTER:
            case Pathfinder.ANCIENT_BOW_BOOSTER:
                o1.nValue =  -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case FURY_OF_THE_WILD:
                Summon oldSummon = chr.getField().getSummons()
                        .stream()
                        .filter(l -> (l.getSkillID() == BowMaster.PHOENIX
                                || l.getSkillID() == Marksman.FREEZER
                                || l.getSkillID() == Pathfinder.RAVEN)
                                && l.getChr() == chr)
                        .findAny().orElse(null);
                if (oldSummon == null) {
                    return;
                }
                chr.getField().removeLife(oldSummon.getObjectId(), true);
                tsm.removeStatsBySkill(BowMaster.PHOENIX);
                tsm.removeStatsBySkill(Marksman.FREEZER);
                tsm.removeStatsBySkill(Pathfinder.RAVEN);
                // Fall Through intended
            case BowMaster.PHOENIX:
            case Marksman.FREEZER:
            case Pathfinder.RAVEN:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Fly);
                Field field = chr.getField();
                field.spawnSummon(summon);
                break;
            case BowMaster.SHARP_EYES_BOW:
            case Marksman.SHARP_EYES_XBOW:
            case Pathfinder.SHARP_EYES_ABOW:
                // Short nOption is split in  2 bytes,  first one = CritDmg  second one = Crit%
                int cr = si.getValue(x, slv)
                        + chr.getSkillStatValue(x, BowMaster.SHARP_EYES_BOW_CRITICAL_CHANCE)
                        + chr.getSkillStatValue(x, Pathfinder.SHARP_EYES_ABOW_CRITICAL_CHANCE)
                        + chr.getSkillStatValue(x, Marksman.SHARP_EYES_XBOW_CRITICAL_CHANCE);
                int crDmg = si.getValue(y, slv);
                o1.xOption = cr;
                o1.yOption = crDmg;
                o1.nOption = (cr << 8) + crDmg;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv)
                        + chr.getSkillStatValue(time, BowMaster.SHARP_EYES_BOW_PERSIST)
                        + chr.getSkillStatValue(time, Marksman.SHARP_EYES_XBOW_PERSIST)
                        + chr.getSkillStatValue(time, Pathfinder.SHARP_EYES_ABOW_PERSIST);
                //mOption is for the hyper passive
                if (chr.hasSkill(BowMaster.SHARP_EYES_BOW_GUARDBREAK) || chr.hasSkill(Marksman.SHARP_EYES_XBOW_GUARDBREAK) || chr.hasSkill(Pathfinder.SHARP_EYES_ABOW_GUARDBREAK)) {
                    o1.mOption =
                            chr.getSkillStatValue(ignoreMobpdpR, BowMaster.SHARP_EYES_BOW_GUARDBREAK)
                                    + chr.getSkillStatValue(ignoreMobpdpR, Marksman.SHARP_EYES_XBOW_GUARDBREAK)
                                    + chr.getSkillStatValue(ignoreMobpdpR, Pathfinder.SHARP_EYES_ABOW_GUARDBREAK); // IED
                }
                tsm.putCharacterStatValue(SharpEyes, o1);
                break;
            case BowMaster.ILLUSION_STEP_BOW:
            case Marksman.ILLUSION_STEP_XBOW:
                o1.nValue = si.getValue(indieDEX, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDEX, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVAR, o2);
                break;
            case BowMaster.MAPLE_WARRIOR_BOW:
            case Marksman.MAPLE_WARRIOR_XBOW:
            case Pathfinder.MAPLE_WARRIOR_PF:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case Marksman.EPIC_ADVENTURE_XBOW:
            case BowMaster.EPIC_ADVENTURE_BOW:
            case Pathfinder.EPIC_ADVENTURE_ABOW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case BowMaster.HEROS_WILL_BM:
            case Marksman.HEROS_WILL_MM:
            case Pathfinder.HEROS_WILL_PF:
                tsm.removeAllDebuffs();
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        switch (skillId) {
        }
        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if (hitInfo.hpDamage == 0 && hitInfo.mpDamage == 0) {
            chr.write(UserLocal.dodgeSkillReady());
        }

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();

        // hacks to bypass the quest glitch (accept but no packet)
        if (JobConstants.isPathFinder(chr.getJob())) {
            short level = chr.getLevel();
            QuestManager qm = chr.getQuestManager();
            switch (level) {
                case 30:
                    qm.completeQuest(1418);
                    break;
                case 60:
                    qm.completeQuest(1438);
                    break;
                case 100:
                    qm.completeQuest(1454);
                    break;
            }
        }
    }

    // Skill related methods -------------------------------------------------------------------------------------------
    public void reSummon(int skillID, int slv) {
        Summon summon = Summon.getSummonBy(chr, skillID, slv);
        summon.setFlyMob(true);
        summon.setMoveAbility(MoveAbility.Fly);
        summon.setSummonTerm(220);
        Field field = chr.getField();
        field.spawnSummon(summon);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
        if (!JobConstants.isPathFinder(chr.getJob())) {
            Item beginnerBow = ItemData.getItemDeepCopy(1452051);
            chr.addItemToInventory(beginnerBow);

            Item beginnerCrossbow = ItemData.getItemDeepCopy(1462092);
            chr.addItemToInventory(beginnerCrossbow);

            Item arrowBow = ItemData.getItemDeepCopy(2060000);
            arrowBow.setQuantity(2000);
            chr.addItemToInventory(arrowBow);

            Item arrowCrossbow = ItemData.getItemDeepCopy(2061000);
            arrowCrossbow.setQuantity(2000);
            chr.addItemToInventory(arrowCrossbow);
        }
    }
}
