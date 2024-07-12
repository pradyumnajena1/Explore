package epp.hashmap.revision;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SmallestSubArrayCoveringAllValues {
  public static void main(String[] args) {
    List<String> paragraph =
        Arrays.asList("apple", "banana", "apple", "apple", "banana", "orange", "apple", "banana");
    Set<String> keywords = new HashSet<>(Arrays.asList("banana", "orange"));
    Range range = findSmallestSubArrayCover(paragraph, keywords);
    System.out.println(range);

    range = findSmallestSubArrayCover2(paragraph, keywords);
    System.out.println(range);

    range = findSmallestSubArrayContainingAllUniqueValues(paragraph);
    System.out.println(range);

    ArrayList<Integer> values = new ArrayList<>(List.of(1, 2, 2, 1, 3, 3, 1, 1, 4, 2, 5, 4, 4, 5));
    rearrangeArrayShortestSubArrayOfUniqueValuesHasMaxLength(values);
    System.out.println(values);

    values = new ArrayList<>(List.of(1, 2, 2, 1, 3, 3, 1, 1, 4, 2, 5, 4, 4, 5));
    rearrangeArraySoThatNoTwoEqualElementAreCloserThanK(values, 3);
    System.out.println(values);
  }

  public static <T> Range findSmallestSubArrayCover(List<T> values, Set<T> keyValues) {
    Map<T, Integer> keywordCountMap = new HashMap<>();
    for (T keyValue : keyValues) {
      keywordCountMap.put(keyValue, 0);
    }
    int foundKeyWord = 0;
    Range result = new Range(-1, -1);
    for (int left = 0, right = 0; right < values.size(); right++) {
      T inComing = values.get(right);
      Integer keywordCount = keywordCountMap.get(inComing);
      if (keywordCount != null) {
        keywordCountMap.put(inComing, ++keywordCount);
        if (keywordCount == 1) {
          foundKeyWord++;
        }
      }

      while (left < values.size() && foundKeyWord == keywordCountMap.size()) {
        if ((result.getStart() == -1 && result.getEnd() == -1)
            || result.dist() > right - left + 1) {
          result = new Range(left, right);
        }
        T outGoing = values.get(left);
        keywordCount = keywordCountMap.get(outGoing);
        if (keywordCount != null) {
          keywordCountMap.put(outGoing, --keywordCount);
          if (keywordCount == 0) {
            foundKeyWord--;
          }
        }
        left++;
      }
    }
    return result;
  }

  public static <T> Range findSmallestSubArrayCover2(List<T> values, Set<T> keyValues) {
    LinkedHashMap<T, Integer> lastLocationMap = new LinkedHashMap<>();

    Range result = new Range(-1, -1);
    for (int i = 0; i < values.size(); i++) {
      T value = values.get(i);
      if (keyValues.contains(value)) {
        lastLocationMap.remove(value);
        lastLocationMap.put(value, i);
      }

      if (lastLocationMap.size() == keyValues.size()) {
        Integer firstEntry = getValueForFirstEntry(lastLocationMap);
        if (result.getStart() == -1 || result.dist() > i - firstEntry + 1)
          result = new Range(firstEntry, i);
      }
    }
    return result;
  }

  private static <T> Integer getValueForFirstEntry(LinkedHashMap<T, Integer> m) {
    // LinkedHashMap guarantees iteration over key-value pairs takes place in
    // insertion order, most recent first.
    Integer result = null;
    for (Map.Entry<T, Integer> entry : m.entrySet()) {
      result = entry.getValue();
      break;
    }
    return result;
  }

  public static <T> Range findSmallestSubArrayContainingAllUniqueValues(List<T> values) {
    Set<T> uniqueValues = new HashSet<>(values);
    return findSmallestSubArrayCover2(values, uniqueValues);
  }

  /**
   * Given an array A, rearrange the elements so that the shortest subarray containing all the
   * distinct values in A has maximum possible length.
   */
  public static <T> void rearrangeArrayShortestSubArrayOfUniqueValuesHasMaxLength(List<T> values) {
    Map<T, Long> freqMap =
        values.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    PriorityQueue<Map.Entry<T, Long>> priorityQueue =
        new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));
    priorityQueue.addAll(freqMap.entrySet());

    int leftIndex = 0;
    int rightIndex = values.size() - 1;
    int turn = 0;
    while (priorityQueue.size() > 0) {
      Map.Entry<T, Long> poll = priorityQueue.poll();
      Long count = poll.getValue();
      if (turn == 0) {
        while (count > 0) {
          values.set(leftIndex++, poll.getKey());
          count--;
        }
      } else {
        while (count > 0) {
          values.set(rightIndex--, poll.getKey());
          count--;
        }
      }
      turn ^= 1;
    }
  }

  /**
   * Given an array A and a positive integer k, rearrange the elements so that no two equal elements
   * are k or less apar
   */
  public static <T> void rearrangeArraySoThatNoTwoEqualElementAreCloserThanK(
      List<T> values, int k) {
    Map<T, Long> freqMap =
        values.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Comparator<Map.Entry<T, Long>> comparator =
        Comparator.comparingLong((Map.Entry<T, Long> e) -> e.getValue()).reversed();
    PriorityQueue<Map.Entry<T, Long>> priorityQueue = new PriorityQueue<>(comparator);
    priorityQueue.addAll(freqMap.entrySet());
    Queue<Map.Entry<T, Long>> waitQueue = new LinkedList<>();
    int index = 0;

    while (priorityQueue.size() > 0) {

      Map.Entry<T, Long> current = priorityQueue.poll();
      values.set(index++, current.getKey());
      current.setValue(current.getValue() - 1);
      waitQueue.add(current);

      if (waitQueue.size() >= k) {
        Map.Entry<T, Long> poll = waitQueue.poll();
        if (poll.getValue() > 0) {
          priorityQueue.offer(poll);
        }
      }
    }
    if (index != values.size()) {
      throw new IllegalStateException("cant be arranged");
    }
  }
}
