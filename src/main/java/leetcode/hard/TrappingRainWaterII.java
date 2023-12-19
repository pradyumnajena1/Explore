package leetcode.hard;

import epp.array.ArrayUtils;

public class TrappingRainWaterII {
    public static void main(String[] args) {
     Solution solution = new Solution();
        System.out.println(solution.trapRainWater(new int[][]{
                        {1, 4, 3, 1, 3, 2},
                        {3, 2, 1, 3, 2, 4},
                        {2, 3, 3, 2, 3, 1}
                }
        ));

        System.out.println(solution.trapRainWater(new int[][]{
                {3,3,3,3,3},
                {3,2,2,2,3},
                {3,2,1,2,3},
                {3,2,2,2,3},
                {3,3,3,3,3}
                }
        ));


        System.out.println(solution.trapRainWater(new int[][]{
                {12,13,1,12},
                {13,4,13,12},
                {13,8,10,12},
                {12,13,12,12},
                {13,13,13,13}
                }
        ));
    }
   private static class Solution {
        public int trapRainWater(int[][] heightMap) {
            int[][] maxFromLeft = new int[heightMap.length][heightMap[0].length];
            int[][] maxFromRight = new int[heightMap.length][heightMap[0].length];
            int[][] maxFromTop = new int[heightMap.length][heightMap[0].length];
            int[][] maxFromBottom = new int[heightMap.length][heightMap[0].length];
            for(int col=0;col<heightMap[0].length;col++){
                maxFromTop[0][col] = heightMap[0][col];
            }
            for(int row=1;row< heightMap.length;row++){
                for(int col=0;col<heightMap[0].length;col++){
                    maxFromTop[row][col] = Math.max(heightMap[row][col], maxFromTop[row-1][col]);
                }
            }

            for(int col=0;col<heightMap[0].length;col++){
                maxFromBottom[heightMap.length-1][col] = heightMap[heightMap.length-1][col];
            }
            for(int row = heightMap.length-2;row>=0;row--){
                for(int col=0;col<heightMap[0].length;col++){
                    maxFromBottom[row][col] =Math.max(heightMap[row][col],   maxFromBottom[row+1][col]);
                }
            }
            for(int row=0;row< heightMap.length;row++){
                maxFromLeft[row][0] = heightMap[row][0];
            }
            for(int col = 1;col<heightMap[0].length;col++){
                for(int row=0;row< heightMap.length;row++){
                    maxFromLeft[row][col] = Math.max( heightMap[row][col],maxFromLeft[row][col-1]);
                }
            }

            for(int row=0;row< heightMap.length;row++){
                maxFromRight[row][heightMap[0].length-1] = heightMap[row][heightMap[0].length-1];
            }
            for(int col = heightMap[0].length-2;col>=0;col--){
                for(int row=0;row< heightMap.length;row++){
                    maxFromRight[row][col] = Math.max(heightMap[row][col], maxFromRight[row][col+1]);
                }
            }
           /* ArrayUtils.print2DArray(maxFromTop);
            ArrayUtils.print2DArray(maxFromBottom);
            ArrayUtils.print2DArray(maxFromLeft);
            ArrayUtils.print2DArray(maxFromRight);*/
            int waterSum = 0;
            for(int row = 0;row< heightMap.length;row++){
                for(int col=0;col<heightMap[0].length;col++){
                    int water =Math.min(maxFromRight[row][col], Math.min(maxFromLeft[row][col],
                            Math.min(maxFromTop[row][col],
                            maxFromBottom[row][col]))) - heightMap[row][col];
                    waterSum+=water;
                }
            }

            return waterSum;
        }
    }
}
