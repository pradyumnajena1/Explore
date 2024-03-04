package cph;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {
    public static void main(String[] args) {
        int[] values = new int[]{8, 1, 15, 2, 19, 7, 16, 16, 18, 8};
        ArrayUtils.printArray(values);
        System.out.println(find2Sum3(values, 10));
        System.out.println(find2Sum(values, 10));
        System.out.println(find2Sum2(values, 10));
    }

    private static Pair<Integer, Integer> find2Sum(int[] values, int sum) {
        Arrays.sort(values);
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            int currentSum = values[left] + values[right];
            if (currentSum == sum) {
                return new Pair<>(values[left], values[right]);
            } else if (currentSum > sum) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }

    private static Pair<Integer, Integer> find2Sum2(int[] values, int sum) {
        Arrays.sort(values);
        for (int i = 1; i < values.length; i++) {
            int index = Arrays.binarySearch(values, 0, i, sum - values[i]);
            if (index >= 0) {
                return new Pair<>(values[index], values[i]);
            }
        }
        return null;
    }

    public static Pair<Integer, Integer> find2Sum3(int[] values, int sum) {
        return find2SumHelper(values, sum,0,values.length-1);
    }

    public static Pair<Integer, Integer> find2SumHelper(int[] values, int sum, int start, int end) {
        if(start<0 || start>= values.length || end<0||end>= values.length){
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = start; i <= end; i++) {
            Integer index = indexMap.get(sum - values[i]);
            if (index != null) {
                return new Pair<>(values[index], values[i]);
            }
            indexMap.put(values[i], i);
        }
        return null;
    }
}
