package net.swordie.ms.world.field.bosses.lucid;

import java.util.Arrays;

/**
 * Created on 20-1-2019.
 *
 * @author Asura
 */
public enum LucidSkillType {
    FlowerTrap1(1),
    FlowerTrap2(2),
    FlowerTrap3(3),
    FairyDust(4),
    LaserRain(5),
    Teleport(6),
    Dragon(7),
    Rush(8),
    WelcomeBarrage(9),
    FairyDust2(10),
    ;

    int val;

    LucidSkillType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static LucidSkillType getSkillByVal(int val) {
        return Arrays.stream(LucidSkillType.values()).filter(lst -> lst.getVal() == val).findFirst().orElse(null);
    }
}
