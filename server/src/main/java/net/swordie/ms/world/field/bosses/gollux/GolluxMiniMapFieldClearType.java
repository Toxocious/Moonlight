package net.swordie.ms.world.field.bosses.gollux;

/**
 * Created on 4-3-2019.
 *
 * @author Asura
 */
public enum GolluxMiniMapFieldClearType {
    Unvisited("0"),
    Attacked("1"),
    Defeated("2"),
    Visited("3"),
    ;

    private String val;

    GolluxMiniMapFieldClearType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
