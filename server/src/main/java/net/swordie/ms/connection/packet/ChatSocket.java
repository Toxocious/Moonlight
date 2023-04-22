package net.swordie.ms.connection.packet;

import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.handlers.header.OutHeader;
import net.swordie.ms.util.FileTime;

/**
 * Created on 4/1/2018.
 */
public class ChatSocket {

    // public static OutPacket loginResult(boolean success) {
    //  OutPacket outPacket = new OutPacket(OutHeader.CHAT_LOGIN_RESULT);

    //   outPacket.encodeByte(!success);

    //  return outPacket;
    //  }

    //  public static OutPacket logoutResult() {
    // return new OutPacket(OutHeader.CHAT_LOGOUT_RESULT);
}

//  public static OutPacket aliveReq() {
//     return new OutPacket(OutHeader.CHAT_ALIVE_REQ);
// }

//public static OutPacket enterGuildChatRoomResult(boolean success) {
//    OutPacket outPacket = new OutPacket(OutHeader.CHAT_ENTER_GUILD_CHAT_ROOM_RESULT);

//   outPacket.encodeByte(!success);

//   return outPacket;
// }

// public static OutPacket guildChatMessage(int accID, int charID, FileTime readTime, String msg, boolean mobile) {
//  OutPacket outPacket = new OutPacket(OutHeader.CHAT_GUILD_CHAT_MESSAGE);

// outPacket.encodeInt(0); // unused
// outPacket.encodeInt(0); // unused
// outPacket.encodeInt(accID);
// outPacket.encodeInt(charID);
/// outPacket.encodeFT(readTime);
// outPacket.encodeString(msg);
// outPacket.encodeByte(mobile);

// return outPacket;
// }

// public static OutPacket friendChatMessage(int accID, int charID, FileTime readTime, String msg, boolean mobile) {
//   OutPacket outPacket = new OutPacket(OutHeader.CHAT_FRIEND_CHAT_MESSAGE);

//   outPacket.encodeInt(0); // unused
//   outPacket.encodeInt(accID);
//  outPacket.encodeInt(charID);
//   outPacket.encodeFT(readTime);
//  outPacket.encodeString(msg);
//  outPacket.encodeByte(mobile);

// return outPacket;
//  }
//}
