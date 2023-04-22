package net.swordie.ms;

public enum ServerStatus {
    NORMAL(0),
    BUSY(1),
    FULL(2);

    /**
     * Created on 11/3/2017.
     */
    private byte value;

    ServerStatus (int value) {
        this.value = (byte) value;
    }

    public byte getValue() {
        return value;
    }
}
