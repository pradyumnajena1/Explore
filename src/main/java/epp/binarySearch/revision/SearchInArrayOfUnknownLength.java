package epp.binarySearch.revision;

import java.util.Arrays;

public class SearchInArrayOfUnknownLength {
    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 4, 6, 8, 10, 15, 17, 21};
        int index = search(values, 21);
        System.out.println(index);
    }

    public static int search(int[] values, int value) {
        int p = 0;
        while (true) {
            try {
                int index = (int) Math.pow(2, p) - 1;
                int x = values[index];
                if (x == value) {
                    return index;
                } else if (x > value) {
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
            p++;

        }
        int fromIndex = p > 0 ? (int) Math.pow(2, p - 1) : 0;
        int toIndex = (int) Math.pow(2, p) - 2;
        return binarySearch(values, fromIndex, toIndex, value);
    }

    private static int binarySearch(int[] values, int fromIndex, int toIndex, int value) {
        int start = fromIndex;
        int end = toIndex - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            try {
                if (values[mid] == value) {
                    return mid;
                } else if (values[mid] < value) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                end = mid - 1;
            }
        }
        return -1;
    }

}
