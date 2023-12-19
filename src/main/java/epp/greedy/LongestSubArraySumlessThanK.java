package epp.greedy;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class LongestSubArraySumlessThanK {
    public static void main(String[] args) {
        int[] values = new int[]{431,-15,639,342,-14,565,-924,635,167,-70};

        int[] longestSubArray = findLongestSubArray(values, 185);
        System.out.println(Arrays.toString(longestSubArray));

    }

    private static int[] findLongestSubArray(int[] values, int k) {
        ArrayUtils.printArray(values);
        int[] cumulativeSum = ArrayUtils.cumulativeSum(values);
        ArrayUtils.printArray(cumulativeSum);
        int[] minSumSeen = new int[cumulativeSum.length];
        minSumSeen[minSumSeen.length-1] = cumulativeSum[cumulativeSum.length-1];
        for(int i=minSumSeen.length-2;i>=0;i--){
            minSumSeen[i] = Math.min(cumulativeSum[i],minSumSeen[i+1]);
        }
        ArrayUtils.printArray(minSumSeen);
        int start = 0;
        int end = 0;
        int maxLength = 0;
        int[] result = new int[]{-1,-1};
        while (start<cumulativeSum.length &&  end<cumulativeSum.length){

            int minSubArraySum = minSumSeen[end]- (start>0? cumulativeSum[start-1]:0);
            if(minSubArraySum<=k){
                if(maxLength< end-start+1){
                    maxLength = Math.max(maxLength,end-start+1);
                    result = new int[]{start,end};
                }

                end++;
            }else{
                start++;
            }
        }


        return result;

    }


}
