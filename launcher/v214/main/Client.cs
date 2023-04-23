using System;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace Swordie
{
    public class Client
    {
        public Socket socket;

        private static readonly ManualResetEvent connectDone = new ManualResetEvent(false);
        private static readonly ManualResetEvent sendDone = new ManualResetEvent(false);
        private static readonly ManualResetEvent receiveDone = new ManualResetEvent(false);

        // Server IP
        private readonly string HOST = "127.0.0.1";
        // Server Port
        private readonly int PORT = 3306;

        public void Connect()
        {
            try
            {
                IPAddress address = IPAddress.Parse(this.HOST);
                IPEndPoint ipEndPoint = new IPEndPoint(address, this.PORT);
                this.socket = new Socket(address.AddressFamily, SocketType.Stream, ProtocolType.Tcp);
                this.socket.BeginConnect((EndPoint)ipEndPoint, new AsyncCallback(this.ConnectCallback), (object)this.socket);
                Client.connectDone.WaitOne();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }

        private void ConnectCallback(IAsyncResult ar)
        {
            try
            {
                Socket asyncState = (Socket)ar.AsyncState;
                asyncState.EndConnect(ar);
                Console.WriteLine("Socket connected to {0}", (object)asyncState.RemoteEndPoint.ToString());
                Client.connectDone.Set();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }

        public void Send(OutPacket outPacket)
        {
            int len = outPacket.len;
            byte[] numArray = new byte[4]
            {
        (byte) 0,
        (byte) 0,
        (byte) 0,
        (byte) len
            };
            numArray[2] = (byte)(len >> 8);
            numArray[1] = (byte)(len >> 16);
            numArray[0] = (byte)(len >> 24);
            byte[] buffer = new byte[4 + len];
            for (int index = 0; index < numArray.Length; ++index)
                buffer[index] = numArray[index];
            for (int length = numArray.Length; length < len + 4; ++length)
                buffer[length] = outPacket.buf[length - 4];
            this.socket.BeginSend(buffer, 0, buffer.Length, SocketFlags.None, new AsyncCallback(this.SendCallback), (object)this.socket);
            Client.sendDone.WaitOne();
        }

        private void SendCallback(IAsyncResult ar)
        {
            try
            {
                Console.WriteLine("Sent {0} bytes to server.", (object)((Socket)ar.AsyncState).EndSend(ar));
                Client.sendDone.Set();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }

        public InPacket Receive()
        {
            try
            {
                InPacket inPacket = new InPacket();
                this.socket.BeginReceive(inPacket.buf, 0, 256, SocketFlags.None, new AsyncCallback(this.ReceiveCallback), (object)inPacket);
                while (inPacket.len == -1)
                    Client.receiveDone.WaitOne();
                return inPacket;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
            return (InPacket)null;
        }

        private void ReceiveCallback(IAsyncResult ar)
        {
            try
            {
                InPacket asyncState = (InPacket)ar.AsyncState;
                int num = this.socket.EndReceive(ar);
                if (asyncState.len == -1)
                {
                    if (num + asyncState.lenBufPtr >= 4)
                    {
                        for (int index = 0; index < 4 - asyncState.lenBufPtr; ++index)
                            asyncState.lenBuf[index - asyncState.lenBufPtr] = asyncState.buf[index];
                        asyncState.len = (int)asyncState.buf[3] + ((int)asyncState.buf[2] << 8) + ((int)asyncState.buf[1] << 16) + ((int)asyncState.buf[0] << 24);
                    }
                    else
                    {
                        for (int index = 0; index < num; ++index)
                            asyncState.lenBuf[index + asyncState.lenBufPtr] = asyncState.buf[index];
                        asyncState.lenBufPtr += num;
                    }
                }
                if (num > 0)
                {
                    for (int index = 0; index < num; ++index)
                        asyncState.bufData[index + asyncState.curLen] = asyncState.buf[index];
                    asyncState.curLen += num;
                    if (asyncState.curLen < asyncState.len)
                        this.socket.BeginReceive(asyncState.buf, 0, 256, SocketFlags.None, new AsyncCallback(this.ReceiveCallback), (object)asyncState);
                    else
                        Client.receiveDone.Set();
                }
                else
                    Client.receiveDone.Set();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.ToString());
            }
        }
    }
}
