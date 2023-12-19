package epp.recursion.revision;

import java.util.HashSet;
import java.util.Set;

public class PalindromicDecomposition {
    public static void main(String[] args) {
        String s = "0204451881";
        Set<String> palindromes = decomposeIntoPalindromes(s);
        System.out.println(palindromes);
    }

    private static Set<String> decomposeIntoPalindromes(String s) {
        return decomposeIntoPalindromes(s, 0);
    }

    private static Set<String> decomposeIntoPalindromes(String s, int start ) {
        HashSet<String> result = new HashSet<>();
       if (start == s.length()) {
            return result;
        }
         // try bigger prefix first as every single char is palindrome
        for (int i = s.length(); i >=start+1  ; i--) {
            String prefix = s.substring(start, i);

            if (isPalindrome(prefix, 0,prefix.length()-1)  ) {
                Set<String> palindromeSplits = decomposeIntoPalindromes(s, i );
                if(palindromeSplits!=null){
                    result.add(prefix);
                    result.addAll(palindromeSplits);
                    return result;
                }

            }
        }
        return null;
    }



    private static boolean isPalindrome(String string, int start, int end) {
        while (start < end) {
            if (string.charAt(start) != string.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
