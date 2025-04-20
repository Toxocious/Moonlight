package net.swordie.ms.client.jobs.adventurer.magician;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.adventurer.Beginner;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.UserRemote;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.MoveAbility;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.Collections;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class Magician extends Beginner {
    //Common
    public static final int TELEPORT = 2001009;
    public static final int MAGIC_GUARD = 2001002;


    // V skills
    public static final int UNRELIABLE_MEMORY = 400001021;


    private ScheduledFuture infinityTimer;


    public Magician(Char chr) {
        super(chr);
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return id == JobConstants.JobEnum.MAGICIAN.getJobId();
    }

    private void infinity(int skillId) {
        if (!chr.hasSkill(skillId)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = chr.getSkill(skillId);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        if (tsm.hasStat(Infinity)) {
            Option o1 = new Option();
            o1.nOption = tsm.getOption(Infinity).nOption + 1;
            o1.rOption = skillId;
            o1.tOption = (int) tsm.getRemainingTime(Infinity, skillId);
            o1.setInMillis(true);
            tsm.putCharacterStatValue(Infinity, o1, true);
            tsm.sendSetStatPacket();

            chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(y, slv))));
            chr.healMP((int) (chr.getMaxMP() / ((double) 100 / si.getValue(y, slv))));

            infinityTimer = EventManager.addEvent(() -> infinity(skillId), 5, TimeUnit.SECONDS);
        } else {
            tsm.removeStatsBySkill(skillId);
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
            incrementArcaneAim();
            mpEaterEffect(attackInfo);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {

        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }


    private void incrementArcaneAim() {
        Skill skill = chr.getSkill(getArcaneAimSkill());
        if (skill == null) {
            return;
        }
        SkillInfo arcaneAimInfo = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int arcaneAimProp = arcaneAimInfo.getValue(prop, slv);
        if (!Util.succeedProp(arcaneAimProp)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        int amount = 1;
        if (tsm.hasStat(ArcaneAim)) {
            amount = tsm.getOption(ArcaneAim).nOption;
            if (amount < arcaneAimInfo.getValue(y, slv)) {
                amount++;
            }
        }
        o.nOption = amount;
        o.rOption = FirePoison.ARCANE_AIM_FP;
        o.tOption = 5; // No Time Variable
        tsm.putCharacterStatValue(ArcaneAim, o);
        o1.nReason = FirePoison.ARCANE_AIM_FP;
        o1.nValue = arcaneAimInfo.getValue(ignoreMobpdpR, slv);
        o1.tTerm = 5; // No Time Variable
        tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
        o2.nReason = FirePoison.ARCANE_AIM_FP;
        o2.nValue = (amount * arcaneAimInfo.getValue(x, slv));
        o2.tTerm = 5; // No Time Variable
        tsm.putCharacterStatValue(IndieDamR, o2);
        tsm.sendSetStatPacket();
    }

    private int getArcaneAimSkill() {
        int res = 0;
        if (chr.hasSkill(FirePoison.ARCANE_AIM_FP)) {
            res = FirePoison.ARCANE_AIM_FP;
        } else if (chr.hasSkill(IceLightning.ARCANE_AIM_IL)) {
            res = IceLightning.ARCANE_AIM_IL;
        } else if (chr.hasSkill(Bishop.ARCANE_AIM_BISH)) {
            res = Bishop.ARCANE_AIM_BISH;
        }
        return res;
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
            case TELEPORT:
                if (chr.hasSkill(IceLightning.CHILLING_STEP)) {
                    createChillStepAA();
                }
                break;
            case FirePoison.HEROS_WILL_FP:
            case IceLightning.HEROS_WILL_IL:
            case Bishop.HEROS_WILL_BISH:
                tsm.removeAllDebuffs();
                break;
            case UNRELIABLE_MEMORY:
                chr.write(UserLocal.skillRequestRequest(Util.getRandomFromCollection(
                        JobConstants.isFirePoison(chr.getJob()) ? FirePoison.unreliableMemFP :
                        JobConstants.isIceLightning(chr.getJob()) ? IceLightning.unreliableMemIL :
                        JobConstants.isBishop(chr.getJob()) ? Bishop.unreliableMemBishop :
                            Collections.singleton(1))));
                break;
            case MAGIC_GUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(MagicGuard, o1);
                break;
            case FirePoison.MAGIC_BOOSTER_FP:
            case IceLightning.MAGIC_BOOSTER_IL:
            case Bishop.MAGIC_BOOSTER_BISH:
                o1.nValue = -5; //si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case FirePoison.MEDITATION_FP:
            case IceLightning.MEDITATION_IL:
                o1.nValue = si.getValue(indieMad, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                break;
            case FirePoison.ELEMENTAL_DECREASE_FP:
            case IceLightning.ELEMENTAL_DECREASE_IL:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ElementalReset, o1);
                break;
            case FirePoison.TELEPORT_MASTERY_FP:
            case IceLightning.TELEPORT_MASTERY_IL:
            case IceLightning.TELEPORT_MASTERY_RANGE_IL:
            case Bishop.TELEPORT_MASTERY_BISH:
                CharacterTemporaryStat masteryStat = skillID == IceLightning.TELEPORT_MASTERY_RANGE_IL ? TeleportMasteryRange : TeleportMasteryOn;
                if (tsm.hasStat(masteryStat)) {
                    tsm.removeStatsBySkill(skillID);
                    tsm.sendResetStatPacket();
                } else {
                    o1.nOption = si.getValue(x, slv);
                    o1.rOption = skillID;
                    tsm.putCharacterStatValue(masteryStat, o1);
                }
                break;
            case FirePoison.INFINITY_FP:
            case IceLightning.INFINITY_IL:
            case Bishop.INFINITY_BISH:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Infinity, o1);
                o2.nOption = si.getValue(prop, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Stance, o2);
                if (infinityTimer != null && !infinityTimer.isDone()) {
                    infinityTimer.cancel(true);
                }
                EventManager.addEvent(() -> infinity(skillID), 5, TimeUnit.SECONDS);
                break;
            case FirePoison.IFRIT:
            case IceLightning.ELQUINES:
            case Bishop.BAHAMUT:
                Summon summon = Summon.getSummonBy(chr, skillID, slv);
                summon.setFlyMob(true);
                summon.setMoveAbility(MoveAbility.Walk);
                Field field = chr.getField();
                field.spawnSummon(summon);
                break;
            case FirePoison.MAPLE_WARRIOR_FP:
            case IceLightning.MAPLE_WARRIOR_IL:
            case Bishop.MAPLE_WARRIOR_BISH:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FirePoison.EPIC_ADVENTURE_FP:
            case IceLightning.EPIC_ADVENTURE_IL:
            case Bishop.EPIC_ADVENTURE_BISH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void handleShootObj(Char chr, int skillId, int slv) {
        super.handleShootObj(chr, skillId, slv);
    }

    public int alterCooldownSkill(int skillid) {
        switch (skillid) {
        }

        return super.alterCooldownSkill(skillid);
    }

    public void handleCancelKeyDownSkill(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {
            case IceLightning.FREEZING_BREATH:
                tsm.removeStatsBySkill(skillID);
                break;

            default:
                super.handleCancelKeyDownSkill(chr, skillID);
        }
    }

    private void mpEaterEffect(AttackInfo attackInfo) {
        Skill skill = getMPEaterSkill();
        if (skill == null || attackInfo.skillId == 0) {
            return;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int mpStolen = si.getValue(x, slv);
        boolean showedEffect = false;
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            if (Util.succeedProp(si.getValue(prop, slv))) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null || mob.getMp() <= 0) {
                    continue;
                }
                long mobMaxMP = mob.getMaxMp();
                int mpAbsorbed = (int) (mobMaxMP * ((double) mpStolen / 100));
                mob.healMP(-(mpAbsorbed > 500 ? 500 : mpAbsorbed));
                chr.healMP(mpAbsorbed > 500 ? 500 : mpAbsorbed);

                if (!showedEffect) {
                    showedEffect = true;
                    chr.write(UserPacket.effect(Effect.skillUse(getMPEaterSkill().getSkillId(), chr.getLevel(), slv, 0)));
                    chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillUse(getMPEaterSkill().getSkillId(), chr.getLevel(), slv, 0)));
                }
            }
        }
    }

    private Skill getMPEaterSkill() {
        Skill skill = null;
        if (chr.hasSkill(FirePoison.MP_EATER_FP)) {
            skill = chr.getSkill(FirePoison.MP_EATER_FP);

        } else if (chr.hasSkill(IceLightning.MP_EATER_IL)) {
            skill = chr.getSkill(IceLightning.MP_EATER_IL);

        } else if (chr.hasSkill(Bishop.MP_EATER_BISHOP)) {
            skill = chr.getSkill(Bishop.MP_EATER_BISHOP);
        }
        return skill;
    }

    private void createChillStepAA() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        SkillInfo chillingStepInfo = SkillData.getSkillInfoById(IceLightning.CHILLING_STEP);
        int slv = chr.getSkill(IceLightning.CHILLING_STEP).getCurrentLevel();
        if (tsm.hasStat(ChillingStep) && Util.succeedProp(chillingStepInfo.getValue(prop, slv))) {
            for (int i = 0; i < 168; i += 56) {
                AffectedArea aa = AffectedArea.getPassiveAA(chr, IceLightning.CHILLING_STEP, (byte) slv);
                int x = chr.isLeft() ? chr.getPosition().getX() - i : chr.getPosition().getX() + i;
                int y = chr.getPosition().getY();
                aa.setPosition(new Position(x, y));
                aa.setRect(aa.getPosition().getRectAround(chillingStepInfo.getRects().get(0)));
                aa.setCurFoothold();
                aa.setDelay((short) 4);
                aa.setSkillID(IceLightning.CHILLING_STEP);
                aa.setRemoveSkill(false);
                chr.getField().spawnAffectedAreaAndRemoveOld(aa);
                //chr.getField().RemoveOldAffectedArea(aa);
            }
        }
    }

    public void handleRemoveCTS(CharacterTemporaryStat cts) {
        super.handleRemoveCTS(cts);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        super.handleHit(c, inPacket, hitInfo);
    }

    public void handleMobDebuffSkill(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        // Elemental Adaptation - FP
        if (chr.hasSkill(FirePoison.ELEMENTAL_ADAPTATION_FP) && tsm.getOptByCTSAndSkill(AntiMagicShell, FirePoison.ELEMENTAL_ADAPTATION_FP) != null) {
            deductEleAdaptationFP();
            tsm.removeAllDebuffs();
        }

        // Elemental Adaptation - IL
        if (chr.hasSkill(IceLightning.ELEMENTAL_ADAPTATION_IL) && tsm.getOptByCTSAndSkill(AntiMagicShell, IceLightning.ELEMENTAL_ADAPTATION_IL) != null) {
            if (tsm.getOption(AntiMagicShell).bOption == 0) {
                Skill skill = chr.getSkill(IceLightning.ELEMENTAL_ADAPTATION_IL);
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                int slv = skill.getCurrentLevel();

                tsm.removeStatsBySkill(skill.getSkillId());
                tsm.removeAllDebuffs();

                Option o = new Option();
                o.nOption = 1;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                o.bOption = 1;
                tsm.putCharacterStatValue(AntiMagicShell, o);
                tsm.sendSetStatPacket();
            } else {
                tsm.removeAllDebuffs();
            }
        }

        // Divine Protection - Bishop
        if (chr.hasSkill(Bishop.DIVINE_PROTECTION) && tsm.getOptByCTSAndSkill(AntiMagicShell, Bishop.DIVINE_PROTECTION) != null) {
            tsm.removeStatsBySkill(Bishop.DIVINE_PROTECTION);
            tsm.sendResetStatPacket();
            tsm.removeAllDebuffs();
        }
    }

    // Elemental Adaptation - FP
    private void deductEleAdaptationFP() {
        if (!chr.hasSkill(FirePoison.ELEMENTAL_ADAPTATION_FP)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(FirePoison.ELEMENTAL_ADAPTATION_FP);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int proc = si.getValue(prop, slv);

        int stack = tsm.getOption(AntiMagicShell).nOption;
        if (stack > 0) {
            if (Util.succeedProp(proc)) {
                stack--;

                o.nOption = stack;
                o.rOption = FirePoison.ELEMENTAL_ADAPTATION_FP;
                tsm.putCharacterStatValue(AntiMagicShell, o);
                tsm.sendSetStatPacket();
            } else {
                tsm.removeStatsBySkill(FirePoison.ELEMENTAL_ADAPTATION_FP);
                tsm.sendResetStatPacket();
            }
        } else {
            tsm.removeStatsBySkill(FirePoison.ELEMENTAL_ADAPTATION_FP);
            tsm.sendResetStatPacket();
        }
    }


    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        // hacks to bypass the quest glitch (accept but no packet)
        short level = chr.getLevel();
        QuestManager qm = chr.getQuestManager();
        switch (level) {
            case 30:
                qm.completeQuest(1414);
                break;
            case 60:
                qm.completeQuest(1434);
                break;
            case 100:
                qm.completeQuest(1452);
                break;
        }
    }

    @Override
    public void cancelTimers() {
        if (infinityTimer != null) {
            infinityTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        Item beginnerWand = ItemData.getItemDeepCopy(1372107);
        chr.addItemToInventory(beginnerWand);

        Item beginnerStaff = ItemData.getItemDeepCopy(1382100);
        chr.addItemToInventory(beginnerStaff);
    }
}

