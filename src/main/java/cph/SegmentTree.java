package cph;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;

public class SegmentTree {
    private final IntBinaryOperator operator;
    private final int defaultValue;
    private final int length;
    private int[] segmentTree;

    public SegmentTree(int[] values, IntBinaryOperator operator, int defaultValue) {
        this.operator = operator;
        this.defaultValue = defaultValue;
        this.length = getNext2Power(values.length);
        this.segmentTree = new int[2*length];
        System.arraycopy(values,0,segmentTree,length,values.length);
        Arrays.fill(segmentTree,length+values.length,segmentTree.length,defaultValue);
        ArrayUtils.printArray(segmentTree);
        int low = length;
        int high = 2*length-1;
        while (low<high){
            for(int i=low;i<=high;i+=2){
                segmentTree[i/2] = operator.applyAsInt(segmentTree[i],segmentTree[i+1]);
            }
            low = low/2;
            high = high/2;
        }
        ArrayUtils.printArray(segmentTree);
    }

    public int rangeResult(int lower, int higher) {
        lower += length;
        higher += length;
        int result = defaultValue;
        while (lower <= higher) {
            if (lower % 2 == 1) result = operator.applyAsInt(result, segmentTree[lower++]);
            if (higher % 2 == 0) result = operator.applyAsInt(result, segmentTree[higher--]);
            lower /= 2;
            higher /= 2;
        }
        return result;
    }

    public void increment(int index, int delta) {
        index += length;
        segmentTree[index] += delta;
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = operator.applyAsInt(segmentTree[2 * index], segmentTree[2 * index + 1]);
        }
    }

    public void set(int index, int value) {
        index += length;
        segmentTree[index] = value;
        for (index /= 2; index >= 1; index /= 2) {
            segmentTree[index] = operator.applyAsInt(segmentTree[2 * index], segmentTree[2 * index + 1]);
        }
    }

    public int get(int index) {
        index += length;
        return segmentTree[index];

    }

    private static int getNext2Power(int x) {
        int highestOneBit = Integer.highestOneBit(x);
        int length = x== highestOneBit ? x: highestOneBit *2;
        return length;
    }

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{1,2,3,4,5,6,7},Integer::sum,0);
        System.out.println(segmentTree.rangeResult(2, 6));
    }
}
