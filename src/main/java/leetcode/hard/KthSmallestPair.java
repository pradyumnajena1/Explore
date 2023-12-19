package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Function;

public class KthSmallestPair {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.smallestDistancePair(new int[]{1, 6, 1}, 3));
    }

   private static class Solution {
        public int smallestDistancePair(int[] nums, int k) {
            Comparator<Integer> comparing = Comparator.comparing(Function.<Integer>identity(),
                    Comparator.<Integer>reverseOrder());
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparing);
            mergeSort(nums,0,nums.length-1, priorityQueue,k);
            return priorityQueue.peek();

        }
        public void mergeSort(int[] nums, int left, int right, PriorityQueue<Integer> priorityQueue, int k){
            if(left==right){
                return  ;
            }
            int mid = (left+right)/2;
            mergeSort(nums,left,mid, priorityQueue, k);
            mergeSort(nums,mid+1,right, priorityQueue, k);
            merge(nums,left,mid,right,priorityQueue,k);

        }

       private void merge(int[] nums, int left, int mid, int right, PriorityQueue<Integer> priorityQueue, int k) {
           int[] result = new int[nums.length];
           int leftPtr = left;
           int rightPtr = mid+1;
           int writePtr=0;
           while (leftPtr<=mid && rightPtr<=right){
               if(nums[leftPtr]<=nums[rightPtr]){
                   result[writePtr++] = nums[leftPtr];
                   leftPtr++;
               }else{
                   result[writePtr++] = nums[rightPtr];

                   int diff= Math.abs( nums[rightPtr]-nums[leftPtr]);
                   if(priorityQueue.size()<k){
                       priorityQueue.offer(diff);
                   }else{
                       if( priorityQueue.peek()>diff){
                           priorityQueue.poll();
                           priorityQueue.offer(diff);
                       }
                   }
                   rightPtr++;
               }
           }
           if(leftPtr<=mid){
               result[writePtr++] = nums[leftPtr];
           }
           if(rightPtr<=right){
               result[writePtr++] = nums[rightPtr];
           }

           System.arraycopy(result,0,nums,left,right-left+1);

       }
   }
}
