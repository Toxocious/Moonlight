package net.swordie.ms.connection.api;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.crypto.MapleCrypto;
import net.swordie.ms.connection.netty.NettyClient;

import java.util.List;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public class ApiDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext chc, ByteBuf in, List<Object> out) throws Exception {
        NettyClient c = chc.channel().attr(NettyClient.CLIENT_KEY).get();
        if (c != null) {
            if (c.getStoredLength() == -1) {
                if (in.readableBytes() >= 4) {
                    c.setStoredLength(in.readInt());
                }
            }
            if (in.readableBytes() >= c.getStoredLength()) {
                byte[] dec = new byte[c.getStoredLength()];
                in.readBytes(dec);
                c.setStoredLength(-1);

                InPacket inPacket = new InPacket(dec);
                out.add(inPacket);
            }
        }
    }
}
