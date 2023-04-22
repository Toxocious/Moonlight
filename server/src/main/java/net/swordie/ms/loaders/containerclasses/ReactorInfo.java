package net.swordie.ms.loaders.containerclasses;

import net.swordie.ms.util.Rect;

/**
 * Created on 4/21/2018.
 */
public class ReactorInfo {
    private int id;
    private int link;
    private int level;
    private int resetTime;
    private int overlapHitTime;
    private int actMark;
    private int hitCount;
    private int overlapHit;
    private int quest;
    private String info = "";
    private String name = "";
    private String viewName = "";
    private String action = "";
    private boolean notFatigue;
    private boolean dcMark;
    private boolean removeInFieldSet;
    private boolean activateByTouch;
    private boolean notHittable;
    private Rect rect;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setDcMark(boolean dcMark) {
        this.dcMark = dcMark;
    }

    public boolean isDcMark() {
        return dcMark;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getLink() {
        return link;
    }

    public void setActivateByTouch(boolean activateByTouch) {
        this.activateByTouch = activateByTouch;
    }

    public boolean isActivateByTouch() {
        return activateByTouch;
    }

    public void setRemoveInFieldSet(boolean removeInFieldSet) {
        this.removeInFieldSet = removeInFieldSet;
    }

    public boolean isRemoveInFieldSet() {
        return removeInFieldSet;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNotFatigue(boolean notFatigue) {
        this.notFatigue = notFatigue;
    }

    public boolean isNotFatigue() {
        return notFatigue;
    }

    public void setResetTime(int resetTime) {
        this.resetTime = resetTime;
    }

    public int getResetTime() {
        return resetTime;
    }

    public void setNotHittable(boolean notHittable) {
        this.notHittable = notHittable;
    }

    public boolean isNotHittable() {
        return notHittable;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Rect getRect() {
        return rect;
    }

    public void setActMark(int actMark) {
        this.actMark = actMark;
    }

    public int getActMark() {
        return actMark;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setOverlapHit(int overlapHit) {
        this.overlapHit = overlapHit;
    }

    public int getOverlapHit() {
        return overlapHit;
    }

    public void setOverlapHitTime(int overlapHitTime) {
        this.overlapHitTime = overlapHitTime;
    }

    public int getOverlapHitTime() {
        return overlapHitTime;
    }

    public void setQuest(int quest) {
        this.quest = quest;
    }

    public int getQuest() {
        return quest;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "ReactorInfo{" +
                "id=" + id +
                '}';
    }
}
