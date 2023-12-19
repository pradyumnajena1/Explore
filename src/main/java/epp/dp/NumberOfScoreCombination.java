package epp.dp;

import java.util.HashMap;
import java.util.Map;

public class NumberOfScoreCombination {
    public static void main(String[] args) {
        int[] scores = new int[]{2,3,7};
        int finalScore = 12;

        int numScoreCombinations = findNumOfScoreCombinations(scores,finalScore);
        System.out.println(numScoreCombinations);
    }

    private static int findNumOfScoreCombinations(int[] scores, int finalScore) {
        Map<Integer,Integer> cache = new HashMap<>();
        int numOfScoreCombinations = findNumOfScoreCombinations(scores, finalScore, cache);
        System.out.println(cache);
        return numOfScoreCombinations;
    }

    private static int findNumOfScoreCombinations(int[] scores, int finalScore, Map<Integer, Integer> cache) {
        if(finalScore==0){
            return 1;
        }
        if(finalScore<0){
            return 0;
        }
        if(cache.containsKey(finalScore)){
            return cache.get(finalScore);
        }
        int num = 0;
        for(int i=0;i<scores.length;i++){

                int numOfScoreCombinations = findNumOfScoreCombinations(scores, finalScore - scores[i], cache);

                    num+= numOfScoreCombinations;


        }
        cache.put(finalScore,num);
        return num;
    }
}
