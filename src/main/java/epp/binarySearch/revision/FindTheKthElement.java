package epp.binarySearch.revision;

import epp.ListUtils;
import epp.array.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;

public class FindTheKthElement {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(values);
        int min5 = findKthMinItem(values, 5);
        ArrayUtils.printArray(values);
        System.out.println(min5);

        values = new int[]{99, 97, 96, 84, 96, 25, 72, 53, 93};// ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(values);
        int max5 = findKthMaxItem(values, 5);
        ArrayUtils.printArray(values);
        System.out.println(max5);


    }

    public static int findKthMinItem(int[] array, int k) {
        return findKthItem(array, k, 0, array.length - 1, Comparator.naturalOrder());
    }
    public static int findKthMaxItem(int[] array, int k) {
        return findKthItem(array, k, 0, array.length - 1, Comparator.reverseOrder());
    }


    public static int findKthItem(int[] values, int k, int start, int end, Comparator<Integer> comparator) {
        int value = 0;
        int left = start;
        int right = end;
        while (left <= right) {
            int partition = partition(values, left, right, comparator);
            if (partition - start == k - 1) {
                value = values[partition];
                break;
            } else if (partition - start > k - 1) {
                right = partition - 1;
            } else {
                left = partition + 1;
            }
        }
        return value;

    }
    public static <T> T findKthItem(T[] values, int k, Comparator<T> comparator) {
     return findKthItem(values,k,0, values.length-1, comparator);
    }

    public static <T> T findKthItem(List<T> values, int k, Comparator<T> comparator) {
        return findKthItem(values,k,0, values.size()-1, comparator);
    }



        public static <T> T findKthItem(T[] values, int k, int start, int end, Comparator<T> comparator) {
        T value = null;
        int left = start;
        int right = end;
        while (left <= right) {
            int partition = partition(values, left, right, comparator);
            if (partition - start == k - 1) {
                value = values[partition];
                break;
            } else if (partition - start > k - 1) {
                right = partition - 1;
            } else {
                left = partition + 1;
            }
        }
        return value;

    }

    public static <T> T findKthItem(List<T> values, int k, int start, int end, Comparator<T> comparator) {
        T value = null;
        int left = start;
        int right = end;
        while (left <= right) {
            int partition = partition(values, left, right, comparator);
            if (partition - start == k - 1) {
                value = values.get(partition);
                break;
            } else if (partition - start > k - 1) {
                right = partition - 1;
            } else {
                left = partition + 1;
            }
        }
        return value;

    }




    public static int partition(int[] values, int start, int end,  Comparator<Integer> comparator) {
        int readPosition = start;
        int writePosition = start;
        int pivot = values[end];
        while (readPosition <= end) {
            if (comparator.compare(values[readPosition], pivot) <= 0) {
                ArrayUtils.swap(values, readPosition, writePosition++);
            }
            readPosition++;
        }
        return writePosition - 1;
    }

    public static<T> int partition(T[] values, int start, int end,  Comparator<T> comparator) {
        int readPosition = start;
        int writePosition = start;
        T pivot = values[end];
        while (readPosition <= end) {
            if (comparator.compare(values[readPosition], pivot) <= 0) {
                ArrayUtils.swap(values, readPosition, writePosition++);
            }
            readPosition++;
        }
        return writePosition - 1;
    }
    public static<T> int partition(List<T> values, int start, int end,  Comparator<T> comparator) {
        int readPosition = start;
        int writePosition = start;
        T pivot = values.get(end);
        while (readPosition <= end) {
            if (comparator.compare(values.get(readPosition), pivot) <= 0) {
                ListUtils.swap(values, readPosition, writePosition++);
            }
            readPosition++;
        }
        return writePosition - 1;
    }


}
