package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class Equal {

    public static void main(String[] args) {
        System.out.println(equal(new ArrayList<>(List.of(1, 1, 5))));
    }

    public static int equal(List<Integer> arr) {
        // Write your code here
        int minOperation =Integer.MAX_VALUE;
        for(int value:arr){
            int operation = value/5 + value%5/2 + value%5%2;
            minOperation = Math.min(minOperation,operation);

        }
        return minOperation;
    }
}
