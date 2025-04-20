package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Kinesis extends Job {
    public static final int RETURN_KINESIS = 140001290;

    public static final int PSYCHIC_FORCE = 142001000;
    public static final int MENTAL_SHIELD = 142001007;
    public static final int ESP_BOOSTER = 142001003;
    public static final int ULTIMATE_METAL_PRESS = 142001002;

    public static final int PSYCHIC_BLAST_FWD = 142100000;
    public static final int PSYCHIC_BLAST_DOWN = 142100001;
    public static final int PSYCHIC_DRAIN = 142101009;
    public static final int PSYCHIC_ARMOR = 142101004;
    public static final int PURE_POWER = 142101005;
    public static final int ULTIMATE_DEEP_IMPACT = 142101003;

    public static final int PSYCHIC_ASSAULT_FWD = 142110000;
    public static final int PSYCHIC_ASSAULT_DOWN = 142110001;
    public static final int PSYCHIC_BULWARK = 142110009;
    public static final int PSYCHIC_REINFORCEMENT = 142111008;
    public static final int KINETIC_JAUNT = 142111010;
    public static final int ULTIMATE_TRAINWRECK = 142111007;
    public static final int KINETIC_COMBO = 142110011;
    public static final int MIND_TREMOR = 142111006;

    public static final int MIND_BREAK = 142121004;
    public static final int ULTIMATE_PSYCHIC_SHOT = 142120002;
    public static final int ULTIMATE_BPM = 142121005;
    public static final int PRESIDENTS_ORDERS = 142121016;
    public static final int PSYCHIC_CHARGER = 142121008;
    public static final int TELEPATH_TACTICS = 142121006;
    public static final int MIND_QUAKE = 142120003;
    public static final int CLEAR_MIND = 142121007;

    public static final int MENTAL_TEMPEST = 142121030;
    public static final int MENTAL_TEMPEST_END = 142120030;
    public static final int MENTAL_SHOCK = 142121031;
    public static final int MENTAL_OVERDRIVE = 142121032;

    // V skills
    public static final int PSYCHIC_TORNADO = 400021008;
    public static final int MIND_OVER_MATTER = 400021048;


    private static final int MAX_PP = 30;

    private static final int[] nonOrbSkills = new int[]{
            ULTIMATE_METAL_PRESS,
            ULTIMATE_DEEP_IMPACT,
            ULTIMATE_TRAINWRECK,
            ULTIMATE_BPM,
            ULTIMATE_PSYCHIC_SHOT,

            PSYCHIC_FORCE,
            PSYCHIC_BLAST_DOWN,
            PSYCHIC_BLAST_FWD,
            PSYCHIC_ASSAULT_DOWN,
            PSYCHIC_ASSAULT_FWD,
            PSYCHIC_DRAIN,
            MENTAL_TEMPEST,
            KINETIC_COMBO,
    };

    public Kinesis(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isKinesis(id);
    }

    public int getPP() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(KinesisPsychicPoint)) {
            return tsm.getOption(KinesisPsychicPoint).nOption;
        }
        return 0;
    }

    public void addPP(int amount) {
        int pp = getPP() + amount > MAX_PP ? MAX_PP : getPP() + amount;
        sendPPPacket(pp);
    }

    public void substractPP(int amount) {
        int pp = getPP() - amount < 0 ? 0 : getPP() - amount;
        sendPPPacket(pp);
    }

    private void sendPPPacket(int pp) {
        Option o = new Option();
        o.nOption = pp;
        o.rOption = JobConstants.JobEnum.KINESIS_4.getJobId();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.putCharacterStatValue(KinesisPsychicPoint, o);
        tsm.sendSetStatPacket();
    }


    public void applyMindAreaDebuff(int skillId, Position position, List<Mob> mobList) {
        Skill skill = chr.getSkill(skillId);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Rect rect = position.getRectAround(si.getFirstRect());

        int multiplier = skillId == MIND_QUAKE ? 3 : 2;
        int mobCount = mobList.size();

        Option o1 = new Option();
        Option o2 = new Option();
        o1.rOption = skillId;
        o1.tOption = si.getValue(time, slv);
        o2.rOption = skillId;
        o2.tOption = si.getValue(time, slv);

        mobList.forEach(m -> {
            if (rect.hasPositionInside(m.getPosition())) {
                MobTemporaryStat mts = m.getTemporaryStat();


                o1.nOption = -(multiplier * mobCount > si.getValue(s, slv) ? si.getValue(s, slv) : multiplier * mobCount);
                o2.nOption = multiplier * mobCount > si.getValue(s, slv) ? si.getValue(s, slv) : multiplier * mobCount;
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                mts.addStatOptionsAndBroadcast(MobStat.PsychicGroundMark, o2);
            }
        });
    }


    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        if (skill == null) {
            switch (attackInfo.skillId) {
                case PSYCHIC_ASSAULT_DOWN:
                    skill = chr.getSkill(PSYCHIC_ASSAULT_FWD);
                    break;
                case PSYCHIC_BLAST_DOWN:
                    skill = chr.getSkill(PSYCHIC_BLAST_FWD);
                    break;
            }
        }
        int skillID = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs && chr.hasSkill(KINETIC_COMBO) && attackInfo.skillId != KINETIC_COMBO) {
            createKineticOrbForceAtom(skillID, slv, attackInfo);
        }
        if (attackInfo.skillId != ULTIMATE_BPM &&
                attackInfo.skillId != ULTIMATE_METAL_PRESS &&
                attackInfo.skillId != ULTIMATE_TRAINWRECK &&
                attackInfo.skillId != MIND_OVER_MATTER &&
                attackInfo.skillId != ULTIMATE_PSYCHIC_SHOT
        ) {
            kinesisPPAttack(skillID, slv, si);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case PSYCHIC_FORCE:
            case PSYCHIC_BLAST_FWD:
            case PSYCHIC_BLAST_DOWN:
            case PSYCHIC_ASSAULT_FWD:
            case PSYCHIC_ASSAULT_DOWN:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case MIND_BREAK:
                int count = 0;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if (mob.isBoss()) {
                        count += si.getValue(x, slv);
                    } else {
                        count++;
                    }
                }
                count = count > si.getValue(w, slv) ? si.getValue(w, slv) : count;
                if (count <= 0) {
                    return;
                }
                o1.nValue = count * si.getValue(indiePMdR, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o1);
                tsm.sendSetStatPacket();
                break;
            case MENTAL_SHOCK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    if (!mob.isBoss()) {
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case MENTAL_TEMPEST:
                if (!tsm.hasStatBySkillId(skillID)) {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = 5;
                    tsm.putCharacterStatValue(NotDamaged, o1);
                    tsm.sendSetStatPacket();
                }
                break;
            case MENTAL_TEMPEST_END:
                tsm.removeStatsBySkill(MENTAL_TEMPEST);
                addPP(30);
                break;
            case ULTIMATE_DEEP_IMPACT:
                attackInfo.mobAttackInfo.forEach(s -> {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(s.mobId);
                    if (mob != null && !mob.isBoss()) {
                        mob.getTemporaryStat().removeBuffs();
                    }
                });
                break;
            case ULTIMATE_PSYCHIC_SHOT:
                o1.nOption = -si.getValue(x, slv);
                o1.rOption = ULTIMATE_PSYCHIC_SHOT;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void createKineticOrbForceAtom(int skillID, int slv, AttackInfo attackInfo) {
        if (Arrays.asList(nonOrbSkills).contains(skillID) || Arrays.asList(nonOrbSkills).contains(attackInfo.skillId)) {
            return;
        }
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(KINETIC_COMBO);
        int proc = si.getValue(prop, chr.getSkillLevel(KINETIC_COMBO));
        ForceAtomEnum fae = ForceAtomEnum.KINESIS_ORB_REAL;
        Rect rect = chr.getRectAround(new Rect(-500, -300, 200, 100));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();

        if (Util.succeedProp(proc) && field.getMobsInRect(rect).size() > 0) {
            Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
            targetList.add(mob.getObjectId());

            int ranStuff = new Random().nextInt(3);
            int fImpact = new Random().nextInt(31) + 20;
            int sImpact = new Random().nextInt(25) + 10;
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc() + ranStuff, fImpact, sImpact,
                    new Random().nextInt(360), new Random().nextInt(400) + 400, Util.getCurrentTime(), 0, 0,
                    new Position());
            faiList.add(fai);
        }

        if (faiList.size() > 0 && targetList.size() > 0) {
            ForceAtom fa = new ForceAtom(chr.getId(), fae, targetList, KINETIC_COMBO, faiList);
            chr.createForceAtom(fa);
        }
    }

    private void kinesisPPAttack(int skillID, int slv, SkillInfo si) {
        if (si == null) {
            if (skillID == 0) {
                addPP(1);
            }
            return;
        }
        int ppRec = si.getValue(ppRecovery, slv);
        addPP(ppRec);
        int ppCons = si.getValue(ppCon, slv);
        if (chr.getTemporaryStatManager().hasStat(KinesisPsychicOver)) {
            ppCons = ppCons / 2;
        }
        if (skillID == ULTIMATE_BPM) {
            ppCons = si.getValue(w, slv); // why nexon..
        }
        if (skillID == KINETIC_JAUNT) {
            ppCons = si.getValue(x, slv); // why nexon..
        }
        substractPP(ppCons);
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
        kinesisPPAttack(skillID, slv, si);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case PSYCHIC_CHARGER:
                int add = (MAX_PP - getPP()) / 2;
                addPP(add);
                break;
            case CLEAR_MIND:
                tsm.removeAllDebuffs();
                break;
            case ESP_BOOSTER:
                o1.nValue = -5; // si.getValue(indieBooster, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case MENTAL_SHIELD:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.nReason = skillID;
                    tsm.putCharacterStatValue(KinesisPsychicEnergeShield, o1);
                }
                break;
            case PSYCHIC_ARMOR:
            case PSYCHIC_BULWARK:
                int psyArmorSLV = chr.getSkillLevel(PSYCHIC_ARMOR);
                int t = SkillData.getSkillInfoById(PSYCHIC_ARMOR).getValue(time, psyArmorSLV);
                int e = SkillData.getSkillInfoById(PSYCHIC_ARMOR).getValue(er, psyArmorSLV);
                o1.nValue = si.getValue(indiePdd, slv);
                o1.nReason = skillID;
                o1.tTerm = t;
                tsm.putCharacterStatValue(IndieDEF, o1);
                o2.nValue = e;
                o2.nReason = skillID;
                o2.tTerm = t;
                tsm.putCharacterStatValue(IndieEVAR, o2);
                o3.nOption = si.getValue(stanceProp, slv);
                o3.rOption = skillID;
                o3.tOption = t;
                tsm.putCharacterStatValue(IndieStance, o3);
                break;
            case PURE_POWER:
                o1.nValue = si.getValue(indieDamR, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case PSYCHIC_REINFORCEMENT:
                o1.nValue = si.getValue(indieMadR, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMADR, o1);
                break;
            case PRESIDENTS_ORDERS:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case TELEPATH_TACTICS:
                o1.nValue = si.getValue(indieMad, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMADR, o1);
                o2.nValue = si.getValue(indieDamR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;
            case KINETIC_JAUNT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NewFlying, o1);
                break;
            case MENTAL_OVERDRIVE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(KinesisPsychicOver, o1);
                break;
            case PSYCHIC_TORNADO: // TODO  Increment as the Tornado is growing.
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PsychicTornado, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStatBySkillId(MENTAL_SHIELD)) {
            hitInfo.hpDamage = (int) (hitInfo.hpDamage * (tsm.getOption(KinesisPsychicEnergeShield).nOption / 100D));
            substractPP(1);
        }
        if (getPP() <= 0) {
            tsm.removeStatsBySkill(MENTAL_SHIELD);
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);

        Item item = ItemData.getItemDeepCopy(1353200); // Pawn Chess Piece
        item.setBagIndex(BodyPart.Shield.getVal());
        chr.getEquippedInventory().addItem(item);

        /*
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setLevel(10);
        cs.setMaxHp(400);
        cs.setHp(400);
        cs.setInt(58);
        cs.setAp(5);
         */
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();

//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.KINESIS_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.KINESIS_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.KINESIS_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353200)); // Pawn Chess Piece
        handleJobAdvance(JobConstants.JobEnum.KINESIS_1.getJobId());
        chr.addSpToJobByCurrentJob(5);

    }
}
