package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 17-2-2019.
 *
 * @author Asura
 */
public enum ProgressMessageColourType {
    White(0),
    Black(1),
    Grey(2),
    Grey2(3),
    Yellow(4),
    DarkBlue(5),
    LightBlue(6),
    Red(7),
    LightGreen(8),
    LightPink(9),
    Orange(10),
    Pink(11),
    GreyBlue(12),
    DarkPink(13),
    DarkGreen(14),
    WhitePink(15),
    SkyBlue(16),
    LightGrey(17),
    DarkGrey(18),
    DarkGrey2(19),
    BrightGreen(20),
    LightGrey2(21),
    BluePurple(22),
    Black2(23),
    ;

    private int val;

    ProgressMessageColourType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ProgressMessageColourType getByVal(int val) {
        return Arrays.stream(values()).filter(pmct -> pmct.getVal() == val).findAny().orElse(null);
    }
}
