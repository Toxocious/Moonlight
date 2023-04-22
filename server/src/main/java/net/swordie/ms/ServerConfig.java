package net.swordie.ms;

import net.swordie.ms.enums.WorldId;

/**
 * Created on 2/18/2017.
 */
public class ServerConfig {

    public static final int USER_LIMIT = 20;
    public static final WorldId WORLD_ID = WorldId.Bera;
    public static final String SERVER_NAME = "MS";
    public static String SERVER_MSG = "v214";
    public static final String EVENT_MSG = String.format("#bv214#k       Buffed Channels 6-10\r\n                Online Players: ");
    public static final String RECOMMEND_MSG = "";
    public static final int MAX_CHARACTERS = 30;
    public static final String HEAP_DUMP_DIR = "../heapdumps";
}
