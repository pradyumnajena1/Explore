package epp.recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubsets {
    public static void main(String[] args) {
        List<List<Integer>> subsets = generateSubsets(5,5);
        System.out.println(subsets);
    }

    private static List<List<Integer>> generateSubsets(int n, int k) {
        if(k==0){
            ArrayList<List<Integer>> subsets = new ArrayList<>();
            subsets.add(new ArrayList<>());
            return subsets;
        }
        List<List<Integer>> subsets = generateSubsets(n, k - 1);
        List<List<Integer>> newsubsets = new ArrayList<>();

        for(List<Integer> subset:subsets){
            Integer lastValue = subset.size()>0? subset.get(subset.size() - 1):0;
            for(int i=lastValue+1;i<=n;i++){
                List<Integer> newSubset = new ArrayList<>(subset);

                newSubset.add(i);
                newsubsets.add(newSubset);
            }

        }
        return newsubsets;
    }
}
