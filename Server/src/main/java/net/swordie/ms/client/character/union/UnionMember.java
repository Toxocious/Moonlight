package net.swordie.ms.client.character.union;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.GameConstants;

import javax.persistence.*;

/**
 * @author a bakery of packets
 */
@Entity
@Table(name = "unionmembers")
public class UnionMember {
    
    public static final int[] boss = {9833101, 9833102, 9833103, 9833104, 9833105, 9833201, 9833202, 9833203, 9833204, 9833205};
    public static final int[] attackerCount = {9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 27, 28, 29, 30, 31, 36, 37, 38, 39, 40};
    public static final int[] reqLev = {500, 100, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000};
    public static final int[] reqCoin = {0, 120, 140, 150, 160, 170, 430, 450, 470, 490, 510, 930, 960, 1000, 1030, 1060, 2200, 2300, 2350, 2400};
    public static final String[] ranks = {
        "Nameless Legion I", "Nameless Legion II", "Nameless Legion III", "Nameless Legion IV", "Nameless Legion V",
        "Renowned Legion I", "Renowned Legion II", "Renowned Legion III", "Renowned Legion IV", "Renowned Legion V",
        "Heroic Legion I", "Heroic Legion II", "Heroic Legion III", "Heroic Legion IV", "Heroic Legion V",
        "Legendary Legion I", "Legendary Legion II", "Legendary Legion III", "Legendary Legion IV", "Legendary Legion V"
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int type; // 1 = normal, 2 = mobile, 3 = lab
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "charid")
    private Char chr;
    private String mobileName = "";
    private int gridPos;
    private int gridRotation;

    public UnionMember() {
        gridPos = -1;
    }

    public UnionMember(int type, Char chr, String mobileName) {
        this();
        this.type = type;
        this.chr = chr;
        this.mobileName = mobileName;
    }

    public static void setCharGridPos(InPacket inPacket, int preset, Account account) {
        int type = inPacket.decodeInt(); // type
        int charID = inPacket.decodeInt(); // charID
        inPacket.decodeInt(); // level
        inPacket.decodeInt(); // job
        inPacket.decodeInt(); // sub job
        int rotation = inPacket.decodeInt(); // Unknown
        int grid = inPacket.decodeInt(); // grid pos
        inPacket.decodeInt(); // chuc
        inPacket.decodeString(); // char name
        if (type == 2) {
            inPacket.decodeString(); // mobile name
        }

        Char chr = account.getCharById(charID);
        if (chr == null) {
            return;
        }
        if (GameConstants.isEligibleForUnion(chr)) {
            Union union = account.getUnion();
            union.setCharPosForPreset(preset, chr, rotation, grid);
        }
    }

    public void encode(OutPacket outPacket) {
        encode(outPacket, false);
    }

    public void encode(OutPacket outPacket, boolean forEligible) {
        outPacket.encodeInt(getType());
        outPacket.encodeInt(getCharId());
        outPacket.encodeInt(getLevel());
        outPacket.encodeInt(getJob());
        outPacket.encodeInt(getSubJob());
        outPacket.encodeInt(getGridRotation());
        outPacket.encodeInt(forEligible ? -1 : getGridPos());
        outPacket.encodeInt(getChuc());
        outPacket.encodeString(getCharacterName());
        if (getType() == 2) {
            outPacket.encodeString(getMobileName());
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCharId() {
        return chr.getId();
    }

    public int getLevel() {
        return chr.getLevel();
    }

    public int getJob() {
        return chr.getJob();
    }

    public int getSubJob() {
        return chr.getSubJob();
    }

    public int getChuc() {
        return chr.getTotalChuc();
    }

    public String getCharacterName() {
        return chr.getName();
    }

    public String getMobileName() {
        return mobileName;
    }

    public int getGridPos() {
        return gridPos;
    }

    public void setGridPos(int gridPos) {
        this.gridPos = gridPos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGridRotation(int gridRotation) {
        this.gridRotation = gridRotation;
    }

    public int getGridRotation() {
        return gridRotation;
    }
}
