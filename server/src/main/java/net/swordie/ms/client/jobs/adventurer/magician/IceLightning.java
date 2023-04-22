package net.swordie.ms.client.jobs.adventurer.magician;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class IceLightning extends Magician {
    public static final int COLD_BEAM = 2201008;
    public static final int FREEZING_CRUSH = 2200011;
    public static final int FROST_CLUTCH = 2220015;
    public static final int MP_EATER_IL = 2200000;
    public static final int MAGIC_BOOSTER_IL = 2201010;
    public static final int CHILLING_STEP = 2201009;
    public static final int MEDITATION_IL = 2201001;


    public static final int ICE_STRIKE = 2211002;
    public static final int GLACIER_CHAIN = 2211010;
    public static final int TELEPORT_MASTERY_IL = 2211007;
    public static final int ELEMENTAL_DECREASE_IL = 2211008;


    public static final int CHAIN_LIGHTNING = 2221006;
    public static final int FREEZING_BREATH = 2221011;
    public static final int BLIZZARD = 2221007;
    public static final int BLIZZARD_FA = 2220014;
    public static final int FROZEN_ORB = 2221012;
    public static final int INFINITY_IL = 2221004;
    public static final int ELQUINES = 2221005;
    public static final int ARCANE_AIM_IL = 2220010;
    public static final int MAPLE_WARRIOR_IL = 2221000;
    public static final int HEROS_WILL_IL = 2221008;
    public static final int ELEMENTAL_ADAPTATION_IL = 2211012;
    public static final int THUNDER_STORM = 2211011;
    public static final int TELEPORT_MASTERY_RANGE_IL = 2221045;
    public static final int EPIC_ADVENTURE_IL = 2221053;
    public static final int ABSOLUTE_ZERO_AURA = 2221054;


    // V skills
    public static final int ICE_AGE = 400021002;
    public static final int ICE_AGE_TILE = 400020002;
    public static final int BOLT_BARRAGE = 400021030;
    public static final int BOLT_BARRAGE_TILE = 400021040;
    public static final int BOLT_BARRAGE_TILE_2 = 400021031;
    public static final int SNOW_OF_CREATION = 400021067;


    public static final List<Integer> unreliableMemIL = new ArrayList<Integer>() {{
                add(2001008);
                add(2201008);
                add(2201005);
                add(2211002);
                add(2211010);
                add(2211011);
                add(2221006);
                add(2221007);
                add(2221012);
                add(2221004);
                add(2221005);
                add(2221052);
                add(2221053);
    }};

    public IceLightning(Char chr) {
        super(chr);
    }


    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isIceLightning(id);
    }

    private void applyFreezeByAbsoluteZeroAura() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!tsm.hasStat(IceAura)) {
            return;
        }
        Skill skill = chr.getSkill(ABSOLUTE_ZERO_AURA);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
        Option o = new Option();
        o.nOption = 1;
        o.rOption = ICE_STRIKE;
        o.tOption = 8; // no time value given
        List<Mob> mobList = chr.getField().getMobsInRect(rect);
        for (Mob mob : mobList) {
            if (mob == null || mob.isBoss()) {
                return;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            mts.addStatOptionsAndBroadcast(MobStat.Freeze, o);
        }
        EventManager.addEvent(this::applyFreezeByAbsoluteZeroAura, 4, TimeUnit.SECONDS);
    }


    public void handleKeyDownSkill(Char chr, int skillID, InPacket inPacket) {
        super.handleKeyDownSkill(chr, skillID, inPacket);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        Skill skill = chr.getSkill(skillID);
        int skillId = 0;
        SkillInfo si = null;
        int slv = 0;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skill.getSkillId());
            slv = skill.getCurrentLevel();
            skillId = skill.getSkillId();
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillId) {
        }
    }

    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
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
            slv = skill.getCurrentLevel();
            skillID = skill.getSkillId();
        }
        //Freezing Crush / Frozen Clutch
        applyFreezingCrushOnMob(attackInfo, attackInfo.skillId);

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {

            case ELQUINES:
                o1.nOption = 5;
                o1.rOption = skillID;
                o1.tOption = 5;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null || mob.isBoss()) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                }
                break;
            case TELEPORT_MASTERY_IL:
            case CHAIN_LIGHTNING:
            case Bishop.SHINING_RAY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case FREEZING_BREATH:
                Rect rect = chr.getPosition().getRectAround(si.getFirstRect());
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);

                o2.nOption = -si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);

                o3.nOption = -si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                for (Life life : chr.getField().getLifesInRect(rect)) {
                    if (life instanceof Mob && ((Mob) life).getHp() > 0) {
                        Mob mob = (Mob) life;
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Freeze, o1);
                        mts.addStatOptionsAndBroadcast(MobStat.PDR, o2);
                        mts.addStatOptionsAndBroadcast(MobStat.MDR, o3);
                    }
                }
                if (tsm.getOptByCTSAndSkill(NotDamaged, skillID) == null) {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(NotDamaged, o1);
                    tsm.sendSetStatPacket();
                }
                break;
            case ICE_AGE:
                rect = chr.getRectAround(si.getFirstRect());
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                for (Foothold fh : chr.getField().getFootholdsInRect(rect)) {
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setSkillID(ICE_AGE_TILE);
                    aa.setPosition(fh.getRandomPosition());
                    aa.setDelay((short) 8);
                    aa.setRect(new Rect(fh.getX1(), aa.getPosition().getY() - 50, fh.getX2(), aa.getPosition().getY() + 20));
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
            case SNOW_OF_CREATION:
                applyFreezingCrushOnMob(attackInfo, attackInfo.skillId);
                applyFreezingCrushOnMob(attackInfo, attackInfo.skillId);
                break;
            case BOLT_BARRAGE_TILE:
            case BOLT_BARRAGE_TILE_2:
                Life life = chr.getField().getLifeByObjectID(attackInfo.affectedAreaObjId);
                if (!(life instanceof AffectedArea)) {
                    return;
                }
                chr.getField().removeLife(life);
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    private void applyFreezingCrushOnMob(AttackInfo attackInfo, int skillID) {
        if (!chr.hasSkill(FREEZING_CRUSH)) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skillID);
        int slv = chr.getSkillLevel(skillID);
        if (si != null && (si.getElemAttr().contains("i") || si.getElemAttr().contains("l"))) {
            Option o1 = new Option();
            o1.rOption = COLD_BEAM;
            o1.tOption = si.getValue(time, slv);
            boolean isIceSkill = si.getElemAttr().contains("i");
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();

                int counter = 1;
                if (mts.hasCurrentMobStat(MobStat.Speed)) {
                    counter = mts.getCurrentOptionsByMobStat(MobStat.Speed).mOption;
                    if (counter < 5 && isIceSkill) {
                        counter++;
                    } else if (counter > 0 && !isIceSkill) {
                        counter--;
                    }
                }
                o1.nOption = -15 * counter;
                o1.mOption = counter;
                mts.addStatOptionsAndBroadcast(MobStat.Speed, o1);
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        SkillInfo si = SkillData.getSkillInfoById(BLIZZARD_FA);
        if (chr.getSkill(BLIZZARD) != null && chr.hasSkillOnCooldown(BLIZZARD)) {
            int slv = chr.getSkill(BLIZZARD).getCurrentLevel();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                return BLIZZARD_FA;
            }
        }
        return 0;
    }

    // Skill related methods -------------------------------------------------------------------------------------------

    @Override
    public void handleSkill(Char chr, TemporaryStatManager tsm, int skillID, int slv, InPacket inPacket, SkillUseInfo skillUseInfo) {
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        }
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case ELEMENTAL_ADAPTATION_IL:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(AntiMagicShell, o1);
                break;
            case THUNDER_STORM:
                Summon summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(true);
                field.spawnSummon(summon);
                break;
            case CHILLING_STEP:
                if (tsm.hasStat(ChillingStep)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(ChillingStep, o1);
                }
                break;
            case ABSOLUTE_ZERO_AURA:
                if (tsm.hasStat(IceAura)) {
                    tsm.removeStatsBySkill(skillID);
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(IceAura, o1);
                    o2.nOption = si.getValue(x, slv);
                    o2.rOption = skillID;
                    tsm.putCharacterStatValue(Stance, o2);
                    o3.nOption = si.getValue(y, slv);
                    o3.rOption = skillID;
                    tsm.putCharacterStatValue(DamAbsorbShield, o3);
                    o4.nOption = si.getValue(v, slv);
                    o4.rOption = skillID;
                    tsm.putCharacterStatValue(AsrR, o4);
                    tsm.putCharacterStatValue(TerR, o4);
                    applyFreezeByAbsoluteZeroAura();
                }
                break;
            case SNOW_OF_CREATION:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case BOLT_BARRAGE:
                Position chrPos = inPacket.decodePosition();
                byte isLeft = inPacket.decodeByte();
                Position[] positions = new Position[inPacket.decodeInt()]; // amount of Positions

                int duration = 3000; // ms
                int delay = 2;
                int distancePerBolt = 200;
                for (int i = 0; i < positions.length; i++) {
                    AffectedArea aa = AffectedArea.getPassiveAA(chr, BOLT_BARRAGE_TILE, slv);
                    aa.setPosition(inPacket.decodePositionInt());
                    aa.setRect(aa.getRectAround(SkillData.getSkillInfoById(BOLT_BARRAGE_TILE).getFirstRect()));
                    aa.setDelay((short) (delay * i));
                    aa.setDuration(duration + (i * distancePerBolt));
                    field.spawnAffectedArea(aa);
                }
                break;
        }
        tsm.sendSetStatPacket();
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        if (skill != null) {
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();

            switch (skillId) {
            }
        }
        return super.alterCooldownSkill(skillId);
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
//        short level = chr.getLevel();
//        switch (level) {
//            case 60:
//                handleJobAdvance(JobConstants.JobEnum.IL_MAGE.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.IL_ARCHMAGE.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {
        super.cancelTimers();
    }
}
