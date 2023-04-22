package net.swordie.ms.handlers;

import net.swordie.ms.Server;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.ApiResponse;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.AccountCreateResult;
import net.swordie.ms.enums.AccountType;
import net.swordie.ms.enums.ApiTokenResultType;
import net.swordie.ms.util.Util;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Sjonnie
 * Created on 10/5/2018.
 */
public class ApiRequestHandler {

    private static final Logger log = Logger.getLogger(ApiRequestHandler.class);
    private static final int TOKEN_LENGTH = 50;

    public static void handleTokenRequest(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        String password = inPacket.decodeString();
        User user = User.getFromDBByName(name);
        ApiTokenResultType atrt;
        boolean success = false;
        if (user == null) {
            atrt = ApiTokenResultType.InvalidUserPassCombination;
        } else {
            String dbPassword = user.getPassword();
            boolean hashed = Util.isStringBCrypt(dbPassword);
            if (hashed) {
                try {
                    success = BCrypt.checkpw(password, dbPassword);
                } catch (IllegalArgumentException e) { // if password hashing went wrong
                    log.error(String.format("bcrypt check in login has failed! dbPassword: %s; stack trace: %s", dbPassword, e.getStackTrace().toString()));
                    success = false;
                }
            } else {
                success = password.equals(dbPassword);
            }
        }
        byte[] token = new byte[]{};
        if (success) {
            atrt = ApiTokenResultType.Success;
            // Generate token
            token = new byte[TOKEN_LENGTH];
            for (int i = 0; i < token.length; i++) {
                token[i] = (byte) Util.getRandom('0', '~');
            }
            Server.getInstance().addAuthToken(token, user.getId());
        } else {
            atrt = ApiTokenResultType.InvalidUserPassCombination;
        }
        c.write(ApiResponse.tokenRequestResult(atrt, new String(token)));
    }

    public static void handleCreateAccountRequest(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        String pwd = inPacket.decodeString();
        String email = inPacket.decodeString();
        AccountCreateResult acr = AccountCreateResult.Success;
        if (User.getFromDBByName(name) != null) {
            acr = AccountCreateResult.NameInUse;
        } /*else if (Account.getFromDBByIp(c.getIP()) != null) {
            acr = AccountCreateResult.IpAlreadyHasAccount;
        } */ else if (name.length() < 4 || pwd.length() < 4) {
            acr = AccountCreateResult.Unknown;
        }
        if (acr == AccountCreateResult.Success) {
            User user = new User(name);
            user.setHashedPassword(pwd);
            user.setEmail(email);
            user.setRegisterIp(c.getIP());
            user.setCharacterSlots(ServerConstants.MAX_CHARACTERS / 2);
            DatabaseManager.saveToDB(user);
        }
        c.write(ApiResponse.createAccountResult(acr));
    }
}
