package epp.recursion.revision;

import java.util.HashSet;
import java.util.Set;

public class GenerateSubsetOfSizeK {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4};
        Set<Set<Integer>> result = generateSubsetsOfSizeK(values, 0, 3);
        System.out.println(result);
    }

    private static Set<Set<Integer>> generateSubsetsOfSizeK(int[] values, int start, int k) {
        if (k == 0) {
            HashSet<Set<Integer>> sets = new HashSet<>();
            sets.add(new HashSet<>());
            return sets;
        }
        if (start == values.length) {
            HashSet<Set<Integer>> sets = new HashSet<>();

            return sets;
        }
        Set<Set<Integer>> result = new HashSet<>();
        Set<Set<Integer>> setsContaining = generateSubsetsOfSizeK(values, start + 1, k - 1);
        for (Set<Integer> aset : setsContaining) {
            HashSet<Integer> e = new HashSet<>(aset);
            e.add(values[start]);
            result.add(e);
        }
        Set<Set<Integer>> setsNotContaining = generateSubsetsOfSizeK(values, start + 1, k);
        for (Set<Integer> aset : setsNotContaining) {
            HashSet<Integer> e = new HashSet<>(aset);
            result.add(e);
        }
        return result;
    }
}
