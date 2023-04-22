package net.swordie.ms.world.shop;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 3/27/2018.
 */
public class BuyLimitInfo {

    private int type;
    private Set<FileTime> dates = new HashSet<>();

    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(getType());
        if(getType() == 1 || getType() == 3 || getType() == 4) {
            outPacket.encodeInt(getDates().size());
            for(FileTime ft : getDates()) {
                outPacket.encodeFT(ft);
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Set<FileTime> getDates() {
        return dates;
    }

    public void addDate(FileTime fileTime) {
        getDates().add(fileTime);
    }

    public void removeDate(FileTime fileTime) {
        getDates().remove(fileTime);
    }
}
