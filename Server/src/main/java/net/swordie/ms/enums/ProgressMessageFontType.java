package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 17-2-2019.
 *
 * @author Asura
 */
public enum ProgressMessageFontType {
    Normal(1),
    Bold(2),
    Normal2(3),
    Squished(4),
    ;

    private int val;

    ProgressMessageFontType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ProgressMessageFontType getByVal(int val) {
        return Arrays.stream(values()).filter(pmft -> pmft.getVal() == val).findAny().orElse(null);
    }
}
