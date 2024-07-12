package epp.dp.revision;

import java.util.HashMap;
import java.util.Map;

/** in a board game you can make 1,2...k max steps , need to reach n ,compute numways */
public class FindNumWays {
  public static void main(String[] args) {
    int numWays = findNumWays(4, 2);
    System.out.println(numWays);
  }

  private static int findNumWays(int n, int k) {
    Map<Integer, Integer> cache = new HashMap<>();
    return findNumWays(n, k, cache);
  }

  private static int findNumWays(int n, int k, Map<Integer, Integer> cache) {
    System.out.println("n = " + n + ", k = " + k);

    if (n == 0) {
      return 1;
    }

    if (!cache.containsKey(n)) {

      int numWays = 0;
      for (int i = 1; i <= k && n - i >= 0; i++) {
        numWays += findNumWays(n - i, k, cache);
      }
      cache.put(n, numWays);
    }
    return cache.get(n);
  }
}
