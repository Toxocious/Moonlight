package net.swordie.ms.client.jobs.adventurer;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.SkillStat;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.party.Party;
import net.swordie.ms.client.party.PartyMember;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.Effect;
import net.swordie.ms.connection.packet.FieldPacket;
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
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.Foothold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

/**
 * Created on 12/14/2017.
 */
public class BeastTamer extends Job {
    //Common
    public static final int GUARDIAN_LEAP = 110001506;
    public static final int CRITTER_SELECT = 110001510;
    public static final int HOMEWARD_BOUND = 110001514;
    public static final int MAPLE_GUARDIAN = 110001511;

    public static final int BEAR_MODE = 110001501;
    public static final int SNOW_LEOPARD_MODE = 110001502;
    public static final int HAWK_MODE = 110001503;
    public static final int CAT_MODE = 110001504;

    public static final int BEAST_SCEPTER_MASTERY = 110000515;
    public static final int GROWTH_SPURT = 110000513;
    public static final int BEASTLY_RESOLVE = 110001512;

    // Quest ID for Growth Spurt
    public static final int GROWTH_SPURT_QUEST_LEVEL = 59340;
    public static final int GROWTH_SPURT_QUEST_EXP = 59341;

    public static final int MASTERY_MASTER_LEVEL = 10;
    public static final int GROWTH_MASTER_LEVEL = 30;

    //Bear Mode
    public static final int LIL_FORT = 112001007;
    public static final int FORT_FOLLOW_UP = 112000015;
    public static final int MAJESTIC_TRUMPET = 112001006;
    public static final int BEAR_REBORN = 112000016;
    public static final int BEAR_ASSAULT = 112001009;
    public static final int FISHY_SLAP = 112001008;

    //Snow Leopard Mode
    public static final int BRO_ATTACK = 112101016;
    public static final int THUNDER_DASH = 112101007;
    public static final int ADV_THUNDER_DASH = 112100012;
    public static final int THUNDER_TRAIL = 112100008; //tile

    //Hawk Mode
    public static final int EKA_EXPRESS = 112111010;    //Door skill
    public static final int FLY = 112111000;
    public static final int HAWK_FLOCK = 112111007;
    public static final int RAPTOR_TALONS = 112111006;
    public static final int BIRDS_EYE_VIEW = 112111009;
    public static final int RAZOR_BEAK = 112111008;
    public static final int REGROUP = 112111011;    //Warp Party to player
    public static final int DEFENSIVE_FORMATION = 112110005;

    //Cat Mode
    public static final int MEOW_HEAL = 112121013;
    public static final int PURR_ZONE = 112121005; // Special Skill
    public static final int MEOW_CARD = 112121006; // Meow Card
    public static final int MEOW_CARD_RED = 112121007; //Red
    public static final int MEOW_CARD_BLUE = 112121008; //Blue
    public static final int MEOW_CARD_GREEN = 112121009; //Green
    public static final int MEOW_CARD_GOLD = 112121020; //112120009;    //Gold
    public static final int MEOW_CARD_GOLD_SKILL = 112120019; // If chr has the Gold Card Skill
    public static final int FIRE_KITTY = 112121004;
    public static final int CATS_CRADLE_BLITZKRIEG = 112121057; // Special Skill (like PURR_ZONE)
    public static final int KITTY_BATTLE_SQUAD = 112120021;
    public static final int KITTY_TREATS = 112120023;
    public static final int STICKY_PAWS = 112120017;
    public static final int CAT_CLAWS = 112120018;
    public static final int MOUSERS_INSIGHT = 112120022;
    public static final int FRIENDS_OF_ARBY = 112120016;
    public static final int MEOW_CURE = 112121010;
    public static final int MEOW_REVIVE = 112121011;


    //Hyper
    public static final int TEAM_ROAR = 112121056;

    // V
    public static final int CHAMP_CHARGE_CAT_TILE = 400021039;
    public static final int CUB_CAVALRY = 400021055;
    public static final int CUB_CAVALRY_SUMMON_1 = 400021056;
    public static final int CUB_CAVALRY_SUMMON_2 = 400021057;
    public static final int CUB_CAVALRY_SUMMON_3 = 400021058;
    public static final int CUB_CAVALRY_SUMMON_4 = 400021059;
    public static final int AERIAL_RELIEF = 400021082;
    public static final int AERIAL_RELIEF_SKILL_USE = 400021085;

    private static final int[] addedSkills = new int[]{
            BEAR_MODE,
            SNOW_LEOPARD_MODE,
            HAWK_MODE,
            CAT_MODE,
            HOMEWARD_BOUND,
            GUARDIAN_LEAP,
            CRITTER_SELECT
    };

    private static final int[] bearBuffs = new int[]{
            BEAR_ASSAULT,
    };

    private static final int[] leopardBuffs = new int[]{
            BRO_ATTACK,
    };

    private static final int[] hawkBuffs = new int[]{
            HAWK_FLOCK,
            RAPTOR_TALONS,
            BIRDS_EYE_VIEW,
            RAZOR_BEAK,
    };

    private static final int[] catBuffs = new int[]{
            MEOW_CARD,
            MEOW_CARD_RED,
            MEOW_CARD_BLUE,
            MEOW_CARD_GREEN,
            MEOW_CARD_GOLD,
            MEOW_CARD_GOLD_SKILL,
            KITTY_BATTLE_SQUAD,
            KITTY_TREATS,
            STICKY_PAWS,
            CAT_CLAWS,
            MOUSERS_INSIGHT,
            FRIENDS_OF_ARBY,
    };

    private static final int[] cards = new int[]{
            MEOW_CARD_RED,
            MEOW_CARD_GREEN,
            MEOW_CARD_BLUE,
            MEOW_CARD_GOLD
    };

    private static final HashMap<Integer, int[]> buffsByMode;

    static {
        buffsByMode = new HashMap<>();
        buffsByMode.put(BEAR_MODE, bearBuffs);
        buffsByMode.put(SNOW_LEOPARD_MODE, leopardBuffs);
        buffsByMode.put(HAWK_MODE, hawkBuffs);
        buffsByMode.put(CAT_MODE, catBuffs);
    }

    private int fortFollowUpAddAttack = 0;
    private Summon defensiveFormation;

    public BeastTamer(Char chr) {
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
        return JobConstants.isBeastTamer(id);
    }

    private boolean isBearMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(BeastMode) && tsm.getOption(BeastMode).nOption == 1;
    }

    private boolean isLeopardMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(BeastMode) && tsm.getOption(BeastMode).nOption == 2;
    }

    private boolean isHawkMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(BeastMode) && tsm.getOption(BeastMode).nOption == 3;
    }

    private boolean isCatMode() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        return tsm.hasStat(BeastMode) && tsm.getOption(BeastMode).nOption == 4;
    }


    //  Buff related methods -------------------------------------------------------------------------------------------

    private void giveMeowCard(int slv) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(MEOW_CARD) && !chr.hasSkill(MEOW_CARD_GOLD_SKILL)) {
            return;
        }
        SkillInfo mc = SkillData.getSkillInfoById(MEOW_CARD);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();

        int randomMeowCard = getRandomMeowCard();

        resetPrevMeowCards();
        switch (randomMeowCard) {
            case MEOW_CARD_RED:
                o1.nReason = randomMeowCard;
                o1.nValue = mc.getValue(indieDamR, slv);
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
            case MEOW_CARD_GREEN:
                o1.nReason = randomMeowCard;
                o1.nValue = -5; // mc.getValue(indieBooster, slv);
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                o2.nReason = randomMeowCard;
                o2.nValue = mc.getValue(indieSpeed, slv);
                o2.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o1);
                break;
            case MEOW_CARD_BLUE:
                o1.nReason = randomMeowCard;
                o1.nValue = mc.getValue(pdd, slv);
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDEF, o1);
                break;
            case MEOW_CARD_GOLD:
                o1.nReason = randomMeowCard;
                o1.nValue = mc.getValue(indieDamR, slv);
                o1.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = randomMeowCard;
                o2.nValue = -5; // mc.getValue(indieBooster, slv);
                o2.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o2);
                o3.nReason = randomMeowCard;
                o3.nValue = mc.getValue(indieSpeed, slv);
                o3.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieSpeed, o3);
                o4.nReason = randomMeowCard;
                o4.nValue = mc.getValue(pdd, slv);
                o4.tTerm = mc.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDEF, o4);
                break;
        }
        tsm.sendSetStatPacket();
    }

    private int getRandomMeowCard() {
        int rng = new Random().nextInt((chr.hasSkill(MEOW_CARD_GOLD_SKILL) ? cards.length : cards.length - 1));
        return cards[rng];
    }

    private void resetPrevMeowCards() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        for (int cardBuffId : cards) {
            if (tsm.hasStatBySkillId(cardBuffId)) {
                tsm.removeStatsBySkill(cardBuffId);
                tsm.sendResetStatPacket();
            }
        }
    }

    private void giveKittyBattleSquadBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(KITTY_BATTLE_SQUAD);
        int slv = si.getCurrentLevel();
        o1.nReason = KITTY_BATTLE_SQUAD;
        o1.nValue = si.getValue(indiePad, slv);
        tsm.putCharacterStatValue(IndiePAD, o1);
        tsm.putCharacterStatValue(IndieMAD, o1);
        tsm.sendSetStatPacket();
    }

    private void giveKittyTreatsBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(KITTY_TREATS);
        int slv = si.getCurrentLevel();
        o1.nReason = KITTY_TREATS;
        o1.nValue = si.getValue(indieMhp, slv);
        tsm.putCharacterStatValue(IndieMHP, o1);
        o2.nReason = KITTY_TREATS;
        o2.nValue = si.getValue(indieMmp, slv);
        tsm.putCharacterStatValue(IndieMMP, o2);
        tsm.sendSetStatPacket();
    }

    private void giveStickyPawsBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(STICKY_PAWS);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(v, slv);
        o1.rOption = STICKY_PAWS;
        tsm.putCharacterStatValue(DropRate, o1);
        tsm.sendSetStatPacket();
    }

    private void giveCatClawsBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(CAT_CLAWS);
        int slv = si.getCurrentLevel();
        o1.nOption = si.getValue(x, slv);
        o1.rOption = CAT_CLAWS;
        tsm.putCharacterStatValue(CriticalBuff, o1);
        o2.nOption = si.getValue(y, slv);
        o2.rOption = CAT_CLAWS;
        tsm.putCharacterStatValue(IncCriticalDam, o2);
        tsm.sendSetStatPacket();
    }

    private void giveMouserInsightBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(MOUSERS_INSIGHT);
        int slv = si.getCurrentLevel();
        o1.nReason = MOUSERS_INSIGHT;
        o1.nValue = si.getValue(x, slv);
        tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
        tsm.sendSetStatPacket();
    }

    private void giveFriendsOfArbyBuff() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(FRIENDS_OF_ARBY);
        int slv = si.getCurrentLevel();
        if (tsm.getOptByCTSAndSkill(HolySymbol, Bishop.HOLY_SYMBOL) == null) { // Only apply if player doesn't have Holy Symbol
            o1.nOption = si.getValue(x, slv);
            o1.rOption = FRIENDS_OF_ARBY;
            tsm.putCharacterStatValue(HolySymbol, o1);
            tsm.sendSetStatPacket();
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

        if (isLeopardMode()) { // Leopard
            if (hasHitMobs) {
                if (skillID != BRO_ATTACK) {
                    procBroAttack(attackInfo);
                }
            }
        }

        if (isHawkMode()) { // Hawk
            if (hasHitMobs) {
                applyRaptorTalonsOnMob(attackInfo);
            }
        }

        if (isCatMode()) { // Cat
            giveKittyBattleSquadBuff();
            giveKittyTreatsBuff();
            giveStickyPawsBuff();
            giveCatClawsBuff();
            giveMouserInsightBuff();
            giveFriendsOfArbyBuff();
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case PURR_ZONE:
            case CATS_CRADLE_BLITZKRIEG:
                AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                chr.getField().spawnAffectedArea(aa);
                break;
            case FIRE_KITTY:
                o1.nOption = si.getValue(SkillStat.x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    MobTemporaryStat mts = mob.getTemporaryStat();
                    mts.addStatOptionsAndBroadcast(MobStat.PDR, o1);
                }
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void procBroAttack(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.getOptByCTSAndSkill(BroAttack, BRO_ATTACK) != null) {
            Summon summon;
            Field field;
            Skill skill = chr.getSkill(BRO_ATTACK);
            if (!chr.hasSkill(BRO_ATTACK)) {
                return;
            }
            SkillInfo si = SkillData.getSkillInfoById(BRO_ATTACK);
            int slv = skill.getCurrentLevel();
            int summonProp = si.getValue(prop, slv);
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                if (Util.succeedProp(summonProp)) {
                    summon = Summon.getSummonByNoCTS(chr, BRO_ATTACK, slv);
                    field = c.getChr().getField();
                    summon.setFlyMob(false);
                    summon.setPosition(mob.getPosition());
                    summon.setSummonTerm(si.getValue(x, slv));
                    summon.setMoveAbility(MoveAbility.WalkRandom);
                    field.spawnAddSummon(summon);
                }
            }

        }
    }

    private void applyRaptorTalonsOnMob(AttackInfo attackInfo) {
        Option o1 = new Option();
        if (!chr.hasSkill(RAPTOR_TALONS)) {
            return;
        }
        Skill skill = chr.getSkill(RAPTOR_TALONS);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
            if (mob == null) {
                return;
            }
            MobTemporaryStat mts = mob.getTemporaryStat();
            if (Util.succeedProp(si.getValue(prop, slv))) {
                mts.createAndAddBurnedInfo(chr, skill);
            }
        }
    }

    @Override
    public int getFinalAttackSkill() {
        fortFollowUpAddAttack++;
        if (isBearMode() && chr.hasSkill(FORT_FOLLOW_UP) && fortFollowUpAddAttack >= 4) {
            fortFollowUpAddAttack = 0;
            return FORT_FOLLOW_UP;
        }

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
        Summon summon;
        Field field = chr.getField();
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        switch (skillID) {
            case EKA_EXPRESS: //TODO Eka Express Skill
/*                Field townField = FieldData.getFieldById(chr.getField().getReturnMap());
                int x = townField.getPortalByName("tp").getX();
                int y = townField.getPortalByName("tp").getY();
                Position townPosition = new Position(x, y); // Grabs the Portal Co-ordinates for the TownPortalPoint
                int duration = si.getValue(time, slv);
                if (chr.getTownPortal() != null) {
                    TownPortal townPortal = chr.getTownPortal();
                    townPortal.despawnTownPortal();
                }
                TownPortal townPortal = new TownPortal(chr, townPosition, chr.getPosition(), chr.getField().getReturnMap(), chr.getFieldID(), skillID, duration);
                townPortal.spawnTownPortal();*/
                break;
            case MEOW_CURE:
                tsm.removeAllDebuffs();
                break;
            case MEOW_HEAL:
                chr.heal((int) (chr.getMaxHP() / ((double) 100 / si.getValue(hp, slv))));
                break;
            case MEOW_REVIVE:
                Party party = chr.getParty();
                if (party != null) {
                    Rect rect = chr.getPosition().getRectAround(si.getRects().get(0));
                    if (!chr.isLeft()) {
                        rect = rect.horizontalFlipAround(chr.getPosition().getX());
                    }
                    List<PartyMember> eligblePartyMemberList = field.getPartyMembersInRect(chr, rect).stream().
                            filter(pml -> pml.getChr().getId() != chr.getId() &&
                                    pml.getChr().getHP() <= 0).
                            collect(Collectors.toList());

                    if (eligblePartyMemberList.size() > 0) {
                        Char randomPartyChr = Util.getRandomFromCollection(eligblePartyMemberList).getChr();
                        TemporaryStatManager partyTSM = randomPartyChr.getTemporaryStatManager();
                        randomPartyChr.heal(randomPartyChr.getMaxHP());
                        partyTSM.putCharacterStatValue(NotDamaged, o1);
                        partyTSM.sendSetStatPacket();
                        randomPartyChr.write(UserPacket.effect(Effect.skillAffected(skillID, (byte) 1, 0)));
                        randomPartyChr.getField().broadcastPacket(UserRemote.effect(randomPartyChr.getId(), Effect.skillAffected(skillID, (byte) 1, 0)));
                    }
                }
                break;
            //Common
            case BEAR_MODE:
            case SNOW_LEOPARD_MODE:
            case HAWK_MODE:
            case CAT_MODE:
                o1.nOption = (skillID - 110001500);
                o1.rOption = skillID;
                tsm.putCharacterStatValue(BeastMode, o1);

                for (int modeId : buffsByMode.keySet()) {
                    if (skillID == modeId) {
                        continue;
                    }
                    for (int buffId : buffsByMode.get(modeId)) {
                        tsm.removeStatsBySkill(buffId);
                        tsm.sendResetStatPacket();
                    }
                }
                break;
            case MAPLE_GUARDIAN:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;

            //Bear Mode
            case LIL_FORT:
                summon = Summon.getSummonBy(c.getChr(), skillID, slv);
                summon.setFlyMob(false);
                summon.setSummonTerm(si.getValue(time, slv));
                summon.setMoveAbility(MoveAbility.Stop);
                field.spawnSummon(summon);
                break;
            case BEAR_ASSAULT:
                o2.nOption = si.getValue(z, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(q, slv);
                tsm.putCharacterStatValue(EnrageCr, o2);
                int mobsHit = si.getValue(mobCount, slv);
                int fd = si.getValue(x, slv);
                o1.nOption = (fd * 100) + mobsHit; // dmg 20,  mobsHit 01
                o1.rOption = skillID;
                o1.tOption = si.getValue(q, slv);
                o1.xOption = fd;
                tsm.putCharacterStatValue(Enrage, o1);
                break;
            case MAJESTIC_TRUMPET:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                int xPos = chr.getPosition().getX();
                int yPos = chr.getPosition().getY() + 41;
                aa.setPosition(new Position(xPos, yPos));
                aa.setRect(aa.getRectAround(si.getFirstRect()));
                aa.setDelay((short) 8);
                field.spawnAffectedArea(aa);
                chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                break;

            //Leopard Mode
            case BRO_ATTACK:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(BroAttack, o1);
                break;
            case THUNDER_TRAIL:
                SkillInfo tdi = SkillData.getSkillInfoById(THUNDER_TRAIL);
                AffectedArea aa2 = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa2.setPosition(chr.getPosition());
                Rect rect = tdi.getRects().get(0);
                if (!chr.isLeft()) {
                    rect = rect.moveRight();
                }
                aa2.setRect(aa2.getRectAround(rect));
                aa2.setDelay((short) 4);
                field.spawnAffectedArea(aa2);
                break;

            //Hawk Mode
            case FLY:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(z, slv);
                o1.xOption = (chr.hasSkill(DEFENSIVE_FORMATION) ? DEFENSIVE_FORMATION : 0);
                tsm.putCharacterStatValue(NewFlying, o1);
                break;
            case HAWK_FLOCK:
                o1.nOption = si.getValue(speed, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Speed, o1);
                o2.nOption = si.getValue(jump, slv);
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(Jump, o2);
                break;
            case RAPTOR_TALONS:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                break;
            case BIRDS_EYE_VIEW:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieCr, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieCr, o1);
                o3.nOption = si.getValue(epdd, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EPDD, o3);
                o4.nOption = si.getValue(acc, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ACC, o4);
                o5.nOption = si.getValue(eva, slv);
                o5.rOption = skillID;
                o5.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(EVA, o5);
                break;
            case RAZOR_BEAK:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieMad, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indiePad, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o2);
                break;

            //Cat Mode
            case MEOW_CARD:
            case MEOW_CARD_GOLD_SKILL:
                giveMeowCard(slv);
                break;

            //Hyper
            case TEAM_ROAR:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nOption = 1;
                o2.rOption = skillID;
                o2.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(NotDamaged, o2);
                o3.nOption = 1;
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TeamRoar, o3);
                break;

            case CHAMP_CHARGE_CAT_TILE:
                aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(inPacket.decodePosition());
                rect = chr.getRectAround(si.getFirstRect());
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                aa.setRect(rect);
                aa.setFlip(!chr.isLeft());
                field.spawnAffectedArea(aa);
                break;
            case CUB_CAVALRY:
                List<Integer> cubCavalrySummons = new ArrayList<Integer>() {{
                    add(CUB_CAVALRY_SUMMON_1);
                    add(CUB_CAVALRY_SUMMON_2);
                    add(CUB_CAVALRY_SUMMON_3);
                    add(CUB_CAVALRY_SUMMON_4);
                }};
                rect = chr.getRectAround(si.getFirstRect());
                if (!chr.isLeft()) {
                    rect = rect.horizontalFlipAround(chr.getPosition().getX());
                }
                int mobAmountInRange = chr.getField().getMobsInRect(rect).size();
                int minSummoned = si.getValue(x, slv); // 4
                int maxSummoned = si.getValue(y, slv); // 8
                int additionalSummons = mobAmountInRange / 3;
                int amountSummoned = minSummoned + additionalSummons > maxSummoned ? maxSummoned : minSummoned + additionalSummons;
                for (int i = 0; i < amountSummoned; i++) {
                    int skillId = Util.getRandomFromCollection(cubCavalrySummons);
                    Foothold fh = Util.getRandomFromCollection(field.getFootholdsInRect(rect));
                    summon = Summon.getSummonByNoCTS(chr, skillId, slv);
                    summon.setPosition(fh.getRandomPosition());
                    summon.setCurFoothold((short) field.findFootHoldBelow(summon.getPosition()).getId());
                    summon.setMoveAbility(MoveAbility.WalkAcrossFootHold);
                    summon.setFlip(new Random().nextBoolean());
                    summon.setAssistType(AssistType.WalkAttack);
                    field.spawnAddSummon(summon);
                }
                break;
            case AERIAL_RELIEF_SKILL_USE:
                o1.nValue = 1;
                o1.nReason = AERIAL_RELIEF;
                o1.tTerm = 7; // no time skillStat
                tsm.putCharacterStatValue(IndieNotDamaged, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void handleSkillRemove(Char chr, int skillID) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillID) {
            case FISHY_SLAP:
                SkillInfo si = SkillData.getSkillInfoById(skillID);
                int slv = chr.getSkillLevel(skillID);
                chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                break;
            case AERIAL_RELIEF_SKILL_USE:
                tsm.removeStatsBySkill(AERIAL_RELIEF);
                break;
        }
    }

    public static void beastTamerRegroup(Char chr) { // Handled in JobSkillHandler
        Party party = chr.getParty();
        if (party != null) {
            for (PartyMember pm : party.getOnlineMembers()) {
                Char pmChr = pm.getChr();
                if (pmChr.getId() != chr.getId() && pmChr.getClient().getChannel() == chr.getClient().getChannel() && pmChr.getLevel() > 9) {
                    pmChr.warp(chr.getField());
                    pmChr.write(FieldPacket.teleport(chr.getPosition(), pmChr));
                }
                pmChr.dispose();
            }
        }
    }

    public int alterCooldownSkill(int skillId) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        switch (skillId) {
            case FISHY_SLAP:
            case MAJESTIC_TRUMPET:
                return 0;
        }

        return super.alterCooldownSkill(skillId);
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {

        super.handleHit(c, inPacket, hitInfo);
    }

    public void reviveByBearReborn() { // TODO
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(BEAR_REBORN);
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Bear Reborn.");
        chr.write(UserPacket.effect(Effect.skillAffected(BEAR_REBORN, (byte) 1, 0)));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillAffected(BEAR_REBORN, (byte) 1, 0)));
    }

    @Override
    public void handleLevelUp() {
        super.handleLevelUp();
        short level = chr.getLevel();

        // Beast Scepter Mastery
        if (level >= 40 && level <= 40 + MASTERY_MASTER_LEVEL * 10) {
            int bsmLevel = (level - 30) / 10;
            addSkill(BEAST_SCEPTER_MASTERY , bsmLevel);
        }

        // Growth Spurt
        if (level >= 60 && level <= 60 + GROWTH_MASTER_LEVEL) {
            int gsLevel = (level - 60);
            addSkill(GROWTH_MASTER_LEVEL , gsLevel);
            chr.getScriptManager().createQuestWithQRValue(GROWTH_SPURT_QUEST_LEVEL, Integer.toString(gsLevel));
        }

        switch (level) {
            case 30: { // Beast Scepter Mastery
                addSkill(BEAST_SCEPTER_MASTERY , 0);
                break;
            }
            case 60: { // Growth Spurt
                addSkill(GROWTH_SPURT, 0);
                break;
            }
            case 120: { // Maple Guardian
                addSkill(MAPLE_GUARDIAN , 30);
                break;
            }
            case 150: { // Beastly Resolve
                addSkill(BEASTLY_RESOLVE , 5);
                break;
            }
        }
    }

    private void addSkill(int skillId, int level) {
        Skill skill = SkillData.getSkillDeepCopyById(skillId);
        if (skill != null) {
            int masterLevel = skill.getMasterLevel();
            chr.addSkill(skillId, level, masterLevel);
        } else {
            chr.chatMessage("Can't find skill - %d!", skillId);
        }
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();
        handleJobAdvance(JobConstants.JobEnum.BEAST_TAMER_4.getJobId());
        chr.addSpToJobByCurrentJob(5);
        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        addSkill(110001506, 1); // Guardian Leap
        addSkill(110001510, 1); // Critter Select
        addSkill(110001514, 1); // Homeward Bound
    }
}