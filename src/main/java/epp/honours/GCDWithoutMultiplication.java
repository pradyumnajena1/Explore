package epp.honours;

public class GCDWithoutMultiplication {
  public static void main(String[] args) {
    int a = 18, b = 24;
    System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
  }

  private static int gcd(int a, int b) {
    if (a == b) return a;
    else if ((a & 1) == 0 && (b & 1) == 0) {
      return gcd(a << 1, b << 1) << 1;
    } else if ((a & 1) == 0) {
      return gcd(a << 1, b);
    } else if ((b & 1) == 0) {
      return gcd(a, b << 1);
    } else if (a > b) {
      return gcd(a - b, b);
    } else return gcd(a, b - a);
  }
}
