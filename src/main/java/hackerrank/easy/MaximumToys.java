package hackerrank.easy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaximumToys {
    public static void main(String[] args) {
        System.out.println(maximumToys(new ArrayList<>(List.of(1, 2, 3, 4)), 7));
        System.out.println(maximumToys(new ArrayList<>(List.of(1, 12, 5 ,111, 200, 1000, 10)), 50));
    }
    public static int maximumToys(List<Integer> prices, int k) {
        // Write your code here
         prices.sort(Comparator.naturalOrder());
         int sum=0;
         int count=0;
          for(int i=0;i<prices.size();i++){
              if(sum+ prices.get(i)<=k){
                  sum+= prices.get(i);
                  count++;
              }
          }
          return count;
    }
}
