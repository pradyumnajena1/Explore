package lfc;

import epp.array.ArrayUtils;

public class MaxAreaOfMatrix {
    public static void main(String[] args) {
        int size = 10;
        int[][] matrix = ArrayUtils.createRandomMNMatrix(size, size, 0, 2);
        ArrayUtils.print2DArray(matrix);
        int area = findMaxAreaFilledWithOne(matrix);
        System.out.println(area);
    }

    private static int findMaxAreaFilledWithOne(int[][] matrix) {
        int[][] depth = getDepthMatrix(matrix);
        int maxArea = 0;
        for (int row = 0; row < depth.length; row++) {
            for (int col = 0; col < depth[0].length; col++) {
                int[] nearestSmallerItemsOnLeft = NearestSmallerItem.findNearestSmallerItemsOnLeft(depth[row]);
                int[] nearestSmallerItemsOnRight = NearestSmallerItem.findNearestSmallerItemsOnRight(depth[row]);
                if (depth[row][col] > 0) {
                    int left = nearestSmallerItemsOnLeft[col] >= 0 ? nearestSmallerItemsOnLeft[col] : 0;
                    int right = nearestSmallerItemsOnRight[col] >= 0 ? nearestSmallerItemsOnRight[col] : nearestSmallerItemsOnRight.length - 1;
                    int width = right - 1 - left - 1 +1 ;
                    int area = width * depth[row][col];
                    if(area>maxArea){
                        maxArea = area;
                        System.out.println(row+ " "+col);
                    }

                }
            }
        }

        return maxArea;
    }

    private static int[][] getDepthMatrix(int[][] matrix) {
        int[][] depth = new int[matrix.length][matrix[0].length];

        for (int row = depth.length - 1; row >= 0; row--) {
            for (int col = depth[0].length - 1; col >= 0; col--) {

                if (row == depth.length - 1) {
                    depth[row][col] = matrix[row][col];
                } else {
                    depth[row][col] = matrix[row][col] == 0 ? 0 : depth[row + 1][col] + 1;
                }
            }
        }
        ArrayUtils.print2DArray(depth);
        return depth;
    }
}
