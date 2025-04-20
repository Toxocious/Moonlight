package net.swordie.ms.client.character.skills;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 12/18/2017.
 * @NexonName GW_Core struct
 */
public class Core {

    private int coreId;
    private int leftCount;

    public Core(int coreId, int leftCount) {
        this.coreId = coreId;
        this.leftCount = leftCount;
    }

    public int getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public int getCoreId() {
        return coreId;
    }

    public void setCoreId(int coreId) {
        this.coreId = coreId;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getCoreId());
        outPacket.encodeInt(getLeftCount());
    }
}
