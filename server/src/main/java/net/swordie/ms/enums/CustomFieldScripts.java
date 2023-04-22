package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1-12-2018.
 *
 * @author Asura
 */
public enum CustomFieldScripts { // Custom Field Scripts
    easy_zakum_enter(280030200)
    ;
    private int id;

    CustomFieldScripts(int val) {
        this.id = val;
    }

    public int getVal() {
        return id;
    }

    public static CustomFieldScripts getByVal(int id) {
        return Arrays.stream(values()).filter(cfs -> cfs.getVal() == id).findAny().orElse(null);
    }
}
