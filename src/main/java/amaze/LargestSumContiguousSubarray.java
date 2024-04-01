package amaze;

public class LargestSumContiguousSubarray {
    public static void main(String[] args) {
        System.out.println(getMaxSum(new int[]{-2,-3,4,-1,-2,1,5,-3}));
    }

    private static int getMaxSum(int[] values) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i< values.length;i++){
            sum = Math.max(sum+values[i],values[i]);
            maxSum = Math.max(sum,maxSum);
        }
        return maxSum;
    }
}
