package epp.heap.revision;

import java.util.*;

public class TopKFromStream {
  public static void main(String[] args) {
    List<String> values = new ArrayList<String>(List.of("hello world", "world", "hello", "test"));
    List<String> result = topKFromStream(values.iterator(), 3);
    System.out.println(result);
  }

  public static List<String> topKFromStream(Iterator<String> stream, int k) {

    PriorityQueue<String> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(String::length));
    while (stream.hasNext()) {
      String next = stream.next();
      if (minHeap.size() < k) {
        minHeap.offer(next);
      } else if (minHeap.peek().length() < next.length()) {
        minHeap.poll();
        minHeap.offer(next);
      }
    }
    return new ArrayList<>(minHeap);
  }
}
