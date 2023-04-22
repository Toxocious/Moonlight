package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.enums.BaseStat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ItemSet {
    private int id;
    private String name;
    private short completeCount;
    private String effectLink = "";
    private final Map<Integer, Map<BaseStat, Double>> effects = new HashMap<>();
    private boolean jokerPossible;
    private boolean zeroWeaponJokerPossible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(short completeCount) {
        this.completeCount = completeCount;
    }

    public String getEffectLink() {
        return effectLink;
    }

    public void setEffectLink(String effectLink) {
        this.effectLink = effectLink;
    }

    public Map<Integer, Map<BaseStat, Double>> getEffects() {
        return effects;
    }

    public void addEffect(int setLevel, BaseStat stat, double value) {
            Map<BaseStat, Double> newStat = getEffects().getOrDefault(setLevel, new HashMap<>());
            newStat.put(stat, value);
            getEffects().put(setLevel, newStat);
    }

    public boolean isJokerPossible() {
        return jokerPossible;
    }

    public void setJokerPossible(boolean jokerPossible) {
        this.jokerPossible = jokerPossible;
    }

    public boolean isZeroWeaponJokerPossible() {
        return zeroWeaponJokerPossible;
    }

    public void setZeroWeaponJokerPossible(boolean zeroWeaponJokerPossible) {
        this.zeroWeaponJokerPossible = zeroWeaponJokerPossible;
    }
}
