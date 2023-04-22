package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.life.movement.MovementInfo;
import net.swordie.ms.life.npc.Npc;

/**
 * Created on 2/19/2018.
 */
public class NpcPool {

	public static OutPacket npcEnterField(Npc npc) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_ENTER_FIELD);

		outPacket.encodeInt(npc.getObjectId());
		outPacket.encodeInt(npc.getTemplateId());
		npc.encode(outPacket);

		return outPacket;
	}

	public static OutPacket npcLeaveField(Npc npc) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_LEAVE_FIELD);

		outPacket.encodeInt(npc.getObjectId());

		return outPacket;
	}
        
        public static OutPacket npcChangeController(Npc npc, boolean controller) {
            return npcChangeController(npc, controller, false);
        }
        
	public static OutPacket npcChangeController(Npc npc, boolean controller, boolean remove) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_CHANGE_CONTROLLER);

		outPacket.encodeByte(controller);
		outPacket.encodeInt(npc.getObjectId());
		if (!remove) {
			outPacket.encodeInt(npc.getTemplateId());
			npc.encode(outPacket);
		}

		return outPacket;
	}

	public static OutPacket npcMove(int objectID, byte oneTimeAction, byte chatIdx, int duration, boolean move,
									MovementInfo movementInfo, byte keyPadState) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_MOVE);

		outPacket.encodeInt(objectID);

		outPacket.encodeByte(oneTimeAction);
		outPacket.encodeByte(chatIdx);
		outPacket.encodeInt(duration);

		if (move) {
			outPacket.encode(movementInfo);
		}

		return outPacket;
	}

	public static OutPacket npcSetForceMove(int npcObjId, boolean left, int distance, int speed) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_SET_FORCE_MOVE);

		outPacket.encodeInt(npcObjId);
		outPacket.encodeInt(left ? -1 : 1); // 1 = right movement,  -1 = left movement
		outPacket.encodeInt(distance);
		outPacket.encodeInt(speed); //  10 < speed < 300

		return outPacket;
	}

	public static OutPacket npcSetForceFlip(int npcObjId, boolean left) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_SET_FORCE_FLIP);

		outPacket.encodeInt(npcObjId);
		outPacket.encodeInt(left ? -1 : 1); // 1 = right movement,  -1 = left movement

		return outPacket;
	}

	public static OutPacket npcViewOrHide(int npcObjId, boolean showTemplate, boolean showNameTag) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_VIEW_OR_HIDE);

		outPacket.encodeInt(npcObjId);
		outPacket.encodeByte(showTemplate);
		outPacket.encodeByte(showNameTag);

		return outPacket;
	}

	public static OutPacket npcSetSpecialAction(int npcObjId, String effectName, int duration) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_SET_SPECIAL_ACTION);

		outPacket.encodeInt(npcObjId);
		outPacket.encodeString(effectName);
		outPacket.encodeInt(duration); // if  duration > 0  -> Repeat Effect lasts: duration
		outPacket.encodeByte(true); // showLocal

		return outPacket;
	}

	public static OutPacket npcResetSpecialAction(int npcObjId) {
		OutPacket outPacket = new OutPacket(OutHeader.NPC_RESET_SPECIAL_ACTION);

		outPacket.encodeInt(npcObjId);

		return outPacket;
	}
}
