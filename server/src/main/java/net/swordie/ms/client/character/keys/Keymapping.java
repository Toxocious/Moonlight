package net.swordie.ms.client.character.keys;

import javax.persistence.*;

/**
 * Created on 1/4/2018.
 */
@Entity
@Table(name = "keymaps")
public class Keymapping {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "idx")
    private int index;
    private byte type;
    private int val;

    public Keymapping(){}

    public Keymapping(byte type, int val) {
        this.type = type;
        this.val = val;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
