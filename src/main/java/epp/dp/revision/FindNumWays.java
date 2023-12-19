package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * in a board game you can make 1,2...k max steps , need to reach n ,compute numways
 */
public class FindNumWays {
    public static void main(String[] args) {
      int numWays =   findNumWays(4,2);
        System.out.println(numWays);
    }

    private static int findNumWays(int n, int k) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return findNumWays2(n, k,cache);
    }

    private static int findNumWays2(int n, int k, Map<Pair<Integer, Integer>, Integer> cache) {
        System.out.println("n = " + n + ", k = " + k);
        if(n <=1){
            return 1;
        }
        Pair<Integer, Integer> key = new Pair<>(n, k);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        int numWays = 0;
        for(int i = 1; i<= k; i++){
            numWays+= findNumWays2(n -i, k,cache);
        }
        cache.put(key,numWays);
        return numWays;
    }
}
