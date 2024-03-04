package meta;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;

public class LargestSubSetOfFibonacciNumbers {
    public static void main(String[] args) {
        int[] values ={0, 2, 8, 5, 2, 1, 4,
                13, 23};// ArrayUtils.randomArray(20, 0, 100);
        ArrayUtils.printArray(values);
        Set<Integer> largestSubset = largestSubSetOfFibNumbers(values);
        System.out.println(largestSubset);
    }

    private static Set<Integer> largestSubSetOfFibNumbers(int[] values) {
        if( values==null|| values.length==0){
            return new HashSet<>();
        }
        int max = Arrays.stream(values).max().getAsInt();
        Set<Integer> fibNumbers = new HashSet<>();
        int prev=0;
        int cur=1;
        fibNumbers.add(prev);
        while (cur<=max){
            fibNumbers.add(cur);
            int next = prev+cur;
            prev = cur;
            cur=next;
        }
        Set<Integer> subset = new HashSet<>();
        for(int i=0;i<values.length;i++){
            if(fibNumbers.contains(values[i])){
                subset.add(values[i]);
            }
        }
        return subset;
    }
}
