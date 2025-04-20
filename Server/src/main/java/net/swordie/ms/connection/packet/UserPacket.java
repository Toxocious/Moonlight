package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.items.Item;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ChatUserType;
import net.swordie.ms.enums.ProgressMessageColourType;
import net.swordie.ms.enums.ProgressMessageFontType;
import net.swordie.ms.handlers.PsychicLock;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;

import java.util.List;

/**
 * Created on 2/3/2018.
 */
public class UserPacket {

    /**
     * Creates a chat packet.
     * @param chr the Char
     * @param type the type of the chat
     * @param msg the message that the Char said
     * @param onlyBalloon what to show (0 = nothing, 1 = just chat, 2 = just balloon, 3 = both)
     * @param idk idk
     * @param worldID world id of the Char
     * @return the created outpacket
     */
    public static OutPacket chat(Char chr, String tag, ChatUserType type, String msg, int onlyBalloon, int idk, int worldID, boolean remote) {
        OutPacket outPacket = new OutPacket(remote ? OutHeader.REMOTE_CHAT : OutHeader.CHAT);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(type.getVal());
        outPacket.encodeString(msg);
        chr.encodeChatInfo(outPacket, msg);
        outPacket.encodeByte(onlyBalloon);
        outPacket.encodeByte(idk);
        outPacket.encodeByte(worldID);
        if (remote) {
            outPacket.encodeString(tag + " " + chr.getName());
        }
        return outPacket;
    }

    public static OutPacket userTossedBySkill(int chrID, int mobOID, int skillID, int slv, int force) {
        OutPacket outPacket = new OutPacket(OutHeader.TOSSED_BY_MOB_SKILL);
        outPacket.encodeInt(chrID);
        outPacket.encodeInt(mobOID);
        outPacket.encodeInt(skillID);
        outPacket.encodeInt(slv);
        outPacket.encodeInt(force);
        outPacket.encodeInt(0); // v212+
        return outPacket;
    }

    public static OutPacket itemLinkedChat(Char chr, ChatUserType type, String msg, int onlyBalloon, int idk,
                                           int worldID, Item item, boolean remote) {
        OutPacket outPacket = new OutPacket(remote ? OutHeader.REMOTE_ITEM_LINKED_CHAT : OutHeader.ITEM_LINKED_CHAT);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(type.getVal());
        outPacket.encodeString(msg);
        chr.encodeChatInfo(outPacket, msg);
        outPacket.encodeByte(onlyBalloon);
        outPacket.encodeByte(idk);
        outPacket.encodeByte(worldID);
        outPacket.encodeByte(item != null);
        if (item != null) {
            outPacket.encode(item);
            outPacket.encodeString(""); // overwrite Item Name
        }
        if (remote) {
            outPacket.encodeString(chr.getName());
        }

        return outPacket;
    }

    public static OutPacket setDamageSkin(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_DAMAGE_SKIN);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getDamageSkin().getDamageSkinID());
        outPacket.encodeString("");
        outPacket.encodeString("");

        return outPacket;
    }

    public static OutPacket setPremiumDamageSkin(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_PREMIUM_DAMAGE_SKIN);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(chr.getPremiumDamageSkin().getDamageSkinID());
        outPacket.encodeString("");
        outPacket.encodeString("");

        return outPacket;
    }

    public static OutPacket showItemSkillSocketUpgradeEffect(int charID, boolean success) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_SKILL_SOCKET_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);

        return outPacket;
    }

    public static OutPacket skillPetState(Char chr, int hakuObjId, byte state) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_PET_STATE);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(hakuObjId);
        outPacket.encodeByte(state);

        return outPacket;
    }

    public static OutPacket showItemSkillOptionUpgradeEffect(int charID, boolean success, boolean boom, int ePos, int uPos) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_SKILL_OPTION_UPGRADE_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);
        outPacket.encodeByte(boom);
        outPacket.encodeInt(ePos); // new
        outPacket.encodeInt(uPos); // new

        return outPacket;
    }

    public static OutPacket SetSoulEffect(int charID, boolean set) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_SOUL_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(set);

        return outPacket;
    }

    public static OutPacket setStigmaEffect(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.STIGMA_EFFECT);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeByte(true);

        return outPacket;
    }

    public static OutPacket setGachaponEffect(Char chr) {
        OutPacket outPacket = new OutPacket(OutHeader.GACHAPON_EFFECT);

        outPacket.encodeInt(chr.getId());

        return outPacket;
    }
    
    public static OutPacket scriptProgressMessage(String string) {
        OutPacket outPacket = new OutPacket(OutHeader.SCRIPT_PROGRESS_MESSAGE);

        outPacket.encodeString(string);

        return outPacket;
    }

    public static OutPacket progressMessageFont(ProgressMessageFontType fontNameType, int fontSize, ProgressMessageColourType fontColorType, int fadeOutDelay, String message) {
        OutPacket outPacket = new OutPacket(OutHeader.PROGRESS_MESSAGE_FONT);

        outPacket.encodeInt(fontNameType.getVal());
        outPacket.encodeInt(fontSize);
        outPacket.encodeInt(fontColorType.getVal());
        outPacket.encodeInt(fadeOutDelay);
        outPacket.encodeByte(0);
        outPacket.encodeString(message);

        return outPacket;
    }
    
    public static OutPacket effect(Effect effect) {
        OutPacket outPacket = new OutPacket(OutHeader.EFFECT);

        effect.encode(outPacket);

        return outPacket;
    }

    public static OutPacket showItemMemorialEffect(int charID, boolean success, int itemID, int ePos, int uPos) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOW_ITEM_MEMORIAL_EFFECT);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(success);
        outPacket.encodeInt(itemID);
        outPacket.encodeInt(ePos);
        outPacket.encodeInt(uPos);

        return outPacket;
    }

    public static OutPacket createPsychicLock(int charID, PsychicLock pl) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_PSYCHIC_LOCK);

        outPacket.encodeInt(charID);
        outPacket.encodeByte(pl.success);
        pl.encode(outPacket);


        return outPacket;
    }

    public static OutPacket followCharacter(int driverChrId, boolean transferField, Position position) {
        OutPacket outPacket = new OutPacket(OutHeader.FOLLOW_CHARACTER);

        outPacket.encodeInt(driverChrId);
        if (driverChrId < 0) {
            outPacket.encodeByte(transferField);
                outPacket.encodePositionInt(position);
            }
        return outPacket;
    }

    public static OutPacket userHitByCounter(int charID, int damage) {
        OutPacket outPacket = new OutPacket(OutHeader.USER_HIT_BY_COUNTER);

        outPacket.encodeInt(charID);
        outPacket.encodeInt(damage);

        return outPacket;
    }

    public static OutPacket shootObjectExplodeResult(int chrId, List<Integer> shootObjectIdList) {
        OutPacket outPacket = new OutPacket(OutHeader.SHOOT_OBJECT_EXPLODE_RESULT);

        outPacket.encodeInt(chrId);
        outPacket.encodeInt(shootObjectIdList.size());
        for (int shootObjId : shootObjectIdList) {
            outPacket.encodeInt(shootObjId);
        }

        return outPacket;
    }

    public static OutPacket skillPetCreated(Char chr, int hakuObjId, int skillId) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_PET_TRANSFER_FIELD);

        outPacket.encodeInt(chr.getId());
        outPacket.encodeInt(hakuObjId);
        outPacket.encodeInt(skillId);
        outPacket.encodeByte(true); // unk  from Sniff
        outPacket.encodePosition(chr.getPosition());
        outPacket.encodeByte(false); // unk  from Sniff
        outPacket.encodeShort(chr.getFoothold()); // unk  from Sniff chr.getField().findFootHoldBelow(chr.getPosition()).getId()

        return outPacket;
    }


    public static OutPacket skillPetState(int hakuObjId, boolean state) {
        OutPacket outPacket = new OutPacket(OutHeader.SKILL_PET_STATE);

        outPacket.encodeInt(hakuObjId);
        outPacket.encodeByte(state);

        return outPacket;
    }

    public static OutPacket shapeShiftResult(int charId, boolean bEnable) {
        OutPacket outPacket = new OutPacket(OutHeader.SHAPESHIFT_RESULT);
        outPacket.encodeInt(charId);
        outPacket.encodeByte(bEnable);
        return outPacket;
    }
}
