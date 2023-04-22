package net.swordie.ms.connection.packet;

import net.swordie.ms.life.Reactor;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;

/**
 * Created on 4/21/2018.
 */
public class ReactorPool {

    /**
     * Packet creator for CReactorPool::OnReactorChangeState.
     * @param reactor The Reactor the change gets changed from
     * @param delay the delay until the start of the change
     * @param stateLength the total length of the change, in 10ths of a second
     * @return the resulting OutPacket
     */
    public static OutPacket reactorChangeState(Reactor reactor, short delay, byte stateLength) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_CHANGE_STATE);

        outPacket.encodeInt(reactor.getObjectId());
        outPacket.encodeByte(reactor.getState());
        outPacket.encodePosition(reactor.getPosition());
        outPacket.encodeShort(delay);
        outPacket.encodeByte(reactor.getProperEventIdx());
        outPacket.encodeByte(stateLength);
        outPacket.encodeInt(reactor.getOwnerID());

        return outPacket;
    }

    public static OutPacket reactorEnterField(Reactor reactor) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_ENTER_FIELD);

        outPacket.encodeInt(reactor.getObjectId());
        outPacket.encodeInt(reactor.getTemplateId());
        outPacket.encodeByte(reactor.getState());
        outPacket.encodePosition(reactor.getPosition());
        outPacket.encodeByte(reactor.isFlip());
        outPacket.encodeString(reactor.getName());

        return outPacket;
    }

    public static OutPacket reactorResetState(Reactor reactor) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_RESET_STATE);

        outPacket.encodeInt(reactor.getObjectId());

        return outPacket;
    }

    public static OutPacket reactorOwnerInfo(Reactor reactor) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_OWNER_INFO);

        outPacket.encodeInt(reactor.getObjectId());
        outPacket.encodeInt(reactor.getOwnerID());

        return outPacket;
    }

    public static OutPacket reactorRemove(Reactor reactor) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_REMOVE);

        outPacket.encodeInt(reactor.getObjectId());

        return outPacket;
    }

    public static OutPacket reactorLeaveField(Reactor reactor) {
        OutPacket outPacket = new OutPacket(OutHeader.REACTOR_LEAVE_FIELD);

        outPacket.encodeInt(reactor.getObjectId());
        outPacket.encodeByte(false); // new 199
        outPacket.encodeByte(reactor.getState());
        outPacket.encodePosition(reactor.getPosition());

        return outPacket;
    }
}
