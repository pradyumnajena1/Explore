package epp.recursion.revision;

import java.util.ArrayList;
import java.util.List;

public class PalindromicDecomposition {
  public static void main(String[] args) {
    String s = "0204451881";
    List<List<String>> palindromes = decomposeIntoPalindromes(s);
    System.out.println(palindromes);
  }

  private static List<List<String>> decomposeIntoPalindromes(String s) {
    List<List<String>> result = new ArrayList<>();
    List<String> partial = new ArrayList<>();
    decomposeIntoPalindromes(s, 0, partial, result);
    return result;
  }

  private static void decomposeIntoPalindromes(
      String s, int offset, List<String> partial, List<List<String>> result) {

    if (offset == s.length()) {
      result.add(new ArrayList<>(partial));
      return;
    }
    // try bigger prefix first as every single char is palindrome
    for (int i = offset + 1; i <= s.length(); i++) {
      String prefix = s.substring(offset, i);
      if (isPalindrome(prefix)) {
        partial.add(prefix);
        decomposeIntoPalindromes(s, i, partial, result);
        partial.remove(partial.size() - 1);
      }
    }
  }
  private static boolean isPalindrome(String string ) {
    return isPalindrome(string,0,string.length()-1);
  }
  private static boolean isPalindrome(String string, int start, int end) {
    while (start < end) {
      if (string.charAt(start++) != string.charAt(end--)) {
        return false;
      }
    }
    return true;
  }
}
