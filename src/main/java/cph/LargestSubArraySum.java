package cph;

import epp.Pair;
import epp.array.ArrayUtils;

public class LargestSubArraySum {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,-10,10);
        ArrayUtils.printArray(values);
        System.out.println(largestSubArraySum(values));
        System.out.println(largestSubArraySum2(values));
        System.out.println(largestSubArraySumRange(values));
    }
    static int largestSubArraySum2(int[] values){
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<values.length;i++){
            int sum=0;
            for(int j=i;j<values.length;j++){
                sum+=values[j];
                maxSum  = Math.max(maxSum,sum);
            }
        }
        return maxSum;
    }
    static int largestSubArraySum(int[] values){
        int sum = 0;
        int maxSum = 0;
        for(int i=0;i< values.length;i++){
            sum = Math.max(sum+values[i],values[i]);
            maxSum = Math.max(sum,maxSum);
        }
        return maxSum;
    }

    static Pair<Integer,Integer> largestSubArraySumRange(int[] values){
        int sum = 0;
        int maxSum = 0;
        int start = 0;
        Pair<Integer,Integer> result= null;
        for(int i=0;i< values.length;i++){
            if(sum+values[i]>values[i]){
                sum     = sum+values[i];
            }else{
                sum = values[i];
                start = i;
            }

            if(sum>maxSum){
                maxSum = sum;
                result = new Pair<>(start,i);
            }
        }
        return result;
    }
}
