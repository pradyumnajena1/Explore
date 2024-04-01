package amaze;

import java.util.HashMap;
import java.util.Map;

public class SticklerThief {
    public static void main(String[] args) {
        int[] values = new int[]{3, 2, 5, 10, 7};
        int maxSum = getMaxSum(values);
        System.out.println(maxSum);
    }

    private static int getMaxSum(int[] values) {
        Map<Integer,Integer> cache = new HashMap<>();
        return getMaxSum(values,0,cache);
    }

    private static int getMaxSum(int[] values, int i, Map<Integer, Integer> cache) {
        if(i>=values.length){
            return 0;
        }
        if(cache.containsKey(i)){
            return cache.get(i);
        }
        int max = Math.max(values[i] + getMaxSum(values, i + 2, cache), getMaxSum(values, i + 1, cache));
        cache.put(i,max);
        return max;
    }
}
