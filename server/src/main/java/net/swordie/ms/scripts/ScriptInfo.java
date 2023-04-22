package net.swordie.ms.scripts;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created on 2/19/2018.
 */
public class ScriptInfo {
    private ScriptType scriptType;
    private Bindings bindings;
    private int parentID;
    private String scriptName;
    private Invocable invocable;
    private final Lock lock = new ReentrantLock();
    private Queue<Object> responses = new LinkedList<>();
    private int objectID;
    private String fileDir;
    private boolean isActive;

    public ScriptInfo(ScriptType scriptType, Bindings bindings, int parentID, String scriptName) {
        this.scriptType = scriptType;
        this.parentID = parentID;
        this.scriptName = scriptName;
        this.bindings = bindings;
    }

    public ScriptInfo(ScriptType scriptType, Bindings bindings, int parentID, String scriptName, Invocable invocable) {
        this.scriptType = scriptType;
        this.bindings = bindings;
        this.parentID = parentID;
        this.scriptName = scriptName;
        this.invocable = invocable;
    }

    public ScriptType getScriptType() {
        return scriptType;
    }

    public void setScriptType(ScriptType scriptType) {
        this.scriptType = scriptType;
    }

    public Bindings getBindings() {
        return bindings;
    }

    public void setBindings(Bindings bindings) {
        this.bindings = bindings;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Invocable getInvocable() {
        return invocable;
    }

    public void setInvocable(Invocable invocable) {
        this.invocable = invocable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void reset() {
        addResponse(null);
        setParentID(0);
        setScriptName("");
        setInvocable(null);
        setActive(false);
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public void addResponse(Object response) {
        if (response == null) {
            responses.clear();
        }
        this.responses.add(response);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public Object awaitResponse() {
        if (responses.size() > 0) {
            return responses.poll();
        }
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                // intended
            }
        }
        return responses.poll();
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileDir() {
        return fileDir;
    }
}
