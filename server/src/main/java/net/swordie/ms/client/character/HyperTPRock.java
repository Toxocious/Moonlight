package net.swordie.ms.client.character;

/**
 * Created by on 21-7-2018.
 */
public class HyperTPRock {

    public static void removeFieldId(Char chr, int fieldId) {
        for (int i = 0; i < chr.getHyperRockFields().length; i++) {
            if (getFieldIdByPosition(chr, i) != fieldId) {
                continue;
            }
            chr.getHyperRockFields()[i] = 999999999; // Removes the map
            chr.setHyperRockFields(chr.getHyperRockFields());
        }
    }

    public static void addFieldId(Char chr, int fieldId) {
        chr.getHyperRockFields()[getFirstEmptyFieldPositon(chr)] = fieldId;
        chr.setHyperRockFields(chr.getHyperRockFields());
    }

    public static int getFieldIdByPosition(Char chr, int position) {
        return chr.getHyperRockFields()[position];
    }

    public static int getPositionByFieldID(Char chr, int fieldId) {
        int position = -1; // Has to be initialised
        for (int i = 0; i < chr.getHyperRockFields().length; i++) {
            if (getFieldIdByPosition(chr, i) != fieldId) {
                continue;
            }
            position = i;
            break; // Ensures it returns the first match, not the last
        }
        return position;
    }

    public static int getFirstEmptyFieldPositon(Char chr) {
        int position = -1; // Has to be initalised
        for (int i = 0; i < chr.getHyperRockFields().length; i++) {
            if (getFieldIdByPosition(chr, i) != 999999999) {
                continue;
            }
            position = i;
            break; // Ensures it returns the first match, not the last
        }
        return position;
    }
}
