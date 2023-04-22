package net.swordie.ms.connection.api;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.swordie.ms.Server;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.handlers.ApiRequestHandler;
import net.swordie.ms.handlers.header.InHeader;
import org.apache.log4j.Logger;

import static net.swordie.ms.connection.netty.NettyClient.CLIENT_KEY;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public class ApiHandler extends SimpleChannelInboundHandler<InPacket> {

    private static final Logger log = Logger.getLogger(ApiHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, InPacket inPacket) throws Exception {
        Client c = (Client) ctx.channel().attr(CLIENT_KEY).get();
        short op = inPacket.decodeShort();
        ApiInHeader opHeader = ApiInHeader.getByVal(op);
        if(opHeader == null) {
            handleUnknown(inPacket, op);
            return;
        }
        if(!InHeader.isSpamHeader(InHeader.getInHeaderByOp(op))) {
            log.debug(String.format("[API In]\t| %s, %d/0x%s\t| %s", InHeader.getInHeaderByOp(op), op, Integer.toHexString(op).toUpperCase(), inPacket));
        }
        switch (opHeader) {
            case REQUEST_TOKEN:
                ApiRequestHandler.handleTokenRequest(c, inPacket);
                break;
            case CREATE_ACCOUNT_REQUEST:
                ApiRequestHandler.handleCreateAccountRequest(c, inPacket);
                break;
        }
    }

    private void handleUnknown(InPacket inPacket, short op) {
        log.error("Unknown API request " + op);
    }
}
