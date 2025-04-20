package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

/**
 * Created on 12/20/2017.
 */
public class LikePoint {
    private int point;
    private FileTime incTime = FileTime.fromType(FileTime.Type.ZERO_TIME);
    private int season;

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getPoint() + 300);
        outPacket.encodeFT(getIncTime());
        outPacket.encodeInt(getSeason());
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public FileTime getIncTime() {
        return incTime;
    }

    public void setIncTime(FileTime incTime) {
        this.incTime = incTime;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
