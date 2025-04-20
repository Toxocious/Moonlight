package net.swordie.ms.client.jobs.resistance.demon;

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
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
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
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.IndieCr;

public class DemonSlayer extends Demon {
    public static final int CURSE_OF_FURY = 30010111;


    public static final int GRIM_SCYTHE = 31001000; //Special Attack            //TODO (Demon Force)
    public static final int BATTLE_PACT_DS = 31001001; //Buff
    public static final int DEMON_LASH = 31000004;
    public static final int DEMON_LASH_2 = 31001006;
    public static final int DEMON_LASH_3 = 31001007;
    public static final int DEMON_LASH_4 = 31001008;


    public static final int SOUL_EATER = 31101000; //Special Attack             //TODO (Demon Force)
    public static final int DARK_THRUST = 31101001; //Special Attack            //TODO (Demon Force)
    public static final int CHAOS_LOCK = 31101002; //Special Attack  -Stun-     //TODO (Demon Force)


    public static final int JUDGEMENT = 31111000; //Special Attack              //TODO (Demon Force)
    public static final int VORTEX_OF_DOOM = 31111001; //Special Attack  -Stun- //TODO (Demon Force)
    public static final int RAVEN_STORM = 31111003; //Special Attack -GainHP-   //TODO (Demon Force)
    public static final int CARRION_BREATH = 31111005; //Special Attack  -DoT-  //TODO (Demon Force)
    public static final int POSSESSED_AEGIS = 31110008;
    public static final int MAX_FURY = 31110009;
    public static final int VENGEANCE = 31101003; //Buff (Stun Debuff)


    public static final int BLUE_BLOOD = 31121054;
    public static final int LEECH_AURA = 31121002; //Buff                       //TODO (Demon Force)
    public static final int BOUNDLESS_RAGE = 31121007; //Buff                   //TODO (Demon Force)
    public static final int DARK_METAMORPHOSIS = 31121005; //Buff               //TODO (Demon Force)
    public static final int INFERNAL_CONCUSSION = 31121000; //Special Attack    //TODO (Demon Force)
    public static final int DEMON_IMPACT = 31121001; //Special Attack  -Slow-   //TODO (Demon Force)
    public static final int DEMON_CRY = 31121003; //Special Attack -DemonCry-   //TODO (Demon Force)
    public static final int BINDING_DARKNESS = 31121006; //Special Attack -Bind-//TODO (Demon Force)
    public static final int MAPLE_WARRIOR_DS = 31121004; //Buff
    public static final int DEMONIC_FORTITUDE_DS = 31121053;
    public static final int CERBERUS_CHOMP = 31121052;


    // V skills
    public static final int ORTHRUS_2 = 400011078;
    public static final int ORTHRUS = 400011077;
    public static final int SPIRIT_OF_RAGE = 400011057;
    public static final int DEMON_AWAKENING = 400011006;


    private static final int[] addedSkills = new int[]{
            CURSE_OF_FURY,
    };

    private long leechAuraCD = Long.MIN_VALUE;
    private ScheduledFuture maxFuryTimer;

    public DemonSlayer(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (maxFuryTimer != null && !maxFuryTimer.isDone()) {
                maxFuryTimer.cancel(true);
            }
            regenDFInterval();
        }
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isDemonSlayer(id);
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
    }

    public void leechAuraHealing(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(LEECH_AURA)) {
            if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.Regen, LEECH_AURA) != null) {
                Skill skill = chr.getSkill(LEECH_AURA);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int cd = si.getValue(y, slv) * 1000;
                if (cd + leechAuraCD < System.currentTimeMillis()) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        long totaldmg = Arrays.stream(mai.damages).sum();
                        int hpheal = (int) (totaldmg * ((double) 100 / si.getValue(x, slv)));
                        if (hpheal >= (chr.getMaxHP() / 4)) {
                            hpheal = (chr.getMaxHP() / 4);
                        }
                        leechAuraCD = System.currentTimeMillis();
                        chr.heal(hpheal);
                    }
                }
            }
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

        if (hasHitMobs) {
            //Demon Slayer Fury Atoms
            createDemonFuryForceAtom(attackInfo);

            //Max Fury
            if (chr.hasSkill(MAX_FURY)) {
                if (attackInfo.skillId == DEMON_LASH || attackInfo.skillId == DEMON_LASH_2 || attackInfo.skillId == DEMON_LASH_3 || attackInfo.skillId == DEMON_LASH_4) {
                    Skill maxfuryskill = chr.getSkill(MAX_FURY);
                    SkillInfo mfsi = SkillData.getSkillInfoById(MAX_FURY);
                    byte skillLevel = (byte) maxfuryskill.getCurrentLevel();
                    int propz = mfsi.getValue(prop, skillLevel);
                    if (Util.succeedProp(propz)) {
                        createDemonFuryForceAtom(attackInfo);
                    }
                }
            }

            //Leech Aura
            leechAuraHealing(attackInfo);
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case CHAOS_LOCK: //prop Stun/Bind
            case VORTEX_OF_DOOM: //prop
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
            case CARRION_BREATH: //DoT
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case BINDING_DARKNESS: //stun + DoT
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (mob.isBoss()) {
                        mts.addStatOptions(MobStat.Stun, o1);
                    }
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case DEMON_CRY:
                o1.nOption = -si.getValue(y, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                o2.nOption = -si.getValue(z, slv);
                o2.rOption = skill.getSkillId();
                o2.tOption = si.getValue(time, slv);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                o3.xOption = si.getValue(w, slv); // exp
                o3.yOption = si.getValue(w, slv); // dropRate
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptions(MobStat.PAD, o1);
                    mts.addStatOptions(MobStat.PDR, o1);
                    mts.addStatOptions(MobStat.MAD, o1);
                    mts.addStatOptions(MobStat.MDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, o2);
                    mts.addStatOptionsAndBroadcast(MobStat.Treasure, o3);
                }
                if (chr.getTemporaryStatManager().hasStatBySkillId(BOUNDLESS_RAGE)) {
                    chr.addSkillCoolTime(skillID, 700);
                } else {
                    chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                }
                break;
            case DEMON_IMPACT:
                o1.nOption = -20;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }
                break;
            case CERBERUS_CHOMP:
                int furyabsorbed = si.getValue(x, slv);
                chr.healMP(furyabsorbed);
                break;
            case RAVEN_STORM:
                int hpheal = (int) (chr.getMaxHP() / ((double) 100 / si.getValue(x, slv)));
                chr.heal(hpheal);
                break;
            case DARK_METAMORPHOSIS:
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void createDemonFuryForceAtom(AttackInfo attackInfo) {
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            int mobID = mai.mobId;
            int angle = new Random().nextInt(40) + 30;
            int speed = new Random().nextInt(31) + 29;

            //Attacking with Demon Lash
            if (attackInfo.skillId == DEMON_LASH || attackInfo.skillId == DEMON_LASH_2 || attackInfo.skillId == DEMON_LASH_3 || attackInfo.skillId == DEMON_LASH_4) {
                ForceAtomEnum fae = ForceAtomEnum.DEMON_SLAYER_FURY_1;
                if (mob.isBoss()) {
                    fae = ForceAtomEnum.DEMON_SLAYER_FURY_1_BOSS;
                }
                if (chr.getJob() == JobConstants.JobEnum.DEMON_SLAYER_4.getJobId()) {
                    fae = ForceAtomEnum.DEMON_SLAYER_FURY_2;
                    if (mob.isBoss()) {
                        fae = ForceAtomEnum.DEMON_SLAYER_FURY_2_BOSS;
                    }
                }
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), speed, 5,
                        angle, 50, Util.getCurrentTime(), 1, 0,
                        new Position(0, 0));
                chr.createForceAtom(new ForceAtom(true, chr.getId(), mobID, fae,
                        true, mobID, chr.getJob(), forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), 0, mob.getPosition(), 0), false);
            } else {

                // Attacking with another skill
                long totaldmg = Arrays.stream(mai.damages).sum();
                if (totaldmg > mob.getHp()) {
                    ForceAtomEnum fae = ForceAtomEnum.DEMON_SLAYER_FURY_1;
                    if (mob.isBoss()) {
                        fae = ForceAtomEnum.DEMON_SLAYER_FURY_1_BOSS;
                    }
                    if (chr.getJob() == JobConstants.JobEnum.DEMON_SLAYER_4.getJobId()) {
                        fae = ForceAtomEnum.DEMON_SLAYER_FURY_2;
                        if (mob.isBoss()) {
                            fae = ForceAtomEnum.DEMON_SLAYER_FURY_2_BOSS;
                        }
                    }
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), speed, 5,
                            angle, 50, Util.getCurrentTime(), 1, 0,
                            new Position(0, 0));
                    chr.createForceAtom(new ForceAtom(true, chr.getId(), mobID, fae,
                            true, mobID, chr.getJob(), forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), 0, mob.getPosition(), 0), false);
                }
            }
        }
    }

    private void createPossessedAegisFuryForceAtom(int mobID) {
        Field field = chr.getField();
        Life life = field.getLifeByObjectID(mobID);
        if (life instanceof Mob) {
            int angle = new Random().nextInt(40) + 30;
            int speed = new Random().nextInt(31) + 29;
            ForceAtomEnum fae = ForceAtomEnum.DEMON_SLAYER_FURY_1;
            if (chr.getJob() == JobConstants.JobEnum.DEMON_SLAYER_4.getJobId()) {
                fae = ForceAtomEnum.DEMON_SLAYER_FURY_2;
            }
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), speed, 4,
                    angle, 50, Util.getCurrentTime(), 1, 0,
                    new Position(0, 0));
            chr.createForceAtom(new ForceAtom(true, chr.getId(), mobID, fae,
                    false, mobID, POSSESSED_AEGIS, forceAtomInfo, new Rect(), 0, 300,
                    life.getPosition(), 0, life.getPosition(), 0));
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
        Summon summon;
        Field field;
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case VENGEANCE: //stun chance = prop | stun dur. = subTime
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.PowerGuard, o1);
                break;
            case DARK_METAMORPHOSIS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(damR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieMhpR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieMHPR, o2);
                o3.nOption = si.getValue(damage, slv); //?
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.PowerGuard, o3);
                o4.nOption = 1;
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.DevilishPower, o4);
                break;
            case BOUNDLESS_RAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.InfinityForce, o1);
                break;
            case LEECH_AURA:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Regen, o1);
                break;
            case BLUE_BLOOD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.ShadowPartner, o1);
                break;
            case SPIRIT_OF_RAGE:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = chr.getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setPosition(inPacket.decodePosition());
                summon.setFlip(inPacket.decodeByte() == 0);
                field.spawnSummon(summon);
                break;
            case DEMON_AWAKENING:
                o1.nValue = si.getValue(indieCr, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieCr, o1);
                // Fallthrough intended
            case DEFENDER_OF_THE_DEMON:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                field = chr.getField();
                summon.setFlyMob(false);
                summon.setMoveAction((byte) 0);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setPosition(chr.getPosition());
                field.spawnSummon(summon);
                break;
            case ORTHRUS:
                List<Integer> list = Arrays.asList(ORTHRUS, ORTHRUS_2);
                for (int summonDS : list) {
                    summon = Summon.getSummonBy(c.getChr(), summonDS, slv);
                    field = chr.getField();
                    summon.setFlyMob(false);
                    summon.setMoveAction((byte) 0);
                    summon.setMoveAbility(MoveAbility.Walk);
                    summon.setPosition(chr.getPosition());
                    field.spawnSummon(summon);
                }
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);
        Option o1 = new Option();

        // Vengeance
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.PowerGuard, VENGEANCE) != null) {
            if (hitInfo.hpDamage != 0) {
                Skill skill = chr.getSkill(VENGEANCE);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int mobID = hitInfo.mobID;
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mobID);
                if (mob == null) {
                    return;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    o1.nOption = 1;
                    o1.rOption = skill.getSkillId();
                    o1.tOption = si.getValue(subTime, slv);
                    o1.bOption = 1;
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
            }
        }


        // Possessed Aegis
        if (hitInfo.hpDamage == 0 && hitInfo.mpDamage == 0) {
            // Guarded
            if (chr.hasSkill(POSSESSED_AEGIS)) {
                Skill skill = chr.getSkill(POSSESSED_AEGIS);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int propz = si.getValue(x, slv);
                if (Util.succeedProp(propz)) {
                    int mobID = hitInfo.mobID;
                    createPossessedAegisFuryForceAtom(mobID);
                    chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(y, slv))));
                }
            }
        }
    }

    @Override
    public void handleRecoverySchedulers() {
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            if (!chr.getRecoverySchedules().containsKey(chr.getJob())) {
                final ScheduledFuture maxFury = EventManager.addFixedRateEvent(this::regenDFInterval, 0, 4, TimeUnit.SECONDS);

                chr.getRecoverySchedules().put(chr.getJob(), maxFury);
            }
        }
    }

    public void regenDFInterval() {
        if (chr != null && chr.getField() != null && chr.hasSkill(MAX_FURY)) {
            chr.healMP(10);
        }
    }


    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        switch (chr.getLevel()) {
            case 30 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099000);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099002)); // DS Will
            }
            case 60 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099002);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099003)); // DS Authority
            }
            case 100 -> {
                Item oldAegis = chr.getEquippedInventory().getItemByItemID(1099003);
                chr.forceUpdateSecondary(oldAegis, ItemData.getItemDeepCopy(1099004)); // DS Extremes
            }
        }
    }


    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item doomScepter = chr.getEquippedInventory().getItemByItemID(1322123); // Doom Scepter Lv. 0
        chr.consumeItem(doomScepter);

        Item oldPatience = chr.getEquippedInventory().getItemByItemID(1099001); // Old DS Patience
        chr.forceUpdateSecondary(oldPatience, ItemData.getItemDeepCopy(1099000)); // New DS Patience

        Item primaryWeapon = ItemData.getItemDeepCopy(1322122); // Doom Scepter Lv. 10
        int bagIndex = BodyPart.Weapon.getVal();
        primaryWeapon.setBagIndex(bagIndex);
        chr.getEquippedInventory().addItem(primaryWeapon);
        primaryWeapon.updateToChar(chr);
    }
}
