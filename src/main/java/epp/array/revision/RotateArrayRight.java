package epp.array.revision;

import epp.array.ArrayUtils;

public class RotateArrayRight {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        rotateArrayRight(values, 3);
        ArrayUtils.printArray(values);

        values = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotateArrayRight2(values, 3);
        ArrayUtils.printArray(values);
    }

    private static void rotateArrayRight(int[] values, int numPlaces) {
        ArrayUtils.reverse(values, 0, values.length - numPlaces - 1);
        ArrayUtils.reverse(values, values.length - numPlaces, values.length - 1);
        ArrayUtils.reverse(values, 0, values.length - 1);
    }

    private static void rotateArrayRight2(int[] values, int numPlaces) {
        int current = 0;
        int currentValue = values[current];
        do {

            int next = (current + numPlaces) % values.length;
            int nextValue = values[next];

            values[next] = currentValue;

            current = next;
            currentValue = nextValue;

        } while (current != 0);
    }
}
