package epp.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromicDecomposition {
    public static void main(String[] args) {
        System.out.println(isPalindrome("madam"));
        List<List<String>> palindromes = getPalindromes("0204451881");
        System.out.println(palindromes);
    }

    private static List<List<String>> getPalindromes(String s) {
        System.out.println(s);
        if (s.length() < 2) {
            return List.of(List.of(s));
        }

        List<List<String>> newPalindromeSplits = new ArrayList<>();
        for (int i = s.length() - 1; i > 0; i--) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            if (isPalindrome(prefix)) {
                List<List<String>> palindromeSplits = getPalindromes(suffix);
                if (!palindromeSplits.isEmpty()) {

                    for (List<String> palindromeSplit : palindromeSplits) {
                        List<String> newPalindromeSplit = new ArrayList<>(palindromeSplit);
                        newPalindromeSplit.add(prefix);
                        newPalindromeSplits.add(newPalindromeSplit);
                    }


                }
            }

        }
          return newPalindromeSplits;
    }

    private static boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1);
    }

    private static boolean isPalindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        while (start < end && s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
        }

        return start >= end;
    }
}
