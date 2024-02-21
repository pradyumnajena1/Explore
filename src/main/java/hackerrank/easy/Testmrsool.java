package hackerrank.easy;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Testmrsool {

    public static void main(String[] args) {
     int[] values  = new int[]{1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
     pushToRight(values);
        System.out.println(Arrays.toString(values));


    }

    public  static void pushToRight(int[] values){
        int writeIndex = values.length-1;
        for(int i= values.length-1;i>=0;i--){
            if(values[i]==0){
                swap( values,i,writeIndex);
                writeIndex--;
            }
        }
    }

    private static void swap(int[] values, int i, int j) {
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
