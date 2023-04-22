package net.swordie.ms.enums;

import java.util.Arrays;

/**
 * Created on 1/25/2018.
 */
public enum EquipmentEnchantType {
    ScrollUpgradeRequest(0),
    HyperUpgradeResult(1),
    TransmissionResult(2),
    ScrollUpgradeDisplay(50),
    ScrollTimerEffective(51),
    HyperUpgradeDisplay(52),
    MiniGameDisplay(53),
    ShowScrollUpgradeResult(100),
    ShowHyperUpgradeResult(101),
    ShowScrollVestigeCompensationResult(102),
    ShowTransmissionResult(103),
    ShowUnknownFailResult(104),
    Unk(105);

    private byte val;

    EquipmentEnchantType(int val) {
        this.val = (byte) val;
    }

    public static EquipmentEnchantType getByVal(byte val) {
        return Arrays.stream(values()).filter(tt -> tt.getVal() == val).findAny().orElse(null);
    }

    public byte getVal() {
        return val;
    }
}
