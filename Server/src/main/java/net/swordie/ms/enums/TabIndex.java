package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 2/20/2019.
 */
public enum TabIndex {
    Blank(0),
    Equip(1),
    Use(2),
    Setup(3),
    Etc(4),
    Recipe(5),
    Scroll(6),
    Special(7),
    EighthYear(8),
    Button(9),
    Ticket(10),
    Material(11),
    Maple(12),
    Homecoming(13),
    Core(14),
    Core2(15),

    Korean(20),
    MainWeapon(21),
    SecondaryWeapon(22),
    Armor(23),
    Accessory(24),
    FirstToFifth(25),
    SixthToSeventh(26),
    Eighth(27),
    Ninth(28),
    Korean2(29),

    ;

    private int val;

    TabIndex(int val) {
        this.val = val;
    }

    public static TabIndex getByVal(int val) {
        return Util.findWithPred(values(), v -> v.getVal() == val);
    }

    public int getVal() {
        return val;
    }}
