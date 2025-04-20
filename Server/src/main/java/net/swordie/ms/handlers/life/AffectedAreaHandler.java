package net.swordie.ms.handlers.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.life.AffectedArea;
import net.swordie.ms.world.field.Field;
import org.apache.log4j.Logger;

public class AffectedAreaHandler {

    private static final Logger log = Logger.getLogger(AffectedAreaHandler.class);


    @Handler(op = InHeader.USER_AFFECTED_AREA_REMOVE_BY_TIME)
    public static void handleUserAffectedAreaRemoveByTime(Char chr, InPacket inPacket) {
        int skillID = inPacket.decodeInt();

        Field field = chr.getField();
        AffectedArea affectedArea = field.getAffectedAreas().stream().filter(aa -> aa.getOwner() == chr && aa.getSkillID() == skillID).findFirst().orElse(null);
        if (affectedArea != null) {
            field.removeLife(affectedArea);
        }
    }
}
