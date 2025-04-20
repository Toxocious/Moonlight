package net.swordie.ms.client.character.potential;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.CharPotGrade;
import net.swordie.ms.enums.PotentialResetType;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 5/27/2018.
 */
public class CharacterPotentialMan {

    private Char chr;
    private int skillID;

    public CharacterPotentialMan(Char chr) {
        this.chr = chr;
    }

    private Set<CharacterPotential> getPotentials() {
        return chr.getPotentials();
    }

    public CharacterPotential getPotentialByKey(byte key) {
        return getPotentials().stream().filter(pot -> pot.getKey() == key).findAny().orElse(null);
    }

    /**
     * Adds a potential to the char's potential list. Will override the old one with the same key if one exists.
     * Also sends a packet to the client to indicate the change.
     * @param potential The potential to add
     */
    public void addPotential(CharacterPotential potential) {
        var pot = getPotentialByKey(potential.getKey());
        if (pot != null) {
            pot.setGrade(potential.getGrade());
            pot.setSkillID(potential.getSkillID());
            pot.setSlv(potential.getSlv());
        } else {
            getPotentials().add(potential);
        }
        chr.write(WvsContext.characterPotentialSet(potential));
    }

    /**
     * Removes a potential from the char's potential list by key. Will do nothing if there is no such potential.
     * Also sends a packet to the client to indicate the change.
     * @param key the potential's key to remove
     */
    public void removePotential(byte key) {
        CharacterPotential cp = getPotentialByKey(key);
        if (cp != null) {
            getPotentials().remove(cp);
            chr.write(WvsContext.characterPotentialReset(PotentialResetType.Pos, cp.getKey()));
        }
    }

    public byte getGrade() {
        int max = 0;
        for(CharacterPotential cp : getPotentials()) {
            if (cp.getGrade() > max) {
                max = cp.getGrade();
            }
        }
        return (byte) max;
    }

    /**
     * Returns the current grade of a Char's potential, which is equivalent to the highest potential of the Char.
     * @return the current grade of a Char's potential
     */
    private static byte getGrade(byte line, byte maxGrade) {
        if (line == 1) {
            return maxGrade;
        } else {
            byte grade;
            int prop = Util.getRandom(0, 100);
            if (maxGrade >= CharPotGrade.Unique.ordinal() && prop < 10) {
                grade = 2; // Unique
            } else if (maxGrade >= CharPotGrade.Epic.ordinal() && prop < 40) {
                grade = 1; // Epic
            } else {
                grade = 0; // Rare
            }
            return grade;
        }
    }

    public int getSkillID() {
        return skillID;
    }




    public static List<CharacterPotential> generateRandomPotential(int lines, byte maxGrade, boolean onlySlv, Map<Byte, CharacterPotential> keepLines) {
        Map<Integer, CharacterPotential> potentials = new HashMap<>(lines);
        for (byte i = 1; i <= lines; i++) {
            if (keepLines != null) {
                var keepLine = keepLines.get(i);
                if (keepLine != null) {
                    if (onlySlv) {
                        byte grade = getGrade(i, maxGrade);
                        int skillID = keepLine.getSkillID();
                        int slv = 1 + Util.getRandom(0, 10); // slv are 1-40 inclusive, split up into 4 "tiers" of 10 slv per grade (0-3 inclusive)
                        int maxLevel = SkillData.getSkillInfoById(skillID).getMaxLevel();
                        int rankOffset = Math.min(grade * 10, maxLevel - 10);
                        slv += rankOffset;
                        int oldSLV = keepLine.getSlv();
                        if (slv == oldSLV) {
                            do {
                                slv = 1 + Util.getRandom(0, 10) + rankOffset;
                            } while (slv == oldSLV);
                        }
                        keepLine.setSlv(slv);
                        keepLine.setGrade(grade);
                    }
                    potentials.put(keepLine.getSkillID(), keepLine);
                    continue;
                }
            }
            byte grade = getGrade(i, maxGrade);
            int skillID;
            int slv; // slv are 1-40 inclusive, split up into 4 "tiers" of 10 slv per grade (0-3 inclusive)
            int maxLevel;
            skillID = Util.getRandom(GameConstants.CHAR_POT_BASE_ID, GameConstants.CHAR_POT_END_ID + 1);
            maxLevel = SkillData.getSkillInfoById(skillID).getMaxLevel();
            byte reqGrade = (byte) (4 - maxLevel / 10);
            if (grade < reqGrade || potentials.containsKey(skillID)) {
                do {
                    skillID = Util.getRandom(GameConstants.CHAR_POT_BASE_ID, GameConstants.CHAR_POT_END_ID + 1);
                    maxLevel = SkillData.getSkillInfoById(skillID).getMaxLevel();
                    reqGrade = (byte) (4 - maxLevel / 10);
                } while (grade < reqGrade || potentials.containsKey(skillID));
            }
            slv = 1 + Util.getRandom(0, 10) + Math.min(grade * 10, maxLevel - 10);
            potentials.put(skillID, new CharacterPotential(i, skillID, (byte) slv, grade));
        }
        return potentials.values().stream().sorted(Comparator.comparingInt(CharacterPotential::getGrade).reversed().thenComparingInt(CharacterPotential::getKey)).collect(Collectors.toList());
    }




}
