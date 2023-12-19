package epp.array;

public class MaxProfitInStock {
    public static void main(String[] args) {
        int maxProfit = getMaxProfit(new int[]{10,5,3 });
        System.out.println(maxProfit);

        maxProfit = getMaxProfitTwice(new int[]{10,5,3,2,17,12,19});
        System.out.println(maxProfit);
    }

    private static int getMaxProfit(int[] stockPrices) {

        return getMaxProfit(stockPrices,0,stockPrices.length-1);
    }

    private static int  getMaxProfitTwice(int[] stockPrices){
        int maxProfit = 0;
        for(int i=0;i<=stockPrices.length;i++){

            int leftMax = getMaxProfit(stockPrices,0,i-1);
            int rightMax = getMaxProfit(stockPrices,i,stockPrices.length-1);
            int totalProfit = leftMax+rightMax;
            maxProfit = Math.max(maxProfit,totalProfit);

        }
        return maxProfit;
    }

    private static int getMaxProfit(int[] stockPrices,int start,int end) {

        int minimum = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i = start; i<= end; i++){

            int profitMade = stockPrices[i] - minimum;
            maxProfit = Math.max(profitMade,maxProfit);

            minimum = Math.min(minimum, stockPrices[i]);
        }
        return maxProfit;
    }
}
