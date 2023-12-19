package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class BreakingTheRecords {
    public static void main(String[] args) {
        System.out.println(breakingRecords(new ArrayList<>(List.of(12, 24, 10, 24))));
        System.out.println(breakingRecords(new ArrayList<>(List.of(10,5,20,20,4,5,2,25,1))));
        System.out.println(breakingRecords(new ArrayList<>(List.of(3,4,21,36,10,28,35,5,24,42))));
    }

    public static List<Integer> breakingRecords(List<Integer> scores) {
        // Write your code here
        int min = scores.get(0);
        int minRecordCount = 0;
        int max = scores.get(0);
        int maxRecordCount = 0;
        for(int i=1;i<scores.size();i++){
            Integer currentScore = scores.get(i);
            if(min> currentScore){
                min = currentScore;
                minRecordCount++;
            }else if(max<currentScore){
                max = currentScore;
                maxRecordCount++;
            }
        }
       return new ArrayList<>(List.of(maxRecordCount,minRecordCount));
    }
}
