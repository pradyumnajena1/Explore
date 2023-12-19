package epp.dp;

import epp.array.ArrayUtils;

public class MaxSubarray {
    public static void main(String[] args) {

        int[] array = ArrayUtils.randomArray(20,-20,20);
        ArrayUtils.printArray(array);
        int[] maxSumSubarray = getMaxSubarray(array);
        ArrayUtils.printArray(maxSumSubarray);
    }

    private static int[] getMaxSubarray(int[] array) {
        int sum = 0;
        int minSum = Integer.MAX_VALUE;
        int maxSum = Integer.MIN_VALUE;

        int minIndex = -1;
        int[] result = new int[]{-1,-1};
        for(int i=0;i< array.length;i++){
            sum+=array[i];
            if(sum<minSum){
                minSum = sum;
                minIndex=i;
            }
            if(sum-minSum>maxSum){
                maxSum = sum-minSum;
                result = new int[]{minIndex+1,i};
            }

        }
         return result;
    }
}
