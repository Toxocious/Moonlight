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
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Wreckage;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * @author Sjonnie
 * Created on 6/25/2018.
 */
public class Ark extends Job {

    public static final int SPELL_BULLETS = 155001103;
    public static final int SPECTER_STATE = 155000007;
    public static final int CORRUPTION_COOLDOWN = 155001008;

    public static final int BASIC_CHARGE_DRIVE_ATTACK = 155001100;
    public static final int BASIC_CHARGE_DRIVE_ATOM = 155001000;
    public static final int BASIC_CHARGE_DRIVE_BUFF = 155001001;

    public static final int SCARLET_CHARGE_DRIVE_ATTACK_1 = 155101100;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_2 = 155101013;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_COMBO_1 = 155101101;
    public static final int SCARLET_CHARGE_DRIVE_ATTACK_COMBO_2 = 155101015;
    public static final int SCARLET_CHARGE_DRIVE_ATOM = 155101002;
    public static final int SCARLET_CHARGE_DRIVE_BUFF = 155101003;

    public static final int GUST_CHARGE_DRIVE_ATTACK = 155111102;
    public static final int GUST_CHARGE_DRIVE_ATTACK_COMBO = 155111111;
    public static final int GUST_CHARGE_DRIVE_ATOM = 155111003;
    public static final int GUST_CHARGE_DRIVE_BUFF = 155111005;

    public static final int ABYSSAL_CHARGE_DRIVE_ATTACK = 155121102;
    public static final int ABYSSAL_CHARGE_DRIVE_ATOM = 155121003;
    public static final int ABYSSAL_CHARGE_DRIVE_TILE = 155121004;
    public static final int ABYSSAL_CHARGE_DRIVE_BUFF = 155121005;

    public static final int OMINOUS_NIGHTMARE = 155001102;
    public static final int VIVID_NIGHTMARE = 155110000;
    public static final int ENDLESS_NIGHTMARE = 155120000;

    public static final int KNUCKLE_BOOSTER_ARK = 155101005;
    public static final int MASTER_CORRUPTION = 155101006;
    public static final int IMPENDING_DEATH_ATOM = 155100009;
    public static final int IMPENDING_DEATH = 155101008;

    public static final int CREEPING_TERROR = 155111006;
    public static final int CREEPING_TERROR_HELD_DOWN = 155111306;
    public static final int VENGEFUL_HATE = 155111207;

    public static final int HERO_OF_THE_FLORA = 155121008;
    public static final int FLORAN_HEROS_WILL = 155121009;
    public static final int BLISSFUL_RESTRAINT_TILE = 155121006;
    public static final int BLISSFUL_RESTRAINT_ATTACK = 155121306;
    public static final int ENDLESS_DREAM = 155120001;
    public static final int ENHANCED_SPECTRA = 155120034;

    // Hyper Skills
    public static final int DIVINE_WRATH = 155121042;
    public static final int CHARGE_SPELL_AMPLIFIER = 155121043;
    public static final int ENDLESS_AGONY = 155121341;

    // V skills
    public static final int ABYSSAL_RECALL = 400051334;
    public static final int INFINITY_SPELL = 400051036;
    public static final int NIGHTMARES_ESCAPE = 400051047;
    public static final int DREAMS_ESCAPE = 400051048;

    private ScheduledFuture spectraEnergyTimer;
    List<CharacterTemporaryStat> spellCasts = Arrays.asList(AbyssalCast, GustCast, ScarletCast, BasicCast);
    private int[] addedSkills = new int[]{

    };

    public enum SpellChargeType {
        Basic(15500),
        Scarlet(15510),
        Gust(15511),
        Abyssal(15512),
        ;
        private int val;

        SpellChargeType(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public static SpellChargeType getByVal(int val) {
            return Arrays.stream(values()).filter(sct -> sct.getVal() == val).findAny().orElse(null);
        }
    }

    public Ark(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            if (spectraEnergyTimer != null && !spectraEnergyTimer.isDone()) {
                spectraEnergyTimer.cancel(true);
            }

            spectraEnergyTimer = EventManager.addFixedRateEvent(this::modifySpectraEnergy, 4000, 1000);
        }
    }

    public void clearSpectraTimer() {
        if (spectraEnergyTimer != null) {
            spectraEnergyTimer.cancel(true);
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isArk(id);
    }

    private int getCurrentChargeCount() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int count = 0;
        if (tsm.hasStat(BasicCast)) {
            count += (tsm.getOption(BasicCast).xOption / 2);
        }
        if (tsm.hasStat(ScarletCast)) {
            count += tsm.getOption(ScarletCast).xOption;
        }
        if (tsm.hasStat(GustCast)) {
            count += tsm.getOption(GustCast).xOption;
        }
        if (tsm.hasStat(AbyssalCast)) {
            count += tsm.getOption(AbyssalCast).xOption;
        }
        return count;
    }

    private void resetCharges() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.xOption = 0;
        tsm.putCharacterStatValue(BasicCast, o);
        tsm.putCharacterStatValue(ScarletCast, o);
        tsm.putCharacterStatValue(GustCast, o);
        tsm.putCharacterStatValue(AbyssalCast, o);
        tsm.sendSetStatPacket();
    }

    private void modifySpectraEnergy() {
        if (chr == null || chr.getField() == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int delta = chr.hasSkill(ENHANCED_SPECTRA) ? 8 : 7;
        int currentEnergy = tsm.getOption(SpecterEnergy).xOption;
        if (tsm.hasStat(SpecterState)) {
            delta = -9;
        }
        if (chr.hasSkillOnCooldown(CORRUPTION_COOLDOWN) || tsm.hasStat(AbyssalRecall)) { // if Player has Spectra Fatigue
            return;
        }
        updateSpectraEnergy(currentEnergy + delta);
    }

    private void updateSpectraEnergy(int spectraEnergy) {
        if (chr == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = 1;
        o.xOption = (spectraEnergy > 1000 ? 1000 : (spectraEnergy < 0 ? 0 : spectraEnergy));
        tsm.putCharacterStatValue(SpecterEnergy, o);
        if (o.xOption <= 0) { // Spectra Fatigue
            SkillInfo si = SkillData.getSkillInfoById(CORRUPTION_COOLDOWN);
            tsm.removeStatsBySkill(SPECTER_STATE);
            chr.addSkillCoolTime(CORRUPTION_COOLDOWN, si.getValue(cooltime, 1) * 1000);
        } else if (o.xOption >= 1000 && !chr.hasSkill(MASTER_CORRUPTION)) { // If Player doesn't have Control over their Specter State yet.
            changeSpecterState();
        }
        tsm.sendSetStatPacket();
    }

    private void changeSpecterState() {
        Skill skill = chr.getSkill(SPECTER_STATE);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();

        if (!tsm.hasStat(SpecterState)) {
            o1.nReason = SPECTER_STATE;
            o1.nValue = si.getValue(indiePad, slv);
            tsm.putCharacterStatValue(IndiePAD, o1);
            o2.nReason = SPECTER_STATE;
            o2.nValue = si.getValue(indieStance, slv);
            tsm.putCharacterStatValue(IndieStance, o2);
            o3.nOption = 1;
            o3.rOption = SPECTER_STATE;
            tsm.putCharacterStatValue(SpecterState, o3);
        } else {
            tsm.removeStatsBySkill(SPECTER_STATE);
        }
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
        if (hasHitMobs
                && !SkillConstants.isArkForceAtomAttack(attackInfo.skillId)
                && attackInfo.skillId != CONVERSION_OVERDRIVE_ATTACK) {
            createImpendingDeathForceAtom();
            bonusConversionOverdriveAttack();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Field field = chr.getField();
        switch (attackInfo.skillId) {
            case BASIC_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(BASIC_CHARGE_DRIVE_ATTACK);
                    SkillInfo si2 = SkillData.getSkillInfoById(BASIC_CHARGE_DRIVE_BUFF);
                    int slv2 = (byte) skill2.getCurrentLevel();

                    o1.nReason = BASIC_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(speed, slv2);
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieSpeed, o1);
                    o2.nReason = BASIC_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieStance, slv2);
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieStance, o2);
                }
                break;
            case SCARLET_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(SCARLET_CHARGE_DRIVE_ATTACK_1);
                    SkillInfo si2 = SkillData.getSkillInfoById(SCARLET_CHARGE_DRIVE_BUFF);
                    int slv2 = (byte) skill2.getCurrentLevel();
                    o1.nReason = SCARLET_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(indiePad, slv2);
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndiePAD, o1);
                    o2.nReason = SCARLET_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieCr, slv2);
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieCr, o2);
                }
                break;
            case GUST_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    Skill skill2 = chr.getSkill(GUST_CHARGE_DRIVE_ATTACK);
                    SkillInfo si2 = SkillData.getSkillInfoById(GUST_CHARGE_DRIVE_BUFF);
                    int slv2 = (byte) skill2.getCurrentLevel();
                    o1.nReason = GUST_CHARGE_DRIVE_BUFF;
                    o1.nValue = -1;
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieBooster, o1);
                    o2.nReason = GUST_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieEvaR, slv2);
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieEVAR, o2);
                }
                break;
            case ABYSSAL_CHARGE_DRIVE_ATOM:
                if (hasHitMobs) {
                    // Buff
                    Skill skill2 = chr.getSkill(ABYSSAL_CHARGE_DRIVE_ATTACK);
                    SkillInfo si2 = SkillData.getSkillInfoById(ABYSSAL_CHARGE_DRIVE_BUFF);
                    int slv2 = (byte) skill2.getCurrentLevel();
                    o1.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o1.nValue = si2.getValue(indieDamR, slv2);
                    o1.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieDamR, o1);
                    o2.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o2.nValue = si2.getValue(indieBDR, slv2);
                    o2.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieBDR, o2);
                    o3.nReason = ABYSSAL_CHARGE_DRIVE_BUFF;
                    o3.nValue = si2.getValue(indieIgnoreMobpdpR, slv2);
                    o3.tTerm = si2.getValue(time, slv2);
                    tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o3);

                    // Tile
                    field = chr.getField();
                    Mob mob = Util.getRandomFromCollection(new ArrayList<Mob>() {{
                        attackInfo.mobAttackInfo.forEach(mai -> add((Mob) chr.getField().getLifeByObjectID(mai.mobId)));
                    }});
                    SkillInfo rca = SkillData.getSkillInfoById(ABYSSAL_CHARGE_DRIVE_TILE);
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setSkillID(ABYSSAL_CHARGE_DRIVE_TILE);
                    aa.setPosition(mob.getPosition());
                    Rect rect = aa.getPosition().getRectAround(rca.getFirstRect());
                    aa.setRect(rect);
                    field.spawnAffectedArea(aa);
                }
                break;

            // Gain Energy Charge on Attack
            case BASIC_CHARGE_DRIVE_ATTACK:
            case SCARLET_CHARGE_DRIVE_ATTACK_1:
            case SCARLET_CHARGE_DRIVE_ATTACK_2:
            case SCARLET_CHARGE_DRIVE_ATTACK_COMBO_1:
            case SCARLET_CHARGE_DRIVE_ATTACK_COMBO_2:
            case GUST_CHARGE_DRIVE_ATTACK:
            case GUST_CHARGE_DRIVE_ATTACK_COMBO:
            case ABYSSAL_CHARGE_DRIVE_ATTACK:
                if (hasHitMobs) {
                    addSpellCharge(attackInfo.skillId);
                    if (tsm.hasStat(InfinitySpell)) {
                        for (int i = 0; i < 4; i++) {
                            addSpellCharge(BASIC_CHARGE_DRIVE_ATTACK);
                        }
                    }
                }
                break;
            case OMINOUS_NIGHTMARE:
            case VIVID_NIGHTMARE:
            case ENDLESS_NIGHTMARE:
                chr.addSkillCoolTime(OMINOUS_NIGHTMARE, 2000);

            case ENDLESS_DREAM:
                if (chr.hasSkill(NIGHTMARES_ESCAPE)) {
                    int vSkill = NIGHTMARES_ESCAPE;
                    if (tsm.hasStat(SpecterState)) {
                        vSkill = DREAMS_ESCAPE;
                    }
                    if (chr.hasSkillOnCooldown(vSkill)) {
                        return;
                    }
                    chr.write(UserLocal.userBonusAttackRequest(vSkill));
                }
                break;
            case NIGHTMARES_ESCAPE:
            case DREAMS_ESCAPE:
                si = SkillData.getSkillInfoById(NIGHTMARES_ESCAPE);
                slv = (byte) chr.getSkillLevel(NIGHTMARES_ESCAPE);
                chr.addSkillCoolTime(attackInfo.skillId, si.getValue(cooltime, slv) * 1000);
                break;
            case CREEPING_TERROR_HELD_DOWN:
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = 2;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case CREEPING_TERROR:
                tsm.removeStatsBySkill(CREEPING_TERROR_HELD_DOWN);
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                break;
            case ABYSSAL_RECALL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 30;
                tsm.putCharacterStatValue(AbyssalRecall, o1);
                // Fallthrough intended
            case ENDLESS_AGONY:
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                break;
            case BLISSFUL_RESTRAINT_ATTACK:
                if (!tsm.hasStat(SpecterState)) {
                    changeSpecterState();
                }
                SkillInfo rca = SkillData.getSkillInfoById(BLISSFUL_RESTRAINT_TILE);
                AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                aa.setSkillID(BLISSFUL_RESTRAINT_TILE);
                aa.setPosition(chr.getPosition());
                Rect rect = aa.getPosition().getRectAround(rca.getRects().get(0));
                aa.setRect(rect);
                field.spawnAffectedArea(aa);
                break;
            case IMPENDING_DEATH_ATOM:
                spawnWreckage(attackInfo);
                break;
        }

        if (isBuff(attackInfo.skillId)) {
            tsm.sendSetStatPacket();
        }
        super.handleAttack(c, attackInfo);
    }

    private void addSpellCharge(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        if (!tsm.hasStat(SpecterState) && getCurrentChargeCount() < 5) {
            SpellChargeType spellChargeType = SpellChargeType.getByVal(skillId / 10000);
            switch (spellChargeType) {
                case Basic:
                    o1.xOption = tsm.hasStat(BasicCast) ? tsm.getOption(BasicCast).xOption > 10 ? 10 : tsm.getOption(BasicCast).xOption + 2 : 2;
                    tsm.putCharacterStatValue(BasicCast, o1);
                    break;
                case Scarlet:
                    o1.xOption = 1;
                    tsm.putCharacterStatValue(ScarletCast, o1);
                    break;
                case Gust:
                    o1.xOption = 1;
                    tsm.putCharacterStatValue(GustCast, o1);
                    break;
                case Abyssal:
                    o1.xOption = 1;
                    tsm.putCharacterStatValue(AbyssalCast, o1);
                    break;
            }
        }
    }

    private void spawnWreckage(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        if (!chr.hasSkill(VENGEFUL_HATE) || !tsm.hasStat(SpecterState) || !tsm.hasStat(ImpendingDeath)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(VENGEFUL_HATE);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(s, slv)) && field.getWreckageByChrId(chr.getId()).size() < si.getValue(z, slv)) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                Wreckage wreckage = Wreckage.getWreckageBy(chr, skill.getSkillId(), mob.getPosition(), si.getValue(q, slv) * 1000, 0);
                field.spawnWreckage(chr, wreckage);
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        SkillInfo si = null;
        if (chr.getSkill(skillID) != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        switch (skillID) {
            case SPELL_BULLETS:
                if (!chr.hasSkill(SPELL_BULLETS) || tsm.hasStat(SpecterState)) {
                    return;
                }
                createSpellBulletForceAtom();
                resetCharges();
                break;
            case FLORAN_HEROS_WILL:
                tsm.removeAllDebuffs();
                break;
            case VENGEFUL_HATE:
                List<Wreckage> wreckageList = chr.getField().getWreckageByChrId(chr.getId());
                createVengefulHateForceAtom(wreckageList);
                break;
            case MASTER_CORRUPTION:
                if (chr.hasSkill(MASTER_CORRUPTION) && tsm.hasStat(SpecterEnergy) && tsm.getOption(SpecterEnergy).xOption > 0) {
                    changeSpecterState();
                } else {
                    chr.chatMessage("Cannot enter Specter state because of Spectra Fatigue.");
                }
                break;
            case KNUCKLE_BOOSTER_ARK:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case IMPENDING_DEATH:
                if (tsm.hasStat(ImpendingDeath)) {
                    tsm.removeStatsBySkill(IMPENDING_DEATH);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(ImpendingDeath, o1);
                }
                break;
            case HERO_OF_THE_FLORA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case DIVINE_WRATH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case CHARGE_SPELL_AMPLIFIER:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ChargeSpellAmplifier, o1);
                break;
            case INFINITY_SPELL:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(InfinitySpell, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    private void createSpellBulletForceAtom() {
        if (!chr.hasSkill(SPELL_BULLETS)) {
            return;
        }
        Skill skill = chr.getSkill(SPELL_BULLETS);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        int i = new Random().nextBoolean() ? 900 : 700;
        ForceAtom abyssalFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.ABYSSAL_CHARGE,
                true, new ArrayList<>(), ABYSSAL_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), ABYSSAL_CHARGE_DRIVE_ATOM, new Position(), 0);
        ForceAtom gustFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.GUST_CHARGE,
                true, new ArrayList<>(), GUST_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), GUST_CHARGE_DRIVE_ATOM, new Position(), 0);
        ForceAtom scarletFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.SCARLET_CHARGE,
                true, new ArrayList<>(), SCARLET_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), SCARLET_CHARGE_DRIVE_ATOM, new Position(), 0);
        ForceAtom basicFA = new ForceAtom(true, 0, chr.getId(), ForceAtomEnum.BASIC_CHARGE,
                true, new ArrayList<>(), BASIC_CHARGE_DRIVE_ATOM, new ArrayList<>(), new Rect(), 0, 0,
                new Position(), BASIC_CHARGE_DRIVE_ATOM, new Position(), 0);
        for (CharacterTemporaryStat cast : spellCasts) {
            if (cast == null || !tsm.hasStat(cast)) {
                continue;
            }
            for (int j = 0; j < tsm.getOption(cast).xOption; j++) {
                int firstImpact = new Random().nextInt(15) + 35;
                int secondImpact = new Random().nextInt(2) + 5;
                int delay = new Random().nextInt(400) + 500;
                int angle = new Random().nextInt(20) + 50;

                Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
                ForceAtomInfo fai = new ForceAtomInfo(i, i, firstImpact, secondImpact,
                        angle, delay, Util.getCurrentTime(), 0, 0,
                        new Position());

                switch (cast) {
                    case ScarletCast: // Scarlet Charge
                        scarletFA.getTargetIdList().add(mob != null ? mob.getObjectId() : 0);
                        scarletFA.getFaiList().add(fai);
                        break;
                    case GustCast: // Gust Charge
                        gustFA.getTargetIdList().add(mob != null ? mob.getObjectId() : 0);
                        gustFA.getFaiList().add(fai);
                        break;
                    case AbyssalCast: // Abyssal Charge
                        abyssalFA.getTargetIdList().add(mob != null ? mob.getObjectId() : 0);
                        abyssalFA.getFaiList().add(fai);
                        break;
                    default:    // Basic Charge
                        basicFA.getTargetIdList().add(mob != null ? mob.getObjectId() : 0);
                        basicFA.getFaiList().add(fai);
                        break;
                }
                i++;
            }
        }
        field.broadcastPacket(FieldPacket.createArkForceAtom(chr.getId(), SPELL_BULLETS, abyssalFA, gustFA, scarletFA, basicFA));
    }

    private void createImpendingDeathForceAtom() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStatBySkillId(SPECTER_STATE) || !chr.hasSkill(IMPENDING_DEATH) || !tsm.hasStat(ImpendingDeath)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(IMPENDING_DEATH);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        Rect rect = chr.getPosition().getRectAround(SkillData.getSkillInfoById(IMPENDING_DEATH_ATOM).getFirstRect());
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<ForceAtomInfo> faiList = new ArrayList<>();
        List<Integer> targetIdList = new ArrayList<>();
        int firstImpact = new Random().nextInt(15) + 35;
        int secondImpact = new Random().nextInt(2) + 5;
        ForceAtomEnum fae = ForceAtomEnum.IMPENDING_DEATH;
        int bulletCount = si.getValue(z, slv) + (tsm.hasStat(InfinitySpell) ? 3 : 0);
        for (int i = 0; i < bulletCount; i++) {
            Mob mob = Util.getRandomFromCollection(field.getMobsInRect(rect));
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                    270, 100, Util.getCurrentTime(), 0, 0,
                    new Position());

            targetIdList.add(mob != null ? mob.getObjectId() : 0);
            faiList.add(forceAtomInfo);
        }
        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                true, targetIdList, IMPENDING_DEATH_ATOM, faiList, new Rect(), 0, 300,
                new Position(), IMPENDING_DEATH_ATOM, new Position(), 0));
    }

    private void createVengefulHateForceAtom(List<Wreckage> wreckageList) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        if (!chr.hasSkill(VENGEFUL_HATE) || !tsm.hasStat(SpecterState) || !tsm.hasStat(ImpendingDeath)) {
            return;
        }
        for (Wreckage wreckage : wreckageList) {
            int firstImpact = new Random().nextInt(30) + 330;
            int secondImpact = new Random().nextInt(5) + 60;
            ForceAtomEnum fae = ForceAtomEnum.VENGEFUL_HATE;
            Mob mob = Util.getRandomFromCollection(field.getMobs());
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(1, fae.getInc(), firstImpact, secondImpact,
                    0, 500, Util.getCurrentTime(), 8, 0,
                    wreckage.getPosition());
            chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                    false, new ArrayList<>(), VENGEFUL_HATE, Collections.singletonList(forceAtomInfo), new Rect(), 0, 300,
                    chr.getPosition(), VENGEFUL_HATE, mob == null ? new Position() : mob.getPosition(), 0));
        }
        field.removeWreckage(chr, wreckageList);
    }

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (spectraEnergyTimer != null) {
            spectraEnergyTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        // cs.setPosMap(100000000); // default: 402090000
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.ARK_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.ARK_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.ARK_4.getJobId());
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

        handleJobAdvance(JobConstants.JobEnum.ARK_1.getJobId());
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353600)); // Initial Path (2ndary)
        chr.addSpToJobByCurrentJob(3);
    }
}
