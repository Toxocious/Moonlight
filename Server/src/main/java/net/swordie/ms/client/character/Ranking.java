package net.swordie.ms.client.character;

import net.swordie.ms.connection.OutPacket;

/**
 * Created on 11/17/2017.
 */
public class Ranking {
    private int totRank;
    private int totRankGap;
    private int worldRank;
    private int worldRankGap;

    public Ranking(int totRank, int totRankGap, int worldRank, int worldRankGap) {
        this.totRank = totRank;
        this.totRankGap = totRankGap;
        this.worldRank = worldRank;
        this.worldRankGap = worldRankGap;
    }

    public Ranking() {
        this(1,3,3,7);
    }

    public int getTotRank() {
        return totRank;
    }

    public void setTotRank(int totRank) {
        this.totRank = totRank;
    }

    public int getTotRankGap() {
        return totRankGap;
    }

    public void setTotRankGap(int totRankGap) {
        this.totRankGap = totRankGap;
    }

    public int getWorldRank() {
        return worldRank;
    }

    public void setWorldRank(int worldRank) {
        this.worldRank = worldRank;
    }

    public int getWorldRankGap() {
        return worldRankGap;
    }

    public void setWorldRankGap(int worldRankGap) {
        this.worldRankGap = worldRankGap;
    }

    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getTotRank());
        outPacket.encodeInt(getTotRankGap());
        outPacket.encodeInt(getWorldRank());
        outPacket.encodeInt(getWorldRankGap());
    }
}
