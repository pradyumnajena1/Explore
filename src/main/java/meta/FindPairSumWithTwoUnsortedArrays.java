package meta;

import epp.Pair;

import java.util.*;

public class FindPairSumWithTwoUnsortedArrays {
    public static void main(String[] args) {
        System.out.println(findAllPairsSumEquals(new int[]{-1, -2, 4, -6, 5, 7}, new int[]{6, 3, 4, 0},8));
        System.out.println(findAllPairsSumEquals2(new int[]{-1, -2, 4, -6, 5, 7}, new int[]{6, 3, 4, 0},8));
    }

    private static List<Pair<Integer, Integer>> findAllPairsSumEquals(int[] values1, int[] values2, int k) {
        Set<Integer> set = new HashSet<>( );
        for(int value:values1){
            set.add(value);
        }
        List<Pair<Integer,Integer>> result = new ArrayList<>();
        for(int i=0;i<values2.length;i++){
            if(set.contains(k-values2[i])){
                result.add(new Pair<>(k-values2[i],values2[i]));
            }
        }
        return result;
    }


    private static List<Pair<Integer, Integer>> findAllPairsSumEquals2(int[] values1, int[] values2, int k) {
        Arrays.sort(values1);
        List<Pair<Integer,Integer>> result = new ArrayList<>();
        for(int i=0;i<values2.length;i++){

            int index = Arrays.binarySearch(values1, k - values2[i]);
            if(index>=0){
                result.add(new Pair<>(k-values2[i],values2[i]));
            }
        }
        return result;
    }
}
