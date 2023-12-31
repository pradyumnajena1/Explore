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
        int highestSetBit =31-Integer.numberOfLeadingZeros(values.length);
        int[][] sparseTable = new int[highestSetBit+1][values.length];
        System.arraycopy(values,0,sparseTable[0],0,values.length);

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

        int row =  31-Integer.numberOfLeadingZeros(b-a+1);
        int rangeSize = 1 << row;
        return Math.min(sparseTable[row][a], ( b - rangeSize + 1 >= 0 ? sparseTable[row][b - rangeSize + 1] : Integer.MAX_VALUE));
    }

}
