package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by Asura on 5-9-2018.
 */
public enum ForcedInputType {
    Stop(0),
    WalkLeft(1),
    WalkRight(2),
    ClimbUp(3),
    ClimbDown(4),
    JumpLeft(5),
    JumpRight(6),
    JumpUp(7),
    JumpDown(8),
    ;

    int val;

    ForcedInputType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ForcedInputType getByVal(int val) {
        return Arrays.stream(values()).filter(fit -> fit.getVal() == val).findAny().orElse(null);
    }
}
