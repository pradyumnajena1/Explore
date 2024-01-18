package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        int lcs = longestCommonSubSequence("tour", "opera");
        System.out.println(lcs);
    }

    private static int longestCommonSubSequence(String a, String b) {
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return longestCommonSubSequence(a, b, a.length(), b.length(), cache);
    }

    private static int longestCommonSubSequence(String a, String b, int i, int j, Map<Pair<Integer, Integer>, Integer> cache) {
        if (i == 0 || j == 0) {
            return 0;
        }
        Pair<Integer, Integer> key = new Pair<>(i, j);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int result;
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
            result = longestCommonSubSequence(a, b, i - 1, j - 1, cache) + 1;
        } else {

            result = Math.max(longestCommonSubSequence(a, b, i - 1, j, cache), longestCommonSubSequence(a, b, i, j - 1, cache));
        }
        cache.put(key, result);
        return result;
    }
}
