package net.swordie.ms.client.jobs.resistance;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.UserLocal;
import net.swordie.ms.connection.packet.UserPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.life.mob.MobStat.DodgeBodyAttack;
import static net.swordie.ms.life.mob.MobStat.JaguarProvoke;

/**
 * Created on 12/14/2017.
 */
public class WildHunter extends Citizen {

    //Jaguar Summon
    public static final int SUMMON_JAGUAR_GREY = 33001007;           //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_YELLOW = 33001008;         //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_RED = 33001009;            //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_PURPLE = 33001010;         //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_BLUE = 33001011;           //No Special Jaguar Stats
    public static final int SUMMON_JAGUAR_JAIRA = 33001012;          //Critical Rate +5%
    public static final int SUMMON_JAGUAR_SNOW_WHITE = 33001013;     //Buff Duration +10%
    public static final int SUMMON_JAGUAR_ONYX = 33001014;           //Buff Duration +10%
    public static final int SUMMON_JAGUAR_CRIMSON = 33001015;        //Dmg Absorption +10%
    public static final int[] SUMMONS = new int[]{SUMMON_JAGUAR_GREY, SUMMON_JAGUAR_YELLOW, SUMMON_JAGUAR_RED,
            SUMMON_JAGUAR_PURPLE, SUMMON_JAGUAR_BLUE, SUMMON_JAGUAR_JAIRA, SUMMON_JAGUAR_SNOW_WHITE, SUMMON_JAGUAR_ONYX,
            SUMMON_JAGUAR_CRIMSON};

    //Jaguar Mount
    public static final int MOUNT_JAGUAR_GREY = 1932015;
    public static final int MOUNT_JAGUAR_YELLOW = 1932030;
    public static final int MOUNT_JAGUAR_RED = 1932031;
    public static final int MOUNT_JAGUAR_PURPLE = 1932032;
    public static final int MOUNT_JAGUAR_BLUE = 1932033;
    public static final int MOUNT_JAGUAR_JAIRA = 1932036;
    public static final int MOUNT_JAGUAR_SNOW_WHITE = 1932100;
    public static final int MOUNT_JAGUAR_ONYX = 1932149;
    public static final int MOUNT_JAGUAR_CRIMSON = 1932215;
    public static final int[] MOUNTS = new int[]{MOUNT_JAGUAR_GREY, MOUNT_JAGUAR_YELLOW, MOUNT_JAGUAR_RED,
            MOUNT_JAGUAR_PURPLE, MOUNT_JAGUAR_BLUE, MOUNT_JAGUAR_JAIRA, MOUNT_JAGUAR_SNOW_WHITE, MOUNT_JAGUAR_ONYX,
            MOUNT_JAGUAR_CRIMSON};


    public static final int SECRET_ASSEMBLY = 30001281;
    public static final int CAPTURE = 30001061;
    public static final int CALL_OF_THE_HUNTER = 30001062;

    public static final int RIDE_JAGUAR = 33001001; //Special Buff
    public static final int SWIPE = 33001016; //Special Attack (Bite Debuff)
    public static final int WILD_LURE = 33001025;
    public static final int ANOTHER_BITE = 33000036;

    public static final int SOUL_ARROW_CROSSBOW = 33101003; //Buff
    public static final int CROSSBOW_BOOSTER = 33101012; //Buff
    public static final int CALL_OF_THE_WILD = 33101005; //Buff
    public static final int DASH_N_SLASH_JAGUAR_SUMMONED = 33101115; //Special Attack (Stun Debuff) + (Bite Debuff)
    public static final int DASH_N_SLASH_RIDE_JAGUAR = 33101215; //Special Attack (Stun Debuff) + (Bite Debuff)

    public static final int FELINE_BERSERK = 33111007; //Buff
    public static final int FELINE_BERSERK_REINFORCE = 33120043; //Buff
    public static final int FELINE_BERSERK_VITALITY = 33120044; //Buff
    public static final int FELINE_BERSERK_RAPID_ATTACK = 33120045; //Buff
    public static final int BACKSTEP = 33111011; //Special Buff (ON/OFF)
    public static final int HUNTING_ASSISTANT_UNIT = 33111013; //Area of Effect
    public static final int SONIC_ROAR = 33111015; //Special Attack (Bite Debuff)
    public static final int FLURRY = 33110008; //Dodge

    public static final int JAGUAR_SOUL = 33121017; //Special Attack (Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
    public static final int DRILL_SALVO = 33121016; //Summon
    public static final int SHARP_EYES = 33121004; //Buff
    public static final int MAPLE_WARRIOR_WH = 33121007; //Buff
    public static final int HEROS_WILL_WH = 33121008;

    //Final Attack
    public static final int FINAL_ATTACK_WH = 33100009;
    public static final int ADVANCED_FINAL_ATTACK_WH = 33120011;

    public static final int FOR_LIBERTY_WH = 33121053;
    public static final int SILENT_RAMPAGE = 33121054;
    public static final int JAGUAR_RAMPAGE = 33121255;
    public static final int EXPLODING_ARROWS = 33121155;


    // V
    public static final int JAGUAR_STORM = 400031005;
    public static final int PRIMAL_FURY = 400031012;
    public static final int PRIMAL_FURY_2 = 400031013; // screen
    public static final int PRIMAL_FURY_MOUNT_REQUEST = 400031014;
    public static final int PRIMAL_GRENADE = 400031032;

    private static final int[] addedSkills = new int[]{
            SECRET_ASSEMBLY,
    };

    private static final int[] jaguarSummons = new int[]{
            SUMMON_JAGUAR_GREY,
            SUMMON_JAGUAR_YELLOW,
            SUMMON_JAGUAR_RED,
            SUMMON_JAGUAR_PURPLE,
            SUMMON_JAGUAR_BLUE,
            SUMMON_JAGUAR_JAIRA,
            SUMMON_JAGUAR_SNOW_WHITE,
            SUMMON_JAGUAR_ONYX,
            SUMMON_JAGUAR_CRIMSON,
    };

    private int lastUsedSkill = 0;
    private ScheduledFuture primalGrenadeCountTimer;

    public WildHunter(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            if (chr.getWildHunterInfo() == null) {
                chr.setWildHunterInfo(new WildHunterInfo());
            }
            for (int id : addedSkills) {
                Skill skill = chr.getSkill(id);
                if (!chr.hasSkill(id) || skill.getCurrentLevel() != skill.getMaxLevel()) {
                    skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMaxLevel());
                    chr.addSkill(skill);
                }
            }
            incrementPrimalGrenade();
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isWildHunter(id);
    }


    private void incrementPrimalGrenade() {
        primalGrenadeCountTimer = EventManager.addFixedRateEvent(this::increasePrimalGrenadeCount, 4500, 4500, TimeUnit.MILLISECONDS);
    }

    private void increasePrimalGrenadeCount() {
        if (!chr.hasSkill(PRIMAL_GRENADE)) {
            return;
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        int count = 1;
        if (tsm.hasStat(PrimalGrenade)) {
            count = tsm.getOption(PrimalGrenade).nOption;
            if (count < 8) {
                count++;
            }
        }
        updatePrimalGrenadeCount(count);
    }

    private void updatePrimalGrenadeCount(int count) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        o.nOption = count;
        o.rOption = PRIMAL_GRENADE;
        tsm.putCharacterStatValue(PrimalGrenade, o);
        tsm.sendSetStatPacket();
    }


    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (attackInfo.skillId >= SUMMON_JAGUAR_GREY && attackInfo.skillId <= SUMMON_JAGUAR_CRIMSON) {
            attackInfo.skillId = lastUsedSkill;
            lastUsedSkill = 0;
        }
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
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        int jaguarBleedingTime = SkillData.getSkillInfoById(SUMMON_JAGUAR_GREY).getValue(time, 1);
        switch (attackInfo.skillId) {
            case DASH_N_SLASH_JAGUAR_SUMMONED: //(33101115)  //Stun + Bite Debuff
                o2.nOption = 1;
                o2.rOption = skill.getSkillId();
                o2.tOption = si.getValue(time, 1);
                o1.rOption = ANOTHER_BITE;
                o1.tOption = jaguarBleedingTime;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int amount = 0;
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(subProp, 1)) && !mob.isBoss()) {
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o2);
                    }
                    if (Util.succeedProp(si.getValue(prop, 1)) || !mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                        if (mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                    }
                }
                break;
            case DASH_N_SLASH_RIDE_JAGUAR: //(33101215)   //Stun Debuff
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, 1);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, 1))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null || mob.isBoss()) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                    }
                }
                break;
            case SONIC_ROAR:
            case SWIPE: //Bite Debuff
                o1.rOption = ANOTHER_BITE;
                o1.tOption = jaguarBleedingTime;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int amount = 0;
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, 1)) || !mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                        if (mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                    }
                }
                break;
            case JAGUAR_RAMPAGE:
                o1.rOption = ANOTHER_BITE;
                o1.tOption = jaguarBleedingTime;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int amount = 0;
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                        amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                    }
                    amount = amount + 1 > 3 ? 3 : amount + 1;
                    o1.nOption = amount;
                    mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                }
                break;
            case JAGUAR_SOUL: //(Stun Debuff) + (Bite Debuff) + (Magic Crash Debuff)
                o1.rOption = ANOTHER_BITE;
                o1.tOption = jaguarBleedingTime;
                o2.nOption = 1;
                o2.rOption = skill.getSkillId();
                o2.tOption = si.getValue(time, 1);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    int amount = 0;
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    if (Util.succeedProp(si.getValue(prop, 1)) || !mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                        if (mts.hasCurrentMobStat(MobStat.JaguarBleeding)) {
                            amount = mts.getCurrentOptionsByMobStat(MobStat.JaguarBleeding).nOption;
                        }
                        amount = amount + 1 > 3 ? 3 : amount + 1;
                        o1.nOption = amount;
                        mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                    }
                    mts.addStatOptionsAndBroadcast(MobStat.Stun, o2);
                    mts.removeBuffs();
                }
                break;
            case PRIMAL_FURY_2:
            case PRIMAL_FURY:
                o1.nOption = 3; // max stacks instantly
                o1.rOption = ANOTHER_BITE;
                o1.tOption = jaguarBleedingTime;
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.JaguarBleeding, o1);
                }
                break;
            case PRIMAL_GRENADE:
                updatePrimalGrenadeCount(tsm.hasStat(PrimalGrenade) ? tsm.getOption(PrimalGrenade).nOption - 1 : 0);
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    @Override
    public int getFinalAttackSkill() {
        if (Util.succeedProp(getFinalAttackProc())) {
            int fas = 0;
            if (chr.hasSkill(FINAL_ATTACK_WH)) {
                fas = FINAL_ATTACK_WH;
            }
            if (chr.hasSkill(ADVANCED_FINAL_ATTACK_WH)) {
                fas = ADVANCED_FINAL_ATTACK_WH;
            }
            return fas;
        } else {
            return 0;
        }
    }

    private Skill getFinalAtkSkill(Char chr) {
        Skill skill = null;
        if (chr.hasSkill(FINAL_ATTACK_WH)) {
            skill = chr.getSkill(FINAL_ATTACK_WH);
        }
        if (chr.hasSkill(ADVANCED_FINAL_ATTACK_WH)) {
            skill = chr.getSkill(ADVANCED_FINAL_ATTACK_WH);
        }
        return skill;
    }

    private int getFinalAttackProc() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOptByCTSAndSkill(IndieDamR, SILENT_RAMPAGE) != null) {
            return 100;
        }
        Skill skill = getFinalAtkSkill(chr);
        if (skill == null) {
            return 0;
        }
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = chr.getSkill(skill.getSkillId()).getCurrentLevel();
        int proc = si.getValue(prop, slv);

        return proc;
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
        TemporaryStatBase tsb = tsm.getTSBByTSIndex(TSIndex.RideVehicle);
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case JAGUAR_RAMPAGE:
            case DASH_N_SLASH_JAGUAR_SUMMONED:
                int cooltimeSkillID;
                if (skillID == JAGUAR_RAMPAGE) {
                    cooltimeSkillID = EXPLODING_ARROWS;
                    si = SkillData.getSkillInfoById(cooltimeSkillID);
                } else {
                    cooltimeSkillID = DASH_N_SLASH_RIDE_JAGUAR;
                }
                chr.addSkillCoolTime(cooltimeSkillID, si.getValue(cooltime, 1) * 1000);
            case SWIPE:
            case SONIC_ROAR:
            case JAGUAR_SOUL:
                lastUsedSkill = skillID;
                chr.write(UserLocal.jaguarSkill(skillID));
                break;
            case WILD_LURE:
                Position position = inPacket.decodePosition();
                boolean left = inPacket.decodeByte() != 0;
                Rect rect = position.getRectAround(si.getFirstRect());
                if (!left) {
                    rect.horizontalFlipAround(position.getX());
                }
                int mobCounter = 0;
                int maxMobAffected = si.getValue(mobCount, slv);
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv) / 2;
                for (Mob mob : chr.getField().getMobsInRect(rect)) {
                    if (mobCounter >= maxMobAffected) {
                        break;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(DodgeBodyAttack, mob.isBoss() ? o2 : o1);
                    mts.addStatOptionsAndBroadcast(JaguarProvoke, mob.isBoss() ? o2 : o1);

                    mobCounter++;
                }
                lastUsedSkill = skillID;
                chr.write(UserLocal.jaguarSkill(skillID));
                break;
            case HUNTING_ASSISTANT_UNIT:
                position = inPacket.decodePosition();
                inPacket.decodeInt();
                left = inPacket.decodeByte() != 0;
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(position);
                rect = aa.getPosition().getRectAround(si.getRects().get(0));
                if (!left) {
                    rect = rect.horizontalFlipAround(aa.getPosition().getX());
                }
                aa.setRect(rect);
                aa.setFlip(!left);
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedAreaAndRemoveOld(aa);
                break;
            case HEROS_WILL_WH:
                tsm.removeAllDebuffs();
                break;
            case CAPTURE:
                int mobID = inPacket.decodeInt();
                Life life = chr.getField().getLifeByObjectID(mobID);
                if (life instanceof Mob) {
                    Mob mob = (Mob) life;
                    if (mob.getMaxHp() * 0.90 <= mob.getHp()) {
                        chr.write(UserPacket.effect(Effect.showCaptureEffect(skillID, slv, 0, 1)));
                        return;
                    }
                    Quest quest = chr.getQuestManager().getQuestById(QuestConstants.WILD_HUNTER_JAGUAR_STORAGE_ID);
                    if (quest == null) {
                        quest = new Quest(QuestConstants.WILD_HUNTER_JAGUAR_STORAGE_ID, QuestStatus.Started);
                        chr.getQuestManager().addQuest(quest);
                    }
                    String key = QuestConstants.getWhStorageQuestValByTemplateID(mob.getTemplateId());
                    if (key != null) {
                        quest.setProperty(key, "1");
                        chr.write(WvsContext.message(MessageType.QUEST_RECORD_EX_MESSAGE,
                                quest.getQRKey(), quest.getQRValue(), (byte) 0));
                        chr.write(UserPacket.effect(Effect.showCaptureEffect(skillID, slv, 0, 0)));
                        WildHunterInfo whi = chr.getWildHunterInfo();
                        mob.die(true);
                    } else {
                        chr.write(UserPacket.effect(Effect.showCaptureEffect(skillID, slv, 0, 2)));
                    }
                }
                break;
            case PRIMAL_FURY_MOUNT_REQUEST:
                handleSkill(chr, tsm, RIDE_JAGUAR, chr.getSkillLevel(RIDE_JAGUAR), inPacket, new SkillUseInfo());
                break;
            case SUMMON_JAGUAR_GREY:
            case SUMMON_JAGUAR_YELLOW:
            case SUMMON_JAGUAR_RED:
            case SUMMON_JAGUAR_PURPLE:
            case SUMMON_JAGUAR_BLUE:
            case SUMMON_JAGUAR_JAIRA:
            case SUMMON_JAGUAR_SNOW_WHITE:
            case SUMMON_JAGUAR_ONYX:
            case SUMMON_JAGUAR_CRIMSON:
                if (chr.getWildHunterInfo() == null
                        || chr.getWildHunterInfo().getIdx() < 0
                        || chr.getWildHunterInfo().getIdx() >= MOUNTS.length) {
                    chr.chatMessage("You haven't selected a jaguar.");
                    return;
                }
                summon = Summon.getSummonByNoCTS(chr, SUMMONS[chr.getWildHunterInfo().getIdx()], (byte) 1);
                summon.setSummonTerm(0);
                summon.setMoveAbility(MoveAbility.Jaguar);
                summon.setAssistType(AssistType.AttackJaguar);

                field.spawnSummon(summon);

                if (tsm.hasStatBySkillId(RIDE_JAGUAR)) {
                    tsm.removeStatsBySkill(RIDE_JAGUAR);
                }

                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(JaguarSummoned, o1);
                tsm.putCharacterStatValue(JaguarCount, o1);
                o2.nValue = 1;
                o2.nReason = skillID;
                o2.summon = summon;
                tsm.putCharacterStatValue(IndieSummon, o2, true);
                chr.write(UserLocal.jaguarActive(true)); // Makes Jaguar use normal attacks
                break;
            case RIDE_JAGUAR:
                if (chr.getWildHunterInfo() == null
                        || chr.getWildHunterInfo().getIdx() < 0
                        || chr.getWildHunterInfo().getIdx() >= MOUNTS.length) {
                    chr.chatMessage("You haven't selected a jaguar.");
                    return;
                }

                for (int jaguarSummonSkill : jaguarSummons) {
                    tsm.removeStatsBySkill(jaguarSummonSkill);
                }

                if (tsm.hasStat(RideVehicle)) {
                    tsm.removeStat(RideVehicle, false);
                } else {
                    tsb.setNOption(MOUNTS[chr.getWildHunterInfo().getIdx()]);
                    tsb.setROption(skillID);
                    tsm.putCharacterStatValue(RideVehicle, tsb.getOption());
                }
                break;
            case SOUL_ARROW_CROSSBOW:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SoulArrow, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;
            case CROSSBOW_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case CALL_OF_THE_WILD:
                o1.nOption = si.getValue(z, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HowlingAttackDamage, o1);
                o3.nOption = si.getValue(y, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HowlingCritical, o3);
                o2.nOption = si.getValue(x, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(HowlingMaxMP, o2);
                tsm.putCharacterStatValue(HowlingDefence, o2);
                tsm.putCharacterStatValue(HowlingEvasion, o2);
                break;
            case FELINE_BERSERK:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieBooster, slv) - chr.getSkillStatValue(w, FELINE_BERSERK_RAPID_ATTACK);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nOption = si.getValue(z, slv) + chr.getSkillStatValue(z, FELINE_BERSERK_REINFORCE);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BeastFormDamageUp, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o3);
                if (chr.hasSkill(FELINE_BERSERK_VITALITY)) {
                    o4.nOption = chr.getSkillStatValue(mhpR, FELINE_BERSERK_VITALITY);
                    o4.rOption = skillID;
                    o4.tOption = si.getValue(time, slv);
                    tsm.putCharacterStatValue(BeastFormMaxHP, o4);
                }
                break;
            case BACKSTEP:
                o1.nOption = 1;
                o1.rOption = skillID;
                tsm.putCharacterStatValue(DrawBack, o1);
                break;
            case SHARP_EYES: // x = crit rate%  |  y = max crit dmg%
                // Short nOption is split in  2 bytes,  first one = CritDmg  second one = Crit%
                int cr = si.getValue(x, slv);
                int crDmg = si.getValue(y, slv);
                o1.xOption = cr;
                o1.yOption = crDmg;
                o1.nOption = (cr << 8) + crDmg;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(SharpEyes, o1);
                break;
            case MAPLE_WARRIOR_WH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case FOR_LIBERTY_WH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;

            case SILENT_RAMPAGE: // FinalAttackProp
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case DRILL_SALVO:
                position = inPacket.decodePosition();
                inPacket.decodeInt();
                left = inPacket.decodeByte() != 0;
                aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(position);
                rect = aa.getPosition().getRectAround(si.getRects().get(0));
                if (!left) {
                    rect = rect.horizontalFlipAround(aa.getPosition().getX());
                }
                aa.setRect(rect);
                aa.setFlip(!left);
                aa.setDelay((short) 5);
                field.spawnAffectedAreaAndRemoveOld(aa);
                break;
            case PRIMAL_FURY:
                aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setDuration(si.getValue(v, slv) * 1000);
                field.spawnAffectedAreaAndRemoveOld(aa);

                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(v, slv);
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
            case JAGUAR_STORM:
                for (int summonId : SUMMONS) {
                    if (summonId == SUMMONS[chr.getWildHunterInfo().getIdx()]) {
                        continue;
                    }

                    Summon jaguar = Summon.getSummonByNoCTS(chr, summonId, 1);
                    jaguar.setMoveAbility(MoveAbility.WalkRandom);
                    jaguar.setAssistType(AssistType.Attack);
                    jaguar.setSummonTerm(si.getValue(time, slv));
                    jaguar.setFlip(true);
                    jaguar.setState(1); // sniffed value
                    jaguar.setCount(300); // sniffed value
                    Position randPos = Util.getRandomFromCollection(field.getFootholdsInRect(chr.getRectAround(si.getFirstRect()))).getRandomPosition();
                    jaguar.setPosition(randPos);
                    jaguar.setCurFoothold((short) field.findFootHoldBelow(jaguar.getPosition()).getId());
                    field.spawnSummon(jaguar);
                }
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ExtraSkillCTS, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        if (hitInfo.hpDamage == 0 && hitInfo.mpDamage == 0) {
            // Dodged
            if (chr.hasSkill(FLURRY)) {
                Skill skill = chr.getSkill(FLURRY);
                int slv = skill.getCurrentLevel();
                SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                TemporaryStatManager tsm = chr.getTemporaryStatManager();
                Option o = new Option();
                o.nOption = 100;
                o.rOption = skill.getSkillId();
                o.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CriticalBuff, o);
                tsm.sendSetStatPacket();
            }
        }
        super.handleHit(c, inPacket, hitInfo);
    }

    @Override
    public void cancelTimers() {
        if (primalGrenadeCountTimer != null) {
            primalGrenadeCountTimer.cancel(false);
        }
        super.cancelTimers();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Skills
        // TODO: Maybe move these to addedSkills for ALL jobs.
        chr.addSkill(CAPTURE, 1 ,1);
        chr.addSkill(CALL_OF_THE_HUNTER, 1, 1);

        // Items
        Item beginnerCrossbow = ItemData.getItemDeepCopy(1462092);
        chr.addItemToInventory(beginnerCrossbow);

        Item arrowCrossbow = ItemData.getItemDeepCopy(2061000);
        arrowCrossbow.setQuantity(2000);
        chr.addItemToInventory(arrowCrossbow);


        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352960)); // Arrow Head (Secondary)
    }
}
