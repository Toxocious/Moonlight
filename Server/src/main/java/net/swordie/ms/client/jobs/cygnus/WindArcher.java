package net.swordie.ms.client.jobs.cygnus;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
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

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class WindArcher extends Noblesse {


    public static final int ELEMENTAL_HARMONY_DEX = 10000247;

    public static final int STORM_ELEMENTAL = 13001022; //Buff

    public static final int TRIFLING_WIND_I = 13101022; //Special Buff (Proc) (ON/OFF)  // Normal Arrow - Atom
    public static final int TRIFLING_WIND_ATOM = 13100022;
    public static final int TRIFLING_WIND_ATOM_ENHANCED = 13100027;                     // Enhanced Arrow - Atom
    public static final int BOW_BOOSTER = 13101023; //Buff
    public static final int SYLVAN_AID = 13101024; //Buff

    public static final int TRIFLING_WIND_II = 13110022; //Special Buff Upgrade         // Enhanced Arrow - Atom
    public static final int TRIFLING_WIND_II_ATOM = 13110027;                           // Normal Arrow - Atom
    public static final int ALBATROSS = 13111023; //Buff
    public static final int EMERALD_FLOWER = 13111024; //Summon (Stationary, No Attack, Aggros)
    public static final int SECOND_WIND = 13110026; //
    public static final int FEATHER_WEIGHT = 13110025;
    public static final int PINPOINT_PIERCE = 13111021;

    public static final int TRIFLING_WIND_III_ENHANCED = 13120010;                      // Enhanced Arrow - Atom
    public static final int ALBATROSS_MAX = 13120008; //Upgrade on Albatross
    public static final int TRIFLING_WIND_III = 13120003; //Special Buff Upgrade        // Normal Arrow - Atom
    public static final int SHARP_EYES = 13121005; //Buff
    public static final int TOUCH_OF_THE_WIND = 13120004; // Passive
    public static final int CALL_OF_CYGNUS_WA = 13121000; //Buff
    public static final int EMERALD_DUST = 13120007;
    public static final int SPIRALING_VORTEX = 13121002;
    public static final int SPIRALING_VORTEX_EXPLOSION = 13121009;
    public static final int TRIFLING_WIND_ENHANCE = 13120044;
    public static final int TRIFLING_WIND_DOUBLE_CHANCE = 13120045;

    public static final int GLORY_OF_THE_GUARDIANS_WA = 13121053;
    public static final int STORM_BRINGER = 13121054;
    public static final int MONSOON = 13121052;

    public static final int HOWLING_GALE_1 = 400031003; // takes 1 Wind Energy
    public static final int HOWLING_GALE_2 = 400031004; // takes 2 Wind Energy
    public static final int MERCILESS_WINDS = 400031022;
        public static final int GALE_BARRIER = 400031030;
    public static final int GALE_BARRIER_ATOM = 400031031;

    private long lastGaleBarrierFA = Long.MIN_VALUE;

    private static final int[] addedSkills = new int[]{
            ELEMENTAL_HARMONY_DEX,
    };

    public WindArcher(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            incrementWindEnergy();
        }
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isWindArcher(id);
    }


    private void incrementWindEnergy() {
        EventManager.addFixedRateEvent(this::increaseWindEnergy, 20, 20, TimeUnit.SECONDS);
    }

    private void increaseWindEnergy() {
        if (!chr.hasSkill(HOWLING_GALE_1)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int count = 1;
        if (tsm.hasStat(WindEnergy)) {
            count = tsm.getOption(WindEnergy).nOption;
            if (count < 2) {
                count++;
            }
        }
        updateWindEnergy(count);
    }

    private void updateWindEnergy(int energy) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = energy;
        o.rOption = 0;
        tsm.putCharacterStatValue(WindEnergy, o);
        tsm.sendSetStatPacket();
    }

    public void diminishGaleBarrier(int elemental) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option prevOpt = tsm.getOptByCTSAndSkill(GaleBarrier, GALE_BARRIER);
        Option o = new Option();
        o.nOption = prevOpt.nOption - elemental;
        o.rOption = prevOpt.rOption;
        o.tOption = (int) tsm.getRemainingTime(GaleBarrier, GALE_BARRIER);
        o.setInMillis(true);
        if (o.nOption <= 0) {
            tsm.removeStatsBySkill(GALE_BARRIER);
        } else {
            tsm.putCharacterStatValue(GaleBarrier, o, true);
            tsm.sendSetStatPacket();
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

        if (hasHitMobs) {
            if (!SkillConstants.isForceAtomSkill(attackInfo.skillId)
                    && attackInfo.skillId != 0
                    && attackInfo.skillId != SPIRALING_VORTEX_EXPLOSION) {

                createStormBringerForceAtom(attackInfo);

                if (skillID == 13111020 || skillID == 13121002) {
                    return;
                }
                if (Util.succeedProp(50)) {
                    createTriflingWhimForceAtom(attackInfo);
                }


                createGaleBarrierForceAtom();


            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case MONSOON:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case PINPOINT_PIERCE:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = 20;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.FinalDmgReceived, o1);
                }
                break;
            case SPIRALING_VORTEX:
                List<MobAttackInfo> mai = attackInfo.mobAttackInfo;
                if (attackInfo.mobAttackInfo.size() <= 0) {
                    return;
                }
                Mob mob = (Mob) chr.getField().getLifeByObjectID(Util.getRandomFromCollection(mai).mobId);
                if (mob == null) {
                    return;
                }
                chr.getField().broadcastPacket(UserLocal.explosionAttack(SPIRALING_VORTEX_EXPLOSION, mob.getPosition(), mob.getObjectId(), 1));
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void createGaleBarrierForceAtom() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (chr.hasSkill(GALE_BARRIER) && tsm.hasStat(GaleBarrier) && (lastGaleBarrierFA + 2000 < Util.getCurrentTimeLong())) {
            Skill skill = chr.getSkill(GALE_BARRIER);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            for (int i = 0; i < si.getValue(q2, slv); i++) {
                Rect rect = chr.getRectAround(new Rect(-350, -200, 100, 100));
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                if (chr.getField().getMobsInRect(rect).size() <= 0) {
                    continue;
                }
                Mob mob = Util.getRandomFromCollection(chr.getField().getMobsInRect(rect));
                int randomfImpact = new Random().nextInt(6) + 30;
                int randomsImpact = new Random().nextInt(3) + 4;

                ForceAtomEnum fae = ForceAtomEnum.GREEN_TORNADO;
                ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), randomfImpact, randomsImpact,
                        (int) Util.getAngleOfTwoPositions(chr.getPosition(), mob.getPosition()), 500, Util.getCurrentTime(), 1, 0,
                        new Position());
                chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                        true, mob.getObjectId(), GALE_BARRIER_ATOM, forceAtomInfo, new Rect(), 0, 300,
                        mob.getPosition(), GALE_BARRIER_ATOM, mob.getPosition(), 0));
            }
            lastGaleBarrierFA = Util.getCurrentTimeLong();
        }
    }

    private void createMercilessWindForceAtom() {
        Field field = chr.getField();
        if (!chr.hasSkill(MERCILESS_WINDS)) {
            return;
        }
        Skill skill = chr.getSkill(MERCILESS_WINDS);
        SkillInfo si = SkillData.getSkillInfoById(MERCILESS_WINDS);
        int slv = skill.getCurrentLevel();
        int forceAtomCount = si.getValue(x, slv);
        ForceAtomEnum fae = ForceAtomEnum.MERCILESS_WINDS;
        for (int i = 0; i < forceAtomCount; i++) {
            int angle = (360 / forceAtomCount) * i;
            int circleRadii = 150;
            int vTranslation = (int) (Math.sin(angle) * circleRadii);
            int hTranslation = (int) (Math.cos(angle) * circleRadii);
            Mob mob = Util.getRandomFromCollection(field.getMobs());
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 35, 5,
                    angle, 500, Util.getCurrentTime(), 1, 0,
                    new Position(chr.getPosition().getX() + hTranslation, chr.getPosition().getY() + vTranslation));
            chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                    true, mob == null ? 0 : mob.getObjectId(), MERCILESS_WINDS, forceAtomInfo, new Rect(), 0, 300,
                    chr.getPosition(), MERCILESS_WINDS, mob == null ? new Position() : mob.getPosition(), 0));
        }
    }

    private void createTriflingWhimForceAtom(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getTriflingWindSkill();
        if (skill == null || !Util.succeedProp(getTriflingWindProp()) || !tsm.hasStat(TriflingWhimOnOff)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Field field = chr.getField();
        Rect rect = chr.getRectAround(new Rect(-250, -200, 20, 20));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        Mob mob = (Mob) attackInfo.mobAttackInfo.stream().map(mai -> field.getLifeByObjectID(mai.mobId)).filter(Objects::nonNull).findFirst().orElse(null);
        if (mob == null) {
            if (field.getMobsInRect(rect).size() <= 0) {
                return;
            }
            mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
        }
        int fImpact = new Random().nextInt(10) + 35; // 36
        int sImpact = 3;
        ForceAtomEnum fae = Util.succeedProp(getTriflingWindSubProp()) ? ForceAtomEnum.WA_ARROW_2 : ForceAtomEnum.WA_ARROW_1;
        int skillId = Util.succeedProp(getTriflingWindSubProp()) ? TRIFLING_WIND_ATOM_ENHANCED : skill.getSkillId();
        switch (skill.getSkillId()) {
            case TRIFLING_WIND_II:
                skillId = Util.succeedProp(getTriflingWindSubProp()) ? skill.getSkillId() : TRIFLING_WIND_II_ATOM;
                break;
            case TRIFLING_WIND_III:
                skillId = Util.succeedProp(getTriflingWindSubProp()) ? TRIFLING_WIND_III_ENHANCED : skill.getSkillId();
                break;
        }
        for (int i = 0; i < (chr.hasSkill(TRIFLING_WIND_DOUBLE_CHANCE) ? 2 : 1); i++) {
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, sImpact,
                    new Random().nextBoolean() ? 180 : 0, 0, Util.getCurrentTime(), 0, 0,
                    new Position(30, 0));
            ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                    true, mob.getObjectId(), skillId, fai, new Rect(), 0, 0,
                    new Position(), 0, new Position(), 0);
            chr.createForceAtom(fa);
        }
    }

    private void createStormBringerForceAtom(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(StormBringer)) {
            SkillInfo si = SkillData.getSkillInfoById(STORM_BRINGER);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int ranY = new Random().nextInt(150) - 100;
                int hyperprop = si.getValue(prop, 1);
                if (Util.succeedProp(hyperprop)) {
                    int mobID = mai.mobId;
                    ForceAtomEnum fae = ForceAtomEnum.WA_ARROW_HYPER;
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 5, 5,
                            270, 0, Util.getCurrentTime(), 1, 0,
                            new Position(35, ranY)); //Slightly behind the player
                    chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                            true, mobID, STORM_BRINGER, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), STORM_BRINGER, mob.getPosition(), 0));
                }
            }
        }
    }

    private Skill getTriflingWindSkill() {
        Skill skill = null;
        if (chr.hasSkill(TRIFLING_WIND_I)) {
            skill = chr.getSkill(TRIFLING_WIND_I);
        }
        if (chr.hasSkill(TRIFLING_WIND_II)) {
            skill = chr.getSkill(TRIFLING_WIND_II);
        }
        if (chr.hasSkill(TRIFLING_WIND_III)) {
            skill = chr.getSkill(TRIFLING_WIND_III);
        }

        return skill;
    }

    private int getTriflingWindProp() {
        Skill skill = getTriflingWindSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            return si.getValue(prop, slv) + (chr.hasSkill(TRIFLING_WIND_ENHANCE) ? 10 : 0);
        }
        return 0;
    }

    private int getTriflingWindSubProp() {
        Skill skill = getTriflingWindSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            return si.getValue(subProp, slv);
        }
        return 0;
    }

    private int getMaxTriffling() {
        Skill skill = getTriflingWindSkill();
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            return si.getValue(x, slv);
        }
        return 0;
    }

    public static Skill getEmeraldFlowerSkill(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(EMERALD_FLOWER)) {
            skill = chr.getSkill(EMERALD_FLOWER);
        }
        if (chr.hasSkill(EMERALD_DUST)) {
            skill = chr.getSkill(EMERALD_DUST);
        }

        return skill;
    }

    public void applyEmeraldFlowerDebuffToMob(Summon summon, int mobTemplateId) {
        Skill skill = getEmeraldFlowerSkill(chr);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        List<Mob> mobListWithTemplateId = chr.getField()
                .getMobsInRect(summon.getPosition().getRectAround(si.getRects().get(0)))
                .stream()
                .filter(mob -> mob.getTemplateId() == mobTemplateId)
                .collect(Collectors.toList());
        Option o = new Option();
        o.nOption = si.getValue(z, slv);
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(time, slv);
        for (Mob mob : mobListWithTemplateId) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            mts.addStatOptionsAndBroadcast(MobStat.Speed, o);
        }
    }

    public void applyEmeraldDustDebuffToMob(Summon summon, int mobTemplateId) {
        Skill skill = getEmeraldFlowerSkill(chr);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        List<Mob> mobListWithTemplateId = chr.getField()
                .getMobsInRect(summon.getPosition().getRectAround(si.getRects().get(0)))
                .stream()
                .filter(mob -> mob.getTemplateId() == mobTemplateId)
                .collect(Collectors.toList());
        Option o = new Option();
        o.nOption = si.getValue(w, slv);
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(time, slv);
        for (Mob mob : mobListWithTemplateId) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
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
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        Option o7 = new Option();
        Option o8 = new Option();
        Option o9 = new Option();
        switch (skillID) {
            case MERCILESS_WINDS:
                createMercilessWindForceAtom();
                break;

            case HOWLING_GALE_1:
            case HOWLING_GALE_2:
                updateWindEnergy(0);
                break;
            case STORM_ELEMENTAL:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1); //Indie
                break;
            case BOW_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case SYLVAN_AID:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(x, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o3);
                break;
            case ALBATROSS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieCr, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieMhp, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHP, o3); //Indie
                o4.nReason = skillID;
                o4.nValue = si.getValue(indiePad, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o4); //Indie
                o5.nOption = 1;
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Albatross, o5);
                break;
            case ALBATROSS_MAX:
                SkillInfo albaSI = SkillData.getSkillInfoById(ALBATROSS);
                int albaSLV = chr.getSkillLevel(ALBATROSS);
                o1.nReason = skillID;
                o1.nValue = si.getValue(indiePad, slv) + albaSI.getValue(indiePad, albaSLV);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1); //Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o2); //Indie
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieCr, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o3); //Indie
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieAsrR, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieAsrR, o4); //Indie
                o5.nReason = skillID;
                o5.nValue = si.getValue(indieAsrR, slv);
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieTerR, o5); //Indie
                o6.nReason = skillID;
                o6.nValue = albaSI.getValue(indieBooster, albaSLV);
                o6.tTerm = albaSI.getValue(time, albaSLV);
                tsm.putCharacterStatValue(IndieBooster, o6); //Indie
                o7.nReason = skillID;
                o7.nValue = si.getValue(ignoreMobpdpR, slv);
                o7.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o7);
                o8.nReason = skillID;
                o8.nValue = albaSI.getValue(indieMhp, albaSLV);
                o8.tTerm = albaSI.getValue(time, albaSLV);
                tsm.putCharacterStatValue(IndieMHP, o8);
                o9.nOption = 1;
                o9.rOption = skillID;
                o9.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Albatross, o9);
                break;
            case SHARP_EYES: // x = crit rate    y = max crit dmg
                // Short nOption is split in  2 bytes,  first one = CritDmg  second one = Crit%
                int cr = si.getValue(x, slv);
                int crDmg = si.getValue(y, slv);
                o1.xOption = cr;
                o1.yOption = crDmg;
                o1.nOption = (cr << 8) + crDmg;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1);
                break;
            case CALL_OF_CYGNUS_WA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case TRIFLING_WIND_I:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(TriflingWhimOnOff, o1);
                break;
            case EMERALD_FLOWER:
            case EMERALD_DUST:
                Position position = inPacket.decodePosition();
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setPosition(position);
                summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                summon.setMaxHP(si.getValue(x, slv));
                summon.setHp(summon.getMaxHP());
                field.spawnSummon(summon);
                break;
            case GLORY_OF_THE_GUARDIANS_WA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case STORM_BRINGER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(StormBringer, o1);
                break;
            case GALE_BARRIER:
                o1.nOption = si.getValue(w, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(GaleBarrier, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if (hitInfo.hpDamage <= 0 && hitInfo.mpDamage <= 0) {
            giveSecondWindBuff();
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private void giveSecondWindBuff() {
        if (!chr.hasSkill(SECOND_WIND)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o2 = new Option();
        Option o3 = new Option();
        Skill skill = chr.getSkill(SECOND_WIND);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        o2.nOption = si.getValue(pddX, slv);
        o2.rOption = skill.getSkillId();
        o2.tOption = 5; // time isn't a variable in the skill Info
        tsm.putCharacterStatValue(DEF, o2);
        o3.nReason = skill.getSkillId();
        o3.nValue = si.getValue(indiePad, slv);
        o3.tTerm = 5; // time isn't a variable in the skill Info
        tsm.putCharacterStatValue(IndiePAD, o3);
        tsm.sendSetStatPacket();
    }

    @Override
    public void handleLevelUp() {
        short level = chr.getLevel();
        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.WIND_ARCHER_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.WIND_ARCHER_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.WIND_ARCHER_4.getJobId());
//                break;
            case 120:
                giveCallOfCygnus(CALL_OF_CYGNUS_WA);
                break;
        }
        super.handleLevelUp();
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item beginnerBow = ItemData.getItemDeepCopy(1452051);
        chr.addItemToInventory(beginnerBow);

        Item arrowBow = ItemData.getItemDeepCopy(2060000);
        arrowBow.setQuantity(2000);
        chr.addItemToInventory(arrowBow);
    }
}
