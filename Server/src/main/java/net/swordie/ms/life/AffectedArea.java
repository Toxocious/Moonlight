package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Zero;
import net.swordie.ms.client.jobs.adventurer.*;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.pirate.Pirate;
import net.swordie.ms.client.jobs.adventurer.thief.NightLord;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.legend.Aran;
import net.swordie.ms.client.jobs.legend.Shade;
import net.swordie.ms.client.jobs.resistance.BattleMage;
import net.swordie.ms.client.jobs.resistance.Xenon;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.life.mob.skill.MobSkillStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.Rect;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.client.jobs.sengoku.Kanna.*;

/**
 * Created on 1/6/2018.
 */
public class AffectedArea extends Life {
    private static final Logger log = Logger.getLogger(AffectedArea.class);

    private Char owner;
    private Rect rect, skillRect;
    private int skillID;
    private int force;
    private int option;
    private int elemAttr;
    private int chrLv;
    private int slv;
    private byte aaType;
    private short delay;
    private boolean flip;
    private int duration;
    private boolean removeSkill, followOwner;
    private ScheduledFuture aaTimer;
    private int mobOwnerID;
    private boolean hitMob;

    public AffectedArea(int templateId) {
        super(templateId);
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Rect getSkillRect() {
        return skillRect;
    }

    public void setSkillRect(Rect skillRect) {
        this.skillRect = skillRect;
    }

    public int getCharID() {
        return owner == null ? 0 : owner.getId();
    }

    public void setOwner(Char owner) {
        this.owner = owner;
    }

    public Char getOwner() {
        return owner;
    }

    public int getSkillID() {
        return skillID;
    }

    public int getOwnerID() {
        return owner == null ? mobOwnerID : owner.getId();
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getElemAttr() {
        return elemAttr;
    }

    public void setElemAttr(int elemAttr) {
        this.elemAttr = elemAttr;
    }

    public int getChrLv() {
        return chrLv;
    }

    public void setChrLv(int chrLv) {
        this.chrLv = chrLv;
    }

    public int getSlv() {
        return slv;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public byte getAaType() {
        return aaType;
    }

    public void setAaType(byte aaType) {
        this.aaType = aaType;
    }

    public short getDelay() {
        return delay;
    }

    public void setDelay(short delay) {
        this.delay = delay;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public boolean getRemoveSkill() {
        return removeSkill;
    }

    public void setRemoveSkill(boolean removeSkill) {
        this.removeSkill = removeSkill;
    }

    public boolean isFollowOwner() {
        return followOwner;
    }

    public void setFollowOwner(boolean followOwner) {
        this.followOwner = followOwner;
    }

    public boolean hasHitMob() {
        return hitMob;
    }

    public void setHitMob(boolean hasHitMob) {
        this.hitMob = hasHitMob;
    }

    public static AffectedArea getAffectedArea(Char chr, AttackInfo attackInfo) {
        AffectedArea aa = new AffectedArea(attackInfo.skillId);
        aa.setSkillID(attackInfo.skillId);
        aa.setSlv(attackInfo.slv);
        aa.setElemAttr(attackInfo.elemAttr);
        aa.setForce(attackInfo.force);
        aa.setOption(attackInfo.option);
        aa.setOwner(chr);
        aa.setChrLv(chr.getLevel());
        return aa;
    }

    public static AffectedArea getPassiveAA(Char chr, int skillID, int slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        AffectedArea aa = new AffectedArea(skillID);
        aa.setOwner(chr);
        aa.setChrLv(chr.getLevel());
        aa.setSkillID(skillID);
        aa.setSlv(slv);
        aa.setRemoveSkill(true);
        aa.setPosition(chr.getPosition());
        if (si != null && si.getFirstRect() != null) {
            aa.setRect(aa.getRectAround(si.getFirstRect()));
        }
        return aa;
    }

    public static AffectedArea getAura(Char chr, int skillID, int slv) {
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        AffectedArea aa = getPassiveAA(chr, skillID, slv);
        aa.setFollowOwner(true);
        aa.setSkillRect(si.getFirstRect());
        aa.setRect(aa.getOwner().getRectAround(chr.isLeft() ? aa.getSkillRect() : aa.getSkillRect().horizontalFlipAround(0)));
        aa.setDuration(-1);
        return aa;
    }

    public static AffectedArea getMobAA(Mob mob, short skill, short slv, MobSkillInfo msi) {
        AffectedArea aa = new AffectedArea(0);

        aa.setSkillID(skill);
        aa.setSlv(slv);
        aa.setDuration(msi.getSkillStatIntValue(MobSkillStat.time) * 1000);
        aa.setRect(mob.getPosition().getRectAround(new Rect(msi.getLt(), msi.getRb())));

        return aa;
    }

    public void handleMobInside(Mob mob) {
        Char chr = getField().getCharByID(getCharID());
        if (chr == null) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int skillID = getSkillID();
        Skill skill = chr.getSkill(getSkillID());
        int slv = getSlv();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        MobTemporaryStat mts = mob.getTemporaryStat();
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case FirePoison.POISON_MIST:
                if (!mts.hasBurnFromSkillAndOwner(skillID, getCharID())) {
                    int dotDmg = si.getValue(dot, slv);
                    int dotTime = ((FirePoison) chr.getJobHandler()).getExtendedDoTTime(si.getValue(SkillStat.dotTime, slv));
                    if (chr.hasSkill(FirePoison.MIST_ERUPTION)) {
                        dotDmg = chr.getSkillStatValue(SkillStat.x, FirePoison.MIST_ERUPTION); // passive DoT dmg boost to Poison Mist
                    }
                    if (chr.hasSkill(FirePoison.POISON_MIST_CRIPPLE)) {
                        dotDmg += chr.getSkillStatValue(dot, FirePoison.POISON_MIST_CRIPPLE);
                    }
                    if (chr.hasSkill(FirePoison.POISON_MIST_AFTERMATH)) {
                        dotTime += chr.getSkillStatValue(SkillStat.dotTime, FirePoison.POISON_MIST_AFTERMATH);
                    }
                    mts.createAndAddBurnedInfo(chr, skillID, slv, dotDmg, si.getValue(dotInterval, slv), dotTime, 1);
                }
            case BowMaster.FLAME_SURGE:
            case Kanna.EXORCIST_CHARM:
                if (!mts.hasBurnFromSkillAndOwner(skillID, getCharID())) {
                    mts.createAndAddBurnedInfo(chr, skill);
                }
                break;
            case Shade.SPIRIT_TRAP:
                o.nOption = 1;
                o.rOption = skillID;
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                break;
            case NightLord.FRAILTY_CURSE:
                if (!mob.isBoss() || chr.hasSkill(NightLord.FRAILTY_CURSE_BOSS_RUSH)) {
                    o.nOption = si.getValue(SkillStat.y, slv) - chr.getSkillStatValue(s, NightLord.FRAILTY_CURSE_SLOW); // already negative in SI
                    o.rOption = skillID;
                    o.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.Speed, o);
                    o1.nOption = -si.getValue(SkillStat.w, slv) - chr.getSkillStatValue(v, NightLord.FRAILTY_CURSE_ENHANCE);
                    o1.rOption = skillID;
                    o1.tOption = si.getValue(time, slv);
                    mts.addStatOptionsAndBroadcast(MobStat.PAD, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MAD, o1);
                    mts.addStatOptionsAndBroadcast(MobStat.MDR, o1);
                }
                break;
            case Zero.TIME_DISTORTION:
                mts.removeBuffs();
                o.nOption = 1;
                o.rOption = skillID;
                o.tOption = 5;
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                o1.tOption = 5;
                mts.addStatOptionsAndBroadcast(MobStat.AddDamParty, o1);
                break;
            case Aran.FINAL_CHARGE:
                o.nOption = 1;
                o.rOption = skillID;
                o.tOption = 3;
                mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
                break;
            case Pirate.PIRATES_BANNER:
                o.nOption = -si.getValue(z, slv);
                o.rOption = skillID;
                o.tOption = 5;
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
                mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
                break;


                // Auras
            case BattleMage.WEAKENING_AURA:
            case BattleMage.AURA_SCYTHE:
                si = SkillData.getSkillInfoById(BattleMage.WEAKENING_AURA);
                slv = getOwner().getSkillLevel(BattleMage.WEAKENING_AURA);
                o.nOption = -si.getValue(SkillStat.x, slv);
                o.rOption = si.getSkillId();
                o.tOption = si.getValue(time, slv);
                mts.addStatOptionsAndBroadcast(MobStat.PDR, o);
                mts.addStatOptionsAndBroadcast(MobStat.MDR, o);
                break;
        }
    }

    public void handleCharInside(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasAffectedArea(this)) {
            return;
        }
        tsm.addAffectedArea(this);
        int skillID = getSkillID();
        int slv = getSlv();
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case Zero.TIME_DISTORTION:
                tsm.removeAllDebuffs();
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                tsm.putCharacterStatValue(IndieBooster, o2); // Indie
                break;
            case BlazeWizard.BURNING_CONDUIT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                tsm.putCharacterStatValue(IndieDamR, o1); // Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieBooster, slv);
                tsm.putCharacterStatValue(IndieBooster, o2); // Indie
                break;
            case Kanna.BELLFLOWER_BARRIER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(bdR, slv) + (getOwner().hasSkill(Kanna.BELLFLOWER_BARRIER_BOSS_RUSH_H) ? 20 : 0);
                tsm.putCharacterStatValue(IndieBDR, o1); // Indie
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieDamR, slv);
                tsm.putCharacterStatValue(IndieDamR, o2);
                break;
            case BLOSSOM_BARRIER:
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DamageReduce, o1);
                o2.nOption = si.getValue(SkillStat.y, slv);
                o2.rOption = skillID;
                tsm.putCharacterStatValue(AsrR, o2);
                tsm.putCharacterStatValue(TerR, o2);
                o3.nValue = si.getValue(prop, slv);
                o3.nReason = skillID;
                tsm.putCharacterStatValue(IndieStance, o3);
                break;
            case BeastTamer.CHAMP_CHARGE_CAT_TILE:
                o1.nOption = si.getValue(q2, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;
            case Aran.MAHAS_DOMAIN:
                o1.nValue = 1;
                o1.nReason = skillID;
                tsm.putCharacterStatValue(IndieACC, o1);
                break;
            case Shadower.SMOKE_SCREEN:
                o2.nReason = skillID;
                o2.nValue = si.getValue(SkillStat.x, slv);
                tsm.putCharacterStatValue(IndieCrDmg, o2);
                break;
            case BeastTamer.PURR_ZONE:
                chr.heal(si.getValue(hp, slv));
                break;
            case Xenon.TEMPORAL_POD:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(OnCapsule, o1);
                break;
            case Xenon.HYPOGRAM_FIELD_FUSION:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.wOption = getOwner().getId();
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case Xenon.HYPOGRAM_FIELD_SUPPORT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.wOption = getOwner().getId();
                tsm.putCharacterStatValue(IndieMHPR, o1);
                break;
            case Pirate.PIRATES_BANNER:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieStatRBasic, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case SPIRITS_DOMAIN:
                int stateMultiplier = getOption() + 1;
                o1.nValue = si.getValue(SkillStat.x, slv) * stateMultiplier;
                o1.nReason = skillID;
                tsm.putCharacterStatValue(IndiePMdR, o1);
                o2.nValue = si.getValue(w, slv) * stateMultiplier;
                o2.nReason = skillID;
                tsm.putCharacterStatValue(IndieAsrR, o2);
                if (stateMultiplier == 3) {
                    o3.nValue = -2;
                    o3.nReason = skillID;
                    tsm.putCharacterStatValue(IndieBooster, o3);
                }
                break;
            case Pathfinder.OBSIDIAN_BARRIER_BURST:
            case Pathfinder.OBSIDIAN_BARRIER_TORRENT:
                o1.nOption = si.getValue(SkillStat.y, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DamageReduce, o1);
                break;

                // Auras
            case BattleMage.HASTY_AURA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieSpeed, slv);
                tsm.putCharacterStatValue(IndieSpeed, o1);
                o2.nReason = skillID;
                o2.nValue = -1;
                tsm.putCharacterStatValue(IndieBooster, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case BattleMage.DRAINING_AURA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(killRecoveryR, slv);
                tsm.putCharacterStatValue(IndieDrainHP, o1);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.xOption = chr.getId();
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
            case BattleMage.BLUE_AURA:
                o2.nReason = skillID;
                o2.nValue = si.getValue(asrR, slv);
                tsm.putCharacterStatValue(IndieAsrR, o2);
                o3.nReason = skillID;
                o3.nValue = si.getValue(terR, slv);
                tsm.putCharacterStatValue(IndieTerR, o3);
                o4.nOption = si.getValue(SkillStat.y, slv);
                o4.rOption = skillID;
                tsm.putCharacterStatValue(DamageReduce, o4);
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(BMageAura, o1);
                break;
            case BattleMage.DARK_AURA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o3.nOption = 1;
                o3.rOption = skillID;
                tsm.putCharacterStatValue(BMageAura, o3);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void activateTimer(int initialDelayMS, int delayMS) {
        aaTimer = EventManager.addFixedRateEvent(this::doScheduledFuture, initialDelayMS, delayMS);
    }

    private void doScheduledFuture() {
        int skillID = getSkillID();
        SkillInfo si = SkillData.getSkillInfoById(getSkillID());
        int slv = getSlv();
        Field field = getField();
        List<Char> chrList = field.getCharsInRect(getRect());
        switch (skillID) {
            case Xenon.TEMPORAL_POD:
                for (Char chr : chrList) {
                    TemporaryStatManager tsm = chr.getTemporaryStatManager();
                    if (tsm.hasStat(OnCapsule) && !chr.hasSkillOnCooldown(SkillConstants.XENON_POD_FOR_COOLDOWN)) {
                        for (int skillId : chr.getSkillCoolTimes().keySet()) {
                            if (SkillData.getSkillInfoById(skillId) != null && !SkillData.getSkillInfoById(skillId).isNotCooltimeReset()) {
                                chr.reduceSkillCoolTime(skillId, 1000);

                            }
                        }
                        chr.addSkillCoolTime(SkillConstants.XENON_POD_FOR_COOLDOWN, 950);
                    }
                }
                break;
            case Aran.MAHAS_DOMAIN:
                for (Char chr : chrList) {
                    TemporaryStatManager tsm = chr.getTemporaryStatManager();
                    tsm.removeAllDebuffs();
                    chr.heal((int) ((chr.getMaxHP() * si.getValue(w, slv)) / 100D));
                    chr.healMP((int) ((chr.getMaxMP() * si.getValue(w, slv)) / 100D));
                }
                break;
            case SPIRITS_DOMAIN:
                int stateMultiplier = getOption() + 1;
                int healed = si.getValue(SkillStat.y, slv) * stateMultiplier;
                for (Char chr : chrList) {
                    chr.heal(chr.getHPPerc(healed));
                    chr.healMP((int) ((chr.getMaxMP() * healed) / 100D));
                }
                break;
            case BLOSSOM_BARRIER: // Only if Mana Vein is nearby
            case BELLFLOWER_BARRIER:
            case MANA_VEIN:
                si = SkillData.getSkillInfoById(MANA_VEIN);
                int healedMana = si.getValue(w, slv);
                if (skillID != MANA_VEIN) {
                    healedMana -= getOwner().getSkillStatValue(w, WARDING_BARRIER);
                }
                int finalHealedMana = healedMana;
                chrList.stream().filter(c -> c == getOwner()).findFirst().ifPresent(chrInsideAA -> chrInsideAA.healMP(finalHealedMana));
                break;
            default:
                log.error(String.format("Unhandled AffectedArea Timer. SkillId: %d", skillID));
                aaTimer.cancel(true);
                break;
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        Field field = getField();
        if (!isFollowOwner()) {
            field.broadcastPacket(FieldPacket.affectedAreaCreated(this));
        }
        if (onlyChar != null) {
            field.checkCharInAffectedAreas(onlyChar);
        }
    }

    @Override
    public void broadcastLeavePacket() {
        if (aaTimer != null && !aaTimer.isDone()) {
            aaTimer.cancel(true);
        }
        Field field = getField();
        if (!isFollowOwner()) {
            field.broadcastPacket(FieldPacket.affectedAreaRemoved(this, false));
        }
        for (Char chr : field.getChars()) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            if (tsm.hasAffectedArea(this)) {
                tsm.removeAffectedArea(this);
            }
        }
    }
}
