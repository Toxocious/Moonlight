package net.swordie.ms.enums;

public enum HotTimeRewardSendType {
    REWARD(9),
    MAPLE_POINT_RECEIVED(11),
    GAME_ITEM_RECEIVED(12),
    CASH_ITEM_RECEIVED(13),
    MESO_RECEIVED(14),
    EXP_RECEIVED(15);

    private final int value;

    HotTimeRewardSendType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
