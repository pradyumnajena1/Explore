package hackerrank.easy;

import commons.IntegerUtils;

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
        int gcd = IntegerUtils.findGCD(bigger);
        int lcm = IntegerUtils.findLCM(smaller);

        //System.out.println(lcm+" "+gcd);
        int count = 0;
        for (int i = 1; i * lcm <= gcd; i++) {

            if( gcd%( i*lcm)==0 ){

                count++;
            }
        }
        return count;
    }


}
