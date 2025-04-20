package net.swordie.ms.client.character.keys;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/4/2018.
 */
@Entity
@Table(name = "funckeymap")
public class FuncKeyMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fkMapId")
    private List<Keymapping> keymap = new ArrayList<>();

    @Transient
    private static final int MAX_KEYBINDS = 89;

    public FuncKeyMap() {

    }

    public List<Keymapping> getKeymap() {
        return keymap;
    }

    public void setKeymap(List<Keymapping> keymap) {
        this.keymap = keymap;
    }

    public Keymapping getMappingAt(int index) {
        for (Keymapping km : getKeymap()) {
            if (km.getIndex() == index) {
                return km;
            }
        }
        return null;
    }

    public void encode(OutPacket outPacket) {
            for (int i = 0; i < MAX_KEYBINDS; i++) {
                Keymapping tuple = getMappingAt(i);
                if (tuple == null) {
                    outPacket.encodeByte(0);
                    outPacket.encodeInt(0);
                } else {
                    outPacket.encodeByte(tuple.getType());
                    outPacket.encodeInt(tuple.getVal());
                }
            }
    }

    public void putKeyBinding(int index, byte type, int value) {
        Keymapping km = getMappingAt(index);
        if (km == null) {
            km = new Keymapping();
            km.setIndex(index);
            km.setType(type);
            km.setVal(value);
            getKeymap().add(km);
        } else {
            km.setType(type);
            km.setVal(value);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static FuncKeyMap getDefaultMapping(int keySetting) {
        int[] array1, array2, array3;
        if (keySetting == 0) {
            // Basic Key Setting
            array1 = new int[]{70, 66, 1, 60, 61, 62, 63, 56, 57, 59, 48, 49, 50, 51, 45, 44, 47, 46, 41, 40, 43, 37, 39, 38, 33, 35, 34, 31, 29, 26, 27, 24, 25, 22, 23, 20, 21, 18, 19, 16, 17, 13, 8, 7, 6, 5, 65, 4, 64, 3, 2};
            array2 = new int[]{4, 6, 4, 6, 6, 0, 6, 5, 5, 6, 4, 0, 4, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 6, 4, 6, 4, 4};
            array3 = new int[]{47, 106, 46, 101, 102, 0, 103, 53, 54, 100, 29, 0, 7, 0, 51, 50, 31, 6, 22, 16, 9, 3, 26, 20, 0, 11, 17, 2, 52, 14, 15, 41, 19, 39, 1, 27, 30, 0, 4, 8, 5, 0, 0, 0, 23, 18, 105, 13, 104, 12, 10};
        } else {
            // Secondary Key Setting
            array1 = new int[]{65, 4, 64, 3, 2, 60, 61, 62, 63, 56, 57, 59, 48, 49, 50, 51, 45, 44, 47, 46, 41, 40, 43, 37, 39, 38, 33, 35, 34, 31, 29, 26, 27, 24, 25, 22, 23, 20, 21, 18, 19, 16, 17, 13, 8, 7, 6, 5, 1, 66, 70, 36, 52, 71, 73, 79, 82, 83};
            array2 = new int[]{6, 0, 6, 0, 0, 6, 6, 0, 6, 5, 5, 6, 4, 4, 4, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4, 4, 0, 5, 4, 4, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4};
            array3 = new int[]{105, 0, 104, 0, 0, 101, 102, 0, 103, 53, 54, 100, 29, 5, 7, 0, 51, 50, 31, 2, 22, 16, 9, 3, 26, 20, 0, 11, 17, 0, 52, 14, 15, 0, 19, 0, 1, 27, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 46, 106, 47, 8, 4, 12, 13, 23, 10, 18};
        }
        FuncKeyMap fkm = new FuncKeyMap();
        for (int i = 0; i < array1.length; i++) {
            fkm.putKeyBinding(array1[i], (byte) array2[i], array3[i]);
        }
        return fkm;

        // got these from mushy, too lazy to make this myself ;D
        /* mushy shitty funckeymap
        int[] array1 = {1, 2, 3, 64, 4, 65, 5, 6, 7, 8, 13, 17, 16, 19, 18, 21, 20, 23, 22, 25, 24, 27, 26, 29, 31, 34, 35, 33, 38, 39, 37, 43, 40, 41, 46, 47, 44, 45, 51, 50, 49, 48, 59, 57, 56, 63, 62, 61, 60};
        int[] array2 = {4, 4, 4, 6, 4, 6, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 4, 6, 5, 5, 6, 6, 6, 6};
        int[] array3 = {46, 10, 12, 105, 13, 106, 18, 24, 21, 29, 33, 5, 8, 4, 0, 31, 28, 1, 34, 19, 25, 15, 14, 52, 2, 17, 11, 26, 20, 27, 3, 9, 16, 23, 6, 32, 50, 51, 35, 7, 22, 30, 100, 54, 53, 104, 103, 102, 101};
        */
    }
}
