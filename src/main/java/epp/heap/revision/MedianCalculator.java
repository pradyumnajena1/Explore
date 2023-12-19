package epp.heap.revision;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianCalculator {
    private PriorityQueue<Integer> smaller = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> bigger = new PriorityQueue<>();

    public void add(Integer value) {
        if (smaller.size() == bigger.size()) {
            if (smaller.size() == 0) {
                smaller.offer(value);
            } else {
                if (bigger.peek() < value) {
                    smaller.offer(bigger.poll());
                    bigger.offer(value);
                } else {
                    smaller.offer(value);
                }
            }
        } else {
            if (smaller.peek() > value) {
                bigger.offer(smaller.poll());
                smaller.offer(value);
            } else {
                bigger.offer(value);
            }
        }
    }

    public Integer getMedian() {
        if (smaller.size() > bigger.size()) {
            return smaller.peek();
        } else {
            return (smaller.peek() + bigger.peek()) / 2;
        }
    }
}
