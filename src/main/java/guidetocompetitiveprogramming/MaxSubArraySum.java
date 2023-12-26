package guidetocompetitiveprogramming;

public class MaxSubArraySum {
    public static void main(String[] args) {
        System.out.println(maxSubArraySum(new int[]{3,3,-2,4,5,-6,10,-9}));
    }
    static int maxSubArraySum(int[] arr){
        int sum=0,best = 0;
        for (int j : arr) {
            sum = Math.max(j, sum + j);
            best = Math.max(best, sum);
        }
        return best;
    }
}
