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

  private static final Map<String, Tuple<String, Integer>> expected_checksums =
      new HashMap<String, Tuple<String, Integer>>() {
        {
          // put ("filename", new Tuple<>(checksum, file_size));
          put("Character",
              new Tuple<>("0c3a2d111090491a64fdf5e142bdbdaa", 1341660164));
          put("Etc",
              new Tuple<>("d7539df859344568b686fbb760df70d6", 214681713));
          put("Item",
              new Tuple<>("6123e13d6ff116f2bac4121116296165", 388819460));
          put("Map",
              new Tuple<>("782a0930234120c0bee5d23bc3ed173d", 1473380548));
          put("Map001",
              new Tuple<>("720e14ca2f3098a4964169bee432fa66", 872969929));
          put("Map002",
              new Tuple<>("ca4c6ede1af51d149c34f60926f4d0a7", 690729962));
          put("Map2",
              new Tuple<>("146bef286693cd7c34b5d76d45905cbf", 861081084));
          put("Mob",
              new Tuple<>("3c3f96fae7af021cbc392102d80d2470", 1839555194));
          put("Mob001",
              new Tuple<>("06947fac99bd520c47d1d5affae2116f", 386513100));
          put("Mob2",
              new Tuple<>("2e390f74d7745b99de1fa321cf29d75c", 1006464994));
          put("Npc",
              new Tuple<>("830bc0721f4ad0547c5020e9845e58e2", 901711815));
          put("Quest",
              new Tuple<>("f3c70c00630e254aff0ee5bfd1d5e91e", 23147121));
          put("Skill",
              new Tuple<>("98a41d8eb1990aedfd7d35a7434f2715", 1057756523));
          put("Skill001",
              new Tuple<>("a246edf3a279895f9dc497808d63d96d", 1663688499));
          put("Skill002",
              new Tuple<>("95ecc4ae0437e80eb5619c0db5e46a8f", 462731503));
          put("UI", new Tuple<>("0b50cddf55e772f21c40016a4dfc0c9f", 488263570));
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

    FileChecksumResult fcr = FileChecksumResult.Failure;

    Tuple<String, Integer> expectedValues =
        expected_checksums.get(received_filename);

    if (expectedValues != null) {
      String expectedChecksum = expectedValues.getLeft();
      int expectedFileSize = expectedValues.getRight();

      if (expectedChecksum.equals(received_checksum) &&
          expectedFileSize == received_file_size) {
        fcr = FileChecksumResult.Success;
      }
    }

    c.write(ApiResponse.checkFileChecksumResult(fcr));
  }
}
