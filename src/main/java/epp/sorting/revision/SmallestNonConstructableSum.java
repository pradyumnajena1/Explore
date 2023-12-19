package epp.sorting.revision;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 1,1,2,5,10
 * 1,2,5,10,2,3,6,11
 * 2,5,10,2,3,6,11, 3,6,11,3,4,7,12
 * 5,10,2,3,6,11, 3,6,11,3,4,7,12 5,10,2,3,6,11, 3,6,11,3,4,7,12
 */
public class SmallestNonConstructableSum {
    public static void main(String[] args) {
        int[] coins = {1,1,2,5,10,21};
        int sum = findSmallestNonConstructableSum(coins);
        System.out.println(sum);
    }

    private static int findSmallestNonConstructableSum(int[] coins) {
         Arrays.sort(coins);
         int sum =0;
         for(int coin:coins){
             if(coin> sum+1){
                 return sum+1;
             }
             sum = sum+coin;
         }
         return sum+1;
    }
}
