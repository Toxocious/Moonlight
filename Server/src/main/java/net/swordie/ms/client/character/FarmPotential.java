package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

/**
 * Created on 12/20/2017.
 */
public class FarmPotential {
    public void encode(OutPacket outPacket) {
        int size = 33;
        outPacket.encodeInt(size);
        for (int i = 0; i < size; i++) {
            outPacket.encodeInt(-1); // dwMonsterID
            outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME)); // potentialExpire
        }
    }
}
