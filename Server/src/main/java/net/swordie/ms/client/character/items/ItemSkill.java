package net.swordie.ms.client.character.items;

public class ItemSkill {
    private int skill;
    private int slv;

    public ItemSkill(int skill, int slv) {
        this.skill = skill;
        this.slv = slv;
    }

    public int getSlv() {
        return slv;
    }

    public void setSlv(int slv) {
        this.slv = slv;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
