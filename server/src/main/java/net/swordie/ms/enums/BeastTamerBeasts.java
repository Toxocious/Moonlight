package net.swordie.ms.enums;

public enum BeastTamerBeasts {
    NONE(0),
    BEAR(1),
    LEOPARD(2),
    BIRD(3),
    CAT(4);

    private final int value;

    BeastTamerBeasts(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
