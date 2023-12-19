package epp.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinSum {
    public static void main(String[] args) {
        System.out.println(findNumWaysDriver( 3, Arrays.asList(2,3,7)));
    }

    static long findNumWaysDriver(int sum, List<Integer> denoms){
        Map<Integer,Long> cache = new HashMap<>();
        long numWays = findNumWays(sum, denoms, cache);
        System.out.println(cache);
        return numWays;

    }

    static long findNumWays(int sum, List<Integer> denoms,Map<Integer,Long> cache){
        if(sum<0) return 0;
        if(sum==0) return 1;

        if(cache.containsKey(sum)){
            return cache.get(sum);
        }
        long count=0;
        for(Integer denom:denoms){
            count+=findNumWays(sum-denom,denoms,cache);
        }
        cache.put(sum,count);
        return count;
    }
}
