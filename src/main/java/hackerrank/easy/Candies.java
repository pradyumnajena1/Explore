package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Candies {
    public static void main(String[] args) {
        System.out.println(candies(3, new ArrayList<>(List.of(1, 2, 2))));
        System.out.println(candies(10, new ArrayList<>(List.of(2, 4, 2, 6, 1, 7, 8, 9, 2, 1))));
        System.out.println(candies2(10, new ArrayList<>(List.of(2, 4, 2, 6, 1, 7, 8, 9, 2, 1))));
    }

    public static long candies(int n, List<Integer> scores) {
        // Write your code here
        List<Integer> candies = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            candies.add(1);
        }

        Integer changesCount = null;
        while (changesCount == null || changesCount > 0) {
            changesCount = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > (i > 0 ? scores.get(i - 1) : Integer.MAX_VALUE)) {
                    if (candies.get(i) <= candies.get(i - 1)) {
                        changesCount++;
                        candies.set(i, candies.get(i - 1) + 1);
                    }

                } else if (scores.get(i) > (i < scores.size() - 1 ? scores.get(i + 1) : Integer.MAX_VALUE)) {
                    if (candies.get(i) <= candies.get(i + 1)) {
                        changesCount++;
                        candies.set(i, candies.get(i + 1) + 1);
                    }

                }
            }

        }


        return candies.stream().collect(Collectors.summingLong(x -> x));
    }


    public static long candies2(int n, List<Integer> scores) {
        // Write your code here
        List<Integer> candies = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            candies.add(1);
        }
        for(int i=1;i<scores.size();i++){
            if(scores.get(i)> scores.get(i-1)){
                candies.set(i,candies.get(i-1)+1);
            }
        }
        for(int i=scores.size()-2;i >=0;i--){
            if(scores.get(i)> scores.get(i+1)){
                candies.set(i, Math.max(candies.get(i), candies.get(i+1)+1));
            }
        }



        return candies.stream().collect(Collectors.summingLong(x -> x));
    }
}
