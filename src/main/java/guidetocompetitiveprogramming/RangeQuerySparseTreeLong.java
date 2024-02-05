package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.List;
import java.util.function.BiFunction;

public class RangeQuerySparseTreeLong {
    private final long[][] sparseTable;
    private final BiFunction<Long, Long, Long> function;
    private final long defaultValue;
    long[] values;

    public static void main(String[] args) {
        RangeQuerySparseTreeLong minQuery = new RangeQuerySparseTreeLong(ArrayUtils.randomLongArray(10, 1, 30),Long::min,Long.MAX_VALUE);
        System.out.println(minQuery.rangeQuery(2, 8));
    }
    public RangeQuerySparseTreeLong(List<Long> values, BiFunction<Long,Long,Long> function, long defaultValue) {
        this(values.stream().mapToLong(Long::longValue).toArray(),function,defaultValue);
    }

    public RangeQuerySparseTreeLong(long[] values, BiFunction<Long,Long,Long> function, long defaultValue) {
        this.values = values;
        this.function = function;
        this.defaultValue = defaultValue;
        int highestSetBit =31-Integer.numberOfLeadingZeros(values.length);
        long[][] sparseTable = new long[highestSetBit+1][values.length];
        System.arraycopy(values,0,sparseTable[0],0,values.length);

        for (int row = 1; row < sparseTable.length; row++) {
            int windowSize = 1<<row;
            for (int j = 0; j < values.length; j++) {
                sparseTable[row][j] = function.apply(sparseTable[row - 1][j], (j + windowSize / 2 < values.length ? sparseTable[row - 1][j + windowSize / 2] : defaultValue));
            }
        }
        this.sparseTable = sparseTable;
         ArrayUtils.print2DArray(sparseTable);

    }

   public long rangeQuery(int a, int b) {

        int row =  31-Integer.numberOfLeadingZeros(b-a+1);
        int rangeSize = 1 << row;
        return function.apply(sparseTable[row][a], ( b - rangeSize + 1 >= 0 ? sparseTable[row][b - rangeSize + 1] : defaultValue));
    }

}
