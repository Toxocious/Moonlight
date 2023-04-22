package net.swordie.ms.client.character;


import net.swordie.ms.client.character.avatar.AvatarLook;
import net.swordie.ms.client.character.quest.Quest;
import net.swordie.ms.client.character.quest.QuestManager;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.QuestConstants;
import net.swordie.ms.enums.ChairType;
import net.swordie.ms.enums.QuestStatus;
import net.swordie.ms.enums.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 26-5-2019.
 *
 * @author Asura
 */
public class PortableChair {
    private Char chr;
    private int itemID;
    private String msg = "";
    private List<String> displayChrs = new ArrayList<>();
    private long meso;
    private int displayedNumber;
    private int groupChairOID;
    private ChairType type;
    private int unk1;
    private int unk2;
    private byte unk3;

    public PortableChair(Char chr, int itemID, ChairType type) {
        this.chr = chr;
        this.itemID = itemID;
        this.type = type;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public int getItemID() {
        return itemID;
    }

    public boolean isTextChair() {
        return ItemConstants.isTextChair(getItemID());
    }

    public boolean isTowerChair() {
        return ItemConstants.isTowerChair(getItemID());
    }

    public boolean isMesoChair() {
        return ItemConstants.isMesoChair(getItemID());
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getDisplayChrs() {
        return displayChrs;
    }

    public void setDisplayChrs(List<String> displayChrs) {
        this.displayChrs = displayChrs;
    }

    public void addDisplayChrs(String name) {
        getDisplayChrs().add(name);
    }

    public long getMeso() {
        return meso;
    }

    public void setMeso(long meso) {
        this.meso = meso;
    }

    public int getDisplayedNumber() {
        return displayedNumber;
    }

    public void setDisplayedNumber(int displayedNumber) {
        this.displayedNumber = displayedNumber;
    }

    public int getGroupChairOID() {
        return groupChairOID;
    }

    public void setGroupChairOID(int groupChairOID) {
        this.groupChairOID = groupChairOID;
    }

    public ChairType getType() {
        return type;
    }

    public void setType(ChairType type) {
        this.type = type;
    }

    public int getUnk1() {
        return unk1;
    }

    public void setUnk1(int unk1) {
        this.unk1 = unk1;
    }

    public int getUnk2() {
        return unk2;
    }

    public void setUnk2(int unk2) {
        this.unk2 = unk2;
    }

    public byte getUnk3() {
        return unk3;
    }

    public void setUnk3(byte unk3) {
        this.unk3 = unk3;
    }

    public void addMeso(long meso) {
        if (meso > 0 && isMesoChair()) {
            setMeso(getMeso() + meso);
            //chr.getField().broadcastPacket(UserPool.addMesoChairCount(chr.getId(), meso));
        }
    }

    /**
     * used by
     * CUserRemote::OnSetActivePortableChair
     * CUserRemote::Init
     * @param outPacket
     */
    public void encode(OutPacket outPacket) {
        switch (getType()) {
            case NormalChair -> {
                outPacket.encodeInt(getUnk1()); // new 176 this[15]
                outPacket.encodeInt(getUnk2()); // randInt this[16]
                outPacket.encodeByte(getUnk3()); // randByte this[17]
            }
            case MesoChair -> outPacket.encodeLong(getMeso()); // this[12]
            case LvChair -> {
                boolean bool;
                outPacket.encodeByte(getDisplayChrs().size() > 0); // legion shit
                if (getDisplayChrs().size() > 0) {
                    // sub_B5ABB0
                    outPacket.encodeInt(chr.getAccount().getTotalLvOfAllChrs());
                    outPacket.encodeInt(getDisplayChrs().size());
                    for (String name : getDisplayChrs()) {
                     //   Database.get().openPooledConnection();
                        try {
                            Char displayChr = Char.getFromDBByName(name);
                            outPacket.encodeInt(displayChr.getLevel());
                            outPacket.encodeInt(displayChr.getJob());
                            outPacket.encodeString(name);

                            bool = true;
                            outPacket.encodeByte(bool);
                            if (bool) {
                                displayChr.getAvatarData().getAvatarLook().encode(outPacket);
                            }
                            bool = false;
                            outPacket.encodeByte(bool);
                            if (bool) {
                                displayChr.getAvatarData().getAvatarLook().encode(outPacket);
                            }
                        } finally {
                       //     Database.get().close();
                        }
                    }
                    outPacket.encodeInt(0);
                }
            }
            case TextChair -> {
                outPacket.encodeString(getMsg());
                getChr().encodeChatInfo(outPacket, getMsg());
            }
            case TowerChair -> {
                QuestManager qm = chr.getQuestManager();
                Quest q = qm.getQuests().getOrDefault(QuestConstants.TOWER_CHAIR, null);
                if (q == null) {
                    q = new Quest(QuestConstants.TOWER_CHAIR, QuestStatus.Started);
                    qm.addQuest(q);
                }
                q.convertQRValueToProperties();
                outPacket.encodeInt(q.getProperties().size());
                for (Map.Entry<String, String> entry : q.getProperties().entrySet()) {
                    int towerChairID = Integer.parseInt(entry.getValue());
                    outPacket.encodeInt(towerChairID);
                }
            }
            case TimeChair -> outPacket.encodeInt(0); // this[21]
            case PopChair -> {
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeString(""); // name
                    outPacket.encodeInt(0); // pop
                }
            }
            case TrickOrTreatChair -> {
                outPacket.encodeInt(0);
                outPacket.encodeInt(0);
            }
            case CelebChair -> outPacket.encodeInt(itemID); // this[21]
            case IdentityChair -> {
                outPacket.encodeByte(0); // this[88]
                outPacket.encodeLong(0); // this[90]
            }
            case PopButtonChair -> outPacket.encodeInt(chr.getStat(Stat.pop)); // not sure. this[33]
            case RollingHouseChair -> {
                outPacket.encodeInt(0);
                // function
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
                //
            }
            case TraitsChair -> outPacket.encodeInt(itemID); // this[21]
            case HashTagChair -> {
                //this[23] questID
                int size = 0; // this[24] "totalQrNum"
                for (int i = 0; i < size; i++) {
                    outPacket.encodeString("");
                }
            }
            case PetChair -> {
                for (int i = 0; i < 3; i++) {
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                }
            }
            case ScaleAvatarChair -> {
                boolean bool = false;
                outPacket.encodeByte(bool);
                if (bool) {
                    outPacket.encodeInt(0); // this[28]
                }
            }
            case MannequinChair -> {
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                    // function
                    outPacket.encodeInt(0);
                    // function
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                    outPacket.encodeByte(0);
                    //
                    //
                }
            }
            case CharLvChair -> outPacket.encodeInt(chr.getLevel()); // this[22]
            case ScoreChair -> outPacket.encodeInt(0); // this[20]
            case ArcaneForceChair -> outPacket.encodeInt(chr.getTotalAf()); // this[20]
            case WasteChair -> outPacket.encodeLong(0); // this[22]
            case _2019RollingHouseChair -> {
                outPacket.encodeInt(0);
                // function
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
                //
                // function
                int unk = 0;
                outPacket.encodeInt(unk);
                if (unk != 0) {
                    new AvatarLook().encode(outPacket);
                    outPacket.encodeInt(0);
                    outPacket.encodeInt(0);
                    boolean zero = false;
                    outPacket.encodeByte(zero);
                    if (zero) {
                        new AvatarLook().encode(outPacket);
                    }
                }
            }
        }
    }

    public void encodeCustomChairInfo(OutPacket outPacket) {
        switch (getType()) {
            case TimeChair, PopChair, CelebChair, IdentityChair, PopButtonChair, RollingHouseChair, TraitsChair,
                    HashTagChair, PetChair, ScaleAvatarChair, MannequinChair, ScoreChair, ArcaneForceChair
                    -> encode(outPacket);
            case MesoChair -> {
                outPacket.encodeInt(0); // up(1) or down(0)?
                outPacket.encodeLong(getMeso());
                outPacket.encodeLong(getMeso());
            }
            case TrickOrTreatChair -> {
                outPacket.encodeInt(0); // type
                // TODO
            }
            case WasteChair -> {
                int result = 0;
                outPacket.encodeInt(result); // result type 8: max, 9: update
                if (result == 9) { // update
                    outPacket.encodeLong(getMeso());
                    outPacket.encodeLong(getMeso());
                }
            }
            case _2019RollingHouseChair -> {
                outPacket.encodeInt(0);
                // function
                int size = 0;
                outPacket.encodeInt(size);
                for (int i = 0; i < size; i++) {
                    outPacket.encodeInt(0);
                }
                //
                boolean bool = false;
                outPacket.encodeByte(bool);
                if (bool) {
                    // function
                    int unk = 0;
                    outPacket.encodeInt(unk);
                    if (unk != 0) {
                        new AvatarLook().encode(outPacket);
                        outPacket.encodeInt(0);
                        outPacket.encodeInt(0);
                        boolean zero = false;
                        outPacket.encodeByte(zero);
                        if (zero) {
                            new AvatarLook().encode(outPacket);
                        }
                    }
                }
            }
        }
    }

    public void empty() {
        if (chr != null) {
            chr = null;
        }
        displayChrs = null;
    }
}
