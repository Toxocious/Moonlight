package net.swordie.ms.handlers.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.packet.AndroidPacket;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.Android;
import net.swordie.ms.life.movement.MovementInfo;
import org.apache.log4j.Logger;

public class AndroidHandler {

    private static final Logger log = Logger.getLogger(AndroidHandler.class);


    @Handler(op = InHeader.ANDROID_MOVE)
    public static void handleAndroidMove(Char chr, InPacket inPacket) {
        Android android = chr.getAndroid();
        if (android == null) {
            return;
        }
        MovementInfo mi = new MovementInfo(inPacket);
        mi.applyTo(android);
        chr.getField().broadcastPacket(AndroidPacket.move(android, mi), chr);
    }
}
