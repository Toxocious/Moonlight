package net.swordie.ms.life.movement;

import net.swordie.ms.util.Position;

/**
 * Created on 1/2/2018.
 * These classes + children/parents are basically the same as Mushy, credits to @MaxCloud.
 */
public abstract class MovementBase implements Movement {
    protected byte command;
    protected byte moveAction;
    protected byte forcedStop;
    protected byte stat;

    protected short fh;
    protected short footStart;
    protected short elapse;
    protected short unk;

    protected Position position;
    protected Position vPosition;
    protected Position offset;
    protected int idk;

    @Override
    public byte getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

    @Override
    public byte getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(byte moveAction) {
        this.moveAction = moveAction;
    }

    @Override
    public byte getForcedStop() {
        return forcedStop;
    }

    public void setForcedStop(byte forcedStop) {
        this.forcedStop = forcedStop;
    }

    @Override
    public byte getStat() {
        return stat;
    }

    public void setStat(byte stat) {
        this.stat = stat;
    }

    @Override
    public short getFh() {
        return fh;
    }

    public void setFh(short fh) {
        this.fh = fh;
    }

    @Override
    public short getFootStart() {
        return footStart;
    }

    public void setFhFootStart(short footStart) {
        this.footStart = footStart;
    }

    public short getElapse() {
        return elapse;
    }

    public void setElapse(short elapse) {
        this.elapse = elapse;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getVPosition() {
        return vPosition;
    }

    public void setVPosition(Position vPosition) {
        this.vPosition = vPosition;
    }

    @Override
    public Position getOffset() {
        return offset;
    }

    public void setOffset(Position offset) {
        this.offset = offset;
    }

    @Override
    public short getDuration() {
        return elapse;
    }

    public void setDuration(short duration) {
        this.elapse = duration;
    }

    public int getIdk() {
        return idk;
    }

    public void setIdk(int idk) {
        this.idk = idk;
    }

    public void setUnk(short unk) {
        this.unk = unk;
    }

    public short getUnk() {
        return unk;
    }
}
