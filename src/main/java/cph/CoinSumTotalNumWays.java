package cph;

public class CoinSumTotalNumWays {
    public static void main(String[] args) {
        System.out.println(countNumWays(new int[]{1, 3, 4}, 5));
    }

    public static long countNumWays(int[] coins, int sum) {
        long[] values = new long[sum + 1];
        values[0] = 1;
        for (int i = 1; i <= sum; i++) {

            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    values[i] += values[i - coins[j]];
                }
            }
        }
        return values[sum];

    }
}
