package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 10/2/2018.
 */
public enum MatrixUpdateRequest {
    Activate(0),
    Deactivate(1),
    Swap(2),

    Enhance(4),
    Disassemble(5),
    DisassembleGroup(6),
    CraftNode(7),

    CraftNodestone(9),
    EnhanceSlot(10),
    ExpandSlot(11),
    EnhanceReset(12),
    ;

    private int val;

    MatrixUpdateRequest(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static MatrixUpdateRequest getByVal(int val) {
        return Util.findWithPred(values(), mur -> mur.getVal() == val);
    }
}
