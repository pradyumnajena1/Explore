package epp.binarySearch.revision;

/**
 * Given two positive floating point numbers x and y,how would you compute * to within a specified
 * tolerance e if the division operator cannot be used? You cannot use any library functions, such
 * as log and exp; addition and multiplication are acceptable
 */
public class ComputeDivisionWithoutUsingDivision {
  public static void main(String[] args) {
    System.out.println(computeDivisionWithoutUsingDivision(10, 3, 0.00001));
  }

  private static double computeDivisionWithoutUsingDivision(double x, double y, double epsilon) {
    double left, right;
    if (compare(x, y, epsilon) == Ordering.SMALLER) {
      left = 0.0;
      right = 1;
    } else {
      left = 1;
      right = x;
    }
    while (compare(left, right, epsilon) == Ordering.SMALLER) {
      double mid = (left + right) / 2;
      double result = mid * y;
      if (compare(result, x, epsilon) == Ordering.EQUAL) {
        return mid;
      } else if (compare(result, x, epsilon) == Ordering.SMALLER) {
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
