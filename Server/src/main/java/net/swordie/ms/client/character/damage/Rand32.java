package net.swordie.ms.client.character.damage;

/**
 * Class for managing the randomness used in calculations such as damage, miss chance, etc..
 * http://forum.ragezone.com/f427/implement-damage-calculation-damage-check-1080336/
 */
public class Rand32 {
    private long seed1, seed2, seed3;
    private long oldSeed1, oldSeed2, oldSeed3;


    public Rand32() {
    }

    public long getSeed1() {
        return seed1;
    }

    public long getSeed2() {
        return seed2;
    }

    public long getSeed3() {
        return seed3;
    }

    public long random() {
       long seed1 = getSeed1();
       long seed2 = getSeed2();
       long seed3 = getSeed3();

        this.oldSeed1 = seed1;
        this.oldSeed2 = seed2;
        this.oldSeed3 = seed3;

        long newSeed1 = (seed1 << 12) ^ (seed1 >>> 19) ^ ((seed1 >>> 6) ^ (seed1 << 12)) & 0x1FFF;
        long newSeed2 = 16 * seed2 ^ (seed2 >>> 25) ^ ((16 * seed2) ^ (seed2 >>> 23)) & 0x7F;
        long newSeed3 = (seed3 >>> 11) ^ (seed3 << 17) ^ ((seed3 >>> 8) ^ (seed3 << 17)) & 0x1FFFFF;

        this.seed1 = newSeed1 & 0xFFFFFFFFL;
        this.seed2 = newSeed2 & 0xFFFFFFFFL;
        this.seed3 = newSeed3 & 0xFFFFFFFFL;
        return (newSeed1 ^ newSeed2 ^ newSeed3) & 0xFFFFFFFFL;
    }

    public float randomFloat() {
        return (0x7fffff & random() | 0x3F800000) - 1f;
    }

    public void seed(int s1, int s2, int s3) {
        this.seed1 = ((long) s1 & 0xFFFFFFFFL) | 0x100000;
        this.oldSeed1 = ((long) s1 & 0xFFFFFFFFL) | 0x100000;

        this.seed2 = ((long) s2 & 0xFFFFFFFFL) | 0x1000;
        this.oldSeed2 = ((long) s2 & 0xFFFFFFFFL) | 0x1000;

        this.seed3 = ((long) s3 & 0xFFFFFFFFL) | 0x10;
        this.oldSeed3 = ((long) s3 & 0xFFFFFFFFL) | 0x10;
    }

    public void setRawSeed(long s1, long s2, long s3) {

        this.seed1 = s1;
        this.oldSeed1 = s1;

        this.seed2 = s2;
        this.oldSeed2 = s2;

        this.seed3 = s3;
        this.oldSeed3 = s3;
    }

    public void rollBack() {
        seed1 = oldSeed1;
        seed2 = oldSeed2;
        seed3 = oldSeed3;
    }
}
