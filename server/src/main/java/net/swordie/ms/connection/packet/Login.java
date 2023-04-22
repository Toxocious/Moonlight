package net.swordie.ms.connection.packet;

import net.swordie.ms.client.Account;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.constants.JobConstants;
import net.swordie.ms.ServerConstants;
import net.swordie.ms.enums.LoginType;
import net.swordie.ms.ServerStatus;
import net.swordie.ms.enums.WorldId;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Util;
import net.swordie.ms.util.container.Tuple;
import net.swordie.ms.world.Channel;
import net.swordie.ms.Server;
import net.swordie.ms.world.World;
import net.swordie.ms.util.FileTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Tim on 2/28/2017.
 */
public class Login {

    private static final Comparator<Char> LOCATION_COMPARATOR = (Char c1, Char c2) -> {
        int res;
        int c1id = c1.getId();
        int c1loc = c1.getLocation();
        int c2id = c2.getId();
        int c2loc = c2.getLocation();
        if (c1loc == c2loc) {
            res = Integer.compare(c1id, c2id);
        } else {
            res = Integer.compare(c1loc, c2loc);
        }
        return res;
    };

    public static OutPacket sendConnect(byte[] siv, byte[] riv) {
        OutPacket oPacket = new OutPacket();

        // version (short) + MapleString (short + char array size) + local IV (int) + remote IV (int) + locale (byte)
        // 0xE
        oPacket.encodeShort(15);
        oPacket.encodeShort(ServerConstants.VERSION);
        oPacket.encodeString(ServerConstants.MINOR_VERSION);
        oPacket.encodeArr(siv);
        oPacket.encodeArr(riv);
        oPacket.encodeByte(ServerConstants.LOCALE);
        oPacket.encodeByte(false);
        return oPacket;
    }


    public static OutPacket sendAliveReq() {
        return new OutPacket(OutHeader.ALIVE_REQ.getValue());
    }

    public static OutPacket sendAuthServer(boolean useAuthServer) {
        OutPacket outPacket = new OutPacket(OutHeader.AUTH_SERVER.getValue());
        outPacket.encodeByte(useAuthServer);
        return outPacket;
    }

    public static OutPacket sendHotfix(byte[] data) {
        OutPacket outPacket = new OutPacket(OutHeader.HOTFIX.getValue());

        if (data.length == 0) {
            outPacket.encodeByte(0);
        } else {
            outPacket.encodeArr(Util.toPackedInt(data.length));
            outPacket.encodeInt(data.length); // version maybe?
            outPacket.encodeArr(data);
        }

        return outPacket;
    }

    public static OutPacket checkPasswordResult(boolean success, LoginType msg, User user) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_PASSWORD_RESULT.getValue());

            if (success) {
                outPacket.encodeByte(LoginType.Success.getValue());
                outPacket.encodeByte(0);
                outPacket.encodeInt(0);
                outPacket.encodeString(user.getName());
                outPacket.encodeInt(user.getId());
                outPacket.encodeByte(user.getGender());
                outPacket.encodeByte(0); // new
                outPacket.encodeInt(user.getAccountType().getVal());
                outPacket.encodeInt(user.getAge());
                outPacket.encodeByte(user.getMsg2());
                outPacket.encodeString(user.getName());
                outPacket.encodeByte(user.getpBlockReason());
                outPacket.encodeByte(0); // idk

                outPacket.encodeLong(user.getChatUnblockDate());
                outPacket.encodeLong(user.getChatUnblockDate());
                outPacket.encodeInt(user.getCharacterSlots());
                JobConstants.encode(outPacket);
                outPacket.encodeByte(user.getGradeCode());
                outPacket.encodeInt(-1);
                outPacket.encodeByte(0); // idk
                outPacket.encodeByte(0); // ^
                outPacket.encodeFT(user.getCreationDate());
            } else if (msg == LoginType.Blocked) {
                outPacket.encodeByte(msg.getValue());
                outPacket.encodeByte(0);
                outPacket.encodeInt(0);
                outPacket.encodeByte(0); // nReason
                outPacket.encodeFT(user.getBanExpireDate());
            } else {
                outPacket.encodeByte(msg.getValue());
                outPacket.encodeByte(0); // these two aren't in ida, wtf
                outPacket.encodeInt(0);
            }

        return outPacket;
    }

    public static OutPacket checkPasswordResultForBan(User user) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_PASSWORD_RESULT);

        outPacket.encodeByte(LoginType.BlockedNexonID.getValue());
        outPacket.encodeByte(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeFT(user.getBanExpireDate());

        return outPacket;
    }

    public static int onlineAmount() {
        int total = 0;
        for (World w : Server.getInstance().getWorlds()) {
            for (Channel c : w.getChannels()) {
                total += c.getChars().size();
            }
        }
        return total;
    }

    public static OutPacket sendWorldInformation(World world, Set<Tuple<Position, String>> stringInfos) {
        // CLogin::OnWorldInformation
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION.getValue());

        outPacket.encodeByte(world.getWorldId().getVal());
        outPacket.encodeString(world.getName());
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(world.getWorldState());
        outPacket.encodeString(world.getWorldEventDescription() + onlineAmount());
        outPacket.encodeByte(world.isCharCreateBlock());
        outPacket.encodeByte(world.getChannels().size());
        for (Channel c : world.getChannels()) {
            outPacket.encodeString(c.getName());
            outPacket.encodeInt(50); // c.getGaugePx()
            outPacket.encodeByte(c.getWorldId().getVal());
            outPacket.encodeByte(c.getChannelId());
            outPacket.encodeByte(c.isAdultChannel());
        }
        if (stringInfos == null) {
            outPacket.encodeShort(0);
        } else {
            outPacket.encodeShort(stringInfos.size());
            for (Tuple<Position, String> stringInfo : stringInfos) {
                outPacket.encodePosition(stringInfo.getLeft());
                outPacket.encodeString(stringInfo.getRight());
            }
        }
        outPacket.encodeInt(0); // some offset
        outPacket.encodeShort(0); // connect with star planet stuff, not interested
        return outPacket;
    }


    public static OutPacket sendWorldInformationEnd() {
        OutPacket outPacket = new OutPacket(OutHeader.WORLD_INFORMATION);

        outPacket.encodeByte(-1);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeInt(-1);

        return outPacket;
    }


    public static OutPacket viewChannelResult(LoginType type, int worldId) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_WORLD_BUTTON);

        outPacket.encodeByte(type.getValue());
        outPacket.encodeInt(worldId);
        outPacket.encodeInt(0); // new v214

        return outPacket;
    }

    public static OutPacket sendAccountInfo(User user) {
        OutPacket outPacket = new OutPacket(OutHeader.ACCOUNT_INFO_RESULT);

        outPacket.encodeByte(0); // succeed
        outPacket.encodeInt(user.getId());
        outPacket.encodeByte(user.getGradeCode());
        outPacket.encodeInt(user.getAccountType().getVal());
        outPacket.encodeInt(user.getVipGrade());
        outPacket.encodeByte(user.getPurchaseExp());
        outPacket.encodeString(user.getName());
        outPacket.encodeByte(user.getnBlockReason());
        outPacket.encodeByte(0); // ?
        outPacket.encodeLong(user.getChatUnblockDate());
        outPacket.encodeString(user.getCensoredNxLoginID());
        outPacket.encodeLong(0);
        outPacket.encodeInt(0);
        outPacket.encodeLong(0);
        outPacket.encodeString(""); //v25 = CInPacket::DecodeStr(iPacket_1, &nAge);
        JobConstants.encode(outPacket);
        outPacket.encodeByte(0); // bIsBeginningUser
        outPacket.encodeInt(-1);

        return outPacket;
    }

    public static OutPacket sendServerStatus(byte worldId) {
        OutPacket outPacket = new OutPacket(OutHeader.SERVER_STATUS.getValue());
        World world = Server.getInstance().getWorldById(worldId);
        if (world != null && !world.isFull()) {
            outPacket.encodeByte(world.getStatus().getValue());
        } else {
            outPacket.encodeByte(ServerStatus.BUSY.getValue());
        }
        outPacket.encodeByte(0); // ?

        return outPacket;
    }


    public static OutPacket selectWorldResult(User user, Account account, byte code, String specialServer,
                                              boolean burningEventBlock) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_WORLD_RESULT);

        outPacket.encodeByte(code);
        outPacket.encodeByte(0);
        outPacket.encodeString(specialServer);
        outPacket.encodeString(specialServer);
        outPacket.encodeInt(account.getTrunk().getSlotCount());
        outPacket.encodeByte(burningEventBlock); // bBurningEventBlock
        outPacket.encodeByte(0); //UNK, new in 216
        int reserved = 0;
        outPacket.encodeInt(reserved); // Reserved size
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME)); //Reserved timestamp
        for(int i = 0; i < reserved; i++) {
            // not really interested in this
            FileTime ft = FileTime.fromType(FileTime.Type.ZERO_TIME);
            outPacket.encodeInt(ft.getLowDateTime());
            ft.encode(outPacket);
        }
        boolean isEdited = false;
        outPacket.encodeByte(isEdited); // edited characters
        List<Char> chars = new ArrayList<>(account.getCharacters());
        chars.sort(Comparator.comparingInt(Char::getId));
        int orderSize = chars.size();
        outPacket.encodeInt(orderSize);
        for (Char chr : chars) {
            // order of chars
            outPacket.encodeInt(chr.getId());
        }

        outPacket.encodeByte(chars.size());
        for(Char chr : chars) {
            chr.getAvatarData().encode(outPacket);
            outPacket.encodeByte(false); // family stuff, deprecated (v61 = &v2->m_abOnFamily.a[v59];)
            /*boolean hasRanking = chr.getRanking() != null && !JobConstants.isGmJob(chr.getJob());
            outPacket.encodeByte(hasRanking);
            if (hasRanking) {
                chr.getRanking().encode(outPacket);
            }*/
        }
        outPacket.encodeByte(user.getPicStatus().getVal()); // bLoginOpt
        outPacket.encodeByte(false); // bQuerySSNOnCreateNewCharacter
        outPacket.encodeInt(user.getCharacterSlots());
        outPacket.encodeInt(0); // buying char slots
        outPacket.encodeInt(-1); // nEventNewCharJob
        outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME));
        outPacket.encodeByte(0); // nRenameCount
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeByte(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket checkDuplicatedIDResult(String name, byte code) {
        OutPacket outPacket = new OutPacket(OutHeader.CHECK_DUPLICATED_ID_RESULT);

        outPacket.encodeString(name);
        outPacket.encodeByte(code);

        return outPacket;
    }

    public static OutPacket createNewCharacterResult(LoginType type, Char c) {
        OutPacket outPacket = new OutPacket(OutHeader.CREATE_NEW_CHARACTER_RESULT);

        outPacket.encodeByte(type.getValue());
        if (type == LoginType.Success) {
            c.getAvatarData().encode(outPacket);
        }
        outPacket.encodeByte(true); // new 199

        return outPacket;
    }

    public static OutPacket sendAuthResponse(int response) {
        OutPacket outPacket = new OutPacket(OutHeader.PRIVATE_SERVER_PACKET);

        outPacket.encodeInt(response);

        return outPacket;
    }

    public static OutPacket selectCharacterResult(LoginType loginType, byte errorCode, int port, int characterId) {
        OutPacket outPacket = new OutPacket(OutHeader.SELECT_CHARACTER_RESULT);

        outPacket.encodeByte(loginType.getValue());
        outPacket.encodeByte(errorCode);

        if(loginType == LoginType.Success) {
            byte[] server = new byte[]{54, 68, ((byte) 160), ((byte) 34)};
            outPacket.encodeArr(server);
            outPacket.encodeShort(port);

            byte[] chatServer = new byte[]{0, 0, 0, 0};
            // chat stuff
            outPacket.encodeArr(chatServer);
            //outPacket.encodeShort(0);// chat port

            //outPacket.encodeInt(0);
            outPacket.encodeInt(characterId);

            outPacket.encodeByte(0);
            outPacket.encodeInt(0); // ulArgument
            outPacket.encodeFT(FileTime.fromType(FileTime.Type.ZERO_TIME));
            outPacket.encodeByte(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);

            outPacket.encodeInt(0);

        }
        return outPacket;
    }

    public static OutPacket sendDeleteCharacterResult(int charId, LoginType loginType) {
        OutPacket outPacket = new OutPacket(OutHeader.DELETE_CHARACTER_RESULT);

        outPacket.encodeInt(charId);
        outPacket.encodeByte(loginType.getValue());
        if (loginType == LoginType.Success) {
            outPacket.encodeLong(0);
            outPacket.encodeLong(0);
        }


        return outPacket;
    }

    public static OutPacket sendAuthInfoRequest() {
        OutPacket outPacket = new OutPacket(OutHeader.AUTH_INFO_REQUEST);

        outPacket.encodeByte(0);
        outPacket.encodeByte(0);

        return outPacket;
    }

    public static OutPacket sendRecommendWorldMessage(WorldId worldId, String msg) {
        OutPacket oPacket = new OutPacket(OutHeader.RECOMMENDED_WORLD_MESSAGE);
        oPacket.encodeByte(1);
        oPacket.encodeInt(worldId.getVal());
        oPacket.encodeString(msg);
        return oPacket;
    }
}
