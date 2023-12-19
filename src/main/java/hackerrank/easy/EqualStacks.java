package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;

public class EqualStacks {
    public static void main(String[] args) {
        System.out.println(equalStacks(new ArrayList<>(List.of(1, 2, 1, 1)), new ArrayList<>(List.of(1, 1, 2)), new ArrayList<>(List.of(1, 1))));
        System.out.println(equalStacks(new ArrayList<>(List.of(3, 2, 1, 1, 1)), new ArrayList<>(List.of(4, 3, 2)), new ArrayList<>(List.of(1, 1, 4, 1))));
    }

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        // Write your code here
        int h1Sum = h1.stream().mapToInt(Integer::intValue).sum();
        int h2Sum = h2.stream().mapToInt(Integer::intValue).sum();
        int h3Sum = h3.stream().mapToInt(Integer::intValue).sum();


        while (!(h1Sum == h2Sum && h2Sum == h3Sum)) {

            Pair<Integer, List<Integer>> max = getMax(h1, h2, h3, h1Sum, h2Sum, h3Sum);
            Integer remove = max.getSecond().remove(0);
            h1Sum = max.getSecond() == h1 ? h1Sum - remove : h1Sum;
            h2Sum = max.getSecond() == h2 ? h2Sum - remove : h2Sum;
            h3Sum = max.getSecond() == h3 ? h3Sum - remove : h3Sum;

        }
        return h2Sum;

    }

    private static Pair<Integer, List<Integer>> getMax(List<Integer> h1, List<Integer> h2, List<Integer> h3, int h1Sum, int h2Sum, int h3Sum) {
        List<Integer> maxStack;
        int maxSum = 0;
        if (h1Sum >= h2Sum && h1Sum >= h3Sum) {
            maxSum = h1Sum;
            maxStack = h1;
        } else if (h2Sum >= h3Sum && h2Sum >= h1Sum) {
            maxSum = h2Sum;
            maxStack = h2;
        } else {
            maxSum = h3Sum;
            maxStack = h3;
        }
        return new Pair<>(maxSum, maxStack);
    }
}
