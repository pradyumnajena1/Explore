package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveInMatrix {
  public static void main(String[] args) {
    int m = 5;
    int n = 5;
    int numWays = computeNumberOfWaysToXY(m - 1, n - 1);
    System.out.println(numWays);
    boolean[][] obstacles = {
            {false, false, true, false, true},
            {false, false, false, true, false},
            {false, true, false, false, false},
            {false, false, true, true, false},
            {true, false, false, false, false}
    };
    numWays = computeNumberOfWaysToXYWithObstacles(m - 1, n - 1,obstacles);
    System.out.println(numWays);
    int[][] rewards = {
            {3, 1, 4, 0, 5},
            {2, 5, 3, 2, 1},
            {0, 4, 1, 5, 3},
            {4, 2, 0, 3, 1},
            {5, 0, 3, 1, 2}
    };

   int  maxRewards = computeMaxRewardsToXY(m - 1, n - 1,rewards);
    System.out.println(maxRewards);
  }

  public static int computeNumberOfWaysToXY(int x, int y) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return computeNumberOfWaysToXY(x, y, cache);
  }

  private static int computeNumberOfWaysToXY(int x, int y, Map<List<Integer>, Integer> cache) {
    if (x == 0 && y == 0) {
      return 1;
    }
    List<Integer> key = List.of(x, y);
    if (!cache.containsKey(key)) {

      int numWaysToTop = x > 0?computeNumberOfWaysToXY(x - 1, y, cache):0;
      int numWaysToLeft = y > 0?computeNumberOfWaysToXY(x , y-1, cache):0;
      int numWays = numWaysToTop + numWaysToLeft;
      cache.put(key, numWays);
    }

    return cache.get(key);
  }

  public static int computeNumberOfWaysToXYWithObstacles(int x, int y,boolean[][] obstacles) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return computeNumberOfWaysToXYWithObstacles(x, y,obstacles, cache);
  }

  private static int computeNumberOfWaysToXYWithObstacles(int x, int y, boolean[][] obstacles, Map<List<Integer>, Integer> cache) {
    if (x == 0 && y == 0) {
      return 1;
    }
    List<Integer> key = List.of(x, y);
    if (!cache.containsKey(key)) {

      int numWaysToTop = x > 0 && !obstacles[x-1][y] ?computeNumberOfWaysToXYWithObstacles(x - 1, y,obstacles, cache):0;
      int numWaysToLeft = y > 0 && !obstacles[x][y-1] ?computeNumberOfWaysToXYWithObstacles(x , y-1, obstacles,
              cache):0;
      int numWays = numWaysToTop + numWaysToLeft;
      cache.put(key, numWays);
    }

    return cache.get(key);
  }


  public static int computeMaxRewardsToXY(int x, int y,int[][] rewards) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return computeMaxRewardsToXY(x, y,rewards, cache);
  }

  private static int computeMaxRewardsToXY(int x, int y, int[][] rewards, Map<List<Integer>, Integer> cache) {
    if (x == 0 && y == 0) {
      return rewards[x][y];
    }
    List<Integer> key = List.of(x, y);
    if (!cache.containsKey(key)) {

      int rewardsToTop = x > 0 ?computeMaxRewardsToXY(x - 1, y,rewards, cache):0;
      int rewardsToLeft = y > 0   ?computeMaxRewardsToXY(x , y-1, rewards, cache):0;
      int maxRewards = Math.max( rewardsToTop , rewardsToLeft) + rewards[x][y];
      cache.put(key, maxRewards);
    }

    return cache.get(key);
  }
}
