package net.swordie.ms.client.character.union;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.InlinedIntArrayConverter;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.util.Util;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "unionboards")
public class UnionBoard implements Encodable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int unionPower;
    private long unionDamage;

    @JoinColumn(name = "unionboardid")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UnionMember> activeMembers = new HashSet<>();
    @Convert(converter = InlinedIntArrayConverter.class)
    private List<Integer> synergyGrid = new ArrayList<>();

    public UnionBoard() {
        for (int allocation = 0; allocation < Union.MAX_STATS; allocation++) {
            synergyGrid.add(allocation);
        }
    }

    public Set<UnionMember> getActiveMembers() {
        return activeMembers;
    }

    public void setActiveMembers(Set<UnionMember> activeMembers) {
        this.activeMembers = activeMembers;
    }

    public int getUnionPower() {
        return unionPower;
    }

    public void setUnionPower(int unionPower) {
        this.unionPower = unionPower;
    }

    public long getUnionDamage() {
        return unionDamage;
    }

    public void setUnionDamage(long unionDamage) {
        this.unionDamage = unionDamage;
    }

    public List<Integer> getSynergyGrid() {
        return synergyGrid;
    }

    public void setSynergyGrid(List<Integer> synergyGrid) {
        this.synergyGrid = synergyGrid;
    }

    public void recalcUnionPower() {
        setUnionPower(calculateTotalUnionPower());
    }

    public int calculateTotalUnionPower() {
        return calculateUnionAttackPower() + calculateUnionStarForcePower();
    }

    public int calculateUnionAttackPower() {
        double attackPower = 0.0;
        for (UnionMember um : getActiveMembers()) {
            double multiplier = GameConstants.getUnionMultiplier((short) Math.min(Short.MAX_VALUE, um.getLevel()));
            attackPower += multiplier * Math.pow(um.getLevel(), 3) + 12500D;
        }
        return (int) attackPower;
    }

    public int calculateUnionStarForcePower() {
        int chuc = 0;
        for (UnionMember um : getActiveMembers()) {
            chuc += um.getChuc();
        }
        GameConstants.UnionChucMultiplier mult = GameConstants.getUnionChucMultiplier(chuc);
        double starforcePower = mult.firstMulti * Math.pow(chuc, 3) + mult.secondMulti * Math.pow(chuc, 2)
                + mult.thirdMulti * chuc + mult.fourthMulti;
        return (int) starforcePower;
    }

    @Override
    public void encode(OutPacket outPacket) {
        for (int allocation : getSynergyGrid()) { // size = 8
            outPacket.encodeInt(allocation);
        }
        outPacket.encodeInt(getActiveMembers().size());
        for (UnionMember um : getActiveMembers()) {
            um.encode(outPacket, false);
        }
    }

    /**
     * Sets a position to the given Char in this board. Will create a new member if no matching one exists for the Char.
     * Deletes the member if grid is -1.
     * @param chr the Char to assign
     * @param rotation the rotation of the assignment
     * @param grid the position of the assignment
     */
    public void setCharGridPos(Char chr, int rotation, int grid) {
        UnionMember oldMember = getMemberById(chr.getId());
        if (oldMember == null && grid != -1) {
            oldMember = chr.createUnionMember();
            getActiveMembers().add(oldMember);
        }
        if (grid != -1) {
            oldMember.setGridPos(grid);
            oldMember.setGridRotation(rotation % 360);
        } else if (oldMember != null){
            getActiveMembers().remove(oldMember);
        }
    }

    public UnionMember getMemberById(int charId) {
        return Util.findWithPred(getActiveMembers(), am -> am.getCharId() == charId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void removeMemberByCharId(int id) {
        UnionMember um = getMemberById(id);
        getActiveMembers().remove(um);
    }
}
