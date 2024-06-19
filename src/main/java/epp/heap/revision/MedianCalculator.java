package epp.heap.revision;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianCalculator {
  public static void main(String[] args){
    MedianCalculator calculator = new MedianCalculator();
    calculator.add(1);
    System.out.println(calculator.getMedian());
    calculator.add(0);
    System.out.println(calculator.getMedian());
    calculator.add(3);
    System.out.println(calculator.getMedian());
    calculator.add(5);
    System.out.println(calculator.getMedian());
    calculator.add(2);
    System.out.println(calculator.getMedian());
    calculator.add(0);
    System.out.println(calculator.getMedian());
    calculator.add(1);
    System.out.println(calculator.getMedian());
  }
  private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
  private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

  public void add(Integer value) {
    if (maxHeap.size() == 0) {
      maxHeap.offer(value);
    } else {
      if (value <= maxHeap.peek()) {
        maxHeap.offer(value);
      } else {
        minHeap.offer(value);
      }
    }

    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
  }

  public Double getMedian() {
    if (maxHeap.size() > minHeap.size()) {
      return Double.valueOf(maxHeap.peek());
    } else {
      return Double.valueOf(maxHeap.peek() + minHeap.peek()) / 2;
    }
  }
}
