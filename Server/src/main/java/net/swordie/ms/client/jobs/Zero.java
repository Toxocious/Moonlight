package net.swordie.ms.client.jobs;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.LinkSkill;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.ExtendSP;
import net.swordie.ms.client.character.SPSet;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.HotTimeRewardType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.QuestData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.HashMap;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Zero extends Job {

    public static final int DUAL_COMBAT = 100001270;
    public static final int DUAL_COMBAT_2 = 100000282;
    public static final int TEMPLE_RECALL = 100001262;
    public static final int RESOLUTION_TIME = 100000279;
    public static final int BURST_JUMP = 100001265;
    public static final int BURST_STEP = 100001266;
    public static final int BURST_LEAP = 100001269;

    public static final int RHINNES_BLESSING_BOOST = 100000280;

    public static final int DIVINE_FORCE = 100001263; //Aura (Unlimited Duration)
    public static final int DIVINE_SPEED = 100001264; //Aura (Unlimited Duration)
    public static final int RHINNES_PROTECTION = 100001268; //Buff

    public static final int TIME_HOLDING = 100001274;
    public static final int TIME_HOLDING_2 = 100001281;
    public static final int TIME_DISTORTION = 100001261;
    public static final int REWIND = 100001272;
    public static final int FOCUSED_TIME = 100001005;
    public static final int DOUBLE_TIME = 100000267;
    public static final int DOUBLE_TIME_ALPHA = 100000276;
    public static final int DOUBLE_TIME_BETA = 100000277;

    public static final int AIR_RIOT = 101000101; //Special Attack (Stun Debuff)
    public static final int THROWING_WEAPON = 101100100; //Special Attack (Throw Sword)
    public static final int ADVANCED_THROWING_WEAPON = 101100101; //Special Attack (Throw Sword)
    public static final int STORM_BREAK = 101120202;
    public static final int STORM_BREAK_INIT = 101120203;
    public static final int ADV_EARTH_BREAK = 101120104;
    public static final int ADV_STORM_BREAK = 101120204;
    public static final int ADV_EARTH_BREAK_SHOCK_INIT = 101120105; //Attack to initialise the Shockwave
    public static final int ADV_STORM_BREAK_SHOCK_INIT = 101120205; //Attack to initialise the Shockwave
    public static final int ADV_EARTH_BREAK_SHOCKWAVE = 101120106; //Tile Skill
    public static final int ADV_STORM_BREAK_SHOCKWAVE = 101120206; //Tile Skill
    public static final int DIVINE_LEER = 101120207;
    public static final int CRITICAL_BIND = 101120110;
    public static final int IMMUNE_BARRIER = 101120109;
    public static final int ARMOR_SPLIT = 101110103;

    // V
    public static final int CHRONO_BREAK = 400011015;
    public static final int TWIN_BLADES_OF_TIME_START = 400011039;
    public static final int SHADOW_FLASH_ALPHA_TILE = 400011098;
    public static final int SHADOW_FLASH_ALPHA_ATTACK = 400011099;
    public static final int SHADOW_FLASH_BETA_TILE = 400011100;
    public static final int SHADOW_FLASH_BETA_ATTACK = 400011101;

    private static final int[] addedSkills = new int[]{
            DUAL_COMBAT,
            DUAL_COMBAT_2,
            TEMPLE_RECALL,
            RESOLUTION_TIME,
            BURST_STEP,
            BURST_JUMP,
            BURST_LEAP,
            DIVINE_FORCE,
            DIVINE_SPEED,
            RHINNES_PROTECTION,
            DOUBLE_TIME,
    };

    private int doubleTimePrevSkill = 0;

    public static int getAlphaOrBetaSkill(int skillID) {
        switch (skillID) {
            case 101001200: //Moon Strike
            case 101000200: //Piercing Thrust
            case 101000201: //Shadow Strike
            case 101000202: //Shadow Strike

            case 101101200: //Flash Assault
            case 101100200: //Spin Cutter
            case 101100201: //Adv Spin Cutter
            case 101100202: //Adv Blade Ring

            case 101110200: //Grand Rolling Cross
            case 101110201: //Grand Rolling Cross
            case 101111200: //Rolling Cross
            case 101110202: //Rolling Assault
            case 101110203: //Advanced Rolling Assault
            case 101110204: //Advanced Rolling Assault

            case 101120200: //Wind Cutter
            case 101120201: //Wind Striker
            case 101120202: //Storm Break
            case 101120203: //Storm Break
            case 101120204: //Advanced Storm Break
            case 101120205: //Severe Storm Break (Tile)
            case 101120206: //Severe Storm Break
            case 101121101: //Hurricane Wind
            case 101121200: //Wind Cutter:
                return 1; //Alpha skills

            case 101001100: //Rising Slash
            case 101000100: //Air Raid
            case 101000101: //Air Riot
            case 101000102: //Air Riot

            case 101101100: //Flash Cut
            case 101100100: //Throwing Weapon
            case 101100101: //Adv. Throwing Weapon

            case 101111100: //Spin Driver
            case 101110101: //Wheel Wind
            case 101110102: //Adv Wheel Wind
            case 101110104: //Adv Blade Tempest

            case 101121100: //Giga Crash
            case 101120100: //Falling Star
            case 101120101: //Falling Star
            case 101120102: //Earth Break
            case 101120103: //Groundbreaker
            case 101120104: //Adv Earth Break
            case 101120105: //Mega Groundbreaker (Tile)
                return 2; //Beta skills

        }
        return skillID; // no original skill linked with this one
    }

    public Zero(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (chr.getZeroInfo() == null) {
                chr.initZeroInfo();
            }
        }
    }

    @Override
    public void addMissingSkills(Char chr) {
        super.addMissingSkills(chr);
        chr.addSkill(DUAL_COMBAT, 1, 1);
        chr.addSkill(DUAL_COMBAT_2, 1, 1);
        chr.addSkill(TEMPLE_RECALL, 1, 1);
        chr.addSkill(RESOLUTION_TIME, 5, 5);
        chr.addSkill(BURST_STEP, 1, 1);
        chr.addSkill(BURST_JUMP, 1, 1);
        chr.addSkill(BURST_LEAP, 1, 1);
        chr.addSkill(RHINNES_BLESSING_BOOST, 1 ,1);
        chr.addSkill(DIVINE_FORCE, 1, 1);
        chr.addSkill(DIVINE_SPEED, 1, 1);
        chr.addSkill(RHINNES_PROTECTION, 1, 1);
        chr.addSkill(DOUBLE_TIME, 1, 1);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isZero(id);
    }

    private boolean isBeta() {
        return chr.getZeroInfo().isZeroBetaState();
    }

    @Override
    public void handleLevelUp() {
        short level = chr.getLevel();
        if (chr.getLevel() == 110) {
            chr.addSkill(TIME_DISTORTION, 1, 1);
        }
        if (chr.getLevel() == 120) {
            chr.addSkill(RHINNES_BLESSING_BOOST, 1, 5);
            chr.addSkill(TIME_HOLDING, 1, 1);
        }
        if (chr.getLevel() == 130) {
            chr.addSkill(RHINNES_BLESSING_BOOST, 2, 5);
        }
        if (chr.getLevel() == 140) {
            chr.addSkill(RHINNES_BLESSING_BOOST, 3, 5);
            chr.addSkill(REWIND, 1, 1);
        }
        if (chr.getLevel() == 150) {
            chr.addSkill(RHINNES_BLESSING_BOOST, 4, 5);
        }
        if (chr.getLevel() == 160) {
            chr.addSkill(RHINNES_BLESSING_BOOST, 5, 5);
            chr.addSkill(100001283, 1, 1); // Shadow Rain
        }
        if (chr.getLevel() == 200) {
            chr.addSkill(FOCUSED_TIME, 1, 1);
            chr.getQuestManager().completeQuest(QuestConstants.FIFTH_JOB_QUEST);
            chr.addHotTimeReward(2435902, HotTimeRewardType.GAME_ITEM, 100, 0, 0,0,"Gift for reaching level 200."); // Nodestones
        }
        chr.addStat(Stat.mhp, 500);
        chr.addStat(Stat.ap, 5);
        int sp = 3;
        if (level > 100 && (level % 10) % 3 == 0) {
            sp = 6; // double sp on levels ending in 3/6/9
            if (level == 110) {
                chr.getQuestManager().completeQuest(QuestConstants.ZERO_WEAPON_WINDOW_QUEST); //enables weapon button
                Quest q = QuestData.createQuestFromId(QuestConstants.ZERO_SET_QUEST);
                q.setQrValue(String.valueOf(0));
                chr.getQuestManager().addQuest(q);
            }
        }
        ExtendSP esp = chr.getAvatarData().getCharacterStat().getExtendSP();
        SPSet alphaSpSet = esp.getSpSet().get(0);
        SPSet betaSpSet = esp.getSpSet().get(1);
        alphaSpSet.addSp(sp);
        betaSpSet.addSp(sp);
        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mhp, chr.getStat(Stat.mhp));
        stats.put(Stat.mmp, chr.getStat(Stat.mmp));
        stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.write(WvsContext.statChanged(stats));
        int linkSkill = SkillConstants.getLinkSkillByJob(chr.getJob()); //80000110 - Rhinne's Blessing
        byte linkSkillLevel = (byte) chr.getSkillLevel(RHINNES_BLESSING_BOOST);
        int linkSkillID = SkillConstants.getOriginalOfLinkedSkill(linkSkill);
        if (linkSkillID != 0 && linkSkillLevel > 0) {
            Skill skill = chr.getSkill(linkSkillID, true);
            if (skill.getCurrentLevel() != linkSkillLevel) {
                chr.addSkill(linkSkillID, linkSkillLevel, 3);
            }
            Account acc = chr.getAccount();
            LinkSkill ls = acc.getLinkSkillByLinkSkillId(linkSkill);
            if (ls == null) {
                ls = new LinkSkill(chr.getId(), 0, linkSkill, linkSkillLevel);
                acc.getLinkSkills().add(ls);
            } else if (ls.getLevel() < linkSkillLevel) {
                ls.setLinkSkillID(linkSkill);
                ls.setLevel(linkSkillLevel);
                ls.setOriginID(chr.getId());
            }
            System.err.println(linkSkillID + " " + linkSkillLevel);
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
            skillID = skill.getSkillId();
        }

        if (getAlphaOrBetaSkill(skillID) == 1) {
            if (hasHitMobs) {
                incrementDoubleTimeAlpha(skillID);
            }

            applyDivineLeerOnMob(attackInfo, skillID);
        }

        if (getAlphaOrBetaSkill(skillID) == 2) {
            if (hasHitMobs) {
                incrementDoubleTimeBeta(skillID);
            }

            applyCriticalBindOnMob(attackInfo, skillID);
            applyArmorSplitOnMob(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AIR_RIOT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(SkillStat.time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(SkillStat.prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case ADV_EARTH_BREAK_SHOCK_INIT:
                slv = (byte) chr.getSkill(ADV_EARTH_BREAK).getCurrentLevel();
                SkillInfo fci = SkillData.getSkillInfoById(ADV_EARTH_BREAK);
                AffectedArea aa = AffectedArea.getPassiveAA(chr, ADV_EARTH_BREAK, slv);
                aa.setPosition(chr.getPosition());
                aa.setSkillID(ADV_EARTH_BREAK);
                aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
                aa.setDuration(fci.getValue(SkillStat.v, slv) * 1000);
                chr.getField().spawnAffectedArea(aa);
                break;
            case CHRONO_BREAK:
                o1.nValue = -2;
                o1.nReason = skillID;
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nValue = si.getValue(SkillStat.indiePMdR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                tsm.sendSetStatPacket();
                break;
            case SHADOW_FLASH_ALPHA_TILE:
            case SHADOW_FLASH_BETA_TILE:
                si = SkillData.getSkillInfoById(SHADOW_FLASH_ALPHA_TILE);
                slv = (byte) chr.getSkillLevel(SHADOW_FLASH_ALPHA_TILE);
                aa = AffectedArea.getPassiveAA(chr, attackInfo.skillId, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                aa.setDuration(si.getValue(SkillStat.cooltime, slv) * 1000);
                chr.getField().spawnAffectedArea(aa);
                chr.addSkillCoolTime(attackInfo.skillId, si.getValue(SkillStat.cooltime, slv) * 1000);
                break;
            case SHADOW_FLASH_ALPHA_ATTACK:
            case SHADOW_FLASH_BETA_ATTACK:
                int tileSkill = attackInfo.skillId == SHADOW_FLASH_ALPHA_ATTACK ? SHADOW_FLASH_ALPHA_TILE : SHADOW_FLASH_BETA_TILE;
                AffectedArea shadowFlashAA = chr.getField().getAffectedAreas().stream()
                        .filter(sfaa -> sfaa.getSkillID() == tileSkill && sfaa.getOwner() == chr)
                        .findFirst()
                        .orElse(null);
                if (shadowFlashAA == null) {
                    return;
                }
                chr.getField().removeLife(shadowFlashAA);
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void applyDivineLeerOnMob(AttackInfo ai, int skillID) {
        Skill skill = chr.getSkill(DIVINE_LEER);
        if (skill == null) {
            return;
        }
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(DIVINE_LEER);
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(SkillStat.prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                mob.getTemporaryStat().createAndAddBurnedInfo(chr, skill);
            }
        }
    }

    private void applyCriticalBindOnMob(AttackInfo ai, int skillID) {
        Skill skill = chr.getSkill(CRITICAL_BIND);
        if (skill == null) {
            return;
        }

        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(CRITICAL_BIND);
        Option o = new Option();
        Option o1 = new Option();
        o.nOption = 1;
        o.rOption = CRITICAL_BIND;
        o.tOption = 4;
        o1.nOption = si.getValue(SkillStat.x, slv);
        o1.rOption = CRITICAL_BIND;
        o1.tOption = 4;//   si.getValue(time, slv);
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(SkillStat.prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
            }
        }
    }

    private void applyArmorSplitOnMob(AttackInfo ai) {
        Skill skill = chr.getSkill(ARMOR_SPLIT);
        if (skill == null) {
            return;
        }
        Option o = new Option();
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(ARMOR_SPLIT);
        int amount = 1;
        o.tOption = si.getValue(SkillStat.time, slv);
        o.rOption = skill.getSkillId();
        for (MobAttackInfo mai : ai.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(SkillStat.prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();

                if (mts.hasCurrentMobStat(MobStat.MultiPMDR)) {
                    amount = mts.getCurrentOptionsByMobStat(MobStat.MultiPMDR).cOption;
                    if (amount < si.getValue(SkillStat.x, slv)) {
                        amount++;
                    }
                }
                o.nOption = si.getValue(SkillStat.y, slv) * amount;
                o.cOption = amount;
                mts.addStatOptionsAndBroadcast(MobStat.MultiPMDR, o);
            }
        }
    }

    private void incrementDoubleTimeAlpha(int skillID) {
        if (chr.hasSkill(DOUBLE_TIME)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            Option o1 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(DOUBLE_TIME_ALPHA);
            int amount = 1;
            if (tsm.hasStat(TimeFastABuff)) {
                if (doubleTimePrevSkill == skillID) {
                    return;
                }
                amount = tsm.getOption(TimeFastABuff).nOption;
                if (amount < 10) {
                    amount++;
                }
            }
            doubleTimePrevSkill = skillID;
            o.nOption = amount;
            o.rOption = DOUBLE_TIME_ALPHA;
            o.tOption = 20;
            tsm.putCharacterStatValue(TimeFastABuff, o);
            tsm.sendSetStatPacket();
        }
    }

    private void incrementDoubleTimeBeta(int skillID) {
        if (chr.hasSkill(DOUBLE_TIME)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            Option o = new Option();
            Option o1 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(DOUBLE_TIME_BETA);
            int amount = 1;
            if (tsm.hasStat(TimeFastBBuff)) {
                if (doubleTimePrevSkill == skillID) {
                    return;
                }
                amount = tsm.getOption(TimeFastBBuff).nOption;
                if (amount < 10) {
                    amount++;
                }
            }
            doubleTimePrevSkill = skillID;
            o.nOption = amount;
            o.rOption = DOUBLE_TIME_BETA;
            o.tOption = 20;
            tsm.putCharacterStatValue(TimeFastBBuff, o);
            tsm.sendSetStatPacket();
        }
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
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        switch (skillID) {
            case THROWING_WEAPON:
            case ADVANCED_THROWING_WEAPON:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.FixVMove);
                chr.getField().spawnSummon(summon);
                break;
            case TIME_DISTORTION:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setDelay((short) 5);
                chr.getField().spawnAffectedArea(aa);
                break;
            case DIVINE_FORCE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(SkillStat.indieAsrR, slv);
                    tsm.putCharacterStatValue(IndieAsrR, o1); //Indie
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(SkillStat.indieMad, slv);
                    tsm.putCharacterStatValue(IndieMAD, o2); //Indie
                    o3.nReason = skillID;
                    o3.nValue = si.getValue(SkillStat.indiePad, slv);
                    tsm.putCharacterStatValue(IndiePAD, o3); //Indie
                    o4.nReason = skillID;
                    o4.nValue = si.getValue(SkillStat.indiePdd, slv);
                    tsm.putCharacterStatValue(IndieDEF, o4); //Indie
                    o5.nReason = skillID;
                    o5.nValue = si.getValue(SkillStat.indieTerR, slv);
                    tsm.putCharacterStatValue(IndieTerR, o5); //Indie
                    o6.nOption = 1;
                    o6.rOption = skillID;
                    tsm.putCharacterStatValue(ZeroAuraStr, o6);
                }
                break;
            case DIVINE_SPEED:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nReason = skillID;
                    o1.nValue = si.getValue(SkillStat.indieAcc, slv);
                    o1.tTerm = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(IndieACC, o1); //Indie
                    o2.nReason = skillID;
                    o2.nValue = si.getValue(SkillStat.indieBooster, slv);
                    o2.tTerm = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(IndieBooster, o2); //Indie
                    o3.nReason = skillID;
                    o3.nValue = si.getValue(SkillStat.indieEva, slv);
                    o3.tTerm = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(IndieEVA, o3); //Indie
                    o4.nReason = skillID;
                    o4.nValue = si.getValue(SkillStat.indieJump, slv);
                    o4.tTerm = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(IndieJump, o4); //Indie
                    o5.nReason = skillID;
                    o5.nValue = si.getValue(SkillStat.indieSpeed, slv);
                    o5.tTerm = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(IndieSpeed, o5); //Indie
                    o6.nOption = 1;
                    o6.rOption = skillID;
                    tsm.putCharacterStatValue(ZeroAuraSpd, o6);
                }
                break;
            case RHINNES_PROTECTION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(SkillStat.x, slv);
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;

            case TIME_HOLDING:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);

                if (chr.getStat(Stat.level) >= 200) {
                    o2.nOption = si.getValue(SkillStat.y, slv);
                    o2.rOption = TIME_HOLDING_2;
                    o2.tOption = si.getValue(SkillStat.x, slv);
                    tsm.putCharacterStatValue(DamR, o2);
                }

                for (int skillId : chr.getSkillCoolTimes().keySet()) {
                    if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset()) {
                        chr.resetSkillCoolTime(skillId);
                    }
                }
                break;
            case REWIND:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case FOCUSED_TIME:
                o1.nReason = skillID;
                o1.nValue = 4;
                o1.tTerm = 2400;
                tsm.putCharacterStatValue(IndiePADR, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = 4;
                o2.tTerm = 2400;
                tsm.putCharacterStatValue(IndieMADR, o2); //Indie
                break;
            case TWIN_BLADES_OF_TIME_START:
                o1.nReason = skillID;
                o1.nValue = 1;
                o1.tTerm = 10;
                tsm.putCharacterStatValue(IndieKeyDownTime, o2);
                tsm.putCharacterStatValue(IndieNotDamaged, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill immuneBarrier = chr.getSkill(IMMUNE_BARRIER);
        if (immuneBarrier == null) {
            return;
        }
        int slv = immuneBarrier.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(IMMUNE_BARRIER);
        if (Util.succeedProp(si.getValue(SkillStat.prop, slv))) {
            Option o = new Option(IMMUNE_BARRIER, slv);
            int max = (int) (chr.getStat(Stat.mhp) * (si.getValue(SkillStat.x, slv) / 100D));
            o.nOption = max;
            o.xOption = max;
            chr.getTemporaryStatManager().putCharacterStatValue(ImmuneBarrier, o);
        }
        if (tsm.hasStat(ImmuneBarrier)) {
            Option o = tsm.getOption(ImmuneBarrier);
            int maxSoakDamage = o.nOption;
            int newDamage = hitInfo.hpDamage - maxSoakDamage < 0 ? 0 : hitInfo.hpDamage - maxSoakDamage;
            o.nOption = maxSoakDamage - (hitInfo.hpDamage - newDamage); // update soak value
            hitInfo.hpDamage = newDamage;
            o.tOption = si.getValue(SkillStat.time, slv); //added duration
            tsm.putCharacterStatValue(ImmuneBarrier, o);
            tsm.sendSetStatPacket();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void reviveByRewind() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(REWIND);
        tsm.sendResetStatPacket();
        chr.write(UserPacket.effect(Effect.skillSpecial(REWIND)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(REWIND)));
    }


    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        addMissingSkills(chr);
    }


    @Override
    public void setCharCreationStats(Char chr) {
//        super.setCharCreationStats(chr);

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setJob(10112);
        cs.setLevel(100);
        cs.setStr(518);
        cs.setHp(5000);
        cs.setMaxHp(5000);
        cs.setMp(100);
        cs.setMaxMp(100);
        cs.setPosMap(100000000);

        Map<Stat, Object> stats = new HashMap<>();
        stats.put(Stat.mhp, chr.getStat(Stat.mhp));
        stats.put(Stat.mmp, chr.getStat(Stat.mmp));
        stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.write(WvsContext.statChanged(stats));


        int sp = 5;
        ExtendSP esp = chr.getAvatarData().getCharacterStat().getExtendSP();
        SPSet alphaSpSet = esp.getSpSet().get(0);
        SPSet betaSpSet = esp.getSpSet().get(1);
        alphaSpSet.addSp(sp);
        betaSpSet.addSp(sp);
        stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        chr.write(WvsContext.statChanged(stats));


        // starter consumables
        Item powerElixir = ItemData.getItemDeepCopy(2000005);
        powerElixir.setQuantity(200);
        chr.addItemToInventory(powerElixir);
        Item hyperTp = ItemData.getItemDeepCopy(5040004);
        chr.addItemToInventory(hyperTp);
        Item frenzyTotem = ItemData.getItemDeepCopy(1202236);
        chr.addItemToInventory(frenzyTotem);
    }

}

