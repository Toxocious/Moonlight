package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.MobData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Shade extends Job {
    public static final int SPIRIT_BOND_I = 20050285;
    public static final int FLASH_FIST = 25001002;
    public static final int FOX_TROT = 20051284;

    public static final int SPIRIT_AFFINITY = 20050074;

    public static final int SWIFT_STRIKE = 25001000;
    public static final int VULPES_LEAP = 25001204;


    public static final int FOX_SPIRITS = 25101009; //Buff (ON/OFF)
    public static final int FOX_SPIRITS_INIT = 25100009;
    public static final int FOX_SPIRITS_ATOM = 25100010;
    public static final int FOX_SPIRITS_ATOM_2 = 25120115; //Upgrade
    public static final int GROUND_POUND_FIRST = 25101000; //Special Attack (Slow Debuff)
    public static final int GROUND_POUND_SECOND = 25100001; //Special Attack (Slow Debuff)

    public static final int SUMMON_OTHER_SPIRIT_KNOCKBACK = 25111211;

    public static final int SUMMON_OTHER_SPIRIT = 25111209; //Passive Buff (Icon)
    public static final int SPIRIT_TRAP = 25111206; //Tile
    public static final int WEAKEN = 25110210; //Passive Debuff
    public static final int SPIRIT_FRENZY = 25111005; // keydown
    public static final int SPIRIT_FRENZY_AA = 25111012; // spirit flow aa

    public static final int SPIRIT_WARD = 25121209; //Special Buff
    public static final int MAPLE_WARRIOR_SH = 25121108; //Buff
    public static final int BOMB_PUNCH = 25121000;
    public static final int BOMB_PUNCH_FINAL = 25120003; //Special Attack (Stun Debuff)
    public static final int DEATH_MARK = 25121006; //Special Attack (Mark Debuff)
    public static final int SOUL_SPLITTER = 25121007; //Special Attack (Split)
    public static final int FIRE_FOX_SPIRIT_MASTERY = 25120110;
    public static final int HEROS_WILL_SH = 25121211;

    public static final int HEROIC_MEMORIES_SH = 25121132;
    public static final int SPIRIT_BOND_MAX = 25121131;
    public static final int SPIRIT_BOND_MAX_SUMMON = 25121133;
    public static final int SPIRIT_INCARNATION = 25121030;
    public static final int SPIRIT_INCARNATION_AA = 25121055;

    public static final int SPIRIT_FLOW = 400051010;
    public static final int SPIRITGATE_SUMMONER = 400051022;
    public static final int SPIRITGATE_SUMMONS = 400051023;

    private static final int[] addedSkills = new int[]{
          SPIRIT_AFFINITY,
            SWIFT_STRIKE,
    };

    private long spiritWardTimer;
    private long lastSpiritFlowAttack;

    public Shade(Char chr) {
        super(chr);
        if (chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
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
        return JobConstants.isShade(id);
    }


    public void summonSpiritgateSummons(Position position) {
        if (!chr.hasSkill(SPIRITGATE_SUMMONER)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = chr.getSkill(SPIRITGATE_SUMMONER);
        int slv = skill.getCurrentLevel();

        if (field.getSummons().stream().anyMatch(s -> s.getSkillID() == SPIRITGATE_SUMMONER && s.getChr() == chr)) {
            for (int i = 0; i < 2; i++) {
                Summon summon = Summon.getSummonByNoCTS(chr, SPIRITGATE_SUMMONS, slv);
                summon.setMoveAbility(MoveAbility.FlyRandomAroundPos);
                summon.setAssistType(AssistType.Attack);
                summon.setPosition(position);
                field.spawnAddSummon(summon);
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
            slv = (byte) skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        if (hasHitMobs) {
            if (attackInfo.skillId != FOX_SPIRITS_ATOM || attackInfo.skillId != FOX_SPIRITS_ATOM_2) {
                if (Util.succeedProp(10)) {
                    createFoxSpiritForceAtom(attackInfo.skillId);
                }
            }
            applyWeakenOnMob(attackInfo, slv);
            deathMarkDoTHeal(attackInfo);
        }
        if (attackInfo.skillId != FOX_SPIRITS_ATOM
                && attackInfo.skillId != FOX_SPIRITS_ATOM_2
                && attackInfo.skillId != SPIRITGATE_SUMMONS
                && attackInfo.skillId != SPIRIT_TRAP
                && Util.getCurrentTimeLong() > 2 * 1000 + lastSpiritFlowAttack) {
            doSpiritFlowBonusAttack();
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Field field = chr.getField();
        switch (attackInfo.skillId) {
            case GROUND_POUND_FIRST:
            case GROUND_POUND_SECOND:
                si = SkillData.getSkillInfoById(GROUND_POUND_FIRST);
                slv = (byte) chr.getSkillLevel(GROUND_POUND_FIRST);
                o1.nOption = -si.getValue(y, slv);
                o1.rOption = GROUND_POUND_FIRST;
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
            case BOMB_PUNCH_FINAL:
                SkillInfo bpi = SkillData.getSkillInfoById(BOMB_PUNCH);
                byte bombPunchslv = (byte) chr.getSkill(BOMB_PUNCH).getCurrentLevel();
                o1.nOption = 1;
                o1.rOption = BOMB_PUNCH;
                o1.tOption = bpi.getValue(time, bombPunchslv);
                if (Util.succeedProp(bpi.getValue(prop, bombPunchslv))) {
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
            case DEATH_MARK:
                int healrate = si.getValue(x, slv);
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / healrate)));
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(dotTime, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.DebuffHealing, o1);
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case SOUL_SPLITTER: // TODO  Has some Glitches  Copy&Origin don't match up, somehow
                o1.nOption = 50;
                o1.tOption = si.getValue(time, slv);
                o1.wOption = 70;
                o1.uOption = SOUL_SPLITTER;
                o1.chr = chr;
                o2.nOption = 50;
                o2.tOption = si.getValue(time, slv);
                o2.wOption = 70;
                o2.uOption = SOUL_SPLITTER;
                o2.chr = chr;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob origin = (Mob) field.getLifeByObjectID(mai.mobId);
                    if (origin == null
                            || origin.isSplit()
                            || (origin.getTemporaryStat().hasCurrentMobStat(MobStat.SeperateSoulC) || origin.getTemporaryStat().hasCurrentMobStat(MobStat.SeperateSoulP))
                            || (origin.getCopyMob() != null || origin.getOriginMob() != null)
                            || Arrays.stream(mai.damages).sum() > origin.getHp()) {
                        continue;
                    }
                    Mob copy = MobData.getMobDeepCopyById(origin.getTemplateId());
                    copy.setPosition(origin.getPosition());
                    copy.setMaxHp(origin.getMaxHp());
                    copy.setMaxMp(origin.getMaxMp());
                    copy.setHp(origin.getHp());
                    copy.setMp(origin.getMp());
                    copy.setNotRespawnable(true);
                    copy.setSplit(true);
                    copy.setField(field);
                    field.spawnLife(copy, null);

                    MobTemporaryStat mtsCopy = copy.getTemporaryStat();
                    o1.rOption = origin.getObjectId();
                    mtsCopy.addStatOptionsAndBroadcast(MobStat.SeperateSoulC, o1);

                    MobTemporaryStat mts = origin.getTemporaryStat();
                    o2.rOption = copy.getObjectId();
                    mts.addStatOptionsAndBroadcast(MobStat.SeperateSoulP, o2);
                }
                break;
            case SPIRIT_INCARNATION:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                tsm.sendSetStatPacket();
                break;
            case SPIRITGATE_SUMMONS:
                si = SkillData.getSkillInfoById(SPIRITGATE_SUMMONER);
                slv = (byte) chr.getSkillLevel(SPIRITGATE_SUMMONER);
                o1.rOption = SPIRITGATE_SUMMONS;
                o1.tOption = si.getValue(s2, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    int stack = 0;
                    if (mts.hasCurrentMobStat(MobStat.SpiritGate)) {
                        stack = mts.getCurrentOptionsByMobStat(MobStat.SpiritGate).nOption;
                    }
                    if (stack < si.getValue(q2, slv)) {
                        stack++;
                    }
                    o1.nOption = stack * si.getValue(v2, slv);
                    o1.xOption = stack * si.getValue(q, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.SpiritGate, o1);
                }
                break;
        }
        super.handleAttack(c, attackInfo);
    }

    public void handleRemoveMobStat(MobStat mobStat, Mob mob) {
        if (mobStat == MobStat.SeperateSoulC && mob != null) {
            mob.getField().broadcastPacket(MobPool.leaveField(mob.getObjectId(), DeathType.ANIMATION_DEATH));
            mob.getField().removeLife(mob.getObjectId());
        }
    }

    private void doSpiritFlowBonusAttack() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(SPIRIT_FLOW) || !tsm.hasStatBySkillId(SPIRIT_FLOW)) {
            return;
        }
        Skill skill = chr.getSkill(SPIRIT_FLOW);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Map<Integer, Integer> randomSkillMap = Util.getRandomFromCollection(si.getRandomSkills());
        chr.write(UserLocal.bonusAttackDelayRequest(randomSkillMap));
        lastSpiritFlowAttack = Util.getCurrentTimeLong();
    }

    private void createFoxSpiritForceAtom(int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(HiddenPossession)) {
            SkillInfo si = SkillData.getSkillInfoById(FOX_SPIRITS);
            Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
            List<Mob> mobs = chr.getField().getMobsInRect(rect);
            if (mobs.size() <= 0) {
                return;
            }
            Mob mob = Util.getRandomFromCollection(mobs);
            int mobID = mob.getObjectId();
            int recreationCount = 2;
            int atomid = FOX_SPIRITS_ATOM;
            ForceAtomEnum fae = ForceAtomEnum.RABBIT_ORB;

            if (skillID == FIRE_FOX_SPIRIT_MASTERY) {
                atomid = FOX_SPIRITS_ATOM_2;
                fae = ForceAtomEnum.FLAMING_RABBIT_ORB;
                recreationCount = 3;
            }
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 15, 7,
                    305, 400, Util.getCurrentTime(), 1, 0,
                    new Position(chr.isLeft() ? 0 : -50, -50));
            ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                    true, mobID, atomid, forceAtomInfo, new Rect(), 0, 300,
                    mob.getPosition(), atomid, mob.getPosition(), 0);
            fa.setMaxRecreationCount(recreationCount);
            chr.createForceAtom(fa);
        }
    }

    public void applyWeakenOnMob(AttackInfo attackInfo, int slv) {
        if (chr.hasSkill(WEAKEN)) {
            Option o1 = new Option();
            Option o2 = new Option();
            Option o3 = new Option();
            SkillInfo si = SkillData.getSkillInfoById(WEAKEN);
            o1.nOption = si.getValue(x, slv);
            o1.rOption = WEAKEN;
            o1.tOption = si.getValue(time, slv);
            o2.nOption = si.getValue(y, slv);
            o2.rOption = WEAKEN;
            o2.tOption = si.getValue(time, slv);
            o3.nOption = si.getValue(z, slv);
            o3.rOption = WEAKEN;
            o3.tOption = si.getValue(time, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(si.getValue(prop, slv))) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Weakness, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.ACC, o2);
                    mts.addStatOptionsAndBroadcast(MobStat.EVA, o3);
                }
            }
        }
    }
    public void deathMarkDoTHeal(AttackInfo attackInfo) {
        Skill skill = chr.getSkill(DEATH_MARK);
        if (skill != null) {
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int healrate = si.getValue(x, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                if (mts.hasBurnFromSkillAndOwner(DEATH_MARK, chr.getId())) {
                    long totaldmg = Arrays.stream(mai.damages).sum();
                    chr.heal((int) (chr.getMaxHP() / ((double) 100 / healrate)));
                }
            }
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        Option o6 = new Option();
        switch (skillID) {
            case SPIRIT_TRAP:
                SkillInfo fci = SkillData.getSkillInfoById(skillID);
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(fci.getRects().get(0)));
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedArea(aa);
                break;
            case FIRE_FOX_SPIRIT_MASTERY:
            case FOX_SPIRITS_INIT:
                createFoxSpiritForceAtom(skillID);
                break;
            case HEROS_WILL_SH:
                tsm.removeAllDebuffs();
                break;
            case FOX_SPIRITS:
                if (tsm.hasStat(HiddenPossession)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(HiddenPossession, o1);
                }
                break;
            case SUMMON_OTHER_SPIRIT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case SPIRIT_WARD:
                o1.nOption = 3;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SpiritGuard, o1);
                spiritWardTimer = System.currentTimeMillis() + (si.getValue(time, slv) * 1000);
                chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                break;
            case SPIRIT_FRENZY_AA:
            case SPIRIT_INCARNATION_AA:
                si = SkillData.getSkillInfoById(skillID);
                slv = skillID == SPIRIT_FRENZY_AA ? chr.getSkillLevel(SPIRIT_FRENZY) : chr.getSkillLevel(SPIRIT_INCARNATION);
                aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setRect(aa.getRectAround(si.getFirstRect()));
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedArea(aa);
                break;
            case MAPLE_WARRIOR_SH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_SH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                var stat = IndieDamR;
                if (chr.getParty() != null) {
                    final List<Char> chrList = chr.getParty().getPartyMembersInField(chr);
                    for (Char pChr : chrList) {
                        var pTSM = pChr.getTemporaryStatManager();
                        if (JobConstants.isHeroJob(pChr.getJob())) {
                            pTSM.putCharacterStatValue(stat, o1);
                            pTSM.sendSetStatPacket();
                        }
                    }
                } else {
                    tsm.putCharacterStatValue(stat, o1);
                }
                break;
            case SPIRIT_BOND_MAX:
                o1.nReason = SPIRIT_BOND_MAX_SUMMON;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = SPIRIT_BOND_MAX_SUMMON;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                o3.nReason = SPIRIT_BOND_MAX_SUMMON;
                o3.nValue = si.getValue(indieBDR, slv);
                o3.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBDR, o3);
                o4.nReason = SPIRIT_BOND_MAX_SUMMON;
                o4.nValue = -1; //Booster
                o4.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o4);
                o5.nReason = SPIRIT_BOND_MAX_SUMMON;
                o5.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o5.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o5);
                o6.nOption = 1;
                o6.rOption = SPIRIT_BOND_MAX_SUMMON;
                o6.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HiddenHyperLinkMaximization, o6);
                Summon summon = Summon.getSummonByNoCTS(chr, SPIRIT_BOND_MAX_SUMMON, slv);
                summon.setMoveAbility(MoveAbility.Walk);
                summon.setAssistType(AssistType.AttackCounter);
                chr.getField().spawnSummon(summon);
                break;
            case SPIRIT_FLOW:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ExtraSkillCTS, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePMdR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePMdR, o2);
                for (int skillId : chr.getSkillCoolTimes().keySet()) {
                    if (!SkillData.getSkillInfoById(skillId).isNotCooltimeReset() && SkillData.getSkillInfoById(skillId).getHyper() == 0) {
                        chr.resetSkillCoolTime(skillId);
                    }
                }
                break;
            case SPIRITGATE_SUMMONER:
                summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.AttackManual);
                chr.getField().spawnSummon(summon);
                break;
        }
        tsm.sendSetStatPacket();

    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(SpiritGuard) && hitInfo.hpDamage > 0) {
            deductSpiritWard();
            hitInfo.hpDamage = 0;
            hitInfo.mpDamage = 0;
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    private void deductSpiritWard() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(SPIRIT_WARD)) {
            return;
        }
        Skill skill = chr.getSkill(SPIRIT_WARD);
        Option o = new Option();
        if (tsm.hasStat(SpiritGuard)) {
            int spiritWardCount = tsm.getOption(SpiritGuard).nOption;

            if (spiritWardCount > 0) {
                spiritWardCount--;
            }

            if (spiritWardCount <= 0) {
                tsm.removeStatsBySkill(skill.getSkillId());
                tsm.sendResetStatPacket();
            } else {
                o.setInMillis(true);
                o.nOption = spiritWardCount;
                o.rOption = skill.getSkillId();
                o.tOption = (int) (spiritWardTimer - System.currentTimeMillis());
                tsm.putCharacterStatValue(SpiritGuard, o);
                tsm.sendSetStatPacket();
            }
        }
    }


    @Override
    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(SpiritGuard)) {
            tsm.removeAllDebuffs();
            deductSpiritWard();
        }

    }

    public void reviveBySummonOtherSpirit() {
        Option o1 = new Option();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(SUMMON_OTHER_SPIRIT);
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Summon Other Spirit.");
        chr.write(UserPacket.effect(Effect.skillSpecial(SUMMON_OTHER_SPIRIT)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(SUMMON_OTHER_SPIRIT)));

        chr.write(UserPacket.effect(Effect.skillUse(25111211, chr.getLevel(), (byte) 1, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(25111211, chr.getLevel(), (byte) 1, 0)));
        chr.addSkillCoolTime(SUMMON_OTHER_SPIRIT, 1800 * 1000);
        o1.nOption = 1;
        o1.rOption = SUMMON_OTHER_SPIRIT;
        o1.tOption = 3;
        tsm.putCharacterStatValue(NotDamaged, o1);
        chr.write(UserLocal.userBonusAttackRequest(SUMMON_OTHER_SPIRIT_KNOCKBACK));
        tsm.sendSetStatPacket();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
        chr.getAvatarData().getCharacterStat().setPosMap(927030050);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
               switch (chr.getLevel()) {
            case 30:
               chr.getQuestManager().addQuest(38030);
               chr.getQuestManager().getQuestById(38030).setQrValue("clear");
               break;
        }
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.SHADE_1.getJobId());

        handleJobEnd();
    }

    int[] questsToSkip = new int[]{38000, 38001, 38002, 38003, 38004, 38005, 38006, 38007, 38008, 38009, 38010, 38011, 38012, 38013, 38014, 38015, 38016, 38017, 38018, 38019, 38020, 38021, 38022, 38023, 38024, 38025, 38026, 38027};


    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Skills
        chr.addSkill(FLASH_FIST, 0, 25);
        chr.addSkill(FOX_TROT, 1, 1);
        chr.addSkill(SPIRIT_BOND_I, 1, 1);



        int[] questToComplete = {38000, 38001, 38002, 38907};
        for (int i : questToComplete) {
            chr.getScriptManager().completeQuestNoRewards(i);
        }


        // Items
        Item chair = ItemData.getItemDeepCopy(3010766);
        chr.addItemToInventory(chair);

        Item medal = ItemData.getItemDeepCopy(1142671);
        chr.addItemToInventory(medal);

        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1353100)); // Blue Marble

        QuestManager qm = chr.getQuestManager();
        for (int questId : questsToSkip) {
            qm.completeQuest(questId);
        }
    }
}
