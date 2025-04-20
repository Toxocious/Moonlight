package net.swordie.ms.world.field.bosses.lucid;

/**
 * Created on 20-1-2019.
 *
 * @author Asura
 */
public class FairyDust {
    private int scale, speed, angle, delay;

    public FairyDust(int scale, int speed, int angle, int delay) {
        this.scale = scale;
        this.speed = speed;
        this.angle = angle;
        this.delay = delay;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
