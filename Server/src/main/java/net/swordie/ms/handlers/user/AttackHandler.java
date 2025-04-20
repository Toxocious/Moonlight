package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.skills.ProcessType;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.character.skills.info.SkillUseInfo;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.jobs.Job;
import net.swordie.ms.client.jobs.adventurer.magician.FirePoison;
import net.swordie.ms.client.jobs.cygnus.BlazeWizard;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.client.jobs.nova.AngelicBuster;
import net.swordie.ms.client.jobs.nova.Cadena;
import net.swordie.ms.client.jobs.nova.Kaiser;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.*;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ChatType;
import net.swordie.ms.enums.FieldOption;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Rect;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static net.swordie.ms.enums.ChatType.*;

public class AttackHandler {

    private static final Logger log = Logger.getLogger(AttackHandler.class);

    // No handler, gets called from other handlers
    private static void handleAttack(Client c, AttackInfo attackInfo) {
        Char chr = c.getChr();
        int skillID = attackInfo.skillId;
        Field field = chr.getField();
        if (skillID != 0 && (field.getFieldLimit() & FieldOption.SkillLimit.getVal()) > 0
                || (field.getFieldLimit() & FieldOption.MoveSkillOnly.getVal()) > 0
        ) {
            // don't allow skills on maps where it's not allowed
            // ignore the damage from the level up skill, instakills buffed mobs
            return;
        }
        boolean noCoolTimeAttackHeader =
                attackInfo.attackHeader == OutHeader.SUMMONED_ATTACK ||     // Summon Attack
                        attackInfo.inHeader == InHeader.USER_AREA_DOT_ATTACK ||
                        attackInfo.inHeader == InHeader.USER_AFFECTED_AREA_FOR_SCREEN_ATTACK;   ;       // Affected Area Attack
        boolean multiAttack = SkillConstants.isMultiAttackCooldownSkill(skillID);
        boolean extraSkills = SkillConstants.isExtraSkill(skillID);
        if (!attackInfo.byUnreliableMemory && !noCoolTimeAttackHeader && !multiAttack && (!SkillConstants.isNoMPConsumeSkill(skillID) && !chr.applyMpCon(attackInfo.skillId, attackInfo.slv))) {
            return;
        }
        if (SkillConstants.isDelayedCooldownSkill(skillID) ||  attackInfo.byUnreliableMemory || noCoolTimeAttackHeader || multiAttack || chr.hasSkillCDBypass() || (SkillConstants.isKeyDownSkill(skillID) || chr.checkAndSetSkillCooltime(skillID)) || extraSkills) {
            int slv = attackInfo.slv;
             chr.chatMessage(Mob, "SkillID: " + skillID); // removed for now
            Job sourceJobHandler = chr.getJobHandler();
            SkillInfo si = SkillData.getSkillInfoById(skillID);
            if (si != null && si.getExtraSkillInfo().size() > 0) {
                List<Integer> extraSkillList = new ArrayList<>();
                si.getExtraSkillInfo().forEach(map -> extraSkillList.addAll(map.keySet()));
                chr.write(FieldPacket.registerExtraSkill(chr, skillID, extraSkillList));
            }
            if (si != null && si.isMassSpell() && chr.getParty() != null) {
                Rect r = si.getFirstRect();
                if (r != null) {
                    Rect rectAround = chr.getRectAround(r);
                    for (Char ptChr : chr.getParty().getPartyMembersInField(chr)) {
                        if (rectAround.hasPositionInside(ptChr.getPosition())) {
                            Effect effect = Effect.skillAffected(skillID, slv, 0);
                            if (ptChr != chr) {  // Caster shouldn't get the Affected Skill Effect
                                chr.getField().broadcastPacket(UserRemote.effect(ptChr.getId(), effect), ptChr);
                                ptChr.write(UserPacket.effect(effect));
                                if (si != null && !si.isFinalAttack()) {
                                    sourceJobHandler.handleAttack(c, attackInfo);
                                }
                            }

                        }
                    }
                }
            }
            if (si != null && !si.isFinalAttack()) {
                sourceJobHandler.handleAttack(c, attackInfo);
            }

            // Final Attack Request
            int faSkill = sourceJobHandler.getFinalAttackSkill();
            if (faSkill > 0) {
                chr.write(FieldPacket.finalAttackRequest(chr, skillID, faSkill));
            }

            if (attackInfo.attackHeader != null) {
                switch (attackInfo.attackHeader) {
                    case SUMMONED_ATTACK:
                        chr.getField().broadcastPacket(Summoned.summonedAttack(chr.getId(), attackInfo, false), chr);
                        if (chr.getCopy() != null) {
                            Summon oldSummon = attackInfo.summon;
                            int oldOID = oldSummon.getObjectId();
                            oldSummon.setObjectId(13371337);
                            Char oldChr = oldSummon.getChr();
                            oldSummon.setChr(chr.getCopy());
                            chr.write(Summoned.summonedAttack(chr.getCopy().getId(), attackInfo, false));
                            oldSummon.setChr(oldChr);
                            oldSummon.setObjectId(oldOID);
                        }
                        break;
                    case FAMILIAR_ATTACK:
                        chr.getField().broadcastPacket(CFamiliar.familiarAttack(chr.getId(), attackInfo), chr);
                        break;
                    default:
                        chr.getField().broadcastPacket(UserRemote.attack(chr, attackInfo), chr);
                        Char copy = chr.getCopy();
                        if (copy != null) {
                            chr.write(UserRemote.attack(copy, attackInfo));
                        }
                }
            }
            Summon summon = attackInfo.summon;
            if (attackInfo.attackHeader == OutHeader.SUMMONED_ATTACK && summon.isDeleteOnNextAttack()) {
                summon.getField().removeLife(summon);
            }

            int multiKillMessage = 0;
            long mobexp = 0;
            for (MobAttackInfo mai : attackInfo.mobAttackInfo) {
                Mob mob = (Mob) field.getLifeByObjectID(mai.mobId);
                mai.mob = mob; // used for damage calculation later on

                if (mob == null) {
                    chr.chatMessage(Mob, String.format("Wrong attack info parse (probably)! SkillID = %d, Mob ID = %d", skillID, mai.mobId));
                    // Lol :p
                } else if (mob.getHp() > 0) {
                    long totalDamage = 0;
                    for (long dmg : mai.damages) {
                        //totalDamage += dmg;
                        mob.damage(chr, dmg);
                        mob.handleDamageReflect(chr, skillID, dmg);
                    }
//                    totalDamage = (long) (totalDamage / 2 + (((totalDamage / 2) / 100) * chr.getAvatarData().getCharacterStat().getPierce()));
                    //mob.damage(chr, totalDamage);
                    //mob.handleDamageReflect(chr, skillID, totalDamage);
                    //TODO Horntail sponge damage, should make a separate function
                    if ((mob.getTemplateId() >= 8810202 && mob.getTemplateId() <= 8810209)) {
                        Life life = field.getLifeByTemplateId(8810214);
                        if (life != null) {
                            Mob mob2 = (Mob) life;
                            mob2.damage(chr, totalDamage);
                            field.broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob2)));
                        }
                    }
                    if ((mob.getTemplateId() >= 8810002 && mob.getTemplateId() <= 8810009)) {
                        Life life2 = field.getLifeByTemplateId(8810018);
                        if (life2 != null) {
                            Mob mob2 = (Mob) life2;
                            mob2.damage(chr, totalDamage);
                            field.broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob2)));
                        }
                    }
                    if ((mob.getTemplateId() >= 8810102 && mob.getTemplateId() <= 8810109)) {
                        Life life3 = field.getLifeByTemplateId(8810118);
                        if (life3 != null) {
                            Mob mob3 = (Mob) life3;
                            mob3.damage(chr, totalDamage);
                            field.broadcastPacket(FieldPacket.fieldEffect(FieldEffect.mobHPTagFieldEffect(mob3)));
                        }
                    }
                }
                if (mob != null && mob.getHp() <= 0) {

                    mob.onKilledByChar(chr);
                    // MultiKill +1,  per killed mob
                    multiKillMessage++;
                    mobexp = mob.getForcedMobStat().getExp();
                }
            }
            // damage check
            chr.getDamageCalc().checkDamage(attackInfo);

            // MultiKill Message Popup & Exp
            if (multiKillMessage > 2) {
                int bonusExpMultiplier = (multiKillMessage - 2) * 5;
                long totalBonusExp = 0;
                if (chr.getLevel() < GameConstants.charExp.length - 1) {
                    totalBonusExp = (long) (mobexp * (bonusExpMultiplier * GameConstants.MULTI_KILL_BONUS_EXP_MULTIPLIER));
                }
                chr.write(UserLocal.comboCounter((byte) 0, (int) totalBonusExp, multiKillMessage > 10 ? 10 : multiKillMessage, 1));
                chr.addExpNoMsg(totalBonusExp);
            }
        } else {
            chr.chatMessage("This skill is not handled: " + skillID);
        }
    }


    @Handler(op = InHeader.USER_BODY_ATTACK)
    public static void handleBodyAttack(Client c, InPacket inPacket) {
        AttackInfo ai = new AttackInfo();
        SkillUseInfo skillUseInfo = new SkillUseInfo();
        ai.attackHeader = OutHeader.REMOTE_BODY_ATTACK;
        ai.fieldKey = inPacket.decodeByte();
        byte mask = inPacket.decodeByte();
        ai.hits = (byte) (mask & 0xF);
        ai.mobCount = (mask >>> 4) & 0xF;
        inPacket.decodeInt(); // 0
        ai.skillId = inPacket.decodeInt();
        ai.slv = inPacket.decodeInt();
        inPacket.decodeInt(); // crc

        int idk199 = inPacket.decodeInt(); // new 199
        int idk203 = inPacket.decodeInt(); // unk 203


        // START UNKNOWNATTACKPACKETDATA
        int unk1 = inPacket.decodeByte();
        int unk2 = inPacket.decodeShort(); // BulletItem Position
        int unk3 = inPacket.decodeInt(); // Bullet Item ID
        ai.byUnreliableMemory = inPacket.decodeByte() != 0; // by Unreliable Memory?
        int unk5 = inPacket.decodeByte();
        int unk6 = inPacket.decodeByte(); // new 199
        int unk7 = inPacket.decodeInt(); // bonus attack Count
        while (unk7 > 0 && unk7 < Short.MAX_VALUE) { // ?
            unk7 = inPacket.decodeInt();
        }
        // END UNKNOWNATTACKPACKETDATA


        // START PROCESS TYPE ENCODE FUNC
        new ProcessType(skillUseInfo).decode(inPacket); // Not using anything from Process type as of now
        // END PROCESS TYPE ENCODE FUNC


        ai.areaPAD = inPacket.decodeByte() >>> 3;
        byte nul = inPacket.decodeByte(); // encoded as 0
        short actionMask = inPacket.decodeShort();
        ai.left = ((actionMask >>> 15) & 1) != 0;
        ai.attackAction = (short) (actionMask & 0x7FFF);
        ai.attackCount = inPacket.decodeInt();
        ai.attackSpeed = inPacket.decodeByte(); // encoded as 0
        ai.wt = inPacket.decodeInt();
        ai.ar01Mad = inPacket.decodeInt(); // only done if mage skill
        byte idk2 = inPacket.decodeByte();

        if (ai.skillId > 0) {
            for (int i = 0; i < ai.mobCount; i++) {
                MobAttackInfo mai = new MobAttackInfo();
                mai.mobId = inPacket.decodeInt();
                mai.idkInt = inPacket.decodeInt();
                mai.calcDamageStatIndex = inPacket.decodeByte();
                mai.templateID = inPacket.decodeInt();
                mai.rect = new Rect(inPacket.decodePosition(), inPacket.decodePosition());
                mai.idk6 = inPacket.decodeShort();
                mai.hitAction = inPacket.decodeByte();
                mai.idk8 = inPacket.decodeInt(); // new 199
                mai.idk7 = inPacket.decodeInt();
                long[] damages = new long[ai.hits];
                for (int j = 0; j < ai.hits; j++) {
                    damages[j] = inPacket.decodeLong();
                }
                mai.damages = damages;
                mai.mobUpDownYRange = inPacket.decodeInt();
                inPacket.decodeInt(); // crc
                parseAttackInfoPacket(inPacket, mai);
                inPacket.decodeInt(); // new 199
                ai.mobAttackInfo.add(mai);
            }
        }
        ai.pos = inPacket.decodePosition();
        handleAttack(c, ai);
    }


    @Handler(op = InHeader.SUMMONED_ATTACK)
    public static void handleSummonedAttack(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        Field field = chr.getField();
        AttackInfo ai = new AttackInfo();
        int summonedID = inPacket.decodeInt();
        ai.attackHeader = OutHeader.SUMMONED_ATTACK;
        ai.summon = (Summon) field.getLifeByObjectID(summonedID);
        inPacket.decodeInt(); // v205's int before skillId
        ai.updateTime = inPacket.decodeInt();
        ai.skillId = inPacket.decodeInt();
        ai.summonSpecialSkillId = inPacket.decodeInt();
        inPacket.decodeByte(); // new 200
        byte leftAndAction = inPacket.decodeByte();
        ai.attackActionType = (byte) (leftAndAction & 0x7F);
        ai.left = (byte) (leftAndAction >>> 7) != 0;
        byte mask = inPacket.decodeByte();
        ai.hits = (byte) (mask & 0xF);
        ai.mobCount = (mask >>> 4) & 0xF;
        inPacket.decodeByte(); // hardcoded 0
        ai.pos = inPacket.decodePosition();
        inPacket.decodeByte();
        inPacket.decodeInt(); // hardcoded -1
        short idk3 = inPacket.decodeShort();
        int idk4 = inPacket.decodeInt();
        inPacket.decodeInt(); // hardcoded 0
        ai.bulletID = inPacket.decodeInt();
        if (ai.skillId == AngelicBuster.MIGHTY_MASCOT) { //pos might be wrong but it works now
            inPacket.decodeInt();
        }
        for (int i = 0; i < ai.mobCount; i++) {
            MobAttackInfo mai = new MobAttackInfo();
            mai.mobId = inPacket.decodeInt();
            mai.templateID = inPacket.decodeInt();
            mai.hitAction = inPacket.decodeByte();
            mai.left = inPacket.decodeByte();
            mai.idk3 = inPacket.decodeByte();
            mai.forceActionAndLeft = inPacket.decodeByte();
            mai.frameIdx = inPacket.decodeByte();
            int idk5 = inPacket.decodeInt(); // another template id, same as the one above
            mai.calcDamageStatIndexAndDoomed = inPacket.decodeByte(); // 1st bit for bDoomed, rest for calcDamageStatIndex
            mai.hitX = inPacket.decodeShort();
            mai.hitY = inPacket.decodeShort();
            mai.oldPosX = inPacket.decodeShort(); // ?
            mai.oldPosY = inPacket.decodeShort(); // ?
            int idk7 = inPacket.decodeInt();
            short idk6 = inPacket.decodeShort();
            int idk8 = inPacket.decodeInt();
            int idk9 = inPacket.decodeInt();
            long[] damages = new long[ai.hits];
            for (int j = 0; j < ai.hits; j++) {
                damages[j] = inPacket.decodeLong();
            }
            mai.damages = damages;
            mai.mobUpDownYRange = inPacket.decodeInt();
            inPacket.decodeInt(); // new 199
            parseAttackInfoPacket(inPacket, mai);
            inPacket.decodeByte(); //new 214
            ai.mobAttackInfo.add(mai);
        }
        handleAttack(c, ai);
    }

    @Handler(ops = {InHeader.USER_MELEE_ATTACK, InHeader.USER_SHOOT_ATTACK, InHeader.USER_MAGIC_ATTACK,
            InHeader.USER_NON_TARGET_FORCE_ATOM_ATTACK, InHeader.USER_AREA_DOT_ATTACK,  InHeader.USER_AFFECTED_AREA_FOR_SCREEN_ATTACK})
    public static void handleAttack(Char chr, InPacket inPacket, InHeader header) {
        AttackInfo ai = new AttackInfo();
        SkillUseInfo skillUseInfo = new SkillUseInfo();
        ai.inHeader = header;
        switch (header) {
            case USER_MELEE_ATTACK:
                ai.attackHeader = OutHeader.REMOTE_MELEE_ATTACK;
                break;
            case USER_SHOOT_ATTACK:
                ai.boxAttack = inPacket.decodeByte() != 0; // hardcoded in shootAttack (0) and box2d (1)
                ai.attackHeader = OutHeader.REMOTE_SHOOT_ATTACK;
                break;
            case USER_NON_TARGET_FORCE_ATOM_ATTACK:
                inPacket.decodeArr(12); // id/crc/something else
            case USER_MAGIC_ATTACK:
                ai.attackHeader = OutHeader.REMOTE_MAGIC_ATTACK;
                break;
            case USER_AFFECTED_AREA_FOR_SCREEN_ATTACK:
                ai.attackHeader = OutHeader.REMOTE_MELEE_ATTACK;
                break;
        }
        ai.bulletID = chr.getBulletIDForAttack();
        ai.fieldKey = inPacket.decodeByte();
        byte mask = inPacket.decodeByte();
        ai.hits = (byte) (mask & 0xF);
        ai.mobCount = (mask >>> 4) & 0xF;
        ai.idk205 = inPacket.decodeInt();
        ai.skillId = inPacket.decodeInt();
        ai.slv = inPacket.decodeInt();
        if (header == InHeader.USER_MELEE_ATTACK || header == InHeader.USER_SHOOT_ATTACK
                || header == InHeader.USER_NON_TARGET_FORCE_ATOM_ATTACK) {
            ai.addAttackProc = inPacket.decodeByte();
        }
        inPacket.decodeInt(); // crc
        int idk199 = inPacket.decodeInt(); // new 199
        int idk203 = inPacket.decodeInt(); // unk 203


        // START UNKNOWNATTACKPACKETDATA
        int unk1 = inPacket.decodeByte();
        int unk2 = inPacket.decodeShort(); // BulletItem Position
        int unk3 = inPacket.decodeInt(); // Bullet Item ID
        ai.byUnreliableMemory = inPacket.decodeByte() != 0; // by Unreliable Memory?
        int unk5 = inPacket.decodeByte();
        int unk6 = inPacket.decodeByte(); // new 199
        int unk7 = inPacket.decodeInt(); // bonus attack Count
        while (unk7 > 0 && unk7 < Short.MAX_VALUE) { // ?
            unk7 = inPacket.decodeInt();
        }
        // END UNKNOWNATTACKPACKETDATA


        // START PROCESS TYPE ENCODE FUNC
        new ProcessType(skillUseInfo).decode(inPacket); // Not using anything from Process type as of now
        // END PROCESS TYPE ENCODE FUNC


        int skillID = ai.skillId;

        // Melee Attack
        if (SkillConstants.isKeyDownSkill(skillID) || SkillConstants.isSuperNovaSkill(skillID)) {
            ai.keyDown = inPacket.decodeInt();
        }


        if (SkillConstants.isRushBombSkill(skillID) || skillID == 5300007 || skillID == 27120211 || skillID == 14111023
                || skillID == 400031003 || skillID == 400031004 || skillID == 80011389 || skillID == 80011390 || skillID == 400031036
                || skillID == 42120000) {
            ai.grenadeId = inPacket.decodeInt();
        }

        // Melee Attack
        if (SkillConstants.isZeroSkill(skillID)) {
            ai.zero = inPacket.decodeByte();
        }

        // Melee Attack
        if (SkillConstants.isUsercloneSummonedAbleSkill(skillID)) {
            ai.bySummonedID = inPacket.decodeInt();
        }

        if(skillID == 400051046) {
            inPacket.decodeInt();
        }

        if (skillID == 23121011) { // Mercedes Rolling Moonsault skill
            inPacket.decodeInt();
        }



        if (skillID == NightWalker.SHADOW_SPEAR_AA_LARGE) {
            ai.shadowSpear1 = inPacket.decodeInt();
            ai.shadowSpear2 = inPacket.decodeInt();
        }
       /* if (skillID == 155101104 || skillID == 155101100 || skillID == 155121306
                || skillID == 155101101 || skillID == 155101200 || skillID == 155101201
                || skillID == 155101204) {
            // Unstoppable Impulse / Scarlet Charge Drive / Blissful Restraint (all dash attacks)
            byte idk2 = inPacket.decodeByte();
            int idk = inPacket.decodeInt();
            int idk3 = inPacket.decodeInt();
            int idk4 = inPacket.decodeInt();
        }*/ // Asura told me to remove this
        ai.buckShot = inPacket.decodeByte();
        ai.someMask = inPacket.decodeByte(); // decides where the Remote Attack goes for mimicked skills (Divine Echo)
        if (header == InHeader.USER_SHOOT_ATTACK) {
            int idk3 = inPacket.decodeInt();
            ai.isJablin = inPacket.decodeByte() != 0;
            if (ai.boxAttack) {
                int boxIdk1 = inPacket.decodeInt();
                short boxIdk2 = inPacket.decodeShort();
                short boxIdk3 = inPacket.decodeShort();
            }
        }
        short maskie = inPacket.decodeShort();
        ai.left = ((maskie >>> 15) & 1) != 0;
        ai.attackAction = (short) (maskie & 0x7FFF);
        ai.requestTime = inPacket.decodeInt();
        ai.attackActionType = inPacket.decodeByte();
        if (skillID == 23111001 || skillID == 80001915 || skillID == 36111010) {
            int idk5 = inPacket.decodeInt();
            int x = inPacket.decodeInt(); // E0 6E 1F 00
            int y = inPacket.decodeInt();
        }

        if (SkillConstants.isEvanForceSkill(skillID)) {
            ai.idk0 = inPacket.decodeByte();
        }
        ai.attackSpeed = inPacket.decodeByte();
        ai.tick = inPacket.decodeInt();

        if (header == InHeader.USER_AREA_DOT_ATTACK) {
            ai.affectedAreaObjId = inPacket.decodeInt();
        }

        if (header != InHeader.USER_MAGIC_ATTACK && header != InHeader.USER_NON_TARGET_FORCE_ATOM_ATTACK && header != InHeader.USER_AREA_DOT_ATTACK && header != InHeader.USER_AFFECTED_AREA_FOR_SCREEN_ATTACK) {
            int bulletSlot = inPacket.decodeInt();
        }

        if (header == InHeader.USER_MELEE_ATTACK || header == InHeader.USER_SHOOT_ATTACK) {
            ai.finalAttackLastSkillID = inPacket.decodeInt();
            if (ai.finalAttackLastSkillID > 0) {
                ai.finalAttackByte = inPacket.decodeByte();
            }
        } else if (header == InHeader.USER_MAGIC_ATTACK || header == InHeader.USER_NON_TARGET_FORCE_ATOM_ATTACK) {
            int idk = inPacket.decodeInt();
        } else {
            short idk8 = inPacket.decodeShort();
            short idk9 = inPacket.decodeShort();
        }
        if (header == InHeader.USER_NON_TARGET_FORCE_ATOM_ATTACK || skillID == RuneStone.LIBERATE_THE_RUNE_OF_THUNDER_2) {
            inPacket.decodeInt(); // hardcoded 0
        }
        if (header == InHeader.USER_SHOOT_ATTACK) {
            // this looks correct in ida, but completely wrong when comparing it to the actual packet
//            ai.shootRange = inPacket.decodeByte();
//            if (!SkillConstants.isShootSkillNotConsumingBullets(skillID)
//                    || chr.getTemporaryStatManager().hasStat(CharacterTemporaryStat.SoulArrow)) {
//                ai.bulletCount = inPacket.decodeInt();
//            }
            int bulletSlot = inPacket.decodeShort();
//            short idk2 = inPacket.decodeShort();
            byte idk = inPacket.decodeByte();
            if ((bulletSlot == 0 || idk == 0) && (ai.buckShot & 0x40) != 0 && !SkillConstants.isFieldAttackObjSkill(skillID)) {
                //int maybeID = inPacket.decodeInt(); // int needs to be read here?
            }
            ai.rect = inPacket.decodeShortRect();
        }
        if (skillID == 5111009) { // Spiral Assault
            ai.ignorePCounter = inPacket.decodeByte() != 0;
        }

        if (skillID == 23121002 || skillID == 23111002 || skillID == 23121003) { // Spirit Frenzy and spikes royal
            ai.idk1 = inPacket.decodeInt();
        }
        if (skillID == 25111005) {
            ai.spiritCoreEnhance = inPacket.decodeInt();
        }

        for (int i = 0; i < ai.mobCount; i++) {
            MobAttackInfo mai = new MobAttackInfo();
            mai.mobId = inPacket.decodeInt();
            //chr.chatMessage(AdminChat,"mobid = " + mai.mobId);

            mai.hitAction = inPacket.decodeByte();
            //chr.chatMessage(AdminChat,"hitAction = " + mai.hitAction);

            mai.left = inPacket.decodeByte();
            //chr.chatMessage(AdminChat,"left = " + mai.left);

            mai.idk3 = inPacket.decodeByte();
            //chr.chatMessage(AdminChat,"idk3 = " + mai.idk3);

            mai.forceActionAndLeft = inPacket.decodeByte();
            //chr.chatMessage(AdminChat,"forceActionAndLeft = " + mai.forceActionAndLeft);

            mai.frameIdx = inPacket.decodeByte();
            //chr.chatMessage(AdminChat,"frameIdx = " + mai.frameIdx);

            mai.templateID = inPacket.decodeInt();
            //chr.chatMessage(AdminChat,"templateID = " + mai.templateID);
            //chr.chatMessage(Mob,"------------------");

            mai.calcDamageStatIndexAndDoomed = inPacket.decodeByte(); // 1st bit for bDoomed, rest for calcDamageStatIndex
            mai.hitX = inPacket.decodeShort();
            mai.hitY = inPacket.decodeShort();
            mai.oldPosX = inPacket.decodeShort(); // ?
            mai.oldPosY = inPacket.decodeShort(); // ?
            if (header == InHeader.USER_MAGIC_ATTACK) {
                mai.hpPerc = inPacket.decodeByte();
                if (skillID == 80001835) {
                    mai.magicInfo = (short) inPacket.decodeByte();
                } else {
                    mai.magicInfo = inPacket.decodeShort();
                }
            } else {
                mai.idk6 = inPacket.decodeShort();
            }
            mai.idk8 = inPacket.decodeInt(); // new 199
            mai.idk7 = inPacket.decodeInt();
            mai.damages = new long[ai.hits];
            for (int j = 0; j < ai.hits; j++) {
                mai.damages[j] = inPacket.decodeLong();
            }
            mai.mobUpDownYRange = inPacket.decodeInt();
            inPacket.decodeInt(); // crc
            if (SkillConstants.isKinesisPsychicLockSkill(skillID)) {
                //mai.psychicLockInfo
                inPacket.decodeInt(); // CMob->nPsychicLockKey
                inPacket.decodeInt(); // CKinesis_PsychicLock->nCurrentPathIndex
            }
            if (skillID == 37111005) {
                mai.isResWarriorLiftPress = inPacket.decodeByte() != 0;
            }
            parseAttackInfoPacket(inPacket, mai);
            int x = inPacket.decodeInt(); // new 199
            inPacket.decodeByte(); // new 214
            ai.mobAttackInfo.add(mai);
        }
        if (skillID == 61121052 || skillID == 36121052 || skillID == 112001018 || skillID == 112001017
                || SkillConstants.isScreenCenterAttackSkill(skillID)) {
            ai.ptTarget.setX(inPacket.decodeShort());
            ai.ptTarget.setY(inPacket.decodeShort());
        } else {
            if (skillID == 27121052 || skillID == 80001837) {
                ai.x = inPacket.decodeShort();
                ai.y = inPacket.decodeShort();
            }
            if (header == InHeader.USER_MAGIC_ATTACK && skillID != 3211016 && inPacket.getUnreadAmount() >= 16) {
                short forcedX = inPacket.decodeShort();
                short forcedY = inPacket.decodeShort();
                boolean dragon = inPacket.decodeByte() != 0;
                ai.forcedX = forcedX;
                ai.forcedY = forcedY;
                if (dragon) {
                    short rcDstRight = inPacket.decodeShort();
                    short rectRight = inPacket.decodeShort();
                    short x = inPacket.decodeShort();
                    short y = inPacket.decodeShort();
                    inPacket.decodeByte(); // always 0
                    inPacket.decodeByte(); // -1
                    inPacket.decodeByte(); // 0
                    //   ai.rcDstRight = rcDstRight;
                    ///   ai.rectRight = rectRight;
                    //   ai.x = x;
                    //   ai.y = y;
                }
            }
            if (skillID == BlazeWizard.IGNITION_EXPLOSION) {
                ai.option = inPacket.decodeInt();
            }
            if (skillID == FirePoison.MIST_ERUPTION) {
                byte size = inPacket.decodeByte();
                int[] mists = new int[size > 0 ? size : 0];
                for (int i = 0; i < mists.length; i++) {
                    //   mists[i] = inPacket.decodeInt(); // removed for v206, encoded an extra int
                }
                ai.mists = mists;
            }
            if (skillID == FirePoison.POISON_MIST) {
                byte force = inPacket.decodeByte();
                short forcedXSh = inPacket.decodeShort();
                short forcedYSh = inPacket.decodeShort();
                ai.force = force;
                ai.forcedXSh = forcedXSh;
                ai.forcedYSh = forcedYSh;
            }
            if (skillID == 80001835) { // Soul Shear
                byte sizeB = inPacket.decodeByte();
                int[] idkArr2 = new int[sizeB];
                short[] shortArr2 = new short[sizeB];
                for (int i = 0; i < sizeB; i++) {
                    idkArr2[i] = inPacket.decodeInt();
                    shortArr2[i] = inPacket.decodeShort();
                }
                short delay = inPacket.decodeShort();
                ai.mists = idkArr2;
                ai.shortArr = shortArr2;
                ai.delay = delay;
            }
            if (SkillConstants.isSuperNovaSkill(skillID)) {
                ai.ptAttackRefPoint.setX(inPacket.decodeShort());
                ai.ptAttackRefPoint.setY(inPacket.decodeShort());
            }
            if (skillID == 101000102 || skillID == 40011289 || skillID == 40011290 || skillID == 400031016
                    || skillID == 400041024 || skillID == 41111001 || skillID == 41111017
                    || skillID == 41121015) {  // Air Riot, Summer Rain, Hitokiri Hundred Strike, Split Shot
                // Ace in the Hole, Sweeping Sword x2, Eye for an Eye
                ai.idkPos = inPacket.decodePosition();
            }
            if (skillID == 400020009 || skillID == 400020010 || skillID == 400020011 || skillID == 400021029
                    || skillID == 400021053) { // Psychic Tornado x3, Poison Nova, Ultimate - Mind Over Matter
                ai.idk2 = inPacket.decodeInt();
                ai.idk3 = inPacket.decodeShort();
                ai.idk4 = inPacket.decodeShort();
                ai.idk5 = inPacket.decodeByte(); // hardcoded 0?
            }
//            if (ai.someMask != 0) {
//                ai.idk2 = inPacket.decodeInt();
//                ai.idk3 = inPacket.decodeShort();
//                ai.idk4 = inPacket.decodeShort();
//            }
            if (header == InHeader.USER_AREA_DOT_ATTACK) {
                ai.pos.setX(inPacket.decodeShort());
                ai.pos.setY(inPacket.decodeShort());
            }
            if (SkillConstants.isAranFallingStopSkill(skillID)) {
                ai.fh = inPacket.decodeByte();
            }

            if (ai.inHeader == InHeader.USER_AFFECTED_AREA_FOR_SCREEN_ATTACK) {
                inPacket.decodePosition();
            }

            if (header == InHeader.USER_SHOOT_ATTACK && skillID / 1000000 == 33) {
                ai.bodyRelMove = inPacket.decodePosition();
            }
            if (skillID == 21120019 || skillID == 37121052 || SkillConstants.isShadowAssault(skillID)
                    || skillID == 11121014 || skillID == 510004) {
                ai.teleportPt.setX(inPacket.decodeShort());
                ai.teleportPt.setY(inPacket.decodeShort());
            }
            if (header == InHeader.USER_SHOOT_ATTACK && SkillConstants.isKeydownSkillRectMoveXY(skillID)) {
                ai.keyDownRectMoveXY = inPacket.decodePosition();
            }
            if (skillID == Kaiser.INFERNO_BREATH || skillID == Kaiser.INFERNO_BREATH_FINAL_FORM /*|| skillID == 24121052*/) {
                inPacket.decodeInt(); // unk
                ai.Vx = inPacket.decodeShort();
                for (int i = 0; i < ai.Vx; i++) {
                    // Inferno Breath Affected Area Positions
                    ai.positions.add(inPacket.decodePosition());
                }
            }

            if (skillID == 101120104) {
                // CUser::EncodeAdvancedEarthBreak
                // TODO
            }
            if (skillID == 14111006 && ai.grenadeId != 0) {
                ai.grenadePos.setX(inPacket.decodeShort());
                ai.grenadePos.setY(inPacket.decodeShort());
            }
            if (/*skillID == 23121002 ||*/skillID == 80001914) { // first skill is Spikes Royale, not needed?
                ai.fh = inPacket.decodeByte();
            }
            if (skillID == Cadena.CHAIN_ARTS_PURSUIT_UP || skillID == Cadena.CHAIN_ARTS_PURSUIT_DOWN) {
                if (inPacket.getUnreadAmount() > 5) {
                    int toReadAmount = inPacket.getUnreadAmount() - 5;
                    inPacket.decodeArr(toReadAmount);
                    // 64001010
                    //log.error(String.format("Wrong Attack Info Parse (decoded too little)  |  SkillID = %d,  Too few bytes decoded = %d", skillID, toReadAmount));
                }
                inPacket.decodeByte(); // unk
                ai.ptTarget = inPacket.decodePosition(); // pursuit hook end position
            }
        }
        if (SkillConstants.isDivineEchoMimicSkills(skillID) && inPacket.getUnreadAmount() == 8) { // Divine Echo Mimic Skills
            ai.isMimickedBy = inPacket.decodeInt(); // Mimic Chr Id
            inPacket.decodePosition(); // Mimic Chr Position
        }
        handleAttack(chr.getClient(), ai);
    }

    private static void parseAttackInfoPacket(InPacket inPacket, MobAttackInfo mai) {
        // PACKETMAKER::MakeAttackInfoPacket
        mai.type = inPacket.decodeByte();
        mai.currentAnimationName = "";
        mai.unkStr = "";
        if (mai.type == 1) {
            mai.currentAnimationName = inPacket.decodeString();
            mai.unkStr = inPacket.decodeString();
            mai.animationDeltaL = inPacket.decodeInt();
            mai.hitPartRunTimesSize = inPacket.decodeInt();
            mai.hitPartRunTimes = new String[mai.hitPartRunTimesSize];
            for (int j = 0; j < mai.hitPartRunTimesSize; j++) {
                mai.hitPartRunTimes[j] = inPacket.decodeString();
            }
        } else if (mai.type == 2) {
            mai.currentAnimationName = inPacket.decodeString();
            mai.unkStr = inPacket.decodeString();
            mai.animationDeltaL = inPacket.decodeInt();
        }
        mai.packetMakerUnk1 = inPacket.decodeByte();
        mai.packetMakerRect = inPacket.decodeShortRect(); // I guess?
    }

    @Handler(op = InHeader.FAMILIAR_ATTACK)
    public static void handleFamiliarAttack(Char chr, InPacket inPacket) {
        inPacket.decodeByte(); // ?
        int familiarID = inPacket.decodeInt();
        if (chr.getActiveFamiliar() == null || chr.getActiveFamiliar().getFamiliarID() != familiarID) {
            return;
        }
        AttackInfo ai = new AttackInfo();
        ai.attackHeader = OutHeader.FAMILIAR_ATTACK;
        ai.fieldKey = inPacket.decodeByte();
        ai.skillId = inPacket.decodeInt();
        ai.idk = inPacket.decodeByte();
        ai.slv = 1;
        ai.mobCount = inPacket.decodeByte();
        for (int i = 0; i < ai.mobCount; i++) {
            MobAttackInfo mai = new MobAttackInfo();
            mai.mobId = inPacket.decodeInt();
            mai.hitAction = inPacket.decodeByte();
            mai.left = inPacket.decodeByte();
            mai.byteIdk3 = inPacket.decodeByte();
            mai.forceActionAndLeft = inPacket.decodeByte();
            mai.frameIdx = inPacket.decodeByte();
            mai.templateID = inPacket.decodeInt();
            mai.calcDamageStatIndexAndDoomed = inPacket.decodeByte();
            mai.hitX = inPacket.decodeShort();
            mai.hitY = inPacket.decodeShort();
            mai.damages = new long[inPacket.decodeByte()];
            for (int j = 0; j < mai.damages.length; j++) {
                mai.damages[j] = inPacket.decodeInt();
            }
            ai.mobAttackInfo.add(mai);
        }
        handleAttack(chr.getClient(), ai);
        // 4 more bytes after this, not sure what it is
    }
}
