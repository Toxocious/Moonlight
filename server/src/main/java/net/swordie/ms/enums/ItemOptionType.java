package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created on 16/9/2018.
 */
public enum ItemOptionType {
    AnyEquip(0),
    Weapon(10),
    AnyExceptWeapon(11),
    ArmorExceptGlove(20),
    Accessory(40),
    Hat(51),
    Top(52),
    Bottom(53),
    Glove(54),
    Shoes(55);

    private int val;

    ItemOptionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ItemOptionType getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), stat -> stat.getVal() == val);
    }
}