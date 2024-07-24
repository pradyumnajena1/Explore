package epp.honours;

import epp.ListUtils;
import epp.binarySearch.revision.FindTheKthElement;
import java.util.*;

public class FindKthLargestUnknownLength {
  public static void main(String[] args) {
    List<Integer> values = ListUtils.randomList(20, 1, 30);
    System.out.println(values);
    int kthLargest = findKthLargestUnknownLength(values.iterator(), 3);
    System.out.println(kthLargest);

    kthLargest = findKthLargestUnknownLength2(values.iterator(), 3);
    System.out.println(kthLargest);
  }

  /**
   * time complexity O(nlogk)
   *
   * @param iterator
   * @param k
   * @return
   */
  public static int findKthLargestUnknownLength(Iterator<Integer> iterator, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    while (iterator.hasNext()) {
      Integer next = iterator.next();
      if (minHeap.size() < k) {
        minHeap.offer(next);
      } else {
        if (minHeap.peek() < next) {
          minHeap.poll();
          minHeap.offer(next);
        }
      }
    }
    return minHeap.peek();
  }

  /**
   * time complexity O(n)
   * @param iterator
   * @param k
   * @return
   */
  public static int findKthLargestUnknownLength2(Iterator<Integer> iterator, int k) {
    List<Integer> candidates = new ArrayList<>();

    while (iterator.hasNext()) {
      int next = iterator.next();
      candidates.add(next);
      if (candidates.size() == 2 * k - 1) {
        FindTheKthElement.findKthItem(
            candidates, k, 0, candidates.size() - 1, Comparator.reverseOrder());
        candidates.subList(k, candidates.size()).clear();
      }
    }
    FindTheKthElement.findKthItem(
        candidates, k, 0, candidates.size() - 1, Comparator.reverseOrder());
    return candidates.get(k - 1);
  }


}
