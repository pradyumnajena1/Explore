package cph;

import epp.array.ArrayUtils;

public class Knapsack {
    public static void main(String[] args) {
        System.out.println(isPossible(new int[]{1,3,3,5}, 12));
        System.out.println(isPossible(new int[]{1,3,3,5}, 10));
        System.out.println(isPossible2(new int[]{1,3,3,5}, 10));
        System.out.println(isPossible2(new int[]{1,3,3,5}, 12));
    }

    private static boolean isPossible(int[] weights, int sum) {
        boolean[][] possible = new boolean[weights.length+1][sum+1];
        possible[0][0] = true;
        for(int i=1;i<=weights.length;i++){
            for(int j=0;j<=sum;j++){
                if(j>=weights[i-1]){
                    possible[i][j] = possible[i-1][j-weights[i-1]];
                }
                possible[i][j] = possible[i][j] || possible[i-1][j];
            }
        }
        return possible[weights.length][sum];
    }
    private static boolean isPossible2(int[] weights,int sum){
        boolean[] possible=  new boolean[sum+ 1];
        possible[0] = true;
        for (int k = 0; k < weights.length; k++) {
            for (int x = sum; x >= 0; x--) {
                if (possible[x] && x+weights[k]<=sum ) possible[x+weights[k]] = true;
            }
            ArrayUtils.printArray(possible);
        }
        return possible[sum];
    }
}
