package epp.greedy;

import java.util.Arrays;

public class FindPairSumInAbsSortedArray {
    public static void main(String[] args) {
        int[] array = new int[]{-49,75,103,-147,164,-197,-238,314,348,-422};
        int[] found = findPair(array,167);
        System.out.println(Arrays.toString(found));
    }

    private static int[] findPair(int[] array, int sum) {
        int start = getNextPositive(array, array.length - 1);
        int end = getNextNegative(array, array.length - 1);
        while (start>=0 && end>=0){
            if(array[start]+array[end]==sum){
                return new int[]{Math.min( start,end),Math.max( start,end)};
            }else if(array[start]+array[end]>sum){
                start = getNextPositive(array,start-1);
            }else{
                end = getNextNegative(array,end-1);
            }
        }
        return new int[]{-1,-1};
    }

    private static int getNextNegative(int[] array, int current) {
        int end = current;
        while (end>=0 && array[end]>0){
            end--;
        }
        return end;
    }

    private static int getNextPositive(int[] array, int current) {
        int start = current;
        while (start>=0&& array[start]<0){
            start--;
        }
        return start;
    }
}
