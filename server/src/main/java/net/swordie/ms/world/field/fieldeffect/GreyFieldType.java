package net.swordie.ms.world.field.fieldeffect;

import net.swordie.ms.util.Util;

public enum GreyFieldType {
    Field(0),
    Background(1),
    Platform(2),
    BackGroundObject(3),
    NPC(4),
    Player(5),
    Mob(6),
    Portal(7),
    Reactor(8),
    ;

    private byte val;

    GreyFieldType(int val) {this.val = (byte) val;}

    public static GreyFieldType byVal(short val) {
        return Util.findWithPred(values(), v -> v.getVal() == val);
    }

    public byte getVal() {return val;}
}
