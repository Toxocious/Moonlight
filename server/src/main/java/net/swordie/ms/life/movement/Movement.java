package net.swordie.ms.life.movement;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 * These classes + children/parents are basically the same as Mushy, credits to @MaxCloud.
 */
public interface Movement {
    void encode(OutPacket outPacket);
    Position getPosition();

    byte getCommand();

    byte getMoveAction();

    byte getForcedStop();

    byte getStat();

    short getFh();

    short getFootStart();

    short getDuration();

    int getIdk();

    short getUnk();

    Position getVPosition();

    Position getOffset();

    void applyTo(Char chr);

    void applyTo(Life life);
}
