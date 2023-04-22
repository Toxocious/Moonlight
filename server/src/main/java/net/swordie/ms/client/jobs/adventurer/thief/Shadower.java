package net.swordie.ms.client.jobs.adventurer.thief;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.Skill;
import net.swordie.ms.client.character.skills.info.*;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.handlers.EventManager;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.life.mob.MobStat;
import net.swordie.ms.life.mob.MobTemporaryStat;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

import static net.swordie.ms.client.character.skills.SkillStat.*;
import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.*;

public class Shadower extends Thief {

    public static final int CRITICAL_GROWTH = 4200013; //Passive Crit increasing buff
    public static final int MESOGUARD = 4201011; //Buff
    public static final int STEAL = 4201004; //Special Attack (Steal Debuff)?
    public static final int DAGGER_BOOSTER = 4201002; //Buff


    public static final int VENOM_SHAD = 4210010; //Passive DoT
    public static final int MESO_EXPLOSION_ATOM = 4210014; // ?
    public static final int ADVANCED_DARK_SIGHT_SHAD = 4210015;
    public static final int INTO_DARKNESS = 4211016;
    public static final int SHADOW_PARTNER_SHAD = 4211008; //Buff
    public static final int DARK_FLARE_SHAD = 4211007; //Summon
    public static final int MESO_EXPLOSION = 4211006; //CreateForceAtom Attack
    public static final int PICK_POCKET = 4211003; //Buff


    public static final int BOOMERANG_STAB = 4221007; //Special Attack (Stun Debuff)
    public static final int MAPLE_WARRIOR_SHAD = 4221000; //Buff
    public static final int SUDDEN_RAID_SHAD = 4221010; //Special Attack
    public static final int MESO_EXPLOSION_ENHANCE = 4220045;
    public static final int SMOKE_SCREEN = 4221006; //Affected Area
    public static final int PRIME_CRITICAL = 4220015; //Passive Buff
    public static final int TOXIC_VENOM_SHAD = 4220011; //Passive DoT
    public static final int SHADOWER_INSTINCT = 4221013; //Buff //Stacks (Body Count)
    public static final int HEROS_WILL_SHAD = 4221008;
    public static final int ASSASSINATE = 4221014;
    public static final int ASSASSINATE_FINISHER = 4221016;
    public static final int FLIP_THE_COIN = 4221054;
    public static final int EPIC_ADVENTURE_SHAD = 4221053;


    // V skills
    public static final int SHADOW_ASSAULT_4 = 400041005;
    public static final int SHADOW_ASSAULT_3 = 400041004;
    public static final int SHADOW_ASSAULT_2 = 400041003;
    public static final int SHADOW_ASSAULT = 400041002;
    public static final int TRICKBLADE = 400041025;
    public static final int TRICKBLADE_FINISHER = 400041026;
    public static final int TRICKBLADE_MOB_ATTACK = 400041027;
    public static final int SONIC_BLOW = 400041039;

    private ScheduledFuture critGrowthTimer;
    private boolean canFlipTheCoin = false;

    public Shadower(Char chr) {
        super(chr);
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            if (critGrowthTimer != null && !critGrowthTimer.isDone()) {
                critGrowthTimer.cancel(true);
            }
            critGrowthTimer = EventManager.addFixedRateEvent(this::incrementCritGrowth, 2000, 2000);
        }
    }

    @Override
    public void handleRecoverySchedulers() {
        if (chr != null && chr.getId() != 0 && isHandlerOfJob(chr.getJob())) {
            if (!chr.getRecoverySchedules().containsKey(chr.getJob())) {
                final ScheduledFuture criticalGrowth = EventManager.addFixedRateEvent(this::incrementCritGrowth, 2000, 2000);

                chr.getRecoverySchedules().put(chr.getJob(), criticalGrowth);
            }
        }
    }

    @Override
    public boolean isHandlerOfJob(short id) {
        return JobConstants.isShadower(id);
    }

    private void incrementFlipTheCoinStack() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        Option o2 = new Option();
        SkillInfo FlipTheCoinInfo = SkillData.getSkillInfoById(FLIP_THE_COIN);
        int amount = 1;
        if (tsm.hasStat(CharacterTemporaryStat.FlipTheCoin)) {
            amount = tsm.getOption(CharacterTemporaryStat.FlipTheCoin).nOption;
            if (amount < FlipTheCoinInfo.getValue(y, 1)) {
                amount++;
            }
        }
        o.nOption = amount;
        o.rOption = FLIP_THE_COIN;
        o.tOption = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(CharacterTemporaryStat.FlipTheCoin, o);

        //Stats
        o1.nReason = FLIP_THE_COIN;
        o1.nValue = (amount * FlipTheCoinInfo.getValue(x, 1));
        o1.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndieCr, o1);
        o2.nReason = FLIP_THE_COIN;
        o2.nValue = (amount * FlipTheCoinInfo.getValue(indieDamR, 1));
        o2.tTerm = FlipTheCoinInfo.getValue(time, 1);
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndieDamR, o2);
        tsm.sendSetStatPacket();
    }

    private void activateFlipTheCoin() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        long totalCrit = (tsm.getBaseStats().get(BaseStat.cr) == null ? 0 : tsm.getBaseStats().get(BaseStat.cr)) + chr.getBaseStats().get(BaseStat.cr);
        if ((tsm.hasStat(CharacterTemporaryStat.FlipTheCoin) || tsm.getOption(CharacterTemporaryStat.FlipTheCoin).nOption < 5)
                && Util.succeedProp((int) (totalCrit > 100 ? 100 : totalCrit))
                && !canFlipTheCoin) {
            c.write(WvsContext.flipTheCoinEnabled((byte) 1));
            canFlipTheCoin = true;
        }
    }

    private void incrementCritGrowth() {
        if (chr.hasSkill(CRITICAL_GROWTH) || chr.hasSkill(PRIME_CRITICAL)) {
            TemporaryStatManager tsm = chr.getTemporaryStatManager();
            SkillInfo si = SkillData.getSkillInfoById(PRIME_CRITICAL);
            int slv = chr.getSkillLevel(PRIME_CRITICAL);
            Option o = new Option();
            int amountCr = (chr.hasSkill(PRIME_CRITICAL) ? si.getValue(x, slv) : 3);
            int amountCd = 1;
            if (tsm.hasStat(CharacterTemporaryStat.CriticalGrowing)) {
                amountCr = tsm.getOption(CharacterTemporaryStat.CriticalGrowing).nOption;
                amountCd = tsm.getOption(CharacterTemporaryStat.CriticalGrowing).xOption;
                long totalCrit = (tsm.getBaseStats().get(BaseStat.cr) == null ? 0 : tsm.getBaseStats().get(BaseStat.cr)) + chr.getBaseStats().get(BaseStat.cr);
                if (totalCrit < 100) {
                    amountCr = amountCr + (chr.hasSkill(PRIME_CRITICAL) ? si.getValue(x, slv) : 3);
                    amountCd = amountCd + (chr.hasSkill(PRIME_CRITICAL) ? si.getValue(w, slv) : 1);
                } else {
                    amountCr = 0;
                    amountCd = 0;
                }
            }
            o.nOption = amountCr;
            o.rOption = chr.hasSkill(PRIME_CRITICAL) ? PRIME_CRITICAL : CRITICAL_GROWTH;
            o.xOption = amountCd > 10 ? 10 : amountCd;
            tsm.putCharacterStatValue(CharacterTemporaryStat.CriticalGrowing, o);
            tsm.sendSetStatPacket();
        }
    }

    private void incrementShadowInstinct() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Option o = new Option();
        Option o1 = new Option();
        SkillInfo InstinctInfo = SkillData.getSkillInfoById(SHADOWER_INSTINCT);
        Skill skill = chr.getSkill(SHADOWER_INSTINCT);
        int slv = skill.getCurrentLevel();
        int amount = 1;
        if (tsm.hasStat(CharacterTemporaryStat.KillingPoint)) {
            if (chr.hasSkill(SHADOWER_INSTINCT)) {
                amount = tsm.getOption(CharacterTemporaryStat.KillingPoint).nOption;
                if (amount < 5) {
                    amount++;
                } else {
                    return;
                }
            }
        }
        o.nOption = amount;
        o.rOption = skill.getSkillId();
        tsm.putCharacterStatValue(CharacterTemporaryStat.KillingPoint, o);
        o1.nReason = skill.getSkillId();
        o1.nValue = (amount * InstinctInfo.getValue(kp, slv));
        tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePAD, o1);
        tsm.sendSetStatPacket();
    }

    private void updatePickPocketCounter() {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        if (!chr.hasSkill(PICK_POCKET) || !tsm.hasStat(CharacterTemporaryStat.PickPocket)) {
            return;
        }
        Option o = new Option();
        Skill skill = chr.getSkill(PICK_POCKET);
        SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
        int slv = skill.getCurrentLevel();
        int maxMesoCount = si.getValue(y, slv) + (chr.hasSkill(MESO_EXPLOSION_ENHANCE) ? 5 : 0);
        if (getMesoDropsByPickPocket() < maxMesoCount) {
            o.nOption = 1;
            o.rOption = skill.getSkillId();
            o.xOption = getMesoDropsByPickPocket();
            tsm.putCharacterStatValue(CharacterTemporaryStat.PickPocket, o);
            tsm.sendSetStatPacket();
        }
    }

    private int getMesoDropsByPickPocket() {
        return (int) chr.getField().getDrops().stream().filter(Drop::isByPickPocket).count();
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

            // Critical Growth & Prime Critical
            if (chr.hasSkill(CRITICAL_GROWTH)) {
                incrementCritGrowth();
            }
            // Pick Pocket
            if (!SkillConstants.isForceAtomSkill(attackInfo.skillId)) {
                dropFromPickPocket(attackInfo);
            }
            // Shadower Instinct
            if (chr.hasSkill(SHADOWER_INSTINCT) && tsm.hasStat(CharacterTemporaryStat.IndieIgnoreMobpdpR)) {
                incrementShadowInstinct();
            }
            // Shadower Hyper
            if (chr.hasSkill(FLIP_THE_COIN)) {
                activateFlipTheCoin();
            }
            // Updated PickPocket Counter
            updatePickPocketCounter();

        }


        Option o1 = new Option();
        Option o2 = new Option();
        Option o3 = new Option();
        Option o4 = new Option();
        switch (attackInfo.skillId) {
            case STEAL:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
                o1.tOption = si.getValue(time, slv);
                for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                    if (Util.succeedProp(si.getValue(prop, slv))) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(mai.mobId);
                        if (mob == null) {
                            continue;
                        }
                        MobTemporaryStat mts = mob.getTemporaryStat();
                        Field field = chr.getField();
                        int itemId = 2431835;
                        if (mob.isBoss()) {
                            itemId = 2431850;
                        }
                        Item item = ItemData.getItemDeepCopy(itemId);
                        Drop drop = new Drop(item.getItemId(), item);
                        field.drop(drop, mob.getPosition());

                        if (!mob.isBoss()) {
                            mts.addStatOptionsAndBroadcast(MobStat.Stun, o1);
                        }
                    }
                }
                break;
            case BOOMERANG_STAB:
                o1.nOption = 1;
                o1.rOption = skill.getSkillId();
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
            case ASSASSINATE_FINISHER:
                if (!hasHitMobs) {
                    return;
                }
                if (chr.hasSkill(TRICKBLADE)) {
                    int count = 1;
                    int prevMobId = 0;
                    if (tsm.hasStat(CharacterTemporaryStat.TrickBladeMobStat)) {
                        count = tsm.getOption(CharacterTemporaryStat.TrickBladeMobStat).nOption;
                        prevMobId = tsm.getOption(CharacterTemporaryStat.TrickBladeMobStat).xOption;
                    }

                    int finalPrevMobId = prevMobId;
                    boolean hitsPrevMob = attackInfo.mobAttackInfo.stream().anyMatch(mai -> mai.mobId == finalPrevMobId);
                    if (hitsPrevMob) {
                        Mob mob = (Mob) chr.getField().getLifeByObjectID(finalPrevMobId);
                        if (mob != null) {
                            count++;
                            o1.nOption = count > 3 ? 3 : count;
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
                }
                o1.rOption = attackInfo.skillId;
                o1.tOption = 10;
                tsm.putCharacterStatValue(CharacterTemporaryStat.TrickBladeMobStat, o1);
                tsm.sendSetStatPacket();
                break;
            case TRICKBLADE_FINISHER:
                tsm.removeStatsBySkill(ASSASSINATE_FINISHER);

                si = SkillData.getSkillInfoById(TRICKBLADE);
                slv = (byte) chr.getSkillLevel(TRICKBLADE);
                o1.nOption = 1;
                o1.rOption = TRICKBLADE;
                o1.tOption = si.getValue(s, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.NotDamaged, o1);
                tsm.sendSetStatPacket();
            case TRICKBLADE_MOB_ATTACK:
                si = SkillData.getSkillInfoById(TRICKBLADE);
                slv = (byte) chr.getSkillLevel(TRICKBLADE);
                chr.addSkillCoolTime(TRICKBLADE, si.getValue(cooltime, slv) * 1000);
                break;
        }
        if (!JobConstants.isPhantom(chr.getJob())) {
            super.handleAttack(c, attackInfo);
        }
    }



    private void createMesoExplosionForceAtom(List<Drop> droplist) {
        if (!chr.hasSkill(MESO_EXPLOSION)) {
            return;
        }
        Field field = chr.getField();
        ForceAtomEnum fae = ForceAtomEnum.FLYING_MESO;
        List<Integer> targetList = new ArrayList<>();
        List<ForceAtomInfo> faiList = new ArrayList<>();
        for (Drop drop : droplist) {
            Mob mob = Util.getRandomFromCollection(field.getMobs());
            ForceAtomInfo forceAtomInfo = new ForceAtomInfo(chr.getNewForceAtomKey(), fae.getInc(), 2, 3,
                    0, 0, Util.getCurrentTime(), 1, 0,
                    drop.getPosition());
            targetList.add(mob != null ? mob.getObjectId() : 0);
            faiList.add(forceAtomInfo);
        }
        chr.createForceAtom(new ForceAtom(false, chr.getId(), chr.getId(), fae,
                true, targetList, MESO_EXPLOSION_ATOM, faiList, new Rect(), 0, 300,
                new Position(), MESO_EXPLOSION_ATOM, new Position(), 0));
    }

    private void dropFromPickPocket(AttackInfo attackInfo) {
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        Field field = chr.getField();
        if (!chr.hasSkill(PICK_POCKET) || tsm.getOptByCTSAndSkill(CharacterTemporaryStat.PickPocket, PICK_POCKET) == null) {
            return;
        }
        for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
            Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
            if (mob == null) {
                continue;
            }
            Skill skill = chr.getSkill(PICK_POCKET);
            SkillInfo si = SkillData.getSkillInfoById(skill.getSkillId());
            int slv = skill.getCurrentLevel();
            int maxMesoCount = si.getValue(y, slv) + (chr.hasSkill(MESO_EXPLOSION_ENHANCE) ? 5 : 0);
            int proc = si.getValue(prop, slv) + (chr.hasSkill(MESO_EXPLOSION_ENHANCE) ? 10 : 0);
            if (Util.succeedProp(proc) && getMesoDropsByPickPocket() < maxMesoCount) {
                Drop drop = new Drop(-1, 1);
                drop.setByPickPocket(true);
                drop.setOwnerID(chr.getId());
                field.drop(drop, mob.getPosition());
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
            case SMOKE_SCREEN:
                AffectedArea aa = AffectedArea.getPassiveAA(chr, skillID, slv);
                aa.setPosition(chr.getPosition());
                aa.setRect(aa.getPosition().getRectAround(si.getRects().get(0)));
                aa.setDelay((short) 4);
                chr.getField().spawnAffectedArea(aa);
                break;
            case MESO_EXPLOSION:
                Field field = chr.getField();
                int rectRange = si.getValue(range, slv);
                Rect rect = new Rect(
                        new Position(
                                chr.getPosition().getX() - rectRange,
                                chr.getPosition().getY() - rectRange),
                        new Position(
                                chr.getPosition().getX() + rectRange,
                                chr.getPosition().getY() + rectRange)
                );
                List<Drop> dropList = field.getDropsInRect(rect).stream()
                        .filter(d -> d.isByPickPocket()
                                && d.getOwnerID() == chr.getId()
                                && d.isMoney())
                        .collect(Collectors.toList());
                createMesoExplosionForceAtom(dropList);
                break;
            case INTO_DARKNESS:
                handleSkill(chr, tsm, DARK_SIGHT, chr.getSkillLevel(DARK_SIGHT), inPacket, new SkillUseInfo());
                break;
            case MESOGUARD:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.MesoGuard, o1);
                break;
            case PICK_POCKET:
                o1.nOption = si.getValue(x, slv);
                o1.rOption = skillID;
                o1.tOption = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.PickPocket, o1);
                break;
            case SHADOWER_INSTINCT:
                o1.nReason = skillID;
                o1.nValue = si.getValue(x, slv);
                o1.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndiePAD, o1);
                o2.nReason = skillID;
                o2.nValue = si.getValue(ignoreMobpdpR, slv);
                o2.tTerm = si.getValue(time, slv);
                tsm.putCharacterStatValue(CharacterTemporaryStat.IndieIgnoreMobpdpR, o2);
                break;
            case FLIP_THE_COIN:
                incrementFlipTheCoinStack();
                canFlipTheCoin = false;
                c.write(WvsContext.flipTheCoinEnabled((byte) 0));
                break;
            case SHADOW_ASSAULT:
            case SHADOW_ASSAULT_2:
            case SHADOW_ASSAULT_3:
                o1.nOption = SHADOW_ASSAULT_4 - skillID;
                o1.rOption = SHADOW_ASSAULT;
                tsm.putCharacterStatValue(CharacterTemporaryStat.ShadowAssault, o1);
                break;
            case SHADOW_ASSAULT_4:
                tsm.removeStatsBySkill(SHADOW_ASSAULT);
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
//                handleJobAdvance(JobConstants.JobEnum.CHIEF_BANDIT.getJobId());
//                break;
//            case 100:
//                handleJobAdvance(JobConstants.JobEnum.SHADOWER.getJobId());
//                break;
//        }
    }

    @Override
    public void cancelTimers() {
        if (critGrowthTimer != null) {
            critGrowthTimer.cancel(false);
        }
        super.cancelTimers();
    }
}
