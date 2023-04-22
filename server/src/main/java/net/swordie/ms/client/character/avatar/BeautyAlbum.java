package net.swordie.ms.client.character.avatar;


import net.swordie.ms.connection.OutPacket;

import javax.persistence.*;


/**
 * Created on 26/9/2020.
 */

@Entity
@Table(name = "beautyalbuminventory")
public class BeautyAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int styleid;
    private int slotid;

    public BeautyAlbum() {
        this.styleid = 0;
    }

    public BeautyAlbum(int slotid)   {
        this.styleid = 0;
        this.slotid = slotid;
    }

    public BeautyAlbum(int styleid, int slotid) {
        this.styleid = styleid;
        this.slotid = slotid;
    }

    public void encode(OutPacket outPacket)  {

        int styleid = getSlotID();
        outPacket.encodeInt(styleid);
        if (styleid / 10000 < 40000)    {
            outPacket.encodeByte(-1);
            outPacket.encodeByte(0);
            outPacket.encodeByte(0);
        }

    }

    public long getID() {   return id;  }

    public void setID(long id) {   this.id = id;  }

    public int getStyleID() {  return styleid;  }

    public void setStyleID(int styleid) {   this.styleid = styleid; }

    public int getSlotID() {   return slotid;   }

    public void setSlotID( int slotid) {   this.slotid = slotid;   }


}