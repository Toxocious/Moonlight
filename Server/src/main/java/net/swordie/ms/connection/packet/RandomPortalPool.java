package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * @author Sjonnie
 * Created on 9/12/2018.
 */
public class RandomPortalPool {
    public static OutPacket created(RandomPortal rp) {
        OutPacket outPacket = new OutPacket(OutHeader.RANDOM_PORTAL_CREATED);

        outPacket.encodeByte(rp.getAppearType().ordinal());
        outPacket.encodeInt(rp.getObjectId());
        outPacket.encode(rp);

        return outPacket;
    }

    public static OutPacket removed(int id) {
        OutPacket outPacket = new OutPacket(OutHeader.RANDOM_PORTAL_REMOVED);

        outPacket.encodeByte(0); // ignored
        outPacket.encodeInt(id);
        outPacket.encodeInt(0); // ignored

        return outPacket;
    }
}
