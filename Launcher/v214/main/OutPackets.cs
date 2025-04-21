
namespace Moonlight
{
    internal class OutPackets
    {
        public static OutPacket AuthRequest(
            string username,
            string password
        )
        {
            OutPacket outPacket = new OutPacket((short) 100);
            outPacket.WriteString(username);
            outPacket.WriteString(password);

            return outPacket;
        }

        public static OutPacket CreateAccountRequest(
            string username,
            string password,
            string email
        )
        {
            OutPacket outPacket = new OutPacket((short) 101);
            outPacket.WriteString(username);
            outPacket.WriteString(password);
            outPacket.WriteString(email);

            return outPacket;
        }

        public static OutPacket FileChecksum(
            string filename,
            string checksum,
            long fileSize
        )
        {
            OutPacket outPacket = new OutPacket((short) 102);
            outPacket.WriteString(filename);
            outPacket.WriteString(checksum);
            outPacket.WriteLong(fileSize);

            return outPacket;
        }
    }
}
