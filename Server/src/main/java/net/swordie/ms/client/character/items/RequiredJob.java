package net.swordie.ms.client.character.items;

/**
 * Created on 20/9/2018.
 */
public enum RequiredJob {
    Beginner(-1),
    AnyJob(0),
    Warrior(0x1),
    Magician(0x2),
    Bowman(0x4),
    Thief(0x8),
    Pirate(0x10);

    private int val;

    RequiredJob(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
