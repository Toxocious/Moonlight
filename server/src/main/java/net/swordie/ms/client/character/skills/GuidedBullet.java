package net.swordie.ms.client.character.skills;

import net.swordie.ms.client.character.skills.temp.TemporaryStatBase;
import net.swordie.ms.connection.OutPacket;

/**
 * Created on 2/3/2018.
 */
public class GuidedBullet extends TemporaryStatBase {

    private int mobID;
    private int userID;

    public GuidedBullet() {
        super(false);
        mobID = 0;
        userID = 0;
    }

    public int getMobID() {
        return mobID;
    }

    public void setMobID(int mobID) {
        this.mobID = mobID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public void encode(OutPacket outPacket) {
        super.encode(outPacket);
        outPacket.encodeInt(getMobID());
        outPacket.encodeInt(getUserID());
    }

    @Override
    public void reset() {
        super.reset();
        setMobID(0);
        setUserID(0);
    }
}
