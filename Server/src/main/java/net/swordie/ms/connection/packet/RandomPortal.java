package net.swordie.ms.connection.packet;

import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.Encodable;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.life.Life;
import net.swordie.ms.util.Position;

/**
 * @author Sjonnie
 * Created on 9/12/2018.
 */
public class RandomPortal extends Life implements Encodable {

    private int charID;
    private Type appearType;

    public RandomPortal(Type appearType, Position pos, int charID) {
        super(0);
        this.appearType = appearType;
        super.setPosition(pos);
        this.charID = charID;
    }

    @Override
    public void encode(OutPacket outPacket) {
        outPacket.encodePosition(getPosition());
        outPacket.encodeInt(getField().getId());
        outPacket.encodeInt(getCharID());
    }

    public int getCharID() {
        return charID;
    }

    public void setCharID(int charID) {
        this.charID = charID;
    }

    @Override
    public void broadcastSpawnPacket(Char onlyChar) {
        onlyChar.write(RandomPortalPool.created(this));
    }

    @Override
    public void broadcastLeavePacket() {
        OutPacket packet = RandomPortalPool.removed(getObjectId());
        if (getCharID() == -1) {
            getField().broadcastPacket(packet);
        } else {
            Char chr = getField().getCharByID(getCharID());
            if (chr != null) {
                chr.write(packet);
            }
        }
    }

    public Type getAppearType() {
        return appearType;
    }

    public void setAppearType(Type appearType) {
        this.appearType = appearType;
    }

    public enum Type {
        None("undefined"),
        Event("random_portal_event"),
        PolloFritto("random_portal_pollo_fritto"),
        Inferno("random_portal_inferno"),
        ;

        private String script;

        Type(String script) {
            this.script = script;
        }

        public String getScript() {
            return script;
        }
    }
}
