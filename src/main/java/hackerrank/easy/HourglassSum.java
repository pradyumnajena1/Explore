package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class HourglassSum {
    public static void main(String[] args) {
        System.out.println(hourglassSum(new ArrayList<>(List.of(
                List.of(1, 1, 1, 0, 0, 0),
                List.of(0, 1, 0, 0, 0, 0),
                List.of(1, 1, 1, 0, 0, 0),
                List.of(0, 0, 2, 4, 4, 0),
                List.of(0, 0, 0, 2, 0, 0),
                List.of(0, 0, 1, 2, 4, 0)
        ))));
    }

    public static int hourglassSum(List<List<Integer>> arr) {
        // Write your code here
        int numRows = arr.size();
        int numCol = arr.get(0).size();
        Integer maxSum = null;
        for (int i = 0; i + 2 < numRows; i++) {
            for (int j = 0; j + 2 < numCol; j++) {
                //System.out.println(i+ " "+j);
                //top row
                int sum = 0;
                for (int trc = j; trc < j + 3; trc++) {
                    sum += arr.get(i).get(trc);
                }
                //bottom row
                for (int brc = j; brc < j + 3; brc++) {
                    sum += arr.get(i + 2).get(brc);
                }
               //center
                sum += arr.get(i + 1).get(j + 1);
                if (maxSum == null || maxSum < sum) {
                    maxSum = sum;
                }

            }
        }
        return maxSum;
    }
}
