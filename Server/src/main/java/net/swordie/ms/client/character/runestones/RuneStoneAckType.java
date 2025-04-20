package net.swordie.ms.client.character.runestones;

/**
 * Created on 19-5-2019.
 *
 * @author Asura
 */
public enum RuneStoneAckType {
    // More
    RuneDelayTime(2),
    // More
    TooStrongForYouToHandle(5),
    CannotUseThatRuneRightNow(7),
    MustBeCloserToDoThat(8),
    ShowArrows(9),
    // More
    ;

    private int val;

    public int getVal() {
        return val;
    }

    RuneStoneAckType(int val) {
        this.val = val;
    }
}