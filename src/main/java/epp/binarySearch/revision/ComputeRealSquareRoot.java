package epp.binarySearch.revision;

public class ComputeRealSquareRoot {
  private static double epsilon = 0.0000000001;

  public static void main(String[] args) {
    double value = 10;
    double sqrt = computeRealSquareRoot(value, epsilon);
    System.out.println(sqrt);
  }

  private static double computeRealSquareRoot(double value, double epsilon) {
    double left;
    double right;
    if (value <= 1.0) {
      left = value;
      right = 1.0;
    } else {
      left = 1.0;
      right = value;
    }
    while (compare(left, right, epsilon) == Ordering.SMALLER) {
      double mid = (left + right) / 2.0;
      Ordering compare = compare(mid * mid, value, epsilon);
      if (compare == Ordering.EQUAL) {
        return mid;
      } else if (compare == Ordering.SMALLER) {
        left = mid;
      } else {
        right = mid;
      }
    }
    return left;
  }

  private static Ordering compare(double a, double b, double epsilon) {
    double diff = (a - b) / b;
    return diff < -epsilon ? Ordering.SMALLER : diff > epsilon ? Ordering.LARGER : Ordering.EQUAL;
  }

  private static enum Ordering {
    SMALLER,
    EQUAL,
    LARGER
  }
}
