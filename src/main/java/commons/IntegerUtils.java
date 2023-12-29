package commons;

import java.util.List;

public class IntegerUtils {
   public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public static int findLCM(List<Integer> arr) {
        int result = arr.get(0);
        for (int element : arr) {
            if (element == 0) {
                return 0;
            }
            result = result * element / gcd(result, element);
        }

        return result;
    }

    public static int findGCD(List<Integer> arr) {
        int result = arr.get(0);
        for (int element : arr) {
            result = gcd(result, element);

            if (result == 1) {
                return 1;
            }
        }

        return result;
    }
}
