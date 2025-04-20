package net.swordie.ms.client.jobs.adventurer.thief;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AssistType;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class NightLord extends Thief {
    public static final int ASSASSINS_MARK = 4101011; //Buff (ON/OFF)
    public static final int ASSASSIN_MARK_ATOM = 4100012;
    public static final int NIGHTLORD_MARK_ATOM = 4120019;
    public static final int CLAW_BOOSTER = 4101003; //Buff


    public static final int SHADOW_STARS = 4111009; //Buff
    public static final int SHADOW_PARTNER_NL = 4111002; //Buff
    public static final int EXPERT_THROWING_STAR_HANDLING = 4110012;
    public static final int DARK_FLARE_NL = 4111007; //Summon
    public static final int SHADOW_WEB = 4111003; //Special Attack (Dot + Bind)
    public static final int VENOM_NL = 4110011; //Passive DoT


    public static final int MAPLE_WARRIOR_NL = 4121000; //Buff
    public static final int SHOWDOWN = 4121017; //Special Attack
    public static final int SHOWDOWN_ENHANCE = 4120045;
    public static final int SUDDEN_RAID_NL = 4121016; //Special Attack
    public static final int FRAILTY_CURSE = 4121015; //AoE
    public static final int FRAILTY_CURSE_SLOW = 4120047;
    public static final int FRAILTY_CURSE_ENHANCE = 4120046;
    public static final int FRAILTY_CURSE_BOSS_RUSH = 4120048;
    public static final int NIGHT_LORD_MARK = 4120018;
    public static final int TOXIC_VENOM_NL = 4120011; //Passive DoT
    public static final int HEROS_WILL_NL = 4121009;
    public static final int BLEED_DART = 4121054;
    public static final int EPIC_ADVENTURE_NL = 4121053;


    // V skills
    public static final int DARK_LORDS_OMEN = 400041038;
    public static final int THROWING_STAR_BARRAGE = 400041001;

    public NightLord(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isNightLord(id);
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
        if (!SkillConstants.isSummon(attackInfo.skillId) && hasHitMobs) {

            // NightLord's Mark & ForceAtom
            if (chr.hasSkill(ASSASSINS_MARK)
                    && attackInfo.skillId != 400041016
                    && attackInfo.skillId != 400041017
                    && attackInfo.skillId != 400041018) {
                setMarkonMob(attackInfo);
                if (!SkillConstants.isForceAtomSkill(attackInfo.skillId)) {
                    handleMark(attackInfo);
                }
            }
            // Expert Throwing Star Handling
            if (!SkillConstants.isForceAtomSkill(attackInfo.skillId) && chr.hasSkill(EXPERT_THROWING_STAR_HANDLING) && Util.succeedProp(chr.getSkillStatValue(prop, EXPERT_THROWING_STAR_HANDLING))) {
                chr.write(UserLocal.setNextShootExJablin());
            }
            // Night Lord Hyper
            applyBleedDartOnMob(attackInfo);

        }


        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case SHADOW_WEB:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        mts.createAndAddBurnedInfo(chr, skill);
                    }
                }
                break;
            case SHOWDOWN:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (!mob.isBoss()) {
                        mts.addStatOptionsAndBroadcast(MobStat.Showdown, o1);
                    }

                    int bonus = si.getValue(x, slv) + chr.getSkillStatValue(x, SHOWDOWN_ENHANCE);
                    o2.xOption = mob.isBoss() ? bonus / 2 : bonus; // Exp
                    o2.yOption = mob.isBoss() ? bonus / 2 : bonus; // Item Drop
                    mts.addStatOptionsAndBroadcast(MobStat.Treasure, o2);
                }
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }

    public void createDarkLordOmenForceAtoms(Position position) {
        Skill skill = chr.getSkill(DARK_LORDS_OMEN);
        if (skill == null) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = chr.getSkillLevel(skill.getSkillId());

        Rect rect = position.getRectAround(si.getFirstRect());
        ForceAtomEnum fae = chr.hasSkill(NIGHT_LORD_MARK) ? ForceAtomEnum.DARK_LORD_OMEN_2 : ForceAtomEnum.DARK_LORD_OMEN;
        int mobCountInRect = chr.getField().getMobsInRect(rect).size();
        int forceAtomCount = ((mobCountInRect * si.getValue(bulletCount, slv)) + si.getValue(x, slv));
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        for (int i = 0; i < forceAtomCount; i++) {
            int angle = (360 / forceAtomCount) * i;
            Mob mob = Util.getRandomFromCollection(chr.getField().getMobs());
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 30, 4,
                    angle, 0, Util.getCurrentTime(), 1, 0,
                    new Position());
            targetList.add(mob != null ? mob.getObjectId() : 0);
            faiList.add(forceAtomInfo);
        }
        Summon darkOmenSummon = chr.getField().getSummonByChrAndSkillId(chr, DARK_LORDS_OMEN);
        chr.createForceAtom(new ForceAtom(false, darkOmenSummon.getObjectId(), chr.getId(), fae,
                true, targetList, DARK_LORDS_OMEN, faiList, rect, 0, 0,
                position, chr.getBulletIDForAttack(), position, 0));
    }

    private void handleMark(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (getMarkSkill() == null || !tsm.hasStat(NightLordMark)) {
            return;
        }
        Field field = chr.getField();
        Skill skill = getMarkSkill();
        Rect rect = new Rect(-250, -250, 250, 250);
        int starCount = chr.getSkillStatValue(bulletCount, skill.getSkillId());

        ForceAtomEnum fae = ForceAtomEnum.ASSASSIN_MARK;
        int atom = ASSASSIN_MARK_ATOM;
        if (chr.hasSkill(NIGHT_LORD_MARK)) {
            fae = ForceAtomEnum.NIGHTLORD_MARK;
            atom = NIGHTLORD_MARK_ATOM;
        }

        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            int randomInt = new Random().nextInt((360 / starCount) - 1);
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            MobTemporaryStat mts = mob.getTemporaryStat();

            if (mts.hasBurnFromSkillAndOwner(skill.getSkillId(), chr.getId())) {
                List<Integer> targetList = new ArrayList<>();
                List<ForceAtomInfo> faiList = new ArrayList<>();
                for (int i = 0; i < starCount; i++) {
                    Mob targetMob;
                    if (field.getBossMobsInRect(mob.getRectAround(rect)).size() > 0) {
                        targetMob = Util.getRandomFromCollection(field.getBossMobsInRect(mob.getRectAround(rect)));
                    } else if (field.getMobsInRect(mob.getRectAround(rect)).size() > 0) {
                        targetMob = Util.getRandomFromCollection(field.getMobsInRect(mob.getRectAround(rect)));
                    } else {
                        targetMob = null;
                    }
                    targetList.add(targetMob == null ? 0 : targetMob.getObjectId());

                    int angle = (360 / starCount) * i;
                    ForceAtomInfo fai = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 40, 4,
                            randomInt + angle, 170, Util.getCurrentTime(), 0, 0,
                            new Position());
                    faiList.add(fai);
                }
                chr.createForceAtom(new ForceAtom(true, chr.getId(), mob.getObjectId(), fae,
                        true, targetList, atom, faiList, mob.getRectAround(rect), 0, 300,
                        new Position(), chr.getBulletIDForAttack(), new Position(), 0));
            }
        }
    }

    private Skill getMarkSkill() {
        Skill skill = null;
        if (chr.hasSkill(ASSASSINS_MARK)) {
            skill = chr.getSkill(ASSASSINS_MARK);
        }
        if (chr.hasSkill(NIGHT_LORD_MARK)) {
            skill = chr.getSkill(NIGHT_LORD_MARK);
        }
        return skill;
    }

    private void setMarkonMob(AttackInfo attackInfo) {
        Skill skill = getMarkSkill();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int markprop = si.getValue(prop, slv);
        if (tsm.hasStat(NightLordMark)) {
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                if (Util.succeedProp(markprop)) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.createAndAddBurnedInfo(chr, skill);
                }
            }
        }
    }

    private void applyBleedDartOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(BleedingToxin)) {
            Skill skill = chr.getSkill(BLEED_DART);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                MobTemporaryStat mts = mob.getTemporaryStat();
                mts.createAndAddBurnedInfo(chr, skill);
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
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        }
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (skillID) {
            case FRAILTY_CURSE:
                SkillInfo fci = SkillData.getSkillInfoById(skillID);
                AffectedArea aa2 = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa2.setPosition(chr.getPosition());
                aa2.setRect(aa2.getPosition().getRectAround(fci.getFirstRect()));
                aa2.setFlip(!chr.isLeft());
                aa2.setDelay((short) 9);
                chr.getField().spawnAffectedArea(aa2);
                break;

            case ASSASSINS_MARK:
                if (tsm.hasStat(NightLordMark)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = 1;
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(NightLordMark, o1);
                }
                break;
            case SHADOW_STARS:
                break;
            case BLEED_DART:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BleedingToxin, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case THROWING_STAR_BARRAGE:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SpreadThrow, o1);
                break;
            case DARK_LORDS_OMEN:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(false);
                summon.setAttackActive(true);
                summon.setMoveAbility(MoveAbility.Stop);
                summon.setAssistType(AssistType.CreateForceAtom);
                chr.getField().spawnSummon(summon);
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
//                handleJobAdvance(JobConstants.JobEnum.HERMIT.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.NIGHT_LORD.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {

        super.cancelTimers();
    }
}
