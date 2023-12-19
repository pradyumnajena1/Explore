package epp.dp.revision;

import epp.array.ArrayUtils;

public class MaximumSubArraySum {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, -20, 20);
        ArrayUtils.printArray(values);
        Result result = findMaxSubArraySum(values);
        System.out.println(result);

        result = findMaxSubArraySum2(values);
        System.out.println(result);
    }

    public static Result findMaxSubArraySum2(int[] values){
        int minSum=0;
        int maxSum=0;
        int minSumIndex = -1;
        int sum =0;
        Result result = null;
        for(int i=0;i< values.length;i++){
            sum = sum+values[i];
            if(sum<minSum){
                minSum = sum;
                minSumIndex = i;
            }
            if(sum-minSum>maxSum){
                maxSum = sum-minSum;
                result = new Result(minSumIndex+1,i,sum-minSum);
            }
        }
        return result;
    }

    public static Result findMaxSubArraySum(int[] values) {
        Result prev = new Result(0,0,values[0]);
        Result result = prev;
        for (int i = 1; i < values.length; i++) {
            Result curr;
            if (  prev.sum + values[i] <= values[i]) {
                curr = new Result(i, i, values[i]);
            } else {
                curr = new Result(prev.start, i, prev.sum + values[i]);
            }

            if (  curr.sum > result.sum) {
                result = curr;
            }
            prev = curr;
        }
        return result;
    }


}
