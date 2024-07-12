package epp.binarySearch.revision;

public class ComputeIntegerSquareRoot {
  public static void main(String[] args) {
    int sqrt = computeSquareRoot(300);
    System.out.println(sqrt);
  }

  public static int computeSquareRoot(int value) {
    int left = 0;
    int right = value;
    // Candidate interval [left, right] where everything before left has
    // square <= k, and everything after right has square > k.
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int midSquared = mid * mid;
      if (midSquared <= value) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return left - 1;
  }
}
