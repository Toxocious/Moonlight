package net.swordie.ms.client.jobs.adventurer.archer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class BowMaster extends Archer {
    public static final int SOUL_ARROW_BOW = 3101004;
    public static final int ARROW_BOMB = 3101005;
    public static final int BOW_BOOSTER = 3101002;
    public static final int FINAL_ATTACK_BOW = 3100001;
    public static final int QUIVER_CARTRIDGE = 3101009;
    public static final int QUIVER_CARTRIDGE_ATOM = 3100010;


    public static final int PHOENIX = 3111005;
    public static final int FLAME_SURGE = 3111003;
    public static final int FOCUSED_FURY = 3110012;
    public static final int MORTAL_BLOW_BOW = 3110001;
    public static final int ARROW_PLATTER = 3111013;
    public static final int EVASION_BOOST = 3110007;
    public static final int HOOKSHOT_BM = 3111010;
    public static final int RECKLESS_HUNT_BOW = 3111011;


    public static final int SHARP_EYES_BOW = 3121002;
    public static final int SHARP_EYES_BOW_PERSIST = 3120043;
    public static final int SHARP_EYES_BOW_GUARDBREAK = 3120044;
    public static final int SHARP_EYES_BOW_CRITICAL_CHANCE = 3120045;
    public static final int ILLUSION_STEP_BOW = 3121007;
    public static final int BINDING_SHOT = 3121014;
    public static final int ENCHANTED_QUIVER = 3121016;
    public static final int ENCHANTED_QUIVER_ATOM = 3120017;
    public static final int MAPLE_WARRIOR_BOW = 3121000;
    public static final int HEROS_WILL_BM = 3121009;
    public static final int EPIC_ADVENTURE_BOW = 3121053;
    public static final int ADVANCED_FINAL_ATTACK_BOW = 3120008;
    public static final int ARMOR_BREAK = 3120018;
    public static final int CONCENTRATION = 3121054;
    public static final int GRITTY_GUST = 3121052;


    // V skills
    public static final int STORM_OF_ARROWS = 400031002;
    public static final int STORM_OF_ARROWS_AA = 400030002;
    public static final int INHUMAN_SPEED = 400031020;
    public static final int INHUMAN_SPEED_2 = 400031021;
    public static final int QUIVER_BARRAGE = 400031028;
    public static final int QUIVER_BARRAGE_ATOM = 400031029;



    private int inhumanSpeedCounter = 0;
    private ScheduledFuture stormArrowTimer;
    private QuiverCartridge quiverCartridge;
    private long lastQuiverBarrage = Long.MIN_VALUE;


    public BowMaster(Char chr) {
        super(chr);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isBowMaster(id);
    }


    public void createInhumanSpeedForceAtom(int mobId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Mob mob = (Mob) chr.getField().getLifeByObjectID(mobId);
        if (!chr.hasSkill(INHUMAN_SPEED)
                || (tsm.getOptByCTSAndSkill(ExtraSkillCTS, INHUMAN_SPEED) == null && tsm.getOptByCTSAndSkill(ExtraSkillCTS, INHUMAN_SPEED_2) == null)
                || mob == null) {
            return;
        }

        int randomfImpact = new Random().nextInt(1) + 35;
        int randomsImpact = new Random().nextInt(1) + 5;

        ForceAtomEnum fae = ForceAtomEnum.INHUMAN_SPEED;
        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), randomfImpact, randomsImpact,
                chr.isLeft() ? 270 : 90, 0, Util.getCurrentTime(), 1, 0,
                new Position());
        chr.createForceAtom(new ForceAtom(chr.getId(), fae, mob.getObjectId(), INHUMAN_SPEED_2, forceAtomInfo));
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

    private void quiverCartridge(TemporaryStatManager tsm, AttackInfo attackInfo, int slv) {
        Char chr = c.getChr();
        if (quiverCartridge == null) {
            return;
        }
        Skill skill = chr.hasSkill(ENCHANTED_QUIVER) ? chr.getSkill(ENCHANTED_QUIVER)
                : chr.getSkill(QUIVER_CARTRIDGE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            if (tsm.getOptByCTSAndSkill(QuiverBarrage, QUIVER_BARRAGE) != null) {
                bloodArrowEffect(si, (byte) slv);
                mts.createAndAddBurnedInfo(chr, skill);
                quiverCartridge.decrementAmount();
                magicArrowEffect(mob, si, (byte) slv);
            } else {
                switch (quiverCartridge.getType()) {
                    case 1: // Blood
                        bloodArrowEffect(si, (byte) slv);
                        break;
                    case 2: // Poison
                        int maxStacks = si.getValue(dotSuperpos, slv);
                        mts.createAndAddBurnedInfo(chr, QUIVER_CARTRIDGE, slv, maxStacks);
                        quiverCartridge.decrementAmount();
                        break;
                    case 3: // Magic
                        magicArrowEffect(mob, si, (byte) slv);
                        break;
                }
            }
        }
        tsm.putCharacterStatValue(QuiverCatridge, quiverCartridge.getOption());
        tsm.sendSetStatPacket();
    }

    private void bloodArrowEffect(SkillInfo si, int slv) {
        if (Util.succeedProp(si.getValue(w, slv))) {
            quiverCartridge.decrementAmount();
            int healrate = si.getValue(x, slv);
            chr.heal(chr.getHPPerc(healrate));
        }
    }

    private void magicArrowEffect(Mob mob, SkillInfo si, int slv) {
        if (Util.succeedProp(si.getValue(u, slv))) {
            quiverCartridge.decrementAmount();
            ForceAtomEnum fae = ForceAtomEnum.BM_ARROW;
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 13, 12,
                    (int) Util.getAngleOfTwoPositions(chr.getPosition(), mob.getPosition()), 150, Util.getCurrentTime(), 1, 0,
                    new Position());
            chr.createForceAtom(new ForceAtom(chr.getId(), fae, mob.getObjectId(), chr.hasSkill(ENCHANTED_QUIVER) ? ENCHANTED_QUIVER_ATOM : QUIVER_CARTRIDGE_ATOM, forceAtomInfo));
        }
    }

    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
            quiverCartridge(chr.getTemporaryStatManager(), attackInfo, slv);
            incrementFocusedFury();
            incrementMortalBlow();

            if (attackInfo.skillId != QUIVER_BARRAGE_ATOM
                    && chr.hasSkill(QUIVER_BARRAGE)
                    && tsm.getOptByCTSAndSkill(QuiverBarrage, QUIVER_BARRAGE) != null
                    && (lastQuiverBarrage + 5000 < System.currentTimeMillis())) {
                createQuiverBarrageForceAtom(attackInfo);
            }

            if (attackInfo.skillId != INHUMAN_SPEED && attackInfo.skillId != INHUMAN_SPEED_2) {
                incrementInhumanSpeedCounter();
            }

            procArmorBreak();
            if (chr.hasSkill(ARMOR_BREAK) && chr.hasSkillOnCooldown(ARMOR_BREAK)) {
                chr.reduceSkillCoolTime(ARMOR_BREAK, 1000);
            }
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case ARROW_BOMB:
                o1.nOption = 1;
                o1.rOption = skillID;
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
            case PHOENIX:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 3;
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
            case FLAME_SURGE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case BINDING_SHOT:
                o1.nOption = si.getValue(s, slv); // Already negative in SI
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o2.nOption = -si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.DebuffHealing, o2);
                }
                break;
            case GRITTY_GUST:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void incrementMortalBlow() {
        Skill skill = chr.getSkill(MORTAL_BLOW_BOW);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int amount = 1;
        if (tsm.hasStat(BowMasterMortalBlow)) {
            amount = tsm.getOption(BowMasterMortalBlow).nOption;
            if (amount < si.getValue(x, slv)) {
                amount++;
            } else {
                tsm.removeStatsBySkill(MORTAL_BLOW_BOW);
                return;
            }
        }
        o.nOption = amount;
        o.rOption = MORTAL_BLOW_BOW;
        tsm.putCharacterStatValue(BowMasterMortalBlow, o);
        tsm.sendSetStatPacket();
    }

    private void incrementInhumanSpeedCounter() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(INHUMAN_SPEED)
                || !chr.hasSkillOnCooldown(INHUMAN_SPEED)
                || tsm.getOptByCTSAndSkill(ExtraSkillCTS, INHUMAN_SPEED) != null
                || tsm.getOptByCTSAndSkill(ExtraSkillCTS, INHUMAN_SPEED_2) != null) {
            return;
        }

        if (inhumanSpeedCounter < 10) {
            inhumanSpeedCounter++;
        } else {
            inhumanSpeedCounter = 0;
            giveInhumanPassiveBuff();
        }
    }

    private void giveInhumanPassiveBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(INHUMAN_SPEED);
        int slv = chr.getSkillLevel(INHUMAN_SPEED);
        o1.nOption = 1;
        o1.rOption = INHUMAN_SPEED_2;
        o1.tOption = si.getValue(q, slv);
        tsm.putCharacterStatValue(ExtraSkillCTS, o1);
        tsm.sendSetStatPacket();
    }

    private void createQuiverBarrageForceAtom(AttackInfo attackInfo) {
        Field field = chr.getField();
        int firstImpact = new Random().nextInt(15) + 35;
        int secondImpact = new Random().nextInt(2) + 5;
        ForceAtomEnum fae = ForceAtomEnum.QUIVER_FULL_BURST;
        for (int i = 0; i < 6; i++) {
            Mob mob = (Mob) field.getLifeByObjectID(Util.getRandomFromCollection(attackInfo.mobAttackInfo).mobId);
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), firstImpact, secondImpact,
                    chr.isLeft() ? 320 : 40, i * 100, Util.getCurrentTime(), 1, 0,
                    new Position(0, 0));
            ForceAtom fa = new ForceAtom(chr.getId(), fae, mob != null ? mob.getObjectId() : 0, QUIVER_BARRAGE_ATOM, fai);
            fa.setForcedTargetPosition(mob != null ? mob.getPosition() : Util.getRandomFromCollection(field.getMobs()).getPosition());
            chr.createForceAtom(fa);
        }
        lastQuiverBarrage = System.currentTimeMillis();
    }

    private void procArmorBreak() {
        if (!chr.hasSkill(ARMOR_BREAK) || chr.hasSkillOnCooldown(ARMOR_BREAK)) {
            return;
        }
        Skill skill = chr.getSkill(ARMOR_BREAK);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        chr.write(UserPacket.effect(Effect.skillUse(skill.getSkillId(), chr.getLevel(), slv, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(skill.getSkillId(), chr.getLevel(), slv, 0)));
        chr.addSkillCoolTime(skill.getSkillId(), si.getValue(y, slv) * 1000);
    }

    private void incrementFocusedFury() {
        if (!chr.hasSkill(FOCUSED_FURY)) {
            return;
        }
        Skill skill = chr.getSkill(FOCUSED_FURY);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(FOCUSED_FURY);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o2 = new Option();
        int amount = 0;

        if (tsm.hasStat(BowMasterConcentration)) {
            amount = tsm.getOption(BowMasterConcentration).nOption;
            if (amount < (100 / si.getValue(x, slv))) {
                amount++;
            }
        }
        o2.nOption = amount;
        o2.rOption = FOCUSED_FURY;
        o2.tOption = 10;
        tsm.putCharacterStatValue(BowMasterConcentration, o2);
        tsm.sendSetStatPacket();
        if (amount % 10 == 0 && amount < (100 / si.getValue(x, slv))) {
            chr.write(UserPacket.effect(Effect.skillUse(skill.getSkillId(), chr.getLevel(), slv, 0)));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(skill.getSkillId(), chr.getLevel(), slv, 0)));
        }
    }

    private void stormOfArrowAA() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (chr.hasSkill(STORM_OF_ARROWS) && tsm.getOptByCTSAndSkill(IndieDamR, STORM_OF_ARROWS) != null) {
            Field field = chr.getField();
            for (int i = 0; i < 5; i++) {
                Mob mob = Util.getRandomFromCollection(chr.getField().getMobs());
                AffectedArea aa = AffectedArea.getPassiveAA(chr, STORM_OF_ARROWS_AA, (byte) chr.getSkill(STORM_OF_ARROWS).getCurrentLevel());
                aa.setPosition(new Position(mob.getX() + 250, mob.getY()));
                aa.setDuration(2500);
                int randomX = new Random().nextInt(200) - 100;
                int randomY = new Random().nextInt(150) - 75;
                aa.setPosition(new Position(aa.getX() + randomX, aa.getY() + randomY));
                aa.setRect(aa.getPosition().getRectAround(SkillData.getSkillInfoById(STORM_OF_ARROWS_AA).getLastRect()));
                field.spawnAffectedArea(aa);
            }
            stormArrowTimer = EventManager.addEvent(this::stormOfArrowAA, 5, TimeUnit.SECONDS);
        }
    }

    private Skill getFinalAtkSkill() {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_BOW)) {
            skill = chr.getSkill(FINAL_ATTACK_BOW);
        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK_BOW)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK_BOW);
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
            case QUIVER_CARTRIDGE:
                if (quiverCartridge == null) {
                    quiverCartridge = new QuiverCartridge(chr);
                } else if (tsm.hasStat(QuiverCatridge)) {
                    quiverCartridge.incType();
                }
                o1 = quiverCartridge.getOption();
                tsm.putCharacterStatValue(QuiverCatridge, o1);
                break;
            case RECKLESS_HUNT_BOW:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nValue = -si.getValue(x, slv);
                    o1.nReason = skillID;
                    tsm.putCharacterStatValue(IndiePDDR, o1);
                    o2.nValue = si.getValue(indiePMdR, slv);
                    o2.nReason = skillID;
                    tsm.putCharacterStatValue(IndiePMdR, o2);
                    o3.nOption = si.getValue(padX, slv);
                    o3.rOption = skillID;
                    tsm.putCharacterStatValue(PAD, o3);
                }
                break;
            case ENCHANTED_QUIVER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AdvancedQuiver, o1);
                break;
            case CONCENTRATION:
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o2);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Preparation, o3); //preparation = BD%
                break;
            case STORM_OF_ARROWS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                if (stormArrowTimer != null && !stormArrowTimer.isDone()) {
                    stormArrowTimer.cancel(true);
                }
                stormOfArrowAA();
                break;
            case INHUMAN_SPEED:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ExtraSkillCTS, o1);
                break;
            case QUIVER_BARRAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(QuiverBarrage, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            switch (skillId) {
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.RANGER.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.BOWMASTER.getJobId());
//                break;
//        }
    }

    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(FOCUSED_FURY) && tsm.hasStat(BowMasterConcentration)) {
            tsm.removeStatsBySkill(FOCUSED_FURY);
            tsm.sendResetStatPacket();
            chr.write(UserPacket.effect(Effect.skillSpecial(FOCUSED_FURY)));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(FOCUSED_FURY)));
            tsm.removeAllDebuffs();
        }
    }

    @Override
    public void cancelTimers() {
        if (stormArrowTimer != null) {
            stormArrowTimer.cancel(false);
        }
        super.cancelTimers();
    }

    public enum QCType {
        BLOOD(1),
        POISON(2),
        MAGIC(3),
        ;
        private byte val;

        QCType(int val) {
            this.val = (byte) val;
        }

        public byte getVal() {
            return val;
        }
    }

    public class QuiverCartridge {

        private int blood; // 1
        private int poison; // 2
        private int magic; // 3
        private int type;
        private Char chr;

        public QuiverCartridge(Char chr) {
            blood = getMaxNumberOfArrows(QCType.BLOOD.getVal());
            poison = getMaxNumberOfArrows(QCType.POISON.getVal());
            magic = getMaxNumberOfArrows(QCType.MAGIC.getVal());
            type = 1;
            this.chr = chr;
        }

        public void decrementAmount() {
            if (chr.getTemporaryStatManager().hasStat(AdvancedQuiver) || chr.getTemporaryStatManager().hasStat(QuiverBarrage)) {
                return;
            }
            switch (type) {
                case 1:
                    blood--;
                    if (blood == 0) {
                        blood = getMaxNumberOfArrows(QCType.BLOOD.getVal());
                        incType();
                    }
                    break;
                case 2:
                    poison--;
                    if (poison == 0) {
                        poison = getMaxNumberOfArrows(QCType.POISON.getVal());
                        incType();
                    }
                    break;
                case 3:
                    magic--;
                    if (magic == 0) {
                        magic = getMaxNumberOfArrows(QCType.MAGIC.getVal());
                        incType();
                    }
                    break;
            }
        }

        public int getNumberArrowInQuiverFromType(int type) {
            switch (type) {
                case 1:
                    return blood;
                case 2:
                    return poison;
                case 3:
                    return magic;
            }
            return -1;
        }

        public void incType() {
            type = (type % 3) + 1;
            chr.write(UserPacket.effect(Effect.skillModeEffect(QUIVER_CARTRIDGE, type - 1, quiverCartridge.getNumberArrowInQuiverFromType(type))));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillModeEffect(QUIVER_CARTRIDGE, type - 1, quiverCartridge.getNumberArrowInQuiverFromType(type))));
        }

        public int getTotal() {
            return blood * 10000 + poison * 100 + magic;
        }

        public Option getOption() {
            Option o = new Option();
            o.nOption = getTotal();
            o.rOption = QUIVER_CARTRIDGE;
            o.xOption = type;
            return o;
        }

        public int getType() {
            return type;
        }
    }

    public int getMaxNumberOfArrows(int type) {
        int num = 0;
        Skill firstSkill = chr.getSkill(QUIVER_CARTRIDGE);
        Skill secondSkill = chr.getSkill(ENCHANTED_QUIVER);
        if (secondSkill != null && secondSkill.getCurrentLevel() > 0) {
            num = 20;

        } else if (firstSkill != null && firstSkill.getCurrentLevel() > 0) {
            num = 10;
        }
        return type == 3 ? num * 2 : num; // Magic Arrow has 2x as many arrows
    }

}
