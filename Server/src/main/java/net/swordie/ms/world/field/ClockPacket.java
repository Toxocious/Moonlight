package net.swordie.ms.world.field;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.ClockType;

/**
 * Created on 5/31/2018.
 */
public class ClockPacket {

    private ClockType clockType;

    private int arg1, arg2, arg3;
    private boolean bool;

    private ClockPacket() {}

    private ClockPacket(ClockType clockType) {
        this.clockType = clockType;
    }

    private ClockPacket(ClockType clockType, int arg1) {
        this.clockType = clockType;
        this.arg1 = arg1;
    }

    private ClockPacket(ClockType clockType, int arg1, int arg2) {
        this.clockType = clockType;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    private ClockPacket(ClockType clockType, int arg1, int arg2, int arg3) {
        this.clockType = clockType;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public static ClockPacket eventTimer(int time) {
        return new ClockPacket(ClockType.EventTimer, time);
    }

    public static ClockPacket hmsClock(byte hour, byte minutes, byte seconds) {
        return new ClockPacket(ClockType.HMSClock, hour, minutes, seconds);
    }

    public static ClockPacket secondsClock(int seconds) {
        return new ClockPacket(ClockType.SecondsClock, seconds);
    }

    public static ClockPacket removeClock() {
        return new ClockPacket(ClockType.SecondsClock, -1);
    }

    public static ClockPacket templateClock(byte template) {
        return new ClockPacket(ClockType.FromDefault, template);
    }

    public static ClockPacket timerGauge(int current, int max) {
        return new ClockPacket(ClockType.TimerGauge, max, current);
    }

    public static ClockPacket shiftTimer(boolean left, int time) {
        ClockPacket cp = new ClockPacket(ClockType.ShiftTimer, time);
        cp.bool = left;
        return cp;
    }

    public static ClockPacket stopWatch(int time) {
        return new ClockPacket(ClockType.StopWatch, time*1000);
    }

    public static ClockPacket pauseTimer(boolean paused, int time) {
        ClockPacket cp = new ClockPacket(ClockType.PauseTimer, time);
        cp.bool = paused;
        return cp;
    }

    public static ClockPacket withoutField(boolean clear, int time) {
        ClockPacket cp = new ClockPacket(ClockType.WithoutField, time);
        cp.bool = clear;
        return cp;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(clockType.getVal());
        switch(clockType) {
            case EventTimer:
                outPacket.encodeInt(arg1);
                break;
            case HMSClock:
                outPacket.encodeByte(arg1);
                outPacket.encodeByte(arg2);
                outPacket.encodeByte(arg3);
                break;
            case SecondsClock:
                outPacket.encodeInt(arg1);
                break;
            case FromDefault:
                outPacket.encodeByte(arg1);
                break;
            case TimerGauge:
                outPacket.encodeInt(arg1);
                outPacket.encodeInt(arg2);
                break;
            case ShiftTimer:
                outPacket.encodeByte(bool);
                outPacket.encodeInt(arg1);
                break;
            case StopWatch:
                outPacket.encodeInt(arg1);
                break;
            case PauseTimer:
                outPacket.encodeByte(bool);
                outPacket.encodeInt(arg1);
                break;
            case TimerInfoEx:
                break;
            case WithoutField:
                outPacket.encodeByte(bool);
                outPacket.encodeInt(arg1);
                break;
        }
    }
}
