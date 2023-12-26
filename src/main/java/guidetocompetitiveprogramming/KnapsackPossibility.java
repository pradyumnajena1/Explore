package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnapsackPossibility {
    public static void main(String[] args) {
        int[] weights = {1,3,3,4};
        List<Integer> notPossible = getAllImpossibleWeights(weights);
        System.out.println(notPossible);
    }

    private static List<Integer> getAllImpossibleWeights(int[] weights) {
        int sum = Arrays.stream(weights).sum();
        boolean[][] possible = new boolean[weights.length+1][sum+1];
        possible[0][0] = true;
        for(int k=1;k<=weights.length;k++){
            for(int s=0;s<=sum;s++){
                if(s-weights[k-1]>=0){
                    possible[k][s] |= possible[k-1][s-weights[k-1]];
                }
                possible[k][s] |= possible[k-1][s];
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int s=1;s<=sum;s++){
            if(!possible[weights.length][s]){
                result.add(s);
            }
        }

        return result;
    }
}
