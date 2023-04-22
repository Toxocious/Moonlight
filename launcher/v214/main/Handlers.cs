using System;

namespace Swordie
{
    internal class Handlers
    {
        public static string getAuthTokenFromInput(InPacket inPacket)
        {
            inPacket.readInt();
            int num1 = (int)inPacket.readShort();
            int num2 = (int)inPacket.readByte();
            string str = "";
            if (num2 == 0)
            {
                str = inPacket.readString();
                Console.WriteLine(str);
            }
            return str;
        }
    }
}
