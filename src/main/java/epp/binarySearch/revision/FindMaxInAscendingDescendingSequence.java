package epp.binarySearch.revision;

import java.util.Arrays;

/**
 * A sequence is strictly ascending if each element is greater than its prede¬
 * cessor. Suppose it is known that an array A consists of a strictly ascending sequence
 * followed by a strictly a strictly descending sequence. Design an algorithm for finding
 * the maximum element in A.
 */
public class FindMaxInAscendingDescendingSequence {
    public static void main(String[] args){
        int[] values = new int[]{1,2,3,4,9,6,2};
        System.out.println(Arrays.toString(values));
        int max = findMax(values);
        System.out.println(max);
    }

    private static int findMax(int[] values) {
        int left = 0;
        int right = values.length-1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (values[mid] > values[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
