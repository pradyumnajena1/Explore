package meta;

import epp.Pair;

public class LargestSubArraySumHavingAtleastKNumbers {
    public static void main(String[] args) {
        System.out.println(getLargestSubArray(new int[]{-4, -2, 1, -3}, 2));
        System.out.println(getLargestSubArray2(new int[]{-4, -2, 1, -3}, 2));

    }

    private static int getLargestSubArray2(int[] values, int k) {
        int[] maxSubArraySum = new int[values.length];
        maxSubArraySum[0] = values[0];
        for(int i=1;i< values.length;i++){
            maxSubArraySum[i] = Math.max(values[i],values[i]+maxSubArraySum[i-1]);
        }
        int sum=0;
        for(int i=0;i<k;i++){
            sum+=values[i];
        }
        int result = sum;
        for(int i=k;i<values.length;i++){
            sum = sum+values[i]-values[i-k];
            result = Math.max(result,sum);
            result = Math.max(result,sum+maxSubArraySum[i-k]);
        }
        return result;
    }

    private static int getLargestSubArray(int[] values, int k) {
        int sum = 0;
        for(int i=0;i<k;i++){
            sum+=values[i];
        }
        int last = 0;
        int j=0;
        int ans = Integer.MIN_VALUE;
        ans  = Math.max(ans,sum);
        for(int i=k;i< values.length;i++){
            sum+= values[i];
            last+= values[j++];

            ans = Math.max(ans,sum);
            if(last<0){
                sum=sum-last;
                ans = Math.max(ans,sum);
                last=0;
            }
        }
        return ans;
    }
}
