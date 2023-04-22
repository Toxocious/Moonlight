package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.LinkSkill;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.life.*;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Evan extends Job {

    public static final int RUNE_PERSISTANCE = 20010294;

    public static final int INHERITED_WILL = 20010194;
    public static final int BACK_TO_NATURE = 20011293;

    public static final int MAGIC_GUARD = 22001012; // Buff

    public static final int MAGIC_BOOSTER = 22111020; // Buff
    public static final int ELEMENTAL_DECREASE = 22141016; // Buff
    public static final int PARTNERS = 22110016;

    public static final int BLESSING_OF_THE_ONYX = 22171073; // Buff
    public static final int MAPLE_WARRIOR_EVAN = 22171068; // Buff
    public static final int MAGIC_DEBRIS = 22141017;

    public static final int DRAGON_MASTER = 22171080; // Mount
    public static final int DRAGON_MASTER_2 = 22171083; // Add-on
    public static final int SUMMON_ONYX_DRAGON = 22171081; // Summon
    public static final int HEROIC_MEMORIES_EVAN = 22171082;
    public static final int ENHANCED_MAGIC_DEBRIS = 22170070;
    public static final int HEROS_WILL_EVAN = 22171004;
    public static final int DRAGON_FURY = 22170074;

    //Returns
    public static final int RETURN_FLASH = 22110013; // Return after Wind Skills (Mob Debuff)
    public static final int RETURN_DIVE = 22140013; // Return Dive (Buff)
    public static final int RETURN_FLAME = 22170064; // Return Flame (Flame  AoE)
    public static final int RETURN_FLAME_TILE = 22170093; // Return Flames Tile


    //Evan Attacks
    public static final int MANA_BURST_I = 22001010;
    public static final int MANA_BURST_II = 22110010;
    public static final int MANA_BURST_III = 22140010;
    public static final int MANA_BURST_IV_1 = 22170060;
    public static final int MANA_BURST_IV_2 = 22170061;
    public static final int WIND_CIRCLE = 22111011;
    public static final int THUNDER_CIRCLE = 22141011;
    public static final int EARTH_CIRCLE = 22171062;
    public static final int DARK_FOG = 22171095;

    //Final Attack
    public static final int DRAGON_SPARK = 22000015;
    public static final int ADV_DRAGON_SPARK = 22110021;

    private int prevSkill = 0;
    private Dragon dragon;

    private static final int[] addedSkills = new int[]{
            INHERITED_WILL,
            BACK_TO_NATURE,
    };


    public Evan(Char chr) {
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

    public void spawnMir() {
        Dragon dragon = getDragon();
        dragon.resetToPlayer();
    }

    public int getEvanSkill(int skillID) {
        switch (skillID) {
            case MANA_BURST_I:
            case MANA_BURST_II:
            case MANA_BURST_III:
            case MANA_BURST_IV_1:
            case MANA_BURST_IV_2:
            case WIND_CIRCLE:
            case THUNDER_CIRCLE:
            case EARTH_CIRCLE:
            case DARK_FOG:
                return 1;

        }
        return skillID;
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isEvan(id);
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
        if (hasHitMobs) {
            // Partners
            if (getEvanSkill(skillID) != 1) {
                givePartnersBuff(skillID);
            }
            if (chr.hasSkill(MAGIC_DEBRIS)) {
                createWreckage(attackInfo);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
        }

        super.handleAttack(c, attackInfo);
    }

    public void givePartnersBuff(int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(PARTNERS);
        Option o = new Option();
        Option o1 = new Option();
        if (tsm.getOptByCTSAndSkill(Stance, PARTNERS) == null) {
            prevSkill = skillID;
            o.nReason = PARTNERS;
            o.nValue = si.getValue(indieDamR, 1);
            o.tTerm = 3;
            tsm.putCharacterStatValue(IndieDamR, o);
            o1.nOption = si.getValue(stanceProp, 1);
            o1.rOption = PARTNERS;
            o1.tOption = 3;
            tsm.putCharacterStatValue(Stance, o1);
            tsm.sendSetStatPacket();
        }
    }


    private void createWreckageForceAtom() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(getDebrisSkill());
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Mob> lifes = field.getMobsInRect(rect);
        if (lifes.size() <= 0) {
            return;
        }
        for (Wreckage wreckage : field.getWreckageByChrId(chr.getId())) {
            if (wreckage.getField() != chr.getField()) {
                continue;
            }
            Life life = Util.getRandomFromCollection(lifes);
            int mobID = (life).getObjectId();
            ForceAtomEnum fae = ForceAtomEnum.WRECKAGE;
            if (chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
                fae = ForceAtomEnum.ADV_WRECKAGE;
            }
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 15, 10,
                    0, 200, Util.getCurrentTime(), 1, 0,
                    wreckage.getPosition());
            chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                    true, mobID, getDebrisSkill(), forceAtomInfo, new Rect(), 0, 300,
                    life.getPosition(), getDebrisSkill(), life.getPosition(), 0));
        }
        chr.write(FieldPacket.delWreckage(chr, field.getWreckageByChrId(chr.getId())));
    }

    private void createWreckage(AttackInfo attackInfo) {
        if (SkillConstants.isEvanFusionSkill(attackInfo.skillId) && (attackInfo.skillId != MAGIC_DEBRIS && attackInfo.skillId != ENHANCED_MAGIC_DEBRIS)) {
            Field field = chr.getField();
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                if (field.getWreckage().size() < getMaxDebris()) {
                    Wreckage wreckage = Wreckage.getWreckageBy(chr, getDebrisSkill(), mob.getPosition(), 30000, 1);
                    field.spawnWreckage(chr, wreckage);
                }
            }
        }
    }

    private int getMaxDebris() {
        Skill skill = null;
        if (chr.hasSkill(MAGIC_DEBRIS)) {
            skill = chr.getSkill(MAGIC_DEBRIS);
        }
        if (chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
            skill = chr.getSkill(ENHANCED_MAGIC_DEBRIS);
        }
        if (skill == null) {
            return 0;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        return si.getValue(x, slv);
    }

    private int getDebrisSkill() {
        int skill = 0;
        if (chr.hasSkill(MAGIC_DEBRIS)) {
            skill = MAGIC_DEBRIS;
        }
        if (chr.hasSkill(ENHANCED_MAGIC_DEBRIS)) {
            skill = ENHANCED_MAGIC_DEBRIS;
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

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(DRAGON_SPARK)) {
            skill = chr.getSkill(DRAGON_SPARK);
        }
        if (chr.hasSkill(ADV_DRAGON_SPARK)) {
            skill = chr.getSkill(ADV_DRAGON_SPARK);
        }
        return skill;
    }

    public Dragon getDragon() {
        if (dragon == null && chr.getJob() != JobConstants.JobEnum.EVAN_NOOB.getJobId()) {
            dragon = new Dragon(0);
            dragon.setOwner(chr);
        }
        return dragon;
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
            case RETURN_FLAME:
                SkillInfo rft = SkillData.getSkillInfoById(RETURN_FLAME_TILE);
                AffectedArea aa = AffectedArea.getPassiveAA(chr, RETURN_FLAME_TILE, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(rft.getRects().get(0)));
                chr.getField().spawnAffectedArea(aa);
                break;
            case RETURN_FLASH:
                SkillInfo rflash = SkillData.getSkillInfoById(RETURN_FLASH);
                Rect rect = new Rect(       //Skill itself doesn't give a Rect
                        new Position(
                                chr.getPosition().deepCopy().getX() - 300,
                                chr.getPosition().deepCopy().getY() - 300),
                        new Position(
                                chr.getPosition().deepCopy().getX() + 300,
                                chr.getPosition().deepCopy().getY() + 300)
                );
                o1.nOption = rflash.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = rflash.getValue(time, slv);
                for (Life life : chr.getField().getLifesInRect(rect)) {
                    if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                        Mob mob = (Mob) life;
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);

                    }
                }
                break;
            case MAGIC_DEBRIS:
            case ENHANCED_MAGIC_DEBRIS:
                createWreckageForceAtom();
                break;
            case HEROS_WILL_EVAN:
                tsm.removeAllDebuffs();
                break;
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(MagicGuard, o1);
                break;
            case MAGIC_BOOSTER:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case RETURN_DIVE:
                o1.nReason = skillID;
                o1.nValue = 1; //si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case ELEMENTAL_DECREASE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case BLESSING_OF_THE_ONYX:
                o1.nOption = si.getValue(emad, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EMAD, o1);
                o3.nOption = si.getValue(epdd, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPDD, o3);
                break;
            case MAPLE_WARRIOR_EVAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_EVAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case SUMMON_ONYX_DRAGON:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case DRAGON_MASTER:
                TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicleExpire);

                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);

                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                o2.xOption = 0;
                tsm.putCharacterStatValue(NewFlying, o2);

                tsb.setNOption(1939007);
                tsb.setROption(skillID);
                tsb.setDynamicTermSet(true);
                tsb.setExpireTerm(10);
                tsm.putCharacterStatValue(RideVehicleExpire, tsb.getOption());
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setPosMap(900010000);
    }




    @Override
    public void handleLevelUp() {
     //   super.handleLevelUp();
        short level = chr.getLevel();
        Map<Stat, Object> stats = new HashMap<>();
        if (level > 10) {
            chr.addStat(Stat.ap, 5);
            stats.put(Stat.ap, (short) chr.getStat(Stat.ap));
        } else if (level < 10) {
            if (level >= 6) {
                chr.addStat(Stat.str, 4);
                chr.addStat(Stat.dex, 1);
            } else {
                chr.addStat(Stat.str, 5);
            }
            stats.put(Stat.str, (short) chr.getStat(Stat.str));
            stats.put(Stat.dex, (short) chr.getStat(Stat.dex));
        }
        if (level >= 50) {
            chr.addHonorExp(700 + ((chr.getLevel() - 50) / 10) * 100);
        }
        int sp = SkillConstants.getBaseSpByLevel(level);
        // Double SP gain on levels ending with 3/6/9 after level 100
        if ((level % 10) % 3 == 0 && level > 100) {
            sp *= 2;
        }
        chr.addSpToJobByCurrentLevel(sp);
        if (JobConstants.isExtendSpJob(chr.getJob())) {
            stats.put(Stat.sp, chr.getAvatarData().getCharacterStat().getExtendSP());
        } else {
            stats.put(Stat.sp, (short) chr.getAvatarData().getCharacterStat().getSp());
        }
        int linkSkill = SkillConstants.getLinkSkillByJob(chr.getJob());
        byte linkSkillLevel = (byte) SkillConstants.getLinkSkillLevelByCharLevel(level);
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
        }

        // Add server-sided increments for hp/mp then modify based on job passives
        int[][] incVal = GameConstants.getIncValArray(chr.getJob());
        if (incVal != null) {
            chr.addStat(Stat.mhp, incVal[0][1]);
            stats.put(Stat.mhp, chr.getStat(Stat.mhp));
            if (!JobConstants.isNoManaJob(chr.getJob())) {
                chr.addStat(Stat.mmp, incVal[3][0]);
                stats.put(Stat.mmp, chr.getStat(Stat.mmp));
            }
            chr.recalcStats(EnumSet.of(BaseStat.mhp, BaseStat.mmp));
        } else {
            chr.chatMessage("Unhandled HP/MP job " + chr.getJob());
        }
        chr.write(WvsContext.statChanged(stats));
        chr.heal(chr.getMaxHP());
        chr.healMP(chr.getMaxMP());

        short job = -1;
        switch (chr.getLevel()) {
            case 10:
                job = JobConstants.JobEnum.EVAN_1.getJobId();
                break;
            case 30:
                job = JobConstants.JobEnum.EVAN_2.getJobId();
                chr.addHotTimeReward(2435851, HotTimeRewardType.GAME_ITEM, 1, 0, 0,0,"Gift for reaching level 30."); // Pearl Weapon
                chr.addHotTimeReward(2438907, HotTimeRewardType.GAME_ITEM, 1, 0,0,0,"Gift for reaching level 30."); // Pearl Armor
                break;
            case 60:
                job = JobConstants.JobEnum.EVAN_3.getJobId();
                break;
            case 100:
                job = JobConstants.JobEnum.EVAN_4.getJobId();
                break;
            case 200:
                chr.chatMessage("Congratulations on reaching level 200");
                chr.chatMessage("You've unlocked the V Matrix!");
                chr.chatMessage("I've given you some Nodestones to help you on your adventure!");
                chr.getQuestManager().completeQuest(QuestConstants.FIFTH_JOB_QUEST);
                chr.addHotTimeReward(2435902, HotTimeRewardType.GAME_ITEM, 100, 0,0,0,"Gift for reaching level 200."); // Nodestones
                break;

        }
        if (job != -1) {
            chr.setJob(job);
            chr.setStatAndSendPacket(Stat.job, job);
            chr.addSpToJobByCurrentJob(5);
        }

    }
}