package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class CountingSort {
    public static void main(String[] args) {
        System.out.println(countingSort(new ArrayList<>(List.of(1, 1, 3, 2, 1))));
    }

    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<100;i++){
            result.add(0);
        }
        for(int n:arr){
           result.set(n, result.get(n)+1);
        }
        return result;
    }
}
