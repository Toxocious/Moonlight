package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 3/19/2019.
 */
public enum WorldId {
    Scania(0),
    Bera(1),
    Broa(2),
    Windia(3),
    Khaini(4),
    Bellocan(5),
    Mardia(6),
    Kradia(7),
    Yellonde(8),
    Demethos(9),
    Galicia(10),
    ElNido(11),
    Zenith(12),
    Arcania(13),
    Chaos(14),
    Nova(15),
    Renegades(16),
    Enosis(29),
    Luna(30),
    Elysium(31),
    Red(43),
    Aurora(44),
    Reboot(45),
    Euboot(46),
    Burning(49),
    Arcane(50),
    NovaKms(51),

    Tespia(100),


    ;

    private int val;

    WorldId(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

}