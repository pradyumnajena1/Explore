package epp.honours;

import epp.array.ArrayUtils;

import java.util.StringJoiner;

public class LongestIncreasingSubArray {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 1, 20);
    ArrayUtils.printArray(values);
    SubArray longestIncreasingSubArray = getLongestIncreasingSubArray(values);
    System.out.println(longestIncreasingSubArray);

    longestIncreasingSubArray = getLongestIncreasingSubArray2(values);
    System.out.println(longestIncreasingSubArray);
  }

  /**
   * complexity o(n)
   *
   * @param values
   * @return
   */
  public static SubArray getLongestIncreasingSubArray(int[] values) {
    SubArray result = new SubArray(0, 0);
    SubArray prev = new SubArray(0, 0);
    for (int i = 1; i < values.length; i++) {
      SubArray current;
      if (values[i] > values[i - 1]) {
        current = new SubArray(prev.start, i);
      } else {
        current = new SubArray(i, i);
      }
      if (current.length() > result.length()) {
        result = current;
      }
      prev = current;
    }
    return result;
  }

  /**
   * complexity O(n) but more optimized than previous implementation
   *
   * @param values
   * @return
   */
  public static SubArray getLongestIncreasingSubArray2(int[] values) {
    int maxLength = 1;
    SubArray result = new SubArray(0, 0);
    int i = 1;
    while (i < values.length - maxLength) {
      // backward check
      boolean isSkipable = false;
      for (int j = i + maxLength; j > i; j--) {
        if (values[j - 1] >= values[j]) {
          i = j;
          isSkipable = true;
          break;
        }
      }
      // check forward
      if (!isSkipable) {
        i += maxLength;
        while (i < values.length && values[i] >= values[i - 1]) {
          i++;
          ++maxLength;
        }
        result = new SubArray(i - maxLength, i - 1);
      }
    }
    return result;
  }

  private static class SubArray {

    int start;
    int end;

    public SubArray(int start, int end) {
      this.start = start;
      this.end = end;
    }

    int length() {
      return end - start + 1;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", SubArray.class.getSimpleName() + "[", "]")
              .add("start=" + start)
              .add("end=" + end)
              .toString();
    }
  }
}
