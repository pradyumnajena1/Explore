package epp.array.revision;

import java.util.List;

public class FurthestReach {
  public static void main(String[] args) {
    System.out.println(canReachEnd(List.of(3, 3, 1, 0, 2, 0, 1)));
    System.out.println(minStepsRequired(List.of(3, 3, 1, 0, 2, 0, 1)));
  }

  public static boolean canReachEnd(List<Integer> maxMoves) {
    int furthestReach = 0;
    int lastIndex = maxMoves.size() - 1;
    for (int i = 0; i <= furthestReach && i < lastIndex; i++) {
      furthestReach = Math.max(furthestReach, i + maxMoves.get(i));
    }
    return furthestReach >= lastIndex;
  }

  public static int minStepsRequired(List<Integer> maxMoves) {
    int furthestReach = 0;
    int numSteps = 0;
    int lastIndex = maxMoves.size() - 1;
    for (int i = 0; i <= furthestReach && i < lastIndex; i++) {

      if (i + maxMoves.get(i) > furthestReach) {
        furthestReach = i + maxMoves.get(i);
        numSteps++;
      }
    }
    return furthestReach >= lastIndex ? numSteps : -1;
  }
}
