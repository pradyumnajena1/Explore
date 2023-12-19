package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindFirstOccurrence {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(20, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(findFirstOccurrence(values, 7));
        System.out.println(findLastOccurrence(values, 7));

        values = ArrayUtils.randomSortedArray(20, 2, 10);
        ArrayUtils.printArray(values);
        System.out.println(findFirstOccurrence(values, 11));
        System.out.println(findLastOccurrence(values, 11));

        values = new int[]{2, 3, 3, 3, 4, 5, 5, 5, 7, 7, 8, 8, 9, 9, 9, 9, 9, 9};
        ArrayUtils.printArray(values);
        System.out.println(findFirstOccurrence(values, 6));
        System.out.println(findLastOccurrence(values, 6));
    }

    public static int findFirstOccurrence(int[] values, int value) {
        return findFirstOccurrence(values, value, 0, values.length - 1);
    }

    public static int findLastOccurrence(int[] values, int value) {
        return findLastOccurrence(values, value, 0, values.length - 1);
    }

    public static int findFirstOccurrence(int[] values, int value, int start, int end) {
        Integer index = null;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (value == values[mid]) {
                index = mid;
                end = mid - 1;
            } else if (value > values[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }


        }
        return index != null ? index : -(start + 1);
    }

    public static int findLastOccurrence(int[] values, int value, int start, int end) {
        Integer index = null;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (value == values[mid]) {
                index = mid;
                start = mid + 1;
            } else if (value > values[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }


        }
        return index != null ? index : -(start + 1);
    }
}
