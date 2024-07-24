package epp.honours;

public class MaxSubArrayWithSumLessThanK {
  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int k = 6;
    int maxLength = maxSubArrayWIthSumLessThan(nums, k);
    System.out.println(maxLength);
  }

  private static int maxSubArrayWIthSumLessThan(int[] nums, int k) {
    int[] prefixSum = new int[nums.length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      prefixSum[i] = nums[i] + prefixSum[i - 1];
    }
    int[] minimumPrefixSum = new int[nums.length];
    minimumPrefixSum[minimumPrefixSum.length - 1] = prefixSum[prefixSum.length - 1];
    for (int i = minimumPrefixSum.length - 2; i >= 0; i--) {
      minimumPrefixSum[i] = Math.min(minimumPrefixSum[i + 1], prefixSum[i]);
    }

    int left = 0, right = 0;
    int maxLength = 0;
    while (left < nums.length && right < nums.length) {
      int currentMinSum =
          left > 0 ? minimumPrefixSum[right] - minimumPrefixSum[left - 1] : minimumPrefixSum[right];
      if (currentMinSum <= k) {
        int currentLength = right - left + 1;
        maxLength = Math.max(maxLength, currentLength);
        right++;
      } else {
        left++;
      }
    }
    return maxLength;
  }
}
