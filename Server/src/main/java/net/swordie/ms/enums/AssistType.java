package net.swordie.ms.enums;

/**
 * Created on 1/6/2018.
 */
public enum AssistType {
    None(0),
    Attack(1),
    Heal(2),
    AttackEx(3),
    AttackEx2(4),
    Summon(5),
    AttackManual(6),
    AttackCounter(7),
    CreateArea(8),
    Bodyguard(9),
    Jaguar(10),
    AttackJaguar(11),
    CreateB2BodyRequests(12),
    CreateForceAtom(13),
    TeleportToMobs(14),
    MultiSkills(15),
    CreateShootObj(16),
    SequenceAttack(17),
    ExplosionAttack(18),
    GroupAttack(19),
    WalkAttack(20), // Cub Cavalry
    ;
    private byte val;

    AssistType(int val) {
        this.val = (byte) val;
    }

    public byte getVal() {
        return val;
    }
}
