package epp.dp.revision;

import epp.Triplet;
import epp.array.ArrayUtils;

public class MaxCircularSubArraySum {
    public static void main(String[] args) {
        int[] values =  ArrayUtils.randomArray(6, -10, 10);
        ArrayUtils.printArray(values);
        Result maxSum = getMaxCircularSubArraySum(values);
        System.out.println(maxSum);
    }

    private static Result getMaxCircularSubArraySum(int[] values) {
        Result maxSubArraySum = MaximumSubArraySum.findMaxSubArraySum(values);
        System.out.println(maxSubArraySum);
        Result maxLoopAroundSum = getMaxLoopAroundSum(values);
        System.out.println(maxLoopAroundSum);
        return maxSubArraySum.sum>maxLoopAroundSum.sum?maxSubArraySum:maxLoopAroundSum;
    }

    private static Result getMaxLoopAroundSum(int[] values) {
        int leftSum = values[0];
        int maxLeftSum = values[0];
        int maxLeftIndex = 0;
        for(int i=1;i< values.length-1;i++){
            leftSum+=values[i];
            if(maxLeftSum<leftSum){
                maxLeftSum = leftSum;
                maxLeftIndex = i;
            }
        }
        int rightSum = values[values.length-1];
         int maxRightSum = values[values.length-1];
        int maxRightIndex = 0;
        for(int i= values.length-2;i>=maxLeftIndex+1;i--){
            rightSum+=values[i];
            if(maxRightSum<rightSum){
                maxRightSum  = rightSum;
                maxRightIndex=i;
            }
        }

        return new Result(maxLeftIndex,maxRightIndex,maxRightSum+maxLeftSum);
    }


}
