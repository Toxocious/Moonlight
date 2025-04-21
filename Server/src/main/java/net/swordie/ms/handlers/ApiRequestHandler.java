package net.swordie.ms.handlers;

import java.util.HashMap;
import java.util.Map;
import net.swordie.ms.Server;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.ApiResponse;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.enums.AccountCreateResult;
import net.swordie.ms.enums.AccountType;
import net.swordie.ms.enums.ApiTokenResultType;
import net.swordie.ms.enums.FileChecksumResult;
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

  private static final Map<String, String> expected_checksums =
      new HashMap<String, String>() {
        {
          put("Character", "80a5dbda09f0d4c2deef483a43357f6a");
          put("Data", "8a2f0c581fe80acbb475de0fa957acf5");
          put("Etc", "97505eb28654e6f79a331e3032b6a27a");
          put("Item", "0734c1003e859c0dadd3ed9b6a4ac422");
          put("Map", "cb7218f83d02cafb2a4de3b2102a97cd");
          put("Map001", "90c727a20bb855aa8b08c8c8e657037c");
          put("Map002", "fe041aee6c9af90ae4c6f6bd14a7ef27");
          put("Map2", "f0cc8c815d1592462a8575be2bd8627d");
          put("Mob", "17669211382353a375128ffee9844501");
          put("Mob001", "d858a2f98196cf0d189ce0fcf9da1a6c");
          put("Mob2", "15195ab866e521a4a05826499ce63abb");
          put("Npc", "e7cbc8e27e60b5cea3747601650654f3");
          put("Quest", "9f3f6e97811895916b2aed45740ff1bf");
          put("Skill", "3561c2d424d797543b5fe6db22adb513");
          put("Skill001", "6e379f832193365d7d9f8df9541974ed");
          put("Skill002", "5b24f097af12103079cf53e818dd937e");
          put("UI", "eaefc622a93956fc8b99a3011241794a");
        }
      };

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
          log.error(String.format("bcrypt check in login has failed! "
                                      + "dbPassword: %s; stack trace: %s",
                                  dbPassword, e.getStackTrace().toString()));
          success = false;
        }
      } else {
        success = password.equals(dbPassword);
      }
    }

    byte[] token = new byte[] {};

    if (success) {
      atrt = ApiTokenResultType.Success;
      // Generate token
      token = new byte[TOKEN_LENGTH];

      for (int i = 0; i < token.length; i++) {
        token[i] = (byte)Util.getRandom('0', '~');
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
    } */
    else if (name.length() < 4 || pwd.length() < 4) {
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

  public static void handleCheckFileChecksum(Client c, InPacket inPacket) {
    String received_filename = inPacket.decodeString();
    String received_checksum = inPacket.decodeString();
    int received_file_size = inPacket.decodeInt();

    FileChecksumResult fcr = expected_checksums.get(received_filename)
                                     .toString()
                                     .equals(received_checksum)
                                 ? FileChecksumResult.Success
                                 : FileChecksumResult.Failure;

    c.write(ApiResponse.checkFileChecksumResult(fcr));
  }
}
