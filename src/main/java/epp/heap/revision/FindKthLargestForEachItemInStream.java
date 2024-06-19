package epp.heap.revision;

import java.util.*;

public class FindKthLargestForEachItemInStream {
  public static void main(String[] args) {
    List<Integer> values = new ArrayList<>(List.of(1, 5, 2, 6, 7, 9, 11));
    int k = 3;
    List<Integer> result = findKthLargestValues(values.iterator(), k);
    System.out.println(result);
  }

  public static List<Integer> findKthLargestValues(Iterator<Integer> iterator, int k) {

    List<Integer> result = new ArrayList<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int i = 0; i < k && iterator.hasNext(); i++) {
      minHeap.offer(iterator.next());
    }
    result.add(minHeap.peek());
    while (iterator.hasNext()) {
      int next = iterator.next();
      if (next > minHeap.peek()) {
        minHeap.poll();
        minHeap.offer(next);
      }
      result.add(minHeap.peek());
    }
    return result;
  }
}
