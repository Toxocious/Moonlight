package net.swordie.ms.connection.crypto;

import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;
import net.swordie.ms.handlers.header.InHeader;
import net.swordie.ms.util.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 * @author PacketBakery
 */
public class TripleDESCipher {

    private byte[] rawKey = new byte[24];
    private Key key;
    private static Map<Short, Short> encryptedHeaderToNormalHeaders = new HashMap<>();

    public TripleDESCipher(byte[] key) {
        System.arraycopy(key, 0, this.rawKey, 0, key.length);
        this.key = new SecretKeySpec(key, "DESede");
    }

    public byte[] encrypt(byte[] data) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, this.key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher;
        cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        return cipher.doFinal(data);
    }

    public byte[] getRawKey() {
        return rawKey;
    }

    public void setRawKey(byte[] key) {
        System.arraycopy(key, 0, this.rawKey, 0, key.length);
        this.key = new SecretKeySpec(key, "DESede");
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public static byte[] test(int charID) {
        byte[] key = new byte[24];
        String charIDString = String.valueOf(charID);
        int len = charIDString.length();
        for (int i = 0; i < len; i++) {
            key[i] = (byte) charIDString.charAt(i);
        }
        byte[] machineID = Util.getByteArrayByString("D8 CB 8A C1 D2 E6 83 97 33 2C 00 00 00 00 41 AE");
        for (int i = len; i < 16; i++) {
            key[i] = machineID[i - len];
        }
        System.arraycopy(key, 0, key, 16, 8);
        System.out.println("Key is " + Util.readableByteArray(key));
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
//        System.out.println("Content             : " + new String(content.toString().getBytes()));
//        System.out.println("Content byte arr    : " + Util.readableByteArray(content.toString().getBytes()));
        byte[] buf = new byte[Short.MAX_VALUE + 1];
        byte[] encryptedBuf = cipher.encrypt(content.toString().getBytes());
        System.arraycopy(encryptedBuf, 0, buf, 0,encryptedBuf.length);
//        System.out.println("Encrypted content   : " + new String(encryptedBuf));
//        System.out.println("Encrypted content ar: " + Util.readableByteArray(encryptedBuf));
        Random random = new Random();
        for (int i = encryptedBuf.length; i < buf.length; i++) {
            buf[i] = (byte) random.nextInt();
        }
        return buf;
    }

    public static void main(String[] args) {
        byte[] buf = test(3996506);
//        System.out.println("Encrypted total buf : " + new String(buf));
//        System.out.println("Encrypted total bufa: " + Util.readableByteArray(buf));
        byte[] cyp = Util.getByteArrayByString("6D 0F CE 7F 9A C4 D3 C0 4C BF 3C D6 6C 78 C5 E7");
        byte[] msg = new byte[]{48,49,50,51,52,53,55,56};
//        byte[] key = Util.getByteArrayByString("33 39 39 36 35 30 36 D8 CB 8A C1 D2 E6 83 97 33 33 39 39 36 35 30 36 D8");
        byte[] key = Util.getByteArrayByString("33 39 39 36 35 30 36 D8 CB 8A C1 D2 E6 83 97 33");
        List<Byte> key2 = new ArrayList<>();
        for (byte b : key) {
            key2.add(b);
        }
//        Set<List<Byte>> lala = permute(key, 0);
        Collection<List<Byte>> perms = Collections2.permutations(key2);
        System.out.println("Done with combinations!");
        System.out.println("Size = " + perms.size());
        for (List<Byte> l : perms) {
            byte[] aKey = new byte[l.size()];
            byte[] aKey2 = new byte[l.size() + 8];
            for (int i = 0; i < aKey.length; i++) {
                aKey[i] = l.get(i);
            }
            System.arraycopy(aKey, 0, aKey2, 0, aKey.length);
            System.arraycopy(aKey, 0, aKey2, aKey.length, 8);
            TripleDESCipher tdc = new TripleDESCipher(aKey2);
            byte[] dec;
            try {
                dec = tdc.decrypt(cyp);
            } catch (Exception e1) {
                continue;
            }
            if (isNumber(dec)) {
                System.out.println("Possible key: " + Util.readableByteArray(aKey2));
                System.out.println("Decrypted text is " + new String(dec));
            }
        }
//        System.out.println(lala.size());
//        TripleDESCipher cipher = new TripleDESCipher(key);

////        System.out.println(Util.readableByteArray(cipher.encrypt(m)));
//        byte[] cadad = cipher.encrypt(msg);
//        System.out.println(new String(cadad));
//        System.out.println("c: " + Util.readableByteArray(cyp) + ", string " + new String(cyp));
////        System.out.println("m to c: " + new String(cipher.decrypt(cipher.encrypt(m))));
//        byte[] dec = cipher.decrypt(cyp);
//        System.out.println("dec: " + Util.readableByteArray(dec) + ", string " + new String(dec));

    }

    public static boolean isNumber(byte[] arr) {
        for (byte b : arr) {
            if (b < 48 || b > 57) {
                return false;
            }
        }
        return true;
    }

}
