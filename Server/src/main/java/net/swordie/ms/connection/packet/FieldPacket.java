package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.MarriageRecord;
import net.swordie.ms.client.character.info.FarmUserInfo;
import net.swordie.ms.client.character.items.*;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.runestones.RuneStone;
import net.swordie.ms.client.character.runestones.RuneStoneAckType;
import net.swordie.ms.client.character.skills.ForceAtom;
import net.swordie.ms.client.character.skills.PsychicArea;
import net.swordie.ms.client.character.skills.TownPortal;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.jobs.resistance.OpenGate;
import net.swordie.ms.client.jobs.sengoku.Kanna;
import net.swordie.ms.client.trunk.TrunkDlg;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.*;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.life.Wreckage;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.loaders.containerclasses.MakingSkillRecipe;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Randomizer;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.world.auction.AuctionResult;
import net.swordie.ms.world.field.ClockPacket;
import net.swordie.ms.world.field.bosses.gollux.FallingCatcher;
import net.swordie.ms.world.field.bosses.gollux.GolluxMiniMapFieldClearType;
import net.swordie.ms.world.field.bosses.lucid.*;
import net.swordie.ms.world.field.fieldeffect.FieldEffect;
import net.swordie.ms.world.field.obtacleatom.ObtacleAtomInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleInRowInfo;
import net.swordie.ms.world.field.obtacleatom.ObtacleRadianInfo;

import java.util.*;
import java.util.stream.Collectors;

import static net.swordie.ms.client.jobs.adventurer.magician.IceLightning.BOLT_BARRAGE_TILE;

public class FieldPacket {

    public static OutPacket funcKeyMappedManInit(FuncKeyMap funcKeyMap) {
        OutPacket outPacket = new OutPacket(OutHeader.FUNC_KEY_MAPPED_MAN_INIT);

        if (funcKeyMap.getKeymap() == null || funcKeyMap.getKeymap().size() == 0) {
            outPacket.encodeByte(true);
        } else {
            outPacket.encodeByte(false);
            funcKeyMap.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket beastTamerFuncKeyMappedManInit(List<FuncKeyMap> funcKeyMaps) {
        OutPacket outPacket = new OutPacket(OutHeader.FUNC_KEY_MAPPED_MAN_INIT);
        outPacket.encodeByte(false);
        for (FuncKeyMap funcKeyMap : funcKeyMaps) {
            funcKeyMap.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket runeActSuccess() {
        return new OutPacket(OutHeader.RUNE_ACT_SUCCESS);
    }

    public static OutPacket foxManShowChangeEffect(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.FOX_MAN_SHOW_CHANGE_EFFECT);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }

    public static OutPacket foxManExclResult(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.FOX_MAN_EXCL_RESULT);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }


    public static OutPacket foxManLeaveField(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.FOX_MAN_LEAVE_FIELD);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }

    public static OutPacket showItemUnReleaseEffect(int charID, boolean success, int cItemID, int mgItemID, int eItemID) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_UNRELEASE_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);
        outPacket.encodeInt(cItemID);
        outPacket.encodeInt(mgItemID);
        outPacket.encodeInt(eItemID);


        return outPacket;
    }

    public static OutPacket setHideEffect(boolean hide) {
        OutPacket outPacket = new OutPacket(OutHeader.ADMIN_RESULT);
        outPacket.encodeInt(25);
        outPacket.encodeByte(hide);
        outPacket.encodeByte(hide);
        return outPacket;
    }

    public static OutPacket quickslotInit(List<Integer> keys) {
        OutPacket outPacket = new OutPacket(OutHeader.QUICKSLOT_INIT);

        boolean encode = keys != null && keys.size() > 0;
        outPacket.encodeByte(encode);
        if (encode) {
            for (int i = 0; i < GameConstants.QUICKSLOT_LENGTH; i++) {
                outPacket.encodeInt(i < keys.size() ? keys.get(i) : 0);
            }
        }

        return outPacket;
    }

    public static OutPacket affectedAreaCreated(AffectedArea aa) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_CREATED);

        outPacket.encodeInt(aa.getObjectId());
        outPacket.encodeByte(aa.getAaType());
        outPacket.encodeInt(aa.getOwnerID());
        outPacket.encodeInt(aa.getSkillID());
        outPacket.encodeShort(aa.getSlv());
        outPacket.encodeShort(aa.getDelay());
        aa.getRect().encode(outPacket);
        outPacket.encodeInt(aa.getElemAttr());
        outPacket.encodeInt(0);
        outPacket.encodePosition(aa.getPosition());
        outPacket.encodeInt(aa.getForce());
        outPacket.encodeInt(aa.getOption());
        outPacket.encodeByte(0);
        outPacket.encodeInt(aa.getChrLv()); // chr Lv
        if (SkillConstants.isFlipAffectAreaSkill(aa.getSkillID())) {
            outPacket.encodeByte(aa.isFlip());
        }
        outPacket.encodeInt(aa.getSkillID() == BOLT_BARRAGE_TILE ? 0 : aa.getDuration()); // duration
        outPacket.encodeInt(0); // v208
        outPacket.encodeInt(0); // new v214
        outPacket.encodeByte(aa.hasHitMob()); // new v214
        boolean bool = false;
        outPacket.encodeByte(bool); // ?
        if (bool) {
            int size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
                outPacket.encodeString("");
            }
        }
        return outPacket;
    }

    public static OutPacket affectedAreaRemoved(AffectedArea aa, boolean mistEruption) {
        OutPacket outPacket = new OutPacket(OutHeader.AFFECTED_AREA_REMOVED);

        outPacket.encodeInt(aa.getObjectId());
        if (aa.getSkillID() == 2111003) {
            outPacket.encodeByte(mistEruption);
        }

        return outPacket;
    }

    public static OutPacket installedAreaFire(AffectedArea aa, List<AffectedArea> toFire) {
        OutPacket outPacket = new OutPacket(OutHeader.INSTALLED_AREA_FIRE);

        outPacket.encodeInt(aa.getSkillID());
        outPacket.encodeInt(aa.getSlv());
        outPacket.encodeInt(toFire.size());
        for (AffectedArea area : toFire) {
            outPacket.encodeInt(area.getObjectId());
        }

        return outPacket;
    }

    public static OutPacket curNodeEventEnd(boolean enable) {
        OutPacket outPacket = new OutPacket(OutHeader.CUR_NODE_EVENT_END);

        outPacket.encodeByte(enable);

        return outPacket;
    }

    public static OutPacket createForceAtom(ForceAtom fa) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_FORCE_ATOM);

        int forceAtomType = fa.getForceAtomEnum().getForceAtomType();
        outPacket.encodeByte(fa.isByMob());
        if (fa.isByMob()) {
            outPacket.encodeInt(fa.getUserOwner());
        }
        outPacket.encodeInt(fa.getCharId());
        outPacket.encodeInt(forceAtomType);
        if (forceAtomType != 0 && forceAtomType != 9 && forceAtomType != 14 && forceAtomType != 29 && forceAtomType != 35 && forceAtomType != 36 && forceAtomType != 37
                && forceAtomType != 42) {
            outPacket.encodeByte(fa.isToMob());
            switch (forceAtomType) {
                case 2:
                case 3:
                case 6:
                case 7:
                case 11:
                case 12:
                case 13:
                case 17:
                case 19:
                case 20:
                case 23:
                case 24:
                case 25:
                case 27:
                case 28:
                case 30:
                case 32:
                case 34:
                case 38:
                case 39:
                case 40:
                case 41:
                case 47:
                case 48:
                case 49:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 60: // v210
                case 64: // v210
                    outPacket.encodeInt(fa.getTargetIdList().size());
                    for (int i : fa.getTargetIdList()) {
                        outPacket.encodeInt(i);
                    }
                    break;
                default:
                    outPacket.encodeInt(fa.getTargetIdList().get(0));
                    if (forceAtomType == 62) { // v210
                        for (int i : fa.getTargetIdList()) {
                            outPacket.encodeInt(i);
                        }
                    }
                    break;
            }
        }
        if (forceAtomType != 35) {
            outPacket.encodeInt(fa.getSkillId());
        }
        for (ForceAtomInfo fai : fa.getFaiList()) {
            outPacket.encodeByte(1);
            fai.encode(outPacket);
        }
        outPacket.encodeByte(0);
        switch (forceAtomType) {
            case 11:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodeInt(fa.getBulletId());
                break;
            case 9:
                outPacket.encodeRectInt(fa.getRect());
                break;
            case 15:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodeByte(0); // Unknown Boolean
                break;
            case 29:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodePositionInt(fa.getForcedTargetPosition());
                break;
            case 4:
            case 16:
            case 26:
            case 33:
            case 61:
                outPacket.encodePositionInt(fa.getPosition());
                break;
            case 17:
                outPacket.encodeInt(fa.getArriveDir());
                outPacket.encodeInt(fa.getArriveRange());
                break;
            case 18:
                outPacket.encodePositionInt(fa.getForcedTargetPosition());
            case 27:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodeInt(0);
                break;
            case 28:
            case 34:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodeInt(fa.getTime());
                break;
            case 57:
            case 58:
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodeInt(fa.getTime());
                outPacket.encodePositionInt(fa.getPosition());
                break;
            case 35:
                outPacket.encodePositionInt(fa.getForcedTargetPosition());
                break;
            case 36:
            case 39:
                outPacket.encodeInt(4);
                outPacket.encodeInt(550);
                outPacket.encodeInt(0);
                outPacket.encodeRectInt(fa.getRect());
                if (forceAtomType == 36) {
                    outPacket.encodeRectInt(fa.getRect2());
                    outPacket.encodeInt(0);
                }
                break;
            case 37:
                outPacket.encodeInt(0);
                outPacket.encodeRectInt(fa.getRect());
                outPacket.encodePositionInt(fa.getForcedTargetPosition());
                break;
            case 42:
                outPacket.encodeRectInt(fa.getRect());
                break;
            case 49:
                outPacket.encodeInt(fa.getBulletId());
                outPacket.encodeInt(fa.getUserOwner()); // Summon Obj Id
                outPacket.encodeRectInt(fa.getRect());
                break;
            case 50:
                outPacket.encodePositionInt(fa.getForcedTargetPosition());
                break;
        }

        outPacket.encodeInt(0); // new 208.3
        outPacket.encodeInt(0);

        return outPacket;
    }

    public static OutPacket createArkForceAtom(int chrId, int skillId, ForceAtom abyssalFA, ForceAtom gustFA, ForceAtom scarletFA, ForceAtom basicFA) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_ARK_FORCE_ATOM);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(skillId);

        outPacket.encodeInt(4); // size of the different force atoms Ark can get.

        // Abyssal Force Atom
        outPacket.encodeInt(abyssalFA.getForceAtomEnum().getForceAtomType());
        outPacket.encodeInt(abyssalFA.getSkillId());
        outPacket.encodeInt(abyssalFA.getTargetIdList().size());
        for (int i = 0; i < abyssalFA.getTargetIdList().size(); i++) {
            outPacket.encodeInt(abyssalFA.getTargetIdList().get(i));
            outPacket.encodeByte(abyssalFA.getFaiList().size());
            //for (ForceAtomInfo fai : gustFA.getFaiList()) {
            abyssalFA.getFaiList().get(i).encode(outPacket);
            outPacket.encodeByte(0); // unk
            //}
        }

        // Gust Force Atom
        outPacket.encodeInt(gustFA.getForceAtomEnum().getForceAtomType());
        outPacket.encodeInt(gustFA.getSkillId());
        outPacket.encodeInt(gustFA.getTargetIdList().size());
        for (int i = 0; i < gustFA.getTargetIdList().size(); i++) {
            outPacket.encodeInt(gustFA.getTargetIdList().get(i));
            outPacket.encodeByte(gustFA.getFaiList().size());
            //for (ForceAtomInfo fai : gustFA.getFaiList()) {
            gustFA.getFaiList().get(i).encode(outPacket);
            outPacket.encodeByte(0); // unk
            //}
        }

        // Scarlet Force Atom
        outPacket.encodeInt(scarletFA.getForceAtomEnum().getForceAtomType());
        outPacket.encodeInt(scarletFA.getSkillId());
        outPacket.encodeInt(scarletFA.getTargetIdList().size());
        for (int i = 0; i < scarletFA.getTargetIdList().size(); i++) {
            outPacket.encodeInt(scarletFA.getTargetIdList().get(i));
            outPacket.encodeByte(scarletFA.getFaiList().size());
            //for (ForceAtomInfo fai : gustFA.getFaiList()) {
            scarletFA.getFaiList().get(i).encode(outPacket);
            outPacket.encodeByte(0); // unk
            //}
        }

        // Basic Force Atom
        outPacket.encodeInt(basicFA.getForceAtomEnum().getForceAtomType());
        outPacket.encodeInt(basicFA.getSkillId());
        outPacket.encodeInt(basicFA.getTargetIdList().size());
        for (int i = 0; i < basicFA.getTargetIdList().size(); i++) {
            outPacket.encodeInt(basicFA.getTargetIdList().get(i));
            outPacket.encodeByte(basicFA.getFaiList().size());
            //for (ForceAtomInfo fai : gustFA.getFaiList()) {
            basicFA.getFaiList().get(i).encode(outPacket);
            outPacket.encodeByte(0); // unk
            //}
        }

        return outPacket;
    }

    public static OutPacket guideForceAtom(int chrId, int faKey, int mobId) {
        OutPacket outPacket = new OutPacket(OutHeader.GUIDE_FORCE_ATOM);

        outPacket.encodeInt(faKey);
        outPacket.encodeInt(chrId);
        outPacket.encodeInt(1);
        outPacket.encodeInt(1);
        outPacket.encodeInt(1);
        outPacket.encodeInt(mobId);

        return outPacket;
    }

    public static OutPacket finalAttackRequest(Char chr, int skillID, int finalSkillID) {
        OutPacket outPacket = new OutPacket(OutHeader.FINAL_ATTACK_REQUEST);

        WeaponType wt = ItemConstants.getWeaponType(chr.getEquippedItemByBodyPart(BodyPart.Weapon).getItemId());

        outPacket.encodeInt(Util.getCurrentTime());
        outPacket.encodeInt(1); // success
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(finalSkillID);
        outPacket.encodeInt(wt == null ? 0 : wt.getVal());

        return outPacket;
    }

    public static OutPacket setAmmo(int ammo) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_AMMO);

        outPacket.encodeInt(ammo);

        return outPacket;
    }

    public static OutPacket createPsychicArea(int charID, PsychicArea pa) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_AREA);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(pa.success);
        outPacket.encodeInt(pa.action);
        outPacket.encodeInt(pa.actionSpeed);
        outPacket.encodeInt(pa.psychicAreaKey);
        outPacket.encodeInt(pa.skillID);
        outPacket.encodeShort(pa.slv);
        outPacket.encodeInt(pa.localPsychicAreaKey);
        outPacket.encodeInt(pa.duration);
        outPacket.encodeByte(pa.isLeft);
        outPacket.encodeShort(pa.skeletonFilePathIdx);
        outPacket.encodeShort(pa.skeletonAniIdx);
        outPacket.encodeShort(pa.skeletonLoop);
        outPacket.encodePositionInt(pa.start);

        return outPacket;
    }

    public static OutPacket characterInfo(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.CHARACTER_INFO);

        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(chr.getStat(Stat.level));
        outPacket.encodeShort(chr.getJob());
        outPacket.encodeShort(chr.getStat(Stat.job));
        outPacket.encodeByte(cs.getPvpGrade());
        outPacket.encodeInt(cs.getPop()); //Fame

        //marriage
        MarriageRecord marriage = chr.getMarriageRecord();
        outPacket.encodeByte(1);
        outPacket.encodeInt(1);
        outPacket.encodeInt(1);
        outPacket.encodeInt(2);
        outPacket.encodeShort(0);
        outPacket.encodeInt(1112300);
        outPacket.encodeInt(1112300);
        outPacket.encodeString("v214", 13); //max length 13
        outPacket.encodeString("v214", 13);


        List<Short> makingSkills = new ArrayList<>();
        for (short i = 9200; i <= 9204; i++) {
            if (chr.getMakingSkillLevel(i * 10000) > 0) {
                makingSkills.add(i);
            }
        }
        outPacket.encodeByte(makingSkills.size());
        for (Short makingSkill : makingSkills) {
            outPacket.encodeShort(makingSkill);
        }
        outPacket.encodeString(chr.getGuild() == null ? "-" : chr.getGuild().getName());
        outPacket.encodeString(chr.getGuild() == null || chr.getGuild().getAlliance() == null ? "-" :
                chr.getGuild().getAlliance().getName());
        outPacket.encodeByte(-1); // Forced pet IDx
        outPacket.encodeByte(0); // User state (?)
        outPacket.encodeByte(0); // 202
//        outPacket.encodeByte(chr.getPets().size() > 0); // pet activated
        for (Pet pet : chr.getPets()) {
            PetItem pi = pet.getItem();
            outPacket.encodeByte(1);
            outPacket.encodeInt(pet.getIdx());
            outPacket.encodeInt(pi.getItemId());
            outPacket.encodeString(pet.getName());
            outPacket.encodeByte(pi.getLevel());
            outPacket.encodeShort(pi.getTameness());
            outPacket.encodeByte(pi.getRepleteness());
            outPacket.encodeShort(pi.getPetSkill());
            outPacket.encodeInt(0); // equip
            outPacket.encodeInt(pi.getPetHue());
        }
        outPacket.encodeByte(0); // end of pets

        // MedalAchievementInfo::Decode
        Equip medal = (Equip) chr.getEquippedItemByBodyPart(BodyPart.Medal);
        outPacket.encodeInt(medal == null ? 0 : medal.getItemId());
        // for each medal, encode int (itemID) and complete time (FT)
        outPacket.encodeShort(0); // medal size
        // End MedalAchievementInfo::Decode
        chr.encodeDamageSkins(outPacket);
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharisma());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getInsight());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getWill());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCraft());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getSense());
        outPacket.encodeByte(cs.getNonCombatStatDayLimit().getCharm());
        outPacket.encodeInt(chr.getUserId());
        // FarmUserInfo::Decode
        new FarmUserInfo().encode(outPacket);
        // End FarmUserInfo::Decode
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        List<Integer> chairs = chr.getInstallInventory().getItems().stream().filter(i -> (i.getItemId() / 10000 == 301)).mapToInt(Item::getItemId).boxed().collect(Collectors.toList());
        outPacket.encodeInt(chairs.size()); //chair amount(size)
        for (int itemId : chairs) {

            outPacket.encodeInt(itemId);
        }

        // sub
        outPacket.encodeInt(30);
        outPacket.encodeInt(0);
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
        // ~sub


        return outPacket;
    }

    public static OutPacket showItemUpgradeEffect(int charID, boolean success, boolean enchantDlg, int uItemID, int eItemID, boolean boom) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(boom ? 2 : success ? 1 : 0);
        outPacket.encodeByte(enchantDlg);
        outPacket.encodeInt(uItemID);
        outPacket.encodeInt(eItemID);

        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket showItemReleaseEffect(int charID, short pos, boolean bonus) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_RELEASE_EFFECT);

        outPacket.encodeInt(charID);

        outPacket.encodeShort(pos);
        outPacket.encodeByte(bonus);

        return outPacket;
    }

    public static OutPacket hyperUpgradeDisplay(Equip equip, boolean downgradeable, long meso, long beforeMvp,
                                                long beforePc, int successChance, int oldSuccessChance,
                                                int destroyChance, int oldDestroyChance, boolean chanceTime) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.HyperUpgradeDisplay.getVal());
        outPacket.encodeByte(downgradeable);
        outPacket.encodeLong(meso);
        outPacket.encodeLong(beforeMvp); // beforeMvp
        outPacket.encodeLong(beforePc); // beforePc
        outPacket.encodeByte(beforeMvp != 0); // bMvp
        outPacket.encodeByte(beforePc != 0 && beforeMvp == 0); // mvp over pc
        outPacket.encodeInt(successChance);
        outPacket.encodeInt(destroyChance);
        outPacket.encodeInt(oldSuccessChance); // old sucChance
        outPacket.encodeInt(oldDestroyChance);
        outPacket.encodeByte(chanceTime);
        TreeMap<EnchantStat, Integer> vals = equip.getHyperUpgradeStats();
        int mask = 0;
        for (EnchantStat es : vals.keySet()) {
            mask |= es.getVal();
        }
        outPacket.encodeInt(mask);
        vals.forEach((es, val) -> outPacket.encodeInt(val));

        return outPacket;
    }

    public static OutPacket miniGameDisplay(EquipmentEnchantType eeType) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(eeType.getVal());
        outPacket.encodeByte(0);
        outPacket.encodeInt(2000); // TODO nSeed

        return outPacket;
    }

    public static OutPacket showUpgradeResult(Equip oldEquip, Equip equip, boolean succeed, boolean boom, boolean canDegrade) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowHyperUpgradeResult.getVal());
        outPacket.encodeInt(boom ? 2 : succeed ? 1 : canDegrade ? 0 : 3);
        outPacket.encodeByte(0);
        oldEquip.encode(outPacket);
        equip.encode(outPacket);

        return outPacket;
    }

        public static OutPacket showUnknownEnchantFailResult(byte msg) {
            OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

            outPacket.encodeByte(EquipmentEnchantType.ShowUnknownFailResult.getVal());
            outPacket.encodeByte(msg);

            return outPacket;
        }

    public static OutPacket scrollUpgradeDisplay(boolean feverTime, List<ScrollUpgradeInfo> infos) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ScrollUpgradeDisplay.getVal());
        outPacket.encodeByte(feverTime);

        outPacket.encodeByte(infos.size());
        for (ScrollUpgradeInfo sui : infos) {
            outPacket.encode(sui);
        }

        return outPacket;
    }

    public static OutPacket showScrollUpgradeResult(boolean feverAfter, int result, String desc, Equip prevEquip,
                                                    Equip newEquip) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowScrollUpgradeResult.getVal());

        outPacket.encodeByte(feverAfter);
        outPacket.encodeInt(result);
        outPacket.encodeString(desc);
        outPacket.encode(prevEquip);
        outPacket.encode(newEquip);

        return outPacket;
    }

    public static OutPacket showTranmissionResult(Equip fromEq, Equip toEq) {
        OutPacket outPacket = new OutPacket(OutHeader.EQUIPMENT_ENCHANT);

        outPacket.encodeByte(EquipmentEnchantType.ShowTransmissionResult.getVal());
        fromEq.encode(outPacket);
        toEq.encode(outPacket);

        return outPacket;
    }

    public static OutPacket redCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip, int cubeCount) {
        OutPacket outPacket = new OutPacket(OutHeader.RED_CUBE_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        outPacket.encodeInt(cubeCount);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket bonusCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip, int cubeCount) {
        OutPacket outPacket = new OutPacket(OutHeader.BONUS_CUBE_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        outPacket.encodeInt(cubeCount);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket inGameCubeResult(int charID, boolean upgrade, int cubeID, int ePos, Equip equip, boolean bonus, int cubeCount) {
        OutPacket outPacket = new OutPacket(!bonus ? OutHeader.IN_GAME_CUBE_RESULT : OutHeader.IN_GAME_ADDITIONAL_CUBE_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeByte(upgrade);
        outPacket.encodeInt(cubeID);
        outPacket.encodeInt(ePos);
        outPacket.encodeInt(cubeCount);
        equip.encode(outPacket);

        return outPacket;
    }

    public static OutPacket sitResult(int chrId, int fieldSeatId) {
        OutPacket outPacket = new OutPacket(OutHeader.SIT_RESULT);

        outPacket.encodeInt(chrId);
        outPacket.encodeByte(!(fieldSeatId == -1));
        if (!(fieldSeatId == -1)) {
            outPacket.encodeShort(fieldSeatId);
        }

        return outPacket;
    }


    public static OutPacket questClear(int qrKey) {
        OutPacket outPacket = new OutPacket(OutHeader.QUEST_CLEAR);

        outPacket.encodeInt(qrKey);

        return outPacket;
    }

    public static OutPacket setQuestTime(List<Triple<Integer, FileTime, FileTime>> questTimes) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_QUEST_TIME);

        outPacket.encodeByte(questTimes.size());
        for (Triple<Integer, FileTime, FileTime> times : questTimes) {
            outPacket.encodeInt(times.getLeft());
            outPacket.encodeFT(times.getMiddle());
            outPacket.encodeFT(times.getRight());
        }

        return outPacket;
    }

    public static OutPacket addWreckage(Wreckage wreckage, int wreckageCount) {
        OutPacket outPacket = new OutPacket(OutHeader.ADD_WRECKAGE);

        outPacket.encodeInt(wreckage.getOwnerId());  // owner Id
        outPacket.encodePositionInt(wreckage.getPosition());
        outPacket.encodeInt(wreckage.getDuration());  // duration
        outPacket.encodeInt(wreckage.getObjectId());  //evanWreckage.nIDx
        outPacket.encodeInt(wreckage.getSkillId());  //nSkillID
        outPacket.encodeInt(wreckage.getType());  //nType

        outPacket.encodeInt(wreckageCount);  //Number on Skill Icon, # of Wreckages on map

        return outPacket;
    }

    public static OutPacket delWreckage(Char chr, List<Wreckage> wreckageList) {
        OutPacket outPacket = new OutPacket(OutHeader.DEL_WRECKAGE);

        outPacket.encodeInt(chr.getId()); // Owner Id
        outPacket.encodeInt(wreckageList.size()); //Count
        outPacket.encodeByte(true); //Unk Boolean
        for (Wreckage wreckage : wreckageList) {
            outPacket.encodeInt(wreckage.getObjectId()); // Wreckage Id
        }

        return outPacket;
    }

    public static OutPacket enterFieldFoxMan(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.FOX_MAN_ENTER_FIELD);

        Position position = chr.getPosition();

        outPacket.encodeInt(chr.getId());
        outPacket.encodeShort(0);   // 1 = Haku Old Form,  0 = Haku New Form
        outPacket.encodePosition(position);
        outPacket.encodeByte(Kanna.hasHakuPerfected(chr) ? 5 : 4); //MoveAction
        outPacket.encodeShort((short) 0); //Foothold
        outPacket.encodeInt(Kanna.hasHakuPerfected(chr) ? 2 : 0); //nUpgrade

        Equip fan = (Equip) chr.getEquippedInventory().getItemBySlot(BodyPart.HakuFan.getVal());
        outPacket.encodeInt(fan != null ? fan.getItemId() : 0); //FanID Equipped by Haku

        return outPacket;
    }

    public static OutPacket whisper(Char from, byte channelIdx, boolean gm, String msg, boolean notFound) {
        OutPacket outPacket = new OutPacket(OutHeader.WHISPER);

        if (notFound) {
            outPacket.encodeByte(9);
            outPacket.encodeString(from.getName());
            outPacket.encodeByte(0); // new v213.3
            outPacket.encodeInt(channelIdx);
        } else {
            outPacket.encodeByte(18);
            outPacket.encodeString(from.getName());
            outPacket.encodeInt(0);
            outPacket.encodeByte(channelIdx); // channel
            outPacket.encodeByte(0); // ?
            outPacket.encodeString(msg);
            from.encodeChatInfo(outPacket, msg);
            boolean encode = false;
            outPacket.encodeByte(encode); // if true, encode something in a virtual func
            if (encode) {
                outPacket.encodeString(""); // new v213.3
                // virtual func here
            }
        }

        return outPacket;
    }

    public static OutPacket teleport(Position position, Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.TELEPORT);
        outPacket.encodeByte(false);
        outPacket.encodeByte(6);

        outPacket.encodeInt(chr.getId());
        outPacket.encodePosition(position);

        return outPacket;
    }

    public static OutPacket fieldEffect(FieldEffect fieldEffect) {
        OutPacket outPacket = new OutPacket(OutHeader.FIELD_EFFECT);

        fieldEffect.encode(outPacket);

        return outPacket;
    }
    
    public static OutPacket setAchieveRate(int ratio) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_ACHIEVE_RATE);
        
        outPacket.encodeInt(ratio);
        
        return outPacket;
    }

    public static OutPacket removeBlowWeather() {
        return blowWeather(0, null, 0, null);
    }

    public static OutPacket blowWeather(int itemID, String message, int seconds, byte[] packedAvatarLook) {
        OutPacket outPacket = new OutPacket(OutHeader.BLOW_WEATHER);

        outPacket.encodeInt(itemID);
        if (itemID > 0) {
            outPacket.encodeString(message);
            outPacket.encodeInt(seconds); // seconds
            outPacket.encodeByte(packedAvatarLook != null);// boolean if true send PackedCharacterLook
            if (packedAvatarLook != null) {
                outPacket.encodeArr(packedAvatarLook);
            }
        }
        return outPacket;
    }

    public static OutPacket trunkDlg(TrunkDlg trunkDlg) {
        OutPacket outPacket = new OutPacket(OutHeader.TRUNK_DLG);

        outPacket.encodeByte(trunkDlg.getType().getVal());
        trunkDlg.encode(outPacket);

        return outPacket;
    }

    public static OutPacket openUI(UIType uiType) {
        return openUI(uiType.getVal());
    }

    public static OutPacket openUI(int uiID) {
        OutPacket outpacket = new OutPacket(OutHeader.OPEN_UI);

        outpacket.encodeInt(uiID);

        return outpacket;
    }

    public static OutPacket closeUI(UIType uiType) {
        return closeUI(uiType.getVal());
    }

    public static OutPacket closeUI(int uiID) {
        OutPacket outpacket = new OutPacket(OutHeader.CLOSE_UI);
        outpacket.encodeInt(uiID);
        return outpacket;
    }

    public static OutPacket socketCreateResult(boolean success) {
        OutPacket outPacket = new OutPacket(OutHeader.SOCKET_CREATE_RESULT);

        outPacket.encodeByte(success ? 2 : 3);

        return outPacket;
    }

    public static OutPacket changeMobZone(int mobID, int dataType) {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_MOB_ZONE);

        outPacket.encodeInt(mobID);
        outPacket.encodeInt(dataType);

        return outPacket;
    }

    public static OutPacket clearObtacle() {
        return new OutPacket(OutHeader.CLEAR_OBSTACLE);
    }

    public static OutPacket createObtacle(ObtacleAtomCreateType oact, ObtacleInRowInfo oiri, ObtacleRadianInfo ori,
                                          Set<ObtacleAtomInfo> atomInfos) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_OBSTACLE);

        outPacket.encodeInt(0); // ? gets used in 1 function, which forwards it to another, which does nothing with it
        outPacket.encodeInt(atomInfos.size());
        outPacket.encodeByte(oact.getVal());
        if (oact == ObtacleAtomCreateType.IN_ROW) {
            oiri.encode(outPacket);
        } else if (oact == ObtacleAtomCreateType.RADIAL) {
            ori.encode(outPacket);
        }
        for (ObtacleAtomInfo atomInfo : atomInfos) {
            outPacket.encodeByte(true); // false -> no encode
            atomInfo.encode(outPacket);
            if (oact == ObtacleAtomCreateType.DIAGONAL) {
                atomInfo.getObtacleDiagonalInfo().encode(outPacket);
            }
        }

        return outPacket;
    }

    public static OutPacket openDimensionalMirror() {
        OutPacket outPacket = new OutPacket(OutHeader.DIMENSIONAL_MIRROR);
        outPacket.encodeInt(DimensionalMirrorType.values().length);
        for (DimensionalMirrorType dmt : DimensionalMirrorType.values()) {
            outPacket.encodeString(""); //name
            outPacket.encodeString(dmt.getDesc());
            outPacket.encodeInt(dmt.getReqLv());
            outPacket.encodeInt(dmt.getPos());
            outPacket.encodeInt(dmt.getID());
            outPacket.encodeInt(dmt.getQuestID());
            outPacket.encodeInt(0);
            outPacket.encodeByte(dmt.isParty());
            outPacket.encodeInt(dmt.getItems().size());
            for (int j = 0; j < dmt.getItems().size(); j++) {
                outPacket.encodeInt(dmt.getItems().get(j));
            }
        }
        return outPacket;
    }

    public static OutPacket runeStoneAppear(RuneStone runeStone) { //Spawn in RuneStone
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_APPEAR);

        outPacket.encodeInt(0); // object id ??
        outPacket.encodeInt(runeStone.getEventType().ordinal());
        outPacket.encodeInt(0); // new 202, doesn't do anything visually
        outPacket.encodeInt(runeStone.getRuneType().getVal()); // Rune Type

        outPacket.encodePositionInt(runeStone.getPosition()); // Position
        outPacket.encodeByte(runeStone.isFlip()); // flip

        return outPacket;
    }

    public static OutPacket completeRune(Char chr) { //RuneStone Disappears
        OutPacket outPacket = new OutPacket(OutHeader.COMPLETE_RUNE);

        outPacket.encodeInt(0);
        outPacket.encodeInt(chr.getId());

        return outPacket;
    }

    public static OutPacket runeStoneUseAck(RuneStoneAckType type, int time) {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_USE_ACK);

        outPacket.encodeInt(type.getVal());
        switch (type) {
            case RuneDelayTime: // Rune Delay time
                outPacket.encodeInt(time);
                break;
            case TooStrongForYouToHandle: //That rune is to strong for you to handle
                break;
            case ShowArrows: //Shows arrows
                // TODO: find correct formula
                outPacket.encodeInt(1584890054); // idk
                outPacket.encodeInt(24);
                outPacket.encodeInt(661368441); // idk
                outPacket.encodeInt(758724816); // whatever.
                outPacket.encodeInt(758724778 + Randomizer.nextInt(4)); // 1st
                outPacket.encodeInt(758724778 + Randomizer.nextInt(4)); // 2nd
                outPacket.encodeInt(758724778 + Randomizer.nextInt(4)); // 3rd
                outPacket.encodeInt(758724778 + Randomizer.nextInt(4)); // 4th
                break;
        }

        return outPacket;
    }

    public static OutPacket runeStoneDisappear(int charID) { //RuneStone is Used
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_DISAPPEAR);

        outPacket.encodeInt(0); // Has to be 0
        outPacket.encodeInt(charID);
        outPacket.encodeInt(0); // EXP%?
        outPacket.encodeByte(false); // new 188
        outPacket.encodeByte(1); // new

        return outPacket;
    }


    public static OutPacket runeStoneSkillAck(RuneType runeType) {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_SKILL_ACK);

        outPacket.encodeInt(runeType.getVal());

        return outPacket;
    }

    public static OutPacket runeStoneClearAndAllRegister() {
        OutPacket outPacket = new OutPacket(OutHeader.RUNE_STONE_CLEAR_AND_ALL_REGISTER);
        int count = 0;
        outPacket.encodeInt(count); // count
        outPacket.encodeInt(1); // new
        for (int i = 0; i < count; i++) {
            outPacket.encodeInt(0); // not sure, but whatever
        }

        return outPacket;
    }

    /**
     * Creates a Clock on a Field.
     *
     * @param clockPacket the clock to display
     * @return packet for the client
     */
    public static OutPacket clock(ClockPacket clockPacket) {
        OutPacket outPacket = new OutPacket(OutHeader.CLOCK);

        clockPacket.encode(outPacket);

        return outPacket;
    }

    /**
     * Creates a packet for changing the elite state of a field.
     *
     * @param eliteState             The new elite state
     * @param notShowPopup           whether or not the popup should show up (warning message for boss spawn, countdown for bonus)
     * @param bgm                    The new bgm if the state is ELITE_BOSS
     * @param propSpecialEliteEffect special elite effect
     * @param backUOL                back uol
     * @return packet for the client
     */
    public static OutPacket eliteState(EliteState eliteState, boolean notShowPopup, String bgm, String propSpecialEliteEffect,
                                       String backUOL) {
        OutPacket outPacket = new OutPacket(OutHeader.ELITE_STATE);

        outPacket.encodeInt(eliteState.getVal()); // elite state
        outPacket.encodeInt(notShowPopup ? 1 : 0);
        outPacket.encodeInt(0); // new v20X (character id)
        switch (eliteState) {
            case BonusStage:
            case BonusStage2:
                boolean custom = bgm != null || backUOL != null || propSpecialEliteEffect != null;
                outPacket.encodeByte(custom); // Bgm36.img/HappyTimeShort | Map/Map/Map9/924050000.img/back | Effect/EliteMobEff.img/eliteBonusStage
                if (custom) {
                    outPacket.encodeString(bgm); //sBgm Sound/%s
                    outPacket.encodeString(backUOL); //sBackUOL Map/Map/Map9/924050000.img/back
                    outPacket.encodeString(propSpecialEliteEffect); //sSpecialEffect Effect/EliteMobEff.img/eliteBonusStage
                    outPacket.encodeString("");
                    outPacket.encodeString("");
                    outPacket.encodeByte(0);
                }
                break;
            case EliteBoss:
            case EliteBoss2:
                outPacket.encodeString(bgm); //sBgm Sound/%s
                outPacket.encodeString(backUOL); //sFrame Effect/EliteMobEff.img/eliteMonsterFrame
                outPacket.encodeString(propSpecialEliteEffect); //sFrameEffect Effect/EliteMobEff.img/eliteMonsterEffect
                break;
            case Unk6:
                outPacket.encodeString("");
                break;
        }

        return outPacket;
    }


    public static OutPacket setQuickMoveInfo(List<QuickMoveInfo> quickMoveInfos) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_QUICK_MOVE_INFO);

        outPacket.encodeByte(quickMoveInfos.size());
        quickMoveInfos.forEach(qmi -> qmi.encode(outPacket));

        return outPacket;
    }

    public static OutPacket groupMessage(GroupMessageType gmt, Char from, String msg) {

        OutPacket outPacket = new OutPacket(OutHeader.GROUP_MESSAGE.getValue());

        outPacket.encodeByte(gmt.ordinal());

        outPacket.encodeInt(from.getUserId());
        outPacket.encodeInt(from.getId());
        outPacket.encodeString(from.getName());
        outPacket.encodeString(msg);
        from.encodeChatInfo(outPacket, msg);


        return outPacket;
    }

    public static OutPacket itemLinkedGroupMessage(GroupMessageType gmt, Char from, String msg, Item item) {

        OutPacket outPacket = new OutPacket(OutHeader.GROUP_MESSAGE.getValue());

        outPacket.encodeByte(gmt.ordinal());

        outPacket.encodeInt(from.getUserId());
        outPacket.encodeInt(from.getId());
        outPacket.encodeString(from.getName());
        outPacket.encodeString(msg);
        from.encodeChatInfo(outPacket, msg);
        outPacket.encodeByte(item != null);
        if (item != null) {
            outPacket.encode(item);
        }


        return outPacket;
    }

    public static OutPacket openGateCreated(OpenGate openGate) {
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_GATE_CREATED);

        outPacket.encodeByte(1); // Animation
        outPacket.encodeInt(openGate.getChr().getId()); // Character Id
        outPacket.encodePosition(openGate.getPosition()); // Position
        outPacket.encodeByte(openGate.getGateId()); // Gate Id
        outPacket.encodeInt(openGate.getParty() != null ? openGate.getParty().getId() : 0); // Party Id

        return outPacket;
    }

    public static OutPacket openGateRemoved(OpenGate openGate) {
        OutPacket outPacket = new OutPacket(OutHeader.OPEN_GATE_REMOVED);

        outPacket.encodeByte(1); // Animation
        outPacket.encodeInt(openGate.getChr().getId()); // Character Id
        outPacket.encodeByte(openGate.getGateId()); // Gate Id

        return outPacket;
    }

    public static OutPacket createMirrorImage(Position position, int alpha, int red, int green, int blue, boolean left) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_MIRROR_IMAGE);

        outPacket.encodePositionInt(position);
        outPacket.encodeInt(alpha); // nAlpha   out of 1,000 (?)
        outPacket.encodeInt(red); // R  out of 100,000 (?)

        outPacket.encodeInt(green); // G  out of 100,000 (?)
        outPacket.encodeInt(blue); // B  out of 100,000 (?)
        outPacket.encodeInt(left ? 1 : 0); // bLeft

        return outPacket;
    }

    public static OutPacket townPortalCreated(TownPortal townPortal, boolean noAnimation) {
        OutPacket outPacket = new OutPacket(OutHeader.TOWN_PORTAL_CREATED);

        outPacket.encodeByte(noAnimation); // No Animation  (false = Animation : true = No Animation)
        outPacket.encodeInt(townPortal.getChr().getId());
        outPacket.encodePosition(townPortal.getFieldPosition()); // as this doesn't need to be initialised yet.
        outPacket.encodePosition(townPortal.getFieldPosition()); //

        return outPacket;
    }

    public static OutPacket townPortalRemoved(TownPortal townPortal, boolean animation) {
        OutPacket outPacket = new OutPacket(OutHeader.TOWN_PORTAL_REMOVED);

        outPacket.encodeByte(animation); // Animation
        outPacket.encodeInt(townPortal.getChr().getId());

        return outPacket;
    }

    public static OutPacket setOneTimeAction(int charID, int action, int duration) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_ONE_TIME_ACTION);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(action);
        outPacket.encodeInt(duration);

        return outPacket;
    }

    public static OutPacket makingSkillResult(int charID, int recipeCode, MakingSkillResult result, MakingSkillRecipe.TargetElem target, int incSkillProficiency) {
        OutPacket outPacket = new OutPacket(OutHeader.MAKING_SKILL_RESULT);

        outPacket.encodeInt(charID);

        outPacket.encodeInt(recipeCode);
        outPacket.encodeInt(result.getVal());
        if (result == MakingSkillResult.SUCESS_SOSO || result == MakingSkillResult.SUCESS_GOOD || result == MakingSkillResult.SUCESS_COOL) {
            outPacket.encodeInt(target.getItemID());
            outPacket.encodeInt(target.getCount());
        }
        outPacket.encodeInt(incSkillProficiency);

        return outPacket;
    }

    public static OutPacket auctionResult(AuctionResult auctionResult) {
        OutPacket outPacket = new OutPacket(OutHeader.AUCTION_RESULT);

        auctionResult.encode(outPacket);

        return outPacket;
    }

    public static OutPacket registerExtraSkill(Char chr, int mainSkillId, List<Integer> extraSkillIds) {
        return registerExtraSkill(chr.getPosition(), mainSkillId, extraSkillIds, chr.isLeft());
    }

    public static OutPacket registerExtraSkill(Position position, int mainSkilId, List<Integer> extraSkillIds, boolean isLeft) {
        OutPacket outPacket = new OutPacket(OutHeader.REGISTER_EXTRA_SKILL);

        outPacket.encodePositionInt(position);
        outPacket.encodeShort(isLeft ? -1 : 1);
        outPacket.encodeInt(mainSkilId);
        outPacket.encodeShort(extraSkillIds.size());
        for (int extraSkillId : extraSkillIds) {
            outPacket.encodeInt(extraSkillId);
        }

        return outPacket;
    }

    public static OutPacket footholdAppear(String footholdName, boolean show) {
        OutPacket outPacket = new OutPacket(OutHeader.FOOT_HOLD_APPEAR);

        int loopSize = 1;

        outPacket.encodeInt(loopSize);
        for (int i = 0; i < loopSize; i++) {
            outPacket.encodeString(footholdName);
            outPacket.encodeByte(0);
            outPacket.encodeInt(show ? 1 : 0);
            outPacket.encodePositionInt(new Position());
        }

        return outPacket;
    }

    public static OutPacket footholdAppear(String[] names, boolean show, Position[] positions) {
        OutPacket outPacket = new OutPacket(OutHeader.FOOT_HOLD_APPEAR);
        int loopSize = names.length;
        outPacket.encodeInt(loopSize);
        for (int i = 0; i < loopSize; i++) {
            if (names[i] == null || positions[i] == null) continue;
            outPacket.encodeString(names[i]);
            outPacket.encodeByte(0);
            outPacket.encodeInt(show ? 1 : 0);
            outPacket.encodePositionInt(positions[i]);
        }
        return outPacket;
    }

    public static OutPacket golluxMiniMap(Map<String, GolluxMiniMapFieldClearType> gFieldMap) {
        OutPacket outPacket = new OutPacket(OutHeader.GOLLUX_MINI_MAP);

        outPacket.encodeInt(gFieldMap.size());

        for (Map.Entry<String, GolluxMiniMapFieldClearType> entry : gFieldMap.entrySet()) {
            String fieldIdString = entry.getKey();
            GolluxMiniMapFieldClearType clearType = entry.getValue();

            outPacket.encodeString(fieldIdString);
            outPacket.encodeString(clearType.getVal());
        }

        return outPacket;
    }

    public static OutPacket createFallingCatcher(FallingCatcher fallingCatcher) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_FALLING_CATCHER);

        outPacket.encodeString(fallingCatcher.getTemplateStr());
        outPacket.encodeInt(fallingCatcher.getHpR());
        outPacket.encodeInt(fallingCatcher.getPositions().size());
        fallingCatcher.getPositions().forEach(outPacket::encodePositionInt);

        return outPacket;

    }

    public static OutPacket golluxPortalOpen(String happeningName) {
        OutPacket outPacket = new OutPacket(OutHeader.GOLLUX_PORTAL_OPEN);

        outPacket.encodeString(happeningName);
        outPacket.encodeInt(1); // show

        return outPacket;
    }

    public static OutPacket lucidButterflyCreated(List<Butterfly> butterflyList) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_BUTTERFLY_CREATED);

        outPacket.encodeInt(0); // unk

        outPacket.encodeInt(butterflyList.size());
        for (Butterfly butterfly : butterflyList) {
            outPacket.encodeInt(butterfly.getType().getVal()); // type
            outPacket.encodePositionInt(butterfly.getPosition()); // position
        }

        return outPacket;
    }

    public static OutPacket lucidButterflyActionAdd(ButterflyType butterflyType, Position position) {
        return lucidButterflyAction(ButterflyActionType.Add, butterflyType, position, 0, 0);
    }

    public static OutPacket lucidButterflyActionMove(Position position) {
        return lucidButterflyAction(ButterflyActionType.Move, ButterflyType.PinkSmall, position, 0, 0);
    }

    public static OutPacket lucidButterflyActionAttack(int count, int delay) {
        return lucidButterflyAction(ButterflyActionType.Attack, ButterflyType.PinkSmall, new Position(), count, delay);
    }

    public static OutPacket lucidButterflyActionRemove() {
        return lucidButterflyAction(ButterflyActionType.Remove, ButterflyType.PinkSmall, new Position(), 0, 0);
    }

    public static OutPacket lucidButterflyAction(ButterflyActionType actionType, ButterflyType butterflyType, Position position, int count, int delay) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_BUTTERFLY_ACTION);

        outPacket.encodeInt(actionType.getVal());
        switch (actionType) {
            case Add:
                outPacket.encodeInt(0); // unk
                outPacket.encodeInt(butterflyType.getVal());
                outPacket.encodePositionInt(position);
                break;
            case Move:
                outPacket.encodePositionInt(position);
                break;
            case Attack:
                outPacket.encodeInt(count);
                outPacket.encodeInt(delay);
                break;
            case Remove:
                // Empty
                break;
        }

        return outPacket;
    }

    public static OutPacket lucidDragonCreated(int phase, Position position, Position createPosition, boolean left) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_DRAGON_CREATED);

        outPacket.encodeInt(phase);
        outPacket.encodePositionInt(position);
        outPacket.encodePositionInt(createPosition);
        outPacket.encodeByte(left);

        return outPacket;
    }

    public static OutPacket doLucidSkillFlowerTrap(Position position, int pattern, boolean flip) {
        return doLucidSkill(LucidSkillType.FlowerTrap1, position, new ArrayList<>(), pattern, flip);
    }

    public static OutPacket doLucidSkillFairyDust(List<Object> fairyDustList) {
        return doLucidSkill(LucidSkillType.FairyDust, new Position(), fairyDustList, 0, false);
    }

    public static OutPacket doLucidSkillLaserRain(List<Object> laserIntervalList, int delay) {
        return doLucidSkill(LucidSkillType.LaserRain, new Position(), laserIntervalList, delay, false);
    }

    public static OutPacket doLucidSkillTeleport(int portalId) {
        return doLucidSkill(LucidSkillType.Teleport, new Position(), new ArrayList<>(), portalId, false);
    }

    public static OutPacket doLucidSkillRush() {
        return doLucidSkill(LucidSkillType.Rush, new Position(), new ArrayList<>(), 0, false);
    }

    public static OutPacket doLucidSkill(LucidSkillType skillType, Position position, List<Object> list, int int1, boolean flip) {
        OutPacket outPacket = new OutPacket(OutHeader.DO_LUCID_SKILL);

        outPacket.encodeInt(238); // hardcoded value in IDA
        outPacket.encodeInt(skillType.getVal());

        switch (skillType) {
            case FlowerTrap1:
            case FlowerTrap2:
            case FlowerTrap3:
                outPacket.encodeInt(int1); // Pattern
                outPacket.encodePositionInt(position); // Position
                outPacket.encodeByte(flip); // flip
                break;
            case FairyDust:
            case FairyDust2:
                outPacket.encodeInt(list.size());
                for (Object fairyDust : list) {
                    if (fairyDust instanceof FairyDust) {
                        outPacket.encodeInt(((FairyDust) fairyDust).getScale()); // scale
                        outPacket.encodeInt(((FairyDust) fairyDust).getDelay()); // delay
                        outPacket.encodeInt(((FairyDust) fairyDust).getSpeed()); // moveSpeed
                        outPacket.encodeInt(((FairyDust) fairyDust).getAngle()); // angle
                    }
                }
                break;
            case LaserRain:
                outPacket.encodeInt(int1); // delay
                outPacket.encodeInt(list.size());
                for (Object interval : list) {
                    outPacket.encodeInt((int) interval); // interval
                }
                break;
            case Teleport:
                outPacket.encodeInt(int1); // portalId
                break;
            case Rush:
                outPacket.encodeInt(0);
                break;
        }

        return outPacket;
    }

    public static OutPacket lucidStainedGlassOnOff(boolean enable, List<String> stringList) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_PHASE_2_STAINED_GLASS_ON_OFF);

        outPacket.encodeByte(enable);
        outPacket.encodeInt(stringList.size());
        for (String string : stringList) {
            outPacket.encodeString(string);
        }

        return outPacket;
    }

    public static OutPacket lucidStainedGlassBreak(List<String> stringList) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_PHASE_2_STAINED_GLASS_BREAK);

        outPacket.encodeInt(stringList.size());
        for (String string : stringList) {
            outPacket.encodeString(string);
        }

        return outPacket;
    }

    public static OutPacket lucidFlyingMode(boolean enable) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_PHASE_2_SET_FLYING_MODE);

        outPacket.encodeByte(enable);

        return outPacket;
    }

    public static OutPacket lucidStatueStateChange(boolean placement, int gauge, boolean enable) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_STATUE_STATE_CHANGE);

        outPacket.encodeInt(placement ? 1 : 0);
        if (placement) {
            outPacket.encodeByte(enable);
        } else {
            outPacket.encodeInt(gauge);
            outPacket.encodeByte(enable);
        }

        return outPacket;
    }

    public static OutPacket lucidWelcomeBarrage(int type, int... args) {
        OutPacket outPacket = new OutPacket(OutHeader.LUCID_PHASE_2_WELCOME_BARRAGE);

        outPacket.encodeInt(type);

        switch (type) {
            case 0:
                outPacket.encodeInt(args[0]); // angle
                outPacket.encodeInt(args[1]); // speed
                break;
            case 1:
            case 2:
                break;
            case 3:
                outPacket.encodeInt(args[0]); // angleRate
                outPacket.encodeInt(args[1]); // speed
                outPacket.encodeInt(args[2]); // interval
                outPacket.encodeInt(args[3]); // shotCount
                break;
            case 4:
            case 5:
                outPacket.encodeInt(args[0]); // angle
                outPacket.encodeInt(args[1]); // angleRate
                outPacket.encodeInt(args[2]); // angleDiff
                outPacket.encodeInt(args[3]); // speed
                outPacket.encodeInt(args[4]); // interval
                outPacket.encodeInt(args[5]); // shotCount
                outPacket.encodeInt(args[6]); // bulletAngleRate
                outPacket.encodeInt(args[7]); // bulletSpeedRate
                break;
        }

        return outPacket;
    }
}
