package net.swordie.ms.client.character.skills.temp;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.skills.GuidedBullet;
import net.swordie.ms.client.character.skills.PartyBooster;
import net.swordie.ms.client.character.skills.*;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.resistance.demon.Demon;
import net.swordie.ms.client.jobs.resistance.demon.DemonAvenger;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import org.apache.log4j.LogManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.jobs.resistance.Mechanic.HUMANOID_MECH;
import static net.swordie.ms.client.jobs.resistance.Mechanic.TANK_MECH;

/**
 * Created on 1/3/2018.
 */
public class TemporaryStatManager {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();
    private final Map<CharacterTemporaryStat, List<Option>> currentStats = new ConcurrentHashMap<>();
    private final Map<CharacterTemporaryStat, List<Option>> newStats = new ConcurrentHashMap<>();
    private final Map<CharacterTemporaryStat, List<Option>> removedStats = new ConcurrentHashMap<>();
    private final Map<CharacterTemporaryStat, ScheduledFuture> schedules = new ConcurrentHashMap<>();
    private final Map<Tuple<CharacterTemporaryStat, Option>, ScheduledFuture> indieSchedules = new ConcurrentHashMap<>();
    private int pvpDamage;
    private byte defenseState;
    private byte defenseAtt;
    private int[] diceInfo = new int[22];
    private int[] diceOption = new int[8];
    private List<Integer> mobZoneStates;
    private int viperEnergyCharge;
    private StopForceAtom stopForceAtom;
    private LarknessManager larknessManager;
    private Char chr;
    private int lastStatResetRequestTime = Util.getCurrentTime();
    private List<TemporaryStatBase> twoStates = new ArrayList<>();
    private Set<AffectedArea> affectedAreas = new HashSet<>();
    private Map<BaseStat, Integer> baseStats = new HashMap<>();
    private Map<BaseStat, Set<Integer>> nonAddBaseStats = new HashMap<>();


    public TemporaryStatManager(Char chr) {
        this.chr = chr;
        for (CharacterTemporaryStat cts : TSIndex.getAllCTS()) {
            switch (cts) {
                case PartyBooster:
                    twoStates.add(new PartyBooster());
                    break;
                case GuidedBullet:
                    twoStates.add(new GuidedBullet());
                    break;
                case EnergyCharged:
                    twoStates.add(new TemporaryStatBase(true));
                    break;
                case RideVehicle:
                    twoStates.add(new TwoStateTemporaryStat(false));
                    break;
                case RelicGauge:
                    twoStates.add(new TwoStateTemporaryStat(false));
                    break;
                default:
                    twoStates.add(new TwoStateTemporaryStat(true));
                    break;
            }
        }
    }

    public List<TemporaryStatBase> getTwoStates() {
        return twoStates;
    }

    public TemporaryStatBase getTSBByTSIndex(TSIndex tsi) {
        return getTwoStates().get(tsi.getIndex());
    }



    public int[] getMaskByCollectionRemote(Map<CharacterTemporaryStat, List<Option>> map) {
        int[] res = new int[CharacterTemporaryStat.length];
        for (CharacterTemporaryStat cts : map.keySet()) {
            if(cts == EnergyCharged || cts == FullSoulMP || cts == WeaponCharge)
                continue;

            res[cts.getPos()] |= cts.getVal();
        }
        return res;
    }

    public void putCharacterStatValue(CharacterTemporaryStat cts, Option option) {
        putCharacterStatValue(cts, option, false);
    }

    public void putCharacterStatValue(CharacterTemporaryStat cts, int skillID, int value, int duration) {
        Option option = new Option();
        if (cts.isIndie()) {
            option.nReason = skillID;
            option.nValue = value;
            option.tTerm = duration;
        } else {
            option.rOption = skillID;
            option.nOption = value;
            option.tOption = duration;
        }
        putCharacterStatValue(cts, option);
    }


    public void putCharacterStatValue(CharacterTemporaryStat cts, Option option, boolean reapply) {  // reapply is used when the buff's Options have to be changed whilst the duration stays the same (e.g. Infinity)
        boolean indie = cts.isIndie();
        boolean twoState = TSIndex.isTwoState(cts);
        TemporaryStatBase tsb = null;
        if (twoState) {
            tsb = getTSBByTSIndex(TSIndex.getTSEFromCTS(cts));
        }
        option.setTimeToMillis();
        SkillInfo skillinfo = SkillData.getSkillInfoById(indie ? option.nReason : option.rOption);
        if (skillinfo != null && !skillinfo.isNotIncBuffDuration() && !reapply) {
            int duration = (indie ? option.tTerm : option.tOption);
            long buffTimeR = getChr().getTotalStat(BaseStat.buffTimeR); // includes the 100% base
            if (indie) {
                option.tTerm = (int) ((buffTimeR * duration) / 100);
            } else {
                option.tOption = (int) ((buffTimeR * duration) / 100);
            }
        }
        if (cts == CombatOrders) {
            chr.setCombatOrders(option.nOption);
        }
        if (!indie) {
            List<Option> optList = new ArrayList<>();
            optList.add(option);
            if (hasStat(cts)) {
                Option oldOption = getCurrentStats().get(cts).get(0);
                // remove old base stats from map
                for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, oldOption).entrySet()) {
                    removeBaseStat(stats.getKey(), stats.getValue());
                }
            }
            getNewStats().put(cts, optList);
            getCurrentStats().put(cts, optList);
            for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, option).entrySet()) {
                addBaseStat(stats.getKey(), stats.getValue());
            }
            if (option.tOption > 0 || (twoState && !tsb.hasExpired() && tsb.getExpireTerm() != 0)) {
                long delay = option.tOption;
                if (twoState) {
                    delay = tsb.getExpireTerm();
                }
                if (getSchedules().containsKey(cts)) {
                    getSchedules().get(cts).cancel(false);
                }
                ScheduledFuture sf = EventManager.addEvent(() -> removeStat(cts, true), delay);
                getSchedules().put(cts, sf);
            }
        } else {
            List<Option> optList;
            if (hasStat(cts)) {
                optList = getCurrentStats().get(cts);
            } else {
                optList = new ArrayList<>();
            }
            if (optList.contains(option)) {
                // remove old option of the same skill
                Option oldOption = getOptByCTSAndSkill(cts, option.nReason);
                for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, oldOption).entrySet()) {
                    removeBaseStat(stats.getKey(), stats.getValue());
                }
                optList.remove(oldOption);
            }
            optList.add(option);
            getNewStats().put(cts, optList);
            getCurrentStats().put(cts, optList);
            // Add stats to basestat
            for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, option).entrySet()) {
                addBaseStat(stats.getKey(), stats.getValue());
            }
            if (option.tTerm > 0) {
                Tuple tuple = new Tuple(cts, option);
                if (getIndieSchedules().containsKey(tuple)) {
                    getIndieSchedules().get(tuple).cancel(false);
                }
                ScheduledFuture sf = EventManager.addEvent(() -> removeIndieStat(cts, option, true), option.tTerm);
                getIndieSchedules().put(tuple, sf);
            }
        }
        if (cts != LifeTidal && JobConstants.isDemonAvenger(chr.getJob())) {
            ((DemonAvenger) chr.getJobHandler()).sendHpUpdate();
        }
    }


    public Option getOptByCTSAndSkill(CharacterTemporaryStat cts, int skillID) {
        Option res = null;
        if (getCurrentStats().containsKey(cts)) {
            for (Option o : getCurrentStats().get(cts)) {
                if (o.rOption == skillID || o.nReason == skillID) {
                    res = o;
                    break;
                }
            }
        }
        return res;
    }

    public synchronized void removeStat(CharacterTemporaryStat cts, boolean fromSchedule) {
        if (!hasStat(cts)) {
            return;
        }
        if (cts == CombatOrders) {
            chr.setCombatOrders(0);
        }

        // Handler for specific CTSs
        getChr().getJobHandler().handleRemoveCTS(cts);

        Option opt = getOption(cts);
        for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, opt).entrySet()) {
            removeBaseStat(stats.getKey(), stats.getValue());
        }
        getRemovedStats().put(cts, getCurrentStats().get(cts));
        getCurrentStats().remove(cts);
        sendResetStatPacket(cts == RideVehicle || cts == RideVehicleExpire);
        if (TSIndex.isTwoState(cts)) {
            getTSBByTSIndex(TSIndex.getTSEFromCTS(cts)).reset();
        }
        if (!fromSchedule && getSchedules().containsKey(cts)) {
            getSchedules().get(cts).cancel(false);
        } else {
            getSchedules().remove(cts);
        }
        if (JobConstants.isDemonAvenger(chr.getJob())) {
            ((DemonAvenger) chr.getJobHandler()).sendHpUpdate();
        }
    }

    public synchronized void removeIndieStat(CharacterTemporaryStat cts, Option option, boolean fromSchedule) {
        List<Option> optList = new ArrayList<>();
        optList.add(option);
        getRemovedStats().put(cts, optList);
        for (Map.Entry<BaseStat, Integer> stats : BaseStat.getFromCTS(chr, cts, option).entrySet()) {
            removeBaseStat(stats.getKey(), stats.getValue());
        }
        if (getCurrentStats().containsKey(cts)) {
            Summon summon = option.summon;
            if (summon != null) {
                if (SkillConstants.isExplodeOnDeathSummon(summon.getSkillID())) {
                    summon.setDeleteOnNextAttack(true);
//                    summon.broadcastLeavePacket();
                } else {
                    summon.getField().removeLife(summon);
                }
                option.summon = null;
            }
            getCurrentStats().get(cts).remove(option);
            if (getCurrentStats().get(cts).size() == 0) {
                getCurrentStats().remove(cts);
            }
        }
        sendResetStatPacket();
        Tuple tuple = new Tuple(cts, option);
        if (!fromSchedule && getIndieSchedules().containsKey(tuple)) {
            getIndieSchedules().get(tuple).cancel(false);
        } else {
            getIndieSchedules().remove(tuple);
        }
    }

    public boolean hasNewStat(CharacterTemporaryStat cts) {
        return newStats.containsKey(cts);
    }

    public boolean hasStat(CharacterTemporaryStat cts) {
        return getCurrentStats().containsKey(cts);
    }

    public Option getOption(CharacterTemporaryStat cts) {
        if (hasStat(cts)) {
            return getCurrentStats().get(cts).get(0);
        }
        return new Option();
    }

    public List<Option> getOptions(CharacterTemporaryStat cts) {
        if (hasStat(cts)) {
            return getCurrentStats().get(cts);
        }
        return new ArrayList<>();
    }

    public long getRemainingTime(CharacterTemporaryStat cts, int skillId) {
        if (getOptByCTSAndSkill(cts, skillId) != null) {
            Option opt = getOptByCTSAndSkill(cts, skillId);
            return (opt.startTime + ((opt.isInMillis ? 1 : 1000) * (cts.isIndie() ? opt.tTerm : opt.tOption))) - System.currentTimeMillis();
        }
        return 0;
    }

    public int[] getMaskByCollection(Map<CharacterTemporaryStat, List<Option>> map) {
        int[] res = new int[CharacterTemporaryStat.length];
        for (CharacterTemporaryStat cts : map.keySet()) {
            if (cts == FullSoulMP) {
                continue;
            }
            res[cts.getPos()] |= cts.getVal();
        }
        return res;
    }

    public int[] getTotalMask() {
        return getMaskByCollection(getCurrentStats());
    }

    public int[] getNewMask() {
        return getMaskByCollection(getNewStats());
    }

    public int[] getRemovedMask() {
        return getMaskByCollection(getRemovedStats());
    }

    public void encodeForLocal(OutPacket outPacket) {
        outPacket.encodeInt(0); // new 205
        Map<CharacterTemporaryStat, List<Option>> collection = getNewStats();
        int[] mask = getMaskByCollection(collection);
        for (int i = 0; i < getNewMask().length; i++) {
            outPacket.encodeInt(mask[i]);
        }
        List<CharacterTemporaryStat> orderedAndFilteredCtsList = new ArrayList<>(getNewStats().keySet()).stream()
                .filter(cts -> cts.getOrder() != -1)
                .sorted(Comparator.comparingInt(CharacterTemporaryStat::getOrder))
                .collect(Collectors.toList());
        for (CharacterTemporaryStat cts : orderedAndFilteredCtsList) {
            Option o = getOption(cts);
            if (cts.isEncodeInt() || SkillConstants.isEncode4Reason(o.rOption)) {
                outPacket.encodeInt(o.nOption);
            } else {
                outPacket.encodeShort(o.nOption);
            }
            outPacket.encodeInt(o.rOption);
            outPacket.encodeInt(o.tOption);
        }

        System.err.println("CTS " + getNewStats().keySet());

        if (hasNewStat(SoulMP)) {
            outPacket.encodeInt(getOption(SoulMP).xOption);
            outPacket.encodeInt(getOption(SoulMP).rOption);
        }
        if (hasNewStat(FullSoulMP)) {
            outPacket.encodeInt(getOption(FullSoulMP).xOption);
        }
        short size = 0;
        outPacket.encodeShort(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0); // nKey
            outPacket.encodeByte(0); // bEnable
        }
        if (hasNewStat(HayatoStance)) {
            outPacket.encodeInt(getOption(HayatoStance).xOption);
        }
        if (hasNewStat(Unk199_559)) {
            outPacket.encodeInt(getOption(Unk199_559).xOption);
        }
        outPacket.encodeByte(getDefenseAtt());
        outPacket.encodeByte(getDefenseState());
        outPacket.encodeByte(getPvpDamage());
        outPacket.encodeInt(getOption(EtherealForm).xOption); // new 199  Red Blue Green, used in Ethereal Form  so far
        if (hasNewStat(Dice)) {
            for (int i = 0; i < getDiceInfo().length; i++) {
                outPacket.encodeInt(diceInfo[i]);
            }
        }
        if (hasNewStat(Unk176_466)) {
            outPacket.encodeInt(getOption(Unk176_466).xOption);
        }
        if (hasNewStat(Unk200_527)) {
            outPacket.encodeInt(getOption(Unk200_527).xOption);
        }
        if (hasNewStat(BigBangAttackCharge)) {
            outPacket.encodeInt(getOption(BigBangAttackCharge).xOption);
        }
        if (hasStat(KeyDownMoving)) {
            outPacket.encodeInt(getOption(KeyDownMoving).xOption);
        }
        if (hasNewStat(KillingPoint)) {
            outPacket.encodeByte(getOption(KillingPoint).nOption);
        }
        if (hasNewStat(PinkbeanRollingGrade)) {
            outPacket.encodeByte(getOption(PinkbeanRollingGrade).nOption);
        }
        if (hasNewStat(Judgement)) {
            outPacket.encodeInt(getOption(Judgement).xOption);
        }
        if (hasNewStat(StackBuff)) {
            outPacket.encodeByte(getOption(StackBuff).mOption);
        }
        if (hasNewStat(Trinity)) {
            outPacket.encodeByte(getOption(Trinity).mOption);
        }
        if (hasNewStat(ElementalCharge)) {
            outPacket.encodeByte(getOption(ElementalCharge).mOption);
            outPacket.encodeShort(getOption(ElementalCharge).wOption);
            outPacket.encodeByte(getOption(ElementalCharge).uOption);
            outPacket.encodeByte(getOption(ElementalCharge).zOption);
        }
        if (hasNewStat(LifeTidal)) {
            outPacket.encodeInt(getOption(LifeTidal).mOption);
        }
        if (hasNewStat(AntiMagicShell)) {
            outPacket.encodeByte(getOption(AntiMagicShell).bOption);
            outPacket.encodeInt(getOption(AntiMagicShell).xOption); // new v210?
        }
        if (hasNewStat(Larkness)) {
            getLarknessManager().encode(outPacket);

        }
        if (hasNewStat(IgnoreTargetDEF)) {
            outPacket.encodeInt(getOption(IgnoreTargetDEF).mOption);
        }
        if (hasNewStat(StopForceAtomInfo)) {
            getStopForceAtom().encode(outPacket);
        }
        if (hasNewStat(SmashStack)) {
            outPacket.encodeInt(getOption(SmashStack).xOption);
            outPacket.encodeInt(getOption(SmashStack).yOption);
            outPacket.encodeInt(getOption(SmashStack).tOption); // duration? (new v210?)
        }
        if (hasNewStat(MobZoneState)) {
            for (int zoneState : getMobZoneStates()) {
                outPacket.encodeInt(zoneState);
            }
            outPacket.encodeInt(0); // notify end
        }
        if (hasNewStat(RadiantOrb)) {
            int orbSize = 0;
            outPacket.encodeInt(orbSize);
            for (int i = 0; i < orbSize; i++) {
                outPacket.encodeInt(0);
            }
        }
        if (hasNewStat(Slow)) {
            outPacket.encodeByte(getOption(Slow).bOption);
        }
        if (hasNewStat(IceAura)) {
            outPacket.encodeByte(getOption(IceAura).bOption);
        }
        if (hasNewStat(KnightsAura)) {
            outPacket.encodeByte(getOption(KnightsAura).bOption);
        }
        if (hasNewStat(IgnoreMobpdpR)) {
            outPacket.encodeByte(getOption(IgnoreMobpdpR).bOption);
        }
        if (hasNewStat(BdR)) {
            outPacket.encodeByte(getOption(BdR).bOption);
        }
        if (hasNewStat(DropRIncrease)) {
            outPacket.encodeInt(getOption(DropRIncrease).xOption);
            outPacket.encodeByte(getOption(DropRIncrease).bOption);
        }
        if (hasNewStat(PoseType)) {
            outPacket.encodeByte(getOption(PoseType).bOption);
        }
        if (hasNewStat(Beholder)) {
            outPacket.encodeInt(getOption(Beholder).sOption);
            outPacket.encodeInt(getOption(Beholder).ssOption);
        }
        if (hasNewStat(CrossOverChain)) {
            outPacket.encodeInt(getOption(CrossOverChain).xOption);
        }
        if (hasNewStat(Reincarnation)) {
            outPacket.encodeInt(getOption(Reincarnation).xOption);
        }
        if (hasNewStat(ExtremeArchery)) {
            outPacket.encodeInt(getOption(ExtremeArchery).bOption);
            outPacket.encodeInt(getOption(ExtremeArchery).xOption);
        }
        if (hasNewStat(QuiverCatridge)) {
            outPacket.encodeInt(getOption(QuiverCatridge).xOption);
        }
        if (hasNewStat(ImmuneBarrier)) {
            outPacket.encodeInt(getOption(ImmuneBarrier).xOption);
        }
        if (hasNewStat(ZeroAuraStr)) {
            outPacket.encodeByte(getOption(ZeroAuraStr).bOption);
        }
        if (hasNewStat(ZeroAuraSpd)) {
            outPacket.encodeByte(getOption(ZeroAuraSpd).bOption);
        }
        if (hasNewStat(ArmorPiercing)) {
            outPacket.encodeInt(getOption(ArmorPiercing).bOption);
        }
        if (hasNewStat(SharpEyes)) {
            outPacket.encodeInt(getOption(SharpEyes).mOption);
        }
        if (hasNewStat(AdvancedBless)) {
            outPacket.encodeInt(getOption(AdvancedBless).xOption);
        }
        if (hasNewStat(DotHealHPPerSecond)) {
            outPacket.encodeInt(getOption(DotHealHPPerSecond).xOption);
        }
        if (hasNewStat(Unk203_374)) { // unsure
            outPacket.encodeInt(getOption(Unk203_374).xOption);
        }
        if (hasNewStat(SpiritGuard)) {
            outPacket.encodeInt(getOption(SpiritGuard).nOption);
        }
        if (hasNewStat(MastemasMark)) {
            outPacket.encodeInt(getOption(MastemasMark).xOption);
        }
        if (hasNewStat(KnockBack)) {
            outPacket.encodeInt(getOption(KnockBack).nOption);
            outPacket.encodeInt(getOption(KnockBack).bOption);
        }
        if (hasNewStat(ShieldAttack)) {
            outPacket.encodeInt(getOption(ShieldAttack).xOption);
        }
        if (hasNewStat(SSFShootingAttack)) {
            outPacket.encodeInt(getOption(SSFShootingAttack).xOption);
        }
        if (hasNewStat(BMageAura)) {
            outPacket.encodeInt(getOption(BMageAura).xOption);
            outPacket.encodeByte(getOption(BMageAura).bOption);
        }
        if (hasNewStat(BattlePvPHelenaMark)) {
            outPacket.encodeInt(getOption(BattlePvPHelenaMark).cOption);
        }
        if (hasNewStat(PinkbeanAttackBuff)) {
            outPacket.encodeInt(getOption(PinkbeanAttackBuff).bOption);
        }
        if (hasNewStat(RoyalGuardState)) {
            outPacket.encodeInt(getOption(RoyalGuardState).bOption);
            outPacket.encodeInt(getOption(RoyalGuardState).xOption);
        }
        if (hasNewStat(MichaelSoulLink)) {
            outPacket.encodeInt(getOption(MichaelSoulLink).xOption);
            outPacket.encodeByte(getOption(MichaelSoulLink).bOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).cOption);
            outPacket.encodeInt(getOption(MichaelSoulLink).yOption);
        }
        if (hasNewStat(AdrenalinBoost)) {
            outPacket.encodeByte(getOption(AdrenalinBoost).cOption);
        }
        if (hasNewStat(RWCylinder)) {
            outPacket.encodeByte(getOption(RWCylinder).bOption);
            outPacket.encodeShort(getOption(RWCylinder).cOption);
            outPacket.encodeByte(getOption(RWCylinder).xOption);
        }
        if (hasNewStat(ImpenetrableSkin)) {
            outPacket.encodeInt(getOption(ImpenetrableSkin).xOption);
        }
        if (hasNewStat(Unk188_460)) {
            outPacket.encodeInt(getOption(Unk188_460).xOption);
        }
        if (hasNewStat(RWMagnumBlow)) {
            outPacket.encodeShort(getOption(RWMagnumBlow).bOption);
            outPacket.encodeByte(getOption(RWMagnumBlow).xOption);
        }
        outPacket.encodeInt(getViperEnergyCharge());
        if (hasNewStat(BladeStance)) {
            outPacket.encodeInt(getOption(BladeStance).xOption);
        }
        if (hasNewStat(DarkSight)) {
            outPacket.encodeInt(getOption(DarkSight).cOption);
            outPacket.encodeInt(getOption(DarkSight).xOption);
        }
        if (hasNewStat(Stigma)) {
            outPacket.encodeInt(getOption(Stigma).bOption);
        }
        // new 188 until TwoState
        if (hasNewStat(ExtraSkillCTS)) {
            outPacket.encodeInt(getOption(ExtraSkillCTS).xOption);
        }
        if (hasNewStat(CriticalGrowing)) {
            outPacket.encodeInt(getOption(CriticalGrowing).xOption);
        }
        if (hasNewStat(Ember)) {
            outPacket.encodeInt(getOption(Ember).xOption);
        }
        if (hasNewStat(PickPocket)) {
            outPacket.encodeInt(getOption(PickPocket).xOption);
        }
        if (hasNewStat(DivineEcho)) {
            outPacket.encodeShort(getOption(DivineEcho).xOption);
        }
        if (hasNewStat(DemonicFrenzy)) {
            outPacket.encodeShort(getOption(DemonicFrenzy).xOption);
        }
        if (hasNewStat(Unk200_431)) {
            outPacket.encodeShort(getOption(Unk200_431).xOption);
        }
        if (hasNewStat(RhoAias)) {
            outPacket.encodeInt(getOption(RhoAias).xOption);
            outPacket.encodeInt(getOption(RhoAias).cOption);
            outPacket.encodeInt(getOption(RhoAias).bOption);
            outPacket.encodeInt(getOption(RhoAias).wOption);
        }
        if (hasNewStat(VampDeath)) {
            outPacket.encodeInt(getOption(VampDeath).xOption);
        }
        if (hasNewStat(HolyMagicShell)) {
            outPacket.encodeInt(getOption(HolyMagicShell).xOption);
        }
        for (int i = 0; i < TSIndex.values().length; i++) {
            if (hasNewStat(TSIndex.getCTSFromTwoStatIndex(i))) {
                getTwoStates().get(i).encode(outPacket);
            }
        }
        if (hasNewStat(ComboCounter)) {
            outPacket.encodeInt(getOption(ComboCounter).xOption);
        }
        encodeIndieTempStat(outPacket, collection);
        if (hasNewStat(UsingScouter)) {
            // skillID check: 80011295
            outPacket.encodeInt(getOption(UsingScouter).nOption);
            outPacket.encodeInt(getOption(UsingScouter).xOption);
        }
        if (hasNewStat(Unk188_500)) {
            outPacket.encodeInt(getOption(Unk188_500).xOption);
        }
        if (hasNewStat(WingsOfGlory)) {
            outPacket.encodeInt(getOption(WingsOfGlory).xOption);
            outPacket.encodeInt(getOption(WingsOfGlory).cOption);
        }
        if (hasNewStat(LucentBrand)) {
            outPacket.encodeInt(getOption(LucentBrand).xOption);
            outPacket.encodeInt(getOption(LucentBrand).cOption);
        }
        if (hasNewStat(TrickBladeMobStat)) {
            outPacket.encodeInt(getOption(TrickBladeMobStat).xOption);
        }
        if (hasNewStat(MuscleMemory)) {
            outPacket.encodeInt(getOption(MuscleMemory).xOption);
        }

        if (hasNewStat(OverloadMode)) {
            outPacket.encodeInt(getOption(OverloadMode).xOption);
        }
        if (hasNewStat(SpecterEnergy)) {
            outPacket.encodeInt(getOption(SpecterEnergy).xOption); // energy (out of 1000)
        }
        if (hasNewStat(BasicCast)) {
            outPacket.encodeInt(getOption(BasicCast).xOption);
            outPacket.encodeInt(getOption(BasicCast).cOption);
        }
        if (hasNewStat(ScarletCast)) {
            outPacket.encodeInt(getOption(ScarletCast).xOption);
            outPacket.encodeInt(getOption(ScarletCast).cOption);
        }
        if (hasNewStat(GustCast)) {
            outPacket.encodeInt(getOption(GustCast).xOption);
            outPacket.encodeInt(getOption(GustCast).cOption);
        }
        if (hasNewStat(AbyssalCast)) {
            outPacket.encodeInt(getOption(AbyssalCast).xOption);
            outPacket.encodeInt(getOption(AbyssalCast).cOption);
        }
        if (hasNewStat(Unk199_545)) {
            outPacket.encodeInt(getOption(Unk199_545).xOption);
        }
        if (hasNewStat(Unk199_552)) {
            outPacket.encodeInt(getOption(Unk199_552).xOption);
        }
        if (hasNewStat(PhantomMark)) {
            outPacket.encodeInt(getOption(PhantomMark).xOption);
        }
        if (hasNewStat(PhantomMarkMobStat)) {
            outPacket.encodeInt(getOption(PhantomMarkMobStat).xOption);
        }
        if (hasNewStat(Unk200_573)) {
            outPacket.encodeInt(getOption(Unk200_573).xOption);
        }
        if (hasNewStat((Unk208_594))) { // new v208
            outPacket.encodeInt(getOption(Unk208_594).xOption);
            outPacket.encodeInt(getOption(Unk208_594).yOption);
        }
        if (hasNewStat((Unk208_599))) { // new v208
            outPacket.encodeInt(getOption(Unk208_599).xOption);
            outPacket.encodeInt(getOption(Unk208_599).yOption);
        }
        if (hasNewStat(Unk200_574)) {
            outPacket.encodeInt(getOption(Unk200_574).xOption);
        }
        if (hasNewStat(Unk202_576)) {
            outPacket.encodeInt(getOption(Unk202_576).xOption);
        }
        if (hasNewStat(TanadianRuin)) {
            outPacket.encodeInt(getOption(TanadianRuin).xOption);
        }
        if (hasNewStat(Unk210_593)) { // new v210?
            outPacket.encodeInt(getOption(Unk210_593).xOption);
        }
        if (hasNewStat(AncientGuidance)) {
            outPacket.encodeInt(getOption(AncientGuidance).xOption);
            outPacket.encodeInt(getOption(AncientGuidance).yOption);
        }
        if (hasNewStat(Unk205_587)) {
            outPacket.encodeInt(getOption(Unk205_587).xOption);
        }
        if (hasNewStat(Unk205_589)) {
            outPacket.encodeInt(getOption(Unk205_589).xOption);
        }
        if (hasNewStat(HolySymbol)) {
            outPacket.encodeInt(getOption(HolySymbol).xOption);
            outPacket.encodeInt(getOption(HolySymbol).yOption);
            outPacket.encodeInt(getOption(HolySymbol).zOption);
            outPacket.encodeInt(getOption(HolySymbol).wOption);
            outPacket.encodeByte(getOption(HolySymbol).bOption);
            outPacket.encodeByte(getOption(HolySymbol).cOption);
        }
        if (hasNewStat(Unk208_598)) { // new v208
            outPacket.encodeInt(getOption(Unk208_598).xOption);
            outPacket.encodeInt(getOption(Unk208_598).yOption);
            outPacket.encodeInt(getOption(Unk208_598).zOption);
        }
        if (hasNewStat(TalismanEnergy)) {
            outPacket.encodeInt(getOption(TalismanEnergy).xOption);
            outPacket.encodeInt(getOption(TalismanEnergy).yOption);
        }
        if (hasNewStat(ScrollEnergy)) {
            outPacket.encodeInt(getOption(ScrollEnergy).xOption); // Tallisman Gauge 35->70->100?
        }
        if (hasNewStat(Unk210_607)) {
            outPacket.encodeInt(getOption(Unk210_607).xOption);
        }
        if (hasNewStat(Unk210_608)) {
            outPacket.encodeInt(getOption(Unk210_608).xOption);
        }
        if (hasNewStat(unk214_1)) { //612
            outPacket.encodeInt(getOption(unk214_1).xOption);
        }
        if (hasNewStat(Nobility)) { //620
            outPacket.encodeInt(getOption(Nobility).xOption);
            outPacket.encodeInt(getOption(Nobility).yOption);
        }
        if (hasNewStat(Unk212_613)) {
            outPacket.encodeInt(getOption(Unk212_613).xOption);
        }
        if (hasNewStat(NewFlying)) {
            outPacket.encodeInt(getOption(NewFlying).xOption);
        }
        if (hasNewStat(Unk176_491)) {
            outPacket.encodeArr(Util.toPackedInt(getOption(Unk176_491).xOption));
            outPacket.encodeArr(Util.toPackedInt(getOption(Unk176_491).yOption));
        }
        if (hasNewStat(Unk176_492)) {
            outPacket.encodeArr(Util.toPackedInt(getOption(Unk176_492).xOption));
            outPacket.encodeArr(Util.toPackedInt(getOption(Unk176_492).yOption));
        }
        if (hasNewStat(YukiMusumeShoukan)) {
            outPacket.encodeInt(getOption(YukiMusumeShoukan).nOption);
            outPacket.encodeInt(getOption(YukiMusumeShoukan).yOption);
            outPacket.encodeInt(getOption(YukiMusumeShoukan).rOption);
        }
        if (hasNewStat(Unk199_595)) {
            outPacket.encodeInt(getOption(Unk199_595).xOption);
            outPacket.encodeInt(getOption(Unk199_595).yOption);
            outPacket.encodeInt(getOption(Unk199_595).zOption);
        }
        if (hasNewStat(Unk199_596)) {
            outPacket.encodeInt(getOption(Unk199_596).xOption);
            outPacket.encodeInt(getOption(Unk199_596).yOption);
            outPacket.encodeInt(getOption(Unk199_596).zOption);
        }
        if (hasNewStat(Unk199_597)) {
            outPacket.encodeInt(getOption(Unk199_597).xOption);
            outPacket.encodeInt(getOption(Unk199_597).yOption);
            outPacket.encodeInt(getOption(Unk199_597).zOption);
            outPacket.encodeInt(getOption(Unk199_597).cOption);
        }
        if (hasNewStat(BroAttack)) {
            outPacket.encodeInt(getOption(BroAttack).xOption);
            outPacket.encodeInt(getOption(BroAttack).yOption);
            outPacket.encodeInt(getOption(BroAttack).zOption);
        }
        if (hasNewStat(LiberatedSpiritCircle)) {
            outPacket.encodeInt(getOption(LiberatedSpiritCircle).xOption);
        }
        if (hasNewStat(Unk205_639)) {
            outPacket.encodeInt(getOption(Unk205_639).xOption);
        }
        if (hasNewStat(Unk208_648)) { // new v208
            outPacket.encodeInt(getOption(Unk208_648).xOption);
        }
        getNewStats().clear();
    }


    public void encodeForRemote(OutPacket outPacket, Map<CharacterTemporaryStat, List<Option>> collection) {
        int[] mask = getMaskByCollection(collection);
        for (int maskElem : mask) {
            outPacket.encodeInt(maskElem);
        }

        Set<CharacterTemporaryStat> ctses = new HashSet<>(collection.keySet());
        for (CharacterTemporaryStat cts : collection.keySet()) {
            // add duplicate but distinct cts for those that have a seperate encoding
            if (cts.requiresDuplicate()) {
                ctses.add(cts.getDuplicateCts());
            }
        }
        List<CharacterTemporaryStat> orderedAndFilteredCtsList = ctses.stream()
                .filter(cts -> cts.getRemoteOrder() != -1)
                .sorted(Comparator.comparingInt(CharacterTemporaryStat::getRemoteOrder))
                .collect(Collectors.toList());
        for (CharacterTemporaryStat cts : orderedAndFilteredCtsList) {
            if (cts.getRemoteOrder() != -1) {
                Option o = getOption(cts);
                switch (cts) {
                    case Poison:
                        outPacket.encodeShort(o.xOption);
                        break;
                }
                if (!cts.isNotEncodeAnything()) {
                    if (cts.isRemoteEncode1()) {
                        outPacket.encodeByte(o.nOption);
                    } else if (cts.isRemoteEncode4()) {
                        outPacket.encodeInt(o.nOption);
                    } else {
                        outPacket.encodeShort(o.nOption);
                    }
                    if (!cts.isNotEncodeReason()) {
                        outPacket.encodeInt(o.rOption);
                    }
                }
                // Extra options that are directly after the n/r encodes
                switch (cts) {
                    case Contagion:
                        outPacket.encodeInt(o.tOption);
                        break;
                    case BladeStance:
                    case HayatoStance:
                    case ImmuneBarrier:
                    case Unk199_520:
                    case Unk199_559:
                        outPacket.encodeInt(o.xOption);
                        break;
                    case FullSoulMP:
                        outPacket.encodeInt(o.rOption);
                        outPacket.encodeInt(o.xOption);
                        break;
                }
                // Extra options that are seperated from the normal encode
                if (cts.isSeperatedDuplicate()) {
                    CharacterTemporaryStat origin = cts.getOriginalFromDuplicate();
                    Option originOpt = getOption(origin);
                    switch (origin) {
                        case AntiMagicShell:
                            outPacket.encodeByte(o.bOption);
                            outPacket.encodeInt(o.xOption); // new v210?
                            break;
                        case Unk199_521:
                            outPacket.encodeInt(originOpt.xOption);
                            break;
                    }
                }
            }
        }
        outPacket.encodeByte(getDefenseAtt());
        outPacket.encodeByte(getDefenseState());
        outPacket.encodeByte(getPvpDamage());
        outPacket.encodeInt(getOption(EtherealForm).xOption); // Red Blue Green, used in Ethereal Form  so far
        outPacket.encodeInt(getViperEnergyCharge()); // maybe?
        Set<CharacterTemporaryStat> ctsSet = collection.keySet();
        if (ctsSet.contains(Unk176_466)) {
            outPacket.encodeInt(collection.get(Unk176_466).get(0).xOption);
        }
        if (ctsSet.contains(Unk200_527)) {
            outPacket.encodeInt(collection.get(Unk200_527).get(0).xOption);
        }
        if (ctsSet.contains(PoseType)) {
            outPacket.encodeByte(collection.get(PoseType).get(0).bOption);
        }
        if (ctsSet.contains(ZeroAuraStr)) {
            outPacket.encodeByte(collection.get(ZeroAuraStr).get(0).xOption);
        }
        if (ctsSet.contains(ZeroAuraSpd)) {
            outPacket.encodeByte(collection.get(ZeroAuraSpd).get(0).bOption);
        }
        if (ctsSet.contains(BMageAura)) {
            outPacket.encodeByte(collection.get(BMageAura).get(0).bOption);
        }
        if (ctsSet.contains(BattlePvPHelenaMark)) {
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).nOption);
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).rOption);
            outPacket.encodeInt(collection.get(BattlePvPHelenaMark).get(0).cOption);
        }
        if (ctsSet.contains(BattlePvPLangEProtection)) {
            outPacket.encodeInt(collection.get(BattlePvPLangEProtection).get(0).nOption);
            outPacket.encodeInt(collection.get(BattlePvPLangEProtection).get(0).rOption);
        }
        if (ctsSet.contains(MichaelSoulLink)) {
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).xOption);
            outPacket.encodeByte(collection.get(MichaelSoulLink).get(0).bOption);
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).cOption);
            outPacket.encodeInt(collection.get(MichaelSoulLink).get(0).yOption);
        }
        if (ctsSet.contains(AdrenalinBoost)) {
            outPacket.encodeByte(collection.get(AdrenalinBoost).get(0).cOption);
        }
        if (ctsSet.contains(Stigma)) {
            outPacket.encodeInt(collection.get(Stigma).get(0).bOption);
        }
        if (ctsSet.contains(DivineEcho)) {
            outPacket.encodeShort(collection.get(DivineEcho).get(0).xOption);
        }
        if (ctsSet.contains(DemonicFrenzy)) {
            outPacket.encodeShort(collection.get(DemonicFrenzy).get(0).xOption);
        }
        if (ctsSet.contains(Unk200_431)) {
            outPacket.encodeShort(collection.get(Unk200_431).get(0).xOption);
        }
        if (ctsSet.contains(RhoAias)) {
            outPacket.encodeInt(collection.get(RhoAias).get(0).xOption);
            outPacket.encodeInt(collection.get(RhoAias).get(0).cOption);
            outPacket.encodeInt(collection.get(RhoAias).get(0).bOption);
            outPacket.encodeInt(collection.get(RhoAias).get(0).wOption);
        }
        if (ctsSet.contains(VampDeath)) {
            outPacket.encodeInt(collection.get(VampDeath).get(0).xOption);
        }
        if (ctsSet.contains(WingsOfGlory)) {
            outPacket.encodeInt(collection.get(WingsOfGlory).get(0).xOption);
            outPacket.encodeInt(collection.get(WingsOfGlory).get(0).cOption);
        }
        if (ctsSet.contains(LucentBrand)) {
            outPacket.encodeInt(collection.get(LucentBrand).get(0).xOption);
            outPacket.encodeInt(collection.get(LucentBrand).get(0).cOption);
        }
        if (ctsSet.contains(Unk199_528)) {
            outPacket.encodeInt(collection.get(Unk199_528).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk199_528).get(0).yOption);
            outPacket.encodeInt(collection.get(Unk199_528).get(0).zOption);
        }
        if (ctsSet.contains(Unk202_580)) {
            outPacket.encodeInt(collection.get(Unk202_580).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk202_580).get(0).yOption);
            outPacket.encodeInt(collection.get(Unk202_580).get(0).zOption);
        }
        if (ctsSet.contains(Unk205_587)) {
            outPacket.encodeInt(collection.get(Unk205_587).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk205_587).get(0).yOption);
            outPacket.encodeInt(collection.get(Unk205_587).get(0).zOption);
        }
        if (ctsSet.contains(Unk205_589)) {
            outPacket.encodeInt(collection.get(Unk205_589).get(0).xOption);
        }

        if (getStopForceAtom() != null) {
            getStopForceAtom().encode(outPacket);
        } else {
            new StopForceAtom().encode(outPacket);
        }
        for (int i = 0; i < TSIndex.values().length; i++) {
            if (ctsSet.contains(TSIndex.getCTSFromTwoStatIndex(i))) {
                getTwoStates().get(i).encode(outPacket);
            }
        }
        encodeIndieTempStat(outPacket, collection);
        if (ctsSet.contains(NewFlying)) {
            outPacket.encodeInt(collection.get(NewFlying).get(0).xOption);
        }
        if (ctsSet.contains(Unk188_500)) {
            outPacket.encodeInt(collection.get(Unk188_500).get(0).xOption);
        }
        if (ctsSet.contains(KeyDownMoving)) {
            outPacket.encodeInt(collection.get(KeyDownMoving).get(0).xOption);
        }
        if (ctsSet.contains(Unk199_545)) {
            outPacket.encodeInt(collection.get(Unk199_545).get(0).xOption);
        }
        if (ctsSet.contains(ComboCounter)) {
            outPacket.encodeInt(collection.get(ComboCounter).get(0).xOption);
        }
        if (ctsSet.contains(Unk208_598)) { // new v208
            outPacket.encodeInt(collection.get(Unk208_598).get(0).xOption);
            outPacket.encodeInt(collection.get(Unk208_598).get(0).yOption);
            outPacket.encodeInt(collection.get(Unk208_598).get(0).zOption);
        }
        if (ctsSet.contains(Unk212_612)) { // new v212
            outPacket.encodeInt(collection.get(Unk212_612).get(0).xOption);
        }
        outPacket.encodeByte(0); // new v208 GMS always encodes this as 100.
    }
    private void encodeIndieTempStat(OutPacket outPacket, Map<CharacterTemporaryStat, List<Option>> collection) {
        Map<CharacterTemporaryStat, List<Option>> stats = collection.entrySet().stream()
                .filter(stat -> stat.getKey().isIndie())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        TreeMap<CharacterTemporaryStat, List<Option>> sortedStats = new TreeMap<>(stats);
        for (Map.Entry<CharacterTemporaryStat, List<Option>> stat : sortedStats.entrySet()) {
            int curTime = Util.getCurrentTime();
            List<Option> options = stat.getValue();
            if (options == null) {
                outPacket.encodeInt(0);
                continue;
            }
            outPacket.encodeInt(options.size());
            for (Option option : options) {
                outPacket.encodeInt(option.nReason);
                outPacket.encodeInt(option.nValue);
                outPacket.encodeInt(option.nKey);
                outPacket.encodeInt(curTime - option.tStart); // elapsedTime
                outPacket.encodeInt(option.tTerm);
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0); // MValueKey
                    outPacket.encodeInt(0); // MValue
                }
                size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                }
            }
        }
    }

    public void encodeRemovedIndieTempStat(OutPacket outPacket) {
        TreeMap<CharacterTemporaryStat, List<Option>> sortedStats = new TreeMap<>();
        // add removed stats into a sorted map
        for (Map.Entry<CharacterTemporaryStat, List<Option>> entry : getRemovedStats().entrySet()) {
            if (entry.getKey().isIndie() && entry.getValue() != null) {
                sortedStats.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<CharacterTemporaryStat, List<Option>> stat : sortedStats.entrySet()) {
            int curTime = Util.getCurrentTime();
            // encode remaining stats
            CharacterTemporaryStat key = stat.getKey();
            List<Option> options = getOptions(key);
            if (options == null) {
                outPacket.encodeInt(0);
                continue;
            }
            outPacket.encodeInt(options.size());
            for (Option option : options) {
                outPacket.encodeInt(option.nReason);
                outPacket.encodeInt(option.nValue);
                outPacket.encodeInt(option.nKey); // nKey
                outPacket.encodeInt(curTime - option.tStart);
                outPacket.encodeInt(option.tTerm); // tTerm
                outPacket.encodeInt(0); // size
                // pw.writeInt(0); // nMValueKey
                // pw.writeInt(0); // nMValue
                outPacket.encodeInt(0); // size not sure when this was added
                // int, int
            }
        }
    }

    public boolean hasNewMovingEffectingStat() {
        return getNewStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovementAffectingStat);
    }

    public boolean hasMovingEffectingStat() {
        return getCurrentStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovementAffectingStat);
    }

    public boolean hasRemovedMovingEffectingStat() {
        return getRemovedStats().keySet().stream().anyMatch(CharacterTemporaryStat::isMovementAffectingStat);
    }

    public Map<CharacterTemporaryStat, List<Option>> getCurrentStats() {
        return currentStats;
    }

    public Map<CharacterTemporaryStat, List<Option>> getNewStats() {
        return newStats;
    }

    public Map<CharacterTemporaryStat, List<Option>> getRemovedStats() {
        return removedStats;
    }

    public int getPvpDamage() {
        return pvpDamage;
    }

    public void setPvpDamage(int pvpDamage) {
        this.pvpDamage = pvpDamage;
    }

    public byte getDefenseState() {
        return defenseState;
    }

    public void setDefenseState(byte defenseState) {
        this.defenseState = defenseState;
    }

    public byte getDefenseAtt() {
        return defenseAtt;
    }

    public void setDefenseAtt(byte defenseAtt) {
        this.defenseAtt = defenseAtt;
    }

    public int[] getDiceInfo() {
        return diceInfo;
    }

    public void setDiceInfo(int[] diceInfo) {
        this.diceInfo = diceInfo;
    }

    public void throwDice(int roll) {
        throwDice(roll, 0);
    }

    public void throwDice(int dice1, int dice2) {
        throwDice(dice1, dice2, 0);
    }

    public void throwDice(int dice1, int dice2, int dice3) {
        int[] array = {0, 0, 30, 20, 15, 20, 30, 20};   // Stats for Normal Rolls
        int[] arrayDD = {0, 0, 40, 30, 25, 30, 40, 30}; // Stats for 2 identical numbers
        int[] arrayLD = {0, 0, 50, 40, 40, 40, 50, 40}; // Stats for 3 identical numbers
        for (int i = 0; i < diceOption.length; i++) {
            diceOption[i] = 0;
        }

        if (dice1 == dice2 && dice1 == dice3) { // 3 identical numbers
            diceOption[dice1] = arrayLD[dice1];
        } else if (dice1 == dice2) {
            diceOption[dice1] = arrayDD[dice1];
            diceOption[dice3] = array[dice3];
        } else if (dice1 == dice3) {
            diceOption[dice1] = arrayDD[dice1];
            diceOption[dice2] = array[dice2];
        } else if (dice2 == dice3) {
            diceOption[dice2] = arrayDD[dice2];
            diceOption[dice1] = array[dice1];
        } else {                                // 3 non-identical numbers
            diceOption[dice1] = array[dice1];
            diceOption[dice2] = array[dice2];
            diceOption[dice3] = array[dice3];
        }

        int[] diceinfo = new int[]{
                diceOption[3],  //nOption 3 (MHPR)
                diceOption[3],  //nOption 3 (MMPR)
                diceOption[4],  //nOption 4 (Cr)
                0,  // CritDamage Min
                0,  // ???  ( CritDamage Max (?) )
                0,  // EVAR
                0,  // AR
                0,  // ER
                diceOption[2],  //nOption 2 (PDDR)
                diceOption[2],  //nOption 2 (MDDR)
                0,  // PDR
                0,  // MDR
                diceOption[5],  //nOption 5 (PIDR)
                0,  // PDamR
                0,  // MDamR
                0,  // PADR
                0,  // MADR
                diceOption[6], //nOption 6 (EXP)
                diceOption[7], //nOption 7 (IED)
                0,  // ASRR
                0,  // TERR
                0,  // MesoRate
                0,
        };
        setDiceInfo(diceinfo);
    }

    public List<Integer> getMobZoneStates() {
        return mobZoneStates;
    }

    public void setMobZoneStates(List<Integer> mobZoneStates) {
        this.mobZoneStates = mobZoneStates;
    }

    public int getViperEnergyCharge() {
        return viperEnergyCharge;
    }

    public void setViperEnergyCharge(int viperEnergyCharge) {
        this.viperEnergyCharge = viperEnergyCharge;
    }

    public StopForceAtom getStopForceAtom() {
        return stopForceAtom;
    }

    public void setStopForceAtom(StopForceAtom stopForceAtom) {
        this.stopForceAtom = stopForceAtom;
    }

    public LarknessManager getLarknessManager() {
        return larknessManager;
    }

    public Char getChr() {
        return chr;
    }

    public Map<CharacterTemporaryStat, ScheduledFuture> getSchedules() {
        return schedules;
    }

    public Map<Tuple<CharacterTemporaryStat, Option>, ScheduledFuture> getIndieSchedules() {
        return indieSchedules;
    }

    public void sendSetStatPacket() {
        Char copy = chr.getCopy();
        if (copy != null) {
            chr.write(UserRemote.setTemporaryStat(copy, (short) 0));
        }
        synchronized (newStats) {
            if (newStats.size() != 0) {
                getChr().getField().broadcastPacket(UserRemote.setTemporaryStat(getChr(), (short) 0), getChr());
                getChr().write(WvsContext.temporaryStatSet(this));
            }
        }
    }

    public void sendResetStatPacket() {
        sendResetStatPacket(false);
    }

    public void sendResetStatPacket(boolean demount) {
        if (getRemovedStats().size() > 0) {
            getChr().getField().broadcastPacket(UserRemote.resetTemporaryStat(getChr()), getChr());
            getChr().getClient().write(WvsContext.temporaryStatReset(this, demount));
        }
    }

    public void removeAllDebuffs() {
        if (hasStat(Stun)) {
            removeStat(CharacterTemporaryStat.Stun, false);
        }
        if (hasStat(Poison)) {
            removeStat(CharacterTemporaryStat.Poison, false);
        }
        if (hasStat(Seal)) {
            removeStat(CharacterTemporaryStat.Seal, false);
        }
        if (hasStat(Darkness)) {
            removeStat(CharacterTemporaryStat.Darkness, false);
        }
        if (hasStat(Thaw)) {
            removeStat(CharacterTemporaryStat.Thaw, false);
        }
        if (hasStat(Weakness)) {
            removeStat(CharacterTemporaryStat.Weakness, false);
        }
        if (hasStat(Curse)) {
            removeStat(CharacterTemporaryStat.Curse, false);
        }
        if (hasStat(Slow)) {
            removeStat(CharacterTemporaryStat.Slow, false);
        }
        if (hasStat(Blind)) {
            removeStat(CharacterTemporaryStat.Blind, false);
        }
        sendResetStatPacket();
    }

    public void setLarknessManager(LarknessManager larknessManager) {
        this.larknessManager = larknessManager;
    }

    public Set<AffectedArea> getAffectedAreas() {
        return affectedAreas;
    }

    public void addAffectedArea(AffectedArea aa) {
        getAffectedAreas().add(aa);
    }

    public void removeAffectedArea(AffectedArea aa) {
        getAffectedAreas().remove(aa);

        if (aa.getRemoveSkill()) {
            removeStatsBySkill(aa.getSkillID());
        }
    }

    public boolean hasAffectedArea(AffectedArea affectedArea) {
        return getAffectedAreas().contains(affectedArea);
    }

    public boolean hasStatBySkillId(int skillId) {
        for (CharacterTemporaryStat cts : getCurrentStats().keySet()) {
            if (getOption(cts).rOption == skillId || getOption(cts).nReason == skillId) {
                return true;
            }
        }
        return false;
    }

    public static final EnumSet<CharacterTemporaryStat> RESET_BY_TIME_CTS = EnumSet.of(
            Stun, Shock, Poison, Seal, Darkness, Weakness, WeaknessMdamage, Curse, Slow, /*TimeBomb,*/
            DisOrder, Thread, Attract, Magnet, MagnetArea, ReverseInput, BanMap, StopPortion, StopMotion,
            Fear, Frozen, Frozen2, Web, NotDamaged, FinalCut, Lapidification, VampDeath, VampDeathSummon,
            GiveMeHeal, TouchMe, Contagion, ComboUnlimited, CrossOverChain, Reincarnation, ComboCostInc,
            DotBasedBuff, ExtremeArchery, QuiverCatridge, AdvancedQuiver, UserControlMob, ArmorPiercing,
            CriticalGrowing, QuickDraw, BowMasterConcentration, ComboTempest, SiphonVitality, KnockBack, RWMovingEvar);

    public void resetByTime(int curTime) {
        if (curTime - lastStatResetRequestTime < 500) {
            return;
        }
        getCurrentStats().forEach((key, value) -> {
            if (RESET_BY_TIME_CTS.contains(key)) {
                Option o = value.get(0);
                if (o.tOption != 0 && curTime - o.tStart >= o.tOption) {
                    removeStat(key, true);
                }
            }
        });
        this.lastStatResetRequestTime = curTime;
    }

    public void removeStatsBySkill(int skillId) {
        Map<CharacterTemporaryStat, Option> removedMap = new HashMap<>();
        for (CharacterTemporaryStat cts : getCurrentStats().keySet()) {
            Option checkOpt = new Option();
            checkOpt.nReason = skillId;
            if (cts.isIndie() && getOptions(cts) != null && getOptions(cts).contains(checkOpt)) {
                Option o = Util.findWithPred(getOptions(cts), opt -> opt.equals(checkOpt));
                if (o == null) {
                    log.error("Found option null, yet the options contained it?");
                } else {
                    removedMap.put(cts, o);
                }
            } else if (getOption(cts).rOption == skillId || getOption(cts).nReason == skillId) {
                removedMap.put(cts, getOption(cts));
            }
        }
        removedMap.forEach((cts, opt) -> {
            if (cts.isIndie()) {
                removeIndieStat(cts, opt, false);
            } else {
                removeStat(cts, false);
            }
        });
    }

    public void addSoulMPFromMobDeath() {
        if (hasStat(CharacterTemporaryStat.SoulMP)) {
            Option o = getOption(SoulMP);
            o.nOption = Math.min(ItemConstants.MAX_SOUL_CAPACITY, o.nOption + ItemConstants.MOB_DEATH_SOUL_MP_COUNT);
            putCharacterStatValue(SoulMP, o);
            if (o.nOption >= ItemConstants.MAX_SOUL_CAPACITY && !hasStat(FullSoulMP)) {
                Option o2 = new Option();
                //o2.rOption = ItemConstants.getSoulSkillFromSoulID(((Equip) chr.getEquippedItemByBodyPart(BodyPart.Weapon)).getSoulOptionId());
                if (o2.rOption == 0) {
                    chr.chatMessage(String.format("Unknown corresponding skill for soul socket id %d!",
                            ((Equip) chr.getEquippedItemByBodyPart(BodyPart.Weapon)).getSoulOptionId()));
                }
                o2.nOption = ItemConstants.MAX_SOUL_CAPACITY;
                o2.xOption = ItemConstants.MAX_SOUL_CAPACITY;
                putCharacterStatValue(FullSoulMP, o2);
            }
            sendSetStatPacket();
        }
    }

    public void putCharacterStatValueFromMobSkill(CharacterTemporaryStat cts, Option o) {
        o.rOption |= o.slv << 16; // mob skills are encoded differently: not an int, but short (skill ID), then short (slv).
        putCharacterStatValue(cts, o);
        Job sourceJobHandler = chr.getJobHandler();
        sourceJobHandler.handleMobDebuffSkill(chr);
    }

    public void removeAllStats() {
        Set<CharacterTemporaryStat> currentStats = new HashSet<>();
        currentStats.addAll(getNewStats().keySet());
        currentStats.addAll(getCurrentStats().keySet());
        currentStats.forEach(stat -> removeStat(stat, false));
    }

    public void removeAllStats(boolean fromSchedule) {
        Set<CharacterTemporaryStat> currentStats = new HashSet<>();
        currentStats.addAll(getNewStats().keySet());
        currentStats.addAll(getCurrentStats().keySet());
        currentStats.forEach(stat -> removeStat(stat, fromSchedule));

        if (getOptByCTSAndSkill(CharacterTemporaryStat.RideVehicle, HUMANOID_MECH) != null
                || getOptByCTSAndSkill(CharacterTemporaryStat.RideVehicle, TANK_MECH) != null) {
            removeStatsBySkill(TANK_MECH + 100);
            removeStatsBySkill(HUMANOID_MECH + 100);
            sendResetStatPacket(true);
        } else {
            sendResetStatPacket();
        }
        currentStats.clear();
    }

    public Map<BaseStat, Integer> getBaseStats() {
        return baseStats;
    }

    public Map<BaseStat, Set<Integer>> getNonAddBaseStats() {
        return nonAddBaseStats;
    }

    public void addBaseStat(BaseStat bs, int value) {
        if (bs.isNonAdditiveStat()) {
            if (!getNonAddBaseStats().containsKey(bs)) {
                getNonAddBaseStats().put(bs, new HashSet<>());
            }
            getNonAddBaseStats().get(bs).add(value);
        } else {
            getBaseStats().put(bs, getBaseStats().getOrDefault(bs, 0) + value);
        }
    }

    public void removeBaseStat(BaseStat bs, int value) {
        addBaseStat(bs, -value);
    }

    public long getTotalNOptionOfStat(CharacterTemporaryStat cts) {
        if (cts.isIndie()) {
            return getOptions(cts).stream().mapToLong(o -> o.nValue).sum();
        } else {
            return getOptions(cts).stream().mapToLong(o -> o.nOption).sum();
        }
    }
}
