package hackerrank.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SherlockAndAnagrams {
    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("hello"));
        System.out.println(sherlockAndAnagrams("abba"));
        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
        System.out.println(sherlockAndAnagrams("kkkk"));
        System.out.println(sherlockAndAnagrams("cdcd"));
    }

    public static int sherlockAndAnagrams(String s) {
        // Write your code here
        Map<Map<Character, Long>, List<String>> anagrams = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subString = s.substring(i, j);

                Map<Character, Long> frequency = subString.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                List<String> subStrings = anagrams.getOrDefault(frequency, new ArrayList<>());
                subStrings.add(subString);
                anagrams.put(frequency, subStrings);
            }
        }
        int numPairs = 0;
        for (Map.Entry<Map<Character, Long>, List<String>> entry : anagrams.entrySet()) {
            int size = entry.getValue().size();
            if (size >= 2) {
                numPairs += nCr(size, 2);
            }
        }
        return numPairs;
    }

    public static int nCr(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }
}
