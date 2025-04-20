package net.swordie.ms.handlers;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created on 1/10/2018.
 */
public class ClientSocket {

    public static OutPacket migrateCommand(boolean succeed, short port) {
        OutPacket outPacket = new OutPacket(OutHeader.MIGRATE_COMMAND);

        outPacket.encodeByte(succeed); // will disconnect if false
        if (succeed) {
            outPacket.encodeArr(ServerConstants.CHANNEL_IP);
            outPacket.encodeShort(port);
            outPacket.encodeInt(0); // ?
        }

        return outPacket;
    }

    public static OutPacket opcodeEncryption(int blockSize, byte[] buf) {
        OutPacket outPacket = new OutPacket(OutHeader.OPCODE_ENCRYPTION);

        outPacket.encodeInt(blockSize);
        outPacket.encodeInt(buf.length);
        outPacket.encodeArr(buf);

        return outPacket;
    }
}
