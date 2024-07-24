package epp.honours;

import java.util.ArrayList;
import java.util.List;

public class CountInversions {
  public static void main(String[] args) {
    int countInversions = countInversions(new ArrayList<>(List.of(2, 4, 3, 7, 1, 9)));
    System.out.println(countInversions);
  }

  public static int countInversions(List<Integer> values) {

    return countInversions(values, 0, values.size());
  }

  private static int countInversions(List<Integer> values, int start, int end) {
    if (end-start<=1) {
      return 0;
    }
    int mid = start + (end - start) / 2;
    int leftInversions = countInversions(values, start, mid);
    int rightInversions = countInversions(values, mid, end);

    return leftInversions + rightInversions + mergeAndCount(values, start, mid, end);
  }

  private static int mergeAndCount(List<Integer> values, int start, int mid, int end) {
    List<Integer> result = new ArrayList<>();
    int leftStart = start;
    int rightStart = mid;
    int inversions = 0;
    while (leftStart < mid && rightStart < end) {
      if (values.get(leftStart) <= values.get(rightStart)) {
        result.add(values.get(leftStart++));
      } else {
        result.add(values.get(rightStart++));
        inversions += mid - leftStart;
      }
    }
    result.addAll(values.subList(leftStart, mid));
    result.addAll(values.subList(rightStart, end));
    for (int t : result) {
      values.set(start++, t);
    }

    return inversions;
  }
}
