/*
    This file is part of Desu: MapleStory v62 Server Emulator
    Copyright (C) 2014  Zygon <watchmystarz@hotmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.swordie.ms.connection.crypto;

/**
 * Fragment borrowed from some remote site for AES cryptography.
 * Author is unknown at the moment, was an artifact used in Invictus that
 * has made its way into this engine as well.  Not well documented since
 * getting into the math with regards to how AES functions is not something
 * easily written here.
 */
public final class AES {

    public static final int ROUNDS = 14,
            BLOCK_SIZE = 16,
            KEY_LENGTH = 32;
    int numRounds;
    byte[][] Ke;
    byte[][] Kd;  
    byte[] Ker;
    byte[] ta;
    static final byte[] S = {
        99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118,
        -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64,
        -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21,
        4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117,
        9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124,
        83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49,
        -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88,
        81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46,
        -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115,
        96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37,
        -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121,
        -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8,
        -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118,
        112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98,
        -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33,
        -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    static final byte[] Si = {
        82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5,
        124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53,
        84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78,
        8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37,
        114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110,
        108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124,
        -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6,
        -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107,
        58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115,
        -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110,
        71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27,
        -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12,
        31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95,
        96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17,
        -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97,
        23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    static final int[] rcon = {
        0,
        1, 2, 4, 8, 16, 32,
        64, -128, 27, 54, 108, -40,
        -85, 77, -102, 47, 94, -68,
        99, -58, -105, 53, 106, -44,
        -77, 125, -6, -17, -59, -111};
    public static final int COL_SIZE = 4,
            NUM_COLS = BLOCK_SIZE / COL_SIZE,
            ROOT = 0x11B;
    static final int[] row_shift = {0, 1, 2, 3};
    static final int[] alog = new int[256];
    static final int[] log = new int[256];

    static {
        int i, j;
        alog[0] = 1;
        for (i = 1; i < 256; i++) {
            j = (alog[i - 1] << 1) ^ alog[i - 1];
            if ((j & 0x100) != 0) {
                j ^= ROOT;
            }
            alog[i] = j;
        }
        for (i = 1; i < 255; i++) {
            log[alog[i]] = i;
        }
    }

    public AES() {
    }

    public static int getRounds(int keySize) {
        switch (keySize) {
            case 16:
                return 10;
            case 24:
                return 12;
            default:
                return 14;
        }
    }

    static int mul(int a, int b) {
        return (a != 0 && b != 0)
                ? alog[(log[a & 0xFF] + log[b & 0xFF]) % 255]
                : 0;
    }
    
    public byte[] encrypt(byte[] a) {
        ta = new byte[BLOCK_SIZE];
        int i, k, row, col;
        if (a == null) {
            throw new IllegalArgumentException("Empty data to be processed.");
        }
        if (a.length != BLOCK_SIZE) {
            throw new IllegalArgumentException("Incorrect input length.");
        }
        Ker = Ke[0];
        for (i = 0; i < BLOCK_SIZE; i++) {
            a[i] = (byte) (a[i] ^ Ker[i]);
        }
        for (int r = 1; r < numRounds; r++) {
            Ker = Ke[r];
            for (i = 0; i < BLOCK_SIZE; i++) {
                ta[i] = S[a[i] & 0xFF];
            }
            for (i = 0; i < BLOCK_SIZE; i++) {
                row = i % COL_SIZE;
                k = (i + (row_shift[row] * COL_SIZE)) % BLOCK_SIZE;
                a[i] = ta[k];
            }
            for (col = 0; col < NUM_COLS; col++) {
                i = col * COL_SIZE;
                ta[i] = (byte) (mul(2, a[i]) ^ mul(3, a[i + 1]) ^ a[i + 2] ^ a[i + 3]);
                ta[i + 1] = (byte) (a[i] ^ mul(2, a[i + 1]) ^ mul(3, a[i + 2]) ^ a[i + 3]);
                ta[i + 2] = (byte) (a[i] ^ a[i + 1] ^ mul(2, a[i + 2]) ^ mul(3, a[i + 3]));
                ta[i + 3] = (byte) (mul(3, a[i]) ^ a[i + 1] ^ a[i + 2] ^ mul(2, a[i + 3]));
            }
            for (i = 0; i < BLOCK_SIZE; i++) {
                a[i] = (byte) (ta[i] ^ Ker[i]);
            }
        }
        Ker = Ke[numRounds];
        for (i = 0; i < BLOCK_SIZE; i++) {
            a[i] = S[a[i] & 0xFF];
        }
        for (i = 0; i < BLOCK_SIZE; i++) {
            row = i % COL_SIZE;
            k = (i + (row_shift[row] * COL_SIZE)) % BLOCK_SIZE;
            ta[i] = a[k];
        }
        for (i = 0; i < BLOCK_SIZE; i++) {
            a[i] = (byte) (ta[i] ^ Ker[i]);
        }
        ta = null;
        Ker = null;
        return a;
    }

    public void setKey(byte[] key) {
        final int BC = BLOCK_SIZE / 4;
        final int Klen = key.length;
        final int Nk = Klen / 4;
        int i, j, r;
        if (key == null) {
            throw new IllegalArgumentException("Empty key");
        }
        if (!(key.length == 16 || key.length == 24 || key.length == 32)) {
            throw new IllegalArgumentException("Incorrect key length");
        }
        numRounds = getRounds(Klen);
        final int ROUND_KEY_COUNT = (numRounds + 1) * BC;
        byte[] w0 = new byte[ROUND_KEY_COUNT];
        byte[] w1 = new byte[ROUND_KEY_COUNT];
        byte[] w2 = new byte[ROUND_KEY_COUNT];
        byte[] w3 = new byte[ROUND_KEY_COUNT];
        Ke = new byte[numRounds + 1][BLOCK_SIZE];
        Kd = new byte[numRounds + 1][BLOCK_SIZE];
        for (i = 0, j = 0; i < Nk; i++) {
            w0[i] = key[j++];
            w1[i] = key[j++];
            w2[i] = key[j++];
            w3[i] = key[j++];
        }
        int t0, t1, t2, t3, old0;
        for (i = Nk; i < ROUND_KEY_COUNT; i++) {
            t0 = w0[i - 1];
            t1 = w1[i - 1];
            t2 = w2[i - 1];
            t3 = w3[i - 1];
            if (i % Nk == 0) {
                old0 = t0;
                t0 = (S[t1 & 0xFF] ^ rcon[i / Nk]);
                t1 = (S[t2 & 0xFF]);
                t2 = (S[t3 & 0xFF]);
                t3 = (S[old0 & 0xFF]);
            } else if ((Nk > 6) && (i % Nk == 4)) {
                t0 = S[t0 & 0xFF];
                t1 = S[t1 & 0xFF];
                t2 = S[t2 & 0xFF];
                t3 = S[t3 & 0xFF];
            }
            w0[i] = (byte) (w0[i - Nk] ^ t0);
            w1[i] = (byte) (w1[i - Nk] ^ t1);
            w2[i] = (byte) (w2[i - Nk] ^ t2);
            w3[i] = (byte) (w3[i - Nk] ^ t3);
        }
        for (r = 0, i = 0; r < numRounds + 1; r++) {
            for (j = 0; j < BC; j++) {
                Ke[r][4 * j] = w0[i];
                Ke[r][4 * j + 1] = w1[i];
                Ke[r][4 * j + 2] = w2[i];
                Ke[r][4 * j + 3] = w3[i];
                Kd[numRounds - r][4 * j] = w0[i];
                Kd[numRounds - r][4 * j + 1] = w1[i];
                Kd[numRounds - r][4 * j + 2] = w2[i];
                Kd[numRounds - r][4 * j + 3] = w3[i];
                i++;
            }
        }
    }
}
