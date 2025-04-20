package net.swordie.ms.client.character.info;

import net.swordie.ms.util.Position;

/**
 * Created on 1/11/2018.
 */
public class HitInfo {
    public int hpDamage;
    public int templateID;
    public int mobID;
    public int mpDamage;
    public int type = -1;
    public int blockSkillId;
    public int otherUserID;
    public boolean isCrit;
    public int action;
    public boolean isGuard;
    public int hitAction;
    public int specialEffectSkill; // mask: 0x1 if true, 0x2 if custom skillID. Default is 33110000 (jaguar boost)
    public int reflectDamage;
    public int userSkillID;
    public byte attackIdx;
    public short obstacle;
    public byte elemAttr;

    public int damagedTime;
    public int mobIdForMissCheck;
    public boolean isLeft;
    public int reducedDamage;
    public byte reflect;
    public byte guard;
    public boolean powerGuard;
    public int reflectMobID;
    public Position hitPos;
    public Position userHitPos;
    public byte stance;
    public int stanceSkillID;
    public int cancelSkillID;
    public int reductionSkillID;

    @Override
    public String toString() {
        return "HitInfo {" +
                "hpDamage = " + hpDamage +
                ", templateID = " + templateID +
                ", mobID = " + mobID +
                ", attackIdx = " + attackIdx +
                ", mpDamage = " + mpDamage +
                ", type = " + type +
                ", blockSkillId = " + blockSkillId +
                ", otherUserID = " + otherUserID +
                ", isCrit = " + isCrit +
                ", action = " + action +
                ", isGuard = " + isGuard +
                ", hitAction = " + hitAction +
                ", specialEffectSkill = " + specialEffectSkill +
                ", reflectDamage = " + reflectDamage +
                ", userSkillID = " + userSkillID +
                '}';
    }
}
