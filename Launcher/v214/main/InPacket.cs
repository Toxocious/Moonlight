
using System;
using System.Text;

namespace Moonlight
{
    public class InPacket
    {
        public byte[] buf = new byte[256];
        public byte[] bufData = new byte[256];
        public byte[] lenBuf = new byte[4];
        public int len = -1;
        public const int BufferSize = 256;
        public int curLen;
        public int lenBufPtr;
        public int readPtr;

        public byte readByte()
        {
            int num = (int)this.bufData[this.readPtr];
            ++this.readPtr;
            return (byte)num;
        }

        public byte[] readBytes(int len)
        {
            byte[] numArray = new byte[len];
            for (int index = 0; index < len; ++index)
                numArray[index] = this.bufData[this.readPtr++];
            return numArray;
        }

        public short readShort()
        {
            int num = (int)(short)((int)this.bufData[this.readPtr] + ((int)this.bufData[this.readPtr + 1] << 8));
            this.readPtr += 2;
            return (short)num;
        }

        public int readInt()
        {
            int num = (int)this.bufData[this.readPtr] + ((int)this.bufData[this.readPtr + 1] << 8) + ((int)this.bufData[this.readPtr + 2] << 16) + ((int)this.bufData[this.readPtr + 3] << 24);
            this.readPtr += 4;
            return num;
        }

        public long readLong()
        {
            long num = (long)this.bufData[this.readPtr]
                    | ((long)this.bufData[this.readPtr + 1] << 8)
                    | ((long)this.bufData[this.readPtr + 2] << 16)
                    | ((long)this.bufData[this.readPtr + 3] << 24)
                    | ((long)this.bufData[this.readPtr + 4] << 32)
                    | ((long)this.bufData[this.readPtr + 5] << 40)
                    | ((long)this.bufData[this.readPtr + 6] << 48)
                    | ((long)this.bufData[this.readPtr + 7] << 56);
            this.readPtr += 8;
            return num;
        }

        public string readString()
        {
            short num = this.readShort();
            Console.WriteLine("Len = " + (object)num);
            return Encoding.ASCII.GetString(this.readBytes((int)num), 0, (int)num);
        }
    }
}
