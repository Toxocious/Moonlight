package net.swordie.ms.loaders.containerclasses;

/**
 * Created on 1/11/2018.
 */
public class SkillStringInfo {
    private String name, desc, h;

    public SkillStringInfo(String name, String desc, String h) {
        this.name = name;
        this.desc = desc;
        this.h = h;
    }

    public SkillStringInfo() {
        name = "";
        desc = "";
        h = "";
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc == null ? "" : desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getH() {
        return h == null ? "" : h;
    }

    public void setH(String h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Desc: " + desc + ", H: " + h;
    }
}
