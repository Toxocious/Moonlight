package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 */
public class MovementStatChange extends MovementBase {
    public MovementStatChange(InPacket inPacket, byte command) {
        super();
        this.command = command;
        this.stat = inPacket.decodeByte();
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getCommand());
        outPacket.encodeByte(getStat());
    }

    @Override
    public void applyTo(Char chr) {

    }

    @Override
    public void applyTo(Life life) {

    }
}
