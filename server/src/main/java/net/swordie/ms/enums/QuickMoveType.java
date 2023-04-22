package net.swordie.ms.enums;

/**
 * @author Sjonnie
 * Created on 8/2/2018.
 */
public enum QuickMoveType {
    Gladiator(0),
    MonsterPark(1),
    DimensionalPortal(2),
    FreeMarket(3),
    Ardentmill(4),
    Boat(5),
    Taxi(6),
    Baret(7),
    Cat(8),
    Dojo(9),
    SpinningGlasses(10),
    BlueKnightOfCygnusWoman(11),
    BlueKnightOfCygnusMan(12),
    BigHeadward(13),
    Nurse(14),
    PinkKnightOfCygnusWoman(15),
    PinkKnightOfCygnusMan(16),
    Waitress(17),
    Bunny(18),
    Girl(19),
    Announcer(20),
    MobileWoman(21),


    ;
    private int val;

    QuickMoveType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
