package net.swordie.ms.connection.packet;

import net.swordie.ms.util.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/19/2018.
 */
public class MobSkillAttackInfo {
    public byte actionAndDirMask;
    public byte action;
    public long targetInfo;
    public short skillID;
    public List<Position> multiTargetForBalls = new ArrayList<>();
    public List<Short> randTimeForAreaAttacks = new ArrayList<>();
    public byte idk1;
    public int[] ints;
    public short slv;
    public int option;
}
