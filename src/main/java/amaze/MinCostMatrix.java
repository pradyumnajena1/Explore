package amaze;

import epp.Pair;

public class MinCostMatrix {
    public static void main(String[] args) {
        int[][] cost = new int[][]{
                {1,2,3},
                {4,8,2},
                {1,5,3}
        };
        int minCost = getMinCost(cost  );
        System.out.println(minCost);
    }

    private static int getMinCost(int[][] cost ) {
        int[][] minCost = new int[cost.length][cost[0].length];
        minCost[0][0] = cost[0][0];;
        for(int i=1;i<cost[0].length;i++){
            minCost[0][i] = minCost[0][i-1]+cost[0][i];
        }
        for(int i=1;i<cost.length;i++){
            minCost[i][0] = minCost[i-1][0]+cost[i][0];
        }
        for(int i=1;i<cost.length;i++){
            for(int j=1;j<cost[0].length;j++){
                minCost[i][j] =   cost[i][j] + Math.min(minCost[i-1][j-1], Math.min(minCost[i-1][j],minCost[i][j-1]));
            }
        }
        return minCost[cost.length-1][cost[0].length-1];
    }
}
