package epp.array.revision;

import commons.IntegerUtils;
import epp.array.ArrayUtils;

import java.util.Arrays;

public class ArrayShiftUtils {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        rotateArrayRight(values, 2);
        ArrayUtils.printArray(values);

        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.printArray(values);
        shiftRight(values, 3, 2, 7);
        ArrayUtils.printArray(values);

        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.printArray(values);
        shiftLeft(values, 3 );
        ArrayUtils.printArray(values);

        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        ArrayUtils.printArray(values);
        shiftLeft(values, 3,2,7 );
        ArrayUtils.printArray(values);
    }

    private static void rotateArrayRight(int[] values, int numPlaces) {
        ArrayUtils.reverse(values, 0, values.length - numPlaces - 1);
        ArrayUtils.reverse(values, values.length - numPlaces, values.length - 1);
        ArrayUtils.reverse(values, 0, values.length - 1);
    }

    public static void shiftRight(int[] values, int numPlaces) {
        shiftRight(values, numPlaces, 0, values.length - 1);
    }
    public static void shiftLeft(int[] values, int numPlaces) {
        shiftLeft(values, numPlaces, 0, values.length - 1);
    }

    public static void shiftRight(int[] values, int numPlaces, int low, int high) {
        int length = high - low + 1;
        numPlaces = numPlaces % length;
        int numCycle = IntegerUtils.gcd(length, numPlaces);
        int cycleLength = length / numCycle;
        for (int i = 0; i < numCycle; i++) {
            applyCyclicPermutationRight(values, i, cycleLength, numPlaces, low, high);
        }
    }
    public static void shiftLeft(int[] values, int numPlaces, int low, int high) {
        int length = high - low + 1;
        numPlaces = numPlaces % length;
        int numCycle = IntegerUtils.gcd(length, numPlaces);
        int cycleLength = length / numCycle;
        for (int i = 0; i < numCycle; i++) {
            applyCyclicPermutationLeft(values, i, cycleLength, numPlaces, low, high);
        }
    }

    private static void applyCyclicPermutationRight(int[] values, int offset, int cycleLength, int numPlaces, int low, int high) {
           int length = high - low + 1;
        int delta = numPlaces;
        for (int i = 1; i < cycleLength; i++) {

            int next = low + (offset + delta ) % length;
            ArrayUtils.swap(values, low + offset, next);
            delta = (delta + numPlaces)%length;
        }

    }
    private static void applyCyclicPermutationLeft(int[] values, int offset, int cycleLength, int numPlaces, int low,
                                                int high) {
        int length = high - low + 1;
        for (int i = 1; i < cycleLength; i++) {
            int next = high -  (offset + numPlaces * i) % length;
            ArrayUtils.swap(values, high - offset, next);
        }

    }
}
