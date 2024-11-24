package hackerrank.easy;

public class MaxProductSubArray {
  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 4, -1, 0, 5, -3};
    int[] indices = maxProduct(nums);
    System.out.println(
        "Indices of maximum product subarray: ["
            + indices[0]
            + ", "
            + indices[1]
            + ", "
            + indices[2]
            + "]");
  }

  public static int[] maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int maxProduct = nums[0];
    int minProduct = nums[0];
    int result = nums[0];

    int start = 0;
    int end = 0;
    int maxStart = 0;
    int minStart = 0;

    for (int i = 1; i < nums.length; i++) {

      if (nums[i] < 0) {
        int temp = maxProduct;
        maxProduct = minProduct;
        minProduct = temp;

        temp = maxStart;
        maxStart = minStart;
        minStart = temp;
      }

      if (nums[i] > maxProduct * nums[i]) {
        maxProduct = nums[i];
        maxStart = i;
      } else {
        maxProduct = maxProduct * nums[i];
      }

      if (nums[i] < minProduct * nums[i]) {
        minProduct = nums[i];
        minStart = i;
      } else {
        minProduct = minProduct * nums[i];
      }

      if (maxProduct > result) {
        result = maxProduct;
        start = maxStart;
        end = i;
      }

    }

    return new int[] {start, end, result};
  }
}
