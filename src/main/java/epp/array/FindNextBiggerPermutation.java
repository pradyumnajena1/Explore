package epp.array;

import java.util.Arrays;

public class FindNextBiggerPermutation {
    public static void main(String[] args) {
        int[] value = new int[]{4,0,3,2,1};
        int[] nextBig = findNextBigPermutation(value);
        System.out.println(Arrays.toString(nextBig));

        value = new int[]{4,0,1,2,3};
        int[]  nextSmall = findNextSmallPermutation(value);
        System.out.println(Arrays.toString(nextSmall));
    }



    private static int[] findNextBigPermutation(int[] value) {


        int firstNonIncreasingIndex = findFirstNonIncreasingIndex(value);
        if(firstNonIncreasingIndex==-1){
            return new int[0];
        }
        ArrayUtils.swap(value,firstNonIncreasingIndex,value.length-1);
        ArrayUtils.reverse(value,firstNonIncreasingIndex+1,value.length-1);



        return value;
    }

    private static int[] findNextSmallPermutation(int[] value) {


        int firstNonDecreasingIndex = findFirstNonDecreasingIndex(value);
        if(firstNonDecreasingIndex==-1){
            return new int[0];
        }
        ArrayUtils.swap(value,firstNonDecreasingIndex,value.length-1);
        ArrayUtils.reverse(value,firstNonDecreasingIndex+1,value.length-1);



        return value;
    }

    private static int findFirstNonIncreasingIndex(int[] value) {
        int index = value.length-1;
        while (index>0 && value[index]<value[index-1]){
            index--;
        }
        return index-1;
    }

    private static int findFirstNonDecreasingIndex(int[] value) {
        int index = value.length-1;
        while (index>0 && value[index]>value[index-1]){
            index--;
        }
        return index-1;
    }
}
