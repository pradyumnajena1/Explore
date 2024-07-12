package epp.hashmap.revision;

import epp.array.ArrayUtils;
import java.util.*;

public class LongestSubArrayContainingUniqueValues {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 1, 10);
    Range range = longestSubArrayContainingUniqueValues(values);
    ArrayUtils.printArray(values);
    System.out.println(range);

    values = new int[] {5, 7, 5, 11, 13, 2, 11, 19, 2, 11};
    range = longestSubArrayContainingUniqueValues(values);
    ArrayUtils.printArray(values);
    System.out.println(range);

    values = new int[] {5, 7, 5, 11, 13, 2, 11, 19, 2, 11};
    range = longestSubArrayContainingUniqueValues2(values);
    ArrayUtils.printArray(values);
    System.out.println(range);
  }

  private static Range longestSubArrayContainingUniqueValues(int[] values) {
    Queue<Integer> queue = new ArrayDeque<>();
    Set<Integer> uniqueValues = new HashSet<>();
    Range range = null;

    for (int i = 0; i < values.length; i++) {
      if (uniqueValues.contains(values[i])) {
        while (queue.peek() != values[i]) {
          uniqueValues.remove(queue.poll());
        }
        uniqueValues.remove(queue.poll());
      }
      uniqueValues.add(values[i]);
      queue.offer(values[i]);
      if (range == null || range.dist() < uniqueValues.size()) {
        range = new Range(i - uniqueValues.size() + 1, i);
      }
    }
    return range;
  }

  private static Range longestSubArrayContainingUniqueValues2(int[] values) {

    Map<Integer, Integer> uniqueValuesLocationMap = new HashMap<>();
    Range maxRange = null;
    int startIndex = 0;
    for (int i = 0; i < values.length; i++) {

      Integer prev = uniqueValuesLocationMap.put(values[i], i);
      if (prev != null && prev >= startIndex) {
        startIndex = prev + 1;
      }
      int dist = i - startIndex + 1;
      if (maxRange == null || dist > maxRange.dist()) {
        maxRange = new Range(i - dist + 1, i);
      }
    }
    return maxRange;
  }
}
