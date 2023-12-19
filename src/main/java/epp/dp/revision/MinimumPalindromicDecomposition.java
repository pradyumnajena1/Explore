package epp.dp.revision;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumPalindromicDecomposition {
    public static void main(String[] args) {
        String s = "0204451881";
        List<String> decomposition = getMinimumPalindromicDecomposition(s);
        System.out.println(decomposition);
    }

    private static List<String> getMinimumPalindromicDecomposition(String s) {
        Map<Pair<Integer, Integer>, List<String>> cache = new HashMap<>();
        return getMinimumPalindromicDecomposition(s.toCharArray(), 0, s.length() - 1, cache);
    }

    private static List<String> getMinimumPalindromicDecomposition(char[] characters, int start, int end, Map<Pair<Integer, Integer>, List<String>> cache) {
        if (start > end) {
            return new ArrayList<>();
        }
        if (start == end) {
            ArrayList<String> result = new ArrayList<>();
            result.add(new String(characters, start, end - start + 1));
            return result;
        }
        Pair<Integer, Integer> key = new Pair<>(start, end);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int min = Integer.MAX_VALUE;
        List<String> result = null;
        for (int i = start; i <= end; i++) {
            if (isPalindrome(characters, start, i)) {
                List<String> splits = getMinimumPalindromicDecomposition(characters, i + 1, end, cache);
                if (splits != null && splits.size() < min) {
                    min = splits.size();
                    result = new ArrayList<>(splits);
                    result.add(new String(characters, start, i - start + 1));
                }
            }
        }
        cache.put(key, result);
        return result;
    }

    private static boolean isPalindrome(char[] characters, int start, int end) {
        while (start < end) {
            if (characters[start++] != characters[end--]) {
                return false;
            }
        }
        return true;
    }
}
