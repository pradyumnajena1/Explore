package epp.dp.revision;

import epp.Multiset;
import epp.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoinSumWithFiniteSupply {
    public static void main(String[] args) {
        int[] coins = {2,2,3,3,3,7,7,7,7};
        int sum = 31;
      Set<Multiset<Integer>>  result = getCombinations(coins,sum);
        System.out.println(result);
    }

    private static Set<Multiset<Integer>> getCombinations(int[] coins, int sum) {
        Map<Pair<Integer,Integer> ,Set<Multiset<Integer>>> cache = new HashMap<>();
        return getCombinations(coins,0,sum, cache);
    }

    private static Set<Multiset<Integer>> getCombinations(int[] coins, int pos, int sum, Map<Pair<Integer, Integer>, Set<Multiset<Integer>>> cache) {
        if(sum==0){
            HashSet<Multiset<Integer>> result = new HashSet<>();
            result.add(new Multiset<>());
            return result;
        }
        if(pos==coins.length || sum<coins[pos]){
            return null;
        }
        Pair<Integer, Integer> key = new Pair<>(sum, pos);
        if(cache.containsKey(key)){
             return cache.get(key);
         }
        Set<Multiset<Integer>> notIncluding = getCombinations(coins, pos + 1, sum, cache);
        Set<Multiset<Integer>> including = getCombinations(coins, pos + 1, sum-coins[pos], cache);
        Set<Multiset<Integer>> result = new HashSet<>();
        if(notIncluding!=null){
            for(Multiset<Integer> combination:notIncluding){
                Multiset<Integer> newCombo = new Multiset<>(combination);
                result.add(newCombo);
            }
        }
        if(including!=null){
            for(Multiset<Integer> combination:including){
                Multiset<Integer> newCombo = new Multiset<>(combination);
                newCombo.add(coins[pos]);
                result.add(newCombo);
            }
        }
        cache.put(key,result);
        return result;
    }
}
