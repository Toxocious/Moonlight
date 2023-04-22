package net.swordie.ms.client.jobs.sengoku;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;

import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.enums.MoveAbility.Fly;

/**
 * Created on 12/14/2017.
 */

public class Kanna extends Job {

    // Beginner Job
    public static final int HAKU = 40020109;
    public static final int RETURN_OF_THE_FIVE_PLANETS = 40021227;

    // 1st Job
    public static final int SHIKIGAMI_HAUNTING_1_1 = 42001000;
    public static final int SHIKIGAMI_HAUNTING_1_2 = 42001005;
    public static final int SHIKIGAMI_HAUNTING_1_3 = 42001006; // spawns mana vein
    public static final int SHIKIGAME_HAUNTING_1_SLOW = 42100024; // mob debuff
    public static final int MANA_WARP = 42001002;
    public static final int GEOMANCY = 42000000;
    public static final int MANA_VEIN = 42001101;
    public static final int GHOST_YAKSHA_TRAINEE = 42001100;


    // 2nd Job
    public static final int SHIKIGAMI_HAUNTING_2_1 = 42101100;
    public static final int SHIKIGAMI_HAUNTING_2_2 = 42101101;
    public static final int SHIKIGAMI_HAUNTING_2_3 = 42101102; // spawns mana vein
    public static final int SHIKIGAMI_HAUNTING_2_SLOW = 42101103; // mob debuff
    public static final int RADIANT_PEACOCK = 42101003;
    public static final int GHOST_YAKSHA_BROTHER = 42100000;
    public static final int SHIKIGAMI_CHARM = 42101104; // spawns mana vein
    public static final int EXORCIST_CHARM = 42101005;
    public static final int HAKU_REBORN = 42101002;


    // 3rd Job
    public static final int SHIKIGAMI_ACTIVATION_SKILL = 42110013;
    public static final int SHIKIGAMI_HAUNTING_3_1 = 42111110;
    public static final int SHIKIGAMI_HAUNTING_3_2 = 42111111;
    public static final int SHIKIGAMI_HAUNTING_3_3 = 42111112; // spawns mana vein
    public static final int GHOST_YAKSHA_LIEUTENANT = 42110000;
    public static final int BLOSSOM_BARRIER = 42111004;
    public static final int YOSUZUME = 42110002;
    public static final int TENGU_STRIKE_SUMMON_R = 42111101;
    public static final int TENGU_STRIKE_SUMMON_L = 42111102;
    public static final int TENGU_STRIKE = 42111103;
    public static final int MANA_BALANCE = 42111012;
    public static final int WARDING_BARRIER = 42110001;
    public static final int TENGU_RETURN = 42111100;

    // 4th Job
    public static final int SHIKIGAMI_HAUNTING_4_1 = 42120026;
    public static final int SHIKIGAMI_HAUNTING_4_2 = 42120027;
    public static final int SHIKIGAMI_HAUNTING_4_3 = 42120028; // spawns mana vein
    public static final int GHOST_YAKSHA_BOSS = 42120001;
    public static final int FALLING_SAKURA = 42121002; // spawns mana vein
    public static final int HAKU_PERFECTED = 42120011;
    public static final int OROCHI_UNBOUND = 42121100;
    public static final int OROCHI_UNBOUND2 = 42121101;
    public static final int SHIKIGAMI_DOPPLEGANGER = 42121104;


    // Hyper Skills
    public static final int GEOMANCY_SPREAD = 42120044;
    public static final int GEOMANCY_PERSIST = 42120050;


    // V Skills
    public static final int YUKI_MUSUME_SHOUKAN = 400021017;
    public static final int YUKI_MUSUME_SHOUKAN_SUMMON = 400021018;
    public static final int SPIRITS_DOMAIN = 400021054;
    public static final int LIBERATED_SPIRIT_CIRCLE_SMALL = 400021078;
    public static final int LIBERATED_SPIRIT_CIRCLE_BIG = 400021080;
    public static final int LIBERATED_SPIRIT_CIRCLE_SUMMON = 400021079;
    public static final int LIBERATED_SPIRIT_CIRCLE_SUMMON_2 = 400021081;
    public static final int PRINCESS_SAKUNO_BLESSING = 400001057;





    //Old Skills        ------------------------------------------------------------------------------------------------
    public static final int KISHIN_SHOUKAN = 42111003; //summon
    public static final int LIFEBLOOD_RITUAL = 42110008;
    //public static final int BLOSSOM_BARRIER = 42111004; //AoE
    public static final int SOUL_SHEAR = 42111002; //Reactive Skill [4033270 - soul shear balls]
    public static final int SOUL_SHEAR_BOMB_ITEM_ID = 4033270;

    public static final int BELLFLOWER_BARRIER = 42121005; //AoE
    public static final int BELLFLOWER_BARRIER_PERSIST_H = 42120049;
    public static final int BELLFLOWER_BARRIER_BOSS_RUSH_H = 42120051;
    public static final int AKATUSKI_HERO_KANNA = 42121006;
    public static final int NINE_TAILED_FURY = 42121024; //Attacking Skill + Buff
    public static final int BINDING_TEMPEST = 42121004;
    public static final int BLOSSOMING_DAWN = 42121007;
    public static final int FALLING_SAKURA_VITALITY_H = 42120048;

    public static final int VERITABLE_PANDEMONIUM = 42121052; //Immobility Debuff
    public static final int PRINCESS_VOW_KANNA = 42121053;
    public static final int BLACKHEARTED_CURSE = 42121054;

    public static final int BURNING_SHIKIGAMI_ATTACK = 42101004;
    public static final int BURNING_SHIKIGAMI_DOT = 42100024;
    public static final int FROZEN_SHIKIGAMI_ATTACK = 42111006;
    public static final int FROZEN_SHIKIGAMI_MS = 42110013;
    public static final int BURNING_SHIKIGAMI_ATTACK_COMBO = 42001000;
    public static final int FROZEN_SHIKIGAMI_ATTACK_COMBO = 42001005;

    //Haku Buffs
    public static final int HAKUS_GIFT = 42121020;
    public static final int FOXFIRE = 42121021;
    public static final int FOXFIRE_2 = 42121021;
    public static final int HAKUS_BLESSING = 42121022;
    public static final int BREATH_UNSEEN = 42121023;
    public static final int HAKUS_BLESSING_2 = 42121022;
    public static final int BREATH_UNSEEN_2 = 42121023;
    public static final int HAKUS_GIFT_2 = 42121020;


    public ScheduledFuture foxManTimer;



    private static final int[] addedSkills = new int[]{
            RETURN_OF_THE_FIVE_PLANETS
    };

    public Kanna(Char chr) {
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
        return JobConstants.isKanna(id);
    }


    public void spawnHaku() {
        Field field = chr.getField();
       // if (chr.getSkill(Kanna.HAKU).getCurrentLevel() > 30) {
            field.broadcastPacket(UserPacket.skillPetCreated(chr, 1, HAKU));
      //  }

    }

    public static void foxManUpdate(Char chr) {
        if (hasFoxMan(chr)) {
            chr.write(FieldPacket.foxManExclResult(chr));
        }
    }


    public static void hakuFoxFire(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillID = !hasHakuPerfected(chr) ? FOXFIRE : FOXFIRE_2;
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int slv = getHakuSkillLevel(chr);
        Option o1 = new Option();

        o1.nOption = 3;
        o1.rOption = skillID;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(FireBarrier, o1);
        chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
        tsm.sendSetStatPacket();
    }

    public static void hakuGift(Char chr) {
        if (!chr.hasSkillOnCooldown(HAKUS_GIFT_2) || !chr.hasSkillOnCooldown(HAKUS_GIFT)) {
            SkillInfo si = SkillData.getSkillInfoById(80011056);
            int healHP = (int) ((double) (chr.getMaxHP() * si.getValue(hp, 1)) / 100F);
            chr.heal(healHP);
            chr.addSkillCoolTime(HAKUS_GIFT_2, 120 * 1000);
            chr.addSkillCoolTime(HAKUS_GIFT, 120 * 1000);
        }
    }

    public static void hakuHakuBlessing(Char chr, Char other) {
        TemporaryStatManager tsm = other.getTemporaryStatManager();
        int skillID = !hasHakuPerfected(chr) ? HAKUS_BLESSING : HAKUS_BLESSING_2;
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int slv = getHakuSkillLevel(chr);

        Option o1 = new Option();

        Equip fan = (Equip) chr.getEquippedInventory().getItemBySlot(BodyPart.HakuFan.getVal());
        o1.rOption = skillID;
        o1.nOption = fan != null ? (int) (si.getValue(x, slv) * (fan.getiMad() / 100.0f)) : 0; // TODO: add to dmg calc?
        o1.tOption = 0;
        tsm.putCharacterStatValue(HakuBlessing, o1);
        tsm.sendSetStatPacket();
    }

    public static void hakuBreathUnseen(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillID = !hasHakuPerfected(chr) ? BREATH_UNSEEN : BREATH_UNSEEN_2;
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Option o1 = new Option();

        int gain = si.getValue(q, 1); // slv doesn't matter
        o1.nOption = chr.getParty() != null ? gain * chr.getParty().getMembers().size() : gain;
        o1.rOption = skillID;
        o1.tOption = 0;
        tsm.putCharacterStatValue(BlessEnsenble, o1);
        tsm.sendSetStatPacket();
    }

    public static boolean hasFoxMan(Char chr) {
        return chr.getTemporaryStatManager().hasStat(ChangeFoxMan) ||
                chr.getTemporaryStatManager().getOptByCTSAndSkill(ChangeFoxMan, Kanna.HAKU_REBORN) != null;
    }



    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        Field field = chr.getField();
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
        if (hasHitMobs && attackInfo.skillId != YOSUZUME && attackInfo.skillId != 0) {
            yosuzume();
        }
        if (SkillConstants.isSpiritWalkerSkill(attackInfo.skillId)) {
            spawnManaVein();
        }


        if (chr.getField().getSummonByChrAndSkillId(chr, YUKI_MUSUME_SHOUKAN_SUMMON) != null
                && chr.hasSkill(YUKI_MUSUME_SHOUKAN)
                && tsm.hasStat(YukiMusumeShoukan)
                && hasHitMobs) {
            doYukiMusumeShoukanAttack();
        }
        if (attackInfo.skillId != SPIRITS_DOMAIN
                && attackInfo.skillId != LIBERATED_SPIRIT_CIRCLE_BIG
                && attackInfo.skillId != LIBERATED_SPIRIT_CIRCLE_SMALL) {
            createSpiritDomainForceAtom(attackInfo);
        }
        if (hasHitMobs) {
            dropSoulShearBomb(attackInfo);
            lifeBloodRitual(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case GHOST_YAKSHA_TRAINEE:
            case GHOST_YAKSHA_BROTHER:
            case GHOST_YAKSHA_LIEUTENANT:
            case GHOST_YAKSHA_BOSS:
                if (attackInfo.attackActionType == 25 && tsm.hasStatBySkillId(skillID)) { // 25 is ActionType for the Leaving Attack
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                }
                break;
            case SHIKIGAMI_HAUNTING_1_1:
            case SHIKIGAMI_HAUNTING_1_2:
            case SHIKIGAMI_HAUNTING_1_3:

            case SHIKIGAMI_HAUNTING_2_1:
            case SHIKIGAMI_HAUNTING_2_2:
            case SHIKIGAMI_HAUNTING_2_3:

            case SHIKIGAMI_HAUNTING_3_1:
            case SHIKIGAMI_HAUNTING_3_2:
            case SHIKIGAMI_HAUNTING_3_3:

            case SHIKIGAMI_HAUNTING_4_1:
            case SHIKIGAMI_HAUNTING_4_2:
            case SHIKIGAMI_HAUNTING_4_3:
                si = SkillData.getSkillInfoById(SHIKIGAMI_HAUNTING_2_SLOW);
                slv = 1;
                o1.nOption = si.getValue(x, slv);
                o1.rOption = si.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                    if (mob == null || !(Util.succeedProp(si.getValue(prop, slv)))) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                    if (chr.hasSkill(SHIKIGAMI_ACTIVATION_SKILL)) {
                        mts.createAndAddBurnedInfo(chr, SHIKIGAMI_HAUNTING_2_SLOW, chr.getSkillLevel(SHIKIGAMI_ACTIVATION_SKILL));
                    }
                }
                break;
            case EXORCIST_CHARM:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, (byte) slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                aa.setDelay((short) 5);
                chr.getField().spawnAffectedArea(aa);
                break;
            case YOSUZUME:
                o1.nOption = si.getValue(v, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.FinalDmgReceived, o1);
                }
                break;
            case BINDING_TEMPEST:
            case VERITABLE_PANDEMONIUM:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case SOUL_SHEAR:
                explodeSoulShearBomb();
                break;
            case FALLING_SAKURA:
                List<Char> chrList = new ArrayList<>();
                if (chr.getParty() != null) {
                    chrList.addAll(chr.getParty().getPartyMembersInSameField(chr));
                }
                chrList.add(chr);
                int percentHealed = si.getValue(x, slv) + (chr.hasSkill(FALLING_SAKURA_VITALITY_H) ? 20 : 0);
                for (Char pChr : chrList) {
                    pChr.heal((int) ((pChr.getMaxHP() * percentHealed) / 100D));
                }
                break;
            case NINE_TAILED_FURY:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                tsm.sendSetStatPacket();
                break;
            case MANA_WARP:
                o1.nOption = 1;
                o1.rOption = attackInfo.skillId;
                o1.tOption = si.getValue(time, slv);
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case YUKI_MUSUME_SHOUKAN:
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(YukiMusumeShoukan, o2);
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setSkillID(YUKI_MUSUME_SHOUKAN_SUMMON);
                summon.setAssistType(AssistType.AttackCounter);
                summon.setMoveAbility(MoveAbility.Walk);
                chr.getField().spawnSummon(summon);
                break;
            case OROCHI_UNBOUND2:
                chr.addSkillCoolTime(OROCHI_UNBOUND, 90000);
                break;
        }

        super.handleAttack(c, attackInfo);
    }


    private int getAmountOfManaVeins() {
        return (int) chr.getField().getAffectedAreas().stream().filter(aa -> aa.getSkillID() == MANA_VEIN && aa.getOwner() == chr).count();
    }

    private void spawnManaVein() {
        Skill skill = chr.getSkill(GEOMANCY);
        if (!chr.hasSkill(skill.getSkillId())) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        SkillInfo mvsi = SkillData.getSkillInfoById(MANA_VEIN);

        int maxManaVeins = si.getValue(x, slv) + chr.getSkillStatValue(x, GEOMANCY_SPREAD);
        if (getAmountOfManaVeins() < maxManaVeins && !chr.hasSkillOnCooldown(GEOMANCY)) {
            Field field = chr.getField();
            Rect rect = new Rect(-300, -300, 300, 300);
            try {
                Position position = Util.getRandomFromCollection(field.getFootholdsInRect(chr.getPosition().getRectAround(rect)).stream().filter(fh -> !fh.isWall()).collect(Collectors.toList())).getRandomPosition();
                AffectedArea aa = AffectedArea.getPassiveAA(chr, MANA_VEIN, slv);
                aa.setPosition(position);
                aa.setDuration(5000 + (mvsi.getValue(time, 1) + (chr.getSkillStatValue(time, GEOMANCY_PERSIST))) * 1000);
                aa.setRect(aa.getPosition().getRectAround(SkillData.getSkillInfoById(MANA_VEIN).getFirstRect()));
                aa.setDelay((short) 4);
                field.spawnAffectedArea(aa);
                aa.activateTimer(1000, 1000);
                chr.addSkillCoolTime(skill.getSkillId(), si.getValue(cooltime, slv) * 1000);
            } catch (NullPointerException ex) {

            }
        }
    }

    private void yosuzume() {
        Skill skill = chr.getSkill(YOSUZUME);
        if (!chr.hasSkill(YOSUZUME)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        int chance = si.getValue(prop, slv);
        if (Util.succeedProp(chance)) {
            createYosuzumeForceAtoms();
        }
    }

    private void createYosuzumeForceAtoms() {
        Random random = new Random();
        ForceAtomEnum fae = ForceAtomEnum.YOSUZUME_1;
        Mob mob = Util.getRandomFromCollection(chr.getField().getMobsInRect(chr.getPosition().getRectAround(new Rect(-300, -300, 300, 300))));
        int angle = random.nextInt(360);
        if (mob != null) {
            angle = (int) Util.getAngleOfTwoPositions(chr.getPosition(), mob.getPosition());
        }
        ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), random.nextInt(13) + 50, 6,
                angle, 500, Util.getCurrentTime(), 0, 0,
                new Position());
        ForceAtom fa = new ForceAtom(chr.getId(), fae, mob == null ? 0 : mob.getObjectId(), YOSUZUME, fai);
        chr.createForceAtom(fa);
    }

    private void lifeBloodRitual(AttackInfo attackInfo) {
        if (!chr.hasSkill(LIFEBLOOD_RITUAL)) {
            return;
        }
        Skill skill = chr.getSkill(LIFEBLOOD_RITUAL);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int healed = (int) ((chr.getMaxHP() * si.getValue(x, slv)) / 100D);
        boolean hasHealed = false;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            long totalDmg = Arrays.stream(mai.damages).sum();
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            if (totalDmg >= mob.getHp()) {

                chr.heal(healed);
                hasHealed = true;
            }
        }
        if (hasHealed) {
            chr.write(UserPacket.effect(Effect.skillAffected(skill.getSkillId(), slv, healed)));
            chr.write(UserRemote.effect(chr.getId(), Effect.skillAffected(skill.getSkillId(), slv, healed)));
        }
    }

    private void doYukiMusumeShoukanAttack() {
        if (!chr.hasSkill(YUKI_MUSUME_SHOUKAN)) {
            return;
        }
        Skill skill = chr.getSkill(YUKI_MUSUME_SHOUKAN);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int randomInt = new Random().nextInt(2) + 10;
        chr.getField().broadcastPacket(Summoned.summonedAssistAttackRequest(chr.getField().getSummonByChrAndSkillId(chr, YUKI_MUSUME_SHOUKAN_SUMMON), randomInt));
        chr.heal((int) ((chr.getMaxHP() * si.getValue(y, slv)) / 100D));
    }

    private int getManaConsumptionBySkill(int skillId) {
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        if (si != null) {
            return si.getValue(epCon, chr.getSkillLevel(skillId));
        }
        return 0;
    }

    private void createSpiritDomainForceAtom(AttackInfo attackInfo) {
        Summon summon = getSpiritDomainSummon();
        if (summon == null) {
            return;
        }
        ForceAtomEnum fae = ForceAtomEnum.SPIRIT_DOMAIN;
        List<ForceAtomInfo> faiList = new ArrayList<>();
        int atomsCreated = getManaConsumptionBySkill(attackInfo.skillId) / 5;
        int angle = chr.isLeft() ? 220 : 140;
        for (int i = 0; i < atomsCreated; i++) {
            int fImpact = new Random().nextInt(7) + 23;
            int sImpact = new Random().nextInt(3) + 3;
            ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, sImpact,
                    new Random().nextInt(30) + angle - 15, 0, Util.getCurrentTime(), 1, 0, new Position());
            faiList.add(fai);
        }
        chr.createForceAtom(new ForceAtom(false, chr.getId(), chr.getId(), fae,
                false, Collections.singletonList(0), SPIRITS_DOMAIN, faiList, new Rect(), 0, 0,
                summon.getPosition(), SPIRITS_DOMAIN, summon.getPosition(), 0));
    }

    private void incrementSpiritDomainCount() {
        Summon summon = getSpiritDomainSummon();
        if (summon == null) {
            return;
        }
        int countPerAtom = 3;
        Skill skill = chr.getSkill(SPIRITS_DOMAIN);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int stateIncReq = si.getValue(z, slv);
        summon.incCount(countPerAtom);
        if (summon.getCount() >= stateIncReq) {
            incrementSpiritDomainState();
        }
    }

    private void incrementSpiritDomainState() {
        Summon summon = getSpiritDomainSummon();
        if (summon == null) {
            return;
        }
        if (summon.getState() < 2) {
            summon.incState();
            summon.setCount(0);
            createSpiritDomainAA(summon);
            broadcastSpiritDomainState(getSpiritDomainSummon().getState());
        }
    }

    private void createSpiritDomainAA(Summon summon) {
        if (summon == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(summon.getSkillID());
        AffectedArea aa = AffectedArea.getPassiveAA(chr, summon.getSkillID(), summon.getSlv());
        aa.setPosition(summon.getPosition());
        aa.setRect(aa.getRectAround(si.getRects().get(summon.getState())));
        aa.setOption(summon.getState());
        aa.activateTimer(3000, 3000);
        chr.getField().spawnAffectedAreaAndRemoveOld(aa);
    }

    private void broadcastSpiritDomainState(int state) {
        Field field = chr.getField();
        for (Char otherChr : field.getChars()) {
            chr.write(Summoned.broadcastSpiritDomainState(otherChr.getId(), chr.getId(), state));
        }
    }

    private Summon getSpiritDomainSummon() {
        return chr.getField().getSummonByChrAndSkillId(chr, SPIRITS_DOMAIN);
    }

    public void handleForceAtomCollision(int faKey, int skillId, int mobObjId, Position position, InPacket inPacket) {
        ForceAtom fa = chr.getForceAtomByKey(faKey);
        if (fa == null) {
            return;
        }
        if (fa.getSkillId() == SPIRITS_DOMAIN) {
            incrementSpiritDomainCount();
        }
        super.handleForceAtomCollision(faKey, skillId, mobObjId, position, inPacket);
    }

    private void dropSoulShearBomb(AttackInfo attackInfo) {
        if (!chr.hasSkill(SOUL_SHEAR)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(SOUL_SHEAR);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int proc = si.getValue(prop, slv);
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(proc)) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                Item item = ItemData.getItemDeepCopy(SOUL_SHEAR_BOMB_ITEM_ID);
                Drop drop = new Drop(item.getItemId(), item);
                field.drop(drop, mob.getPosition());
            }
        }
    }


    private void explodeSoulShearBomb() {
        Set<Drop> soulShearBombSet = chr.getField().getDrops().stream().filter(d -> !d.isMoney() && d.getItem().getItemId() == SOUL_SHEAR_BOMB_ITEM_ID).collect(Collectors.toSet());
        for (Drop soulShearBomb : soulShearBombSet) {
            chr.getField().broadcastPacket(DropPool.dropExplodeField(soulShearBomb.getObjectId()));
            chr.getField().removeLife(soulShearBomb.getObjectId(), true);
        }
    }

    private boolean hasTenguStrikeActive() {
        return chr.getField().getSummonByChrAndSkillId(chr, TENGU_STRIKE_SUMMON_R) != null
                && chr.getField().getSummonByChrAndSkillId(chr, TENGU_STRIKE_SUMMON_L) != null;
    }

    public static boolean hasHakuPerfected(Char chr) {
        return chr.hasSkill(HAKU_PERFECTED) && chr.getSkillLevel(HAKU_PERFECTED) != 0;
    }

    public static int getHakuSkillLevel(Char chr) {
        return chr.getSkillLevel(!hasHakuPerfected(chr) ? HAKU_REBORN : HAKU_PERFECTED);
    }

    public ScheduledFuture getFoxManTimer() {
        return foxManTimer;
    }

    private void spawnFoxMan(Option o1, int skillID, TemporaryStatManager tsm) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int slv = getHakuSkillLevel(chr);

        o1.nOption = 1;
        o1.rOption = skillID;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(ChangeFoxMan, o1);
        chr.write(FieldPacket.enterFieldFoxMan(chr));
        chr.write(FieldPacket.foxManShowChangeEffect(chr));
        chr.write(UserPacket.skillPetState(chr, 1, (byte) 2));

        chr.addSkillCoolTime(HAKU_REBORN, 5 * 1000); // 5 seconds
        foxManTimer = EventManager.addEvent(() -> removeFoxMan(chr), o1.tOption);
    }

    @Override
    public int getFinalAttackSkill() {
        return 0;
    }

    @Override
    public void handleSkillRemove(Char chr, int skillID) {
        switch (skillID) {
            case HAKU_REBORN:
                removeFoxMan(chr);
                break;
        }
    }



    public static void removeFoxMan(Char chr) {
        if (chr != null) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();

            if (JobConstants.isKanna(chr.getJob())) {
                chr.write(UserPacket.skillPetState(chr, 1, (byte) 1));
                chr.write(FieldPacket.foxManLeaveField(chr));
                chr.write(FieldPacket.foxManShowChangeEffect(chr));
                tsm.removeStatsBySkill(HAKU_REBORN);
                tsm.removeStatsBySkill(!hasHakuPerfected(chr) ? HAKUS_BLESSING : HAKUS_BLESSING_2);
                tsm.removeStatsBySkill(!hasHakuPerfected(chr) ? BREATH_UNSEEN : BREATH_UNSEEN_2);

                Kanna job = ((Kanna) chr.getJobHandler());
                if (job.getFoxManTimer() != null) {
                    job.getFoxManTimer().cancel(true);
                }
            }

            // Remove skill for party members that have it, not sure if this is GMS like?
            if (chr.getParty() != null) {
                List<PartyMember> pChr = chr.getParty().getMembers();
                for (PartyMember m : pChr) {
                    if (m != null && m.getChr() != null) {
                        TemporaryStatManager pTsm = m.getChr().getTemporaryStatManager();
                        if (pTsm != null) {
                            if (pTsm.hasStat(HakuBlessing) && !JobConstants.isKanna(m.getChr().getJob())) {
                                pTsm.removeStat(HakuBlessing, true);
                            }
                        }
                    }
                }
            } else if (!chr.hasSkill(HAKU_REBORN)) {
                // Probably not needed but oh well...
                // when someone leaves a party or isn't in one and isn't a Kanna and has Haku Blessing active, remove it
                if (tsm.hasStat(HakuBlessing)) {
                    tsm.removeStat(HakuBlessing, true);
                }
            }
        }
    }


    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        Field field = chr.getField();
        Summon summon;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case GHOST_YAKSHA_TRAINEE:
            case GHOST_YAKSHA_BROTHER:
            case GHOST_YAKSHA_LIEUTENANT:
            case GHOST_YAKSHA_BOSS:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.Attack);
                field.spawnSummon(summon);
                break;
            case RADIANT_PEACOCK:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case TENGU_STRIKE:
                if (hasTenguStrikeActive()) {
                    field.removeSummon(TENGU_STRIKE_SUMMON_L, chr.getId());
                    field.removeSummon(TENGU_STRIKE_SUMMON_R, chr.getId());
                    chr.addSkillCoolTime(TENGU_STRIKE, 1000);
                } else {
                    for (int i = TENGU_STRIKE_SUMMON_R; i <= TENGU_STRIKE_SUMMON_L; i++) {
                        summon = Summon.getSummonBy(chr, i, slv);
                        summon.setMoveAbility(Fly);
                        summon.setMoveAction((byte) 4);
                        summon.setCurFoothold((short) 0);
                        summon.setAssistType(AssistType.Attack);
                        summon.setEnterType(EnterType.Animation);
                        summon.setFlyMob(false); //?
                        summon.setBeforeFirstAttack(true);
                        summon.setFlip(i != TENGU_STRIKE_SUMMON_L);
                        field.spawnSummon(summon);

                        int xTranslation = i == TENGU_STRIKE_SUMMON_L ? 600 : -600;
                        field.broadcastPacket(Summoned.repositionSummon(summon, i, new Position(summon.getX() + xTranslation, summon.getY() - 50)));
                        //field.broadcastPacket(Summoned.assistSpecialAttackRequest(summon, 11));
                    }
                }
                break;
            case TENGU_RETURN:
                if (hasTenguStrikeActive()) {
                    Summon summon1 = chr.getField().getSummons().stream().filter(s -> s.getChr() == chr && s.getSkillID() == TENGU_STRIKE_SUMMON_L).findAny().orElse(null);
                    Summon summon2 = chr.getField().getSummons().stream().filter(s -> s.getChr() == chr && s.getSkillID() == TENGU_STRIKE_SUMMON_R).findAny().orElse(null);
                    field.broadcastPacket(Summoned.repositionSummon(summon1, TENGU_STRIKE_SUMMON_L, chr.getPosition()));
                    field.broadcastPacket(Summoned.repositionSummon(summon2, TENGU_STRIKE_SUMMON_R, chr.getPosition()));
                    chr.getField().broadcastPacket(Summoned.assistSpecialAttackRequest(summon1, 11));
                    chr.getField().broadcastPacket(Summoned.assistSpecialAttackRequest(summon2, 11));
                    chr.addSkillCoolTime(TENGU_STRIKE, 1000);
                }
                break;
            case MANA_BALANCE: // HP subtraction  done by client, this is only to server matches.
                chr.setStatAndSendPacket(Stat.hp, chr.getHPPerc(si.getValue(hp, slv)));
                break;
            case BLOSSOM_BARRIER:
                spawnBlossomBarrier();
                break;
            case BELLFLOWER_BARRIER:
                spawnBellflowerBarrier();
                break;
            case BLOSSOMING_DAWN:
                tsm.removeAllDebuffs();
                break;
            case LIBERATED_SPIRIT_CIRCLE_SUMMON:
            case LIBERATED_SPIRIT_CIRCLE_SUMMON_2:
                Position endPosition = inPacket.decodePosition();

                SkillInfo ssi = SkillData.getSkillInfoById(skillID);
                si = SkillData.getSkillInfoById(LIBERATED_SPIRIT_CIRCLE_SMALL);
                slv = chr.getSkillLevel(LIBERATED_SPIRIT_CIRCLE_SMALL);
                int soulAmount = si.getValue((skillID == LIBERATED_SPIRIT_CIRCLE_SUMMON_2 ? u2 : u), slv);
                Rect rect = endPosition.getRectAround(ssi.getFirstRect());

                for (int i = 0; i < soulAmount; i++) {
                    Foothold fh = Util.getRandomFromCollection(field.getFootholdsInRect(rect));
                    summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                    summon.setMoveAbility(MoveAbility.Stop);
                    summon.setAssistType(AssistType.Attack);
                    summon.setPosition(fh.getRandomPosition());
                    summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                    field.spawnAddSummon(summon);
                }
                break;
            case HAKU_REBORN:
                spawnFoxMan(o1, skillID, tsm);
                break;
            case KISHIN_SHOUKAN:
                field.getSummons().stream().filter(s -> s.getSkillID() == skillID && s.getChr() == chr).forEach(field::removeLife);
                Foothold foothold = field.findFootHoldBelow(chr.getPosition());
                List<Foothold> footholds = field.getFootholdsByGroupId(foothold.getGroupId());
                int x1 = Integer.MAX_VALUE;
                int x2 = Integer.MIN_VALUE;
                for (Foothold fh : footholds) {
                    if (foothold.getY1() != fh.getY1() || foothold.getY2() != fh.getY2() || fh.getY1() != fh.getY2()) {
                        continue;
                    }
                    if (fh.getX1() < x1 && fh.getX1() > chr.getPosition().getX() - 350) {
                        x1 = fh.getX1();
                    }
                    if (fh.getX2() > x2 && fh.getX2() < chr.getPosition().getX() + 350) {
                        x2 = fh.getX2();
                    }
                }
                if (footholds.size() <= 0) {
                    chr.chatMessage("You cannot spawn kishin shoukan here.");
                    return;
                }

                // Left
                Summon kishinL = Summon.getSummonByNoCTS(chr, skillID, slv);
                kishinL.setMoveAbility(MoveAbility.Stop);
                kishinL.setPosition(new Position(x1, chr.getPosition().getY()));
                if (field.findFootHoldBelow(kishinL.getPosition()) == null) {
                    chr.chatMessage("You cannot spawn kishin shoukan here.");
                    return;
                }
                kishinL.setCurFoothold((short) field.findFootHoldBelow(kishinL.getPosition()).getId());
                kishinL.setFlip(true);

                // Right
                Summon kishinR = Summon.getSummonByNoCTS(chr, skillID, slv);
                kishinR.setMoveAbility(MoveAbility.Stop);
                kishinR.setMoveAction((byte) 5);
                kishinR.setPosition(new Position(x2, chr.getPosition().getY()));
                if (field.findFootHoldBelow(kishinR.getPosition()) == null) {
                    chr.chatMessage("You cannot spawn kishin shoukan here.");
                    return;
                }
                kishinR.setCurFoothold((short) field.findFootHoldBelow(kishinR.getPosition()).getId());
                kishinR.setFlip(false);

                kishinL.setKishinPositions(new Position[]{new Position(kishinL.getX(), kishinR.getX()), new Position(kishinL.getY() - 120, kishinR.getY())});
                kishinR.setKishinPositions(new Position[]{new Position(kishinL.getX(), kishinR.getX()), new Position(kishinL.getY() - 120, kishinR.getY())});

                field.spawnAddSummon(kishinL);
                field.spawnAddSummon(kishinR);
                field.setKishin(true);
                break;
            case AKATUSKI_HERO_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case PRINCESS_VOW_KANNA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case BLACKHEARTED_CURSE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BlackHeartedCurse, o1);
                break;
            case SPIRITS_DOMAIN:
                summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                chr.getField().spawnSummon(summon);
                createSpiritDomainAA(summon);
                break;
            case SHIKIGAMI_DOPPLEGANGER:
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = 0;
                tsm.putCharacterStatValue(Unk207_640, o2);
                break;
            case PRINCESS_SAKUNO_BLESSING:
                o1.nReason = skillID;
                o1.nValue = si.getValue(q, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void spawnBlossomBarrier() {
        if (!chr.hasSkill(BLOSSOM_BARRIER)) {
            return;
        }
        Skill skill = chr.getSkill(BLOSSOM_BARRIER);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int maxBarriers = chr.getSkillStatValue(x, WARDING_BARRIER);
        if (getBlossomBarriers() < maxBarriers) {
            Field field = chr.getField();
            Rect rect = chr.getRectAround(new Rect(-100, -100, 100, 100));
            List<AffectedArea> affectAreasInRect = field.getAffectAreasInRect(rect).stream().filter(aa -> aa.getOwner() == chr && aa.getSkillID() == MANA_VEIN).collect(Collectors.toList());
            boolean hasAAsInRect = affectAreasInRect.size() > 0;
            if (hasAAsInRect) {
                affectAreasInRect.forEach(field::removeLife);
            }
            AffectedArea aa = AffectedArea.getPassiveAA(chr, skill.getSkillId(), slv);
            aa.setPosition(chr.getPosition());
            aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
            aa.setDelay((short) 3);
            field.spawnAffectedArea(aa);
            if (hasAAsInRect) {
                aa.activateTimer(1000, 1000);
            }
        }
    }

    public int getBlossomBarriers() {
        return (int) chr.getField().getAffectedAreas().stream().filter(aa -> aa.getOwner() == chr && aa.getSkillID() == BLOSSOM_BARRIER).count();
    }

    public void spawnBellflowerBarrier() {
        if (!chr.hasSkill(BELLFLOWER_BARRIER)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(BELLFLOWER_BARRIER);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        Rect rect = chr.getRectAround(new Rect(-100, -100, 100, 100));
        List<AffectedArea> affectAreasInRect = field.getAffectAreasInRect(rect).stream().filter(aa -> aa.getOwner() == chr && aa.getSkillID() == MANA_VEIN).collect(Collectors.toList());
        boolean hasAAsInRect = affectAreasInRect.size() > 0;
        if (hasAAsInRect) {
            affectAreasInRect.forEach(field::removeLife);
        }

        AffectedArea aa = AffectedArea.getPassiveAA(chr, skill.getSkillId(), slv);
        aa.setPosition(chr.getPosition());
        aa.setDuration((si.getValue(time, slv) + (chr.hasSkill(BELLFLOWER_BARRIER_PERSIST_H) ? 20 : 0)) * 1000);
        aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
        aa.setDelay((short) 3);
        field.spawnAffectedAreaAndRemoveOld(aa);
        if (hasAAsInRect) {
            aa.activateTimer(1000, 1000);
        }
    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(skillId);
        Option o1 = new Option();
        switch (skillId) {
            case LIBERATED_SPIRIT_CIRCLE_SMALL:
            case LIBERATED_SPIRIT_CIRCLE_BIG:
                si = SkillData.getSkillInfoById(LIBERATED_SPIRIT_CIRCLE_SMALL);
                slv = chr.getSkillLevel(LIBERATED_SPIRIT_CIRCLE_SMALL);
                o1.nOption = skillId == LIBERATED_SPIRIT_CIRCLE_SMALL ? (tsm.hasStat(LiberatedSpiritCircle) ? tsm.getOption(LiberatedSpiritCircle).nOption + 1 : 1) : 0;
                o1.rOption = LIBERATED_SPIRIT_CIRCLE_SMALL;
                o1.xOption = skillId == LIBERATED_SPIRIT_CIRCLE_SMALL ? (tsm.hasStat(LiberatedSpiritCircle) ? tsm.getOption(LiberatedSpiritCircle).xOption + 1 : 1) : 0;
                tsm.putCharacterStatValue(LiberatedSpiritCircle, o1);
                tsm.sendSetStatPacket();
                chr.addSkillCoolTime(LIBERATED_SPIRIT_CIRCLE_SMALL, si.getValue(cooltime, slv) * 1000);
                chr.addSkillCoolTime(LIBERATED_SPIRIT_CIRCLE_BIG, si.getValue(cooltime, slv) * 1000);
                break;

        }
        super.handleShootObj(chr, skillId, slv);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------


    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int foxfires = 6;
        if (tsm.hasStat(FireBarrier)) {
            if (foxfires > 1) {
                foxfires = foxfires - 1;
            }
            if (foxfires == 4 || foxfires == 3) {
                o.nOption = 2;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if (foxfires == 2) {
                o.nOption = 1;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            } else if (foxfires == 1) {
                resetFireBarrier();
                o.nOption = 0;
                tsm.putCharacterStatValue(FireBarrier, o);
                tsm.sendSetStatPacket();
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    public void resetFireBarrier() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        tsm.removeStat(FireBarrier, false);
        tsm.sendResetStatPacket();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        switch (chr.getLevel()) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.KANNA_2.getJobId());
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.KANNA_3.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.KANNA_4.getJobId());
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

        // override cause kanna op
        chr.setStatAndSendPacket(Stat.mhp, 400);
        chr.setStatAndSendPacket(Stat.mmp, 100);

        chr.heal(chr.getMaxHP());
        chr.healMP(chr.getMaxMP());
        handleJobAdvance(JobConstants.JobEnum.KANNA_1.getJobId());
        chr.addSpToJobByCurrentJob(5);
    }
}