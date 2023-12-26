package guidetocompetitiveprogramming;

public class CountNumWays {

    public static final int Modulo = (1 << 9 + 7);

    public static void main(String[] args) {
        int[] coins = new int[]{1,3,4};
        int sum = 5;
        System.out.println(countNumWays(coins,sum));

    }

    private static int countNumWays(int[] coins, int sum) {
        int[] count = new int[sum+1];
        count[0] = 1;
        for(int s=1;s<=sum;s++){
            int numWays = 0;
            for(int c:coins){
                if(s-c>=0 ){
                    numWays+= count[s-c];
                    numWays=numWays% Modulo;
                }
            }
            count[s] = numWays;

        }
        return count[sum];
    }
}
