package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort2 {
    public static void main(String[] args) {
     insertionSort2(6,new ArrayList<>(List.of(1, 4, 3, 5, 6, 2)));
    }
    public static void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        for(int i=1;i<arr.size();i++ ){
            insertionSort1(i+1,arr);
            System.out.println(getAsString(arr));
        }

    }

    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here
        int value = arr.get(n-1);
        int i=n-2;
        while (i>=0 && value<arr.get(i)){
            arr.set(i+1,arr.get(i));

            i--;
        }
        arr.set(i+1,value);


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
