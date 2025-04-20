package net.swordie.ms.client.jobs.nova;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;

import java.util.Arrays;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * @author Sjonnie
 * Created on 6/25/2018.
 */
public class Cadena extends Job {

    public static final int HAGGLE = 60020216;
    public static final int BACK_TO_HQ = 60021217;
    public static final int CHAIN_ARTS_TRASH = 60021278;
    public static final int CHAIN_ARTS_TRASH_2 = 60021279;

    public static final int SUMMON_SCIMITAR = 64001002;
    public static final int SUMMON_SCIMITAR2 = 64001013;
    public static final int CHAIN_ARTS_PURSUIT_HORIZONTAL = 64001009;
    public static final int CHAIN_ARTS_PURSUIT_HORIZONTAL_START = 64001000;
    public static final int CHAIN_ARTS_PURSUIT_UP = 64001010;
    public static final int CHAIN_ARTS_PURSUIT_UP_START = 64001007;
    public static final int CHAIN_ARTS_PURSUIT_DOWN = 64001011;
    public static final int CHAIN_ARTS_PURSUIT_DOWN_START = 64001008;
    public static final int CHAIN_ARTS_PURSUIT_PULL = 64001012;

    public static final int WEAPON_BOOSTER_CADENA = 64101003;
    public static final int SUMMON_SHURIKEN = 64101002;
    public static final int SUMMON_SHURIKEN2 = 64101008;
    public static final int SUMMON_CLAW = 64101001;

    public static final int SUMMON_DAGGERS = 64111003;
    public static final int SUMMON_SHOTGUN = 64111002;
    public static final int SUMMON_DECOY_BOMB = 64111004;
    public static final int SUMMON_DECOY_BOMB2 = 64111012;

    public static final int CHEAP_SHOT_II = 64120007;
    public static final int NOVA_WARRIOR_CADENA = 64121004;
    public static final int NOVA_TEMPERANCE = 64121005;
    public static final int CHAIN_ART_BEATDOWN = 64121001;
    public static final int SUMMON_BRICK = 64121021;
    public static final int SUMMON_SPIKED_BAT_1 = 64121003;
    public static final int SUMMON_SPIKED_BAT_3 = 64121011;

    public static final int CHAIN_ARTS_TRASH_LINKED_ATTACK_REINFORCE = 64120046;
    public static final int SHADOW_DEALER_ELIXIR = 64121054;
    public static final int VETERAN_SHADOWDEALER = 64121053;
    public static final int VETERAN_SHADOW_DEALER_FA = 64121055;

    public static final int APOCALYPSE_CANNON_SUMMON = 400041033;
    public static final int APOCALYPSE_CANNON_SHOOTOBJ = 400041034;
    public static final int CHAIN_ARTS_VOID_STRIKE_BUFF = 400041035;
    public static final int CHAIN_ARTS_VOID_STRIKE_ATTACK = 400041036;
    public static final int CHAIN_ART_MAELSTROM = 400041041;

    public static final int MUSCLE_MEMORY_I_BUFF = 64100004;
    public static final int MUSCLE_MEMORY_I_ATTACK = 64101009;
    public static final int MUSCLE_MEMORY_II_BUFF = 64110005;
    public static final int MUSCLE_MEMORY_II_ATTACK = 64111013;
    public static final int MUSCLE_MEMORY_III_BUFF = 64120006;
    public static final int MUSCLE_MEMORY_III_ATTACK = 64121020;

    public static final int CHAIN_ART_TRASH_I = 64001001;
    public static final int CHAIN_ART_TRASH_I_2 = 64001006;
    public static final int CHAIN_ART_TRASH_II = 64100000;
    public static final int CHAIN_ART_TRASH_III = 64110000;
    public static final int CHAIN_ART_TRASH_IV = 64120000;
    public static final int CHAIN_ART_REIGN_OF_CHAINS = 64121002;

    private static final int[] addedSkills = new int[]{
            HAGGLE,
            BACK_TO_HQ,
    };

    private static final int[] muscleMemoryBuff = new int[]{
            MUSCLE_MEMORY_I_BUFF,
            MUSCLE_MEMORY_II_BUFF,
            MUSCLE_MEMORY_III_BUFF,
    };

    private static final int[] muscleMemoryAttack = new int[]{
            MUSCLE_MEMORY_I_ATTACK,
            MUSCLE_MEMORY_II_ATTACK,
            MUSCLE_MEMORY_III_ATTACK,
    };

    private static final int[] chainArtSkills = new int[]{
            CHAIN_ARTS_TRASH,
            CHAIN_ARTS_TRASH_2,
            CHAIN_ART_TRASH_I,
            CHAIN_ART_TRASH_I_2,
            CHAIN_ART_TRASH_II,
            CHAIN_ART_TRASH_III,
            CHAIN_ART_TRASH_IV,
            CHAIN_ART_REIGN_OF_CHAINS,
            CHAIN_ARTS_VOID_STRIKE_ATTACK,
    };

    private static final int[] summonedSkills = new int[]{
            SUMMON_SCIMITAR,
            SUMMON_CLAW,
            SUMMON_SHURIKEN,
            SUMMON_DAGGERS,
            SUMMON_SHOTGUN,
            SUMMON_DECOY_BOMB,
            SUMMON_BRICK,
            SUMMON_SPIKED_BAT_1,
            APOCALYPSE_CANNON_SUMMON
    };

    private int lastAttack = 0;
    private long lastMMAttack = Long.MIN_VALUE;

    public Cadena(Char chr) {
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
        return JobConstants.isCadena(id);
    }


    public void handleShootObj(Char chr, int skillId, int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case SUMMON_DECOY_BOMB:
            case SUMMON_DECOY_BOMB2:
                chr.addSkillCoolTime(SUMMON_DECOY_BOMB, 8 * 1000);
                increaseMuscleMemory(SUMMON_DECOY_BOMB);
                break;
        }
        super.handleShootObj(chr, skillId, slv);
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
        if (skill != null && hasHitMobs) {
            increaseMuscleMemory(skillID);
            if (attackInfo.skillId != VETERAN_SHADOW_DEALER_FA
                    && attackInfo.skillId != APOCALYPSE_CANNON_SUMMON
                    && tsm.getOptByCTSAndSkill(ExtraSkillCTS, VETERAN_SHADOWDEALER) != null) {
                doVeteranShadowDealerAttack(attackInfo);
            }
            if (attackInfo.skillId != CHAIN_ARTS_VOID_STRIKE_ATTACK
                    && tsm.getOptByCTSAndSkill(VoidStrike, CHAIN_ARTS_VOID_STRIKE_BUFF) != null
                    && !chr.hasSkillOnCooldown(CHAIN_ARTS_VOID_STRIKE_ATTACK)) {
                chr.write(UserLocal.cadenaVoidStrikeRequest(Util.getRandomFromCollection(chr.getField().getMobs()).getPosition()));
            }
        }
        applyCheapShotDoT(attackInfo);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case SUMMON_SCIMITAR2:
                chr.addSkillCoolTime(SUMMON_SCIMITAR, 4 * 1000);
                increaseMuscleMemory(SUMMON_SCIMITAR);
                break;
            case SUMMON_DECOY_BOMB2:
                chr.addSkillCoolTime(SUMMON_DECOY_BOMB, 8 * 1000);
                break;
            case CHAIN_ART_TRASH_I:
                o1.nOption = -30;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }

                o2.nOption = si.getValue(x, slv) + (chr.hasSkill(CHAIN_ARTS_TRASH_LINKED_ATTACK_REINFORCE) ? 5 : 0);
                o2.rOption = attackInfo.skillId;
                o2.tOption = 1;
                tsm.putCharacterStatValue(NextAttackEnhance, o2);
                tsm.sendSetStatPacket();
                break;
            case CHAIN_ARTS_TRASH:
            case CHAIN_ARTS_TRASH_2:
            case CHAIN_ART_TRASH_I_2:
            case CHAIN_ART_TRASH_II:
            case CHAIN_ART_TRASH_III:
            case CHAIN_ART_TRASH_IV:
                o1.nOption = -30;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
                }

                tsm.removeStatsBySkill(CHAIN_ART_TRASH_I);
                break;
            case CHAIN_ART_BEATDOWN:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = 10;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                }
                break;
            case SUMMON_DAGGERS:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                    }
                }
                break;
            case SUMMON_SPIKED_BAT_3:
                si = SkillData.getSkillInfoById(SUMMON_SPIKED_BAT_1);
                slv = chr.getSkill(SUMMON_SPIKED_BAT_1).getCurrentLevel();
                o1.nOption = si.getValue(u, slv);
                o1.rOption = SUMMON_SPIKED_BAT_3;
                o1.tOption = si.getValue(s, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(s2, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.PAD, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.MAD, o1);
                    }
                }
                break;
            case CHAIN_ARTS_VOID_STRIKE_ATTACK:
                chr.addSkillCoolTime(attackInfo.skillId, 600);
                break;
            case CHAIN_ARTS_PURSUIT_UP:
            case CHAIN_ARTS_PURSUIT_DOWN:
                chr.write(UserPacket.effect(Effect.showChainArtPursuitEffect(attackInfo.skillId, chr.getLevel(), slv, attackInfo.left, attackInfo.ptTarget)));
                chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showChainArtPursuitEffect(attackInfo.skillId, chr.getLevel(), slv, attackInfo.left, attackInfo.ptTarget)));
                tsm.removeStat(DarkSight, true);
                tsm.sendResetStatPacket();
                break;
            case CHAIN_ARTS_PURSUIT_HORIZONTAL:
                o1.nOption = -30;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);

                    chr.write(UserPacket.effect(Effect.showChainArtPursuitEffect(attackInfo.skillId, chr.getLevel(), slv, attackInfo.left, new Position(mob.getPosition().getX(), chr.getPosition().getY()))));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.showChainArtPursuitEffect(attackInfo.skillId, chr.getLevel(), slv, attackInfo.left, new Position(mob.getPosition().getX(), chr.getPosition().getY()))));
                }
                tsm.removeStat(DarkSight, true);
                tsm.sendResetStatPacket();
                break;
        }
        super.handleAttack(c, attackInfo);
    }


    private void doVeteranShadowDealerAttack(AttackInfo attackInfo) {
        chr.write(UserLocal.userBonusAttackRequest(VETERAN_SHADOW_DEALER_FA, attackInfo.mobAttackInfo.stream().map(mai -> mai.mobId).collect(Collectors.toList())));
    }

    public void handleSkillRemove(Char chr, int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (skillId == 4001003) { // sent when Cadena needs to go out of DarkSight Mode from Pursuit
            tsm.removeStat(DarkSight, true);
            tsm.sendResetStatPacket();
        }
    }

    private void increaseMuscleMemory(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int amount = 1;
        if (lastAttack == skillId
                || SUMMON_SHURIKEN2 == skillId
                || APOCALYPSE_CANNON_SUMMON == skillId
                || Arrays.stream(chainArtSkills).anyMatch(s -> s == skillId)
                || Arrays.stream(muscleMemoryAttack).anyMatch(s -> s == skillId)) {
            return;
        }
        if (tsm.hasStat(MuscleMemory)) {
            amount = tsm.getOption(MuscleMemory).nOption;
            if (amount < Arrays.stream(summonedSkills).filter(skill -> chr.hasSkill(skill)).count()) {
                amount++;
            }
        }
        lastAttack = skillId;
        setMuscleMemory(amount);
    }

    private void setMuscleMemory(int mmCombo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = getMuscleMemorySkill();
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        Option o1 = new Option();
        o1.nOption = mmCombo;
        o1.rOption = skill.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(MuscleMemory, o1);
        tsm.sendSetStatPacket();

        if (mmCombo > 0 && (lastMMAttack + 500 < Util.getCurrentTime())) {
            lastMMAttack = Util.getCurrentTime();
            chr.write(UserLocal.userBonusAttackRequest(getMMAttackByMMBuff(getMuscleMemorySkill().getSkillId())));
        }
    }

    private Skill getMuscleMemorySkill() {
        Skill skill = null;
        for (int muscleMem : muscleMemoryBuff) {
            if (chr.hasSkill(muscleMem)) {
                skill = chr.getSkill(muscleMem);
            }
        }
        return skill;
    }

    private int getMMAttackByMMBuff(int skillId) {
        switch (skillId) {
            case MUSCLE_MEMORY_I_BUFF:
                return MUSCLE_MEMORY_I_ATTACK;
            case MUSCLE_MEMORY_II_BUFF:
                return MUSCLE_MEMORY_II_ATTACK;
            case MUSCLE_MEMORY_III_BUFF:
                return MUSCLE_MEMORY_III_ATTACK;
            default:
                return 0;
        }
    }

    private void applyCheapShotDoT(AttackInfo attackInfo) { // Doesn't to damage? wut
        if (!chr.hasSkill(CHEAP_SHOT_II)) {
            return;
        }
        Skill skill = chr.getSkill(CHEAP_SHOT_II);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            if (mts.hasAMobStat() && Util.succeedProp(si.getValue(prop, slv))) {
                mts.createAndAddBurnedInfo(chr, skill);
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
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case NOVA_TEMPERANCE:
                tsm.removeAllDebuffs();
                break;
            case CHAIN_ARTS_PURSUIT_PULL:
                if (inPacket.getUnreadAmount() > 13) {
                    inPacket.decodeArr(inPacket.getUnreadAmount() - 13);
                }
                boolean isLeft = inPacket.decodeByte() != 0;
                Position position = inPacket.decodePositionInt();
                int originSkillId = inPacket.decodeInt();
                o1.nOption = chr.getSkillLevel(CHAIN_ARTS_PURSUIT_HORIZONTAL_START);
                o1.rOption = CHAIN_ARTS_PURSUIT_HORIZONTAL_START;
                o1.tOption = 2;
                tsm.putCharacterStatValue(DarkSight, o1);
                chr.write(UserPacket.effect(Effect.showChainArtPursuitPullEffect(skillID, chr.getLevel(), chr.getSkillLevel(CHAIN_ARTS_PURSUIT_HORIZONTAL_START), isLeft, position, originSkillId)));
                break;
            case WEAPON_BOOSTER_CADENA:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case NOVA_WARRIOR_CADENA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SHADOW_DEALER_ELIXIR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv) * 1000;
                tsm.putCharacterStatValue(IndieDamR, o1);
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tTerm = si.getValue(time, slv) * 1000;
                tsm.putCharacterStatValue(IndieCr, o1);
                break;
            case VETERAN_SHADOWDEALER: // TODO double MuscleMemory Buff with this buff
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ExtraSkillCTS, o1);
                break;
            case CHAIN_ARTS_VOID_STRIKE_BUFF:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(VoidStrike, o1);
                break;
            case APOCALYPSE_CANNON_SUMMON:
                Summon summon = Summon.getSummonByNoCTS(chr, skillID, slv);
                summon.setMoveAction((byte) 4);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.None);
                summon.setFlip(!chr.isLeft());
                chr.getField().spawnSummon(summon);
                break;
            case CHAIN_ART_MAELSTROM:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(new Position(chr.getPosition().getX() + (chr.isLeft() ? -200 : 200), chr.getPosition().getY()));
                aa.setRect(aa.getRectAround(si.getFirstRect()));
                aa.setFlip(!chr.isLeft());
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedAreaAndRemoveOld(aa);
                break;
            case SUMMON_BRICK:
                increaseMuscleMemory(SUMMON_BRICK);
                break;
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
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        switch (chr.getLevel()) {
//            case 30:
//                handleJobAdvance(JobConstants.JobEnum.CADENA_2.getJobId());
//                chr.addSpToJobByCurrentJob(5);
//                break;
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.CADENA_3.getJobId());
//                chr.addSpToJobByCurrentJob(5);
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.CADENA_4.getJobId());
//                chr.addSpToJobByCurrentJob(5);
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
        handleJobAdvance(JobConstants.JobEnum.CADENA_1.getJobId());
        chr.addSpToJobByCurrentJob(3);
        Item secondary = ItemData.getItemDeepCopy(1353300);
        chr.forceUpdateSecondary(null, secondary);
    }
}
