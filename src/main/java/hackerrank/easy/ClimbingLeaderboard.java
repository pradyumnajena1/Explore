package hackerrank.easy;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClimbingLeaderboard {
    public static void main(String[] args) {
        System.out.println(climbingLeaderboard(new ArrayList<>(List.of(100, 100, 50, 40, 40, 20, 10
        )), new ArrayList<>(List.of(5, 25, 50, 120))));

        System.out.println(climbingLeaderboard(new ArrayList<>(List.of(100,90,90,80,75,60

        )), new ArrayList<>(List.of(50,65,77,90,102))));
    }

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        ranked = new ArrayList<>( new HashSet<>(ranked));
        ranked.sort(Comparator.naturalOrder());

       // System.out.println(ranked);

        List<Integer> result = new ArrayList<>();
        for(int score:player){
          int index =   getIndexOfFirstItemBiggerThan(ranked,score);
         //   System.out.println(score +" "+index);
          if(index==-1){
              result.add(1);
          }else{
             result.add( ranked.size() -index  +1 );
          }
        }
       return result;
    }

    private static int getIndexOfFirstItemBiggerThan(List<Integer> ranked, int score) {
        int low = 0;
        int high = ranked.size()-1;
        Integer index = null;
        while (low<=high){
            int mid  = (low+high)/2;
            if(  ranked.get(mid) <=score){
                low = mid+1;
            }else{
                index = mid;
                high = mid-1;
            }
        }
        return index!=null?index:-1;
    }
}
