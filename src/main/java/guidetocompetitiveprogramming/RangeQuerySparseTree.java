package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.List;
import java.util.function.BiFunction;

public class RangeQuerySparseTree {
    private final int[][] sparseTable;
    private final BiFunction<Integer, Integer, Integer> function;
    private final int defaultValue;
    int[] values;

    public static void main(String[] args) {
        RangeQuerySparseTree minQuery = new RangeQuerySparseTree(ArrayUtils.randomArray(10, 1, 30),Integer::min,Integer.MAX_VALUE);
        System.out.println(minQuery.rangeQuery(2, 8));
    }
    public RangeQuerySparseTree(List<Integer> values, BiFunction<Integer,Integer,Integer> function,int defaultValue) {
        this(values.stream().mapToInt(Integer::intValue).toArray(),function,defaultValue);
    }

    public RangeQuerySparseTree(int[] values, BiFunction<Integer,Integer,Integer> function,int defaultValue) {
        this.values = values;
        this.function = function;
        this.defaultValue = defaultValue;
        int highestSetBit =31-Integer.numberOfLeadingZeros(values.length);
        int[][] sparseTable = new int[highestSetBit+1][values.length];
        System.arraycopy(values,0,sparseTable[0],0,values.length);

        for (int row = 1; row < sparseTable.length; row++) {
            int windowSize = 1<<row;
            for (int j = 0; j < values.length; j++) {
                sparseTable[row][j] = function.apply(sparseTable[row - 1][j], (j + windowSize / 2 < values.length ? sparseTable[row - 1][j + windowSize / 2] : Integer.MAX_VALUE));
            }
        }
        this.sparseTable = sparseTable;
        ArrayUtils.print2DArray(sparseTable);

    }

   public int rangeQuery(int a, int b) {

        int row =  31-Integer.numberOfLeadingZeros(b-a+1);
        int rangeSize = 1 << row;
        return function.apply(sparseTable[row][a], ( b - rangeSize + 1 >= 0 ? sparseTable[row][b - rangeSize + 1] : defaultValue));
    }

}
