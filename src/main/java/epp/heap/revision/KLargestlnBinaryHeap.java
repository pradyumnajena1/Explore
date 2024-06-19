package epp.heap.revision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestlnBinaryHeap {
  public static void main(String[] args) {
    List<Integer> originalMaxHeap =
        new ArrayList<Integer>(List.of(561, 314, 401, 28, 156, 359, 271, 11, 3));
    List<Integer> result = findKLargestlnBinaryHeap(originalMaxHeap, 5);
    System.out.println(result);
  }

  private static List<Integer> findKLargestlnBinaryHeap(List<Integer> originalMaxHeap, int k) {
    List<Integer> result = new ArrayList<>();
    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<HeapEntry>(Comparator.reverseOrder());
    maxHeap.offer(new HeapEntry(originalMaxHeap.get(0), 0));
    for (int i = 0; i < k; i++) {
      HeapEntry heapEntry = maxHeap.poll();
      result.add(heapEntry.value);
      int leftChildIndex = heapEntry.index * 2 + 1;
      if (leftChildIndex < originalMaxHeap.size()) {
        maxHeap.offer(new HeapEntry(originalMaxHeap.get(leftChildIndex), leftChildIndex));
      }
      int rightChildIndex = heapEntry.index * 2 + 2;
      if (rightChildIndex < originalMaxHeap.size()) {
        maxHeap.offer(new HeapEntry(originalMaxHeap.get(rightChildIndex), rightChildIndex));
      }
    }

    return result;
  }

  private static class HeapEntry implements Comparable<HeapEntry> {
    int value;
    int index;

    public HeapEntry(int value, int index) {
      this.value = value;
      this.index = index;
    }

    @Override
    public int compareTo(HeapEntry o) {
      return Integer.compare(value, o.value);
    }
  }
}
