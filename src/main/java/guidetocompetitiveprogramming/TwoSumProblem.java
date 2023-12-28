package guidetocompetitiveprogramming;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumProblem {
    public static void main(String[] args) {
        int[] values = new int[]{1, 2, 2, 8, 8, 6, 4, 4, 9, 10, 12}; // ArrayUtils.randomArray(10, 1, 20);
        System.out.println(findAllTwoSums(values, 10));
        System.out.println(findAllThreeSums(values, 10));
    }

    public static List<Triplet<Integer, Integer, Integer>> findAllThreeSums(int[] values, int sum) {
        Arrays.sort(values);
        ArrayUtils.printArray(values);
        List<Triplet<Integer, Integer, Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            ArrayList<Pair<Integer, Integer>> twoSumPairs = findAllTwoSumPairs(values, sum - values[i], 0, i - 1);
            for (Pair<Integer, Integer> pair : twoSumPairs) {

                triplets.add(new Triplet<>(pair.getFirst(), pair.getSecond(), values[i]));
            }
        }
        return triplets;
    }


    public static List<Pair<Integer, Integer>> findAllTwoSums(int[] values, int sum) {
        Arrays.sort(values);
        ArrayUtils.printArray(values);
        int left = 0;
        int right = values.length - 1;
        return findAllTwoSumPairs(values, sum, left, right);
    }

    public static ArrayList<Pair<Integer, Integer>> findAllTwoSumPairs(int[] values, int sum, int left, int right) {
        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
        while (left < right) {
            int s = values[left] + values[right];
            if (s == sum) {
                int start = right;
                while (start > left && values[start] == values[right]) {
                    pairs.add(new Pair<>(left, start));
                    start--;
                }
                left++;

            } else if (s < sum) {
                left++;
            } else {
                right--;
            }
        }
        return pairs;
    }
}
