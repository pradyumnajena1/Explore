package guidetocompetitiveprogramming;

import epp.Pair;
import epp.array.ArrayUtils;

public class RangeSumQueryProcessor2D {

    public static void main(String[] args) {
        RangeSumQueryProcessor2D queryProcessor2D = new RangeSumQueryProcessor2D(ArrayUtils.createRandomMNMatrix(5,5,1,20));
        System.out.println(queryProcessor2D.rangeSum(new Pair<>(1, 1), new Pair<>(2, 2)));

    }
    int[][] values;

    public RangeSumQueryProcessor2D(int[][] values) {
        ArrayUtils.print2DArray(values);
        this.values = values;
        for (int j = 0; j < values[0].length; j++) {
            values[0][j] = values[0][j] + (j - 1 >= 0 ? values[0][j - 1] : 0);
        }
        for (int i = 0; i < values.length; i++) {
            values[i][0] = values[i][0] + (i - 1 >= 0 ? values[i - 1][0] : 0);
        }
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j < values[0].length; j++) {
                values[i][j] = values[i - 1][j] + values[i][j - 1] +values[i][j] - values[i - 1][j - 1];
            }
        }
        ArrayUtils.print2DArray(values);
    }

    int rangeSum(Pair<Integer, Integer> leftTop, Pair<Integer, Integer> bottomRight) {
        return rangeSum(bottomRight)
                - rangeSum(new Pair<>(leftTop.getFirst() - 1, bottomRight.getSecond()))
                - rangeSum(new Pair<>(bottomRight.getFirst(), leftTop.getSecond() - 1))
                + rangeSum(new Pair<>(leftTop.getFirst() - 1, leftTop.getSecond() - 1));
    }

    private int rangeSum(Pair<Integer, Integer> cell) {
        if (cell.getFirst() < 0 || cell.getSecond() < 0) {

            return 0;
        }
        return values[cell.getFirst()][cell.getSecond()];
    }
}
