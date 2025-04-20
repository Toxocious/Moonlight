package net.swordie.ms.client.jobs.adventurer.warrior;

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
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Beginner;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.IndieDamR;

/**
 * Created on 12/14/2017.
 */
public class Warrior extends Beginner {


    // V skill
    public static final int BLITZ_SHIELD_BUFF = 400001010;
    public static final int BLITZ_SHIELD_ATTACK = 400001011;

    private boolean removeBlitzNotByHit;

    public Warrior(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.WARRIOR.getJobId();
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
        switch (skillID) {
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

        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
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
            case DarkKnight.MAGIC_CRASH_DRK:
            case Hero.MAGIC_CRASH_HERO:
            case Paladin.MAGIC_CRASH_PALLY:
                Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (Mob mob : chr.getField().getMobsInRect(rect)) {
                    if (mob == null) {
                        return;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        mts.removeBuffs();
                        mts.addStatOptionsAndBroadcast(MobStat.MagicCrash, o1);
                    }
                }
                break;
            case Hero.HEROS_WILL_HERO:
            case Paladin.HEROS_WILL_PALA:
            case DarkKnight.HEROS_WILL_DRK:
                tsm.removeAllDebuffs();
                break;
            case BLITZ_SHIELD_ATTACK:
                removeBlitzNotByHit = true;
                tsm.removeStatsBySkill(BLITZ_SHIELD_BUFF);
                break;
            case Hero.WEAPON_BOOSTER_FIGHTER:
            case DarkKnight.WEAPON_BOOSTER_SPEARMAN:
            case Paladin.WEAPON_BOOSTER_PAGE:
                o1.nValue = -5; //si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                chr.chatMessage(Integer.toString(o1.nValue));
                break;
            case Hero.MAPLE_WARRIOR_HERO:
            case Paladin.MAPLE_WARRIOR_PALADIN:
            case DarkKnight.MAPLE_WARRIOR_DARK_KNIGHT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case DarkKnight.EPIC_ADVENTURE_DRK:
            case Hero.EPIC_ADVENTURE_HERO:
            case Paladin.EPIC_ADVENTURE_PALA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case BLITZ_SHIELD_BUFF:
                o1.nOption = 1500;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BlitzShield, o1);
                removeBlitzNotByHit = true;
                break;
        }
        tsm.sendSetStatPacket();
    }


    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == BlitzShield && removeBlitzNotByHit) {
            chr.write(UserLocal.userBonusAttackRequest(BLITZ_SHIELD_ATTACK));
        }

        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        // Warriors - Blitz Shield
        if (tsm.hasStat(BlitzShield)) {
            int blockedHP = tsm.getOptByCTSAndSkill(BlitzShield, BLITZ_SHIELD_BUFF).nOption;
            if (hitInfo.hpDamage >= chr.getHP()) {
                hitInfo.hpDamage = chr.getHP() - blockedHP;
                removeBlitzNotByHit = false;
                tsm.removeStatsBySkill(BLITZ_SHIELD_BUFF);
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
            case 30:
                qm.completeQuest(1410);
                break;
            case 60:
                qm.completeQuest(1430);
                break;
            case 100:
                qm.completeQuest(1450);
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

        Item sword = ItemData.getItemDeepCopy(1302077);
        chr.addItemToInventory(sword);
    }
}

