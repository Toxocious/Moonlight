package net.swordie.ms.client.friend.result;

import net.swordie.ms.client.friend.Friend;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.client.friend.FriendType;

import java.util.Set;

/**
 * Created on 3/31/2018.
 */
public class FriendResult {

    private FriendType friendType;
    private Friend friend;
    private Set<Friend> friends;
    private String str;
    private boolean isAccountFriend;
    private int level;
    private int job;
    private int subJob;

    public FriendResult(FriendType friendType) {
        this.friendType = friendType;
    }
    public void encode(OutPacket outPacket) {
        outPacket.encodeByte(friendType.getVal());
        switch (friendType) {
            case FriendRes_LoadFriend_Done:
                outPacket.encodeInt(friends.size());
                for (Friend f : friends) {
                    f.encode(outPacket);
                }
                break;
            case FriendRes_NotifyChange_FriendInfo:
                outPacket.encodeInt(friend.getFriendID());
                outPacket.encodeInt(friend.getFriendAccountID());
                friend.encode(outPacket);
                break;
            case FriendRes_Invite:
                outPacket.encodeByte(isAccountFriend);
                outPacket.encodeInt(friend.getFriendID());
                outPacket.encodeInt(friend.getFriendAccountID());
                outPacket.encodeString(friend.getName());
                outPacket.encodeInt(level);
                outPacket.encodeInt(job);
                outPacket.encodeInt(subJob);
                friend.encode(outPacket);
                break;
            case FriendRes_DeleteFriend_Done:
                outPacket.encodeByte(friend.isAccount());
                if (friend.isAccount()) {
                    outPacket.encodeInt(friend.getFriendAccountID());
                } else {
                    outPacket.encodeInt(friend.getFriendID());
                }
                break;
            default:
                outPacket.encodeString(str);
        }
    }

    public static FriendResult loadFriends(Set<Friend> friends) {
        FriendResult fr = new FriendResult(FriendType.FriendRes_LoadFriend_Done);

        fr.friends = friends;
        return fr;
    }

    public static FriendResult updateFriend(Friend friend) {
        FriendResult fr = new FriendResult(FriendType.FriendRes_NotifyChange_FriendInfo);

        fr.friend = friend;

        return fr;
    }

    public static FriendResult friendInvite(Friend friend, boolean accountFriend, int level, int job, int subJob) {
        FriendResult fr = new FriendResult(FriendType.FriendRes_Invite);

        fr.friend = friend;
        fr.isAccountFriend = accountFriend;
        fr.level = level;
        fr.job = job;
        fr.subJob = job;

        return fr;
    }

    public static FriendResult deleteFriend(Friend friend) {
        FriendResult fr = new FriendResult(FriendType.FriendRes_DeleteFriend_Done);

        fr.friend = friend;

        return fr;
    }

    public static FriendResult message(FriendType friendType) {
        return new FriendResult(friendType);
    }

    public static FriendResult message(FriendType friendType, String str) {
        FriendResult fr = new FriendResult(friendType);

        fr.str = str;

        return fr;
    }



}
