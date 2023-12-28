package guidetocompetitiveprogramming;

import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MinMaxAvgSlidingWindow {
    public static void main(String[] args) {
        int[] values =new int[]{12, 7, 8, 22, 19, 2, 23, 20, 25, 26};// ArrayUtils.randomArray(10, 1, 30);
        ArrayUtils.printArray(values);
        Triplet<Integer, Integer, Double>[] minMaxAvg = getMinMaxAvg(values, 4);
        ArrayUtils.printArray(minMaxAvg);
    }

    private static Triplet<Integer, Integer, Double>[] getMinMaxAvg(int[] values, int windowSize) {
        int[] min = getMin(values, windowSize);
        int[] max = getMax(values, windowSize);
        double[] avg = getAvg(values, windowSize);
        ArrayUtils.printArray(min);
        ArrayUtils.printArray(max);
        ArrayUtils.printArray(avg);
        Triplet<Integer, Integer, Double>[] minMaxAvg = new Triplet[min.length];
        for (int i = 0; i < minMaxAvg.length; i++) {
            minMaxAvg[i] = new Triplet<>(min[i], max[i], avg[i]);
        }
        return minMaxAvg;
    }

    private static double[] getAvg(int[] values, int windowSize) {
        double[] avg = new double[values.length - windowSize + 1];
        double sum = 0;
        for (int i = 0; i < windowSize; i++) {

            sum += values[i];
        }
        for (int i = 0; i + windowSize - 1 < values.length; i++) {
            double avgValue = sum / windowSize;
            avg[i] = avgValue;
            if (i + windowSize < values.length) {
                sum += values[i + windowSize];
                sum -= values[i];
            }


        }
        return avg;
    }

    private static int[] getMax(int[] values, int windowSize) {
        int[] max = new int[values.length - windowSize + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < windowSize; i++) {
            while (!queue.isEmpty() && values[queue.peekLast()] <= values[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        for (int i = 0; i + windowSize - 1 < values.length; i++) {
            Integer maxIndex = queue.peekFirst();
            max[i] = values[maxIndex];

            if (i + windowSize < values.length) {
                while (!queue.isEmpty() && values[queue.peekLast()] <= values[i + windowSize]) {
                    queue.pollLast();
                }
                queue.offerLast(i + windowSize);
                if (i == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }


        }
        return max;
    }

    private static int[] getMin(int[] values, int windowSize) {
        int[] min = new int[values.length - windowSize + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < windowSize; i++) {
            while (!queue.isEmpty() && values[queue.peekLast()] >= values[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        System.out.println(queue);
        for (int i = 0; i + windowSize - 1 < values.length; i++) {

            Integer minIndex = queue.peekFirst();
            min[i] = values[minIndex];

            if (i + windowSize < values.length) {
                while (!queue.isEmpty() && values[queue.peekLast()] >= values[i + windowSize]) {
                    queue.pollLast();
                }
                queue.offerLast(i + windowSize);
                if (i == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }


        }
        return min;
    }
}
