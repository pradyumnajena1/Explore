package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class RangeMinQuery {
    private final int[][] sparseTable;
    int[] values;

    public static void main(String[] args) {
        RangeMinQuery minQuery = new RangeMinQuery(ArrayUtils.randomArray(10, 1, 30));
        System.out.println(minQuery.rangeMin(2, 8));
    }

    public RangeMinQuery(int[] values) {
        this.values = values;
        int i = 0;
        while (1<< i <= values.length) {
            i++;
        }

        int[][] sparseTable = new int[i][values.length];
        for (int j = 0; j < values.length; j++) {
            sparseTable[0][j] = values[j];
        }
        for (int row = 1; row < sparseTable.length; row++) {
            int windowSize = 1<<row;
            for (int j = 0; j < values.length; j++) {

                sparseTable[row][j] = Math.min(sparseTable[row - 1][j], (j + windowSize / 2 < values.length ? sparseTable[row - 1][j + windowSize / 2] : Integer.MAX_VALUE));
            }
        }
        this.sparseTable = sparseTable;
        ArrayUtils.print2DArray(sparseTable);

    }

    int rangeMin(int a, int b) {

        int row = 0;
        while (1 << (row + 1) <= (b - a + 1)) {
            row++;
        }
        int rangeSize = 1 << row;
        return Math.min(sparseTable[row][a], ( b - rangeSize + 1 >= 0 ? sparseTable[row][b - rangeSize + 1] : Integer.MAX_VALUE));
    }

}
