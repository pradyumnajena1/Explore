package cph;

import epp.array.ArrayUtils;

public class PathsInGrid {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(3, 3, 1, 50);
        ArrayUtils.print2DArray(matrix);
        int sum = getMaxSum(matrix);
        System.out.println(sum);
    }

    private static int getMaxSum(int[][] matrix) {
        int[][] sum = new int[matrix.length][matrix[0].length];
        sum[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            sum[i][0] = matrix[i][0]+ sum[i - 1][0];
        }
        for (int i = 1; i < matrix[0].length; i++) {
            sum[0][i] = matrix[0][i]+ sum[0][i - 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                sum[i][j] = matrix[i][j] + Math.max(sum[i - 1][j], sum[i][j - 1]);
            }
        }
        ArrayUtils.print2DArray(sum);
        return sum[matrix.length - 1][matrix[0].length - 1];
    }
}
