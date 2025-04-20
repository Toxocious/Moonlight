package net.swordie.ms.handlers.social;

import net.swordie.ms.client.Client;
import net.swordie.ms.client.User;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.client.friend.FriendFlag;
import net.swordie.ms.client.friend.FriendType;
import net.swordie.ms.client.friend.result.FriendResult;
import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.db.DatabaseManager;
import net.swordie.ms.connection.packet.WvsContext;
import net.swordie.ms.handlers.Handler;
import net.swordie.ms.handlers.header.InHeader;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class FriendHandler {

    private static final Logger log = Logger.getLogger(FriendHandler.class);


    @Handler(op = InHeader.LOAD_ACCOUNT_ID_OF_CHARACTER_FRIEND_REQUEST)
    public static void handleLoadAccountIDOfCharacterFriendRequest(Client c, InPacket inPacket) {
//        c.write(WvsContext.loadAccountIDOfCharacterFriendResult(c.getChr().getFriends()));
    }

    @Handler(op = InHeader.FRIEND_REQUEST)
    public static void handleFriendRequest(Client c, InPacket inPacket) {
        Char chr = c.getChr();
        byte type = inPacket.decodeByte();
        FriendType ft = Arrays.stream(FriendType.values()).filter(f -> f.getVal() == type).findFirst().orElse(null);
        if (ft == null) {
            chr.chatMessage("Unknown friend request type.");
            log.error(String.format("Unknown friend request type %d", type));
            return;
        }
        Char other;
        boolean online;
        switch (ft) {
            case FriendReq_SetFriend:
                String name = inPacket.decodeString();
                other = c.getWorld().getCharByName(name);
                online = true;
                if (other == null) {
                    other = Char.getFromDBByName(name);
                    if (other == null) {
                        c.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                        return;
                    }
                    online = false;
                }
                String groupName = inPacket.decodeString();
                String memo = inPacket.decodeString();
                boolean account = inPacket.decodeByte() != 0;
                String nick = "";
                if (account) {
                    nick = inPacket.decodeString();
                    if (nick.equalsIgnoreCase("")) {
                        nick = name;
                    }
                }
                if (!online) {
                    chr.chatMessage("That person is currently not online.");
                    return;
                }
                Friend friend = new Friend();
                friend.setFriendID(other.getId());
                friend.setGroup(groupName);
                friend.setMemo(memo);
                friend.setName(name);
                friend.setFriendAccountID(other.getAccount().getId());
                if (account) {
                    friend.setNickname(nick);
                    friend.setFlag(FriendFlag.AccountFriendOffline);
                    chr.getUser().addFriend(friend);
                } else {
                    chr.getFriends().add(friend);
                    friend.setFlag(FriendFlag.FriendOffline);
                }

                Friend otherFriend = new Friend();
                otherFriend.setFriendID(chr.getId());
                otherFriend.setName(chr.getName());
                otherFriend.setFriendAccountID(chr.getAccount().getId());
                otherFriend.setGroup("Default Group");
                if (account) {
                    otherFriend.setNickname(chr.getName());
                    otherFriend.setFlag(FriendFlag.AccountFriendRequest);
                    other.getUser().addFriend(otherFriend);
                } else {
                    otherFriend.setFlag(FriendFlag.FriendRequest);
                    other.addFriend(otherFriend);
                }
                c.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_Done, name)));
                c.write(WvsContext.friendResult(FriendResult.loadFriends(chr.getAllFriends())));
                other.write(WvsContext.friendResult(
                        FriendResult.friendInvite(otherFriend, account, chr.getLevel(), chr.getJob(), chr.getSubJob())));
                chr.initFriendStatus();
                break;
            case FriendReq_AcceptFriend:
                int friendID = inPacket.decodeInt();
                online = true;
                Char requestor = c.getWorld().getCharByID(friendID);
                if (requestor == null) {
                    requestor = Char.getFromDBById(friendID);
                    online = false;
                    if (requestor == null) {
                        c.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                        return;
                    }
                }
                friend = chr.getFriendByCharID(friendID);
                friend.setFlag(online ? FriendFlag.AccountFriendOnline : FriendFlag.AccountFriendOffline);
                if (online) {
                    friend.setChannelID(requestor.getClient().getChannel());
                    otherFriend = requestor.getFriendByCharID(chr.getId());
                    otherFriend.setChannelID(c.getChannel());
                    otherFriend.setFlag(FriendFlag.AccountFriendOnline);
                    requestor.write(WvsContext.friendResult(FriendResult.updateFriend(otherFriend)));
                    requestor.chatMessage(String.format("%s has accepted your friend request!", chr.getName()));
                }
                c.write(WvsContext.friendResult(FriendResult.updateFriend(friend)));
                chr.initFriendStatus();
                if (!online) {
                    DatabaseManager.saveToDB(requestor);
                }
                break;
            case FriendReq_AcceptAccountFriend:
                int userId = inPacket.decodeInt();
                User userRef = c.getWorld().getUserById(userId);
                User myUser = chr.getUser();
                online = true;
                if (userRef == null) {
                    online = false;
                    userRef = User.getFromDBById(userId);
                    if (userRef == null) {
                        chr.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                        return;
                    }
                }
                friend = myUser.getFriendByUserID(userId);
                friend.setFlag(online ? FriendFlag.AccountFriendOnline : FriendFlag.AccountFriendOffline);
                otherFriend = userRef.getFriendByUserID(myUser.getId());
                otherFriend.setFlag(FriendFlag.AccountFriendOnline);
                otherFriend.setChannelID(chr.getClient().getChannel());
                if (online) {
                    requestor = userRef.getCurrentChr();
                    friend.setChannelID(requestor.getClient().getChannel());
                    requestor.chatMessage(String.format("%s has accepted your account friend request!", chr.getName()));
                    requestor.write(WvsContext.friendResult(FriendResult.updateFriend(otherFriend)));
                } else {
                    DatabaseManager.saveToDB(otherFriend);
                }
                c.write(WvsContext.friendResult(FriendResult.updateFriend(friend)));
                chr.initFriendStatus();
                break;
            case FriendReq_DeleteFriend:
                friendID = inPacket.decodeInt();
                friend = chr.getFriendByCharID(friendID);
                if (friend == null) {
                    chr.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                    return;
                }
                other = c.getWorld().getCharByID(friendID);
                online = true;
                if (other == null) {
                    online = false;
                    other = Char.getFromDBById(friendID);
                }
                otherFriend = other == null ? null : other.getFriendByCharID(chr.getId());
                if (other != null) {
                    other.removeFriend(otherFriend);
                }
                if (online) {
                    other.write(WvsContext.friendResult(FriendResult.deleteFriend(otherFriend)));
                }
                other.removeFriendByID(chr.getId());
                chr.removeFriend(friend);
                chr.write(WvsContext.friendResult(FriendResult.deleteFriend(friend)));
                break;
            case FriendReq_DeleteAccountFriend:
                int accID = inPacket.decodeInt();
                userRef = chr.getUser();
                friend = userRef.getFriendByUserID(accID);
                if (friend == null) {
                    chr.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                    return;
                }
                online = true;
                User otherAccount = c.getWorld().getUserById(accID);
                otherFriend = otherAccount.getFriendByUserID(chr.getUserId());
                if (otherAccount == null) {
                    otherAccount = User.getFromDBById(accID);
                    online = false;
                }
                if (otherAccount != null) {
                    otherAccount.removeFriend(otherFriend);
                }
                if (online) {
                    otherAccount.getCurrentChr().write(WvsContext.friendResult(FriendResult.deleteFriend(otherFriend)));
                }
                userRef.removeFriend(friend);
                //DatabaseManager.saveToDB(userRef);
                //DatabaseManager.saveToDB(otherAccount);
                chr.write(WvsContext.friendResult(FriendResult.deleteFriend(friend)));
                break;
            case FriendReq_RefuseFriend:
                friendID = inPacket.decodeInt();
                friend = chr.getFriendByCharID(friendID);
                if (friend == null) {
                    chr.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                    return;
                }
                chr.write(WvsContext.friendResult(FriendResult.deleteFriend(friend)));
                chr.removeFriend(friend);
                other = c.getWorld().getCharByID(friendID);
                if (other == null) {
                    other = Char.getFromDBById(friendID);
                    if (other == null) {
                        return;
                    }
                    other.removeFriendByID(chr.getId());
                   // DatabaseManager.saveToDB(other);
                } else {
                    other.write(WvsContext.friendResult(FriendResult.deleteFriend(other.getFriendByCharID(chr.getId()))));
                    other.removeFriendByID(chr.getId());
                }
                break;
            case FriendReq_RefuseAccountFriend:
                accID = inPacket.decodeInt();
                userRef = chr.getUser();
                friend = userRef.getFriendByUserID(accID);
                if (friend == null) {
                    chr.write(WvsContext.friendResult(FriendResult.message(FriendType.FriendRes_SetFriend_UnknownUser)));
                    return;
                }
                chr.write(WvsContext.friendResult(FriendResult.deleteFriend(friend)));
                userRef.removeFriend(friend);
                otherAccount = c.getWorld().getUserById(accID);
                if (otherAccount == null) {
                    otherAccount = User.getFromDBById(accID);
                    if (otherAccount == null) {
                        return;
                    }
                    otherAccount.removeFriend(accID);
               //     DatabaseManager.saveToDB(otherAccount);
                } else {
                    other = userRef.getCurrentChr();
                    other.write(WvsContext.friendResult(FriendResult.deleteFriend(other.getFriendByCharID(chr.getId()))));
                    otherAccount.removeFriend(chr.getId());
                }
                break;
            case FriendReq_ModifyAccountFriendGroup:
                accID = inPacket.decodeInt();
                friend = chr.getUser().getFriendByUserID(accID);
                if (friend != null) {
                    friend.setGroup(inPacket.decodeString());
                    chr.write(WvsContext.friendResult(FriendResult.updateFriend(friend)));
                }
                break;
            case FriendReq_ModifyFriend:
                account = inPacket.decodeByte() != 0;
                friendID = inPacket.decodeInt();
                accID = inPacket.decodeInt();
                friend = account ? chr.getUser().getFriendByUserID(accID) : chr.getFriendByCharID(friendID);
                friend.setNickname(inPacket.decodeString());
                friend.setMemo(inPacket.decodeString());
                chr.write(WvsContext.friendResult(FriendResult.updateFriend(friend)));
                break;
            default:
                chr.chatMessage(String.format("Unhandled friend request type %s", ft.toString()));
                log.error(String.format("Unhandled friend request type %s", ft.toString()));
                break;
        }
    }
}
