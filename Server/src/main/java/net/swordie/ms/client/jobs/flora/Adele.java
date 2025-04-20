package net.swordie.ms.client.jobs.flora;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.SecondAtom;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.SecondAtomPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
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
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * @Author Hex
 * Created on 10/10/2020
 */
public class Adele extends Job {

    // Beginner
    public static final int ARTISTIC_RECALL = 150021000;
    public static final int MAGIC_CONVERSION = 150000079;
    public static final int RECALLING_GREATNESS = 150020006; // TODO: Level this up periodically

    // First Job
    public static final int MAGIC_DISPATCH = 151001001;
    public static final int AETHER_CRYSTAL = 151100002;
    public static final int HIGH_RISE = 151001004;

    // Second Job
    public static final int AETHER_WEAVING = 151100017;
    public static final int SKEWERING = 151101000;
    public static final int IMPALE = 151101001;
    public static final int RESONANCE_RUSH = 151101003;
    public static final int RESONANCE_RUSH_1 = 151101004;
    public static final int RESONANCE_RUSH_2 = 151101010;
    public static final int WEAVE_INFUSION = 151101005;
    public static final int AETHER_FORGE = 151101006;
    public static final int AETHER_FORGE_1 = 151101007;
    public static final int AETHER_FORGE_2 = 151101008;
    public static final int AETHER_FORGE_3 = 151101009;
    public static final int AETHERIAL_ARMS = 151101013;

    // Third Job
    public static final int EVISCERATE = 151111000;
    public static final int REIGN_OF_DESTRUCTION = 151111001;
    public static final int NOBLE_SUMMONS = 151111002;
    public static final int HUNTING_DECREE = 151111003;
    public static final int FEATHER_FLOAT = 151111004;
    public static final int TRUE_NOBILITY = 151111005;

    // Forth Job
    public static final int AETHER_MASTERY = 151120012;

    public static final int CLEAVE = 151121000;
    public static final int GRAVE_PROCLAMATION = 151121001;
    public static final int PLUMMET = 151121002;
    public static final int AETHER_BLOOM = 151121003;
    public static final int AETHER_GUARD = 151121004;
    public static final int HERO_OF_THE_FLORA = 151121005;
    public static final int FLORAN_HEROS_WILL = 151121006;

    // Hyper Skills
    public static final int BLADE_TORRENT = 151121040;
    public static final int SHARDBREAKER = 151121041;
    public static final int DIVINE_WRATH = 151121042;

    // V Skills
    public static final int INFINITY_BLADE = 400011108;
    public static final int LEGACY_RESTORATION = 400011109;

    private Map<Integer, Integer> capeAtoms = new HashMap<>();
    private List<Summon> summonList = new ArrayList<>();
    private int capeCounter = 0;
    private ScheduledFuture aetherTimer;

    private boolean isTriggerSkill(int skillID) {
        return switch (skillID) {
            case SKEWERING, EVISCERATE, CLEAVE, PLUMMET -> true;
            default -> false;
        };
    }

    public Map<Integer, Integer> getCapeAtoms() {
        return capeAtoms;
    }

    public void setCapeAtoms(Map<Integer, Integer> capeAtoms) {
        this.capeAtoms = capeAtoms;
    }

    public void clearCapeAtoms() {
        getCapeAtoms().clear();
        setCapeCounter(0);
    }

    public void clearAetherTimer() {
        if (aetherTimer != null) {
            aetherTimer.cancel(true);
        }
    }

    public void setCapeCounter(int forceAtomKeyCounter) {
        this.capeCounter = forceAtomKeyCounter;
    }

    public int getNewCapeCounter() {
        return capeCounter++;
    }

    private static final int[] addedSkills = new int[]{
            RECALLING_GREATNESS
    };

    public Adele(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (chr.hasSkill(151100017)) {
            aetherTimer = EventManager.addFixedRateEvent(this::autoModifyAether, 1000, 3000);
        }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isAdele(id);
    }


    private void autoModifyAether() {
        if (chr == null || chr.getField() == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int delta = tsm.hasStat(CharacterTemporaryStat.Nobility) ? 40 : 15;
        int currentEnergy = tsm.getOption(CharacterTemporaryStat.AetherGauge).nOption;
        updateAether(currentEnergy + delta);
    }

    private int getCurrentAether() {
        if (chr == null || chr.getField() == null) {
            return 0;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int currentEnergy = tsm.getOption(CharacterTemporaryStat.AetherGauge).nOption;
        return currentEnergy;
    }

    private void modifyAether(int change) {
        if (chr == null || chr.getField() == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(CharacterTemporaryStat.Creation)) {
            return;
        }
        int currentEnergy = tsm.getOption(CharacterTemporaryStat.AetherGauge).nOption;
        updateAether(currentEnergy + change);
    }

    private void updateAether(int aether) {
        if (chr == null) {
            return;
        }
        int maxAether = 300;
        if (chr.hasSkill(AETHER_MASTERY)) {
            maxAether = 400;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = (aether > maxAether ? maxAether : (aether < 0 ? 0 : aether));
        o.nReason = 15002;
        o.tOption = 0;
        tsm.putCharacterStatValue(CharacterTemporaryStat.AetherGauge, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        List<SecondAtom> seconAtoms = new LinkedList();
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
        Option o = new Option();
        Option o1 = new Option();

        if (hasHitMobs && isTriggerSkill(attackInfo.skillId)) {
            modifyAether(+10);
            if (!chr.hasSkillOnCooldown(AETHER_FORGE_1) && tsm.hasStat(CharacterTemporaryStat.Creation)) { //if (tsm.hasStat(CharacterTemporaryStat.MassDestructionRockets)) {
                getCapeAtoms().forEach((key, objectID) -> {
                    if (getCurrentAether() >= 100) {
                        chr.getField().broadcastPacket(SecondAtomPacket.secondAtomAttack(chr, objectID, key > 1 ? 0 : getCurrentAether() >= 300 ? 3 : getCurrentAether() >= 200 ? 2 : 1));
                    }
                });
                chr.addSkillCoolTime(AETHER_FORGE_1, chr.getJob() == 15112 ? 6000 : chr.getJob() == 15111 ? 9000 : 12000);
            }
            if (!chr.hasSkillOnCooldown(AETHERIAL_ARMS) && tsm.hasStat(CharacterTemporaryStat.Restore)) { //if (tsm.hasStat(CharacterTemporaryStat.MassDestructionRockets)) {
                Rect rect = chr.getRectAround(new Rect(-600, -600, 600, 600));
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                for (int i = 0; i < 5; i++) {
                    List<Mob> lifes = chr.getField().getMobsInRect(rect);
                    if (lifes.size() <= 0) {
                        return;
                    }
                    Mob mob = Util.getRandomFromCollection(chr.getField().getMobsInRect(rect));


                    final SecondAtom fa = new SecondAtom(
                            chr.getField().getNewObjectID(),
                            chr.getId(),
                            mob.getObjectId(),
                            0,
                            MAGIC_DISPATCH,
                            chr.getSkillLevel(MAGIC_DISPATCH),
                            chr.getPosition(),
                            SecondAtomEnum.MagicShard.getVal(),
                            chr.getNewSecondAtomKey(),
                            0,
                            1,
                            i < 1 ? 0 : i < 3 ? 120 : 240,
                            1200,
                            3000,
                            false,
                            null);
                    seconAtoms.add(fa);
                }
                chr.createSecondAtom(seconAtoms, true);
                seconAtoms.clear();
                chr.addSkillCoolTime(AETHERIAL_ARMS, 8000);
            }
        }

        switch (attackInfo.skillId) {
            case GRAVE_PROCLAMATION:
                if (hasHitMobs) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.SecondAtomLockOn);
                        tsb.setNOption(1);
                        tsb.setROption(skillID);
                        tsb.setXOption(mob.getObjectId());
                        tsb.setYOption(skillID);
                        tsb.setExpireTerm(1080);
                        tsm.putCharacterStatValue(CharacterTemporaryStat.SecondAtomLockOn, tsb.getOption());
                        tsm.sendSetStatPacket();
                    }
                }
                break;
            case BLADE_TORRENT:
                o.nOption = 1;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(SkillStat.time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o);
                }
                break;
            case AETHER_FORGE_1:
            case AETHER_FORGE_2:
            case AETHER_FORGE_3:
            case HUNTING_DECREE:
                if (hasHitMobs) {
                    int chance = 100;
                    if (skillID == HUNTING_DECREE) {
                        chance = 15;
                    }
                    List<MobAttackInfo> mai = attackInfo.mobAttackInfo;
                    if (attackInfo.mobAttackInfo.size() <= 0) {
                        return;
                    }
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(Util.getRandomFromCollection(mai).mobId);
                    if (mob == null) {
                        return;
                    }
                    if (Util.succeedProp(chance)) {
                        spawnAetherShard(mob.getPosition());
                    }
                }
                break;
            case RESONANCE_RUSH:
            case RESONANCE_RUSH_1:
                si = SkillData.getSkillInfoById(RESONANCE_RUSH_2);
                int amount = 1;
                if (tsm.hasStat(CharacterTemporaryStat.Adele_Resonance)) {
                    amount = tsm.getOption(CharacterTemporaryStat.Adele_Resonance).xOption;
                    if (amount < 3) {
                        amount++;
                    }
                }
                o.nValue = si.getValue(SkillStat.z, slv) * amount;
                o.nReason = RESONANCE_RUSH_2;
                o.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieIgnoreMobpdpR, o);
                o1.nOption = amount; //idk
                o1.xOption = amount;
                o1.yOption = si.getValue(SkillStat.y, slv) * amount;
                o1.rOption = RESONANCE_RUSH_2;
                o1.tOption = si.getValue(SkillStat.time, slv) * 2;
                tsm.putCharacterStatValue(CharacterTemporaryStat.Adele_Resonance, o1);
                tsm.sendSetStatPacket();
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    public void createCapeAtoms() {
        ArrayList<SecondAtom> atomToCreate = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int atomObjectID = chr.getField().getNewObjectID();
            final SecondAtom capeAtom =
                    new SecondAtom(atomObjectID,
                            chr.getId(),
                            0,
                            0,
                            AETHER_FORGE,
                            chr.getSkillLevel(AETHER_FORGE),
                            chr.getPosition(),
                            SecondAtomEnum.SwordCape.getVal() /*+ getCapeAtoms().size() */ + i,
                            capeAtoms.size(),
                            0,
                            65,
                            0,
                            1320,
                            0,
                            false,
                            null);

            atomToCreate.add(capeAtom);
            capeAtoms.put(capeAtoms.size(), atomObjectID);
            chr.createSecondAtom(atomToCreate);
            atomToCreate.clear();
        }
    }

    public void removeCapeAtoms() {
//        List<SecondAtom> toRemove = new ArrayList<>();
//        List<SecondAtom> toRemoveCape = new ArrayList<>();
//
//        chr.getSecondAtoms().forEach((k, info) -> {
//            chr.getField().broadcastPacket(SecondAtomPacket.removeSecondAtom(chr, info.getObjectID()));
//        });
//
//        getCapeAtoms().forEach((k, objectID) -> {
//            chr.getField().broadcastPacket(SecondAtomPacket.removeSecondAtom(chr, objectID));
//        });
//
//        for (SecondAtom str : toRemove) {
//            chr.getSecondAtoms().remove(str);
//        }
//        for (SecondAtom str : toRemoveCape) {
//            getCapeAtoms().remove(str);
//        }

//        chr.setSecondAtomKeyCounter(1);
//        setCapeCounter(0);

        capeAtoms.forEach((key, objectID) -> {
            chr.getField().broadcastPacket(SecondAtomPacket.removeSecondAtom(chr, objectID));
        });
        capeAtoms.clear();

        chr.setSecondAtomKeyCounter(1); // not sure what this is for
    }

    public void spawnAetherShard(Position pos) {
        Skill skill = chr.getSkill(AETHER_CRYSTAL);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(AETHER_CRYSTAL);
        }
        Summon summon = Summon.getSummonByNoCTS(c.getChr(), si.getSkillId(), si.getCurrentLevel());
        summon.setPosition(pos);
        summon.setMoveAction((byte) 4);
        summon.setCurFoothold((short) 0);
        summon.setMoveAbility(MoveAbility.Stop);
        summon.setAssistType(AssistType.None);
        summon.setEnterType(EnterType.NoAnimation);
        summon.setFlyMob(false);
        summon.setBeforeFirstAttack(true);
        summon.setAttackActive(true);
        summon.setSummonTerm(30);

        summonList.add(summon);
        if (summonList.size() > 7) {
            chr.getField().removeLife(summonList.get(0));
            summonList.remove(0);
        }

        chr.getField().spawnAddSummon(summon);
    }

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        List<SecondAtom> seconAtoms = new LinkedList();
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        Rect rect = chr.getRectAround(new Rect(-600, -600, 600, 600));
        int mobObjId;
        Field field = chr.getField();
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case AETHER_FORGE:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                    removeCapeAtoms();
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.Creation, o1);
                    createCapeAtoms();
                }
                break;
            case AETHERIAL_ARMS:
                if (tsm.hasStatBySkillId(skillID)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;// si.getValue(SkillStat.x, slv); ?? 8 in wz 1 in sniff?
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(SkillStat.time, slv);
                    tsm.putCharacterStatValue(CharacterTemporaryStat.Restore, o1);
                }
                break;
            case WEAVE_INFUSION:
                o1.rOption = skillID;
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Booster, o1);
                break;
            case HERO_OF_THE_FLORA:
                o1.rOption = skillID;
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.BasicStatUp, o1);
                break;
            case FLORAN_HEROS_WILL:
                tsm.removeAllDebuffs();
                break;
            case REIGN_OF_DESTRUCTION:
                Summon summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setSummonTerm(si.getValue(SkillStat.time, slv));
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case MAGIC_DISPATCH:
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                for (int i = 0; i < 5; i++) {
                    List<Mob> lifes = field.getMobsInRect(rect);
                    if (lifes.size() <= 0) {
                        return;
                    }
                    Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));


                    final SecondAtom fa = new SecondAtom(
                            chr.getField().getNewObjectID(),
                            chr.getId(),
                            mob.getObjectId(),
                            0,
                            MAGIC_DISPATCH,
                            chr.getSkillLevel(MAGIC_DISPATCH),
                            chr.getPosition(),
                            SecondAtomEnum.MagicShard.getVal(),
                            chr.getNewSecondAtomKey(),
                            0,
                            1,
                            i < 1 ? 0 : i < 3 ? 120 : 240,
                            600,
                            2400,
                            false,
                            null);

                    seconAtoms.add(fa);
                }
                chr.createSecondAtom(seconAtoms, true);
                seconAtoms.clear();
                break;
            case HUNTING_DECREE:
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                for (int i = 0; i < 2; i++) {
                    List<Mob> lifes = field.getMobsInRect(rect);
                    if (lifes.size() <= 0) {
                        return;
                    }
                    Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));

                    final SecondAtom fa = new SecondAtom(
                            chr.getField().getNewObjectID(),
                            chr.getId(),
                            mob.getObjectId(),
                            0,
                            HUNTING_DECREE,
                            chr.getSkillLevel(HUNTING_DECREE),
                            rect.getRandomPositionInside(),
                            SecondAtomEnum.FlyingSword.getVal(),
                            chr.getNewSecondAtomKey(),
                            0,
                            80,
                            0,
                            1000,
                            40000,
                            false,
                            null);

                    seconAtoms.add(fa);
                }
                chr.createSecondAtom(seconAtoms, true);
                seconAtoms.clear();
                modifyAether(-100);
                break;
            case INFINITY_BLADE:
                o1.nValue = 1;
                o1.nReason = 15112;
                o1.tTerm = 3;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieNotDamaged, o1);

                for (int i = 0; i < 18; i++) {
                    List<Mob> lifes = field.getMobsInRect(rect);
                    if (lifes.size() <= 0) {
                        return;
                    }
                    Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));

                    final SecondAtom fa = new SecondAtom(
                            chr.getField().getNewObjectID(),
                            chr.getId(),
                            0,
                            0,
                            INFINITY_BLADE,
                            chr.getSkillLevel(INFINITY_BLADE),
                            rect.getRandomPositionInside(),
                            SecondAtomEnum.RedFlyingSword.getVal(),
                            chr.getNewSecondAtomKey(),
                            0,
                            65,
                            0,
                            1320,
                            31000,
                            false,
                            null);

                    seconAtoms.add(fa);
                }
                chr.createSecondAtom(seconAtoms, true);
                seconAtoms.clear();
                break;
            case LEGACY_RESTORATION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(SkillStat.indieDamR, slv);
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                o2.rOption = skillID;
                o2.nOption = 1;
                o2.tOption = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Nobility, o1);
                break;
            case DIVINE_WRATH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(SkillStat.indieDamR, slv);
                o1.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                break;
            case TRUE_NOBILITY:
                o1.rOption = skillID;
                o1.nOption = 10; //Sniff
                o1.tOption = si.getValue(SkillStat.time, slv);
                o1.xOption = si.getValue(SkillStat.x, slv);
                o1.yOption = si.getValue(SkillStat.y, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Nobility, o1);
                break;
            case SHARDBREAKER:
                if (skillUseInfo.spawnCrystals) break;
                List<Rect> shardRects = new ArrayList<>();
                for (Tuple<Integer, Position> shard : skillUseInfo.shardsPositions) {
                    Position shardPosition = shard.getRight();
                    Rect shardRect = shardPosition.getRectAround(si.getLastRect());
                    shardRects.add(shardRect);
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, SHARDBREAKER, slv);
                    aa.setSkillID(SHARDBREAKER);
                    aa.setPosition(shard.getRight());
                    aa.setDelay((short) 3);
                    aa.setDuration(1590);
                    aa.setRect(shard.getRight().getRectAround(si.getLastRect()));
                    aa.setHitMob(shard.getLeft() != 0);
                    chr.getField().spawnAffectedArea(aa);
                }
                chr.write(UserLocal.adeleShardBreakerResult(SHARDBREAKER, shardRects));
                break;
            case AETHER_CRYSTAL:
                spawnAetherShard(skillUseInfo.endingPosition);
                break;
            case HIGH_RISE:
                o2.nReason = skillID;
                o2.nValue = 50;
                o2.tTerm = si.getValue(SkillStat.time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.Indie208_63, o2);
                o1.rOption = skillID;
                o1.nOption = 50;
                o1.tOption = 1900 / 1000;
                tsm.putCharacterStatValue(CharacterTemporaryStat.NewFlying, o1);
                break;
            case RESONANCE_RUSH: {
                chr.getField().removeLife(skillUseInfo.objectId, true);
                break;
            }
        }

        tsm.sendSetStatPacket();
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        cs.setLevel(10);
        cs.setStr(4);
        cs.setDex(4);
        cs.setInt(4);
        cs.setLuk(4);
        cs.setAp(4 + cs.getLevel() * 5);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
        handleJobAdvance(JobConstants.JobEnum.ADELE_1.getJobId());
        chr.addSpToJobByCurrentJob(3);

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item secondary = ItemData.getItemDeepCopy(1354000); // unk
        if (secondary != null) {
            chr.addItemToInventory(secondary);
        }
    }

}
