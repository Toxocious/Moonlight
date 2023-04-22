package net.swordie.ms.enums;

import java.util.Arrays;

public enum SoulType {
    Beefy(0),
    Swift(1),
    Clever(2),
    Fortuitous(3),
    Flashy(4),
    Potent(5),
    Radiant(6),
    Hearty(7),
    Magnificent(8),
    ;

    private int val;

    SoulType(int val) {
        this.val = val;
    }

    public int getVal() {
        return this.val;
    }

    public static SoulType getSoulTypeByVal(int val) {
        return Arrays.stream(values()).filter(st -> st.getVal() == val).findFirst().orElse(null);
    }
}
