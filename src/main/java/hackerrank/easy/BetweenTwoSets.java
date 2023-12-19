package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BetweenTwoSets {
    public static void main(String[] args) {
        System.out.println(getTotalX(new ArrayList<>(List.of(2, 6)), new ArrayList<>(List.of(24, 36))));
        System.out.println(getTotalX(new ArrayList<>(List.of(2, 4)), new ArrayList<>(List.of(16,32, 96))));
    }

    public static int getTotalX(List<Integer> smaller, List<Integer> bigger) {
        // Write your code here
        smaller.sort(Comparator.naturalOrder());
        bigger.sort(Comparator.naturalOrder());
        int gcd = findGCD(bigger);
        int lcm = findLCM(smaller);

        //System.out.println(lcm+" "+gcd);
        int count = 0;
        for (int i = 1; i * lcm <= gcd; i++) {

            if( gcd%( i*lcm)==0 ){

                count++;
            }
        }
        return count;
    }



    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    static int findLCM(List<Integer> arr) {
        int result = arr.get(0);
        for (int element : arr) {
            if (element == 0) {
                return 0;
            }
            result = result * element / gcd(result, element);
        }

        return result;
    }

    static int findGCD(List<Integer> arr) {
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
