package hackerrank.easy;

import java.util.Comparator;
import java.util.List;

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {

    }

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // Write your code here
        arr.sort(Comparator.naturalOrder());
        int mindiff = Integer.MAX_VALUE;
        for(int i=0;i<arr.size()-1;i++){
            int diff = Math.abs(arr.get(i+1)- arr.get(i));
            mindiff = Math.min(mindiff,diff);
        }
        return mindiff;
    }
}
