package net.swordie.ms.client.jobs.nova;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class AngelicBuster extends Job {
    //AB Beginner Skills
    public static final int DRESS_UP = 60011222;
    public static final int SOUL_BUSTER = 60011216;
    public static final int HYPER_COORDINATE = 60011221; // removed from wz?
    public static final int GRAPPLING_HEART = 60011218;
    public static final int DAY_DREAMER = 60011220;
    public static final int TRUE_HEART_INHERITANCE = 60010217;
    public static final int TERMS_AND_CONDITIONS = 60011219;

    public static final int AB_NORMAL_ATTACK = 60011216;

    //1st Job
    public static final int STAR_BUBBLE = 65001100;
    public static final int MELODY_CROSS = 65001002; //Buff

    //2nd job
    public static final int LOVELY_STING = 65101100;
    public static final int LOVELY_STING_EXPLOSION = 65101006;
    public static final int PINK_PUMMEL = 65101001;
    public static final int POWER_TRANSFER = 65101002; //Buff

    //3rd Job
    public static final int SOUL_SEEKER = 65111100;
    public static final int SOUL_SEEKER_ATOM = 65111007;
    public static final int SHINING_STAR_BURST = 65111101;
    public static final int HEAVENLY_CRASH = 65111002;
    public static final int IRON_BLOSSOM = 65111004; //Buff

    //4th Job
    public static final int CELESTIAL_ROAR = 65121100;
    public static final int TRINITY = 65121101; //TODO Recharge Attack
    //65121101 - Trinity -combo count-
    public static final int FINALE_RIBBON = 65121002;
    public static final int STAR_GAZER = 65121004; //Buff
    public static final int NOVA_WARRIOR_AB = 65121009; //Buff
    public static final int SOUL_SEEKER_EXPERT = 65121011; //ON/OFF Buff
    public static final int NOVA_TEMPERANCE_AB = 65121010;


    //Hypers
    public static final int PRETTY_EXALTATION = 65121054;
    public static final int FINAL_CONTRACT = 65121053;


    //Affinity Heart Passives
    public static final int AFFINITY_HEART_I = 65000003;
    public static final int AFFINITY_HEART_II = 65100005;
    public static final int AFFINITY_HEART_III = 65110006;
    public static final int AFFINITY_HEART_IV = 65120006;

    // V skills
    public static final int SPARKLE_BURST = 400051011;
    public static final int SUPER_STAR_SPOTLIGHT = 400051018;
    public static final int SUPER_STAR_SPOTLIGHT_2 = 400051027; // number value
    public static final int MIGHTY_MASCOT = 400051046;
    private static final short INSTANT_RECHARGE_LEVEL = 140;


    private static final int[] addedSkills = new int[]{
            DRESS_UP,
            SOUL_BUSTER,
            GRAPPLING_HEART,
            DAY_DREAMER,
            TRUE_HEART_INHERITANCE,
    };


    private int affinityHeartIIcounter = 0;
    private int affinityHeartIIIcounter = 0;

    public enum MightMascotSkillTypes {
        TwinkleStar(10),
        MagicalBalloon(11),
        ShinyBubbleBreath(12),
        ;

        int val;

        MightMascotSkillTypes(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public AngelicBuster(Char chr) {
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
        return JobConstants.isAngelicBuster(id);
    }

    public void giveSpotlightBuff(boolean giveBuff, int stack) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(SUPER_STAR_SPOTLIGHT)
                || !tsm.hasStat(CharacterTemporaryStat.SpotLight)
                || stack > 3
                || stack < 0) {
            return;
        }
        Skill skill = chr.getSkill(SUPER_STAR_SPOTLIGHT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        if (giveBuff) {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            Option o4 = new Option();
            Option o5 = new Option();

            o1.nValue = stack * si.getValue(w, slv);
            o1.nReason = SUPER_STAR_SPOTLIGHT_2;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndieAsrR, o1);
            o2.nValue = stack * si.getValue(v, slv);
            o2.nReason = SUPER_STAR_SPOTLIGHT_2;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndieCr, o2);
            o3.nValue = stack * si.getValue(q, slv);
            o3.nReason = SUPER_STAR_SPOTLIGHT_2;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStance, o3);
            o4.nValue = stack * si.getValue(s, slv);
            o4.nReason = SUPER_STAR_SPOTLIGHT_2;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o4);
            o5.nOption = stack;
            o5.rOption = SUPER_STAR_SPOTLIGHT_2;
            o5.xOption = stack;
            tsm.putCharacterStatValue(CharacterTemporaryStat.ExtraSkillCTS, o5);
        }
        tsm.sendSetStatPacket();
    }


    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (cts == CharacterTemporaryStat.SpotLight) {
            tsm.removeStatsBySkill(SUPER_STAR_SPOTLIGHT_2);
        }

        super.handleRemoveCTS(cts);
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
            // Sparkle Burst
            if (attackInfo.skillId != 0
                    && attackInfo.skillId != SOUL_SEEKER_ATOM
                    && attackInfo.skillId != SPARKLE_BURST) {
                createSparkleBurstForceAtom(attackInfo);
            }

            //Soul Seeker Recreation
            if (attackInfo.skillId == SOUL_SEEKER_ATOM) {
                //recreateSoulSeekerForceAtom(attackInfo);
            }

            //Soul Seeker Expert
            if (attackInfo.skillId != SOUL_SEEKER_ATOM) {
                soulSeekerExpert(skillID, slv, attackInfo);
            }


            //Recharging System
            if (Util.succeedProp(getRechargeProc(attackInfo))) {
                rechargeABSkills();
                affinityHeartIIIcounter = 0;
            } else {

                //Affinity Heart IV passive
                if (chr.hasSkill(AFFINITY_HEART_IV) && Util.succeedProp(getRechargeProc(attackInfo))) {
                    Skill ah4Skill = chr.getSkill(AFFINITY_HEART_IV);
                    byte ah4LV = (byte) ah4Skill.getCurrentLevel();
                    SkillInfo ah4SI = SkillData.getSkillInfoById(ah4Skill.getSkillId());
                    if (Util.succeedProp(ah4SI.getValue(x, ah4LV))) {
                        rechargeABSkills();
                        affinityHeartIIIcounter = 0;
                        affinityHeartIV(tsm, ah4LV);
                    }

                } else {

                    //Affinity Heart III passive
                    if (!chr.hasSkill(AFFINITY_HEART_III)) {
                        return;
                    }
                    affinityHeartIIIcounter++;
                    if (affinityHeartIIIcounter > 2) {
                        rechargeABSkills();
                    }
                }
            }

            affinityHeartII(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case AB_NORMAL_ATTACK:
                soulSeekerExpert(60011216, slv, attackInfo);
                break;
            case TRINITY:
            case 65121007:
            case 65121008:
                trinityBuff(tsm);
                break;
            case LOVELY_STING:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.wOption = chr.getId();
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Explosion, o1);
                }
                break;
            case FINALE_RIBBON:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.FinalDmgReceived, o1); //TODO Check if this is the Correct MobStat
                }
                break;
            case CELESTIAL_ROAR:    //Stun Debuff
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
            case 65101006:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {

                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.removeMobStat(MobStat.Explosion, false);
                }
                break;
            case SPARKLE_BURST:
                chr.getField().removeLife(getSparkleBurstSummon());
                tsm.removeStatsBySkill(SPARKLE_BURST);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void soulSeekerExpert(int skillID, int slv, AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(CharacterTemporaryStat.AngelicBursterSoulSeeker)) {
            SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER_EXPERT);
            int anglenum;
            if (new Random().nextBoolean()) {
                anglenum = 50;
            } else {
                anglenum = 130;
            }
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int TW1prop = si.getValue(prop, slv);
                if (attackInfo.skillId == CELESTIAL_ROAR) {
                    TW1prop += si.getValue(z, slv);
                }
                if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.IndieIgnoreMobpdpR, PRETTY_EXALTATION) != null) {
                    TW1prop += 15;
                }
                if (Util.succeedProp(TW1prop)) {
                    int mobID = mai.mobId;
                    ForceAtomEnum fae = ForceAtomEnum.AB_ORB;
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 40,
                            anglenum, 0, Util.getCurrentTime(), 1, 0,
                            new Position(5, 0)); //Slightly behind the player
                    chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                            true, mobID, SOUL_SEEKER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), SOUL_SEEKER_ATOM, mob.getPosition(), 0));
                }
            }
        }
    }


    private void createSoulSeekerForceAtom() {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(SOUL_SEEKER);
        int slv = chr.getSkillLevel(SOUL_SEEKER);
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Mob> lifes = field.getMobsInRect(rect);
        if (lifes.size() <= 0) {
            return;
        }

        List<Mob> bossLifes = field.getBossMobsInRect(rect);
        Life life = Util.getRandomFromCollection(lifes);
        if (bossLifes.size() > 0) {
            life = Util.getRandomFromCollection(bossLifes);
        }
        int fImpact = new Random().nextInt(4) + 29;
        int angle = new Random().nextInt(10);
        int mobID = life.getObjectId();
        ForceAtomEnum fae = ForceAtomEnum.AB_ORB;
        ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, 40,
                angle, 250, Util.getCurrentTime(), 0, 0,
                new Position());
        ForceAtom fa = new ForceAtom(false, chr.getId(), chr.getId(), fae,
                true, mobID, SOUL_SEEKER_ATOM, fai, new Rect(), 0, 0,
                new Position(), SOUL_SEEKER_ATOM, new Position(), 0);
        fa.setMaxRecreationCount(si.getValue(z, slv));
        fa.setRecreationChance(si.getValue(s, slv));
        chr.createForceAtom(fa);
    }

    private void createSparkleBurstForceAtom(AttackInfo attackInfo) {
        Field field = chr.getField();
        if (!chr.hasSkill(SPARKLE_BURST) || getSparkleBurstSummon() == null) {
            return;
        }
        Rect rect = new Rect(
                new Position(
                        -1500,
                        -1500),
                new Position(
                        1500,
                        1500)
        );
        ForceAtomEnum fae = ForceAtomEnum.SPARKLE_BURST_1;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                int fImpact = new Random().nextInt(35) + 15;
                int sImpact = new Random().nextInt(2) + 5;
                int inc = new Random().nextInt(3) + 1;
                int delay = new Random().nextInt(200) + 100;
                ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), inc, fImpact, sImpact, chr.isLeft() ? 270 : 90, delay, Util.getCurrentTime(), 0, 0, new Position());
                chr.createForceAtom(new ForceAtom(true, chr.getId(), chr.getId(), fae,
                        false, mob.getObjectId(), SPARKLE_BURST, fai, mob.getRectAround(rect), 0, 0,
                        mob.getPosition(), 0, mob.getPosition(), 0));
            }
        }
    }

    private void incrementSparkleBurstEnergy() {
        incrementSparkleBurstEnergy(1);
    }

    private void incrementSparkleBurstEnergy(int amount) {
        Summon summon = getSparkleBurstSummon();
        if (!chr.hasSkill(SPARKLE_BURST) || summon == null) {
            return;
        }

        summon.incCount(amount);

        if (summon.getCount() == 50 || summon.getCount() == 120) {
            incrementSparkleBurstState();
        }
    }

    private void incrementSparkleBurstState() {
        Summon summon = getSparkleBurstSummon();
        if (!chr.hasSkill(SPARKLE_BURST) || summon == null || summon.getState() > 2) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();

        if (summon.getState() == 0) {
            chr.getField().broadcastPacket(Summoned.summonedSkill(summon, (byte) 16, SPARKLE_BURST));
            summon.incState();
        } else if (summon.getState() == 1) {
            chr.getField().broadcastPacket(Summoned.summonedSkill(summon, (byte) 17, SPARKLE_BURST));
            summon.incState();
        }
        o.nOption = summon.getState() + 1;
        o.rOption = SPARKLE_BURST;
        o.tOption = (int) tsm.getRemainingTime(CharacterTemporaryStat.IndiePMdR, SPARKLE_BURST);
        o.setInMillis(true);
        tsm.putCharacterStatValue(CharacterTemporaryStat.SparkleBurstStage, o, true);

        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.NotDamaged, SPARKLE_BURST) != null) {
            o1.nOption = 1;
            o1.rOption = SPARKLE_BURST;
            o1.tOption = (int) (tsm.getRemainingTime(CharacterTemporaryStat.NotDamaged, SPARKLE_BURST) + 2000);
            o1.setInMillis(true);
            tsm.putCharacterStatValue(CharacterTemporaryStat.NotDamaged, o1);
        }

        tsm.sendSetStatPacket();
    }


    public void handleForceAtomCollision(int faKey, int skillId, int mobObjId, Position position, InPacket inPacket) {
        switch (skillId) {
            case SPARKLE_BURST:
                incrementSparkleBurstEnergy();
                break;
        }

        super.handleForceAtomCollision(faKey, skillId, mobObjId, position, inPacket);
    }

    private Summon getSparkleBurstSummon() {
        return chr.getField().getSummons().stream().filter(s -> s.getChr() == chr && s.getSkillID() == SPARKLE_BURST).findAny().orElse(null);
    }

    private int getRechargeProc(AttackInfo attackInfo) {
        if (chr.getLevel() > INSTANT_RECHARGE_LEVEL) {
            return 100;
        }
        Skill skill = chr.getSkill(SkillConstants.getActualSkillIDfromSkillID(attackInfo.skillId));
        if (skill == null) {
            return 0;
        }
        int slv = skill.getCurrentLevel();
        SkillInfo rechargeInfo = SkillData.getSkillInfoById(skill.getSkillId());
        int rechargeproc = rechargeInfo.getValue(onActive, slv);
        if (rechargeproc == 0) {
            return rechargeproc;
        }
        if (chr.hasSkill(AFFINITY_HEART_I)) {
            SkillInfo ah1 = SkillData.getSkillInfoById(AFFINITY_HEART_I);
            int extraRecharge = ah1.getValue(x, slv);
            rechargeproc += (extraRecharge - 10);
        }

        return rechargeproc;
    }

    private void affinityHeartII(AttackInfo attackInfo) {
        if (!chr.hasSkill(AFFINITY_HEART_II)) {
            return;
        }
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            if (affinityHeartIIcounter >= 10) {
                rechargeABSkills();
                affinityHeartIIcounter = 0;
                affinityHeartIIIcounter = 0;
            } else {
                long totaldmg = Arrays.stream(mai.damages).sum();
                if (totaldmg > mob.getHp()) {
                    affinityHeartIIcounter++;
                }
            }
        }
    }

    private void affinityHeartIV(TemporaryStatManager tsm, int slv) {
        if (!chr.hasSkill(AFFINITY_HEART_IV)) {
            return;
        }
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.IndieDamR, AFFINITY_HEART_IV) == null) {
            Skill skill = chr.getSkill(AFFINITY_HEART_IV);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            Option o = new Option();
            o.nValue = si.getValue(y, slv);
            o.nReason = skill.getSkillId();
            o.tTerm = 5;
            tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o);
            tsm.sendSetStatPacket();
        }
    }

    private void trinityBuff(TemporaryStatManager tsm) {
        Option o1 = new Option();
        Option o2 = new Option();
        int amount = 1;
        if (tsm.hasStat(Trinity)) {
            amount = tsm.getOption(Trinity).mOption;
            if (amount < 3) {
                amount++;
            }
        }
        o1.nOption = 5;
        o1.rOption = TRINITY;
        o1.tOption = 5;
        o1.mOption = amount;
        tsm.putCharacterStatValue(Trinity, o1);
        o2.nValue = (5 * amount);
        o2.nReason = TRINITY;
        o2.tTerm = 5;
        tsm.putCharacterStatValue(IndieDamR, o2);
        tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
        tsm.sendSetStatPacket();
    }

    private void rechargeABSkills() {
        Effect effect = Effect.createABRechargeEffect();
        chr.write(UserPacket.effect(effect));
        chr.write(UserLocal.resetStateForOffSkill());
        doMightyMascotAttack();
    }

    private Summon getMightyMascot() {
        return chr.getField().getSummonByChrAndSkillId(chr, MIGHTY_MASCOT);
    }

    private void doMightyMascotAttack() {
        SkillInfo si = SkillData.getSkillInfoById(MIGHTY_MASCOT);
        int slv = chr.getSkillLevel(MIGHTY_MASCOT);
        Summon mightyMascot = getMightyMascot();
        if (mightyMascot == null) {
            return;
        }
        if (new Random().nextBoolean()) {
            chr.getField().broadcastPacket(Summoned.summonedAssistAttackRequest(mightyMascot, MightMascotSkillTypes.MagicalBalloon.getVal()));
        } else {
            chr.getField().broadcastPacket(Summoned.summonedAssistAttackRequest(mightyMascot, MightMascotSkillTypes.TwinkleStar.getVal()));
        }
        int maxStack = si.getValue(s, slv);
        if (mightyMascot.getCount() < maxStack) {
            mightyMascot.setCount(mightyMascot.getCount() + 1);
            increaseMightyMascotSummonTerm();
        }
    }

    private void increaseMightyMascotSummonTerm() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option prevOpt = tsm.getOptByCTSAndSkill(IndieSummon, MIGHTY_MASCOT);
        SkillInfo si = SkillData.getSkillInfoById(MIGHTY_MASCOT);
        int slv = chr.getSkillLevel(MIGHTY_MASCOT);
        int termInc = si.getValue(q, slv);
        o.nValue = prevOpt.nValue;
        o.nReason = prevOpt.nReason;
        o.tTerm = (int) tsm.getRemainingTime(IndieSummon, MIGHTY_MASCOT) + termInc;
        o.summon = prevOpt.summon;
        o.setInMillis(true);
        tsm.putCharacterStatValue(IndieSummon, o, true);
        tsm.sendSetStatPacket();
    }

    public void doBubbleBreath() {
        Summon mightyMascot = getMightyMascot();
        if (mightyMascot == null || mightyMascot.isJaguarActive()) {
            return;
        }
        chr.getField().broadcastPacket(Summoned.assistSpecialAttackRequest(mightyMascot, MightMascotSkillTypes.ShinyBubbleBreath.getVal()));
        mightyMascot.setCount(0);
        mightyMascot.setJaguarActive(true); // placeholder for  Usage Check.
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
        switch (skillID) {
            case TERMS_AND_CONDITIONS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                break;
            case SOUL_SEEKER:
                createSoulSeekerForceAtom();
                createSoulSeekerForceAtom();
                break;
            case NOVA_TEMPERANCE_AB:
                tsm.removeAllDebuffs();
                break;
            case MELODY_CROSS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o1);
                break;
            case POWER_TRANSFER:
                o1.nOption = 1000;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(PowerTransferGauge, o1);
                break;
            case IRON_BLOSSOM:
                o1.nOption = si.getValue(prop, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o1);
                break;
            case STAR_GAZER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1); //Changed IncCriticalDamMax to SharpEyes
                o2.nOption = si.getValue(y, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IncCriticalDam, o2);
                break;
            case NOVA_WARRIOR_AB:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SOUL_SEEKER_EXPERT:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(AngelicBursterSoulSeeker, o1);
                break;
            case PRETTY_EXALTATION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBDR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o2);
                break;
            case FINAL_CONTRACT:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.CriticalBuff, o1);

                o2.nOption = si.getValue(asrR, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.AsrR, o2);
                tsm.putCharacterStatValue(CharacterTemporaryStat.TerR, o2);

                o3.nValue = si.getValue(indieStance, slv);
                o3.nReason = skillID;
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStance, o3);

                o4.nValue = si.getValue(indiePad, slv);
                o4.nReason = skillID;
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePAD, o4);
                break;
            case SUPER_STAR_SPOTLIGHT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SpotLight, o1);
                break;
            case SPARKLE_BURST:
                Summon summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.Attack);

                o1.nValue = 1;
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o1);
                chr.getField().spawnSummon(summon);

                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(z, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.NotDamaged, o2);

                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.SparkleBurstStage, o2);
                break;
            case MIGHTY_MASCOT:
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setAssistType(AssistType.SequenceAttack);
                summon.setSummonTerm(60000);
                o1.nReason = skillID;
                o1.nValue = 1;
                o1.summon = summon;
                o1.tTerm = si.getValue(time, slv) * 1000;
                o1.setInMillis(true);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSummon, o1, true);
                tsm.sendSetStatPacket();

                chr.getField().spawnSummon(summon);
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
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();

//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.ANGELIC_BUSTER_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.ANGELIC_BUSTER_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.ANGELIC_BUSTER_4.getJobId());
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.ANGELIC_BUSTER_1.getJobId());
        chr.addSpToJobByCurrentJob(5);

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Skills
        // Items
        Item secondary = ItemData.getItemDeepCopy(1352601); // Pink Soul Ring (Secondary)
        chr.forceUpdateSecondary(null, secondary);
    }
}