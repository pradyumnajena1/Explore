package epp.array.revision;

import epp.array.ArrayUtils;

public class RandomPermutation {
    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6,7,8,9,10};
        randomPermutation(values);
        ArrayUtils.printArray(values);
    }

    private static void randomPermutation(int[] values) {
        for(int i=0;i<values.length;i++){
            int index = (int) (Math.random()*(values.length-i));
            ArrayUtils.swap(values,index,values.length-1-i);
        }
    }
}
