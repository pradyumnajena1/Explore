package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class GenericSegmentTree<T> {
    private final Object[] segmentTree;
    private final int size;
    private final BiFunction<T, T, T> function;
    private final T defaultValue;

    public static void main(String[] args) {
        GenericSegmentTree<Integer> segmentTree = new GenericSegmentTree<>(new Integer[]{5, 8, 6, 3, 2, 7, 2, 6}, Integer::sum, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 5);
        System.out.println(segmentTree.rangeResult(1, 5));
    }


    public GenericSegmentTree(List<T> values, BiFunction<T, T, T> function, T defaultValue) {
        this((T[]) values.stream().toArray(),function,defaultValue);
    }

    public GenericSegmentTree(T[] values, BiFunction<T, T, T> function, T defaultValue) {

        this.size = nextPowerOf2(values.length);
        this.function = function;
        this.defaultValue = defaultValue;
        Object[] segmentTree = new Object[2 * size];
        Arrays.fill(segmentTree,defaultValue);
        System.arraycopy(values, 0, segmentTree, size, values.length);
        int a = size;
        int b = 2 * size - 1;
        while (a < b) {
            for (int i = a; i <= b; i += 2) {
                segmentTree[i / 2] = function.apply((T) segmentTree[i], (T) segmentTree[i + 1]);
            }
            a = a / 2;
            b = b / 2;
        }
        this.segmentTree = segmentTree;
         printTree( );
    }

    public   void printTree( ) {
        ArrayUtils.printArray(segmentTree);
    }

    public T rangeResult(int lower, int higher) {
        lower += size;
        higher += size;
        T result = defaultValue;
        while (lower <= higher) {
            if (lower % 2 == 1) result = function.apply(result, (T) segmentTree[lower++]);
            if (higher % 2 == 0) result = function.apply(result, (T) segmentTree[higher--]);
            lower /= 2;
            higher /= 2;
        }
        return result;
    }

    public void increment(int index, T delta) {
        index += size;
        segmentTree[index] = function.apply((T) segmentTree[index], delta);
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = function.apply((T) segmentTree[2 * index], (T) segmentTree[2 * index + 1]);
        }
    }
    public void set(int index, T value) {
        index += size;
        segmentTree[index] = value;
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = function.apply((T) segmentTree[2 * index], (T) segmentTree[2 * index + 1]);
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
