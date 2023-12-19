package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;

public class FrogJump {
    public static void main(String[] args) {
       Solution solution = new Solution();
        System.out.println(solution.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(solution.canCross(new int[]{0,1,2,3,4,8,9,11}));
    }
   private static class Solution {
        public boolean canCross(int[] stones) {
            HashMap<String,Boolean> cache = new HashMap<>();
           return canCross(stones,0,0,cache);
        }

       private boolean canCross(int[] stones, int currentPosition, int lastJump, HashMap<String, Boolean> cache) {
           System.out.println(stones[currentPosition]+" "+lastJump);
            if(currentPosition==stones.length-1){
                return true;
            }
           String key = currentPosition + "_" + lastJump;
           if(cache.containsKey(key)){
                return cache.get(key);
            }
           boolean result = false;
            for(int i=-1;i<=1;i++){
                int nextJump = lastJump+i;
                int nextPosition = Arrays.binarySearch(stones, stones[currentPosition] + nextJump);
                if(nextPosition > currentPosition && canCross(stones,nextPosition,nextJump, cache)){
                    result = true;
                    break;
                }
            }
            cache.put(key,result);
           return result;
       }
   }
}
