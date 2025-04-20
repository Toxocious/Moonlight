package net.swordie.ms.client.jobs.adventurer.pirate;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.Beginner;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.Random;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Pirate extends Beginner {

    //Pirate
    public static final int DASH = 5001005; //Buff


    // V skills
    public static final int PIRATES_BANNER = 400001017;


    public Pirate(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.PIRATE.getJobId()
                || id == JobConstants.JobEnum.PIRATE_CANNONEER.getJobId();
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
            skillID = skill.getSkillId();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();

        switch (attackInfo.skillId) {
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(Cannonneer.BARREL_ROULETTE);
        if (tsm.hasStat(Roulette) && tsm.getOption(Roulette).nOption == 1 && Util.succeedProp(si.getValue(z, chr.getSkill(Cannonneer.BARREL_ROULETTE).getCurrentLevel()))) {
            return Cannonneer.BARREL_ROULETTE_FA;
        } else if (chr.hasSkill(Corsair.MAJESTIC_PRESENCE)) {
            return Corsair.MAJESTIC_PRESENCE;
        } else if (chr.hasSkill(Buccaneer.NAUTILUS_STRIKE_BUCC) && tsm.hasStatBySkillId(Buccaneer.NAUTILUS_STRIKE_BUCC) && chr.hasSkillOnCooldown(Buccaneer.NAUTILUS_STRIKE_BUCC)) {
            return Buccaneer.NAUTILUS_STRIKE_BUCC_FA;
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
            case Buccaneer.HEROS_WILL_BUCC:
            case Corsair.HEROS_WILL_SAIR:
            case Cannonneer.HEROS_WILL_CANNON:
            case Jett.HEROS_WILL_JETT:
                tsm.removeAllDebuffs();
                break;
            case DASH:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                break;
            case Buccaneer.KNUCKLE_BOOSTER:
            case Corsair.GUN_BOOSTER:
            case Cannonneer.CANNON_BOOSTER:
            case Jett.GALACTIC_MIGHT:
                o1.nValue = -5; //si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                if (skillID == Jett.GALACTIC_MIGHT) {
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(mhpR, slv);
                    o2.tTerm = si.getValue(time, slv);
                    tsm.putCharacterStatValue(IndieMHPR, o2);
                }
                break;
            case Buccaneer.MAPLE_WARRIOR_BUCC:
            case Cannonneer.MAPLE_WARRIOR_CANNON:
            case Jett.MAPLE_WARRIOR_JETT:
            case Corsair.MAPLE_WARRIOR_SAIR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case Buccaneer.ROLL_OF_THE_DICE_BUCC:
            case Corsair.ROLL_OF_THE_DICE_SAIR:
            case Cannonneer.LUCK_OF_THE_DIE:
                int upbound = 6;
                if ((chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_DD) && chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_ADDITION)) ||
                        (chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_DD) && chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ADDITION))) {
                    upbound = 7;
                }
                int diceThrow1 = new Random().nextInt(upbound) + 1;

                if ((chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_ENHANCE) || chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ENHANCE)) && Util.succeedProp(40)) {
                    diceThrow1 = new Random().nextInt(4) + 4;
                }

                chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));

                if (diceThrow1 < 2) {
                    chr.reduceSkillCoolTime(skillID, (1000 * si.getValue(cooltime, slv)) / 2);
                    return;
                }

                o1.nOption = diceThrow1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                tsm.throwDice(diceThrow1);
                tsm.putCharacterStatValue(Dice, o1);
                break;
            case Corsair.ROLL_OF_THE_DICE_SAIR_DD:
            case Cannonneer.LUCK_OF_THE_DIE_DD:
                upbound = 6;
                if ((chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_DD) && chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_ADDITION)) ||
                        (chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_DD) && chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ADDITION))) {
                    upbound = 7;
                }
                diceThrow1 = new Random().nextInt(upbound) + 1;
                int diceThrow2 = new Random().nextInt(upbound) + 1;

                if ((chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_ENHANCE) || chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_ENHANCE)) && Util.succeedProp(40)) {
                    diceThrow1 = new Random().nextInt(4) + 4;
                    diceThrow2 = new Random().nextInt(4) + 4;
                }

                int diceThrow3 = 1;
                if (chr.hasSkill(Job.LOADED_DICE)) {
                    if (chr.getQuestManager().getQuestById(GameConstants.LOADED_DICE_SELECTION) == null) {
                        chr.getScriptManager().createQuestWithQRValue(GameConstants.LOADED_DICE_SELECTION, "1");
                    }
                    diceThrow3 = Integer.parseInt(chr.getScriptManager().getQRValue(GameConstants.LOADED_DICE_SELECTION));

                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), -1, 1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow3, -1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow1, 0, true)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), diceThrow2, -1, true)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(LOADED_DICE, chr.getSkillLevel(LOADED_DICE), -1, 2, false)));

                    o1.nOption = (diceThrow1 * 100) + (diceThrow2 * 10) + diceThrow3; // if rolled: 3, 5, and 6 the LoadedDice nOption = 356
                    tsm.throwDice(diceThrow1, diceThrow2, diceThrow3);
                } else {
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                    chr.write(UserPacket.effect(Effect.skillAffectedSelect(skillID, slv, diceThrow2, true)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow1, false)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffectedSelect(skillID, slv, diceThrow2, true)));

                    o1.nOption = (diceThrow1 * 10) + diceThrow2; // if rolled: 3 and 5, the DoubleDown nOption = 35
                    tsm.throwDice(diceThrow1, diceThrow2);
                }
                if (diceThrow1 == 1 || diceThrow2 == 1 || diceThrow3 == 1) {
                    chr.reduceSkillCoolTime(skillID, (1000 * si.getValue(cooltime, slv)) / 2);
                }
                // Saving Grace Hyper Passive Skill
                if ((chr.hasSkill(Buccaneer.ROLL_OF_THE_DICE_BUCC_SAVING_GRACE) || (chr.hasSkill(Corsair.ROLL_OF_THE_DICE_SAIR_SAVING_GRACE))) && diceThrow1 != diceThrow2) {
                    if (Util.succeedProp(40)) {
                        chr.resetSkillCoolTime(skillID);
                    }
                }
                if (diceThrow1 == 1 && diceThrow2 == 1 && diceThrow3 == 1) {
                    return;
                }
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Dice, o1);
                break;

            //Hyper
            case Buccaneer.EPIC_ADVENTURER_BUCC:
            case Cannonneer.EPIC_ADVENTURER_CANNON:
            case Jett.EPIC_ADVENTURER_JETT:
            case Corsair.EPIC_ADVENTURER_SAIR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case PIRATES_BANNER:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setFlip(!chr.isLeft());
                chr.getField().spawnAffectedArea(aa);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if (chr.hasSkill(Buccaneer.PIRATE_REVENGE_BUCC) || chr.hasSkill(Corsair.PIRATE_REVENGE_SAIR)) {
            applyPirateRevenge();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private void applyPirateRevenge() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getPirateRevenge();
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Option o1 = new Option();
        Option o2 = new Option();
        int prop = si.getValue(SkillStat.prop, slv);
        if (Util.succeedProp(prop)) {
            o1.nOption = si.getValue(y, slv);
            o1.rOption = getPirateRevenge().getSkillId();
            o1.tOption = si.getValue(time, slv);
            tsm.putCharacterStatValue(DamageReduce, o1);
            o2.nReason = getPirateRevenge().getSkillId();
            o2.nValue = si.getValue(indieDamR, slv);
            o2.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieDamR, o2);
            tsm.sendSetStatPacket();
        }
    }

    private Skill getPirateRevenge() {
        Skill skill = null;
        if (chr.hasSkill(Corsair.PIRATE_REVENGE_SAIR)) {
            skill = chr.getSkill(Corsair.PIRATE_REVENGE_SAIR);
        }
        if (chr.hasSkill(Buccaneer.PIRATE_REVENGE_BUCC)) {
            skill = chr.getSkill(Buccaneer.PIRATE_REVENGE_BUCC);
        }
        return skill;
    }

 /*   @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        switch (level) {
            case 30:
                qm.completeQuest(1424);
                break;
            case 60:
                qm.completeQuest(1444);
                break;
            case 100:
                qm.completeQuest(1458);
                break;
        }
    }

  */

    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item steelKnuckler = ItemData.getItemDeepCopy(1482110);
        chr.addItemToInventory(steelKnuckler);

        Item pistol = ItemData.getItemDeepCopy(1492109);
        chr.addItemToInventory(pistol);
    }
}
