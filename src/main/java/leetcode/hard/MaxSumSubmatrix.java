package leetcode.hard;

import epp.array.ArrayUtils;

public class MaxSumSubmatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
       // System.out.println(solution.maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
        System.out.println(solution.maxSumSubmatrix(new int[][]{{2, 2, -1}}, 0));
    }

    private static class Solution {

        public int maxSumSubmatrix(int[][] matrix, int sum) {
            int[][] cumSumMatrix = new int[matrix.length][matrix[0].length];
            cumSumMatrix[0][0] = matrix[0][0];
            for(int i=1;i<matrix.length;i++){
                cumSumMatrix[i][0] = cumSumMatrix[i-1][0]+matrix[i][0];
            }
            for(int i=1;i<matrix[0].length;i++){
                cumSumMatrix[0][i] = cumSumMatrix[0][i-1]+matrix[0][i];
            }
            for(int i=1;i<cumSumMatrix.length;i++){
                for(int j=1;j<cumSumMatrix[0].length;j++){
                    cumSumMatrix[i][j] =
                            matrix[i][j]+ cumSumMatrix[i-1][j] + cumSumMatrix[i][j-1] - cumSumMatrix[i-1][j-1];
                }
            }
            ArrayUtils.print2DArray(matrix);
            ArrayUtils.print2DArray(cumSumMatrix);
            Integer maxArea = null;
            for(int i=0;i<cumSumMatrix.length;i++){
                for(int j=0;j<cumSumMatrix[0].length;j++){
                    for(int k=0;k<cumSumMatrix.length;k++){
                        for(int l=0;l<cumSumMatrix[0].length;l++){
                            if( k>=i && l>=j ){
                                int rectangularSum =
                                        cumSumMatrix[k][l] - (i>0? cumSumMatrix[i-1][l]:0)
                                                - (j>0? cumSumMatrix[k][j-1]:0) +( i>0&&j>0? cumSumMatrix[i-1][j-1]:0);

                                if(rectangularSum<=sum){
                                    if(maxArea==null){
                                        maxArea = rectangularSum;
                                    }else{

                                        maxArea = Math.max(maxArea,rectangularSum);
                                    }
                                }
                            }

                        }
                    }
                }
            }
            return maxArea==null?-1:maxArea;
        }
    }
}
