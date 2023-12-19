package epp.dp.revision;

import epp.Multiset;

import java.util.*;

public class CoinSumWithInfinitySupply {
    public static void main(String[] args) {


        System.out.println(getWays(new int[]{1, 2, 3}, 3));

    }

    private static Set<Multiset<Long>> getWays(List<Long>  coins, int score) {
        Map<Integer, Set<Multiset<Long>>> cache = new HashMap<>();
        coins.sort(Long::compareTo);
        return getCombinations(coins, score, cache);
    }

    private static Set<Multiset<Long>> getCombinations(List<Long> coins, int score,
                                                          Map<Integer, Set<Multiset<Long>>> cache) {
        if (score == 0) {
            Set<Multiset<Long>> result = new HashSet<>();
            result.add(new Multiset<>());
            return result;
        }
        if (score < coins.get(0)) {
            return null;
        }
        if (cache.containsKey(score)) {
            return cache.get(score);
        }
        Set<Multiset<Long>> result = new HashSet<>();
        for (int i = 0; i < coins.size() && score >= coins.get(i); i++) {
            Set<Multiset<Long>> combinations = getCombinations(coins, (int) (score - coins.get(i)), cache);
            if (combinations != null) {
                for (Multiset<Long> combination : combinations) {
                    Multiset<Long> newCombination = new Multiset<>(combination);
                    newCombination.add(coins.get(i));
                    result.add(newCombination);
                }
            }
        }
        cache.put(score, result);
        return result;
    }

    private static int getWays( int[]  coins, int sum) {
        Map<String,Integer> cache = new HashMap<>();
       return getWaysHelper(coins,sum,0,cache);
    }

    private static int getWaysHelper(int[] coins, int sum, int index, Map<String, Integer> cache) {
        if(sum==0){
            return 1;
        }
        if(index>=coins.length){
            return 0;
        }
        String key = sum + "_" + index;
        if(cache.containsKey(key)){
            cache.get(key);
        }
        int totalNumWays = 0;
        if(coins[index]<=sum){

            totalNumWays+= getWaysHelper(coins,sum-coins[index],index,cache);
        }
        totalNumWays+= getWaysHelper(coins,sum ,index+1,cache);
        cache.put(key,totalNumWays);
        return totalNumWays;
    }
}


