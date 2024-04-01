package amaze;

import java.util.HashMap;
import java.util.Map;

public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(getNumWays(14));
        System.out.println(getNumWays(30));
    }

    private static int getNumWays(int numSteps) {
        Map<Integer,Integer> cache = new HashMap<>();
        return getNumWays(1,numSteps,cache);
    }

    private static int getNumWays(int i, int numSteps, Map<Integer, Integer> cache) {
        if(i>numSteps){
            return 0;
        }
        if(i==numSteps){
            return 1;
        }
        if(cache.containsKey(i)){
            return cache.get(i);
        }
        int result = getNumWays(i + 1, numSteps, cache) + getNumWays(i + 2, numSteps, cache);
        cache.put(i,result);
        return result;
    }
}
