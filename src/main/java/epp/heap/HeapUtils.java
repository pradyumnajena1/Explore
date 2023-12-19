package epp.heap;

import epp.array.ArrayUtils;

import java.util.Comparator;

public class HeapUtils {
    public static void main(String[] args) {
        Integer[] values = new Integer[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        createMaxHeap(values);
        ArrayUtils.printArray(values);
        values = new Integer[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        createMinHeap(values);
        ArrayUtils.printArray(values);
    }

    public static <T extends Comparable<T>> void createMaxHeap(T[] values) {
        createMaxHeap(values, Comparator.reverseOrder());
    }

    public static <T extends Comparable<T>> void createMaxHeap(T[] values, Comparator<T> comparator) {
        int lastNonLeafNode = values.length / 2 - 1;
        for (int i = lastNonLeafNode; i >= 0; i--) {
            heapifyDown(values, i, comparator);
        }
    }

    public static <T extends Comparable<T>> void createMinHeap(T[] values) {
        createMinHeap(values, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> void createMinHeap(T[] values, Comparator<T> comparator) {
        int lastNonLeafNode = values.length / 2 - 1;
        for (int i = lastNonLeafNode; i >= 0; i--) {
            heapifyDown(values, i, comparator);
        }
    }

    public static <T extends Comparable<T>> void heapifyDown(T[] values, int i, Comparator<T> comparator) {
        int minimum = i;
        int left = getLeft(i);
        int right = getRight(i);
        if (left < values.length && comparator.compare(values[left], values[minimum]) < 0) {
            minimum = left;
        }
        if (right < values.length && comparator.compare(values[right], values[minimum]) < 0) {
            minimum = right;
        }
        if (minimum != i) {
            ArrayUtils.swap(values, i, minimum);
            heapifyDown(values, minimum, comparator);
        }

    }

    public static int getRight(int i) {
        return i * 2 + 2;
    }

    public static int getLeft(int i) {
        return i * 2 + 1;
    }
}
