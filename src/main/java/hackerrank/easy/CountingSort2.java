package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class CountingSort2 {
    public static void main(String[] args) {
        System.out.println(countingSort(new ArrayList<>(List.of(0, 3, 1, 1, 0))));
    }
    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        List<Integer> frequencies = countingSort1(arr);
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<frequencies.size();i++){
            Integer frequency = frequencies.get(i);
            while (frequency-->0){
                result.add(i);
            }
        }
        return result;

    }
    public static List<Integer> countingSort1(List<Integer> arr) {
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
