package amaze;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Given an array arr[] of size N, the task is to printing K largest elements in an array.
 * Note: Elements in output array can be in any order
 */
public class KLargest {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 30);
        ArrayUtils.printArray(values);
        int[] klargest = getLargest(values, 3);
        ArrayUtils.printArray(klargest);

        klargest = getLargest2(values, 3);
        ArrayUtils.printArray(klargest);
    }

    private static int[] getLargest(int[] values, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(values[i]);
        }
        for (int i = k; i < values.length; i++) {
            if (minHeap.peek() < values[i]) {
                minHeap.poll();
                minHeap.offer(values[i]);
            }
        }
        return new ArrayList<>(minHeap).stream().mapToInt(x -> x.intValue()).toArray();
    }

    private static int[] getLargest2(int[] values, int k) {
        int partition = partition(values, 0, values.length - 1);
        ArrayUtils.printArray(values);
        System.out.println(partition);
        while (values.length - partition != k) {

            if (values.length - partition > k) {
                partition = partition(values, partition + 1, values.length - 1);
            } else {
                partition = partition(values, 0, partition - 1);
            }
        }
        int[] res = new int[k];
        System.arraycopy(values, partition, res, 0, k);
        return res;
    }

    private static int partition(int[] values, int left, int right) {
        int pivot = values[right];
        int writePos = left;
        int readPos = left;
        while (readPos <= right) {
            if (values[readPos] <= pivot) {
                ArrayUtils.swap(values, writePos, readPos);
                readPos++;
                writePos++;
            } else {
                readPos++;
            }
        }
        return writePos - 1;
    }
}
