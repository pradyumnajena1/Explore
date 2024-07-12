package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindFirstOccurrence {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(20, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(findFirstOccurrence(values, values[7]));
        System.out.println(findLastOccurrence(values, values[7]));

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

    public static int findFirstOccurrence(int[] values, int value, int left, int right) {
        Integer index = null;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (value == values[mid]) {
                index = mid;
                right = mid - 1;
            } else if (value > values[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }


        }
        return index != null ? index : -(left + 1);
    }

    public static int findLastOccurrence(int[] values, int value, int left, int right) {
        Integer index = null;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (value == values[mid]) {
                index = mid;
                left = mid + 1;
            } else if (value > values[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }


        }
        return index != null ? index : -(left + 1);
    }
}
