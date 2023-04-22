package net.swordie.ms.connection.api;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.swordie.ms.client.Client;
import net.swordie.ms.connection.Packet;
import net.swordie.ms.connection.crypto.MapleCrypto;
import net.swordie.ms.connection.netty.NettyClient;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Util;
import org.apache.log4j.Logger;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public class ApiEncoder extends MessageToByteEncoder<Packet> {

    private static final Logger log = Logger.getLogger(ApiEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext chc, Packet outPacket, ByteBuf bb) throws Exception {
        byte[] data = outPacket.getData();
        NettyClient c = chc.channel().attr(NettyClient.CLIENT_KEY).get();

        if (c != null) {
            log.debug("[API Out]\t| " + outPacket);

            bb.writeInt(data.length);
            bb.writeBytes(data);

        } else {
            log.debug("[PacketEncoder] | Plain sending " + outPacket);
            bb.writeBytes(data);
        }
    }
}
