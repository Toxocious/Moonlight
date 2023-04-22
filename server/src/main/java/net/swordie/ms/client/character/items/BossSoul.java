package net.swordie.ms.client.character.items;

import net.swordie.ms.enums.SoulType;

public class BossSoul {
    private int skillId;
    private SoulType soulType;

    public BossSoul(int skillId, SoulType soulType) {
        this.skillId = skillId;
        this.soulType = soulType;
    }

    public BossSoul(int skillId, byte soulType) {
        this.skillId = skillId;
        this.soulType = SoulType.getSoulTypeByVal(soulType);
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public SoulType getSoulType() {
        return soulType;
    }

    public void setSoulType(SoulType soulType) {
        this.soulType = soulType;
    }
}
