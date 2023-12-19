package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LuckBalance {
    public static void main(String[] args) {
        System.out.println(luckBalance(2, new ArrayList<>(List.of(List.of(5, 1), List.of(4, 0), List.of(1, 1)))));
        System.out.println(luckBalance(1, new ArrayList<>(List.of(List.of(5, 1), List.of(4, 0), List.of(1, 1)))));
    }

    // f
    public static int luckBalance(int k, List<List<Integer>> contests) {
        // Write your code here
       // inner list format is Li,Ti , l the luck and t important or not
        Comparator<List<Integer>> listComparator =
                Comparator.comparingInt((List<Integer> list) -> list.get(1))
                .thenComparing( Comparator.comparingInt((List<Integer> list) -> list.get(0)).reversed() ) ;

        contests.sort(listComparator);
        System.out.println(contests);
        int totalLuck = getTotalLuck(k, contests);
        return totalLuck;
    }

    private static int getTotalLuck(int k, List<List<Integer>> contests) {
        int totalLuck =0;
        int importantTestLost = 0;
        for(int i = 0; i< contests.size(); i++){
            List<Integer> contest = contests.get(i);
            if(contest.get(1)==0){
                totalLuck+= contest.get(0);
            }else if(contest.get(1)==1){
                if(importantTestLost< k){
                    totalLuck+= contest.get(0);
                    importantTestLost++;
                }else{
                    totalLuck-= contest.get(0);
                }
            }
        }
        return totalLuck;
    }
}
