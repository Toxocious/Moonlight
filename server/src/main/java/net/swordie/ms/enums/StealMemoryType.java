package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created by Asura on 5-5-2018.
 */
public enum StealMemoryType {
    STEAL_SKILL(0),
    NO_TARGETS(1),
    FAILED_UNK_REASON(2),
    REMOVE_STEAL_MEMORY(3),
    REMOVE_MEMORY_ALL_SLOT(4),
    REMOVE_ALL_MEMORY(5),
    ;

    private byte val;

    StealMemoryType(int val) {this.val = (byte) val;}

    public byte getVal() {return val;}

    public static StealMemoryType getByVal(byte val) {
        return Arrays.stream(values()).filter(smt -> smt.getVal() == val).findAny().orElse(null);
    }
}
