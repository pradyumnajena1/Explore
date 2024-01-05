package guidetocompetitiveprogramming;

import commons.MatrixUtils;
import epp.array.ArrayUtils;

import java.util.Arrays;

public class FindNumPathsOfLength {
    public static void main(String[] args) {
        int[][] adjMatrix = new int[][]{
                {0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0}};

      int num =  findNumPathsOfLength(adjMatrix,4);
        System.out.println(num);

        adjMatrix = new int[][]{
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 2},
                {Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 2, Integer.MAX_VALUE}};

        num =  findNumPathsOfLength(adjMatrix,4,10);
        System.out.println(num);
    }

    public static int findNumPathsOfLength(int[][] adjMatrix, int numEdges) {
        int[][] power = MatrixUtils.matrixPower(adjMatrix, numEdges,MatrixUtils::matrixMultiply);
        ArrayUtils.print2DArray(power);
        long count = Arrays.stream(power).flatMap((int[] x) -> Arrays.stream(x).boxed()).reduce(0,Integer::sum);
        return (int) count;
    }

    public static int findNumPathsOfLength(int[][] adjMatrix, int numEdges,int maxLength) {
        int[][] power = MatrixUtils.matrixPower(adjMatrix, numEdges,MatrixUtils::matrixMultiplyForMin);

        ArrayUtils.print2DArray(power);
        long count = Arrays.stream(power).flatMap((int[] x) -> Arrays.stream(x).boxed()).filter(x->x<=maxLength).count( );
        return (int) count;
    }
}
