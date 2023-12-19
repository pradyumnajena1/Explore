package epp.array.revision;

import epp.array.ArrayUtils;

public class PascalTriangle {
    public static void main(String[] args) {
        int[][] pascalTriangle = getPascalTriangle(5);
        ArrayUtils.print2DArray(pascalTriangle);
    }

    private static int[][] getPascalTriangle(int n) {
        int[][] pascalTriangle = new int[n][];
        for(int i=0;i<n;i++){
            pascalTriangle[i] = new int[i+1];
        }
        pascalTriangle[0][0] = 1;
        for(int i=1;i<n;i++){
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][i] = 1;
            for(int j=1;j<i;j++){
                pascalTriangle[i][j] = pascalTriangle[i-1][j-1]+pascalTriangle[i-1][j];
            }
        }
        return pascalTriangle;
    }
}
