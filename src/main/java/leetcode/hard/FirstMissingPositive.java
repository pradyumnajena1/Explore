package leetcode.hard;

import epp.array.ArrayUtils;

public class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(fmp.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
        System.out.println(fmp.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(fmp.firstMissingPositive(new int[]{8,7,6,5,4,3,2,1}));
    }
    public int firstMissingPositive(int[] nums) {
        int low =1;
        int high = nums.length;
        for(int i=0;i<nums.length;i++){

            if(nums[i]>=low && nums[i]<=high && nums[i]!=i+1){

                int current = i;
                while ( nums[current]>=low && nums[current]<=high && nums[current]!=current+1){
                    ArrayUtils.swap(nums,current,nums[current]-1);

                }
            }
        }
        ArrayUtils.printArray(nums);
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;

    }
}
