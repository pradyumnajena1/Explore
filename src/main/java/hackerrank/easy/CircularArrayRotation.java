package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayRotation {
    public static void main(String[] args) {
        System.out.println(circularArrayRotation(new ArrayList<>(List.of(3, 4, 5)), 2, new ArrayList<>(List.of(1, 2))));
    }

    public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
        // Write your code here
         k = k%a.size();
         rotateList(a,a.size()-k,a.size()-1);
         rotateList(a,0,a.size()-k-1);
         rotateList(a,0,a.size() -1);
       // System.out.println(a);

         List<Integer> result = new ArrayList<>();
         for(Integer i:queries){
             result.add(a.get(i));
         }
         return result;
    }

    private static void rotateList(List<Integer> arr, int start, int end) {
        while (start<end){
            int t = arr.get(start);
            arr.set(start,arr.get(end));
            arr.set(end,t);
            start++;
            end--;
        }
    }
}
