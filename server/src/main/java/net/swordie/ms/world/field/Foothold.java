package net.swordie.ms.world.field;

import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;

public class Foothold {
    private int id;
    private int layerId;
    private int groupId;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int next;
    private int prev;
    private int force;
    private boolean forbidFallDown;

    public Foothold(int id, int layerId, int groupId, int x1, int y1, int x2, int y2, int next, int prev, int force) {
        this.id = id;
        this.layerId = layerId;
        this.groupId = groupId;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.next = next;
        this.prev = prev;
        this.force = force;
    }

    public Foothold(int id, int layerId, int groupId) {
        this.id = id;
        this.layerId = layerId;
        this.groupId = groupId;
    }

    public int getLayerId() {
        return layerId;
    }

    public void setLayerId(int layerId) {
        this.layerId = layerId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public boolean isForbidFallDown() {
        return forbidFallDown;
    }

    public void setForbidFallDown(boolean forbidFallDown) {
        this.forbidFallDown = forbidFallDown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Foothold deepCopy() {
        Foothold copy = new Foothold(getId(), getLayerId(), getGroupId(), getX1(), getY1(), getX2(), getY2(), getNext(), getPrev(), getForce());
        copy.setForbidFallDown(isForbidFallDown());
        return copy;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + ", Start = " + new Position(getX1(), getY1()) + ", End = " + new Position(getX2(), getY2());
    }

    /**
     * Gets the y value of this Foothold according to a given x value.
     * @param x the x where the y should correspond to
     * @return the y such that (x,y) lies on this Foothold
     */
    public int getYFromX(int x) {
        // interpolate between the two foothold ends for the y value below pos.x
        int x1 = getX1();
        int x2 = getX2() - x1;
        x = x - x1;
        double perc = (double) x / (double) x2;
        return (int) Math.ceil(getY1() + (perc * (getY2() - getY1())));
    }

    /**
     * Gets the x value of this Foothold according to a given y value.
     * @param y the y where the x should correspond to
     * @return the x such that (x,y) lies on this Foothold
     */
    public int getXFromY(int y) {
        int y1 = getY1();
        int y2 = getY2() - y1;
        y = y - y1;
        double perc = (double) y / (double) y2;
        return (int) Math.ceil(getX1() + (perc * (getX2() - getX1())));
    }

    /**
     * Gets a random Position that is part of this Foothold.
     * @return the randomly generated Position.
     */
    public Position getRandomPosition() {
        int randX = Util.getRandom(getX1(), getX2());
        return new Position(randX, getYFromX(randX));
    }

    /**
     * Checks whether or not this Foothold is a wall (i.e., vertical)
     * @return whether or not this Foothold is a wall
     */
    public boolean isWall() {
        return getX1() == getX2();
    }

    public boolean xIsInFoothold(int x) {
        return getX1() < x && x < getX2();
    }
}
