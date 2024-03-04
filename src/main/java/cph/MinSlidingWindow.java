package cph;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinSlidingWindow {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 20);
        int[] result = getMinValues(values, 4);
        ArrayUtils.printArray(values);
        ArrayUtils.printArray(result);
    }

    private static int[] getMinValues(int[] values, int windowSize) {
        int[] result = new int[values.length - windowSize + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < windowSize; i++) {
            while (deque.size() > 0 && deque.peekLast() > values[i]) {
                deque.pollLast();
            }
            deque.offerLast(values[i]);
        }
        result[0] = deque.peekFirst();
        for (int i = windowSize; i < values.length; i++) {

            if (deque.peekFirst() == values[i - windowSize]) {
                deque.pollFirst();
            }
            while (deque.size() > 0 && deque.peekLast() > values[i]) {
                deque.pollLast();
            }
            deque.offerLast(values[i]);
            result[i - windowSize + 1] = deque.peekFirst();

        }
        return result;
    }
}
