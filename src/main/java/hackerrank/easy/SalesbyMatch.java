package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalesbyMatch {
    public static void main(String[] args) {
        System.out.println(sockMerchant(9, new ArrayList<>(List.of(10, 20, 20, 10, 10, 30, 50, 10, 20))));
    }
    public static int sockMerchant(int n, List<Integer> ar) {
        // Write your code here
        Map<Integer, Long> freq = ar.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int numPairs = 0;
        for(long num: freq.values()){

                numPairs += num/2;

        }
        return numPairs;
    }
}
