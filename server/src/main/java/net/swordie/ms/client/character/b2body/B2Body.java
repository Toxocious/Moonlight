package net.swordie.ms.client.character.b2body;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.util.Position;

/**
 * Created on 11-1-2019.
 *
 * @author Asura
 */
public class B2Body {

    private Char chr;
    private int bodyId;
    private byte type;
    private Position position;
    private short nRadius, fRadius, scale;
    private int skillId;
    private int slv;
    private int duration;
    private int maxSpeedX;
    private int maxSpeedY;
    private byte unk1;
    private short unk2, unk3;

    public B2Body(Char chr, int bodyId, int skillId, int slv, int maxSpeedX, int maxSpeedY) {
        this.chr = chr;
        this.bodyId = bodyId;
        this.skillId = skillId;
        this.slv = slv;
        this.maxSpeedX = maxSpeedX;
        this.maxSpeedY = maxSpeedY;
    }

    public B2Body(Char chr, int bodyId, byte type, Position position, short nRadius, short fRadius, short scale, int skillId, int slv, int duration) {
        this.chr = chr;
        this.bodyId = bodyId;
        this.type = type;
        this.position = position;
        this.nRadius = nRadius;
        this.fRadius = fRadius;
        this.scale = scale;
        this.skillId = skillId;
        this.slv = slv;
        this.duration = duration;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getBodyId() {
        return bodyId;
    }

    public void setBodyId(int bodyId) {
        this.bodyId = bodyId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public short getnRadius() {
        return nRadius;
    }

    public void setnRadius(short nRadius) {
        this.nRadius = nRadius;
    }

    public short getfRadius() {
        return fRadius;
    }

    public void setfRadius(short fRadius) {
        this.fRadius = fRadius;
    }

    public short getScale() {
        return scale;
    }

    public void setScale(short scale) {
        this.scale = scale;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getSlv() {
        return slv;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxSpeedX() {
        return maxSpeedX;
    }

    public void setMaxSpeedX(int maxSpeedX) {
        this.maxSpeedX = maxSpeedX;
    }

    public int getMaxSpeedY() {
        return maxSpeedY;
    }

    public void setMaxSpeedY(int maxSpeedY) {
        this.maxSpeedY = maxSpeedY;
    }
}
