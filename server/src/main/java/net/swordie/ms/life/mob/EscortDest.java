package net.swordie.ms.life.mob;


import net.swordie.ms.util.Position;

public class EscortDest {
    private Position destPos;
    private int attr;
    private int mass;
    private int stopDuration;

    public EscortDest(int destPosX, int destPosY, int attr, int mass, int stopDuration) {
        this.destPos = new Position(destPosX, destPosY);
        this.attr = attr;
        this.mass = mass;
        this.stopDuration = stopDuration;
    }

    public Position getDestPos() {
        return destPos;
    }

    public int getAttr() {
        return attr;
    }

    public int getMass() {
        return mass;
    }

    public int getStopDuration() {
        return stopDuration;
    }
}
