package net.swordie.ms.client.guild.bbs;

import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;

import java.util.List;

/**
 * @author Sjonnie
 * Created on 8/12/2018.
 */
public class GuildBBSPacket implements Encodable {

    private GuildBBSType type;
    private BBSRecord record;
    private int totalSize;
    private List<BBSRecord> records;

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(type.getVal());
        switch (type) {
            case Res_LoadPages:
                outPacket.encodeByte(record != null);
                if (record != null) {
                    record.encodeForPagesLoad(outPacket);
                }
                outPacket.encodeInt(totalSize);
                if (totalSize > 0) {
                    outPacket.encodeInt(records.size());
                    for (BBSRecord record : records) {
                        record.encodeForPagesLoad(outPacket);
                    }
                }
                break;
            case Res_LoadRecord:
                record.encodeForRecordLoad(outPacket);
                break;
        }
    }

    public static GuildBBSPacket loadPages(BBSRecord notice, int totalSize, List<BBSRecord> pageRecords) {
        GuildBBSPacket gbp = new GuildBBSPacket();

        gbp.type = GuildBBSType.Res_LoadPages;
        gbp.record = notice;
        gbp.totalSize = totalSize;
        gbp.records = pageRecords;

        return gbp;
    }

    public static GuildBBSPacket loadRecord(BBSRecord record) {
        GuildBBSPacket gbp = new GuildBBSPacket();

        gbp.type = GuildBBSType.Res_LoadRecord;
        gbp.record = record;

        return gbp;
    }
}
