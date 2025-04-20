package net.swordie.ms.life.npc;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.loaders.StringData;
import net.swordie.ms.world.field.Field;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.packet.NpcPool;
import net.swordie.ms.life.Life;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2/19/2018.
 */
public class Npc extends Life {
    private boolean enabled = true;
    private int presentItemID;
    private byte presentItemState;
    private int presentItemTime;
    private int noticeBoardType;
    private int noticeBoardValue;
    private int alpha; // if hideToLocalUser is true
    private String localRepeatEffect;
    private ScreenInfo screenInfo;
    private Map<Integer, String> scripts = new HashMap<>();
    private boolean move;
    private int trunkGet;
    private int trunkPut;
    private boolean shop;


    public Npc(int templateId) {
        super(templateId);
    }

    public void encode(OutPacket outPacket) {
        // CNpc::Init
        outPacket.encodePosition(getPosition());
        outPacket.encodeByte(isMove());
        outPacket.encodeByte(!isFlip());
        outPacket.encodeShort(getFh());

        outPacket.encodeShort(getRx0()); // rgHorz.low
        outPacket.encodeShort(getRx1()); // rgHorz.high
        outPacket.encodeByte(isEnabled());
        outPacket.encodeInt(getPresentItemID());
        outPacket.encodeByte(getPresentItemState());
        outPacket.encodeInt(getPresentItemTime());
        outPacket.encodeInt(getNoticeBoardType());
        if (getNoticeBoardType() == 1) {
            outPacket.encodeInt(getNoticeBoardValue());
        }
        outPacket.encodeInt(getAlpha());
        outPacket.encodeInt(0); // new 188
        outPacket.encodeString(getLocalRepeatEffect());
        ScreenInfo si = getScreenInfo();
        outPacket.encodeByte(si != null);
        if (si != null) {
            si.encode(outPacket);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPresentItemID() {
        return presentItemID;
    }

    public void setPresentItemID(int presentItemID) {
        this.presentItemID = presentItemID;
    }

    public byte getPresentItemState() {
        return presentItemState;
    }

    public void setPresentItemState(byte presentItemState) {
        this.presentItemState = presentItemState;
    }

    public int getPresentItemTime() {
        return presentItemTime;
    }

    public void setPresentItemTime(int presentItemTime) {
        this.presentItemTime = presentItemTime;
    }

    public int getNoticeBoardType() {
        return noticeBoardType;
    }

    public void setNoticeBoardType(int noticeBoardType) {
        this.noticeBoardType = noticeBoardType;
    }

    public int getNoticeBoardValue() {
        return noticeBoardValue;
    }

    public void setNoticeBoardValue(int noticeBoardValue) {
        this.noticeBoardValue = noticeBoardValue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public String getLocalRepeatEffect() {
        return localRepeatEffect;
    }

    public void setLocalRepeatEffect(String localRepeatEffect) {
        this.localRepeatEffect = localRepeatEffect;
    }

    public ScreenInfo getScreenInfo() {
        return screenInfo;
    }

    public void setScreenInfo(ScreenInfo screenInfo) {
        this.screenInfo = screenInfo;
    }

    @Override
    public Npc deepCopy() {
        Npc copy = new Npc(getTemplateId());
        copy.setLifeType(getLifeType());
        copy.setX(getX());
        copy.setY(getY());
        copy.setMobTime(getMobTime());
        copy.setFlip(isFlip());
        copy.setHide(isHide());
        copy.setFh(getFh());
        copy.setCy(getCy());
        copy.setRx0(getRx0());
        copy.setRx1(getRx1());
        copy.setLimitedName(getLimitedName());
        copy.setUseDay(isUseDay());
        copy.setUseNight(isUseNight());
        copy.setHold(isHold());
        copy.setNoFoothold(isNoFoothold());
        copy.setDummy(isDummy());
        copy.setSpine(isSpine());
        copy.setMobTimeOnDie(isMobTimeOnDie());
        copy.setRegenStart(getRegenStart());
        copy.setMove(isMove());
        copy.setMobAliveReq(getMobAliveReq());
        copy.setTrunkGet(getTrunkGet());
        copy.setTrunkPut(getTrunkPut());
        copy.getScripts().putAll(getScripts());
        copy.setShop(isShop());
        return copy;
    }

    public Map<Integer, String> getScripts() {
        return scripts;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        Field field = getField();
        Char controller = field.getLifeToControllers().get(this);
        for (Char chr : field.getChars()) {
            chr.write(NpcPool.npcEnterField(this));
            chr.write(NpcPool.npcChangeController(this, chr == controller));
        }
    }

    @Override
    public void broadcastLeavePacket() {
        Field field = getField();
        for (Char chr : field.getChars()) {
            chr.write(NpcPool.npcLeaveField(this));
            chr.write(NpcPool.npcChangeController(this, false, true));
        }
    }

    @Override
    public void notifyControllerChange(Char controller) {
        for (Char chr : getField().getChars()) {
            chr.write(NpcPool.npcChangeController(this, chr == controller));
        }
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Move: " + isMove() +
                ", Name: " + StringData.getNpcStringById(getTemplateId());
    }

    public void setTrunkGet(int trunkGet) {
        this.trunkGet = trunkGet;
    }

    public int getTrunkGet() {
        return trunkGet;
    }

    public void setTrunkPut(int trunkPut) {
        this.trunkPut = trunkPut;
    }

    public int getTrunkPut() {
        return trunkPut;
    }

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
