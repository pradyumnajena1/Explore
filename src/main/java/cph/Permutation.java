package cph;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Permutation {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomUniqueArray(4,1,10);
        printAllPermutations(values);
        System.out.println();
        System.out.println();
        System.out.println();
        printAllPermutations2(values);
    }

    private static void printAllPermutations(int[] values) {
        List<Integer> permutation = new ArrayList<>();
        boolean[] selected = new boolean[values.length];
        printAllPermutations(values,0,permutation,selected);

    }

    private static void printAllPermutations(int[] values, int i, List<Integer> permutation, boolean[] selected) {
        if(i==values.length){
            System.out.println(permutation);
            return;
        }
        for(int j=0;j< values.length;j++){
            if(selected[j])continue;
            selected[j] = true;
            permutation.add(values[j]);
            printAllPermutations(values,i+1,permutation,selected);
            permutation.remove(permutation.size()-1);
            selected[j] = false;
        }
    }

    private static void printAllPermutations2(int[] values) {
       int[] permutation = IntStream.range(0, values.length).toArray();
       do{
           System.out.println(Arrays.toString(permutation));
           int[] permValues = Arrays.stream(permutation).mapToObj(i -> values[i]).mapToInt(x -> Integer.valueOf(x)).toArray();
           System.out.println(Arrays.toString(permValues));
       }while (getNextPermutation(permutation)!=null);
    }

    private static int[] getNextPermutation(int[] permutation) {
        int start= permutation.length-1;
        while (start>0 && permutation[start]<=permutation[start-1]){
            start--;
        }
        if(start==0){
            return null;
        }
        int j= permutation.length-1;
        while (permutation[start-1]>=permutation[j]){
            j--;
        }
        ArrayUtils.swap(permutation,start-1,j);
        ArrayUtils.reverse(permutation,start,permutation.length-1);
        return permutation;

    }
}
