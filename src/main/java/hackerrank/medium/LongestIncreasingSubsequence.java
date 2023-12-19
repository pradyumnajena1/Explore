package hackerrank.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(longestIncreasingSubsequence2(new ArrayList<>(List.of(2, 7, 4, 3, 8))));
        System.out.println(longestIncreasingSubsequence(new ArrayList<>(List.of(2, 7, 4, 3, 8))));
        System.out.println(longestIncreasingSubsequence(new ArrayList<>(List.of(2,4,3,7,4,8))));
    }

    public static int longestIncreasingSubsequence2(List<Integer> arr) {
        // Write your code here

        List<Integer> lis = new ArrayList<>();
        lis.add(1);
        int result = 0;
        for (int i = 1; i < arr.size(); i++) {
            int maxLength = 0;
            for (int j = 0; j < i; j++) {
                if (arr.get(j) < arr.get(i)) {
                    int length = lis.get(j) + 1;
                    maxLength = Math.max(maxLength, length);
                }
            }
            lis.add(maxLength);
            result = Math.max(maxLength, result);
        }
        return result;
    }

    public static int longestIncreasingSubsequence(List<Integer> arr) {
        // Write your code here

        List<Integer> lis = new ArrayList<>();
        lis.add(arr.get(0));

        for (int i = 1; i < arr.size(); i++) {

            if(arr.get(i)>=lis.get(lis.size()-1)){
                lis.add(arr.get(i));
            }else{
                int index = Collections.binarySearch(lis,arr.get(i));

                if(index<0){
                    index = -(index+1) ;
                }
                lis.set(index,arr.get(i));
                System.out.println(lis);
            }

        }
        return lis.size();
    }
}
