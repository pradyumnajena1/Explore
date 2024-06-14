package epp.array.revision;

import epp.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class PermutationUtil {

  public static void main(String[] args) {
    ArrayList<Integer> values = new ArrayList<>(List.of(6, 2, 3, 5, 4, 1, 0));
    List<Integer> nextPermutation = getNextPermutation(values);
    System.out.println(nextPermutation);
    List<Integer> previousPermutation = getPreviousPermutation(values);
    System.out.println(previousPermutation);

    // System.out.println(getPermutation(values, 5000));
  }

  public static List<Integer> getPreviousPermutation(List<Integer> values, int k) {

    for (int i = 1; i <= k; i++) {
      if (values == null) {
        break;
      }
      values = getPreviousPermutation(values);
    }
    return values;
  }

  public static List<Integer> getNextPermutation(List<Integer> values) {
    int k = values.size() - 1;
    while (k > 0 && values.get(k) <= values.get(k - 1)) {
      k--;
    }
    if (k == 0) {
      return null;
    }
    for (int i = values.size() - 1; i >= k; i--) {
      if (values.get(i) > values.get(k - 1)) {
        ListUtils.swap(values, k - 1, i);
        break;
      }
    }

    ListUtils.reverse(values.subList(k, values.size()));
    return values;
  }

  public static List<Integer> getPreviousPermutation(List<Integer> values) {
    int k = values.size() - 1;
    while (k > 0 && values.get(k) >= values.get(k - 1)) {
      k--;
    }
    if (k == 0) {
      return null;
    }
    for (int i = values.size() - 1; i >= k; i--) {
      if (values.get(i) < values.get(k - 1)) {
        ListUtils.swap(values, k - 1, i);
        break;
      }
    }
    ListUtils.reverse(values.subList(k, values.size()));
    return values;
  }
}
