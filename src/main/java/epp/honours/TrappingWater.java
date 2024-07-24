package epp.honours;

import epp.array.ArrayUtils;

/**
 * A one-dimensional container is specified by an array of n nonnegative integers, spec¬ ifying the
 * height of each unit-width rectangle. Design an algorithm for computing the capacity of the
 * container.
 */
public class TrappingWater {

  public static void main(String[] args) {
    int[] heights = ArrayUtils.randomArray(10, 0, 20);
    ArrayUtils.printArray(heights);
    int trappedWater = calculateTrappedWater(heights);
    System.out.println(trappedWater);

    trappedWater = calculateTrappedWater2(heights);
    System.out.println(trappedWater);
  }

  /**
   * space complexity O(n) time complexity O(n)
   *
   * @param heights
   * @return
   */
  public static int calculateTrappedWater(int[] heights) {
    int n = heights.length;
    int[] maxSeenOnLeft = new int[n];
    int[] maxSeenOnRight = new int[n];
    maxSeenOnLeft[0] = heights[0];
    maxSeenOnRight[n - 1] = heights[n - 1];
    for (int i = 1; i < n; i++) {
      maxSeenOnLeft[i] = Math.max(maxSeenOnLeft[i - 1], heights[i]);
    }
    for (int i = n - 2; i >= 0; i--) {
      maxSeenOnRight[i] = Math.max(maxSeenOnRight[i + 1], heights[i]);
    }
    int totalWater = 0;
    for (int i = 0; i < n; i++) {
      int trapped = Math.min(maxSeenOnLeft[i], maxSeenOnRight[i]) - heights[i];
      System.out.println("Trapped water at index " + i + ": " + trapped);
      totalWater += trapped;
    }
    return totalWater;
  }

  /**
   * space complexity O(1) time complexity O(n)
   *
   * @param heights
   * @return
   */
  public static int calculateTrappedWater2(int[] heights) {
    int maxIndex = findMaxIndex(heights);
    System.out.println(maxIndex);
    int leftMax = heights[0];
    int rightMax = heights[heights.length - 1];
    int totalWater = 0;
    for (int i = 0; i < maxIndex; i++) {
      if (heights[i] > leftMax) {
        leftMax = heights[i];
      } else {
        int trapped = leftMax - heights[i];
        totalWater += trapped;
        System.out.println("Trapped water at index " + i + ": " + trapped);
      }
    }
    for (int i = heights.length - 1; i > maxIndex; i--) {
      if (heights[i] > rightMax) {
        rightMax = heights[i];
      } else {
        int trapped = rightMax - heights[i];
        totalWater += trapped;
        System.out.println("Trapped water at index " + i + ": " + trapped);
      }
    }
    return totalWater;
  }

  private static int findMaxIndex(int[] heights) {
    int maxIndex = 0;
    int max = heights[0];
    for (int i = 1; i < heights.length; i++) {
      if (heights[i] > max) {
        max = heights[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }
}
