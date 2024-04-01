package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class RollingHash {

    private static final int FACTOR = 3;
    private static final int MOD = (int) (Math.pow(10, 8) + 7);
    private final int[] powers;
    private final int[] hashes;


    public RollingHash(String s ) {

        this.powers = new int[s.length()];
        this.hashes = new int[s.length()];
        powers[0] = 1;
        hashes[0] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            powers[i] = (powers[i - 1] * FACTOR) % MOD;
            hashes[i] = (hashes[i - 1] * FACTOR) % MOD + s.charAt(i);
        }
    }

    public static int getHash(String s){
        int  hash = s.charAt(0);
        int power = 1;
        for (int i = 1; i < s.length(); i++) {
            hash = (hash * FACTOR) % MOD + s.charAt(i);
            power = (power  * FACTOR) % MOD;
        }
        return hash;
    }

   public int getHash(int start, int end) {
        ArrayUtils.rangeCheck(hashes.length,start,end);
        return (hashes[end] - (start-1>=0? (hashes[start-1]*powers[end-start+1])%MOD :0)) %MOD;
    }

    public static void main(String[] args) {
        RollingHash rollingHash = new RollingHash("hello");
        System.out.println(rollingHash.getHash(0, 2));
        System.out.println(rollingHash.getHash(1, 3));
        System.out.println(rollingHash.getHash(2, 4));
        System.out.println(rollingHash.getHash(3, 5));
    }

}
