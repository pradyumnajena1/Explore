package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TwoArrays {
    public static void main(String[] args) {
        System.out.println(twoArrays(10, new ArrayList<>(List.of(2, 1, 3)), new ArrayList<>(List.of(7, 8, 9))));
        System.out.println(twoArrays(5, new ArrayList<>(List.of(1, 2, 2, 1)), new ArrayList<>(List.of(3, 3, 3, 4))));
    }
    public static String twoArrays(int k, List<Integer> A, List<Integer> B) {
        // Write your code here
          A.sort(Comparator.naturalOrder());
          B.sort(Comparator.reverseOrder());
          for(int i=0;i<A.size();i++){
              if(A.get(i)+B.get(i)<k){
                  return "NO";
              }
          }
          return "YES";
    }
}
