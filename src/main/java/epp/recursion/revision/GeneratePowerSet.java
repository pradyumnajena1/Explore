package epp.recursion.revision;

import java.util.HashSet;
import java.util.Set;

public class GeneratePowerSet {
    public static void main(String[] args) {
        int[] values  = {1,2,3};
        Set<Set<Integer>> result = generatePowerSets(values,0);
        System.out.println(result);
    }

    private static Set<Set<Integer>> generatePowerSets(int[] values, int i) {
        if(i==values.length){
            HashSet<Set<Integer>> sets = new HashSet<>();
            sets.add(new HashSet<>());
            return sets;
        }
        Set<Set<Integer>> partial = generatePowerSets(values,i+1);
        Set<Set<Integer>> result  = new HashSet<>();
        // not included
        for(Set<Integer> set:partial){
            result.add(new HashSet<>(set));
        }
        //included
        for(Set<Integer> set:partial){
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(values[i]);
            result.add(newSet);
        }
        return result;
    }
}
