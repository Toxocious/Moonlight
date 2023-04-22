package net.swordie.ms.life.mob;

import net.swordie.ms.ServerConstants;
import net.swordie.ms.util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public enum MobStat {
    IndieUnk0(0),
    IndieUnk1(1),
    IndieUnk2(2),
    PAD(3), // *
    PDR(4),
    MAD(5),
    MDR(6),
    ACC(7),
    EVA(8),
    Speed(9),
    Stun(10),

    Freeze(11),
    Poison(12),
    Seal(13),
    Darkness(14),
    PowerUp(15),
    MagicUp(16),
    PGuardUp(17),
    MGuardUp(18),

    PImmune(19),
    MImmune(20),
    Web(21),
    HardSkin(22),
    Ambush(23),
    Venom(24),
    Blind(25),
    SealSkill(26),

    Dazzle(27),
    PCounter(28),
    MCounter(29),
    RiseByToss(30),
    BodyPressure(31),
    Weakness(32),
    Showdown(33),
    MagicCrash(34),

    DamagedElemAttr(35),
    Dark(36),
    Mystery(37),
    Unk205_33(36),
    AddDamParty(37),
    HitCriDamR(38),
    Fatality(39),
    Lifting(40),
    DeadlyCharge(41),

    Smite(42),
    AddDamSkill(43),
    Incizing(44),
    DodgeBodyAttack(45),
    DebuffHealing(46),
    FinalDmgReceived(47),
    BodyAttack(48),
    TempMoveAbility(49),

    FixDamRBuff(50),
    SpiritGate(51),
    ElementDarkness(52),
    AreaInstallByHit(53),
    BMageDebuff(54),
    JaguarProvoke(55),
    JaguarBleeding(56),
    DarkLightning(57),
    PinkBeanFlowerPot(58),

    BattlePvPHelenaMark(59),
    PsychicLock(60),
    PsychicLockCoolTime(61),
    PsychicGroundMark(62),

    PowerImmune(63),
    PsychicForce(64),
    MultiPMDR(65),
    ElementResetBySummon(66),

    BahamutLightElemAddDam(67),
    UmbralBrand(68),
    BossPropPlus(69),
    Unk65(70),
    MultiDamSkill(71),
    RWLiftPress(72),
    RWChoppingHammer(73),
    TimeBomb(74),
    Treasure(75),
    AddEffect(76),

    Unknown1(77),
    Unknown2(78),
    Invincible(79),
    Unknown75(80),
    Unknown76(81),
    Curseweaver(82),
    unk83(83),
    Degeneration(84), // v209+?
    TossAndSwallow(85), // HoYoung -> Talisman Evil-Sealing Gourd
    Unknown78(87),
    Unknown79(88),
    Unknown80(89),
    Unknown81(90),
    Unk205_85(91),
    Unk205_86(92),
    Unk208_80(93), // new
    Explosion(94),
    HangOver(95),
    Unknown84(96),
    Unk208_94(97),
    BurnedInfo(98), //
    InvincibleBalog(99),
    ExchangeAttack(100),

    ExtraBuffStat(101), // *
    LinkTeam(102),
    SoulExplosion(103),
    SeperateSoulP(104),
    SeperateSoulC(105),
    Ember(106),
    TrueSight(107),
    Laser(108),
    Unk199_97(109),
    Unk188_97(110),
    Unk199_99(111),
    Unk199_100(112),
    Unk199_101(113),
    Unk199_102(114),
    Unk199_103(115),
    Unk199_104(116),
    Unk205_109(117),
    Unk205_110(118),

    No(119),
    ;

    public static final int LENGTH = 5;
    private int val, pos, bitPos;

    MobStat(int val, int pos) {
        this.val = val;
        this.pos = pos;
    }

    MobStat(int bitPos) {
        this.bitPos = bitPos;
        this.val = 1 << (31 - bitPos % 32);
        this.pos = bitPos / 32;
    }

    public int getPos() {
        return pos;
    }

    public int getVal() {
        return val;
    }

    public boolean isMovementAffectingStat() {
        switch(this) {
            case Speed:
            case Stun:
            case Freeze:
            case RiseByToss:
            case Lifting:
            case Smite:
            case TempMoveAbility:
            case RWLiftPress:
                return true;
            default:
                return false;
        }
    }

    public int getBitPos() {
        return bitPos;
    }

    public static void main(String[] args) {
//        int change = 39;
//        for (OutHeader header : values()) {
//            int val = header.getValue();
//            if (val >= SET_FIELD.getValue()) {
//                val += change;
//            }
//            System.out.printf("%s(%d),%n", header, val);
//        }
        File file = new File(ServerConstants.DIR + "\\src\\main\\java\\net\\swordie\\ms\\life\\mob\\MobStat.java");
        int change = 1;
        MobStat checkOp = null;
        try(Scanner s = new Scanner(file)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.contains(",") && line.contains("(")) {
                    String[] split = line.split("[()]");
                    String name = split[0];
                    if (!Util.isNumber(split[1])) {
                        System.out.println(line);
                        continue;
                    }
                    int val = Integer.parseInt(split[1]);
                    MobStat ih = Arrays.stream(MobStat.values()).filter(o -> o.toString().equals(name.trim())).findFirst().orElse(null);
                    if (ih != null) {
                        MobStat start = PAD;
                        if (ih.ordinal() >= start.ordinal() && ih.ordinal() < LinkTeam.ordinal()) {
                            if (line.contains("*")) {
                                checkOp = ih;
                            }
                            val += change;
                            System.out.println(String.format("%s(%d), %s", name, val, start == ih ? "// *" : ""));
                        } else {
                            System.out.println(line);
                        }
                    } else {
                        System.out.println(line);
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (checkOp != null) {
            System.err.println(String.format("Current op (%s) contains a * (= updated). Be sure to check for overlap.", checkOp));
        }
    }
}