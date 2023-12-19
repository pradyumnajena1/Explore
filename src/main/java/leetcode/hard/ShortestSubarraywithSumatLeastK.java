package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.Stack;

public class ShortestSubarraywithSumatLeastK {
    public static void main(String[] args) {
        Solution solution = new Solution();
         System.out.println(solution.shortestSubarray(new int[]{2, -1, 2}, 3));
        System.out.println(solution.shortestSubarray(new int[]{1, 2}, 4));
        System.out.println(solution.shortestSubarray(new int[]{1}, 1));
        System.out.println(solution.shortestSubarray(new int[]{17,85,93,-45,-21}, 150));
    }

    private static class Solution {
        public int shortestSubarray(int[] nums, int k) {

            ArrayUtils.printArray(nums);

            int start=0;
            int end=0;
            int minLength = nums.length+1;
            int curSum = 0;
            while ( end<nums.length){

                while (end<nums.length && curSum <= k){
                    curSum+= nums[end++];
                }

                while (start<nums.length &&  curSum >= k  ){
                    int length = end-start;
                    minLength = Math.min(length,minLength);
                    curSum-= nums[start++];
                }
            }
            return minLength==nums.length+1?-1:minLength;
        }
    }
}
