package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class ReversePairs {
    public static void main(String[] args) {
           Solution solution = new Solution();
        System.out.println(solution.reversePairs(new int[]{1, 3, 2, 3, 1}));
        System.out.println(solution.reversePairs(new int[]{2,4,3,5,1}));
        System.out.println(solution.reversePairs(new int[]{-5,-5}));
        System.out.println(solution.reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}));
    }
   private static class Solution {

        public int reversePairs(int[] nums) {
            AtomicInteger count = new AtomicInteger(0);
           mergeSort(nums,count);
           return count.get();
        }

       private void mergeSort(int[] nums, AtomicInteger count) {
           mergeSort(nums,0,nums.length-1,count);
       }
       private void mergeSort(int[] nums,int start,int end, AtomicInteger count) {
           if(start==end){
               return;
           }
           int mid = (start+end)/2;
           mergeSort(nums,start,mid,count);
           mergeSort(nums,mid+1,end,count);
           merge(nums,start,mid,end,count);
       }

       private void merge(int[] nums, int start, int mid, int end, AtomicInteger count) {
           int[] result = new int[end-start+1];
           int wp = 0;
           int fp = start;
           int sp = mid+1;
           while (fp<=mid && sp<=end){

               if(nums[fp]< nums[sp]){
                   result[wp++] = nums[fp++];
               }else{
                   result[wp++] = nums[sp];
                   long value = (long) nums[sp] * 2 + 1;
                   int index =  greaterOrEqual(nums, fp, mid , value);

                   if(index>=fp){

                       count.addAndGet(mid-index+1);
                   }

                   sp++;
               }
           }


           while (fp<=mid){
               result[wp++] = nums[fp++];

           }
           while (sp<=end){
               result[wp++] = nums[sp++];
           }
           System.arraycopy(result,0,nums,start,end-start+1);
       }
       private   int greaterOrEqual(int[] nums,int start,int end,long value){
           int low = start;
           int high = end;
           int index = -1;
           while (low<=high){
               int mid = (low+high)/2;
               if(nums[mid]==value){
                   index=mid;
                   break;
               }else if(nums[mid]<value){
                   low=mid+1;
               }else{
                   index = mid;
                   high = mid-1;
               }
           }
           return index;
       }
   }
}
