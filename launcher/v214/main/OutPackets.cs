
namespace Swordie
{
    internal class OutPackets
    {
        public static OutPacket AuthRequest(string username, string pwd)
        {
            OutPacket outPacket = new OutPacket((short)100);
            outPacket.WriteString(username);
            outPacket.WriteString(pwd);
            return outPacket;
        }

        public static OutPacket CreateAccountRequest(
          string username,
          string pwd,
          string email)
        {
            OutPacket outPacket = new OutPacket((short)101);
            outPacket.WriteString(username);
            outPacket.WriteString(pwd);
            outPacket.WriteString(email);
            return outPacket;
        }
    }
}
