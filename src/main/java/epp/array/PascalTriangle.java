package epp.array;

public class PascalTriangle {
    public static void main(String[] args) {
        int[][] pascalTriangle = createPascalTriangle(15);
        ArrayUtils.print2DArray(pascalTriangle);

    }

    private static int[][] createPascalTriangle(int numRows) {

        int[][] pascalTriangle = new int[numRows][];
        pascalTriangle[0] = new int[1];
        pascalTriangle[0][0] = 1;
        for(int i=1;i<numRows;i++){
            pascalTriangle[i] = new int[i+1];
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][i] = 1;

            for(int j=1;j<i;j++){
                pascalTriangle[i][j]  = pascalTriangle[i-1][j]+pascalTriangle[i-1][j-1];
            }


        }
        return pascalTriangle;
    }
}
