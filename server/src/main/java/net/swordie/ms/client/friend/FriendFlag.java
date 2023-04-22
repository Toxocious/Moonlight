package net.swordie.ms.client.friend;

/**
 * Created on 4/1/2018.
 */
public enum FriendFlag {
    Friend(0),
    FriendRequest(1),
    FriendOffline(2),
    FriendOnline(3),
    MobileOnline(4),
    MobileOffline(5), // ?
    AccountFriendRequest(6),
    AccountFriendOnline(7),
    AccountFriendOffline(8),

    ;

    private int val;

    FriendFlag(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
