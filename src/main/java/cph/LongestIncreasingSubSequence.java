package cph;

import epp.array.ArrayUtils;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,1,20);
        ArrayUtils.printArray(values);
        int length = getLongestIncreasingSubSequenceLength(values);
        System.out.println(length);
    }

    private static int getLongestIncreasingSubSequenceLength(int[] values) {
        int[] lengths = new int[values.length];
        int maxLength = 0;
        for(int i=0;i< values.length;i++){
            lengths[i] = 1;
            for(int j=0;j<i;j++){
                if(values[j]<values[i]){
                    lengths[i] = Math.max(lengths[i],1+lengths[j]);
                }
            }
            maxLength = Math.max(maxLength,lengths[i]);
        }
        return maxLength;
    }
}
