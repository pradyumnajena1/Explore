package epp.dp.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumPalindromicDecomposition {
  public static void main(String[] args) {
    String s = "0204451881";
    List<String> decomposition = decomposeIntoPalindromes(s);
    System.out.println(decomposition);
  }

  private static List<String> decomposeIntoPalindromes(String s) {
    Map<Integer, PartialResult> cache = new HashMap<>();
    decomposeIntoPalindromes(s, 0, cache);
    return buildSolution(s.length(), cache);
  }

  private static List<String> buildSolution(int length, Map<Integer, PartialResult> cache) {
    List<String> splits = new ArrayList<>();

    int start = 0;
    while (start < length && cache.containsKey(start)) {
      String prefix = cache.get(start).prefix;
      splits.add(prefix);
      start += prefix.length();
    }
    return start < length ? null : splits;
  }

  private static PartialResult decomposeIntoPalindromes(
      String s, int offset, Map<Integer, PartialResult> cache) {
    if (offset == s.length()) {
      return new PartialResult("", 0);
    }
    if (!cache.containsKey(offset)) {

      int minSplit = Integer.MAX_VALUE;
      PartialResult result = null;
      for (int j = offset + 1; j <= s.length(); j++) {
        String prefix = s.substring(offset, j);
        if (isPalindrome(prefix)) {
          PartialResult partialResult = decomposeIntoPalindromes(s, j, cache);
          if (partialResult != null) {
            if (partialResult.count + 1 < minSplit) {
              minSplit = partialResult.count + 1;
              result = new PartialResult(prefix, minSplit);
            }
          }
        }
      }
      cache.put(offset, result);
    }
    return cache.get(offset);
  }

  private static boolean isPalindrome(String string) {
    return isPalindrome(string, 0, string.length() - 1);
  }

  private static boolean isPalindrome(String string, int start, int end) {
    while (start < end) {
      if (string.charAt(start++) != string.charAt(end--)) {
        return false;
      }
    }
    return true;
  }

  private static class PartialResult {
    String prefix;
    int count;

    public PartialResult(String prefix, int count) {
      this.prefix = prefix;
      this.count = count;
    }
  }
}
