package net.swordie.ms.handlers.user;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.Macro;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MacroHandler {
    private static final Logger log = Logger.getLogger(MacroHandler.class);

    @Handler(op = InHeader.USER_MACRO_SYS_DATA_MODIFIED)
    public static void handleUserMacroSysDataModified(Client c, InPacket inPacket) {
        byte size = inPacket.decodeByte();
        List<Macro> macros = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Macro macro = new Macro();
            macro.setName(inPacket.decodeString());
            macro.setMuted(inPacket.decodeByte() != 0);
            for (int j = 0; j < 3; j++) {
                macro.setSkillAtPos(j, inPacket.decodeInt());
            }
            macros.add(macro);
        }
        c.getChr().getMacros().clear();
        c.getChr().getMacros().addAll(macros); // don't set macros directly, as a new row will be made in the DB
    }

    @Handler(op = InHeader.USER_ANTI_MACRO_QUESTION_RESULT)
    public static void handleUserAntiMacroQuestionResult(Client c, InPacket inPacket) {
        short length = inPacket.decodeShort();
        Char chr = c.getChr();

        if (length > 0) {
            String answer = inPacket.decodeString(length);

            if (answer.length() < 6 || !answer.equalsIgnoreCase(chr.getLieDetectorAnswer())) {
                chr.failedLieDetector();
            } else {
                chr.passedLieDetector();
            }
        } else {
            chr.failedLieDetector();
            chr.dispose();
        }
    }

    @Handler(op = InHeader.USER_ANTI_MACRO_REFRESH_REQUEST)
    public static void handleUserAntiMacroRefreshResult(Client c, InPacket inPacket) {
        Char chr = c.getChr();

        // attempting to refresh while there's no LD
        if (chr.getLieDetectorAnswer().length() > 0) {
            chr.sendLieDetector(true);
        }
    }
}
