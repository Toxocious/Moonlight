package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 10/10/2018.
 */
public enum LegionType {

    Req_ChangeBoard(8),
    ;

    private int val;

    LegionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

}
