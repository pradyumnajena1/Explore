package epp.dp.revision;

import epp.array.ArrayUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanPartitionInHalf {
  public static void main(String[] args) {

    int[] values = {65, 35, 245, 195, 64, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
    ArrayUtils.printArray(values);
    boolean canPartition = canPartitionInHalf(values);
    System.out.println(canPartition);
  }

  private static boolean canPartitionInHalf(int[] values) {
    int sum = Arrays.stream(values).sum();
    if (sum % 2 == 1) {
      return false;
    }
    int max = getMaxSum(values, sum / 2);
    System.out.println(max);
    return max == sum / 2;
  }

  private static int getMaxSum(int[] values, int targetSum) {
    Map<List<Integer>, Integer> cache = new HashMap<List<Integer>, Integer>();
    return getMaxSum(values, targetSum, values.length - 1, cache);
  }

  private static int getMaxSum(
      int[] values, int targetSum, int i, Map<List<Integer>, Integer> cache) {
    if (i < 0 || targetSum == 0) {
      return 0;
    }
    List<Integer> key = List.of(targetSum, i);
    if (!cache.containsKey(key)) {
      int nonIncluding = getMaxSum(values, targetSum, i - 1, cache);
      int including =
          targetSum - values[i] >= 0
              ? values[i] + getMaxSum(values, targetSum - values[i], i - 1, cache)
              : 0;
      cache.put(key, Math.max(including, nonIncluding));
    }
    return cache.get(key);
  }
}
