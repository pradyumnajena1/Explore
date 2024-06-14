package epp;

import java.util.ArrayList;
import java.util.List;

public class SmallestNonconstructibleValue {
  public static void main(String[] args) {
    List<Integer> coins = new ArrayList<>(List.of(1, 1, 1, 1, 1, 5, 10, 25));
    System.out.println(smallestNonconstructibleValue(coins));
  }

  public static int smallestNonconstructibleValue(List<Integer> coins) {
    coins.sort(Integer::compareTo);
    int maxConstructibleValue = 0;
    for (int i = 0; i < coins.size(); i++) {
      Integer nextValue = coins.get(i);
      if (nextValue > maxConstructibleValue + 1) {
        break;
      }

      maxConstructibleValue = maxConstructibleValue + nextValue;
    }
    return maxConstructibleValue + 1;
  }
}
