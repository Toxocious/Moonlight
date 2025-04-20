package net.swordie.ms.enums;

public enum HotTimeRewardType {
    GAME_ITEM(1),
    CASH_ITEM(2),
    MAPLE_POINT(3),
    MESO(4),
    EXPERIENCE(5);

    private int value;

    HotTimeRewardType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
