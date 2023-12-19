package epp.dp;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PickupCoins {
    public static void main(String[] args) {
        int[] coins = ArrayUtils.randomArray(40,1,6);
        for(int i=0;i<coins.length;i++){
            coins[i] *= 5;
        }
        ArrayUtils.printArray(coins);
        int odd = getMaxDiff(coins);
        System.out.println(odd);


    }
    private static class Key{
        int start;
        int end;

        public Key(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return start == key.start && end == key.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
    private static int getMaxDiff(int[] coins){
        Map<Key,Integer> cache = new HashMap<>();
        return getMaxDiff(coins,0,coins.length-1,cache);
    }

    private static int getMaxDiff(int[] coins, int start, int end,Map<Key,Integer> cache) {
        if(start>end){
            return 0;
        }
        Key key = new Key(start, end);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        int maxDiff = Math.max(Math.max((coins[start] - coins[end]) + getMaxDiff(coins, start + 1, end - 1,cache),
                        (coins[start] - coins[start + 1]) + getMaxDiff(coins, start + 2, end,cache)),
                Math.max((coins[end] - coins[start]) + getMaxDiff(coins, start + 1, end - 1,cache),
                        (coins[end] - coins[end - 1]) + getMaxDiff(coins, start, end - 2,cache)));
        cache.put(key,maxDiff);
        return maxDiff;
    }
}
