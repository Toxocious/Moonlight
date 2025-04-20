package net.swordie.ms.client;

import net.swordie.ms.connection.InPacket;
import net.swordie.ms.connection.crypto.TripleDESCipher;
import net.swordie.ms.enums.AccountType;
import net.swordie.ms.handlers.ClientSocket;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.util.FileoutputUtil;
import net.swordie.ms.util.Util;
import net.swordie.ms.world.Channel;
import net.swordie.ms.client.character.Char;
import net.swordie.ms.connection.OutPacket;
import net.swordie.ms.connection.netty.NettyClient;
import net.swordie.ms.connection.packet.Login;
import net.swordie.ms.Server;
import net.swordie.ms.world.World;

import java.util.*;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tim on 2/18/2017.
 */
public class Client extends NettyClient {
    private Char chr;
    private final Lock lock;
    private Account account;
    private User user;
    private byte channel;
    private byte worldId;
    private boolean authorized;
    private Channel channelInstance;
    private byte[] machineID;
    private final Map<Short, Short> encryptedHeaderToNormalHeaders = new HashMap<>();
    private byte oldChannel;
    private long ping;
    private long lastPingTime;
    private boolean waitingForAliveAck;

    private int lastTick = 0;
    private int tickCount = 0;
    public int clientPacketSpamCount = 0;

    public Client(io.netty.channel.Channel channel, byte[] sendSeq, byte[] recvSeq) {
        super(channel, sendSeq, recvSeq);
        lock = new ReentrantLock(true);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Lock getLock() {
        return lock;
    }

    public void write(byte[] data) {
        write(new OutPacket(data));
    }

    public void sendPing() {
        lastPingTime = System.currentTimeMillis();
        waitingForAliveAck = true;
        write(Login.sendAliveReq());
    }

    public void setPing(long ping) {
        this.ping = ping;
    }

    public long getPing() {
        return ping;
    }

    public long getLastPingTime() {
        return lastPingTime;
    }

    public boolean isWaitingForAliveAck() {
        return waitingForAliveAck;
    }

    public Account getAccount() {
        return account;
    }

    public void setChannel(byte channel) {
        this.channel = channel;
    }

    public byte getChannel() {
        return channel;
    }

    public byte getWorldId() {
        return worldId;
    }

    public void setWorldId(byte worldId) {
        this.worldId = worldId;
    }

    public Char getChr() {
        return chr;
    }

    public void setChr(Char chr) {
        this.chr = chr;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setChannelInstance(Channel channelInstance) {
        this.channelInstance = channelInstance;
    }

    public Channel getChannelInstance() {
        return channelInstance;
    }

    public World getWorld() {
        return Server.getInstance().getWorldById(getWorldId());
    }

    public boolean hasCorrectMachineID(byte[] machineID) {
        return Arrays.equals(machineID, getMachineID());
    }

    public void setMachineID(byte[] machineID) {
        this.machineID = machineID;
    }

    public byte[] getMachineID() {
        return machineID;
    }

    public void sendOpcodeEncryption(int charID) {
        byte[] key = new byte[24];
        System.arraycopy("M@PleStoryMaPLe!".getBytes(), 0, key, 0, 16);
        System.arraycopy(key, 0, key, 16, 8);
        TripleDESCipher cipher = new TripleDESCipher(key);
        StringBuilder content = new StringBuilder();
        List<Integer> possibleNums = new ArrayList<>();
        for (int i = InHeader.B_E_G_I_N__U_S_E_R.getValue(); i < 9999; i++) {
            possibleNums.add(i);
        }
        for (short header = InHeader.B_E_G_I_N__U_S_E_R.getValue(); header < InHeader.NO.getValue(); header++) {
            int randNum = Util.getRandomFromCollection(possibleNums);
            possibleNums.remove((Integer) randNum);
            String num = String.format("%04d", randNum);
            encryptedHeaderToNormalHeaders.put((short) randNum, header);
            content.append(num);
        }
        content.append(" ");
        byte[] buf = new byte[Short.MAX_VALUE + 1];
        byte[] encryptedBuf = cipher.encrypt(content.toString().getBytes());
        System.arraycopy(encryptedBuf, 0, buf, 0, encryptedBuf.length);
        Random random = new Random();
        for (int i = encryptedBuf.length; i < buf.length; i++) {
            buf[i] = (byte) random.nextInt();
        }
        write(ClientSocket.opcodeEncryption(4, buf));
    }

    public Map<Short, Short> getEncryptedHeaderToNormalHeaders() {
        return encryptedHeaderToNormalHeaders;
    }

    public void setOldChannel(byte oldChannel) {
        this.oldChannel = oldChannel;
    }

    public byte getOldChannel() {
        return oldChannel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void verifyTick(InPacket inPacket) {
        int tick = inPacket.decodeInt();
        InHeader inHeader;
        if (tick <= lastTick) {
            if (getChr() != null && getUser().getAccountType() != AccountType.Admin
                    && tickCount >= 5) {
                //  setWaitingToDisconnect(true);
                inHeader = InHeader.getInHeaderByOp(inPacket.getPacketID());
                FileoutputUtil.spamLog("PacketTickLog.txt", this, inHeader, inPacket);
            } else {
                tickCount++;
            }
        } else {
            tickCount = 0;
        }
        lastTick = tick;
    }
}
