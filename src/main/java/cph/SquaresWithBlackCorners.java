package cph;

import epp.array.ArrayUtils;

public class SquaresWithBlackCorners {
    public static void main(String[] args) {
        int size = 5;
        int[][] matrix = ArrayUtils.createRandomMNMatrix(size, size, 0, 1);
        ArrayUtils.print2DArray(matrix);
        int count = findNumberOfMatrixWithBlackCorners(matrix);
        System.out.println(count);

    }

    private static int findNumberOfMatrixWithBlackCorners(int[][] matrix) {
        int count=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix.length;j++){
              count+=  findNumberOfMatrixWithBlackCorners(matrix,i,j);
            }
        }
        return count;
    }

    private static int findNumberOfMatrixWithBlackCorners(int[][] matrix, int i, int j) {
        int matchCount = 0;
        for(int k=0;k<matrix[0].length;k++){
            if(matrix[i][k]==1 && matrix[j][k]==1){
                matchCount++;
            }
        }
        return matchCount>0? matchCount*(matchCount-1)/2:0;
    }
}
