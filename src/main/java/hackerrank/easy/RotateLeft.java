package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class RotateLeft {
    public static void main(String[] args) {
        System.out.println(rotateLeft(4, new ArrayList<>(List.of(1, 2, 3, 4, 5))));
    }


    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        // Write your code here
             d = d%arr.size();
             reverseList(arr,0,d-1);
             reverseList(arr,d,arr.size()-1);
             reverseList(arr,0,arr.size()-1);
             return arr;
    }
    private static void reverseList(List<Integer> arr, int start, int end) {
        while (start<end){
            int t = arr.get(start);
            arr.set(start,arr.get(end));
            arr.set(end,t);
            start++;
            end--;
        }
    }
}
