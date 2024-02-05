package guidetocompetitiveprogramming;

import commons.IntegerUtils;
import epp.array.ArrayUtils;

import java.util.List;
import java.util.function.BiFunction;

public class SegmentTree {
    private final int[] segmentTree;
    private final int size;
    private final BiFunction<Integer, Integer, Integer> function;
    private final int defaultValue;



    public SegmentTree(List<Integer> values, BiFunction<Integer, Integer, Integer> function, int defaultValue) {
        this(values.stream().mapToInt(Integer::intValue).toArray(), function, defaultValue);
    }

    public SegmentTree(int[] values, BiFunction<Integer, Integer, Integer> function, int defaultValue) {

        this.size = nextPowerOf2(values.length);
        this.function = function;
        this.defaultValue = defaultValue;
        int[] segmentTree = new int[2 * size];
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
         printTree( );
    }

    public void printTree() {
        StringBuilder sb = new StringBuilder();
        int a = size;
        int b = 2 * size - 1;
        while ( a>=1 && a <= b) {
            sb.append("[");
            for (int i = a; i <= b; i += 2) {
                sb.append(segmentTree[i]).append(",");
                if(a!=b){

                    sb.append(segmentTree[i + 1]).append(",");
                }

            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
            a = a / 2;
            b = b / 2;
            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }

    public int rangeResult(int lower, int higher) {
        lower += size;
        higher += size;
        int result = defaultValue;
        while (lower <= higher) {
            if (lower % 2 == 1) result = function.apply(result, segmentTree[lower++]);
            if (higher % 2 == 0) result = function.apply(result, segmentTree[higher--]);
            lower /= 2;
            higher /= 2;
        }
        return result;
    }

    public void increment(int index, int delta) {
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

    public int get(int index) {
        index += size;
        return segmentTree[index];

    }

    public int getSize() {
        return size;
    }

    private static int nextPowerOf2(int n) {
        int highestOneBit = Integer.highestOneBit(n);
        return highestOneBit == n ? highestOneBit : highestOneBit << 1;
    }
}
