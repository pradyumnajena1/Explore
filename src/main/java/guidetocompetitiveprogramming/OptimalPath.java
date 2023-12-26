package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class OptimalPath {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(3, 3, 1, 50);
        ArrayUtils.print2DArray(matrix);
        int maxPathSum = findOptimalPathSum(matrix);
        System.out.println(maxPathSum);
    }

    private static int findOptimalPathSum(int[][] matrix) {
        int[][] sum = new int[matrix.length][matrix[0].length];

        for(int row=0;row<matrix.length;row++){
            for(int col=0;col<matrix[0].length;col++){
                sum[row][col] = Math.max( row-1>=0?sum[row-1][col]:0, col-1>=0? sum[row][col-1]:0)+matrix[row][col];
            }
        }
        return sum[matrix.length-1][matrix[0].length-1];
    }
}
