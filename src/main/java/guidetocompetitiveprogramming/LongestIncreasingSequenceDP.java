package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class LongestIncreasingSequenceDP {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 10);
        ArrayUtils.printArray(values);
        int length = longestIncreasingSequence(values);
        System.out.println(length);
    }

    private static int longestIncreasingSequence(int[] values) {
        int maxSequenceLength = 0;
        int[] sequenceLength = new int[values.length];

        for (int i = 0; i < values.length; i++) {
            sequenceLength[i] = 1;
            for (int j = 0; j < i; j++) {
                if (values[j] < values[i]) {
                    sequenceLength[i] = Math.max(sequenceLength[i], sequenceLength[j] + 1);
                }
            }
            maxSequenceLength = Math.max(maxSequenceLength, sequenceLength[i]);
        }
        return maxSequenceLength;
    }
}
