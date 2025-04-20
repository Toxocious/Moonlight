package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.util.FileTime;

/**
 * Created on 12/19/2017.
 */
public class MiniGameRecord {
    private FileTime fileTime = FileTime.fromType(FileTime.Type.ZERO_TIME);
    private int sOwnerName; // string, yet 4 bytes?
    private int rewardGradeQ;
    private int rewardGradeQHead;
    private int rewardGradeQSize;

    public FileTime getFileTime() {
        return fileTime;
    }

    public void setFileTime(FileTime fileTime) {
        this.fileTime = fileTime;
    }

    public int getsOwnerName() {
        return sOwnerName;
    }

    public void setsOwnerName(int sOwnerName) {
        this.sOwnerName = sOwnerName;
    }

    public int getRewardGradeQ() {
        return rewardGradeQ;
    }

    public void setRewardGradeQ(int rewardGradeQ) {
        this.rewardGradeQ = rewardGradeQ;
    }

    public int getRewardGradeQHead() {
        return rewardGradeQHead;
    }

    public void setRewardGradeQHead(int rewardGradeQHead) {
        this.rewardGradeQHead = rewardGradeQHead;
    }

    public int getRewardGradeQSize() {
        return rewardGradeQSize;
    }

    public void setRewardGradeQSize(int rewardGradeQSize) {
        this.rewardGradeQSize = rewardGradeQSize;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getFileTime().getHighDateTime());
        outPacket.encodeInt(getsOwnerName());
        outPacket.encodeInt(getRewardGradeQ());
        outPacket.encodeInt(getRewardGradeQHead());
        outPacket.encodeInt(getRewardGradeQSize());
    }
}
