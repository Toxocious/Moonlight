package net.swordie.ms.enums;

import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2/3/2018.
 *
 * @author Eric
 */
public enum TSIndex {
    EnergyCharged(0),
    DashSpeed(1),
    DashJump(2),
    RideVehicle(3),
    PartyBooster(4),
    GuidedBullet(5),
    Undead(6),
    RideVehicleExpire(7),
    RelicGauge(8), // could be on the wrong pos
    SecondAtomLockOn(9),
    ;
    private final int index;

    TSIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static TSIndex getTSEByIndex(int index) {
        return Arrays.stream(TSIndex.values()).filter(tse -> tse.getIndex() == index).findFirst().orElse(null);
    }

    public static CharacterTemporaryStat getCTSFromTwoStatIndex(int index) {
        switch (index) {
            case 0:
                return CharacterTemporaryStat.EnergyCharged;
            case 1:
                return CharacterTemporaryStat.DashSpeed;
            case 2:
                return CharacterTemporaryStat.DashJump;
            case 3:
                return CharacterTemporaryStat.RideVehicle;
            case 4:
                return CharacterTemporaryStat.PartyBooster;
            case 5:
                return CharacterTemporaryStat.GuidedBullet;
            case 6:
                return CharacterTemporaryStat.Undead;
            case 7:
                return CharacterTemporaryStat.RideVehicleExpire;
            case 8:
                return CharacterTemporaryStat.RelicGauge;
            case 9:
                return CharacterTemporaryStat.SecondAtomLockOn;
            default:
                return null;
        }
    }

    public static TSIndex getTSEFromCTS(CharacterTemporaryStat cts) {
        switch (cts) {
            case EnergyCharged:
                return EnergyCharged;
            case DashJump:
                return DashJump;
            case DashSpeed:
                return DashSpeed;
            case RideVehicle:
                return RideVehicle;
            case PartyBooster:
                return PartyBooster;
            case GuidedBullet:
                return GuidedBullet;
            case Undead:
                return Undead;
            case RideVehicleExpire:
                return RideVehicleExpire;
            case RelicGauge:
                return RelicGauge;
            case SecondAtomLockOn:
                return SecondAtomLockOn;
        }
        return null;
    }

    public static boolean isTwoState(CharacterTemporaryStat cts) {
        return getTSEFromCTS(cts) != null;
    }

    /**
     * Creates a list of all {@link CharacterTemporaryStat CharacterTemporaryStats} that are a TwoState.
     * Is guaranteed to be sorted by their index.
     *
     * @return
     */
    public static List<CharacterTemporaryStat> getAllCTS() {
        List<CharacterTemporaryStat> characterTemporaryStats = new ArrayList<>();
        for (int i = 0; i < TSIndex.values().length; i++) {
            characterTemporaryStats.add(getCTSFromTwoStatIndex(i));
        }
        return characterTemporaryStats;
    }
}
