package epp.honours;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class MaxSubArraySumInCircularArray {
  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int maxSum = maxSubArraySumInCircularArray(nums);
    System.out.println("Maximum sum of circular subarray: " + maxSum);

    maxSum = maxSubArraySumInCircularArray2(nums);
    System.out.println("Maximum sum of circular subarray: " + maxSum);
  }

  /**
   * space complexity O(n)
   * time complexity O(n)
   * @param nums
   * @return
   */

  public static int maxSubArraySumInCircularArray(int[] nums) {
    int maxSum = maxSubArraySum(nums,Math::max);
    int maxSumWrapAround = maxSubArraySumWrapAround(nums);
    return Math.max(maxSum, maxSumWrapAround);
  }

  public static int maxSubArraySumInCircularArray2(int[] nums) {
    int total = Arrays.stream(nums).sum();
    int maxSum = maxSubArraySum(nums,Math::max);
    int maxSumWrapAround = total- maxSubArraySum(nums,Math::min);
    return Math.max(maxSum,  maxSumWrapAround);
  }

  private static int maxSubArraySumWrapAround(int[] nums) {
    List<Integer> leftSums = new ArrayList<>();
    List<Integer> rightSums = new ArrayList<>(Collections.nCopies(nums.length, 0));
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum = Math.max(sum + nums[i], nums[i]);
      leftSums.add(sum);
    }
    sum = 0;
    for (int i = nums.length - 2; i >= 0; i--) {
      sum = Math.max(sum + nums[i + 1], nums[i + 1]);
      rightSums.set(i, sum);
    }
    int maxSumWrapAround = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      maxSumWrapAround =
          Math.max(
              maxSumWrapAround,
              leftSums.get(i) + rightSums.get(i)); // wrap around sum of left and right subarrays
    }
    return maxSumWrapAround;
  }

  private static int maxSubArraySum(int[] nums, BiFunction<Integer,Integer,Integer> function) {
    int sum = 0;
    int maxSum = Integer.MIN_VALUE;
    for (int a : nums) {
      sum = function.apply(a, sum + a);
      maxSum = function.apply(maxSum, sum);
    }
    return maxSum;
  }


}
