package net.swordie.ms.enums;

public enum HotTimeRewardResult {
    SUCCESS_MAPLE_POINTS(11),
    SUCCESS_GAME_ITEM(12),
    SUCCESS_CASH_ITEM(13),
    SUCCESS_MESO(14),
    SUCCESS_EXPERIENCE(15),
    FAILED_MAPLE_POINTS(20),
    FAILED_GAME_ITEM(21),
    FAILED_CASH_ITEM(22),
    FAILED_MESO(23),
    FAILED_EXPERIENCE(24),
    FAILED_GAME_INVENTORY_FULL(102),
    FAILED_CASH_INVENTORY_FULL(34);

    private int value;

    HotTimeRewardResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
