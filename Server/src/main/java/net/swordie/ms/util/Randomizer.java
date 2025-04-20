package net.swordie.ms.util;

import java.security.SecureRandom;
import java.util.Random;

public class Randomizer {

    private static final Random rand = new Random();

    public static final int nextInt() {
        return rand.nextInt();
    }

    public static final int nextInt(final int arg0) {
        return rand.nextInt(arg0);
    }

    public static final void nextBytes(final byte[] bytes) {
        rand.nextBytes(bytes);
    }

    public static final boolean nextBoolean() {
        return rand.nextBoolean();
    }

    public static final double nextDouble() {
        return rand.nextDouble();
    }

    public static final float nextFloat() {
        return rand.nextFloat();
    }

    public static final long nextLong() {
        return rand.nextLong();
    }

    public static final int rand(final int lbound, final int ubound) {
        return nextInt(ubound - lbound + 1) + lbound;
    }

    public static boolean isSuccess(int rate) {
        return rate > nextInt(100);
    }

    public static int secureRandomInt(int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max);
    }

    /**
     * Creates a random "unsigned" int. Does this by creating a random long, then casting that to an int.
     * @return
     */
    public static int secureRandomInt() {
        return (int) new SecureRandom().nextLong();
    }
}
