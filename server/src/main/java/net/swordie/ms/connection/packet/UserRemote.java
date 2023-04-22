package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.PortableChair;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.damage.DamageCalc;
import net.swordie.ms.client.character.info.HitInfo;
import net.swordie.ms.client.character.skills.Option;
import net.swordie.ms.client.character.skills.ShootObject;
import net.swordie.ms.client.character.skills.info.AttackInfo;
import net.swordie.ms.client.character.skills.info.MobAttackInfo;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.FriendshipRingRecord;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.jobs.adventurer.thief.BladeMaster;
import net.swordie.ms.client.jobs.cygnus.Mihile;
import net.swordie.ms.client.jobs.flora.Adele;
import net.swordie.ms.client.jobs.legend.Mercedes;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.AvatarModifiedMask;
import net.swordie.ms.enums.BaseStat;
import net.swordie.ms.enums.ChairType;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Dragon;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.world.field.Field;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.swordie.ms.enums.BaseStat.addCrOnBoss;


/**
 * Created on 2/3/2018.
 */
public class UserRemote {
    public static OutPacket setActiveNickItem(Char chr, String msg) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_NICK_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getActiveNickItemID());
        outPacket.encodeByte(msg != null);
        if (msg != null) {
            outPacket.encodeString(msg);
        }

        return outPacket;
    }

    public static OutPacket move(Char chr, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_MOVE);

        outPacket.encodeInt(chr.getId());
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket emotion(int id, int emotion, int duration, boolean byItemOption) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EMOTION);

        outPacket.encodeInt(id);
        outPacket.encodeInt(emotion);
        outPacket.encodeInt(duration);
        outPacket.encodeByte(byItemOption);

        return outPacket;
    }

    public static OutPacket attack(Char chr, AttackInfo ai) {
        OutHeader attackType = ai.attackHeader;
        int skillID = ai.skillId;
        OutPacket outPacket = new OutPacket(attackType);
        outPacket.encodeInt(chr.getId());

        outPacket.encodeByte(ai.fieldKey);
        outPacket.encodeByte(ai.mobCount << 4 | ai.hits);
        outPacket.encodeInt(chr.getLevel());
        outPacket.encodeInt(ai.slv);
        if (ai.slv > 0) {
            outPacket.encodeInt(skillID);
        }
        if (SkillConstants.isZeroSkill(skillID)) {
            outPacket.encodeByte(ai.zero);
            if (ai.zero != 0) {
                outPacket.encodePosition(chr.getPosition());
            }
        }
        if (attackType == OutHeader.REMOTE_SHOOT_ATTACK &&
                (SkillConstants.getAdvancedCountHyperSkill(skillID) != 0 ||
                        SkillConstants.getAdvancedAttackCountHyperSkill(skillID) != 0)) {
            outPacket.encodeInt(ai.passiveSLV);
            if (ai.passiveSLV > 0) {
                outPacket.encodeInt(ai.passiveSkillID);
            }
            if (skillID == 400031026) { // Surge Bolt
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
        }
        if (skillID == 80001850) {
            int val = 0;
            outPacket.encodeInt(val);
            if (val != 0) {
                outPacket.encodeInt(0);
            }
        }
//        if (skillID == 42001000 || (skillID > 42001004 && skillID <= 42001006) || skillID == 40021185 || skillID == 80011067) {
//            byte idk = 0;
//            outPacket.encodeByte(idk); // another mask?
//            if (idk != 0) {
//                outPacket.encodeInt(0);
//            }
//        }
        // TODO proper encoding.  someMask needs to be encoded at the place of buckShot encode
        outPacket.encodeByte(ai.someMask);  // check
        outPacket.encodeByte(ai.isMimickedBy > 0 ? 32 : ai.buckShot); // check
        outPacket.encodeInt(ai.option3);
        outPacket.encodeInt(ai.bySummonedID);
        if ((ai.buckShot & 2) != 0) {
            outPacket.encodeInt(ai.buckShotSkillID);
            outPacket.encodeInt(ai.buckShotSlv);
        }
        if ((ai.buckShot & 8) != 0) {
            outPacket.encodeByte(ai.psdTargetPlus);
        }

        if (skillID == 400031010 || skillID == 400031006) { // Marksman Perfect Shot
            outPacket.encodeArr(new byte[100]);
        }

        if (skillID == 400021017) { // Kanna Yuki-Musume Shoukan
            outPacket.encodeArr(new byte[20]);
        }

        if (skillID == 400051054 || skillID == 400051055) {
            outPacket.encodeArr(new byte[100]);
        }

        if (skillID == 400021038) { // Beast Tamer Champ Charge 5th job skill
            outPacket.encodeArr(new byte[50]);
        }

        if (skillID == Adele.IMPALE || skillID == Adele.RESONANCE_RUSH) {
            outPacket.encodeInt(0);
        }

        if (skillID == Mercedes.UNICORN_SPIKE || skillID == Mercedes.SPIKES_ROYALE || skillID == Mercedes.LIGHTNING_EDGE || skillID == Mercedes.ROLLING_MOONSAULT) {
            outPacket.encodeInt(0);
        }

        byte left = (byte) (ai.left ? 1 : 0);
        outPacket.encodeShort((left << 15) | ai.attackAction);
        if (ai.attackAction < 2017) {
            outPacket.encodeByte(ai.attackActionType);
            outPacket.encodeShort(ai.x);
            outPacket.encodeShort(ai.y);
            outPacket.encodeByte(ai.showFixedDamage);
            outPacket.encodeByte(!ai.isDragonAttack);
            outPacket.encodeByte(ai.attackSpeed);

            outPacket.encodeByte(ai.mastery);
            outPacket.encodeInt(ai.bulletID);
            for (MobAttackInfo mai : ai.mobAttackInfo) {
                outPacket.encodeInt(mai.mobId);
                if (mai.mobId > 0) {
                    outPacket.encodeByte(mai.byteIdk1);
                    outPacket.encodeByte(mai.boolIdk1);
                    outPacket.encodeByte(mai.boolIdk2);
                    outPacket.encodeShort(mai.byteIdk4);
                    outPacket.encodeInt(0); // new 188
                    outPacket.encodeInt(0); // new 200
                    if (skillID == 80001835 || skillID == 42111002 || skillID == 80011050) {
                        // Soul Shear
                        outPacket.encodeByte(ai.hits);
                        outPacket.encodeLong(0); // not exactly sure
                    }
                    for (int i = 0; i < mai.damages.length; i++) {
                        outPacket.encodeByte(mai.crits[i]); // isCrit
                        outPacket.encodeLong(mai.damages[i]);
                    }
                    if (SkillConstants.isKinesisPsychicLockSkill(skillID)) {
                        outPacket.encodeInt(mai.psychicLockInfo);
                    }
                    if (skillID == 37111005) { // rocket rush
                        outPacket.encodeByte(mai.rocketRushInfo); // boolean
                    }
                }
            }
            if (skillID == 2321001 || skillID == 2221052 || skillID == 11121052 || skillID == 12121054) {
                outPacket.encodeInt(ai.keyDown);
            } else if (SkillConstants.isSuperNovaSkill(skillID) || SkillConstants.isScreenCenterAttackSkill(skillID)
                    || SkillConstants.isRandomAttackSkill(skillID) || SkillConstants.isWingedJavelinOrAbyssalCast(skillID)
                    || skillID == 101000202 || skillID == 101000102 || skillID == 400041019 || skillID == 400031016
                    || skillID == 400041024 || skillID == 400021075) {
                outPacket.encodePositionInt(ai.ptAttackRefPoint);
            }
            if (skillID == 80002452) { // Fruitful Bounty
                outPacket.encodePositionInt(chr.getPosition());
            }

            if (skillID == 400021077) { // Bishop Peacemaker Explosion
                outPacket.encodeInt(0);
            }

            if (skillID == 400041043) { // Shadower Blade Tornado
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }

            if (skillID == 37120059 || skillID == 37121052 || skillID == 37000007 || skillID == 37120055 || skillID == 37120056  || skillID == 37120057 || skillID == 37120058) { // Blaster Hyper Magnum Punch related skills
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeShort(0);
            }

            if (skillID == 400041018 || skillID == 400041017 || skillID == 400041016) { // Nightlord Throwing Star Barrage related skills
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }

            if (skillID == 164121044) { // Hoyoung Hyper Sage: Tai Yu Clone
                outPacket.encodeInt(0);
            }

            if (SkillConstants.isSentientArrowOrTornadoFlight(skillID)) {
                outPacket.encodePosition(ai.ptAttackRefPoint);
            }
            if (skillID == Mihile.RADIANT_CROSS) {
                outPacket.encodeByte(ai.showFixedDamage);
            }
            if (skillID == 112110003) { // formation attack
                outPacket.encodeInt(0); // does a << 16
            }
            if (skillID == 42100007) { // Soul Bomb
                outPacket.encodeShort(0);
                byte size = 0;
                outPacket.encodeByte(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodePosition(new Position());
                }
            }
            if (skillID == 21120019 || skillID == 37121049 || SkillConstants.isShadowAssault(skillID)
                    || skillID == 11121014 || skillID == 5101004) {
                outPacket.encodeByte(0); // new 188
                outPacket.encodePositionInt(ai.teleportPt);
            }
            boolean isSomePfSkill = SkillConstants.isSomePathfinderSkill(skillID);
            if (SkillConstants.isSomeAA(skillID) || isSomePfSkill) {
                outPacket.encodePosition(ai.teleportPt);
                if (isSomePfSkill) {
                    outPacket.encodeInt(0);
                    outPacket.encodeByte(false);
                }
            }
            if (SkillConstants.isSomeFifthSkillForRemote(skillID)) {
                outPacket.encodeInt(0);
                outPacket.encodeByte(false);
            }
            if (skillID == 155101104 || skillID == 155101204 || skillID == 400051042 || skillID == 41121022) {
                boolean bool = false;
                outPacket.encodeByte(bool);
                if (bool) {
                    outPacket.encodePositionInt(ai.teleportPt);
                }
            }
        }

        return outPacket;
    }

    public static OutPacket avatarModified(Char chr, byte mask, byte carryItemEffect) {
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_AVATAR_MODIFIED);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(mask);
        if ((mask & AvatarModifiedMask.AvatarLook.getVal()) != 0) {
            al.encode(outPacket);
        }
        if ((mask & AvatarModifiedMask.SubAvatarLook.getVal()) != 0) {
            al.encode(outPacket); // subAvatarLook
        }
        if ((mask & AvatarModifiedMask.Speed.getVal()) != 0) {
            outPacket.encodeByte(tsm.getOption(CharacterTemporaryStat.Speed).nOption);
        }
        if ((mask & AvatarModifiedMask.CarryItemEffect.getVal()) != 0) {
            outPacket.encodeByte(carryItemEffect);
        }
        Triple<List<FriendshipRingRecord>, List<FriendshipRingRecord>, List<FriendshipRingRecord>> rings = chr.getRings(false);
        UserPool.addRingInfo(outPacket, rings.getLeft());
        UserPool.addRingInfo(outPacket, rings.getMiddle());

        boolean hasWedding = chr.getMarriageRecord() != null;
        outPacket.encodeByte(hasWedding);
        if (hasWedding) {
            chr.getMarriageRecord().encode(outPacket);
        }
        outPacket.encodeInt(chr.getCompletedSetItemID());
        outPacket.encodeInt(chr.getTotalChuc());
        outPacket.encodeInt(chr.getTotalAf());

        return outPacket;
    }

    public static OutPacket throwGrenade(int charID, int grenadeID, Position pos, int keyDown, int skillID, int bySummonedID,
                                         int slv, boolean left, int attackSpeed) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_THROW_GRENADE);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(grenadeID);
        outPacket.encodePositionInt(pos);
        outPacket.encodeInt(keyDown);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(bySummonedID);
        outPacket.encodeInt(slv);
        outPacket.encodeByte(left);
        outPacket.encodeInt(attackSpeed);

        return outPacket;
    }



    public static OutPacket destroyGrenade(int charID, int grenadeID) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_DESTROY_GRENADE);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(grenadeID);

        return outPacket;
    }

    public static OutPacket receiveHP(Char chr) {
        return receiveHP(chr.getId(), chr.getHP(), chr.getTotalStat(BaseStat.mhp));
    }

    public static OutPacket receiveHP(int charID, int curHP, int maxHP) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_RECEIVE_HP);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(curHP);
        outPacket.encodeInt(maxHP);

        return outPacket;
    }

    public static OutPacket hit(Char chr, HitInfo hitInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_HIT);

        outPacket.encodeInt(chr.getId());

        if (hitInfo.mobID == 0) {
            hitInfo.type = -3;
        }

        outPacket.encodeByte(hitInfo.type);
        outPacket.encodeInt(0); // hitInfo.hpDamage
        outPacket.encodeByte(hitInfo.isCrit);
        outPacket.encodeByte(hitInfo.hpDamage == 0);
        if (hitInfo.type == -8) {
            outPacket.encodeInt(hitInfo.blockSkillId);
            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.otherUserID);
        } else if (hitInfo.type >= -1){
            outPacket.encodeInt(hitInfo.templateID);
            outPacket.encodeByte(hitInfo.action);
            outPacket.encodeInt(hitInfo.mobID);


            outPacket.encodeInt(0); // ignored
            outPacket.encodeInt(hitInfo.reflectDamage);
            outPacket.encodeByte(hitInfo.hpDamage == 0); // bGuard
            if (hitInfo.reflectDamage > 0) {
                outPacket.encodeByte(hitInfo.isGuard);
                outPacket.encodeInt(hitInfo.mobID);

                outPacket.encodeByte(hitInfo.hitAction);
                outPacket.encodePosition(chr.getPosition());
            }
            outPacket.encodeByte(hitInfo.specialEffectSkill);
            if ((hitInfo.specialEffectSkill & 1) != 0) {
                outPacket.encodeInt(hitInfo.stanceSkillID);
            }
        }
        outPacket.encodeInt(hitInfo.hpDamage);
        if (hitInfo.hpDamage == -1) {
            outPacket.encodeInt(hitInfo.userSkillID);
        }

        return outPacket;
    }

    public static OutPacket effect(int id, Effect effect) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_EFFECT);

        outPacket.encodeInt(id);
        effect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket setDefaultWingItem(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_DEFAULT_WING_ITEM);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getAvatarData().getCharacterStat().getWingItem());

        return outPacket;
    }

    public static OutPacket setTemporaryStat(Char chr, short delay) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_TEMPORARY_STAT);

        outPacket.encodeInt(chr.getId());
        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        boolean hasMovingAffectingStat = tsm.hasNewMovingEffectingStat();
        tsm.encodeForRemote(outPacket, tsm.getNewStats());
        outPacket.encodeShort(delay);
        outPacket.encodeByte(hasMovingAffectingStat); // bMovementSN

        return outPacket;
    }

    public static OutPacket resetTemporaryStat(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_RESET_TEMPORARY_STAT);

        TemporaryStatManager tsm = chr.getTemporaryStatManager();
        outPacket.encodeInt(chr.getId());
        Map<CharacterTemporaryStat, List<Option>> removeStats = tsm.getRemovedStats();
        int[] mask = tsm.getMaskByCollection(removeStats);
        for (int maskElem : mask) {
            outPacket.encodeInt(maskElem);
        }
        if (removeStats.containsKey(CharacterTemporaryStat.IndieSummon)
                || removeStats.containsKey(CharacterTemporaryStat.IndieNotDamaged)
                || removeStats.containsKey(CharacterTemporaryStat.IndieCrystalCharge)
                || removeStats.containsKey(CharacterTemporaryStat.Indie205_56)) { // 47, 49, 52, 56
            tsm.encodeRemovedIndieTempStat(outPacket);
        }
        int poseType = 0;
        if (removeStats.containsKey(CharacterTemporaryStat.PoseType)) {
            poseType = tsm.getOption(CharacterTemporaryStat.PoseType).bOption;
        }
        outPacket.encodeByte(poseType);
        if (removeStats.containsKey(CharacterTemporaryStat.Unk205_590)) {
            outPacket.encodeByte(0);
        }
        outPacket.encodeByte(false); // if true, show a ride vehicle effect. Why should this be called on reset tho?

        return outPacket;
    }

    public static OutPacket remoteSetActivePortableChair(int chrId, PortableChair chair) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SET_ACTIVE_PORTABLE_CHAIR);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(chair.getItemID());
        outPacket.encodeByte(chair.getType() != ChairType.None);
        if (chair.getType() != ChairType.None) {
            chair.encode(outPacket);
        }

        return outPacket;
    }



    public static OutPacket skillPrepare(Char chr, int skillId, int slv) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SKILL_PREPARE);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(slv);

        outPacket.encodeShort(0); // unknown

        outPacket.encodeByte(chr.getTotalStat(BaseStat.booster)); // action Speed
        outPacket.encodePosition(chr.getPosition());

        return outPacket;
    }

    public static OutPacket skillCancel(int chrId, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SKILL_CANCEL);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(skillId);

        return outPacket;
    }

    public static OutPacket shootObject(Char chr, int action, int skillId, List<ShootObject> shootObjectList) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_SHOOT_OBJECT);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(skillId);
        outPacket.encodeInt(chr.getSkillLevel(skillId));

        outPacket.encodeInt(action);
        outPacket.encodeInt(chr.getTotalStat(BaseStat.booster)); // action Speed
        outPacket.encodeInt(chr.getBulletIDForAttack());

        boolean bool = false;
        outPacket.encodeByte(bool);
        if (bool) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeByte(0);
        }

        outPacket.encodeInt(shootObjectList.size()); // loop size
        for (ShootObject shootObject : shootObjectList) {
            shootObject.encodeShootObjectRemote(outPacket);
        }

        return outPacket;
    }


    public static OutPacket guildMarkChanged(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_GUILD_MARK_CHANGED);

        outPacket.encodeInt(chr.getId());

        Guild guild = chr.getGuild();
        if (guild == null) {
            outPacket.encodeShort(0);
            outPacket.encodeByte(0);
            outPacket.encodeShort(0);
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeShort(guild.getMarkBg());
            outPacket.encodeByte(guild.getMarkBgColor());
            outPacket.encodeShort(guild.getMark());
            outPacket.encodeByte(guild.getMarkColor());
        }

        return outPacket;
    }


    public static OutPacket guildNameChanged(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.REMOTE_GUILD_NAME_CHANGED);

        outPacket.encodeInt(chr.getId());

        Guild guild = chr.getGuild();
        if (guild == null) {
            outPacket.encodeString("");
        } else {
            outPacket.encodeString(guild.getName());
        }

        return outPacket;
    }
}
