package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

import java.util.*;

public enum SendTypeFromClient {
    CameraResult(0),
    CameraStart(1),
    StarPlanetEnterLeave(2),
    StarPlanetUserInfoBTEXPBuff(3),
    SpinOffDungeonRequest(4),
    SpinOffReset(5),
    ItemCollectionFAQ(6),
    ScriptRunRectEvent(7),
    BingsooEventFAQ(8),
    UrusResultUIClose(9),
    MiniGamePinballExit(10),
    MonsterCollectionFAQ(11),
    PlatformerStageExit(12),
    DamageSkinSaveFAQ(13);

    private int val;

    SendTypeFromClient(int val) {
        this.val = val;
    }

    public static SendTypeFromClient getByVal(int val) {
        return Util.findWithPred(Arrays.asList(values()), stfc -> stfc.getVal() == val);
    }

    public int getVal() {
        return val;
    }
}
