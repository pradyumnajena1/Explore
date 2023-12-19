package epp.dp;

import java.util.HashMap;
import java.util.Map;

public class BinomialCoeffs {
    public static void main(String[] args) {
        int value = getBinomialCoefs(4,2);
        System.out.println(value);
    }

    private static int getBinomialCoefs(int n, int k) {
        Map<String,Integer> cache = new HashMap<>();
        int value =  getBinomialCoefs(n,k,cache);
        return value;
    }

    private static int getBinomialCoefs(int n, int k, Map<String, Integer> cache) {
        if(n==0 ){
            return 0;
        }
        if(k==0 || k==n){
            return 1;
        }
        String key = n + "_" + k;
        //return cache.computeIfAbsent(key,x-> getBinomialCoefs(n-1,k,cache)+getBinomialCoefs(n-1,k-1,cache));
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int value = getBinomialCoefs(n-1,k,cache)+getBinomialCoefs(n-1,k-1,cache);
        cache.put(key,value);
        return value;
    }
}
