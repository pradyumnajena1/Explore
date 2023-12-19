package epp.recursion;

import java.util.HashSet;
import java.util.Set;

public class PowerSet {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        printPowersets(array);
    }

    private static void printPowersets(int[] array) {
     Set<Set<Integer>>  powersets =  getPowerSets(array,0,array.length-1);
        System.out.println(powersets);
    }

    private static Set<Set<Integer>> getPowerSets(int[] array, int start, int end) {
        if(start>end){
            Set<Set<Integer>> sets = new HashSet<>();
            sets.add(new HashSet<>());
            return sets;
        }else {
            Set<Set<Integer>> powerSets = getPowerSets(array, start, end - 1);
            Set<Set<Integer>> newPoserSets = new HashSet<>(powerSets);
            for(Set<Integer> powerset:powerSets){
                Set<Integer> newPowerSet = new HashSet<>(powerset);
                newPowerSet.add(array[end]);
                newPoserSets.add(newPowerSet);
            }
            return newPoserSets;
        }

    }
}
