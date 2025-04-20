package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class MovementJump extends MovementBase {
    public MovementJump(InPacket inPacket, byte command) {
        super();
        this.command = command;

        short vx = inPacket.decodeShort();
        short vy = inPacket.decodeShort();
        position = new Position(vx, vy);

        if (command == 21 || command == 22) {
            footStart = inPacket.decodeShort();
        }

        if (command == 60) {
            offset = new Position(inPacket.decodeShort(), inPacket.decodeShort());
        }

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodePosition(getVPosition());
        if (getCommand() == 21 || getCommand() == 22) {
            outPacket.encodeShort(getFootStart());
        }
        if (getCommand() == 60) {
            outPacket.encodePosition(getOffset());
        }
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }

    @Override
    public void applyTo(Char chr) {
        chr.setPosition(getPosition());
        chr.setMoveAction(getMoveAction());
    }

    @Override
    public void applyTo(Life life) {
        life.setPosition(getPosition());
        life.setMoveAction(getMoveAction());
    }
}
