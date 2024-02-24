package cph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinCoinSum {
    public static void main(String[] args) {
        System.out.println(getMinimumCoinsCountForSum(new int[]{1, 3, 4, 10, 20, 50}, 152));
        System.out.println(getMinimumCoinsCountForSumIter(new int[]{1, 3, 4, 10, 20, 50}, 152));
        System.out.println(Arrays.toString( getMinimumCoinsForSumIter(new int[]{1, 3, 4, 10, 20, 50}, 152)));
    }

    private static int getMinimumCoinsCountForSumIter(int[] coins, int sum) {
        int[] values = new int[sum + 1];
        values[0] = 0;
        for (int i = 1; i <= sum; i++) {
            values[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    values[i] = Math.min(values[i], values[i - coins[j]] == Integer.MAX_VALUE ? values[i - coins[j]] :
                            1 + values[i - coins[j]]);
                }
            }
        }

        return values[sum];
    }

    private static int[] getMinimumCoinsForSumIter(int[] coins, int sum) {
        int[] values = new int[sum + 1];
        int[] first = new int[sum + 1];
        values[0] = 0;
        for (int i = 1; i <= sum; i++) {
            values[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    int partial = values[i - coins[j]] == Integer.MAX_VALUE ? values[i - coins[j]] :
                            1 + values[i - coins[j]];
                    if (values[i] > partial) {
                        values[i] = partial;
                        first[i] = coins[j];
                    }
                }
            }
        }
        List<Integer> coinValues = new ArrayList<>();
        int start = sum;
        while (start>0){
            coinValues.add(first[start]);
            start = start-first[start];
        }
        return coinValues.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getMinimumCoinsCountForSum(int[] coins, int sum) {
        int[] values = new int[sum + 1];
        boolean[] ready = new boolean[sum + 1];
        return getMinimumCoinsCountForSumHelper(coins, sum, values, ready);
    }

    private static int getMinimumCoinsCountForSumHelper(int[] coins, int sum, int[] values, boolean[] ready) {
        if (sum < 0) {
            return Integer.MAX_VALUE;
        }
        if (sum == 0) {
            return 0;
        }
        if (ready[sum]) {
            return values[sum];
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int partial = getMinimumCoinsCountForSumHelper(coins, sum - coins[i], values, ready);
            best = Math.min(best, partial == Integer.MAX_VALUE ? partial : 1 + partial);
        }
        values[sum] = best;
        ready[sum] = true;
        return best;
    }
}
