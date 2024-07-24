package epp.honours;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriticalHeight {

  public static void main(String[] args) {
    int numDropsAllowed = 5;
    int numEggs = 2;
    int criticalHeight = criticalHeight(numDropsAllowed, numEggs);
    System.out.println(criticalHeight);

    int minDrops = minDropsRequired(10, 2);
    System.out.println(minDrops);

    minDrops = minDrops(10, 2);
    System.out.println(minDrops);
  }

  private static int criticalHeight(int numDropsAllowed, int numEggs) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return criticalHeight(numDropsAllowed, numEggs, cache);
  }

  private static int criticalHeight(
      int numDropsAllowed, int numEggs, Map<List<Integer>, Integer> cache) {
    if (numDropsAllowed == 0 || numEggs == 0) {
      return 0;
    }
    if (numEggs == 1) {
      return numDropsAllowed;
    }
    List<Integer> key = List.of(numDropsAllowed, numEggs);
    if (!cache.containsKey(key)) {

      cache.put(
          key,
          criticalHeight(numDropsAllowed - 1, numEggs - 1, cache)
              + 1
              + criticalHeight(numDropsAllowed - 1, numEggs));
    }
    return cache.get(key);
  }

  public static int minDropsRequired(int numFloors, int numEggs) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    int minDropsRequired = minDropsRequired(numFloors, numEggs, cache);
    System.out.println(cache);
    return minDropsRequired;
  }

  private static int minDropsRequired(
      int numFloors, int numEggs, Map<List<Integer>, Integer> cache) {
    if (numFloors == 0) {
      return 0;
    }
    if (numEggs == 1) {
      return numFloors;
    }
    List<Integer> key = List.of(numFloors, numEggs);
    if (!cache.containsKey(key)) {
      int min = Integer.MAX_VALUE;
      for (int k = 1; k <= numFloors; k++) {
        int breaks = minDropsRequired(k - 1, numEggs - 1, cache);
        int doesNotBreak = minDropsRequired(numFloors - k, numEggs, cache);
        int res = Math.max(doesNotBreak, breaks) + 1;
        min = Math.min(min, res );
      }
      cache.put(key, min);
    }
    return cache.get(key);
  }


  public static int minDrops(int floors, int cases) {
    // Create a DP table to store the results of subproblems
    int[][] dp = new int[cases + 1][floors + 1];

    // Base cases
    // One case: we need to try each floor from 1 to F
    for (int j = 1; j <= floors; j++) {
      dp[1][j] = j;
    }

    // Zero floors requires zero drops
    // One floor requires one drop
    for (int i = 1; i <= cases; i++) {
      dp[i][0] = 0;
      dp[i][1] = 1;
    }

    // Fill the rest of the table using the recursive relation
    for (int i = 2; i <= cases; i++) {
      for (int j = 2; j <= floors; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = 1; k <= j; k++) {
          int res = 1 + Math.max(dp[i-1][k-1], dp[i][j-k]);
          dp[i][j] = Math.min(dp[i][j], res);
        }
      }
    }

    ArrayUtils.print2DArray(dp);

    return dp[cases][floors];
  }
}
