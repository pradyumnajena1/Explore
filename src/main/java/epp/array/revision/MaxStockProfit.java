package epp.array.revision;

import epp.array.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class MaxStockProfit {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 5, 20);
    int maxProfit = getMaxProfit(values);
    ArrayUtils.printArray(values);
    System.out.println(maxProfit);

    maxProfit = getMaxProfit2Times(values);
    ArrayUtils.printArray(values);
    System.out.println(maxProfit);

    System.out.println(longestSubArrayOfEquals(new int[] {1, 1, 1, 1, 2, 3, 3, 2, 2, 2, 1, 1, 5}));
  }

  private static int getMaxProfit(int[] values) {
    return getMaxProfit(values, 0, values.length - 1);
  }

  private static int getMaxProfit2Times(int[] values) {
    int min = Integer.MAX_VALUE;
    int maxProfit = 0;
    List<Integer> forwardProfits = new ArrayList<>();

    for (int i = 0; i < values.length; i++) {
      min = Math.min(min, values[i]);
      maxProfit = Math.max(maxProfit, values[i] - min);
      forwardProfits.add(maxProfit);
    }
    int max = Integer.MIN_VALUE;
    maxProfit = 0;
    for (int i = values.length - 1; i > 0; i--) {
      max = Math.max(max, values[i]);
      maxProfit = Math.max(maxProfit, max - values[i] + forwardProfits.get(i - 1));
    }
    return maxProfit;
  }

  private static int getMaxProfit(int[] values, int start, int end) {

    int min = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int i = start; i <= end; i++) {
      min = Math.min(min, values[i]);
      maxProfit = Math.max(maxProfit, values[i] - min);
    }
    return maxProfit;
  }

  /**
   * Write a program that takes an array of integers and finds the length of a longest subarray all
   * of whose entries are equal
   */
  public static int longestSubArrayOfEquals(int[] values) {
    if (values == null || values.length == 0) {
      return 0;
    }
    int longest = 1;
    int lastValue = values[0];
    int lastCount = 1;
    for (int i = 1; i < values.length; i++) {
      if (values[i] == lastValue) {
        lastCount++;
      } else {
        lastCount = 1;
        lastValue = values[i];
      }
      longest = Math.max(longest, lastCount);
    }
    return longest;
  }
}
