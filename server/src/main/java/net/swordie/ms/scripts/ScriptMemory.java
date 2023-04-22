package net.swordie.ms.scripts;

import net.swordie.ms.life.npc.NpcScriptInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sjonnie
 * Created on 9/9/2018.
 */
public class ScriptMemory {

    private List<NpcScriptInfo> memory = new ArrayList<>();
    private int position;

    public List<NpcScriptInfo> getMemory() {
        return memory;
    }

    private int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the previous script info according to the current position (or null if we don't have it).
     * Automatically decrements the position.
     * @return previous script info
     */
    public NpcScriptInfo getPreviousScriptInfo() {
        if (getPosition() > 1) {
            setPosition(getPosition() - 2);
        } else if (getPosition() == 1){
            setPosition(0);
        }
        NpcScriptInfo nsi = isInMemory() ? getMemory().get(getPosition()) : null;
        if (getPosition() < getMemory().size()) {
            setPosition(getPosition() + 1);
        }
        return nsi;
    }

    /**
     * Returns the previous script info according to the current position (or null if we don't have it).
     * Automatically decrements the position.
     * @return previous script info
     */
    public NpcScriptInfo getNextScriptInfo() {
        NpcScriptInfo nsi = isInMemory() ? getMemory().get(getPosition()) : null;
        if (getPosition() < getMemory().size()) {
            setPosition(getPosition() + 1);
        }
        return nsi;
    }

    /**
     * Checks if the current position corresponds to something in memory.
     * @return if we're in memory
     */
    public boolean isInMemory() {
        return getPosition() < getMemory().size();
    }

    /**
     * Adds a new entry to the memory. Automatically increments the position.
     * @param nsi the info to add
     */
    public void addMemoryInfo(NpcScriptInfo nsi) {
        if (getPosition() < getMemory().size() - 1) {
            // overwrite the old memory
            getMemory().set(getPosition() + 1, nsi.deepCopy());
        } else {
            getMemory().add(nsi.deepCopy());
        }
        setPosition(getPosition() + 1);
    }

    public void clear() {
        setPosition(0);
        getMemory().clear();
    }
}
