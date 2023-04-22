package net.swordie.ms.client.jobs.anima;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.enums.Stat;
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

import java.util.*;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class HoYoung extends Job {


    // Beginner Skills (Anima Thief)
    public static final int RETURN_TO_CHEONGWOON = 160001074; // TODO: Not handled.
    public static final int SPIRIT_AFFINITY = 160000000;
    public static final int SHAPESHIFT = 160001075;
    public static final int FIEND_SEAL = 160000076;

    // 1st Job
    public static final int TALISMAN_ENERGY = 164000010;
    public static final int HUMANITY_AS_YOU_WILL_FAN = 164001000; //Humanity
    public static final int TALISMAN_EVIL_SEALING_GOURD = 164001001; //Talisman
    public static final int TALISMAN_EVIL_SEALING_GOURD_EFFECT = 164001002; //Talisman

    public static final int GRACEFUL_FLIGHT = 164001003; // FJ
    public static final int LIGHT_STEPS = 164000011; // (passive)
    public static final int SHROUDING_MIST = 164000012; // (passive)
    public static final int NIMBUS_CLOUD = 164001004;

    // 2nd Job
    public static final int GROUND_SHATTERING_WAVE = 164101000; //Earth
    public static final int GROUND_SHATTERING_WAVE_1 = 164100000;
    public static final int GROUND_SHATTERING_WAVE_2 = 164101001;
    public static final int GROUND_SHATTERING_WAVE_3 = 164101002;
    public static final int CLONE = 164101003; //Talisman
    public static final int CLONE_1 = 164101004; //Talisman
    public static final int RITUAL_FAN_ACCELERATION = 164101005;
    public static final int RITUAL_FAN_MASTERY = 164100010;
    public static final int OUT_OF_SIGHT = 164100006;
    public static final int OUT_OF_SIGHT_1 = 164101006;
    public static final int THIRD_EYE = 164100011;
    public static final int HEAVENLY_BODY = 164100012;
    public static final int FORTUNE_FITNESS = 164100013;

    // 3rd Job
    public static final int SCROLL_ENERGY = 164110014;
    public static final int IRON_FAN_GALE = 164111000;
    public static final int IRON_FAN_GALE_1 = 164110000;
    public static final int IRON_FAN_GALE_2 = 164111001;
    public static final int IRON_FAN_GALE_3 = 164111002;
    public static final int IRON_FAN_GALE_4 = 164111009;
    public static final int IRON_FAN_GALE_5 = 164111010;
    public static final int IRON_FAN_GALE_6 = 164111011;
    public static final int STONE_TREMOR = 164111003; //Earth
    public static final int STONE_TREMOR_1 = 164110003;
    public static final int STONE_TREMOR_2 = 164111004;
    public static final int STONE_TREMOR_3 = 164111005;
    public static final int STONE_TREMOR_4 = 164111006;
    public static final int SEEKING_GHOST_FLAME = 164111007; //Talisman
    public static final int DEGENERATION = 164111008; //Scroll
    public static final int ATTAINMENT = 164110010;
    public static final int ASURA = 164110011;
    public static final int DIAMOND_BODY = 164110012;
    public static final int BALANCED_BREATH = 164110013;

    // 4th Job
    public static final int CONSUMING_FLAMES = 164121000; // Heaven
    public static final int CONSUMING_FLAMES_1 = 164120000;
    public static final int CONSUMING_FLAMES_2 = 164121001;
    public static final int CONSUMING_FLAMES_3 = 164121002;
    public static final int CONSUMING_FLAMES_4 = 164121014;
    public static final int GOLD_BANDED_CUDGEL = 164121003; // Humanity
    public static final int GOLD_BANDED_CUDGEL_1 = 164121004; // Humanity
    public static final int THOUSAND_TON_STONE = 164121005;
    public static final int WARP_GATE = 164121006;
    public static final int WARP_GATE_1 = 164121011; // Talisman
    public static final int WARP_GATE_2 = 164121012; // Talisman
    public static final int STAR_VORTEX = 164121008; // Scroll
    public static final int STAR_VORTEX_1 = 164121015; // Scroll
    public static final int BUTTERFLY_DREAM = 164121007; // Scroll
    public static final int BUTTERFLY_DREAM_1 = 164120007; // Scroll
    public static final int ANIMA_WARRIOR = 164121009;
    public static final int ANIMA_HERO_WILL = 164121010;
    public static final int ADVANCED_RITUAL_FAN_MASTERY = 164120010;
    public static final int ENLIGHTENMENT = 164120011;
    public static final int DRAGONS_EYE = 164120012;

    public static final int DREAM_OF_SHANGRI_LA = 164121042;
    public static final int MIRACLE_TONIC = 164121041;
    public static final int CLONE_RAMPAGE = 400041048;
    public static final int CLONE_RAMPAGE_ATOM = 400041049;
    public static final int SAGE_WRATH_OF_GODS = 400041052;

    private long warpTime;

    // Gauge
    private static final int MAX_TALISMAN_ENERGY = 100;
    private static final int MAX_SCROLL_ENERGY = 900;
    private static final int BASE_TALISMAN_ENERGY_TO_ADD = 10;
    private static final int COMBO_TALISMAN_ENERGY_TO_ADD = 5;
    private static final int BASE_SCROLL_ENERGY_TO_ADD = 15;

    private int comboCount = 1;
    private Seals lastSealUsed;

    private enum Seals {
        HEAVEN,
        EARTH,
        HUMANITY
    }

    // Degeneration
    private static final Integer[] GROUNDED_MORPH = new Integer[]{
            2400500,
            2400501,
            2400502
    };

    private static final Integer[] FLYING_MORPH = new Integer[]{
            2400503
    };

    // Evil-Sealing Gourd
    private boolean isUsingGourdToss = false; // scuffed
    private final Stack<Mob> swallowedMobs = new Stack<>();


    private static final int[] addedSkills = new int[]{};

    @Override
    public void addMissingSkills(Char chr) {
        super.addMissingSkills(chr);
        chr.addSkill(RETURN_TO_CHEONGWOON, 1, 1);
        chr.addSkill(SPIRIT_AFFINITY, 1, 1);
        chr.addSkill(SHAPESHIFT, 1, 1);
        chr.addSkill(FIEND_SEAL, 10, 10);
        chr.addSkill(NIMBUS_CLOUD, 1, 1);
    }


    public HoYoung(Char chr) {
        super(chr);
    }



    private boolean hasHumanity() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(CharacterTemporaryStat.TalismanEnergy) && tsm.getOption(CharacterTemporaryStat.TalismanEnergy).yOption == 0;
    }

    private boolean hasEarth() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(CharacterTemporaryStat.TalismanEnergy) && tsm.getOption(CharacterTemporaryStat.TalismanEnergy).xOption == 0;
    }

    private boolean hasHeaven() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(CharacterTemporaryStat.TalismanEnergy) && tsm.getOption(CharacterTemporaryStat.TalismanEnergy).nOption == 0;
    }

    public int getTalismanEnergy() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.getOption(CharacterTemporaryStat.ScrollEnergy).nOption;
    }

    public int getScrollEnergy() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.getOption(CharacterTemporaryStat.ScrollEnergy).xOption;
    }

    private void createButterflyForceAtom(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillId = BUTTERFLY_DREAM_1;
        if (!tsm.hasStat(CharacterTemporaryStat.TriflingWhimOnOff)) {
            return;
        }
        Field field = chr.getField();
        Rect rect = chr.getRectAround(new Rect(-250, -200, 20, 20));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        int fImpact = new Random().nextInt(10) + 35;
        ForceAtomEnum fae = null;
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> fae = ForceAtomEnum.BUTTERYFLY_1;
                case 1 -> fae = ForceAtomEnum.BUTTERYFLY_2;
                case 2 -> fae = ForceAtomEnum.BUTTERYFLY_3;
                case 3 -> fae = ForceAtomEnum.BUTTERYFLY_4;
                case 4 -> fae = ForceAtomEnum.BUTTERYFLY_5;
            }
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, 1,
                        100 + (i * 30), 0, Util.getCurrentTime(), 0, 0,
                        new Position(30, 0));

                ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                        true, mob.getObjectId(), skillId, fai, new Rect(), 0, 0,
                        new Position(), 0, chr.getPosition(), 0);

                chr.createForceAtom(fa);
                chr.addSkillCoolTime(BUTTERFLY_DREAM, 2 * 1000);
            }
        }
    }

    public void createCloneForceAtom(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(CharacterTemporaryStat.TalismanClone)) {
            return;
        }
        Field field = chr.getField();
        Rect rect = chr.getRectAround(new Rect(-250, -200, 20, 20));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }

        ForceAtomEnum fae = ForceAtomEnum.CLONE_ATOM;
        for (int i = 0; i < 3; i++) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int fImpact = new Random().nextInt(10) + 35;
                ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, 1,
                        100 + (i * 30), 0, Util.getCurrentTime(), 1, 0,
                        new Position(30, 0));
                ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                        true, mob.getObjectId(), CLONE_1, fai, new Rect(), 0, 0,
                        new Position(), 0, chr.getPosition(), 0);
                chr.createForceAtom(fa);
                chr.addSkillCoolTime(CLONE_1, 2 * 1000);
            }
        }
    }

    public void createCloneRForceAtom(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(CharacterTemporaryStat.CloneRampage)) {
            return;
        }
        Field field = chr.getField();
        Rect rect = chr.getRectAround(new Rect(-250, -200, 20, 20));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }

        ForceAtomEnum fae = ForceAtomEnum.CLONE_R_ATOM;
        for (int i = 0; i < 3; i++) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int fImpact = new Random().nextInt(10) + 35;
                ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, 1,
                        100 + (i * 30), 0, Util.getCurrentTime(), 1, 0,
                        new Position(30, 0));
                ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                        true, mob.getObjectId(), CLONE_RAMPAGE_ATOM, fai, new Rect(), 0, 0,
                        new Position(), 0, chr.getPosition(), 0);
                chr.createForceAtom(fa);
                chr.addSkillCoolTime(CLONE_RAMPAGE_ATOM, 2 * 1000);
            }
        }
    }

    private boolean isHumanitySkill(int skillId) {
        return switch (skillId) {
            case HUMANITY_AS_YOU_WILL_FAN, GOLD_BANDED_CUDGEL -> true;
            default -> false;
        };
    }

    private boolean isEarthSkill(int skillId) {
        return switch (skillId) {
            case STONE_TREMOR, GROUND_SHATTERING_WAVE -> true;
            default -> false;
        };
    }

    private boolean isHeavenSkill(int skill) {
        return switch (skill) {
            case CONSUMING_FLAMES, IRON_FAN_GALE -> true;
            default -> false;
        };
    }

    private boolean isAttributeSkill(int skillId) {
        return isHumanitySkill(skillId) || isEarthSkill(skillId) || isHeavenSkill(skillId);
    }

    private boolean isTalismanSkill(int skillId) {
        return switch (skillId) {
            case TALISMAN_EVIL_SEALING_GOURD, CLONE, SEEKING_GHOST_FLAME, WARP_GATE -> true;
            default -> false;
        };
    }

    private boolean isScrollSkill(int skillId) {
        return switch (skillId) {
            case DEGENERATION, BUTTERFLY_DREAM, STAR_VORTEX -> true;
            default -> false;
        };
    }

    public void handleSeals(boolean humanity, boolean earth, boolean heaven) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();

        o1.nOption = heaven ? 0 : 1; // Blue
        o1.xOption = earth ? 0 : 1; // Yellow
        o1.yOption = humanity ? 0 : 1; //Purple
        o1.rOption = 16400; //JobID?
        o1.tOption = 0;

        tsm.putCharacterStatValue(CharacterTemporaryStat.TalismanEnergy, o1);
        tsm.sendSetStatPacket();
    }

    public void handleSpellGauge(int talismanEnergy, int scrollEnergy) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();

        if (talismanEnergy >= MAX_TALISMAN_ENERGY) {
            talismanEnergy = MAX_TALISMAN_ENERGY;
        }

        if (scrollEnergy >= MAX_SCROLL_ENERGY) {
            scrollEnergy = MAX_SCROLL_ENERGY;
        }

        o1.nOption = talismanEnergy;
        o1.xOption = scrollEnergy;
        o1.rOption = 16400; //JobID?
        o1.tOption = 0;

        tsm.putCharacterStatValue(CharacterTemporaryStat.ScrollEnergy, o1);
        tsm.sendSetStatPacket();
    }

    public void handleTalismanGauge(int skillId) {
        int talismanEnergyToAdd = BASE_TALISMAN_ENERGY_TO_ADD;
        int scrollEnergyToAdd = BASE_SCROLL_ENERGY_TO_ADD;

        if (!hasHumanity() && !hasHeaven() && !hasEarth()) {
            comboCount = 1;
            lastSealUsed = null;
            handleSeals(true, true, true);
        }

        Seals currentSealUsed = null;
        if (isHumanitySkill(skillId) && hasHumanity()) {
            currentSealUsed = Seals.HUMANITY;
        } else if (isEarthSkill(skillId) && hasEarth()) {
            currentSealUsed = Seals.EARTH;
        } else if (isHeavenSkill(skillId) && hasHeaven()) {
            currentSealUsed = Seals.HEAVEN;
        }

        if (currentSealUsed != null && lastSealUsed != null) {
            if (lastSealUsed == currentSealUsed) {
                comboCount = 1;
            } else {
                comboCount++;
                if (comboCount >= 3) {
                    comboCount = 3;
                }
            }
        } else {
            comboCount = 1;
        }

        if (isHumanitySkill(skillId)) {
            lastSealUsed = hasHumanity() ? Seals.HUMANITY : null;
            handleSeals(false, hasEarth(), hasHeaven());
        } else if (isEarthSkill(skillId)) {
            lastSealUsed = hasEarth() ? Seals.EARTH : null;
            handleSeals(hasHumanity(), false, hasHeaven());
        } else if (isHeavenSkill(skillId)) {
            lastSealUsed = hasHeaven() ? Seals.HEAVEN : null;
            handleSeals(hasHumanity(), hasEarth(), false);
        }

        talismanEnergyToAdd += COMBO_TALISMAN_ENERGY_TO_ADD * (comboCount - 1);

        handleSpellGauge(getTalismanEnergy() + talismanEnergyToAdd, getScrollEnergy() + scrollEnergyToAdd);

        if (chr.getLevel() < 30) {
            if (!hasHumanity()) {
                comboCount = 1;
                lastSealUsed = null;
                handleSeals(true, true, true);
            }
        } else if (chr.getLevel() < 60) {
            if (!hasHumanity() && !hasEarth()) {
                comboCount = 1;
                lastSealUsed = null;
                handleSeals(true, true, true);
            }
        } else {
            if (!hasHumanity() && !hasHeaven() && !hasEarth()) {
                comboCount = 1;
                lastSealUsed = null;
                handleSeals(true, true, true);
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isHoYoung(id);
    }


    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(attackInfo.skillId);
        int skillId = 0;
        SkillInfo si = null;
        boolean hasHitMobs = attackInfo.mobAttackInfo.size() > 0;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillId = skill.getSkillId();
        }
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();

        switch (attackInfo.skillId) {
            case HUMANITY_AS_YOU_WILL_FAN:
                break;

            case TALISMAN_EVIL_SEALING_GOURD:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    o.rOption = TALISMAN_EVIL_SEALING_GOURD;
                    o.nOption = slv;
                    o.tOption = 23;
                    o.xOption = chr.getId();

                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (!mob.isBoss() && ((mob.getLevel() - chr.getLevel()) <= 11)) { // TODO: stationary mobs?
                        mts.addStatOptionsAndBroadcast(MobStat.TossAndSwallow, o);
                        swallowedMobs.push(mob);
                    }
                }
                swallowMobForceAtom(attackInfo);
                break;
            case TALISMAN_EVIL_SEALING_GOURD_EFFECT:
                isUsingGourdToss = true;
                break;
            case DEGENERATION:

                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) continue;

                    int morphId = Util.getRandomFromCollection(mob.getFlySpeed() > 0 ? FLYING_MORPH : GROUNDED_MORPH);

                    o.rOption = DEGENERATION;
                    o.nOption = morphId;
                    o.tOption = 60000;

                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Degeneration, o);
                }
                break;
            case GROUND_SHATTERING_WAVE:
                o1.nValue = si.getValue(SkillStat.x, slv);
                o1.nReason = OUT_OF_SIGHT;
                o1.tTerm = 2;
                tsm.putCharacterStatValue(CharacterTemporaryStat.DarkSight, o1);
                break;
        }

        handleSpellGauge(
                isTalismanSkill(skillId) ? 0 : getTalismanEnergy(),
                isScrollSkill(skillId) ? 0 : getScrollEnergy()
        );

        tsm.sendSetStatPacket();

        if (hasHitMobs) {
            if (isAttributeSkill(skillId)) {
                handleTalismanGauge(skillId);
            }
        }

        if (!chr.hasSkillOnCooldown(BUTTERFLY_DREAM)) {
            createButterflyForceAtom(attackInfo);
        }

        if (!chr.hasSkillOnCooldown(CLONE_1)) {
            createCloneForceAtom(attackInfo);
        }
        if (!chr.hasSkillOnCooldown(CLONE_RAMPAGE_ATOM)) {
            createCloneRForceAtom(attackInfo);
        }

        doSageGodAssist();

        super.handleAttack(c, attackInfo);
    }

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillId, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillId, slv, inPacket, skillUseInfo);

        Skill skill = chr.getSkill(skillId);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillId);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        int scrollEnergyToAdd = 0;
        if (chr.getJob() > 16410 && chr.getJob() <= 16412) {
            scrollEnergyToAdd = isTalismanSkill(skillId) ? 200 : 15;
        }
        Field field = chr.getField();
        Summon summon = null;

        switch (skillId) {
            case SHAPESHIFT:
                boolean enable;
                int qid = QuestConstants.SHAPESHIFT_QR;
                if (chr.getRecordFromQuestEx(qid, "sw") == 0) {
                    chr.setQuestRecordEx(qid, "sw", 1);
                    enable = true;
                } else {
                    chr.setQuestRecordEx(qid, "sw", 0);
                    enable = false;
                }
                chr.getField().broadcastPacket(UserPacket.shapeShiftResult(chr.getId(), enable));
                chr.addSkillCoolTime(SHAPESHIFT, 10000);
                break;
            case RITUAL_FAN_ACCELERATION:
                handleSpellGauge(MAX_TALISMAN_ENERGY, MAX_SCROLL_ENERGY); // TODO: REMOVE, ONLY FOR DEBUG
                o1.nValue = si.getValue(SkillStat.x, slv);
                o1.nReason = skillId;
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o1);
                break;
            case ANIMA_HERO_WILL:
                tsm.removeAllDebuffs();
                break;
            case ANIMA_WARRIOR:
                o1.nReason = skillId;
                o1.nValue = si.getValue(SkillStat.x, slv);
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStatR, o1);
                break;
            case CLONE:
                o1.rOption = CLONE;
                o1.nOption = 1;
                o1.yOption = CLONE;
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.TalismanClone, o1);
                break;
            case SEEKING_GHOST_FLAME:
                summon = Summon.getSummonBy(chr, skillId, slv);
                summon.setFlyMob(true);
                summon.setMoveAction((byte) 4);
                summon.setAssistType(AssistType.Summon);
                summon.setMoveAbility(MoveAbility.Fly);
                field.spawnSummon(summon);
                break;
            case STAR_VORTEX:
                summon = Summon.getSummonBy(chr, skillId, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case STAR_VORTEX_1:
                List<Char> chrList = new ArrayList<>();
                chr.getField().getSummons().stream().filter(s -> s.getSkillID() == STAR_VORTEX && s.getChr() == chr).forEach(chr.getField()::removeLife);
                int percentHealed = 5;
                if (chr.getParty() != null) {
                    chrList.addAll(chr.getParty().getPartyMembersInSameField(chr));
                } else {
                    chr.heal((int) ((chr.getMaxHP() * percentHealed) / 100D));
                    chr.healMP((int) ((chr.getMaxMP() * percentHealed) / 100D));
                }
                chrList.add(chr);
                for (Char pChr : chrList) {
                    pChr.heal((int) ((pChr.getMaxHP() * percentHealed) / 100D));
                    pChr.healMP((int) ((pChr.getMaxMP() * percentHealed) / 100D));
                }
                break;
            case WARP_GATE:
                chr.getField().getSummons().stream().filter(s -> s.getSkillID() == WARP_GATE && s.getChr() == chr).forEach(chr.getField()::removeLife);
                chr.getField().getSummons().stream().filter(s -> s.getSkillID() == WARP_GATE_1 && s.getChr() == chr).forEach(chr.getField()::removeLife);
                summon = Summon.getSummonBy(chr, skillId, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case WARP_GATE_1:
                summon = Summon.getSummonBy(chr, skillId, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case BUTTERFLY_DREAM:
                o1.nOption = 1;
                o1.rOption = skillId;
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.TriflingWhimOnOff, o1);
                break;
            case MIRACLE_TONIC:
                o1.nOption = 1;
                o1.rOption = skillId;
                o1.tOption = si.getValue(SkillStat.time, slv);
                o2.nOption = 10;
                o2.rOption = skillId;
                o2.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.MiracleTonic, o1);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o2);
                break;
            case CLONE_RAMPAGE:
                o1.rOption = CLONE_RAMPAGE;
                o1.nOption = 1;
                o1.yOption = CLONE;
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.CloneRampage, o1);
                break;
            case SAGE_WRATH_OF_GODS:
                summon = Summon.getSummonBy(chr, skillId, slv);
                summon.setMoveAction((byte) 4);
                summon.setAssistType(AssistType.Summon);
                summon.setMoveAbility(MoveAbility.Smart);
                field.spawnSummon(summon);
                o2.nValue = si.getValue(SkillStat.indieDamR, slv);
                o2.nReason = skillId;
                o2.tTerm = 30;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o2);
                o1.nOption = 1;
                o1.rOption = skillId;
                o1.tOption = 30;
                o1.xOption = 0;
                tsm.putCharacterStatValue(CharacterTemporaryStat.Unk210_607, o1);
                break;
        }

        handleSpellGauge(
                isTalismanSkill(skillId) ? 0 : getTalismanEnergy(),
                isScrollSkill(skillId) ? 0 : isTalismanSkill(skillId) ? getScrollEnergy() + scrollEnergyToAdd : getScrollEnergy()
        );

        tsm.sendSetStatPacket();
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    public void handleKeyDownSkill(Char chr, int skillID, InPacket inPacket) {
        super.handleKeyDownSkill(chr, skillID, inPacket);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Skill skill = chr.getSkill(skillID);
        int skillId = skillID;
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

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
//        CharacterStat cs = chr.getAvatarData().getCharacterStat();
//        cs.setPosMap(993028000);
//        cs.setLevel(10);
//        cs.setHp(442);
//        cs.setMaxHp(442);
//        cs.setMaxMp(320);
//        cs.setMp(231);
//        chr.setJob(JobConstants.JobEnum.HO_YOUNG1.getJobId());
//        chr.addSpToJobByCurrentJob(5);
//        chr.getScriptManager().giveAndEquip(1353800); // secondary
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        chr.setJob(JobConstants.JobEnum.HOYOUNG_1.getJobId());
        chr.setStatAndSendPacket(Stat.job, chr.getJob());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        chr.addSpToJobByCurrentJob(5);
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353800)); // Mano Fan Tassel (2ndary)
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }


    private void swallowMobForceAtom(AttackInfo attackInfo) {
        ForceAtomEnum fae = ForceAtomEnum.SWALLOW_MOB;
        int mobObjId;
        Rect rect = new Rect(-405, -50, 405, 50);

        // FA created per mob
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            final Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            final ForceAtomInfo fai = new ForceAtomInfo(2, fae.getInc(), 5, 30,
                    0, 0, Util.getCurrentTime(), 0, 0, new Position(0, 0));

            mobObjId = Util.getRandomFromCollection(chr.getField().getMobsInRect(mob.getRectAround(rect))).getObjectId();

            ArrayList<Integer> targetList = new ArrayList<>();
            targetList.add(chr.getId());

            final ForceAtom fa = new ForceAtom(true, chr.getId(), mobObjId, fae,
                    true, targetList, TALISMAN_EVIL_SEALING_GOURD, new ArrayList<>(Collections.singleton(fai)),
                    rect, 0, 0, new Position(), 0, new Position(), 0);
            chr.createForceAtom(fa);
        }
    }

    private void doSageGodAssist() {
        if (!chr.hasSkill(SAGE_WRATH_OF_GODS)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int stack = 0;
        if (tsm.hasStat(CharacterTemporaryStat.Unk210_607)) {
            stack = tsm.getOption(CharacterTemporaryStat.Unk210_607).xOption;
            if (stack < 12) {
                stack++;
            }
            if (stack == 12) {
                int randomInt = new Random().nextInt(2) + 10;
                chr.getField().broadcastPacket(Summoned.summonedAssistAttackRequest(chr.getField().getSummonByChrAndSkillId(chr, SAGE_WRATH_OF_GODS), randomInt));
                stack = 0;
            }
            o.xOption = stack;
            tsm.putCharacterStatValue(CharacterTemporaryStat.Unk210_607, o);
            tsm.sendSetStatPacket();
        }
    }


    @Override
    public void handleMobDamaged(Char chr, Mob mob, long damage) {
        if (!swallowedMobs.empty() && chr.hasSkill(TALISMAN_EVIL_SEALING_GOURD) && isUsingGourdToss) {
            chr.write(UserPacket.effect(Effect.showTalismanSwallowEffect(TALISMAN_EVIL_SEALING_GOURD_EFFECT, chr.getLevel(),
                    chr.getSkillLevel(TALISMAN_EVIL_SEALING_GOURD), mob.getObjectId(), mob.getTemplateId())));

            MobTemporaryStat mts = swallowedMobs.pop().getTemporaryStat();
            mts.removeMobStat(MobStat.TossAndSwallow, false);
            if (swallowedMobs.empty()) isUsingGourdToss = false;
        }
        super.handleMobDamaged(chr, mob, damage);
    }
}
