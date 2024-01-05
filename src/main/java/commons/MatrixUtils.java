package commons;

import epp.array.ArrayUtils;

import java.util.function.BiFunction;

public class MatrixUtils {
    public static void main(String[] args) {
        int[][] power = MatrixUtils.matrixPower(new int[][]{{0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0}}, 4,MatrixUtils::matrixMultiply);
        ArrayUtils.print2DArray(power);
    }

    public static int[][] matrixMultiply(int[][] a, int[][] b) {
        assert a[0].length == b.length;
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < b.length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public static int[][] matrixMultiplyForMin(int[][] a, int[][] b) {
        assert a[0].length == b.length;
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                c[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < b.length; k++) {
                    if(!(a[i][k] ==Integer.MAX_VALUE ||  b[k][j]==Integer.MAX_VALUE)){

                        c[i][j] = Math.min(c[i][j], a[i][k] + b[k][j]);
                    }
                }
            }
        }
        return c;
    }

    public static int[][] matrixPower(int[][] a, int power, BiFunction<int[][],int[][],int[][]> multiply) {
        assert a.length == a[0].length;
        if (power == 0) {
            return getIdentityMatrix(a.length);
        }
        if (power == 1) {
            return a;
        }

        return multiply.apply(matrixPower(a, power / 2, multiply), matrixPower(a, power - power / 2,multiply));
    }

    public static int[][] getIdentityMatrix(int size) {
        int[][] identity = new int[size][size];
        for (int i = 0; i < size; i++) {
            identity[i][i] = 1;
        }
        return identity;
    }
}
