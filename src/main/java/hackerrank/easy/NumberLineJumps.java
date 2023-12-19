package hackerrank.easy;

public class NumberLineJumps {
    public static void main(String[] args) {
        System.out.println(kangaroo(2, 1, 1, 2));
        System.out.println(kangaroo(0, 3, 4, 2));
        System.out.println(kangaroo(0, 2, 5, 3));
    }

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        // Write your code here

        if (v1 < v2) {
            if (x1 <= x2) {
                return "NO";
            } else {
                return (x1 - x2) % (v2 - v1) == 0 ? "YES" : "NO";
            }

        } else if (v1 == v2) {
            return x1 == x2 ? "YES" : "NO";
        } else {
            if (x1 >= x2) {
                return "NO";
            } else {
                return (x2 - x1) % (v1 - v2) == 0 ? "YES" : "NO";
            }
        }

    }
}
