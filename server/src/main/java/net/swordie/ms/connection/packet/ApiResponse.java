package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.api.ApiOutHeader;
import net.swordie.ms.enums.AccountCreateResult;
import net.swordie.ms.enums.ApiTokenResultType;
import net.swordie.ms.handlers.ApiRequestHandler;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public class ApiResponse {

    public static OutPacket tokenRequestResult(ApiTokenResultType atrt, String token) {
        OutPacket outPacket = new OutPacket(ApiOutHeader.REQUEST_TOKEN_RESULT);

        outPacket.encodeByte(atrt.getVal());
        if (atrt == ApiTokenResultType.Success) {
            outPacket.encodeString(token);
        }

        return outPacket;
    }

    public static OutPacket createAccountResult(AccountCreateResult acr) {
        OutPacket outPacket = new OutPacket(ApiOutHeader.CREATE_ACCOUNT_RESULT);

        outPacket.encodeByte(acr.ordinal());
//work?
        return outPacket;
    }
}
