package epp.hashmap.revision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindTheLengthOfALongestContainedInterval {

  public static void main(String[] args) {
    ArrayList<Integer> values = new ArrayList<>(List.of(10, 5, 3, 11, 6, 100, 4));
    int length = findTheLengthOfALongestContainedInterval(values);
    System.out.println(length);
  }

  private static int findTheLengthOfALongestContainedInterval(ArrayList<Integer> values) {
    int result = 0;
    Set<Integer> unprocessedEntries = new HashSet<>(values);
    while (unprocessedEntries.size() > 0) {
      int next = unprocessedEntries.iterator().next();
      unprocessedEntries.remove(next);
      int left = next - 1;
      while (unprocessedEntries.contains(left)) {
        unprocessedEntries.remove(left);
        left--;
      }
      int right = next + 1;
      while (unprocessedEntries.contains(right)) {
        unprocessedEntries.remove(right);
        right++;
      }
      result = Math.max(result, right - left - 1);
    }
    return result;
  }
}
