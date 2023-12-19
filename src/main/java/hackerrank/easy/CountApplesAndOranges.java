package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class CountApplesAndOranges {
    public static void main(String[] args) {
            countApplesAndOranges(7,11,5,15,new ArrayList<>(List .of (-2,2,1)),new ArrayList<>(List .of (5,-6)));
    }

    public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        int appleCount=0;
        for(Integer apple:apples){
            int appleFallen = a + apple;
            if(appleFallen >= s && appleFallen<=t )appleCount++;
        }
        int orangeCount=0;
        for(Integer orange:oranges){
            int orangeFallen = b + orange;
            if(orangeFallen >= s && orangeFallen<=t )orangeCount++;
        }
        System.out.println(appleCount);
        System.out.println(orangeCount);

    }
}
