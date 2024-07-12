package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumEditDistance {
  public static void main(String[] args) {
    String source = "Saturday";
    String target = "Sundays";
    int editDistance = minimumEditDistance(source, target);
    System.out.println(editDistance);
  }

  public static int minimumEditDistance(String source, String target) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return minimumEditDistance(source, target, 0, 0, cache);
  }

  private static int minimumEditDistance(
      String source, String target, int i, int j, Map<List<Integer>, Integer> cache) {

    if (i == source.length() && j == target.length()) {
      return 0;
    } else if (j == target.length()) {
      return source.length() - i;
    } else if (i == source.length()) {
      return target.length() - j;
    }
    List<Integer> key = List.of(i, j);
    if (!cache.containsKey(key)) {


      int minEditDistance = 0;
      if (source.charAt(i) == target.charAt(j)) {
        minEditDistance = minimumEditDistance(source, target, i + 1, j + 1, cache);
      } else {
        int deleteDistance = minimumEditDistance(source, target, i + 1, j, cache);
        int insertDistance = minimumEditDistance(source, target, i, j + 1, cache);
        int substituteDistance = minimumEditDistance(source, target, i + 1, j + 1, cache);
        minEditDistance =
            1 + Math.min(deleteDistance, Math.min(insertDistance, substituteDistance));
      }
      cache.put(key, minEditDistance);
    }

    return cache.get(key);
  }
}
