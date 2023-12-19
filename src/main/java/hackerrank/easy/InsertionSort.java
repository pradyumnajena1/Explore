package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort {
    public static void main(String[] args) {
      insertionSort1(5,new ArrayList<>(List.of(2, 4, 6, 8, 3
      )));
    }
    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here
        int value = arr.get(n-1);
        int i=n-2;
        while (i>=0 && value<arr.get(i)){
            arr.set(i+1,arr.get(i));
            System.out.println(getAsString(arr));
            i--;
        }
        arr.set(i+1,value);
        System.out.println(getAsString(arr));

    }
    private static String getAsString(List<Integer> arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.size();i++){
            sb.append(arr.get(i));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        String string = sb.toString();

        return string;
    }
}
