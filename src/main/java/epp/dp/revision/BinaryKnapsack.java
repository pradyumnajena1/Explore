package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryKnapsack {
    public static void main(String[] args) {
        int[] cost = ArrayUtils.randomArray(5, 10, 50);
        int[] weight = ArrayUtils.randomArray(5, 10, 50);
        ArrayUtils.printArray(cost);
        ArrayUtils.printArray(weight);
        Pair<List<Integer>,Integer> maxValue = getMaxValue(cost, weight, 100);
        System.out.println(maxValue);
    }

    private static Pair<List<Integer>,Integer> getMaxValue(int[] cost, int[] weight, int capacity) {
        Map<Pair<Integer, Integer>, Pair<List<Integer>,Integer>> cache = new HashMap<>();
        return getMaxValue(cost, weight, capacity, 0, cache);
    }

    private static Pair<List<Integer>,Integer> getMaxValue(int[] cost, int[] weight, int capacity, int index,
                                                     Map<Pair<Integer,
            Integer>,
                                                             Pair<List<Integer>,Integer>> cache) {
        if (index == cost.length || capacity == 0) {
            return new Pair<>(new ArrayList<>(),0);
        }
        Pair<Integer, Integer> key = new Pair<>(capacity, index);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Pair<List<Integer>, Integer> notIncluding = getMaxValue(cost, weight, capacity, index + 1, cache);
        Pair<List<Integer>, Integer> including = null;
        if (capacity >= weight[index]) {
            Pair<List<Integer>, Integer> partial = getMaxValue(cost, weight, capacity - weight[index], index + 1, cache);
            ArrayList<Integer> selected = new ArrayList<>( );
            selected.add(index);
            selected.addAll(partial.getFirst());
            including =new Pair<>(selected,partial.getSecond()+cost[index]);
        }
        Pair<List<Integer>, Integer> result =  notIncluding;
        if(including!=null && including.getSecond()>notIncluding.getSecond()){
            result = including;
        }
        cache.put(key, result);
        return result;
    }
}
