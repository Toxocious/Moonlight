package net.swordie.ms.handlers;

import java.awt.Color;

import net.swordie.ms.ServerConfig;
import net.swordie.ms.client.Account;
import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.BroadcastMsg;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.character.CharacterStat;
import net.swordie.ms.client.character.keys.FuncKeyMap;
import net.swordie.ms.client.character.items.BodyPart;
import net.swordie.ms.client.character.items.Equip;
import net.swordie.ms.client.character.skills.temp.CharacterTemporaryStat;
import net.swordie.ms.client.jobs.JobManager;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.netty.ChannelHandler;
import net.swordie.ms.connection.packet.MapLoadable;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.constants.GameConstants;
import net.swordie.ms.constants.ItemConstants;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.AccountType;
import net.swordie.ms.enums.CashItemType;
import net.swordie.ms.enums.CharNameResult;
import net.swordie.ms.enums.LoginType;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.loaders.ItemData;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.World;
import net.swordie.ms.world.field.MapTaggedObject;
import org.apache.log4j.LogManager;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.world.Channel;
import net.swordie.ms.Server;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import net.swordie.ms.discord.DiscordWebhook;

import static net.swordie.ms.enums.InvType.EQUIPPED;

import net.swordie.ms.util.FileoutputUtil;
import org.apache.log4j.Logger;

/**
 * Created on 4/28/2017.
 */
public class LoginHandler {
    private static final org.apache.log4j.Logger log = LogManager.getRootLogger();


    @Handler(op = InHeader.PERMISSION_REQUEST)
    public static void handlePermissionRequest(Client c, InPacket inPacket) {
        byte locale = inPacket.decodeByte();
        short version = inPacket.decodeShort();
        String minorVersion = inPacket.decodeString(1);
        if (locale != ServerConstants.LOCALE || version != ServerConstants.VERSION) {
            log.info(String.format("Client %s has an incorrect version.", c.getIP()));
            c.close();
        }
    }

        @Handler(op = InHeader.USE_AUTH_SERVER)
    public static void handleAuthServer(Client client, InPacket inPacket) {
           // client.write(Login.sendAuthServer(false));
    }

    @Handler(op = InHeader.APPLY_HOTFIX)
    public static void handleApplyHotfix(Client c, InPacket inPacket) {
        File dataWz = new File(ServerConstants.RESOURCES_DIR + "/Data2.wz");
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(dataWz.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.write(Login.sendHotfix(data));
    }

    @Handler(op = InHeader.PONG)
    public static void handlePong(Client c, InPacket inPacket) {

    }

    @Handler(op = InHeader.CHECK_LOGIN_AUTH_INFO)
    public static void handleCheckLoginAuthInfo(Client c, InPacket inPacket) {
        byte sid = inPacket.decodeByte();
        String password = inPacket.decodeString();
        String username = inPacket.decodeString();
        byte[] machineID = inPacket.decodeArr(16);
        boolean success;
        LoginType result;
        User user = User.getFromDBByName(username);
        System.out.println("User " + user);
        if (user != null) {
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
            result = success ? LoginType.Success : LoginType.IncorrectPassword;
            if (success) {
                if (Server.getInstance().isUserLoggedIn(user)) {
                    success = false;
                    result = LoginType.AlreadyConnected;
                } else if (user.getBanExpireDate() != null && !user.getBanExpireDate().isExpired()) {
                    success = false;
                    result = LoginType.Blocked;
                    String banMsg = String.format("You have been banned. \nReason: %s. \nExpire date: %s",
                            user.getBanReason(), user.getBanExpireDate().toLocalDateTime());
                    c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(banMsg)));
                } else {
                    if (!hashed) {
                        user.setHashedPassword(user.getPassword());
                        // if a user has an assigned pic, hash it
                        if (user.getPic() != null && user.getPic().length() >= 6 && !Util.isStringBCrypt(user.getPic())) {
                            user.setPic(BCrypt.hashpw(user.getPic(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
                        }
                    }
                    Server.getInstance().addUser(user);
                    c.setUser(user);
                    c.setMachineID(machineID);
                    DatabaseManager.saveToDB(user);
                }
            } else {
                result = LoginType.IncorrectPassword;
                success = false;
            }
        } else {
            result = LoginType.NotRegistered;
            success = false;
        }
        c.write(Login.checkPasswordResult(success, result, user));
    }

        @Handler(ops = {InHeader.WORLD_INFO_REQUEST, InHeader.WORLD_LIST_REQUEST})
    public static void handleWorldListRequest(Client c, InPacket inPacket) {
        if (c.getAccount() != null) {
            DatabaseManager.saveToDB(c.getAccount());
            c.setAccount(null);
        }
        //String[] bgs = new String[]{"Adventure", "adventurePathfinder"};
        String[] bgs = new String[]{"adele"};
        c.write(MapLoadable.setMapTaggedObjectVisisble(Collections.singleton(
                new MapTaggedObject(String.format("%s", Util.getRandomFromCollection(bgs)), true)
        )));
        for (World world : Server.getInstance().getWorlds()) {
            c.write(Login.sendWorldInformation(world, null));
        }
        c.write(Login.sendWorldInformationEnd());
        c.write(Login.sendRecommendWorldMessage(ServerConfig.WORLD_ID, ServerConfig.RECOMMEND_MSG));
    }

    @Handler(op = InHeader.WVS_CRASH_CALLBACK)
    public static void handleWvsCrashCallback(Client c, InPacket inPacket) {
        if (c.getChr() != null) {
            c.getChr().setChangingChannel(false);
        }
    }

    @Handler(op = InHeader.SERVER_STATUS_REQUEST)
    public static void handleServerStatusRequest(Client c, InPacket inPacket) {
            c.write(Login.sendWorldInformationEnd());
    }

    @Handler(op = InHeader.WORLD_STATUS_REQUEST)
    public static void handleWorldStatusRequest(Client c, InPacket inPacket) {
        byte worldId = inPacket.decodeByte();
        c.write(Login.sendServerStatus(worldId));
    }

    @Handler(op = InHeader.SELECT_WORLD)
    public static void handleSelectWorld(Client c, InPacket inPacket) {
        byte type = inPacket.decodeByte();
        if (type != 0) { // idk
            c.write(WvsContext.returnToTitle());
            return;
        }
        byte worldId = inPacket.decodeByte();
        byte channel = (byte) (inPacket.decodeByte() + 1);
        byte idk2 = inPacket.decodeByte();
        String authInfo = inPacket.decodeString();
        int userID = Server.getInstance().getUserIdFromAuthToken(authInfo);
        byte[] machineID = inPacket.decodeArr(16);
        int idk = inPacket.decodeInt();
        byte idk3 = inPacket.decodeByte();
        byte[] localIP = inPacket.decodeArr(4);
        inPacket.decodeString(); // CPU Name
        inPacket.decodeString(); // OS Name
        inPacket.decodeInt(); // RAM
        inPacket.decodeInt();
        inPacket.decodeInt();
        inPacket.decodeInt();
        inPacket.decodeInt();
        inPacket.decodeInt();
        inPacket.decodeInt();

        byte code = 0; // success code

        System.out.println("auth info: " + authInfo);

        User user = User.getFromDBById(userID);
        userID = userID == 0 ? 1 : userID;
        if (user == null) {
            c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Cannot find user or your token is expired. restart the client and try again.")));
            return;
        }

        if (!Server.getInstance().isOnline()) {
            c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Server is offline.")));
            return;
        }


        Account acc = user.getAccountByWorldId(worldId);

        if (acc != null && Server.getInstance().isUserLoggedIn(user)) {
            c.write(Login.checkPasswordResult(false, LoginType.AlreadyConnected, user));
            user.getAccountByWorldId(worldId);
            user.addAccount(acc);
            return;
        }

        if (user.getBanExpireDate() != null && !user.getBanExpireDate().isExpired()) {
            boolean success;
            LoginType result;
            success = false;
            result = LoginType.Blocked;
            String banMsg = String.format("You have been banned. \r\nReason: %s. \r\nExpire date: %s", user.getBanReason(), user.getBanExpireDate().toYYMMDD());
            c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage(banMsg)));
            return;
        }

        //user.setCharacterSlots(40); // already decided on account creation using launcher
        user.getAccountByWorldId(worldId);
        if (acc == null) {
            acc = new Account(user, worldId);
            DatabaseManager.saveToDB(acc); // assign id
            user.addAccount(acc);
            DatabaseManager.saveToDB(user); // add to user's list of accounts
        }
        c.setUser(user);
        c.setAccount(acc);
        acc.setUser(user);
        user.setCurrentAcc(acc);
        c.setChannel(channel);

        c.write(Login.sendAccountInfo(user));


        c.setWorldId(worldId);
        c.setChannel(channel);
        c.setMachineID(machineID);
        c.write(Login.selectWorldResult(c.getUser(), c.getAccount(), code, Server.getInstance().getWorldById(worldId).isReboot() ? "reboot" : "normal", true));
        Server.getInstance().addUser(user);
    }

    @Handler(op = InHeader.LOGOUT_WORLD)
    public static void handleLogOutWorld(Client c, InPacket inPacket) {
        Server.getInstance().removeUser(c.getUser());
        //c.setAccount(c.getAccount());
        c.setUser(c.getUser());
    }

    @Handler(op = InHeader.CHECK_DUPLICATE_ID)
    public static void handleCheckDuplicatedID(Client c, InPacket inPacket) {
        String name = inPacket.decodeString();
        CharNameResult code;
        if (!GameConstants.isValidName(c, name)) {
            code = CharNameResult.Unavailable_Invalid;
        } else {
            code = Char.getFromDBByNameAndWorld(name, c.getAccount().getWorldId()) == null ? CharNameResult.Available : CharNameResult.Unavailable_InUse;
        }
        c.write(Login.checkDuplicatedIDResult(name, code.getVal()));
    }

    @Handler(op = InHeader.LOGIN_AFTER_CHAR_CREATION)
    public static void LoginAfterCharacterCreation(Client c, InPacket inPacket) {
        int characterId = inPacket.decodeInt();
        inPacket.decodeByte();
        String mac = inPacket.decodeString();
        String somethingElse = inPacket.decodeString();
        //String pic = BCrypt.hashpw(inPacket.decodeString(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS));
        //c.getAccount().setPic(pic);
        // Update in DB
        DatabaseManager.saveToDB(c.getAccount());
        if (c.getAccount().getCharById(characterId) == null) {
            c.write(Login.selectCharacterResult(LoginType.UnauthorizedUser, (byte) 0, 0, 0));
            return;
        }
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        Server.getInstance().getWorldById(worldId).getChannelById(channelId).addClientInTransfer(channelId, characterId, c);
        c.write(Login.selectCharacterResult(LoginType.Success, (byte) 0, channel.getPort(), characterId));
    }

    @Handler(op = InHeader.CREATE_NEW_CHARACTER)
    public static void handleCreateNewCharacter(Client c, InPacket inPacket) {
        Account acc = c.getAccount();
        String name = inPacket.decodeString();
        int keySettingType = inPacket.decodeInt();
        int eventNewCharSaleJob = inPacket.decodeInt();
        int curSelectedRace = inPacket.decodeInt();
        JobConstants.JobEnum job = JobConstants.LoginJob.getLoginJobById(curSelectedRace).getBeginJob();
        short curSelectedSubJob = inPacket.decodeShort();
        byte gender = inPacket.decodeByte();
        byte skin = inPacket.decodeByte();

        byte itemLength = inPacket.decodeByte();
        int[] items = new int[itemLength]; //face, hair, markings, skin, overall, top, bottom, cape, boots, weapon
        for (int i = 0; i < itemLength; i++) {
            items[i] = inPacket.decodeInt();
        }
        int face = items[0];
        int hair = items[1];
        CharNameResult code = null;
        if (!ItemData.isStartingItems(items) || skin > ItemConstants.MAX_SKIN || skin < 0
                || face < ItemConstants.MIN_FACE || face > ItemConstants.MAX_FACE
                || hair < ItemConstants.MIN_HAIR || hair > ItemConstants.MAX_HAIR) {
            c.getUser().getOffenseManager().addOffense("Tried to add items unavailable on character creation.");
            code = CharNameResult.Unavailable_CashItem;
        }

        if (!GameConstants.isValidName(c, name)) {
            code = CharNameResult.Unavailable_Invalid;
        } else if (Char.getFromDBByNameAndWorld(name, acc.getWorldId()) != null) {
            code = CharNameResult.Unavailable_InUse;
        } else if (acc.getCharacters().size() >= acc.getUser().getCharacterSlots()) {
            code = CharNameResult.Unavailable_Invalid;
            c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("You are already at the maximum amount of characters on this world.")));
        }
        if (code != null) {
            c.write(Login.checkDuplicatedIDResult(name, code.getVal()));
            return;
        }
        Char chr = new Char(name, keySettingType, eventNewCharSaleJob, job.getJobId(),
                curSelectedSubJob, curSelectedRace, gender, skin, face, hair, items);
        chr.setUserId(acc.getUser().getId());
        JobManager.getJobById(job.getJobId(), chr).setCharCreationStats(chr);

        chr.initFuncKeyMaps(keySettingType, JobConstants.isBeastTamer(chr.getJob()));
        // chr.setFuncKeyMap(FuncKeyMap.getDefaultMapping());
        // default quick slot keys
        chr.setQuickslotKeys(Arrays.asList(42, 82, 71, 73, 29, 83, 79, 81, 2, 3, 4, 5, 16, 17, 18, 19, 6, 7, 8, 9, 20,
                30, 31, 32, 10, 11, 33, 34, 37, 38, 49, 50));
        DatabaseManager.saveToDB(chr);
        CharacterStat cs = chr.getAvatarData().getCharacterStat();
        if (curSelectedRace == JobConstants.LoginJob.DUAL_BLADE.getJobType()) {
            cs.setSubJob(1);
        }

        cs.setCharacterId(chr.getId());
        cs.setCharacterIdForLog(chr.getId());
        cs.setWorldIdForLog(acc.getWorldId());
        for (Map.Entry<Byte, Integer> entry : chr.getAvatarData().getAvatarLook().getHairEquips().entrySet()) {
            Equip equip = ItemData.getEquipDeepCopyFromID(entry.getValue(), false);
            if (equip != null && equip.getItemId() >= 1000000) {
                equip.setBagIndex(entry.getKey());
                chr.addItemToInventory(EQUIPPED, equip, true);
            }
        }
        Equip codex = ItemData.getEquipDeepCopyFromID(1172000, false);
        codex.setInvType(EQUIPPED);
        codex.setBagIndex(BodyPart.MonsterBook.getVal());
        chr.addItemToInventory(EQUIPPED, codex, true);
        if (curSelectedRace == 15) { // Zero hack for adding 2nd weapon (removing it in hair equips for zero look)
            Equip equip = ItemData.getEquipDeepCopyFromID(1562000, false);
            equip.setBagIndex(ItemConstants.getBodyPartFromItem(
                    equip.getItemId(), chr.getAvatarData().getAvatarLook().getGender()));
            chr.addItemToInventory(EQUIPPED, equip, true);
        }
        chr.setLocation(ServerConstants.MAX_CHARACTERS); // so new characters are appended to the end
        acc.addCharacter(chr);
        c.write(Login.createNewCharacterResult(LoginType.Success, chr));
    }

    @Handler(op = InHeader.DELETE_CHARACTER)
    public static void handleDeleteCharacter(Client c, InPacket inPacket) {
        Account acc = c.getAccount();
        User user = c.getUser();
        if (acc != null) {
            String pic = inPacket.decodeString();
            int charId = inPacket.decodeInt();
            if (!Util.isStringBCrypt(user.getPic())) {
                user.setPic(BCrypt.hashpw(user.getPic(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
            }
            if (BCrypt.checkpw(pic, user.getPic())) {
                Char chr = acc.getCharById(charId);
                if (chr != null) {
                    acc.removeChar(chr);
                    c.write(Login.sendDeleteCharacterResult(charId, LoginType.Success));
                } else {
                    c.write(Login.sendDeleteCharacterResult(charId, LoginType.UnauthorizedUser));
                }
            } else {
                c.write(Login.selectCharacterResult(LoginType.IncorrectPassword, (byte) 0, 0, 0));
            }
        }
        // TODO: Update database when deleting a character.
    }

    @Handler(op = InHeader.CLIENT_ERROR)
    public static void handleClientError(Client c, InPacket inPacket) {
        c.close();
        if (inPacket.getData().length < 8) {
            log.error(String.format("Error: %s", inPacket));
            return;
        }
        short type = inPacket.decodeShort();
        String type_str = "Unknown?!";
        if (type == 0x01) {
            type_str = "SendBackupPacket";
        } else if (type == 0x02) {
            type_str = "Crash Report";
        } else if (type == 0x03) {
            type_str = "Exception";
        }
        int errortype = inPacket.decodeInt();
        short data_length = inPacket.decodeShort();

        int idk = inPacket.decodeInt();

        short op = inPacket.decodeShort();

        OutHeader opcode = OutHeader.getOutHeaderByOp(op);
        log.error(String.format("[Error %s] (%s / %d) Data: %s", errortype, opcode, op, inPacket));
        if (opcode == OutHeader.REMOTE_SET_TEMPORARY_STAT) {
            inPacket.decodeInt();
        }
        if (opcode == OutHeader.TEMPORARY_STAT_SET || opcode == OutHeader.REMOTE_SET_TEMPORARY_STAT) {
            for (int i = 0; i < CharacterTemporaryStat.length; i++) {
                int mask = inPacket.decodeInt();
                for (CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                    if (cts.getPos() == i && (cts.getVal() & mask) != 0) {
                        log.error(String.format("[Error %s] Contained stat %s", errortype, cts.toString()));
                    }
                }
            }
        } else if (opcode == OutHeader.CASH_SHOP_CASH_ITEM_RESULT) {
            byte cashType = inPacket.decodeByte();
            CashItemType cit = CashItemType.getResultTypeByVal(cashType);
            log.error(String.format("[Error %s] CashItemType %s", errortype, cit == null ? "Unknown" : cit.toString()));
        }
    }

    @Handler(op = InHeader.PRIVATE_SERVER_PACKET)
    public static void handlePrivateServerPacket(Client c, InPacket inPacket) {
        if (inPacket.getUnreadAmount() > 0) { // hack to ignore another non-game op that throws you a bunch of random bytes
            c.write(Login.sendAuthResponse(inPacket.decodeInt() ^ (OutHeader.PRIVATE_SERVER_PACKET.getValue())));
        }
    }

    @Handler(op = InHeader.CHAR_SELECT_NO_PIC)
    public static void handleCharSelectNoPic(Client c, InPacket inPacket) {

         if (!Server.getInstance().isOnline()) {
             c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Server is offline.")));
             return;
         }

        int characterId = inPacket.decodeInt();
        String mac = inPacket.decodeString();
        String somethingElse = inPacket.decodeString();
        String pic = BCrypt.hashpw(inPacket.decodeString(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS));
        c.getUser().setPic(pic);
        // Update in DB
        DatabaseManager.saveToDB(c.getUser());
        if (c.getUser().getCharById(characterId) == null) {
            c.write(Login.selectCharacterResult(LoginType.UnauthorizedUser, (byte) 0, 0, 0));
            return;
        }
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        if (c.getAccount().hasCharacter(characterId)) {
            Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
            Server.getInstance().getWorldById(worldId).getChannelById(channelId).addClientInTransfer(channelId, characterId, c);
            c.write(Login.selectCharacterResult(LoginType.Success, (byte) 0, channel.getPort(), characterId));
        } else {
            c.write(Login.selectCharacterResult(LoginType.UnauthorizedUser, (byte) 0, 0, 0));
        }
    }


    @Handler(op = InHeader.CHAR_SELECT)
    public static void handleCharSelect(Client c, InPacket inPacket) {
        if (!Server.getInstance().isOnline()) {
            c.write(WvsContext.broadcastMsg(BroadcastMsg.popUpMessage("Server is offline.")));
            return;
        }
        int characterId = inPacket.decodeInt();
        String name = inPacket.decodeString();
        byte worldId = c.getWorldId();
        byte channelId = c.getChannel();
        Channel channel = Server.getInstance().getWorldById(worldId).getChannelById(channelId);
        System.out.println(name + " is logging in!");
        FileoutputUtil.log("Chat.txt", "[LOGIN] " + name + " is logging in!");
        if (c.isAuthorized() && c.getAccount().hasCharacter(characterId)) {
            Server.getInstance().getWorldById(worldId).getChannelById(channelId).addClientInTransfer(channelId, characterId, c);
            c.write(Login.selectCharacterResult(LoginType.Success, (byte) 0, channel.getPort(), characterId));

        }
        // if anything is wrong, the 2nd pwd authorizer should return an error
    }

    @Handler(op = InHeader.CHECK_SPW_REQUEST)
    public static boolean handleCheckSpwRequest(Client c, InPacket inPacket) {
        boolean success = false;
        User user = c.getUser();
        Account account = c.getAccount();
        int picEncodingType = inPacket.decodeInt();
        /* hardcoded 0, could change the encoding of pic in further patches . In 202.2 I think it did some weird things
        per encoding type + length. Got reverted in 202.3 though.
           */
        String pic = inPacket.decodeString();
        int charId = inPacket.decodeInt();
        boolean invisible = inPacket.decodeByte() != 0;
        String mac = inPacket.decodeString();
        String hwid = inPacket.decodeString();
//        int userId = inPacket.decodeInt();
        // after this: 2 strings indicating pc info. Not interested in that rn
        if (!Util.isStringBCrypt(user.getPic())) {
            user.setPic(BCrypt.hashpw(user.getPic(), BCrypt.gensalt(ServerConstants.BCRYPT_ITERATIONS)));
        }
      //  if (BCrypt.checkpw(pic, user.getPic())) {
            success = true;
      //  } else {
     //       c.write(Login.selectCharacterResult(LoginType.IncorrectPassword, (byte) 0, 0, 0));
     //   }
        c.setAuthorized(success);
        return success;
    }


    @Handler(op = InHeader.CHANGE_PIC_REQUEST)
    public static void handleChangePicRequest(Client c, InPacket inPacket) {
        User user = c.getUser();
        String currentPic = inPacket.decodeString();
        String newPic = inPacket.decodeString();
        if (currentPic.equals(user.getPic())) {
            user.setPic(newPic);
        } else {
            c.write(Login.selectCharacterResult(LoginType.IncorrectPassword, (byte) 0, 0, 0));
        }
    }

    @Handler(op = InHeader.LOGIN_BASIC_INFO)
    public static void handleLoginBasicInfo(Client c, InPacket inPacket) {
        boolean idk = inPacket.decodeByte() != 0;
    }


    @Handler(op = InHeader.AUTH_FAILURE)
    public static void handleAuthFailure(Client c, InPacket inPacket) {
        byte step = inPacket.decodeByte();
        int errorCode = inPacket.decodeInt();
        log.error(String.format("Auth failure! Login step %d, errorCode %d.", step, errorCode));
        byte worldID = 1;
        byte channel = 1;
        byte code = 0;
        c.setWorldId(worldID);
        c.setChannel(channel);
    }

    @Handler(op = InHeader.EXCEPTION_LOG)
    public static void handleExceptionLog(Client c, InPacket inPacket) throws IOException {
        String str = Util.toStringFromAscii(inPacket.getData());
        log.error(str + "\r\n");
        FileoutputUtil.log("ExceptionLog.txt", str + "\r\n\n"); // prob this isn't needed


        String packet = str.split("[]]")[1].substring(12); // skip everything up until the opcode
        byte[] fullPacketArr = Util.getByteArrayByString(packet);
        short op = (short) ((fullPacketArr[0] & 0xFF) + ((fullPacketArr[1] & 0xFF) << 8));
        byte[] packetData = new byte[fullPacketArr.length - 2];
        System.arraycopy(fullPacketArr, 2, packetData, 0, packetData.length);
        OutHeader header = OutHeader.getOutHeaderByOp(op);

        String msg = String.format("Exception log: [%s], %d/0x%X\t| %s\r\n Full String: %s", header, op, op, Util.readableByteArray(packetData), str);
        StringBuilder sb = new StringBuilder();

        if (header == OutHeader.TEMPORARY_STAT_SET || header == OutHeader.REMOTE_SET_TEMPORARY_STAT || header == OutHeader.USER_ENTER_FIELD) {
            inPacket = new InPacket(packetData);
            if (header == OutHeader.REMOTE_SET_TEMPORARY_STAT) {
                inPacket.decodeInt(); // chr id
            } else if (header == OutHeader.USER_ENTER_FIELD) {
                inPacket.decodeInt();
                inPacket.decodeInt();
                inPacket.decodeString();
                inPacket.decodeString(); // parent name, deprecated
                // guild
                inPacket.decodeString();
                inPacket.decodeShort();
                inPacket.decodeByte();
                inPacket.decodeShort();
                inPacket.decodeByte();
                // end guild
                inPacket.decodeByte();
                inPacket.decodeInt();
                inPacket.decodeInt();
                inPacket.decodeInt();
                inPacket.decodeInt();
                inPacket.decodeByte();
            }
            for (int i = 0; i < CharacterTemporaryStat.length; i++) {
                int mask = inPacket.decodeInt();
                for (CharacterTemporaryStat cts : CharacterTemporaryStat.values()) {
                    if (cts.getPos() == i && (cts.getVal() & mask) != 0) {
                        String stat = String.format("Contained stat %s", cts.toString());
                        sb.append(stat);
                        log.error(stat);
                    }
                }
            }
        }

        msg = msg + "\r\n" + sb.toString();
        log.error(msg);
        FileoutputUtil.log("ErrorCodes.txt", msg);

        DiscordWebhook webhook = new DiscordWebhook("");
        webhook.setContent("Exception Encountered \r\nHeader: " + header);
        try {
            webhook.execute(); //Handle exception
        } catch (IOException ex) {
            log.error(ex);
            //  Logger.getLogger(AdminCommands.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }


    @Handler(op = InHeader.VIEW_CHANNEL_REQUEST)
    public static void handleViewChannelRequest(Client c, InPacket inPacket) {
        byte idk = inPacket.decodeByte();
        int worldId = inPacket.decodeInt();
        World world = Server.getInstance().getWorldById(worldId);
        if (world != null) {
            c.write(Login.viewChannelResult(LoginType.Success, worldId));
        } else {
            c.write(Login.viewChannelResult(LoginType.Unknown, worldId));
        }

    }

    @Handler(op = InHeader.CHAR_POSITION_CHANGE)
    public static void handleCharPositionChange(Client c, InPacket inPacket) {
        User user = c.getUser();
        Account acc = c.getAccount();
        if (acc == null) {
            return;
        }
        inPacket.decodeInt(); // accID
        if (inPacket.decodeByte() != 0) {
            int size = inPacket.decodeInt();
            for (int i = 0; i < size; i++) {
                int charId = inPacket.decodeInt();
                Char chr = acc.getCharById(charId);
                if (chr == null) {
                    user.getOffenseManager().addOffense("Tried to change order of a Char that is not linked to their account.");
                } else {
                    chr.setLocation(i);
                }
            }
        }
    }

    @Handler(op = InHeader.ALIVE_ACK)
    public static void handleAliveAck(Client c, InPacket inPacket) {
        if (c.isWaitingForAliveAck()) {
            c.setPing(System.currentTimeMillis() - c.getLastPingTime());
        }
    }
}
