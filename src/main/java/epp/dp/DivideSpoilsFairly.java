package epp.dp;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class DivideSpoilsFairly {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomUniqueArray(4,1,30);
        ArrayUtils.printArray(values);
        int minDiff = getMinDiffSplits(values);
        System.out.println(minDiff);
    }

    private static int getMinDiffSplits(int[] values) {
        int sum = Arrays.stream(values).sum();
        int maxValue = KnapsackProblem.getMaxValue(values, values, sum / 2);
        int remaining = sum-maxValue;
        System.out.println(maxValue+" "+remaining);
        return Math.abs(maxValue-remaining);
    }


}
