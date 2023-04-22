package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.skills.info.ForceAtomInfo;
import net.swordie.ms.client.character.skills.info.SkillInfo;
import net.swordie.ms.client.jobs.cygnus.NightWalker;
import net.swordie.ms.constants.SkillConstants;
import net.swordie.ms.enums.ForceAtomEnum;
import net.swordie.ms.life.mob.Mob;
import net.swordie.ms.loaders.SkillData;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.field.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created on 1-2-2019.
 *
 * @author Asura
 */
public class ForceAtom {
    private boolean byMob;
    private int userOwner;
    private int charId;
        private ForceAtomEnum forceAtomEnum;
    private boolean toMob;
    private List<Integer> targetIdList;
    private int skillId;
    private List<ForceAtomInfo> faiList;
    private Rect rect = new Rect();
    private Rect rect2 = new Rect();
    private int arriveDir;
    private int arriveRange;
    private Position forcedTargetPosition, position;
    private int bulletId;
    private int time;

    public ForceAtom(boolean byMob, int userOwner, int charId, ForceAtomEnum forceAtomEnum,
                     boolean toMob, List<Integer> targetIdList, int skillId, List<ForceAtomInfo> faiList,
                     Rect rect, int arriveDir, int arriveRange, Position forcedTargetPosition, int bulletId,
                     Position position, int time) {
        this.byMob = byMob;
        this.userOwner = userOwner;
        this.charId = charId;
        this.forceAtomEnum = forceAtomEnum;
        this.toMob = toMob;
        this.targetIdList = targetIdList;
        this.skillId = skillId;
        this.faiList = faiList;
        this.rect = rect;
        this.arriveDir = arriveDir;
        this.arriveRange = arriveRange;
        this.forcedTargetPosition = forcedTargetPosition;
        this.position = position;
        this.bulletId = bulletId;
        this.time = time;
    }

    public ForceAtom(boolean byMob, int userOwner, int charId, ForceAtomEnum forceAtomEnum,
                     boolean toMob, int targetId, int skillId, ForceAtomInfo fai,
                     Rect rect, int arriveDir, int arriveRange, Position forcedTargetPosition, int bulletId,
                     Position position, int time) {
        this.byMob = byMob;
        this.userOwner = userOwner;
        this.charId = charId;
        this.forceAtomEnum = forceAtomEnum;
        this.toMob = toMob;
        this.targetIdList = Collections.singletonList(targetId);
        this.skillId = skillId;
        this.faiList = Collections.singletonList(fai);
        this.rect = rect;
        this.arriveDir = arriveDir;
        this.arriveRange = arriveRange;
        this.forcedTargetPosition = forcedTargetPosition;
        this.position = position;
        this.bulletId = bulletId;
        this.time = time;
    }

    public ForceAtom(int charId, ForceAtomEnum forceAtomEnum, List<Integer> targetIdList, int skillId, List<ForceAtomInfo> faiList) {
        this.byMob = false;
        this.toMob = true;
        this.charId = charId;
        this.forceAtomEnum = forceAtomEnum;
        this.targetIdList = targetIdList;
        this.skillId = skillId;
        this.faiList = faiList;
    }

    public ForceAtom(int charId, ForceAtomEnum forceAtomEnum, int targetId, int skillId, ForceAtomInfo fai) {
        this.byMob = false;
        this.toMob = true;
        this.charId = charId;
        this.forceAtomEnum = forceAtomEnum;
        this.targetIdList = Collections.singletonList(targetId);
        this.skillId = skillId;
        this.faiList = Collections.singletonList(fai);
    }

    public ForceAtom(ForceAtom fa) {
        this.byMob = fa.isByMob();
        this.userOwner = fa.getUserOwner();
        this.charId = fa.getCharId();
        this.forceAtomEnum = fa.getForceAtomEnum();
        this.toMob = fa.isToMob();
        this.targetIdList = fa.getTargetIdList();
        this.skillId = fa.getSkillId();
        this.faiList = fa.getFaiList();
        this.rect = fa.getRect();
        this.arriveDir = fa.getArriveDir();
        this.arriveRange = fa.getArriveRange();
        this.forcedTargetPosition = fa.getForcedTargetPosition();
        this.position = fa.getPosition();
        this.bulletId = fa.getBulletId();
        this.time = fa.getTime();
    }

    public boolean isByMob() {
        return byMob;
    }

    public void setByMob(boolean byMob) {
        this.byMob = byMob;
    }

    public int getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(int userOwner) {
        this.userOwner = userOwner;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public ForceAtomEnum getForceAtomEnum() {
        return forceAtomEnum;
    }

    public void setForceAtomEnum(ForceAtomEnum forceAtomEnum) {
        this.forceAtomEnum = forceAtomEnum;
    }

    public boolean isToMob() {
        return toMob;
    }

    public void setToMob(boolean toMob) {
        this.toMob = toMob;
    }

    public List<Integer> getTargetIdList() {
        return targetIdList;
    }

    public void setTargetIdList(List<Integer> targetIdList) {
        this.targetIdList = targetIdList;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public List<ForceAtomInfo> getFaiList() {
        return faiList;
    }

    public void setFaiList(List<ForceAtomInfo> faiList) {
        this.faiList = faiList;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Rect getRect2() {
        return rect2;
    }

    public void setRect2(Rect rect2) {
        this.rect2 = rect2;
    }

    public int getArriveDir() {
        return arriveDir;
    }

    public void setArriveDir(int arriveDir) {
        this.arriveDir = arriveDir;
    }

    public int getArriveRange() {
        return arriveRange;
    }

    public void setArriveRange(int arriveRange) {
        this.arriveRange = arriveRange;
    }

    public Position getForcedTargetPosition() {
        return forcedTargetPosition;
    }

    public void setForcedTargetPosition(Position forcedTargetPosition) {
        this.forcedTargetPosition = forcedTargetPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getBulletId() {
        return bulletId;
    }

    public void setBulletId(int bulletId) {
        this.bulletId = bulletId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public List<Integer> getKeys() {
        return new ArrayList<Integer>() {{
            getFaiList().forEach(fai -> add(fai.getKey()));
        }};
    }

    public ForceAtomInfo getFaiByKey(int faKey) {
        return getFaiList().stream().filter(fai -> fai.getKey() == faKey).findFirst().orElse(null);
    }

    public int getCurRecreationCount(int faKey) {
        return getFaiByKey(faKey).getCurRecreationCount();
    }

    public void setCurRecreationCount(int curRecreationCount) {
        getFaiList().forEach(fai -> fai.setCurRecreationCount(curRecreationCount));
    }

    public void setCurRecreationCountOnFai(int faKey, int curRecreationCount) {
        getFaiByKey(faKey).setCurRecreationCount(curRecreationCount);
    }

    public void incrementCurRecreationCount(int faKey) {
        setCurRecreationCountOnFai(faKey, getCurRecreationCount(faKey) + 1);
    }

    public int getMaxRecreationCount(int faKey) {
        return getFaiByKey(faKey).getMaxRecreationCount();
    }

    public void setMaxRecreationCount(int maxRecreationCount) {
        for (int i = 0; i < getFaiList().size(); i++) {
            ForceAtomInfo fai = getFaiList().get(i);
            fai.setMaxRecreationCount(maxRecreationCount);
        }
    }

    public int getRecreationChance(int faKey) {
        return getFaiByKey(faKey).getRecreationChance();
    }

    public void setRecreationChance(int recreationChance) {
        for (int i = 0; i < getFaiList().size(); i++) {
            ForceAtomInfo fai = getFaiList().get(i);
            fai.setRecreationChance(recreationChance);
        }
    }

    public ForceAtom recreate(int faKey, Char chr, int mobObjId, Position position) {
        Field field = chr.getField();
        SkillInfo si = SkillData.getSkillInfoById(SkillConstants.getSkillIdByAtomSkillId(getSkillId()));
        Rect rectFA = position.getRectAround(new Rect(-500, -500, 500, 500));
        if (si != null && si.getFirstRect() != null) {
            rectFA = position.getRectAround(si.getFirstRect());
        }

        Mob mob;
        if (field.getBossMobsInRect(rectFA).size() > 0) {
            mob = Util.getRandomFromCollection(field.getBossMobsInRect(rectFA));
        } else if (field.getMobsInRect(rectFA).size() > 0) {
            mob = Util.getRandomFromCollection(field.getMobsInRect(rectFA));
        } else {
            mob = null;
        }

        setTargetIdList(Collections.singletonList(mob != null ? mob.getObjectId() : 0));
        setPosition(mob != null ? mob.getPosition() : position);
        setForcedTargetPosition(mob != null ? mob.getPosition() : position);
        setByMob(true);
        setCharId(mobObjId);
        setUserOwner(chr.getId());
        setForceAtomEnum(ForceAtomEnum.getRecreationType(getForceAtomEnum()));


        Random random = new Random();
        int angle = random.nextInt(360);
        int fImpact = random.nextInt(8) + 28;
        int sImpact = random.nextInt(2) + 1;
        int delay = 0;

        switch (getSkillId()) {
            case NightWalker.SHADOW_BAT_ATOM:
            case NightWalker.SHADOW_BAT_FROM_MOB_ATOM:
                fImpact = 1;
                sImpact = 5;
                angle = (int) Util.getAngleOfTwoPositions(position, mob != null ? mob.getPosition() : position);
                setSkillId(NightWalker.SHADOW_BAT_FROM_MOB_ATOM);
                setPosition(position);
        }

        ForceAtomInfo fai = getFaiByKey(faKey);
        fai.setFirstImpact(fImpact);
        fai.setSecondImpact(sImpact);
        fai.setStartDelay(delay);
        fai.setAngle(angle);
        fai.setStartPosition(new Position());

        incrementCurRecreationCount(faKey);
        return this;
    }
}
