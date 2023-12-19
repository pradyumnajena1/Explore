package leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarrayswithKDifferentIntegers {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3));
        System.out.println(solution.subarraysWithKDistinct(new int[]{2,2,1,2,2,2,1,1}, 2));





    }

    private static class Solution {
        public int subarraysWithKDistinct(int[] nums, int k) {
            int count = 0;
            Map<Integer, List<Integer>> freqMap = new HashMap<>();
            int start = 0;
            int end = 0;
            while (end<nums.length){

                while (end < nums.length && (freqMap.size()<k || freqMap.containsKey(nums[end]))){
                    List<Integer> positions = freqMap.getOrDefault(nums[end], new ArrayList<>());
                    positions.add(end);
                    freqMap.put(nums[end], positions );
                    if(freqMap.size()==k){
                        System.out.println(freqMap);
                        int length = end - start + 1;
                        int min = getMin(freqMap);
                        int requiredLength = end - min + 1;
                        count+= length-requiredLength +1;
                        //System.out.println("added "+(length-requiredLength +1));
                    }
                    end++;
                }
                while (start<nums.length && freqMap.size()==k){
                    List<Integer> positions = freqMap.getOrDefault(nums[start], new ArrayList<>());
                    positions.remove(0);
                    if(positions.size()>0){
                        freqMap.put(nums[start], positions );
                    }else{
                        freqMap.remove(nums[start]);
                    }

                    start++;
                }
            }
            return count;
        }

        private int getMin(Map<Integer, List<Integer>> freqMap) {
            int min  = Integer.MAX_VALUE;
            for(Map.Entry<Integer, List<Integer>> entry:freqMap.entrySet()){
                min = Math.min(min,entry.getValue().get(entry.getValue().size()-1));
            }
                return min;
        }
    }
}
