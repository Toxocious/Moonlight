package net.swordie.ms.connection.packet;

import net.swordie.ms.enums.DropMotionType;
import net.swordie.ms.life.drop.Drop;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.enums.DropEnterType;
import net.swordie.ms.enums.DropLeaveType;
import net.swordie.ms.enums.ItemGrade;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.FileTime;
import net.swordie.ms.util.Position;

/**
 * Created on 2/21/2018.
 */
public class DropPool {
    private static int i =0;

    public static OutPacket dropEnterField(Drop drop, Position dropPosition, int charID, DropEnterType dropEnterType) {
        return DropPool.dropEnterField(drop, dropEnterType, 100, 100,
                (byte) 2, dropPosition, charID, dropPosition, 0, true, (short) 0, false,
                (byte) 0, 0, false, false);
    }

    public static OutPacket dropEnterField(Drop drop, Position dropPosition, int charID, boolean canBePickedUpByPet) {
        return DropPool.dropEnterField(drop, DropEnterType.Instant, 100, 100,
                (byte) 2, dropPosition, charID, dropPosition, 0, true, (short) 0, false,
                (byte) 0, 0, false, canBePickedUpByPet);
    }

    public static OutPacket dropEnterFieldCollisionPickUp(Drop drop, Position dropPosition, int charID) {
        return DropPool.dropEnterField(drop, DropEnterType.Floating, 100, 100,
                (byte) 2, dropPosition, charID, dropPosition, 0, true, (short) 0, false,
                (byte) 0, 1, false, false);
    }

    public static OutPacket dropEnterField(Drop drop, Position dropPositionFrom, Position dropPositionTo, int charID,
                                           boolean canBePickedUpByPet) {
        return DropPool.dropEnterField(drop, DropEnterType.Floating, 100, 100,
                (byte) 2, dropPositionTo, charID, dropPositionFrom, 0, true, (short) 0, false,
                (byte) 0, 0, false, canBePickedUpByPet);
    }

    public static OutPacket dropEnterField(Drop drop, DropEnterType dropEnterType, int rand, int dropSpeed, byte ownType, Position dropPos, int sourceID,
                                           Position tempPos, int delay, boolean unkBool, short fallingVY,
                                           boolean fadeInEffect, byte makeType, int collisionPickup,
                                           boolean prepareCollisionPickUp, boolean canBePickedUpByPet) {
        OutPacket outPacket = new OutPacket(OutHeader.DROP_ENTER_FIELD);

        outPacket.encodeByte(drop.getDropType().getVal());
        outPacket.encodeByte(dropEnterType.getVal());
        outPacket.encodeInt(drop.getObjectId());

        outPacket.encodeByte(drop.isMoney());
        outPacket.encodeInt(drop.getDropMotionType().ordinal()); // 2 = Horizontal, 4 = Vertical movement
        outPacket.encodeInt(dropSpeed);
        outPacket.encodeInt(rand);
        outPacket.encodeInt(drop.getItem() == null ? drop.getMoney() : drop.getItem().getItemId());
        outPacket.encodeInt(drop.getOwnerID());
        outPacket.encodeByte(ownType); // 3 = high drop
        outPacket.encodePosition(dropPos);
        outPacket.encodeInt(sourceID);
        outPacket.encodeInt(0); // new 199
        outPacket.encodeLong(0); // new 199
        outPacket.encodeInt(0); // new 199
        // sub new 199
        outPacket.encodeLong(0);
        outPacket.encodeInt(0);
        outPacket.encodeLong(0);
        // ~sub new 199
        outPacket.encodeByte(0);
        outPacket.encodeByte(false); // bIsMegaCoin (head of a professor on it)
        byte enterType = dropEnterType.getVal();
        if (enterType != 2) {
            outPacket.encodePosition(tempPos);
            outPacket.encodeInt(delay);
        }
        outPacket.encodeByte(drop.isExplosiveDrop());
        // TODO: Fake money: if ( !v8->bIsMoney || (v34 = v8->nInfo == 0, bFakeMoney = 1, !v34) )
        if(!drop.isMoney()) {
            FileTime expireTime = drop.getExpireTime();
            if(expireTime == null) {
                expireTime = FileTime.fromType(FileTime.Type.MAX_TIME);
            }
            outPacket.encodeFT(expireTime);
        }
        outPacket.encodeByte(drop.canBePickedUpByPet() && canBePickedUpByPet); // former = general case, latter = specific to a single chr
        outPacket.encodeByte(unkBool);
        outPacket.encodeShort(fallingVY);
        outPacket.encodeByte(fadeInEffect);
//        outPacket.encodeByte(makeType); // removed 188?
        outPacket.encodeInt(collisionPickup); // decode4, but is bCollisionPickUp?
        outPacket.encodeByte(ItemGrade.getHiddenGradeByVal(drop.getItemGrade()).getVal());
        outPacket.encodeByte(prepareCollisionPickUp);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket dropLeaveField(int dropID, int charID) {
        return dropLeaveField(DropLeaveType.CharPickup1, charID, dropID, (short) 0, 0, 0);
    }

    public static OutPacket dropExplodeField(int dropID) {
        return dropLeaveField(DropLeaveType.DelayedPickup, 0, dropID, (short) 0, 0, 0);
    }

    public static OutPacket dropLeaveField(DropLeaveType dropLeaveType, int pickupID, int dropID, short delay, int petID, int key) {
        OutPacket outPacket = new OutPacket(OutHeader.DROP_LEAVE_FIELD);

        outPacket.encodeByte(dropLeaveType.getVal());
        outPacket.encodeInt(dropID);

        switch(dropLeaveType) {
            case CharPickup1:
            case CharPickup2:
                outPacket.encodeInt(pickupID);
                break;
            case PetPickup:
                outPacket.encodeInt(pickupID);
                outPacket.encodeInt(petID);
                break;
            case DelayedPickup:
                outPacket.encodeShort(delay);
                break;
            case Absorb:
                outPacket.encodeInt(key);
                break;
        }

        return outPacket;
    }
}
