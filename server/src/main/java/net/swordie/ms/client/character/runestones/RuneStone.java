package net.swordie.ms.client.character.runestones;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.legend.Evan;
import net.swordie.ms.connection.packet.FieldPacket;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.RuneType;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created by Asura on 6-6-2018.
 */
public class RuneStone {
    private RuneType runeType;
    private Position position;
    private boolean flip;
    private ScheduledFuture thunderTimer;

    public static final int LIBERATED_RUNE_POWER = 80002280;
    public static final int SEALED_RUNE_POWER = 80002282;
    public static final int LIBERATE_THE_SWIFT_RUNE = 80001427;
    public static final int LIBERATE_THE_RECOVERY_RUNE = 80001428;
    public static final int LIBERATE_THE_DESTRUCTIVE_RUNE = 80001431; // DoT
    public static final int LIBERATE_THE_DESTRUCTIVE_RUNE_BUFF = 80001432; // Buff
    public static final int LIBERATE_THE_RUNE_OF_THUNDER = 80001752;
    public static final int LIBERATE_THE_RUNE_OF_MIGHT = 80001753;
    public static final int LIBERATE_THE_RUNE_OF_DARKNESS = 80001754;
    public static final int LIBERATE_THE_RUNE_OF_RICHES = 80001755;
    public static final int LIBERATE_THE_RUNE_OF_GREED = 80002281;
    public static final int LIBERATE_THE_RUNE_OF_SKILL = 80001875;

    public static final int LIBERATE_THE_RUNE_OF_MIGHT_2 = 80001757;
    public static final int LIBERATE_THE_RUNE_OF_THUNDER_2 = 80001762;
    public static final int RUNE_PERSISTANCE_LINK = 80000369;
    private EventType eventType;

    public RuneType getRuneType() {
        return runeType;
    }

    public void setRuneType(RuneType runeType) {
        this.runeType = runeType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public void spawnRune(Char chr) {
        chr.write(FieldPacket.runeStoneAppear(this));
    }

    public void despawnRune(Char chr) {
        chr.write(FieldPacket.runeStoneClearAndAllRegister());
    }

    public RuneStone getRandomRuneStone(Field field) {
        RuneStone runeStone = null;
        if (field.getMobGens().size() > 0 && field.getBossMobID() == 0 && !field.isTown()
                && field.getAverageMobLevel() > GameConstants.MIN_LEVEL_FOR_RANDOM_FIELD_OCCURENCES) {
            runeStone = new RuneStone();
            runeStone.setRuneType(RuneType.getByVal((byte) new Random().nextInt(RuneType.values().length)));
            runeStone.setEventType(EventType.Halloween);

            List<Foothold> listOfFootHolds = new ArrayList<>(field.getNonWallFootholds());
            Foothold foothold = Util.getRandomFromCollection(listOfFootHolds);
            Position position = foothold.getRandomPosition();

            runeStone.setPosition(position);
            runeStone.setFlip(false);
        }
        return runeStone;
    }



    public void activateRuneStoneEffect(Char chr) {
        int runeBuffID = 0;
        switch (runeType) {
            case Swiftness:
                applyRuneSwiftness(chr);
                runeBuffID = LIBERATE_THE_SWIFT_RUNE;
                break;
            case Recovery:
                applyRuneRecovery(chr);
                runeBuffID = LIBERATE_THE_RECOVERY_RUNE;
                break;
            case Destruction:
                // Handled in Job.java : handleAttack
                runeBuffID = LIBERATE_THE_DESTRUCTIVE_RUNE_BUFF;
                break;
            case Thunder:
                applyRuneThunder(chr);
                runeBuffID = LIBERATE_THE_RUNE_OF_THUNDER;
                break;
            case Might:
                applyRuneMight(chr);
                runeBuffID = LIBERATE_THE_RUNE_OF_MIGHT;
                break;
            case Darkness:
                applyRuneDarkness(chr);
                runeBuffID = LIBERATE_THE_RUNE_OF_DARKNESS;
                break;
            case Riches:

                //TODO  Drop stuff from the sky

                applyRuneRiches(chr);
                runeBuffID = LIBERATE_THE_RUNE_OF_RICHES;
                break;
            case Greed:
                applyRuneGreed(chr);
                runeBuffID = LIBERATE_THE_RUNE_OF_GREED;
                break;
            case Skill:
                // Handled in Char.java : setSkillCooldown
                runeBuffID = LIBERATE_THE_RUNE_OF_SKILL;
                break;
        }

        // Common EXP buff
        int runeBoost = 0;
        if (chr.hasSkill(Evan.RUNE_PERSISTANCE) || chr.hasSkill(RUNE_PERSISTANCE_LINK)) {
            int skillID = chr.hasSkill(RUNE_PERSISTANCE_LINK) ? RUNE_PERSISTANCE_LINK : Evan.RUNE_PERSISTANCE;
            Skill skill = chr.getSkill(skillID);
            int slv = skill.getCurrentLevel();
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            runeBoost = si.getValue(x, slv);
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATED_RUNE_POWER);
        int skillID = skill.getSkillId();
        skill.setCurrentLevel(1);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o1 = new Option();
        o1.nReason = skillID;
        o1.nValue = si.getValue(indieExp, slv);
        o1.tTerm = si.getValue(time, slv) + runeBoost;
        tsm.putCharacterStatValue(IndieEXP, o1);
        tsm.sendSetStatPacket();
    }

    private void applyRuneSwiftness(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATE_THE_SWIFT_RUNE);
        int skillID = skill.getSkillId();
        skill.setCurrentLevel(1);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o1 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        o1.nReason = skillID;
        o1.nValue = si.getValue(indieBooster, slv);
        o1.tStart = Util.getCurrentTime();
        o1.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieBooster, o1);
        o3.nReason = skillID;
        o3.nValue = si.getValue(indieJump, slv);
        o3.tStart = Util.getCurrentTime();
        o3.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieJump, o3);
        o4.nReason = skillID;
        o4.nValue = si.getValue(indieSpeed, slv);
        o4.tStart = Util.getCurrentTime();
        o4.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieSpeed, o4);
        tsm.sendSetStatPacket();
    }

    private void applyRuneRecovery(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATE_THE_RECOVERY_RUNE);
        int skillID = skill.getSkillId();
        skill.setCurrentLevel(1);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o3 = new Option();
        Option o4 = new Option();

        // HP Regen handled in Job.java : handleAttack
        o3.nOption = si.getValue(ignoreMobDamR, slv);
        o3.rOption = skillID;
        o3.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(IgnoreMobDamR, o3);
        o4.nReason = skillID;
        o4.nValue = si.getValue(indieAsrR, slv);
        o4.tStart = Util.getCurrentTime();
        o4.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieAsrR, o4);
        tsm.putCharacterStatValue(IndieTerR, o4);
        tsm.sendSetStatPacket();
    }

    private void applyRuneGreed(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATE_THE_RUNE_OF_GREED);
        int slv = 1;
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o = new Option();

        o.nOption = si.getValue(mesoAmountUp, slv);
        o.rOption = skill.getSkillId();
        o.tOption = 60; // no time SkillStat
        tsm.putCharacterStatValue(MesoUpByItem, o);
    }

    private void applyRuneThunder(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATE_THE_RUNE_OF_THUNDER_2);
        int skillID = skill.getSkillId();
        skill.setCurrentLevel(1);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o1 = new Option();

        // RandAreaAttack Buff
        o1.nOption = 1;
        o1.rOption = skillID;
        o1.tOption = SkillData.getSkillInfoById(80001756).getValue(time, slv);
        tsm.putCharacterStatValue(RandAreaAttack, o1);
        tsm.sendSetStatPacket();

        int fieldID = chr.getFieldID();

        checkAndCancelThunderTimer();
        thunderTimer = EventManager.addFixedRateEvent(() -> randAreaAttack(fieldID, tsm, chr), 0, GameConstants.THUNDER_RUNE_ATTACK_DELAY, TimeUnit.SECONDS);
    }

    private void checkAndCancelThunderTimer() {
        if (thunderTimer != null) {
            thunderTimer.cancel(true);
        }
    }


    private void randAreaAttack(int fieldID, TemporaryStatManager tsm, Char chr) {
        if((tsm.getOptByCTSAndSkill(RandAreaAttack, LIBERATE_THE_RUNE_OF_THUNDER_2) == null) || fieldID != chr.getFieldID()) {
            return;
        }

        Mob randomMob = Util.getRandomFromCollection(chr.getField().getMobs());
        chr.write(UserLocal.userRandAreaAttackRequest(randomMob, LIBERATE_THE_RUNE_OF_THUNDER_2));

        if(thunderTimer != null && !thunderTimer.isDone()) {
            thunderTimer.cancel(true);
        }
        thunderTimer = EventManager.addEvent(() -> randAreaAttack(fieldID, tsm, chr), GameConstants.THUNDER_RUNE_ATTACK_DELAY, TimeUnit.SECONDS);
    }

    private void applyRuneMight(Char chr) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Skill skill = SkillData.getSkillDeepCopyById(LIBERATE_THE_RUNE_OF_MIGHT_2);
        int skillID = skill.getSkillId();
        skill.setCurrentLevel(1);
        int slv = skill.getCurrentLevel();
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        Option o1 = new Option();
        Option o2 = new Option();
        o1.nOption = si.getValue(x, slv);
        o1.rOption = skillID;
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(Inflation, o1);
        o2.nReason = skillID;
        o2.nValue = si.getValue(indieSpeed, slv);
        o2.tStart = Util.getCurrentTime();
        o2.tTerm = si.getValue(time, slv);
        tsm.putCharacterStatValue(IndieSpeed, o2);
        tsm.putCharacterStatValue(IndieJump, o2);
        tsm.sendSetStatPacket();
    }


    private void applyRuneDarkness(Char chr) {
        Field field = chr.getField();
        int numberOfEliteMobsSpawned = GameConstants.DARKNESS_RUNE_NUMBER_OF_ELITE_MOBS_SPAWNED;
        for(int i = 0; i < numberOfEliteMobsSpawned; i++) {
            Mob mob = Util.getRandomFromCollection(field.getMobs());
            mob.spawnEliteMobRuneOfDarkness();
        }
    }

    private void applyRuneRiches(Char chr) {
        chr.chatMessage(ChatType.BlackOnWhite, "This rune's effect has not yet been implemented.");
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public enum EventType {
        None,
        Halloween,
        Anniverssary,
        NewYear,
        Honey
    }
}
