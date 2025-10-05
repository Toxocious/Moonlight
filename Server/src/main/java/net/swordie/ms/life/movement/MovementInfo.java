package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.MovementType;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 8/16/2018.
 */
public class MovementInfo implements Encodable {
    private static final Logger log = Logger.getLogger(MovementInfo.class);

    private int encodedGatherDuration;
    private Position oldPos;
    private Position oldVPos;
    private List<Movement> movements = new ArrayList<>();
    private byte keyPadState;

    public MovementInfo(Position oldPos, Position oldVPos) {
        this.oldPos = oldPos;
        this.oldVPos = oldVPos;
    }

    public MovementInfo(InPacket inPacket) {
        decode(inPacket);
    }

    public void applyTo(Char chr) {
        for (Movement m : getMovements()) {
            m.applyTo(chr);
        }
    }

    public void applyTo(Life life) {
        for (Movement m : getMovements()) {
            m.applyTo(life);
        }
    }

    public void decode(InPacket inPacket) {
        encodedGatherDuration = inPacket.decodeInt();
        oldPos = inPacket.decodePosition();
        oldVPos = inPacket.decodePosition();
        movements = parseMovement(inPacket);
        keyPadState = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(encodedGatherDuration);
        outPacket.encodePosition(oldPos);
        outPacket.encodePosition(oldVPos);
        outPacket.encodeByte(movements.size());
        for (Movement m : movements) {
            m.encode(outPacket);
        }
        outPacket.encodeByte(keyPadState);
    }

    private static List<Movement> parseMovement(InPacket inPacket) {
        // Taken from mushy when my IDA wasn't able to show this properly
        // Made by Maxcloud
        List<Movement> res = new ArrayList<>();
        byte size = inPacket.decodeByte();
        for (int i = 0; i < size; i++) {
            byte type = inPacket.decodeByte();
            //  System.err.println("move type " + type);
            MovementType movementType = MovementType.getByVal(type);
            if (movementType == null) {
                log.warn(String.format("Unknown movement type %s", type));
                break;
            }


            switch (movementType) {

                case MOVEMENT_UNK0, MOVEMENT_UNK8, MOVEMENT_UNK15, MOVEMENT_UNK17, MOVEMENT_UNK19,
                     MOVEMENT_UNK71, MOVEMENT_UNK72, MOVEMENT_UNK73, MOVEMENT_UNK74, MOVEMENT_UNK75,
                     MOVEMENT_UNK76, MOVEMENT_UNK94 -> res.add(new MovementNormal(inPacket, type));
                case MOVEMENT_UNK1, MOVEMENT_UNK2, MOVEMENT_UNK18, MOVEMENT_UNK21, MOVEMENT_UNK22,
                     MOVEMENT_UNK24, MOVEMENT_UNK60, MOVEMENT_UNK63, MOVEMENT_UNK64, MOVEMENT_UNK66,
                     MOVEMENT_UNK67, MOVEMENT_UNK68, MOVEMENT_UNK69, MOVEMENT_UNK99 ->
                        res.add(new MovementJump(inPacket, type));
                case MOVEMENT_UNK3, MOVEMENT_UNK4, MOVEMENT_UNK5, MOVEMENT_UNK6, MOVEMENT_UNK7,
                     MOVEMENT_UNK9, MOVEMENT_UNK10, MOVEMENT_UNK11, MOVEMENT_UNK13, MOVEMENT_UNK26,
                     MOVEMENT_UNK27, MOVEMENT_UNK52, MOVEMENT_UNK53, MOVEMENT_UNK54, MOVEMENT_UNK65,
                     MOVEMENT_UNK83, MOVEMENT_UNK84, MOVEMENT_UNK85, MOVEMENT_UNK87, MOVEMENT_UNK89,
                     MOVEMENT_TELEPORT -> res.add(new MovementTeleport(inPacket, type));
                case MOVEMENT_STAT_CHANGE -> res.add(new MovementStatChange(inPacket, type));
                case MOVEMENT_UNK14, MOVEMENT_UNK16 -> res.add(new MovementStartFallDown(inPacket, type));
                case MOVEMENT_FLYING_BLOCK -> res.add(new MovementFlyingBlock(inPacket, type));
                case MOVEMENT_NEW1 -> res.add(new MovementNew1(inPacket, type));
                case MOVEMENT_UNK30, MOVEMENT_UNK31, MOVEMENT_UNK32, MOVEMENT_UNK33, MOVEMENT_UNK34,
                     MOVEMENT_UNK35, MOVEMENT_UNK36, MOVEMENT_UNK37, MOVEMENT_UNK38, MOVEMENT_UNK39,
                     MOVEMENT_UNK40, MOVEMENT_UNK41, MOVEMENT_UNK42, MOVEMENT_UNK43, MOVEMENT_UNK44,
                     MOVEMENT_UNK45, MOVEMENT_UNK46, MOVEMENT_UNK47, MOVEMENT_UNK48, MOVEMENT_UNK50,
                     MOVEMENT_UNK51, MOVEMENT_UNK55, MOVEMENT_UNK57, MOVEMENT_UNK58, MOVEMENT_UNK77,
                     MOVEMENT_UNK78, MOVEMENT_UNK79, MOVEMENT_UNK81, MOVEMENT_UNK86, MOVEMENT_UNK88,
                     MOVEMENT_UNK90, MOVEMENT_UNK91, MOVEMENT_UNK92, MOVEMENT_UNK93, MOVEMENT_UNK95,
                     MOVEMENT_UNK96, MOVEMENT_UNK97, MOVEMENT_UNK98 -> res.add(new MovementAction(inPacket, type));
                case MOVEMENT_UNK49 -> res.add(new MovementOffsetX(inPacket, type));
                case MOVEMENT_UNK56, MOVEMENT_UNK70, MOVEMENT_ANGLE ->
                        res.add(new MovementAngle(inPacket, type)); // probably not a good name
                // caught in default case
                // ?
                case MOVEMENT_UNK59, MOVEMENT_UNK61, MOVEMENT_ACTION -> res.add(new MovementAction(inPacket, type));
                default -> log.warn(String.format("Unhandled move path attribute %s. Packet = %s", type, inPacket));
            }
        }
        return res;
    }

    public byte getKeyPadState() {
        return keyPadState;
    }

    public List<Movement> getMovements() {
        return movements;
    }
}
