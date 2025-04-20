package net.swordie.ms.client.jobs.legend;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.archer.Archer;
import net.swordie.ms.client.jobs.adventurer.archer.BowMaster;
import net.swordie.ms.client.jobs.adventurer.archer.Marksman;
import net.swordie.ms.client.jobs.adventurer.archer.Pathfinder;
import net.swordie.ms.client.jobs.adventurer.magician.Bishop;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.adventurer.magician.IceLightning;
import net.swordie.ms.client.jobs.adventurer.magician.Magician;
import net.swordie.ms.client.jobs.adventurer.pirate.Buccaneer;
import net.swordie.ms.client.jobs.adventurer.pirate.Cannonneer;
import net.swordie.ms.client.jobs.adventurer.pirate.Corsair;
import net.swordie.ms.client.jobs.adventurer.pirate.Pirate;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.adventurer.thief.NightLord;
import net.swordie.ms.client.jobs.adventurer.thief.Shadower;
import net.swordie.ms.client.jobs.adventurer.thief.Thief;
import net.swordie.ms.client.jobs.adventurer.warrior.DarkKnight;
import net.swordie.ms.client.jobs.adventurer.warrior.Hero;
import net.swordie.ms.client.jobs.adventurer.warrior.Paladin;
import net.swordie.ms.client.jobs.adventurer.warrior.Warrior;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import org.python.modules.math;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;
import static net.swordie.ms.life.mob.MobStat.*;

/**
 * Created on 12/14/2017.
 */
public class Phantom extends Job {
    public static final int JUDGMENT_DRAW_1 = 20031209;
    public static final int JUDGMENT_DRAW_2 = 20031210;
    public static final int JUDGMENT_DRAW_AUTO_MANUAL = 20031260;

    public static final int SKILL_SWIPE = 20031207;
    public static final int LOADOUT = 20031208;
    public static final int TO_THE_SKIES = 20031203;
    public static final int DEXTEROUS_TRAINING = 20030206;
    public static final int GHOST_WALK = 20031211;
    public static final int SHROUD_WALK = 20031205;

    public static final int IMPECCABLE_MEMORY_I = 24001001;

    public static final int IMPECCABLE_MEMORY_II = 24101001;
    public static final int CANE_BOOSTER = 24101005; //Buff
    public static final int CARTE_BLANCHE = 24100003;

    public static final int IMPECCABLE_MEMORY_III = 24111001;
    public static final int FINAL_FEINT = 24111002; //Buff (Unlimited Duration) Gone upon Death
    public static final int BAD_LUCK_WARD = 24111003; //Buff
    public static final int CLAIR_DE_LUNE = 24111005; //Buff

    public static final int IMPECCABLE_MEMORY_IV = 24121001;
    public static final int PRIERE_DARIA = 24121004; //Buff
    public static final int VOL_DAME = 24121007; // Special Buff
    public static final int MAPLE_WARRIOR_PH = 24121008; //Buff
    public static final int CARTE_NOIR = 24120002;              //80001890
    public static final int HEROS_WILL_PH = 24121009;
    public static final int PENOMBRE = 24121003;
    public static final int MILLE_AIGUILLES = 24121000;
    public static final int TEMPEST = 24121005;

    public static final int HEROIC_MEMORIES_PH = 24121053;
    public static final int CARTE_ROSE_FINALE = 24121052;

    // V skill
    public static final int LUCK_OF_THE_DRAW = 400041009;
    public static final int LUCK_OF_THE_DRAW_ATOM = 400041010;
    public static final int LUCK_OF_THE_DRAW_RED_CROSS = 400041011;
    public static final int LUCK_OF_THE_DRAW_TREE_OF_LIFE = 400041012;
    public static final int LUCK_OF_THE_DRAW_HOURGLASS = 400041013;
    public static final int LUCK_OF_THE_DRAW_SHARP_SWORD = 400041014;
    public static final int LUCK_OF_THE_DRAW_COMBINED = 400041015;

    public static final int ACE_IN_THE_HOLE = 400041022;
    public static final int ACE_IN_THE_HOLE_ATOM = 400041023;
    public static final int ACE_IN_THE_HOLE_FINISHER = 400041024;

    public static final int PHANTOMS_MARK = 400041040;
    public static final int PHANTOMS_MARK_2 = 400041045;
    public static final int PHANTOMS_MARK_3 = 400041046;

    public static final int CARTE_ATOM = 80001890;

    private static final int[] addedSkills = new int[]{
            JUDGMENT_DRAW_2,
            SKILL_SWIPE,
            LOADOUT,
            TO_THE_SKIES,
            DEXTEROUS_TRAINING,
            JUDGMENT_DRAW_AUTO_MANUAL,
    };

    private boolean isCaneSkill(int skillId) {
        return skillId == 24001000 || skillId == 24101000 || skillId == 24101002 || skillId == 24111000 || skillId == 24111006 || skillId == 24121010 || skillId == MILLE_AIGUILLES;
    }

    private byte cardAmount;
    private byte phantomMarkMille = 0;
    private Set<Job> stealJobHandlers = new HashSet<>();

    public Phantom(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            for (int id : addedSkills) {
                if (!chr.hasSkill(id)) {
                    Skill skill = SkillData.getSkillDeepCopyById(id);
                    skill.setCurrentLevel(skill.getMasterLevel());
                    chr.addSkill(skill);
                }
            }
            stealJobHandlers.add(new Warrior(chr));
            stealJobHandlers.add(new Hero(chr));
            stealJobHandlers.add(new Paladin(chr));
            stealJobHandlers.add(new DarkKnight(chr));

            stealJobHandlers.add(new Magician(chr));
            stealJobHandlers.add(new Bishop(chr));
            stealJobHandlers.add(new IceLightning(chr));
            stealJobHandlers.add(new FirePoison(chr));

            stealJobHandlers.add(new Archer(chr));
            stealJobHandlers.add(new BowMaster(chr));
            stealJobHandlers.add(new Marksman(chr));
            stealJobHandlers.add(new Pathfinder(chr));

            stealJobHandlers.add(new Thief(chr));
            stealJobHandlers.add(new NightLord(chr));
            stealJobHandlers.add(new Shadower(chr));
            stealJobHandlers.add(new BladeMaster(chr));

            stealJobHandlers.add(new Pirate(chr));
            stealJobHandlers.add(new Buccaneer(chr));
            stealJobHandlers.add(new Corsair(chr));
            stealJobHandlers.add(new Cannonneer(chr));
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isPhantom(id);
    }

    private void giveJudgmentDrawBuff(int skillId) {

        Skill skill = chr.getSkill(skillId);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        int randomInt = new Random().nextInt((skillId == JUDGMENT_DRAW_1 ? 2 : 5)) + 1;
        int xOpt = 0;
        switch (randomInt) {
            case 1: // Crit Rate
                xOpt = si.getValue(v, slv);
                break;
            case 2: // Item Drop Rate
                xOpt = si.getValue(w, slv);
                break;
            case 3: // AsrR & TerR
                xOpt = si.getValue(x, slv);
                break;
            case 4: // Defense %
                xOpt = 10;
                break;
            case 5: // Life Drain
                xOpt = 1;
                break;
        }
        chr.write(UserPacket.effect(Effect.avatarOriented("Skill/2003.img/skill/20031210/affected/" + (randomInt - 1))));
        chr.write(UserRemote.effect(chr.getId(), Effect.avatarOriented("Skill/2003.img/skill/20031210/affected/" + (randomInt - 1))));

        o.nOption = randomInt;
        o.rOption = skill.getSkillId();
        o.tOption = si.getValue(time, slv);
        o.xOption = xOpt;
        tsm.putCharacterStatValue(Judgement, o);
        tsm.sendSetStatPacket();
    }


    // Attack related methods ------------------------------------------------------------------------------------------

    @Override
    public void handleAttack(Client c, AttackInfo attackInfo) {
        if (attackInfo.skillId != LUCK_OF_THE_DRAW_ATOM) {
            for (Job jobHandler : stealJobHandlers) {
                jobHandler.handleAttack(c, attackInfo);
            }
        }
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
        if (hasHitMobs
                && attackInfo.skillId != CARTE_NOIR
                && attackInfo.skillId != CARTE_BLANCHE
                && attackInfo.skillId != LUCK_OF_THE_DRAW_ATOM) {
            for (int i = 0; i < attackInfo.mobCount; i++) {
                cartDeck();
                createCarteForceAtom(attackInfo);
            }
            drainLifeByJudgmentDraw();
            if (chr.hasSkill(PHANTOMS_MARK) && hasHitMobs && isCaneSkill(attackInfo.skillId)) {
                setPhantomMarkOnMob(attackInfo);
            }
        }

        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        switch (attackInfo.skillId) {
            case CARTE_ROSE_FINALE:
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                    if (mob == null) {
                        continue;
                    }
                    AffectedArea aa = AffectedArea.getAffectedArea(chr, attackInfo);
                    aa.setPosition(mob.getPosition());
                    aa.setDelay((short) 13);
                    aa.setRect(aa.getPosition().getRectAround(si.getFirstRect()));
                    chr.getField().spawnAffectedArea(aa);
                }
                break;
            case PHANTOMS_MARK:
            case PHANTOMS_MARK_2:
            case PHANTOMS_MARK_3:
                o1.nOption = 1;
                o1.rOption = PHANTOMS_MARK;
                o1.tOption = 3;
                tsm.putCharacterStatValue(IndieNotDamaged, o1);
                tsm.putCharacterStatValue(DarkSight, o1);
                tsm.sendSetStatPacket();
                break;
        }

        super.handleAttack(c, attackInfo);
    }

    private void setPhantomMarkOnMob(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(PHANTOMS_MARK);
        byte slv = (byte) chr.getSkillLevel(PHANTOMS_MARK);

        int count = 1;
        int prevMobId = 0;
        if (attackInfo.skillId == MILLE_AIGUILLES) {
            if (phantomMarkMille < si.getValue(y, slv)) {
                phantomMarkMille++;
                return;
            }
            phantomMarkMille = 0;
        }
        if (tsm.hasStat(PhantomMarkMobStat)) {
            count = tsm.getOption(PhantomMarkMobStat).nOption;
            prevMobId = tsm.getOption(PhantomMarkMobStat).xOption;
        }

        int finalPrevMobId = prevMobId;
        boolean hitsPrevMob = attackInfo.mobAttackInfo.stream().anyMatch(mai -> mai.mobId == finalPrevMobId);
        if (hitsPrevMob) {
            Mob mob = (Mob) chr.getField().getLifeByObjectID(finalPrevMobId);
            if (mob != null) {
                count++;
                o1.nOption = count > si.getValue(s2, slv) ? si.getValue(s2, slv) : count;
                o1.xOption = finalPrevMobId;
            }
        } else {
            o1.nOption = 1;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                o1.xOption = mob.getObjectId();

                if (mob.isBoss()) { //  QoL | If attacking boss, boss will get hit.
                    break;
                }
            }
        }

        o1.rOption = si.getSkillId();
        o1.tOption = si.getValue(time, slv);
        tsm.putCharacterStatValue(PhantomMarkMobStat, o1);
        o2.nOption = 1;
        o2.rOption = si.getSkillId();
        o2.xOption = (tsm.hasStat(PhantomMark) ? (tsm.getOption(PhantomMark).xOption + 1 > si.getValue(q, slv) ? si.getValue(q, slv) : tsm.getOption(PhantomMark).xOption + 1) : 1);
        tsm.putCharacterStatValue(PhantomMark, o2);
        tsm.sendSetStatPacket();
    }

    private void createCarteForceAtom(AttackInfo attackInfo) {
        if (chr.hasSkill(CARTE_BLANCHE)) {
            SkillInfo si = SkillData.getSkillInfoById(CARTE_BLANCHE);
            int anglenum = new Random().nextInt(30) + 295;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                if (mob == null) {
                    continue;
                }
                int TW1prop = 100;
                if (Util.succeedProp(TW1prop)) {
                    if (chr.hasSkill(CARTE_NOIR)) {
                        int mobID = mai.mobId;
                        ForceAtomEnum fae = ForceAtomEnum.PHANTOM_CARD_2;
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 35,
                                anglenum, 0, Util.getCurrentTime(), 1, 0,
                                new Position()); //Slightly behind the player
                        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                                true, mobID, CARTE_NOIR, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), CARTE_NOIR, mob.getPosition(), 0));
                    } else if (chr.hasSkill(CARTE_BLANCHE)) {
                        int mobID = mai.mobId;
                        ForceAtomEnum fae = ForceAtomEnum.PHANTOM_CARD_1;
                        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 40,
                                anglenum, 0, Util.getCurrentTime(), 1, 0,
                                new Position()); //Slightly behind the player
                        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                                true, mobID, CARTE_BLANCHE, forceAtomInfo, new Rect(), 0, 300,
                                mob.getPosition(), CARTE_BLANCHE, mob.getPosition(), 0));
                    }
                }
            }
        }
    }

    private void createCarteForceAtomByJudgmentDraw() {
        if (chr.hasSkill(CARTE_BLANCHE)) {
            SkillInfo si = SkillData.getSkillInfoById(CARTE_BLANCHE);
            Rect rect = new Rect(
                    new Position(
                            chr.getPosition().getX() - 450,
                            chr.getPosition().getY() - 450),
                    new Position(
                            chr.getPosition().getX() + 450,
                            chr.getPosition().getY() + 450)
            );
            List<Mob> mobs = chr.getField().getMobsInRect(rect);
            if (mobs.size() <= 0) {
                chr.dispose();
                return;
            }
            Mob mob = Util.getRandomFromCollection(mobs);

            for (int i = 0; i < 10; i++) {
                if (chr.hasSkill(CARTE_NOIR)) {
                    int mobID = mob.getObjectId();
                    ForceAtomEnum fae = ForceAtomEnum.PHANTOM_CARD_2;
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 35,
                            350 - (2 * i), i * 5, Util.getCurrentTime(), 1, 0,
                            new Position()); //Slightly behind the player
                    chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                            true, mobID, CARTE_NOIR, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), CARTE_NOIR, mob.getPosition(), 0));
                } else if (chr.hasSkill(CARTE_BLANCHE)) {
                    int mobID = mob.getObjectId();
                    ForceAtomEnum fae = ForceAtomEnum.PHANTOM_CARD_1;
                    ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 20, 40,
                            350 - (2 * i), i * 5, Util.getCurrentTime(), 1, 0,
                            new Position()); //Slightly behind the player
                    chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                            true, mobID, CARTE_BLANCHE, forceAtomInfo, new Rect(), 0, 300,
                            mob.getPosition(), CARTE_BLANCHE, mob.getPosition(), 0));
                }
            }
        }
    }

    public void handleCancelKeyDownSkill(Char chr, int skillID) {
        if ( skillID == LUCK_OF_THE_DRAW) {
            drawCardByLuckOfTheDraw();
        } else {
            super.handleCancelKeyDownSkill(chr, skillID);
        }
    }

    private void createAceInTheHoleForceAtom() {
        Field field = chr.getField();
        if (!chr.hasSkill(ACE_IN_THE_HOLE)) {
            return;
        }
        Skill skill = chr.getSkill(ACE_IN_THE_HOLE);
        SkillInfo si = SkillData.getSkillInfoById(ACE_IN_THE_HOLE);
        int slv = skill.getCurrentLevel();
        ForceAtomEnum fae = ForceAtomEnum.ACE_IN_THE_HOLE;

        Mob mob = Util.getRandomFromCollection(field.getMobs());
        ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 4, 4,
                270, 500, Util.getCurrentTime(), 1, 0,
                new Position());
        ForceAtom fa = new ForceAtom(false, 0, chr.getId(), fae,
                true, mob == null ? 0 : mob.getObjectId(), ACE_IN_THE_HOLE_ATOM, forceAtomInfo, chr.getPosition().getRectAround(si.getFirstRect()), 0, 300,
                chr.getPosition(), 0, mob == null ? new Position() : mob.getPosition(), 0);
        fa.setMaxRecreationCount(si.getValue(z, slv));
        chr.createForceAtom(fa);
    }

    public void handleForceAtomCollision(int faKey, int skillId, int mobObjId, Position position, InPacket inPacket) {
        ForceAtom fa = chr.getForceAtomByKey(faKey);
        if (fa != null && fa.getCurRecreationCount(faKey) >= fa.getMaxRecreationCount(faKey) && fa.getSkillId() == ACE_IN_THE_HOLE_ATOM) {
            chr.write(UserLocal.aceInTheHoleFinisher(ACE_IN_THE_HOLE_FINISHER, chr.getSkillLevel(ACE_IN_THE_HOLE), position));
        }
        super.handleForceAtomCollision(faKey, skillId, mobObjId, position, inPacket);
    }

    private void drainLifeByJudgmentDraw() {
        if (!chr.hasSkill(JUDGMENT_DRAW_2)) {
            return;
        }
        Skill skill = chr.getSkill(JUDGMENT_DRAW_2);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (tsm.hasStat(Judgement) && tsm.getOption(Judgement).nOption == 5) {
            int healrate = si.getValue(z, slv);
            chr.heal((int) (chr.getMaxHP() * ((double) healrate / 100)));
        }
    }

    private int getMaxCards() {
        int num = 0;
        if (chr.hasSkill(JUDGMENT_DRAW_1)) {
            num = 20;
        }
        if (chr.hasSkill(JUDGMENT_DRAW_2)) {
            num = 40;
        }
        return num;
    }

    private void resetCardStack() {
        setCardAmount((byte) 0);
    }

    public byte getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(byte cardAmount) {
        this.cardAmount = cardAmount;
        chr.write(UserLocal.incJudgementStack(getCardAmount()));
    }

    private void cartDeck() {
        if (getCardAmount() < getMaxCards()) {
            setCardAmount((byte) (getCardAmount() + 1));
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
        for (Job jobHandler : stealJobHandlers) {
            jobHandler.handleSkill(chr, tsm, skillID, slv, inPacket, skillUseInfo);
        }
        Skill skill = chr.getSkill(skillID);
        SkillInfo si = null;
        if (skill != null) {
            si = SkillData.getSkillInfoById(skillID);
        }
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (skillID) {
            case SHROUD_WALK:
                o1.nOption = 500; //Custom
                o1.rOption = skillID;
                o1.tOption = 10; //Custom
                tsm.putCharacterStatValue(CharacterTemporaryStat.Invisible, o1);
                break;
            case VOL_DAME:
                stealBuffVolDame();
                break;
            case JUDGMENT_DRAW_1:
            case JUDGMENT_DRAW_2:
                createCarteForceAtomByJudgmentDraw();
                giveJudgmentDrawBuff(skillID);
                resetCardStack();
                break;
            case HEROS_WILL_PH:
                tsm.removeAllDebuffs();
                break;
            case ACE_IN_THE_HOLE:
                createAceInTheHoleForceAtom();
                break;
            case PHANTOMS_MARK:
                List<Integer> extraSkillList = new ArrayList<>();
                Objects.requireNonNull(si).getExtraSkillInfo().forEach(map -> extraSkillList.addAll(map.keySet()));
                chr.write(FieldPacket.registerExtraSkill(skillUseInfo.endingPosition, skillID, extraSkillList, skillUseInfo.isLeft));
                tsm.removeStatsBySkill(skillID);
                break;
            case GHOST_WALK:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(DarkSight, o1);
                break;
            case CANE_BOOSTER:
                o1.nValue = -5; // si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieBooster, o1);
                break;
            case FINAL_FEINT:
                o1.nOption = 1;
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(ReviveOnce, o1);
                break;
            case BAD_LUCK_WARD:
                o1.nValue = si.getValue(indieMhpR, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMHPR, o1);
                o2.nValue = si.getValue(indieMmpR, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieMMPR, o2);
                o3.nOption = si.getValue(x, slv);
                o3.rOption = skillID;
                o3.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(AsrR, o3);
                o4.nOption = si.getValue(y, slv);
                o4.rOption = skillID;
                o4.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(TerR, o4);
                break;
            case PENOMBRE:
                o1.nValue = si.getValue(y, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o1);
                break;
            case CLAIR_DE_LUNE:
                o1.nValue = si.getValue(indiePad, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndiePAD, o1);
                o2.nValue = si.getValue(indieAcc, slv);
                o2.nReason = skillID;
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieACC, o2);
                break;
            case PRIERE_DARIA:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(indieIgnoreMobpdpR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieIgnoreMobpdpR, o2);
                break;
            case MAPLE_WARRIOR_PH:
                o1.nValue = si.getValue(x, slv);
                o1.nReason = skillID;
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieStatR, o1);
                break;
            case HEROIC_MEMORIES_PH:
                o1.nReason = skillID;
                o1.nValue = si.getValue(indieDamR, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(IndieDamR, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    private void stealBuffVolDame() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o1 = new Option();

        if (!chr.hasSkill(VOL_DAME)) {
            return;
        }

        Skill skill = chr.getSkill(VOL_DAME);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        Rect rect = new Rect(   //NPE when using the skill's rect
                new Position(
                        chr.getPosition().getX() - 250,
                        chr.getPosition().getY() - 250),
                new Position(
                        chr.getPosition().getX() + 250,
                        chr.getPosition().getY() + 250)
        );
        List<Mob> mobs = chr.getField().getMobsInRect(rect);
        if (mobs.size() <= 0) {
            return;
        }
        MobStat buffFromMobStat = MobStat.Mystery; //Needs to be initialised
        MobStat[] mobStats = new MobStat[]{ //Ordered from Weakest to Strongest, since  the for loop will save the last MobsStat
                PCounter,           //Dmg Reflect 600%
                MCounter,           //Dmg Reflect 600%
                PImmune,            //Dmg Recv -40%
                MImmune,            //Dmg Recv -40%
                PowerUp,            //Attack +40
                MagicUp,            //Attack +40
                MobStat.Invincible, //Invincible for short time
        };
        for (Mob mob : mobs) {
            MobTemporaryStat mts = mob.getTemporaryStat();
            List<MobStat> currentMobStats = Arrays.stream(mobStats).filter(mts::hasCurrentMobStat).collect(Collectors.toList());
            for (MobStat currentMobStat : currentMobStats) {
                if (mts.hasCurrentMobStat(currentMobStat)) {
                    mts.removeMobStat(currentMobStat, true);
                    buffFromMobStat = currentMobStat;
                }
            }
        }
        switch (buffFromMobStat) {
            case PCounter:
            case MCounter:
                o1.nOption = si.getValue(y, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(PowerGuard, o1);
                break;
            case PImmune:
            case MImmune:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(CharacterTemporaryStat.EVA, o1); //as a check to allow for DmgReduction in the Hit Handler
                break;
            case PowerUp:
            case MagicUp:
                o1.nOption = si.getValue(epad, slv);
                o1.rOption = skill.getSkillId();
                o1.tOption = 30;
                tsm.putCharacterStatValue(CharacterTemporaryStat.PAD, o1);
                break;
            case Invincible:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = 5;
                tsm.putCharacterStatValue(NotDamaged, o1);
                break;
        }
        tsm.sendSetStatPacket();
    }

    public void createLuckOfTheDrawForceAtom() {
        Skill skill = chr.getSkill(LUCK_OF_THE_DRAW);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        //Rect rect = chr.getRectAround(si.getFirstRect());
        Rect rect = chr.getRectAround(new Rect(-700, -400, 0, 40));
        if (!chr.isLeft()) {
            rect = rect.horizontalFlipAround(chr.getPosition().getX());
        }
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        ForceAtomEnum fae = ForceAtomEnum.PHANTOM_CARD_2;
        for (int i = 0; i < 14; i++) {
            Mob mob = Util.getRandomFromCollection(chr.getField().getMobsInRect(rect));
            int fImpact = new Random().nextInt(15) + 15;
            int sImpact = new Random().nextInt(5) + 8;
            int angle = new Random().nextInt(60) + 295;
            int fullR = new Random().nextInt(360);
            int radius = new Random().nextInt(20) + 70;
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), fImpact, sImpact,
                    angle, 30 * i, Util.getCurrentTime(), 0, 0,
                    new Position((int) (radius * math.cos(fullR)), (int) (radius * math.sin(fullR))));
            faiList.add(forceAtomInfo);
            targetList.add(mob != null ? mob.getObjectId() : 0);
        }
        chr.createForceAtom(new ForceAtom(false, 0, chr.getId(), fae,
                true, targetList, LUCK_OF_THE_DRAW_ATOM, faiList, rect, 0, 0,
                new Position(), LUCK_OF_THE_DRAW_ATOM, new Position(), 0));

    }

    private void drawCardByLuckOfTheDraw() {
        List<Integer> randomCardSkillId = Arrays.asList(
                LUCK_OF_THE_DRAW_RED_CROSS,
                LUCK_OF_THE_DRAW_TREE_OF_LIFE,
                LUCK_OF_THE_DRAW_HOURGLASS,
                LUCK_OF_THE_DRAW_SHARP_SWORD,
                LUCK_OF_THE_DRAW_COMBINED
        );

        int drawnCard = Util.getRandomFromCollection(randomCardSkillId);
        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        Option o5 = new Option();
        SkillInfo si = SkillData.getSkillInfoById(drawnCard);
        int slv = chr.getSkillLevel(LUCK_OF_THE_DRAW);
        int duration = SkillData.getSkillInfoById(LUCK_OF_THE_DRAW).getValue(dotInterval, slv);
        List<Char> partyChrList = new ArrayList<>();
        if (chr.getParty() != null) {
            partyChrList.addAll(chr.getParty().getPartyMembersInSameField(chr));
        }
        partyChrList.add(chr);
        for (Char partyChr : partyChrList) {
            TemporaryStatManager ptyTsm = partyChr.getTemporaryStatManager();
            switch (drawnCard) {
                case LUCK_OF_THE_DRAW_RED_CROSS:
                    o1.nValue = si.getValue(indieMhpR, slv);
                    o1.nReason = drawnCard;
                    o1.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndieMHPR, o1);
                    healOTByLuckOfTheDraw(ptyTsm, si, slv);
                    break;
                case LUCK_OF_THE_DRAW_TREE_OF_LIFE:
                    o1.nOption = si.getValue(z, slv);
                    o1.rOption = drawnCard;
                    o1.tOption = duration;
                    ptyTsm.putCharacterStatValue(DamageReduce, o1);
                    o2.nValue = si.getValue(indieAsrR, slv);
                    o2.nReason = drawnCard;
                    o2.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndieAsrR, o2);
                    break;
                case LUCK_OF_THE_DRAW_HOURGLASS:
                    for (Map.Entry<Integer, Long> entry : partyChr.getSkillCoolTimes().entrySet()) {
                        int cdSkills = entry.getKey();
                        SkillInfo cdsi = SkillData.getSkillInfoById(cdSkills);
                        if (cdsi != null && !cdsi.isNotCooltimeReset()) {
                            partyChr.reduceSkillCoolTime(cdSkills, (long) (chr.getRemainingCoolTime(cdSkills) * ((double) si.getValue(x, slv) / 100)));
                        }
                    }
                    break;
                case LUCK_OF_THE_DRAW_SHARP_SWORD:
                    o1.nValue = si.getValue(indiePMdR, slv);
                    o1.nReason = drawnCard;
                    o1.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndiePMdR, o1);
                    break;
                case LUCK_OF_THE_DRAW_COMBINED:
                    o1.nValue = si.getValue(indieMhpR, slv);
                    o1.nReason = drawnCard;
                    o1.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndieMHPR, o1);
                    o2.nOption = si.getValue(z, slv);
                    o2.rOption = drawnCard;
                    o2.tOption = duration;
                    ptyTsm.putCharacterStatValue(DamageReduce, o2);
                    o3.nValue = si.getValue(indieAsrR, slv);
                    o3.nReason = drawnCard;
                    o3.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndieAsrR, o3);
                    o4.nValue = si.getValue(indiePMdR, slv);
                    o4.nReason = drawnCard;
                    o4.tTerm = duration;
                    ptyTsm.putCharacterStatValue(IndiePMdR, o4);
                    for (Map.Entry<Integer, Long> entry : partyChr.getSkillCoolTimes().entrySet()) {
                        int cdSkills = entry.getKey();
                        SkillInfo cdsi = SkillData.getSkillInfoById(cdSkills);
                        if (cdsi != null && !cdsi.isNotCooltimeReset()) {
                            partyChr.reduceSkillCoolTime(cdSkills, (long) (chr.getRemainingCoolTime(cdSkills) * ((double) si.getValue(x, slv) / 100)));
                        }
                    }
                    break;
            }
            if (chr == partyChr) {
                o5.nValue = 1;
                o5.nReason = LUCK_OF_THE_DRAW;
                o5.tTerm = 2;
                ptyTsm.putCharacterStatValue(IndieNotDamaged, o5);
            }
            ptyTsm.sendSetStatPacket();
        }
        for (int skill : Arrays.asList(LUCK_OF_THE_DRAW, drawnCard)) {
            chr.write(FieldPacket.fieldEffect(FieldEffect.getOffFieldEffectFromWz(String.format("Skill/40004.img/skill/%d/screen", skill), 0)));
            chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.avatarOriented(String.format("Skill/40004.img/skill/%d/screen", skill))));
        }
    }

    private void healOTByLuckOfTheDraw(TemporaryStatManager tsm, SkillInfo si, int slv) {
        if (tsm.getOptByCTSAndSkill(IndieMHPR, LUCK_OF_THE_DRAW_RED_CROSS) != null) {
            int healR = (int) ((double) (chr.getMaxHP() * si.getValue(y, slv)) / 100F);
            tsm.getChr().heal(healR);
            tsm.getChr().healMP(healR);

            EventManager.addEvent(() -> healOTByLuckOfTheDraw(tsm, si, slv), 2, TimeUnit.SECONDS);
        }
    }

    public void handleSkillRemove(Char chr, int skillID) {
        switch (skillID) {
            case TEMPEST:
                Skill skill = chr.getSkill(skillID);
                if (skill != null) {
                    SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
                    int slv = skill.getCurrentLevel();
                    chr.addSkillCoolTime(skillID, si.getValue(cooltime, slv) * 1000);
                }
                break;
        }
    }


    // Hit related methods ---------------------------------------------------------------------------------------------

    @Override
    public void handleHit(Client c, InPacket inPacket, HitInfo hitInfo) {
        for (Job jobHandler : stealJobHandlers) {
            jobHandler.handleHit(c, inPacket, hitInfo);
        }
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(VOL_DAME)) {
            return;
        }
        if (tsm.getOptByCTSAndSkill(CharacterTemporaryStat.EVA, VOL_DAME) != null) {
            Skill skill = chr.getSkill(VOL_DAME);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int dmgPerc = si.getValue(x, skill.getCurrentLevel());
            int dmg = hitInfo.hpDamage;
            hitInfo.hpDamage = dmg - (dmg * (dmgPerc / 100));
        }

        super.handleHit(c, inPacket, hitInfo);
    }

    public void reviveByFinalFeint() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Skill skill = chr.getSkill(FINAL_FEINT);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();

        chr.heal(chr.getMaxHP());
        tsm.removeStatsBySkill(skill.getSkillId());
        tsm.sendResetStatPacket();
        chr.chatMessage("You have been revived by Final Feint.");
        chr.write(UserPacket.effect(Effect.skillSpecial(skill.getSkillId())));
        chr.getField().broadcastPacket(UserRemote.effect(chr.getId(), Effect.skillSpecial(skill.getSkillId())));

        o.nOption = 1;
        o.rOption = skill.getSkillId();
        o.tStart = si.getValue(y, slv);
        tsm.putCharacterStatValue(NotDamaged, o);
        tsm.sendSetStatPacket();
    }

    @Override
    public void setCharCreationStats(Char chr) {
        super.setCharCreationStats(chr);

        chr.setStolenSkills(new HashSet<>());
        chr.setChosenSkills(new HashSet<>());
//        chr.getAvatarData().getCharacterStat().setPosMap(915000000);
    }

    @Override
    public void handleJobStart() {
        super.handleJobStart();

        handleJobAdvance(JobConstants.JobEnum.PHANTOM_1.getJobId());

        handleJobEnd();
    }

    @Override
    public void handleJobEnd() {
        super.handleJobEnd();

        // Skills
        chr.addSkill(SHROUD_WALK, 1, 1);

        // Items
        chr.forceUpdateSecondary(null, ItemData.getItemDeepCopy(1352100)); // Carte Magique
    }
}

