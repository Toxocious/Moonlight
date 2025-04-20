package net.swordie.ms.connection;

import io.netty.buffer.*;
import net.swordie.ms.util.Position;
import net.swordie.ms.util.Rect;
import net.swordie.ms.util.Util;

import java.util.Arrays;

/**
 * Created on 2/18/2017.
 */
public class InPacket extends Packet {
    private final ByteBuf byteBuf;
    private boolean loopback;
    private short packetID;

    /**
     * Creates a new InPacket with a given buffer.
     * @param byteBuf The buffer this InPacket has to be initialized with.
     */
    public InPacket(ByteBuf byteBuf) {
        super(byteBuf.array());
        this.byteBuf = byteBuf.copy();
    }

    /**
     * Creates a new InPacket with no data.
     */
    public InPacket(){
        this(Unpooled.buffer());
    }

    /**
     * Creates a new InPacket with given data.
     * @param data The data this InPacket has to be initialized with.
     */
    public InPacket(byte[] data) {
        this(Unpooled.copiedBuffer(data));
    }

    @Override
    public int getLength() {
        return byteBuf.readableBytes();
    }

    @Override
    public byte[] getData() {
        return byteBuf.array();
    }

    @Override
    public InPacket clone() {
        return new InPacket(byteBuf);
    }

    /**
     * Reads a single byte of the ByteBuf.
     * @return The byte that has been read.
     */
    public byte decodeByte() {
        return byteBuf.readByte();
    }

    public short decodeUByte() {
            return byteBuf.readUnsignedByte();
    }

    /**
     * Reads an <code>amount</code> of bytes from the ByteBuf.
     * @param amount The amount of bytes to read.
     * @return The bytes that have been read.
     */
    public byte[] decodeArr(int amount) {
        byte[] arr = new byte[amount];
        for(int i = 0; i < amount; i++) {
            arr[i] = byteBuf.readByte();
        }
        return arr;
    }

    /**
     * Reads an integer from the ByteBuf.
     * @return The integer that has been read.
     */
    public int decodeInt() {
        return byteBuf.readIntLE();
    }

    /**
     * Reads a short from the ByteBuf.
     * @return The short that has been read.
     */
    public short decodeShort() {
        return byteBuf.readShortLE();
    }

    /**
     * Reads a char array of a given length of this ByteBuf.
     * @param amount The length of the char array
     * @return The char array as a String
     */
    public String decodeString(int amount) {
        byte[] bytes = decodeArr(amount);
        char[] chars = new char[amount];
        for(int i = 0; i < amount; i++) {
            chars[i] = (char) bytes[i];
        }
        return String.valueOf(chars);
    }

    /**
     * Reads a String, by first reading a short, then reading a char array of that length.
     * @return The char array as a String
     */
    public String decodeString() {
        int amount = decodeShort();
        return decodeString(amount);
    }

    @Override
    public String toString() {
        return Util.readableByteArray(Arrays.copyOfRange(getData(), getData().length - getUnreadAmount(), getData().length)); // Substring after copy of range xd
    }


    /**
     * Reads and returns a long from this net.swordie.ms.connection.packet.
     * @return The long that has been read.
     */
    public long decodeLong() {
        return byteBuf.readLongLE();
    }

    /**
     * Reads a position (short x, short y) and returns this.
     * @return The position that has been read.
     */
    public Position decodePosition() {
        return new Position(decodeShort(), decodeShort());
    }

    /**
     * Reads a position (int x, int y) and returns this.
     * @return The position that has been read.
     */
    public Position decodePositionInt() {
        return new Position(decodeInt(), decodeInt());
    }

    /**
     * Reads a rectangle (short l, short t, short r, short b) and returns this.
     * @return The rectangle that has been read.
     */
    public Rect decodeShortRect() {
        return new Rect(decodePosition(), decodePosition());
    }

    /**
     * Reads a rectangle (int l, int t, int r, int b) and returns this.
     * @return The rectangle that has been read.
     */
    public Rect decodeIntRect() {
        return new Rect(decodePositionInt(), decodePositionInt());
    }


    /**
     * Returns the amount of bytes that are unread.
     * @return The amount of bytes that are unread.
     */
    public int getUnreadAmount() {
        return byteBuf.readableBytes();
    }

    public void release() {
        byteBuf.release();
    }

    public boolean isLoopback() {
        return loopback;
    }

    public void setLoopback(boolean loopback) {
        this.loopback = loopback;
    }

    public short getPacketID() {
        return packetID;
    }
}
