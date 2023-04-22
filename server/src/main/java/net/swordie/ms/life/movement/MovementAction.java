package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class MovementAction extends MovementBase {
    public MovementAction(InPacket inPacket, byte command) {
        super();
        this.command = command;

        moveAction = inPacket.decodeByte();
        elapse = inPacket.decodeShort();
        forcedStop = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getDuration());
        outPacket.encodeByte(getForcedStop());
    }

    @Override
    public void applyTo(Char chr) {
        chr.setMoveAction(moveAction);
    }

    @Override
    public void applyTo(Life life) {
        life.setMoveAction(moveAction);
    }


}
