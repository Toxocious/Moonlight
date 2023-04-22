package net.swordie.ms.world.field;

import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;

/**
 * @author Sjonnie
 * Created on 11/27/2018.
 */
public class MapTaggedObject implements Encodable {
    private String tag;
    private boolean visible;
    private int manual;
    private int delay;

    public MapTaggedObject(String tag, boolean visible) {
        this.tag = tag;
        this.visible = visible;
    }

    public MapTaggedObject(String tag, boolean visible, int manual, int delay) {
        this.tag = tag;
        this.visible = visible;
        this.manual = manual;
        this.delay = delay;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getTag());
        outPacket.encodeByte(isVisible());
        outPacket.encodeInt(getManual());
        outPacket.encodeInt(getDelay());
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getManual() {
        return manual;
    }

    public void setManual(int manual) {
        this.manual = manual;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
