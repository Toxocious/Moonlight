using System.Text;

namespace Moonlight
{
    public class OutPacket
    {
        public byte[] buf = new byte[256];
        public int len;

        public OutPacket(short op)
        {
            this.WriteShort(op);
        }

        public void WriteShort(short s)
        {
            this.Write(new byte[2]
            {
                (byte) s,
                (byte) ((uint) s >> 8)
            });
        }

        public void WriteByte(byte b)
        {
            this.Write(new byte[1] { b });
        }

        public void WriteInt(int i)
        {
            this.Write(new byte[4]
            {
                (byte) i,
                (byte) (i >> 8),
                (byte) (i >> 16),
                (byte) (i >> 24)
            });
        }

        public void WriteLong(long l)
        {
            this.Write(new byte[8]
            {
                (byte) l,
                (byte) (l >> 8),
                (byte) (l >> 16),
                (byte) (l >> 24),
                (byte) (l >> 32),
                (byte) (l >> 40),
                (byte) (l >> 48),
                (byte) (l >> 56)
            });
        }

        public void WriteString(string str)
        {
            this.WriteShort((short)str.Length);
            this.Write(Encoding.ASCII.GetBytes(str));
        }

        public void Write(byte[] data)
        {
            int len = this.len;
            foreach (byte num in data)
            {
                this.buf[len] = num;
                ++len;
            }
            this.len = len;
        }
    }
}
