package epp.honours;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestSubstringWithMatchingParen {
  public static void main(String[] args) {
    String s = "((())()(()(";
    int longestMatchingString = longestMatchingSubstring(s);
    System.out.println(longestMatchingString);
  }

  private static int longestMatchingSubstring(String s) {
    // TODO: Implement this method to find the longest substring in the given string
    Deque<Integer> stack = new ArrayDeque<Integer>();
    int end = -1;
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else if (stack.isEmpty()) {
        end = i;
      } else {
        stack.pop();
        int start = stack.isEmpty() ? end : stack.peek();
        max = Math.max(max, i - start);
      }
    }
    return max;
  }
}
