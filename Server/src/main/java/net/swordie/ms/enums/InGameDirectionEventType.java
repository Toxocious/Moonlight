package net.swordie.ms.enums;

import net.swordie.ms.util.Util;

/**
 * @author Sjonnie
 * Created on 8/31/2018.
 */
public enum InGameDirectionEventType {
    ForcedAction(0),
    Delay(1),
    EffectPlay(2),
    ForcedInput(3),
    PatternInputRequest(4),
    CameraMove(5),// automated send delay
    CameraOnCharacter(6),
    CameraZoom(7),// automated send delay
    UnknownDirection(8), // new v211
    CameraReleaseFromUserPoint(9),
    VansheeMode(10),
    FaceOff(11),
    Monologue(12),
    MonologueScroll(13),
    AvatarLookSet(14),
    RemoveAdditionalEffect(15),
    RemoveAdditionalEffect_2(16),
    ForcedMove(17),
    ForcedFlip(18),
    Unk18(19),
    InputUI(20),
    CloseUI(21),
    Monologue_2(22),
    Unk22(23),
    ;

    private int val;

    InGameDirectionEventType(int val) {
        this.val = val;
    }

    public static InGameDirectionEventType getByVal(byte val) {
        return Util.findWithPred(values(), v -> v.getVal() == val);
    }

    public int getVal() {
        return val;
    }
}
