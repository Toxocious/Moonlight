package net.swordie.ms.world.gach.result;

public enum GachaponResult {
    SUCCESS(0),
    EXIT(1),
    ERROR(3);
    private final int result;

    private GachaponResult(final int result) {
        this.result = result;
    }

    public int getValue() {
        return result;
    }

    public static GachaponResult getByVal(final int val) {
        for (GachaponResult res : GachaponResult.values()) {
            if (res.getValue() == val) {
                return res;
            }
        }
        return null;
    }
}
