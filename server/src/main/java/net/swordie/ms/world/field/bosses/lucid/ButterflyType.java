package net.swordie.ms.world.field.bosses.lucid;

/**
 * Created on 20-1-2019.
 *
 * @author Asura
 */
public enum ButterflyType {
    PurpleBig(0),
    PurpleMed(1),
    PurpleSmall(2),
    PinkBig(3),
    PinkMed(4),
    PinkSmall(5),
    BlueBig(6),
    BlueMed(7),
    BlueSmall(8);

    int val;

    ButterflyType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
