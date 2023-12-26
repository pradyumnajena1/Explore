package guidetocompetitiveprogramming;

import java.util.HashMap;

public class MinCoinsSum {
    public static void main(String[] args) {
        int[] coins = new int[]{1,3,4};
        int sum = 10;
        System.out.println(minCoinsSumIter(coins,sum));
        System.out.println(minCoinsSum(coins,sum));
    }

    private static int minCoinsSum(int[] coins, int sum) {
        HashMap<Integer, Integer> cache = new HashMap<>();
        return minCoinsSum(coins,sum, cache);
    }

    private static int minCoinsSum(int[] coins, int sum, HashMap<Integer, Integer> cache) {
        if(sum==0){
            return 0;
        }
        if(sum<0){
            return Integer.MAX_VALUE;
        }
        if(cache.containsKey(sum)){
            return cache.get(sum);
        }
        int min = Integer.MAX_VALUE;
        for(int coin:coins){
            int coinsSum = minCoinsSum(coins, sum - coin, cache);
            if(Integer.MAX_VALUE!=coinsSum){
                coinsSum+=1;
            }
            min = Math.min(min, coinsSum );
        }
        cache.put(sum,min);
        return min;
    }
    private static int minCoinsSumIter(int[] coins, int sum){
        int[] result = new int[sum+1];
        int[] firstCoin = new int[sum+1];
        result[0]=0;
        for(int i=1;i<=sum;i++){
            result[i] = Integer.MAX_VALUE;
            for(int c:coins){
                if(i-c>=0 && result[i-c]!=Integer.MAX_VALUE && result[i - c] + 1 < result[i]){
                    result[i] =  result[i-c]+1;
                    firstCoin[i] = c;
                }
            }
        }
        printCoins(sum, firstCoin);
        return result[sum];
    }

    private static void printCoins(int sum, int[] firstCoin) {
        int n= sum;
        while (n>0){
            System.out.print(firstCoin[n]+" ");
            n= n - firstCoin[n];
        }
        System.out.println();
    }
}
