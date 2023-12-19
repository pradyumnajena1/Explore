package epp.greedy;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class KsumProblem {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomUniqueArray(10,1,20);
        ArrayUtils.printArray(values);
        boolean exist = findThreeTuple(values,30);
        System.out.println(exist);
    }

    private static boolean findThreeTuple(int[] values, int sum) {
        Arrays.sort(values);
        for(int i=0;i<values.length;i++){
            if(findTwoTuple(values,sum-values[i])){
                return true;
            }
        }
        return false;
    }

    private static boolean findTwoTuple(int[] values, int sum) {
        int start=0;
        int end = values.length-1;
        while (start<=end){
            int currentSum = values[start] + values[end];
            if(currentSum ==sum){
                return true;
            }else if(currentSum<sum){
                start++;
            }else{
                end--;
            }
        }
        return false;
    }
}
