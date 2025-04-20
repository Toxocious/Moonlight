package net.swordie.ms.life;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 6/9/2018.
 */
@Entity
@Table(name = "familiars")
public class Familiar extends Life {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int idk1;
    private int familiarID;
    private String name;
    private boolean idk2;
    private short idk3;
    private int fatigue;
    private long idk4;
    private long idk5;
    @Convert(converter = FileTimeConverter.class)
    private FileTime expiration = FileTime.fromType(FileTime.Type.MAX_TIME);
    private short vitality;
    @Transient
    private int skillID;

    public Familiar() {
        super(0);
    }

    public Familiar(int templateId) {
        super(templateId);
    }

    public Familiar(long id, int familiarID, String name, FileTime expiration, short vitality) {
        super(0);
        this.id = id;
        this.familiarID = familiarID;
        this.name = name;
        this.expiration = expiration;
        this.vitality = vitality;
    }

    public void encode(OutPacket outPacket, Char chr) {
        int mask = 0xFFFF;
        outPacket.encodeInt(mask);

        if (mask == 0xFFFF) {
            outPacket.encodeInt(chr.getId()); // charId
            outPacket.encodeInt(1); // familiarIdx?
            outPacket.encodeInt(getFamiliarID()); // familiarId

            outPacket.encodeString(getName(), 12);

            outPacket.encodeByte(3);
            outPacket.encodeByte(0); // bLocked
            outPacket.encodeByte(5);
            outPacket.encodeByte(6);

            outPacket.encodeByte(0); // bLocked again?
            outPacket.encodeByte(0); // nLevel (1~9)
            outPacket.encodeByte(3); // Affects gauge under Level
            outPacket.encodeByte(0);
            outPacket.encodeByte(1); // Affects gauge under Level
            outPacket.encodeByte(0); // nGrade

            outPacket.encodeShort(50); // exp

            outPacket.encodeByte(0); // nAtt - 1
            outPacket.encodeByte(0); // nDef - 1
            outPacket.encodeShort(10041); // nOption1
            outPacket.encodeShort(10041); // nOption2
              /*
            outPacket.encodeShort(getOptions()[0] | 10041); // nOption1
            outPacket.encodeShort(getOptions()[1] | 10041); // nOption2
              */
            outPacket.encodeByte(5);
            outPacket.encodeShort(0xCAFA);
            outPacket.encodeShort(0xB951);
            outPacket.encodeByte(6);
            outPacket.encodeShort(466); // was 466
        } else {
            if ((mask & 0x1) != 0) {
                outPacket.encodeInt(getFamiliarID());
            }
            if ((mask & 0x2) != 0) {
                outPacket.encodeString(getName(), 12);
            }
            if ((mask & 0x4) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x8) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x10) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x20) != 0) {
                outPacket.encodeShort(0);
            }
            if ((mask & 0x40) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x80) != 0) {
                outPacket.encodeShort(0);
            }
            if ((mask & 0x100) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x200) != 0) {
                outPacket.encodeByte(0);
            }
            if ((mask & 0x400) != 0) {
                outPacket.encodeShort(0);
            }
            if ((mask & 0x800) != 0) {
                outPacket.encodeShort(0);
            }
        }
    }
    public void encodeForRemote(OutPacket outPacket) {
        outPacket.encodeInt(getIdk1()); // not 100% sure about these 3
        outPacket.encodeInt(getFamiliarID());
        outPacket.encodeInt(getVitality());
        outPacket.encodeString(getName());
        outPacket.encodePosition(getPosition());
        outPacket.encodeByte(getMoveAction());
        outPacket.encodeShort(getFh());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdk1() {
        return idk1;
    }

    public void setIdk1(int idk1) {
        this.idk1 = idk1;
    }

    public int getFamiliarID() {
        return familiarID;
    }


    public void setFamiliarID(int familiarID) {
        this.familiarID = familiarID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIdk2() {
        return idk2;
    }

    public void setIdk2(boolean idk2) {
        this.idk2 = idk2;
    }

    public short getIdk3() {
        return idk3;
    }

    public void setIdk3(short idk3) {
        this.idk3 = idk3;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public long getIdk4() {
        return idk4;
    }

    public void setIdk4(long idk4) {
        this.idk4 = idk4;
    }

    public long getIdk5() {
        return idk5;
    }

    public void setIdk5(long idk5) {
        this.idk5 = idk5;
    }

    public FileTime getExpiration() {
        return expiration;
    }

    public void setExpiration(FileTime expiration) {
        this.expiration = expiration;
    }

    public short getVitality() {
        return vitality;
    }

    public void setVitality(short vitality) {
        this.vitality = vitality;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }
}
