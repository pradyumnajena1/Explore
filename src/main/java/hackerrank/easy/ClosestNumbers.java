package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;

public class ClosestNumbers {
    public static void main(String[] args) {
        System.out.println(closestNumbers(new ArrayList<>(List.of(5, 2, 1, 3, 4))));
    }

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        arr.sort(Integer::compareTo);
        int minDiff = Integer.MAX_VALUE;
        List<Pair<Integer, Integer>> minPairs = new ArrayList<>();
        for (int i = 1; i < arr.size(); i++) {
            int newDiff = Math.abs(arr.get(i) - arr.get(i - 1));
            if (newDiff < minDiff) {
                minDiff = newDiff;
                minPairs = new ArrayList<>();
                minPairs.add(new Pair<>(arr.get(i - 1), arr.get(i)));
            } else if (newDiff == minDiff) {
                minPairs.add(new Pair<>(arr.get(i - 1), arr.get(i )));
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Pair<Integer, Integer> pair : minPairs) {
            result.add(pair.getFirst());
            result.add(pair.getSecond());
        }
        return result;
    }
}
