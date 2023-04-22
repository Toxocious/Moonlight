package net.swordie.ms.world.field.bosses.lucid;

/**
 * Created on 20-1-2019.
 *
 * @author Asura
 */
public enum ButterflyActionType {
    Add(0),
    Move(1),
    Attack(2),
    Remove(3),;

    int val;

    ButterflyActionType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
