package net.swordie.ms.scripts;

/**
 * Created on 2/19/2018.
 */
public enum ScriptType {
    None(""),
    Npc("npc"),
    Field("field"),
    FirstEnterField("field"),
    Portal("portal"),
    Reactor("reactor"),
    Item("item"),
    Quest("quest")
    ;

    private String dir;

    ScriptType(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }
}
