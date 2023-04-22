package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;

/**
 * Created on 4/2/2018.
 */
@Entity
@Table(name = "macros")
public class Macro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private boolean muted;
    @ElementCollection
    @CollectionTable(name = "macroskills", joinColumns = @JoinColumn(name = "macroID"))
    @OrderColumn(name = "orderCol")
    @Column(name = "skillID")
    private int[] skills = new int[3];

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getSkills() {
        return skills;
    }

    public void setSkills(int[] skills) {
        this.skills = skills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setSkillAtPos(int pos, int skillID) {
        if(pos >= 0 && pos < 3) {
            getSkills()[pos] = skillID;
        }
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeString(getName());
        outPacket.encodeByte(isMuted());
        for(int i : getSkills()) {
            outPacket.encodeInt(i);
        }
    }
}
