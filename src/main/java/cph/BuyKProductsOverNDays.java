package cph;

import epp.array.ArrayUtils;

/**
 * We are given the prices of k
 * products over n days, and we want to buy each product exactly once. However,
 * we are allowed to buy at most one product in a day. What is the minimum total
 * price? For example, consider the following scenario (k = 3 and n = 8):
 */
public class BuyKProductsOverNDays {

    public static void main(String[] args) {
        int[][] prodPrices =  {
                {6,9,5,2,8,9,1,6},
                {8,2,6,2,7,5,7,2},
                {5,3,9,7,3,5,1,4}
        };
        int minCost = findMinCost(prodPrices);
        System.out.println(minCost);
    }

    private static int findMinCost(int[][] prodPrices) {
        int numProds = prodPrices.length;
        int numDays = prodPrices[0].length;
        int[][] cost = new int[1<< numProds][numDays];
        for(int k=0;k< numProds;k++){
            cost[1<<k][0] = prodPrices[k][0];
        }
        for(int day=1;day<numDays;day++){
            for(int s=0;s<(1<<numProds);s++){
                System.out.println("s "+ s);
                cost[s][day] = cost[s][day-1];
                for(int k=0;k<numProds;k++){
                    int product = 1 << k;
                    System.out.println(s + " "+product);
                    System.out.println(s & product);
                    if( (s & product)>0){
                        System.out.println("remaining"+ (s ^ product));
                        cost[s][day] = Math.min(cost[s][day], cost[(s ^ product)][day-1]+prodPrices[k][day] );
                    }
                }

            }
        }
        ArrayUtils.print2DArray(cost);
        return cost[(1<< numProds)-1][numDays-1];
    }
}
