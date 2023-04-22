package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.PortableChair;
import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.character.skills.temp.TemporaryStatManager;
import net.swordie.ms.client.friend.FriendshipRingRecord;
import net.swordie.ms.client.guild.Guild;
import net.swordie.ms.client.jobs.adventurer.pirate.Buccaneer;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.ChairType;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.enums.TSIndex;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.Life;
import net.swordie.ms.life.Summon;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.life.pet.Pet;
import net.swordie.ms.util.container.Triple;
import net.swordie.ms.world.field.Field;

import java.util.List;
import java.util.Map;

import static net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat.EnergyCharged;

/**
 * Created on 3/18/2018.
 */
public class UserPool {

    private static Field field;

    public static Field getField() {
        return field;
    }

    public static OutPacket userEnterField(Char chr) {
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        AvatarLook al = chr.getAvatarData().getAvatarLook();
        TemporaryStatManager tsm = chr.getTemporaryStatManager();

        OutPacket outPacket = new OutPacket(OutHeader.USER_ENTER_FIELD);

        outPacket.encodeInt(chr.getId());

        outPacket.encodeInt(chr.getLevel());
        outPacket.encodeString(chr.getName());
        outPacket.encodeString(""); // parent name, deprecated
        if(chr.getGuild() != null) {
            chr.getGuild().encodeForRemote(outPacket);
        } else {
            Guild.defaultEncodeForRemote(outPacket);
        }
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(cs.getGender());

        outPacket.encodeInt(cs.getPop());
        outPacket.encodeInt(10); // nFarmLevel
        outPacket.encodeInt(0); // nNameTagMark
        outPacket.encodeByte(0); // new 203
        outPacket.encodeInt(0); // new 203, next two are probably related
        tsm.encodeForRemote(outPacket, tsm.getCurrentStats());
        outPacket.encodeShort(chr.getJob());
        outPacket.encodeShort(cs.getSubJob());
        outPacket.encodeInt(chr.getTotalChuc());
        outPacket.encodeInt(chr.getTotalAf());
        al.encode(outPacket);
        if (JobConstants.isZero(chr.getJob())) {
            chr.getAvatarData().getZeroAvatarLook().encode(outPacket);
        }
        outPacket.encodeInt(chr.getDriverID());
        outPacket.encodeInt(chr.getPassengerID()); // dwPassenserID
        // new 176: sub_191E2D0
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        int size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
        }
        // end sub_191E2D0
        outPacket.encodeInt(chr.getChocoCount());
        outPacket.encodeInt(chr.getActiveEffectItemID());
        outPacket.encodeInt(chr.getMonkeyEffectItemID());
        outPacket.encodeInt(chr.getActiveNickItemID());
        boolean bool = false;
        outPacket.encodeByte(bool); // new 188
        if (bool) {
            outPacket.encodeString(""); // new 188
        }
        outPacket.encodeInt(chr.getDamageSkin() == null ? 0 : chr.getDamageSkin().getDamageSkinID());
        outPacket.encodeInt(chr.getPremiumDamageSkin() == null ? 0 : chr.getPremiumDamageSkin().getDamageSkinID());
        outPacket.encodeString(""); // damage skin?
        outPacket.encodeString(""); // premium damage skin?
        outPacket.encodeInt(al.getDemonWingID());
        outPacket.encodeInt(al.getKaiserWingID());
        outPacket.encodeInt(al.getKaiserTailID());
        outPacket.encodeByte(JobConstants.isHoYoung(chr.getJob())); // new v209+? (Hoyoung's ear and tail)
        outPacket.encodeInt(chr.getCompletedSetItemID());
        outPacket.encodeShort(chr.getFieldSeatID());


        // ==== START  Portable Chair Encoding ====
        PortableChair chair = chr.getChair() != null ? chr.getChair() : new PortableChair(chr, 0, ChairType.None);
        outPacket.encodeInt(chair.getItemID());


        outPacket.encodePosition(chr.getPosition());
        outPacket.encodeByte(chr.getMoveAction());
        outPacket.encodeShort(chr.getFoothold());
        outPacket.encodeByte(0); // ? new

        outPacket.encodeByte(chair.getType() != ChairType.None);
        if (chair.getType() != ChairType.None) {
            chair.encode(outPacket);
        }


        for(Pet pet : chr.getPets()) {
            if(pet.getId() == 0) {
                continue;
            }
            outPacket.encodeByte(1);
            outPacket.encodeInt(pet.getIdx());
            pet.encode(outPacket);
        }
        outPacket.encodeByte(0); // indicating that pets are no longer being encoded

        outPacket.encodeByte(0); // if true, encode something. idk what (v4->vfptr[35].Update)(v4, iPacket);
        outPacket.encodeByte(0); // new, having a 0 will 38
        outPacket.encodeByte(chr.getMechanicHue());
        outPacket.encodeInt(chr.getTamingMobLevel());
        outPacket.encodeInt(chr.getTamingMobExp());
        outPacket.encodeInt(chr.getTamingMobFatigue());

        outPacket.encodeByte(0);//unknown
        byte miniRoomType = chr.getMiniRoom() != null ? chr.getMiniRoom().getType() : 0;
        outPacket.encodeByte(miniRoomType);
        if(miniRoomType > 0) {
            chr.getMiniRoom().encode(outPacket);
            chr.encodeChatInfo(outPacket, chr.getMiniRoom().getMsg());
        }
        outPacket.encodeByte(chr.getADBoardRemoteMsg() != null);
        if (chr.getADBoardRemoteMsg() != null) {
            outPacket.encodeString(chr.getADBoardRemoteMsg());
        }
        outPacket.encodeByte(chr.isInCouple());
        if(chr.isInCouple()) {
            chr.getCouple().encodeForRemote(outPacket);
        }
        outPacket.encodeByte(chr.hasFriendshipItem());
        if(chr.hasFriendshipItem()) {
            chr.getFriendshipRingRecord().encode(outPacket);
        }
        outPacket.encodeByte(chr.isMarried());
        if(chr.isMarried()) {
            chr.getMarriageRecord().encodeForRemote(outPacket);
        }
        bool = false;
        outPacket.encodeByte(bool); // v212+
        if (bool) {
            outPacket.encodeInt(0); // size
            // seems like new life/object data (like pet,familiar,android)
        }
        outPacket.encodeInt(chr.getEvanDragonGlide());
        if(JobConstants.isKaiser(chr.getJob())) {
            outPacket.encodeInt(chr.getKaiserMorphRotateHueExtern());
            outPacket.encodeInt(chr.getKaiserMorphPrimiumBlack());
            outPacket.encodeByte(chr.getKaiserMorphRotateHueInnner());
        }
        outPacket.encodeInt(chr.getMakingMeisterSkillEff());
        chr.getFarmUserInfo().encode(outPacket);
        for (int i = 0; i < 5; i++) {
            outPacket.encodeByte(-1); // activeEventNameTag
        }
        outPacket.encodeInt(chr.getCustomizeEffect());
        if(chr.getCustomizeEffect() > 0) {
            outPacket.encodeString(chr.getCustomizeEffectMsg());
        }
        outPacket.encodeByte(chr.getSoulEffect());
        if(tsm.hasStat(CharacterTemporaryStat.RideVehicle)) {
            int vehicleID = tsm.getTSBByTSIndex(TSIndex.RideVehicle).getNOption();
            if(vehicleID == 1932249) { // is_mix_vehicle
                size = 0;
                outPacket.encodeInt(size); // ???
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
            }
        }
        outPacket.encodeByte(0); // flashfire
        /*
         Flashfire (12101025) info
         not really interested in encoding this
         structure is:
         if(bool)
            if(bool)
                slv = int
                notused = int
                x = short
                y = short
         */
        outPacket.encodeByte(0); // StarPlanetRank::Decode
        // CUser::DecodeStarPlanetTrendShopLook not interesting, will break REMOTE_AVATAR_MODIFIED if 1st int is != 0
        outPacket.encodeInt(0);
        // ~CUser::DecodeStarPlanetTrendShopLook
        outPacket.encodeInt(0); // CUser::DecodeTextEquipInfo
        chr.getFreezeHotEventInfo().encode(outPacket);
        outPacket.encodeInt(chr.getEventBestFriendAID()); // CUser::DecodeEventBestFriendInfo
        outPacket.encodeByte(tsm.hasStat(CharacterTemporaryStat.KinesisPsychicEnergeShield));
        outPacket.encodeByte(chr.isBeastFormWingOn());
        outPacket.encodeByte(false);
        outPacket.encodeByte(false);
        outPacket.encodeByte(false);
        //outPacket.encodeInt(chr.getMesoChairCount()); // removed in v212 o_O
        // end kmst
        outPacket.encodeInt(0);

        outPacket.encodeByte(0);
        // outPacket.encodeByte(quest != null); // new 200

    /*    if (quest != null) {
            outPacket.encodeInt(0); // Etc/ZodiacEvent.img/%d
            outPacket.encodeInt(quest.getIntProperty("lv")); // Etc/ZodiacEvent.img/{id}/icon/%d
        }*/
        // next part: only if SecondaryStat::GetValue(v5 + 27522, iPacket_2) > 0
        outPacket.encodeInt(0);
        outPacket.encodeInt(0); // new 188
        outPacket.encodeString("");
        outPacket.encodeInt(0);
        bool = false;
        outPacket.encodeByte(bool);
        if(bool) {
            size = 0;
            outPacket.encodeInt(size);
            for (int i = 0; i < size; i++) {
                outPacket.encodeInt(0);
            }
        }
        int someID = 0;
        outPacket.encodeInt(someID);
        if(someID > 0) {
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeShort(0);
            outPacket.encodeShort(0);
        }
        outPacket.encodeInt(0);
        // start sub_16D99C0
        size = 0;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(0);
        }
        // end sub_16D99C0
        return outPacket;
    }

    public static OutPacket hakuMove(int id, int haku, MovementInfo movementInfo) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_PET_MOVE);

        outPacket.encodeInt(id); // outside

        outPacket.encodeInt(haku);
        outPacket.encode(movementInfo);

        return outPacket;
    }

    public static OutPacket userLeaveField(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_LEAVE_FIELD);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }

    public static OutPacket releasePsychicLock(Char chr, int id) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(id);

        return outPacket;
    }

    public static OutPacket releasePsychicArea(Char chr, int localAreaKey) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_AREA);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(localAreaKey);
        outPacket.encodeInt(localAreaKey);

        return outPacket;
    }

    public static OutPacket createPsychicLock(Char chr, boolean approved, PsychicLock pl) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_LOCK);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(approved);
        if (approved) {
            pl.encode(outPacket);
        }

        return outPacket;
    }

    public static OutPacket releasePsychicLockMob(Char chr, List<Integer> ids) {
        OutPacket outPacket = new OutPacket(OutHeader.RELEASE_PSYCHIC_LOCK_MOB);

        outPacket.encodeInt(chr.getId());
        for(int i : ids) {
            outPacket.encodeByte(1);
            outPacket.encodeInt(i);
        }
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static void addRingInfo(OutPacket outPacket, List<FriendshipRingRecord> rings) {
        outPacket.encodeByte(rings.size());
        for (FriendshipRingRecord ring : rings) {
            outPacket.encodeInt(1);
            outPacket.encodeLong(ring.getFriendshipItemSN());
            outPacket.encodeLong(ring.getFriendshipPairItemSN());
            outPacket.encodeInt(ring.getItemID());
        }
    }

    public static OutPacket teslaTriangle(int chrId, List<Summon> rockNshockList) {
        OutPacket outPacket = new OutPacket(OutHeader.TESLA_TRIANGLE);

        outPacket.encodeInt(chrId);
        for (Summon rockNshockSummon : rockNshockList) {
            outPacket.encodeInt(rockNshockSummon.getObjectId());
        }

        return outPacket;
    }

    public static OutPacket skillOnOffEffect(int chrId, boolean show) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_ON_OFF_EFFECT);

        outPacket.encodeInt(chrId);
        outPacket.encodeByte(show);

        return outPacket;
    }

    public static OutPacket addMesoChairCount(int chrId, long meso) {
        OutPacket outPacket = new OutPacket(OutHeader.ADD_MESO_CHAIR_COUNT);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(0); // unk
        outPacket.encodeLong(meso);
        outPacket.encodeLong(meso);

        return outPacket;
    }
}
