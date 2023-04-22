package net.swordie.ms.client.friend;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**

 /**
 * Created on 3/18/2018.
 */
public class FriendshipRingRecord {

    private FriendshipRingRecord() {
    }
    private long friendshipItemSN;
    private long friendshipPairItemSN;
    private int itemID;
    private int partnerId;
    private int characterid;
    public int ringcount = 0;
    private String friendShipname;
    private boolean equipped = false;

    public int[] chr_id = new int[60000];
    public int[] partner_id = new int[60000];
    public long[] id_1 = new long[60000];
    public int[] id_2 = new int[60000];
    public String[] partner_name = new String[60000];
    public int[] item_id = new int[60000];


    public FriendshipRingRecord(long id, int id2, int partnerId, int itemid, String partnerName, int chrid) {
        this.friendshipItemSN = id;
        this.friendshipPairItemSN = id2;
        this.partnerId = partnerId;
        this.itemID = itemid;
        this.friendShipname = partnerName;
        this.characterid = chrid; //forloading
        // this.partnerName = partnerName;
    }
    public void encode(OutPacket outPacket) {
        outPacket.encodeInt(getPartnerId());
        outPacket.encodeString(getFriendShipName(), 13);
        outPacket.encodeLong(getFriendshipItemSN());
        outPacket.encodeLong(getFriendshipPairItemSN());
        outPacket.encodeInt(getItemID());
    }

    public void encodeRemote(OutPacket outPacket) {
        outPacket.encodeLong(getFriendshipItemSN());
        outPacket.encodeLong(getFriendshipPairItemSN());
        outPacket.encodeInt(getItemID());
    }

    public long getFriendshipItemSN() {
        return friendshipItemSN;
    }

    public void setFriendshipItemSN(long friendshipItemSN) {
        this.friendshipItemSN = friendshipItemSN;
    }

    public long getFriendshipPairItemSN() {
        return friendshipPairItemSN;
    }

    public void setFriendshipPairItemSN(long friendshipPairItemSN) {
        this.friendshipPairItemSN = friendshipPairItemSN;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPartnerId() { return partnerId; }

    public void setPartnerId(int partnerId) { this.partnerId = partnerId; }

    public int getCharacterID() { return characterid; }

    public void setCharacterID(int characterid) { this.characterid = characterid; }

    public String getFriendShipName() { return friendShipname; }

    public void setFriendShipname(String name) { this.friendShipname = name; }
    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public static FriendshipRingRecord loadFromDb(long ringId) {
        return loadFromDb(ringId, false);
    }

    public static FriendshipRingRecord loadFromDb(long ringId, boolean equipped) {
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();

        FriendshipRingRecord ret;
        Query query = session.createNativeQuery("SELECT * FROM rings WHERE ringid = :ringid");
        query.setParameter("ringid", ringId);
        List<Object[]> results = query.getResultList();
        ret = null;
        for(Object[] r : results) {
            ret = new FriendshipRingRecord((Integer)r[0], (Integer)r[1], (Integer)r[2], (Integer)r[3], (String) r[4], (Integer)r[5]);
            ret.setEquipped(equipped);
        }
        transaction.commit();
        session.close();
        return ret;
    }

    public static void addToDB(int itemid, Char chr, String player, int id, long[] ringId) {
        Session session = DatabaseManager.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("INSERT INTO rings (ringid, itemid, partnerChrId, partnerName, partnerRingId, chrid) VALUES (:ringid,:itemid,:partnerChrId,:partnerame,:partnerRingId,:chrid)");
        query.setParameter("ringid", ringId[0]);
        query.setParameter("itemid", itemid);
        query.setParameter("partnerChrId", chr.getId());
        query.setParameter("partnerame", chr.getName());
        query.setParameter("partnerRingId", ringId[1]);
        query.setParameter("chrid", id);
        query.executeUpdate();

        Query query2 = session.createNativeQuery("INSERT INTO rings (ringid, itemid, partnerChrId, partnerName, partnerRingId, chrid) VALUES (:ringid,:itemid,:partnerChrId,:partnerame,:partnerRingId,:chrid)");
        query2.setParameter("ringid", ringId[1]);
        query2.setParameter("itemid", itemid);
        query2.setParameter("partnerChrId", id);
        query2.setParameter("partnerame", player);
        query2.setParameter("partnerRingId", ringId[0]);
        query2.setParameter("chrid", chr.getId());
        query2.executeUpdate();
        transaction.commit();
        session.close();
    }

    public static class RingComparator implements Comparator<FriendshipRingRecord>, Serializable {

        @Override
        public int compare(FriendshipRingRecord o1, FriendshipRingRecord o2) {
            if (o1.friendshipItemSN < o2.friendshipItemSN) {
                return -1;
            } else if (o1.friendshipItemSN == o2.friendshipItemSN) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}