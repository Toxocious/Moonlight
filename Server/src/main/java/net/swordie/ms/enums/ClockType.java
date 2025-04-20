package net.swordie.ms.enums;

/**
 * Created on 5/31/2018.
 */
public enum ClockType {
    EventTimer(0),
    HMSClock(1),
    SecondsClock(2),
    FromDefault(3),
    TimerGauge(4),
    ShiftTimer(5),
    StopWatch(6),
    PauseTimer(7),
    TimerInfoEx(8),
    WithoutField(9);

    private byte val;

    ClockType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
