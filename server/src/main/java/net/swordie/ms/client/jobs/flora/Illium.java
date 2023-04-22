package net.swordie.ms.client.jobs.flora;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Summoned;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import net.swordie.ms.life.AffectedArea;

/**
 * @author Sjonnie
 * Created on 6/25/2018.
 */
public class Illium extends Job {

    public static final int SHELTER_RETURN = 150001021; // requires Complete QuestId  34900
    public static final int MAGIC_CONVERSION = 150000079;

    public static final int EX = 152001003;
    public static final int RADIANT_ORB = 152001002;
    public static final int RADIANT_JAVELIN = 152001001;
    public static final int CRYSTALLINE_WINGS = 152001004;
    public static final int CRYSTALLINE_WINGS_FLY = 152001005;
    public static final int LUCENT_BRAND = 152000007;
    public static final int UMBRAL_BRAND_HIDDEN = 152000010;

    public static final int REACTION_DESTRUCTION = 152100001;
    public static final int REACTION_DOMINATION = 152100002;
    public static final int DEPLOY_CRYSTAL = 152101000;
    public static final int REPOSITION_CRYSTAL = 152101003;
    public static final int CRYSTAL_BATTERY = 152100010;
    public static final int VORTEX_OF_LIGHT = 152101006;
    public static final int UMBRAL_BRAND = 152100012;
    public static final int GAUNTLET_FRENZY = 152101007;
    public static final int MACHINA = 152101008;

    public static final int CRYSTAL_BATTERY_II = 152110008;
    public static final int LUCENT_BRAND_II = 152110009;
    public static final int UMBRAL_BRAND_II = 152110010;
    public static final int RESONANCE = 152111007;
    public static final int REACTION_DESTRUCTION_II = 152110001;
    public static final int REACTION_DOMINATION_II = 152110002;
    public static final int RADIANT_JAVELIN_ENHANCED = 152110004;

    public static final int RADIANT_ORB_II = 152120003;
    public static final int CRYSTAL_BATTERY_III = 152120014;
    public static final int LUCENT_BRAND_III = 152120012;
    public static final int UMBRAL_BRAND_III = 152120013;
    public static final int CRYSTAL_SKILL_DEUS = 152121005;
    public static final int HERO_OF_THE_FLORA = 152121009;
    public static final int FLORAN_HERO_WILL = 152121010;
    public static final int FLASH_CRYSTAL_BATTERY = 152121011;
    public static final int RADIANT_JAVELIN_II = 152120001;
    public static final int LONGINUS_SPEAR = 152121004;
    public static final int WINGS_OF_GLORY = 152111003;
    public static final int DEUS_SUB = 152121006;
    public static final int VORTEX_WINGS = 152121007;
    public static final int RADIANT_ATOM = 152120002;

    public static final int LONGINUS_ZONE = 152121041;
    public static final int DIVINE_WRATH = 152121042;
    public static final int CRYSTALLINE_BULWARK = 152121043;

    // V skills
    public static final int CRYSTAL_IGNITION = 400021061;
    public static final int REFLECTION_SPECTRAL_BLAST = 400021062;
    public static final int TEMPLAR_KNIGHT = 400021063;
    public static final int CRYSTALLINE_SPIRIT = 400021068;


    public static final int CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT = 1;
    public static final int CRYSTAL_SKILL_ID_RESONANCE = 2;
    public static final int CRYSTAL_SKILL_ID_DEUS = 3;
    public static final int CRYSTAL_SKILL_ID_WINGS_OF_GLORY = 4;
    public static final int CRYSTAL_SKILL_ID_VORTEX_WINGS = 5;

    private static final int[] lucentSkills = new int[]{
            LUCENT_BRAND,
            LUCENT_BRAND_II,
            LUCENT_BRAND_III
    };

    private static final int[] umbralSkills = new int[]{
            UMBRAL_BRAND,
            UMBRAL_BRAND_II,
            UMBRAL_BRAND_III
    };

    public Map<Integer, Boolean> crystalSkillMap = new HashMap<Integer, Boolean>() {{
        put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, true);
        put(CRYSTAL_SKILL_ID_RESONANCE, true);
        put(CRYSTAL_SKILL_ID_DEUS, true);
        put(CRYSTAL_SKILL_ID_WINGS_OF_GLORY, true);
        put(CRYSTAL_SKILL_ID_VORTEX_WINGS, true);
    }};

    private static final int[] addedSkills = new int[]{
            SHELTER_RETURN,
            MAGIC_CONVERSION,
    };

    public Illium(Char chr) {
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
        return JobConstants.isIllium(id);
    }


    public Summon getCrystal() {
        return chr.getField().getSummons().stream().filter(s -> s.getSkillID() == DEPLOY_CRYSTAL && s.getChr() == chr).findAny().orElse(null);
    }

    private int getCrystalCharge() {
        if (getCrystal() == null) {
            return 0;
        }
        return getCrystal().getCount();
    }

    private void setCrystalCharge(int charge) {
        if (getCrystal() != null) {
            getCrystal().setCount(charge);
        }
    }

    private int getShards() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(CharacterTemporaryStat.MassDestructionRockets) ? tsm.getOption(CharacterTemporaryStat.MassDestructionRockets).nOption : 0;
    }

    public void incrementCrystallineShard() {
        if (!chr.hasSkill(CRYSTALLINE_SPIRIT) || chr.hasSkillOnCooldown(CRYSTALLINE_SPIRIT)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(CRYSTALLINE_SPIRIT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int maxStack = si.getValue(y, slv);
        int count = 1;
        if (tsm.hasStat(CharacterTemporaryStat.MassDestructionRockets)) {
            count = getShards();
            if (count < maxStack) {
                count++;
            }
        }
        updateShardCount(count);
        chr.addSkillCoolTime(CRYSTALLINE_SPIRIT, 20 * 1000);
    }

    private void updateShardCount(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = count;
        tsm.putCharacterStatValue(CharacterTemporaryStat.MassDestructionRockets, o);
        tsm.sendSetStatPacket();
    }


    private void resetCrystalBattery() {
        crystalSkillMap.put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_RESONANCE, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_DEUS, true);
        crystalSkillMap.put(CRYSTAL_SKILL_ID_WINGS_OF_GLORY, true);
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
    }

    public void doResonanceSkill() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 1, crystalSkillMap));
        if (!chr.hasSkill(RESONANCE)) {
            return;
        }
        Skill skill = chr.getSkill(RESONANCE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        o.nOption = 1;
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(x, slv);
        tsm.putCharacterStatValue(CharacterTemporaryStat.Resonance, o);
        tsm.sendSetStatPacket();
        crystalSkillMap.put(CRYSTAL_SKILL_ID_RESONANCE, false);
        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
    }

    public void incrementCrystal(int skillId) {
        int increment = (getIncrementBySkillId(skillId) * (hasFlashCrystalBattery() ? 2 : 1));
        changeCrystalCharge(getCrystalCharge() + increment);
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks

        if (!chr.hasSkillOnCooldown(REACTION_DESTRUCTION_II) && (chr.getSkillLevel(REACTION_DESTRUCTION_II) >= 1) && (skillId == RADIANT_JAVELIN_II || skillId == RADIANT_JAVELIN || skillId == RADIANT_JAVELIN || skillId == LONGINUS_SPEAR)) {
            doDestructionII();
        } else if (!chr.hasSkillOnCooldown(REACTION_DESTRUCTION) && (chr.getSkillLevel(REACTION_DESTRUCTION) >= 1) && (skillId == RADIANT_JAVELIN || skillId == RADIANT_JAVELIN)) {
            doDestruction();
        }
    }

    private void doDestruction() {
        SkillInfo si = SkillData.getSkillInfoById(REACTION_DESTRUCTION);
        int slv = chr.getSkillLevel(REACTION_DESTRUCTION);
        //   chr.write(Summoned.summonUseSpecifiedSkill(getCrystal(), REACTION_DESTRUCTION));
        chr.addSkillCoolTime(REACTION_DESTRUCTION, si.getValue(cooltime, slv) * 1000);
    }

    private void doDestructionII() {
        SkillInfo si = SkillData.getSkillInfoById(REACTION_DESTRUCTION_II);
        int slv = chr.getSkillLevel(REACTION_DESTRUCTION_II);
        chr.write(Summoned.summonUseSpecifiedSkill(getCrystal(), REACTION_DESTRUCTION_II));
        chr.addSkillCoolTime(REACTION_DESTRUCTION_II, si.getValue(cooltime, slv) * 1000);
    }

    public void doCrystallineDestruction(int summonObjId, int summonAttackId) {
        Summon illi = (Summon) chr.getField().getLifeByObjectID(summonObjId);
        chr.write(Summoned.summonUseSpecifiedSkill(illi, summonAttackId));
    }

    private void changeCrystalCharge(int charge) {
        int curState = getCrystal().getState();

        charge = charge < 0 ? 0 : charge > getMaxCrystalCharge() ? getMaxCrystalCharge() : charge;
        setCrystalCharge(charge);
        getCrystal().setState(getCrystalStateByCharge(charge));
        chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 2)); // change Crystal Summon State

        if (curState != getCrystal().getState()) {
            chr.getField().broadcastPacket(Summoned.effect(getCrystal(), 3)); // new form effect
        } else {
            chr.getField().broadcastPacket(Summoned.effect(getCrystal(), 2)); // increment effect
        }

        if (getCrystalCharge() >= getMaxCrystalCharge() && !chr.getTemporaryStatManager().hasStat(CharacterTemporaryStat.CrystalBattery)) {
            giveCrystalBatteryBuff();
        }
    }

    private int getMaxCrystalCharge() {
        int maxCrystalCharge = 0;
        if (chr.hasSkill(CRYSTAL_BATTERY)) {
            maxCrystalCharge = 30;
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_II)) {
            maxCrystalCharge = 150;
        }
        return maxCrystalCharge;
    }


    private Skill getCrystalBatterySkill() {
        Skill skill = null;
        if (chr.hasSkill(CRYSTAL_BATTERY)) {
            skill = chr.getSkill(CRYSTAL_BATTERY);
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_II)) {
            skill = chr.getSkill(CRYSTAL_BATTERY_II);
        }
        if (chr.hasSkill(CRYSTAL_BATTERY_III)) {
            skill = chr.getSkill(CRYSTAL_BATTERY_III);
        }
        return skill;
    }

    private boolean hasFlashCrystalBattery() {
        return chr.getTemporaryStatManager().hasStat(CharacterTemporaryStat.FlashCrystalBattery);
    }

    private void giveCrystalBatteryBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.rOption = getCrystalBatterySkill().getSkillId();
        o.tOption = 10;
        tsm.putCharacterStatValue(CharacterTemporaryStat.CrystalBattery, o);
        tsm.sendSetStatPacket();
    }

    private int getIncrementBySkillId(int skillId) {
        switch (skillId) {
            case RADIANT_JAVELIN:
            case RADIANT_JAVELIN_II:
                return 1;
            case RADIANT_ORB:
            case RADIANT_ORB_II:
                return 2;
            case LONGINUS_SPEAR:
                return 3;
        }
        return 0;
    }

    private int getCrystalStateByCharge(int count) {
        int state = 0;
        if (count >= 0) {
            state = 0;
        }
        if (count >= 30) {
            state = 1;
        }
        if (count >= 60) {
            state = 2;
        }
        if (count >= 100) {
            state = 3;
        }
        if (count >= 150) {
            state = 4;
        }
        return state;
    }

    public void applyUmbralBrand(int oid) {
        int skillId = UMBRAL_BRAND;
        Skill skill = chr.getSkill(skillId);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Option o1 = new Option();
        int maxstack = chr.hasSkill(UMBRAL_BRAND_III) ? 5 : chr.hasSkill(UMBRAL_BRAND_II) ? 3 : 1;
        int skillID = chr.hasSkill(UMBRAL_BRAND_III) ? UMBRAL_BRAND_III : chr.hasSkill(UMBRAL_BRAND_II) ? UMBRAL_BRAND_II : UMBRAL_BRAND;
        Mob mob = (Mob) chr.getField().getLifeByObjectID(oid);
        MobTemporaryStat mts = mob.getTemporaryStat();

        o1.nOption = 1;
        o1.rOption = UMBRAL_BRAND_HIDDEN;
        o1.tOption = chr.hasSkill(UMBRAL_BRAND_III) ? 50 : chr.hasSkill(UMBRAL_BRAND_II) ? 30 : 10; //TODO fix this
        o1.xOption = 4;
        o1.yOption = 0;
        o1.zOption = skillID;

        if (mts.hasCurrentMobStat(MobStat.UmbralBrand)) {
            o1.nOption = mts.getCurrentOptionsByMobStat(MobStat.UmbralBrand).nOption;
            if (o1.nOption < maxstack) {
                o1.nOption++;
                o1.xOption = o1.nOption;
            }
            mts.removeMobStat(MobStat.UmbralBrand, false);
        }

        mts.addStatOptionsAndBroadcast(MobStat.UmbralBrand, o1);
    }



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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        if (hasHitMobs
                && attackInfo.skillId != CONVERSION_OVERDRIVE_ATTACK) {
            bonusConversionOverdriveAttack();
        }
        if (hasHitMobs &&
                (attackInfo.skillId != UMBRAL_BRAND &&
                        attackInfo.skillId != UMBRAL_BRAND_II &&
                        attackInfo.skillId != UMBRAL_BRAND_III
                ))
        {
            int UMBRAL_SKILLID = chr.hasSkill(UMBRAL_BRAND_III) ? UMBRAL_BRAND_III : chr.hasSkill(UMBRAL_BRAND_II) ? UMBRAL_BRAND_II : UMBRAL_BRAND;
            List<Integer> mobObjIdList = new ArrayList<>();
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                MobTemporaryStat mts = mob.getTemporaryStat();
                if (mts.hasCurrentMobStat(MobStat.UmbralBrand)) {
                    mobObjIdList.add(mob.getObjectId());
                }

                if (mobObjIdList.size() > 0) {
                    chr.write(UserLocal.userBonusAttackRequest(UMBRAL_SKILLID, mobObjIdList));
                }
            }
        }
        switch (attackInfo.skillId) {
            case DEPLOY_CRYSTAL:
                int attackingSkillId = attackInfo.summonSpecialSkillId;
                switch (attackingSkillId) {
                    case REACTION_DOMINATION:
                    case REACTION_DOMINATION_II:
                        slv = chr.getSkillLevel(attackingSkillId);
                        chr.setSkillCooldown(attackingSkillId, slv);
                        si = SkillData.getSkillInfoById(attackingSkillId);
                        chr.addSkillCoolTime(attackingSkillId, si.getValue(cooltime, slv) * 1000);
                        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                            applyUmbralBrand(mai.mobId);
                        }
                        break;
                    case VORTEX_OF_LIGHT:
                        crystalSkillMap.put(CRYSTAL_SKILL_ID_VORTEX_OF_LIGHT, false);
                        chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
                        break;
                    case REFLECTION_SPECTRAL_BLAST:
                        chr.setSkillCooldown(attackingSkillId, chr.getSkillLevel(CRYSTAL_IGNITION));
                        break;
                }
                chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
                break;
            case VORTEX_WINGS:
                handleVortexWings();
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    private void handleVortexWings() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        if (tsm.hasStat(CharacterTemporaryStat.WingsOfGlory)) {
            int nOpt = tsm.getOption(CharacterTemporaryStat.WingsOfGlory).nOption;
            int nSkill = tsm.getOption(CharacterTemporaryStat.WingsOfGlory).rOption;
            o.nOption = nOpt;
            o.rOption = nSkill;
            o.tOption = 0;
            o.xOption = 0;
            o.cOption = 1;
            tsm.putCharacterStatValue(CharacterTemporaryStat.WingsOfGlory, o);
            tsm.sendSetStatPacket();
        }
    }

    public void handleRadiantAtom() {
        Rect rect = new Rect(-550, -550, 550, 550);
        int targetID = 0;
        for (int i = 0; i < 2; i++) {
            ForceAtomEnum fae = ForceAtomEnum.RADIANT_JAVELIN_AFTER;
            Mob targetMob;
            if (chr.getField().getMobsInRect(chr.getRectAround(rect)).size() > 0) {
                targetMob = Util.getRandomFromCollection(chr.getField().getMobsInRect(chr.getRectAround(rect)));
            } else {
                targetMob = null;
            }
            targetID = (targetMob == null ? 0 : targetMob.getObjectId());

            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), 1, 50, 7, 0, 300, Util.getCurrentTime(), 0, 0, chr.getPosition());
            ForceAtom fa = new ForceAtom(false, chr.getId(), chr.getId(), fae,
                    true, targetID, RADIANT_ATOM, fai, chr.getRectAround(rect), 0, 0,
                    chr.getPosition(), 0, chr.getPosition(), 0);
            chr.createForceAtom(fa);
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

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
        switch (skillID) {
            case FLORAN_HERO_WILL:
                tsm.removeAllDebuffs();
                break;
            case REPOSITION_CRYSTAL:
                if (getCrystal() != null) {
                    inPacket.decodeInt(); // unknown
                    boolean isLeft = inPacket.decodeByte() != 0;
                    Position position = inPacket.decodePosition();
                    chr.getField().broadcastPacket(Summoned.repositionSummon(getCrystal(), skillID, position));
                }
                break;
            case RADIANT_JAVELIN:
            case RADIANT_JAVELIN_II:
            case RADIANT_JAVELIN_ENHANCED:
                ForceAtomEnum fae = skillID == RADIANT_JAVELIN_ENHANCED ? ForceAtomEnum.GLORY_WING_JAVELIN : ForceAtomEnum.RADIANT_JAVELIN;
                ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), skillID == RADIANT_JAVELIN_II ? 2 : 1, 50, 50,
                        0, 300, Util.getCurrentTime(), 0, 0,
                        new Position(-48, 7));
                Position pos = new Position(0, 0);
                if (getCrystal() == null) {
                    pos = chr.getPosition();
                } else {
                    pos = getCrystal().getPosition();
                }
                ForceAtom fa = new ForceAtom(false, chr.getId(), chr.getId(), fae,
                        true, 0, skillID, fai, si == null ? new Rect() : si.getFirstRect(), 0, 0,
                        pos, 0, pos, 0);

                if (skillID != RADIANT_JAVELIN_ENHANCED) {
                    fa.setRect2(si.getLastRect());
                }
                chr.createForceAtom(fa);
                if (skillID == RADIANT_JAVELIN_II) {
                    handleRadiantAtom();
                }
                break;
            case CRYSTALLINE_WINGS_FLY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 1000;
                o1.setInMillis(true);
                tsm.putCharacterStatValue(CharacterTemporaryStat.NewFlying, o1);
//                o2.nOption = 1;
//                o2.rOption = skillID;
//                o2.tOption = si.getValue(time, slv);
//                o2.setInMillis(true);
//                tsm.putCharacterStatValue(NuclearOption, o2); // NuclearOption is moved/removed
                break;
            case GAUNTLET_FRENZY:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBooster, o1);
                break;
            case HERO_OF_THE_FLORA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStatR, o1);
                break;
            case DIVINE_WRATH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o1);
                break;
            case FLASH_CRYSTAL_BATTERY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.FlashCrystalBattery, o1);
                break;
            case WINGS_OF_GLORY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.WingsOfGlory, o1);
                tsm.putCharacterStatValue(CharacterTemporaryStat.NewFlying, o1);
                o2.nReason = skillID;
                o2.nValue = 19; //Bugged
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePMdR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(indieBDR, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieBDR, o3);
                o4.nReason = skillID;
                o4.nValue = si.getValue(indieStance, slv);
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieStance, o4);
                crystalSkillMap.put(CRYSTAL_SKILL_ID_WINGS_OF_GLORY, false);
                chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
                chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
                break;
            case EX:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setAssistType(AssistType.AttackCounter);
                field.spawnSummon(summon);
                break;
            case MACHINA: // spawn at Crystal Position
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Fly);
                summon.setAssistType(AssistType.Attack);
                field.spawnSummon(summon);
                break;
            case CRYSTAL_SKILL_DEUS: // TODO
                field.getSummons().stream().filter(s -> s.getChr() == chr && (s.getSkillID() == MACHINA || s.getSkillID() == EX)).forEach(field::removeLife);
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Walk);
                field.spawnSummon(summon);

                for (int i = 0; i < 5; i++) {
                    summon = Summon.getSummonByNoCTS(chr, DEUS_SUB, slv);
                    summon.setMoveAbility(MoveAbility.Fly);
                    summon.setAssistType(AssistType.AttackCounter);
                    field.spawnAddSummon(summon);
                }

                crystalSkillMap.put(CRYSTAL_SKILL_ID_DEUS, false);
                chr.getField().broadcastPacket(Summoned.stateChanged(getCrystal(), 2, crystalSkillMap));
                chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
                break;
            case DEPLOY_CRYSTAL:
                Summon crystal = Summon.getSummonByNoCTS(chr, skillID, slv);
                crystal.setMoveAbility(MoveAbility.Crystal);
                crystal.setAssistType(AssistType.AttackCounter);
                crystal.setSummonTerm(0);
                o1.nValue = 1;
                o1.nReason = skillID;
                o1.summon = crystal;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieSummon, o1, true);
                field.spawnSummon(crystal);
                resetCrystalBattery();
                chr.getField().broadcastPacket(Summoned.summonUpgradeStage(getCrystal(), 3)); // resets crystal attacks
                break;
            case CONVERSION_OVERDRIVE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.ConversionOverdrive, o1);
                int mpRatio = (int) (chr.getCurrentMPPerc());
                int damR = si.getValue(y, slv);

                if (mpRatio <= 80) { // upto this based on mp proportions
                    damR = damR - 5;
                } if (mpRatio <= 60){
                damR = damR - 8;
            } if (mpRatio <= 40){
                damR = damR - 12;
            } if (mpRatio <= 20){
                damR = damR - 16;
            } if (mpRatio <= 5){
                damR = damR - 18;
            }
                o2.nValue = damR;
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o2);
                break;
            case TEMPLAR_KNIGHT:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.CreateShootObj);
                field.spawnSummon(summon);
                break;
            case CRYSTALLINE_BULWARK:
                o1.nOption = 1;
                o1.rOption = skillID;
                int stack = 1;
                if (tsm.hasStat(CharacterTemporaryStat.LucentBrand)) {
                    stack = tsm.getOption(CharacterTemporaryStat.LucentBrand).nOption;
                    if (stack < getMaxLucentBrand()) {
                        stack++;
                    }
                }
                o1.tOption = 1 * stack;
                tsm.putCharacterStatValue(CharacterTemporaryStat.NotDamaged, o1);
                tsm.sendSetStatPacket();
                break;
            case LONGINUS_ZONE:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, LONGINUS_ZONE, slv);
                if (getCrystal() == null) {
                    pos = chr.getPosition();
                } else {
                    pos = getCrystal().getPosition();
                }
                aa.setPosition(pos);
                aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                aa.setSkillID(LONGINUS_ZONE);
                aa.setDelay((short) 18);
                chr.getField().spawnAffectedArea(aa);
                o1.nValue = 1;
                o1.nReason = skillID;
                o1.tTerm = 3;
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieNotDamaged, o1);
                tsm.sendSetStatPacket();
                break;
            case CRYSTALLINE_SPIRIT:
                if (getShards() > 0) {
                    updateShardCount(getShards() - 1);
                } else {
                    chr.chatMessage("You don't have enough Charges to use this.");
                }
                Summon Shard;
                Shard = Summon.getSummonBy(chr, skillID, slv);
                Shard.setMoveAbility(MoveAbility.Crystal);
                Shard.setAssistType(AssistType.AttackCounter);
                Shard.setSummonTerm(30);
                Shard.setPosition(getCrystal().getPosition());
                field.spawnAddSummon(Shard);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case RADIANT_ORB:
            case RADIANT_ORB_II:
                SkillInfo si = SkillData.getSkillInfoById(skillId);
                slv = chr.getSkillLevel(skillId);
                giveLucentBrand();
                Option o = new Option();
                Option o1 = new Option();
                o.nOption = 1;
                o.rOption = skillId;
                o.tOption = 2;
                o.setInMillis(true);
                //tsm.putCharacterStatValue(RadiantOrb, o);
//                o1.nOption = si.getValue(y, slv);
//                o1.rOption = skillId;
//                o1.tOption = 2;
//                o.setInMillis(true);
//                tsm.putCharacterStatValue(NuclearOption, o1); // NuclearOption is moved/removed
                tsm.sendSetStatPacket();
                break;
        }
        super.handleShootObj(chr, skillId, slv);
    }

    public void giveLucentBrand() {
        Skill skill = getLucentBrandSkill();
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        int stack = 1;
        if (tsm.hasStat(CharacterTemporaryStat.LucentBrand)) {
            stack = tsm.getOption(CharacterTemporaryStat.LucentBrand).nOption;
            if (stack < getMaxLucentBrand()) {
                stack++;
            }
        }
        o1.nOption = stack;
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(CharacterTemporaryStat.LucentBrand, o1);
        o2.nReason = skill.getSkillId();
        o2.nValue = si.getValue(w, slv) * stack;
        o2.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePAD, o2);
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndieMAD, o2);
        tsm.sendSetStatPacket();
    }

    private int getMaxLucentBrand() {
        int maxStack = 0;
        for (int lucentSkillId : lucentSkills) {
            if (chr.hasSkill(lucentSkillId)) {
                SkillInfo si = SkillData.getSkillInfoById(lucentSkillId);
                maxStack = si.getValue(x, chr.getSkillLevel(lucentSkillId));
            }
        }
        return maxStack;
    }

    private Skill getLucentBrandSkill() {
        Skill skill = null;
        for (int lucentSkillId : lucentSkills) {
            if (chr.hasSkill(lucentSkillId)) {
                skill = chr.getSkill(lucentSkillId);
            }
        }
        return skill;
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        if (cts == CharacterTemporaryStat.CrystalBattery) {
            changeCrystalCharge(0);
            resetCrystalBattery();
        }
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        //cs.setPosMap(100000000); // default - 940202013
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        int level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.ILLIUM_2.getJobId());
//                chr.addSpToJobByCurrentJob(7);
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.ILLIUM_3.getJobId());
//                chr.addSpToJobByCurrentJob(7);
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.ILLIUM_4.getJobId());
//                chr.addSpToJobByCurrentJob(5);
//                break;
//        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.ILLIUM_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();


        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353500)); // Basic Wings (2ndary)
        chr.getQuestManager().completeQuest(34900); // Quest for Shelter Return
        chr.addSpToJobByCurrentJob(8);
    }
}
