package leetcode.hard;

import epp.array.ArrayUtils;

public class MinimumSwapsToMakeSequencesIncreasing {
    public static void main(String[] args) {
      Solution solution = new Solution();
        System.out.println(solution.minSwap(new int[]{1, 2, 3, 8}, new int[]{5, 6, 7, 4}));
        System.out.println(solution.minSwap(new int[]{0,3,5,8,9}, new int[]{2,1,4,6,9}));
        System.out.println(solution.minSwap(new int[]{0,4,4,5,9}, new int[]{0,1,6,8,10}));

    }
    private static class Solution {
        public int minSwap(int[] nums1, int[] nums2) {
            ArrayUtils.printArray(nums1);
            ArrayUtils.printArray(nums2);
           int p=0;

           int count =0;
           while (p<nums1.length){
                if(nums1[p] > (p==0?Integer.MIN_VALUE:nums1[p-1])  && nums2[p]> (p==0?Integer.MIN_VALUE:
                        nums2[p-1])){
                    p++;
                }else {


                    int temp = nums1[p];
                    nums1[p] = nums2[p];
                    nums2[p] =temp;
                    count++;
                    ArrayUtils.printArray(nums1);
                    ArrayUtils.printArray(nums2);
                }
           }

         return count;
        }
    }
}
