package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumSum {

    public static void main(String[] args) {
        ArrayList<Long> a = new ArrayList<>();
        List<Integer> integers = List.of( 1,2,3);
        List<Long> collect = integers.stream().map(x -> Long.valueOf(x)).collect(Collectors.toList());
        System.out.println(collect);
        a.addAll(collect);
        System.out.println(maximumSum(a, 4));
    }

    public static long maximumSum(List<Long> a, long m) {
        // Write your code here
        long sum = 0;
        long maxSum = 0;
        for(long value:a){

            if( (sum+value)%m<value%m){
                sum = value%m;
            }else{
                sum = (sum+value)%m;
            }
            maxSum = Math.max(sum,maxSum);

        }
   return maxSum;
    }
}
