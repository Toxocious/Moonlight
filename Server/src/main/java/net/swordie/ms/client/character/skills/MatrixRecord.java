package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.converters.FileTimeConverter;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.loaders.containerclasses.VCoreInfo;
import net.swordie.ms.util.FileTime;

import javax.persistence.*;

/**
 * @author Sjonnie
 * Created on 10/1/2018.
 */
@Entity
@Table(name = "matrix_records")
public class MatrixRecord implements Encodable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int iconID;
    private int skillID1;
    private int skillID2;
    private int skillID3;
    private int slv;
    private int maxLevel;
    private int row;
    private int exp;
    private long crc;
    @Convert(converter = FileTimeConverter.class)
    private FileTime expireDate = FileTime.fromType(FileTime.Type.MAX_TIME);
    private boolean active;
    private int position;

    public MatrixRecord() {
    }

    public MatrixRecord(int iconID, int skillID1, int skillID2, int skillID3, int slv, int masterSlv) {
        this.iconID = iconID;
        this.skillID1 = skillID1;
        this.skillID2 = skillID2;
        this.skillID3 = skillID3;
        this.slv = slv;
        this.maxLevel = masterSlv;
    }

    public void activate(Char chr, int toPos) {
        this.setPosition(toPos);
        this.setActive(true);
        this.addSkillsToChar(chr, false);
    }

    public static MatrixRecord fromVCoreInfo(VCoreInfo vci) {
        return null;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getSkillID1() {
        return skillID1;
    }

    public void setSkillID1(int skillID1) {
        this.skillID1 = skillID1;
    }

    public int getSkillID2() {
        return skillID2;
    }

    public void setSkillID2(int skillID2) {
        this.skillID2 = skillID2;
    }

    public int getSkillID3() {
        return skillID3;
    }

    public void setSkillID3(int skillID3) {
        this.skillID3 = skillID3;
    }

    public int getSlv() {
        return slv;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public long getCrc() {
        return crc;
    }

    public void setCrc(long crc) {
        this.crc = crc;
    }

    public FileTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(FileTime expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeLong(getCrc());
        outPacket.encodeInt(getIconID());
        outPacket.encodeInt(getSlv());
        outPacket.encodeInt(getExp());
        outPacket.encodeInt(isActive() ? 2 : 1);
        outPacket.encodeInt(getSkillID1());
        outPacket.encodeInt(getSkillID2());
        outPacket.encodeInt(getSkillID3());
        outPacket.encodeInt(getPosition());
        outPacket.encodeFT(getExpireDate());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int[] getSkills() {
        return new int[]{getSkillID1(), getSkillID2(), getSkillID3()};
    }

    public void addSkillsToChar(Char chr, boolean remove) {
        for (int skillID : getSkills()) {
            if (skillID != 0) {
                Skill skill = chr.getSkill(skillID);
                if (skill == null) {
                    skill = SkillData.getSkillDeepCopyById(skillID);
                    skill.setCurrentLevel(0);
                }
                skill.setCurrentLevel(remove ? skill.getCurrentLevel() - getSlv() : skill.getCurrentLevel() + getSlv());
                chr.addSkill(skill);
                chr.write(WvsContext.changeSkillRecordResult(skill));
            }
        }
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
