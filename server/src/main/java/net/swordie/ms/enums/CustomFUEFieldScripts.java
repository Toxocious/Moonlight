package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1-12-2018.
 *
 * @author Asura
 */
public enum CustomFUEFieldScripts { // Custom First User Enter Field Scripts
    captain_lat_enter(541010100),
    easy_zakum_enter(280030200),
    zakum_summon_normal(280030100),
    chaos_summon_zakum(280030000),
    GiantBoss_Head_First_Enter(863010600),
    Commerci(865000100),
    Commerci1(865000200),
    Commerci2(865000300),
    Commerci3(865000400),
    Commerci4(865000900),
    PapulatusFight(220080001),
    NormalHillaLeaderboard(262030300),
    HardHillaLeaderboard(262031300),
    ;
    private int id;

    CustomFUEFieldScripts(int val) {
        this.id = val;
    }

    public int getVal() {
        return id;
    }

    public static CustomFUEFieldScripts getByVal(int id) {
        return Arrays.stream(values()).filter(cfuefs -> cfuefs.getVal() == id).findAny().orElse(null);
    }
}
