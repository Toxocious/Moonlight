package net.swordie.ms.life.mob.skill;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.MobPool;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.MobConstants;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.ObtacleAtomCreateType;
import net.swordie.ms.enums.ObtacleAtomEnum;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.DeathType;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.MobSkillInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.bosses.lucid.FairyDust;
import net.swordie.ms.world.field.bosses.lucid.LucidSkillType;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.life.mob.skill.MobSkillStat.*;

/**
 * Created on 2/28/2018.
 */
public class MobSkill {
    private static final Logger log = LogManager.getRootLogger();
    private int skillSN;
    private byte action;
    private int level;
    private int effectAfter;
    private int skillAfter;
    private byte priority;
    private boolean firstAttack;
    private boolean onlyFsm;
    private boolean onlyOtherSkill;
    private int skillForbid;
    private int afterDelay;
    private int fixDamR;
    private boolean doFirst;
    private int preSkillIndex;
    private int preSkillCount;
    private String info;
    private String text;
    private boolean afterDead;
    private int afterAttack = -1;
    private int afterAttackCount;
    private int castTime;
    private int coolTime;
    private int delay;
    private int useLimit;
    private String speak;
    private int skillID;
    private int disease;

    public int getSkillSN() {
        return skillSN;
    }

    public void setSkillSN(int skillSN) {
        this.skillSN = skillSN;
    }

    public byte getAction() {
        return action;
    }

    public void setAction(byte action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEffectAfter() {
        return effectAfter;
    }

    public void setEffectAfter(int effectAfter) {
        this.effectAfter = effectAfter;
    }

    public int getSkillAfter() {
        return skillAfter;
    }

    public void setSkillAfter(int skillAfter) {
        this.skillAfter = skillAfter;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean getFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public void setOnlyFsm(boolean onlyFsm) {
        this.onlyFsm = onlyFsm;
    }

    public boolean isOnlyFsm() {
        return onlyFsm;
    }

    public void setOnlyOtherSkill(boolean onlyOtherSkill) {
        this.onlyOtherSkill = onlyOtherSkill;
    }

    public boolean isOnlyOtherSkill() {
        return onlyOtherSkill;
    }

    public void setSkillForbid(int skillForbid) {
        this.skillForbid = skillForbid;
    }

    public int getSkillForbid() {
        return skillForbid;
    }

    public void setAfterDelay(int afterDelay) {
        this.afterDelay = afterDelay;
    }

    public int getAfterDelay() {
        return afterDelay;
    }

    public void setFixDamR(int fixDamR) {
        this.fixDamR = fixDamR;
    }

    public int getFixDamR() {
        return fixDamR;
    }

    public void setDoFirst(boolean doFirst) {
        this.doFirst = doFirst;
    }

    public boolean isDoFirst() {
        return doFirst;
    }

    public void setPreSkillIndex(int preSkillIndex) {
        this.preSkillIndex = preSkillIndex;
    }

    public int getPreSkillIndex() {
        return preSkillIndex;
    }

    public void setPreSkillCount(int preSkillCount) {
        this.preSkillCount = preSkillCount;
    }

    public int getPreSkillCount() {
        return preSkillCount;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info == null ? "" : info;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text == null ? "" : text;
    }

    public void setAfterDead(boolean afterDead) {
        this.afterDead = afterDead;
    }

    public boolean isAfterDead() {
        return afterDead;
    }

    public void setAfterAttack(int afterAttack) {
        this.afterAttack = afterAttack;
    }

    public int getAfterAttack() {
        return afterAttack;
    }

    public void setAfterAttackCount(int afterAttackCount) {
        this.afterAttackCount = afterAttackCount;
    }

    public int getAfterAttackCount() {
        return afterAttackCount;
    }

    public void setCastTime(int castTime) {
        this.castTime = castTime;
    }

    public int getCastTime() {
        return castTime;
    }

    public void setCoolTime(int coolTime) {
        this.coolTime = coolTime;
    }

    public int getCoolTime() {
        return coolTime;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setUseLimit(int useLimit) {
        this.useLimit = useLimit;
    }

    public int getUseLimit() {
        return useLimit;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getSpeak() {
        return speak == null ? "" : speak;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public void applyEffect(Mob mob) {
        MobTemporaryStat mts = mob.getTemporaryStat();
        short skill = (short) getSkillID();
        short slv = (short) getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skill, slv);
        MobSkillID msID = MobSkillID.getMobSkillIDByVal(skill);
        Field field = mob.getField();
        Option o = new Option(skill);
        o.slv = slv;
        o.tOption = msi.getSkillStatIntValue(time);
        Option o2 = new Option(skill);
        o2.slv = slv;
        o2.tOption = msi.getSkillStatIntValue(time);
        Rect rect = null;
        Set<Mob> mobs = new HashSet<>();
        Set<Char> chars = new HashSet<>();
        if (msi.getLt() != null) {
            rect = new Rect(msi.getLt(), msi.getRb());
            if (mob.isFlip()) {
                rect.horizontalFlipAround(mob.getPosition().getX());
            }
            mobs.addAll(mob.getField().getMobsInRect(rect));
            chars.addAll(mob.getField().getCharsInRect(rect));
        }
        if (msi.getSkillStatIntValue(fieldScript) != 0) {
            // mabye?
//            field.startScript(String.format("mobskill_%s_%d", msID.toString(), slv));
//            return;
        }
        switch (msID) {
            case PowerUp:
            case PowerUpM:
            case Pad:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PowerUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.PowerUp, o);
                }
                break;
            case MagicUp:
            case MagicUpM:
            case Mad:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MagicUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.MagicUp, o);
                }
                break;
            case Damage:
                boolean fixDamR = msi.getSkillStatIntValue(MobSkillStat.fixDamR) > 0;
                int damage = 5000;

                if (damage != 0)
                    chars.stream().filter(chra -> !chra.getTemporaryStatManager().hasStat(CharacterTemporaryStat.NotDamaged)).forEach(chra -> chra.damage((fixDamR ? chra.getHPPerc(damage) : damage), true));
                break;
            case PGuardUp:
            case PGuardUpM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PGuardUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.PGuardUp, o);
                }
                break;
            case MGuardUp:
            case MGuardUpM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.MGuardUp, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.MGuardUp, o);
                }
                break;
            case Haste:
            case HasteM:
                o.nOption = msi.getSkillStatIntValue(x);
                mts.addMobSkillOptionsAndBroadCast(MobStat.Speed, o);
                for (Mob m : mobs) {
                    m.getTemporaryStat().addMobSkillOptionsAndBroadCast(MobStat.Speed, o);
                }
                break;
            case HealM:
                for (Mob m : mobs) {
                    m.heal(msi.getSkillStatIntValue(x));
                }
                break;
            case Seal:
            case Darkness:
            case Weakness:
            case Stun:
            case Curse:
            case Poison:
            case Slow:
            case Undead:
            case Fear:
            case PainMark:
            case UserMorph:
                Char chr = chars.size() == 0 ? null : Util.getRandomFromCollection(chars);
                if (chr != null) {
                    applyEffect(chr);
                }
                if (msID == MobSkillID.Fear && slv == 26) {
                    // Black Mage P2 mass skill
                }
                break;
            case Dispel:

                break;
            case Attract:

                break;
            case AreaFire:
            case AreaPoison:
            case AreaForce:
            case AreaTimezone:
            case AreaTosp:
            case AreaAbnormal:
            case AreaMobBuff:
            case AreaWarning:
            case AreaForceFromUser:
                AffectedArea aa = AffectedArea.getMobAA(mob, skill, slv, msi);
                field.spawnAffectedArea(aa);
                break;

            case PhysicalImmune:
                if (!mts.hasCurrentMobStat(MobStat.PImmune) && !mts.hasCurrentMobStat(MobStat.MImmune)) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.PImmune, o);
                }
                break;
            case MagicImmune:
                if (!mts.hasCurrentMobStat(MobStat.PImmune) && !mts.hasCurrentMobStat(MobStat.MImmune)) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.MImmune, o);
                }
                break;
            case Teleport:
                int skillAfter = msi.getSkillStatIntValue(x);
                int xPos = msi.getSkillStatIntValue(y);
                Position newPos = new Position(mob.isLeft() ? mob.getX() - xPos : mob.getX() + xPos, mob.getY());
                if (getLevel() != 1 || getLevel() != 2) {
                    mob.teleport(newPos, skillAfter);
                }
                if (MobConstants.isDamien(mob.getTemplateId())) {
                    field.broadcastPacket(MobPool.demianDelayedAttackCreate(mob, skillID, getLevel(), msi));
                }
                break;
            case PMCounter:
                o.nOption = msi.getSkillStatIntValue(x);
                o.mOption = 100;
                o.bOption = msi.getSkillStatIntValue(MobSkillStat.delay);
                o.wOption = msi.getSkillStatIntValue(y);
                mts.addMobSkillOptionsAndBroadCast(MobStat.PCounter, o);
                break;
            case Magnet:
                o.nOption = msi.getSkillStatIntValue(x);
                for (Char c : chars) {
                    TemporaryStatManager tsm = c.getTemporaryStatManager();
                    tsm.putCharacterStatValue(CharacterTemporaryStat.Magnet, o);
                    tsm.sendSetStatPacket();
                }
                break;
            case Summon:
            case Summon2:
                Position spawnPos = mob.getPosition();
                if (msi.getLt() != null) {
                    rect = new Rect(msi.getLt(), msi.getRb());
                    spawnPos = rect.getRandomPositionInside();
                }
                Set<Mob> spawnedMobs = field.getMobs().stream()
                        .filter(m -> m.getMobSpawnerId() == mob.getObjectId())
                        .collect(Collectors.toSet());
                for (int mobId : msi.getInts()) {
                    long spawnedSize = spawnedMobs.stream().filter(m -> m.getTemplateId() == mobId).count();
                    int maxSpawned = msi.getSkillStatIntValue(limit);
                    if (maxSpawned == 0) {
                        maxSpawned = 100;
                    }
                    if (spawnedSize < maxSpawned) {
                        Mob m = mob.getField().spawnMob(mobId, spawnPos.getX(), spawnPos.getY(), false, 0);
                        m.setMobSpawnerId(mob.getObjectId());
                    }
                }
                if (afterDead) {
                    // maybe grab animation from wz, so far it's just -2 (no ani)
                    field.broadcastPacket(MobPool.leaveField(mob.getObjectId(), DeathType.NO_ANIMATION_DEATH));
                    field.removeLife(mob.getObjectId());
                    mob.setNextSummonPossible(GameConstants.MOB_RESUMMON_COOLDOWN);
                }
                break;
            case CastingBar:
                field.broadcastPacket(MobPool.castingBarSkillStart(1,
                        msi.getSkillStatIntValue(castingTime), false, false));
                break;
            case ObstacleAttack:
                xPos = msi.getSkillStatIntValue(x2); // Starting Position  offset from mob.getPositions()
                int yPos = msi.getSkillStatIntValue(y2); // Starting Position  offset from mob.getPositions()
                int mobTemplateId = msi.getSkillStatIntValue(x); // why?
                skillAfter = msi.getSkillStatIntValue(MobSkillStat.skillAfter);
                Position position = new Position(mob.getX() + xPos, mob.getY() + yPos);
                switch (getLevel()) {
                    case 2: // Demian Phase 1
                    case 4: // Demian Phase 2
                        ObtacleAtomEnum oae = getLevel() == 2 ? ObtacleAtomEnum.DemianYellowOrb : ObtacleAtomEnum.DemianRedOrb;
                        ObtacleAtomCreateType oact = ObtacleAtomCreateType.RADIAL;
                        ObtacleRadianInfo ori = new ObtacleRadianInfo(mobTemplateId, position.getX(), position.getY(), position.getX(), 0);
                        Set<ObtacleAtomInfo> obtacleAtomInfos = new HashSet<>();
                        int atomsCreated = getLevel() == 2 ? 50 : 200;
                        for (int i = 0; i < atomsCreated; i++) {
                            Position endPos = new Position(mob.getX() + new Random().nextInt(1600) - 800, mob.getY());
                            ObtacleAtomInfo oai = new ObtacleAtomInfo(oae.getType(), 1, position, endPos,
                                    oae.getHitBox(), 90, 0, 1000 + (i * 100), yPos, 100, 2, (int) ObtacleAtomInfo.getLengthByPositions(position, endPos) - 50,
                                    0);
                            obtacleAtomInfos.add(oai);
                        }
                        field.broadcastPacket(FieldPacket.createObtacle(oact, null, ori, obtacleAtomInfos));
                        break;
                    default:
                        log.warn(String.format("[MobSkill::applyEffect] Unhandled ObstacleAttack, slv = %d", getLevel()));
                        break;
                }
                break;
            case BounceAttack:
                mob.getField().broadcastPacket(MobPool.createBounceAttackSkill(mob, msi, false));
                break;
            case LaserAttack:
                if (!mts.hasCurrentMobStat(MobStat.Laser)) {
                    o.nOption = 1;
                    o.wOption = msi.getSkillStatIntValue(w);
                    o.uOption = msi.getSkillStatIntValue(z);
                    mts.addMobSkillOptionsAndBroadCast(MobStat.Laser, o);
                }
                break;
            case LaserControl:
                // Not needed? Automatically handled well by the controller
                break;
            case RandomAngleLucidAttack:
                LucidSkillType lucidSkillType = LucidSkillType.getSkillByVal(getLevel());
                switch (lucidSkillType) {
                    case FlowerTrap1:
                    case FlowerTrap2:
                    case FlowerTrap3:
                        field.broadcastPacket(FieldPacket.doLucidSkillFlowerTrap(mob.getPosition(), new Random().nextInt(3), new Random().nextBoolean()));
                        break;
                    case FairyDust:
                    case FairyDust2:
                        List<Object> fairyDustList = new ArrayList<>();
                        int numberOfFairyDust = msi.getSkillStatIntValue(w) - new Random().nextInt(msi.getSkillStatIntValue(w2));
                        for (int i = 0; i < numberOfFairyDust; i++) {
                            int v2val = msi.getSkillStatIntValue(v2);
                            if (v2val > 0) {
                                v2val = Util.getRandom(v2val);
                            }
                            int sVal = msi.getSkillStatIntValue(s);
                            if (sVal > 0) {
                                sVal = Util.getRandom(sVal);
                            }
                                fairyDustList.add(new FairyDust(
                                    new Random().nextInt(3),
                                    msi.getSkillStatIntValue(u),
                                    msi.getSkillStatIntValue(v) + v2val,
                                    msi.getSkillStatIntValue(s2) - sVal)
                            );
                        }
                        field.broadcastPacket(FieldPacket.doLucidSkillFairyDust(fairyDustList));
                        break;
                    case LaserRain:
                        List<Object> laserIntervalList =  new ArrayList<Object>() {
                            {
                                for (int i = 0; i < msi.getSkillStatIntValue(z); i++) {
                                    add(500);
                                }
                            }
                        };
                        field.broadcastPacket(FieldPacket.doLucidSkillLaserRain(laserIntervalList, msi.getSkillStatIntValue(s)));
                        break;
                    case Teleport:
                        field.broadcastPacket(FieldPacket.doLucidSkillTeleport(new Random().nextInt(8)));
                        break;
                    case Dragon:
                        boolean isLeft = new Random().nextBoolean();
                        if (field.getId() == 450004150) {
                            field.broadcastPacket(FieldPacket.lucidDragonCreated(1, new Position(), new Position(), isLeft));
                        } else {
                            int createPosX = isLeft ? -138 : 1498;
                            int createPosY = (new Random().nextBoolean() ? -1312 : 238);
                            int posX = createPosX;
                            int posY = mob.getY();
                            field.broadcastPacket(FieldPacket.lucidDragonCreated(2, new Position(posX, posY), new Position(createPosX, createPosY), isLeft));
                        }
                        break;
                    case Rush:
                        field.broadcastPacket(FieldPacket.doLucidSkillRush());
                        break;
                    case WelcomeBarrage:
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(2));
                        field.broadcastPacket(FieldPacket.lucidStainedGlassOnOff(false, Arrays.asList("Bblue1", "Bblue2", "Bblue3", "Bred1", "Bred2", "Bred3", "Mred2", "Mred3", "Myellow1","Myellow2", "Myellow3")));
                        field.broadcastPacket(FieldPacket.lucidFlyingMode(true));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(3, 50, 120, 500, 3));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(4, 180, 150, 30, 30, 700, 12, 5, 1));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(3, 50, 100, 500, 4));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(4, 180, 150, 30, 70, 1000, 12, 10, 0));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(3, 50, 90, 700, 8));
                        field.broadcastPacket(FieldPacket.lucidWelcomeBarrage(4, 180, 100, 30, 100, 700, 12, 0, 0));
                        // TODO
                        break;
                }
                break;
            case Unk:
                log.warn(String.format("[MobSkill::applyEffect] Unknown mob skillID %d, slv = %d", skill, slv));
                break;
            default:
                log.warn(String.format("[MobSkill::applyEffect] Unhandled mob skillID %s, slv = %d", msID, getLevel()));
                break;
        }
    }

    public void applyEffect(Char chr) {
        short skill = (short) getSkillID();
        if (skill == 0) {
            skill = (short) getDisease();
        }
        short level = (short) getLevel();
        MobSkillInfo msi = SkillData.getMobSkillInfoByIdAndLevel(skill, level);
        MobSkillID msID = MobSkillID.getMobSkillIDByVal(skill);
        Field field = chr.getField();
        Option o = new Option(skill);
        o.slv = level;
        o.tOption = msi.getSkillStatIntValue(time);
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        boolean appliedDisease = Util.succeedProp(msi.getSkillStatIntValue(prop))
                    && Util.succeedProp(100 - chr.getTotalStat(BaseStat.asr));
        switch (msID) {
            case Seal:
            case Darkness:
            case Weakness:
            case Stun:
            case Curse:
            case Slow:
            case Fear:
                if (appliedDisease) {
                    o.nOption = 1;
                    tsm.putCharacterStatValueFromMobSkill(msID.getAffectedCTS(), o);
                    tsm.sendSetStatPacket();
                }
                break;
            case PainMark:
            case Poison:
            case UserMorph:
                if (appliedDisease) {
                    o.nOption = msi.getSkillStatIntValue(x);
                    tsm.putCharacterStatValueFromMobSkill(msID.getAffectedCTS(), o);
                    tsm.sendSetStatPacket();
                }
                break;
            case Undead:
                if (appliedDisease) {
                    o.nOption = 1;
                    tsm.putCharacterStatValueFromMobSkill(CharacterTemporaryStat.Undead, o);
                    TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.Undead);
                    tsb.setNOption(o.nOption);
                    tsb.setROption(skill << level | 16);
                    tsb.setExpireTerm(o.tOption);
                    tsm.sendSetStatPacket();
                }
                break;
            case Unk:
                log.warn(String.format("[MobSkill::applyEffect(Char)] Unknown mob skillID %d, slv = %d", skill, level));
                break;
            default:
                log.warn(String.format("[MobSkill::applyEffect(Char)] Unhandled mob skillID %s, slv = %d", msID, getLevel()));
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobSkill mobSkill = (MobSkill) o;
        return skillSN == mobSkill.skillSN &&
                skillID == mobSkill.skillID &&
                level == mobSkill.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillSN, skillID, level);
    }

    public int getDisease() {
        return disease;
    }

    public void setDisease(int disease) {
        this.disease = disease;
    }
}
