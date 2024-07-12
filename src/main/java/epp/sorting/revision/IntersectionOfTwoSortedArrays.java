package epp.sorting.revision;

import java.util.*;

public class IntersectionOfTwoSortedArrays {
  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5, 5};
    int[] b = {2, 3, 3, 4, 5, 6};
    System.out.println(getIntersection(a, b));
  }

  public static List<Integer> getIntersection2(int[] a, int[] b) {
    if (a.length > b.length) {
      return getIntersection2(b, a);
    }
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < a.length; i++) {
      // avoid repeated search
      if ((i == 0 || a[i] != a[i - 1]) && Arrays.binarySearch(b, a[i]) >= 0) {
        result.add(a[i]);
      }
    }
    return result;
  }

  public static List<Integer> getIntersection(int[] a, int[] b) {
    List<Integer> result = new ArrayList<Integer>();
    int i = 0;
    int j = 0;
    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        i++;
      } else if (a[i] > b[j]) {
        j++;
      } else {
        if (result.isEmpty() || result.get(result.size() - 1) < a[i]) {
          result.add(a[i]);
        }
        i++;
        j++;
      }
    }
    return result;
  }
}
