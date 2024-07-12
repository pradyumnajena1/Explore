package epp.dp.revision;

public class MaximumRevenue {
    public static void main(String[] args){
        int[] prices = {2, 5, 7, 1, 3, 6, 4};
        System.out.println(maxRevenue(prices));
    }

    private static int maxRevenue(int[] prices) {
        int[][] cache = new int[prices.length][prices.length];
        return maxRevenue(prices,0,prices.length-1,cache);
    }

    private static int maxRevenue(int[] prices, int a, int b, int[][] cache) {
        if(a>b){
            // no coins left
            return 0;
        }
        if(cache[a][b]==0){
            int maxRevenueA = prices[a] + Math.min(maxRevenue(prices,a+1,b-1,cache),maxRevenue(prices,a+2,b,cache));
            int maxRevenueB = prices[b] + Math.min(maxRevenue(prices,a,b-2,cache),maxRevenue(prices,a+1,b-1,cache));
          cache[a][b] = Math.max(maxRevenueA,maxRevenueB);
        }
        return cache[a][b];
    }
}
