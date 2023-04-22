package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.MapTaggedObject;

import java.util.Collections;
import java.util.Set;

/**
 * @author Sjonnie
 * Created on 11/27/2018.
 */
public class MapLoadable {

    public static OutPacket setMapTaggedObjectVisisble(MapTaggedObject object) {
        return setMapTaggedObjectVisisble(Collections.singleton(object));
    }

    public static OutPacket setMapTaggedObjectVisisble(Set<MapTaggedObject> objects) {
        OutPacket outPacket = new OutPacket(OutHeader.SET_MAP_TAGGED_OBJECT_VISISBLE);

        outPacket.encodeByte(objects.size());
        for (MapTaggedObject mto : objects) {
            outPacket.encode(mto);
        }

        return outPacket;
    }
}
