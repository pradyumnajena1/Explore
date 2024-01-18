package guidetocompetitiveprogramming;

import commons.IntegerUtils;
import epp.array.ArrayUtils;

import java.util.function.BiFunction;

public class SegmentTreeLong {
    private final long[] segmentTree;
    private final int size;
    private final BiFunction<Long, Long, Long> function;
    private final long defaultValue;

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Integer::sum, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 5);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Math::min, Integer.MAX_VALUE);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, -5);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Math::max, Integer.MIN_VALUE);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, IntegerUtils::gcd, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x & y, -1);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x | y, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x ^ y, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));
    }

    public SegmentTreeLong(long[] values, BiFunction<Long, Long, Long> function, long defaultValue) {

        this.size = nextPowerOf2(values.length);
        this.function = function;
        this.defaultValue = defaultValue;
        long[] segmentTree = new long[2 * size];
        System.arraycopy(values, 0, segmentTree, size, values.length);
        int a = size;
        int b = 2 * size - 1;
        while (a < b) {
            for (int i = a; i <= b; i += 2) {
                segmentTree[i / 2] = function.apply(segmentTree[i], segmentTree[i + 1]);
            }
            a = a / 2;
            b = b / 2;
        }
        this.segmentTree = segmentTree;
        // printTree( );
    }

    public   void printTree( ) {
        ArrayUtils.printArray(segmentTree);
    }

    public long rangeResult(int lower, int higher) {
        lower += size;
        higher += size;
        long result = defaultValue;
        while (lower <= higher) {
            if (lower % 2 == 1) result = function.apply(result, segmentTree[lower++]);
            if (higher % 2 == 0) result = function.apply(result, segmentTree[higher--]);
            lower /= 2;
            higher /= 2;
        }
        return result;
    }

    public void increment(int index, long delta) {
        index += size;
        segmentTree[index] += delta;
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = function.apply(segmentTree[2 * index], segmentTree[2 * index + 1]);
        }
    }
    public void set(int index, int value) {
        index += size;
        segmentTree[index] = value;
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = function.apply(segmentTree[2 * index], segmentTree[2 * index + 1]);
        }
    }

    public int getSize() {
        return size;
    }

    private static int nextPowerOf2(int n) {
        int highestOneBit = Integer.highestOneBit(n);
        return highestOneBit == n ? highestOneBit : highestOneBit << 1;
    }
}
