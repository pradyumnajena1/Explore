package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonotonicInteger {
    public static void main(String[] args){
        int numInts = countNumMonotonicIntegers(2,false);
    System.out.println(numInts);
    }

    private static int countNumMonotonicIntegers(int k,boolean strict) {
        Map<List<Integer>,Integer> cache = new HashMap<>();
        return countNumMonotonicIntegers(1,k,strict, cache);
    }

    private static int countNumMonotonicIntegers(int start, int k, boolean strict, Map<List<Integer>,Integer> cache) {

        if(k==0){
            return 1;
        }

        List<Integer> key = List.of(start, k);
        if(!cache.containsKey(key)){
            int count = 0;
            for(int i= start ;i<=9;i++){
                count+= countNumMonotonicIntegers( strict?i+1: i,k-1, strict, cache);
            }
            cache.put(key, count);
        }

        return cache.get(key);
    }
}
