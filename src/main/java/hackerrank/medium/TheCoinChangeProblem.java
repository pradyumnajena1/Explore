package hackerrank.medium;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheCoinChangeProblem {
    public static void main(String[] args) {
        System.out.println(getWays(4, new ArrayList<Long>(List.of(1l, 2l, 3l))));
        System.out.println(getWays(10, new ArrayList<Long>(List.of(2l,5l,3l,6l))));
    }

    public static long getWays(int n, List<Long> c) {
        // Write your code here
        Map<Pair<Integer,Integer> ,Long> cache = new HashMap<>();
        return getWaysHelper(n, c ,0,cache);

    }

    private static long getWaysHelper(int n, List<Long> c, int index, Map<Pair<Integer, Integer>, Long> cache) {
        if (n < 0 || index==c.size()) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        Pair<Integer, Integer> key = new Pair<>(n, index);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        long including = getWaysHelper((int) (n - c.get(index)), c,index, cache);
        long excluding = getWaysHelper(n, c ,index+1, cache);
        long total = including + excluding;
        cache.put(key,total);
        return total;
    }
}
