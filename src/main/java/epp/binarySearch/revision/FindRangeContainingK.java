package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class FindRangeContainingK {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomSortedArray(10, 1, 10);
    ArrayUtils.printArray(values);
    List<Integer> ranges = findRangeContainingK(values, values[4]);
    System.out.println(ranges);
    ranges = findRangeContainingK(values, 11);
    System.out.println(ranges);
  }

  private static List<Integer> findRangeContainingK(int[] values, int k) {
    int firstOccurrence = FindFirstOccurrence.findFirstOccurrence(values, k);
    if (firstOccurrence >= 0) {
      int lastOccurrence = FindFirstOccurrence.findLastOccurrence(values, k);
      return new ArrayList<Integer>(List.of(firstOccurrence, lastOccurrence));
    }
    return new ArrayList<Integer>(List.of(-1, -1));

  }
}
