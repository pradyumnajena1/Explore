package epp.dp.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumWeightPathInTriangle {
  public static void main(String[] args) {
    List<List<Integer>> triangle =
        List.of(
            List.of(2),
            List.of(4, 4),
            List.of(8, 5, 6),
            List.of(4, 2, 6, 2),
            List.of(1, 5, 2, 6, 2));
    int minWeightPath = findMinWeightPath(triangle);
    System.out.println(minWeightPath);
  }

  private static int findMinWeightPath(List<List<Integer>> triangle) {
    if (triangle.size() == 0) {
      return 0;
    }
    List<Integer> previous = new ArrayList<>(triangle.get(0));
    for (int i = 1; i < triangle.size(); i++) {
      List<Integer> current = new ArrayList<>(triangle.get(i));
      current.set(0, current.get(0) + previous.get(0));
      for (int j = 1; j < triangle.get(i).size() - 1; j++) {
        current.set(j, current.get(j) + Math.min(previous.get(j - 1), previous.get(j)));
      }
      current.set(
          current.size() - 1, current.get(current.size() - 1) + previous.get(previous.size() - 1));
      previous = current;
    }
    return Collections.min(previous);
  }
}
