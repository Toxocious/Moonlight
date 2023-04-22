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
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.AffectedArea;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class BlazeWizard extends Noblesse {

    public static final int ELEMENTAL_HARMONY_INT = 10000248;

    public static final int ORBITAL_FLAME = 12001020;
    public static final int GREATER_ORBITAL_FLAME = 12100020;
    public static final int GRAND_ORBITAL_FLAME = 12110020;
    public static final int FINAL_ORBITAL_FLAME = 12120006;

    public static final int CATACLYSM = 12121052;

    public static final int ORBITAL_FLAME_ATOM = 12000026;
    public static final int GREATER_ORBITAL_FLAME_ATOM = 12100028;
    public static final int GRAND_ORBITAL_FLAME_ATOM = 12110028;
    public static final int FINAL_ORBITAL_FLAME_ATOM = 12120010;

    public static final int FIREWALK_HORIZONTAL = 12001028;
    public static final int FIREWALK_VERTICAL = 12001027;

    public static final int IGNITION = 12101024; //Buff
    public static final int IGNITION_EXPLOSION = 12100029; // Explosion Attack
    public static final int FLASHFIRE = 12101025; //Special Skill
    public static final int WORD_OF_FIRE = 12101023; //Buff
    public static final int CONTROLLED_BURN = 12101022; //Special Skill

    public static final int CINDER_MAELSTROM = 12111022; //Special Skill
    public static final int PHOENIX_RUN = 12111023; //Special Buff
    public static final int PHOENIX_RUN_EFFECTS = 12111029;

    public static final int BURNING_CONDUIT = 12121005;
    public static final int FIRES_OF_CREATION = 12121004; //only used for visual cooldown
    public static final int FIRES_OF_CREATION_FOX = 12120014; //Buff
    public static final int FIRES_OF_CREATION_LION = 12120013; //Buff
    public static final int FLAME_BARRIER = 12121003; //Buff
    public static final int CALL_OF_CYGNUS_BW = 12121000; //Buff
    public static final int ORBITAL_FLAME_RANGE = 12121043; // Buff - toggle

    public static final int GLORY_OF_THE_GUARDIANS_BW = 12121053;

    public static final int SAVAGE_FLAME = 400021042;
    public static final int SAVAGE_FLAME_FOX = 400021044;
    public static final int SAVAGE_FLAME_FOX_ATOM = 400021045;

    //Flame Elements
    public static final int FLAME_ELEMENT = 12000022;
    public static final int GREATER_FLAME_ELEMENT = 12100026;
    public static final int GRAND_FLAME_ELEMENT = 12110024;
    public static final int FINAL_FLAME_ELEMENT = 12120007;

    private static final int[] addedSkills = new int[]{
            ELEMENTAL_HARMONY_INT,
    };

    boolean used;
    Position chrPos;
    int prevmap;
    private int flameCharge = 0;
    private byte phoenixFeatherCount = 0;

    public byte getPhoenixFeatherCount() {
        return phoenixFeatherCount;
    }

    public void setPhoenixFeatherCount(byte phoenixFeatherCount) {
        this.phoenixFeatherCount = phoenixFeatherCount;
    }

    public BlazeWizard(Char chr) {
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
        return JobConstants.isBlazeWizard(id);
    }


    @Override
    public void handleSkillRemove(Char chr, int skillID) {

        super.handleSkillRemove(chr, skillID);
    }

    private void summonFlameElement() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOptByCTSAndSkill(IndieMAD, getFlameElement()) == null && chr.hasSkill(FLAME_ELEMENT)) {
            Option o1 = new Option();
            Skill skill = chr.getSkill(getFlameElement());
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            Summon summon;
            Field field;
            field = c.getChr().getField();
            summon = Summon.getSummonBy(chr, getFlameElement(), slv);
            summon.setFlyMob(false);
            summon.setAttackActive(false);
            summon.setAssistType(AssistType.None);
            field.spawnSummon(summon);

            o1.nValue = si.getValue(x, slv);
            o1.nReason = getFlameElement();
            o1.tTerm = si.getValue(time, slv);
            tsm.putCharacterStatValue(IndieMAD, o1);
            tsm.sendSetStatPacket();
        }
    }

    private int getFlameElement() {
        int skill = 0;
        if (chr.hasSkill(FLAME_ELEMENT)) {
            skill = FLAME_ELEMENT;
        }
        if (chr.hasSkill(GREATER_FLAME_ELEMENT)) {
            skill = GREATER_FLAME_ELEMENT;
        }
        if (chr.hasSkill(GRAND_FLAME_ELEMENT)) {
            skill = GRAND_FLAME_ELEMENT;
        }
        if (chr.hasSkill(FINAL_FLAME_ELEMENT)) {
            skill = FINAL_FLAME_ELEMENT;
        }
        return skill;
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
            if (attackInfo.skillId != IGNITION_EXPLOSION) {
                applyIgniteOnMob(attackInfo);
            }
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();

        switch (attackInfo.skillId) {
            case ORBITAL_FLAME_ATOM:
            case GREATER_ORBITAL_FLAME_ATOM:
            case GRAND_ORBITAL_FLAME_ATOM:
            case FINAL_ORBITAL_FLAME_ATOM:
                summonFlameElement();
                break;
            case FIRES_OF_CREATION_LION:
                chr.setSkillCooldown(SAVAGE_FLAME, chr.getSkillLevel(SAVAGE_FLAME));
                updateFlameCharge(0);
                chr.dispose();
                break;
            case CATACLYSM:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 3;
                tsm.putCharacterStatValue(NotDamaged, o1);
                chr.addSkillCoolTime(CATACLYSM, 100000);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void applyIgniteOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(Ember)) {
            Skill skill = chr.getSkill(IGNITION);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            Option o = new Option();
            o.nOption = 1;
            o.rOption = skill.getSkillId();
            o.tOption = 10;
            o.wOption = 10;
            o.chr = chr;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Ember, o);
                    mts.createAndAddBurnedInfo(chr, skill);
                    incrementFlameCharge();
                }
            }
        }
    }

    private void incrementFlameCharge() {
        if (!chr.hasSkill(SAVAGE_FLAME)) {
            return;
        }
        updateFlameCharge(flameCharge + 1);
    }

    private void updateFlameCharge(int stack) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(SAVAGE_FLAME);
        int slv = chr.getSkillLevel(SAVAGE_FLAME);
        if (!chr.hasSkill(SAVAGE_FLAME)) {
            return;
        }
        int maxFlameCharge = si.getValue(y, slv);
        int remainingTime = (int) tsm.getRemainingTime(Ember, IGNITION);
        Option o = new Option();

        o.nOption = tsm.hasStat(Ember) ? 1 : 0;
        o.rOption = IGNITION;
        o.tOption = remainingTime;
        o.xOption = stack < 0 ? 0 : stack > maxFlameCharge ? maxFlameCharge : stack;
        o.setInMillis(true);
        tsm.putCharacterStatValue(Ember, o, true);
        tsm.sendSetStatPacket();
        flameCharge = o.xOption;
    }

    public void handleRemoveMobStat(MobStat mobStat, Mob mob) {
        if (mobStat == MobStat.Ember) {
            chr.write(UserLocal.explosionAttack(IGNITION_EXPLOSION, mob.getPosition(), mob.getObjectId(), 10));
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
        switch (skillID) {
            case FLASHFIRE: // TODO
                Position flamepos = chr.getPosition();
                if (used) {
                    if (chr.getFieldID() != prevmap) {
                        //Set Blink
                        prevmap = chr.getFieldID();
                        chr.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                        chrPos = chr.getPosition();
                        used = true;
                    } else {
                        //Clear Blink + Teleport
                        chr.write(WvsContext.flameWizardFlareBlink(chr, chrPos, true));
                        used = false;
                    }
                } else {
                    //Set Blink
                    prevmap = chr.getFieldID();
                    chr.write(WvsContext.flameWizardFlareBlink(chr, flamepos, false));
                    chrPos = chr.getPosition();
                    used = true;
                }
                break;
            case CONTROLLED_BURN:
                int healmp = si.getValue(x, slv);
                int healpercent = (chr.getMaxMP() * healmp) / 100;
                chr.healMP(healpercent);
                break;
            case BURNING_CONDUIT:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setDelay((short) 15);
                field.spawnAffectedArea(aa);
                break;
            case FIREWALK_HORIZONTAL:
            case FIREWALK_VERTICAL:
                field.broadcastPacket(WvsContext.flameWizardFlameWalkEffect(chr));
                break;
            case WORD_OF_FIRE:
                o1.nValue = -5; // si.getValue(indieBooster, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nValue = si.getValue(indieMad, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o2);
                break;
            case FLAME_BARRIER:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case CALL_OF_CYGNUS_BW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1); //Indie
                break;
            case IGNITION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o1.xOption = flameCharge;
                tsm.putCharacterStatValue(Ember, o1); // xOption = Savage Flame
                break;
            case FIRES_OF_CREATION_FOX:
            case FIRES_OF_CREATION_LION:
                si = SkillData.getSkillInfoById(FIRES_OF_CREATION);
                slv = chr.getSkillLevel(FIRES_OF_CREATION);

                if (chr.hasSkillOnCooldown(FIRES_OF_CREATION_FOX) || chr.hasSkillOnCooldown(FIRES_OF_CREATION_LION)) {
                    break;
                }
                chr.setSkillCooldown(FIRES_OF_CREATION, slv); // to display cooldown in quickslot
                chr.setSkillCooldown(FIRES_OF_CREATION_FOX, slv);
                chr.setSkillCooldown(FIRES_OF_CREATION_LION, slv);

                field.getSummons().stream()
                        .filter(s -> (s.getSkillID() == FIRES_OF_CREATION_FOX || s.getSkillID() == FIRES_OF_CREATION_LION)
                                && s.getChr() == chr)
                        .forEach(field::removeLife);

                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(skillID == FIRES_OF_CREATION_FOX);
                summon.setMoveAbility(skillID == FIRES_OF_CREATION_LION ? MoveAbility.WalkSmart : MoveAbility.Walk);
                summon.setAssistType(AssistType.AttackCounter);
                summon.setSummonTerm(SkillData.getSkillInfoById(FIRES_OF_CREATION).getValue(time, slv));
                field.spawnSummon(summon);

                o1.nOption = si.getValue(y, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(IgnoreTargetDEF, o1);
                o2.nOption = si.getValue(z, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o2);
                break;
            case CINDER_MAELSTROM:  //Special Summon // TODO Stationary mobs need to be excluded   |  MoveAction != 0  ?
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                Rect rect = chr.getPosition().getRectAround(new Rect(-200, -200, 300, 300));
                //   Rect rect = chr.getPosition().getRectAround(si.getLastRect());
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                List<Mob> mobList = field.getMobsInRect(rect).stream().filter(m -> !m.isBoss()).collect(Collectors.toList());
                if (mobList.size() > 0) {
                    Mob mob = Util.getRandomFromCollection(mobList);
                    summon.setMobTemplateId(mob.getTemplateId());
                    summon.setPosition(mob.getPosition());
                    summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                    field.spawnSummon(summon);
                }
                break;
            case PHOENIX_RUN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case GLORY_OF_THE_GUARDIANS_BW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;

            case ORBITAL_FLAME_RANGE:
                if (tsm.hasStat(AddRangeOnOff)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = si.getValue(range, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(AddRangeOnOff, o1);
                }
                break;
            case CATACLYSM:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 3;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void doFoxSavageFlameAttack() {
        createSavageFlameForceAtoms();
        chr.setSkillCooldown(BlazeWizard.SAVAGE_FLAME, chr.getSkillLevel(BlazeWizard.SAVAGE_FLAME));
        updateFlameCharge(0);
    }

    private void createSavageFlameForceAtoms() {
        SkillInfo si = SkillData.getSkillInfoById(SAVAGE_FLAME);
        Field field = chr.getField();
        int slv = chr.getSkillLevel(SAVAGE_FLAME);
        int flameChargeOverReq = flameCharge - si.getValue(x, slv);
        Rect rect = chr.getPosition().getRectAround(SkillData.getSkillInfoById(SAVAGE_FLAME_FOX).getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        if (flameChargeOverReq < 0) {
            return;
        }
        int atomsCreated = si.getValue(q, slv);
        int maxRecreations = si.getValue(u2, slv) + (flameChargeOverReq * 2);
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        ForceAtomEnum fae = ForceAtomEnum.FLAME_DISCHARGE;
        for (int i = 0; i < atomsCreated; i++) {
            Mob mob;
            if (field.getBossMobsInRect(rect).size() > 0) {
                mob = Util.getRandomFromCollection(field.getBossMobsInRect(rect));
            } else {
                mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
            }
            if (mob != null) {
                targetList.add(mob.getObjectId());
            } else {
                targetList.add(0);
            }
            int fImpact = new Random().nextInt(4) + 10;
            int sImpact = new Random().nextInt(2) + 4;
            int angle = new Random().nextInt(20) + ((chr.isLeft() ? 260 : 260) - 10);
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, sImpact,
                    angle, 750, Util.getCurrentTime(), 0, 0,
                    new Position());
            faiList.add(fai);
        }
        ForceAtom fa = new ForceAtom(false, chr.getId(), chr.getId(), fae,
                true, targetList, SAVAGE_FLAME_FOX_ATOM, faiList, new Rect(), 0, 0,
                new Position(), 0, new Position(), 0);
        fa.setMaxRecreationCount(maxRecreations);
        chr.createForceAtom(fa);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    public void reviveByPhoenixRun() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(PHOENIX_RUN);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        chr.heal(chr.getMaxHP() / 2); // 50%
        tsm.removeStatsBySkill(PHOENIX_RUN);
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Phoenix Run.");

        Position position = chr.getPosition();
        chr.write(FieldPacket.teleport(new Position(position.getX() + (chr.isLeft() ? +350 : -350), position.getY()), chr));

        // Hit effect
        chr.write(UserPacket.effect(Effect.skillUse(PHOENIX_RUN_EFFECTS, chr.getLevel(), slv, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(PHOENIX_RUN_EFFECTS, chr.getLevel(), slv, 0)));

        // Backstep effect
        chr.write(UserPacket.effect(Effect.skillAffected(PHOENIX_RUN_EFFECTS, slv, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(PHOENIX_RUN_EFFECTS, slv, 0)));

        o.nOption = 1;
        o.rOption = PHOENIX_RUN;
        o.tOption = si.getValue(x, slv); // duration
        tsm.putCharacterStatValue(NotDamaged, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        short level = chr.getLevel();
        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.BLAZE_WIZARD_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.BLAZE_WIZARD_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.BLAZE_WIZARD_4.getJobId());
//                break;
            case 120:
                giveCallOfCygnus(CALL_OF_CYGNUS_BW);
                break;
        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item beginnerStaff = ItemData.getItemDeepCopy(1382100);
        chr.addItemToInventory(beginnerStaff);
    }
}
