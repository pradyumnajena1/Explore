package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        System.out.println(unboundedKnapsack(12, new ArrayList<>(List.of(1, 6, 9))));
        System.out.println(unboundedKnapsack(9, new ArrayList<>(List.of(3, 4, 4, 4, 8))));
    }

    public static int unboundedKnapsack(int k, List<Integer> arr) {
        // Write your code here
             return unboundedKnapsack(k,arr,0);
    }

    private static int unboundedKnapsack(int k, List<Integer> arr, int i) {
        if(k<=0 ){
            return 0;
        }
        if(arr.size() ==i){
            return 0;
        }
        int including = arr.get(i)<=k? arr.get(i) + unboundedKnapsack(k - arr.get(i), arr, i) : 0;
        int excluding = unboundedKnapsack(k  , arr, i+1);
        int max = Math.max(including, excluding);
        return max;
    }
}
