package net.swordie.ms.world.shop.cashshop;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Sjonnie
 * Created on 7/7/2018.
 */
@Entity
@Table(name = "cs_categories")
public class CashShopCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //idx: base = 1000000; favorite = +1000000; category = +10000; subcategory = +100 subsubcategory = +1 (odin)
    private int idx;
    private String name;
    private int parentIdx;
    @Column(name = "flag")
    @Enumerated(EnumType.ORDINAL)
    private Flag flag = Flag.None;
    private int stock;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getIdx());
        outPacket.encodeString(getName());
        outPacket.encodeInt(getParentIdx());
        outPacket.encodeInt(getFlag() == null ? 0 : getFlag().ordinal());
        outPacket.encodeInt(getStock());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentIdx() {
        return parentIdx;
    }

    public void setParentIdx(int parentIdx) {
        this.parentIdx = parentIdx;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashShopCategory that = (CashShopCategory) o;
        return idx == that.idx &&
                parentIdx == that.parentIdx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, parentIdx);
    }

    @Override
    public String toString() {
        return "CashShopCategory{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", parentIdx=" + parentIdx +
                '}';
    }

    public enum Flag {
        None,
        New,
        Hot
    }



}