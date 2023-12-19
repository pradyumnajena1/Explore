package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class CriticalHeight {
    public static void main(String[] args) {
        int maxHeight = getMaxHeight(3,2);
        System.out.println(maxHeight);
    }

    private static int getMaxHeight(int cases, int drops) {
        Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
        return getMaxHeightHelper(cases, drops,cache);
    }

    private static int getMaxHeightHelper(int cases, int drops, Map<Pair<Integer, Integer>, Integer> cache) {
        if(cases ==0|| drops ==0){
            return 0;
        }
        if(cases ==1){
            return drops;
        }
        Pair<Integer, Integer> key = new Pair<>(cases, drops);
        if(cache.containsKey(key)){
            return cache.get(key);
        }

        int result = getMaxHeight(cases - 1, drops - 1) + 1 + getMaxHeight(cases, drops - 1);
        cache.put(key,result);
        return result;
    }
}
