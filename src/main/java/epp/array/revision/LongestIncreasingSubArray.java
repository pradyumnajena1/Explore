package epp.array.revision;

import epp.array.ArrayUtils;



public class LongestIncreasingSubArray {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,2,10);
        int[] subArray = getLongestIncreasingSubArray(values);
        ArrayUtils.printArray(values);
        ArrayUtils.printArray(subArray);
    }

    private static int[] getLongestIncreasingSubArray(int[] values) {
        int[] lengths = new int[values.length];
        lengths[0] = 1;
        int maxLength = 0;
        int[] result = new int[2];
        for(int i=1;i<values.length;i++){
            if(values[i]>values[i-1]){
                lengths[i] = lengths[i-1]+1;
            }else{
                lengths[i] = 1;
            }

            if(lengths[i]>maxLength){
                maxLength = lengths[i];
                result[1] = i;
                result[0] = i-lengths[i] +1;

            }
        }
        return result;
    }
}
