package epp.heap.revision;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedStream {
  public static void main(String[] args) {

    List<Integer> values = new ArrayList<>(List.of(3, 2, 1, 5, 6, 4, 7, 8));
    List<Integer> sorted = sortAlmostSortedStream(values.iterator(), 2);
    System.out.println(sorted);
  }

  private static List<Integer> sortAlmostSortedStream(
      Iterator<Integer> iterator, int maxDisplacement) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < maxDisplacement && iterator.hasNext(); i++) {
      minHeap.offer(iterator.next());
    }
    while (iterator.hasNext()) {
      minHeap.offer(iterator.next());
      result.add(minHeap.poll());
    }
    while (!minHeap.isEmpty()) {
      result.add(minHeap.poll());
    }

    return result;
  }
}
